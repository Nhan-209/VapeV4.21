package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsHeaderComponent;

class ProfilesHeaderApplyPendingProfileClickHandler
implements GuiClickListener {
    final ProfilesSettingsFrame a;
    final ProfilesSettingsHeaderComponent z;

    ProfilesHeaderApplyPendingProfileClickHandler(ProfilesSettingsHeaderComponent profilesSettingsHeaderComponent, ProfilesSettingsFrame profilesSettingsFrame) {
        this.z = profilesSettingsHeaderComponent;
        this.a = profilesSettingsFrame;
    }

    @Override
    public void P() {
        this.a.N$src$V$66cfbp();
    }
}

