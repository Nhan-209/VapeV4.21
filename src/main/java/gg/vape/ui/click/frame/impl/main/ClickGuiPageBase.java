package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.FrameScrollbarPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiContentPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiPanelBase;

public class ClickGuiPageBase
extends ClickGuiPanelBase {
    private static final double Qd = 10.0;
    private final ClickGuiContentPanel Qu;
    private static final double Qt = 8.0;
    private final SimpleTextLabelComponent Qp;
    private final ClickGuiContentPanel QH;
    private final double Qk;
    private final ClickGuiContentPanel Q2;
    private final ClickGuiContentPanel QX;
    private final ClickGuiContentPanel QR;
    private final double QU;
    private static final double Qx = 16.0;
    private static final double Qf = 16.0;

    public void U(String string) {
        this.Qp.G(string);
    }

    public ClickGuiContentPanel f$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$o6l04() {
        return this.QX;
    }

    protected FlowLayoutComponent p(double d, double d2) {
        FlowLayoutComponent flowLayoutComponent = new FlowLayoutComponent(d, d2);
        flowLayoutComponent.P(true);
        flowLayoutComponent.W(true);
        flowLayoutComponent.o(d);
        flowLayoutComponent.t(d2);
        flowLayoutComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        flowLayoutComponent.I(true);
        return flowLayoutComponent;
    }

    public ClickGuiContentPanel y$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$hqf2uf() {
        return this.QH;
    }

    public ClickGuiContentPanel L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi() {
        return this.Q2;
    }

    public ClickGuiContentPanel H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u() {
        return this.Qu;
    }

    public ClickGuiContentPanel I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr() {
        return this.QR;
    }

    private SimpleTextLabelComponent t(String string) {
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent(string);
        simpleTextLabelComponent.c(0);
        simpleTextLabelComponent.g(0.0f);
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.i(1.0);
        simpleTextLabelComponent.T$src$V$1orl066(ClickGuiPageBase.J.A);
        return simpleTextLabelComponent;
    }

    private ClickGuiContentPanel l(double d, double d2) {
        ClickGuiContentPanel clickGuiContentPanel = new ClickGuiContentPanel(d, d2);
        clickGuiContentPanel.P(true);
        clickGuiContentPanel.W(true);
        clickGuiContentPanel.o(d);
        clickGuiContentPanel.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        clickGuiContentPanel.d(false);
        clickGuiContentPanel.t(d2);
        return clickGuiContentPanel;
    }

    private ClickGuiContentPanel D(double d) {
        ClickGuiContentPanel clickGuiContentPanel = new ClickGuiContentPanel(d, 16.0);
        clickGuiContentPanel.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        clickGuiContentPanel.u(true);
        clickGuiContentPanel.P(true);
        clickGuiContentPanel.W(true);
        clickGuiContentPanel.o(d);
        clickGuiContentPanel.d(false);
        return clickGuiContentPanel;
    }

    public ClickGuiPageBase(double d, double d2, double d3, double d4, String string) {
        super(d, d2);
        this.d(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.Qk = d3;
        this.Qu = this.D(d3);
        this.Qp = this.t(string);
        this.Qp.Y(this.Qu.L());
        this.Qu.h(new PaddedComponent(0.0, 0.0, 2.0, 0.0, this.Qp), new Object[0]);
        this.QH = this.l(d3, d2 - 10.0);
        this.QH.h(this.Qu, "wrap");
        this.QH.h(new SpacerComponent(0.0, 4.0), "wrap");
        this.Q2 = this.l(d3, this.QH.L() - this.QH.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y() - 2.0);
        this.Q2.t(this.Q2.L());
        this.Q2.F(FrameScrollbarPlacement.OUTSIDE);
        this.Q2.I(true);
        this.QH.h(new PaddedComponent(1.0, 0.0, 0.0, 0.0, this.Q2), "wrap");
        this.H(new PaddedComponent(7.0, 5.0, 10.0, 0.0, this.QH));
        this.QU = this.A() - this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C() - 20.0 + d4;
        this.QX = this.l(this.QU, d2 - 18.0);
        this.QX.u(true);
        this.QR = this.l(this.QX.A(), this.QX.L() - 0.1);
        this.QR.u(true);
        this.QR.t(this.QR.L());
        this.QR.F(FrameScrollbarPlacement.OUTSIDE);
        this.QR.I(true);
        this.QX.h(this.QR, "wrap");
        this.H(new PaddedComponent(8.0, 5.0, 10.0 - d4, 10.0, this.QX));
    }

    @Override
    public void K(double d, double d2, double d3) {
        super.K(d, d2, d3);
        this.Qu.o(this.Qk);
        this.Q2.o(this.Qk);
        double d4 = Math.max(0.0, this.QU - d3);
        this.QX.o(d4);
        this.QR.o(d4);
    }
}

