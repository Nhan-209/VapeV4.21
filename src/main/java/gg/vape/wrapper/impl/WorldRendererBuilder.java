package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MWorldRendererBuilder;
import gg.vape.wrapper.Wrapper;

public class WorldRendererBuilder
extends Wrapper {
    public WorldRendererBuilder W() {
        return new WorldRendererBuilder(MWorldRendererBuilder.E(WorldRendererBuilder.vapeInstance.getMappingsMapperCompat().D));
    }

    public WorldRendererBuilder q() {
        return new WorldRendererBuilder(MWorldRendererBuilder.W(WorldRendererBuilder.vapeInstance.getMappingsMapperCompat().D));
    }

    public WorldRendererBuilder D() {
        return new WorldRendererBuilder(MWorldRendererBuilder.X(WorldRendererBuilder.vapeInstance.getMappingsMapperCompat().D));
    }

    public WorldRendererBuilder(Object object) {
        super(object);
    }
}

