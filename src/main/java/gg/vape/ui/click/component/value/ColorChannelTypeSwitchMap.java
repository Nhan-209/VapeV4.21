package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.value.ColorChannelType;

class ColorChannelTypeSwitchMap {
    static final int[] l = new int[ColorChannelType.values().length];

    ColorChannelTypeSwitchMap() {
    }

    static {
        try {
            ColorChannelTypeSwitchMap.l[ColorChannelType.BLOCK_CHILD.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ColorChannelTypeSwitchMap.l[ColorChannelType.RAINBOW.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ColorChannelTypeSwitchMap.l[ColorChannelType.SATURATION.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ColorChannelTypeSwitchMap.l[ColorChannelType.VIBRANCE.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ColorChannelTypeSwitchMap.l[ColorChannelType.OPACITY.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

