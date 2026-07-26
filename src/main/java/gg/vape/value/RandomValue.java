package gg.vape.value;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.StringUtils;
import gg.vape.value.Value;
import java.text.DecimalFormat;
import java.util.Random;

public class RandomValue
extends Value<double[], RandomValue> {
    private double G = 999999.0;
    private double V = 0.01;
    private final Random J = new Random();
    private final double p;
    private final String O;
    private final DecimalFormat Q;
    private final DecimalFormat A;
    private final String g;
    private final double d;

    public double O$src$D$uya02x() {
        return this.d;
    }

    public void a(double d) {
        super.o(new double[]{MathUtil.roundToIncrement(d, this.Q$src$D$uzdl9n()), this.M()});
        this.g$src$V$1akzyia();
    }

    public void W(double d) {
        this.V = d;
    }

    public double B() {
        double d = this.q$src$D$vgz097();
        double d2 = this.M();
        return d + (d2 - d) * this.J.nextDouble();
    }

    public static RandomValue C(Object object, String string, String string2, String string3, double d, double d2, double d3, double d4, double d5) {
        RandomValue randomValue = new RandomValue(object, string, new double[]{d2, d3}, d, d4, string2, string3);
        randomValue.V = d5;
        return randomValue;
    }

    public String E() {
        return this.A.format(this.M());
    }

    public int y() {
        return (int)this.M();
    }

    public void q(double d) {
        if (d < this.q$src$D$vgz097()) {
            d = this.q$src$D$vgz097();
        } else if (d > this.p) {
            d = this.p;
        } else if (d < this.d) {
            d = this.d;
        }
        super.o(new double[]{this.q$src$D$vgz097(), MathUtil.roundToIncrement(d, this.Q$src$D$uzdl9n())});
        this.g$src$V$1akzyia();
    }

    private DecimalFormat p() {
        DecimalFormat decimalFormat;
        try {
            decimalFormat = new DecimalFormat(this.O);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
            decimalFormat = new DecimalFormat("#.##");
        }
        decimalFormat.setMinimumIntegerDigits(1);
        return decimalFormat;
    }

    public static RandomValue K(Object object, String string, String string2, String string3, String string4, double d, double d2, double d3, double d4, double d5) {
        RandomValue randomValue = new RandomValue(object, string, new double[]{d2, d3}, d, d4, string3, string4);
        randomValue.V = d5;
        return randomValue;
    }

    public static RandomValue create(Object object, String string, String string2, String string3, double d, double d2, double d3, double d4) {
        return new RandomValue(object, string, new double[]{d2, d3}, d, d4, string2, string3);
    }

    public void u(double d) {
        if (d > this.M()) {
            d = this.M();
        } else if (d > this.p) {
            d = this.p;
        } else if (d < this.d) {
            d = this.d;
        }
        super.o(new double[]{MathUtil.roundToIncrement(d, this.Q$src$D$uzdl9n()), this.M()});
        this.g$src$V$1akzyia();
    }

    public static RandomValue G(Object object, String string, String string2, String string3, double d, double d2, double d3, double d4, double d5, String string4) {
        RandomValue randomValue = new RandomValue(object, string, new double[]{d2, d3}, d, d4, string2, string3);
        randomValue.V = d5;
        return (RandomValue)randomValue.Z$src$Lgg_vape_value_Value_$16i62fx(string4);
    }

    public String b() {
        return this.g;
    }

    public String y$src$Ljava_lang_String_$1nuhg7p() {
        return this.A.format(this.q$src$D$vgz097());
    }

    public double q$src$D$vgz097() {
        return ((double[])this.K())[0];
    }

    public RandomValue V(int n) {
        this.Q.setMaximumFractionDigits(n);
        return this;
    }

    @Override
    public JsonObject H(boolean bl) {
        JsonObject jsonObject = this.toJson();
        if (this.q$src$D$vgz097() != ((double[])this.P$src$Ljava_lang_Object_$qcpui1())[0]) {
            jsonObject.addProperty("minimum", (Number)this.q$src$D$vgz097());
        }
        if (this.M() != ((double[])this.P$src$Ljava_lang_Object_$qcpui1())[1]) {
            jsonObject.addProperty("maximum", (Number)this.M());
        }
        return jsonObject;
    }

    public void Q(double d) {
        super.o(new double[]{this.q$src$D$vgz097(), MathUtil.roundToIncrement(d, this.Q$src$D$uzdl9n())});
        this.g$src$V$1akzyia();
    }

    private static ObfuscatedRuntimeException c(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int s$src$I$vi2lk8() {
        return (int)this.q$src$D$vgz097();
    }

    public void c(double d) {
        this.G = d;
    }

    public DecimalFormat y$src$Ljava_text_DecimalFormat_$bdq2sj() {
        return this.A;
    }

    @Override
    public String c() {
        String string = this.Q.format(this.q$src$D$vgz097());
        String string2 = this.Q.format(this.M());
        return string + "-" + string2 + this.g.trim();
    }

    public double M() {
        return ((double[])this.K())[1];
    }

    public RandomValue(Object object, String string, double[] dArray, double d, double d2, String string2, String string3) {
        super(object, string, dArray);
        this.d = d;
        this.p = d2;
        this.O = StringUtils.p(string2);
        if (!string3.isEmpty()) {
            string3 = " " + string3;
        }
        this.g = string3;
        this.A = this.p();
        this.Q = this.p();
    }

    public double Q$src$D$uzdl9n() {
        return this.V;
    }

    public void j(double[] dArray) {
        super.o(dArray);
        if (dArray[0] > this.G) {
            dArray[0] = this.G;
        } else if (dArray[0] < -this.G) {
            dArray[0] = -this.G;
        }
        if (dArray[1] > this.G) {
            dArray[1] = this.G;
        } else if (dArray[1] < -this.G) {
            dArray[1] = -this.G;
        }
        this.a(dArray[0]);
        this.Q(dArray[1]);
        this.g$src$V$1akzyia();
    }

    public RandomValue t() {
        return new RandomValue(null, this.getName(), (double[])this.P$src$Ljava_lang_Object_$qcpui1(), this.O$src$D$uya02x(), this.g$src$D$vbh2bl(), this.O, this.g);
    }

    @Override
    public RandomValue getALimit() {
        return this.t();
    }

    public double g$src$D$vbh2bl() {
        return this.p;
    }

    @Override
    public void parse(String string) {
    }

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        if (jsonObject.get("id").getAsString().equalsIgnoreCase(this.P$src$Ljava_lang_String_$1ijjhmj())) {
            Double d = ConfigJsonUtils.p(jsonObject, "minimum");
            Double d2 = ConfigJsonUtils.p(jsonObject, "maximum");
            if (d == null && d2 == null) {
                return false;
            }
            this.j(new double[]{d != null ? d : ((double[])this.c)[0], d2 != null ? d2 : ((double[])this.c)[1]});
            return true;
        }
        return false;
    }

    public int int_s() {
        return this.s$src$I$vi2lk8();
    }

    public int int_y() {
        return this.y();
    }
}
