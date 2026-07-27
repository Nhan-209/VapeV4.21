package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.PublicProfileSortMode;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.frame.impl.profile.PublicProfileSearchFilterPanel;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import java.awt.Color;

class PublicProfileSortModeButton
extends TextButton {
    final PublicProfileSearchFilterPanel pg;
    final PublicProfileSortMode p8;

    @Override
    public void H() {
        super.H();
        PublicProfilesFrame publicProfilesFrame = ClientSettings.g(PublicProfilesFrame.class);
        if (publicProfilesFrame.Z$src$Lgg_vape_config_PublicProfileSortMode_$18pvsyy() == this.p8) {
            this.h(Color.WHITE);
            this.G(PublicProfileSortModeButton.J.B, PublicProfileSortModeButton.J.O);
        } else {
            this.h(PublicProfileSortModeButton.J.h);
            this.G(PublicProfileSortModeButton.J.i, PublicProfileSortModeButton.J.y);
        }
    }


    PublicProfileSortModeButton(PublicProfileSearchFilterPanel publicProfileSearchFilterPanel, String string, double d, Color color, Color color2, PublicProfileSortMode publicProfileSortMode) {
        super(string, d, color, color2);
        this.pg = publicProfileSearchFilterPanel;
        this.p8 = publicProfileSortMode;
    }
}
