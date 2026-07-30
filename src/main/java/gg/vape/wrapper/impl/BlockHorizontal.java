package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MBlockHorizontal;

public class BlockHorizontal
extends Block {
    public BlockHorizontal(Object object) {
        super(object);
    }

    public static BlockProperty V() {
        return new BlockProperty(MBlockHorizontal.p(BlockHorizontal.vapeInstance.getMappingsMapperCompat().a));
    }
}

