package gg.vape.tutorial.selector;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.ProfilesTutorialPage;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileCreatePanelComponent;
import java.util.ArrayList;
import java.util.Arrays;

public class ProfileCreateNameInputTargetSelector
extends TutorialTargetSelector<ProfileCreatePanelComponent> {
    final ProfilesTutorialPage x;

    public boolean e(ProfileCreatePanelComponent profileCreatePanelComponent) {
        return true;
    }

    @Override
    public boolean X(ProfileCreatePanelComponent profileCreatePanelComponent) {
        return this.e(profileCreatePanelComponent);
    }

    @Override
    public ArrayList<GuiComponent> v(GuiComponent guiComponent) {
        if (this.o().isInstance(guiComponent)) {
            ProfileCreatePanelComponent profileCreatePanelComponent = (ProfileCreatePanelComponent)guiComponent;
            return new ArrayList<GuiComponent>(Arrays.asList(profileCreatePanelComponent.x$src$Lgg_vape_ui_click_frame_impl_profile_ProfileCrea$1re7zmm()));
        }
        return null;
    }

    public ProfileCreateNameInputTargetSelector(ProfilesTutorialPage profilesTutorialPage, Class clazz) {
        super(clazz);
        this.x = profilesTutorialPage;
    }
}
