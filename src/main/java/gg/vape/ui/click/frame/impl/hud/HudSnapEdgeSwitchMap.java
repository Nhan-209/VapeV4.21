package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.frame.impl.hud.HudSnapEdge;

public class HudSnapEdgeSwitchMap {
    public static final int[] t = new int[HudSnapEdge.values().length];

    HudSnapEdgeSwitchMap() {
    }

    static {
        try {
            HudSnapEdgeSwitchMap.t[HudSnapEdge.RIGHT.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            HudSnapEdgeSwitchMap.t[HudSnapEdge.LEFT.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            HudSnapEdgeSwitchMap.t[HudSnapEdge.VERTICAL_CENTRE.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            HudSnapEdgeSwitchMap.t[HudSnapEdge.BOTTOM.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            HudSnapEdgeSwitchMap.t[HudSnapEdge.TOP.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            HudSnapEdgeSwitchMap.t[HudSnapEdge.HORIZONTAL_CENTRE.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

