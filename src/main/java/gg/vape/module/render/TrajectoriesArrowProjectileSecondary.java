package gg.vape.module.render;

import gg.vape.module.render.Trajectories;
import gg.vape.module.render.proj.ArrowProjectile;

public class TrajectoriesArrowProjectileSecondary
extends ArrowProjectile {
    final Trajectories K;

    @Override
    public float getCollisionRadius() {
        return 0.5f;
    }

    public TrajectoriesArrowProjectileSecondary(Trajectories trajectories) {
        this.K = trajectories;
    }
}

