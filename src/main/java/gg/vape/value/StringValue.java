package gg.vape.value;

import gg.vape.value.Value;

public class StringValue
extends Value<String, StringValue> {
    private String A;

    public String toString() {
        return (String)this.K();
    }

    public StringValue(Object object, String string, String string2) {
        super(object, string, string2);
    }

    @Override
    public void parse(String string) {
        this.o(string);
    }

    public static StringValue Z(Object object, String string, String string2) {
        StringValue stringValue = new StringValue(object, string, string2);
        stringValue.M(string);
        return stringValue;
    }

    public StringValue Z$src$Lgg_vape_value_StringValue_$84j5z9() {
        return StringValue.Z(null, this.S$src$Ljava_lang_String_$1b9155(), (String)this.K());
    }

    @Override
    public StringValue getALimit() {
        return this.Z$src$Lgg_vape_value_StringValue_$84j5z9();
    }

    public String S$src$Ljava_lang_String_$1b9155() {
        return this.A;
    }

    private void M(String string) {
        this.A = string;
    }
}
