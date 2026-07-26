package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.KillAura;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;

public class AutoTool
extends Mod {
    private final BooleanValue V;
    private int o = -1;
    private final BooleanValue H;
    private boolean b;
    private static final long t = -983995720679623614L;
    private boolean O;
    private boolean c;
    private TimerUtil k;
    private TimerUtil J;
    private final BooleanValue Z = BooleanValue.create(this, "Swap weapon", true, "Swaps to the strongest weapon on your hotbar");
    private final NumberValue p;
    private final BooleanValue D;
    private final NumberValue v;
    private final BooleanValue C;

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (eventPrePlayerTick.getThePlayer().isNull() || Minecraft.currentScreen().isNotNull()) {
            return;
        }
        RayTraceResult rayTraceResult = RotationManager.b.n();
        boolean bl = ClientSettings.M();
        int n = eventPrePlayerTick.getThePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        int n2 = this.x(this.Z.L());
        if (this.D.L().booleanValue() && !bl) {
            n2 = -1;
        }
        if (this.C.L().booleanValue() && !eventPrePlayerTick.getThePlayer().P()) {
            n2 = -1;
        }
        if (this.c && this.H.L().booleanValue() && !this.O && n2 == -1 && this.o != -1) {
            this.O = true;
            this.k.reset();
        }
        if (this.O) {
            if (this.c && this.k.hasTimeElapsed(((Double)this.p.K()).longValue())) {
                if (this.o != -1) {
                    eventPrePlayerTick.getThePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.o);
                }
                this.c = false;
                this.O = false;
                this.o = -1;
            }
            return;
        }
        if (!this.b && n2 != -1 && n2 != n) {
            this.b = true;
            this.J.reset();
        }
        if (this.b) {
            boolean bl2 = this.J.hasTimeElapsed(((Double)this.v.K()).longValue());
            if (this.Z.L().booleanValue() && this.V.L().booleanValue() && rayTraceResult.isNotNull() && rayTraceResult.isEntityHit()) {
                bl2 = true;
            }
            if (bl2) {
                int n3 = this.o = this.o == -1 ? n : this.o;
                if (n2 != -1 && n2 != n) {
                    eventPrePlayerTick.getThePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n2);
                    this.c = true;
                }
                this.b = false;
            }
            return;
        }
    }

    private int x(boolean bl) {
        int n;
        block6: {
            RayTraceResult rayTraceResult;
            block5: {
                rayTraceResult = RotationManager.b.n();
                if (rayTraceResult.isNull()) {
                    return -1;
                }
                n = -1;
                if (!rayTraceResult.isBlockHit()) break block5;
                float f = 1.0f;
                InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
                for (int i = 0; i < 9; ++i) {
                    ItemStack itemStack = inventoryPlayer.c(i);
                    if (itemStack.isNull()) continue;
                    float f2 = itemStack.V(rayTraceResult.g(), rayTraceResult.T(), rayTraceResult.a$src$I$8nuo9d());
                    Item item = itemStack.getItem();
                    if (item.isNotNull() && ItemStackScoreUtil.I(item) && ItemStackScoreUtil.K(itemStack)) {
                        f2 /= 2.0f;
                    }
                    if (!(f2 > f)) continue;
                    f = f2;
                    n = i;
                }
                break block6;
            }
            if (!rayTraceResult.isEntityHit() || !bl) break block6;
            float f = 1.0f;
            InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
            for (int i = 0; i < 9; ++i) {
                ItemStack itemStack = inventoryPlayer.c(i);
                if (itemStack.isNull() || itemStack.getItem().isNull() || !ItemStackScoreUtil.h(itemStack.getItem()) && !ItemStackScoreUtil.I(itemStack.getItem())) continue;
                float f3 = (float)ClientSettings.U(itemStack);
                if (ItemStackScoreUtil.h(itemStack.getItem())) {
                    f3 = (float)((double)f3 + 0.01);
                }
                if (!(f3 > f)) continue;
                f = f3;
                n = i;
            }
        }
        return n;
    }

    private boolean w$src$Z$f6xv4o() {
        KillAura killAura = Vape.INSTANCE.getModManager().getMod(KillAura.class);
        return killAura.r$src$Z$14eylz9() && killAura.D();
    }

    public AutoTool() {
        super("AutoTool", (int)t, Category.m, "Automatically swaps your hand to the appropriate tool");
        this.V = BooleanValue.create(this, "Instant swap", true, "Swaps to weapon without swap delay");
        this.v = NumberValue.create(this, "Swap to delay", "#", "ms", 0.0, 50.0, 500.0, 50.0, "How long to wait before swapping to tool");
        this.H = BooleanValue.create(this, "Swap back", false, "Swaps back to your original item when not hovering over blocks");
        this.p = NumberValue.create(this, "Swap back delay", "#", "ms", 50.0, 350.0, 1000.0, 50.0, "How long to wait before swapping back");
        this.D = BooleanValue.create(this, "Require mouse down", true, "Only swaps tools while holding left click");
        this.C = BooleanValue.create(this, "Only while sneaking", false, "Only swaps tools while sneaking");
        this.k = new TimerUtil();
        this.J = new TimerUtil();
        this.Z.K(this.V);
        this.H.K(this.p);
        this.addValue(this.v, this.Z, this.V, this.H, this.p, this.D, this.C);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

