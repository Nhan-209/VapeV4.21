package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MCPacketPlayerDigging;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.PlayerDiggingAction;

public class CPacketPlayerDigging
extends Packet {
    public PlayerDiggingAction Z() {
        return new PlayerDiggingAction(MCPacketPlayerDigging.K(CPacketPlayerDigging.c.getMappingsMapperCompat().qA, this.I));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int I() {
        return MCPacketPlayerDigging.a(CPacketPlayerDigging.c.getMappingsMapperCompat().qA, this.I);
    }

    public boolean f() {
        boolean bl;
        boolean bl2 = ForgeVersion.MC_1_8_9.d() ? this.Z().equals(PlayerDiggingAction.s()) : (bl = this.I() == 5);
        return bl2;
    }

    public BlockPos K() {
        return new BlockPos(MCPacketPlayerDigging.o(CPacketPlayerDigging.c.getMappingsMapperCompat().qA, this.I));
    }

    public EnumFacing z() {
        return new EnumFacing(MCPacketPlayerDigging.B(CPacketPlayerDigging.c.getMappingsMapperCompat().qA, this.I));
    }

    public CPacketPlayerDigging(Object object) {
        super(object);
    }
}

