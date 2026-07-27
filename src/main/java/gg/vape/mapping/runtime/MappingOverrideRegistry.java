package gg.vape.mapping.runtime;

import gg.vape.mapping.runtime.MappingOverrideSet;
import gg.vape.mapping.runtime.MappingOverrideSetV35;
import gg.vape.wrapper.impl.ForgeVersion;

public class MappingOverrideRegistry {
    private static MappingOverrideSet b;


    private static MappingOverrideSet y() {
        MappingOverrideSetV35 yv_02 = null;
        switch (ForgeVersion.c()) {
            case 35: {
                yv_02 = new MappingOverrideSetV35(35);
            }
        }
        return yv_02;
    }

    public static MappingOverrideSet j() {
        if (b == null) {
            b = MappingOverrideRegistry.y();
        }
        return b;
    }
}

