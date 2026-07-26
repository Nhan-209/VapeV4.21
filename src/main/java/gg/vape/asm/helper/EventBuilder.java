package gg.vape.asm.helper;

import gg.vape.Vape;
import gg.vape.asm.ITramsformNode;
import gg.vape.asm.helper.MethodInfo;
import gg.vape.event.EventBus;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.LaunchClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class EventBuilder {
    private int G;
    private LabelNode V;
    private ClassNode n;
    private String d;
    private List<ITramsformNode> u = new ArrayList<ITramsformNode>();
    private int S;
    private ITramsformNode r;
    private boolean D;
    private static int J = 1;
    private InsnList w;
    private boolean U;
    private MethodNode Y;
    private Class c;
    private String x;
    private Class z;
    private boolean y;

    public static int o(String string) {
        switch (string) {
            case "D": {
                return 57;
            }
            case "I": {
                return 21;
            }
            case "F": {
                return 56;
            }
            case "Z": {
                return 54;
            }
            case "J": {
                return 55;
            }
        }
        return 58;
    }

    public EventBuilder W() {
        if (this.G == 0) {
            this.Y.instructions.insert(this.w);
            return this;
        }
        if (this.G == -1) {
            ListIterator<AbstractInsnNode> listIterator = this.Y.instructions.iterator();
            while (listIterator.hasNext()) {
                listIterator.next();
                if (listIterator.hasNext()) continue;
                while (listIterator.hasPrevious()) {
                    AbstractInsnNode abstractInsnNode = listIterator.previous();
                    if (!(abstractInsnNode instanceof LineNumberNode)) continue;
                    this.Y.instructions.insert(abstractInsnNode, this.w);
                    return this;
                }
            }
        } else {
            ListIterator<AbstractInsnNode> listIterator = this.Y.instructions.iterator();
            while (listIterator.hasNext()) {
                AbstractInsnNode abstractInsnNode = listIterator.next();
                if (!(abstractInsnNode instanceof LineNumberNode)) continue;
                LineNumberNode lineNumberNode = (LineNumberNode)abstractInsnNode;
                if (lineNumberNode.line != this.G) continue;
                this.Y.instructions.insert(lineNumberNode, this.w);
                return this;
            }
        }
        return this;
    }

    public static List<String> t(String string) {
        ArrayList<String> arrayList = new ArrayList<String>();
        char[] cArray = string.toCharArray();
        for (int i = 0; i < cArray.length; ++i) {
            char c = cArray[i];
            if (c == '(') continue;
            if (c == ')') break;
            if (c == 'L') {
                String string2 = "";
                while ((c = cArray[i++]) != ';') {
                    string2 = string2 + c;
                }
                --i;
                arrayList.add(string2);
                continue;
            }
            arrayList.add(Character.toString(c));
        }
        return arrayList;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static int j(String string) {
        switch (string) {
            case "D": {
                return 24;
            }
            case "I": {
                return 21;
            }
            case "F": {
                return 23;
            }
            case "Z": {
                return 21;
            }
            case "J": {
                return 22;
            }
        }
        return 25;
    }

    public static int q(String string) {
        int n = string.indexOf(")") + 1;
        switch (string = string.substring(n, n + 1)) {
            case "I": 
            case "Z": {
                return 172;
            }
            case "F": {
                return 174;
            }
            case "D": {
                return 175;
            }
            case "V": {
                return 177;
            }
            case "J": {
                return 173;
            }
        }
        return 176;
    }

    public static String S(String string) {
        int n = string.indexOf(")") + 1;
        string = string.substring(n, n + 1);
        return string;
    }

    public void I() {
        String string = this.c.getName().replace('.', '/');
        LabelNode labelNode = new LabelNode(new Label());
        int n = this.Y.maxLocals++;
        LocalVariableNode localVariableNode = new LocalVariableNode("event", "L" + string + ";", null, this.V, labelNode, n);
        this.Y.localVariables.add(localVariableNode);
        InsnList insnList = new InsnList();
        insnList.add(this.V);
        if (!this.y) {
            for (LocalVariableNode object : this.Y.localVariables) {
                if (object.index != 0) continue;
                object.start = this.V;
            }
        }
        insnList.add(new TypeInsnNode(187, string));
        insnList.add(new InsnNode(89));
        StringBuilder stringBuilder = new StringBuilder("(");
        for (ITramsformNode iTramsformNode : this.u) {
            insnList.add(iTramsformNode.R());
            stringBuilder.append(iTramsformNode.p());
        }
        stringBuilder.append(")V");
        insnList.add(new MethodInsnNode(183, string, "<init>", stringBuilder.toString(), false));
        insnList.add(new VarInsnNode(58, n));
        insnList.add(new VarInsnNode(25, n));
        insnList.add(new MethodInsnNode(182, string, EventBus.getFireMethod(this.c).getName(), "()Z", false));
        insnList.add(new JumpInsnNode(153, labelNode));
        insnList.add(new InsnNode(EventBuilder.q(this.Y.desc)));
        insnList.add(labelNode);
        this.w = insnList;
    }

    public EventBuilder(int n, Class clazz, ClassNode classNode, MethodInfo methodInfo, boolean bl, ITramsformNode ... iTramsformNodeArray) {
        this.V = new LabelNode(new Label());
        if (!Vape.INSTANCE.isNativeAvailable()) {
            LaunchClassLoader.getLaunchClassLoader().cachedClasses().put(clazz.getName(), clazz);
        }
        for (MethodNode methodNode : classNode.methods) {
            if (!methodInfo.O(methodNode.name, methodNode.desc)) continue;
            this.Y = methodNode;
        }
        if (this.Y == null) {
            Vape.debugLog("Couldnt find method node");
        }
        this.G = n;
        this.c = clazz;
        this.n = classNode;
        for (ITramsformNode iTramsformNode : iTramsformNodeArray) {
            iTramsformNode.onTransform(classNode, this.Y);
            this.u.add(iTramsformNode);
        }
        this.y = bl;
    }
}
