package gg.vape.module.utility;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.module.UtilityMod;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;

public class WindChargeJump
extends UtilityMod {
    public final NumberValue aimSpeed = NumberValue.create(this, "Aim speed", "#.#", "", 1.0, 7.0, 10.0);
    private FixedRotationController rotationController;
    private int state;
    private static final long MAGIC_STATE = 1631323600877256706L;
    private final RotationControlClaim rotationClaim;
    private int savedSlot = -1;
    private final BooleanValue silentAim = new BooleanValue((Object)this, "Silent aim", true);

    private int findWindChargeSlot() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        Item item = Item.L("minecraft:wind_charge");
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = inventoryPlayer.c(i);
            if (itemStack.isNull() || itemStack.getItem().isNull()) continue;
            Item slotItem = itemStack.getItem();
            if (item == null || !item.isNotNull() || !slotItem.equals(item)) continue;
            return i;
        }
        return -1;
    }

    private boolean canClaimRotation() {
        return this.rotationClaim.U(this) || this.rotationClaim.h(this, this.silentAim.L());
    }

    @Override
    public void onDisable() {
        this.releaseRotation();
        if (this.savedSlot != -1) {
            Minecraft.gameSettings().O().e();
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.savedSlot);
        }
        this.savedSlot = -1;
        this.state = 0;
    }

    private void releaseRotation() {
        if (this.rotationController != null) {
            RotationManager.b.v(this.rotationController);
            this.rotationClaim.X(this);
        }
    }

    public WindChargeJump() {
        super("WindCharge", "Automatically uses a wind charge");
        this.rotationClaim = SharedModuleControlClaims.I;
        this.addValue(this.aimSpeed, this.silentAim);
        this.rotationClaim.l(this, 6);
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (this.state == 0) {
            int slot = this.findWindChargeSlot();
            if (slot != -1 && eventPreTick.getThePlayer().b$src$Z$fqlxe4()) {
                Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(slot);
                if (!this.canClaimRotation()) {
                    return;
                }
                this.rotationController = this.silentAim.L() != false ? new AdaptiveRotationController(-999.0f, 90.0f) : new FixedRotationController(-999.0f, 90.0f);
                this.rotationController.g(-999.0f, 90.0f);
                this.rotationController.Y(((Double)this.aimSpeed.K()).intValue());
                this.rotationController.U(false);
                this.rotationController.s(true);
                this.rotationController.t(5.0f);
                RotationManager.b.S(this.rotationController);
                this.state = 1;
            }
        } else if (this.state == 1) {
            if (this.rotationController != null) {
                if (this.rotationController.V$src$Z$lb4tvc() || RotationManager.b.x() > 80.0f) {
                    KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                    KeyBinding.setKeyBindState(keyBinding, true);
                    KeyBinding.onTick(keyBinding);
                    KeyBinding.setKeyBindState(keyBinding, false);
                    this.state = (int)MAGIC_STATE;
                }
            } else {
                this.state = -1;
            }
        } else if (this.state == 2) {
            Minecraft.gameSettings().O().I();
            this.state = -1;
        } else if (this.state == -1) {
            this.releaseRotation();
            if (this.savedSlot != -1) {
                Minecraft.gameSettings().O().e();
                Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.savedSlot);
            }
            this.s(false, true);
        }
    }

    @Override
    public void onEnable() {
        int slot = this.findWindChargeSlot();
        if (slot != -1 && Minecraft.thePlayer().b$src$Z$fqlxe4()) {
            this.savedSlot = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            this.state = 0;
        } else {
            this.s(false, true);
        }
    }
}

