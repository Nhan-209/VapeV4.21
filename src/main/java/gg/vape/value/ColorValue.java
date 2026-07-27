package gg.vape.value;

import com.google.gson.JsonObject;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.MutableColor;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.value.Value;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class ColorValue
extends Value<Object[], ColorValue> {
    private final NumberValue M;
    private final NumberValue s;
    private boolean y = false;
    private final NumberValue Z;
    private final BooleanValue R;
    private final NumberValue m;
    public static final int K;
    private final List<Value<?, ?>> l;

    public float n() {
        return ((Double)this.Z.K()).floatValue() / (float)this.Z.Q$src$D$10o6gmd();
    }

    @Override
    public void S() {
        super.S();
        if (this.N$src$Z$1a793rp()) {
            this.C$src$Lgg_vape_value_NumberValue_$z6u28w().S();
            this.G().S();
            this.y().S();
            this.x$src$Lgg_vape_value_NumberValue_$1mjtff9().S();
            this.R.S();
        }
    }

    @Override
    public void parse(String string) {
    }

    private void B() {
        this.y(this.s);
        this.y(this.Z);
        this.y(this.m);
        this.y(this.M);
    }

    public void u(Object[] objectArray) {
        if (objectArray.length < this.l.size()) {
            return;
        }
        double d = (Double)objectArray[0];
        double d2 = (Double)objectArray[1];
        double d3 = (Double)objectArray[2];
        double d4 = (Double)objectArray[3];
        if (d > this.C$src$Lgg_vape_value_NumberValue_$z6u28w().Q$src$D$10o6gmd()) {
            d = (Double)this.C$src$Lgg_vape_value_NumberValue_$z6u28w().K();
        }
        if (d2 > this.y().Q$src$D$10o6gmd()) {
            d2 = (Double)this.y().K();
        }
        if (d3 > this.G().Q$src$D$10o6gmd()) {
            d3 = (Double)this.G().K();
        }
        if (d4 > this.x$src$Lgg_vape_value_NumberValue_$1mjtff9().Q$src$D$10o6gmd()) {
            d4 = (Double)this.x$src$Lgg_vape_value_NumberValue_$1mjtff9().K();
        }
        super.o(objectArray);
        this.s.A(d);
        this.Z.A(d2);
        this.m.A(d3);
        this.M.A(d4);
        this.R.o(Boolean.valueOf((Boolean)objectArray[4]));
        this.g$src$V$1akzyia();
    }

    @Override
    public boolean k() {
        return super.k() && this.s.k() && this.m.k() && this.Z.k() && this.M.k() && this.R.k();
    }

    public MutableColor q$src$Lgg_vape_utils_MutableColor_$1dowyd3() {
        this.B();
        int n = Color.HSBtoRGB(this.q(), this.r(), this.n());
        MutableColor mutableColor = new MutableColor(n);
        mutableColor.withAlpha(((Double)this.M.K()).intValue());
        return mutableColor;
    }

    public NumberValue G() {
        return this.m;
    }

    @Override
    public Color Q() {
        MutableColor mutableColor = this.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
        return new Color(mutableColor.getRed(), mutableColor.getGreen(), mutableColor.getBlue());
    }

    static {
        long l = -6336883246319533825L;
        K = (int)l;
    }

    public ColorValue Z$src$Lgg_vape_value_ColorValue_$1o84a3j() {
        ColorValue colorValue = new ColorValue(null, this.getName(), this.q() * 255.0f, this.n() * 255.0f, this.r() * 255.0f, this.x() * 255.0f);
        colorValue.A(this.P$src$Ljava_lang_Object_$qcpui1());
        colorValue.s.P((Double)this.s.P$src$Ljava_lang_Object_$qcpui1());
        colorValue.Z.P((Double)this.Z.P$src$Ljava_lang_Object_$qcpui1());
        colorValue.m.P((Double)this.m.P$src$Ljava_lang_Object_$qcpui1());
        colorValue.M.P((Double)this.M.P$src$Ljava_lang_Object_$qcpui1());
        colorValue.R.A(this.R.P$src$Ljava_lang_Object_$qcpui1());
        return colorValue;
    }

    @Override
    public ColorValue getALimit() {
        return this.Z$src$Lgg_vape_value_ColorValue_$1o84a3j();
    }

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        if (super.loadJson(jsonObject)) {
            if (jsonObject.has("hue")) {
                this.s.A(jsonObject.get("hue").getAsDouble() / 96.0 * 255.0);
            }
            if (jsonObject.has("hue2")) {
                this.s.A(jsonObject.get("hue2").getAsDouble());
            }
            if (jsonObject.has("saturation")) {
                this.m.A(jsonObject.get("saturation").getAsDouble() / 96.0 * 255.0);
            }
            if (jsonObject.has("saturation2")) {
                this.m.A(jsonObject.get("saturation2").getAsDouble());
            }
            if (jsonObject.has("brightness")) {
                this.Z.A(jsonObject.get("brightness").getAsDouble() / 96.0 * 255.0);
            }
            if (jsonObject.has("brightness2")) {
                this.Z.A(jsonObject.get("brightness2").getAsDouble());
            }
            if (jsonObject.has("alpha")) {
                this.M.A(jsonObject.get("alpha").getAsDouble() / 96.0 * 255.0);
            }
            if (jsonObject.has("alpha2")) {
                this.M.A(jsonObject.get("alpha2").getAsDouble());
            }
            if (jsonObject.has("rainbow")) {
                this.R.o(jsonObject.get("rainbow").getAsBoolean());
            }
            return true;
        }
        return false;
    }

    public boolean H(Color color) {
        return Math.abs(color.getRed() - this.q$src$Lgg_vape_utils_MutableColor_$1dowyd3().getRed()) < 2 && Math.abs(color.getGreen() - this.q$src$Lgg_vape_utils_MutableColor_$1dowyd3().getGreen()) < 2 && Math.abs(color.getBlue() - this.q$src$Lgg_vape_utils_MutableColor_$1dowyd3().getBlue()) < 2 && Math.abs(color.getAlpha() - this.q$src$Lgg_vape_utils_MutableColor_$1dowyd3().getAlpha()) < 2;
    }

    @Override
    public String c() {
        return "";
    }

    public NumberValue x$src$Lgg_vape_value_NumberValue_$1mjtff9() {
        return this.M;
    }

    public Object[] z() {
        Object[] objectArray = new Object[this.l.size()];
        for (int i = 0; i < this.l.size(); ++i) {
            objectArray[i] = this.l.get(i).K();
        }
        return objectArray;
    }

    public void F() {
        this.f(true);
        if (this.y && this.g()) {
            double d = (Double)this.s.K();
            this.Z(ColorUtil.Y(this.q(), this.n(), this.r()));
            this.s.A(d);
        }
        this.f(false);
    }

    @Override
    public void f(boolean bl) {
        super.f(bl);
        this.s.f(bl);
        this.Z.f(bl);
        this.m.f(bl);
        this.M.f(bl);
        this.R.f(bl);
    }

    public boolean g() {
        return this.R.L();
    }

    public void Z(Color color) {
        float[] fArray = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        this.s.A((double)fArray[0] * this.s.Q$src$D$10o6gmd());
        this.m.A((double)fArray[1] * this.m.Q$src$D$10o6gmd());
        this.Z.A((double)fArray[2] * this.Z.Q$src$D$10o6gmd());
        this.M.A(Double.valueOf(color.getAlpha()));
    }

    public int HSBtoRGB() {
        return Color.HSBtoRGB(this.q(), this.r(), this.n());
    }

    public float r() {
        return ((Double)this.m.K()).floatValue() / (float)this.m.Q$src$D$10o6gmd();
    }

    public Object[] N() {
        return this.z();
    }

    @Override
    public JsonObject H(boolean bl) {
        JsonObject jsonObject = this.toJson();
        if (!this.s.k()) {
            jsonObject.addProperty("hue2", (Number)this.s.K());
        }
        if (!this.m.k()) {
            jsonObject.addProperty("saturation2", (Number)this.m.K());
        }
        if (!this.Z.k()) {
            jsonObject.addProperty("brightness2", (Number)this.Z.K());
        }
        if (!this.M.k()) {
            jsonObject.addProperty("alpha2", (Number)this.M.K());
        }
        if (!this.R.k()) {
            jsonObject.addProperty("rainbow", this.R.L());
        }
        return jsonObject;
    }

    ColorValue(Object object, String string, double d, double d2, double d3, double d4) {
        super(object, string, new Object[0]);
        String string2 = "";
        this.s = NumberValue.create(this, string + " hue", string + " hue", "", 0.0, d, 255.0);
        this.Z = NumberValue.create(this, string + " brightness", string + " brightness", "", 0.0, d2, 255.0);
        this.m = NumberValue.create(this, string + " saturation", string + " saturation", "", 0.0, d3, 255.0);
        this.M = NumberValue.create(this, string + " alpha", string + " alpha", "", 0.0, d4, 255.0);
        this.R = BooleanValue.D(this, string + " rainbow", string + " rainbow", false);
        this.l = Arrays.asList(this.s, this.Z, this.m, this.M, this.R);
        Object[] objectArray = new Object[this.l.size()];
        for (int i = 0; i < this.l.size(); ++i) {
            objectArray[i] = this.l.get(i).P$src$Ljava_lang_Object_$qcpui1();
        }
        this.c = objectArray;
    }

    public NumberValue C$src$Lgg_vape_value_NumberValue_$z6u28w() {
        return this.s;
    }

    public void O() {
        this.f(true);
        double d = (Double)this.C$src$Lgg_vape_value_NumberValue_$z6u28w().K() + (Double)ClientSettings.fW.fD.K();
        if (d > this.C$src$Lgg_vape_value_NumberValue_$z6u28w().Q$src$D$10o6gmd() || d < this.C$src$Lgg_vape_value_NumberValue_$z6u28w().S$src$D$10pa1t3()) {
            d = this.C$src$Lgg_vape_value_NumberValue_$z6u28w().S$src$D$10pa1t3();
        }
        this.s.A(d);
        this.f(false);
    }

    public void Y(boolean bl) {
        this.R.o(bl);
        this.u(this.z());
    }

    public double Q$src$D$1rauh53() {
        return this.s.Q$src$D$10o6gmd();
    }

    public void o(boolean bl) {
        this.y = bl;
    }

    public NumberValue y() {
        return this.Z;
    }

    public static ColorValue b(Object object, String string, Color color, int n) {
        float[] fArray = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float f = fArray[0];
        float f2 = fArray[1];
        float f3 = fArray[2];
        return ColorValue.w(object, string, f * 255.0f, f3 * 255.0f, f2 * 255.0f, n);
    }

    public float x() {
        return ((Double)this.M.K()).floatValue() / (float)this.M.Q$src$D$10o6gmd();
    }

    public static ColorValue w(Object object, String string, double d, double d2, double d3, double d4) {
        return new ColorValue(object, string, d, d2, d3, d4);
    }

    public boolean I() {
        return this.y;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static ColorValue L(Object object, String string, Color color) {
        float[] fArray = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float f = fArray[0];
        float f2 = fArray[1];
        float f3 = fArray[2];
        int n = color.getAlpha();
        return ColorValue.w(object, string, f * 255.0f, f3 * 255.0f, f2 * 255.0f, n);
    }

    private void y(NumberValue numberValue) {
        if ((Double)numberValue.K() < numberValue.S$src$D$10pa1t3()) {
            numberValue.A(numberValue.S$src$D$10pa1t3());
        }
        if ((Double)numberValue.K() > numberValue.Q$src$D$10o6gmd()) {
            numberValue.A(numberValue.Q$src$D$10o6gmd());
        }
    }

    public void a() {
        if (this.l == null) {
            return;
        }
        super.o(this.z());
    }

    public double C$src$D$1r35cu1() {
        return this.s.S$src$D$10pa1t3();
    }

    public Object[] i() {
        return this.z();
    }

    public float q() {
        return ((Double)this.s.K()).floatValue() / (float)this.s.Q$src$D$10o6gmd();
    }
}
