package gg.vape.ui.click.frame.impl.profile;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;

class ProfileRenameInputComponent
extends TextInputComponentBase {
    final TruncatedTextComponent zj;
    final ProfileListEntryComponent zI;

    @Override
    public double x() {
        return 100.0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    ProfileRenameInputComponent(ProfileListEntryComponent profileListEntryComponent, String string, TruncatedTextComponent truncatedTextComponent) {
        super(string);
        this.zI = profileListEntryComponent;
        this.zj = truncatedTextComponent;
    }

    @Override
    public double C() {
        double d;
        if (this.V$src$Z$1xhop3l()) {
            this.getClass();
            d = 17.5 + 5.0;
        } else {
            d = 0.0;
        }
        return d;
    }

    @Override
    public void p() {
        this.Z(false);
        String string = this.i$src$Ljava_lang_String_$1n2xf3k().trim();
        if (string.isEmpty()) {
            return;
        }
        ProfileListEntryComponent.f(this.zI).h(string);
        this.zj.O(ProfileListEntryComponent.f(this.zI).n$src$Ljava_lang_String_$xqhelw());
        ProfilesSettingsFrame.Z$src$V$6cxyg1();
    }
}
