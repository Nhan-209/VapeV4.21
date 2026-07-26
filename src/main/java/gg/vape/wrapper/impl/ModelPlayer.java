package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MModelPlayer;
import gg.vape.wrapper.Wrapper;

public class ModelPlayer
extends Wrapper {
    public boolean isFlying() {
        return MModelPlayer.v(ModelPlayer.c.getMappings().Ri, this.I);
    }

    public boolean N() {
        return MModelPlayer.u(ModelPlayer.c.getMappings().Ri, this.I);
    }

    public float m$src$F$1kykyr0() {
        return MModelPlayer.p(ModelPlayer.c.getMappings().Ri, this.I);
    }

    public boolean isCreativeMode() {
        return MModelPlayer.k(ModelPlayer.c.getMappings().Ri, this.I);
    }

    public float l() {
        return MModelPlayer.Y(ModelPlayer.c.getMappings().Ri, this.I);
    }

    public boolean H() {
        return MModelPlayer.D(ModelPlayer.c.getMappings().Ri, this.I);
    }

    public void c(boolean bl) {
        MModelPlayer.t(ModelPlayer.c.getMappings().Ri, this.I, bl);
    }

    public ModelPlayer(Object object) {
        super(object);
    }

    public boolean c() {
        return MModelPlayer.a(ModelPlayer.c.getMappings().Ri, this.I);
    }

    public static ModelPlayer Q() {
        return new ModelPlayer(MModelPlayer.C(ModelPlayer.c.getMappings().Ri));
    }

    public void G(float f) {
        MModelPlayer.H(ModelPlayer.c.getMappings().Ri, this.I, f);
    }

    public void n(boolean bl) {
        MModelPlayer.y(ModelPlayer.c.getMappings().Ri, this.I, bl);
    }

    public void R(float f) {
        MModelPlayer.E(ModelPlayer.c.getMappings().Ri, this.I, f);
    }
}

