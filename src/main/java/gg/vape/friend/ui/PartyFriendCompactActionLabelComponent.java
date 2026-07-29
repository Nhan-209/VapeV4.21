package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyFriendRowComponent;
import gg.vape.ui.click.component.gui.AnimatedCenteredTextLabelComponent;
import java.awt.Color;

public class PartyFriendCompactActionLabelComponent
extends AnimatedCenteredTextLabelComponent {
    final PartyFriendRowComponent Vy;

    @Override
    public double C() {
        return 8.0;
    }

    public PartyFriendCompactActionLabelComponent(PartyFriendRowComponent partyFriendRowComponent, String string, Color color) {
        super(string, color);
        this.Vy = partyFriendRowComponent;
    }

    @Override
    public double x() {
        return this.getDefaultFontRenderer().N(this.getText());
    }
}
