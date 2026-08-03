package gg.vape.reflect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Vanilla189MappingsTest {
    @Test
    public void detectsOriginalObfuscatedRuntime() {
        Map<String, byte[]> classes = new LinkedHashMap<String, byte[]>();
        classes.put("ave", minecraftClass("ave", "A", "S"));
        classes.put("bew", emptyClass("bew"));
        classes.put("adm", emptyClass("adm"));

        assertTrue(Vanilla189Mappings.isRuntimePresent(
                new FixtureClassLoader(classes)));
    }

    @Test
    public void detectsLunarNamedRuntime() {
        assertTrue(Vanilla189Mappings.isRuntimePresent(
                new FixtureClassLoader(namedRuntime(false, false))));
    }

    @Test
    public void rejectsIncompleteNamedRuntime() {
        assertFalse(Vanilla189Mappings.isRuntimePresent(
                new FixtureClassLoader(namedRuntime(true, false))));
    }

    @Test
    public void rejectsModernNamedRuntime() {
        assertFalse(Vanilla189Mappings.isRuntimePresent(
                new FixtureClassLoader(namedRuntime(false, true))));
    }

    private static Map<String, byte[]> namedRuntime(
            boolean omitWorldRenderer, boolean includeModernBlockPos) {
        Map<String, byte[]> classes = new LinkedHashMap<String, byte[]>();
        classes.put("net.minecraft.client.Minecraft", minecraftClass(
                "net/minecraft/client/Minecraft", "getMinecraft", "theMinecraft"));
        if (!omitWorldRenderer) {
            classes.put("net.minecraft.client.renderer.WorldRenderer", emptyClass(
                    "net/minecraft/client/renderer/WorldRenderer"));
        }
        classes.put("net.minecraft.util.BlockPos", emptyClass(
                "net/minecraft/util/BlockPos"));
        if (includeModernBlockPos) {
            classes.put("net.minecraft.util.math.BlockPos", emptyClass(
                    "net/minecraft/util/math/BlockPos"));
        }
        return classes;
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

        FixtureClassLoader(Map<String, byte[]> classes) {
            super(Vanilla189MappingsTest.class.getClassLoader());
            this.classes = classes;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] bytecode = this.classes.get(name);
            if (bytecode == null) {
                throw new ClassNotFoundException(name);
            }
            return this.defineClass(name, bytecode, 0, bytecode.length);
        }
    }
}
