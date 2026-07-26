package gg.vape.module.blatant;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostMove;
import gg.vape.event.impl.EventPreMove;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class SafeWalk
extends Mod {
    private boolean L;
    private static final long k = -5388886013077358019L;
    private final BooleanValue p = BooleanValue.create(this, "Direction Check", true, "Checks if you're walking forwards and it'll allow you to walk off the edge");

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean isBlatantMod() {
        return true;
    }

    @EventHandler
    public void m(EventPostMove eventPostMove) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        entityPlayerSP.movementInput().setCancelled(this.L);
    }

    @EventHandler
    public void C(EventPreMove eventPreMove) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        this.L = entityPlayerSP.movementInput().D$src$Z$v5d6e8();
        boolean bl = true;
        if (this.p.L().booleanValue() && !entityPlayerSP.P() && entityPlayerSP.F() > 0.0f && entityPlayerSP.N$src$F$14ypudi() == 0.0f) {
            bl = false;
        }
        if (bl) {
            entityPlayerSP.movementInput().setCancelled(true);
        }
    }

    public SafeWalk() {
        super("SafeWalk", (int)k, Category.m, "Helps you from falling off the edge.");
        this.addValue(this.p);
    }
}

