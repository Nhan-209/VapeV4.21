package gg.vape.friend;

import gg.vape.friend.TargetType;

public class TargetEntry {
    private static int[] i;
    private final TargetType T;

    public TargetType a() {
        return this.T;
    }

    public static void E(int[] nArray) {
        i = nArray;
    }

    public static int[] s$src$AI$15w11fb() {
        return i;
    }

    public TargetEntry(TargetType targetType) {
        this.T = targetType;
    }

    static {
        if (TargetEntry.s$src$AI$15w11fb() == null) {
            TargetEntry.E(new int[4]);
        }
    }
}

