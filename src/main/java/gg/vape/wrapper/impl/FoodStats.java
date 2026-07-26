package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MFoodStats;
import gg.vape.wrapper.Wrapper;

public class FoodStats
extends Wrapper {
    public int getFoodLevel() {
        return MFoodStats.g(FoodStats.c.getMappingsMapperCompat().DJ, this.I);
    }

    public FoodStats(Object object) {
        super(object);
    }
}

