package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MSPacketSoundEffect;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.EnumParticleTypes;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Packet;

public class SPacketSoundEffect
extends Packet {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SPacketSoundEffect(Object object) {
        super(object);
    }

    public String W() {
        if (ForgeVersion.MC_1_8_9.d()) {
            return new EnumParticleTypes(MSPacketSoundEffect.g(SPacketSoundEffect.c.getMappings().Cs, this.I)).K();
        }
        return MSPacketSoundEffect.B(SPacketSoundEffect.c.getMappings().Cs, this.I);
    }

    public double r() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSPacketSoundEffect.d(SPacketSoundEffect.c.getMappings().Cs, this.I);
        }
        return MSPacketSoundEffect.S(SPacketSoundEffect.c.getMappings().Cs, this.I);
    }

    public double O() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSPacketSoundEffect.u(SPacketSoundEffect.c.getMappings().Cs, this.I);
        }
        return MSPacketSoundEffect.p(SPacketSoundEffect.c.getMappings().Cs, this.I);
    }

    public double T() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return MSPacketSoundEffect.m(SPacketSoundEffect.c.getMappings().Cs, this.I);
        }
        return MSPacketSoundEffect.m$src$F$1cm4rrw(SPacketSoundEffect.c.getMappings().Cs, this.I);
    }
}

