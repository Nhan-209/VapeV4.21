package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;

public class ProfilesShowAllRowsClickHandler
implements GuiClickListener {
    final ProfilesSettingsFrame k;


    public ProfilesShowAllRowsClickHandler(ProfilesSettingsFrame profilesSettingsFrame) {
        this.k = profilesSettingsFrame;
    }

    @Override
    public void P() {
        this.k.h(true);
        for (GuiComponent guiComponent : this.k.f()) {
            if (!(guiComponent instanceof ProfileListEntryComponent)) continue;
            guiComponent.Z(true);
        }
        this.k.l$src$Lgg_vape_ui_click_frame_PopupFrame_$vsvtwn();
        ProfilesSettingsFrame.Y(this.k).Z(true);
        ProfilesSettingsFrame.G(this.k).Z(false);
    }
}

