package gg.vape.protocol.packet;

import gg.vape.friend.FriendModel;
import gg.vape.friend.FriendRequestModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.FriendsListPacket;
import gg.vape.protocol.packet.ZeusTrackedPacket;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class FriendsListResponsePacket
extends ZeusTrackedPacket<FriendsListPacket> {
    private final List<FriendModel> B = new ArrayList<FriendModel>();
    private final List<FriendRequestModel> Z = new ArrayList<FriendRequestModel>();
    private final List<FriendRequestModel> L = new ArrayList<FriendRequestModel>();

    @Override
    public void T(ZeusPacketBuffer gx_12) {
        gx_12.K(this.B.size());
        for (FriendModel object : this.B) {
            object.h(gx_12);
        }
        gx_12.K(this.Z.size());
        for (FriendRequestModel friendRequestModel : this.Z) {
            friendRequestModel.l(gx_12);
        }
        gx_12.K(this.L.size());
        for (FriendRequestModel friendRequestModel : this.L) {
            friendRequestModel.l(gx_12);
        }
    }

    public FriendsListResponsePacket(FriendsListPacket g4, List<FriendModel> list, List<FriendRequestModel> list2, List<FriendRequestModel> list3) {
        super(g4);
        this.B.addAll(list);
        this.Z.addAll(list2);
        this.L.addAll(list3);
    }

    @Override
    public void x(ZeusPacketBuffer gx_12) {
        int n;
        int n2;
        int n3 = gx_12.k();
        for (n2 = 0; n2 < n3; ++n2) {
            FriendModel lq_22 = new FriendModel(gx_12);
            this.B.add(lq_22);
        }
        n2 = gx_12.k();
        for (n = 0; n < n2; ++n) {
            this.Z.add(new FriendRequestModel(gx_12));
        }
        n = gx_12.k();
        for (int i = 0; i < n; ++i) {
            this.L.add(new FriendRequestModel(gx_12));
        }
    }

    public FriendsListResponsePacket() {
    }

    public List<FriendModel> s() {
        return this.B;
    }

    public List<FriendRequestModel> O() {
        return this.L;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public List<FriendRequestModel> W() {
        return this.Z;
    }
}

