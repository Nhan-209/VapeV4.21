package gg.vape.ui.click.frame.impl.main;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.layout.ClickGuiContentLayout;

public class ClickGuiContentPanel
extends PanelComponent {
    private final ClickGuiContentLayout qM = new ClickGuiContentLayout(this);
    private static final String eb = "wrap, widthwrap";

    @Override
    public double x() {
        return 0.0;
    }

    public ClickGuiContentLayout E() {
        return this.qM;
    }

    public void u(boolean bl) {
        this.qM.C(bl);
    }

    @Override
    public void Y(double d) {
        if (d != this.L()) {
            super.u(d);
            this.H(true);
        }
    }

    public void x(String string) {
        this.qM.M(string);
    }

    @Override
    public void S() {
        super.S();
        this.qM.E();
    }

    public boolean C$src$Z$1fo1jr4() {
        return this.qM.K();
    }

    @Override
    public void Y() {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ClickGuiContentPanel(double d) {
        this(d, 0.0);
    }

    public ClickGuiContentPanel(double d, double d2) {
        super(d, d2);
        this.qM.t(false);
        this.qM.M(false);
        this.qM.U(false);
        this.qM.I(false);
        this.qM.u(false);
        this.qM.M(eb);
        this.N(this.qM);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public void o(double d) {
        if (d != this.A()) {
            super.q(d);
            this.H(true);
        }
    }

    @Override
    public void V() {
    }

    @Override
    public void v() {
    }
}

