package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.GuiRefreshListener;
import gg.vape.ui.click.frame.impl.main.ClickGuiProfilesPage;

public class ProfilesPageRefreshListener
implements GuiRefreshListener {
    final ClickGuiProfilesPage n;

    @Override
    public void G() {
        ClickGuiProfilesPage.q(this.n);
    }

    public ProfilesPageRefreshListener(ClickGuiProfilesPage kV) {
        this.n = kV;
    }
}

