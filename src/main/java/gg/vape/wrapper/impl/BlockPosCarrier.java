package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MBlockPosCarrier;
import gg.vape.wrapper.Wrapper;

public class BlockPosCarrier
extends Wrapper {
    public BlockPos M() {
        return new BlockPos(MBlockPosCarrier.getPos(BlockPosCarrier.vapeInstance.getMappingsMapperCompat().hS, this.I));
    }

    public BlockPosCarrier(Object object) {
        super(object);
    }
}
