package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileSearchFilterPanel;

class PublicProfileSelectedFilterPanel
extends PanelComponent {
    final PublicProfileSearchFilterPanel rh;

    @Override
    public void c() {
        PublicProfileSearchFilterPanel.r(this.rh);
        super.c();
    }

    PublicProfileSelectedFilterPanel(PublicProfileSearchFilterPanel publicProfileSearchFilterPanel, double d, double d2) {
        super(d, d2);
        this.rh = publicProfileSearchFilterPanel;
    }
}
