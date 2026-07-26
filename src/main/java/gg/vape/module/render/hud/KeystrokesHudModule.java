package gg.vape.module.render.hud;

import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.event.impl.SyntheticAttackRequestEvent;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.impl.hud.KeystrokesHudFrame;
import gg.vape.unmap.ModeOption;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;

public class KeystrokesHudModule
extends HudModule {
    public final ModeOption H = new ModeOption("Keyboard");
    public final ModeOption K;
    public final ModeValue t;
    public final ModeValue J;
    public final BooleanValue c;
    public final ModeOption j;
    public final BooleanValue P;
    public final ModeOption O = new ModeOption("Arrow");

    @EventHandler
    public void U(EventKeyPress eventKeyPress) {
        KeystrokesHudFrame keystrokesHudFrame = ClientSettings.g(KeystrokesHudFrame.class);
        if (keystrokesHudFrame == null) {
            return;
        }
        keystrokesHudFrame.T(eventKeyPress);
    }

    public KeystrokesHudModule() {
        super("Keystrokes", HudModuleGroup.f, "keystrokes", KeystrokesHudFrame.class);
        this.J = ModeValue.create((Object)this, "Key Style", this.H, this.H, this.O);
        this.j = new ModeOption("Button");
        this.K = new ModeOption("Icon");
        this.t = ModeValue.create((Object)this, "Mouse Style", this.j, this.j, this.K);
        this.P = BooleanValue.create(this, "Show Spacebar", true);
        this.c = BooleanValue.create(this, "Show CPS Only", false);
        this.setSuffix("Shows when your movement keys or mouse buttons are pressed\nAs well as mouse clicks per second");
        this.addValue(this.J, this.t, this.P, this.c);
    }

    public void F(KeyBinding keyBinding, boolean bl) {
        KeystrokesHudFrame keystrokesHudFrame = ClientSettings.g(KeystrokesHudFrame.class);
        if (keystrokesHudFrame == null) {
            return;
        }
        keystrokesHudFrame.o(keyBinding, bl);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void r(SyntheticAttackRequestEvent syntheticAttackRequestEvent) {
        if (syntheticAttackRequestEvent.isCanceled() || Minecraft.currentScreen().isNotNull()) {
            return;
        }
        KeystrokesHudFrame keystrokesHudFrame = ClientSettings.g(KeystrokesHudFrame.class);
        if (keystrokesHudFrame == null) {
            return;
        }
        keystrokesHudFrame.WH();
    }

    @EventHandler
    public void g(EventMouseButton eventMouseButton) {
        KeystrokesHudFrame keystrokesHudFrame = ClientSettings.g(KeystrokesHudFrame.class);
        if (keystrokesHudFrame == null) {
            return;
        }
        keystrokesHudFrame.z(eventMouseButton);
        if (!eventMouseButton.getButtonState()) {
            return;
        }
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        keystrokesHudFrame.o$src$Lgg_vape_ui_click_frame_impl_hud_KeystrokesCpsCo$1bghhzn().O(eventMouseButton.getButton());
    }
}

