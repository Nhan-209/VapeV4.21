package gg.vape.reflect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LegacyRuntimeMappingsTest {
    @Test
    public void detectsOriginal1710ObfuscatedRuntime() {
        assertDetectedOnly(13, obfuscatedRuntime(
                "bao", "B", "M", "blk", "ahb"));
    }

    @Test
    public void detectsOriginal189ObfuscatedRuntime() {
        assertDetectedOnly(15, obfuscatedRuntime(
                "ave", "A", "S", "bew", "adm"));
    }

    @Test
    public void detectsBadlion189Runtime() {
        FixtureClassLoader loader = new FixtureClassLoader(obfuscatedRuntime(
                "ave", "A", "S", "bew", "adm"), true);

        assertTrue(Badlion189Mappings.isRuntimePresent(loader));
    }

    @Test
    public void doesNotClassifyPlain189AsBadlion() {
        FixtureClassLoader loader = new FixtureClassLoader(obfuscatedRuntime(
                "ave", "A", "S", "bew", "adm"));

        assertFalse(Badlion189Mappings.isRuntimePresent(loader));
    }

    @Test
    public void detectsOriginal1122ObfuscatedRuntime() {
        assertDetectedOnly(23, obfuscatedRuntime(
                "bib", "z", "R", "bud", "amu"));
    }

    @Test
    public void detectsOnlyNamed1710Runtime() {
        Map<String, byte[]> classes = namedMinecraftRuntime();
        addEmptyClass(classes, "net/minecraft/client/renderer/WorldRenderer");
        addEmptyClass(classes, "net/minecraft/network/NetworkManager");
        addEmptyClass(classes, "net/minecraft/util/ChunkCoordinates");
        assertDetectedOnly(13, classes);
    }

    @Test
    public void detectsOnlyNamed189Runtime() {
        Map<String, byte[]> classes = namedMinecraftRuntime();
        addEmptyClass(classes, "net/minecraft/client/renderer/WorldRenderer");
        addEmptyClass(classes, "net/minecraft/client/renderer/vertex/VertexFormat");
        addEmptyClass(classes, "net/minecraft/util/BlockPos");
        assertDetectedOnly(15, classes);
    }

    @Test
    public void detectsOnlyNamed1122Runtime() {
        Map<String, byte[]> classes = namedMinecraftRuntime("instance");
        addEmptyClass(classes, "net/minecraft/client/renderer/BufferBuilder");
        addEmptyClass(classes, "net/minecraft/network/play/client/CPacketPlayer");
        addEmptyClass(classes, "net/minecraft/util/math/BlockPos");
        assertDetectedOnly(23, classes);
    }

    @Test
    public void rejectsNamedRuntimeWithWrongMinecraftStructure() {
        Map<String, byte[]> classes = new LinkedHashMap<String, byte[]>();
        addEmptyClass(classes, "net/minecraft/client/Minecraft");
        addEmptyClass(classes, "net/minecraft/client/renderer/WorldRenderer");
        addEmptyClass(classes, "net/minecraft/client/renderer/vertex/VertexFormat");
        addEmptyClass(classes, "net/minecraft/util/BlockPos");
        FixtureClassLoader loader = new FixtureClassLoader(classes);

        assertFalse(Vanilla1710Mappings.isRuntimePresent(loader));
        assertFalse(Vanilla189Mappings.isRuntimePresent(loader));
        assertFalse(Vanilla1122Mappings.isRuntimePresent(loader));
    }

    private static Map<String, byte[]> obfuscatedRuntime(
            String minecraftName, String getterName, String instanceFieldName,
            String firstAnchor, String secondAnchor) {
        Map<String, byte[]> classes = new LinkedHashMap<String, byte[]>();
        classes.put(minecraftName, minecraftClass(
                minecraftName, getterName, instanceFieldName));
        addEmptyClass(classes, firstAnchor);
        addEmptyClass(classes, secondAnchor);
        return classes;
    }

    private static Map<String, byte[]> namedMinecraftRuntime() {
        return namedMinecraftRuntime("theMinecraft");
    }

    private static Map<String, byte[]> namedMinecraftRuntime(
            String instanceFieldName) {
        Map<String, byte[]> classes = new LinkedHashMap<String, byte[]>();
        classes.put("net.minecraft.client.Minecraft", minecraftClass(
                "net/minecraft/client/Minecraft",
                "getMinecraft", instanceFieldName));
        return classes;
    }

    private static void assertDetectedOnly(
            int expectedVersion, Map<String, byte[]> classes) {
        FixtureClassLoader loader = new FixtureClassLoader(classes);
        assertTrue(expectedVersion == 13
                ? Vanilla1710Mappings.isRuntimePresent(loader)
                : !Vanilla1710Mappings.isRuntimePresent(loader));
        assertTrue(expectedVersion == 15
                ? Vanilla189Mappings.isRuntimePresent(loader)
                : !Vanilla189Mappings.isRuntimePresent(loader));
        assertTrue(expectedVersion == 23
                ? Vanilla1122Mappings.isRuntimePresent(loader)
                : !Vanilla1122Mappings.isRuntimePresent(loader));
    }

    private static void addEmptyClass(
            Map<String, byte[]> classes, String internalName) {
        classes.put(internalName.replace('/', '.'), emptyClass(internalName));
    }

    private static byte[] minecraftClass(
            String internalName, String getterName, String instanceFieldName) {
        String descriptor = "L" + internalName + ";";
        ClassWriter writer = new ClassWriter(0);
        writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, internalName,
                null, "java/lang/Object", null);
        writer.visitField(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                instanceFieldName, descriptor, null, null).visitEnd();
        MethodVisitor getter = writer.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                getterName, "()" + descriptor, null, null);
        getter.visitCode();
        getter.visitFieldInsn(Opcodes.GETSTATIC, internalName,
                instanceFieldName, descriptor);
        getter.visitInsn(Opcodes.ARETURN);
        getter.visitMaxs(1, 0);
        getter.visitEnd();
        writer.visitEnd();
        return writer.toByteArray();
    }

    private static byte[] emptyClass(String internalName) {
        ClassWriter writer = new ClassWriter(0);
        writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, internalName,
                null, "java/lang/Object", null);
        writer.visitEnd();
        return writer.toByteArray();
    }

    private static final class FixtureClassLoader extends ClassLoader {
        private final Map<String, byte[]> classes;
        private final boolean badlionMarkers;

        FixtureClassLoader(Map<String, byte[]> classes) {
            this(classes, false);
        }

        FixtureClassLoader(Map<String, byte[]> classes,
                           boolean badlionMarkers) {
            super(LegacyRuntimeMappingsTest.class.getClassLoader());
            this.classes = classes;
            this.badlionMarkers = badlionMarkers;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] bytecode = this.classes.get(name);
            if (bytecode == null) {
                throw new ClassNotFoundException(name);
            }
            return this.defineClass(name, bytecode, 0, bytecode.length);
        }

        @Override
        protected URL findResource(String name) {
            if (!this.badlionMarkers
                    || !"net/badlion/client/Wrapper.class".equals(name)) {
                return null;
            }
            try {
                return new URL("file", "", "/" + name);
            }
            catch (MalformedURLException ignored) {
                return null;
            }
        }
    }
}
