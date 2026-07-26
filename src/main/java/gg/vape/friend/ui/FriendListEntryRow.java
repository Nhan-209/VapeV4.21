package gg.vape.friend.ui;

import gg.vape.friend.ExternalFriend;
import gg.vape.friend.FriendEntry;
import gg.vape.friend.ui.FriendListEntryRemoveClickHandler;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.component.SelectableTextRowComponent;

public class FriendListEntryRow
extends SelectableTextRowComponent {
    private FriendEntry s1;

    @Override
    public String g$src$Ljava_lang_String_$n6442u() {
        return super.g$src$Ljava_lang_String_$n6442u();
    }

    @Override
    public void H() {
        super.H();
        String string = this.s1.s();
        if (!this.u$src$Z$1dafklf()) {
            if (!this.s1.o().equals(this.s1.s())) {
                string = "*" + this.s1.E();
            }
        } else if (this.s1 instanceof ExternalFriend) {
            ExternalFriend externalFriend = (ExternalFriend)this.s1;
            string = "*" + externalFriend.d().C();
        }
        this.n(string);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        super.g(guiMouseEvent);
        if (guiMouseEvent.getAction().equals((Object)MouseButton.LEFT_CLICK)) {
            this.s1.k(!this.s1.c());
        }
    }

    public FriendListEntryRow(FriendEntry friendEntry) {
        super(FriendListEntryRow.J.B, friendEntry.s());
        this.s1 = friendEntry;
        if (friendEntry instanceof ExternalFriend) {
            this.g("synced@2x");
            this.W(FriendListEntryRow.J.T);
        }
        this.i(0.0f);
        this.o(99.0);
        this.P(true);
        this.I(new FriendListEntryRemoveClickHandler(this, friendEntry));
        this.w("Toggle friend between Active and Inactive");
        this.x$src$Lgg_vape_ui_click_component_SquareIconButtonComp$122v8iw().w("Remove friend from list");
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean l() {
        return this.s1.c();
    }
}

