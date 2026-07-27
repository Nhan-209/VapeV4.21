package gg.vape.movement;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationManager;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import java.util.ArrayList;
import java.util.Arrays;

public class MovementInputHelper {
    public static void r() {
        MovementInputHelper.I(true);
    }

    public static void g(double d, double d2, ArrayList<KeyBinding> arrayList) {
        double d3;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        FixedRotationController fixedRotationController = (FixedRotationController)RotationManager.b.w();
        float f = RotationUtil.c();
        float f2 = (float)Math.toDegrees(Math.atan2(d2, d)) - 90.0f;
        float f3 = MathUtil.wrapAngleTo180(MathUtil.wrapAngleTo180(f2) - f);
        float f4 = f3 * ((float)Math.PI / 180);
        float f5 = (float)Math.cos(f4);
        float f6 = (float)Math.sin(f4);
        boolean bl = f5 >= 0.0f;
        boolean bl2 = f6 >= 0.0f;
        boolean bl3 = f6 <= 0.0f;
        boolean bl4 = f5 <= 0.0f;
        GameSettings gameSettings = Minecraft.gameSettings();
        double d4 = 0.2;
        if (entityPlayerSP.P() && entityPlayerSP.b$src$Z$fqlxe4()) {
            d4 = 0.06;
        } else if (entityPlayerSP.B$src$Z$f90iek() && bl) {
            d4 = 0.3;
        }
        if (!entityPlayerSP.b$src$Z$fqlxe4()) {
            d4 *= 0.02;
        }
        double d5 = d3 = bl ? 1.0 : (bl4 ? -1.0 : 0.0);
        double d6 = bl2 ? -1.0 : (bl3 ? 1.0 : 0.0);
        double d7 = d4;
        float f7 = MathUtil.sin(f2 * (float)Math.PI / 180.0f);
        float f8 = MathUtil.cos(f2 * (float)Math.PI / 180.0f);
        double d8 = -((double)f5 * d3 * d4) * (double)f7;
        double d9 = (double)f5 * d3 * d4 * (double)f8;
        double[] dArray = new double[]{d8, d9};
        double[] dArray2 = new double[]{entityPlayerSP.z() + entityPlayerSP.t(), entityPlayerSP.h() + entityPlayerSP.T()};
        double d10 = MovementInputHelper.F(entityPlayerSP, dArray2, new double[]{0.0, 0.0}, d, d2);
        double d11 = MovementInputHelper.F(entityPlayerSP, dArray2, dArray, d, d2);
        if (d11 < d10) {
            gameSettings.Y().setPressed(bl && !arrayList.contains(gameSettings.Y()));
            gameSettings.s().setPressed(bl4 && !arrayList.contains(gameSettings.s()));
        }
        f7 = MathUtil.sin((f2 + 90.0f) * (float)Math.PI / 180.0f);
        f8 = MathUtil.cos((f2 + 90.0f) * (float)Math.PI / 180.0f);
        d8 = (double)f6 * d6 * d4 * (double)f8;
        d9 = -((double)f6 * d6 * d4) * (double)f7;
        dArray = new double[]{d8, d9};
        dArray2 = new double[]{entityPlayerSP.z() + entityPlayerSP.t(), entityPlayerSP.h() + entityPlayerSP.T()};
        d10 = MovementInputHelper.F(entityPlayerSP, dArray2, new double[]{0.0, 0.0}, d, d2);
        d11 = MovementInputHelper.F(entityPlayerSP, dArray2, dArray, d, d2);
        Vape.debugLog("Vector " + dArray[0] + " " + dArray[1] + " | Potential Dist: " + d11 + " | Final Dist: " + d10 + " | " + f3 + " " + f2 + " " + EnumFacing.p(f2) + " " + f5 + " " + f6);
        if (d11 < d10) {
            gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg().setPressed(bl3 && !arrayList.contains(gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg()));
            gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3().setPressed(bl2 && !arrayList.contains(gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3()));
        }
    }


    public static void A(boolean bl) {
        MovementInputHelper.P(Minecraft.gameSettings().O(), bl);
    }

    public static void P(KeyBinding keyBinding, boolean bl) {
        if (bl) {
            if (!keyBinding.isKeyDown()) {
                MovementInputHelper.w(keyBinding, true);
            }
        } else if (keyBinding.isKeyDown() || keyBinding.V() > 0) {
            MovementInputHelper.w(keyBinding, false);
        }
    }

    public static void w(KeyBinding keyBinding, boolean bl) {
        if (ForgeVersion.MC_1_16_5.d()) {
            KeyBinding.setKeyBindState(keyBinding, bl);
            keyBinding.onTick(1);
            if (bl) {
                KeyBinding.onTick(keyBinding);
            }
            if (keyBinding.equals(Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg())) {
                // empty if block
            }
        } else if (bl) {
            KeyBinding.setKeyBindState(keyBinding, true);
            KeyBinding.onTick(keyBinding);
        } else {
            keyBinding.e();
        }
    }

    public static void j(double d, double d2, ArrayList<KeyBinding> arrayList, boolean bl) {
        double d3;
        int n;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        FixedRotationController fixedRotationController = (FixedRotationController)RotationManager.b.w();
        double d4 = RotationUtil.c();
        ArrayList<Double> arrayList2 = new ArrayList<Double>(Arrays.asList(d4, (d4 + 90.0) % 360.0, (d4 + 180.0) % 360.0, (d4 + 270.0) % 360.0));
        ArrayList<KeyBinding> arrayList3 = new ArrayList<KeyBinding>(Arrays.asList(Minecraft.gameSettings().Y(), Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3(), Minecraft.gameSettings().s(), Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg()));
        ArrayList<Boolean> arrayList4 = new ArrayList<Boolean>();
        for (KeyBinding keyBinding : arrayList) {
            arrayList2.remove(arrayList3.indexOf(keyBinding));
            arrayList3.remove(keyBinding);
        }
        double[] position = new double[]{entityPlayerSP.z() + entityPlayerSP.t(), entityPlayerSP.h() + entityPlayerSP.T()};
        double d5 = MovementInputHelper.F(entityPlayerSP, position, new double[]{0.0, 0.0}, d, d2);
        for (n = 0; n < arrayList2.size(); ++n) {
            double[] dArray = MovementInputHelper.p(entityPlayerSP, arrayList2.get(n), arrayList3.get(n));
            d3 = MovementInputHelper.F(entityPlayerSP, position, dArray, d, d2);
            if (d3 < d5) {
                arrayList4.add(true);
                position = new double[]{position[0] + dArray[0], position[1] + dArray[1]};
                d5 = d3;
                continue;
            }
            arrayList4.add(false);
        }
        if (bl) {
            AxisAlignedBB axisAlignedBB;
            double d6 = entityPlayerSP.z();
            d3 = entityPlayerSP.h();
            entityPlayerSP.H(position[0]);
            entityPlayerSP.l(position[1]);
            if (ForgeVersion.MC_1_8_9.d()) {
                axisAlignedBB = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            } else {
                AxisAlignedBB axisAlignedBB2 = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
                axisAlignedBB = axisAlignedBB2.copy();
            }
            double d7 = entityPlayerSP.t();
            double d8 = ForgeVersion.MC_1_20_6.d() ? 1.0 : -1.0;
            double d9 = entityPlayerSP.T();
            AxisAlignedBB axisAlignedBB3 = axisAlignedBB.expand(-0.15, 0.0, -0.15).k(d7, d8, d9);
            int n2 = Minecraft.theWorld().i(entityPlayerSP, axisAlignedBB3).size();
            if (n2 == 0) {
                for (int i = 0; i < arrayList4.size(); ++i) {
                    arrayList4.set(i, false);
                }
            }
            entityPlayerSP.H(d6);
            entityPlayerSP.l(d3);
        }
        for (n = 0; n < arrayList4.size(); ++n) {
            if (((Boolean)arrayList4.get(n)).booleanValue()) {
                if (arrayList3.get(n).isKeyDown()) continue;
                MovementInputHelper.w(arrayList3.get(n), true);
                continue;
            }
            if (!arrayList3.get(n).isKeyDown()) continue;
            MovementInputHelper.w(arrayList3.get(n), false);
        }
    }

    public static boolean k() {
        return ClientSettings.B(Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg()) || ClientSettings.B(Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3()) || ClientSettings.B(Minecraft.gameSettings().Y()) || ClientSettings.B(Minecraft.gameSettings().s());
    }

    public static void i() {
        KeyBinding keyBinding = Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg();
        KeyBinding keyBinding2 = Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3();
        KeyBinding keyBinding3 = Minecraft.gameSettings().Y();
        KeyBinding keyBinding4 = Minecraft.gameSettings().s();
        MovementInputHelper.w(keyBinding, false);
        MovementInputHelper.w(keyBinding2, false);
        MovementInputHelper.w(keyBinding3, false);
        MovementInputHelper.w(keyBinding4, false);
    }

    public static void Q(boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        GameSettings gameSettings = Minecraft.gameSettings();
        KeyBinding keyBinding = gameSettings.Y();
        KeyBinding keyBinding2 = gameSettings.s();
        KeyBinding keyBinding3 = gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg();
        KeyBinding keyBinding4 = gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3();
        ArrayList<KeyBinding> arrayList = new ArrayList<KeyBinding>(Arrays.asList(keyBinding, keyBinding2, keyBinding3, keyBinding4));
        ArrayList<Boolean> arrayList2 = new ArrayList<Boolean>();
        arrayList2.add(bl);
        arrayList2.add(bl2);
        arrayList2.add(bl3);
        arrayList2.add(bl4);
        for (int i = 0; i < arrayList2.size(); ++i) {
            MovementInputHelper.P(arrayList.get(i), (Boolean)arrayList2.get(i));
        }
    }

    public static void q() {
        MovementInputHelper.D(true);
    }

    public static void D(boolean bl) {
        KeyBinding keyBinding = Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg();
        KeyBinding keyBinding2 = Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3();
        KeyBinding keyBinding3 = Minecraft.gameSettings().Y();
        KeyBinding keyBinding4 = Minecraft.gameSettings().s();
        KeyBinding keyBinding5 = Minecraft.gameSettings().r();
        KeyBinding keyBinding6 = Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
        KeyBinding keyBinding7 = Minecraft.gameSettings().O();
        boolean bl2 = ClientSettings.B(keyBinding);
        boolean bl3 = ClientSettings.B(keyBinding2);
        boolean bl4 = ClientSettings.B(keyBinding3);
        boolean bl5 = ClientSettings.B(keyBinding4);
        boolean bl6 = ClientSettings.B(keyBinding5);
        boolean bl7 = ClientSettings.B(keyBinding6);
        boolean bl8 = ClientSettings.B(keyBinding7);
        MovementInputHelper.w(keyBinding, bl2);
        MovementInputHelper.w(keyBinding2, bl3);
        MovementInputHelper.w(keyBinding3, bl4);
        MovementInputHelper.w(keyBinding4, bl5);
        if (bl) {
            MovementInputHelper.w(keyBinding5, bl6);
        }
        MovementInputHelper.w(keyBinding6, bl7);
        MovementInputHelper.w(keyBinding7, bl8);
    }

    public static float U(EntityPlayer entityPlayer) {
        double d = entityPlayer.t();
        double d2 = entityPlayer.T();
        double d3 = Math.toDegrees(Math.atan2(d2, d));
        if (d != 0.0 || d2 != 0.0) {
            d3 = MathUtil.wrapAngleTo180(d3 - 90.0);
        }
        double d4 = d3 - 180.0;
        return (float)d4;
    }

    public static double[] p(EntityPlayerSP entityPlayerSP, double d, KeyBinding keyBinding) {
        double d2 = MovementInputHelper.y(entityPlayerSP, keyBinding);
        double d3 = Math.toRadians(d);
        double[] dArray = new double[]{d2 * -Math.sin(d3), d2 * Math.cos(d3)};
        return dArray;
    }

    public static double F(Entity entity, double[] dArray, double[] dArray2, double d, double d2) {
        double[] dArray3 = new double[]{entity.z() + d, entity.h() + d2};
        double[] dArray4 = new double[]{dArray[0] + dArray2[0], dArray[1] + dArray2[1]};
        double d3 = RotationUtil.y(dArray3[0], 0.0, dArray3[1], dArray4[0], 0.0, dArray4[1]);
        return d3;
    }

    private static double y(Entity entity, KeyBinding keyBinding) {
        double d = 0.2;
        if (entity.P() && entity.b$src$Z$fqlxe4()) {
            d = 0.06;
        } else if (entity.B$src$Z$f90iek() && keyBinding.equals(Minecraft.gameSettings().Y())) {
            d = 0.3;
        }
        if (!entity.b$src$Z$fqlxe4()) {
            d *= 0.02;
        }
        return d;
    }

    public static void I(boolean bl) {
        KeyBinding keyBinding = Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg();
        KeyBinding keyBinding2 = Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3();
        KeyBinding keyBinding3 = Minecraft.gameSettings().Y();
        KeyBinding keyBinding4 = Minecraft.gameSettings().s();
        KeyBinding keyBinding5 = Minecraft.gameSettings().r();
        KeyBinding keyBinding6 = Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
        KeyBinding keyBinding7 = Minecraft.gameSettings().O();
        MovementInputHelper.w(keyBinding, false);
        MovementInputHelper.w(keyBinding2, false);
        MovementInputHelper.w(keyBinding3, false);
        MovementInputHelper.w(keyBinding4, false);
        if (bl) {
            MovementInputHelper.w(keyBinding5, false);
        }
        MovementInputHelper.w(keyBinding6, false);
        MovementInputHelper.w(keyBinding7, false);
    }
}
