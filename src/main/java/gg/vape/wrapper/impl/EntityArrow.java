package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEntityArrow;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.ForgeVersion;

public class EntityArrow
extends Entity {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public double o() {
        if (ForgeVersion.MC_1_21_0.d()) {
            return this.P$src$D$xovcst();
        }
        return MEntityArrow.g(EntityArrow.c.getMappings().qo, this.I);
    }

    public double L() {
        if (ForgeVersion.MC_1_21_0.d()) {
            return this.P$src$D$xovcst();
        }
        return MEntityArrow.d(EntityArrow.c.getMappings().qo, this.I);
    }

    public double P$src$D$xovcst() {
        return MEntityArrow.t(EntityArrow.c.getMappings().qo, this.I);
    }

    public EntityArrow(Object object) {
        super(object);
    }

    public double X$src$D$xt9pjp() {
        if (ForgeVersion.MC_1_21_0.d()) {
            return this.P$src$D$xovcst();
        }
        return MEntityArrow.D(EntityArrow.c.getMappings().qo, this.I);
    }
}

