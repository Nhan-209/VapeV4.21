package gg.vape.tutorial.page.profile;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.ProfilesTutorialPage;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryComponent;

public class FirstActiveProfileListEntrySelector
extends TutorialTargetSelector<ProfileListEntryComponent> {
    final ProfilesTutorialPage d;


    public FirstActiveProfileListEntrySelector(ProfilesTutorialPage profilesTutorialPage, Class clazz) {
        super(clazz);
        this.d = profilesTutorialPage;
    }

    public boolean m(ProfileListEntryComponent profileListEntryComponent) {
        return profileListEntryComponent.m$src$Z$knv3du();
    }

    @Override
    public boolean X(ProfileListEntryComponent profileListEntryComponent) {
        return this.m(profileListEntryComponent);
    }
}
