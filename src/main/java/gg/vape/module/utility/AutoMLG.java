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
    private AutoMLGState state;
    private boolean placingActive = false;
    private final BooleanValue onLethalFall;
    @Nullable
    private BlockCoordinate lastPlacePos = null;
    public final NumberValue c;
    public final BooleanValue kF;
    private final BlockInHotbarSlotHelper slotHelper;
    private final BooleanValue onXDamage = BooleanValue.create(this, "On at least X damage", false, "Activate MLG when the fall will do at least X health");
    @NotNull
    private final TimerUtil equipTimer;
    @NotNull
    private final TimerUtil conserveTimer;
    @Nullable
    private BlockCoordinate targetCoordinate = null;
    @Nullable
    private ItemMappingEntry mlgItem = null;
    private double lastHealth = 0.0;
    @Nullable
    public FixedRotationController V = null;
    public final LimitValue j;
    public final Queue<InventoryClick> Z;
    private final RandomValue clickDelayValue;
    private boolean guiOpenedByMlg = false;
    private final NumberValue healthValue;
    @Nullable
    private BlockPlacementGraph placementGraph = null;
    static final boolean DEBUG = false;
    public final BooleanValue K;
    @NotNull
    public final TimerUtil J;
    private double accumulatedFall = 0.0;
    private static final long MAGIC_ID = 7584752828418109695L;
    private final BooleanValue pickUpWater;
    public final BooleanValue k_;
    @Nullable
    public FixedRotationController C = null;
    public final AutoMLGPlacementController p;
    public final BooleanValue F;

    private void logStack(String string, StackTraceElement stackTraceElement) {
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
        if (this.accumulatedFall < 2.0 || entityPlayerSP.q() == 0.0 || modelPlayer.isCreativeMode() || modelPlayer.N() || modelPlayer.isFlying() || entityPlayerSP.Q$src$Z$fh9faz() || entityPlayerSP.M$src$Z$ff28xj() || entityPlayerSP.k$src$Z$15enw27() || ForgeVersion.MC_1_16_5.d() && entityPlayerSP.X$src$Z$1id4hz7() || entityPlayerSP.b$src$Z$fqlxe4() || entityPlayerSP.S$src$Z$151gttj() || entityPlayerSP.h$src$Z$ftwoya() || entityPlayerSP.d() || entityPlayerSP.D$src$Z$fa43la()) {
            return false;
        }
        if (Vape.INSTANCE.getModManager().getMod(Fly.class).r$src$Z$14eylz9()) {
            return false;
        }
        float f3 = 0.0f;
        BlockCoordinate blockCoordinate = BlockPlacementUtility.S(false, 50, entityPlayerSP, this.getPlacementGraph(entityPlayerSP, true));
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
        if ((f2 = Math.max(f3, f = BlockPlacementUtility.u(entityPlayerSP, true, false, this.getPlacementGraph(entityPlayerSP, false)))) <= 0.0f) {
            return false;
        }
        float f4 = BlockPlacementUtility.Z(entityPlayerSP, f2);
        double d = (Double)this.healthValue.K();
        float f5 = entityPlayerSP.w$src$F$15l9epb() - f4;
        if (this.onXDamage.L().booleanValue() && (double)f4 >= d) {
            return true;
        }
        return this.onLethalFall.L() != false && f5 <= 0.0f;
    }

    void d(String string, Object ... objectArray) {
    }


    public AutoMLG() {
        super("MLG", (int)MAGIC_ID, Category.m, "Automatically places water under you when you fall");
        this.onLethalFall = BooleanValue.create(this, "On lethal fall", true, "Activate MLG when the fall would deal enough damage to kill you");
        this.c = NumberValue.create(this, "Aim speed", "#.#", "", 5.0, 5.0, 15.0, 0.5, "How quickly MLG will change your look angles");
        this.pickUpWater = BooleanValue.create(this, "Pick up water", false, "Pick up placed water back into the bucket");
        this.equipTimer = new TimerUtil();
        this.kN = new TimerUtil();
        this.clickDelayValue = RandomValue.G(this, "Click Delay", "#", "ms", 50.0, 75.0, 125.0, 200.0, 5.0, "Delay used between inv clicks");
        this.J = new TimerUtil();
        this.conserveTimer = new TimerUtil();
        this.Z = new ConcurrentLinkedQueue<InventoryClick>();
        this.F = BooleanValue.create(this, "Silent aim", false, "Uses Silent Aim system");
        this.k_ = BooleanValue.create(this, "Use buckets", true, "Allow use of water buckets to save from fall damage");
        this.K = BooleanValue.create(this, "Use cobwebs", true, "Allow use of cobwebs to save from fall damage");
        this.kF = BooleanValue.create(this, "Check inventory", true, "Retrieves MLG Item to use from Inventory if not in Hotbar");
        this.j = LimitValue.n(this, "mlg-whitelisteditems", "Non-removable Items", LimitValue.r, Arrays.asList(new ItemLimitData("Water Bucket"), new ItemLimitData("Bucket"), new ItemLimitData("Cobweb")));
        this.healthValue = NumberValue.create(this, "Health", "#", "", 1.0, 5.0, 20.0, 1.0, "Min amount of fall damage for activation");
        this.state = AutoMLGState.IDLE;
        this.k_.K(this.pickUpWater);
        this.onXDamage.K(this.healthValue);
        this.kF.K(this.clickDelayValue);
        this.addValue(this.k_, this.pickUpWater, this.K, this.kF, this.clickDelayValue, this.F, this.c, this.onLethalFall, this.onXDamage, this.healthValue);
        this.p = new AutoMLGPlacementController(this);
        this.slotHelper = new BlockInHotbarSlotHelper(this);
        SharedModuleControlClaims.I.l(this, 10);
    }

    private void releaseRotation(FixedRotationController fixedRotationController, boolean releaseClaim, boolean restoreAdaptive) {
        if (fixedRotationController == null) {
            return;
        }
        if (fixedRotationController.equals(RotationManager.b.w())) {
            RotationManager.b.v(fixedRotationController);
        }
        fixedRotationController.w(false);
        if (restoreAdaptive && fixedRotationController instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)fixedRotationController).b(false);
            fixedRotationController.u(true);
        }
        if (releaseClaim) {
            SharedModuleControlClaims.I.X(this);
        }
    }

    void a$src$V$1ortsua(String string) {
    }

    @NotNull
    public HotbarSlotResolution M$src$Lgg_vape_module_blatant_blockin_HotbarSlotResolu$1womqjg() {
        if (ItemStackActionPredicate.o()) {
            this.guiOpenedByMlg = true;
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
            this.guiOpenedByMlg = true;
            return HotbarSlotResolution.J("Waiting for inventory to open");
        }
        return HotbarSlotResolution.J("Waiting for inventory delay before opening GUI.");
    }

    @EventHandler
    public void onRightClickMouse(EventRightClickMouse eventRightClickMouse) {
        if (!this.placingActive || this.lastPlacePos == null) {
            return;
        }
        RayTraceResult rayTraceResult = BlockPlacementUtility.A(BlockPlacementUtility.U());
        if (rayTraceResult == null || rayTraceResult.isNull() || rayTraceResult.getBlockPos().isNull()) {
            return;
        }
        int hitX = rayTraceResult.g();
        int targetX = this.lastPlacePos.B();
        int hitY = rayTraceResult.T();
        int targetY = this.lastPlacePos.E();
        int hitZ = rayTraceResult.a$src$I$8nuo9d();
        int targetZ = this.lastPlacePos.A();
        if (hitX != targetX || hitY != targetY || hitZ != targetZ) {
            return;
        }
    }

    @NotNull
    public HotbarSlotResolution f$src$Lgg_vape_module_blatant_blockin_HotbarSlotResolu$1985fcl() {
        if (!ItemStackActionPredicate.L()) {
            this.guiOpenedByMlg = false;
            return HotbarSlotResolution.j("No GUI is open.");
        }
        if (!this.guiOpenedByMlg) {
            return HotbarSlotResolution.W("In a GUI opened by the player (not MLG), cannot close it.").A();
        }
        if (this.g$src$Z$1ths2vi()) {
            this.kN.reset();
            boolean bl = ItemStackActionPredicate.f();
            if (!bl) {
                return HotbarSlotResolution.j("GUI closed.");
            }
            this.guiOpenedByMlg = false;
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
        this.resetState();
    }

    private BlockPlacementGraph getPlacementGraph(EntityPlayerSP entityPlayerSP, boolean forceRebuild) {
        if (forceRebuild || this.placementGraph == null) {
            this.placementGraph = new BlockPlacementGraph(entityPlayerSP);
        }
        return this.placementGraph;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.resetState();
    }

    public boolean g$src$Z$1ths2vi() {
        return this.clickDelayValue.M() <= 0.0 || this.kN.hasTimeElapsed((long)this.clickDelayValue.B());
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        HotbarSlotResolution hotbarSlotResolution;
        HotbarSlotResolutionWithValue<?> hotbarSlotResolutionWithValue;
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        double health = entityPlayerSP.N();
        double healthDelta = health - this.lastHealth;
        if (healthDelta > 0.0) {
            this.accumulatedFall = 0.0;
        } else if (healthDelta < 0.0) {
            this.accumulatedFall -= healthDelta;
        }
        if (entityPlayerSP.b$src$Z$fqlxe4()) {
            this.accumulatedFall = 0.0;
        }
        this.lastHealth = health;
        if (this.state == AutoMLGState.IDLE && !this.V$src$Z$1t8fksd()) {
            return;
        }
        if (entityPlayerSP.b$src$Z$fqlxe4() && (this.state == AutoMLGState.EQUIPPING_ITEM || this.state == AutoMLGState.AIMING)) {
            this.resetState();
            return;
        }
        if (entityPlayerSP.N() < (double)eventPreTick.getWorld().R()) {
            this.resetState();
            return;
        }
        if (this.state == AutoMLGState.AIMING || this.state == AutoMLGState.EQUIPPING_ITEM) {
            this.targetCoordinate = BlockPlacementUtility.S(false, 50, entityPlayerSP, this.getPlacementGraph(entityPlayerSP, true));
            HotbarSlotResolutionWithValue<Slot> slotResolution = this.slotHelper.p();
            if (!slotResolution.B()) {
                this.resetState();
                return;
            }
            if (!slotResolution.v()) {
                return;
            }
            Slot slot = slotResolution.w();
            if (slot == null || slot.isNull()) {
                this.resetState();
                return;
            }
            this.mlgItem = BlockPlacementUtility.l(slot);
            if (this.state == AutoMLGState.EQUIPPING_ITEM) {
                this.state = AutoMLGState.AIMING;
            }
        }
        switch (AutoMLGStateSwitchMap.stateOrdinalMap[this.state.ordinal()]) {
            case 1: {
                this.state = AutoMLGState.EQUIPPING_ITEM;
                this.equipTimer.reset();
            }
            case 2: {
                break;
            }
            case 3: {
                if (this.C == null) {
                    this.C = this.p.V(this.targetCoordinate, this.mlgItem);
                }
                if (this.mlgItem == null) {
                    this.resetState();
                    return;
                }
                hotbarSlotResolution = this.p.t(this.mlgItem, this.targetCoordinate, this.C, false);
                if (!hotbarSlotResolution.B()) {
                    this.resetState();
                    return;
                }
                if (!hotbarSlotResolution.v()) {
                    return;
                }
                HotbarSlotResolutionWithValue<BlockPos> placementResolution = this.p.D(this.mlgItem, this.targetCoordinate, null);
                if (!placementResolution.B()) {
                    this.resetState();
                    return;
                }
                if (!placementResolution.v()) {
                    return;
                }
                BlockPos blockPos = placementResolution.w();
                if (blockPos != null && blockPos.isNotNull()) {
                    this.lastPlacePos = new BlockCoordinate(blockPos.P(), blockPos.o(), blockPos.d());
                }
                if (BlockPlacementUtility.Y().equals(this.mlgItem) && this.pickUpWater.L().booleanValue()) {
                    this.J.reset();
                    this.conserveTimer.reset();
                    this.state = AutoMLGState.CONSERVING_WATER;
                    break;
                }
                this.resetState();
                return;
            }
            case 4: {
                this.placingActive = true;
                if (!this.conserveTimer.hasTimeElapsed(100L)) {
                    return;
                }
                HotbarSlotResolution aimResolution = this.handleWaterAim();
                hotbarSlotResolution = this.slotHelper.y(this.lastPlacePos, this.conserveTimer);
                if (!hotbarSlotResolution.B()) {
                    this.resetState();
                    return;
                }
                if (!aimResolution.B()) {
                    this.resetState();
                    return;
                }
                if (hotbarSlotResolution.v()) {
                    this.resetState();
                }
                return;
            }
        }
    }

    private HotbarSlotResolution handleWaterAim() {
        if (this.lastPlacePos == null) {
            return HotbarSlotResolution.W("Cannot handle water aim job, lastPlacePos is null.");
        }
        if (this.V == null) {
            this.V = this.p.V(this.lastPlacePos, BlockPlacementUtility.U());
        }
        return this.p.t(BlockPlacementUtility.U(), this.lastPlacePos, this.V, true);
    }

    private void resetState() {
        this.placingActive = false;
        this.equipTimer.reset();
        this.J.reset();
        this.conserveTimer.reset();
        this.Z.clear();
        if (this.C != null) {
            this.releaseRotation(this.C, true, false);
        }
        if (this.V != null) {
            this.releaseRotation(this.V, true, false);
        }
        this.C = null;
        this.V = null;
        this.mlgItem = null;
        this.guiOpenedByMlg = false;
        this.lastPlacePos = null;
        this.placementGraph = null;
        this.targetCoordinate = null;
        this.state = AutoMLGState.IDLE;
        this.accumulatedFall = 0.0;
        this.lastHealth = 0.0;
        this.kN.reset();
    }
}
