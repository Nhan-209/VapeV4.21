package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.render.Freecam;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.MovementInput;
import java.util.List;

public class Parkour
extends Mod {
    private boolean v;
    private boolean b;
    private static final long o = -6495356621742786881L;

    public Parkour() {
        super("Parkour", (int)o, Category.m, "Jumps for you at the edge of blocks.");
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        boolean bl;
        if (Vape.INSTANCE.getModManager().getState(Freecam.class)) {
            return;
        }
        KeyBinding keyBinding = Minecraft.gameSettings().O();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (this.v) {
            if (!this.b) {
                KeyBindingHelper.v(keyBinding, false, false);
            }
            this.v = false;
            this.b = false;
            return;
        }
        if (keyBinding.isKeyDown()) {
            return;
        }
        MovementInput movementInput = entityPlayerSP.movementInput();
        boolean bl2 = bl = movementInput.D() > 0.0f;
        if (bl && entityPlayerSP.b$src$Z$fqlxe4()) {
            AxisAlignedBB axisAlignedBB;
            if (ForgeVersion.MC_1_8_9.d()) {
                axisAlignedBB = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            } else {
                AxisAlignedBB axisAlignedBB2 = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
                axisAlignedBB = axisAlignedBB2.copy();
            }
            double d = 0.0;
            double d2 = entityPlayerSP.J();
            double d3 = 90.0;
            double d4 = Math.cos(Math.toRadians(d2 + d3)) * d;
            double d5 = Math.sin(Math.toRadians(d2 + d3)) * d;
            double d6 = -0.1;
            AxisAlignedBB axisAlignedBB3 = axisAlignedBB.k(d4, d6, d5);
            List list = Minecraft.theWorld().i(entityPlayerSP, axisAlignedBB3);
            d = 1.0;
            d4 = Math.cos(Math.toRadians(d2 + d3)) * d;
            d5 = Math.sin(Math.toRadians(d2 + d3)) * d;
            d6 = -0.1;
            axisAlignedBB3 = axisAlignedBB.k(d4, d6, d5);
            List list2 = Minecraft.theWorld().i(entityPlayerSP, axisAlignedBB3);
            int n = list.size();
            int n2 = list2.size();
            if (n == 0 && n2 == 0) {
                this.b = keyBinding.u();
                KeyBindingHelper.d(keyBinding, true);
                this.v = true;
            }
        }
    }
}

