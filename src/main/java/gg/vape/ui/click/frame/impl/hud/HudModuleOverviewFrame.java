package gg.vape.ui.click.frame.impl.hud;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.HudModuleOverviewHeaderComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleOverviewListFrame;

public class HudModuleOverviewFrame
extends Frame {
    private HudModuleOverviewListFrame gq = new HudModuleOverviewListFrame(this);
    private static final String eb = "LegitMinModuleFrame";
    private boolean gR = false;

    @Override
    public void c() {
        super.c();
        if (!this.gR) {
            this.gq.U();
            this.gR = true;
        }
        this.gq.o(this.A());
        this.gq.Y(this.L());
        this.gq.M(this.G$src$D$1b2f02a(), this.n() + this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L());
    }

    @Override
    public void t(boolean bl, boolean bl2) {
        super.t(bl, bl2);
        this.gq.Z(bl);
    }

    @Override
    public double L() {
        return 50.0;
    }

    @Override
    public double C() {
        return 20.0;
    }

    @Override
    public String getName() {
        return eb;
    }

    public HudModuleOverviewFrame() {
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().t(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().I(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().U(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().u(false);
        this.T(HudModuleOverviewFrame.J.i);
        this.Y(new HudModuleOverviewHeaderComponent(this));
        this.Z(false);
        this.L(false, false);
        this.g(true);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void v() {
    }

    public HudModuleOverviewListFrame s$src$Lgg_vape_ui_click_frame_impl_hud_HudModuleOvervi$1xo3dwo() {
        return this.gq;
    }

    @Override
    public double x() {
        return this.A();
    }

    @Override
    public void U() {
        super.U();
        this.gq.U();
    }

    @Override
    public double A() {
        return 137.5;
    }

    @Override
    public void Y() {
    }
}

