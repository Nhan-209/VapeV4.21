package gg.vape.ui.click.component;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.InteractivePopupOutsideCloseMouseListener;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class PopupMenuButtonComponent
extends InteractiveComponent {
    private ColorAnimation ey;
    private final float eT;
    private float v;
    private final Color el;
    private boolean K;
    private boolean eW;
    private boolean eH;
    private boolean e0;
    @Nullable
    private Color eU;
    private final ColorAnimation eh;
    private Color e5;
    @Nullable
    private Color eo;
    private final PanelComponent I;
    private boolean eX;
    private final Color eI;
    private final List<GuiComponent> b;
    private static final float e6 = 12.5f;
    @Nullable
    private PopupFrame ef;
    private final ColorAnimation Q;
    private boolean eb;
    private float eP;
    private final String eR;

    @Nullable
    public Color R() {
        return this.eU;
    }

    @Override
    public double x() {
        return 0.0;
    }

    public void z(boolean bl) {
        this.eW = bl;
    }

    public boolean z() {
        return this.eb;
    }

    public void S(@Nullable Color color) {
        this.eU = color;
    }

    public boolean l$src$Z$dxtwac() {
        return this.eW;
    }

    public boolean k$src$Z$dxa3oz() {
        return this.eH;
    }

    public static void l(PopupMenuButtonComponent popupMenuButtonComponent) {
        popupMenuButtonComponent.t$src$V$e288xs();
    }

    public void c(boolean bl) {
        this.eb = bl;
    }

    @Override
    public void I() {
    }

    public void r(@Nullable Color color) {
        this.eo = color;
    }

    public PopupMenuButtonComponent(String string, List<GuiComponent> list, Color color, Color color2, Color color3, float f, float f2) {
        this.getClass();
        this.Q = new ColorAnimation(0.15, PopupMenuButtonComponent.J.l, PopupMenuButtonComponent.J.y);
        this.eT = 2.0f;
        this.eI = PopupMenuButtonComponent.J.Z;
        this.el = PopupMenuButtonComponent.J.Z;
        this.e5 = null;
        this.v = 2.0f;
        this.eP = 1.0f;
        this.eo = new Color(255, 255, 255, 30);
        this.eU = new Color(255, 255, 255, 30);
        this.eW = true;
        this.eb = true;
        this.e0 = true;
        this.eR = string;
        this.b = new ArrayList<GuiComponent>(list);
        for (GuiComponent guiComponent : list) {
            guiComponent.W(true);
            guiComponent.P(true);
        }
        if (color != null) {
            this.T(color);
        }
        this.I = new PanelComponent(110.0, 20.0);
        this.I.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.I.t(120.0);
        this.I.d(false);
        this.I.I(true);
        this.getClass();
        this.ey = new ColorAnimation(0.15, this.d(), color2);
        this.getClass();
        this.eh = new ColorAnimation(0.15, this.eI, this.el);
        if (color3 != null && color != null) {
            this.j(color3, color);
        }
        this.v = f;
        this.eP = f2;
    }

    @Override
    public void u() {
        if (this.eX && !this.w$src$Z$e457mb() && !this.y$src$Z$e4z801()) {
            this.Q.J();
            this.eX = false;
        }
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public void H() {
        double d;
        double d2;
        PopupFrame popupFrame = this.ef;
        if (popupFrame != null) {
            if (this.eH) {
                d2 = this.n() - popupFrame.L();
                d = this.L() + popupFrame.L();
            } else {
                d2 = this.n();
                d = this.L() + popupFrame.L();
            }
        } else {
            d2 = this.n();
            d = this.L();
        }
        if (this.y$src$Z$e4z801()) {
            this.ey.u(this.w$src$Z$e457mb() && !this.y$src$Z$e4z801());
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), d2, this.A(), d, this.ey.getInterpolatedColor());
        } else {
            if (this.Z$src$Z$16e8vsp()) {
                GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), d2, this.A(), d, this.ey.getInterpolatedColor());
            }
            if (this.w$src$Z$e457mb()) {
                GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), d2, this.A(), d, new Color(100, 100, 100, 10));
            }
        }
        if (this.e5 != null) {
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), d2, this.A(), d, this.e5, this.v, this.eP, 1.0f);
        }
        SmoothFontRenderer smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.7);
        double d3 = smoothFontRenderer.d(this.eR);
        double d4 = this.G$src$D$1b2f02a();
        double d5 = this.n() + this.L() / 2.0 - d3 / 2.0;
        double d6 = this.n() + this.L() / 2.0;
        this.getClass();
        double d7 = d6 - 2.0 / 2.0;
        if (this.eb) {
            smoothFontRenderer.W(this.eR, d4 += (this.A() - 12.5) / 2.0, d5, PopupMenuButtonComponent.J.A);
        } else {
            this.getClass();
            smoothFontRenderer.d(this.eR, d4 += 5.0, d5, PopupMenuButtonComponent.J.A);
        }
        if (this.eo != null) {
            GuiRenderPrimitives.C(this.G$src$D$1b2f02a() + this.A() - 12.5, this.n() + 2.0, 1.0, this.L() - 4.5, this.eo);
        }
        ImageRenderer.E(Color.WHITE, (float)(this.G$src$D$1b2f02a() + this.A()) - 8.0f, (float)d7, this.y$src$Z$e4z801() ? "upcollapse" : "downexpand", this.eT, this.eT, false);
    }

    public boolean y$src$Z$e4z801() {
        return this.ef != null;
    }

    public String P$src$Ljava_lang_String_$1jjvsuy() {
        return this.eR;
    }

    public boolean c$src$Z$dsvqy3() {
        return this.e0;
    }

    public void p(boolean bl) {
        this.K = bl;
    }


    @Nullable
    public Color D$src$Ljava_awt_Color_$1717gti() {
        return this.eo;
    }

    @Override
    public void F() {
        if (!this.eX) {
            this.Q.J();
        }
        this.eX = true;
    }

    public boolean isBlatantMod() {
        return this.K;
    }

    private void t$src$V$e288xs() {
        PopupFrame popupFrame = this.ef;
        if (popupFrame != null) {
            this.ef = null;
            ClientSettings.fT = null;
            ClientSettings.K(popupFrame);
        } else {
            this.I.S();
            if (this.eW) {
                this.I.q(this.A() - this.Z$src$D$1wvori2());
            } else {
                this.I.q(this.A());
            }
            this.I.V(3.0f);
            this.I.h(new SpacerComponent(0.0, 2.0), new Object[0]);
            for (GuiComponent guiComponent : this.b) {
                guiComponent.q(this.I.A() - 3.0);
                guiComponent.u(12.0);
                this.I.h(new PaddedComponent(0.0, 0.5, 0.0, 0.0, guiComponent), "wrap");
            }
            this.I.u(Math.min(this.I.d$src$D$ibccpu(), this.I.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y()));
            this.I.H(true);
            this.ef = ClientSettings.g(this, this.I, PopupFrame.class);
            this.ef.Z(new InteractivePopupOutsideCloseMouseListener(this));
            ClientSettings.fT = this;
            this.K$src$V$dfoolz();
        }
    }

    public void j(Color color, Color color2) {
        this.e5 = color;
        this.getClass();
        this.ey = new ColorAnimation(0.15, color2, this.ey.getEndColor());
    }

    public static PopupFrame g(PopupMenuButtonComponent popupMenuButtonComponent) {
        return popupMenuButtonComponent.ef;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.eo != null && (double)guiMouseEvent.getX() < this.G$src$D$1b2f02a() + this.A() - 12.5 && (double)guiMouseEvent.getY() > this.n()) {
            super.g(guiMouseEvent);
            ClientSettings.fT = null;
            return;
        }
        if (this.K) {
            return;
        }
        PopupFrame popupFrame = this.ef;
        if (popupFrame != null) {
            if (popupFrame.t()) {
                popupFrame.D(guiMouseEvent);
                this.t$src$V$e288xs();
                return;
            }
            if (!popupFrame.t()) {
                this.t$src$V$e288xs();
            }
            return;
        }
        this.t$src$V$e288xs();
    }

    public void t(Color color) {
        this.e5 = color;
        this.getClass();
        this.ey = new ColorAnimation(0.15, new Color(0, 0, 0, 0), this.ey.getEndColor());
    }

    @Override
    public void c() {
        super.c();
        PopupFrame popupFrame = this.ef;
        if (popupFrame != null) {
            this.K$src$V$dfoolz();
            this.I.T(this.ey.getInterpolatedColor());
            this.I.H(true);
            this.I.c();
        }
    }

    public void e(boolean bl) {
        this.e0 = bl;
    }

    public void l(boolean bl) {
        this.eH = bl;
    }

    private void K$src$V$dfoolz() {
        PopupFrame popupFrame = this.ef;
        if (popupFrame != null) {
            if (this.eW) {
                popupFrame.K(this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2());
            } else {
                popupFrame.K(this.G$src$D$1b2f02a());
            }
            if (this.eH) {
                popupFrame.S(this.n() - popupFrame.L());
            } else {
                popupFrame.S(this.n() + this.L());
            }
        }
    }
}
