package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyFriendRowComponent;
import gg.vape.ui.click.component.gui.AnimatedCenteredTextLabelComponent;
import java.awt.Color;

public class PartyFriendSecondaryActionLabelComponent
extends AnimatedCenteredTextLabelComponent {
    final PartyFriendRowComponent v8;

    public PartyFriendSecondaryActionLabelComponent(PartyFriendRowComponent partyFriendRowComponent, String string, Color color) {
        super(string, color);
        this.v8 = partyFriendRowComponent;
    }

    @Override
    public double C() {
        return 8.0;
    }

    @Override
    public double x() {
        return this.A$src$Lgg_vape_ui_font_SmoothFontRenderer_$jrhwp3().N(this.L$src$Ljava_lang_String_$1ncdwqb()) + 3.0;
    }
}
