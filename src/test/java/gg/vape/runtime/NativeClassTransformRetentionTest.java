package gg.vape.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.Assume;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class NativeClassTransformRetentionTest {
    private static final byte[] CALLBACK_MARKER =
            "gg/vape/native-retention-test".getBytes(
                    StandardCharsets.US_ASCII);

    @Test
    public void retainsCallbacksAcrossRetransformAndClearsOnRollback()
            throws Exception {
        String nativeLibrary = System.getProperty("vape.native.dll");
        Assume.assumeTrue(nativeLibrary != null && !nativeLibrary.isEmpty());
        MarkerClassLoader markerLoader = new MarkerClassLoader(
                getClass().getClassLoader());
        Class<?> minecraftMarker = markerLoader.defineMarker("ave");
        Class<?> badlionMarker = markerLoader.defineMarker(
                "net.badlion.client.Wrapper");
        assertEquals("ave", minecraftMarker.getName());
        assertEquals("net.badlion.client.Wrapper", badlionMarker.getName());
        System.load(nativeLibrary);

        byte[] original = readClassBytes(Fixture.class);
        byte[] transformed = transformFixture(original);
        try {
            assertEquals(0, NativeBridge.scb(Fixture.class, transformed));
            assertEquals(2, Fixture.value());

            byte[] retained = NativeBridge.gcb(Fixture.class);
            assertNotNull(retained);
            assertTrue(contains(retained, CALLBACK_MARKER));
            assertEquals(2, Fixture.value());
        }
        finally {
            assertEquals(0, NativeBridge.scb(Fixture.class, original));
        }

        byte[] rolledBack = NativeBridge.gcb(Fixture.class);
        assertNotNull(rolledBack);
        assertFalse(contains(rolledBack, CALLBACK_MARKER));
        assertEquals(1, Fixture.value());
    }

    private static byte[] transformFixture(byte[] original) {
        ClassNode classNode = new ClassNode();
        new ClassReader(original).accept(classNode, 0);
        MethodNode valueMethod = null;
        for (MethodNode method : classNode.methods) {
            if ("value".equals(method.name) && "()I".equals(method.desc)) {
                valueMethod = method;
                break;
            }
        }
        assertNotNull(valueMethod);
        InsnList instructions = new InsnList();
        instructions.add(new LdcInsnNode("gg/vape/native-retention-test"));
        instructions.add(new InsnNode(Opcodes.POP));
        instructions.add(new InsnNode(Opcodes.ICONST_2));
        instructions.add(new InsnNode(Opcodes.IRETURN));
        valueMethod.instructions = instructions;
        valueMethod.tryCatchBlocks.clear();
        valueMethod.localVariables = null;
        ClassWriter writer = new ClassWriter(
                ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classNode.accept(writer);
        return writer.toByteArray();
    }

    private static byte[] createMarkerClass(String binaryName) {
        ClassWriter writer = new ClassWriter(0);
        writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,
                binaryName.replace('.', '/'), null, "java/lang/Object", null);
        writer.visitEnd();
        return writer.toByteArray();
    }

    private static byte[] readClassBytes(Class<?> type) throws Exception {
        String resource = "/" + type.getName().replace('.', '/') + ".class";
        try (InputStream input = type.getResourceAsStream(resource)) {
            assertNotNull(input);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int count;
            while ((count = input.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }
            return output.toByteArray();
        }
    }

    private static boolean contains(byte[] bytes, byte[] sequence) {
        for (int offset = 0; offset <= bytes.length - sequence.length;
             ++offset) {
            int index = 0;
            while (index < sequence.length
                    && bytes[offset + index] == sequence[index]) {
                ++index;
            }
            if (index == sequence.length) {
                return true;
            }
        }
        return false;
    }

    public static final class Fixture {
        private Fixture() {
        }

        public static int value() {
            return 1;
        }
    }

    private static final class MarkerClassLoader extends ClassLoader {
        private MarkerClassLoader(ClassLoader parent) {
            super(parent);
        }

        private Class<?> defineMarker(String binaryName) {
            byte[] bytecode = createMarkerClass(binaryName);
            return defineClass(binaryName, bytecode, 0, bytecode.length);
        }
    }
}
