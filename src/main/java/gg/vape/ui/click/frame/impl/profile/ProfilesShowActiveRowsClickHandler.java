package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;

public class ProfilesShowActiveRowsClickHandler
implements GuiClickListener {
    final ProfilesSettingsFrame a;


    public ProfilesShowActiveRowsClickHandler(ProfilesSettingsFrame profilesSettingsFrame) {
        this.a = profilesSettingsFrame;
    }

    @Override
    public void P() {
        this.a.h(false);
        for (GuiComponent guiComponent : this.a.f()) {
            if (!(guiComponent instanceof ProfileListEntryComponent) || ((ProfileListEntryComponent)guiComponent).N$src$Lgg_vape_config_Profile_$p2odie().U()) continue;
            guiComponent.Z(false);
        }
        this.a.l$src$Lgg_vape_ui_click_frame_PopupFrame_$vsvtwn();
        ProfilesSettingsFrame.Y(this.a).Z(false);
        ProfilesSettingsFrame.G(this.a).Z(true);
    }
}

