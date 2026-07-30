package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTileEntityEnderChest;

public class TileEntityEnderChest
extends Item {
    public float o() {
        return MTileEntityEnderChest.G(TileEntityEnderChest.vapeInstance.getMappings().D4, this.I);
    }

    public TileEntityEnderChest(Object object) {
        super(object);
    }

    public int o$src$I$tnn4wh() {
        return MTileEntityEnderChest.b(TileEntityEnderChest.vapeInstance.getMappings().D4, this.I);
    }
}

