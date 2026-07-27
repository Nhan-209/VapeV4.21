package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyDetailsPanel;
import gg.vape.ui.click.GuiMouseListener;
import java.awt.Point;

public class PartyOverviewPanelPopupOutsideClickListener
implements GuiMouseListener {
    final PartyDetailsPanel O;

    public PartyOverviewPanelPopupOutsideClickListener(PartyDetailsPanel partyDetailsPanel) {
        this.O = partyDetailsPanel;
    }

    @Override
    public boolean Q(Point point) {
        if (!PartyDetailsPanel.P(this.O).Q().R(point) && !PartyDetailsPanel.b(this.O).Q().R(point)) {
            PartyDetailsPanel.H(this.O);
            return true;
        }
        return GuiMouseListener.super.Q(point);
    }

}

