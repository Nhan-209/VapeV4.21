package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.BlockStateBridge;
import gg.vape.wrapper.impl.TextComponentTranslation;

public class BlockStateContainerBridge
extends Wrapper {
    public BlockStateBridge C() {
        return new BlockStateBridge(BlockStateContainerBridge.c.getMappingsMapperCompat().Ds.V(this.I));
    }

    public Object w(TextComponentTranslation wi_12) {
        return BlockStateContainerBridge.c.getMappingsMapperCompat().Ds.N(this.I, wi_12.getObject());
    }

    public BlockStateContainerBridge(Object object) {
        super(object);
    }

    public int I() {
        BlockStateBridge bi_02 = this.C();
        int n = bi_02.isNull() ? -1 : bi_02.v();
        return n;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }
}

