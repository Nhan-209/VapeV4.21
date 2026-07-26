package gg.vape.asm.matcher;

import gg.vape.asm.matcher.ClassMethodReferenceIndex;
import gg.vape.asm.matcher.ClassNodeCache;
import gg.vape.asm.matcher.MethodInstructionIndex;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.Iterator;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public abstract class MethodNodeMatcher {
    private static String X;
    private final Class C;
    private final ClassMethodReferenceIndex t;
    private final ClassNode y;

    public String findMethodDescriptor(String string) {
        Iterator<MethodNode> iterator = this.y.methods.iterator();
        while (iterator.hasNext()) {
            MethodNode methodNode;
            MethodNode methodNode2 = methodNode = iterator.next();
            if (!string.equals(methodNode2.desc) || !this.matchesMethod(methodNode2)) continue;
            return methodNode2.name;
        }
        return null;
    }

    public abstract boolean matchesMethod(MethodNode var1);

    public MethodNodeMatcher(Class clazz) {
        this.C = clazz;
        this.y = ClassNodeCache.j(clazz);
        this.t = ClassNodeCache.g(clazz);
    }

    public MethodInstructionIndex getMethodInstructionIndex(MethodNode methodNode) {
        return this.t.H().get(methodNode);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static String b() {
        return X;
    }

    public static void O(String string) {
        X = string;
    }

    static {
        if (MethodNodeMatcher.b() == null) {
            MethodNodeMatcher.O("h95Sqb");
        }
    }
}
