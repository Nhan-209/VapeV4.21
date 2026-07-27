package gg.vape.module.utility;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.inventory.InventoryClick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.utility.MLGBlockWrapper;
import gg.vape.module.utility.MLGImpactState;
import gg.vape.module.utility.inventory.ItemStackActionPredicate;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockStateWorldBridge;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayerMacroBridge;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiContainer;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Material;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.SPacketEntityVelocity;
import gg.vape.wrapper.impl.Slot;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.jetbrains.annotations.Nullable;

public class MLG
extends Mod {
    private final BooleanValue refillRods;
    private final BooleanValue recastGround;
    private final TimerUtil stationaryTimer;
    private static final int RECAST_GROUND_DELAY_MS;
    private static final boolean DEBUG = false;
    private boolean biteDetected = false;
    private static final double VELOCITY_THRESHOLD = 0.05;
    private final Queue<InventoryClick> clickQueue;
    private boolean velocityBite = false;
    private boolean hasCast = false;
    private final ArrayDeque<KeyBinding> pressedKeys;
    private final TimerUtil castTimer;
    private double accumulatedMotionY = 0.0;
    private final TimerUtil clickTimer;
    private static final int STATIONARY_DELAY_MS;
    private final RandomValue clickDelay = RandomValue.G(this, "Click delay", "#", "ms", 50.0, 75.0, 125.0, 200.0, 5.0, "How long to wait between clicks in the inventory");
    private boolean refillPending = false;
    private static final boolean UNUSED_FLAG_A = false;
    private static final boolean UNUSED_FLAG_B = true;
    private static final boolean UNUSED_FLAG_C = false;
    private final ArrayDeque<KeyBinding> queuedKeys;
    private final BooleanValue recastCaught;
    private final TimerUtil groundTimer;

    private boolean cancelRefill() {
        if (this.refillPending) {
            this.clickQueue.clear();
            this.refillPending = false;
            return ItemStackActionPredicate.f();
        }
        return false;
    }


    private boolean pumpKeyPresses() {
        KeyBinding keyBinding;
        boolean bl = false;
        KeyBinding keyBinding2 = this.pressedKeys.poll();
        if (keyBinding2 != null && keyBinding2.isNotNull()) {
            KeyBindingHelper.v(keyBinding2, false, false);
            bl = true;
        }
        if ((keyBinding = this.queuedKeys.poll()) != null && keyBinding.isNotNull()) {
            KeyBindingHelper.v(keyBinding, true, true);
            bl = true;
            this.pressedKeys.add(keyBinding);
        }
        return bl;
    }

    static {
        STATIONARY_DELAY_MS = 1000;
        RECAST_GROUND_DELAY_MS = 3000;
    }

    private void logDebug(String string, Object ... objectArray) {
    }

    private boolean hasWaterBelow(EntityPlayerMacroBridge entityPlayerMacroBridge, WorldClient worldClient) {
        double d = entityPlayerMacroBridge.z();
        double d2 = entityPlayerMacroBridge.N();
        double d3 = entityPlayerMacroBridge.h();
        for (double d4 = d2; d4 >= 0.0 && d4 >= d2 - 3.0; d4 -= 1.0) {
            Block block = worldClient.getBlock(d, d4, d3);
            if (block == null || !block.isNotNull()) continue;
            if (BlockUtil.C(block)) {
                return true;
            }
            if (BlockUtil.p(block)) continue;
            return false;
        }
        return false;
    }

    private void log(String string) {
    }

    private boolean beginRefill() {
        this.refillPending = true;
        return ItemStackActionPredicate.V();
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        WorldClient worldClient = eventPreTick.getWorld();
        if (!this.isHoldingRod()) {
            InventoryClick inventoryClick;
            Slot slot = ItemStackActionPredicate.K(MappedClasses.Yi, MLGImpactState.D);
            if (slot != null && slot.isNotNull()) {
                int n;
                if (ItemStackActionPredicate.L()) {
                    if (this.refillPending) {
                        this.cancelRefill();
                    }
                    return;
                }
                InventoryPlayer inventoryPlayer = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
                if (inventoryPlayer.isNull()) {
                    return;
                }
                int n2 = inventoryPlayer.v();
                if (n2 == (n = slot.g() - 36)) {
                    return;
                }
                inventoryPlayer.g(n);
                return;
            }
            if (!this.refillRods.L().booleanValue()) {
                this.Y(false);
                return;
            }
            Slot slot2 = ItemStackActionPredicate.K(MappedClasses.Yi, MLGImpactState.i);
            if (slot2 == null || slot2.isNull()) {
                this.Y(false);
                return;
            }
            int n = slot2.g();
            if (!ItemStackActionPredicate.o()) {
                if (ItemStackActionPredicate.L()) {
                    if (this.refillPending) {
                        this.cancelRefill();
                    }
                    return;
                }
                this.beginRefill();
                return;
            }
            GuiContainer guiContainer = new GuiContainer(Minecraft.currentScreen().getObject());
            int n3 = guiContainer.getInventorySlots().getWindowId();
            if (this.clickQueue.isEmpty()) {
                this.clickQueue.add(new InventoryClick(n3, n, 0, 2));
                return;
            }
            if (this.clickTimer.hasTimeElapsed((long)this.clickDelay.B()) && (inventoryClick = this.clickQueue.poll()) != null) {
                this.performClick(inventoryClick, n3);
            }
            return;
        }
        if (ItemStackActionPredicate.L()) {
            if (this.refillPending) {
                this.cancelRefill();
            }
            return;
        }
        if (this.pumpKeyPresses()) {
            return;
        }
        EntityPlayerMacroBridge entityPlayerMacroBridge = this.getFishHook();
        if (entityPlayerMacroBridge == null || entityPlayerMacroBridge.isNull()) {
            this.recast();
            return;
        }
        Entity entity = entityPlayerMacroBridge.r$src$Lgg_vape_wrapper_impl_Entity_$18p7x3h();
        if (entity != null && entity.isNotNull()) {
            if (this.recastCaught.L().booleanValue()) {
                this.recast();
            } else {
                this.Y(false);
            }
            return;
        }
        if (!this.isHookInLiquid(entityPlayerMacroBridge, worldClient)) {
            if (this.recastGround.L().booleanValue() && this.groundTimer.hasTimeElapsed(3000L)) {
                this.recast();
            }
            return;
        }
        this.groundTimer.reset();
        double d = entityPlayerMacroBridge.q();
        if (!this.biteDetected) {
            double d2 = entityPlayerMacroBridge.t();
            double d3 = entityPlayerMacroBridge.T();
            double d4 = Math.abs(d2) + Math.abs(d) + Math.abs(d3);
            if (d4 <= 0.05) {
                if (this.stationaryTimer.hasTimeElapsed(1000L)) {
                    this.biteDetected = true;
                }
            } else {
                this.stationaryTimer.reset();
            }
            return;
        }
        this.accumulatedMotionY = d <= -0.1 ? (this.accumulatedMotionY += d) : 0.0;
        if (this.accumulatedMotionY <= -0.05 || this.velocityBite) {
            if (this.accumulatedMotionY <= -0.05) {
                // empty if block
            }
            this.recast();
            this.castTimer.reset();
            this.stationaryTimer.reset();
            this.groundTimer.reset();
            this.biteDetected = false;
            this.velocityBite = false;
        } else if (Math.abs(d) > 0.001) {
            // empty if block
        }
    }

    @Nullable
    private EntityPlayerMacroBridge getFishHook() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return null;
        }
        EntityPlayerMacroBridge entityPlayerMacroBridge = entityPlayerSP.K$src$Lgg_vape_wrapper_impl_EntityPlayerMacroBridge_$1agjn9();
        if (entityPlayerMacroBridge.isNotNull()) {
            return entityPlayerMacroBridge;
        }
        World world = entityPlayerSP.getWorld();
        if (world.isNull()) {
            return null;
        }
        ArrayList<EntityPlayerMacroBridge> arrayList = new ArrayList<EntityPlayerMacroBridge>();
        for (Object e : world.z()) {
            EntityPlayerMacroBridge entityPlayerMacroBridge2;
            Entity entity = new Entity(e);
            if (!entity.isInstance(MappedClasses.lM) || !entityPlayerSP.equals((entityPlayerMacroBridge2 = new EntityPlayerMacroBridge(entity.getObject())).A$src$Lgg_vape_wrapper_impl_Entity_$12ijiu4())) continue;
            arrayList.add(entityPlayerMacroBridge2);
        }
        switch (arrayList.size()) {
            case 0: {
                return null;
            }
            case 1: {
                return (EntityPlayerMacroBridge)arrayList.get(0);
            }
        }
        return null;
    }

    private boolean isHookInBlock(EntityPlayerMacroBridge entityPlayerMacroBridge, WorldClient worldClient) {
        BlockPos blockPos = entityPlayerMacroBridge.J$src$Lgg_vape_wrapper_impl_BlockPos_$kv8a0x();
        BlockStateWorldBridge blockStateWorldBridge = worldClient.o(blockPos);
        float f = 0.0f;
        if (blockStateWorldBridge.o(MLGBlockWrapper.t())) {
            f = blockStateWorldBridge.i(worldClient, blockPos);
        }
        return f > 0.0f;
    }

    private boolean isHookInWater(EntityPlayerMacroBridge entityPlayerMacroBridge, WorldClient worldClient) {
        int n = 5;
        double d = 0.0;
        for (int i = 0; i < n; ++i) {
            AxisAlignedBB axisAlignedBB = entityPlayerMacroBridge.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu();
            double d2 = axisAlignedBB.getMaxY() - axisAlignedBB.getMinY();
            double d3 = axisAlignedBB.getMinY() + d2 * (double)i / (double)n;
            double d4 = axisAlignedBB.getMinY() + d2 * (double)(i + 1) / (double)n;
            AxisAlignedBB axisAlignedBB2 = AxisAlignedBB.create(axisAlignedBB.getMinX(), d3, axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), d4, axisAlignedBB.getMaxZ());
            if (!entityPlayerMacroBridge.getWorld().h(axisAlignedBB2, Material.f())) continue;
            d += 1.0 / (double)n;
        }
        return d > 0.0;
    }

    private void recast() {
        boolean bl;
        EntityPlayerMacroBridge entityPlayerMacroBridge = this.getFishHook();
        boolean bl2 = bl = entityPlayerMacroBridge != null && entityPlayerMacroBridge.isNotNull();
        if (bl || !this.hasCast || this.castTimer.hasTimeElapsed(1000L)) {
            this.pressUseKey(Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362());
            this.castTimer.reset();
            this.groundTimer.reset();
            this.stationaryTimer.reset();
            this.hasCast = true;
            this.accumulatedMotionY = 0.0;
        }
    }

    private void pressUseKey(KeyBinding keyBinding) {
        KeyBindingHelper.v(keyBinding, true, true);
        this.pressedKeys.add(keyBinding);
    }

    public MLG() {
        super("AutoFish", 12452021, Category.m, "Automatically fishes for you.");
        this.clickQueue = new ConcurrentLinkedQueue<InventoryClick>();
        this.queuedKeys = new ArrayDeque();
        this.pressedKeys = new ArrayDeque();
        this.recastCaught = BooleanValue.create(this, "Recast caught", false, "Automatically recasts if the hook catches onto an entity");
        this.recastGround = BooleanValue.create(this, "Recast ground", false, "Automatically recasts if the hook hits the ground");
        this.clickTimer = new TimerUtil();
        this.castTimer = new TimerUtil();
        this.stationaryTimer = new TimerUtil();
        this.groundTimer = new TimerUtil();
        this.refillRods = BooleanValue.create(this, "Refill rods", true, "Automatically replaces broken rods with rods from your inventory.");
        this.refillRods.K(this.clickDelay);
        this.addValue(this.recastGround, this.recastCaught, this.refillRods, this.clickDelay);
    }

    private boolean isHookInLiquid(EntityPlayerMacroBridge entityPlayerMacroBridge, WorldClient worldClient) {
        if (entityPlayerMacroBridge.h$src$Z$ftwoya()) {
            return true;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.isHookInBlock(entityPlayerMacroBridge, worldClient);
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            return this.hasWaterBelow1122(entityPlayerMacroBridge, worldClient);
        }
        return this.isHookInWater(entityPlayerMacroBridge, worldClient) || this.hasWaterBelow(entityPlayerMacroBridge, worldClient);
    }

    private void performClick(InventoryClick inventoryClick, int n) {
        this.clickTimer.reset();
        int n2 = inventoryClick.t();
        if (n == n2) {
            inventoryClick.k();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.resetState();
    }

    private void resetState() {
        this.clickTimer.reset();
        this.castTimer.reset();
        this.stationaryTimer.reset();
        this.groundTimer.reset();
        this.clickQueue.clear();
        this.queuedKeys.clear();
        this.pressedKeys.clear();
        this.hasCast = false;
        this.biteDetected = false;
        this.velocityBite = false;
        this.refillPending = false;
        this.accumulatedMotionY = 0.0;
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        if (!this.biteDetected) {
            return;
        }
        EntityPlayerMacroBridge entityPlayerMacroBridge = this.getFishHook();
        if (entityPlayerMacroBridge == null || entityPlayerMacroBridge.isNull()) {
            return;
        }
        Packet packet = eventPacketReceive.getPacket();
        WorldClient worldClient = eventPacketReceive.getWorld();
        EntityPlayerSP entityPlayerSP = eventPacketReceive.getThePlayer();
        if (packet.isInstance(MappedClasses.YX)) {
            SPacketEntityVelocity sPacketEntityVelocity = new SPacketEntityVelocity(packet);
            if (sPacketEntityVelocity.getEntityId() != entityPlayerMacroBridge.S()) {
                return;
            }
            int n = sPacketEntityVelocity.getMotionX();
            int n2 = sPacketEntityVelocity.getMotionZ();
            double d = (double)sPacketEntityVelocity.getMotionY() / 8000.0;
            if (n == 0 && n2 == 0 && d <= -0.05) {
                this.velocityBite = true;
            }
            return;
        }
        if (packet.isInstance(MappedClasses.qz) && !packet.isInstance(MappedClasses.uJ)) {
            return;
        }
        if (packet.isInstance(MappedClasses.Dk)) {
            return;
        }
    }

    private boolean hasWaterBelow1122(EntityPlayerMacroBridge entityPlayerMacroBridge, WorldClient worldClient) {
        return this.hasWaterBelow(entityPlayerMacroBridge, worldClient);
    }

    private boolean isHoldingRod() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return false;
        }
        ItemStack itemStack = entityPlayerSP.getHeldItemHand();
        return !itemStack.isNull() && itemStack.getItem().isInstance(MappedClasses.Yi);
    }
}

