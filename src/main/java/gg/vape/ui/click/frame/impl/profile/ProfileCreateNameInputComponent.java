package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.TextInputComponentBase;

public class ProfileCreateNameInputComponent
extends TextInputComponentBase {
    private final Profile Lh;

    @Override
    public void p() {
        if (!this.u$src$Z$wt77ym()) {
            this.k("");
            return;
        }
        String string = this.i$src$Ljava_lang_String_$1n2xf3k();
        Profile profile = Vape.INSTANCE.getProfilesManager().G(string);
        if (profile != null) {
            return;
        }
        this.Lh.h(string);
        this.Lh.c(true);
        Vape.INSTANCE.getProfilesManager().m(this.Lh, true);
        Vape.INSTANCE.getProfilesManager().L(this.Lh);
        this.k("");
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public double p$src$D$187zcry() {
        return this.A() + 2.5;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ProfileCreateNameInputComponent(String string, Profile profile) {
        super(string);
        this.Lh = profile;
        this.d(false);
        this.n(48);
    }
}

