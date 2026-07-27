package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRightClickMouse;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.anchormacro.AnchorBlockHitTarget;
import gg.vape.module.blatant.anchormacro.AnchorMacroState;
import gg.vape.module.blatant.anchormacro.AnchorObstructionPlacementCandidate;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.utility.clutch.ClutchPlacementPathUtils;
import gg.vape.notification.NotificationType;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.PointRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ItemLimitData;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RayTraceUtil;
import gg.vape.utils.RotationVectorMath;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.datas.BlockData;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.Vec3i;
import gg.vape.wrapper.impl.World;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AnchorMacro
extends Mod {
    private final BooleanValue explosionItemWhitelist;
    private final BooleanValue safeAnchor;
    private BlockPos anchorPos;
    private boolean anchorPlaced;
    private final NumberValue aimSpeed;
    private int chargeRetries;
    private AnchorMacroState state;
    private final BooleanValue aimAssist;
    private EnumFacing hitFacing;
    private final RotationControlClaim rotationClaim = SharedModuleControlClaims.I;
    private BlockData shieldBlock;
    private int glowstoneSlot = -1;
    private static final int MAX_ANCHOR_FACE_RETRIES;
    private static final int SHIELD_MAX_TICKS;
    private final ModeOption onBindMode = new ModeOption("On bind");
    private final BooleanValue silentAim;
    private final TimerUtil timer;
    private int anchorSlot = -1;
    private static final int MAX_CHARGE_RETRIES;
    private int shieldChargeRetries;
    private int retries;
    private final RandomValue delay;
    private BlockPos baseBlockPos;
    private final ModeValue mode;
    private boolean doubleAnchorDone;
    private int shieldStep;
    private static final int MAX_PLACE_RETRIES;
    private final ModeOption onPlaceMode = new ModeOption("On place");
    private int waitRetries;
    private EnumFacing shieldFacing;
    private boolean shieldStarted;
    private boolean shieldPlaced;
    private final BooleanValue doubleAnchor;
    private FixedRotationController rotationController;
    private BlockPos shieldSupportPos;
    private int explosionSlot = -1;
    private int prevSlot = -1;
    private final LimitValue explosionItem;
    private long actionDelay;
    private static final int MAX_DETONATE_RETRIES;

    private float computeVisibilityRatio(Vec3 vec3, BlockData blockData, EntityPlayerSP entityPlayerSP) {
        AxisAlignedBB axisAlignedBB = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
        AxisAlignedBB axisAlignedBB2 = AxisAlignedBB.create(blockData.D(), blockData.B(), blockData.G(), (double)blockData.D() + 1.0, (double)blockData.B() + 1.0, (double)blockData.G() + 1.0);
        double d = axisAlignedBB.getMinX();
        double d2 = axisAlignedBB.getMinY();
        double d3 = axisAlignedBB.getMinZ();
        double d4 = axisAlignedBB.getMaxX();
        double d5 = axisAlignedBB.getMaxY();
        double d6 = axisAlignedBB.getMaxZ();
        double d7 = 1.0 / ((d4 - d) * 2.0 + 1.0);
        double d8 = 1.0 / ((d5 - d2) * 2.0 + 1.0);
        double d9 = 1.0 / ((d6 - d3) * 2.0 + 1.0);
        if (d7 < 0.0 || d8 < 0.0 || d9 < 0.0) {
            return 0.0f;
        }
        double d10 = (1.0 - Math.floor(1.0 / d7) * d7) / 2.0;
        double d11 = (1.0 - Math.floor(1.0 / d9) * d9) / 2.0;
        int n = 0;
        int n2 = 0;
        for (double d12 = 0.0; d12 <= 1.0; d12 += d7) {
            for (double d13 = 0.0; d13 <= 1.0; d13 += d8) {
                for (double d14 = 0.0; d14 <= 1.0; d14 += d9) {
                    double d15 = d + (d4 - d) * d12 + d10;
                    double d16 = d2 + (d5 - d2) * d13;
                    double d17 = d3 + (d6 - d3) * d14 + d11;
                    Vec3 vec32 = Vec3.create(d15, d16, d17);
                    RayTraceResult rayTraceResult = axisAlignedBB2.calculateIntercept(vec3, vec32);
                    if (rayTraceResult != null && rayTraceResult.isNotNull()) {
                        ++n;
                    }
                    ++n2;
                }
            }
        }
        return n2 > 0 ? (float)n / (float)n2 : 0.0f;
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private Vec3 centerOf(BlockPos blockPos) {
        return Vec3.create((double)blockPos.P() + 0.5, (double)blockPos.o() + 0.5, (double)blockPos.d() + 0.5);
    }

    public AnchorMacro() {
        super("AutoAnchor", -8109323, Category.Y, "Places, charges, and detonates a respawn anchor");
        this.mode = ModeValue.create((Object)this, "Mode", "On bind - places, charges, and detonates on keybind press\nOn place - automatically charges and detonates after you place an anchor", (ModeSelection)this.onBindMode, this.onBindMode, this.onPlaceMode);
        this.doubleAnchor = BooleanValue.create(this, "Double anchor", false, "Places a second anchor on detonation, then charges it\nWill require lower delay to work reliably");
        this.safeAnchor = BooleanValue.create(this, "Safe anchor", false, "Places a glowstone block between you and the anchor before charging\nto reduce explosion damage by providing block cover");
        this.explosionItemWhitelist = BooleanValue.create(this, "Explosion item whitelist", false, "Swaps to the first whitelisted hotbar item before exploding\ninstead of swapping back to the anchor");
        this.explosionItem = LimitValue.N(this, "autoanchor-explosionitemwhitelist", "Explosion item", LimitValue.r, new ItemLimitData("totem of undying"));
        this.aimAssist = BooleanValue.create(this, "Aim assist", true, "Holds center of the anchor target");
        this.silentAim = BooleanValue.create(this, "Silent aim", true, "Uses Silent Aim system");
        this.aimSpeed = NumberValue.create(this, "Aim speed", "#.#", "", 1.0, 12.0, 15.0, 0.1, "Speed of aim when placing/charging anchors");
        this.delay = RandomValue.G(this, "Delay", "#", "ms", 0.0, 50.0, 100.0, 500.0, 1.0, "Delay between each action");
        this.timer = new TimerUtil();
        this.state = AnchorMacroState.FINDING_ITEMS;
        this.addValue(this.mode, this.doubleAnchor, this.safeAnchor, this.explosionItemWhitelist, this.explosionItem, this.aimAssist, this.silentAim, this.aimSpeed, this.delay);
        this.explosionItemWhitelist.K(this.explosionItem);
        this.aimAssist.K(this.silentAim, this.aimSpeed);
        this.rotationClaim.l(this, 8);
    }

    static {
        SHIELD_MAX_TICKS = 20;
        MAX_CHARGE_RETRIES = 4;
        MAX_ANCHOR_FACE_RETRIES = 4;
        MAX_PLACE_RETRIES = 4;
        MAX_DETONATE_RETRIES = 4;
    }

    private boolean hasRotationClaim() {
        return this.rotationClaim.U(this) || this.rotationClaim.h(this, this.silentAim.L());
    }

    private boolean isShieldBlockClear() {
        if (this.shieldBlock == null) {
            return false;
        }
        Block block = Minecraft.thePlayer().getWorld().getBlockByPos(this.shieldBlock.D(), this.shieldBlock.B(), this.shieldBlock.G());
        return block.isNotNull() && !BlockUtil.u(block);
    }

    private Vec3 computeAnchorAimPoint(EntityPlayerSP entityPlayerSP) {
        if (this.anchorPos == null || this.anchorPos.isNull()) {
            return this.computeBaseAimPoint();
        }
        BlockData blockData = this.shieldBlock;
        Vec3 vec3 = this.findBestAnchorFace(entityPlayerSP, blockData);
        if (vec3 != null) {
            return vec3;
        }
        return this.centerOf(this.anchorPos);
    }

    private void finishSequence() {
        this.releaseRotation();
        this.restorePrevSlot();
        if (this.onPlaceMode.o()) {
            this.resetState();
            this.state = AnchorMacroState.IDLE;
        } else {
            this.s(false, true);
        }
    }

    private BlockPos findRespawnAnchor() {
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull() || !rayTraceResult.isBlockHit()) {
            return null;
        }
        BlockPos blockPos = rayTraceResult.getBlockPos();
        if (blockPos == null || blockPos.isNull()) {
            return null;
        }
        Block block = rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a();
        if (block.isNull()) {
            return null;
        }
        String string = block.U();
        if (string == null || !string.toLowerCase().contains("respawn_anchor")) {
            return null;
        }
        return blockPos;
    }

    private boolean isLookingAtAnchor() {
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull() || !rayTraceResult.isBlockHit() || this.baseBlockPos == null || this.hitFacing == null) {
            return false;
        }
        BlockPos blockPos = rayTraceResult.getBlockPos();
        if (blockPos == null || blockPos.isNull()) {
            return false;
        }
        if (blockPos.equals(this.baseBlockPos)) {
            return true;
        }
        return this.anchorPos != null && blockPos.equals(this.anchorPos);
    }

    private Vec3 computeBaseAimPoint() {
        return this.topCenterOf(this.baseBlockPos);
    }

    private void restorePrevSlot() {
        if (this.prevSlot == -1) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNotNull() && entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().isNotNull()) {
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.prevSlot);
        }
        this.prevSlot = -1;
    }

    private void releaseRotation() {
        if (this.rotationController != null) {
            RotationManager.b.v(this.rotationController);
            this.rotationController = null;
        }
        this.rotationClaim.X(this);
    }

    @Override
    public void onEnable() {
        this.resetState();
        if (this.onPlaceMode.o()) {
            this.state = AnchorMacroState.IDLE;
        }
    }

    private boolean isRotationDone() {
        return this.rotationController == null || this.rotationController.V$src$Z$lb4tvc();
    }

    private AnchorBlockHitTarget findAnchorBlockHit() {
        RayTraceResult rayTraceResult = RayTraceUtil.p(Minecraft.thePlayer().getWorld(), Minecraft.thePlayer(), false);
        if (rayTraceResult.isNull() || !rayTraceResult.isBlockHit()) {
            return null;
        }
        BlockPos blockPos = rayTraceResult.getBlockPos();
        EnumFacing enumFacing = rayTraceResult.getSideHit();
        if (blockPos == null || blockPos.isNull() || enumFacing == null || enumFacing.isNull()) {
            return null;
        }
        return new AnchorBlockHitTarget(blockPos, enumFacing, null);
    }

    private void notifyMissingItem(String string) {
        Vape.INSTANCE.getNotificationManager().t("AutoAnchor", string + " not in hotbar", NotificationType.WARNING, 3000L);
    }

    private boolean isAnchorFullyVisible(Vec3 vec3, BlockData blockData) {
        Vec3[] vec3Array;
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(blockData.D(), blockData.B(), blockData.G(), (double)blockData.D() + 1.0, (double)blockData.B() + 1.0, (double)blockData.G() + 1.0);
        int n = this.anchorPos.P();
        int n2 = this.anchorPos.o();
        int n3 = this.anchorPos.d();
        for (Vec3 vec32 : vec3Array = new Vec3[]{Vec3.create((double)n + 0.5, (double)n2 + 0.5, (double)n3 + 0.5), Vec3.create((double)n + 0.5, (double)n2 + 1.0, (double)n3 + 0.5), Vec3.create((double)n + 0.5, n2, (double)n3 + 0.5), Vec3.create(n, (double)n2 + 0.5, (double)n3 + 0.5), Vec3.create((double)n + 1.0, (double)n2 + 0.5, (double)n3 + 0.5), Vec3.create((double)n + 0.5, (double)n2 + 0.5, n3), Vec3.create((double)n + 0.5, (double)n2 + 0.5, (double)n3 + 1.0)}) {
            RayTraceResult rayTraceResult = axisAlignedBB.calculateIntercept(vec3, vec32);
            if (rayTraceResult != null && !rayTraceResult.isNull()) continue;
            return false;
        }
        return true;
    }

    private void aimAt(Vec3 vec3) {
        if (!this.aimAssist.L().booleanValue() || vec3 == null || vec3.isNull()) {
            return;
        }
        if (this.rotationController == null) {
            if (this.silentAim.L().booleanValue()) {
                AdaptiveRotationController adaptiveRotationController = new AdaptiveRotationController();
                adaptiveRotationController.d(false);
                this.rotationController = adaptiveRotationController;
            } else {
                PointRotationController pointRotationController = new PointRotationController(vec3);
                pointRotationController.E(false);
                this.rotationController = pointRotationController;
            }
            this.rotationController.w(true);
            this.rotationController.k(true);
            this.rotationController.t(0.0f);
            this.rotationController.Y(((Double)this.aimSpeed.K()).floatValue());
            this.rotationController.A(true);
            this.rotationController.U(false);
            this.rotationController.s(true);
            this.rotationController.z(true);
        }
        this.rotationController.Y(((Double)this.aimSpeed.K()).floatValue());
        if (this.rotationController instanceof PointRotationController) {
            ((PointRotationController)this.rotationController).J(vec3);
        } else if (this.rotationController instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)this.rotationController).J(vec3);
        }
        RotationManager.b.S(this.rotationController);
    }

    private int findHotbarSlot(String string) {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        for (int i = 0; i < 9; ++i) {
            String string2;
            Item item;
            ItemStack itemStack = inventoryPlayer.c(i);
            if (itemStack.isNull() || (item = itemStack.getItem()).isNull() || (string2 = item.A()) == null || !string2.toLowerCase().contains(string)) continue;
            return i;
        }
        return -1;
    }

    @Override
    public void onDisable() {
        this.releaseRotation();
        this.restorePrevSlot();
        this.resetState();
    }

    private void placeShield(EntityPlayerSP entityPlayerSP, InventoryPlayer inventoryPlayer) {
        Object object;
        ItemStack heldStack;
        if (!this.shieldStarted) {
            this.shieldStarted = true;
            heldStack = inventoryPlayer.c(this.glowstoneSlot);
            if (heldStack.isNull() || heldStack.t() < 2) {
                this.state = AnchorMacroState.CHARGING_ANCHOR;
                this.resetActionTimer();
                this.retries = 0;
                return;
            }
            object = this.findShieldCandidate(entityPlayerSP);
            if (object == null) {
                this.state = AnchorMacroState.CHARGING_ANCHOR;
                this.resetActionTimer();
                this.retries = 0;
                return;
            }
            this.shieldBlock = AnchorObstructionPlacementCandidate.b((AnchorObstructionPlacementCandidate)object);
            this.shieldSupportPos = BlockPos.d(AnchorObstructionPlacementCandidate.k((AnchorObstructionPlacementCandidate)object));
            this.shieldFacing = AnchorObstructionPlacementCandidate.O((AnchorObstructionPlacementCandidate)object);
            this.shieldPlaced = false;
            this.shieldChargeRetries = 0;
            this.shieldStep = 0;
        }
        if (this.shieldBlock == null || this.shieldSupportPos == null || this.shieldFacing == null) {
            this.state = AnchorMacroState.CHARGING_ANCHOR;
            this.resetActionTimer();
            this.retries = 0;
            return;
        }
        Vec3 aimPoint = this.faceCenter(this.shieldSupportPos, this.shieldFacing);
        this.aimAt(aimPoint);
        if (!this.timer.hasTimeElapsed(this.actionDelay)) {
            return;
        }
        if (this.shieldStep < 1) {
            ++this.shieldStep;
            return;
        }
        if (!this.shieldPlaced) {
            object = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
            if (((Wrapper)object).isNull() || !((RayTraceResult)object).isBlockHit()) {
                if (++this.retries > 4) {
                    this.state = AnchorMacroState.CHARGING_ANCHOR;
                    this.resetActionTimer();
                    this.retries = 0;
                }
                return;
            }
            inventoryPlayer.g(this.glowstoneSlot);
            this.rightClick();
            this.shieldPlaced = true;
            this.shieldChargeRetries = 0;
            return;
        }
        if (!this.isShieldBlockClear()) {
            if (++this.shieldChargeRetries > 4) {
                this.state = AnchorMacroState.CHARGING_ANCHOR;
                this.resetActionTimer();
                this.retries = 0;
            }
            return;
        }
        this.state = AnchorMacroState.CHARGING_ANCHOR;
        this.resetActionTimer();
        this.retries = 0;
    }

    private boolean isHoldingAnchor(InventoryPlayer inventoryPlayer) {
        ItemStack itemStack = inventoryPlayer.c(inventoryPlayer.v());
        if (itemStack.isNull()) {
            return false;
        }
        Item item = itemStack.getItem();
        if (item.isNull()) {
            return false;
        }
        String string = item.A();
        return string != null && string.toLowerCase().contains("respawn_anchor");
    }

    private Vec3 findBestAnchorFace(EntityPlayerSP entityPlayerSP, BlockData blockData) {
        if (this.anchorPos == null || this.anchorPos.isNull()) {
            return null;
        }
        World world = entityPlayerSP.getWorld();
        if (world.isNull()) {
            return null;
        }
        int n = this.anchorPos.P();
        int n2 = this.anchorPos.o();
        int n3 = this.anchorPos.d();
        Vec3 vec3 = Vec3.create(entityPlayerSP.z(), entityPlayerSP.N() + (double)entityPlayerSP.X(), entityPlayerSP.h());
        Vec3[] vec3Array = new Vec3[]{Vec3.create((double)n + 0.5, (double)n2 + 1.0, (double)n3 + 0.5), Vec3.create((double)n + 0.5, n2, (double)n3 + 0.5), Vec3.create(n, (double)n2 + 0.5, (double)n3 + 0.5), Vec3.create((double)n + 1.0, (double)n2 + 0.5, (double)n3 + 0.5), Vec3.create((double)n + 0.5, (double)n2 + 0.5, n3), Vec3.create((double)n + 0.5, (double)n2 + 0.5, (double)n3 + 1.0), Vec3.create((double)n + 0.25, (double)n2 + 1.0, (double)n3 + 0.5), Vec3.create((double)n + 0.75, (double)n2 + 1.0, (double)n3 + 0.5), Vec3.create(n, (double)n2 + 0.25, (double)n3 + 0.5), Vec3.create((double)n + 1.0, (double)n2 + 0.75, (double)n3 + 0.5), Vec3.create((double)n + 0.5, (double)n2 + 0.25, n3), Vec3.create((double)n + 0.5, (double)n2 + 0.75, (double)n3 + 1.0), Vec3.create((double)n + 0.25, n2, (double)n3 + 0.5), Vec3.create((double)n + 0.75, n2, (double)n3 + 0.5)};
        AxisAlignedBB axisAlignedBB = null;
        if (blockData != null) {
            axisAlignedBB = AxisAlignedBB.create(blockData.D(), blockData.B(), blockData.G(), (double)blockData.D() + 1.0, (double)blockData.B() + 1.0, (double)blockData.G() + 1.0);
        }
        float f = entityPlayerSP.J();
        float f2 = entityPlayerSP.V();
        double d = Minecraft.playerController().N();
        Vec3 vec32 = null;
        double d2 = Double.MAX_VALUE;
        for (Vec3 vec33 : vec3Array) {
            float f3;
            RotationAngles rotationAngles;
            float f4;
            double d3;
            BlockPos blockPos;
            double d4;
            RayTraceResult rayTraceResult;
            if (axisAlignedBB != null && (rayTraceResult = axisAlignedBB.calculateIntercept(vec3, vec33)) != null && rayTraceResult.isNotNull() || (d4 = vec3.distanceTo(vec33)) <= 1.0E-4) continue;
            Vec3 vec34 = vec33.q(vec3);
            double d5 = d / d4;
            Vec3 vec35 = vec3.addVector(vec34.getX() * d5, vec34.getY() * d5, vec34.getZ() * d5);
            RayTraceResult rayTraceResult2 = RayTraceUtil.b(vec3, vec35, world, entityPlayerSP, false, false, false, null);
            if (rayTraceResult2 == null || rayTraceResult2.isNull() || !rayTraceResult2.isBlockHit() || (blockPos = rayTraceResult2.getBlockPos()) == null || blockPos.isNull() || blockPos.P() != n || blockPos.o() != n2 || blockPos.d() != n3 || !((d3 = Math.sqrt((f4 = MathUtil.wrapAngleTo180((rotationAngles = RotationVectorMath.H(vec3, vec33, f, false)).z() - f)) * f4 + (f3 = rotationAngles.N() - f2) * f3)) < d2)) continue;
            d2 = d3;
            vec32 = vec33;
        }
        return vec32;
    }

    private Vec3 topCenterOf(BlockPos blockPos) {
        return Vec3.create((double)blockPos.P() + 0.5, (double)blockPos.o() + 1.0, (double)blockPos.d() + 0.5);
    }

    private int findExplosionSlot(InventoryPlayer inventoryPlayer) {
        int n;
        if (this.explosionItemWhitelist.L().booleanValue() && (n = this.findLimitSlot(this.explosionItem, inventoryPlayer)) != -1) {
            return n;
        }
        return this.anchorSlot;
    }

    private Vec3 faceCenter(BlockPos blockPos, EnumFacing enumFacing) {
        Vec3i vec3i = enumFacing.w$src$Lgg_vape_wrapper_impl_Vec3i_$ixeccr();
        return Vec3.create((double)blockPos.P() + 0.5 + (double)vec3i.P() * 0.5, (double)blockPos.o() + 0.5 + (double)vec3i.o() * 0.5, (double)blockPos.d() + 0.5 + (double)vec3i.d() * 0.5);
    }

    private AnchorMacroState nextChargeState() {
        if (this.safeAnchor.L().booleanValue()) {
            return AnchorMacroState.PLACING_SHIELD;
        }
        return AnchorMacroState.CHARGING_ANCHOR;
    }

    private void resetActionTimer() {
        this.timer.reset();
        this.actionDelay = (long)this.delay.B();
    }

    private boolean isLookingAtBaseFace() {
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull() || !rayTraceResult.isBlockHit() || this.baseBlockPos == null || this.hitFacing == null) {
            return false;
        }
        BlockPos blockPos = rayTraceResult.getBlockPos();
        EnumFacing enumFacing = rayTraceResult.getSideHit();
        if (blockPos == null || blockPos.isNull() || enumFacing == null || enumFacing.isNull()) {
            return false;
        }
        return blockPos.equals(this.baseBlockPos) && enumFacing.equals(this.hitFacing);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        InventoryPlayer inventoryPlayer = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        if (inventoryPlayer.isNull()) {
            return;
        }
        if (this.state == AnchorMacroState.IDLE) {
            if (this.onBindMode.o()) {
                this.s(false, true);
            }
            return;
        }
        if (!this.aimAssist.L().booleanValue() && this.rotationController != null) {
            this.releaseRotation();
        }
        if (this.aimAssist.L().booleanValue() && !this.hasRotationClaim()) {
            return;
        }
        switch (this.state) {
            case FINDING_ITEMS: {
                this.anchorSlot = this.findHotbarSlot("respawn_anchor");
                this.glowstoneSlot = this.findHotbarSlot("glowstone");
                if (this.anchorSlot == -1 || this.glowstoneSlot == -1) {
                    if (this.anchorSlot == -1) {
                        this.notifyMissingItem("Respawn anchor");
                    }
                    if (this.glowstoneSlot == -1) {
                        this.notifyMissingItem("Glowstone");
                    }
                    this.s(false, true);
                    return;
                }
                this.prevSlot = inventoryPlayer.v();
                this.state = AnchorMacroState.PLACING_ANCHOR;
                break;
            }
            case PLACING_ANCHOR: {
                if (this.anchorPos == null || this.baseBlockPos == null || this.hitFacing == null) {
                    if (this.isLookingAtRespawnAnchor()) {
                        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
                        this.baseBlockPos = this.anchorPos = rayTraceResult.getBlockPos();
                        this.state = this.nextChargeState();
                        this.resetActionTimer();
                        this.retries = 0;
                        break;
                    }
                    AnchorBlockHitTarget anchorBlockHitTarget = this.findAnchorBlockHit();
                    if (anchorBlockHitTarget == null) {
                        if (++this.retries <= 20) break;
                        this.state = AnchorMacroState.FINISH;
                        break;
                    }
                    this.baseBlockPos = AnchorBlockHitTarget.q(anchorBlockHitTarget);
                    this.hitFacing = AnchorBlockHitTarget.O(anchorBlockHitTarget);
                    this.anchorPos = this.baseBlockPos.offset(this.hitFacing);
                    this.retries = 0;
                }
                this.aimAt(this.computeBaseAimPoint());
                if (!this.anchorPlaced && !this.isLookingAtAnchor()) {
                    if (++this.retries <= 20) break;
                    this.state = AnchorMacroState.FINISH;
                    break;
                }
                if (!this.anchorPlaced) {
                    inventoryPlayer.g(this.anchorSlot);
                    this.rightClick();
                    this.anchorPlaced = true;
                    this.waitRetries = 0;
                    break;
                }
                if (!this.isAnchorPresent()) {
                    if (++this.waitRetries <= 4) break;
                    this.anchorPlaced = false;
                    this.waitRetries = 0;
                    if (++this.chargeRetries <= 4) break;
                    this.state = AnchorMacroState.FINISH;
                    break;
                }
                this.anchorPlaced = false;
                this.waitRetries = 0;
                this.chargeRetries = 0;
                this.state = this.nextChargeState();
                this.resetActionTimer();
                this.retries = 0;
                break;
            }
            case WAITING_FOR_ANCHOR: {
                if (this.isAnchorPresent()) {
                    this.state = this.nextChargeState();
                    this.resetActionTimer();
                    this.retries = 0;
                    break;
                }
                BlockPos blockPos = this.findRespawnAnchor();
                if (blockPos != null && blockPos.isNotNull()) {
                    this.anchorPos = blockPos;
                    this.state = this.nextChargeState();
                    this.resetActionTimer();
                    this.retries = 0;
                    break;
                }
                if (++this.waitRetries <= 4) break;
                this.finishSequence();
                break;
            }
            case PLACING_SHIELD: {
                this.placeShield(entityPlayerSP, inventoryPlayer);
                break;
            }
            case CHARGING_ANCHOR: {
                this.aimAt(this.computeAnchorAimPoint(entityPlayerSP));
                if (!this.timer.hasTimeElapsed(this.actionDelay)) break;
                if (!this.isLookingAtRespawnAnchor()) {
                    ++this.retries;
                    if (this.retries <= 4) break;
                    this.state = AnchorMacroState.FINISH;
                    break;
                }
                inventoryPlayer.g(this.glowstoneSlot);
                this.rightClick();
                this.state = AnchorMacroState.SWAPPING_TO_EXPLOSION_ITEM;
                this.resetActionTimer();
                this.retries = 0;
                break;
            }
            case SWAPPING_TO_EXPLOSION_ITEM: {
                this.aimAt(this.computeAnchorAimPoint(entityPlayerSP));
                if (!this.timer.hasTimeElapsed(this.actionDelay)) break;
                if (!this.isLookingAtRespawnAnchor()) {
                    ++this.retries;
                    if (this.retries <= 4) break;
                    this.state = AnchorMacroState.FINISH;
                    break;
                }
                this.explosionSlot = this.findExplosionSlot(inventoryPlayer);
                inventoryPlayer.g(this.explosionSlot);
                this.state = AnchorMacroState.DETONATING_ANCHOR;
                this.resetActionTimer();
                this.retries = 0;
                break;
            }
            case DETONATING_ANCHOR: {
                this.aimAt(this.computeAnchorAimPoint(entityPlayerSP));
                if (!this.timer.hasTimeElapsed(this.actionDelay)) break;
                if (!this.isLookingAtRespawnAnchor()) {
                    ++this.retries;
                    if (this.retries <= 4) break;
                    this.state = AnchorMacroState.FINISH;
                    break;
                }
                this.rightClick();
                if (this.doubleAnchor.L().booleanValue() && !this.doubleAnchorDone) {
                    this.aimAt(this.computeAnchorAimPoint(entityPlayerSP));
                    this.rightClick();
                    this.doubleAnchorDone = true;
                    this.state = AnchorMacroState.CHARGING_ANCHOR;
                    this.resetActionTimer();
                    this.retries = 0;
                    break;
                }
                this.state = AnchorMacroState.FINISH;
                break;
            }
            case FINISH: {
                this.finishSequence();
            }
        }
    }

    private int findLimitSlot(LimitValue limitValue, InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = inventoryPlayer.c(i);
            if (itemStack.isNull()) continue;
            for (Object value : (List<?>)limitValue.K()) {
                ItemLimitData itemLimitData = (ItemLimitData)value;
                if (!itemLimitData.W(itemStack)) continue;
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean X() {
        return this.onBindMode.o();
    }

    @EventHandler
    public void J(EventRightClickMouse eventRightClickMouse) {
        if (!this.onPlaceMode.o() || this.state != AnchorMacroState.IDLE) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        InventoryPlayer inventoryPlayer = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        if (inventoryPlayer.isNull()) {
            return;
        }
        if (!this.isHoldingAnchor(inventoryPlayer)) {
            return;
        }
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull() || !rayTraceResult.isBlockHit()) {
            return;
        }
        BlockPos blockPos = rayTraceResult.getBlockPos();
        EnumFacing enumFacing = rayTraceResult.getSideHit();
        if (blockPos == null || blockPos.isNull() || enumFacing == null || enumFacing.isNull()) {
            return;
        }
        this.glowstoneSlot = this.findHotbarSlot("glowstone");
        if (this.glowstoneSlot == -1) {
            this.notifyMissingItem("Glowstone");
            return;
        }
        this.anchorSlot = inventoryPlayer.v();
        this.baseBlockPos = blockPos;
        this.hitFacing = enumFacing;
        this.anchorPos = blockPos.offset(enumFacing);
        this.prevSlot = inventoryPlayer.v();
        this.state = AnchorMacroState.WAITING_FOR_ANCHOR;
        this.retries = 0;
        this.waitRetries = 0;
    }

    private void rightClick() {
        KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
        KeyBinding.setKeyBindState(keyBinding, true);
        KeyBinding.onTick(keyBinding);
        KeyBinding.setKeyBindState(keyBinding, false);
    }

    private boolean isLookingAtRespawnAnchor() {
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull() || !rayTraceResult.isBlockHit()) {
            return false;
        }
        String string = rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a().U();
        return string != null && string.toLowerCase().contains("respawn_anchor");
    }

    private void resetState() {
        this.state = AnchorMacroState.FINDING_ITEMS;
        this.anchorSlot = -1;
        this.glowstoneSlot = -1;
        this.explosionSlot = -1;
        this.retries = 0;
        this.doubleAnchorDone = false;
        this.anchorPlaced = false;
        this.waitRetries = 0;
        this.chargeRetries = 0;
        this.baseBlockPos = null;
        this.hitFacing = null;
        this.anchorPos = null;
        this.shieldBlock = null;
        this.shieldSupportPos = null;
        this.shieldFacing = null;
        this.shieldPlaced = false;
        this.shieldChargeRetries = 0;
        this.shieldStarted = false;
        this.shieldStep = 0;
    }

    private AnchorObstructionPlacementCandidate findShieldCandidate(EntityPlayerSP entityPlayerSP) {
        double d;
        double d2;
        if (this.anchorPos == null) {
            return null;
        }
        World world = entityPlayerSP.getWorld();
        if (world.isNull()) {
            return null;
        }
        double d3 = entityPlayerSP.z();
        double d4 = entityPlayerSP.N();
        double d5 = entityPlayerSP.h();
        Vec3 vec3 = Vec3.create(d3, d4 + (double)entityPlayerSP.X(), d5);
        Vec3 vec32 = this.findBestAnchorFace(entityPlayerSP, null);
        if (vec32 == null) {
            return null;
        }
        double d6 = vec32.getX() - d3;
        double d7 = Math.sqrt(d6 * d6 + (d2 = vec32.getY() - d4) * d2 + (d = vec32.getZ() - d5) * d);
        if (d7 < 1.5) {
            return null;
        }
        double d8 = d6 / d7;
        double d9 = d2 / d7;
        double d10 = d / d7;
        HashSet<Long> hashSet = new HashSet<Long>();
        ArrayList<BlockData> arrayList = new ArrayList<BlockData>();
        for (double d11 = 0.8; d11 < d7 - 0.5; d11 += 0.4) {
            int n;
            int n2;
            int n3 = (int)Math.floor(d3 + d8 * d11);
            long l = ((long)n3 & 0x3FFFFFFL) << 38 | ((long)(n2 = (int)Math.floor(d4 + d9 * d11)) & 0xFFFL) << 26 | (long)(n = (int)Math.floor(d5 + d10 * d11)) & 0x3FFFFFFL;
            if (!hashSet.add(l) || n3 == this.anchorPos.P() && n2 == this.anchorPos.o() && n == this.anchorPos.d()) continue;
            arrayList.add(new BlockData(n3, n2, n));
        }
        float f = 0.15f;
        AnchorObstructionPlacementCandidate anchorObstructionPlacementCandidate = null;
        float f2 = 0.0f;
        AxisAlignedBB axisAlignedBB = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
        for (BlockData blockData : arrayList) {
            Vec3 vec33;
            BlockData[] blockDataArray;
            EnumFacing[] enumFacingArray;
            Block block = world.getBlockByPos(blockData.D(), blockData.B(), blockData.G());
            if (block.isNull() || !BlockUtil.u(block) || !ClutchPlacementPathUtils.V(world, entityPlayerSP, blockData)) continue;
            AxisAlignedBB axisAlignedBB2 = AxisAlignedBB.create(blockData.D(), blockData.B(), blockData.G(), (double)blockData.D() + 1.0, (double)blockData.B() + 1.0, (double)blockData.G() + 1.0);
            if (axisAlignedBB.isNotNull() && axisAlignedBB2.isNotNull() && axisAlignedBB.intersects(axisAlignedBB2) || !this.findAdjacentSupport(world, blockData, enumFacingArray = new EnumFacing[1], blockDataArray = new BlockData[1]) || this.isAnchorFullyVisible(vec3, blockData) || (vec33 = this.findBestAnchorFace(entityPlayerSP, blockData)) == null) continue;
            float f3 = this.computeVisibilityRatio(vec33, blockData, entityPlayerSP);
            if (f3 >= 0.15f) {
                return new AnchorObstructionPlacementCandidate(blockData, blockDataArray[0], enumFacingArray[0], f3, vec33, null);
            }
            if (!(f3 > f2)) continue;
            f2 = f3;
            anchorObstructionPlacementCandidate = new AnchorObstructionPlacementCandidate(blockData, blockDataArray[0], enumFacingArray[0], f3, vec33, null);
        }
        return anchorObstructionPlacementCandidate;
    }

    private boolean isAnchorPresent() {
        if (this.anchorPos == null || this.anchorPos.isNull()) {
            return false;
        }
        String string = Minecraft.thePlayer().getWorld().getBlockByPos(this.anchorPos.P(), this.anchorPos.o(), this.anchorPos.d()).U();
        return string != null && string.toLowerCase().contains("respawn_anchor");
    }

    private boolean findAdjacentSupport(World world, BlockData blockData, EnumFacing[] enumFacingArray, BlockData[] blockDataArray) {
        int[][] nArrayArray = new int[][]{{0, -1, 0}, {0, 1, 0}, {-1, 0, 0}, {1, 0, 0}, {0, 0, -1}, {0, 0, 1}};
        EnumFacing[] enumFacingArray2 = new EnumFacing[]{EnumFacing.F$src$Lgg_vape_wrapper_impl_EnumFacing_$glfxl5(), EnumFacing.B(), EnumFacing.g$src$Lgg_vape_wrapper_impl_EnumFacing_$1ii8mzu(), EnumFacing.X(), EnumFacing.M(), EnumFacing.w()};
        for (int i = 0; i < nArrayArray.length; ++i) {
            int n;
            int n2;
            int n3 = blockData.D() + nArrayArray[i][0];
            Block block = world.getBlockByPos(n3, n2 = blockData.B() + nArrayArray[i][1], n = blockData.G() + nArrayArray[i][2]);
            if (!block.isNotNull() || !BlockUtil.b(block) || BlockUtil.u(block)) continue;
            blockDataArray[0] = new BlockData(n3, n2, n);
            enumFacingArray[0] = enumFacingArray2[i];
            return true;
        }
        return false;
    }
}
