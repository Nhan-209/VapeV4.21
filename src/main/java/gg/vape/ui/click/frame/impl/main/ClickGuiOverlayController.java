package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayBackdropComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayLayer;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayNavigationPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPointPredicate;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayTransitionMode;
import gg.vape.ui.click.frame.impl.main.ClickGuiSectionSwitchMap;
import java.util.Objects;
import org.jetbrains.annotations.Nullable;

final class ClickGuiOverlayController {
    private final ClickGuiOverlayLayer y;
    private static final String c = "mode";
    final ClickGuiMainFrame o;
    private final ClickGuiOverlayNavigationPanel k;
    private final DoubleAnimation l;
    private boolean a;
    private final ClickGuiOverlayBackdropComponent z;
    private ClickGuiOverlayPlacement U;
    private final DoubleAnimation m;
    private double P;
    private ClickGuiOverlaySpec x;
    private boolean w;

    private boolean t(double d) {
        return this.w || d > 0.001;
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    private ClickGuiOverlayController(ClickGuiMainFrame clickGuiMainFrame, ClickGuiOverlayLayer clickGuiOverlayLayer, double d) {
        this.o = clickGuiMainFrame;
        this.y = clickGuiOverlayLayer;
        clickGuiMainFrame.getClass();
        this.l = new DoubleAnimation(0.15, 0.0, 1.0);
        this.l.O();
        clickGuiMainFrame.getClass();
        this.m = new DoubleAnimation(0.15, 0.0, 1.0);
        this.m.O();
        this.P = d;
        this.U = clickGuiOverlayLayer == ClickGuiOverlayLayer.FRAME_OVERLAY ? ClickGuiOverlayPlacement.OVERLAY : ClickGuiOverlayPlacement.DOCKED;
        Runnable runnable = this::lambda$new$0;
        this.k = new ClickGuiOverlayNavigationPanel(clickGuiMainFrame, d, this::n, runnable, null);
        this.k.o(this::Q);
        this.k.Z(false);
        this.z = new ClickGuiOverlayBackdropComponent(clickGuiMainFrame, this::V, this::P, runnable);
        this.z.Z(false);
        this.z.Z(new ClickGuiOverlayPointPredicate(this, clickGuiMainFrame));
    }

    boolean z() {
        return this.w;
    }

    void L(ClickGuiOverlaySpec clickGuiOverlaySpec) {
        boolean bl;
        ClickGuiOverlayPlacement clickGuiOverlayPlacement = Objects.requireNonNull(clickGuiOverlaySpec.O(), c);
        double d = ClickGuiMainFrame.G(this.o, clickGuiOverlayPlacement);
        double d2 = this.n();
        boolean bl2 = this.t(d2);
        this.x = clickGuiOverlaySpec;
        this.U = clickGuiOverlayPlacement;
        this.P = d;
        this.k.o(d);
        this.k.O(clickGuiOverlaySpec, bl2);
        boolean bl3 = this.w;
        if (bl3) {
            boolean bl4;
            this.w = true;
            boolean bl5 = clickGuiOverlaySpec.y() == ClickGuiOverlayTransitionMode.PUSH && this.k.A$src$Z$fwf4yb();
            boolean bl6 = bl4 = bl5 ? this.k.X$src$Z$g92elm() : clickGuiOverlaySpec.R();
            if (bl4) {
                this.m.C();
            } else {
                this.m.O();
            }
            this.z.G(bl4);
            this.k.Z(true);
            this.z.Z(bl4);
            return;
        }
        this.w = true;
        this.l.J();
        boolean bl7 = clickGuiOverlaySpec.y() == ClickGuiOverlayTransitionMode.PUSH && this.k.A$src$Z$fwf4yb();
        boolean bl8 = bl = bl7 ? this.k.X$src$Z$g92elm() : clickGuiOverlaySpec.R();
        if (bl) {
            this.m.J();
        } else {
            this.m.O();
        }
        this.z.G(bl);
        this.k.Z(true);
        this.z.Z(bl);
    }

    boolean F() {
        return this.z.V$src$Z$1xhop3l() && this.z.P();
    }

    private ClickGuiOverlayPlacement P() {
        return this.U;
    }

    ClickGuiOverlayNavigationPanel o() {
        return this.k;
    }

    boolean L() {
        return this.t(this.n());
    }

    ClickGuiOverlayController(ClickGuiMainFrame clickGuiMainFrame, ClickGuiOverlayLayer clickGuiOverlayLayer, double d, ClickGuiSectionSwitchMap clickGuiSectionSwitchMap) {
        this(clickGuiMainFrame, clickGuiOverlayLayer, d);
    }

    private void lambda$new$0() {
        this.q();
        ClickGuiMainFrame.V(this.o);
    }

    double V() {
        return Math.max(0.0, Math.min(1.0, this.m.getInterpolatedValue()));
    }

    boolean d(double d) {
        if (this.y != ClickGuiOverlayLayer.CONTENT_OVERLAY) {
            return false;
        }
        return (this.w || d > 0.001) && this.U == ClickGuiOverlayPlacement.DOCKED_SHIFT;
    }

    ClickGuiOverlayBackdropComponent f() {
        return this.z;
    }

    double y() {
        return this.P;
    }

    ClickGuiOverlaySpec u() {
        return this.x;
    }

    private void U() {
        if (this.y == ClickGuiOverlayLayer.FRAME_OVERLAY) {
            this.U = ClickGuiOverlayPlacement.OVERLAY;
            this.P = 136.0;
        } else {
            this.U = ClickGuiOverlayPlacement.DOCKED;
            this.P = 120.0;
        }
    }

    void l() {
        this.z.K(this.o.G$src$D$1b2f02a());
        this.z.S(this.o.n());
        this.k.K(this.o.G$src$D$1b2f02a());
        this.k.S(this.o.n());
        this.k.T$src$V$1wse0de();
    }

    double n() {
        return Math.max(0.0, Math.min(1.0, this.l.getInterpolatedValue()));
    }

    void q() {
        boolean bl;
        boolean bl2 = this.w;
        double d = this.n();
        boolean bl3 = bl = this.x != null && (this.x.y() == ClickGuiOverlayTransitionMode.PUSH && this.k.A$src$Z$fwf4yb() ? this.k.X$src$Z$g92elm() : this.x.R());
        if (!bl2 && d <= 0.0) {
            return;
        }
        this.w = false;
        if (bl2 || d > 0.0) {
            for (Object pendingCallback : ClickGuiMainFrame.b(this.o)) {
                try {
                    ((Runnable)pendingCallback).run();
                }
                catch (Throwable throwable) {}
            }
        }
        if (bl2) {
            this.l.J();
            if (bl) {
                this.m.J();
            } else {
                this.m.O();
            }
        }
        this.z.G(false);
        if (this.x != null && this.n() <= 0.0 && !this.a) {
            this.a = true;
            for (Object pendingCallback : ClickGuiMainFrame.W(this.o)) {
                try {
                    ((Runnable)pendingCallback).run();
                }
                catch (Throwable throwable) {}
            }
        }
    }

    static ClickGuiOverlayBackdropComponent t(ClickGuiOverlayController clickGuiOverlayController) {
        return clickGuiOverlayController.z;
    }

    private void Q(@Nullable ClickGuiOverlaySpec clickGuiOverlaySpec) {
        this.x = clickGuiOverlaySpec;
        if (clickGuiOverlaySpec != null) {
            this.U = this.k.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$1h8izi9();
            this.P = this.k.A();
        }
    }

    void K() {
        double d = this.n();
        boolean bl = this.t(d);
        this.k.Z(bl);
        boolean bl2 = false;
        if (bl && this.x != null) {
            boolean bl3 = this.x.y() == ClickGuiOverlayTransitionMode.PUSH && this.k.A$src$Z$fwf4yb();
            bl2 = bl3 ? this.k.X$src$Z$g92elm() : this.x.R();
        }
        double d2 = this.V();
        boolean bl4 = bl2 && d2 > 0.001;
        this.z.Z(bl4);
        this.z.G(bl2 && this.w);
        if (!bl && this.x != null) {
            this.k.U$src$V$g7f0q3();
            this.x = null;
            this.U();
            if (!this.a) {
                this.a = true;
                for (Object pendingCallback : ClickGuiMainFrame.W(this.o)) {
                    try {
                        ((Runnable)pendingCallback).run();
                    }
                    catch (Exception exception) {}
                }
            }
        } else if (bl) {
            this.a = false;
        }
    }

    boolean x() {
        return this.x != null;
    }
}
