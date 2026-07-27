package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MBlocks;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Block;

public class Blocks
extends Wrapper {
    private static Block W;
    private static Block d;
    private static Block w;
    private static Block h;

    public Blocks(Object object) {
        super(object);
    }

    public static Block j() {
        if (d == null) {
            d = new Block(MBlocks.M(Blocks.c.getMappingsMapperCompat().Rd));
        }
        return d;
    }

    public static Block t() {
        if (h == null) {
            h = new Block(MBlocks.J(Blocks.c.getMappingsMapperCompat().Rd));
        }
        return h;
    }

    public static Block v() {
        if (W == null) {
            W = new Block(MBlocks.z(Blocks.c.getMappingsMapperCompat().Rd));
        }
        return W;
    }

    public static Block h() {
        if (w == null) {
            w = new Block(MBlocks.U(Blocks.c.getMappingsMapperCompat().Rd));
        }
        return w;
    }

}

