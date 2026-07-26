package gg.vape.friend.ui;

import gg.vape.friend.ui.CurrentPartyPanel;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import java.awt.Point;

public class CurrentPartyNameOpenDetailsMouseListener
implements GuiMouseListener {
    final CurrentPartyPanel Y;

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        CurrentPartyPanel.W(this.Y, point, mouseClickButton);
    }

    public CurrentPartyNameOpenDetailsMouseListener(CurrentPartyPanel currentPartyPanel) {
        this.Y = currentPartyPanel;
    }
}

