package gg.vape.value;

import gg.vape.value.Value;
import gg.vape.value.ValueAccessor;

public class DirectValueAccessor<K, T extends Value<K, T>>
extends ValueAccessor {
    public void e(Object object) {
        this.z().F(object);
    }

    public Object F() {
        return this.z().O$src$Ljava_lang_Object_$1o24gsq();
    }

    public DirectValueAccessor(Value<K, T> value) {
        super(value);
    }

    public Object a() {
        return this.z().l();
    }
}

