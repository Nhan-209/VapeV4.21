package gg.vape.module.blatant;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostMove;
import gg.vape.event.impl.EventPreMove;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class SafeWalk
extends Mod {
    private boolean sneakCancelled;
    private static final long k = -5388886013077358019L;
    private final BooleanValue directionCheck = BooleanValue.create(this, "Direction Check", true, "Checks if you're walking forwards and it'll allow you to walk off the edge");


    @Override
    public boolean isBlatantMod() {
        return true;
    }

    @EventHandler
    public void onPostMove(EventPostMove eventPostMove) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        entityPlayerSP.movementInput().setCancelled(this.sneakCancelled);
    }

    @EventHandler
    public void onPreMove(EventPreMove eventPreMove) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        this.sneakCancelled = entityPlayerSP.movementInput().D$src$Z$v5d6e8();
        boolean shouldSafeWalk = true;
        if (this.directionCheck.L().booleanValue() && !entityPlayerSP.P() && entityPlayerSP.F() > 0.0f && entityPlayerSP.N$src$F$14ypudi() == 0.0f) {
            shouldSafeWalk = false;
        }
        if (shouldSafeWalk) {
            entityPlayerSP.movementInput().setCancelled(true);
        }
    }

    public SafeWalk() {
        super("SafeWalk", (int)k, Category.m, "Helps you from falling off the edge.");
        this.addValue(this.directionCheck);
    }
}

