package gg.vape.click;

import java.util.SplittableRandom;

public class AutoClickerTimingState {
    private double y;
    private long q = 0L;
    private final long c;
    private static final double R = 0.015;
    private double b;
    private boolean t = false;
    private static final double E = 0.24;
    private static final double Z = 0.25;
    private static final long l = 30000L;
    private static final long Q = 3200L;
    private static final double h = 0.4;
    private double j;
    private static final double J = 0.45;
    private static final int r;
    private static final long P = 90000L;
    private double L = 0.0;
    private double V = 0.06;
    private final SplittableRandom M;
    private double i;
    private double m;
    private long n;
    private static final int I;
    private static final double U = 0.05;
    private static final long B = 1200L;
    private double g = 0.0;
    private int W = 0;
    private static final long u = 1200L;
    private int z = 0;
    private static final double d = 0.75;
    private static final int v;
    private long w = 0L;
    private long D;
    private static final double x = 0.004;
    private static final int A;
    private static final int T;


    private void h(long l) {
        long l2 = this.P(l, 30000L, 90000L);
        this.D = l + l2;
    }

    private static double x(double d, double d2) {
        return (d + d2) * 0.5;
    }

    public AutoClickerTimingState(long l) {
        long l2 = AutoClickerTimingState.O(l ^ 0x9E3779B97F4A7C15L);
        this.M = new SplittableRandom(l2);
        this.c = AutoClickerTimingState.O(l2 + -3335678366873096957L);
    }

    private void z(long l) {
        long l2 = this.P(l, 1200L, 3200L);
        this.n = l + l2;
    }

    private double P() {
        double d = 0.0;
        for (int i = 0; i < 6; ++i) {
            d += this.M.nextDouble();
        }
        return (d - 3.0) * 1.22474487139;
    }

    private void M(long l) {
        this.z(l);
        this.h(l);
        this.j = (this.M.nextDouble() * 2.0 - 1.0) * 0.75;
        this.b = AutoClickerTimingState.w(AutoClickerTimingState.x(this.i, this.m) + this.j, this.i, this.m);
    }

    private long P(long l, long l2, long l3) {
        long l4 = (l ^ this.c) & 0xFFFFL;
        long l5 = l3 - l2 + 1L;
        return l2 + (long)(Math.abs((int)l4) + this.M.nextInt((int)l5)) % l5;
    }

    private double a() {
        double d = this.P();
        double d2 = this.M.nextDouble();
        if (d2 < 0.12) {
            return (this.M.nextDouble() - 0.5) * 3.0;
        }
        if (d2 < 0.17) {
            return d * (1.4 + this.M.nextDouble() * 0.6);
        }
        return d;
    }

    private static long O(long l) {
        l = (l ^ l >>> 30) * -4658895280553007687L;
        l = (l ^ l >>> 27) * -7723592293110705685L;
        return l ^ l >>> 31;
    }

    public void Z(int n, int n2) {
        double d = Math.max(0, n);
        double d2 = Math.max(d, (double)n2);
        d += d * 0.5;
        if ((d2 += d2 * 0.5) < 0.5) {
            d2 = 0.5;
        }
        if (d < 0.5) {
            d = 0.5;
        }
        this.i = d;
        this.m = Math.max(d + 0.1, d2);
        if (!this.t) {
            this.b = AutoClickerTimingState.x(this.i, this.m);
            this.y = AutoClickerTimingState.w(this.b + this.P() * 0.25, this.i, this.m);
        }
    }

    private static double w(double d, double d2, double d3) {
        return Math.max(d2, Math.min(d3, d));
    }

    public long Y() {
        long l;
        boolean bl;
        double d;
        long l2;
        long l3 = System.currentTimeMillis();
        if (!this.t) {
            this.t = true;
            this.M(l3);
            this.w = l3;
        }
        long l4 = l2 = this.w == 0L ? 0L : l3 - this.w;
        if (l2 >= 1200L) {
            d = (double)Math.min(5000L, l2) / 10.0;
            this.g = Math.max(0.0, this.g - 0.004 * d);
            this.z = this.M.nextDouble() < 0.7 ? 2 + this.M.nextInt(4) : 0;
            this.V = 0.03 + this.M.nextDouble() * 0.06;
            this.L = this.M.nextDouble() * 0.04;
        }
        if (++this.W > 80 + this.M.nextInt(120)) {
            this.V = 0.03 + this.M.nextDouble() * 0.06;
            this.L = this.M.nextDouble() * 0.04;
            this.W = 0;
        }
        if (l3 >= this.n) {
            d = this.P();
            this.y += 0.25 * (this.b - this.y) + 0.45 * d;
            if (this.M.nextDouble() < 0.03) {
                this.y += (this.M.nextDouble() - 0.5) * 1.2;
            }
            this.y = AutoClickerTimingState.w(this.y, this.i, this.m);
            this.z(l3);
        }
        if (l3 >= this.D) {
            this.j = (this.M.nextDouble() * 2.0 - 1.0) * 0.75;
            this.b = AutoClickerTimingState.w(AutoClickerTimingState.x(this.i, this.m) + this.j, this.i, this.m);
            this.h(l3);
        }
        d = this.y;
        boolean bl2 = bl = this.z > 0;
        if (bl) {
            long l5;
            d *= 1.0 + 0.05 * (0.4 + 0.8 * this.M.nextDouble());
            --this.z;
            d *= 1.0 - Math.min(0.4, this.g);
            d = AutoClickerTimingState.w(d, this.i, this.m);
            double d2 = 1000.0 / d;
            double d3 = Math.exp(0.24 * this.a());
            int n = 35 + (this.M.nextInt(11) - 5);
            double d4 = this.M.nextInt(Math.max(1, n) + 1);
            double d5 = d2 * d3 + d4;
            d5 += (double)this.M.nextInt(15);
            if (this.q > 0L && this.M.nextDouble() < this.V) {
                d5 = this.q + (long)this.M.nextInt(7) - 3L;
            }
            if (this.M.nextDouble() < 0.07) {
                d5 *= 0.7 + this.M.nextDouble() * 0.2;
            }
            double d6 = Math.min(0.04 + 0.12 * this.g + this.L, 0.18);
            if (this.M.nextDouble() < d6) {
                d5 += (double)(50 + this.M.nextInt(101));
            }
            this.g = Math.min(0.4, this.g + 0.015);
            this.w = l3;
            this.q = l5 = (long)Math.max(1.0, Math.min(d5, 2.147483647E9));
            return l5;
        }
        d *= 1.0 - Math.min(0.4, this.g);
        d = AutoClickerTimingState.w(d, this.i, this.m);
        double d7 = 1000.0 / d;
        double d8 = Math.exp(0.24 * this.a());
        int n = 35 + (this.M.nextInt(11) - 5);
        double d9 = this.M.nextInt(Math.max(1, n) + 1);
        double d10 = d7 * d8 + d9;
        if (this.q > 0L && this.M.nextDouble() < this.V) {
            d10 = this.q + (long)this.M.nextInt(7) - 3L;
        }
        if (this.M.nextDouble() < 0.07) {
            d10 *= 0.7 + this.M.nextDouble() * 0.2;
        }
        double d11 = Math.min(0.04 + 0.12 * this.g + this.L, 0.18);
        if (this.M.nextDouble() < d11) {
            d10 += (double)(50 + this.M.nextInt(101));
        }
        this.g = Math.min(0.4, this.g + 0.015);
        this.w = l3;
        this.q = l = (long)Math.max(1.0, Math.min(d10, 2.147483647E9));
        return l;
    }

    static {
        long[] lArray = new long[]{5312392413598187522L, -7076370236940746717L, -5481962850635218844L, 2643463619483795461L, -4122436621473677262L};
        T = (int)lArray[0];
        v = (int)lArray[1];
        r = (int)lArray[4];
        A = (int)lArray[3];
        I = (int)lArray[2];
    }
}

