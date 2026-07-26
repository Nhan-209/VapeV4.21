package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.LegacyPublicProfile;
import gg.vape.notification.NotificationType;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenSelectorComponent;
import gg.vape.value.FriendNameSuggestionProvider;

public class CompactPublicProfileFilterTokenSelectorComponent
extends PublicProfileFilterTokenSelectorComponent {
    public CompactPublicProfileFilterTokenSelectorComponent(String string, double d, double d2) {
        super(string, CompactPublicProfileFilterTokenSelectorComponent::lambda$new$0, d, d2, false, false);
        this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().E(new FriendNameSuggestionProvider());
        this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().C(0.0);
        this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().H(0.0f);
        this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().V(1.0f);
        this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().I(CompactPublicProfileFilterTokenSelectorComponent.J.A);
        this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().A(CompactPublicProfileFilterTokenSelectorComponent.J.Z);
        this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
        this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().d(false);
        this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().e(false);
        this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().o(this::lambda$new$1);
        ((LabeledTextInputComponent)this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz()).v$src$Lgg_vape_ui_click_component_IconButtonComponent_$9khxxe().Z(false);
    }

    private void lambda$new$1(char c, int n) {
        boolean bl;
        boolean bl2 = bl = c == ',' || n == 13;
        if (bl) {
            String string;
            String string2 = this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().i$src$Ljava_lang_String_$1n2xf3k().trim();
            if (c == ',') {
                string2 = string2.substring(0, string2.length() - 1);
                this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().k(string2);
                if (string2.isEmpty()) {
                    return;
                }
            }
            if ((string = LegacyPublicProfile.e(string2 = LegacyPublicProfile.S(string2))) != null) {
                Vape.INSTANCE.getNotificationManager().t("Tag Error", string, NotificationType.WARNING, 5000L);
                return;
            }
            if (this.i$src$Ljava_util_List_$1ydnhqa().size() >= 5) {
                Vape.INSTANCE.getNotificationManager().t("Tag Error", "You can only add up to 5 tags", NotificationType.WARNING, 5000L);
                return;
            }
            this.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().k("");
            this.V(new PublicProfileFilterTokenComponent(string2));
        }
    }

    private static void lambda$new$0() {
    }

    private static ObfuscatedRuntimeException c(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

