package gg.vape.unmap;

import gg.vape.unmap.PropertyKey;

public final class PropertyContainerBooleanKeyA
extends PropertyKey<Boolean> {
    @Override
    public Boolean getDefaultValue() {
        return this.a();
    }

    Boolean a() {
        return false;
    }
}
