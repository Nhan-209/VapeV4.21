package gg.vape.utils;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.Vec3d;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class ProjectilePitchUtil {
    private static final double c = 1.5;
    private static final double h = 0.03;

    public static float calculatePitch(Vec3d vec3d, double d) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d2 = 0.05f;
        double d3 = vec3d.Y() - entityPlayerSP.z();
        double d4 = vec3d.o() - entityPlayerSP.h();
        double d5 = Math.sqrt(d3 * d3 + d4 * d4);
        double d6 = entityPlayerSP.N();
        float f = entityPlayerSP.X();
        double d7 = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY();
        double d8 = vec3d.t() - entityPlayerSP.U();
        double d9 = 2.0 * d8 * (d * d);
        double d10 = d2 * (d5 * d5);
        double d11 = d10 + d9;
        double d12 = d * d * d * d;
        double d13 = d2 * d11;
        double d14 = d12 - d13;
        double d15 = Math.sqrt(d14);
        double d16 = d * d + d15;
        double d17 = d * d - d15;
        double d18 = Math.atan2(d16, d2 * d5);
        double d19 = Math.atan2(d17, d2 * d5);
        float f2 = (float)(-Math.toDegrees(Math.min(d18, d19)));
        return f2;
    }

    public static float calculatePitch(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7;
        double d8;
        double d9 = 1.5;
        double d10 = d9 * d9;
        double d11 = 0.03;
        double d12 = d4 - d;
        double d13 = d6 - d3;
        double d14 = Math.sqrt(d12 * d12 + d13 * d13);
        double d15 = d10 * d10 - d11 * (d11 * (d14 * d14) + 2.0 * (d8 = d5 - d2) * d10);
        if (d15 < 0.0) {
            return Float.NaN;
        }
        double d16 = Math.sqrt(d15);
        double d17 = Math.atan((d10 + d16) / (d11 * d14));
        double d18 = d7 = Math.atan((d10 - d16) / (d11 * d14));
        float f = Minecraft.thePlayer().V();
        double d19 = Math.toDegrees(-d17);
        double d20 = Math.toDegrees(-d7);
        float f2 = (float)Math.abs(d19 - (double)f);
        float f3 = (float)Math.abs(d20 - (double)f);
        boolean bl = false;
        float f4 = (float)Math.toDegrees(-d18);
        return f4;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

