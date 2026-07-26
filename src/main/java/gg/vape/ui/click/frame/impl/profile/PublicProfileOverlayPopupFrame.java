package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOverlayCloseClickHandler;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOverlayOutsideClickCloseHandler;
import java.awt.Color;

public class PublicProfileOverlayPopupFrame
extends PopupFrame {
    private final GuiComponent rT;
    private int rP = 0;
    private int rG = 0;
    private final PanelComponent rK = (PanelComponent)this.D$src$Lgg_vape_ui_click_component_GuiComponent_$srx612();
    private boolean rD = false;

    public boolean X$src$Z$n2tvta() {
        return this.rD;
    }

    public PublicProfileOverlayPopupFrame(GuiComponent guiComponent, GuiComponent guiComponent2) {
        super(guiComponent, new PanelComponent(0.0, 0.0));
        this.rT = guiComponent2;
        this.rK.h(guiComponent2, new Object[0]);
        this.rK.T(new Color(0, 0, 0, 130));
        this.rK.d(true);
        this.rK.C$src$V$nadrmg();
        this.rK.q(this.X$src$Lgg_vape_ui_click_frame_Frame_$1aw5qf9().A());
        this.rK.u(this.X$src$Lgg_vape_ui_click_frame_Frame_$1aw5qf9().L() + 2.0);
        this.rK.j(new PublicProfileOverlayCloseClickHandler(this));
        this.Z(new PublicProfileOverlayOutsideClickCloseHandler(this));
    }

    public void T(int n) {
        this.rG = n;
    }

    public void p(boolean bl) {
        this.rD = bl;
    }

    static boolean C(PublicProfileOverlayPopupFrame publicProfileOverlayPopupFrame) {
        return publicProfileOverlayPopupFrame.rD;
    }

    public void Q(int n) {
        this.rP = n;
    }

    @Override
    public void c() {
        this.K(this.X$src$Lgg_vape_ui_click_frame_Frame_$1aw5qf9().G$src$D$1b2f02a());
        this.S(this.X$src$Lgg_vape_ui_click_frame_Frame_$1aw5qf9().n());
        this.rK.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().z(this.rT, "offsetx " + this.rG + ", offsety " + this.rP);
        this.l$src$V$1mibm4x();
        super.c();
    }
}

