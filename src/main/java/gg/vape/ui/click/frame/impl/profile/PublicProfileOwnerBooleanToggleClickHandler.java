package gg.vape.ui.click.frame.impl.profile;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOwnerDetailsPanel;
import java.awt.Point;

class PublicProfileOwnerBooleanToggleClickHandler
implements GuiMouseListener {
    final BooleanToggleComponent b;
    final PublicProfileOwnerDetailsPanel J;

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        this.b.Z(!this.b.V$src$Z$1xhop3l());
    }

    PublicProfileOwnerBooleanToggleClickHandler(PublicProfileOwnerDetailsPanel publicProfileOwnerDetailsPanel, BooleanToggleComponent booleanToggleComponent) {
        this.J = publicProfileOwnerDetailsPanel;
        this.b = booleanToggleComponent;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

