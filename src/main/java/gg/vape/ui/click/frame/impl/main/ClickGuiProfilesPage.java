package gg.vape.ui.click.frame.impl.main;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.Profile;
import gg.vape.event.EventBus;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.ProfileListMutationEvent;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.input.BindableInputComponent;
import gg.vape.ui.click.component.input.SmallTextInputComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.FrameScrollbarPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiPageBase;
import gg.vape.ui.click.frame.impl.main.ClickGuiProfileCardComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiProfileHeaderComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileCardActionState;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryBadgeComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryMetadataComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryOpenButtonComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileModuleSnapshotListComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileModuleSnapshotListStyle;
import gg.vape.ui.click.frame.impl.profile.ProfilesPageEmptyStateComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilesPageOverlayController;
import gg.vape.ui.click.frame.impl.profile.ProfilesPageRefreshListener;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrameState;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import java.awt.Color;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class ClickGuiProfilesPage
extends ClickGuiPageBase
implements EventListener {
    private final DoubleAnimation Ba;
    private final ClickGuiMainFrame Bt;
    private final Runnable B8;
    private TextButton Bm;
    private boolean Bg;
    private SmallTextInputComponent Bz;
    @Nullable
    private ClickGuiProfileCardComponent BF;
    private Profile Bd;
    private ProfileModuleSnapshotListComponent Bv;
    private FlowLayoutComponent BB;
    private PanelComponent BA;
    private TextButton BD;
    private Profile Bk;
    private static boolean B3;
    private Profile BW;
    private Profile B1;
    private boolean BU;
    private ClickGuiProfileHeaderComponent BE;

    private void U$src$V$1llirxg() {
        if (this.BU) {
            return;
        }
        this.Bt.K$src$V$sfnnd();
        Profile profile = Vape.INSTANCE.getProfilesManager().M();
        profile.a();
        this.Bk = profile;
        this.Bd = new Profile(profile.n$src$Ljava_lang_String_$xqhelw(), "4.21");
        this.Bd.e(profile.C(true));
        this.Bd.d(UUID.randomUUID());
        this.Bd.K(null);
        this.Bd.s(false);
        this.Bd.B(true);
        ApiServices.d().c().u().whenCompleteAsync(this::lambda$beginCreateProfileFlow$4, (Executor)ClientSettings.f6).exceptionally(ClickGuiProfilesPage::lambda$beginCreateProfileFlow$5);
        Vape.INSTANCE.getProfilesManager().U(this.Bd);
        this.BU = true;
        this.d$src$V$1ltrotv();
        this.p$src$V$1m0d7y7();
    }

    private void a$src$V$1ls4b1s() {
        if (!this.BU) {
            return;
        }
        Profile profile = Vape.INSTANCE.getProfilesManager().M();
        if (profile != null && profile.Z()) {
            Vape.INSTANCE.getProfilesManager().L(this.Bk != null ? this.Bk : this.B1);
        }
        this.Bd = null;
        this.Bk = null;
        this.Bz = null;
        this.BF = null;
        this.BD = null;
        this.BU = false;
        this.d$src$V$1ltrotv();
        this.p$src$V$1m0d7y7();
    }

    static {
        ClickGuiProfilesPage.f(true);
    }

    private void l(Profile profile) {
        if (this.BB == null) {
            return;
        }
        this.BW = profile;
        this.Bg = profile != null;
        for (GuiComponent guiComponent : this.BB.f()) {
            ClickGuiProfileCardComponent clickGuiProfileCardComponent;
            if (!(guiComponent instanceof PaddedComponent) || (clickGuiProfileCardComponent = ((PaddedComponent)guiComponent).t(ClickGuiProfileCardComponent.class)) == null || clickGuiProfileCardComponent.h$src$Lgg_vape_config_Profile_$18amnwr() == null) continue;
            boolean bl = profile != null && clickGuiProfileCardComponent.h$src$Lgg_vape_config_Profile_$18amnwr() == profile;
            clickGuiProfileCardComponent.v(bl);
            boolean bl2 = profile != null && !bl;
            clickGuiProfileCardComponent.e(bl2);
        }
    }

    private void t(Profile profile, PanelComponent panelComponent) {
        double d = 16.0;
        double d2 = 4.0;
        double d3 = 18.0;
        double d4 = 8.0;
        PanelComponent panelComponent2 = new PanelComponent(panelComponent.A() - d4 * 2.0, d);
        panelComponent2.d(false);
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        double d5 = panelComponent.A() - d4 * 2.0 - d3 - d2;
        ProfileListEntryMetadataComponent profileListEntryMetadataComponent = new ProfileListEntryMetadataComponent(profile);
        profileListEntryMetadataComponent.q(d5);
        profileListEntryMetadataComponent.u(d);
        ProfileListEntryOpenButtonComponent profileListEntryOpenButtonComponent = new ProfileListEntryOpenButtonComponent(profile, this::lambda$createProfileContent$8);
        profileListEntryOpenButtonComponent.h$src$Lgg_vape_ui_click_frame_impl_profile_ProfileList$h82ue1();
        profileListEntryOpenButtonComponent.q(d3);
        profileListEntryOpenButtonComponent.u(d);
        panelComponent2.h(profileListEntryMetadataComponent, new Object[0]);
        panelComponent2.h(new SpacerComponent(d2, 0.0), new Object[0]);
        panelComponent2.h(profileListEntryOpenButtonComponent, new Object[0]);
        panelComponent.h(new PaddedComponent(4.0, 0.0, d4, d4, panelComponent2), new Object[0]);
        double d6 = d + 4.0 + 8.0;
        double d7 = panelComponent.L() - d6 - 22.0;
        ProfileModuleSnapshotListComponent profileModuleSnapshotListComponent = new ProfileModuleSnapshotListComponent(profile, panelComponent.A() - 4.0, d7);
        profileModuleSnapshotListComponent.o(ProfileModuleSnapshotListStyle.MODERN);
        panelComponent.h(new SpacerComponent(0.0, 4.0), new Object[0]);
        panelComponent.h(profileModuleSnapshotListComponent, new Object[0]);
    }

    public static void f(boolean bl) {
        B3 = bl;
    }

    private void M(Profile profile, double d, double d2) {
        this.BE = new ClickGuiProfileHeaderComponent(profile, d2);
        this.BA.h(new PaddedComponent(2.0, d, d, d, this.BE), new Object[0]);
        double d3 = d * 2.0;
        double d4 = this.BA.L() - this.BA.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y() - d3;
        this.Bv = new ProfileModuleSnapshotListComponent(profile, this.BA.A() - 6.0 - 3.0, d4);
        this.Bv.o(ProfileModuleSnapshotListStyle.MODERN);
        this.BA.h(new PaddedComponent(0.0, d, 3.0, 0.0, this.Bv), new Object[0]);
    }

    private void h() {
        this.Bt.Z(ClickGuiOverlaySpec.q().e("Settings").C("newsettings").n(ClickGuiOverlayPlacement.DOCKED).N(this::O).w());
    }

    public static SmallTextInputComponent V(ClickGuiProfilesPage clickGuiProfilesPage) {
        return clickGuiProfilesPage.Bz;
    }

    private Color a(Color color) {
        return ProfileCardActionState.t(color, this.Ba, this.Bg);
    }

    private void lambda$refreshProfilesList$6(Profile profile) {
        if (!this.BU) {
            this.o(profile);
        }
    }

    private static void lambda$renderCategoryButtons$2() {
        PublicProfilesFrame publicProfilesFrame = ClientSettings.g(PublicProfilesFrame.class);
        ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
        clickGuiFrameManager.K(publicProfilesFrame);
    }

    private void Q$src$V$1ljblk0() {
        GuiComponent guiComponent = this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().f().get(0);
        this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().S();
        this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().h(guiComponent, "widthwrap");
        this.Bm = new TextButton("NEW PROFILE", 0.625, J.z(), J.z().brighter(), null, 2.0f, 1.0f, 53.0, 16.0);
        this.Bm.T("newadd");
        this.Bm.i(6.0f);
        this.Bm.c(true);
        this.Bm.a(true);
        this.Bm.F(true);
        this.Bm.m(true);
        this.Bm.r(this::U$src$V$1llirxg);
        Object object = new GlyphIconComponent("newsettings", 6.0, 6.0, 10.0, 10.0, null, null, null);
        ((GlyphIconComponent)object).R(true);
        ((GlyphIconComponent)object).q(true);
        ((InteractiveComponent)object).r(this::h);
        this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().h(new SpacerComponent(this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().A() - this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C() - this.Bm.A() - ((GuiComponent)object).A() - 8.0 - 1.0, 0.0), new Object[0]);
        this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().h(new PaddedComponent(0.0, 0.0, 0.0, 8.0, this.Bm), new Object[0]);
        this.H$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$3n0k6u().h(new PaddedComponent(4.0, 0.0, 0.0, 1.0, (GuiComponent)object), new Object[0]);
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().S();
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().h(new SpacerComponent(0.0, 3.0), new Object[0]);
        this.BB = this.p(this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().A(), this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().L() - this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y() - 21.0);
        this.BB.F(FrameScrollbarPlacement.OUTSIDE);
        this.BB.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.BB.t(this.BB.L());
        this.BB.d(false);
        this.BB.E(true);
        this.BB.h(new SpacerComponent(0.0, 1.0), new Object[0]);
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().h(this.BB, new Object[0]);
        object = Vape.INSTANCE.getProfilesManager().M();
        for (Profile profile : Vape.INSTANCE.getProfilesManager().b()) {
            ClickGuiProfileCardComponent clickGuiProfileCardComponent = new ClickGuiProfileCardComponent(profile);
            clickGuiProfileCardComponent.o(this.BB.A());
            clickGuiProfileCardComponent.Y(20.0);
            clickGuiProfileCardComponent.g(profile == object);
            clickGuiProfileCardComponent.f(() -> this.lambda$renderCategoryButtons$1(profile));
            this.BB.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, clickGuiProfileCardComponent), new Object[0]);
        }
        ProfileListEntryBadgeComponent profileListEntryBadgeComponent = new ProfileListEntryBadgeComponent();
        profileListEntryBadgeComponent.p(6);
        profileListEntryBadgeComponent.r(ClickGuiProfilesPage::lambda$renderCategoryButtons$2);
        this.L$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$m6o1mi().h(new PaddedComponent(6.0, 0.0, 3.0, 0.0, profileListEntryBadgeComponent), new Object[0]);
    }

    private void L$src$V$1lgkml7() {
        if (this.Bd == null || this.Bz == null) {
            return;
        }
        if (!this.Bz.u$src$Z$wt77ym()) {
            this.Bz.k("");
            return;
        }
        String string = this.Bz.i$src$Ljava_lang_String_$1n2xf3k();
        Profile profile = Vape.INSTANCE.getProfilesManager().G(string);
        if (profile != null) {
            return;
        }
        this.Bd.h(string);
        this.Bd.c(true);
        this.Bd.B(false);
        Vape.INSTANCE.getProfilesManager().m(this.Bd, true);
        Vape.INSTANCE.getProfilesManager().L(this.Bd);
        this.Bd = null;
        this.Bk = null;
        this.Bz = null;
        this.BF = null;
        this.BD = null;
        this.BU = false;
        this.d$src$V$1ltrotv();
        this.p$src$V$1m0d7y7();
    }

    @EventHandler
    public void g(ProfileListMutationEvent profileListMutationEvent) {
        this.d$src$V$1ltrotv();
        Profile profile = Vape.INSTANCE.getProfilesManager().M();
        if (!this.BU && this.B1 != profile) {
            this.p$src$V$1m0d7y7();
        }
    }

    private void lambda$renderCategoryButtons$1(Profile profile) {
        this.o(profile);
    }

    private void lambda$createProfileContent$8() {
        this.Bt.K$src$V$sfnnd();
        this.d$src$V$1ltrotv();
    }

    private void O(PanelComponent panelComponent) {
        for (GuiComponent guiComponent : ProfilesSettingsFrameState.F(true)) {
            guiComponent.o(panelComponent.A());
            guiComponent.q(panelComponent.A());
            panelComponent.h(guiComponent, new Object[0]);
        }
    }

    private void Z(Profile profile, double d, double d2) {
        this.Bz = new SmallTextInputComponent("Type name");
        this.Bz.V(0.0f);
        this.Bz.C(0.0);
        this.Bz.H(0.0f);
        this.Bz.O(0.0f);
        this.Bz.W(true);
        this.Bz.Y(14.0);
        this.Bz.n(48);
        BindableInputComponent bindableInputComponent = new BindableInputComponent(profile, ClickGuiProfilesPage.J.Z);
        bindableInputComponent.f(false);
        bindableInputComponent.Y(10.0);
        ProfilesPageEmptyStateComponent profilesPageEmptyStateComponent = new ProfilesPageEmptyStateComponent(this, bindableInputComponent);
        profilesPageEmptyStateComponent.o(d2);
        profilesPageEmptyStateComponent.Y(22.0);
        profilesPageEmptyStateComponent.d(false);
        profilesPageEmptyStateComponent.H(this.Bz, bindableInputComponent);
        this.BA.h(new PaddedComponent(2.0, d, d, d, profilesPageEmptyStateComponent), new Object[0]);
        PanelComponent panelComponent = new PanelComponent(this.BA.A(), 14.0);
        panelComponent.d(false);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        TextButton textButton = new TextButton("CANCEL", 0.625, ClickGuiProfilesPage.J.i, ClickGuiProfilesPage.J.i.brighter(), null, 2.0f, 1.0f, 35.5, 14.0);
        textButton.a(true);
        textButton.c(true);
        textButton.F(false);
        textButton.h(ClickGuiProfilesPage.J.A);
        textButton.r(this::a$src$V$1ls4b1s);
        this.BD = new TextButton("CREATE", 0.625, ClickGuiProfilesPage.J.B, ClickGuiProfilesPage.J.B.brighter(), null, 2.0f, 1.0f, 35.5, 14.0);
        this.BD.a(true);
        this.BD.c(true);
        this.BD.F(false);
        this.BD.h(ClickGuiProfilesPage.J.A);
        this.BD.k(true);
        this.BD.r(this::L$src$V$1lgkml7);
        panelComponent.h(new SpacerComponent(this.BA.A() - textButton.A() - 4.0 - this.BD.A() - 8.0, 0.0), new Object[0]);
        panelComponent.h(new PaddedComponent(0.0, 0.0, 0.0, 4.0, textButton), new Object[0]);
        panelComponent.h(new PaddedComponent(0.0, 0.0, 0.0, 0.0, this.BD), new Object[0]);
        double d3 = this.BA.L() - this.BA.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y() - panelComponent.L() - d * 5.0 + 1.0;
        d3 = Math.max(0.0, d3);
        this.Bv = new ProfileModuleSnapshotListComponent(profile, this.BA.A() - 6.0 - 3.0, d3);
        this.Bv.o(ProfileModuleSnapshotListStyle.MODERN);
        this.BA.h(new PaddedComponent(0.0, 0.0, 3.0, 0.0, this.Bv), new Object[0]);
        this.BA.h(new PaddedComponent(d * 2.0 - 1.0, d, 0.0, 0.0, panelComponent), new Object[0]);
        this.Bz.o(this::lambda$renderCreateProfileContent$3);
        this.Bz.s(new ProfilesPageRefreshListener(this));
        this.s$src$V$1m20lqa();
        this.Bz.b$src$V$17wa4kz();
    }

    public static void q(ClickGuiProfilesPage clickGuiProfilesPage) {
        clickGuiProfilesPage.s$src$V$1m20lqa();
    }

    private void lambda$beginCreateProfileFlow$4(ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            return;
        }
        if (!apiResponse.t()) {
            return;
        }
        this.Bd.K((UUID)apiResponse.T());
    }

    public static boolean D$src$Z$1lc69xr() {
        boolean bl = ClickGuiProfilesPage.p();
        return false;
    }

    public static boolean p() {
        return B3;
    }

    private void lambda$createProfileSidecar$7(Profile profile, PanelComponent panelComponent) {
        this.t(profile, panelComponent);
    }

    private void s$src$V$1m20lqa() {
        if (this.BD == null || this.Bz == null) {
            return;
        }
        boolean bl = this.Bz.u$src$Z$wt77ym();
        this.BD.k(!bl);
        this.n$src$V$1lz9mrh();
    }

    private void o(Profile profile) {
        this.l(profile);
        ProfilesPageOverlayController profilesPageOverlayController = new ProfilesPageOverlayController(null, profile);
        profilesPageOverlayController.c(false);
        this.Bt.Z(ClickGuiOverlaySpec.q().e(profile.n$src$Ljava_lang_String_$xqhelw()).C("newsettings").n(ClickGuiOverlayPlacement.DOCKED).K(192.0).x(false).v(profilesPageOverlayController).N(arg_0 -> this.lambda$createProfileSidecar$7(profile, arg_0)).w());
    }

    private void n$src$V$1lz9mrh() {
        if (this.BF == null || this.Bz == null) {
            return;
        }
        String string = this.Bz.i$src$Ljava_lang_String_$1n2xf3k();
        if (string == null || string.trim().isEmpty()) {
            this.BF.X("New Profile");
        } else {
            this.BF.X(string);
        }
    }

    private void lambda$new$0() {
        this.l(null);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void u() {
        this.Ba.u(this.Bg);
        if (this.BA != null) {
            this.BA.T(this.a(ClickGuiProfilesPage.J.m));
        }
        if (this.BU) {
            this.n$src$V$1lz9mrh();
        }
        if (this.BB != null) {
            Profile profile = Vape.INSTANCE.getProfilesManager().M();
            if (this.B1 != profile && !this.BU) {
                this.B1 = profile;
                if (this.BE != null) {
                    this.BE.R(profile);
                }
                if (this.Bv != null) {
                    this.Bv.j(profile);
                }
            }
            for (GuiComponent guiComponent : this.BB.f()) {
                ClickGuiProfileCardComponent clickGuiProfileCardComponent;
                if (!(guiComponent instanceof PaddedComponent) || (clickGuiProfileCardComponent = ((PaddedComponent)guiComponent).t(ClickGuiProfileCardComponent.class)) == null || clickGuiProfileCardComponent.h$src$Lgg_vape_config_Profile_$18amnwr() == null || clickGuiProfileCardComponent == this.BF) continue;
                clickGuiProfileCardComponent.g(clickGuiProfileCardComponent.h$src$Lgg_vape_config_Profile_$18amnwr() == profile);
            }
        }
    }

    @Override
    public void Z$src$V$15w0jcm() {
        super.Z$src$V$15w0jcm();
        if (this.B8 != null) {
            this.Bt.F(this.B8);
            this.Bt.k(this.B8);
        }
        EventBus.getInstance().unregisterListener(this);
        EventBus.getInstance().registerListener(this, new Predicate[0]);
        this.d$src$V$1ltrotv();
    }

    private void p$src$V$1m0d7y7() {
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().S();
        Profile profile = Vape.INSTANCE.getProfilesManager().M();
        if (!this.BU) {
            this.B1 = profile;
        }
        this.BA = new PanelComponent(this.f$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$o6l04().A(), this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().L());
        this.BA.T(ClickGuiProfilesPage.J.m);
        this.BA.V(3.0f);
        this.BA.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        double d = 8.0;
        double d2 = this.BA.A() - d * 2.0;
        if (this.BU) {
            this.Z(profile, d, d2);
        } else {
            this.M(profile, d, d2);
        }
        this.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(this.BA, new Object[0]);
    }

    private static ApiResponse lambda$beginCreateProfileFlow$5(Throwable throwable) {
        return null;
    }

    public ClickGuiProfilesPage(ClickGuiMainFrame clickGuiMainFrame, double d, double d2, double d3) {
        super(d, d2, d3, 2.0, "Profiles");
        this.Bt = clickGuiMainFrame;
        this.getClass();
        this.Ba = new DoubleAnimation(0.15, 0.0, 1.0);
        this.Bg = false;
        this.BU = false;
        this.B8 = this::lambda$new$0;
        this.Bt.k(this.B8);
        EventBus.getInstance().registerListener(this, new Predicate[0]);
        this.Q$src$V$1ljblk0();
        this.p$src$V$1m0d7y7();
    }

    private void lambda$renderCreateProfileContent$3(char c, int n) {
        this.s$src$V$1m20lqa();
    }

    private void d$src$V$1ltrotv() {
        if (this.BB == null) {
            return;
        }
        this.BB.S();
        this.BB.h(new SpacerComponent(0.0, 1.0), new Object[0]);
        Profile profile = Vape.INSTANCE.getProfilesManager().M();
        if (this.BU && this.Bd != null) {
            this.BF = new ClickGuiProfileCardComponent(this.Bd);
            this.BF.o(this.BB.A());
            this.BF.Y(20.0);
            this.BF.g(false);
            this.BF.j(false);
            this.BF.v(true);
            this.BF.X("New Profile");
            this.BB.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, this.BF), new Object[0]);
        } else {
            this.BF = null;
        }
        for (Profile profile2 : Vape.INSTANCE.getProfilesManager().b()) {
            ClickGuiProfileCardComponent clickGuiProfileCardComponent = new ClickGuiProfileCardComponent(profile2);
            clickGuiProfileCardComponent.o(this.BB.A());
            clickGuiProfileCardComponent.Y(20.0);
            clickGuiProfileCardComponent.g(!this.BU && profile2 == profile);
            clickGuiProfileCardComponent.e(this.BU);
            clickGuiProfileCardComponent.f(() -> this.lambda$refreshProfilesList$6(profile2));
            this.BB.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, clickGuiProfileCardComponent), new Object[0]);
        }
        this.BB.l$src$V$1mibm4x();
    }

    @Override
    public void K() {
        super.K();
        if (this.B8 != null) {
            this.Bt.F(this.B8);
        }
        EventBus.getInstance().unregisterListener(this);
    }
}
