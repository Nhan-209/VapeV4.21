package gg.vape.friend.ui;

import gg.vape.friend.FriendEntry;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.SelectableTextRowComponent;

public class FriendSettingsEntryRowComponent
extends SelectableTextRowComponent {
    private FriendEntry dc;

    @Override
    public boolean l() {
        return this.dc.c();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        this.dc.k(!this.dc.c());
    }

    @Override
    public void H() {
        String string = this.dc.s();
        if (!this.dc.o().equals(this.dc.s()) && !this.u$src$Z$1dafklf()) {
            string = "*" + this.dc.E();
        }
        this.n(string);
        super.H();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public FriendSettingsEntryRowComponent(FriendEntry friendEntry) {
        super(FriendSettingsEntryRowComponent.J.B, friendEntry.s());
        this.dc = friendEntry;
    }

    public FriendEntry g$src$Lgg_vape_friend_FriendEntry_$1l55ugb() {
        return this.dc;
    }
}

