package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class ChunkProvider
extends Wrapper {
    public boolean isChunkLoaded(int n, int n2) {
        return ChunkProvider.vapeInstance.getMappingsMapperCompat().ho.m(this.I, n, n2);
    }

    public ChunkProvider(Object object) {
        super(object);
    }
}

