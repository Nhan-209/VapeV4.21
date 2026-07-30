package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTileEntityMobSpawner;

public class TileEntityMobSpawner
extends TileEntity {
    public MobSpawnerBaseLogic j() {
        return new MobSpawnerBaseLogic(MTileEntityMobSpawner.H(TileEntityMobSpawner.vapeInstance.getMappingsMapperCompat().DT, this.I));
    }

    public TileEntityMobSpawner(Object object) {
        super(object);
    }
}

