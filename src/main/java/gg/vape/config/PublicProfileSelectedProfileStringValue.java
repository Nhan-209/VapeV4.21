package gg.vape.config;

import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.config.PublicProfileSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.StringUtils;
import gg.vape.value.StringValue;
import java.util.UUID;

public class PublicProfileSelectedProfileStringValue
extends StringValue {
    final PublicProfileSettings s;
    private static final String d = " has no online uuid";

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public String D() {
        Profile profile = Vape.INSTANCE.getProfilesManager().M();
        UUID uUID = profile.P$src$Ljava_util_UUID_$kdhg08();
        if (uUID == null) {
            Vape.debugLog(profile.n$src$Ljava_lang_String_$xqhelw() + d);
            return "";
        }
        return uUID.toString();
    }

    public PublicProfileSelectedProfileStringValue(PublicProfileSettings publicProfileSettings, Object object, String string, String string2) {
        super(object, string, string2);
        this.s = publicProfileSettings;
    }

    public void Z(String string) {
        super.o(string);
        boolean bl = StringUtils.n(string);
        if (bl) {
            Profile profile = Vape.INSTANCE.getProfilesManager().H(UUID.fromString(string));
            if (profile != null) {
                PublicProfileSettings.R(this.s, profile);
            }
        } else {
            Profile profile = Vape.INSTANCE.getProfilesManager().G(string);
            if (profile != null) {
                PublicProfileSettings.R(this.s, profile);
            }
        }
    }
}
