package gg.vape.friend.ui;

import gg.vape.friend.OnlineFriend;
import gg.vape.friend.ui.OnlineChatPanel;
import gg.vape.friend.ui.OnlineChatSender;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    public float g() {
        return super.g();
    }

    @Override
    public double x() {
        return 100.0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void c() {
        super.c();
        GuiRenderPrimitives.L(this.G$src$D$1b2f02a(), this.n() + 1.0, this.A(), OnlineChatInputComponent.J.l);
        if (this.n$src$Z$1rnxqrn()) {
            this.I(OnlineChatInputComponent.J.A);
        } else {
            this.I(new Color(255, 255, 255, 102));
        }
    }

    public static void k(GuiComponent[] guiComponentArray) {
        oz = guiComponentArray;
    }

    private static void lambda$onEnter$0(OnlineFriend onlineFriend, String string) {
        OnlineFriendUiHelper.l(onlineFriend, null, string);
    }

    @Override
    public void j(boolean bl) {
        super.j(bl);
        this.b(bl ? "Type message..." : "User is offline");
        this.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(bl);
    }

    @Override
    public void p() {
        String string = this.i$src$Ljava_lang_String_$1n2xf3k();
        if (string.isEmpty()) {
            return;
        }
        this.k("");
        this.oI.W(string, OnlineChatInputComponent::lambda$onEnter$0);
    }

    @Override
    public float y() {
        return 0.0f;
    }

    public OnlineChatInputComponent(OnlineChatPanel onlineChatPanel, OnlineChatSender onlineChatSender) {
        super("Type message...");
        this.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().W("newnext");
        this.e(false);
        this.ow = onlineChatPanel;
        this.oI = onlineChatSender;
        this.n(255);
        this.t(OnlineChatInputComponent.J.B);
    }
}

