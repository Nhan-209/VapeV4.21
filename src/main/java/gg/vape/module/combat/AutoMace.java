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
    public final BooleanValue O;
    private boolean J = false;
    public final BooleanValue S;
    private int C;
    public final BooleanValue o;
    private boolean r = false;
    private boolean c = false;
    private boolean p = false;
    public final LimitValue U;
    private boolean L = false;
    public final BooleanValue P = BooleanValue.create(this, "Limit to items", false);
    private boolean j;
    public final BooleanValue Z;
    public final BooleanValue A;
    private int t = -1;
    private static final long v = -7666507152973844357L;
    public final BooleanValue V = BooleanValue.create(this, "Maces", true);
    public final BooleanValue H;

    private boolean P() {
        return (this.A.L() != false || this.O.L() != false) && this.z();
    }

    private boolean z() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = inventoryPlayer.c(i);
            if (!this.n(itemStack, true)) continue;
            return true;
        }
        return false;
    }

    @EventHandler(A=EventPriority.HIGH)
    public void D(EventKeyPress eventKeyPress) {
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
        this.p(new PhysicalAttackCancellationAdapter(eventKeyPress, null));
    }

    @Override
    public void onDisable() {
        this.L = false;
        this.c = false;
        if (this.J) {
            AttackKeyController.Q();
        }
        this.J = false;
        this.j = false;
        this.t = -1;
        this.C = 0;
        this.p = false;
        this.r = false;
    }

    private boolean g(EntityOtherPlayerMP entityOtherPlayerMP) {
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
        if (this.o.L().booleanValue()) {
            InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
            int n = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            for (int i = 0; i < 9; ++i) {
                ItemStack itemStack;
                if (i == n || (itemStack = inventoryPlayer.c(i)).isNull() || itemStack.getItem().isNull()) continue;
                Item item = itemStack.getItem();
                if (!this.o.L().booleanValue() || !item.isInstance(MappedClasses.YP)) continue;
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
        return this.P.L() == false || this.U.isValid(itemStack = Minecraft.thePlayer().getHeldItemHand(), false);
    }

    @EventHandler(A=EventPriority.HIGH)
    public void q(SyntheticAttackRequestEvent syntheticAttackRequestEvent) {
        int n = JavassistMappingTask.U();
        if (n == 0) {
            if (syntheticAttackRequestEvent.getSource() == this) {
                return;
            }
            this.p(new SyntheticAttackCancellationAdapter(syntheticAttackRequestEvent, null));
        }
    }

    @EventHandler(A=EventPriority.HIGH)
    public void o(EventMouseButton eventMouseButton) {
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
        this.p(new PhysicalAttackCancellationAdapter(eventMouseButton, null));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean h(EntityOtherPlayerMP entityOtherPlayerMP) {
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
        int n = this.c();
        if (n < 0) {
            return false;
        }
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        ItemStack itemStack = inventoryPlayer.c(n);
        return this.n(itemStack, this.P());
    }

    private int f$src$I$17ls5pk() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = inventoryPlayer.c(i);
            if (!this.n(itemStack, this.P())) continue;
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
        if (this.J) {
            AttackKeyController.Q();
            this.J = false;
        }
        if (this.p && this.j && this.C >= 1) {
            int n2 = this.f$src$I$17ls5pk();
            if (n2 != -1) {
                eventPreTick.getThePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n2);
                AttackKeyController.Q();
                AttackKeyController.u(this);
                this.J = true;
            }
            this.p = false;
            return;
        }
        if (this.L && this.j) {
            AttackKeyController.Q();
            AttackKeyController.u(this);
            this.J = true;
            this.L = false;
            if (this.r) {
                this.c = true;
            }
        }
        if (this.r && this.j && this.c && this.C >= 1) {
            int n3 = this.f$src$I$17ls5pk();
            if (n3 != -1) {
                eventPreTick.getThePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n3);
                AttackKeyController.Q();
                AttackKeyController.u(this);
                this.J = true;
            }
            this.c = false;
            this.r = false;
            return;
        }
        if (this.j) {
            int n4;
            int n5 = n4 = this.r || this.c ? 2 : 1;
            if (this.C++ > n4) {
                if (this.t != -1) {
                    eventPreTick.getThePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.t);
                    this.t = -1;
                }
                this.j = false;
                this.C = 0;
                this.r = false;
                this.c = false;
            }
        }
    }

    private void p(AttackCancellationAdapter attackCancellationAdapter) {
        int n;
        int n2;
        EntityOtherPlayerMP entityOtherPlayerMP;
        ItemStack itemStack;
        if (this.j) {
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
        if (this.S.L().booleanValue() && this.V.L().booleanValue() && (itemStack = entityPlayerSP.getHeldItemHand()).isNotNull() && itemStack.getItem().isNotNull() && (entityOtherPlayerMP = this.g$src$Lgg_vape_wrapper_impl_EntityOtherPlayerMP_$16ze503()) != null && this.g(entityOtherPlayerMP) && (n2 = this.f$src$I$17ls5pk()) >= 0) {
            if (itemStack.getItem().isInstance(MappedClasses.YP)) {
                this.t = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
                this.p = true;
                this.j = true;
                this.C = 0;
                return;
            }
            int n3 = this.S$src$I$17bc2fp();
            if (n3 >= 0) {
                attackCancellationAdapter.u(true);
                this.t = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n3);
                this.r = true;
                this.j = true;
                this.C = 0;
                this.L = true;
                return;
            }
        }
        if ((n = this.c()) >= 0) {
            attackCancellationAdapter.u(true);
            this.t = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
            this.j = true;
            this.C = 0;
            this.L = true;
        }
    }

    private boolean n(ItemStack itemStack, boolean bl) {
        if (this.Z.L().booleanValue() && !RotationUtil.u(Minecraft.thePlayer())) {
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
            if (this.A.L().booleanValue() && (n = EnchantmentHelper.e("density", itemStack)) > 0) {
                return true;
            }
            if (this.O.L().booleanValue() && (n = EnchantmentHelper.e("breach", itemStack)) > 0) {
                return true;
            }
            return this.A.L() == false && this.O.L() == false;
        }
        return false;
    }

    public AutoMace() {
        super("HitSwap", (int)v, Category.Y, "Swaps into another weapon on attack, copying its attributes\nAKA BreachSwap, ZeroTick");
        this.Z = BooleanValue.create(this, "Smash only", true, "Only swap to mace if will smash");
        this.O = BooleanValue.create(this, "Breach maces", false, "Will use Maces with Breach enchantment");
        this.A = BooleanValue.create(this, "Density maces", true, "Will use Maces with Breach enchantment");
        this.S = BooleanValue.create(this, "Stun slam", false, "When holding an axe and attacking a shielded player:\nHits with axe first (breaks shield), then swaps to mace for a follow-up slam");
        this.o = BooleanValue.create(this, "Axes", true);
        this.H = BooleanValue.create(this, "Swords", false);
        this.U = LimitValue.n(this, "bs-alloweditems", "Allowed Items", LimitValue.r, Arrays.asList(new ItemLimitData("swords")));
        this.addValue(this.V, this.Z, this.O, this.A, this.S, this.o, this.H, this.P, this.U);
        this.P.K(this.U);
        this.V.K(this.Z, this.O, this.A, this.S);
    }

    private int c() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        int n = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        for (int i = 0; i < 9; ++i) {
            EnumHand enumHand;
            EntityOtherPlayerMP entityOtherPlayerMP;
            ItemStack itemStack;
            if (i == n || (itemStack = inventoryPlayer.c(i)).isNull() || itemStack.getItem().isNull()) continue;
            Item item = itemStack.getItem();
            if (this.o.L().booleanValue() && item.isInstance(MappedClasses.YP) && (entityOtherPlayerMP = this.g$src$Lgg_vape_wrapper_impl_EntityOtherPlayerMP_$16ze503()) != null && (enumHand = RotationUtil.q(entityOtherPlayerMP)) != null) {
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
            if (this.V.L().booleanValue() && this.n(itemStack, this.P())) {
                return i;
            }
            if (!this.H.L().booleanValue() || !ItemStackScoreUtil.h(item)) continue;
            return i;
        }
        return -1;
    }
}

