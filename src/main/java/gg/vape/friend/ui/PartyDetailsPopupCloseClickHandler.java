package gg.vape.friend.ui;

import gg.vape.friend.ui.CurrentPartyPanel;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiClickListener;

public class PartyDetailsPopupCloseClickHandler
implements GuiClickListener {
    final CurrentPartyPanel c;

    @Override
    public void onPrimaryClick() {
        ClientSettings.removePopup(CurrentPartyPanel.n(this.c));
        CurrentPartyPanel.U(this.c, null);
        CurrentPartyPanel.f(this.c, null);
    }

    public PartyDetailsPopupCloseClickHandler(CurrentPartyPanel kB) {
        this.c = kB;
    }
}
