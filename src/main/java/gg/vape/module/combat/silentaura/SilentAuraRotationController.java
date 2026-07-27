package gg.vape.module.combat.silentaura;

import gg.vape.module.combat.SilentAura;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class SilentAuraRotationController
extends AdaptiveRotationController {
    final SilentAura silentAura;

    @Override
    public float O() {
        double distance;
        EntityPlayerSP entityPlayerSP;
        double meanLog = 0.65;
        double stdDevLog = 0.25;
        double uniform1 = Math.random();
        double uniform2 = Math.random();
        if (uniform1 < 1.0E-4) {
            uniform1 = 1.0E-4;
        }
        double gaussian = Math.sqrt(-2.0 * Math.log(uniform1)) * Math.cos(Math.PI * 2 * uniform2);
        double multiplier = Math.exp(meanLog + stdDevLog * gaussian);
        multiplier = Math.max(1.4, Math.min(3.0, multiplier));
        double reach = (double)((Double)this.silentAura.Zt.K()).floatValue() * multiplier;
        if (SilentAura.A(this.silentAura).U(this.silentAura) && (entityPlayerSP = Minecraft.thePlayer()).isNotNull() && SilentAura.B(this.silentAura) != null && (distance = entityPlayerSP.i(SilentAura.B(this.silentAura).z(), SilentAura.B(this.silentAura).N(), SilentAura.B(this.silentAura).h())) < 0.8) {
            double distanceFactor = distance / 0.8;
            reach *= distanceFactor;
        }
        return (float)reach;
    }


    public SilentAuraRotationController(SilentAura silentAura) {
        this.silentAura = silentAura;
    }
}

