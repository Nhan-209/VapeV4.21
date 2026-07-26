package gg.vape.value;

import gg.vape.value.Value;
import gg.vape.value.ValueAccessor;
import gg.vape.value.ValueSnapshot;

public class SnapshotValueAccessor<K, T extends Value<K, T>>
extends ValueAccessor {
    private final ValueSnapshot<T, K> l;

    public Object a() {
        return this.l.J();
    }

    public Object F() {
        return this.l.J();
    }

    public SnapshotValueAccessor(ValueSnapshot<T, K> valueSnapshot, Value<K, T> value) {
        super(value);
        this.l = valueSnapshot;
    }

    public void e(Object object) {
        this.l.s(object);
    }
}

