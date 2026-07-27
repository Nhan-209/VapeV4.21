package gg.vape.module.combat;

import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.SyntheticAttackRequestEvent;
import gg.vape.input.AttackKeyController;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.control.AttackCancellationAdapter;
import gg.vape.module.control.PhysicalAttackCancellationAdapter;
import gg.vape.module.control.SyntheticAttackCancellationAdapter;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ItemLimitData;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.wrapper.impl.EnchantmentHelper;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumHand;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import java.util.Arrays;

public class AutoMace
extends Mod {
    public final BooleanValue breachMaces;
    private boolean swingReleasePending = false;
    public final BooleanValue stunSlam;
    private int tickCounter;
    public final BooleanValue axes;
    private boolean stunSlamActive = false;
    private boolean stunSlamSecondPhase = false;
    private boolean breachSwapPending = false;
    public final LimitValue allowedItems;
    private boolean swapAttackPending = false;
    public final BooleanValue limitToItems = BooleanValue.create(this, "Limit to items", false);
    private boolean swapActive;
    public final BooleanValue smashOnly;
    public final BooleanValue densityMaces;
    private int originalSlot = -1;
    private static final long v = -7666507152973844357L;
    public final BooleanValue maces = BooleanValue.create(this, "Maces", true);
    public final BooleanValue swords;

    private boolean shouldFilterByEnchant() {
        return (this.densityMaces.L() != false || this.breachMaces.L() != false) && this.hasMaceInHotbar();
    }

    private boolean hasMaceInHotbar() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = inventoryPlayer.c(i);
            if (!this.isValidMace(itemStack, true)) continue;
            return true;
        }
        return false;
    }

    @EventHandler(A=EventPriority.HIGH)
    public void onKeyPress(EventKeyPress eventKeyPress) {
        block4: {
            block3: {
                boolean bl;
                block2: {
                    int n = JavassistMappingTask.U();
                    bl = eventKeyPress.isKeybinding(Minecraft.gameSettings().F());
                    if (n != 0) break block2;
                    if (!bl) break block3;
                    bl = eventKeyPress.isDown();
                }
                if (bl) break block4;
            }
            return;
        }
        this.handleAttack(new PhysicalAttackCancellationAdapter(eventKeyPress, null));
    }

    @Override
    public void onDisable() {
        this.swapAttackPending = false;
        this.stunSlamSecondPhase = false;
        if (this.swingReleasePending) {
            AttackKeyController.Q();
        }
        this.swingReleasePending = false;
        this.swapActive = false;
        this.originalSlot = -1;
        this.tickCounter = 0;
        this.breachSwapPending = false;
        this.stunSlamActive = false;
    }

    private boolean isTargetSmashReady(EntityOtherPlayerMP entityOtherPlayerMP) {
        EnumHand enumHand = RotationUtil.q(entityOtherPlayerMP);
        if (enumHand == null) {
            return false;
        }
        ItemStack itemStack = entityOtherPlayerMP.i(enumHand);
        float f = entityOtherPlayerMP.d(enumHand);
        if (f <= 0.0f) {
            return false;
        }
        int n = itemStack.getItem().I(itemStack, entityOtherPlayerMP);
        float f2 = (float)n - f;
        return f2 > 5.0f;
    }

    public boolean F$src$Z$1746r4n() {
        if (this.axes.L().booleanValue()) {
            InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
            int n = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            for (int i = 0; i < 9; ++i) {
                ItemStack itemStack;
                if (i == n || (itemStack = inventoryPlayer.c(i)).isNull() || itemStack.getItem().isNull()) continue;
                Item item = itemStack.getItem();
                if (!this.axes.L().booleanValue() || !item.isInstance(MappedClasses.YP)) continue;
                return true;
            }
        }
        return false;
    }

    private EntityOtherPlayerMP g$src$Lgg_vape_wrapper_impl_EntityOtherPlayerMP_$16ze503() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        EntityOtherPlayerMP entityOtherPlayerMP = RotationUtil.m(entityPlayerSP, 5.0, 90.0, true);
        return entityOtherPlayerMP;
    }

    private boolean y$src$Z$17w89e2() {
        ItemStack itemStack;
        if (Minecraft.currentScreen().isNotNull()) {
            return false;
        }
        return this.limitToItems.L() == false || this.allowedItems.isValid(itemStack = Minecraft.thePlayer().getHeldItemHand(), false);
    }

    @EventHandler(A=EventPriority.HIGH)
    public void onSyntheticAttack(SyntheticAttackRequestEvent syntheticAttackRequestEvent) {
        int n = JavassistMappingTask.U();
        if (n == 0) {
            if (syntheticAttackRequestEvent.getSource() == this) {
                return;
            }
            this.handleAttack(new SyntheticAttackCancellationAdapter(syntheticAttackRequestEvent, null));
        }
    }

    @EventHandler(A=EventPriority.HIGH)
    public void onMouseButton(EventMouseButton eventMouseButton) {
        block4: {
            block3: {
                boolean bl;
                block2: {
                    int n = JavassistMappingTask.U();
                    bl = eventMouseButton.isKeybinding(Minecraft.gameSettings().F());
                    if (n != 0) break block2;
                    if (!bl) break block3;
                    bl = eventMouseButton.isDown();
                }
                if (bl) break block4;
            }
            return;
        }
        this.handleAttack(new PhysicalAttackCancellationAdapter(eventMouseButton, null));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean isTargetAttacking(EntityOtherPlayerMP entityOtherPlayerMP) {
        EnumHand enumHand = RotationUtil.q(entityOtherPlayerMP);
        if (enumHand != null) {
            float f = entityOtherPlayerMP.d(enumHand);
            return f > 0.0f;
        }
        return false;
    }

    private int S$src$I$17bc2fp() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        int n = inventoryPlayer.v();
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack;
            if (i == n || (itemStack = inventoryPlayer.c(i)).isNull() || itemStack.getItem().isNull() || !itemStack.getItem().isInstance(MappedClasses.YP)) continue;
            return i;
        }
        return -1;
    }

    public boolean a$src$Z$17j175e() {
        int n = this.findWeaponSlot();
        if (n < 0) {
            return false;
        }
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        ItemStack itemStack = inventoryPlayer.c(n);
        return this.isValidMace(itemStack, this.shouldFilterByEnchant());
    }

    private int f$src$I$17ls5pk() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = inventoryPlayer.c(i);
            if (!this.isValidMace(itemStack, this.shouldFilterByEnchant())) continue;
            return i;
        }
        return -1;
    }

    @EventHandler(A=EventPriority.HIGH)
    public void onTick(EventPreTick eventPreTick) {
        int n = JavassistMappingTask.O$src$I$5vfrz4();
        if (eventPreTick.getThePlayer().isNull()) {
            return;
        }
        if (this.swingReleasePending) {
            AttackKeyController.Q();
            this.swingReleasePending = false;
        }
        if (this.breachSwapPending && this.swapActive && this.tickCounter >= 1) {
            int n2 = this.f$src$I$17ls5pk();
            if (n2 != -1) {
                eventPreTick.getThePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n2);
                AttackKeyController.Q();
                AttackKeyController.u(this);
                this.swingReleasePending = true;
            }
            this.breachSwapPending = false;
            return;
        }
        if (this.swapAttackPending && this.swapActive) {
            AttackKeyController.Q();
            AttackKeyController.u(this);
            this.swingReleasePending = true;
            this.swapAttackPending = false;
            if (this.stunSlamActive) {
                this.stunSlamSecondPhase = true;
            }
        }
        if (this.stunSlamActive && this.swapActive && this.stunSlamSecondPhase && this.tickCounter >= 1) {
            int n3 = this.f$src$I$17ls5pk();
            if (n3 != -1) {
                eventPreTick.getThePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n3);
                AttackKeyController.Q();
                AttackKeyController.u(this);
                this.swingReleasePending = true;
            }
            this.stunSlamSecondPhase = false;
            this.stunSlamActive = false;
            return;
        }
        if (this.swapActive) {
            int n4;
            int n5 = n4 = this.stunSlamActive || this.stunSlamSecondPhase ? 2 : 1;
            if (this.tickCounter++ > n4) {
                if (this.originalSlot != -1) {
                    eventPreTick.getThePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.originalSlot);
                    this.originalSlot = -1;
                }
                this.swapActive = false;
                this.tickCounter = 0;
                this.stunSlamActive = false;
                this.stunSlamSecondPhase = false;
            }
        }
    }

    private void handleAttack(AttackCancellationAdapter attackCancellationAdapter) {
        int n;
        int n2;
        EntityOtherPlayerMP entityOtherPlayerMP;
        ItemStack itemStack;
        if (this.swapActive) {
            return;
        }
        if (!this.y$src$Z$17w89e2()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        if (entityPlayerSP.l$src$Z$1io4duf()) {
            return;
        }
        RayTraceResult rayTraceResult = RotationManager.b.n();
        if (rayTraceResult.getEntity().isNull()) {
            return;
        }
        if (this.stunSlam.L().booleanValue() && this.maces.L().booleanValue() && (itemStack = entityPlayerSP.getHeldItemHand()).isNotNull() && itemStack.getItem().isNotNull() && (entityOtherPlayerMP = this.g$src$Lgg_vape_wrapper_impl_EntityOtherPlayerMP_$16ze503()) != null && this.isTargetSmashReady(entityOtherPlayerMP) && (n2 = this.f$src$I$17ls5pk()) >= 0) {
            if (itemStack.getItem().isInstance(MappedClasses.YP)) {
                this.originalSlot = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
                this.breachSwapPending = true;
                this.swapActive = true;
                this.tickCounter = 0;
                return;
            }
            int n3 = this.S$src$I$17bc2fp();
            if (n3 >= 0) {
                attackCancellationAdapter.u(true);
                this.originalSlot = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n3);
                this.stunSlamActive = true;
                this.swapActive = true;
                this.tickCounter = 0;
                this.swapAttackPending = true;
                return;
            }
        }
        if ((n = this.findWeaponSlot()) >= 0) {
            attackCancellationAdapter.u(true);
            this.originalSlot = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
            this.swapActive = true;
            this.tickCounter = 0;
            this.swapAttackPending = true;
        }
    }

    private boolean isValidMace(ItemStack itemStack, boolean bl) {
        if (this.smashOnly.L().booleanValue() && !RotationUtil.u(Minecraft.thePlayer())) {
            return false;
        }
        if (itemStack.isNull()) {
            return false;
        }
        if (itemStack.getItem().isNull()) {
            return false;
        }
        Item item = itemStack.getItem();
        if (item.isInstance(MappedClasses.zx)) {
            int n;
            if (!bl) {
                return true;
            }
            if (this.densityMaces.L().booleanValue() && (n = EnchantmentHelper.e("density", itemStack)) > 0) {
                return true;
            }
            if (this.breachMaces.L().booleanValue() && (n = EnchantmentHelper.e("breach", itemStack)) > 0) {
                return true;
            }
            return this.densityMaces.L() == false && this.breachMaces.L() == false;
        }
        return false;
    }

    public AutoMace() {
        super("HitSwap", (int)v, Category.Y, "Swaps into another weapon on attack, copying its attributes\nAKA BreachSwap, ZeroTick");
        this.smashOnly = BooleanValue.create(this, "Smash only", true, "Only swap to mace if will smash");
        this.breachMaces = BooleanValue.create(this, "Breach maces", false, "Will use Maces with Breach enchantment");
        this.densityMaces = BooleanValue.create(this, "Density maces", true, "Will use Maces with Breach enchantment");
        this.stunSlam = BooleanValue.create(this, "Stun slam", false, "When holding an axe and attacking a shielded player:\nHits with axe first (breaks shield), then swaps to mace for a follow-up slam");
        this.axes = BooleanValue.create(this, "Axes", true);
        this.swords = BooleanValue.create(this, "Swords", false);
        this.allowedItems = LimitValue.n(this, "bs-alloweditems", "Allowed Items", LimitValue.r, Arrays.asList(new ItemLimitData("swords")));
        this.addValue(this.maces, this.smashOnly, this.breachMaces, this.densityMaces, this.stunSlam, this.axes, this.swords, this.limitToItems, this.allowedItems);
        this.limitToItems.K(this.allowedItems);
        this.maces.K(this.smashOnly, this.breachMaces, this.densityMaces, this.stunSlam);
    }

    private int findWeaponSlot() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        int n = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        for (int i = 0; i < 9; ++i) {
            EnumHand enumHand;
            EntityOtherPlayerMP entityOtherPlayerMP;
            ItemStack itemStack;
            if (i == n || (itemStack = inventoryPlayer.c(i)).isNull() || itemStack.getItem().isNull()) continue;
            Item item = itemStack.getItem();
            if (this.axes.L().booleanValue() && item.isInstance(MappedClasses.YP) && (entityOtherPlayerMP = this.g$src$Lgg_vape_wrapper_impl_EntityOtherPlayerMP_$16ze503()) != null && (enumHand = RotationUtil.q(entityOtherPlayerMP)) != null) {
                ItemStack itemStack2 = entityOtherPlayerMP.i(enumHand);
                float f = entityOtherPlayerMP.d(enumHand);
                int n2 = itemStack2.getItem().I(itemStack2, entityOtherPlayerMP);
                if (f > 0.0f) {
                    float f2 = (float)n2 - f;
                    if (f2 > 5.0f) {
                        return i;
                    }
                    return -2;
                }
            }
            if (this.maces.L().booleanValue() && this.isValidMace(itemStack, this.shouldFilterByEnchant())) {
                return i;
            }
            if (!this.swords.L().booleanValue() || !ItemStackScoreUtil.h(item)) continue;
            return i;
        }
        return -1;
    }
}

