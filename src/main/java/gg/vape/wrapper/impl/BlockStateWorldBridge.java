package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class BlockStateWorldBridge
extends Wrapper {
    public boolean o(Object object) {
        return BlockStateWorldBridge.vapeInstance.getMappingsMapperCompat().hu.b(this.getObject(), object);
    }

    public float i(World world, BlockPos blockPos) {
        return BlockStateWorldBridge.vapeInstance.getMappingsMapperCompat().hu.r(this.getObject(), world.getObject(), blockPos.getObject());
    }

    public BlockStateWorldBridge(Object object) {
        super(object);
    }

    public AbstractBlockState X() {
        return new AbstractBlockState(BlockStateWorldBridge.vapeInstance.getMappingsMapperCompat().hu.E(this.I));
    }

    public boolean x() {
        return BlockStateWorldBridge.vapeInstance.getMappingsMapperCompat().hu.e(this.I);
    }
}

