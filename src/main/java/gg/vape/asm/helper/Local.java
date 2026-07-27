package gg.vape.asm.helper;

import gg.vape.asm.ITramsformNode;
import gg.vape.asm.helper.DescUtils;
import gg.vape.asm.helper.EventBuilder;
import java.util.ListIterator;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class Local
implements ITramsformNode {
    private String g = null;
    protected VarInsnNode d;
    protected VarInsnNode M;
    protected String F;
    private String s;
    protected InsnList H;
    private boolean h;
    private static boolean C;
    private String K;
    protected InsnList v = new InsnList();
    protected LocalVariableNode P;

    public Local(String string) {
        this.H = new InsnList();
        this.s = string;
    }

    public static boolean u() {
        boolean bl = Local.G();
        return false;
    }

    @Override
    public ITramsformNode setOwner(Class clazz) {
        this.h = true;
        this.K = clazz.getName().replace(".", "/");
        return this;
    }

    void setLocalVariableEnd(LabelNode labelNode) {
        if (this.P != null) {
            this.P.start = labelNode;
        }
    }

    @Override
    public InsnList R() {
        return this.v;
    }

    @Override
    public String p() {
        if (this.g != null) {
            return this.g;
        }
        return this.F;
    }


    static {
        Local.A(true);
    }

    @Override
    public boolean hasOwner() {
        return this.h;
    }

    public static boolean G() {
        return C;
    }

    public Local setDescriptorClass(Class clazz) {
        this.g = DescUtils.U(clazz);
        return this;
    }

    @Override
    public String F() {
        return this.K;
    }

    public static void A(boolean bl) {
        C = bl;
    }

    @Override
    public InsnList h() {
        return this.H;
    }

    @Override
    public void onTransform(ClassNode classNode, MethodNode methodNode) {
        if (this.s.equals("this") && methodNode.localVariables.size() == 0) {
            AbstractInsnNode object;
            String string = "L" + classNode.name + ";";
            LabelNode labelNode = null;
            LabelNode labelNode2 = null;
            ListIterator<AbstractInsnNode> listIterator = methodNode.instructions.iterator();
            block0: while (listIterator.hasNext()) {
                object = listIterator.next();
                if (!(object instanceof LabelNode)) continue;
                labelNode = (LabelNode)object;
                while (listIterator.hasNext()) {
                    listIterator.next();
                    if (listIterator.hasNext()) continue;
                    while (listIterator.hasPrevious()) {
                        object = listIterator.previous();
                        if (!(object instanceof LabelNode)) continue;
                        labelNode2 = (LabelNode)object;
                        break block0;
                    }
                }
            }
            LocalVariableNode localVariable = new LocalVariableNode("this", string, null, labelNode, labelNode2, 0);
            methodNode.localVariables.add(localVariable);
            this.P = localVariable;
            this.M = new VarInsnNode(25, 0);
            this.v.add(this.M);
            this.d = new VarInsnNode(58, 0);
            this.H.add(this.d);
            this.F = string;
            return;
        }
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            if (!localVariableNode.name.equals(this.s)) continue;
            this.P = localVariableNode;
            this.M = new VarInsnNode(EventBuilder.j(localVariableNode.desc), localVariableNode.index);
            this.v.add(this.M);
            this.d = new VarInsnNode(EventBuilder.o(localVariableNode.desc), localVariableNode.index);
            this.H.add(this.d);
            this.F = this.P.desc;
        }
    }
}
