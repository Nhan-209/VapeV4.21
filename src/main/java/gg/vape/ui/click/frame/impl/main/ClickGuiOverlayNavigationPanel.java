package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiContentPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayNavigationPanelBase;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayNavigationPanelEntry;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayTransitionMode;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import org.jetbrains.annotations.Nullable;

public class ClickGuiOverlayNavigationPanel
extends ClickGuiContentPanel {
    private final Runnable Xk;
    @Nullable
    private Consumer<ClickGuiOverlaySpec> XW;
    private ClickGuiOverlayPlacement Xs;
    private static final double X7 = 3.5;
    public static final double XA = 1.35;
    private final DoubleSupplier XH;
    private static final double Xg = 2.0;
    private static final double XT = 56.0;
    private final ClickGuiContentPanel XX;
    private static final Color X4 = new Color(0, 0, 0, 180);
    private final ClickGuiMainFrame Xj;
    private final Deque<ClickGuiOverlaySpec> Xm = new ArrayDeque<ClickGuiOverlaySpec>();
    private ClickGuiSidecarPanelBase Xf;

    public boolean U$src$Z$g7f0tj() {
        return this.Xm.size() > 1;
    }

    public ClickGuiOverlayPlacement I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$1h8izi9() {
        return this.Xs;
    }

    public void O(ClickGuiOverlaySpec clickGuiOverlaySpec, boolean bl) {
        Double d;
        boolean bl2;
        Objects.requireNonNull(clickGuiOverlaySpec, "config");
        boolean bl3 = bl2 = clickGuiOverlaySpec.y() == ClickGuiOverlayTransitionMode.PUSH && !this.Xm.isEmpty();
        if (!bl2) {
            this.Xm.clear();
        }
        this.Xm.push(clickGuiOverlaySpec);
        ClickGuiOverlayPlacement clickGuiOverlayPlacement = this.g$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$kq168v();
        if (clickGuiOverlayPlacement != null) {
            this.Xs = clickGuiOverlayPlacement;
        }
        if ((d = clickGuiOverlaySpec.B()) != null) {
            this.o(d);
            this.XX.o(d);
        }
        this.p(clickGuiOverlaySpec);
    }

    private void n$src$V$gl5vk4() {
        boolean bl;
        double d = Math.max(0.0, Math.min(1.0, this.XH.getAsDouble()));
        if (d <= 0.0) {
            return;
        }
        double d2 = d;
        if (this.Xs == ClickGuiOverlayPlacement.DOCKED_SHIFT) {
            d2 = Math.min(1.0, d * 1.35);
        }
        double d3 = this.Xj.G$src$D$1b2f02a();
        double d4 = this.Xj.n();
        double d5 = this.Xj.A();
        double d6 = this.Xj.L();
        FrameHeaderComponent frameHeaderComponent = this.Xj.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc();
        if (frameHeaderComponent == null) {
            frameHeaderComponent = this.Xj.l$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiMainFr$5y2poa();
        }
        double d7 = 0.0;
        boolean bl2 = bl = this.Xs == ClickGuiOverlayPlacement.DOCKED || this.Xs == ClickGuiOverlayPlacement.DOCKED_SHIFT;
        if (bl && frameHeaderComponent != null) {
            d7 = frameHeaderComponent.L();
        }
        double d8 = d4 + d7;
        double d9 = Math.max(0.0, d6 - d7);
        double d10 = this.A();
        double d11 = d3 + d5;
        double d12 = d3 + d5 - d10;
        double d13 = d11 - d10 * d2;
        this.K(d13);
        this.S(d8);
        this.Y(d9);
        this.Xf.K(d13);
        this.Xf.S(d8);
        this.Xf.o(d10);
        double d14 = d13;
        double d15 = d8 + this.Xf.L();
        this.XX.K(d14);
        this.XX.S(d15);
        this.XX.o(d10);
        this.b$src$V$gekcfs();
        this.XX.l$src$V$1mibm4x();
    }

    private ClickGuiOverlayPlacement g$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$kq168v() {
        ClickGuiOverlaySpec clickGuiOverlaySpec = this.Xm.peekLast();
        return clickGuiOverlaySpec != null ? clickGuiOverlaySpec.O() : null;
    }

    @Override
    public void c() {
        this.n$src$V$gl5vk4();
        super.c();
    }

    private void b$src$V$gekcfs() {
        boolean bl;
        double d = this.Xj.L();
        double d2 = this.Xj.A();
        FrameHeaderComponent frameHeaderComponent = this.Xj.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc();
        if (frameHeaderComponent == null) {
            frameHeaderComponent = this.Xj.l$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiMainFr$5y2poa();
        }
        double d3 = 0.0;
        boolean bl2 = bl = this.Xs == ClickGuiOverlayPlacement.DOCKED || this.Xs == ClickGuiOverlayPlacement.DOCKED_SHIFT;
        if (bl && frameHeaderComponent != null) {
            d3 = frameHeaderComponent.L();
        }
        double d4 = Math.max(0.0, d - d3);
        double d5 = d4 - this.Xf.L() - 0.5 - 0.1;
        this.XX.o(this.A());
        this.XX.Y(d5);
        this.XX.t(d5);
    }

    public boolean X$src$Z$g92elm() {
        ClickGuiOverlaySpec clickGuiOverlaySpec = this.Xm.peekLast();
        return clickGuiOverlaySpec == null || clickGuiOverlaySpec.R();
    }

    public void U$src$V$g7f0q3() {
        this.Xm.clear();
        this.XX.S();
        this.XX.b(0.0);
        this.Xf.N((Runnable)null);
        if (!(this.Xf instanceof ClickGuiOverlayNavigationPanelBase)) {
            this.Xf.K$src$V$12ct9ax();
            try {
                this.I(this.Xf);
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.Xf = new ClickGuiOverlayNavigationPanelEntry(this.Xk);
            this.H(this.Xf);
        }
        this.M((ClickGuiOverlaySpec)null);
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public ClickGuiContentPanel i$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$1ynyovr() {
        return this.XX;
    }

    private void p(ClickGuiOverlaySpec clickGuiOverlaySpec) {
        Consumer<ClickGuiSidecarPanelBase> consumer;
        boolean bl;
        ClickGuiSidecarPanelBase clickGuiSidecarPanelBase = clickGuiOverlaySpec.o();
        boolean bl2 = bl = clickGuiSidecarPanelBase != null;
        if (bl && clickGuiSidecarPanelBase != this.Xf) {
            try {
                this.I(this.Xf);
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.Xf = clickGuiSidecarPanelBase;
            this.Xf.y(this.Xk);
            this.H(this.Xf);
        }
        Runnable runnable = this.Xm.size() > 1 ? this::N$src$Z$g3kgo0 : null;
        this.Xf.y(clickGuiOverlaySpec.p());
        this.Xf.B(clickGuiOverlaySpec.O$src$Ljava_lang_String_$vd7j9e());
        this.Xf.N(runnable);
        this.XX.S();
        this.XX.b(0.0);
        this.XX.D(true);
        if (!bl && this.R(this.Xf)) {
            this.Xf.K$src$V$12ct9ax();
            this.Xf.c(true);
        }
        if ((consumer = clickGuiOverlaySpec.t()) != null) {
            consumer.accept(this.Xf);
        }
        this.b$src$V$gekcfs();
        clickGuiOverlaySpec.n().accept(this.XX);
        this.XX.H(true);
        this.M(clickGuiOverlaySpec);
    }

    public void P(ClickGuiOverlaySpec clickGuiOverlaySpec) {
        Objects.requireNonNull(clickGuiOverlaySpec, "config");
        this.Xm.push(clickGuiOverlaySpec);
        ClickGuiOverlayPlacement clickGuiOverlayPlacement = this.g$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$kq168v();
        if (clickGuiOverlayPlacement != null) {
            this.Xs = clickGuiOverlayPlacement;
        }
        this.p(clickGuiOverlaySpec);
    }

    private void M(@Nullable ClickGuiOverlaySpec clickGuiOverlaySpec) {
        Consumer<ClickGuiOverlaySpec> consumer = this.XW;
        if (consumer != null) {
            consumer.accept(clickGuiOverlaySpec);
        }
    }

    public boolean N$src$Z$g3kgo0() {
        if (this.Xm.size() <= 1) {
            return false;
        }
        this.Xm.pop();
        ClickGuiOverlaySpec clickGuiOverlaySpec = this.Xm.peek();
        if (clickGuiOverlaySpec != null) {
            ClickGuiOverlayPlacement clickGuiOverlayPlacement = this.g$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiOverla$kq168v();
            if (clickGuiOverlayPlacement != null) {
                this.Xs = clickGuiOverlayPlacement;
            }
            this.p(clickGuiOverlaySpec);
        } else {
            this.U$src$V$g7f0q3();
        }
        return true;
    }

    private boolean R(ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        return clickGuiSidecarPanelBase instanceof ClickGuiOverlayNavigationPanelBase;
    }

    @Override
    public void H() {
        boolean bl;
        double d = Math.max(0.0, Math.min(1.0, this.XH.getAsDouble()));
        if (d <= 0.0) {
            return;
        }
        double d2 = this.Xj.n();
        double d3 = this.Xj.L();
        FrameHeaderComponent frameHeaderComponent = this.Xj.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc();
        if (frameHeaderComponent == null) {
            frameHeaderComponent = this.Xj.l$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiMainFr$5y2poa();
        }
        double d4 = 0.0;
        boolean bl2 = bl = this.Xs == ClickGuiOverlayPlacement.DOCKED || this.Xs == ClickGuiOverlayPlacement.DOCKED_SHIFT;
        if (bl) {
            if (frameHeaderComponent != null) {
                d4 = frameHeaderComponent.L();
            }
            double d5 = d2 + d4;
            double d6 = Math.max(0.0, d3 - d4);
            double d7 = this.A();
            double d8 = this.G$src$D$1b2f02a();
            int n = 4;
            GuiRenderPrimitives.p(d8, d5, d7, d6, ClickGuiOverlayNavigationPanel.J.m, false, 2.0f, 1.0f, 0.0f, ClickGuiOverlayNavigationPanel.J.B, n);
            return;
        }
        double d9 = d2 + d4;
        double d10 = Math.max(0.0, d3 - d4);
        double d11 = this.A();
        double d12 = this.G$src$D$1b2f02a();
        int n = 4;
        GuiRenderPrimitives.p(d12, d9, d11, d10, ClickGuiOverlayNavigationPanel.J.m, false, 2.0f, 1.0f, 0.0f, ClickGuiOverlayNavigationPanel.J.B, n |= 2);
    }

    public boolean A$src$Z$fwf4yb() {
        return !this.Xm.isEmpty();
    }

    public ClickGuiSidecarPanelBase P$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiSideca$1kad6p7() {
        return this.Xf;
    }

    public void o(@Nullable Consumer<ClickGuiOverlaySpec> consumer) {
        this.XW = consumer;
    }

    public ClickGuiOverlayNavigationPanel(ClickGuiMainFrame clickGuiMainFrame, double d, DoubleSupplier doubleSupplier, Runnable runnable, @Nullable Double d2) {
        super(d2 != null ? d2 : d, 0.0);
        this.Xs = ClickGuiOverlayPlacement.OVERLAY;
        this.Xj = clickGuiMainFrame;
        this.XH = doubleSupplier;
        this.Xk = runnable;
        this.Xf = new ClickGuiOverlayNavigationPanelEntry(runnable);
        double d3 = d2 != null ? d2 : d;
        this.XX = new ClickGuiContentPanel(d3, 0.0);
        this.XX.s(this);
        this.XX.x("wrap");
        this.XX.d(false);
        this.XX.t(false);
        this.XX.D(true);
        this.d(false);
        this.o(d3);
        this.H(this.Xf, this.XX);
        this.Z(false);
    }
}

