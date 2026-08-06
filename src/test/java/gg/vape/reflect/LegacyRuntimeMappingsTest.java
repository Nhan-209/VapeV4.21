package gg.vape.reflect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gg.vape.runtime.NativeBridge;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
    public void detectsOriginal1206ObfuscatedRuntime() throws Exception {
        Map<String, byte[]> classes = obfuscatedRuntime(
                "ffh", "Q", "E", "fxx", "gcs", "gcp", "dca");
        assertDetectedOnly(50, classes);

        FixtureClassLoader loader = new FixtureClassLoader(classes);
        Class<?> minecraftClass = loader.loadClass("ffh");
        assertEquals("ffh", Vanilla1206Mappings.remapClassName(
                "net/minecraft/client/Minecraft"));
        assertEquals("f_90981_", Vanilla1206Mappings.lookupFieldSrgName(
                minecraftClass.getDeclaredField("E")));
        assertEquals("m_91087_", Vanilla1206Mappings.lookupMethodSrgName(
                minecraftClass.getDeclaredMethod("Q")));
        Thread thread = Thread.currentThread();
        ClassLoader previousLoader = thread.getContextClassLoader();
        Field cachedVersion = NativeBridge.class.getDeclaredField(
                "vanillaMappingVersion");
        cachedVersion.setAccessible(true);
        cachedVersion.setInt(null, 0);
        try {
            thread.setContextClassLoader(loader);
            assertEquals(50, NativeBridge.gmv());
        }
        finally {
            thread.setContextClassLoader(previousLoader);
            cachedVersion.setInt(null, 0);
        }
    }

    @Test
    public void resolvesOriginal1206TickHookMethods() throws Exception {
        Map<String, byte[]> classes = new LinkedHashMap<String, byte[]>();
        classes.put("dca", emptyClass("dca"));
        classes.put("cmz", emptyClass("cmz"));
        classes.put("bqv", emptyClass("bqv"));
        classes.put("bqx", emptyClass("bqx"));
        classes.put("dbj$b", emptyClass("dbj$b"));
        classes.put("evp", emptyClass("evp"));
        classes.put("csx", methodFixture("csx", "a",
                "(Ldca;Lcmz;Lbqv;)Lbqx;"));
        classes.put("cum", methodFixture("cum", "a",
                "(Ldca;Lcmz;Ldbj$b;)Levp;"));

        FixtureClassLoader loader = new FixtureClassLoader(classes);
        Class<?> level = loader.loadClass("dca");
        Class<?> player = loader.loadClass("cmz");
        Method bucketUse = loader.loadClass("csx").getDeclaredMethod("a",
                level, player, loader.loadClass("bqv"));
        Method playerPovHitResult = loader.loadClass("cum").getDeclaredMethod(
                "a", level, player, loader.loadClass("dbj$b"));

        assertEquals("m_7203_",
                Vanilla1206Mappings.lookupMethodSrgName(bucketUse));
        assertEquals("m_41435_",
                Vanilla1206Mappings.lookupMethodSrgName(playerPovHitResult));
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
            String... anchors) {
        Map<String, byte[]> classes = new LinkedHashMap<String, byte[]>();
        classes.put(minecraftName, minecraftClass(
                minecraftName, getterName, instanceFieldName));
        for (String anchor : anchors) {
            addEmptyClass(classes, anchor);
        }
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
        assertTrue(expectedVersion == 50
                ? Vanilla1206Mappings.isRuntimePresent(loader)
                : !Vanilla1206Mappings.isRuntimePresent(loader));
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

    private static byte[] methodFixture(
            String internalName, String methodName, String descriptor) {
        ClassWriter writer = new ClassWriter(0);
        writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, internalName,
                null, "java/lang/Object", null);
        writer.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_NATIVE,
                methodName, descriptor,
                null, null).visitEnd();
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
