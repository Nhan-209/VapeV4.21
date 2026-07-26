package gg.vape.module.render;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionRegistry;

public class Fullbright
extends Mod {
    private float P;
    private final BooleanValue j;
    private boolean V;
    private boolean H;
    private final ModeOption I;
    private float k = 1.0f;
    private final TimerUtil K;
    private float t = -1.0f;
    private final ModeValue L;
    private final ModeOption v = new ModeOption("Night Vision");

    public void G() {
        if (!this.K.hasTimeElapsed(10L)) {
            return;
        }
        this.K.reset();
        this.k = 0.4f;
        if (this.H && this.P < 10.0f) {
            this.P += this.k;
            if (this.P >= 10.0f) {
                Minecraft.gameSettings().y(10.0f);
                this.H = false;
            } else {
                Minecraft.gameSettings().y(this.P);
            }
        }
        if (this.V && this.P >= this.t) {
            this.P -= this.k;
            if (this.P <= this.t) {
                Minecraft.gameSettings().y(this.t);
                this.V = false;
                super.s(false, true);
            } else {
                Minecraft.gameSettings().y(this.P);
            }
        }
    }

    @Override
    public void onEnable() {
        this.t = Minecraft.gameSettings().b();
        if (!this.j.L().booleanValue()) {
            Minecraft.gameSettings().y(10.0f);
        } else if (!this.V) {
            this.P = this.t;
            this.H = true;
        }
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (((ModeSelection)this.L.K()).equals(this.v)) {
            Minecraft.thePlayer().s(PotionEffect.o(PotionRegistry.T.D(), 5220, 0));
            this.H = false;
        }
    }

    private void lambda$new$0(ModeValue modeValue) {
        if (!this.r$src$Z$14eylz9()) {
            return;
        }
        if (((ModeSelection)this.L.K()).equals(this.I)) {
            Minecraft.thePlayer().q(16);
        } else if (this.t != -1.0f) {
            Minecraft.gameSettings().y(this.t);
        }
    }

    @Override
    public void s(boolean bl, boolean bl2) {
        if (((ModeSelection)this.L.K()).equals(this.I) && this.j.L().booleanValue() && this.r$src$Z$14eylz9()) {
            this.V = true;
            this.H = false;
            return;
        }
        super.s(bl, bl2);
    }

    @EventHandler
    public void K(EventPreRenderTick eventPreRenderTick) {
        if (((ModeSelection)this.L.K()).equals(this.I)) {
            this.G();
        }
    }

    @Override
    public void onDisable() {
        if (((ModeSelection)this.L.K()).equals(this.v)) {
            if (Minecraft.thePlayer().isNotNull()) {
                Minecraft.thePlayer().q(PotionRegistry.T.D());
                this.V = false;
                Minecraft.gameSettings().y(this.t);
            }
        } else {
            if (Minecraft.thePlayer().isNotNull()) {
                Minecraft.thePlayer().q(PotionRegistry.T.D());
            }
            if (!this.j.L().booleanValue()) {
                Minecraft.gameSettings().y(this.t);
            }
        }
    }

    public Fullbright() {
        super("Fullbright", -256, Category.k);
        this.I = new ModeOption("Gamma");
        this.L = ModeValue.create((Object)this, "Mode", this.v, this.v, this.I);
        this.j = BooleanValue.create(this, "Fade", false, "Brightness changes will fade in or out");
        this.K = new TimerUtil();
        this.L.L(this.j, this.I);
        this.addValue(this.L, this.j);
        this.L.B(this::lambda$new$0);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

