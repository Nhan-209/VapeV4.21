package gg.vape.module.world.cheststeal;

import gg.vape.mapping.MappedClasses;
import gg.vape.module.world.ChestSteal;
import gg.vape.module.world.cheststeal.ChestStealSlotScore;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;

public class ChestStealBestSlotTracker {
    private ChestStealSlotScore a;
    private ChestStealSlotScore A;
    private ChestStealSlotScore k;
    private ChestStealSlotScore u;
    final ChestSteal o;
    private ArrayList<Integer> e;
    private ChestStealSlotScore E;
    private ChestStealSlotScore q;
    private ChestStealSlotScore O;
    private ChestStealSlotScore X;
    private ChestStealSlotScore y;
    private ChestStealSlotScore i;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean v(ItemStack itemStack, int n) {
        if (itemStack.isNull() || itemStack.getItem().isNull()) {
            return false;
        }
        Item item = itemStack.getItem();
        if (ItemStackScoreUtil.R(item)) {
            int n2 = ChestSteal.G(this.o, itemStack);
            double d = ItemStackScoreUtil.L(itemStack);
            if (n2 == 8) {
                if (d > this.k.D) {
                    this.k.W = n;
                    this.k.D = d;
                }
            } else if (n2 == 7) {
                if (d > this.q.D) {
                    this.q.W = n;
                    this.q.D = d;
                }
            } else if (n2 == 6) {
                if (d > this.i.D) {
                    this.i.W = n;
                    this.i.D = d;
                }
            } else if (n2 == 5 && d > this.y.D) {
                this.y.W = n;
                this.y.D = d;
            }
            return true;
        }
        if (ItemStackScoreUtil.h(item)) {
            double d = ItemStackScoreUtil.k(itemStack);
            if (d > this.a.D) {
                this.a.W = n;
                this.a.D = d;
            }
            return true;
        }
        if (item.isInstance(MappedClasses.Vl)) {
            double d = ItemStackScoreUtil.O(itemStack);
            if (d > this.O.D) {
                this.O.W = n;
                this.O.D = d;
            }
            return true;
        }
        if (ItemStackScoreUtil.m(item)) {
            double d = ItemStackScoreUtil.T(itemStack);
            if (d > this.E.D) {
                this.E.W = n;
                this.E.D = d;
            }
            return true;
        }
        if (item.isInstance(MappedClasses.YP)) {
            double d = ItemStackScoreUtil.V(itemStack);
            if (d > this.u.D) {
                this.u.W = n;
                this.u.D = d;
            }
            return true;
        }
        if (item.isInstance(MappedClasses.FM)) {
            double d = ItemStackScoreUtil.g(itemStack);
            if (d > this.X.D) {
                this.X.W = n;
                this.X.D = d;
            }
            return true;
        }
        if (item.isInstance(MappedClasses.Ff)) {
            double d = ItemStackScoreUtil.u(itemStack);
            if (d > this.A.D) {
                this.A.W = n;
                this.A.D = d;
            }
            return true;
        }
        return false;
    }

    public ArrayList<Integer> Y() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        if (this.a.W != -1) {
            arrayList.add(this.a.W);
        }
        if (this.k.W != -1) {
            arrayList.add(this.k.W);
        }
        if (this.q.W != -1) {
            arrayList.add(this.q.W);
        }
        if (this.i.W != -1) {
            arrayList.add(this.i.W);
        }
        if (this.y.W != -1) {
            arrayList.add(this.y.W);
        }
        if (this.E.W != -1) {
            arrayList.add(this.E.W);
        }
        if (this.u.W != -1) {
            arrayList.add(this.u.W);
        }
        if (this.X.W != -1) {
            arrayList.add(this.X.W);
        }
        if (this.A.W != -1) {
            arrayList.add(this.A.W);
        }
        if (this.O.W != -1) {
            arrayList.add(this.O.W);
        }
        return arrayList;
    }

    public void F() {
        this.a.W = -1;
        this.k.W = -1;
        this.q.W = -1;
        this.i.W = -1;
        this.y.W = -1;
        this.E.W = -1;
        this.u.W = -1;
        this.X.W = -1;
        this.A.W = -1;
        this.O.W = -1;
        this.a.D = -1.0;
        this.k.D = -1.0;
        this.q.D = -1.0;
        this.i.D = -1.0;
        this.y.D = -1.0;
        this.E.D = -1.0;
        this.u.D = -1.0;
        this.X.D = -1.0;
        this.A.D = -1.0;
        this.O.D = -1.0;
    }

    public ChestStealBestSlotTracker(ChestSteal chestSteal) {
        this.o = chestSteal;
        this.a = new ChestStealSlotScore(this, -1, -1.0);
        this.k = new ChestStealSlotScore(this, -1, -1.0);
        this.q = new ChestStealSlotScore(this, -1, -1.0);
        this.i = new ChestStealSlotScore(this, -1, -1.0);
        this.y = new ChestStealSlotScore(this, -1, -1.0);
        this.E = new ChestStealSlotScore(this, -1, -1.0);
        this.u = new ChestStealSlotScore(this, -1, -1.0);
        this.X = new ChestStealSlotScore(this, -1, -1.0);
        this.A = new ChestStealSlotScore(this, -1, -1.0);
        this.O = new ChestStealSlotScore(this, -1, -1.0);
        this.e = new ArrayList();
    }
}

