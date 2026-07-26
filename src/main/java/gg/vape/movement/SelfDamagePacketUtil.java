package gg.vape.movement;

import gg.vape.config.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.CPacketPlayerPosition;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class SelfDamagePacketUtil {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void Y() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!entityPlayerSP.b$src$Z$fqlxe4() || !entityPlayerSP.u$src$Z$g120nz()) {
            return;
        }
        for (int i = 0; i < 60; ++i) {
            double d = 0.13029834580989086 + 7.045809890852092E-4 * Math.random();
            CPacketPlayerPosition cPacketPlayerPosition = ClientSettings.H ? CPacketPlayerPosition.newInstance(entityPlayerSP.z(), entityPlayerSP.N() + d, entityPlayerSP.h(), false) : CPacketPlayerPosition.newInstance(entityPlayerSP.z(), entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY() + d, entityPlayerSP.N() + d, entityPlayerSP.h(), false);
            double d2 = 0.07029834580989085 + 7.045809890852092E-4 * Math.random();
            CPacketPlayerPosition cPacketPlayerPosition2 = ClientSettings.H ? CPacketPlayerPosition.newInstance(entityPlayerSP.z(), entityPlayerSP.N() + d, entityPlayerSP.h(), false) : CPacketPlayerPosition.newInstance(entityPlayerSP.z(), entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY() + d2, entityPlayerSP.N() + d2, entityPlayerSP.h(), false);
            entityPlayerSP.sendQueue().addToSendQueue(cPacketPlayerPosition);
            entityPlayerSP.sendQueue().addToSendQueue(cPacketPlayerPosition2);
        }
        entityPlayerSP.sendQueue().addToSendQueue(ClientSettings.H ? CPacketPlayerPosition.newInstance(entityPlayerSP.z(), entityPlayerSP.N(), entityPlayerSP.h(), true) : CPacketPlayerPosition.newInstance(entityPlayerSP.z(), entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY(), entityPlayerSP.N(), entityPlayerSP.h(), true));
    }
}

