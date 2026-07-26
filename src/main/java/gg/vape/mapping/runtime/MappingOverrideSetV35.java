package gg.vape.mapping.runtime;

import gg.vape.mapping.mappings.MMinecraft;
import gg.vape.mapping.mappings.MVertexFormat;
import gg.vape.mapping.runtime.MappingOverrideSet;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class MappingOverrideSetV35
extends MappingOverrideSet {
    private static boolean P;

    @Override
    protected void J() {
        this.g(MMinecraft.class, MVertexFormat.class);
    }

    public static void setFeatureFlag(boolean bl) {
        P = bl;
    }

    public MappingOverrideSetV35(int n) {
        super(n);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public static boolean isFeatureFlagEnabled() {
        return P;
    }

    public static boolean isFeatureFlagDisabled() {
        boolean bl = MappingOverrideSetV35.isFeatureFlagEnabled();
        return !bl;
    }

    static {
        if (MappingOverrideSetV35.isFeatureFlagDisabled()) {
            MappingOverrideSetV35.setFeatureFlag(true);
        }
    }
}

