package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;

public class MLanguageStateBridge
extends Mapping {
    private static int[] q;

    public static void k(int[] nArray) {
        q = nArray;
    }

    public MLanguageStateBridge() {
        super(MappedClasses.FN);
    }

    public static int[] T() {
        return q;
    }

    static {
        if (MLanguageStateBridge.T() == null) {
            MLanguageStateBridge.k(new int[3]);
        }
    }
}

