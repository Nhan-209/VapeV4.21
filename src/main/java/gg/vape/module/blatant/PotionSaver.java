package gg.vape.module.blatant;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ColorUtil;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class PotionSaver
extends Mod {
    private boolean k;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean isBlatantMod() {
        return true;
    }

    @EventHandler
    public void onPacketSend(EventPacketSend eventPacketSend) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNotNull() && !entityPlayerSP.B$src$Ljava_util_Collection_$1uxz2f9().isEmpty() && !this.F(entityPlayerSP) && (entityPlayerSP.b$src$Z$fqlxe4() || entityPlayerSP.u$src$Z$g120nz())) {
            if (eventPacketSend.getPacket().isInstance(MappedClasses.qD)) {
                this.k = true;
                eventPacketSend.setCancelled(true);
            }
        } else {
            this.k = false;
        }
    }

    @Override
    public int h() {
        return this.k ? -256 : ColorUtil.U(160);
    }

    private boolean F(EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.t() != 0.0 || entityPlayerSP.T() != 0.0;
    }

    public PotionSaver() {
        super("PotionSaver", -256, Category.w, "Saves your potion effect(s) duration when standing still");
    }
}

