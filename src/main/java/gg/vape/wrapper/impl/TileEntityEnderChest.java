package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTileEntityEnderChest;
import gg.vape.wrapper.impl.Item;

public class TileEntityEnderChest
extends Item {
    public float o() {
        return MTileEntityEnderChest.G(TileEntityEnderChest.c.getMappings().D4, this.I);
    }

    public TileEntityEnderChest(Object object) {
        super(object);
    }

    public int o$src$I$tnn4wh() {
        return MTileEntityEnderChest.b(TileEntityEnderChest.c.getMappings().D4, this.I);
    }
}

