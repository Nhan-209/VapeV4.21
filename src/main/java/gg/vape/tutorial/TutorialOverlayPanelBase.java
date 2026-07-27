package gg.vape.tutorial;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.wrapper.impl.Minecraft;

public abstract class TutorialOverlayPanelBase
extends FrameComponent {
    @Override
    public void v() {
    }


    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void V() {
    }

    @Override
    public double C() {
        return 20.0;
    }

    @Override
    public void Y() {
        if (this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() instanceof Frame) {
            this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().K((double)(Minecraft.J() / 4) - this.x() / 2.0);
            this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().S((double)(Minecraft.h() / 4) - this.C() / 2.0);
        }
    }

    @Override
    public double x() {
        return 20.0;
    }
}

