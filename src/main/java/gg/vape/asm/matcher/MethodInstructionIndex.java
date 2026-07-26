package gg.vape.asm.matcher;

import gg.vape.asm.matcher.FieldInsnPattern;
import gg.vape.asm.matcher.InstructionPattern;
import gg.vape.asm.matcher.MethodInsnPattern;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class MethodInstructionIndex {
    private List<InstructionPattern> v = new ArrayList<InstructionPattern>();
    private MethodNode z;
    private static String[] c;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void s$src$V$s4j71d() {
        InsnList insnList = this.z.instructions;
        ListIterator<AbstractInsnNode> listIterator = insnList.iterator();
        while (listIterator.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode)listIterator.next();
            this.C(abstractInsnNode);
        }
    }

    static {
        if (MethodInstructionIndex.k() == null) {
            MethodInstructionIndex.J(new String[2]);
        }
    }

    public static String[] k() {
        return c;
    }

    public List<InstructionPattern> s() {
        return this.v;
    }

    public static void J(String[] stringArray) {
        c = stringArray;
    }

    public MethodInstructionIndex(MethodNode methodNode) {
        this.z = methodNode;
    }

    private void C(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode instanceof MethodInsnNode) {
            this.v.add(MethodInsnPattern.u((MethodInsnNode)abstractInsnNode));
        }
        if (abstractInsnNode instanceof FieldInsnNode) {
            this.v.add(FieldInsnPattern.o((FieldInsnNode)abstractInsnNode));
        }
    }
}
