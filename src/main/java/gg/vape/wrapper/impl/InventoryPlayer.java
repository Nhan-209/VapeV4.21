package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MInventoryPlayer;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.EquipmentSlotSet;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.InventoryListBridge;
import gg.vape.wrapper.impl.ItemStack;
import java.util.AbstractList;
import java.util.ArrayList;

public class InventoryPlayer
extends Wrapper {
    public void g(int n) {
        InventoryPlayer.c.getMappings().v.A(this.I, n);
    }

    public ItemStack A() {
        return new ItemStack(MInventoryPlayer.N(InventoryPlayer.c.getMappings().v, this.I));
    }

    public Object[] i() {
        if (ForgeVersion.MC_1_21_6.d()) {
            ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
            for (Object e : InventoryListBridge.u().p()) {
                EquipmentSlotSet equipmentSlotSet = new EquipmentSlotSet(e);
                arrayList.add(this.c(equipmentSlotSet.d()));
            }
            return arrayList.toArray();
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            AbstractList abstractList = MInventoryPlayer.S(InventoryPlayer.c.getMappings().v, this.I);
            return abstractList.toArray();
        }
        return MInventoryPlayer.i(InventoryPlayer.c.getMappings().v, this.I);
    }

    public InventoryPlayer(Object object) {
        super(object);
    }

    public Object[] M() {
        if (ForgeVersion.MC_1_12_2.d()) {
            AbstractList abstractList = InventoryPlayer.c.getMappings().v.T(this.I);
            return abstractList.toArray();
        }
        return InventoryPlayer.c.getMappings().v.X(this.I);
    }

    public ItemStack c(int n) {
        return new ItemStack(InventoryPlayer.c.getMappings().v.N(this.I, n));
    }

    public int v() {
        return InventoryPlayer.c.getMappings().v.s(this.I);
    }
}

