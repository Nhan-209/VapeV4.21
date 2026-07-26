package gg.vape.module.blatant.speed;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreMove;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.blatant.Speed;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionRegistry;
import java.util.List;

public class BhopSpeed
extends SubModule<Speed> {
    private final Speed H = (Speed)this.getParent();

    @EventHandler
    public void onMove(EventPreMove eventPreMove) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.h$src$Z$ftwoya()) {
            return;
        }
        if (entityPlayerSP.F() == 0.0f && entityPlayerSP.N$src$F$14ypudi() == 0.0f) {
            this.H.O = this.H.defaultSpeed();
        }
        if (this.H.U == 1 && entityPlayerSP.u$src$Z$g120nz() && (entityPlayerSP.F() != 0.0f || entityPlayerSP.N$src$F$14ypudi() != 0.0f)) {
            this.H.O = 0.25 + this.H.defaultSpeed() - 0.01;
        }
        if (this.H.U == 2 && (entityPlayerSP.F() != 0.0f || entityPlayerSP.N$src$F$14ypudi() != 0.0f) && entityPlayerSP.b$src$Z$fqlxe4()) {
            double d = 0.42f;
            if (entityPlayerSP.i(PotionRegistry.Z)) {
                d += (double)((float)(entityPlayerSP.b(PotionRegistry.Z).L() + 1) * 0.1f);
            }
            eventPreMove.setY(d);
            entityPlayerSP.k(d);
            this.H.O *= 2.149;
        } else if (this.H.U == 3) {
            double d = 0.66 * (this.H.L - this.H.defaultSpeed());
            this.H.O = this.H.L - d;
        } else {
            AxisAlignedBB axisAlignedBB = null;
            axisAlignedBB = ForgeVersion.MC_1_7_10.L() ? entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().copy().A(0.0, entityPlayerSP.q(), 0.0) : entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().A(0.0, entityPlayerSP.q(), 0.0);
            List list = Minecraft.theWorld().i(entityPlayerSP, axisAlignedBB);
            if ((list.size() > 0 || entityPlayerSP.u$src$Z$g120nz()) && this.H.U > 0) {
                this.H.U = 1.35 * this.H.defaultSpeed() - 0.01 > this.H.O ? 0 : (entityPlayerSP.F() != 0.0f || entityPlayerSP.N$src$F$14ypudi() != 0.0f ? 1 : 0);
            }
            this.H.O = this.H.L - this.H.L / 159.0;
        }
        if (this.H.U > 0) {
            this.H.O = Math.max(this.H.O, this.H.defaultSpeed());
            this.H.strafe(eventPreMove, this.H.O, entityPlayerSP);
        }
        if (entityPlayerSP.F() != 0.0f || entityPlayerSP.N$src$F$14ypudi() != 0.0f) {
            ++this.H.U;
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public BhopSpeed(Mod mod, String string) {
        super(mod, string);
    }
}

