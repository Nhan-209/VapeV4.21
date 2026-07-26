package gg.vape.ui.click.frame;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.Frame;
import java.awt.Point;

public class FrameOutsideChildClickFilterMouseListener
implements GuiMouseListener {
    final Frame I;
    final GuiComponent s;
    final Frame G;

    public FrameOutsideChildClickFilterMouseListener(GuiComponent guiComponent, Frame frame, Frame frame2) {
        this.s = guiComponent;
        this.I = frame;
        this.G = frame2;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean Q(Point point) {
        if (this.I.Q().R(point) && !this.G.Q().R(point)) {
            return true;
        }
        return GuiMouseListener.super.Q(point);
    }
}

