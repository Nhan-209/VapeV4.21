package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MBlockPosCarrier;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.BlockPos;

public class BlockPosCarrier
extends Wrapper {
    public BlockPos M() {
        return new BlockPos(MBlockPosCarrier.Y(BlockPosCarrier.c.getMappingsMapperCompat().hS, this.I));
    }

    public BlockPosCarrier(Object object) {
        super(object);
    }
}

