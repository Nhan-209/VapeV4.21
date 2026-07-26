package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MBlockHorizontal;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockProperty;

public class BlockHorizontal
extends Block {
    public BlockHorizontal(Object object) {
        super(object);
    }

    public static BlockProperty V() {
        return new BlockProperty(MBlockHorizontal.p(BlockHorizontal.c.getMappingsMapperCompat().a));
    }
}

