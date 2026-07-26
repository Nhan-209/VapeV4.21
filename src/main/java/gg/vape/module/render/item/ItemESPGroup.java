package gg.vape.module.render.item;

import gg.vape.module.render.ItemESP;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.EntityItem;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemESPGroup {
    public double X;
    public double e;
    public double G;
    public double U;
    private static String[] i;
    public double n;
    private final List<Object> s;
    private final List<ItemStack> u;
    public double y;
    final ItemESP q;

    public static void n(String[] stringArray) {
        i = stringArray;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public List<Object> I() {
        return this.s;
    }

    public static String[] M() {
        return i;
    }

    public void F(EntityItem entityItem) {
        if (this.s.isEmpty()) {
            this.U = entityItem.z();
            this.y = entityItem.N();
            this.X = entityItem.h();
            this.e = entityItem.M();
            this.G = entityItem.W();
            this.n = entityItem.m$src$D$fwnne5();
        } else {
            int n = this.s.size();
            this.U = (this.U * (double)n + entityItem.z()) / (double)(n + 1);
            this.y = (this.y * (double)n + entityItem.N()) / (double)(n + 1);
            this.X = (this.X * (double)n + entityItem.h()) / (double)(n + 1);
        }
        this.s.add(entityItem.getObject());
        this.r();
    }

    public boolean e() {
        return this.s.isEmpty();
    }

    public void k(ItemESPGroup itemESPGroup) {
        for (Object object : itemESPGroup.s) {
            if (this.s.contains(object)) continue;
            this.s.add(object);
        }
        double d = 0.0;
        double d2 = 0.0;
        double d3 = 0.0;
        for (Object object : this.s) {
            EntityItem entityItem = new EntityItem(object);
            d += entityItem.z();
            d2 += entityItem.N();
            d3 += entityItem.h();
        }
        int n = this.s.size();
        this.U = d / (double)n;
        this.y = d2 / (double)n;
        this.X = d3 / (double)n;
        this.r();
    }

    static {
        if (ItemESPGroup.M() == null) {
            ItemESPGroup.n(new String[4]);
        }
    }

    public void r() {
        this.u.clear();
        for (Object object : this.s) {
            EntityItem entityItem = new EntityItem(object);
            ItemStack itemStack = entityItem.J$src$Lgg_vape_wrapper_impl_ItemStack_$5gv0ko();
            if (!itemStack.isNotNull()) continue;
            if (ItemESP.i(this.q).L().booleanValue()) {
                boolean bl = false;
                for (ItemStack itemStack2 : this.u) {
                    if (!itemStack2.e(itemStack)) continue;
                    itemStack2.Y(itemStack2.t() + itemStack.t());
                    bl = true;
                    break;
                }
                if (bl) continue;
                ItemStack itemStack3 = ItemStack.S(itemStack.getItem());
                itemStack3.i(itemStack.l());
                itemStack3.s(itemStack.L());
                itemStack3.Y(itemStack.t());
                this.u.add(itemStack3);
                continue;
            }
            ItemStack itemStack4 = ItemStack.S(itemStack.getItem());
            itemStack4.i(itemStack.l());
            itemStack4.s(itemStack.L());
            itemStack4.Y(itemStack.t());
            this.u.add(itemStack4);
        }
    }

    public List<ItemStack> p() {
        return this.u;
    }

    public ItemESPGroup(ItemESP itemESP, EntityItem entityItem) {
        this.q = itemESP;
        this.s = new ArrayList<Object>();
        this.u = new ArrayList<ItemStack>();
        this.U = entityItem.z();
        this.y = entityItem.N();
        this.X = entityItem.h();
        this.e = this.U;
        this.G = this.y;
        this.n = this.X;
        this.F(entityItem);
    }

    public Object g() {
        return this.s.isEmpty() ? null : this.s.get(0);
    }

    public void x(List<Object> list, EntityPlayerSP entityPlayerSP) {
        this.e = this.U;
        this.G = this.y;
        this.n = this.X;
        EntityItem entityItem = new EntityItem(this.g());
        double d = entityPlayerSP.getDistanceToEntity(entityItem);
        double d2 = Math.max(1.5, d / 5.0);
        Iterator<Object> iterator = this.s.iterator();
        while (iterator.hasNext()) {
            double d3;
            double d4;
            Object object = iterator.next();
            if (!list.contains(object)) {
                iterator.remove();
                continue;
            }
            EntityItem entityItem2 = new EntityItem(object);
            double d5 = entityItem2.z() - this.U;
            if (!(Math.sqrt(d5 * d5 + (d4 = entityItem2.N() - this.y) * d4 + (d3 = entityItem2.h() - this.X) * d3) > d2)) continue;
            iterator.remove();
        }
        if (!this.s.isEmpty()) {
            double d6 = 0.0;
            double d7 = 0.0;
            double d8 = 0.0;
            for (Object object : this.s) {
                EntityItem entityItem3 = new EntityItem(object);
                d6 += entityItem3.z();
                d7 += entityItem3.N();
                d8 += entityItem3.h();
            }
            int n = this.s.size();
            this.U = d6 / (double)n;
            this.y = d7 / (double)n;
            this.X = d8 / (double)n;
        }
        this.r();
    }
}

