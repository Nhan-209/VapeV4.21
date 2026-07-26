package gg.vape.module.world;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumHand;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;

public class FastPlace
extends Mod {
    private final ModeOption Y;
    private final ModeValue H;
    private final ModeOption L;
    private static final long k = 506362964711178016L;
    private final NumberValue t = NumberValue.create((Object)this, "Delay", "#", "", 0.0, 1.0, 4.0, 1.0);
    private final ModeOption o;

    public FastPlace() {
        super("FastPlace", (int)k, Category.m, "Changes the block place delay.");
        this.L = new ModeOption("All");
        this.o = new ModeOption("Blocks");
        this.Y = new ModeOption("Projectiles");
        this.H = ModeValue.create((Object)this, "Held Item", "What kind of items should FastPlace function with?\nAll - All items/blocks\nBlocks - All blocks\nProjectiles - Snowballs & Eggs", (ModeSelection)this.L, this.L, this.o, this.Y);
        this.addValue(this.H, this.t);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        ItemStack itemStack;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        if (SharedModuleControlClaims.x.v$src$Z$1r7ksy2()) {
            return;
        }
        ItemStack itemStack2 = entityPlayerSP.getHeldItemHand();
        ItemStack itemStack3 = itemStack = ForgeVersion.MC_1_12_2.d() ? entityPlayerSP.i(EnumHand.p()) : new ItemStack(null);
        if (this.H.K() == this.o && !this.r(itemStack2) && !this.r(itemStack)) {
            return;
        }
        if (this.H.K() == this.Y && !this.F(itemStack2) && !this.F(itemStack)) {
            return;
        }
        if ((double)Minecraft.w() > (Double)this.t.K()) {
            Minecraft.E(((Double)this.t.K()).intValue());
        }
    }

    private boolean F(ItemStack itemStack) {
        return itemStack.isNotNull() && itemStack.getItem().isNotNull() && ItemStackScoreUtil.Z(itemStack.getItem());
    }

    private boolean r(ItemStack itemStack) {
        return itemStack.isNotNull() && itemStack.getItem().isNotNull() && itemStack.getItem().isInstance(MappedClasses.Vw);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

