package gg.vape.friend.ui;

import gg.vape.friend.ui.CurrentPartyPanel;
import gg.vape.ui.click.component.GuiClickListener;

public class CurrentPartyLeaveDeleteClickHandler
implements GuiClickListener {
    final CurrentPartyPanel N;

    public CurrentPartyLeaveDeleteClickHandler(CurrentPartyPanel kB) {
        this.N = kB;
    }

    @Override
    public void onPrimaryClick() {
        CurrentPartyPanel.M(this.N);
    }
}
