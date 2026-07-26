package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.ForgeVersion;

public class ChunkSection
extends Wrapper {
    public char[] C() {
        return ChunkSection.c.getMappingsMapperCompat().Rx.L(this.I);
    }

    public int l() {
        return ChunkSection.c.getMappingsMapperCompat().Rx.r(this.I);
    }

    public static Object u(int n, boolean bl) {
        if (ForgeVersion.MC_1_16_5.d()) {
            return ChunkSection.c.getMappingsMapperCompat().Rx.i(n);
        }
        return ChunkSection.c.getMappingsMapperCompat().Rx.U(n, bl);
    }

    public void g(int n, int n2, int n3, BlockState pa_22) {
        if (ForgeVersion.MC_1_16_5.d()) {
            ChunkSection.c.getMappingsMapperCompat().Rx.J(this.I, n, n2, n3, pa_22.getObject(), false);
        } else {
            ChunkSection.c.getMappingsMapperCompat().Rx.K(this.I, n, n2, n3, pa_22.getObject());
        }
    }

    public ChunkSection(Object object) {
        super(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }
}

