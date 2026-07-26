package gg.vape.ui.click.component.gui;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.gui.ActionButtonBase;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class IconActionButton
extends ActionButtonBase {
    protected String VG;
    protected ColorAnimation K;
    protected float b;

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ColorAnimation gg_vape_ui_click_animation_ColorAnimation_d() {
        return this.K;
    }

    public IconActionButton(String string, double d, double d2, double d3, Color color, double d4) {
        super(d2, d3, color, d4);
        this.K = new ColorAnimation(0.15, IconActionButton.J.W, IconActionButton.J.f);
        this.VG = string;
        this.b = (float)d;
    }

    public IconActionButton(String string, double d, double d2, double d3, Color color) {
        super(d2, d3, color);
        this.K = new ColorAnimation(0.15, IconActionButton.J.W, IconActionButton.J.f);
        this.VG = string;
        this.b = (float)d;
    }

    @Override
    public void n(boolean bl) {
        if (this.q != bl) {
            this.K.J();
        }
        super.n(bl);
    }

    public void w(ColorAnimation colorAnimation) {
        this.K = colorAnimation;
    }

    @Override
    public void void_H() {
        super.void_H();
        ImageRenderer.drawRes(this.K.getInterpolatedColor(), (float)(this.double_G() + this.double_A() / 2.0 - (double)(32.0f * this.b / 2.0f)), (float)(this.double_n() + this.double_L() / 2.0 - (double)(32.0f * this.b / 2.0f)), this.VG, this.b);
    }

    public /* synthetic */ ColorAnimation d$src$Lgg_vape_ui_click_animation_ColorAnimation_$onqyea() {
        return this.gg_vape_ui_click_animation_ColorAnimation_d();
    }
}

