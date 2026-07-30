package gg.vape.wrapper.impl;

public class BlockPosCarrierValueBridge
extends BlockPosCarrier {
    public BlockPosCarrierValueBridge(Object wrappedObject) {
        super(wrappedObject);
    }

    public Object getBlockEntity() {
        return BlockPosCarrierValueBridge.vapeInstance.getMappingsMapperCompat().D9.getBlockEntity(this.I);
    }
}
