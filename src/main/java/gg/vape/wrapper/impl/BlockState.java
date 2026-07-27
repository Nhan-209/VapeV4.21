package gg.vape.wrapper.impl;

import com.google.common.collect.ImmutableMap;
import gg.vape.mapping.mappings.MIBlockState;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockProperty;
import gg.vape.wrapper.impl.BlockStateWorldBridge;
import gg.vape.wrapper.impl.ForgeVersion;

public class BlockState
extends Wrapper {
    public boolean u() {
        return BlockState.c.getMappings().DE.d(this.I);
    }

    public boolean Y() {
        return BlockState.c.getMappings().DE.W(this.I);
    }

    public Object I(BlockProperty blockProperty) {
        if (ForgeVersion.MC_1_20_6.d()) {
            return BlockState.c.getMappings().DE.o(this.I, blockProperty.getObject());
        }
        ImmutableMap immutableMap = BlockState.c.getMappings().DE.w(this.I);
        for (Object e : immutableMap.keySet()) {
            if (!e.getClass().equals(blockProperty.getObject().getClass())) continue;
            Object object = immutableMap.get(e);
            return object;
        }
        if (immutableMap.containsKey(blockProperty.getObject())) {
            Object object = immutableMap.get(blockProperty.getObject());
            return object;
        }
        return null;
    }

    public boolean x() {
        return BlockState.c.getMappings().DE.e(this.I);
    }

    public Block getBlock() {
        return new Block(BlockState.c.getMappings().DE.v(this.I));
    }

    public BlockState(Object object) {
        super(object);
    }

    public boolean g() {
        return BlockState.c.getMappings().DE.I(this.I);
    }

    public BlockStateWorldBridge j() {
        return new BlockStateWorldBridge(MIBlockState.j(BlockState.c.getMappings().DE, this.I));
    }

}

