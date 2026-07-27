package gg.vape.asm.matcher;

import gg.vape.asm.matcher.InstructionPattern;
import gg.vape.asm.matcher.MethodInstructionIndex;
import gg.vape.asm.matcher.MethodNodeMatcher;
import java.util.List;
import org.objectweb.asm.tree.MethodNode;

public class InstructionSequenceMethodMatcher
extends MethodNodeMatcher {
    private static int S;
    private final InstructionPattern[] e;

    public static int C() {
        return S;
    }

    static {
        if (InstructionSequenceMethodMatcher.J() != 0) {
            InstructionSequenceMethodMatcher.m(89);
        }
    }

    public static void m(int n) {
        S = n;
    }

    public static int J() {
        int n = InstructionSequenceMethodMatcher.C();
        if (n == 0) {
            return 77;
        }
        return 0;
    }

    public InstructionSequenceMethodMatcher(Class clazz, InstructionPattern ... instructionPatternArray) {
        super(clazz);
        this.e = instructionPatternArray;
    }


    @Override
    public boolean matchesMethod(MethodNode methodNode) {
        MethodInstructionIndex methodInstructionIndex = this.getMethodInstructionIndex(methodNode);
        int n = 0;
        int n2 = 0;
        List<InstructionPattern> list = methodInstructionIndex.s();
        block0: for (InstructionPattern instructionPattern : this.e) {
            for (int i = n2; i < list.size(); ++i) {
                n2 = i;
                InstructionPattern instructionPattern2 = list.get(i);
                if (!instructionPattern.matches(instructionPattern2)) continue;
                ++n;
                continue block0;
            }
        }
        boolean bl = n == this.e.length;
        return bl;
    }
}

