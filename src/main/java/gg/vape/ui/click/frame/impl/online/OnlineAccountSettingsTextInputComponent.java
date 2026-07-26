package gg.vape.ui.click.frame.impl.online;

import gg.vape.Vape;
import gg.vape.friend.ui.UsernameEditorPanel;
import gg.vape.notification.NotificationType;
import gg.vape.ui.click.component.input.DebouncedTextInputComponent;
import gg.vape.ui.click.frame.impl.online.OnlineAccountSettingsPageComponent;

public class OnlineAccountSettingsTextInputComponent
extends DebouncedTextInputComponent {
    final OnlineAccountSettingsPageComponent T2;

    private static void lambda$enterEvent$1(String string) {
        Vape.INSTANCE.getNotificationManager().t("Username Change", string, NotificationType.WARNING, 5000L);
    }

    public OnlineAccountSettingsTextInputComponent(OnlineAccountSettingsPageComponent onlineAccountSettingsPageComponent, String string, long l) {
        super(string, l);
        this.T2 = onlineAccountSettingsPageComponent;
    }

    @Override
    public float O() {
        return 0.0f;
    }

    @Override
    public double x() {
        return 82.0;
    }

    @Override
    public float y() {
        return 0.0f;
    }

    private void lambda$enterEvent$0(String string) {
        OnlineAccountSettingsPageComponent.H(this.T2);
        Vape.INSTANCE.getNotificationManager().t("Username Change", "Username changed to " + string, NotificationType.INFO, 5000L);
    }

    @Override
    public void j() {
        Vape.INSTANCE.getNotificationManager().t("Error", "You are on cooldown!", NotificationType.WARNING, 5000L);
    }

    @Override
    public void U$src$V$1pxrzte() {
        UsernameEditorPanel.o(this.T2.fe, this.i$src$Ljava_lang_String_$1n2xf3k(), this::lambda$enterEvent$0, OnlineAccountSettingsTextInputComponent::lambda$enterEvent$1);
    }

    @Override
    public double C() {
        return 12.0;
    }

    @Override
    public float g() {
        return super.g();
    }
}
