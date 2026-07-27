package gg.vape.module.render;

import gg.vape.module.render.Trajectories;
import gg.vape.module.render.proj.ArrowProjectile;

public class TrajectoriesArrowProjectilePrimary
extends ArrowProjectile {
    final Trajectories o;

    @Override
    public float getCollisionRadius() {
        return 0.5f;
    }

    public TrajectoriesArrowProjectilePrimary(Trajectories trajectories) {
        this.o = trajectories;
    }
}

