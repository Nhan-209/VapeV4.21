package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class SquareIconButtonComponent
extends IconButtonComponent {
    private final ColorAnimation Ws;

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            this.Ws.J();
        }
        super.F();
    }

    public SquareIconButtonComponent(String string, double d, Color color, Color color2, double d2, double d3) {
        super(string, d, d2, d3);
        this.Ws = new ColorAnimation(0.1, color, color2);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SquareIconButtonComponent(String string, double d) {
        this(string, d, new Color(0, 0, 0, 0), SquareIconButtonComponent.J.h, 8.0, 8.0);
    }

    @Override
    public void H() {
        float f = (float)(this.A() * (double)((float)this.K));
        GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + this.A() / 2.0 - (double)(f / 2.0f), this.n() + this.L() / 2.0 - (double)(f / 2.0f), f, 1.0, this.Ws.getInterpolatedColor());
        super.H();
    }

    public SquareIconButtonComponent(String string) {
        this(string, 1.0);
    }

    @Override
    public void onEnable() {
        this.Ws.J();
        super.onEnable();
    }
}

