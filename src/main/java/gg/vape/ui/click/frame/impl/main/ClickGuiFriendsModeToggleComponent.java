package gg.vape.ui.click.frame.impl.main;

import gg.vape.friend.FriendEntry;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsPage;

public class ClickGuiFriendsModeToggleComponent
extends BooleanToggleComponent {
    final FriendEntry mF;
    final ClickGuiFriendsPage mf;

    public ClickGuiFriendsModeToggleComponent(ClickGuiFriendsPage clickGuiFriendsPage, String string, double d, FriendEntry friendEntry) {
        super(string, d);
        this.mf = clickGuiFriendsPage;
        this.mF = friendEntry;
    }

    @Override
    public void Q$src$V$11xzx98() {
        if (this.mF.c()) {
            this.n$src$Lgg_vape_ui_click_animation_DoubleAnimation_$12lr9ge().C();
        } else {
            this.n$src$Lgg_vape_ui_click_animation_DoubleAnimation_$12lr9ge().O();
        }
    }

    @Override
    public boolean i$src$Z$1d37ezg() {
        return this.mF.c();
    }

    @Override
    public void k$src$V$5mynh8() {
        this.mF.k(!this.mF.c());
        this.Q$src$V$11xzx98();
        OnlineFriendUiHelper.U();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
