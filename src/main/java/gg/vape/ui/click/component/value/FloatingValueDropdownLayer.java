package gg.vape.ui.click.component.value;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.value.AbstractListValueComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.wrapper.impl.Minecraft;

public abstract class FloatingValueDropdownLayer<T extends AbstractListValueComponent>
extends Frame {
    private double Fr;
    private boolean FF;
    private T F3;
    private double Fp;

    @Override
    public boolean V$src$Z$1xhop3l() {
        return this.F3 != null && ((GuiComponent)this.F3).V$src$Z$1xhop3l() && ((GuiComponent)this.F3).B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() != null && ((GuiComponent)this.F3).B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().V$src$Z$1xhop3l() && ((AbstractListValueComponent)this.F3).P$src$Z$og01j6();
    }

    @Override
    public void u() {
        super.u();
        boolean bl = this.V$src$Z$1xhop3l();
        if (bl && !this.FF) {
            this.e();
        }
        this.FF = bl;
    }

    @Override
    public void v() {
    }

    public void h() {
        if (this.F3 == null || ((GuiComponent)this.F3).B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() == null) {
            return;
        }
        double d = ((GuiComponent)this.F3).L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().G$src$D$1b2f02a() + ((GuiComponent)this.F3).L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().A() + 1.0;
        if (d != this.Fr || ((GuiComponent)this.F3).n() != this.Fp) {
            double d2 = ((GuiComponent)this.F3).n();
            FrameComponent frameComponent = ((GuiComponent)this.F3).B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
            if (frameComponent.k$src$Z$if6xeb()) {
                d2 = Math.min(d2, frameComponent.n() + frameComponent.d$src$D$ibccpu() - ((GuiComponent)this.F3).L());
                d2 = Math.max(d2, frameComponent.n() + (frameComponent.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() != null ? frameComponent.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() : 0.0));
            }
            double d3 = Minecraft.J() / 2;
            if (d + this.A() / 2.0 > d3) {
                this.M(d - ((GuiComponent)this.F3).L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().A() - this.A() - 2.0, d2);
            } else {
                this.M(d, d2);
            }
            this.Fr = ((GuiComponent)this.F3).G$src$D$1b2f02a();
            this.Fp = ((GuiComponent)this.F3).n();
        }
    }

    @Override
    public void Y() {
        if (this.F3 == null || ((GuiComponent)this.F3).B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb() == null) {
            return;
        }
        ClientSettings.fW.N(((GuiComponent)this.F3).B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), this);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public abstract void e();

    public T C$src$Lgg_vape_ui_click_component_value_AbstractListVa$13qpumn() {
        return this.F3;
    }

    @Override
    public String getName() {
        if (this.F3 == null) {
            return "sidecar_" + this.hashCode();
        }
        return "sidecar_" + this.F3.hashCode();
    }

    public FloatingValueDropdownLayer(T t) {
        this.T(FloatingValueDropdownLayer.J.i);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.K(300.0);
        this.S(100.0);
        this.F3 = t;
        this.Y(false);
        this.L(false, false);
    }
}

