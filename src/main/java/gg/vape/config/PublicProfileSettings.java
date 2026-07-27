package gg.vape.config;

import gg.vape.config.Profile;
import gg.vape.config.PublicProfilePrimaryDirtyStringValue;
import gg.vape.config.PublicProfileSecondaryDirtyStringValue;
import gg.vape.config.PublicProfileSelectedProfileStringValue;
import gg.vape.config.PublicProfileSettingsBindSet;
import gg.vape.config.PublicProfileSettingsBindValue;
import gg.vape.ui.font.FontSelector;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.BindValue;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.StringValue;

public class PublicProfileSettings {
    public NumberValue h;
    public BooleanValue A;
    private Profile O;
    public ModeValue k;
    public BooleanValue H;
    public BindValue Y;
    public BooleanValue m;
    public BooleanValue o;
    public BooleanValue R;
    public final ModeOption P;
    public BooleanValue u;
    public final ModeOption M;
    public StringValue L;
    private static int s;
    public BooleanValue U = BooleanValue.create(this, "Save to Cloud", false, "Logs into an account in offline mode.");
    public ModeValue n;
    public StringValue S;
    public StringValue d;
    public BooleanValue Z;
    public BooleanValue r;

    private void lambda$new$0(BooleanValue booleanValue) {
        if (booleanValue.L().booleanValue()) {
            this.S.o("");
        }
    }

    public static int c() {
        int n = PublicProfileSettings.b();
        return 0;
    }

    public static void b(int n) {
        s = n;
    }

    public PublicProfileSettings() {
        this.U.B(this::lambda$new$0);
        this.S = new PublicProfilePrimaryDirtyStringValue(this, this, "alteningKey", "");
        this.u = BooleanValue.create(this, "Auto-load module states", true, "Automatically enable saved module states upon loading, and when selecting profiles");
        this.L = new PublicProfileSecondaryDirtyStringValue(this, this, "alts", "");
        this.d = (StringValue)((StringValue)new PublicProfileSelectedProfileStringValue(this, this, "selectedprofile_uuid", "").W(true)).I("selectedprofile");
        this.o = BooleanValue.create(this, "Auto save", false, "Automatically sync your settings");
        this.Z = BooleanValue.create(this, "Frame positions per profile", false, "Saves the positions of your GUI frames per profile");
        this.k = (ModeValue)ModeValue.create((Object)this, "Language", FontSelector.j, FontSelector.j, FontSelector.S, FontSelector.c, FontSelector.a, FontSelector.P).n(false);
        this.h = NumberValue.create(this, "Volume", "#", "%", 0.0, 50.0, 100.0);
        this.m = BooleanValue.create(this, "Muted", false, "Mutes all sounds");
        this.Y = new PublicProfileSettingsBindValue(this, this, "GUI Bind", new PublicProfileSettingsBindSet(this, 161));
        this.M = new ModeOption("Frames", 0.8);
        this.P = new ModeOption("Central", 0.8);
        this.n = ModeValue.create((Object)this, "GUI style", "Switch between the frames gui and the central gui", (ModeSelection)this.M, this.P, this.M);
        this.R = BooleanValue.create(this, "Notifications", true, "Shows notifications");
        this.r = BooleanValue.create(this, "Toggle alert", false, "Notifies you if a module is enabled/disabled.");
        this.A = BooleanValue.create(this, "Profile switch", false, "Notifies you when you switch profiles");
        this.H = BooleanValue.create(this, "Friend notifications", true, "Shows notifications related to friends");
    }

    public Profile t() {
        return this.O;
    }

    static {
        PublicProfileSettings.b(36);
    }

    public static Profile R(PublicProfileSettings publicProfileSettings, Profile profile) {
        publicProfileSettings.O = profile;
        return publicProfileSettings.O;
    }

    public static int b() {
        return s;
    }

}

