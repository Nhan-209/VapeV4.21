package gg.vape.asm;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

public interface ITramsformNode {
    public String F();

    public InsnList R();

    public boolean hasOwner();

    public String p();

    public ITramsformNode setOwner(Class var1);

    public InsnList h();

    public void onTransform(ClassNode var1, MethodNode var2);
}

