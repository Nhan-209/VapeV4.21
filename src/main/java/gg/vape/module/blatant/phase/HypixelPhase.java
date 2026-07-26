package gg.vape.module.blatant.phase;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreMotion;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.CPacketPlayerPosition;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;

public class HypixelPhase
extends SubModule {
    public HypixelPhase(Mod mod, String string) {
        super(mod, string);
    }

    @EventHandler
    public void onMotionUpdate(EventPreMotion eventPreMotion) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        double d = 0.163;
        double d2 = Math.cos(Math.toRadians(entityPlayerSP.J() + 90.0f));
        double d3 = Math.sin(Math.toRadians(entityPlayerSP.J() + 90.0f));
        double d4 = (double)entityPlayerSP.movementInput().D() * d * d2 + (double)entityPlayerSP.movementInput().T() * d * d3;
        double d5 = (double)entityPlayerSP.movementInput().D() * d * d3 - (double)entityPlayerSP.movementInput().T() * d * d2;
        if (entityPlayerSP.r() && !entityPlayerSP.S$src$Z$151gttj()) {
            if (ForgeVersion.MC_1_7_10.Y()) {
                entityPlayerSP.sendQueue().addToSendQueue(CPacketPlayerPosition.newInstance(entityPlayerSP.z() + d4, entityPlayerSP.N(), entityPlayerSP.h() + d5, false));
                entityPlayerSP.sendQueue().addToSendQueue(CPacketPlayerPosition.newInstance(entityPlayerSP.z(), entityPlayerSP.N() - 0.4982374987, entityPlayerSP.h(), false));
            } else {
                entityPlayerSP.sendQueue().addToSendQueue(CPacketPlayerPosition.newInstance(entityPlayerSP.z() + d4, entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY(), entityPlayerSP.N(), entityPlayerSP.h() + d5, false));
                entityPlayerSP.sendQueue().addToSendQueue(CPacketPlayerPosition.newInstance(entityPlayerSP.z(), entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY() - 0.4982374987, entityPlayerSP.N() - 0.4982374987, entityPlayerSP.h(), false));
            }
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

