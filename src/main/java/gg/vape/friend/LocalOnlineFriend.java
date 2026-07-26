package gg.vape.friend;

import gg.vape.account.MinecraftSessionWrapper;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineFriendActivityState;
import gg.vape.friend.UserModel;
import gg.vape.friend.ui.OnlineFriendActivityPanel;
import gg.vape.wrapper.impl.Minecraft;

public class LocalOnlineFriend
extends OnlineFriend {
    private final OnlineFriendActivityState l;
    private static final String f = "Self#1234";
    private final OnlineFriendActivityPanel C;

    public void c(UserModel userModel) {
        this.q = userModel;
    }

    public OnlineFriendActivityState E() {
        return this.l;
    }

    public OnlineFriendActivityPanel X() {
        return this.C;
    }

    public LocalOnlineFriend() {
        super(f);
        MinecraftSessionWrapper minecraftSessionWrapper = Minecraft.Q$src$Lgg_vape_account_MinecraftSessionWrapper_$1ftnn3u();
        this.d(minecraftSessionWrapper.R(), minecraftSessionWrapper.M());
        this.l = new OnlineFriendActivityState(this);
        this.C = new OnlineFriendActivityPanel(this);
    }
}

