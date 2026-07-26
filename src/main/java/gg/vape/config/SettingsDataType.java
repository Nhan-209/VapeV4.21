package gg.vape.config;

import gg.vape.config.GlobalSettingsPayload;
import gg.vape.config.OnlineSettingsPayload;
import gg.vape.config.SettingsPayload;
import gg.vape.config.SettingsScope;

public enum SettingsDataType {
    GLOBAL(SettingsScope.GLOBAL, GlobalSettingsPayload.class),
    ONLINE(SettingsScope.ONLINE, OnlineSettingsPayload.class);

    private final SettingsScope t;
    private final Class<? extends SettingsPayload> F;
    private static final /* synthetic */ SettingsDataType[] g;

    static {
        String[] stringArray = new String[]{"ONLINE", "GLOBAL"};


        g = new SettingsDataType[]{GLOBAL, ONLINE};
    }

    public Class<? extends SettingsPayload> b() {
        return this.F;
    }

    private SettingsDataType(SettingsScope settingsScope, Class<? extends SettingsPayload> clazz) {
        this.t = settingsScope;
        this.F = clazz;
    }

    public SettingsScope n() {
        return this.t;
    }

}

