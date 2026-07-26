package gg.vape.ui.click.frame.impl.profile;

import gg.vape.manager.client.PublicProfileManager;
import gg.vape.runtime.NativeBridge;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOwnerDetailsPanel;
import java.awt.Point;

class PublicProfileOwnerShareCodeCopyClickHandler
implements GuiMouseListener {
    private static final String b = "Copied share code to clipboard!";
    final PublicProfileOwnerDetailsPanel S;

    PublicProfileOwnerShareCodeCopyClickHandler(PublicProfileOwnerDetailsPanel publicProfileOwnerDetailsPanel) {
        this.S = publicProfileOwnerDetailsPanel;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        String string = this.S.rS.a();
        NativeBridge.cpy(string);
        PublicProfileManager.M(b);
    }
}

