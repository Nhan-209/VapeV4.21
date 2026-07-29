package gg.vape.friend.ui;

import gg.vape.friend.OnlineFriend;
import gg.vape.friend.ui.OnlineChatPanel;
import gg.vape.friend.ui.OnlineChatSender;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class OnlineChatInputComponent
extends TextInputComponentBase {
    private static GuiComponent[] oz;
    private final OnlineChatSender oI;
    private final OnlineChatPanel ow;

    public static GuiComponent[] O$src$ALgg_vape_ui_click_component_GuiComponent_$1catzjv() {
        return oz;
    }

    static {
        OnlineChatInputComponent.k(new GuiComponent[5]);
    }

    @Override
    public double C() {
        return 23.0;
    }

    @Override
    public float getRightInset() {
        return super.getRightInset();
    }

    @Override
    public double x() {
        return 100.0;
    }


    @Override
    public void c() {
        super.c();
        GuiRenderPrimitives.L(this.G$src$D$1b2f02a(), this.n() + 1.0, this.A(), OnlineChatInputComponent.J.l);
        if (this.isFocused()) {
            this.setTextColor(OnlineChatInputComponent.J.A);
        } else {
            this.setTextColor(new Color(255, 255, 255, 102));
        }
    }

    public static void k(GuiComponent[] guiComponentArray) {
        oz = guiComponentArray;
    }

    private static void lambda$onEnter$0(OnlineFriend onlineFriend, String string) {
        OnlineFriendUiHelper.l(onlineFriend, null, string);
    }

    @Override
    public void setInputEnabled(boolean inputEnabled) {
        super.setInputEnabled(inputEnabled);
        this.setPlaceholderText(inputEnabled ? "Type message..." : "User is offline");
        this.getActionButton().setVisible(inputEnabled);
    }

    @Override
    public void submit() {
        String string = this.getText();
        if (string.isEmpty()) {
            return;
        }
        this.setText("");
        this.oI.W(string, OnlineChatInputComponent::lambda$onEnter$0);
    }

    @Override
    public float getLeftInset() {
        return 0.0f;
    }

    public OnlineChatInputComponent(OnlineChatPanel onlineChatPanel, OnlineChatSender onlineChatSender) {
        super("Type message...");
        this.getActionButton().setIconResource("newnext");
        this.setBackgroundVisible(false);
        this.ow = onlineChatPanel;
        this.oI = onlineChatSender;
        this.setMaxLength(255);
        this.setActionButtonColor(OnlineChatInputComponent.J.B);
    }
}
