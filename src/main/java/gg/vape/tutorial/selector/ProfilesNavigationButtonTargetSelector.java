package gg.vape.tutorial.selector;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.ProfilesTutorialPage;
import gg.vape.ui.click.frame.FrameNavigationButtonComponent;

public class ProfilesNavigationButtonTargetSelector
extends TutorialTargetSelector<FrameNavigationButtonComponent> {
    private static final String b = "Profiles";
    final ProfilesTutorialPage N;

    public boolean K(FrameNavigationButtonComponent frameNavigationButtonComponent) {
        return frameNavigationButtonComponent.N$src$Ljava_lang_String_$wy122q().equals(b);
    }

    @Override
    public boolean X(FrameNavigationButtonComponent frameNavigationButtonComponent) {
        return this.K(frameNavigationButtonComponent);
    }

    public ProfilesNavigationButtonTargetSelector(ProfilesTutorialPage profilesTutorialPage, Class clazz) {
        super(clazz);
        this.N = profilesTutorialPage;
    }
}
