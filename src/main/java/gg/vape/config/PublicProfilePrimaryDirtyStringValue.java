package gg.vape.config;

import gg.vape.config.PublicProfileSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.StringValue;

public class PublicProfilePrimaryDirtyStringValue
extends StringValue {
    final PublicProfileSettings K;
    boolean f;

    @Override
    public void Z() {
        if (this.f) {
            return;
        }
        this.f = true;
    }

    public PublicProfilePrimaryDirtyStringValue(PublicProfileSettings publicProfileSettings, Object object, String string, String string2) {
        super(object, string, string2);
        this.K = publicProfileSettings;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
