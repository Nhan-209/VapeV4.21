package gg.vape.module.combat.silentaura;

import gg.vape.module.combat.SilentAura;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class SilentAuraRotationController
extends AdaptiveRotationController {
    final SilentAura gF;

    @Override
    public float O() {
        double d;
        EntityPlayerSP entityPlayerSP;
        double d2 = 0.65;
        double d3 = 0.25;
        double d4 = Math.random();
        double d5 = Math.random();
        if (d4 < 1.0E-4) {
            d4 = 1.0E-4;
        }
        double d6 = Math.sqrt(-2.0 * Math.log(d4)) * Math.cos(Math.PI * 2 * d5);
        double d7 = Math.exp(d2 + d3 * d6);
        d7 = Math.max(1.4, Math.min(3.0, d7));
        double d8 = (double)((Double)this.gF.Zt.K()).floatValue() * d7;
        if (SilentAura.A(this.gF).U(this.gF) && (entityPlayerSP = Minecraft.thePlayer()).isNotNull() && SilentAura.B(this.gF) != null && (d = entityPlayerSP.i(SilentAura.B(this.gF).z(), SilentAura.B(this.gF).N(), SilentAura.B(this.gF).h())) < 0.8) {
            double d9 = d / 0.8;
            d8 *= d9;
        }
        return (float)d8;
    }

    private static ObfuscatedRuntimeException d(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SilentAuraRotationController(SilentAura silentAura) {
        this.gF = silentAura;
    }
}

