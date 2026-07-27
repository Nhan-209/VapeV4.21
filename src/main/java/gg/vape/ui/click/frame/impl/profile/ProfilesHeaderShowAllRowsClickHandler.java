package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsHeaderComponent;

class ProfilesHeaderShowAllRowsClickHandler
implements GuiClickListener {
    final ProfilesSettingsFrame J;
    final ProfilesSettingsHeaderComponent L;

    ProfilesHeaderShowAllRowsClickHandler(ProfilesSettingsHeaderComponent profilesSettingsHeaderComponent, ProfilesSettingsFrame profilesSettingsFrame) {
        this.L = profilesSettingsHeaderComponent;
        this.J = profilesSettingsFrame;
    }

    @Override
    public void P() {
        this.J.h(true);
        for (GuiComponent guiComponent : this.J.f()) {
            if (!(guiComponent instanceof ProfileListEntryComponent)) continue;
            guiComponent.Z(true);
        }
        this.J.l$src$Lgg_vape_ui_click_frame_PopupFrame_$vsvtwn();
    }

}

