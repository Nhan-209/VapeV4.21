package gg.vape.ui.click.component.value;

import gg.vape.module.blatant.AntiBotBooleanValue;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class AntiBotBooleanValueOptionRow
extends GuiComponent {
    private static final double I = 1.0;
    private final AntiBotBooleanValue K;
    private final String O;
    private static final double i = 8.0;
    private static final double R = 8.0;

    @Override
    public void I() {
    }

    @Override
    public double C() {
        return 16.0;
    }

    @Override
    public void F() {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public String N() {
        Integer n = (Integer)this.K.K();
        if (n == null) {
            return "None";
        }
        return String.format("#%06X", n);
    }

    public AntiBotBooleanValueOptionRow(AntiBotBooleanValue antiBotBooleanValue) {
        this(antiBotBooleanValue, "Team color");
    }

    @Override
    public double x() {
        return 110.0;
    }

    public AntiBotBooleanValueOptionRow(AntiBotBooleanValue antiBotBooleanValue, String string) {
        this.K = antiBotBooleanValue;
        this.O = string;
        this.C(antiBotBooleanValue);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void H() {
        this.onDisable();
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        String string = this.O;
        smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + 5.0, this.n() + this.L() / 2.0 - smoothFontRenderer.d(string) / 2.0, AntiBotBooleanValueOptionRow.J.Z);
        Integer n = (Integer)this.K.K();
        Color color = n != null ? new Color(n) : Color.GRAY;
        double d = this.G$src$D$1b2f02a() + this.A() - 5.0 - 8.0;
        double d2 = this.n() + (this.L() - 8.0) / 2.0;
        GuiRenderPrimitives.B(d, d2, 8.0, 8.0, color, 1.0f);
    }

    public AntiBotBooleanValue n$src$Lgg_vape_module_blatant_AntiBotBooleanValue_$1y28mnc() {
        return this.K;
    }

    @Override
    public void u() {
        this.w("Current team color: " + this.N());
    }
}

