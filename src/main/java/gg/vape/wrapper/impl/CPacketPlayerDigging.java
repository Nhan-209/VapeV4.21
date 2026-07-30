package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketPlayerDigging;

public class CPacketPlayerDigging
extends Packet {
    public PlayerDiggingAction Z() {
        return new PlayerDiggingAction(MCPacketPlayerDigging.K(CPacketPlayerDigging.vapeInstance.getMappingsMapperCompat().qA, this.I));
    }


    public int I() {
        return MCPacketPlayerDigging.a(CPacketPlayerDigging.vapeInstance.getMappingsMapperCompat().qA, this.I);
    }

    public boolean f() {
        boolean bl;
        boolean bl2 = ForgeVersion.MC_1_8_9.d() ? this.Z().equals(PlayerDiggingAction.s()) : (bl = this.I() == 5);
        return bl2;
    }

    public BlockPos K() {
        return new BlockPos(MCPacketPlayerDigging.o(CPacketPlayerDigging.vapeInstance.getMappingsMapperCompat().qA, this.I));
    }

    public EnumFacing z() {
        return new EnumFacing(MCPacketPlayerDigging.B(CPacketPlayerDigging.vapeInstance.getMappingsMapperCompat().qA, this.I));
    }

    public CPacketPlayerDigging(Object object) {
        super(object);
    }
}

