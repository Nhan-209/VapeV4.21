package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.ui.click.component.TextInputComponentBase;

public class ProfileCreateNameInputComponent
extends TextInputComponentBase {
    private final Profile profile;

    @Override
    public void submit() {
        if (!this.hasNonBlankText()) {
            this.setText("");
            return;
        }
        String profileName = this.getText();
        Profile existingProfile = Vape.INSTANCE.getProfilesManager().G(profileName);
        if (existingProfile != null) {
            return;
        }
        this.profile.h(profileName);
        this.profile.c(true);
        Vape.INSTANCE.getProfilesManager().m(this.profile, true);
        Vape.INSTANCE.getProfilesManager().L(this.profile);
        this.setText("");
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public double getComponentWidth() {
        return this.A() + 2.5;
    }


    public ProfileCreateNameInputComponent(String placeholder, Profile profile) {
        super(placeholder);
        this.profile = profile;
        this.setShowDisabledOverlay(false);
        this.setMaxLength(48);
    }
}
