package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;

public class MBiomeProvider
extends Mapping {
    private static String[] n;

    public static void a(String[] stringArray) {
        n = stringArray;
    }

    public MBiomeProvider() {
        super(MappedClasses.lq);
    }

    public static String[] T() {
        return n;
    }

    static {
        if (MBiomeProvider.T() != null) {
            MBiomeProvider.a(new String[4]);
        }
    }
}

