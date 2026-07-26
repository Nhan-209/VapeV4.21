package gg.vape.ui.click.frame.impl.hud;

import com.google.common.collect.Sets;
import func.skidline.RectData;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.Set;

public enum HudSnapEdge {
    TOP,
    BOTTOM,
    VERTICAL_CENTRE,
    LEFT,
    RIGHT,
    HORIZONTAL_CENTRE;

    private static final Set<HudSnapEdge> x;
    private static final Set<HudSnapEdge> N;
    private static final HudSnapEdge[] q;

    public static Set<HudSnapEdge> a() {
        return x;
    }

    static {
        String[] stringArray = new String[]{"VERTICAL_CENTRE", "RIGHT", "HORIZONTAL_CENTRE", "LEFT", "TOP", "BOTTOM"};






        q = new HudSnapEdge[]{TOP, BOTTOM, VERTICAL_CENTRE, LEFT, RIGHT, HORIZONTAL_CENTRE};
        x = Sets.newHashSet(TOP, BOTTOM, HORIZONTAL_CENTRE);
        N = Sets.newHashSet(LEFT, RIGHT, VERTICAL_CENTRE);
    }

    public static Set<HudSnapEdge> L() {
        return N;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public double t(RectData rectData) {
        switch (this) {
            case LEFT: {
                return rectData.o();
            }
            case RIGHT: {
                return rectData.o() + (rectData.e() - 19.0);
            }
            case TOP: {
                return rectData.W();
            }
            case BOTTOM: {
                return rectData.W() + rectData.R() + 2.0;
            }
            case HORIZONTAL_CENTRE: {
                return rectData.W() + rectData.R() / 2.0;
            }
            case VERTICAL_CENTRE: {
                return rectData.o() + (rectData.e() - 20.0) / 2.0;
            }
        }
        return 0.0;
    }
}
