package gg.vape.friend.ui;

import gg.vape.friend.FriendEntry;
import gg.vape.runtime.ObfuscatedRuntimeException;
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

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void p() {
        super.p();
        ClickGuiFriendsPage.d(this.pC, this.p4, this.i$src$Ljava_lang_String_$1n2xf3k());
        this.pU[0] = this.i$src$Ljava_lang_String_$1n2xf3k();
        this.pg.Z(false);
        this.pr.Z(false);
    }

    @Override
    public void k(String string) {
        super.k(string);
        boolean bl = !string.equals(this.pU[0]);
        this.pg.Z(bl);
        this.pr.Z(bl);
    }
}
