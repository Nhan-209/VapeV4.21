package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.PublicProfileSortMode;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.profile.DirtyTrackingPublicProfileFilterTokenSelectorComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenClickListener;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenSelectorComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileSelectedFilterPanel;
import gg.vape.ui.click.frame.impl.profile.PublicProfileSortModeButton;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.value.FriendNameSuggestionProvider;
import gg.vape.value.ListValueSuggestionProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public class PublicProfileSearchFilterPanel
extends PanelComponent {
    private final PublicProfileFilterTokenSelectorComponent Hq;
    private PanelComponent HJ;
    private final Runnable Hv;
    private boolean HA = false;
    private final PanelComponent HX;
    public static final double HQ = 240.0;
    private final PanelComponent Hy;
    public static final double Hb = 324.0;

    public TextInputComponentBase V$src$Lgg_vape_ui_click_component_TextInputComponentBa$1su0cvr() {
        return this.Hq.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz();
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private void lambda$new$1() {
        this.g(false);
        this.Hq.k$src$V$15g9qa7();
    }

    private void g(boolean bl) {
        boolean bl2 = this.HA != bl;
        this.HA = bl;
        if (this.HA) {
            this.HJ.Z(false);
            this.Hq.B$src$Lgg_vape_ui_click_component_SquareIconButtonComp$6e843w().Z(true);
            this.Hy.Y(44.0);
            this.Hy.u(44.0);
            this.HX.Z(true);
        } else {
            this.HJ.Z(true);
            this.Hq.B$src$Lgg_vape_ui_click_component_SquareIconButtonComp$6e843w().Z(false);
            this.Hy.Y(26.0);
            this.Hy.u(26.0);
            this.HX.Z(false);
        }
        this.Hq.H(true);
        this.Hy.H(true);
        if (bl2) {
            this.K$src$V$sxcffc();
        }
    }

    static void o(PublicProfileSearchFilterPanel publicProfileSearchFilterPanel, boolean bl) {
        publicProfileSearchFilterPanel.g(bl);
    }

    private static void lambda$addCategoryContainers$4(PublicProfileSortMode publicProfileSortMode) {
        PublicProfilesFrame publicProfilesFrame = ClientSettings.g(PublicProfilesFrame.class);
        if (publicProfilesFrame.Z$src$Lgg_vape_config_PublicProfileSortMode_$18pvsyy() != publicProfileSortMode) {
            publicProfilesFrame.l(publicProfileSortMode);
            publicProfilesFrame.P$src$Lgg_vape_ui_click_frame_impl_profile_PublicProfi$1ezbs2g().W();
        }
    }

    private void lambda$new$0() {
        this.K$src$V$sxcffc();
        this.Hv.run();
    }

    private void b$src$V$t9zp2n() {
        if (this.HA) {
            GuiRenderPrimitives.B(this.Hy.G$src$D$1b2f02a(), this.Hy.n(), this.Hy.A(), this.Hy.L(), PublicProfileSearchFilterPanel.J.l, 2.0f);
            GuiRenderPrimitives.u(this.Hy.G$src$D$1b2f02a() + 5.0, this.Hy.n() + this.Hq.L(), this.Hy.G$src$D$1b2f02a() + this.Hy.A() - 5.0, this.Hy.n() + this.Hq.L(), 0.75f, PublicProfileSearchFilterPanel.J.y);
            GuiRenderPrimitives.P(this.Hy.G$src$D$1b2f02a(), this.Hy.n(), this.Hy.A(), this.Hy.L(), PublicProfileSearchFilterPanel.J.y, 2.0f, 0.75f, 1.0f);
        } else {
            GuiRenderPrimitives.P(this.Hq.G$src$D$1b2f02a(), this.Hq.n(), this.Hq.A(), this.Hq.L(), PublicProfileSearchFilterPanel.J.y, 2.0f, 0.75f, 1.0f);
        }
    }

    static PublicProfileFilterTokenSelectorComponent k(PublicProfileSearchFilterPanel publicProfileSearchFilterPanel) {
        return publicProfileSearchFilterPanel.Hq;
    }

    public PublicProfileFilterTokenSelectorComponent U$src$Lgg_vape_ui_click_frame_impl_profile_PublicProfi$1cokhj1() {
        return this.Hq;
    }

    private void j$src$V$tee1tj() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.Hq.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().b$src$Ljava_util_List_$1hubsov().add(0, (arg_0, arg_1) -> this.lambda$addInputListener$3(atomicBoolean, arg_0, arg_1));
    }

    private void lambda$null$2(AtomicBoolean atomicBoolean, String string) {
        while (atomicBoolean.get()) {
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException interruptedException) {
                Vape.logThrowable(interruptedException);
            }
            String string2 = this.Hq.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().i$src$Ljava_lang_String_$1n2xf3k().trim();
            if (string2.equalsIgnoreCase(string)) {
                if (!string2.isEmpty()) continue;
                atomicBoolean.set(false);
                return;
            }
            try {
                this.Hv.run();
            }
            catch (Throwable throwable) {
                Vape.logThrowable(throwable);
            }
            atomicBoolean.set(false);
            return;
        }
    }

    private void lambda$addInputListener$3(AtomicBoolean atomicBoolean, char c, int n) {
        ClientSettings.f6.execute(this::K$src$V$sxcffc);
        if (!atomicBoolean.get()) {
            atomicBoolean.set(true);
            String string = this.Hq.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().i$src$Ljava_lang_String_$1n2xf3k().trim();
            CompletableFuture.runAsync(() -> this.lambda$null$2(atomicBoolean, string));
        }
    }

    private void K$src$V$sxcffc() {
        ListValueSuggestionProvider listValueSuggestionProvider;
        boolean bl;
        String string = this.Hq.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().i$src$Ljava_lang_String_$1n2xf3k().trim();
        boolean bl2 = bl = !string.isEmpty();
        List<String> arrayList = !bl ? new ArrayList<String>(Vape.INSTANCE.getPublicProfileManager().f()) : ((listValueSuggestionProvider = this.Hq.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().K$src$Lgg_vape_value_ListValueSuggestionProvider_$yndqzl()) != null ? listValueSuggestionProvider.getSuggestions() : new ArrayList<String>());
        this.HX.S();
        double d = 0.0;
        for (String string2 : arrayList) {
            if (this.Hq.F(string2)) continue;
            PublicProfileFilterTokenComponent publicProfileFilterTokenComponent = new PublicProfileFilterTokenComponent(string2);
            PaddedComponent paddedComponent = new PaddedComponent(0.0, 0.0, 1.0, 1.0, publicProfileFilterTokenComponent);
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            publicProfileFilterTokenComponent.j(new PublicProfileFilterTokenClickListener(this, atomicBoolean, paddedComponent, publicProfileFilterTokenComponent));
            double d2 = paddedComponent.A();
            if (d + d2 >= this.HX.A()) break;
            d += d2;
            this.HX.h(paddedComponent, "widthwrap");
        }
    }

    static PanelComponent T(PublicProfileSearchFilterPanel publicProfileSearchFilterPanel) {
        return publicProfileSearchFilterPanel.HX;
    }

    public PublicProfileSearchFilterPanel(double d, Runnable runnable) {
        super(d, 42.0);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.Hv = runnable;
        this.L$src$V$sxw80p();
        Runnable runnable2 = this::lambda$new$0;
        this.Hq = new DirtyTrackingPublicProfileFilterTokenSelectorComponent(this, "Search Profile / Share Code", runnable2, d, 20.0, false, false);
        this.Hq.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().E(new FriendNameSuggestionProvider());
        this.Hq.B$src$Lgg_vape_ui_click_component_SquareIconButtonComp$6e843w().s(this::lambda$new$1);
        this.HX = new PanelComponent(d - 8.0, 16.0);
        this.HX.d(false);
        this.Hy = new PublicProfileSelectedFilterPanel(this, d, 26.0);
        this.Hy.d(false);
        this.Hy.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.Hy.h(this.Hq, new Object[0]);
        this.Hy.h(new SpacerComponent(1.0, 4.0), new Object[0]);
        this.Hy.h(new PaddedComponent(3.0, 0.0, 4.0, 4.0, this.HX), new Object[0]);
        this.h(this.Hy, new Object[0]);
        this.h(this.HJ, new Object[0]);
        this.j$src$V$tee1tj();
        this.g(false);
    }

    private void L$src$V$sxw80p() {
        this.HJ = new PanelComponent(this.A(), 20.0);
        this.HJ.d(false);
        this.HJ.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        for (PublicProfileSortMode publicProfileSortMode : PublicProfileSortMode.VALUES) {
            PublicProfileSortModeButton publicProfileSortModeButton = new PublicProfileSortModeButton(this, publicProfileSortMode.y().toUpperCase(), 0.7, PublicProfileSearchFilterPanel.J.B, PublicProfileSearchFilterPanel.J.O, publicProfileSortMode);
            publicProfileSortModeButton.m(7.0f);
            publicProfileSortModeButton.c(true);
            publicProfileSortModeButton.F(false);
            double d = publicProfileSortModeButton.W();
            this.getClass();
            publicProfileSortModeButton.o(d + (double)(5.0f * 3.0f));
            publicProfileSortModeButton.Y(14.0);
            publicProfileSortModeButton.s(() -> PublicProfileSearchFilterPanel.lambda$addCategoryContainers$4(publicProfileSortMode));
            this.HJ.h(publicProfileSortModeButton, new Object[0]);
        }
    }

    static void r(PublicProfileSearchFilterPanel publicProfileSearchFilterPanel) {
        publicProfileSearchFilterPanel.b$src$V$t9zp2n();
    }
}
