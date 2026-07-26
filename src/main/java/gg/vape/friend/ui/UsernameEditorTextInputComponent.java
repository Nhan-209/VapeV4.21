package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.friend.ui.UsernameEditorPanel;
import gg.vape.ui.click.component.input.DebouncedTextInputComponent;
import gg.vape.ui.notification.NotificationMessage;
import gg.vape.ui.notification.NotificationType;

public class UsernameEditorTextInputComponent
extends DebouncedTextInputComponent {
    final UsernameEditorPanel Dh;

    @Override
    public float O() {
        return 0.0f;
    }

    private void lambda$enterEvent$0(String string) {
        UsernameEditorPanel.t(this.Dh);
        OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.SUCCESS, "Name changed to " + string));
    }

    @Override
    public float g() {
        return super.g();
    }

    @Override
    public double C() {
        return 16.0;
    }

    @Override
    public void U$src$V$1pxrzte() {
        UsernameEditorPanel.o(UsernameEditorPanel.I(this.Dh), this.i$src$Ljava_lang_String_$1n2xf3k(), this::lambda$enterEvent$0, UsernameEditorTextInputComponent::lambda$enterEvent$1);
    }

    public UsernameEditorTextInputComponent(UsernameEditorPanel usernameEditorPanel, String string, long l) {
        super(string, l);
        this.Dh = usernameEditorPanel;
    }

    @Override
    public float y() {
        return 0.0f;
    }

    @Override
    public void j() {
        OnlineFriendUiHelper.w(NotificationType.WARNING, "You are on cooldown!");
    }

    @Override
    public double x() {
        return 82.0;
    }

    private static void lambda$enterEvent$1(String string) {
        OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.ERROR, string));
    }
}
