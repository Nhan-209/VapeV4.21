package gg.vape.friend.ping;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineFriendColorUtil;
import gg.vape.notification.NotificationSounds;
import gg.vape.protocol.packet.PingTargetData;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.RenderWorldLastEvent;
import gg.vape.wrapper.impl.World;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class PingMarker {
    private double[] X;
    private long q = Long.MAX_VALUE;
    private double B = 20.0;
    private final OnlineFriend D;
    private double x = 20.0;
    private static GuiComponent[] R;
    private double[] z;
    private boolean m = true;
    protected double[] M;
    private double S = 6.0;
    private final List<DoubleAnimation> G = new ArrayList<DoubleAnimation>();

    public double[] T$src$AD$1b8e6gj() {
        this.L$src$V$1mon1p8();
        return this.X;
    }

    public double A(double d) {
        double[] dArray;
        double[] dArray2 = this.X;
        double d2 = 100.0;
        double[] dArray3 = new double[]{this.X[0] + d2 * Math.cos(d), this.X[1] + d2 * Math.sin(d)};
        double d3 = dArray2[0] - this.o$src$D$1n7vu1d() / 2.0;
        double d4 = dArray2[0] + this.o$src$D$1n7vu1d() / 2.0;
        double d5 = dArray2[1] - this.i() / 2.0;
        double d6 = dArray2[1] + this.i() / 2.0;
        Double[] doubleArray = new Double[]{d3, d5};
        Double[] doubleArray2 = new Double[]{d4, d5};
        Double[] doubleArray3 = new Double[]{d3, d6};
        Double[] doubleArray4 = new Double[]{d4, d6};
        ArrayList<Double[][]> arrayList = new ArrayList<Double[][]>(Arrays.asList(new Double[][][]{{doubleArray, doubleArray2}, {doubleArray2, doubleArray4}, {doubleArray3, doubleArray4}, {doubleArray, doubleArray3}}));
        double d7 = Double.MAX_VALUE;
        for (Double[][] doubleArray5 : arrayList) {
            double d8;
            dArray = this.s(dArray2, dArray3, doubleArray5[0], doubleArray5[1]);
            if (dArray == null || !((d8 = RotationUtil.V(dArray2[0], dArray2[1], dArray[0], dArray[1])) < d7)) continue;
            d7 = d8;
        }
        double d9 = Math.min(this.o$src$D$1n7vu1d(), this.i()) - (this.o$src$D$1n7vu1d() < this.i() ? this.o$src$D$1n7vu1d() / this.i() : this.i() / this.o$src$D$1n7vu1d()) * (d7 += 4.0);
        dArray = new double[]{this.X[0] + d2 * Math.cos(Math.toRadians(Math.toDegrees(d) - d9)), this.X[1] + d2 * Math.sin(Math.toRadians(Math.toDegrees(d) - d9))};
        double[] dArray4 = new double[]{this.X[0] + d2 * Math.cos(Math.toRadians(Math.toDegrees(d) + d9)), this.X[1] + d2 * Math.sin(Math.toRadians(Math.toDegrees(d) + d9))};
        double d10 = Double.MAX_VALUE;
        for (Double[][] doubleArray6 : arrayList) {
            double d11;
            double[] dArray5 = this.s(dArray2, dArray, doubleArray6[0], doubleArray6[1]);
            if (dArray5 == null || !((d11 = RotationUtil.V(dArray2[0], dArray2[1], dArray5[0], dArray5[1])) < d10)) continue;
            d10 = d11;
        }
        double d12 = Double.MAX_VALUE;
        for (Double[][] doubleArray7 : arrayList) {
            double d13;
            double[] dArray6 = this.s(dArray2, dArray4, doubleArray7[0], doubleArray7[1]);
            if (dArray6 == null || !((d13 = RotationUtil.V(dArray2[0], dArray2[1], dArray6[0], dArray6[1])) < d12)) continue;
            d12 = d13;
        }
        double d14 = 0.0;
        Double[] doubleArray8 = new Double[]{d7, d10, d12};
        for (Double d15 : doubleArray8) {
            if (!(d15.doubleValue() > d14)) continue;
            d14 = d15.doubleValue();
        }
        return d14 - 0.009 * (d14 - d7 + (d14 - d10) + (d14 - d12)) / 2.0;
    }

    public abstract void K(boolean var1);

    public double i() {
        return this.B;
    }

    public void L$src$V$1mon1p8() {
        boolean bl;
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        RenderManager renderManager = Minecraft.D();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP == null || renderManager == null) {
            return;
        }
        double d6 = this.Z();
        double d7 = RotationUtil.y(d6, d5 = this.N(), d4 = this.F(), d3 = RenderManager.getInterpolatedRenderPosX(), d2 = RenderManager.getInterpolatedRenderPosY() + (double)Minecraft.thePlayer().X(), d = RenderManager.getInterpolatedRenderPosZ());
        double d8 = Math.min((38.0 - d7) / d7, 0.0);
        boolean bl2 = bl = d8 == 0.0;
        if (bl) {
            Double[] doubleArray = new Double[]{d6 - d3, d5 - (d2 -= (double)Minecraft.thePlayer().X()), d4 - d};
            Double[] doubleArray2 = new Double[]{doubleArray[0] * d8, doubleArray[1] * d8, doubleArray[2] * d8};
            Double[] doubleArray3 = new Double[]{doubleArray[0] + doubleArray2[0], doubleArray[1] + doubleArray2[1], doubleArray[2] + doubleArray2[2]};
            RenderUtil.d();
            double[] dArray = RenderUtil.W(doubleArray3[0], doubleArray3[1] + (double)0.0f, doubleArray3[2]);
            RenderUtil.Y();
            this.z = new double[]{dArray[0], (double)Minecraft.h() - dArray[1], dArray[2]};
            this.X = this.z;
            if (!this.o(this.z[0], this.z[1], this.z[2])) {
                return;
            }
            double d9 = Minecraft.J();
            double d10 = Minecraft.h();
            double d11 = this.y() - this.Q() + 4.0;
            double d12 = this.k() - this.E() + 6.0;
            double d13 = this.z[0] - d9 / 2.0;
            double d14 = this.z[1] - d10 / 2.0;
            double d15 = this.z[0] - d11 / 2.0 - (d9 - d11) / 2.0;
            double d16 = this.z[1] - d12 / 2.0 - (d10 - d12) / 4.0;
            double d17 = d13 < 0.0 ? -d9 / 2.0 / d13 : d9 / 2.0 / d13;
            double d18 = d14 < 0.0 ? -d10 / 2.0 / d14 : d10 / 2.0 / d14;
            double d19 = d15 < 0.0 ? -d11 / 2.0 / d15 : d11 / 2.0 / d15;
            double d20 = d16 < 0.0 ? -d12 / 2.0 / d16 : d12 / 2.0 / d16;
            double d21 = Math.abs(d17) < Math.abs(d18) ? d17 : d18;
            double d22 = Math.abs(d19) < Math.abs(d20) ? d19 : d20;
            d13 *= dArray[2] >= 1.0 ? -d21 : d21;
            d14 *= dArray[2] >= 1.0 ? -d21 : d21;
            d15 *= dArray[2] >= 1.0 ? -d22 : d22;
            d16 *= dArray[2] >= 1.0 ? -d22 : d22;
            this.z = new double[]{d13 += d9 / 2.0, d14 += d10 / 2.0, this.z[2]};
            this.X = new double[]{d15 += d11 / 2.0 + (d9 - d11) / 2.0, d16 += d12 / 2.0 + (d10 - d12) / 4.0, this.z[2]};
            return;
        }
        Double[] doubleArray = new Double[]{d6 - d3, d5 - d2, d4 - d};
        Double[] doubleArray4 = new Double[]{doubleArray[0] * d8, doubleArray[1] * d8, doubleArray[2] * d8};
        Double[] doubleArray5 = new Double[]{doubleArray[0] + doubleArray4[0], doubleArray[1] + doubleArray4[1], doubleArray[2] + doubleArray4[2]};
        RenderUtil.d();
        double[] dArray = RenderUtil.W(doubleArray5[0], doubleArray5[1] + (double)Minecraft.thePlayer().X(), doubleArray5[2]);
        RenderUtil.Y();
        this.z = new double[]{dArray[0], (double)Minecraft.h() - dArray[1], dArray[2]};
        this.X = this.z;
        if (!this.o(this.z[0], this.z[1], this.z[2])) {
            return;
        }
        double d23 = Minecraft.J();
        double d24 = Minecraft.h();
        double d25 = this.y() - this.Q() + 4.0;
        double d26 = this.k() - this.E() + 6.0;
        double d27 = this.z[0] - d23 / 2.0;
        double d28 = this.z[1] - d24 / 2.0;
        double d29 = this.z[0] - d25 / 2.0 - (d23 - d25) / 2.0;
        double d30 = this.z[1] - d26 / 2.0 - (d24 - d26) / 4.0;
        double d31 = d27 < 0.0 ? -d23 / 2.0 / d27 : d23 / 2.0 / d27;
        double d32 = d28 < 0.0 ? -d24 / 2.0 / d28 : d24 / 2.0 / d28;
        double d33 = d29 < 0.0 ? -d25 / 2.0 / d29 : d25 / 2.0 / d29;
        double d34 = d30 < 0.0 ? -d26 / 2.0 / d30 : d26 / 2.0 / d30;
        double d35 = Math.abs(d31) < Math.abs(d32) ? d31 : d32;
        double d36 = Math.abs(d33) < Math.abs(d34) ? d33 : d34;
        d27 *= dArray[2] >= 1.0 ? -d35 : d35;
        d28 *= dArray[2] >= 1.0 ? -d35 : d35;
        d29 *= dArray[2] >= 1.0 ? -d36 : d36;
        d30 *= dArray[2] >= 1.0 ? -d36 : d36;
        this.z = new double[]{d27 += d23 / 2.0, d28 += d24 / 2.0, this.z[2]};
        this.X = new double[]{d29 += d25 / 2.0 + (d23 - d25) / 2.0, d30 += d26 / 2.0 + (d24 - d26) / 4.0, this.z[2]};
    }

    public boolean r() {
        return System.currentTimeMillis() - this.q >= this.l$src$J$1n68geg();
    }

    public void u(double d) {
        this.x = d;
    }

    public void D() {
        float f = RenderWorldLastEvent.getPartialTicks();
        float f2 = 1.0f;
        float f3 = 2.0f;
        double d = this.X[0] / (double)f3 / (double)f2 / (double)f;
        double d2 = this.X[1] / (double)f / (double)f3 / (double)f2;
        boolean bl = !this.z.equals(this.X);
        OpenGlBackendHolder.d.I(d, d2, 0.0);
        this.K(bl);
        if (bl) {
            this.J();
        }
        OpenGlBackendHolder.d.I(-d, -d2, 0.0);
    }

    public boolean D(double d, double d2, double d3) {
        return RotationUtil.y(this.Z(), this.N(), this.F(), d, d2, d3) <= 0.25;
    }

    public abstract PingTargetData T();

    static {
        if (PingMarker.m() != null) {
            PingMarker.B(new GuiComponent[1]);
        }
    }

    public static void B(GuiComponent[] guiComponentArray) {
        R = guiComponentArray;
    }

    public String j() {
        return this.D != null ? this.D.C() : "";
    }

    public double Z() {
        return this.M[0];
    }

    public double o$src$D$1n7vu1d() {
        return this.x;
    }

    private double k() {
        return (double)Minecraft.h() - this.i() - this.Y() - 60.0;
    }

    public boolean C(double[] dArray) {
        return this.D(dArray[0], dArray[1], dArray[2]);
    }

    private double[] s(double[] dArray, double[] dArray2, Double[] doubleArray, Double[] doubleArray2) {
        double d = dArray2[1] - dArray[1];
        double d2 = dArray[0] - dArray2[0];
        double d3 = d * dArray[0] + d2 * dArray[1];
        double d4 = doubleArray2[1] - doubleArray[1];
        double d5 = doubleArray[0] - doubleArray2[0];
        double d6 = d4 * doubleArray[0] + d5 * doubleArray[1];
        double d7 = d * d5 - d4 * d2;
        if (d7 == 0.0) {
            return null;
        }
        double d8 = (d5 * d3 - d2 * d6) / d7;
        double d9 = (d * d6 - d4 * d3) / d7;
        return new double[]{d8, d9};
    }

    public boolean K() {
        return this.m;
    }

    public void o() {
        Vape.INSTANCE.getNotificationSoundPlayer().q(NotificationSounds.N);
        double d = 0.0;
        for (int i = 0; i < 1; ++i) {
            DoubleAnimation doubleAnimation = new DoubleAnimation(1.0, d, 0.0, 1.0);
            doubleAnimation.c();
            this.G.add(doubleAnimation);
            d += 0.25;
        }
        this.q = System.currentTimeMillis();
    }

    public long M() {
        return this.l$src$J$1n68geg() - (System.currentTimeMillis() - this.q);
    }


    public abstract void d();

    private boolean o(double d, double d2, double d3) {
        if (d <= this.Q() || d >= this.y()) {
            return true;
        }
        if (d2 > this.k() || d2 < this.E()) {
            return true;
        }
        return d3 > 1.0;
    }

    public void Z(boolean bl) {
        this.m = bl;
    }

    public double Q() {
        return this.o$src$D$1n7vu1d() + this.Y() * 3.0;
    }

    public PingMarker(OnlineFriend onlineFriend, double[] dArray) {
        this.D = onlineFriend;
        this.M = dArray;
    }

    public OnlineFriend O() {
        return this.D;
    }

    public boolean e() {
        return this.q != Long.MAX_VALUE;
    }

    public double Y() {
        return this.S;
    }

    public void n(double[] dArray) {
        this.M = dArray;
    }

    public double[] A() {
        return this.M;
    }

    public static GuiComponent[] m() {
        return R;
    }

    public void w(World world) {
    }

    public List<DoubleAnimation> W() {
        return this.G;
    }

    public void Z(double d) {
        this.B = d;
    }

    public void m(double d) {
        this.S = d;
    }

    public void b() {
        Vape.INSTANCE.getNotificationSoundPlayer().q(NotificationSounds.N);
        DoubleAnimation doubleAnimation = new DoubleAnimation(1.0, 0.0, 1.0);
        doubleAnimation.c();
        this.G.add(doubleAnimation);
        doubleAnimation = new DoubleAnimation(1.0, 0.25, 0.0, 1.0);
        doubleAnimation.c();
        this.G.add(doubleAnimation);
        if (this.e()) {
            this.o();
        }
    }

    protected float z(double d, double d2, double d3, double d4) {
        return (float)Math.toDegrees(Math.atan2(d4 - d3, d2 - d));
    }

    public double F() {
        return this.M[2];
    }

    private double j(double[] dArray, double[] dArray2) {
        return Math.atan2(dArray[1] - dArray2[1], dArray[0] - dArray2[0]) * 57.29577951308232;
    }

    public double N() {
        return this.M[1];
    }

    private double y() {
        double d = Minecraft.J();
        return d - (this.o$src$D$1n7vu1d() + this.Y() * 3.0);
    }

    public void J() {
        float f = RenderWorldLastEvent.getPartialTicks();
        Color color = OnlineFriendColorUtil.u(this.O());
        f *= 2.0f;
        double d = this.j(this.z, this.X);
        double d2 = Math.toRadians(d + 45.0);
        double d3 = Math.toRadians(d - 45.0);
        d = Math.toRadians(d);
        double d4 = Math.min(this.o$src$D$1n7vu1d() / 2.0, this.A(d));
        double d5 = (double)(1.0f / f) + (d4 + this.Y()) * Math.cos(d);
        double d6 = (double)(1.0f / f) + (d4 + this.Y()) * Math.sin(d);
        double d7 = d5 - this.Y() * Math.cos(d2);
        double d8 = d6 - this.Y() * Math.sin(d2);
        double d9 = d5 - this.Y() * 0.6 * Math.cos(d);
        double d10 = d6 - this.Y() * 0.6 * Math.sin(d);
        double d11 = d5 - this.Y() * Math.cos(d3);
        double d12 = d6 - this.Y() * Math.sin(d3);
        GuiRenderPrimitives.u(d7, d8, d5, d6, d11, d12, d9, d10, new Color(0, 0, 0, 255));
        GuiRenderPrimitives.u(d7, d8, d5, d6, d11, d12, d9, d10, color);
    }

    public double[] V() {
        return this.z;
    }

    private double E() {
        return this.i() + this.Y() * 3.0;
    }

    public long l$src$J$1n68geg() {
        return TimeUnit.SECONDS.toMillis(4L);
    }

    public double[] d$src$AD$1it44rn() {
        return this.X;
    }

    protected boolean N(double d, double d2) {
        return d > this.Q() && d < this.y() && d2 > this.E() && d2 < this.k();
    }
}
