package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.value.StringMapValueComponent;

class StringMapValueInputComponent
extends TextInputComponentBase {
    final StringMapValueComponent ko;

    @Override
    public double x() {
        return 110.0;
    }

    StringMapValueInputComponent(StringMapValueComponent stringMapValueComponent, String string) {
        super(string);
        this.ko = stringMapValueComponent;
    }

    @Override
    public void p() {
    }

    @Override
    public double C() {
        return 18.0;
    }
}
