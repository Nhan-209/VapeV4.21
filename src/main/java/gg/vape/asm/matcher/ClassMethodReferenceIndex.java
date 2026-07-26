package gg.vape.asm.matcher;

import gg.vape.asm.matcher.MethodInstructionIndex;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class ClassMethodReferenceIndex {
    private Map<MethodNode, MethodInstructionIndex> V = new HashMap<MethodNode, MethodInstructionIndex>();
    private ClassNode q;

    public void I() {
        Iterator<MethodNode> iterator = this.q.methods.iterator();
        while (iterator.hasNext()) {
            MethodNode methodNode;
            MethodNode methodNode2 = methodNode = iterator.next();
            MethodInstructionIndex methodInstructionIndex = new MethodInstructionIndex(methodNode2);
            methodInstructionIndex.s$src$V$s4j71d();
            this.V.put(methodNode2, methodInstructionIndex);
        }
    }

    public Map<MethodNode, MethodInstructionIndex> H() {
        return this.V;
    }

    public ClassMethodReferenceIndex(ClassNode classNode) {
        this.q = classNode;
        this.I();
    }
}
