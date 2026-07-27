package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.Profile;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.CenteredPopupFrame;
import gg.vape.ui.click.frame.impl.profile.ProfileCreateActionButtonComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileCreateDividerComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileCreateNameInputComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileCreateSubmitNameInputComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileModuleSnapshotListComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ProfileCreatePanelComponent
extends GuiComponent {
    private final ProfileCreateActionButtonComponent b;
    private Profile I;
    private final ProfileCreateNameInputComponent G;
    private final ProfileCreateActionButtonComponent R;
    private final ProfilesSettingsFrame v;
    private final ProfileCreateDividerComponent K;

    @Override
    public void I() {
    }

    public ProfileCreateNameInputComponent x$src$Lgg_vape_ui_click_frame_impl_profile_ProfileCrea$1re7zmm() {
        return this.G;
    }


    private static void lambda$null$1(Profile profile, ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            return;
        }
        if (!apiResponse.t()) {
            return;
        }
        profile.K((UUID)apiResponse.T());
    }

    @Override
    public void F() {
    }

    public Profile H$src$Lgg_vape_config_Profile_$pnt2xs() {
        return this.I;
    }

    @Override
    public void u() {
    }

    private static ApiResponse lambda$null$2(Throwable throwable) {
        return null;
    }

    @Override
    public void K(double d) {
        super.K(d);
    }

    private void lambda$new$3(ProfilesSettingsFrame profilesSettingsFrame) {
        Profile profile = Vape.INSTANCE.getProfilesManager().M();
        profile.a();
        this.I = profile;
        Profile profile2 = new Profile(profile.n$src$Ljava_lang_String_$xqhelw(), "4.21");
        profile2.e(profile.C(true));
        profile2.d(UUID.randomUUID());
        profile2.K(null);
        profile2.s(false);
        profile2.B(true);
        ApiServices.d().c().u().whenCompleteAsync((arg_0, arg_1) -> ProfileCreatePanelComponent.lambda$null$1(profile2, arg_0, arg_1), (Executor)ClientSettings.f6).exceptionally(ProfileCreatePanelComponent::lambda$null$2);
        Vape.INSTANCE.getProfilesManager().U(profile2);
        PanelComponent panelComponent = new PanelComponent(profilesSettingsFrame.A(), profilesSettingsFrame.Q$src$Lgg_vape_ui_click_component_FlowLayoutComponent_$s9lre().L());
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        ProfileCreateSubmitNameInputComponent profileCreateSubmitNameInputComponent = new ProfileCreateSubmitNameInputComponent(this, "Type name", profile2);
        profileCreateSubmitNameInputComponent.o(this.v.A() - 2.0);
        this.getClass();
        profileCreateSubmitNameInputComponent.Y(17.5 + 5.0);
        panelComponent.h(profileCreateSubmitNameInputComponent, new Object[0]);
        panelComponent.h(new ProfileModuleSnapshotListComponent(profile2, 105.0, 110.0), new Object[0]);
        CenteredPopupFrame centeredPopupFrame = ClientSettings.g(profilesSettingsFrame.Q$src$Lgg_vape_ui_click_component_FlowLayoutComponent_$s9lre(), panelComponent, CenteredPopupFrame.class);
        profilesSettingsFrame.w(centeredPopupFrame);
        profilesSettingsFrame.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().I("New Profile", false);
    }

    public ProfileCreatePanelComponent(ProfilesSettingsFrame profilesSettingsFrame) {
        this.R = new ProfileCreateActionButtonComponent("Public", true, false, 0.8, null, "newpublicprofiles", 0.8, null, ProfileCreatePanelComponent.J.l);
        this.b = new ProfileCreateActionButtonComponent("Create new", true, false, 0.8, null, "newadd", 0.8, J.z(), ProfileCreatePanelComponent.J.l);
        this.G = new ProfileCreateNameInputComponent("Type name", null);
        this.K = new ProfileCreateDividerComponent();
        this.v = profilesSettingsFrame;
        this.R.r(ProfileCreatePanelComponent::lambda$new$0);
        this.R.w("Browse public profiles");
        this.b.r(() -> this.lambda$new$3(profilesSettingsFrame));
        this.b.w("Create a new profile");
        this.H(this.b, this.R, this.K);
    }

    static ProfilesSettingsFrame f(ProfileCreatePanelComponent profileCreatePanelComponent) {
        return profileCreatePanelComponent.v;
    }

    @Override
    public void H() {
        this.b.p(0.7);
        this.R.p(0.7);
        this.b.n(2.0);
        double d = this.G$src$D$1b2f02a();
        this.getClass();
        this.b.K(d + 5.0);
        this.b.S(this.n());
        double d2 = this.L();
        this.getClass();
        this.b.Y(d2 - 5.0 - 0.5);
        this.R.n(1.0);
        this.R.S(this.n());
        double d3 = this.G$src$D$1b2f02a() + this.A() - this.R.A();
        this.getClass();
        this.R.K(d3 - 5.0);
        double d4 = this.L();
        this.getClass();
        this.R.Y(d4 - 5.0 - 0.5);
        if (this.v.l$src$Lgg_vape_ui_click_frame_PopupFrame_$vsvtwn() == null) {
            this.K.Z(true);
            double d5 = this.G$src$D$1b2f02a() + this.A();
            this.getClass();
            this.K.K(d5 - (double)(5.0f * 2.0f) - 1.0);
            this.K.S(this.n() - 3.0);
        } else {
            this.K.Z(false);
        }
        this.K.Z(false);
    }

    @Override
    public double C() {
        return 20.0;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void S(double d) {
        super.S(d);
    }

    public void u(Profile profile) {
        this.I = profile;
    }

    private static void lambda$new$0() {
        Runnable runnable = PublicProfilesFrame::w$src$V$fyo9a0;
        runnable.run();
    }

    @Override
    public double x() {
        return 110.0;
    }
}
