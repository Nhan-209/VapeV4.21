package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsHeaderComponent;

class ProfilesHeaderShowActiveRowsClickHandler
implements GuiClickListener {
    final ProfilesSettingsHeaderComponent K;
    final ProfilesSettingsFrame R;

    ProfilesHeaderShowActiveRowsClickHandler(ProfilesSettingsHeaderComponent profilesSettingsHeaderComponent, ProfilesSettingsFrame profilesSettingsFrame) {
        this.K = profilesSettingsHeaderComponent;
        this.R = profilesSettingsFrame;
    }

    @Override
    public void P() {
        this.R.h(false);
        for (GuiComponent guiComponent : this.R.f()) {
            if (!(guiComponent instanceof ProfileListEntryComponent) || ((ProfileListEntryComponent)guiComponent).N$src$Lgg_vape_config_Profile_$p2odie().U()) continue;
            guiComponent.Z(false);
        }
        this.R.l$src$Lgg_vape_ui_click_frame_PopupFrame_$vsvtwn();
    }

}

