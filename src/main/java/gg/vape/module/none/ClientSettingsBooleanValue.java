package gg.vape.module.none;

import gg.vape.module.none.ClientSettings;
import gg.vape.value.BooleanValue;

public class ClientSettingsBooleanValue
extends BooleanValue {
    final ClientSettings Z;

    public ClientSettingsBooleanValue(ClientSettings clientSettings, Object owner, String name, boolean defaultValue) {
        super(owner, name, defaultValue);
        this.Z = clientSettings;
    }

    public void G(Boolean value) {
        super.o(value);
    }
}
