package gg.vape.value;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.value.Value;

public abstract class ValueAccessor<K, T extends Value<K, T>> {
    private final Value<K, T> S;
    private static GuiComponent[] O;

    public static void D(GuiComponent[] upArray) {
        O = upArray;
    }

    public abstract K F();

    public abstract void e(K var1);

    public abstract K a();

    public ValueAccessor(Value<K, T> value) {
        this.S = value;
    }

    public static GuiComponent[] k() {
        return O;
    }

    public Value<K, T> z() {
        return this.S;
    }

    static {
        if (ValueAccessor.k() != null) {
            ValueAccessor.D(new GuiComponent[3]);
        }
    }
}

