package gg.vape.friend;

import gg.vape.Vape;
import gg.vape.friend.FriendRequestModel;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.UserModel;

public class FriendRequest {
    private final OnlineFriend X;
    private static String[] k;
    private final long G;

    public FriendRequest(FriendRequestModel friendRequestModel, boolean bl) {
        this.G = friendRequestModel.b();
        UserModel userModel = bl ? friendRequestModel.J() : friendRequestModel.U();
        this.X = Vape.INSTANCE.getOnlineManager().u().Q(userModel.g(), () -> new OnlineFriend(userModel));
    }

    static {
        if (FriendRequest.K() != null) {
            FriendRequest.m(new String[4]);
        }
    }

    public static void m(String[] stringArray) {
        k = stringArray;
    }

    public long C() {
        return this.G;
    }

    public static String[] K() {
        return k;
    }

    public OnlineFriend x() {
        return this.X;
    }
}
