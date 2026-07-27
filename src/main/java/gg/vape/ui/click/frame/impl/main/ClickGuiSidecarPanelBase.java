package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SelectableTextRowToggleClickListener;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public abstract class ClickGuiSidecarPanelBase
extends GuiComponent {
    @Nullable
    private String WJ;
    @Nullable
    private Runnable I;
    private double O;
    private static final double i = 5.0;
    @Nullable
    private Runnable v;
    private static int[] a;
    private static final double We = 12.0;
    private final SquareIconButtonComponent G;
    @Nullable
    private Runnable R;
    private final List<GuiComponent> Q;
    private final TruncatedTextComponent K;
    private double W7;
    private final SpacerComponent o;
    private final GlyphIconComponent WV;
    private boolean b;
    private double Wc;
    private static final double Wx = 4.0;
    private static final double W8 = 20.0;

    public void i(Color color) {
        this.K.R(color);
    }

    public void c(boolean bl) {
        this.b = bl;
    }

    protected void C(@Nullable Runnable runnable) {
        this.R = runnable;
        this.o.Z(runnable != null);
    }

    public void e(GuiComponent guiComponent) {
        if (guiComponent == null) {
            return;
        }
        this.Q.add(guiComponent);
        this.H(guiComponent);
        double d = this.G$src$D$1b2f02a() + this.A() - 4.0 - this.G.A();
        double d2 = d - 4.0;
        double d3 = guiComponent.A() > 0.0 ? guiComponent.A() : guiComponent.x();
        double d4 = guiComponent.L() > 0.0 ? guiComponent.L() : guiComponent.C();
        guiComponent.o(d3);
        guiComponent.Y(d4);
        double d5 = d2 - d3;
        double d6 = this.n() + this.L() / 2.0 - d4 / 2.0;
        guiComponent.K(d5);
        guiComponent.S(d6);
    }

    public TruncatedTextComponent U$src$Lgg_vape_ui_click_component_TruncatedTextCompone$13l4jyw() {
        return this.K;
    }

    public void X(double d) {
        this.O = d;
    }

    protected SpacerComponent R() {
        return this.o;
    }

    public void y(String string) {
        this.K.O(string);
    }

    public static void b(int[] nArray) {
        a = nArray;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public void N(Color color) {
        this.WV.o(color);
    }

    protected void N(TruncatedTextComponent truncatedTextComponent, double d, double d2, double d3, double d4) {
        truncatedTextComponent.K(d);
        truncatedTextComponent.S(d2);
        truncatedTextComponent.o(d3);
        truncatedTextComponent.Y(d4);
        truncatedTextComponent.D(d3);
    }

    @Override
    public double C() {
        return 20.0;
    }

    public GlyphIconComponent v() {
        return this.WV;
    }

    public ClickGuiSidecarPanelBase() {
        this.WV = new GlyphIconComponent("online", 5.0, 5.0, 5.0, 5.0, ClickGuiSidecarPanelBase.J.W, ClickGuiSidecarPanelBase.J.f, null);
        this.K = new TruncatedTextComponent("", 50.0, 0.75);
        this.G = new SquareIconButtonComponent("newclose", 1.2, new Color(0, 0, 0, 0), ClickGuiSidecarPanelBase.J.z, 8.0, 8.0);
        this.W7 = -1.0;
        this.Wc = -1.0;
        this.Q = new ArrayList<GuiComponent>();
        this.b = true;
        this.O = 2.0;
        this.d(false);
        this.K.C(0.0);
        this.K.R(Color.WHITE);
        this.K.p(true);
        this.G.o(ClickGuiSidecarPanelBase.J.h);
        this.G.P(ClickGuiSidecarPanelBase.J.A);
        this.G.r(this::lambda$new$0);
        this.W7 = this.G.A();
        this.Wc = this.G.L();
        this.o = new SpacerComponent(0.0, 0.0);
        this.o.Z(false);
        this.o.j(new SelectableTextRowToggleClickListener(this));
        this.H(this.WV, this.K, this.G, this.o);
        this.WV.o(ClickGuiSidecarPanelBase.J.W);
        this.WV.P(ClickGuiSidecarPanelBase.J.f);
        this.Y(20.0);
    }


    public void y(@Nullable Runnable runnable) {
        this.v = runnable;
    }

    @Override
    public double x() {
        return 110.0;
    }

    public void B(@Nullable String string) {
        this.WJ = string;
        if (this.I != null) {
            return;
        }
        boolean bl = string != null && !string.isEmpty();
        this.WV.Z(bl);
        if (bl) {
            this.WV.W(string);
            this.WV.o(ClickGuiSidecarPanelBase.J.W);
        }
    }

    private void lambda$new$0() {
        if (this.v != null) {
            this.v.run();
        }
    }

    public void N(@Nullable Runnable runnable) {
        this.I = runnable;
        boolean bl = runnable != null;
        this.WV.k$src$V$qmpccm();
        this.WV.r$src$V$1x8vu68();
        if (bl) {
            this.WV.W("moduleback");
            this.WV.Z(true);
            this.WV.P(ClickGuiSidecarPanelBase.J.f);
            this.R = runnable;
            this.o.Z(true);
        } else {
            this.R = null;
            this.o.Z(false);
            if (this.WJ != null && !this.WJ.isEmpty()) {
                this.WV.W(this.WJ);
                this.WV.Z(true);
            } else {
                this.WV.Z(false);
            }
        }
    }

    public SquareIconButtonComponent k() {
        return this.G;
    }

    public static Runnable m(ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        return clickGuiSidecarPanelBase.R;
    }

    static {
        ClickGuiSidecarPanelBase.b(new int[4]);
    }

    @Override
    public void H() {
        double d;
        double d2;
        double d3;
        double d4 = this.G$src$D$1b2f02a();
        double d5 = this.n();
        double d6 = this.A();
        double d7 = this.L();
        if (this.b) {
            GuiRenderPrimitives.C(d4, d5 + d7 - 0.5, d6, 0.5, ClickGuiSidecarPanelBase.J.k);
        }
        double d8 = this.G.V$src$Z$1xhop3l() ? this.G.A() : 0.0;
        double d9 = d4 + d6 - 4.0 - d8;
        double d10 = d5 + d7 / 2.0 - this.G.L() / 2.0;
        this.G.K(d9);
        this.G.S(d10);
        double d11 = d8 == 0.0 ? d4 + d6 - 4.0 : d9 - 4.0;
        ArrayList<GuiComponent> arrayList = new ArrayList<GuiComponent>(this.Q);
        boolean bl = true;
        for (GuiComponent guiComponent : arrayList) {
            if (guiComponent == null || !guiComponent.V$src$Z$1xhop3l()) continue;
            d3 = guiComponent.A() > 0.0 ? guiComponent.A() : guiComponent.x();
            d2 = guiComponent.L() > 0.0 ? guiComponent.L() : guiComponent.C();
            d = bl ? d11 - d3 : d11 - d3 - this.O;
            double d12 = d5 + d7 / 2.0 - d2 / 2.0;
            guiComponent.o(d3);
            guiComponent.Y(d2);
            guiComponent.K(d);
            guiComponent.S(d12);
            d11 = d;
            bl = false;
        }
        double d13 = d4 + 6.0;
        if (this.WV.V$src$Z$1xhop3l()) {
            d3 = d13;
            this.WV.S(d5 + d7 / 2.0 - 2.5);
            this.WV.K(d3);
            d13 = d3 + this.WV.A() + 6.0;
        }
        d3 = Math.max(0.0, d11 - d13 - 12.0);
        this.N(this.K, d13, d5, d3, d7);
        if (this.o.V$src$Z$1xhop3l()) {
            d2 = this.K.G$src$D$1b2f02a();
            d = Math.max(0.0, d2 - d4);
            this.o.K(d4);
            this.o.S(d5);
            this.o.o(d);
            this.o.Y(d7);
        }
        boolean bl2 = this.o.V$src$Z$1xhop3l() && this.o.t();
        this.WV.o(bl2 ? ClickGuiSidecarPanelBase.J.f : ClickGuiSidecarPanelBase.J.W);
    }

    public static int[] L$src$AI$1h0fva0() {
        return a;
    }

    public void K$src$V$12ct9ax() {
        for (GuiComponent guiComponent : new ArrayList<GuiComponent>(this.Q)) {
            if (this.f().contains(guiComponent)) {
                try {
                    this.I(guiComponent);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            this.Q.remove(guiComponent);
        }
    }
}

