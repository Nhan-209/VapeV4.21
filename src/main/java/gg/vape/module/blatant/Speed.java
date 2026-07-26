package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventMove;
import gg.vape.event.impl.EventPreMotion;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.speed.BhopSpeed;
import gg.vape.module.blatant.speed.MineplexSpeed;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.ModeValue;
import gg.vape.value.SubModuleValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionRegistry;

public class Speed
extends Mod {
    private final SubModuleValue H = new MineplexSpeed(this, "AntiCheat B").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
    public double L;
    private final ModeValue C;
    private final SubModuleValue K = new BhopSpeed(this, "Bhop").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
    public double O;
    public int U;

    public double defaultSpeed() {
        double d = 0.28730000691562896;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.i(PotionRegistry.U) && entityPlayerSP.b(PotionRegistry.U).k() > 10) {
            int n = entityPlayerSP.b(PotionRegistry.U).L();
            d *= 1.0 + 0.15 * (double)(n + 1);
        }
        return d;
    }

    @Override
    public boolean isBlatantMod() {
        return true;
    }

    public Speed() {
        super("Speed", 49630, Category.A, "Increases your movement with various methods.");
        this.C = ModeValue.create((Object)this, "Mode", "Speed mode to use.\nAntiCheat B works on various servers\nBhop - Bypasses Old NCP", (ModeSelection)this.K, this.H, this.K);
        this.R(false);
        this.addValue(this.C);
    }

    public void strafe(EventMove eventMove, double d, EntityPlayerSP entityPlayerSP) {
        double d2 = entityPlayerSP.movementInput().D();
        double d3 = entityPlayerSP.movementInput().T();
        float f = entityPlayerSP.J();
        if (d2 == 0.0 && d3 == 0.0) {
            eventMove.setX(0.0);
            eventMove.setZ(0.0);
        } else if (d2 != 0.0) {
            if (d3 != 0.0) {
                if (d3 > 0.0) {
                    f += d2 > 0.0 ? -45.0f : 45.0f;
                    d3 = 0.0;
                } else {
                    f += d2 > 0.0 ? 45.0f : -45.0f;
                    d3 = 0.0;
                }
            }
            d2 = d2 > 0.0 ? 1.0 : -1.0;
        }
        double d4 = Math.cos(Math.toRadians(f + 90.0f));
        double d5 = Math.sin(Math.toRadians(f + 90.0f));
        eventMove.setX(d2 * d * d4 + d3 * d * d5);
        eventMove.setZ(d2 * d * d5 - d3 * d * d4);
    }

    @EventHandler(A=EventPriority.HIGH)
    public void onMotionUpdate(EventPreMotion eventPreMotion) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d = entityPlayerSP.z() - entityPlayerSP.f();
        double d2 = entityPlayerSP.h() - entityPlayerSP.R();
        this.L = Math.sqrt(d * d + d2 * d2);
    }

    @Override
    public String E() {
        return this.C.c();
    }

    @Override
    public void onEnable() {
        Vape.INSTANCE.getClientSettings().k(this);
        this.O = this.defaultSpeed();
        this.L = 0.0;
        this.U = 2;
    }

    public void setStep(int n) {
        this.U = n;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

