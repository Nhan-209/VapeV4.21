package gg.vape.wrapper.impl;

public class BlockPosCarrierValueBridge
extends BlockPosCarrier {
    public BlockPosCarrierValueBridge(Object object) {
        super(object);
    }

    public Object p() {
        return BlockPosCarrierValueBridge.vapeInstance.getMappingsMapperCompat().D9.J(this.I);
    }
}

