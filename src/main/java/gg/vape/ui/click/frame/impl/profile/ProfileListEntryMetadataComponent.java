package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.ClientSettings;
import gg.vape.config.Profile;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.utils.StringUtils;
import java.awt.Color;

public class ProfileListEntryMetadataComponent
extends TextButton {
    private Profile UN;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private String R$src$Ljava_lang_String_$1abziai() {
        if (this.UN == null) {
            return "No profile selected";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Click to re-enable the saved states of modules\n");
        stringBuilder.append("Alternatively, right click the profile to do this\n\n");
        StringBuilder stringBuilder2 = new StringBuilder();
        String string = "";
        for (Mod mod : this.UN.N$src$Ljava_util_List_$tynky5()) {
            string = string + mod.getName() + ", ";
            if (!(this.A$src$Lgg_vape_ui_font_SmoothFontRenderer_$jrhwp3().N(string) > 150.0)) continue;
            stringBuilder2.append(ClientSettings.F).append("f").append(string).append("\n");
            string = "";
        }
        if (!string.isEmpty()) {
            stringBuilder2.append(ClientSettings.F).append("f").append(string);
        }
        if (stringBuilder2.length() > 0) {
            stringBuilder.append("This will re-enable these modules:\n");
            stringBuilder.append(StringUtils.b(stringBuilder2.toString(), ", ", ""));
        } else {
            stringBuilder.append(ClientSettings.F).append("f").append("This profile has no modules saved");
        }
        return stringBuilder.toString();
    }

    private void j$src$V$115is38() {
        if (this.UN != null) {
            this.UN.r$src$V$1goqkjq();
        }
    }

    @Override
    public void H() {
        this.w(this.R$src$Ljava_lang_String_$1abziai());
        super.H();
    }

    public void v(Profile profile) {
        this.UN = profile;
    }

    public Profile B$src$Lgg_vape_config_Profile_$1eas4jl() {
        return this.UN;
    }

    public ProfileListEntryMetadataComponent(Profile profile) {
        super("Enable modules", 0.8, ProfileListEntryMetadataComponent.J.B, ProfileListEntryMetadataComponent.J.O, ProfileListEntryMetadataComponent.J.l, 2.0f, 1.0f);
        this.UN = profile;
        this.F(false);
        this.l(ProfileListEntryMetadataComponent.J.l, ProfileListEntryMetadataComponent.J.i);
        this.u(1.0f);
        this.R(ProfileListEntryMetadataComponent.J.l);
        this.T(ProfileListEntryMetadataComponent.J.i);
        this.T("newload");
        this.i(7.0f);
        this.c(true);
        this.y(0.8);
        this.G(Color.WHITE);
        this.p(true);
        this.r(this::j$src$V$115is38);
    }
}

