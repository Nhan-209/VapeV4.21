package gg.vape.asm.matcher;

import gg.vape.asm.matcher.ClassMethodReferenceIndex;
import gg.vape.runtime.ClassBytecodeCache;
import java.util.HashMap;
import java.util.Map;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

public class ClassNodeCache {
    private static Map<Class, ClassNode> P = new HashMap<Class, ClassNode>();
    private static boolean w;
    private static Map<Class, ClassMethodReferenceIndex> K;

    private static ClassMethodReferenceIndex lambda$getClassReferences$1(Class clazz, Class clazz2) {
        ClassMethodReferenceIndex classMethodReferenceIndex = new ClassMethodReferenceIndex(ClassNodeCache.j(clazz));
        return K.put(clazz, classMethodReferenceIndex);
    }

    public static void V(boolean bl) {
        w = bl;
    }

    private static ClassNode lambda$getClassNode$0(Class clazz, Class clazz2) {
        Class clazz3 = clazz;
        byte[] byArray = ClassBytecodeCache.I(clazz3);
        ClassReader classReader = new ClassReader(byArray);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, 0);
        return P.put(clazz, classNode);
    }


    public static boolean g() {
        return w;
    }

    public static boolean R() {
        boolean bl = ClassNodeCache.g();
        return false;
    }

    static {
        K = new HashMap<Class, ClassMethodReferenceIndex>();
        ClassNodeCache.V(true);
    }

    public static ClassNode j(Class clazz) {
        P.computeIfAbsent(clazz, arg_0 -> ClassNodeCache.lambda$getClassNode$0(clazz, arg_0));
        return P.get(clazz);
    }

    public static ClassMethodReferenceIndex g(Class clazz) {
        K.computeIfAbsent(clazz, arg_0 -> ClassNodeCache.lambda$getClassReferences$1(clazz, arg_0));
        return K.get(clazz);
    }
}

