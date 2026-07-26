package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class IconGlyphComponent
extends GuiComponent {
    private static String[] Q;
    private final float G;
    private String a;
    private boolean O;
    private final float R;
    private Color I;

    public IconGlyphComponent(String string, float f, float f2) {
        this.a = string;
        this.R = f;
        this.G = f2;
        this.I = Color.white;
    }

    @Override
    public void I() {
    }

    @Override
    public double x() {
        return this.R;
    }

    public IconGlyphComponent(String string, float f, float f2, Color color) {
        this.a = string;
        this.R = f;
        this.G = f2;
        this.I = color;
    }

    public Color j$src$Ljava_awt_Color_$1st6xdi() {
        return this.I;
    }

    public String P() {
        return this.a;
    }

    public static void h(String[] stringArray) {
        Q = stringArray;
    }

    public static String[] P$src$ALjava_lang_String_$vx5tx3() {
        return Q;
    }

    static {
        if (IconGlyphComponent.P$src$ALjava_lang_String_$vx5tx3() != null) {
            IconGlyphComponent.h(new String[3]);
        }
    }

    @Override
    public double C() {
        return this.G;
    }

    public void q(String string) {
        this.a = string;
    }

    public boolean N() {
        return this.O;
    }

    public void S(Color color) {
        this.I = color;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void H() {
        ImageRenderer.E(this.I, this.O ? (float)((int)this.G$src$D$1b2f02a()) : (float)this.G$src$D$1b2f02a(), this.O ? (float)((int)this.n()) : (float)this.n(), this.a, this.R, this.G, false);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void r(boolean bl) {
        this.O = bl;
    }

    @Override
    public void F() {
    }

    @Override
    public void u() {
    }
}

