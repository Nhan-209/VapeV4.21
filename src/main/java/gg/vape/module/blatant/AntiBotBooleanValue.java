package gg.vape.module.blatant;

import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.value.Value;
import java.awt.Color;

public class AntiBotBooleanValue
extends Value<Integer, AntiBotBooleanValue> {
    private static final long RGB_MASK = 2840276557354762239L;

    public AntiBotBooleanValue(Object object, String string, Integer n) {
        super(object, string, n);
    }

    public static AntiBotBooleanValue y(Object object, String string, Integer n) {
        return new AntiBotBooleanValue(object, string, n);
    }

    @Override
    public String c() {
        Integer n = (Integer)this.K();
        if (n == null) {
            return "None";
        }
        return "#" + String.format("%06X", n);
    }

    @Override
    public void parse(String string) {
        if (string == null || string.isEmpty()) {
            this.o(this.P$src$Ljava_lang_Object_$qcpui1());
            return;
        }
        try {
            if (string.startsWith("0x") || string.startsWith("0X")) {
                this.o(Integer.parseInt(string.substring(2), 16));
            } else if (string.startsWith("#")) {
                this.o(Integer.parseInt(string.substring(1), 16));
            } else {
                this.o(Integer.parseInt(string));
            }
        }
        catch (NumberFormatException numberFormatException) {
            this.o(this.P$src$Ljava_lang_Object_$qcpui1());
        }
    }

    public Color getDisplayColor() {
        Integer n = (Integer)this.K();
        if (n == null) {
            return Color.WHITE;
        }
        return new Color(n);
    }

    public AntiBotBooleanValue P$src$Lgg_vape_module_blatant_AntiBotBooleanValue_$nq50fd() {
        AntiBotBooleanValue antiBotBooleanValue = new AntiBotBooleanValue((Object)null, this.getName(), (Integer)this.P$src$Ljava_lang_Object_$qcpui1());
        antiBotBooleanValue.o(this.K());
        return antiBotBooleanValue;
    }

    @Override
    public AntiBotBooleanValue getALimit() {
        return this.P$src$Lgg_vape_module_blatant_AntiBotBooleanValue_$nq50fd();
    }

    @Override
    public JsonObject H(boolean bl) {
        JsonObject jsonObject = this.toJson();
        Integer n = (Integer)this.K();
        if (n != null) {
            jsonObject.addProperty("value", "0x" + Integer.toHexString(n).toUpperCase());
        }
        return jsonObject;
    }

    public void V(Color color) {
        if (color == null) {
            this.o(null);
        } else {
            this.o(color.getRGB() & (int)RGB_MASK);
        }
    }

    private static NumberFormatException passThrough(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        if (this.W(jsonObject)) {
            String string = ConfigJsonUtils.P(jsonObject, "value");
            if (string != null) {
                this.parse(string);
            }
            this.Z();
            return true;
        }
        return false;
    }

    public static AntiBotBooleanValue i(Object object, String string, String string2, Integer n) {
        AntiBotBooleanValue antiBotBooleanValue = new AntiBotBooleanValue(object, string, n);
        antiBotBooleanValue.Z$src$Lgg_vape_value_Value_$16i62fx(string2);
        return antiBotBooleanValue;
    }
}
