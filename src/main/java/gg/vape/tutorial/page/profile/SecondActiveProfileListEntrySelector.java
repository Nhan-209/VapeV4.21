package gg.vape.tutorial.page.profile;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.ProfilesTutorialPage;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryComponent;

public class SecondActiveProfileListEntrySelector
extends TutorialTargetSelector<ProfileListEntryComponent> {
    final ProfilesTutorialPage m;

    public boolean p(ProfileListEntryComponent profileListEntryComponent) {
        return profileListEntryComponent.isActiveProfile();
    }

    @Override
    public boolean X(ProfileListEntryComponent profileListEntryComponent) {
        return this.p(profileListEntryComponent);
    }

    public SecondActiveProfileListEntrySelector(ProfilesTutorialPage profilesTutorialPage, Class clazz) {
        super(clazz);
        this.m = profilesTutorialPage;
    }

}
