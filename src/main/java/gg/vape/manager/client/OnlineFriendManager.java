package gg.vape.manager.client;

import gg.vape.friend.FriendModel;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.UserModel;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

public class OnlineFriendManager {
    private final Set<OnlineFriend> H = new HashSet<OnlineFriend>();


    public void g(OnlineFriend onlineFriend) {
        this.H.remove(onlineFriend);
        OnlineFriendUiHelper.n$src$V$uh9sir();
    }

    public void s() {
        this.H.clear();
        OnlineFriendUiHelper.n$src$V$uh9sir();
    }

    @Nullable
    public OnlineFriend F(String string) {
        for (OnlineFriend onlineFriend : this.H) {
            if (!onlineFriend.C().equals(string)) continue;
            return onlineFriend;
        }
        return null;
    }

    public Set<OnlineFriend> g() {
        return this.H;
    }

    public void D(OnlineFriend onlineFriend) {
        this.H.add(onlineFriend);
        OnlineFriendUiHelper.n$src$V$uh9sir();
    }

    @Nullable
    public OnlineFriend h(FriendModel friendModel) {
        return this.Q(friendModel.L());
    }

    @Nullable
    public OnlineFriend Q(UserModel userModel) {
        for (OnlineFriend onlineFriend : this.H) {
            if (onlineFriend.S().g() != userModel.g()) continue;
            return onlineFriend;
        }
        return null;
    }
}

