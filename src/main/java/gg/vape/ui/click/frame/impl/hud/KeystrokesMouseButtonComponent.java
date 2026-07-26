package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.frame.impl.hud.KeystrokesHudFrame;
import gg.vape.ui.click.frame.impl.hud.KeystrokesKeyComponent;
import gg.vape.wrapper.impl.KeyBinding;

class KeystrokesMouseButtonComponent
extends KeystrokesKeyComponent {
    final KeystrokesHudFrame N;

    KeystrokesMouseButtonComponent(KeystrokesHudFrame keystrokesHudFrame, KeyBinding keyBinding) {
        super(keystrokesHudFrame, keyBinding);
        this.N = keystrokesHudFrame;
    }
}

