package gg.vape.ui.click.frame.impl.main;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsPage;

public class ClickGuiFriendsNameInputListener
extends LabeledTextInputComponent {
    final ClickGuiFriendsPage FD;

    @Override
    public void k(String string) {
        super.k(string);
        ClickGuiFriendsPage.z(this.FD, string);
        ClientSettings.f6.execute(this::lambda$setInputValue$0);
    }

    private void lambda$setInputValue$0() {
        ClickGuiFriendsPage.F(this.FD);
    }

    public ClickGuiFriendsNameInputListener(ClickGuiFriendsPage clickGuiFriendsPage, String string) {
        super(string);
        this.FD = clickGuiFriendsPage;
    }
}
