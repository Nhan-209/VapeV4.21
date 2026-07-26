package gg.vape.module.other;

import gg.vape.utils.MutableFloatTriple;
import gg.vape.utils.datas.DirectionalPosition;

public class RotationDebugState {
    private int y;
    private Boolean w;
    private final DirectionalPosition H;
    private final int t;
    private final MutableFloatTriple o;

    public static Boolean Z(RotationDebugState rotationDebugState, Boolean bl) {
        rotationDebugState.w = bl;
        return rotationDebugState.w;
    }

    private String g() {
        String string = "[C08 | %d], Block: %s, FacingVec: %s";
        if (this.y != -1) {
            string = string + ", Seq: " + this.y;
        }
        if (this.w != null) {
            string = string + ", Inside: " + this.w;
        }
        return String.format(string, this.t, this.H.toString(), this.o);
    }

    public RotationDebugState(int n, DirectionalPosition directionalPosition, MutableFloatTriple mutableFloatTriple) {
        this.t = n;
        this.H = directionalPosition;
        this.o = mutableFloatTriple;
    }

    public static String C(RotationDebugState rotationDebugState) {
        return rotationDebugState.g();
    }

    public static int R(RotationDebugState rotationDebugState, int n) {
        rotationDebugState.y = n;
        return rotationDebugState.y;
    }
}

