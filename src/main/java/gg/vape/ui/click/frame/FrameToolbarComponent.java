package gg.vape.ui.click.frame;

import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.ui.click.frame.FrameToolbarEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameToolbarComponent
extends FrameHeaderComponent {
    private static String My;
    private final PanelComponent K;
    private final SimpleTextLabelComponent o;
    private boolean Mz = false;
    private final String i;
    private boolean v = false;
    private float G = 1.0f;
    private final SquareIconButtonComponent MK = new SquareIconButtonComponent("newclose", 1.5);
    private final PaddedComponent O;
    private final FlowLayoutComponent I;
    private final List<FrameToolbarEntry> Mu = new ArrayList<FrameToolbarEntry>();
    private final IconButtonComponent MQ;
    private final String R;
    private final FlowLayoutComponent Q;

    public void C(FrameToolbarEntry frameToolbarEntry) {
        this.Mu.add(frameToolbarEntry);
        Collections.reverse(this.Mu);
        FrameToolbarEntry[] frameToolbarEntryArray = new FrameToolbarEntry[this.Mu.size()];
        frameToolbarEntryArray = this.Mu.toArray(frameToolbarEntryArray);
        Collections.reverse(this.Mu);
        this.B(frameToolbarEntryArray);
    }

    public void B(FrameToolbarEntry ... frameToolbarEntryArray) {
        this.I.t$src$V$zbu1jn();
        for (FrameToolbarEntry frameToolbarEntry : frameToolbarEntryArray) {
            this.I.h(frameToolbarEntry.P(), new Object[0]);
        }
        this.I.h(this.Q, new Object[0]);
    }

    @Override
    public boolean D(int n, int n2) {
        if (this.v && (this.MQ.Q().J(n, n2) || this.I.Q().J(n, n2))) {
            return false;
        }
        return this.Q().J(n, n2);
    }

    public FlowLayoutComponent Q$src$Lgg_vape_ui_click_component_FlowLayoutComponent_$1hjyj7y() {
        return this.Q;
    }

    public static String s() {
        return My;
    }

    public void s(boolean bl) {
        this.I(this.r$src$Ljava_lang_String_$1y8r681() + " settings", bl);
    }


    public IconButtonComponent r$src$Lgg_vape_ui_click_component_IconButtonComponent_$86hdsq() {
        return this.MQ;
    }

    public boolean v() {
        return this.v;
    }

    public void V(boolean bl) {
        this.Mz = bl;
        this.Q$src$Lgg_vape_ui_click_component_FlowLayoutComponent_$1hjyj7y().Z(true);
    }

    public FrameToolbarComponent(Frame frame, String string, String string2) {
        super(frame);
        this.i = string;
        this.R = string2;
        this.K = new PanelComponent(this.x() - 8.0, this.C() - 8.0);
        this.O = new PaddedComponent(4.0, 4.0, this.K);
        FlowLayoutComponent flowLayoutComponent = new FlowLayoutComponent(90.0);
        this.MQ = new IconButtonComponent(string);
        flowLayoutComponent.h(this.MQ, new Object[0]);
        this.o = new SimpleTextLabelComponent(string2, 0.9, FrameToolbarComponent.J.A);
        this.o.g(0.0f);
        this.o.z(0.0f);
        flowLayoutComponent.h(new SpacerComponent(1.0, 1.0), new Object[0]);
        flowLayoutComponent.h(this.o, new Object[0]);
        this.I = new FlowLayoutComponent(60.0);
        this.K.h(flowLayoutComponent, new Object[0]);
        this.K.h(this.I, "alignright");
        this.Q = new FlowLayoutComponent(10.0);
        this.Q.H(new SpacerComponent(10.0, 2.0), this.MK);
        this.Q.Z(false);
        this.H(this.O);
        this.MQ.G(FrameToolbarComponent.J.f);
        flowLayoutComponent.d(false);
        this.I.d(false);
        this.K.d(false);
        this.O.d(false);
        this.MK.E(3.5);
        this.MK.A(1.2);
        this.B(new FrameToolbarEntry[0]);
    }

    public void Q(GuiComponent guiComponent) {
        FrameToolbarEntry frameToolbarEntry = new FrameToolbarEntry(guiComponent);
        this.C(frameToolbarEntry);
    }

    public void x(float f) {
        this.G = f;
        if (!this.v) {
            this.MQ.A(f);
        }
    }

    public void I(String string, boolean bl) {
        this.MQ.H("moduleback");
        this.MQ.A(0.85f);
        this.o.G(string);
        if (bl) {
            this.Q.Z(true);
        }
        for (FrameToolbarEntry frameToolbarEntry : this.Mu) {
            if (!frameToolbarEntry.h() && !bl) continue;
            frameToolbarEntry.P().Z(false);
        }
        this.v = true;
    }

    public void D$src$V$1njh5lz() {
        this.O.H(true);
        this.O.K(this.G$src$D$1b2f02a());
        this.O.S(this.n());
    }

    public static void k(String string) {
        My = string;
    }

    public String r$src$Ljava_lang_String_$1y8r681() {
        return this.R;
    }

    public void g(double d) {
        this.o(d);
        this.q(d);
        this.K.q(d - 8.0);
    }

    static {
        FrameToolbarComponent.k("Mcksjb");
    }

    public void h() {
        this.MQ.H(this.i);
        this.MQ.A(this.G);
        this.o.G(this.R);
        this.Q.Z(this.Mz);
        for (FrameToolbarEntry frameToolbarEntry : this.Mu) {
            frameToolbarEntry.P().Z(true);
        }
        this.v = false;
    }

    public SquareIconButtonComponent w$src$Lgg_vape_ui_click_component_SquareIconButtonComp$1a3t2u0() {
        return this.MK;
    }
}

