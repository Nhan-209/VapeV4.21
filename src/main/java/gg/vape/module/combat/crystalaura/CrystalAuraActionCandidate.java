package gg.vape.module.combat.crystalaura;

import gg.vape.module.combat.crystalaura.CrystalAuraAction;
import gg.vape.module.combat.crystalaura.ExplosionType;
import gg.vape.utils.datas.DirectionalPosition;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.Vec3;

public class CrystalAuraActionCandidate {
    private Entity Q;
    private CrystalAuraAction Y;
    private final Vec3 U;
    private final ExplosionType v;
    public boolean H;
    private final double y;
    private final double p;
    public DirectionalPosition s;

    public void J(Entity entity) {
        this.Q = entity;
    }

    public double Y() {
        return this.y;
    }

    public ExplosionType n() {
        return this.v;
    }

    public CrystalAuraAction R() {
        return this.Y;
    }

    public CrystalAuraActionCandidate(ExplosionType ih_12, DirectionalPosition da_12, Vec3 vec3, double d, double d2) {
        this.v = ih_12;
        this.s = da_12;
        this.U = vec3;
        this.p = d;
        this.y = d2;
        this.Y = CrystalAuraAction.PLACING_CRYSTAL;
    }

    public Entity A() {
        return this.Q;
    }

    public void D(CrystalAuraAction do_12) {
        this.Y = do_12;
    }

    public DirectionalPosition a() {
        return this.s;
    }

    public double Q() {
        return this.p;
    }

    public Vec3 U() {
        return this.U;
    }
}

