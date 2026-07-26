package gg.vape.unmap;

import gg.vape.unmap.Bendable;
import java.util.concurrent.CopyOnWriteArrayList;

class BendableBindList
extends CopyOnWriteArrayList<Integer> {
    final Bendable V;

    BendableBindList(Bendable owner) {
        this.V = owner;
    }

    @Override
    public boolean add(Integer bindId) {
        return super.add(bindId);
    }
}

