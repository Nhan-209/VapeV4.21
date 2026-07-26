package gg.vape.runtime.model;

import gg.vape.runtime.model.DetachedStringTreeNode;
import java.util.List;

public class DetachedStringTree {
    private String m;
    List<DetachedStringTreeNode> u;
    private static int[] C;

    public static int[] c() {
        return C;
    }

    public static void W(int[] nArray) {
        C = nArray;
    }

    static {
        if (DetachedStringTree.c() == null) {
            DetachedStringTree.W(new int[4]);
        }
    }
}

