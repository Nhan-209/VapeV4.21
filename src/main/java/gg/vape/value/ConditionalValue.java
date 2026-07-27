package gg.vape.value;

import gg.vape.value.Value;
import gg.vape.value.ValueCondition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ConditionalValue<K, T extends ConditionalValue<K, T>>
extends Value<K, T> {
    private final List<Value> g = new ArrayList<Value>();
    private static String[] w;

    public ValueCondition a(K k) {
        return new ValueCondition().t(this, arg_0 -> ConditionalValue.lambda$condNotEquals$0(k, arg_0));
    }

    public static void M(String[] stringArray) {
        w = stringArray;
    }

    public abstract boolean P();

    public List<Value> q$src$Ljava_util_List_$fyau59() {
        return this.g;
    }

    public static String[] o$src$ALjava_lang_String_$17s942p() {
        return w;
    }

    public ConditionalValue(Object object, String string, K k) {
        super(object, string, k);
    }


    static {
        if (ConditionalValue.o$src$ALjava_lang_String_$17s942p() != null) {
            ConditionalValue.M(new String[3]);
        }
    }

    public T K(Value<?, ?> ... valueArray) {
        for (Value<?, ?> value : valueArray) {
            value.setParent(this);
            value.L(this);
        }
        this.g.addAll(Arrays.asList(valueArray));
        return (T)this;
    }

    public ValueCondition U(K k) {
        return new ValueCondition().z(this, k);
    }

    private static boolean lambda$condNotEquals$0(Object object, Object object2) {
        return !object2.equals(object);
    }

    public abstract boolean q(Value var1);

    public Value G() {
        for (Value value : this.q$src$Ljava_util_List_$fyau59()) {
            if (!value.equals(this.q$src$Ljava_util_List_$fyau59().get(this.q$src$Ljava_util_List_$fyau59().size() - 1))) continue;
            if (value instanceof ConditionalValue) {
                ConditionalValue conditionalValue = (ConditionalValue)value;
                if (!conditionalValue.q$src$Ljava_util_List_$fyau59().isEmpty() && conditionalValue.P()) {
                    Value value2 = (Value)conditionalValue.q$src$Ljava_util_List_$fyau59().get(conditionalValue.q$src$Ljava_util_List_$fyau59().size() - 1);
                    if (!conditionalValue.q$src$Ljava_util_List_$fyau59().isEmpty()) {
                        return conditionalValue.G();
                    }
                    return value2;
                }
                return value;
            }
            return value;
        }
        return null;
    }
}
