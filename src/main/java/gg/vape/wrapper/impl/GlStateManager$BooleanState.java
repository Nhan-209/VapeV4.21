package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.wrapper.Wrapper;

public class GlStateManager$BooleanState
extends Wrapper {
    public void m$src$V$17py9xa() {
        this.O(true);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void z() {
        this.O(false);
    }

    public GlStateManager$BooleanState(Object object) {
        super(object);
    }

    public void O(boolean bl) {
        boolean bl2 = GlStateManager$BooleanState.c.getMappings().he.J(this.I);
        if (bl != bl2) {
            GlStateManager$BooleanState.c.getMappings().he.T(this.I, bl);
            if (bl) {
                OpenGlBackendHolder.d.l(GlStateManager$BooleanState.c.getMappings().he.n(this.I));
            } else {
                OpenGlBackendHolder.d.u$src$V$hntn98(GlStateManager$BooleanState.c.getMappings().he.n(this.I));
            }
        }
    }
}

