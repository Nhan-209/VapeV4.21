package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MWorldRendererBuilder;
import gg.vape.wrapper.Wrapper;

public class WorldRendererBuilder
extends Wrapper {
    public WorldRendererBuilder W() {
        return new WorldRendererBuilder(MWorldRendererBuilder.E(WorldRendererBuilder.c.getMappingsMapperCompat().D));
    }

    public WorldRendererBuilder q() {
        return new WorldRendererBuilder(MWorldRendererBuilder.W(WorldRendererBuilder.c.getMappingsMapperCompat().D));
    }

    public WorldRendererBuilder D() {
        return new WorldRendererBuilder(MWorldRendererBuilder.X(WorldRendererBuilder.c.getMappingsMapperCompat().D));
    }

    public WorldRendererBuilder(Object object) {
        super(object);
    }
}

