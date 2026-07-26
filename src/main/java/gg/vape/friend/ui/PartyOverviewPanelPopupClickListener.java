package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyDetailsPanel;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import java.awt.Point;

public class PartyOverviewPanelPopupClickListener
implements GuiMouseListener {
    final PartyDetailsPanel p;

    public PartyOverviewPanelPopupClickListener(PartyDetailsPanel kq_02) {
        this.p = kq_02;
    }

    @Override
    public void g(Point point, MouseClickButton uA) {
        PartyDetailsPanel.B(this.p);
    }
}

