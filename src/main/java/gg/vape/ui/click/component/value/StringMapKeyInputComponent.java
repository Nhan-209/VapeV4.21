package gg.vape.ui.click.component.value;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.value.StringMapValueComponent;

class StringMapKeyInputComponent
extends TextInputComponentBase {
    final StringMapValueComponent v_;

    @Override
    public void p() {
        ClientSettings.fT = StringMapValueComponent.r(this.v_);
    }

    @Override
    public double x() {
        return 110.0;
    }

    @Override
    public double C() {
        return 18.0;
    }

    StringMapKeyInputComponent(StringMapValueComponent stringMapValueComponent, String string) {
        super(string);
        this.v_ = stringMapValueComponent;
    }
}
