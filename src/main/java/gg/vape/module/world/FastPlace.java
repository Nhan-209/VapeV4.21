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
    private final ModeOption projectilesOption;
    private final ModeValue heldItemMode;
    private final ModeOption allOption;
    private static final long MODULE_COLOR = 506362964711178016L;
    private final NumberValue delayValue = NumberValue.create((Object)this, "Delay", "#", "", 0.0, 1.0, 4.0, 1.0);
    private final ModeOption blocksOption;

    public FastPlace() {
        super("FastPlace", (int)MODULE_COLOR, Category.m, "Changes the block place delay.");
        this.allOption = new ModeOption("All");
        this.blocksOption = new ModeOption("Blocks");
        this.projectilesOption = new ModeOption("Projectiles");
        this.heldItemMode = ModeValue.create((Object)this, "Held Item", "What kind of items should FastPlace function with?\nAll - All items/blocks\nBlocks - All blocks\nProjectiles - Snowballs & Eggs", (ModeSelection)this.allOption, this.allOption, this.blocksOption, this.projectilesOption);
        this.addValue(this.heldItemMode, this.delayValue);
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
        if (this.heldItemMode.K() == this.blocksOption && !this.isBlock(itemStack2) && !this.isBlock(itemStack)) {
            return;
        }
        if (this.heldItemMode.K() == this.projectilesOption && !this.isProjectile(itemStack2) && !this.isProjectile(itemStack)) {
            return;
        }
        if ((double)Minecraft.w() > (Double)this.delayValue.K()) {
            Minecraft.E(((Double)this.delayValue.K()).intValue());
        }
    }

    private boolean isProjectile(ItemStack itemStack) {
        return itemStack.isNotNull() && itemStack.getItem().isNotNull() && ItemStackScoreUtil.Z(itemStack.getItem());
    }

    private boolean isBlock(ItemStack itemStack) {
        return itemStack.isNotNull() && itemStack.getItem().isNotNull() && itemStack.getItem().isInstance(MappedClasses.Vw);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

