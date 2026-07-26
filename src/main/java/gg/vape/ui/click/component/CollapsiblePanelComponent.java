package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.gui.WrappedTextComponent;
import java.awt.Color;
import java.util.List;

public class CollapsiblePanelComponent
extends PanelComponent {
    private TextLabel dE;
    private FlowLayoutComponent dh;
    private String dU;
    private PanelComponent dW;
    private WrappedTextComponent dL;
    private boolean dI;
    private TruncatedTextComponent dD;

    @Override
    public double L() {
        return this.dI ? this.O(0.8).d(this.dU) : this.dL.L() + 2.0 + this.dh.L();
    }

    private void lambda$new$0() {
        this.dI = !this.dI;
        this.dE.d(this.dI ? "...more" : "...less");
    }

    public CollapsiblePanelComponent(String string, double d) {
        super(d, 0.0);
        this.dD = new TruncatedTextComponent("", "", this.A() - 10.0, 0.8f, CollapsiblePanelComponent.J.A, false);
        this.dI = true;
        this.dW = new PanelComponent(this.A(), 0.0);
        this.dU = string;
        this.dE = new TextLabel("...more", 1.0);
        this.dL = new WrappedTextComponent(string, 0.8, CollapsiblePanelComponent.J.Z, false);
        this.dL.c(d);
        this.dh = new FlowLayoutComponent(d);
        this.d(false);
        this.dW.d(false);
        this.dh.d(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.dW.h(this.dE, new Object[0]);
        this.H(this.dW, this.dh);
        this.dE.y(0.8);
        this.dE.a(false);
        this.dE.l(Color.WHITE);
        this.dE.o(30.0);
        this.dD.R(CollapsiblePanelComponent.J.Z);
        if (string.isEmpty()) {
            this.dI = false;
            this.dE.Z(false);
        }
        this.dE.r(this::lambda$new$0);
    }

    public FlowLayoutComponent N$src$Lgg_vape_ui_click_component_FlowLayoutComponent_$1f7l5nx() {
        return this.dh;
    }

    public boolean X$src$Z$1emldob() {
        return this.dI;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void D(GuiMouseEvent guiMouseEvent) {
        super.D(guiMouseEvent);
    }

    @Override
    public void c() {
        this.dD.K(this.G$src$D$1b2f02a());
        this.dD.S(this.n());
        this.dh.Z(!this.dI);
        this.dL.G(this.dU);
        this.dL.c(this.A() - 20.0);
        this.dL.o(this.A() - 20.0);
        List<String> list = this.dL.Q$src$Ljava_util_List_$1gv03oz();
        String string = list.get(0);
        this.dD.O(string);
        this.dD.D(this.A() - 20.0);
        this.dD.o(list.size() > 1 ? this.dL.O(0.8).N(string) : this.dL.A() - 20.0);
        this.dE.c(true);
        if (this.dI) {
            this.dE.K(this.G$src$D$1b2f02a() + this.dD.A() + 2.0);
            this.dE.S(this.n());
            this.dW.u(this.dD.L());
        } else {
            String string2 = list.get(list.size() - 1);
            double d = this.dL.O(0.8).N(string2);
            this.dE.K(this.G$src$D$1b2f02a() + d + 3.0);
            this.dE.S(this.n() + this.dL.L() - this.dD.L());
            this.dW.u(this.dL.L());
        }
        this.dE.Y(this.dD.L());
        super.c();
        if (this.dI) {
            this.dD.c();
        } else {
            this.dL.K(this.G$src$D$1b2f02a());
            this.dL.S(this.n());
            this.dL.c();
        }
    }
}

