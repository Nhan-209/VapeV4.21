package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyDetailsPanel;
import gg.vape.ui.click.component.GuiClickListener;

public class PartyOverviewPanelActionClickHandler
implements GuiClickListener {
    final PartyDetailsPanel F;

    @Override
    public void P() {
        PartyDetailsPanel.x(this.F);
    }

    public PartyOverviewPanelActionClickHandler(PartyDetailsPanel kq_02) {
        this.F = kq_02;
    }
}

