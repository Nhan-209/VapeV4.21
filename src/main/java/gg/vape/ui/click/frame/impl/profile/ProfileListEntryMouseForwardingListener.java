package gg.vape.ui.click.frame.impl.profile;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryComponent;
import java.awt.Point;

class ProfileListEntryMouseForwardingListener
implements GuiMouseListener {
    final ProfileListEntryComponent g;

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        this.g.g(new GuiMouseEvent(point.x, point.y, mouseClickButton == MouseClickButton.LEFT_CLICK ? MouseButton.LEFT_CLICK : (mouseClickButton == MouseClickButton.RIGHT_CLICK ? MouseButton.RIGHT_CLICK : (mouseClickButton == MouseClickButton.MIDDLE_CLICK ? MouseButton.MIDDLE_CLICK : MouseButton.UNKNOWN))));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    ProfileListEntryMouseForwardingListener(ProfileListEntryComponent profileListEntryComponent) {
        this.g = profileListEntryComponent;
    }
}

