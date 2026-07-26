package gg.vape.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.ConditionalValue;
import gg.vape.value.Value;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class ValueCondition {
    private static int[] W;
    private final Map<ConditionalValue, Predicate<Object>> o = new HashMap<ConditionalValue, Predicate<Object>>();

    public ValueCondition I(ConditionalValue conditionalValue) {
        this.o.put(conditionalValue, Boolean.FALSE::equals);
        return this;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private static boolean lambda$isNotEqual$0(Object object, Object object2) {
        return !object.equals(object2);
    }

    public Map<ConditionalValue, Predicate<Object>> U() {
        return this.o;
    }

    public static void l(int[] nArray) {
        W = nArray;
    }

    public ValueCondition A(ConditionalValue conditionalValue, Object object) {
        this.o.put(conditionalValue, arg_0 -> ValueCondition.lambda$isNotEqual$0(object, arg_0));
        return this;
    }

    public ValueCondition z(ConditionalValue conditionalValue, Object object) {
        this.o.put(conditionalValue, object::equals);
        return this;
    }

    static {
        if (ValueCondition.x$src$AI$m6fhru() != null) {
            ValueCondition.l(new int[1]);
        }
    }

    public void z(Value<?, ?> ... valueArray) {
        for (Map.Entry<ConditionalValue, Predicate<Object>> entry : this.o.entrySet()) {
            ConditionalValue conditionalValue = entry.getKey();
            conditionalValue.K(valueArray);
            for (Value<?, ?> value : valueArray) {
                value.U(this);
            }
        }
    }

    public ValueCondition k(ValueCondition valueCondition) {
        ValueCondition valueCondition2 = new ValueCondition();
        valueCondition2.o.putAll(this.o);
        valueCondition2.o.putAll(valueCondition.o);
        return valueCondition2;
    }

    public ValueCondition t(ConditionalValue conditionalValue, Predicate<Object> predicate) {
        this.o.put(conditionalValue, predicate);
        return this;
    }

    public static int[] x$src$AI$m6fhru() {
        return W;
    }

    public ValueCondition L(ConditionalValue conditionalValue) {
        this.o.put(conditionalValue, Boolean.TRUE::equals);
        return this;
    }

    public boolean x() {
        for (Map.Entry<ConditionalValue, Predicate<Object>> entry : this.o.entrySet()) {
            if (entry.getValue().test(entry.getKey().K())) continue;
            return false;
        }
        return true;
    }
}

