package gg.vape.module.blatant;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.Minecraft;

public class Timer
extends Mod {
    private static final long p = 853958073918623760L;
    private final NumberValue k = NumberValue.create((Object)this, "Speed", "#.##", "", 0.1, 1.07, 2.0, 0.01);
    private boolean c;

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        this.c = !this.c;
        Minecraft.getTimer().setTimerSpeed(((Double)this.k.K()).floatValue());
    }

    public Timer() {
        super("Timer", (int)p, Category.w, "Modifies game timer");
        this.R(false);
        this.addValue(this.k);
    }

    @Override
    public void onDisable() {
        Minecraft.getTimer().setTimerSpeed(1.0f);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

