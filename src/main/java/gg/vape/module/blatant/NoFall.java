package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventMotion;
import gg.vape.event.impl.EventPostMotion;
import gg.vape.event.impl.EventPreMotion;
import gg.vape.input.KeyBindingHelper;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.Fly;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.ModeValue;
import gg.vape.wrapper.impl.C03PacketPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class NoFall
extends Mod {
    private final ModeValue mode;
    private static final long k = -4622334655389492929L;
    private final ModeOption antiCheatMode;
    private final ModeOption normalMode = new ModeOption("Normal");
    private float lastFallDistance;

    @EventHandler
    public void onMotionUpdate(EventPostMotion eventPostMotion) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || entityPlayerSP.getWorld().isNull() || entityPlayerSP.M$src$Z$ff28xj() || entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode() || entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying() || Vape.INSTANCE.getModManager().getState(Fly.class)) {
            return;
        }
        if (this.mode.K() == this.normalMode) {
            boolean shouldCancelFall;
            boolean bl2 = shouldCancelFall = (double)entityPlayerSP.M$src$F$ff28gb() > 2.224 && entityPlayerSP.q() < 0.0;
            if (shouldCancelFall) {
                entityPlayerSP.U(false);
            }
        }
    }

    @Override
    public boolean isBlatantMod() {
        return true;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public NoFall() {
        super("NoFall", (int)k, Category.w, "Prevents taking fall damage.\nThis may not bypass AntiCheats.");
        this.antiCheatMode = new ModeOption("AntiCheat");
        this.mode = ModeValue.create((Object)this, "Mode", "NoFall method to prevent you from taking fall damage.\nNormal - Works on vanilla/some anti-cheats (Does not Bypass AntiCheat)\nAntiCheat - Works and bypasses on various anti-cheats", (ModeSelection)this.normalMode, this.normalMode, this.antiCheatMode);
        this.addValue(this.mode);
    }

    @Override
    public String E() {
        return this.mode.c();
    }

    @EventHandler
    public void onMotionUpdate(EventPreMotion eventPreMotion) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || entityPlayerSP.getWorld().isNull() || entityPlayerSP.M$src$Z$ff28xj() || entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode() || entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying() || Vape.INSTANCE.getModManager().getState(Fly.class)) {
            return;
        }
        if (this.mode.K() == this.normalMode) {
            boolean shouldCancelFall;
            boolean bl2 = shouldCancelFall = (double)entityPlayerSP.M$src$F$ff28gb() > 2.224 && entityPlayerSP.q() < 0.0;
            if (shouldCancelFall) {
                EventMotion.setOnGround(true);
                entityPlayerSP.L(1);
                if (Minecraft.gameSettings().O().isPressed()) {
                    KeyBindingHelper.d(Minecraft.gameSettings().O(), false);
                }
            }
        }
        if (this.mode.K() == this.antiCheatMode) {
            if (this.lastFallDistance > entityPlayerSP.M$src$F$ff28gb()) {
                this.lastFallDistance = 0.0f;
            }
            if ((double)entityPlayerSP.M$src$F$ff28gb() > 2.124 && entityPlayerSP.q() < 0.0 && entityPlayerSP.M$src$F$ff28gb() >= 3.0f && entityPlayerSP.M$src$F$ff28gb() - this.lastFallDistance > 3.0f) {
                this.lastFallDistance = entityPlayerSP.M$src$F$ff28gb();
                entityPlayerSP.sendQueue().addToSendQueue(C03PacketPlayer.newInstance(true));
                entityPlayerSP.sendQueue().addToSendQueue(C03PacketPlayer.newInstance(false));
            }
        }
    }
}

