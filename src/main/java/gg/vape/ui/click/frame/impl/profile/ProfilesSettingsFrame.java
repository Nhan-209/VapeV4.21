package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.click.frame.SettingsSubpageFrame;
import gg.vape.ui.click.frame.impl.profile.ProfileCreatePanelComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrameState;
import gg.vape.ui.click.frame.impl.profile.ProfilesShowActiveRowsClickHandler;
import gg.vape.ui.click.frame.impl.profile.ProfilesShowAllRowsClickHandler;
import gg.vape.ui.font.SmoothFontRenderer;

public class ProfilesSettingsFrame
extends SettingsSubpageFrame {
    private final TextLabel iJ;
    private boolean io;
    private PopupFrame iG;
    private final PanelComponent ij;
    private ProfileListEntryComponent iu;
    private final ProfileCreatePanelComponent ic;
    private final FlowLayoutComponent ix;
    private static int[] iH;
    private BooleanToggleComponent iK;
    private final IconButtonComponent i8 = new IconButtonComponent("newedit", 0.7);

    public static TextLabel Y(ProfilesSettingsFrame profilesSettingsFrame) {
        return profilesSettingsFrame.iJ;
    }

    public void N$src$V$66cfbp() {
        if (this.iG != null) {
            this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().h();
            ClientSettings.K(this.iG);
            this.iG = null;
        }
        if (this.ic.H$src$Lgg_vape_config_Profile_$pnt2xs() != null) {
            Vape.INSTANCE.getProfilesManager().L(this.ic.H$src$Lgg_vape_config_Profile_$pnt2xs());
            this.ic.u(null);
        }
    }

    public static void E(int[] nArray) {
        iH = nArray;
    }

    public void L(ProfileListEntryComponent profileListEntryComponent) {
        this.iu = profileListEntryComponent;
    }

    public void F(Profile profile) {
        ProfileListEntryComponent profileListEntryComponent = null;
        for (GuiComponent guiComponent : this.ij.f()) {
            ProfileListEntryComponent profileListEntryComponent2;
            if (!(guiComponent instanceof ProfileListEntryComponent) || !(profileListEntryComponent2 = (ProfileListEntryComponent)guiComponent).N$src$Lgg_vape_config_Profile_$p2odie().equals(profile)) continue;
            profileListEntryComponent = profileListEntryComponent2;
        }
        if (profileListEntryComponent == null) {
            return;
        }
        this.ij.I(profileListEntryComponent);
        this.ij.l$src$V$1mibm4x();
        this.ix.l$src$V$1mibm4x();
    }

    @Override
    public void w() {
        super.w();
        if (this.iG != null) {
            ClientSettings.K(this.iG);
            this.iG = null;
        }
    }

    public FlowLayoutComponent Q$src$Lgg_vape_ui_click_component_FlowLayoutComponent_$s9lre() {
        return this.ix;
    }

    public static IconButtonComponent G(ProfilesSettingsFrame profilesSettingsFrame) {
        return profilesSettingsFrame.i8;
    }

    public PopupFrame l$src$Lgg_vape_ui_click_frame_PopupFrame_$vsvtwn() {
        return this.iG;
    }

    public ProfileListEntryComponent e() {
        return this.iu;
    }

    public void P(Profile profile) {
        this.ij.H(new ProfileListEntryComponent(this, profile));
        this.ij.l$src$V$1mibm4x();
        this.ix.l$src$V$1mibm4x();
    }

    @Override
    public void J() {
        if (this.iu != null) {
            if (this.iu.w$src$Z$e457mb()) {
                this.iu.J();
            }
            return;
        }
        super.J();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void c() {
        int n = 0;
        for (Profile profile : Vape.INSTANCE.getProfilesManager().b()) {
            if (profile.U()) continue;
            ++n;
        }
        if (!this.u$src$Z$6rsek8()) {
            if (n > 0) {
                this.i8.H("newhide");
            } else {
                this.i8.H("newedit");
            }
            this.i8.Z(this.w$src$Z$e457mb());
        }
        for (int i = 0; i < 5; ++i) {
            this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().D$src$V$1njh5lz();
        }
        this.ij.o(110.0);
        super.c();
        if (!this.u$src$Z$6rsek8() && !this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().v() && n > 0 && this.w$src$Z$e457mb()) {
            SmoothFontRenderer smoothFontRenderer = this.O(0.9);
            double d = smoothFontRenderer.d("" + n);
            double d2 = smoothFontRenderer.N("" + n);
            smoothFontRenderer.d("" + n, this.G$src$D$1b2f02a() + this.A() - 5.0 - 32.0 - 3.0 - d2, this.n() + this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().L() / 2.0 - d / 2.0, ProfilesSettingsFrame.J.Z);
        }
    }

    public void h(boolean bl) {
        this.io = bl;
    }

    public void w(PopupFrame popupFrame) {
        this.iG = popupFrame;
    }

    public static void Z$src$V$6cxyg1() {
        ProfilesSettingsFrame profilesSettingsFrame = ClientSettings.g(ProfilesSettingsFrame.class);
        profilesSettingsFrame.ij.S();
        for (Profile profile : Vape.INSTANCE.getProfilesManager().b()) {
            profilesSettingsFrame.P(profile);
        }
        profilesSettingsFrame.ix.l$src$V$1mibm4x();
    }

    @Override
    public void u() {
        super.u();
        this.ij.o(110.0);
        if (this.iG != null) {
            this.iG.l$src$V$1mibm4x();
        }
    }

    public BooleanToggleComponent I$src$Lgg_vape_ui_click_component_value_BooleanToggleC$1dukpgy() {
        return this.iK;
    }

    public ProfilesSettingsFrame() {
        super("newprofiles", "Profiles");
        this.iJ = new TextLabel("Done", 0.8);
        this.Z(false);
        this.o(103.0);
        this.N(false);
        this.D(true);
        this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().x(0.5f);
        for (GuiComponent guiComponent : ProfilesSettingsFrameState.F(false)) {
            BooleanToggleComponent booleanToggleComponent;
            this.n(guiComponent);
            if (!(guiComponent instanceof BooleanToggleComponent) || !(booleanToggleComponent = (BooleanToggleComponent)guiComponent).r$src$Lgg_vape_value_Value_$fdf20y().equals(Vape.INSTANCE.getPublicProfileSettings().u)) continue;
            this.iK = booleanToggleComponent;
        }
        this.ix = new FlowLayoutComponent(this.A());
        this.ix.h(new SpacerComponent(1.0, 5.0), new Object[0]);
        GuiComponent[] guiComponentArray = new GuiComponent[1];
        this.ic = new ProfileCreatePanelComponent(this);
        guiComponentArray[0] = this.ic;
        this.ix.H(guiComponentArray);
        GuiComponent[] guiComponentArray2 = new GuiComponent[1];
        this.ij = new PanelComponent(100.0, 125.0);
        guiComponentArray2[0] = this.ij;
        this.ix.H(guiComponentArray2);
        this.ix.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.ij.t(125.0);
        this.ij.o(110.0);
        this.ij.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.H(this.ix);
        this.iJ.Z(false);
        this.iJ.q(20.0);
        this.iJ.u(14.0);
        this.i8.w("Edit hidden profiles");
        this.i8.r(new ProfilesShowAllRowsClickHandler(this));
        this.iJ.r(new ProfilesShowActiveRowsClickHandler(this));
        this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().Q(this.i8);
        this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().Q(this.iJ);
    }

    public ProfileCreatePanelComponent U$src$Lgg_vape_ui_click_frame_impl_profile_ProfileCrea$n5v3x4() {
        return this.ic;
    }

    @Override
    public void D(GuiMouseEvent guiMouseEvent) {
        if (this.iu != null) {
            this.iu.D(guiMouseEvent);
            return;
        }
        super.D(guiMouseEvent);
    }

    @Override
    public void K$src$V$qg5iru() {
        super.K$src$V$qg5iru();
        this.N$src$V$66cfbp();
    }

    static {
        ProfilesSettingsFrame.E(new int[1]);
    }

    public PanelComponent m$src$Lgg_vape_ui_click_component_PanelComponent_$1rlcr9s() {
        return this.ij;
    }

    @Override
    public String getName() {
        return "Profiles";
    }

    public static int[] o$src$AI$1t0yq7w() {
        return iH;
    }

    public boolean u$src$Z$6rsek8() {
        return this.io;
    }
}

