package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MSEntityPacket;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.World;

public class SEntityPacket
extends Packet {
    public int x() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSEntityPacket.b(SEntityPacket.c.getMappingsMapperCompat().qC, this.I);
        }
        return MSEntityPacket.P(SEntityPacket.c.getMappingsMapperCompat().qC, this.I);
    }

    public int E() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSEntityPacket.t(SEntityPacket.c.getMappingsMapperCompat().qC, this.I);
        }
        return MSEntityPacket.m(SEntityPacket.c.getMappingsMapperCompat().qC, this.I);
    }

    public Entity V(World world) {
        return new Entity(MSEntityPacket.W(SEntityPacket.c.getMappingsMapperCompat().qC, this.I, world.getObject()));
    }


    public SEntityPacket(Object object) {
        super(object);
    }

    public int Y() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSEntityPacket.U(SEntityPacket.c.getMappingsMapperCompat().qC, this.I);
        }
        return MSEntityPacket.O(SEntityPacket.c.getMappingsMapperCompat().qC, this.I);
    }
}

