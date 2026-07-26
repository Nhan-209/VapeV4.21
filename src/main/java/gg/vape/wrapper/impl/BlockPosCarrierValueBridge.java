package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.BlockPosCarrier;

public class BlockPosCarrierValueBridge
extends BlockPosCarrier {
    public BlockPosCarrierValueBridge(Object object) {
        super(object);
    }

    public Object p() {
        return BlockPosCarrierValueBridge.c.getMappingsMapperCompat().D9.J(this.I);
    }
}

