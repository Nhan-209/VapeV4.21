package gg.vape.unmap;

import gg.vape.unmap.ImageParser$Format;

public class ImageParserFormatSwitchMap {
    public static final int[] B = new int[ImageParser$Format.values().length];

    ImageParserFormatSwitchMap() {
    }

    static {
        try {
            ImageParserFormatSwitchMap.B[ImageParser$Format.ABGR.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ImageParserFormatSwitchMap.B[ImageParser$Format.RGBA.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ImageParserFormatSwitchMap.B[ImageParser$Format.BGRA.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ImageParserFormatSwitchMap.B[ImageParser$Format.RGB.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ImageParserFormatSwitchMap.B[ImageParser$Format.LUMINANCE.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ImageParserFormatSwitchMap.B[ImageParser$Format.ALPHA.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ImageParserFormatSwitchMap.B[ImageParser$Format.WHITE.ordinal()] = 7;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            ImageParserFormatSwitchMap.B[ImageParser$Format.LUMINANCE_ALPHA.ordinal()] = 8;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

