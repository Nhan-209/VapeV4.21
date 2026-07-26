package gg.vape.ui.click.component.value;

import gg.vape.config.PublicProfileSettings;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.value.BooleanValue;
import java.awt.Color;

public final class ClientSettingsSecondaryBooleanToggle
extends BooleanToggleComponent {
    final PublicProfileSettings Q_;

    public ClientSettingsSecondaryBooleanToggle(BooleanValue booleanValue, PublicProfileSettings publicProfileSettings) {
        super(booleanValue);
        this.Q_ = publicProfileSettings;
    }

    @Override
    public Color d() {
        return ClientSettingsSecondaryBooleanToggle.J.r;
    }

    @Override
    public boolean V$src$Z$1xhop3l() {
        return this.Q_.R.L();
    }
}
