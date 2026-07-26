package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MVoxelShapeBridge;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.RenderItemFontBridge;

public class VoxelShape
extends Wrapper {
    public VoxelShape(Object object) {
        super(object);
    }

    public RenderItemFontBridge getBoundingBox() {
        return new RenderItemFontBridge(MVoxelShapeBridge.C(VoxelShape.c.getMappingsMapperCompat().Cl, this.I));
    }
}

