//! Class bytecode parsing and target matching.
//! Inspects JVM constant pools to identify class definitions safely and accurately.

/// Check if a byte slice contains a substring needle.
pub fn contains_bytes(haystack: &[u8], needle: &[u8]) -> bool {
    if needle.is_empty() || haystack.len() < needle.len() {
        return false;
    }
    haystack.windows(needle.len()).any(|w| w == needle)
}

/// Check if bytecode contains references to Vape callbacks (`gg/vape/` or `gg.vape.`).
pub fn contains_vape_callback(bytes: &[u8]) -> bool {
    contains_bytes(bytes, b"gg/vape/") || contains_bytes(bytes, b"gg.vape.")
}

/// Read a big-endian u16 from a byte slice at an offset, advancing the offset.
fn read_u16(bytes: &[u8], offset: &mut usize) -> Option<u16> {
    if *offset + 2 > bytes.len() {
        return None;
    }
    let val = u16::from_be_bytes([bytes[*offset], bytes[*offset + 1]]);
    *offset += 2;
    Some(val)
}

/// Extract the binary class name (e.g. "net/minecraft/client/gui/Gui") from raw classfile bytecode.
pub fn parse_class_name(bytes: &[u8]) -> Option<String> {
    if bytes.len() < 10 || &bytes[0..4] != &[0xCA, 0xFE, 0xBA, 0xBE] {
        return None;
    }

    let mut offset = 8;
    let constant_pool_count = read_u16(bytes, &mut offset)? as usize;
    if constant_pool_count < 2 {
        return None;
    }

    // Vector to store CP entries.
    // We store:
    // - Class info: name_index (u16)
    // - Utf8 info: byte slice range or reference
    enum CpEntry<'a> {
        None,
        Class(u16),
        Utf8(&'a [u8]),
    }

    let mut cp = Vec::with_capacity(constant_pool_count);
    cp.push(CpEntry::None); // CP is 1-indexed

    let mut entry_index = 1;
    while entry_index < constant_pool_count {
        if offset >= bytes.len() {
            return None;
        }
        let tag = bytes[offset];
        offset += 1;

        match tag {
            1 => {
                // CONSTANT_Utf8
                let utf8_len = read_u16(bytes, &mut offset)? as usize;
                if offset + utf8_len > bytes.len() {
                    return None;
                }
                let slice = &bytes[offset..offset + utf8_len];
                offset += utf8_len;
                cp.push(CpEntry::Utf8(slice));
                entry_index += 1;
            }
            3 | 4 => {
                // CONSTANT_Integer / Float
                if offset + 4 > bytes.len() {
                    return None;
                }
                offset += 4;
                cp.push(CpEntry::None);
                entry_index += 1;
            }
            5 | 6 => {
                // CONSTANT_Long / Double (occupies 2 entries in CP)
                if offset + 8 > bytes.len() {
                    return None;
                }
                offset += 8;
                cp.push(CpEntry::None);
                cp.push(CpEntry::None);
                entry_index += 2;
            }
            7 => {
                // CONSTANT_Class
                let name_index = read_u16(bytes, &mut offset)?;
                cp.push(CpEntry::Class(name_index));
                entry_index += 1;
            }
            8 | 16 | 19 | 20 => {
                // CONSTANT_String, MethodType, Module, Package
                if offset + 2 > bytes.len() {
                    return None;
                }
                offset += 2;
                cp.push(CpEntry::None);
                entry_index += 1;
            }
            9 | 10 | 11 | 12 | 17 | 18 => {
                // Fieldref, Methodref, InterfaceMethodref, NameAndType, Dynamic, InvokeDynamic
                if offset + 4 > bytes.len() {
                    return None;
                }
                offset += 4;
                cp.push(CpEntry::None);
                entry_index += 1;
            }
            15 => {
                // CONSTANT_MethodHandle
                if offset + 3 > bytes.len() {
                    return None;
                }
                offset += 3;
                cp.push(CpEntry::None);
                entry_index += 1;
            }
            _ => {
                return None;
            }
        }
    }

    if offset + 6 > bytes.len() {
        return None;
    }

    // Skip access_flags (u16)
    offset += 2;
    let this_class_index = read_u16(bytes, &mut offset)? as usize;
    if this_class_index == 0 || this_class_index >= cp.len() {
        return None;
    }

    if let CpEntry::Class(name_index) = cp[this_class_index] {
        let name_idx = name_index as usize;
        if name_idx > 0 && name_idx < cp.len() {
            if let CpEntry::Utf8(name_bytes) = cp[name_idx] {
                return std::str::from_utf8(name_bytes).ok().map(|s| s.to_string());
            }
        }
    }

    None
}

/// Check if class bytecode matches a JNI class signature (e.g. "Lnet/minecraft/client/gui/Gui;").
pub fn class_data_matches_signature(bytes: &[u8], signature: &str) -> bool {
    let expected_name = if signature.starts_with('L') && signature.ends_with(';') {
        &signature[1..signature.len() - 1]
    } else if signature.starts_with('L') {
        &signature[1..]
    } else {
        signature
    };

    if let Some(parsed_name) = parse_class_name(bytes) {
        parsed_name == expected_name
    } else {
        false
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_contains_vape_callback() {
        assert!(contains_vape_callback(b"invoke gg/vape/event/Listener"));
        assert!(contains_vape_callback(b"call gg.vape.runtime.Bridge"));
        assert!(!contains_vape_callback(b"net/minecraft/client/Minecraft"));
    }

    #[test]
    fn test_class_parser() {
        // Minimal valid classfile bytes
        // CA FE BA BE, minor=0, major=52
        // CP count = 3
        // CP[1] = Utf8 "TestClass"
        // CP[2] = Class CP[1]
        // access_flags = 0x0001
        // this_class = CP[2]
        // super_class = 0
        let mut class_bytes = vec![
            0xCA, 0xFE, 0xBA, 0xBE, // magic
            0x00, 0x00, // minor
            0x00, 0x34, // major 52
            0x00, 0x03, // cp count = 3
            0x01, // tag 1: Utf8
            0x00, 0x09, // len = 9
            b'T', b'e', b's', b't', b'C', b'l', b'a', b's', b's',
            0x07, // tag 7: Class
            0x00, 0x01, // name_index = 1
            0x00, 0x01, // access flags
            0x00, 0x02, // this_class = 2
            0x00, 0x00, // super_class
            0x00, 0x00, // interfaces count
            0x00, 0x00, // fields count
            0x00, 0x00, // methods count
            0x00, 0x00, // attributes count
        ];

        let name = parse_class_name(&class_bytes);
        assert_eq!(name.as_deref(), Some("TestClass"));
        assert!(class_data_matches_signature(&class_bytes, "LTestClass;"));
        assert!(!class_data_matches_signature(&class_bytes, "LOtherClass;"));
    }
}
