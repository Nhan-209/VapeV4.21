package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.Profile;
import gg.vape.ui.click.frame.impl.profile.ProfileCreateNameInputComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileCreatePanelComponent;

class ProfileCreateSubmitNameInputComponent
extends ProfileCreateNameInputComponent {
    final ProfileCreatePanelComponent My;

    @Override
    public void p() {
        super.p();
        this.My.u(null);
        ProfileCreatePanelComponent.f(this.My).N$src$V$66cfbp();
        this.L$src$V$w6nnjd();
    }

    ProfileCreateSubmitNameInputComponent(ProfileCreatePanelComponent profileCreatePanelComponent, String string, Profile profile) {
        super(string, profile);
        this.My = profileCreatePanelComponent;
    }
}
