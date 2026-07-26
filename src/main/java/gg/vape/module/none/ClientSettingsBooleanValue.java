package gg.vape.module.none;

import gg.vape.module.none.ClientSettings;
import gg.vape.value.BooleanValue;

public class ClientSettingsBooleanValue
extends BooleanValue {
    final ClientSettings Z;

    public ClientSettingsBooleanValue(ClientSettings clientSettings, Object object, String string, boolean bl) {
        super(object, string, bl);
        this.Z = clientSettings;
    }

    public void G(Boolean bl) {
        super.o(bl);
    }
}
