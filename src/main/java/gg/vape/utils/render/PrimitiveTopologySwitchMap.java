package gg.vape.utils.render;

import gg.vape.utils.render.PrimitiveTopology;

public class PrimitiveTopologySwitchMap {
    public static final int[] b = new int[PrimitiveTopology.values().length];

    PrimitiveTopologySwitchMap() {
    }

    static {
        try {
            PrimitiveTopologySwitchMap.b[PrimitiveTopology.LINES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PrimitiveTopologySwitchMap.b[PrimitiveTopology.QUADS.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PrimitiveTopologySwitchMap.b[PrimitiveTopology.TRIANGLES.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            PrimitiveTopologySwitchMap.b[PrimitiveTopology.LINES_LOOP.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

