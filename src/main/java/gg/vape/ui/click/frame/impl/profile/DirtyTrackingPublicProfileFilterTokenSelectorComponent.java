package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenSelectorComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileSearchFilterPanel;

class DirtyTrackingPublicProfileFilterTokenSelectorComponent
extends PublicProfileFilterTokenSelectorComponent {
    final PublicProfileSearchFilterPanel FD;

    DirtyTrackingPublicProfileFilterTokenSelectorComponent(PublicProfileSearchFilterPanel publicProfileSearchFilterPanel, String string, Runnable runnable, double d, double d2, boolean bl, boolean bl2) {
        super(string, runnable, d, d2, bl, bl2);
        this.FD = publicProfileSearchFilterPanel;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        PublicProfileSearchFilterPanel.o(this.FD, true);
        super.g(guiMouseEvent);
    }
}
