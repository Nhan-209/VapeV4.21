package gg.vape.module.blatant;

import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.SPacketDestroyEntities;

public class BacktrackPacketReplayState
extends Packet {
    public BacktrackPacketReplayState(Object object) {
        super(object);
    }

    public int[] n() {
        if (ForgeVersion.MC_1_21_4.d()) {
            SPacketDestroyEntities p5_02 = new SPacketDestroyEntities(BacktrackPacketReplayState.c.getMappingsMapperCompat().Rt.M(this.I));
            return p5_02.W();
        }
        return BacktrackPacketReplayState.c.getMappingsMapperCompat().Rt.Y(this.I);
    }
}

