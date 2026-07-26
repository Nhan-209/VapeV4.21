package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.Minecraft;

public class PositionClip
extends Mod {
    private static final long k = -9039312205522206588L;
    private final NumberValue D;
    private final NumberValue A = NumberValue.create((Object)this, "x", "#.##", "", -2.0, 0.5, 2.0, 0.01);
    private final NumberValue S = NumberValue.create((Object)this, "y", "#.##", "", -2.0, 0.5, 2.0, 0.01);

    @Override
    public void onEnable() {
        Vape.debugLog("Trying to knockback " + Minecraft.thePlayer().z() + " " + Minecraft.thePlayer().N() + " " + Minecraft.thePlayer().h());
        Minecraft.thePlayer().E((Double)this.A.K(), (Double)this.S.K(), (Double)this.D.K());
        this.Y(false);
    }

    public PositionClip() {
        super("Knockback Test", (int)k, Category.m);
        this.D = NumberValue.create((Object)this, "z", "#.##", "", -2.0, 0.5, 2.0, 0.01);
        this.addValue(this.A, this.S, this.D);
    }
}

