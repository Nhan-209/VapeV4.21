package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MAbstractBlockState;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockReader;
import gg.vape.wrapper.impl.EntityFishHook;

public class BlockReaderBridge
extends Wrapper {
    public BlockReaderBridge(Object object) {
        super(object);
    }

    public EntityFishHook Z(BlockReader ji_12, BlockPos blockPos) {
        return new EntityFishHook(MAbstractBlockState.i(BlockReaderBridge.c.getMappingsMapperCompat().CJ, this.I, ji_12.getObject(), blockPos.getObject()));
    }

    public boolean e(Object object, Object object2) {
        return MAbstractBlockState.R(BlockReaderBridge.c.getMappingsMapperCompat().CJ, this.I, object, object2);
    }
}

