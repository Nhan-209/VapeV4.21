package gg.vape.friend;

import com.google.gson.JsonObject;
import gg.vape.friend.Friend;
import gg.vape.friend.FriendEntry;
import gg.vape.friend.OnlineFriend;

public class ExternalFriend
extends FriendEntry {
    private final OnlineFriend R;


    @Override
    public JsonObject toJson() {
        return null;
    }

    @Override
    public String s() {
        if (this.R.u()) {
            return this.R.I();
        }
        return "";
    }

    @Override
    public boolean m() {
        return false;
    }

    public OnlineFriend d() {
        return this.R;
    }

    @Override
    public Friend loadJson(JsonObject jsonObject) {
        return null;
    }

    public ExternalFriend(OnlineFriend onlineFriend) {
        this.R = onlineFriend;
    }
}

