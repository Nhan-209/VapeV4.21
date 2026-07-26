package gg.vape.ui.click.component;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class CenteredGlyphComponent
extends GuiComponent {
    private String o;
    private float K;
    private float I;
    private Color R;

    public void H(Color color) {
        this.R = color;
    }

    @Override
    public void u() {
    }

    @Override
    public void I() {
    }

    public void B(String string) {
        this.o = string;
    }

    public String L$src$Ljava_lang_String_$p6m3nd() {
        return this.o;
    }

    public CenteredGlyphComponent(String string, float f, float f2, Color color) {
        this.o = string;
        this.I = f;
        this.K = f2;
        this.R = color;
    }

    @Override
    public double x() {
        return this.I;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public void H(float f) {
        this.K = f;
    }

    public void Z(float f) {
        this.I = f;
    }

    public Color L$src$Ljava_awt_Color_$ombltl() {
        return this.R;
    }

    @Override
    public void F() {
    }

    public float b$src$F$1epz9s3() {
        return this.I;
    }

    public CenteredGlyphComponent(String string, float f, float f2) {
        this(string, f, f2, Color.white);
    }

    public float e() {
        return this.K;
    }

    @Override
    public double C() {
        return this.K;
    }

    @Override
    public void H() {
        float f = this.b$src$F$1epz9s3() / 2.0f;
        float f2 = this.e() / 2.0f;
        ImageRenderer.E(this.R, (float)this.G$src$D$1b2f02a() + f, (float)this.n() + f2, this.o, this.I, this.K, false);
    }
}

