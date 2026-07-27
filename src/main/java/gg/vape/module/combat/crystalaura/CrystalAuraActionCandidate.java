package gg.vape.module.combat.crystalaura;

import gg.vape.module.combat.crystalaura.CrystalAuraAction;
import gg.vape.module.combat.crystalaura.ExplosionType;
import gg.vape.utils.datas.DirectionalPosition;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.Vec3;

public class CrystalAuraActionCandidate {
    private Entity targetEntity;
    private CrystalAuraAction action;
    private final Vec3 position;
    private final ExplosionType explosionType;
    public boolean H;
    private final double secondaryValue;
    private final double damage;
    public DirectionalPosition s;

    public void J(Entity entity) {
        this.targetEntity = entity;
    }

    public double Y() {
        return this.secondaryValue;
    }

    public ExplosionType n() {
        return this.explosionType;
    }

    public CrystalAuraAction R() {
        return this.action;
    }

    public CrystalAuraActionCandidate(ExplosionType ih_12, DirectionalPosition da_12, Vec3 vec3, double d, double d2) {
        this.explosionType = ih_12;
        this.s = da_12;
        this.position = vec3;
        this.damage = d;
        this.secondaryValue = d2;
        this.action = CrystalAuraAction.PLACING_CRYSTAL;
    }

    public Entity A() {
        return this.targetEntity;
    }

    public void D(CrystalAuraAction do_12) {
        this.action = do_12;
    }

    public DirectionalPosition a() {
        return this.s;
    }

    public double Q() {
        return this.damage;
    }

    public Vec3 U() {
        return this.position;
    }
}
