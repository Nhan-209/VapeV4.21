package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class BlockStateContainerBridge
extends Wrapper {
    public BlockStateBridge C() {
        return new BlockStateBridge(BlockStateContainerBridge.vapeInstance.getMappingsMapperCompat().Ds.V(this.I));
    }

    public Object w(TextComponentTranslation wi_12) {
        return BlockStateContainerBridge.vapeInstance.getMappingsMapperCompat().Ds.N(this.I, wi_12.getObject());
    }

    public BlockStateContainerBridge(Object object) {
        super(object);
    }

    public int I() {
        BlockStateBridge bi_02 = this.C();
        int n = bi_02.isNull() ? -1 : bi_02.v();
        return n;
    }

}

