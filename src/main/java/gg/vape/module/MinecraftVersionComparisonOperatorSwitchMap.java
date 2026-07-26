package gg.vape.module;

import gg.vape.module.MinecraftVersionComparisonOperator;

class MinecraftVersionComparisonOperatorSwitchMap {
    static final int[] y = new int[MinecraftVersionComparisonOperator.values().length];

    MinecraftVersionComparisonOperatorSwitchMap() {
    }

    static {
        try {
            MinecraftVersionComparisonOperatorSwitchMap.y[MinecraftVersionComparisonOperator.EQUALS.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            MinecraftVersionComparisonOperatorSwitchMap.y[MinecraftVersionComparisonOperator.NOT_EQUAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            MinecraftVersionComparisonOperatorSwitchMap.y[MinecraftVersionComparisonOperator.GREATER_THAN.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            MinecraftVersionComparisonOperatorSwitchMap.y[MinecraftVersionComparisonOperator.GREATHER_THAN_OR_EQUAL.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            MinecraftVersionComparisonOperatorSwitchMap.y[MinecraftVersionComparisonOperator.LESS_THAN.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            MinecraftVersionComparisonOperatorSwitchMap.y[MinecraftVersionComparisonOperator.LESS_THAN_OR_EQUAL.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

