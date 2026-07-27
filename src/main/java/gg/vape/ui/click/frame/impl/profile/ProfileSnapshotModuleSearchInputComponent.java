package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotModuleListPanel;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotModuleRowComponent;
import gg.vape.utils.StringUtils;

class ProfileSnapshotModuleSearchInputComponent
extends LabeledTextInputComponent {
    final ProfileSnapshotModuleListPanel m0;

    @Override
    public void k(String string) {
        super.k(string);
        for (ProfileSnapshotModuleRowComponent profileSnapshotModuleRowComponent : ProfileSnapshotModuleListPanel.S(this.m0)) {
            if (StringUtils.y(profileSnapshotModuleRowComponent.b$src$Lgg_vape_config_ProfileModuleSnapshot_$6v7veg().getName()).contains(StringUtils.y(ProfileSnapshotModuleListPanel.R(this.m0).i$src$Ljava_lang_String_$1n2xf3k()))) {
                profileSnapshotModuleRowComponent.Z(true);
                continue;
            }
            profileSnapshotModuleRowComponent.Z(false);
        }
        if (ProfileSnapshotModuleListPanel.R(this.m0).i$src$Ljava_lang_String_$1n2xf3k().isEmpty()) {
            ProfileSnapshotModuleListPanel.b(this.m0);
        }
    }


    ProfileSnapshotModuleSearchInputComponent(ProfileSnapshotModuleListPanel profileSnapshotModuleListPanel, String string, boolean bl, boolean bl2) {
        super(string, bl, bl2);
        this.m0 = profileSnapshotModuleListPanel;
    }
}
