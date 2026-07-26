package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.C03PacketPlayer;

public class CPacketPlayer
extends C03PacketPlayer {
    public static CPacketPlayer R(boolean bl, boolean bl2) {
        return new CPacketPlayer(CPacketPlayer.c.getMappingsMapperCompat().C_.W(bl, bl2));
    }

    private CPacketPlayer(Object object) {
        super(object);
    }
}

