package gg.vape.asm.helper;

import gg.vape.asm.helper.EventBuilder;
import gg.vape.asm.helper.IndexedLocal;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class TypedIndexedLocal
extends IndexedLocal {
    public TypedIndexedLocal(int n, String string) {
        super(n);
        this.F = string;
    }

    @Override
    public void onTransform(ClassNode classNode, MethodNode methodNode) {
        this.M = new VarInsnNode(EventBuilder.j(this.F), this.a);
        this.v.add(this.M);
        this.d = new VarInsnNode(EventBuilder.o(this.F), this.a);
        this.H.add(this.d);
    }
}
