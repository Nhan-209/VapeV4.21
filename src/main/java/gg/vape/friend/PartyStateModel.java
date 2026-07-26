package gg.vape.friend;

import gg.vape.friend.GroupUserModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.ArrayList;
import java.util.List;

public class PartyStateModel {
    private List<GroupUserModel> s = new ArrayList<GroupUserModel>();
    private GroupUserModel O;
    private List<GroupUserModel> n = new ArrayList<GroupUserModel>();

    public GroupUserModel g() {
        return this.O;
    }

    public void k(ZeusPacketBuffer gx_12) {
        this.O.f(gx_12);
        gx_12.i(this.s.size());
        for (GroupUserModel groupUserModel : this.s) {
            groupUserModel.f(gx_12);
        }
        gx_12.i(this.n.size());
        for (GroupUserModel groupUserModel : this.n) {
            groupUserModel.f(gx_12);
        }
    }

    public PartyStateModel(ZeusPacketBuffer gx_12) {
        int n;
        this.O = new GroupUserModel(gx_12);
        int n2 = gx_12.Y();
        for (n = 0; n < n2; ++n) {
            this.s.add(new GroupUserModel(gx_12));
        }
        n = gx_12.Y();
        for (int i = 0; i < n; ++i) {
            this.n.add(new GroupUserModel(gx_12));
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public List<GroupUserModel> h() {
        return this.s;
    }

    public PartyStateModel(GroupUserModel groupUserModel, List<GroupUserModel> list, List<GroupUserModel> list2) {
        this.O = groupUserModel;
        this.s = list;
        this.n = list2;
    }

    public List<GroupUserModel> V() {
        return this.n;
    }
}

