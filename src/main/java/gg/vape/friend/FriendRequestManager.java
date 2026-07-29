package gg.vape.friend;

import gg.vape.Vape;
import gg.vape.friend.FriendRequest;
import gg.vape.friend.IncomingFriendRequest;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OutgoingFriendRequest;
import gg.vape.friend.ui.FriendRequestListPanel;
import gg.vape.friend.ui.OnlineFriendsFrame;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.notification.NotificationType;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.FriendRequestUpdateResponsePacket;
import gg.vape.protocol.packet.FriendRequestUpdateStatus;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class FriendRequestManager {
    private final Map<OnlineFriend, IncomingFriendRequest> q = new LinkedHashMap<OnlineFriend, IncomingFriendRequest>();
    private final Map<OnlineFriend, OutgoingFriendRequest> t = new LinkedHashMap<OnlineFriend, OutgoingFriendRequest>();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void i(FriendRequest friendRequest) {
        if (friendRequest instanceof IncomingFriendRequest) {
            Map<OnlineFriend, IncomingFriendRequest> map = this.q;
            synchronized (map) {
                this.q.remove(friendRequest.x());
                this.k().M(friendRequest);
            }
        }
        if (friendRequest instanceof OutgoingFriendRequest) {
            Map<OnlineFriend, OutgoingFriendRequest> map = this.t;
            synchronized (map) {
                this.t.remove(friendRequest.x());
                this.k().M(friendRequest);
            }
        }
    }

    private void lambda$cancelOutgoingRequest$2(OutgoingFriendRequest outgoingFriendRequest, FriendRequestUpdateResponsePacket friendRequestUpdateResponsePacket) {
        if (friendRequestUpdateResponsePacket.l() != FriendRequestUpdateStatus.DECLINED) {
            this.O(outgoingFriendRequest);
        }
    }

    public void y(OnlineFriend onlineFriend) {
        OutgoingFriendRequest outgoingFriendRequest;
        IncomingFriendRequest incomingFriendRequest = this.q.get(onlineFriend);
        if (incomingFriendRequest != null) {
            this.i(incomingFriendRequest);
        }
        if ((outgoingFriendRequest = this.t.get(onlineFriend)) != null) {
            this.i(outgoingFriendRequest);
        }
    }

    public @UnmodifiableView Set<FriendRequest> B() {
        LinkedHashSet<FriendRequest> linkedHashSet = new LinkedHashSet<FriendRequest>();
        linkedHashSet.addAll(this.q.values());
        linkedHashSet.addAll(this.t.values());
        return linkedHashSet;
    }

    public boolean J(OnlineFriend onlineFriend) {
        return this.t.containsKey(onlineFriend);
    }

    public void g() {
        ArrayList<FriendRequest> arrayList = new ArrayList<FriendRequest>();
        arrayList.addAll(this.q.values());
        arrayList.addAll(this.t.values());
        for (FriendRequest friendRequest : arrayList) {
            this.i(friendRequest);
        }
    }

    private FriendRequestListPanel k() {
        return ClientSettings.getFrame(OnlineFriendsFrame.class).o$src$Lgg_vape_friend_ui_FriendRequestsPanel_$8g38ub().k$src$Lgg_vape_friend_ui_FriendRequestListPanel_$1poeaqd();
    }

    public void X(IncomingFriendRequest incomingFriendRequest) {
        this.i(incomingFriendRequest);
        ZeusConnectionManager.T().u().Y(incomingFriendRequest.C(), false, arg_0 -> this.lambda$declineIncomingRequest$1(incomingFriendRequest, arg_0));
    }


    public void w(long l) {
        FriendRequest friendRequest = null;
        for (IncomingFriendRequest friendRequest2 : this.q.values()) {
            if (friendRequest2.C() != l) continue;
            friendRequest = friendRequest2;
            break;
        }
        if (friendRequest != null) {
            this.i(friendRequest);
        } else {
            for (OutgoingFriendRequest outgoingFriendRequest : this.t.values()) {
                if (outgoingFriendRequest.C() != l) continue;
                friendRequest = outgoingFriendRequest;
                break;
            }
            if (friendRequest == null) {
                return;
            }
            this.i(friendRequest);
        }
    }

    public @UnmodifiableView Set<FriendRequest> I() {
        return new LinkedHashSet<FriendRequest>(this.q.values());
    }

    private void lambda$declineIncomingRequest$1(IncomingFriendRequest incomingFriendRequest, FriendRequestUpdateResponsePacket friendRequestUpdateResponsePacket) {
        if (friendRequestUpdateResponsePacket.l() != FriendRequestUpdateStatus.DECLINED) {
            this.O(incomingFriendRequest);
        }
    }

    public void Y(OutgoingFriendRequest outgoingFriendRequest) {
        this.i(outgoingFriendRequest);
        ZeusConnectionManager.T().u().Y(outgoingFriendRequest.C(), false, arg_0 -> this.lambda$cancelOutgoingRequest$2(outgoingFriendRequest, arg_0));
    }

    public void N(IncomingFriendRequest incomingFriendRequest) {
        this.i(incomingFriendRequest);
        ZeusConnectionManager.T().u().Y(incomingFriendRequest.C(), true, arg_0 -> this.lambda$acceptIncomingRequest$0(incomingFriendRequest, arg_0));
    }

    private void lambda$acceptIncomingRequest$0(IncomingFriendRequest incomingFriendRequest, FriendRequestUpdateResponsePacket friendRequestUpdateResponsePacket) {
        if (friendRequestUpdateResponsePacket.l() != FriendRequestUpdateStatus.ACCEPTED) {
            this.O(incomingFriendRequest);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void O(FriendRequest friendRequest) {
        if (friendRequest instanceof IncomingFriendRequest) {
            Map<OnlineFriend, IncomingFriendRequest> map = this.q;
            synchronized (map) {
                this.q.put(friendRequest.x(), (IncomingFriendRequest)friendRequest);
                this.k().z(friendRequest);
                if (OnlineConnectionManager.T.V().hasTimeElapsed(5000L)) {
                    Vape.INSTANCE.getNotificationManager().show("Friend request", "Incoming friend request from " + friendRequest.x().C(), NotificationType.FRIENDS_NEW_REQUEST, 4000L);
                }
            }
        }
        if (friendRequest instanceof OutgoingFriendRequest) {
            Map<OnlineFriend, OutgoingFriendRequest> map = this.t;
            synchronized (map) {
                this.t.put(friendRequest.x(), (OutgoingFriendRequest)friendRequest);
                this.k().z(friendRequest);
            }
        }
    }

    @Nullable
    public FriendRequest o(OnlineFriend onlineFriend) {
        IncomingFriendRequest incomingFriendRequest = this.q.get(onlineFriend);
        if (incomingFriendRequest != null) {
            return incomingFriendRequest;
        }
        OutgoingFriendRequest outgoingFriendRequest = this.t.get(onlineFriend);
        return outgoingFriendRequest;
    }
}

