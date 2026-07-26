package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class AnimatedRingIconButtonComponent
extends IconButtonComponent {
    private final ColorAnimation VT;

    @Override
    public void H() {
        super.H();
        float f = (float)this.A();
        GuiRenderPrimitives.m((float)(this.G$src$D$1b2f02a() + this.A() / 2.0 - (double)(f / 2.0f)), (float)(this.n() + this.L() / 2.0 - (double)(f / 2.0f)), f, 1.5f, 1.0f, this.VT.getInterpolatedColor());
    }

    @Override
    public void onEnable() {
        this.VT.J();
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public AnimatedRingIconButtonComponent(String string, Color color, double d, double d2, double d3) {
        super(string, d, d2, d3);
        this.VT = new ColorAnimation(0.1, color, color.brighter());
    }

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.VT.J();
        }
        super.F();
    }
}

