package gg.vape.asm.helper;

import gg.vape.asm.ITramsformNode;
import gg.vape.runtime.ObfuscatedRuntimeException;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class FieldTransformNode
implements ITramsformNode {
    private FieldNode d;
    private InsnList y;
    private String X;
    private InsnList u = new InsnList();

    @Override
    public boolean hasOwner() {
        return false;
    }

    @Override
    public InsnList R() {
        return this.u;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public FieldTransformNode(String string) {
        this.y = new InsnList();
        this.X = string;
    }

    @Override
    public String F() {
        return null;
    }

    @Override
    public String p() {
        return this.d.desc;
    }

    @Override
    public void onTransform(ClassNode classNode, MethodNode methodNode) {
        for (FieldNode fieldNode : classNode.fields) {
            if (!fieldNode.name.equals(this.X)) continue;
            this.d = fieldNode;
            this.u.add(new VarInsnNode(25, 0));
            this.u.add(new FieldInsnNode(180, classNode.name, fieldNode.name, fieldNode.desc));
            this.y.add(new VarInsnNode(25, 0));
            this.y.add(new FieldInsnNode(181, classNode.name, fieldNode.name, fieldNode.desc));
        }
    }

    @Override
    public ITramsformNode setOwner(Class clazz) {
        return this;
    }

    @Override
    public InsnList h() {
        return this.y;
    }
}
