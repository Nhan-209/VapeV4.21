package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MContainer;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Slot;
import java.util.ArrayList;
import java.util.List;

public class Container
extends Wrapper {
    public Slot getSlot(int n) {
        return new Slot(MContainer.c(Container.c.getMappings().DD, this.I, n));
    }

    public Container(Object object) {
        super(object);
    }

    public ItemStack M() {
        return new ItemStack(MContainer.R(Container.c.getMappings().DD, this.I));
    }

    public List<Slot> getInventorySlots() {
        List list = Container.c.getMappings().DD.i(this.I);
        ArrayList<Slot> arrayList = new ArrayList<Slot>();
        for (Object e : list) {
            arrayList.add(new Slot(e));
        }
        return arrayList;
    }

    public int getWindowId() {
        return Container.c.getMappings().DD.s(this.I);
    }
}

