package gg.vape.asm.helper;

import gg.vape.asm.helper.EventBuilder;
import gg.vape.asm.helper.Local;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class IndexedLocal
extends Local {
    int a;

    public IndexedLocal(int n) {
        super("");
        this.a = n;
    }

    @Override
    public void onTransform(ClassNode classNode, MethodNode methodNode) {
        int n = 0;
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            if (this.a == n) {
                this.P = localVariableNode;
                this.M = new VarInsnNode(EventBuilder.j(localVariableNode.desc), localVariableNode.index);
                this.v.add(this.M);
                this.d = new VarInsnNode(EventBuilder.o(localVariableNode.desc), localVariableNode.index);
                this.H.add(this.d);
                this.F = this.P.desc;
            }
            ++n;
        }
    }

}
