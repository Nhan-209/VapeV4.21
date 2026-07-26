package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AbstractBlockState;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.World;

public class BlockStateWorldBridge
extends Wrapper {
    public boolean o(Object object) {
        return BlockStateWorldBridge.c.getMappingsMapperCompat().hu.b(this.getObject(), object);
    }

    public float i(World world, BlockPos blockPos) {
        return BlockStateWorldBridge.c.getMappingsMapperCompat().hu.r(this.getObject(), world.getObject(), blockPos.getObject());
    }

    public BlockStateWorldBridge(Object object) {
        super(object);
    }

    public AbstractBlockState X() {
        return new AbstractBlockState(BlockStateWorldBridge.c.getMappingsMapperCompat().hu.E(this.I));
    }

    public boolean x() {
        return BlockStateWorldBridge.c.getMappingsMapperCompat().hu.e(this.I);
    }
}

