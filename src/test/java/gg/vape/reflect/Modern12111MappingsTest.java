package gg.vape.reflect;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Modern12111MappingsTest {
    @Test
    public void resolvesVanillaItemStackEmptyMethod() throws Exception {
        Class<?> owner = loadFixture("dlt", "f", null);
        Method method = owner.getDeclaredMethod("f");

        assertEquals("dlt", Vanilla12111Mappings.remapClassName(
                "net/minecraft/world/item/ItemStack"));
        assertEquals("m_41619_",
                Vanilla12111Mappings.lookupMethodSrgName(method));
    }

    @Test
    public void resolvesFabricItemStackEmptyMethod() throws Exception {
        Class<?> owner = loadFixture(
                "net/minecraft/class_1799", "method_7960", null);
        Method method = owner.getDeclaredMethod("method_7960");

        assertEquals("net/minecraft/class_1799",
                Fabric12111Mappings.remapClassName(
                        "net/minecraft/world/item/ItemStack"));
        assertEquals("m_41619_",
                Fabric12111Mappings.lookupMethodSrgName(method));
    }

    @Test
    public void resolvesVanillaAttributeModifierAmountRecordComponent()
            throws Exception {
        Class<?> owner = loadFixture("ciq", "b", "e");
        Method accessor = owner.getDeclaredMethod("b");
        Field field = owner.getDeclaredField("e");

        assertEquals("f_22190_",
                Vanilla12111Mappings.lookupMethodSrgName(accessor));
        assertEquals("f_22190_",
                Vanilla12111Mappings.lookupFieldSrgName(field));
    }

    @Test
    public void resolvesFabricAttributeModifierAmountRecordComponent()
            throws Exception {
        Class<?> owner = loadFixture("net/minecraft/class_1322",
                "comp_2449", "comp_2449");
        Method accessor = owner.getDeclaredMethod("comp_2449");
        Field field = owner.getDeclaredField("comp_2449");

        assertEquals("f_22190_",
                Fabric12111Mappings.lookupMethodSrgName(accessor));
        assertEquals("f_22190_",
                Fabric12111Mappings.lookupFieldSrgName(field));
    }

    private static Class<?> loadFixture(
            String internalName, String methodName, String fieldName)
            throws Exception {
        Map<String, byte[]> classes = new LinkedHashMap<String, byte[]>();
        classes.put(internalName.replace('/', '.'),
                fixtureClass(internalName, methodName, fieldName));
        FixtureClassLoader loader = new FixtureClassLoader(classes);
        return loader.loadClass(internalName.replace('/', '.'));
    }

    private static byte[] fixtureClass(
            String internalName, String methodName, String fieldName) {
        ClassWriter writer = new ClassWriter(0);
        writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, internalName,
                null, "java/lang/Object", null);
        if (fieldName != null) {
            writer.visitField(Opcodes.ACC_PUBLIC, fieldName, "D", null, null)
                    .visitEnd();
        }
        MethodVisitor method = writer.visitMethod(
                Opcodes.ACC_PUBLIC, methodName,
                fieldName == null ? "()Z" : "()D", null, null);
        method.visitCode();
        if (fieldName == null) {
            method.visitInsn(Opcodes.ICONST_0);
            method.visitInsn(Opcodes.IRETURN);
            method.visitMaxs(1, 1);
        } else {
            method.visitVarInsn(Opcodes.ALOAD, 0);
            method.visitFieldInsn(Opcodes.GETFIELD, internalName, fieldName, "D");
            method.visitInsn(Opcodes.DRETURN);
            method.visitMaxs(2, 1);
        }
        method.visitEnd();
        writer.visitEnd();
        return writer.toByteArray();
    }

    private static final class FixtureClassLoader extends ClassLoader {
        private final Map<String, byte[]> classes;

        FixtureClassLoader(Map<String, byte[]> classes) {
            super(Modern12111MappingsTest.class.getClassLoader());
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
