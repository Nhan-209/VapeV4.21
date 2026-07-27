package gg.vape.ui.click.frame.impl.online;

import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.impl.online.OnlineAccountSettingsPageComponent;
import gg.vape.value.BooleanValue;

public class LinkedBooleanSettingsToggleComponent
extends BooleanToggleComponent {
    final BooleanValue[] eD;
    final OnlineAccountSettingsPageComponent eb;

    public LinkedBooleanSettingsToggleComponent(OnlineAccountSettingsPageComponent onlineAccountSettingsPageComponent, String string, double d, BooleanValue booleanValue, BooleanValue[] booleanValueArray) {
        super(string, d, booleanValue);
        this.eb = onlineAccountSettingsPageComponent;
        this.eD = booleanValueArray;
    }

    @Override
    public void N() {
        boolean bl = this.i$src$Z$1d37ezg();
        boolean bl2 = !bl;
        super.N();
        for (BooleanValue booleanValue : this.eD) {
            booleanValue.o(!bl2);
        }
    }


    @Override
    public void u() {
        super.u();
        int n = 0;
        for (BooleanValue booleanValue : this.eD) {
            if (!booleanValue.L().booleanValue()) continue;
            ++n;
        }
        if (n > 0 && this.i$src$Z$1d37ezg()) {
            this.h(false);
        } else if (n == 0 && !this.i$src$Z$1d37ezg()) {
            this.h(true);
        }
    }
}
