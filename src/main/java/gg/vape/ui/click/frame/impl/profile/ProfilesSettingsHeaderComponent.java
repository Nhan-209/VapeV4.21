package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.frame.ToggleableFrameHeaderComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilesHeaderApplyPendingProfileClickHandler;
import gg.vape.ui.click.frame.impl.profile.ProfilesHeaderShowActiveRowsClickHandler;
import gg.vape.ui.click.frame.impl.profile.ProfilesHeaderShowAllRowsClickHandler;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;
import gg.vape.ui.font.SmoothFontRenderer;

public class ProfilesSettingsHeaderComponent
extends ToggleableFrameHeaderComponent {
    private IconButtonComponent Vr = new IconButtonComponent("newhide", 0.7);
    private IconButtonComponent Vn;
    private TextLabel Vu = new TextLabel("Edit");
    private ProfilesSettingsFrame Vl;

    public ProfilesSettingsHeaderComponent(ProfilesSettingsFrame profilesSettingsFrame, String string, String string2, double d) {
        super(profilesSettingsFrame, string, string2, d);
        this.Vn = new IconButtonComponent("moduleback");
        this.Vl = profilesSettingsFrame;
        this.Vr.w("Edit hidden profiles");
        this.Vr.r(new ProfilesHeaderShowAllRowsClickHandler(this, profilesSettingsFrame));
        this.Vn.r(new ProfilesHeaderApplyPendingProfileClickHandler(this, profilesSettingsFrame));
        this.Vu.r(new ProfilesHeaderShowActiveRowsClickHandler(this, profilesSettingsFrame));
        this.H(this.Vr, this.Vu, this.Vn);
    }

    private static ObfuscatedRuntimeException c(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private static boolean lambda$onRender$0(Profile profile) {
        return !profile.U();
    }

    @Override
    public void H() {
        super.H();
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        if (this.Vl.l$src$Lgg_vape_ui_click_frame_PopupFrame_$vsvtwn() != null) {
            this.Y(false);
            this.L$src$Lgg_vape_ui_click_component_IconButtonComponent_$1i7gwfq().Z(false);
            this.Vr.Z(false);
            this.Vu.Z(false);
            this.Vn.G(ProfilesSettingsHeaderComponent.J.A);
            this.Vn.Z(true);
            this.Vn.K(this.G$src$D$1b2f02a() + 5.0 - 1.0);
            this.Vn.S(this.n());
            this.Vn.Y(this.L());
            return;
        }
        this.Y(true);
        this.Vn.Z(false);
        if (this.Vl.u$src$Z$6rsek8()) {
            this.L$src$Lgg_vape_ui_click_component_IconButtonComponent_$1i7gwfq().Z(false);
            this.Vl.N$src$V$66cfbp();
            this.Vr.Z(false);
            this.Vu.Z(true);
            this.Vu.Z(true);
            this.Vu.d("Done");
            this.Vu.K(this.G$src$D$1b2f02a() + this.A() - 10.0 - 16.0 - smoothFontRenderer.N(this.Vu.L$src$Ljava_lang_String_$1ncdwqb()) / 2.0);
            this.Vu.S(this.n());
            this.Vu.Y(this.L());
        } else {
            this.L$src$Lgg_vape_ui_click_component_IconButtonComponent_$1i7gwfq().Z(true);
            this.Vu.Z(false);
            this.Vr.Z(true);
            this.Vr.G(this.Vl.u$src$Z$6rsek8() ? ProfilesSettingsHeaderComponent.J.f : null);
            this.Vr.K(this.G$src$D$1b2f02a() + this.A() - 15.0 - 13.0 - 16.0);
            this.Vr.S(this.n());
            this.Vr.Y(this.L());
            int n = (int)Vape.INSTANCE.getProfilesManager().b().stream().filter(ProfilesSettingsHeaderComponent::lambda$onRender$0).count();
            if (n != 0) {
                String string = Integer.toString(n);
                smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + this.A() - 16.25 - 13.0 - 16.0 - smoothFontRenderer.N(string) / 2.0, this.n() + this.L() / 2.0 - smoothFontRenderer.d(string) / 2.0, ProfilesSettingsHeaderComponent.J.W);
            }
        }
    }
}

