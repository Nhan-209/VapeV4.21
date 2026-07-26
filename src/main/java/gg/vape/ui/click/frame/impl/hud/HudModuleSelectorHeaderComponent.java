package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorOpenConfigFrameClickHandler;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorOpenOverviewClickHandler;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class HudModuleSelectorHeaderComponent
extends FrameHeaderComponent {
    private float I;
    private IconButtonComponent Q;
    private IconButtonComponent G = new IconButtonComponent("newsettings");

    public HudModuleSelectorHeaderComponent(HudModuleSelectorFrame hudModuleSelectorFrame, float f) {
        super(hudModuleSelectorFrame);
        this.Q = new IconButtonComponent("min");
        this.I = f;
        this.G.r(new HudModuleSelectorOpenConfigFrameClickHandler(this));
        this.Q.r(new HudModuleSelectorOpenOverviewClickHandler(this));
        this.H(this.Q, this.G);
    }

    public HudModuleSelectorHeaderComponent(HudModuleSelectorFrame hudModuleSelectorFrame) {
        this(hudModuleSelectorFrame, 1.0f);
    }

    @Override
    public void u() {
    }

    @Override
    public void I() {
    }

    @Override
    public void F() {
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    static IconButtonComponent a(HudModuleSelectorHeaderComponent hudModuleSelectorHeaderComponent) {
        return hudModuleSelectorHeaderComponent.G;
    }

    @Override
    public double A() {
        return this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().A();
    }

    @Override
    public void H() {
        float f = 8.0f * this.I;
        GuiRenderPrimitives.h("legit_mode_icon", (int)(this.G$src$D$1b2f02a() + 5.0 + 8.0), (int)(this.n() + this.L() / 2.0), f, f, Color.white);
        this.Q.K(this.G$src$D$1b2f02a() + this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().A() - 10.0 - 8.0);
        this.Q.S(this.n());
        this.Q.Y(this.L());
        this.G.K(this.G$src$D$1b2f02a() + this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().A() - 30.0 - 8.0);
        this.G.S(this.n());
        this.G.Y(this.L());
    }
}

