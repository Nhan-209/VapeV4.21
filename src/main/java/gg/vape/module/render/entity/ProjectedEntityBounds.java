package gg.vape.module.render.entity;

import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.RenderUtil;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Entity;
import java.awt.Color;

public class ProjectedEntityBounds {
    public double r = -100.0;
    public double I = -100.0;
    public double g = -100.0;
    public double V = -100.0;
    public final Entity y;
    public double H = -100.0;
    public final Color U;
    public final RenderEntityContext c;
    public final boolean L;
    public double m = -100.0;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ProjectedEntityBounds(double d, double d2, double d3, AxisAlignedBB axisAlignedBB, Entity entity, RenderEntityContext renderEntityContext, Color color) {
        this.y = entity;
        this.c = renderEntityContext;
        this.U = color;
        double[] dArray = RenderUtil.W(d + axisAlignedBB.getMinX(), d2 + axisAlignedBB.getMinY(), d3 + axisAlignedBB.getMinZ());
        double[] dArray2 = RenderUtil.W(d + axisAlignedBB.getMaxX(), d2 + axisAlignedBB.getMinY(), d3 + axisAlignedBB.getMinZ());
        double[] dArray3 = RenderUtil.W(d + axisAlignedBB.getMaxX(), d2 + axisAlignedBB.getMinY(), d3 + axisAlignedBB.getMaxZ());
        double[] dArray4 = RenderUtil.W(d + axisAlignedBB.getMinX(), d2 + axisAlignedBB.getMinY(), d3 + axisAlignedBB.getMaxZ());
        double[] dArray5 = RenderUtil.W(d + axisAlignedBB.getMinX(), d2 + axisAlignedBB.getMaxY(), d3 + axisAlignedBB.getMinZ());
        double[] dArray6 = RenderUtil.W(d + axisAlignedBB.getMaxX(), d2 + axisAlignedBB.getMaxY(), d3 + axisAlignedBB.getMinZ());
        double[] dArray7 = RenderUtil.W(d + axisAlignedBB.getMaxX(), d2 + axisAlignedBB.getMaxY(), d3 + axisAlignedBB.getMaxZ());
        double[] dArray8 = RenderUtil.W(d + axisAlignedBB.getMinX(), d2 + axisAlignedBB.getMaxY(), d3 + axisAlignedBB.getMaxZ());
        boolean bl = this.L = dArray != null && dArray[2] >= 0.0 && dArray[2] < 1.0 && dArray2[2] >= 0.0 && dArray2[2] < 1.0 && dArray3[2] >= 0.0 && dArray3[2] < 1.0 && dArray4[2] >= 0.0 && dArray4[2] < 1.0 && dArray5[2] >= 0.0 && dArray5[2] < 1.0 && dArray6[2] >= 0.0 && dArray6[2] < 1.0 && dArray7[2] >= 0.0 && dArray7[2] < 1.0 && dArray8[2] >= 0.0 && dArray8[2] < 1.0;
        if (!this.L) {
            return;
        }
        double d4 = dArray[0];
        double d5 = dArray[1];
        double d6 = dArray8[0];
        double d7 = dArray8[1];
        double[] dArray9 = new double[]{dArray[0], dArray2[0], dArray3[0], dArray4[0], dArray5[0], dArray6[0], dArray7[0], dArray8[0]};
        double[] dArray10 = new double[]{dArray[1], dArray2[1], dArray3[1], dArray4[1], dArray5[1], dArray6[1], dArray7[1], dArray8[1]};
        for (double d8 : dArray9) {
            if (!(d8 < d4)) continue;
            d4 = d8;
        }
        for (double d8 : dArray9) {
            if (!(d8 > d6)) continue;
            d6 = d8;
        }
        for (double d8 : dArray10) {
            if (!(d8 < d5)) continue;
            d5 = d8;
        }
        for (double d8 : dArray10) {
            if (!(d8 > d7)) continue;
            d7 = d8;
        }
        this.r = d4;
        this.I = d5;
        this.V = d6;
        this.g = d7;
    }
}

