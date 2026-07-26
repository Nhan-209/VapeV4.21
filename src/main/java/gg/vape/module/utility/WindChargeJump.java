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
    public final NumberValue D = NumberValue.create(this, "Aim speed", "#.#", "", 1.0, 7.0, 10.0);
    private FixedRotationController S;
    private int p;
    private static final long k = 1631323600877256706L;
    private final RotationControlClaim H;
    private int J = -1;
    private final BooleanValue F = new BooleanValue((Object)this, "Silent aim", true);

    private int y$src$I$93o8cu() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        Item item = Item.L("minecraft:wind_charge");
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = inventoryPlayer.c(i);
            if (itemStack.isNull() || itemStack.getItem().isNull()) continue;
            Item item2 = itemStack.getItem();
            if (item == null || !item.isNotNull() || !item2.equals(item)) continue;
            return i;
        }
        return -1;
    }

    private boolean v() {
        return this.H.U(this) || this.H.h(this, this.F.L());
    }

    @Override
    public void onDisable() {
        this.b$src$V$8r0z0q();
        if (this.J != -1) {
            Minecraft.gameSettings().O().e();
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.J);
        }
        this.J = -1;
        this.p = 0;
    }

    private void b$src$V$8r0z0q() {
        if (this.S != null) {
            RotationManager.b.v(this.S);
            this.H.X(this);
        }
    }

    public WindChargeJump() {
        super("WindCharge", "Automatically uses a wind charge");
        this.H = SharedModuleControlClaims.I;
        this.addValue(this.D, this.F);
        this.H.l(this, 6);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (this.p == 0) {
            int n = this.y$src$I$93o8cu();
            if (n != -1 && eventPreTick.getThePlayer().b$src$Z$fqlxe4()) {
                Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
                if (!this.v()) {
                    return;
                }
                this.S = this.F.L() != false ? new AdaptiveRotationController(-999.0f, 90.0f) : new FixedRotationController(-999.0f, 90.0f);
                this.S.g(-999.0f, 90.0f);
                this.S.Y(((Double)this.D.K()).intValue());
                this.S.U(false);
                this.S.s(true);
                this.S.t(5.0f);
                RotationManager.b.S(this.S);
                this.p = 1;
            }
        } else if (this.p == 1) {
            if (this.S != null) {
                if (this.S.V$src$Z$lb4tvc() || RotationManager.b.x() > 80.0f) {
                    KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                    KeyBinding.setKeyBindState(keyBinding, true);
                    KeyBinding.onTick(keyBinding);
                    KeyBinding.setKeyBindState(keyBinding, false);
                    this.p = (int)k;
                }
            } else {
                this.p = -1;
            }
        } else if (this.p == 2) {
            Minecraft.gameSettings().O().I();
            this.p = -1;
        } else if (this.p == -1) {
            this.b$src$V$8r0z0q();
            if (this.J != -1) {
                Minecraft.gameSettings().O().e();
                Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.J);
            }
            this.s(false, true);
        }
    }

    @Override
    public void onEnable() {
        int n = this.y$src$I$93o8cu();
        if (n != -1 && Minecraft.thePlayer().b$src$Z$fqlxe4()) {
            int n2;
            this.J = n2 = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            this.p = 0;
        } else {
            this.s(false, true);
        }
    }
}

