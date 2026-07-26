package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.module.render.hud.FpsDisplayHudModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;

public class FpsDisplayHudFrame
extends HudModuleConfigFrameBase {
    private static final long xb = 5183938735720366080L;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double A() {
        return 50.0;
    }

    @Override
    public void o() {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().W(1.2, false);
        String string = Minecraft.l() + " FPS";
        float f = (int)(this.G$src$D$1b2f02a() + this.A() / 2.0 - smoothFontRenderer.N(string) / 2.0);
        float f2 = (int)(this.n() + this.L() / 2.0 - smoothFontRenderer.d(string) / 2.0);
        if (this.m()) {
            smoothFontRenderer.d(string, f, f2, this.m$src$Ljava_awt_Color_$ppsp8z());
        } else {
            smoothFontRenderer.T(string, f, f2, this.m$src$Ljava_awt_Color_$ppsp8z(), this.l(new Color((int)xb, true)));
        }
    }

    public FpsDisplayHudFrame() {
        super(FpsDisplayHudModule.class);
    }

    @Override
    public String getName() {
        return "FpsFrame";
    }

    @Override
    public double L() {
        return 20.0;
    }
}

