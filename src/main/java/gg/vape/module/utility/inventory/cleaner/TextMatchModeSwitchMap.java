package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.TextMatchMode;

public class TextMatchModeSwitchMap {
    public static final int[] g = new int[TextMatchMode.values().length];

    TextMatchModeSwitchMap() {
    }

    static {
        try {
            TextMatchModeSwitchMap.g[TextMatchMode.EQUALS.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TextMatchModeSwitchMap.g[TextMatchMode.DOES_NOT_EQUAL.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TextMatchModeSwitchMap.g[TextMatchMode.MATCH_REGEX.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TextMatchModeSwitchMap.g[TextMatchMode.DOES_NOT_MATCH_REGEX.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TextMatchModeSwitchMap.g[TextMatchMode.CONTAINS.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TextMatchModeSwitchMap.g[TextMatchMode.IS_IN.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TextMatchModeSwitchMap.g[TextMatchMode.DOES_NOT_CONTAIN.ordinal()] = 7;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            TextMatchModeSwitchMap.g[TextMatchMode.IS_NOT_IN.ordinal()] = 8;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

