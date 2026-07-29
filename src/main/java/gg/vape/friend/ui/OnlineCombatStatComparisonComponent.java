package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineCombatStatsSettingsFrame;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.value.SliderComponentBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class OnlineCombatStatComparisonComponent
extends SliderComponentBase {
    private OnlineCombatStatsSettingsFrame O;
    private int a;
    private int R;

    private void P() {
        Color color;
        double d;
        double d2;
        String string;
        SmoothFontRenderer smoothFontRenderer;
        double d3;
        String string2;
        StringBuilder stringBuilder;
        SmoothFontRenderer smoothFontRenderer2 = this.getFontRenderer(0.85);
        int n = this.a - this.R;
        int n2 = Math.abs(n);
        boolean bl = n < 0;
        boolean bl2 = n > 0;
        boolean bl3 = this.O.isManagedByClickGui();
        Color color2 = bl3 ? this.O.applyDefaultEditorAlpha(OnlineCombatStatComparisonComponent.J.Z) : OnlineCombatStatComparisonComponent.J.Z;
        Color color3 = bl3 ? this.O.applyDefaultEditorAlpha(OnlineCombatStatComparisonComponent.J.h) : OnlineCombatStatComparisonComponent.J.h;
        Color color4 = bl3 ? this.O.applyDefaultEditorAlpha(OnlineCombatStatComparisonComponent.J.l) : OnlineCombatStatComparisonComponent.J.l;
        Color color5 = bl3 ? this.O.applyDefaultEditorAlpha(OnlineCombatStatComparisonComponent.J.B) : OnlineCombatStatComparisonComponent.J.B;
        Color color6 = bl3 ? this.O.applyDefaultEditorAlpha(OnlineCombatStatComparisonComponent.J.d) : OnlineCombatStatComparisonComponent.J.d;
        StringBuilder stringBuilder2 = new StringBuilder();
        if (bl2) {
            stringBuilder = stringBuilder2;
            string2 = "+";
        } else {
            StringBuilder stringBuilder3 = stringBuilder2;
            if (bl) {
                stringBuilder = stringBuilder3;
                string2 = "-";
            } else {
                stringBuilder = stringBuilder3;
                string2 = "";
            }
        }
        String string3 = stringBuilder.append(string2).append(n2).toString();
        double d4 = smoothFontRenderer2.N(string3) + 5.0;
        if (d4 < 10.0) {
            d4 = 10.0;
        }
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 5.0, this.n() + 5.0, d4, 10.0, color4);
        double d5 = d3 = this.n() + 5.0 + 2.0;
        double d6 = this.G$src$D$1b2f02a() + 5.0 + d4 / 2.0;
        String string4 = string3;
        SmoothFontRenderer smoothFontRenderer3 = smoothFontRenderer2;
        if (bl2) {
            smoothFontRenderer = smoothFontRenderer3;
            string = string4;
            d2 = d6;
            d = d5;
            color = color5;
        } else {
            double d7 = d5;
            double d8 = d6;
            String string5 = string4;
            SmoothFontRenderer smoothFontRenderer4 = smoothFontRenderer3;
            if (bl) {
                smoothFontRenderer = smoothFontRenderer4;
                string = string5;
                d2 = d8;
                d = d7;
                color = color6;
            } else {
                smoothFontRenderer = smoothFontRenderer4;
                string = string5;
                d2 = d8;
                d = d7;
                color = color2;
            }
        }
        smoothFontRenderer.W(string, d2, d, color);
        smoothFontRenderer2.d(this.getLabel(), this.G$src$D$1b2f02a() + 10.0 + d4, d3, color2);
        String string6 = this.a > 9 ? "" + this.a : "0" + this.a;
        String string7 = this.R > 9 ? "" + this.R : "0" + this.R;
        double d9 = smoothFontRenderer2.N(string6);
        double d10 = smoothFontRenderer2.N(string6);
        double d11 = smoothFontRenderer2.N("/");
        double d12 = this.G$src$D$1b2f02a() + this.A() - 5.0 - d10;
        smoothFontRenderer2.d(string7, d12, d3, color3);
        smoothFontRenderer2.d("/", d12 - 5.0 - d11, d3, color3);
        smoothFontRenderer2.d(string6, d12 - 10.0 - d11 - d9, d3, color2);
        double d13 = this.n() + 22.5;
        double d14 = 6.0;
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + 5.0, d13 - 0.5, this.A() - 10.0, 2.0, color4);
        int n3 = this.a + this.R;
        if (n3 == 0) {
            GuiRenderPrimitives.F("greenglowsquare", this.G$src$D$1b2f02a() + this.A() / 2.0, d13, 20.0, 20.0, Color.WHITE);
            return;
        }
        double d15 = (double)n2 / (double)n3;
        double d16 = this.A() / 2.0 - 5.0 - d14;
        double d17 = this.a >= this.R ? this.G$src$D$1b2f02a() + 5.0 + d16 - d16 * d15 : this.G$src$D$1b2f02a() + 5.0 + d16 + d14 * 2.0 + d16 * d15;
        if (this.a >= this.R) {
            GuiRenderPrimitives.C(d17, d13 - 0.5, this.G$src$D$1b2f02a() + this.A() / 2.0 - d17, 2.0, color5);
        } else {
            GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + this.A() / 2.0, d13 - 0.5, d17 - this.G$src$D$1b2f02a() - this.A() / 2.0, 2.0, color6);
        }
        GuiRenderPrimitives.F(this.a >= this.R ? "greenglowsquare" : "redglowsquare", this.G$src$D$1b2f02a() + this.A() / 2.0, d13, 20.0, 20.0, Color.WHITE);
        GuiRenderPrimitives.F(this.a >= this.R ? "greenglowsquare" : "redglowsquare", d17, d13, 20.0, 20.0, Color.WHITE);
    }


    public OnlineCombatStatComparisonComponent(String string, OnlineCombatStatsSettingsFrame onlineCombatStatsSettingsFrame) {
        super(string);
        this.O = onlineCombatStatsSettingsFrame;
    }

    @Override
    public double C() {
        return 30.0;
    }

    public void N(int n) {
        this.R = n;
    }

    @Override
    public void u() {
    }

    @Override
    public double x() {
        return 20.0;
    }

    @Override
    public void I() {
        this.P();
    }

    @Override
    public void H() {
        this.P();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void F() {
    }

    public void n(int n) {
        this.a = n;
    }
}

