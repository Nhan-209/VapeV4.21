package gg.vape.inventory;

import gg.vape.inventory.InventoryClick;
import gg.vape.inventory.InventoryClickAction;
import java.util.Queue;

public class InventoryClickQueue {
    private final int M;
    private final InventoryClickAction r;
    private static String[] k;
    private final int C;

    public static void e(int n, int n2, int n3, Queue<InventoryClick> queue) {
        new InventoryClickQueue(InventoryClickAction.CLICK, n, n2).h(n3, queue);
    }

    public static void a(int n, int n2, Queue<InventoryClick> queue) {
        new InventoryClickQueue(InventoryClickAction.DROP_SLOT, n, 0).h(n2, queue);
    }


    public static void Q(int n, int n2, Queue<InventoryClick> queue) {
        new InventoryClickQueue(InventoryClickAction.SHIFTCLICK, n, 0).h(n2, queue);
    }

    public static String[] i() {
        return k;
    }

    public InventoryClickQueue(InventoryClickAction oW, int n, int n2) {
        this.r = oW;
        this.C = n;
        this.M = n2;
    }

    public static void H(int n, int n2, int n3, Queue<InventoryClick> queue) {
        new InventoryClickQueue(InventoryClickAction.SWAP, n, n2).h(n3, queue);
    }

    public static void Q(int n, Queue<InventoryClick> queue) {
        new InventoryClickQueue(InventoryClickAction.DROP_MOUSE_STACK, 0, 0).h(n, queue);
    }

    public void h(int n, Queue<InventoryClick> queue) {
        if (this.r == InventoryClickAction.MOVE) {
            queue.add(new InventoryClick(n, this.C, 0, 0));
            queue.add(new InventoryClick(n, this.M, 0, 0));
        } else if (this.r == InventoryClickAction.SHIFTCLICK) {
            queue.add(new InventoryClick(n, this.C, 0, 1));
        } else if (this.r == InventoryClickAction.SWAP) {
            queue.add(new InventoryClick(n, this.C, 0, 0));
            queue.add(new InventoryClick(n, this.M, 0, 0));
            queue.add(new InventoryClick(n, this.C, 0, 0));
        } else if (this.r == InventoryClickAction.CLICK) {
            queue.add(new InventoryClick(n, this.C, 0, 0));
        } else if (this.r == InventoryClickAction.DROP_SLOT) {
            queue.add(new InventoryClick(n, this.C, 1, 4));
        } else if (this.r == InventoryClickAction.DROP_MOUSE_STACK) {
            queue.add(new InventoryClick(n, -999, 0, 0));
        }
    }

    public static void H(String[] stringArray) {
        k = stringArray;
    }

    public static void V(int n, int n2, int n3, Queue<InventoryClick> queue) {
        new InventoryClickQueue(InventoryClickAction.MOVE, n, n2).h(n3, queue);
    }

    static {
        if (InventoryClickQueue.i() != null) {
            InventoryClickQueue.H(new String[2]);
        }
    }
}

