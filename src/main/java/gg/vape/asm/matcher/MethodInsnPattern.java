package gg.vape.asm.matcher;

import gg.vape.asm.matcher.InstructionPattern;
import org.objectweb.asm.tree.MethodInsnNode;

public class MethodInsnPattern
extends InstructionPattern {
    public MethodInsnPattern(int n, String string, String string2, String string3) {
        super(n, string, string2, string3);
    }

    public static MethodInsnPattern u(MethodInsnNode methodInsnNode) {
        return new MethodInsnPattern(methodInsnNode.getOpcode(), methodInsnNode.owner, methodInsnNode.name, methodInsnNode.desc);
    }
}
