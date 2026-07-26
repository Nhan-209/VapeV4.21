package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRightClickMouse;
import gg.vape.inventory.InventoryClick;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.Fly;
import gg.vape.module.blatant.blockin.BlockInHotbarSlotHelper;
import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.module.blatant.blockin.BlockPlacementUtility;
import gg.vape.module.blatant.blockin.HotbarSlotResolution;
import gg.vape.module.blatant.blockin.HotbarSlotResolutionWithValue;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.utility.AutoMLGPlacementController;
import gg.vape.module.utility.AutoMLGState;
import gg.vape.module.utility.AutoMLGStateSwitchMap;
import gg.vape.module.utility.inventory.ItemStackActionPredicate;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ItemLimitData;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.ModelPlayer;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.Slot;
import gg.vape.wrapper.impl.World;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AutoMLG
extends Mod {
    @NotNull
    public final TimerUtil kN;
    private AutoMLGState a;
    private boolean s = false;
    private final BooleanValue U;
    @Nullable
    private BlockCoordinate O = null;
    public final NumberValue c;
    public final BooleanValue kF;
    private final BlockInHotbarSlotHelper v;
    private final BooleanValue I = BooleanValue.create(this, "On at least X damage", false, "Activate MLG when the fall will do at least X health");
    @NotNull
    private final TimerUtil k;
    @NotNull
    private final TimerUtil b;
    @Nullable
    private BlockCoordinate D = null;
    @Nullable
    private ItemMappingEntry r = null;
    private double L = 0.0;
    @Nullable
    public FixedRotationController V = null;
    public final LimitValue j;
    public final Queue<InventoryClick> Z;
    private final RandomValue A;
    private boolean t = false;
    private final NumberValue o;
    @Nullable
    private BlockPlacementGraph H = null;
    static final boolean Y = false;
    public final BooleanValue K;
    @NotNull
    public final TimerUtil J;
    private double S = 0.0;
    private static final long ib = 7584752828418109695L;
    private final BooleanValue P;
    public final BooleanValue k_;
    @Nullable
    public FixedRotationController C = null;
    public final AutoMLGPlacementController p;
    public final BooleanValue F;

    private void S(String string, StackTraceElement stackTraceElement) {
    }

    private boolean V$src$Z$1t8fksd() {
        float f;
        float f2;
        if (Minecraft.currentScreen().isNotNull()) {
            return false;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return false;
        }
        ModelPlayer modelPlayer = entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86();
        if (this.S < 2.0 || entityPlayerSP.q() == 0.0 || modelPlayer.isCreativeMode() || modelPlayer.N() || modelPlayer.isFlying() || entityPlayerSP.Q$src$Z$fh9faz() || entityPlayerSP.M$src$Z$ff28xj() || entityPlayerSP.k$src$Z$15enw27() || ForgeVersion.MC_1_16_5.d() && entityPlayerSP.X$src$Z$1id4hz7() || entityPlayerSP.b$src$Z$fqlxe4() || entityPlayerSP.S$src$Z$151gttj() || entityPlayerSP.h$src$Z$ftwoya() || entityPlayerSP.d() || entityPlayerSP.D$src$Z$fa43la()) {
            return false;
        }
        if (Vape.INSTANCE.getModManager().getMod(Fly.class).r$src$Z$14eylz9()) {
            return false;
        }
        float f3 = 0.0f;
        BlockCoordinate blockCoordinate = BlockPlacementUtility.S(false, 50, entityPlayerSP, this.q(entityPlayerSP, true));
        if (blockCoordinate != null) {
            World world = entityPlayerSP.getWorld();
            for (int i = 0; i <= 3; ++i) {
                Block block;
                BlockPos blockPos = blockCoordinate.E$src$Lgg_vape_wrapper_impl_BlockPos_$1bb1czr().W(i);
                BlockState blockState = world.getBlockState(blockPos);
                if (!blockState.isNotNull() || !(block = blockState.getBlock()).isNotNull() || !BlockUtil.C(block)) continue;
                return false;
            }
            f3 = (float)(entityPlayerSP.N() - (double)(blockCoordinate.E() + 1));
        }
        if ((f2 = Math.max(f3, f = BlockPlacementUtility.u(entityPlayerSP, true, false, this.q(entityPlayerSP, false)))) <= 0.0f) {
            return false;
        }
        float f4 = BlockPlacementUtility.Z(entityPlayerSP, f2);
        double d = (Double)this.o.K();
        float f5 = entityPlayerSP.w$src$F$15l9epb() - f4;
        if (this.I.L().booleanValue() && (double)f4 >= d) {
            return true;
        }
        return this.U.L() != false && f5 <= 0.0f;
    }

    void d(String string, Object ... objectArray) {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public AutoMLG() {
        super("MLG", (int)ib, Category.m, "Automatically places water under you when you fall");
        this.U = BooleanValue.create(this, "On lethal fall", true, "Activate MLG when the fall would deal enough damage to kill you");
        this.c = NumberValue.create(this, "Aim speed", "#.#", "", 5.0, 5.0, 15.0, 0.5, "How quickly MLG will change your look angles");
        this.P = BooleanValue.create(this, "Pick up water", false, "Pick up placed water back into the bucket");
        this.k = new TimerUtil();
        this.kN = new TimerUtil();
        this.A = RandomValue.G(this, "Click Delay", "#", "ms", 50.0, 75.0, 125.0, 200.0, 5.0, "Delay used between inv clicks");
        this.J = new TimerUtil();
        this.b = new TimerUtil();
        this.Z = new ConcurrentLinkedQueue<InventoryClick>();
        this.F = BooleanValue.create(this, "Silent aim", false, "Uses Silent Aim system");
        this.k_ = BooleanValue.create(this, "Use buckets", true, "Allow use of water buckets to save from fall damage");
        this.K = BooleanValue.create(this, "Use cobwebs", true, "Allow use of cobwebs to save from fall damage");
        this.kF = BooleanValue.create(this, "Check inventory", true, "Retrieves MLG Item to use from Inventory if not in Hotbar");
        this.j = LimitValue.n(this, "mlg-whitelisteditems", "Non-removable Items", LimitValue.r, Arrays.asList(new ItemLimitData("Water Bucket"), new ItemLimitData("Bucket"), new ItemLimitData("Cobweb")));
        this.o = NumberValue.create(this, "Health", "#", "", 1.0, 5.0, 20.0, 1.0, "Min amount of fall damage for activation");
        this.a = AutoMLGState.IDLE;
        this.k_.K(this.P);
        this.I.K(this.o);
        this.kF.K(this.A);
        this.addValue(this.k_, this.P, this.K, this.kF, this.A, this.F, this.c, this.U, this.I, this.o);
        this.p = new AutoMLGPlacementController(this);
        this.v = new BlockInHotbarSlotHelper(this);
        SharedModuleControlClaims.I.l(this, 10);
    }

    private void K(FixedRotationController fixedRotationController, boolean bl, boolean bl2) {
        if (fixedRotationController == null) {
            return;
        }
        if (fixedRotationController.equals(RotationManager.b.w())) {
            RotationManager.b.v(fixedRotationController);
        }
        fixedRotationController.w(false);
        if (bl2 && fixedRotationController instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)fixedRotationController).b(false);
            fixedRotationController.u(true);
        }
        if (bl) {
            SharedModuleControlClaims.I.X(this);
        }
    }

    void a$src$V$1ortsua(String string) {
    }

    @NotNull
    public HotbarSlotResolution M$src$Lgg_vape_module_blatant_blockin_HotbarSlotResolu$1womqjg() {
        if (ItemStackActionPredicate.o()) {
            this.t = true;
            return HotbarSlotResolution.j("Inventory GUI is open.");
        }
        if (ItemStackActionPredicate.L()) {
            HotbarSlotResolution hotbarSlotResolution = this.f$src$Lgg_vape_module_blatant_blockin_HotbarSlotResolu$1985fcl();
            if (hotbarSlotResolution.h()) {
                return HotbarSlotResolution.W(String.format("Cannot open inventory GUI because we cannot exit our current GUI due to: %s", hotbarSlotResolution.b())).i(hotbarSlotResolution.B());
            }
            return hotbarSlotResolution.v() ? HotbarSlotResolution.J("Closed existing GUI, will open inventory next tick.") : HotbarSlotResolution.J("Waiting to close GUI");
        }
        if (this.g$src$Z$1ths2vi()) {
            this.kN.reset();
            boolean bl = ItemStackActionPredicate.V();
            if (!bl) {
                return HotbarSlotResolution.j("Opened inventory GUI");
            }
            this.t = true;
            return HotbarSlotResolution.J("Waiting for inventory to open");
        }
        return HotbarSlotResolution.J("Waiting for inventory delay before opening GUI.");
    }

    @EventHandler
    public void A(EventRightClickMouse eventRightClickMouse) {
        if (!this.s || this.O == null) {
            return;
        }
        RayTraceResult rayTraceResult = BlockPlacementUtility.A(BlockPlacementUtility.U());
        if (rayTraceResult == null || rayTraceResult.isNull() || rayTraceResult.getBlockPos().isNull()) {
            return;
        }
        int n = rayTraceResult.g();
        int n2 = this.O.B();
        int n3 = rayTraceResult.T();
        int n4 = this.O.E();
        int n5 = rayTraceResult.a$src$I$8nuo9d();
        int n6 = this.O.A();
        if (n != n2 || n3 != n4 || n5 != n6) {
            return;
        }
    }

    @NotNull
    public HotbarSlotResolution f$src$Lgg_vape_module_blatant_blockin_HotbarSlotResolu$1985fcl() {
        if (!ItemStackActionPredicate.L()) {
            this.t = false;
            return HotbarSlotResolution.j("No GUI is open.");
        }
        if (!this.t) {
            return HotbarSlotResolution.W("In a GUI opened by the player (not MLG), cannot close it.").A();
        }
        if (this.g$src$Z$1ths2vi()) {
            this.kN.reset();
            boolean bl = ItemStackActionPredicate.f();
            if (!bl) {
                return HotbarSlotResolution.j("GUI closed.");
            }
            this.t = false;
            return HotbarSlotResolution.J("Waiting for GUI to close.");
        }
        return HotbarSlotResolution.J("Waiting for inventory click to be available");
    }

    public boolean A() {
        return SharedModuleControlClaims.I.U(this) || SharedModuleControlClaims.I.h(this, this.F.L());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.x();
    }

    private BlockPlacementGraph q(EntityPlayerSP entityPlayerSP, boolean bl) {
        if (bl || this.H == null) {
            this.H = new BlockPlacementGraph(entityPlayerSP);
        }
        return this.H;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.x();
    }

    public boolean g$src$Z$1ths2vi() {
        return this.A.M() <= 0.0 || this.kN.hasTimeElapsed((long)this.A.B());
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        HotbarSlotResolution hotbarSlotResolution;
        HotbarSlotResolutionWithValue<?> hotbarSlotResolutionWithValue;
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        double d = entityPlayerSP.N();
        double d2 = d - this.L;
        if (d2 > 0.0) {
            this.S = 0.0;
        } else if (d2 < 0.0) {
            this.S -= d2;
        }
        if (entityPlayerSP.b$src$Z$fqlxe4()) {
            this.S = 0.0;
        }
        this.L = d;
        if (this.a == AutoMLGState.IDLE && !this.V$src$Z$1t8fksd()) {
            return;
        }
        if (entityPlayerSP.b$src$Z$fqlxe4() && (this.a == AutoMLGState.EQUIPPING_ITEM || this.a == AutoMLGState.AIMING)) {
            this.x();
            return;
        }
        if (entityPlayerSP.N() < (double)eventPreTick.getWorld().R()) {
            this.x();
            return;
        }
        if (this.a == AutoMLGState.AIMING || this.a == AutoMLGState.EQUIPPING_ITEM) {
            this.D = BlockPlacementUtility.S(false, 50, entityPlayerSP, this.q(entityPlayerSP, true));
            HotbarSlotResolutionWithValue<Slot> slotResolution = this.v.p();
            if (!slotResolution.B()) {
                this.x();
                return;
            }
            if (!slotResolution.v()) {
                return;
            }
            Slot slot = slotResolution.w();
            if (slot == null || slot.isNull()) {
                this.x();
                return;
            }
            this.r = BlockPlacementUtility.l(slot);
            if (this.a == AutoMLGState.EQUIPPING_ITEM) {
                this.a = AutoMLGState.AIMING;
            }
        }
        switch (AutoMLGStateSwitchMap.u[this.a.ordinal()]) {
            case 1: {
                this.a = AutoMLGState.EQUIPPING_ITEM;
                this.k.reset();
            }
            case 2: {
                break;
            }
            case 3: {
                if (this.C == null) {
                    this.C = this.p.V(this.D, this.r);
                }
                if (this.r == null) {
                    this.x();
                    return;
                }
                hotbarSlotResolution = this.p.t(this.r, this.D, this.C, false);
                if (!hotbarSlotResolution.B()) {
                    this.x();
                    return;
                }
                if (!hotbarSlotResolution.v()) {
                    return;
                }
                HotbarSlotResolutionWithValue<BlockPos> placementResolution = this.p.D(this.r, this.D, null);
                if (!placementResolution.B()) {
                    this.x();
                    return;
                }
                if (!placementResolution.v()) {
                    return;
                }
                BlockPos blockPos = placementResolution.w();
                if (blockPos != null && blockPos.isNotNull()) {
                    this.O = new BlockCoordinate(blockPos.P(), blockPos.o(), blockPos.d());
                }
                if (BlockPlacementUtility.Y().equals(this.r) && this.P.L().booleanValue()) {
                    this.J.reset();
                    this.b.reset();
                    this.a = AutoMLGState.CONSERVING_WATER;
                    break;
                }
                this.x();
                return;
            }
            case 4: {
                this.s = true;
                if (!this.b.hasTimeElapsed(100L)) {
                    return;
                }
                HotbarSlotResolution aimResolution = this.v();
                hotbarSlotResolution = this.v.y(this.O, this.b);
                if (!hotbarSlotResolution.B()) {
                    this.x();
                    return;
                }
                if (!aimResolution.B()) {
                    this.x();
                    return;
                }
                if (hotbarSlotResolution.v()) {
                    this.x();
                }
                return;
            }
        }
    }

    private HotbarSlotResolution v() {
        if (this.O == null) {
            return HotbarSlotResolution.W("Cannot handle water aim job, lastPlacePos is null.");
        }
        if (this.V == null) {
            this.V = this.p.V(this.O, BlockPlacementUtility.U());
        }
        return this.p.t(BlockPlacementUtility.U(), this.O, this.V, true);
    }

    private void x() {
        this.s = false;
        this.k.reset();
        this.J.reset();
        this.b.reset();
        this.Z.clear();
        if (this.C != null) {
            this.K(this.C, true, false);
        }
        if (this.V != null) {
            this.K(this.V, true, false);
        }
        this.C = null;
        this.V = null;
        this.r = null;
        this.t = false;
        this.O = null;
        this.H = null;
        this.D = null;
        this.a = AutoMLGState.IDLE;
        this.S = 0.0;
        this.L = 0.0;
        this.kN.reset();
    }
}
