package gg.vape.config;

import gg.vape.config.GlobalSettingsPayload;
import gg.vape.config.SettingsPayload;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public enum SettingsScope {
    GLOBAL("global", GlobalSettingsPayload.class),
    ONLINE("online", null);

    private final String G;
    private static final /* synthetic */ SettingsScope[] F;
    @Nullable
    private final Class<? extends SettingsPayload> Z;
    public static final List<SettingsScope> VALUES;
    private static int[] A;

    public String g() {
        return this.G;
    }

    @Nullable
    public static SettingsScope U(String string) {
        for (SettingsScope settingsScope : VALUES) {
            if (!settingsScope.g().equalsIgnoreCase(string)) continue;
            return settingsScope;
        }
        return null;
    }

    public static int[] D() {
        return A;
    }

    private SettingsScope(String string2, Class<? extends SettingsPayload> clazz) {
        this.G = string2;
        this.Z = clazz;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Nullable
    public Class<? extends SettingsPayload> P() {
        return this.Z;
    }

    public static void o(int[] nArray) {
        A = nArray;
    }

    static {
        if (SettingsScope.D() != null) {
            SettingsScope.o(new int[5]);
        }
        String[] stringArray = new String[]{"ONLINE", "global", "online", "GLOBAL"};


        F = new SettingsScope[]{GLOBAL, ONLINE};
        VALUES = Arrays.asList(SettingsScope.values());
    }
}

