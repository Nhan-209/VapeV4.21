package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineCombatStatsSettingsFrame;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class OnlineCombatStatsTargetLabelComponent
extends GuiComponent {
    private OnlineCombatStatsSettingsFrame I;

    private void R() {
        SmoothFontRenderer smoothFontRenderer = this.getFontRenderer(0.9);
        double d = smoothFontRenderer.d("Aim");
        double d2 = this.n() + this.L() / 2.0 - d / 2.0;
        double d3 = this.n() + this.L() / 2.0 - 3.0;
        Color color = this.I.isManagedByClickGui() ? this.I.applyDefaultEditorAlpha(OnlineCombatStatsTargetLabelComponent.J.Z) : OnlineCombatStatsTargetLabelComponent.J.Z;
        ImageRenderer.drawImage(color, (float)this.G$src$D$1b2f02a() + 5.0f, (float)d3, "newaim", 6.0f, 6.0f, false);
        smoothFontRenderer.d(this.I.b$src$Ljava_lang_String_$tewuww(), this.G$src$D$1b2f02a() + 8.0 + 10.0, d2, color);
    }

    @Override
    public void u() {
    }

    @Override
    public void I() {
        this.R();
    }


    public OnlineCombatStatsTargetLabelComponent(OnlineCombatStatsSettingsFrame onlineCombatStatsSettingsFrame) {
        this.I = onlineCombatStatsSettingsFrame;
    }

    @Override
    public void H() {
        this.R();
    }

    @Override
    public void F() {
    }

    @Override
    public double x() {
        return 110.0;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public double C() {
        return 20.0;
    }
}

