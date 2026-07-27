package gg.vape.module.blatant.speed;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreMove;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.blatant.Speed;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionRegistry;
import java.util.List;

public class BhopSpeed
extends SubModule<Speed> {
    private final Speed speed = (Speed)this.getParent();

    @EventHandler
    public void onMove(EventPreMove eventPreMove) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.h$src$Z$ftwoya()) {
            return;
        }
        if (entityPlayerSP.F() == 0.0f && entityPlayerSP.N$src$F$14ypudi() == 0.0f) {
            this.speed.O = this.speed.defaultSpeed();
        }
        if (this.speed.U == 1 && entityPlayerSP.u$src$Z$g120nz() && (entityPlayerSP.F() != 0.0f || entityPlayerSP.N$src$F$14ypudi() != 0.0f)) {
            this.speed.O = 0.25 + this.speed.defaultSpeed() - 0.01;
        }
        if (this.speed.U == 2 && (entityPlayerSP.F() != 0.0f || entityPlayerSP.N$src$F$14ypudi() != 0.0f) && entityPlayerSP.b$src$Z$fqlxe4()) {
            double jumpMotion = 0.42f;
            if (entityPlayerSP.i(PotionRegistry.Z)) {
                jumpMotion += (double)((float)(entityPlayerSP.b(PotionRegistry.Z).L() + 1) * 0.1f);
            }
            eventPreMove.setY(jumpMotion);
            entityPlayerSP.k(jumpMotion);
            this.speed.O *= 2.149;
        } else if (this.speed.U == 3) {
            double reduction = 0.66 * (this.speed.L - this.speed.defaultSpeed());
            this.speed.O = this.speed.L - reduction;
        } else {
            AxisAlignedBB axisAlignedBB = null;
            axisAlignedBB = ForgeVersion.MC_1_7_10.L() ? entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().copy().A(0.0, entityPlayerSP.q(), 0.0) : entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().A(0.0, entityPlayerSP.q(), 0.0);
            List collisions = Minecraft.theWorld().i(entityPlayerSP, axisAlignedBB);
            if ((collisions.size() > 0 || entityPlayerSP.u$src$Z$g120nz()) && this.speed.U > 0) {
                this.speed.U = 1.35 * this.speed.defaultSpeed() - 0.01 > this.speed.O ? 0 : (entityPlayerSP.F() != 0.0f || entityPlayerSP.N$src$F$14ypudi() != 0.0f ? 1 : 0);
            }
            this.speed.O = this.speed.L - this.speed.L / 159.0;
        }
        if (this.speed.U > 0) {
            this.speed.O = Math.max(this.speed.O, this.speed.defaultSpeed());
            this.speed.strafe(eventPreMove, this.speed.O, entityPlayerSP);
        }
        if (entityPlayerSP.F() != 0.0f || entityPlayerSP.N$src$F$14ypudi() != 0.0f) {
            ++this.speed.U;
        }
    }


    public BhopSpeed(Mod mod, String string) {
        super(mod, string);
    }
}

