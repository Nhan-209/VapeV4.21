package gg.vape.utils;

import gg.vape.event.EventListener;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;

public class AttackCooldownUtil
implements EventListener {
    private static float b;

    public static boolean T(float f) {
        if (ForgeVersion.MC_1_12_2.v()) {
            return true;
        }
        float f2 = Minecraft.a_xH_J().getCooledAttackStrength(f);
        boolean bl = f2 == 1.0f;
        return bl;
    }

}

