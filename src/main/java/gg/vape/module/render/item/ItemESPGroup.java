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
    private static String[] itemNames;
    public double n;
    private final List<Object> itemEntities;
    private final List<ItemStack> stackedItems;
    public double y;
    final ItemESP q;

    public static void n(String[] stringArray) {
        itemNames = stringArray;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public List<Object> I() {
        return this.itemEntities;
    }

    public static String[] M() {
        return itemNames;
    }

    public void F(EntityItem entityItem) {
        if (this.itemEntities.isEmpty()) {
            this.U = entityItem.z();
            this.y = entityItem.N();
            this.X = entityItem.h();
            this.e = entityItem.M();
            this.G = entityItem.W();
            this.n = entityItem.m$src$D$fwnne5();
        } else {
            int count = this.itemEntities.size();
            this.U = (this.U * (double)count + entityItem.z()) / (double)(count + 1);
            this.y = (this.y * (double)count + entityItem.N()) / (double)(count + 1);
            this.X = (this.X * (double)count + entityItem.h()) / (double)(count + 1);
        }
        this.itemEntities.add(entityItem.getObject());
        this.r();
    }

    public boolean e() {
        return this.itemEntities.isEmpty();
    }

    public void k(ItemESPGroup itemESPGroup) {
        for (Object object : itemESPGroup.itemEntities) {
            if (this.itemEntities.contains(object)) continue;
            this.itemEntities.add(object);
        }
        double sumX = 0.0;
        double sumY = 0.0;
        double sumZ = 0.0;
        for (Object object : this.itemEntities) {
            EntityItem entityItem = new EntityItem(object);
            sumX += entityItem.z();
            sumY += entityItem.N();
            sumZ += entityItem.h();
        }
        int count = this.itemEntities.size();
        this.U = sumX / (double)count;
        this.y = sumY / (double)count;
        this.X = sumZ / (double)count;
        this.r();
    }

    static {
        if (ItemESPGroup.M() == null) {
            ItemESPGroup.n(new String[4]);
        }
    }

    public void r() {
        this.stackedItems.clear();
        for (Object object : this.itemEntities) {
            EntityItem entityItem = new EntityItem(object);
            ItemStack itemStack = entityItem.J$src$Lgg_vape_wrapper_impl_ItemStack_$5gv0ko();
            if (!itemStack.isNotNull()) continue;
            if (ItemESP.i(this.q).L().booleanValue()) {
                boolean merged = false;
                for (ItemStack existingStack : this.stackedItems) {
                    if (!existingStack.e(itemStack)) continue;
                    existingStack.Y(existingStack.t() + itemStack.t());
                    merged = true;
                    break;
                }
                if (merged) continue;
                ItemStack copyStack = ItemStack.S(itemStack.getItem());
                copyStack.i(itemStack.l());
                copyStack.s(itemStack.L());
                copyStack.Y(itemStack.t());
                this.stackedItems.add(copyStack);
                continue;
            }
            ItemStack singleStack = ItemStack.S(itemStack.getItem());
            singleStack.i(itemStack.l());
            singleStack.s(itemStack.L());
            singleStack.Y(itemStack.t());
            this.stackedItems.add(singleStack);
        }
    }

    public List<ItemStack> p() {
        return this.stackedItems;
    }

    public ItemESPGroup(ItemESP itemESP, EntityItem entityItem) {
        this.q = itemESP;
        this.itemEntities = new ArrayList<Object>();
        this.stackedItems = new ArrayList<ItemStack>();
        this.U = entityItem.z();
        this.y = entityItem.N();
        this.X = entityItem.h();
        this.e = this.U;
        this.G = this.y;
        this.n = this.X;
        this.F(entityItem);
    }

    public Object g() {
        return this.itemEntities.isEmpty() ? null : this.itemEntities.get(0);
    }

    public void x(List<Object> list, EntityPlayerSP entityPlayerSP) {
        this.e = this.U;
        this.G = this.y;
        this.n = this.X;
        EntityItem entityItem = new EntityItem(this.g());
        double distance = entityPlayerSP.getDistanceToEntity(entityItem);
        double maxRadius = Math.max(1.5, distance / 5.0);
        Iterator<Object> iterator = this.itemEntities.iterator();
        while (iterator.hasNext()) {
            double deltaZ;
            double deltaY;
            Object object = iterator.next();
            if (!list.contains(object)) {
                iterator.remove();
                continue;
            }
            EntityItem entityItem2 = new EntityItem(object);
            double deltaX = entityItem2.z() - this.U;
            if (!(Math.sqrt(deltaX * deltaX + (deltaY = entityItem2.N() - this.y) * deltaY + (deltaZ = entityItem2.h() - this.X) * deltaZ) > maxRadius)) continue;
            iterator.remove();
        }
        if (!this.itemEntities.isEmpty()) {
            double sumX = 0.0;
            double sumY = 0.0;
            double sumZ = 0.0;
            for (Object object : this.itemEntities) {
                EntityItem entityItem3 = new EntityItem(object);
                sumX += entityItem3.z();
                sumY += entityItem3.N();
                sumZ += entityItem3.h();
            }
            int count = this.itemEntities.size();
            this.U = sumX / (double)count;
            this.y = sumY / (double)count;
            this.X = sumZ / (double)count;
        }
        this.r();
    }
}

