package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MSEntityPacket;

public class SEntityPacket
extends Packet {
    public int x() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSEntityPacket.b(SEntityPacket.vapeInstance.getMappingsMapperCompat().qC, this.I);
        }
        return MSEntityPacket.P(SEntityPacket.vapeInstance.getMappingsMapperCompat().qC, this.I);
    }

    public int E() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSEntityPacket.t(SEntityPacket.vapeInstance.getMappingsMapperCompat().qC, this.I);
        }
        return MSEntityPacket.m(SEntityPacket.vapeInstance.getMappingsMapperCompat().qC, this.I);
    }

    public Entity V(World world) {
        return new Entity(MSEntityPacket.W(SEntityPacket.vapeInstance.getMappingsMapperCompat().qC, this.I, world.getObject()));
    }


    public SEntityPacket(Object object) {
        super(object);
    }

    public int Y() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSEntityPacket.U(SEntityPacket.vapeInstance.getMappingsMapperCompat().qC, this.I);
        }
        return MSEntityPacket.O(SEntityPacket.vapeInstance.getMappingsMapperCompat().qC, this.I);
    }
}

