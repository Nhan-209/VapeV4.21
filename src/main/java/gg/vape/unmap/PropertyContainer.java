package gg.vape.unmap;

import gg.vape.unmap.PropertyContainerBooleanKeyA;
import gg.vape.unmap.PropertyContainerBooleanKeyB;
import gg.vape.unmap.PropertyContainerBooleanKeyC;
import gg.vape.unmap.PropertyKey;
import java.util.HashMap;

public class PropertyContainer {
    public static PropertyKey<Boolean> W;
    public static PropertyKey<Boolean> B;
    private static String T;
    public static PropertyKey<Boolean> x;
    private HashMap<PropertyKey<?>, Object> n = new HashMap();

    public <T> void putProperty(PropertyKey<T> propertyKey, T t) {
        this.n.put(propertyKey, t);
    }

    public <T> T getProperty(PropertyKey<T> propertyKey) {
        if (!this.n.containsKey(propertyKey)) {
            return propertyKey.getDefaultValue();
        }
        return (T)this.n.get(propertyKey);
    }

    public static String getLabel() {
        return T;
    }

    public static void setLabel(String string) {
        T = string;
    }


    static {
        x = new PropertyContainerBooleanKeyA();
        B = new PropertyContainerBooleanKeyB();
        PropertyContainer.setLabel("m4RdLb");
        W = new PropertyContainerBooleanKeyC();
    }
}
