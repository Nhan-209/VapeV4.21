package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;

public class MBiomeRegistrySwitch
extends Mapping {
    private static int[] q;

    public MBiomeRegistrySwitch() {
        super(MappedClasses.FU);
    }

    public static void R(int[] nArray) {
        q = nArray;
    }

    public static int[] L() {
        return q;
    }

    static {
        if (MBiomeRegistrySwitch.L() == null) {
            MBiomeRegistrySwitch.R(new int[5]);
        }
    }
}

