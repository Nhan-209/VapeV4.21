package gg.vape.friend.ui;

import gg.vape.friend.FriendEntry;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.input.TrailingActionTextInputComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsPage;
import java.util.List;

public class FriendAliasEditInputComponent
extends TrailingActionTextInputComponent {
    final String[] pU;
    final ClickGuiFriendsPage pC;
    final TextButton pr;
    final TextButton pg;
    final FriendEntry p4;

    public FriendAliasEditInputComponent(ClickGuiFriendsPage clickGuiFriendsPage, String string, List list, String[] stringArray, TextButton textButton, TextButton textButton2, FriendEntry friendEntry) {
        super(string, list);
        this.pC = clickGuiFriendsPage;
        this.pU = stringArray;
        this.pg = textButton;
        this.pr = textButton2;
        this.p4 = friendEntry;
    }


    @Override
    public void submit() {
        super.submit();
        ClickGuiFriendsPage.updateFriendAlias(this.pC, this.p4, this.getText());
        this.pU[0] = this.getText();
        this.pg.setVisible(false);
        this.pr.setVisible(false);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        boolean bl = !text.equals(this.pU[0]);
        this.pg.setVisible(bl);
        this.pr.setVisible(bl);
    }
}
