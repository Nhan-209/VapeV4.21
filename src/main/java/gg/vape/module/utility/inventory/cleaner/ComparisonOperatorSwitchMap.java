package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;

public class ComparisonOperatorSwitchMap {
    public static final int[] g = new int[ComparisonOperator.values().length];

    ComparisonOperatorSwitchMap() {
    }

    static {
        try {
            ComparisonOperatorSwitchMap.g[ComparisonOperator.EQUALS.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ComparisonOperatorSwitchMap.g[ComparisonOperator.NOT_EQUAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ComparisonOperatorSwitchMap.g[ComparisonOperator.GREATER_THAN.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ComparisonOperatorSwitchMap.g[ComparisonOperator.GREATHER_THAN_OR_EQUAL.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ComparisonOperatorSwitchMap.g[ComparisonOperator.LESS_THAN.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ComparisonOperatorSwitchMap.g[ComparisonOperator.LESS_THAN_OR_EQUAL.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

