package gg.vape.value;

import gg.vape.value.ConditionalValue;
import gg.vape.value.ListValue;
import gg.vape.value.Value;
import gg.vape.value.ValueCondition;
import org.jetbrains.annotations.Nullable;

public class BooleanValue
extends ConditionalValue<Boolean, BooleanValue> {
    private static String[] o;
    @Nullable
    private ListValue R = null;
    private final String L;

    public BooleanValue(Object object, String string, boolean bl) {
        this(object, string, string, bl);
    }

    @Override
    public void parse(String string) {
        if (string.isEmpty()) {
            return;
        }
        this.o(Boolean.parseBoolean(string));
    }

    public static void W(String[] stringArray) {
        o = stringArray;
    }

    @Override
    public ListValue G() {
        return this.R;
    }

    @Override
    public boolean P() {
        return this.L();
    }

    public BooleanValue L$src$Lgg_vape_value_BooleanValue_$9w2bbh() {
        return BooleanValue.U(null, this.P$src$Ljava_lang_String_$1ijjhmj(), this.o(), this.L(), this.w$src$Ljava_lang_String_$ikqblg());
    }

    @Override
    public BooleanValue getALimit() {
        return this.L$src$Lgg_vape_value_BooleanValue_$9w2bbh();
    }

    public void l(ListValue listValue) {
        this.R = listValue;
    }

    public static BooleanValue D(Object object, String string, String string2, boolean bl) {
        return BooleanValue.U(object, string, string2, bl, null);
    }

    @Override
    public boolean q(Value value) {
        return this.e();
    }

    public BooleanValue(Object object, String string, String string2, boolean bl) {
        super(object, string, bl);
        this.L = string2;
    }

    public static BooleanValue create(Object object, String string, boolean bl, String string2) {
        return BooleanValue.U(object, string, string, bl, string2);
    }

    public Boolean L() {
        if (this.C$src$Z$1a17d8q()) {
            return false;
        }
        return (Boolean)super.K();
    }

    public boolean e() {
        return this.L();
    }

    public static BooleanValue create(Object object, String string, boolean bl) {
        return BooleanValue.create(object, string, bl, null);
    }

    public ValueCondition getEnabledCondition() {
        return new ValueCondition().L(this);
    }

    public static BooleanValue U(Object object, String string, String string2, boolean bl, String string3) {
        BooleanValue booleanValue = new BooleanValue(object, string, string2, bl);
        booleanValue.Z$src$Lgg_vape_value_Value_$16i62fx(string3);
        return booleanValue;
    }

    public ValueCondition C() {
        return new ValueCondition().I(this);
    }

    static {
        if (BooleanValue.H() == null) {
            BooleanValue.W(new String[3]);
        }
    }

    public boolean Z$src$Z$15e9hxx() {
        boolean bl;
        boolean bl2 = bl = this.L() == false;
        if (this.b(bl)) {
            this.o(bl);
            return true;
        }
        return false;
    }


    public void M() {
        this.o(this.L() == false);
    }

    public static String[] H() {
        return o;
    }

    public String o() {
        return this.L;
    }

    public Boolean java_lang_Boolean_L() {
        return this.L();
    }
}
