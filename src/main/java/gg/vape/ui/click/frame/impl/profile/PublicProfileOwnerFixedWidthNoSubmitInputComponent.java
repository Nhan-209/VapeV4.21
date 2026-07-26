package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOwnerDetailsPanel;

class PublicProfileOwnerFixedWidthNoSubmitInputComponent
extends TextInputComponentBase {
    final double qb;
    final PublicProfileOwnerDetailsPanel qV;

    PublicProfileOwnerFixedWidthNoSubmitInputComponent(PublicProfileOwnerDetailsPanel publicProfileOwnerDetailsPanel, String string, double d) {
        super(string);
        this.qV = publicProfileOwnerDetailsPanel;
        this.qb = d;
    }

    @Override
    public void p() {
    }

    @Override
    public double C() {
        return 16.0;
    }

    @Override
    public double x() {
        return this.qb;
    }
}
