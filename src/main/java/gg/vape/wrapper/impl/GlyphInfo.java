package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.BlockStateBridge;
import gg.vape.wrapper.impl.FontGlyphInfo;

public class GlyphInfo
extends Wrapper {
    public float M() {
        return GlyphInfo.c.getMappings().qp.h(this.I);
    }

    public float s() {
        return GlyphInfo.c.getMappings().qp.j(this.I);
    }

    public FontGlyphInfo z() {
        Object object = GlyphInfo.c.getMappings().qp.H(this.I);
        return object != null ? new FontGlyphInfo(object) : null;
    }

    public GlyphInfo(Object object) {
        super(object);
    }

    public float S() {
        return GlyphInfo.c.getMappings().qp.v(this.I);
    }

    public float E() {
        return this.s() - this.h();
    }

    public BlockStateBridge H$src$Lgg_vape_wrapper_impl_BlockStateBridge_$1eevee() {
        Object object = GlyphInfo.c.getMappings().qp.r$src$Ljava_lang_Object_$1r6sqxs(this.I);
        return object != null ? new BlockStateBridge(object) : null;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public float F(boolean bl) {
        FontGlyphInfo fontGlyphInfo = this.z();
        return fontGlyphInfo != null ? fontGlyphInfo.f(bl) : 0.0f;
    }

    public float j() {
        return GlyphInfo.c.getMappings().qp.K(this.I);
    }

    public int q() {
        BlockStateBridge blockStateBridge = this.H$src$Lgg_vape_wrapper_impl_BlockStateBridge_$1eevee();
        return blockStateBridge != null ? blockStateBridge.v() : -1;
    }

    public float h() {
        return GlyphInfo.c.getMappings().qp.r(this.I);
    }

    public float Q() {
        return this.j() - this.t();
    }

    public float x() {
        return GlyphInfo.c.getMappings().qp.g(this.I);
    }

    public float H() {
        return GlyphInfo.c.getMappings().qp.M(this.I);
    }

    public float t() {
        return GlyphInfo.c.getMappings().qp.N(this.I);
    }
}

