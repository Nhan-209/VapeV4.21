package gg.vape.rotation;

import gg.vape.Vape;
import gg.vape.module.render.hud.FreeLookHudModule;
import gg.vape.rotation.RotationManager;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class PlayerMouseRotationApplier {
    public static void L(EntityPlayer entityPlayer, float f, float f2) {
        float f3 = entityPlayer.V();
        float f4 = entityPlayer.J();
        entityPlayer.H((float)((double)entityPlayer.J() + (double)f * 0.15));
        entityPlayer.C((float)((double)entityPlayer.V() - (double)f2 * 0.15));
        if (entityPlayer.V() < -90.0f) {
            entityPlayer.C(-90.0f);
        }
        if (entityPlayer.V() > 90.0f) {
            entityPlayer.C(90.0f);
        }
        entityPlayer.l(entityPlayer.D() + entityPlayer.V() - f3);
        entityPlayer.D(entityPlayer.j() + entityPlayer.J() - f4);
        entityPlayer.z(entityPlayer.J());
        entityPlayer.o(entityPlayer.s());
        PlayerMouseRotationApplier.M();
    }

    private static void M() {
        FreeLookHudModule freeLookHudModule = Vape.INSTANCE.getModManager().getMod(FreeLookHudModule.class);
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        freeLookHudModule.G(entityPlayerSP.J(), entityPlayerSP.V(), entityPlayerSP.j(), entityPlayerSP.D());
    }

    public static void X(float f, float f2) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        float f3 = entityPlayerSP.V();
        float f4 = entityPlayerSP.J();
        entityPlayerSP.H(f);
        entityPlayerSP.C(f2);
        if (entityPlayerSP.V() < -90.0f) {
            entityPlayerSP.C(-90.0f);
        }
        if (entityPlayerSP.V() > 90.0f) {
            entityPlayerSP.C(90.0f);
        }
        entityPlayerSP.l(entityPlayerSP.D() + entityPlayerSP.V() - f3);
        entityPlayerSP.D(entityPlayerSP.j() + entityPlayerSP.J() - f4);
    }

    public static void j(float f, float f2) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        float f3 = RotationManager.g(entityPlayerSP);
        float f4 = RotationManager.s(entityPlayerSP);
        entityPlayerSP.H((float)((double)f4 + (double)f * 0.15));
        entityPlayerSP.C((float)((double)f3 - (double)f2 * 0.15));
        if (entityPlayerSP.V() < -90.0f) {
            entityPlayerSP.C(-90.0f);
        }
        if (entityPlayerSP.V() > 90.0f) {
            entityPlayerSP.C(90.0f);
        }
        entityPlayerSP.l(entityPlayerSP.D() + entityPlayerSP.V() - f3);
        entityPlayerSP.D(entityPlayerSP.j() + entityPlayerSP.J() - f4);
        PlayerMouseRotationApplier.M();
    }

    public static void y(float f, float f2) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        float f3 = entityPlayerSP.V();
        float f4 = entityPlayerSP.J();
        entityPlayerSP.H((float)((double)entityPlayerSP.J() + (double)f * 0.15));
        entityPlayerSP.C((float)((double)entityPlayerSP.V() - (double)f2 * 0.15));
        if (entityPlayerSP.V() < -90.0f) {
            entityPlayerSP.C(-90.0f);
        }
        if (entityPlayerSP.V() > 90.0f) {
            entityPlayerSP.C(90.0f);
        }
        entityPlayerSP.l(entityPlayerSP.D() + entityPlayerSP.V() - f3);
        entityPlayerSP.D(entityPlayerSP.j() + entityPlayerSP.J() - f4);
    }

}

