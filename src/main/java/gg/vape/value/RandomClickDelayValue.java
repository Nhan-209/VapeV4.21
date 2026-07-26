package gg.vape.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.TimerUtil;
import gg.vape.value.RandomValue;
import java.util.Random;

public class RandomClickDelayValue
extends RandomValue {
    private int L;
    private final Random w;
    private final Random Z;
    private long R;
    private final TimerUtil f = new TimerUtil();
    private final Random D;
    private boolean M;
    private final Random v = new Random();
    private int r;

    public boolean R() {
        return this.f.hasTimeElapsed(this.F());
    }

    public static RandomClickDelayValue M(Object object, String string, String string2, String string3, double d, double d2, double d3, double d4) {
        return new RandomClickDelayValue(object, string, new double[]{d2, d3}, d, d4, string2, string3);
    }

    public RandomClickDelayValue(Object object, String string, double[] dArray, double d, double d2, String string2, String string3) {
        super(object, string, dArray, d, d2, string2, string3);
        this.w = new Random();
        this.D = new Random();
        this.Z = new Random();
    }

    public long F() {
        int n;
        int n2;
        int n3 = this.s$src$I$vi2lk8();
        int n4 = n3 - (n2 = this.y());
        int n5 = n = n4 <= 0 ? n2 : this.v.nextInt(n4) + n2 + 1;
        if (n == 0) {
            n = 1;
        }
        if (!this.M) {
            this.R = 1000 / n;
            if (this.Z.nextInt(4) == 1) {
                this.M = true;
                this.L = 1 + this.Z.nextInt(5);
            } else if (this.Z.nextInt(10) != 1 && this.Z.nextInt(10) == 1) {
                this.M = true;
                this.L = 5 + this.Z.nextInt(10);
            }
        }
        if (this.M) {
            ++this.r;
            if (this.r >= this.L) {
                this.r = 0;
                this.M = false;
            }
        }
        boolean bl = true;
        if (this.w.nextInt(48) % 10 == 0 && !this.M) {
            n2 = 25;
            n3 = 70;
            n4 = n3 - n2;
            this.R += (long)(this.D.nextInt(n4) + n2);
        }
        return this.R;
    }

    public static RandomClickDelayValue f(Object object, String string, String string2, String string3, String string4, double d, double d2, double d3, double d4, double d5) {
        return new RandomClickDelayValue(object, string, new double[]{d2, d3}, d, d4, string3, string4);
    }

    public static RandomClickDelayValue h(Object object, String string, String string2, String string3, double d, double d2, double d3, double d4, double d5, String string4) {
        RandomClickDelayValue randomClickDelayValue = new RandomClickDelayValue(object, string, new double[]{d2, d3}, d, d4, string2, string3);
        randomClickDelayValue.W(d5);
        randomClickDelayValue.Z$src$Lgg_vape_value_Value_$16i62fx(string4);
        return randomClickDelayValue;
    }

    public void s() {
        this.f.reset();
    }

    public static RandomClickDelayValue u(Object object, String string, String string2, String string3, double d, double d2, double d3, double d4, double d5) {
        RandomClickDelayValue randomClickDelayValue = new RandomClickDelayValue(object, string, new double[]{d2, d3}, d, d4, string2, string3);
        randomClickDelayValue.W(d5);
        return randomClickDelayValue;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean R(long l) {
        return this.f.hasTimeElapsed(l);
    }
}

