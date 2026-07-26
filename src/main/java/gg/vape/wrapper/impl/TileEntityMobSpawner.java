package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MTileEntityMobSpawner;
import gg.vape.wrapper.impl.MobSpawnerBaseLogic;
import gg.vape.wrapper.impl.TileEntity;

public class TileEntityMobSpawner
extends TileEntity {
    public MobSpawnerBaseLogic j() {
        return new MobSpawnerBaseLogic(MTileEntityMobSpawner.H(TileEntityMobSpawner.c.getMappingsMapperCompat().DT, this.I));
    }

    public TileEntityMobSpawner(Object object) {
        super(object);
    }
}

