package gg.vape.wrapper.impl;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.mappings.MBiome;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.BiomeRegistryName;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Holder;
import gg.vape.wrapper.impl.ResourceKey;
import java.util.Optional;

public class Biome
extends Wrapper {
    private static final String b = "Unknown";

    public String n() {
        if (ForgeVersion.MC_1_20_6.d()) {
            Optional optional;
            ResourceKey resourceKey;
            if (this.isInstance(MappedClasses.Vo) && !(resourceKey = new ResourceKey((optional = new Holder(this.I).f()).orElse(null))).isNull()) {
                return resourceKey.X().getResourcePath();
            }
            return b;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            BiomeRegistryName biomeRegistryName = new BiomeRegistryName(MBiome.D(Biome.c.getMappings().Rm, this.I));
            return biomeRegistryName.n();
        }
        return Biome.c.getMappings().Rm.q(this.I);
    }

    public Biome(Object object) {
        super(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

