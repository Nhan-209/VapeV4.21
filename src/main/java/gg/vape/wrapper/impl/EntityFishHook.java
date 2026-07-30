package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MVoxelShape;
import gg.vape.wrapper.Wrapper;

public class EntityFishHook
extends Wrapper {
    public EntityFishHook(Object object) {
        super(object);
    }

    public boolean o() {
        return MVoxelShape.v(EntityFishHook.vapeInstance.getMappingsMapperCompat().Cr, this.I);
    }

    public AxisAlignedBB n() {
        return new AxisAlignedBB(MVoxelShape.o(EntityFishHook.vapeInstance.getMappingsMapperCompat().Cr, this.I));
    }
}

