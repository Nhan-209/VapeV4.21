package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.Profile;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryMetadataComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryOpenButtonComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;

public class ProfileListEntryContainer
extends GuiComponent {
    private final ProfilesSettingsFrame R;
    private final Profile v;
    private final ProfileListEntryMetadataComponent G;
    private final ProfileListEntryOpenButtonComponent a;

    public ProfileListEntryContainer(ProfilesSettingsFrame profilesSettingsFrame, Profile profile) {
        this.R = profilesSettingsFrame;
        this.v = profile;
        double d = this.A();
        this.getClass();
        double d2 = (d - (double)(5.0f * 4.0f) - 1.0) / 4.0;
        this.a = new ProfileListEntryOpenButtonComponent(profile, profilesSettingsFrame::N$src$V$66cfbp);
        this.a.o(d2 * 1.0);
        this.a.Y(16.0);
        this.G = new ProfileListEntryMetadataComponent(profile);
        this.G.o(d2 * 3.0);
        this.G.Y(16.0);
        this.H(this.G, this.a);
    }

    @Override
    public double C() {
        return 25.0;
    }

    @Override
    public double x() {
        return 110.0;
    }

    @Override
    public void H() {
        double d = this.G$src$D$1b2f02a() + 10.0;
        double d2 = this.n() + 5.0;
        this.G.K(d);
        this.G.S(d2);
        this.a.K(d += 2.0 + this.G.A());
        this.a.S(d2);
    }
}

