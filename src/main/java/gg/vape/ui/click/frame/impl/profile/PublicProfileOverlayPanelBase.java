package gg.vape.ui.click.frame.impl.profile;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.InsetFilledSpacerComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SplitPanelComponent;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOverlayCloseButton;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import java.awt.Color;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;

public class PublicProfileOverlayPanelBase
extends SplitPanelComponent {
    @Nullable
    private PublicProfileOverlayCloseButton gK;
    protected final PublicProfilesFrame gc;
    @Nullable
    private PopupFrame gj;
    private static int gE;
    @Nullable
    private CompletableFuture<?> gI;
    protected PanelComponent gb;
    protected PanelComponent gg;
    protected boolean gZ = true;

    public void d$src$V$15t6q4y() {
        CompletableFuture<?> completableFuture = this.gI;
        if (completableFuture != null && !completableFuture.isCancelled() && !completableFuture.isCompletedExceptionally()) {
            completableFuture.cancel(true);
        }
    }

    @Nullable
    public PopupFrame E() {
        return this.gj;
    }

    public void S(@Nullable PopupFrame popupFrame) {
        this.gj = popupFrame;
    }

    public void T(@Nullable CompletableFuture<?> completableFuture) {
        this.gI = completableFuture;
    }

    public void n$src$V$s6msm2() {
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().d(false);
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().S();
    }

    public static void r(int n) {
        gE = n;
    }

    public void s$src$V$1l7a8uk() {
        this.gb.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.gb.d(false);
        this.gb.S();
    }

    @Nullable
    public PublicProfileOverlayCloseButton s$src$Lgg_vape_ui_click_frame_impl_profile_PublicProfi$urrnyv() {
        return this.gK;
    }

    public static int S$src$I$rrsca4() {
        return gE;
    }

    public void b$src$V$s019hq() {
        this.gg.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.gg.S();
    }

    public PublicProfileOverlayPanelBase(PublicProfilesFrame publicProfilesFrame) {
        super(-1.0, -1.0, new PanelComponent(-1.0, -1.0), new PanelComponent(-1.0, -1.0));
        this.gc = publicProfilesFrame;
        this.e();
    }

    static {
        PublicProfileOverlayPanelBase.r(67);
    }

    public static int g$src$I$s2s85c() {
        int n = PublicProfileOverlayPanelBase.S$src$I$rrsca4();
        return 0;
    }

    public void K(@Nullable PublicProfileOverlayCloseButton publicProfileOverlayCloseButton) {
        if (this.gK != null) {
            this.gK.X(false);
        }
        this.gK = publicProfileOverlayCloseButton;
    }

    protected void e() {
        this.d(false);
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().t$src$V$zbu1jn();
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().d(false);
        this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().t$src$V$zbu1jn();
        this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().d(false);
        this.getClass();
        double d = 5.0f * 4.0f;
        this.o(this.gc.A() - d);
        double d2 = this.gc.L() - this.gc.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() - 2.0 - d;
        this.getClass();
        this.Y(d2 - 5.0);
        PanelComponent panelComponent = this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy();
        this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().N(false);
        this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().t(this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().L());
        this.h(panelComponent, new Object[0]);
        this.gb = new PanelComponent(panelComponent.A(), 30.0);
        this.gb.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.gb.d(false);
        this.gg = this.gZ ? new PanelComponent(panelComponent.A() - 1.0, panelComponent.L() - this.gb.L()) : new PanelComponent(panelComponent.A() - 1.0, panelComponent.L());
        this.gg.t(this.gg.L() + 2.0);
        this.gg.d(false);
        this.gg.T(Color.MAGENTA);
        panelComponent.h(this.gg, new Object[0]);
        if (this.gZ) {
            panelComponent.h(this.gb, new Object[0]);
            InsetFilledSpacerComponent insetFilledSpacerComponent = new InsetFilledSpacerComponent(this.gb.A(), 1.0, 1.0, 0.0, PublicProfileOverlayPanelBase.J.h);
            insetFilledSpacerComponent.Q(false);
        }
    }

    @Nullable
    public CompletableFuture<?> R$src$Ljava_util_concurrent_CompletableFuture_$1ccqvok() {
        return this.gI;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

