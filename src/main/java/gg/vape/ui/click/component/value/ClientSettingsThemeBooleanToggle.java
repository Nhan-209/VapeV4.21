package gg.vape.ui.click.component.value;

import gg.vape.config.ClientSettings;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.value.BooleanValue;
import java.awt.Color;

public final class ClientSettingsThemeBooleanToggle
extends BooleanToggleComponent {
    final ClientSettings d9;

    public ClientSettingsThemeBooleanToggle(BooleanValue booleanValue, ClientSettings clientSettings) {
        super(booleanValue);
        this.d9 = clientSettings;
    }

    @Override
    public boolean V$src$Z$1xhop3l() {
        return this.d9.B.L();
    }

    @Override
    public Color d() {
        return ClientSettingsThemeBooleanToggle.J.r;
    }
}
