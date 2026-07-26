package gg.vape.ui.click.frame.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.SessionSpoofFrame;

public class SessionSpoofApplyClickHandler
implements GuiClickListener {
    final SessionSpoofFrame P;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    @Override
    public void P() {
        if (SessionSpoofFrame.t(this.P) != null) {
            SessionSpoofFrame.M(this.P);
        }
    }

    public SessionSpoofApplyClickHandler(SessionSpoofFrame sw_02) {
        this.P = sw_02;
    }
}

