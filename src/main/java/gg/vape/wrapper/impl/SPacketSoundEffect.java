package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MSPacketSoundEffect;

public class SPacketSoundEffect
extends Packet {

    public SPacketSoundEffect(Object object) {
        super(object);
    }

    public String W() {
        if (ForgeVersion.MC_1_8_9.d()) {
            return new EnumParticleTypes(MSPacketSoundEffect.g(SPacketSoundEffect.vapeInstance.getMappings().Cs, this.I)).K();
        }
        return MSPacketSoundEffect.B(SPacketSoundEffect.vapeInstance.getMappings().Cs, this.I);
    }

    public double r() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSPacketSoundEffect.d(SPacketSoundEffect.vapeInstance.getMappings().Cs, this.I);
        }
        return MSPacketSoundEffect.S(SPacketSoundEffect.vapeInstance.getMappings().Cs, this.I);
    }

    public double O() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSPacketSoundEffect.u(SPacketSoundEffect.vapeInstance.getMappings().Cs, this.I);
        }
        return MSPacketSoundEffect.p(SPacketSoundEffect.vapeInstance.getMappings().Cs, this.I);
    }

    public double T() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSPacketSoundEffect.m(SPacketSoundEffect.vapeInstance.getMappings().Cs, this.I);
        }
        return MSPacketSoundEffect.m$src$F$1cm4rrw(SPacketSoundEffect.vapeInstance.getMappings().Cs, this.I);
    }
}

