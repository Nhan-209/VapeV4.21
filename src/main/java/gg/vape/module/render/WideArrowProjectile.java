package gg.vape.module.render;

import gg.vape.module.render.proj.ArrowProjectile;

public class WideArrowProjectile extends ArrowProjectile {
    @Override
    public float getCollisionRadius() {
        return 0.5f;
    }
}
