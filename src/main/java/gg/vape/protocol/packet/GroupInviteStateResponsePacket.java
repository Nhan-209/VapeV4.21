package gg.vape.protocol.packet;

import gg.vape.friend.PartyStateModel;
import gg.vape.protocol.ZeusPacketBuffer;
import gg.vape.protocol.packet.GroupInviteStatePacket;
import gg.vape.protocol.packet.GroupInviteStateStatus;
import gg.vape.protocol.packet.ZeusTrackedPacket;
import org.jetbrains.annotations.Nullable;

public class GroupInviteStateResponsePacket
extends ZeusTrackedPacket<GroupInviteStatePacket> {
    private GroupInviteStateStatus e;
    private PartyStateModel d;

    public GroupInviteStateResponsePacket() {
    }

    public GroupInviteStateStatus M() {
        return this.e;
    }

    public GroupInviteStateResponsePacket(GroupInviteStatePacket groupInviteStatePacket, GroupInviteStateStatus dr_22) {
        super(groupInviteStatePacket);
        this.e = dr_22;
    }

    @Override
    public void T(ZeusPacketBuffer gx_12) {
        gx_12.U(this.e);
        if (this.e == GroupInviteStateStatus.SUCCESSFULLY_ACCEPTED) {
            this.d.k(gx_12);
        }
    }


    @Nullable
    public PartyStateModel Y() {
        return this.d;
    }

    public GroupInviteStateResponsePacket(GroupInviteStatePacket groupInviteStatePacket, PartyStateModel partyStateModel) {
        this(groupInviteStatePacket, GroupInviteStateStatus.SUCCESSFULLY_ACCEPTED);
        this.d = partyStateModel;
    }

    @Override
    public void x(ZeusPacketBuffer gx_12) {
        this.e = gx_12.Y(GroupInviteStateStatus.class);
        if (this.e == GroupInviteStateStatus.SUCCESSFULLY_ACCEPTED) {
            this.d = new PartyStateModel(gx_12);
        }
    }
}

