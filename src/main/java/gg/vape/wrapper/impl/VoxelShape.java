package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MVoxelShapeBridge;
import gg.vape.wrapper.Wrapper;

public class VoxelShape
extends Wrapper {
    public VoxelShape(Object object) {
        super(object);
    }

    public RenderItemFontBridge getBoundingBox() {
        return new RenderItemFontBridge(MVoxelShapeBridge.C(VoxelShape.vapeInstance.getMappingsMapperCompat().Cl, this.I));
    }
}

