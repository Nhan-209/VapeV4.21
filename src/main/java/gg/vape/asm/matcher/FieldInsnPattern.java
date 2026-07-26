package gg.vape.asm.matcher;

import gg.vape.asm.matcher.InstructionPattern;
import gg.vape.mapping.MappingField;
import org.objectweb.asm.tree.FieldInsnNode;

public class FieldInsnPattern
extends InstructionPattern {
    public FieldInsnPattern(int n, String string, String string2, String string3) {
        super(n, string, string2, string3);
    }

    public static FieldInsnPattern o(FieldInsnNode fieldInsnNode) {
        return new FieldInsnPattern(fieldInsnNode.getOpcode(), fieldInsnNode.owner, fieldInsnNode.name, fieldInsnNode.desc);
    }

    public static FieldInsnPattern l(int n, MappingField mappingField) {
        return new FieldInsnPattern(n, mappingField.L().getName(), mappingField.J(), mappingField.w());
    }
}
