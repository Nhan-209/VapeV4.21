package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.friend.ui.UsernameEditorPanel;
import gg.vape.ui.click.component.input.DebouncedTextInputComponent;
import gg.vape.ui.notification.NotificationMessage;
import gg.vape.ui.notification.NotificationType;

public class UsernameEditorTextInputComponent
extends DebouncedTextInputComponent {
    final UsernameEditorPanel editorPanel;

    @Override
    public float getVerticalInset() {
        return 0.0f;
    }

    private void lambda$enterEvent$0(String string) {
        UsernameEditorPanel.t(this.editorPanel);
        OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.SUCCESS, "Name changed to " + string));
    }

    @Override
    public float getRightInset() {
        return super.getRightInset();
    }

    @Override
    public double C() {
        return 16.0;
    }

    @Override
    public void handleSubmitReady() {
        UsernameEditorPanel.o(UsernameEditorPanel.I(this.editorPanel), this.getText(), this::lambda$enterEvent$0, UsernameEditorTextInputComponent::lambda$enterEvent$1);
    }

    public UsernameEditorTextInputComponent(UsernameEditorPanel editorPanel, String text, long cooldownMillis) {
        super(text, cooldownMillis);
        this.editorPanel = editorPanel;
    }

    @Override
    public float getLeftInset() {
        return 0.0f;
    }

    @Override
    public void handleSubmitCooldown() {
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
