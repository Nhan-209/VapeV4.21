package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameHeaderCloseClickHandler;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameToggleSelectedModuleClickHandler;
import gg.vape.ui.font.SmoothFontRenderer;
import java.awt.Color;

public class HudModuleConfigFrameHeaderComponent
extends FrameHeaderComponent {
    private IconButtonComponent K = new IconButtonComponent("newstar");
    private HudModuleConfigFrame I;
    private IconButtonComponent R = new IconButtonComponent("moduleback");

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void I() {
    }


    public IconButtonComponent U$src$Lgg_vape_ui_click_component_IconButtonComponent_$1p5p8kd() {
        return this.R;
    }

    public HudModuleConfigFrameHeaderComponent(HudModuleConfigFrame hudModuleConfigFrame) {
        super(hudModuleConfigFrame);
        this.I = hudModuleConfigFrame;
        this.K.r(new HudModuleConfigFrameToggleSelectedModuleClickHandler(this, hudModuleConfigFrame));
        this.R.r(new HudModuleConfigFrameHeaderCloseClickHandler(this, hudModuleConfigFrame));
        this.H(this.K, this.R);
    }

    @Override
    public void u() {
    }

    @Override
    public void F() {
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        Color color = HudModuleConfigFrameHeaderComponent.J.A;
        double d = smoothFontRenderer.d(this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().getName());
        double d2 = this.n() + this.L() / 2.0 - d / 2.0;
        smoothFontRenderer.d(this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().getName(), this.G$src$D$1b2f02a() + 10.0 + 8.0, d2, color);
        this.R.K(this.G$src$D$1b2f02a() + 5.0 - 2.0);
        this.R.S(this.n());
        this.R.Y(this.L());
        this.K.Z(this.I.F$src$Lgg_vape_module_render_hud_HudModule_$vjtm3x() != null);
        if (this.I.F$src$Lgg_vape_module_render_hud_HudModule_$vjtm3x() != null) {
            this.K.K(this.G$src$D$1b2f02a() + this.A() - 7.5 - 8.0);
            this.K.S(this.n());
            this.K.Y(this.L());
            this.K.G(this.I.F$src$Lgg_vape_module_render_hud_HudModule_$vjtm3x().f$src$Z$148d2ux() ? HudModuleConfigFrameHeaderComponent.J.I : null);
        }
    }
}

