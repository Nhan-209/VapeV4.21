package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.frame.impl.hud.KeystrokesHudFrame;
import gg.vape.wrapper.impl.KeyBinding;
import java.awt.Color;

class KeystrokesKeyComponent {
    boolean k;
    final KeystrokesHudFrame S;
    public ColorAnimation R;
    KeyBinding p;
    public ColorAnimation h;
    boolean C;

    KeystrokesKeyComponent(KeystrokesHudFrame keystrokesHudFrame, KeyBinding keyBinding) {
        this.S = keystrokesHudFrame;
        this.h = new ColorAnimation(0.05, new Color(20, 20, 20, 180), new Color(255, 255, 255, 255));
        this.R = new ColorAnimation(0.05, new Color(255, 255, 255, 255), new Color(20, 20, 20, 255));
        this.k = true;
        this.p = keyBinding;
    }

    public ColorAnimation j() {
        return this.h;
    }

    public void a() {
        this.h.J();
        this.R.J();
        this.k = false;
    }

    public ColorAnimation D() {
        return this.R;
    }

    public void c() {
    }

    public void t() {
        this.h.Z();
        this.R.Z();
        this.k = true;
    }

    public boolean R() {
        return this.k;
    }
}

