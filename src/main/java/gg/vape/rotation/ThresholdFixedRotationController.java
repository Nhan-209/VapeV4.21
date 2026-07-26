package gg.vape.rotation;

import gg.vape.rotation.FixedRotationController;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GuiScreen;

public class ThresholdFixedRotationController
extends FixedRotationController {
    final double H;
    final double d;

    @Override
    public void J(EntityPlayerSP entityPlayerSP, GuiScreen guiScreen) {
        if (guiScreen.isNotNull()) {
            return;
        }
        boolean bl = false;
        boolean bl2 = false;
        if ((double)this.N() >= this.H) {
            bl = true;
        }
        if ((double)this.V() >= this.d) {
            bl2 = true;
        }
        boolean bl3 = false;
        boolean bl4 = false;
        if (!bl && (bl3 = this.A())) {
            bl = true;
        }
        if (!bl2 && (bl4 = this.m())) {
            bl2 = true;
        }
        if (bl3 && bl4 && Math.abs(this.B) < 1.0f && Math.abs(this.y) < 1.0f) {
            this.u(true);
        }
        if (bl && bl2) {
            this.u(true);
        }
    }

    public ThresholdFixedRotationController(EntityPlayerSP entityPlayerSP, float f, float f2) {
        super(entityPlayerSP.J() - f, entityPlayerSP.V() - f2);
        this.H = Math.abs(f);
        this.d = Math.abs(f2);
    }

    private static ObfuscatedRuntimeException d(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

