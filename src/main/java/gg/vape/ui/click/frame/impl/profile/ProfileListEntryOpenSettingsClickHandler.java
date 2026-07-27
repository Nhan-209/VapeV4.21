package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryComponent;

class ProfileListEntryOpenSettingsClickHandler
implements GuiClickListener {
    final ProfileListEntryComponent T;
    final Profile t;

    ProfileListEntryOpenSettingsClickHandler(ProfileListEntryComponent profileListEntryComponent, Profile profile) {
        this.T = profileListEntryComponent;
        this.t = profile;
    }

    @Override
    public void P() {
        if (Vape.INSTANCE.getProfilesManager().M().equals(this.t)) {
            this.t.a();
        }
        this.T.e$src$V$kjgqji();
    }

}

