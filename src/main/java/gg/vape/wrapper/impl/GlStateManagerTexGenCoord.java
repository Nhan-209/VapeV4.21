package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class GlStateManagerTexGenCoord
extends Wrapper {
    public GlStateManagerTexGenCoord(Object object) {
        super(object);
    }

    public static GlStateManagerTexGenCoord T() {
        return new GlStateManagerTexGenCoord(GlStateManagerTexGenCoord.vapeInstance.getMappingsMapperCompat().R_.z());
    }

    public static GlStateManagerTexGenCoord K() {
        return new GlStateManagerTexGenCoord(GlStateManagerTexGenCoord.vapeInstance.getMappingsMapperCompat().R_.m());
    }

    public static GlStateManagerTexGenCoord c() {
        return new GlStateManagerTexGenCoord(GlStateManagerTexGenCoord.vapeInstance.getMappingsMapperCompat().R_.u());
    }
}

