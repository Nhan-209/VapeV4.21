package gg.vape.module.utility;

import gg.vape.input.KeyBindingHelper;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.blatant.blockin.BlockPlacementUtility;
import gg.vape.module.blatant.blockin.HotbarSlotResolution;
import gg.vape.module.blatant.blockin.HotbarSlotResolutionWithValue;
import gg.vape.module.utility.AutoMLG;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.PointRotationController;
import gg.vape.rotation.RotationManager;
import gg.vape.rotation.WorldPointRotationTarget;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AutoMLGPlacementController {
    private final AutoMLG k;

    @Nullable
    private FixedRotationController Y(@Nullable BlockCoordinate blockCoordinate, @Nullable FixedRotationController fixedRotationController, ItemMappingEntry itemMappingEntry) {
        FixedRotationController fixedRotationController2 = fixedRotationController;
        if (blockCoordinate == null) {
            return fixedRotationController2;
        }
        if (fixedRotationController2 != null && !(fixedRotationController2 instanceof AdaptiveRotationController) && !(fixedRotationController2 instanceof PointRotationController)) {
            BlockPlacementUtility.r(fixedRotationController2, false, true);
            fixedRotationController2 = this.V(blockCoordinate, itemMappingEntry);
        }
        if (fixedRotationController2 instanceof PointRotationController || fixedRotationController2 instanceof AdaptiveRotationController) {
            BlockPlacementUtility.y((WorldPointRotationTarget)((Object)fixedRotationController2), blockCoordinate, itemMappingEntry);
        }
        if (this.k.A() && fixedRotationController2 != null && !fixedRotationController2.equals(RotationManager.b.w())) {
            RotationManager.b.S(fixedRotationController2);
        }
        return fixedRotationController2;
    }

    @NotNull
    public HotbarSlotResolution t(@NotNull ItemMappingEntry itemMappingEntry, @Nullable BlockCoordinate blockCoordinate, @Nullable FixedRotationController fixedRotationController, boolean bl) {
        FixedRotationController fixedRotationController2 = this.Y(blockCoordinate, fixedRotationController, itemMappingEntry);
        if (fixedRotationController2 != null) {
            if (!fixedRotationController2.equals(fixedRotationController)) {
                if (bl) {
                    this.k.V = fixedRotationController2;
                } else {
                    this.k.C = fixedRotationController2;
                }
            }
            if (BlockPlacementUtility.R(blockCoordinate, itemMappingEntry)) {
                return HotbarSlotResolution.j("AimJob completed by looking at valid block");
            }
            if (fixedRotationController2.V$src$Z$lb4tvc()) {
                return HotbarSlotResolution.j("AimJob was already completed");
            }
            if (!fixedRotationController2.equals(RotationManager.b.w())) {
                if (this.k.A()) {
                    RotationManager.b.S(fixedRotationController2);
                    return HotbarSlotResolution.J("AimJob set as current job");
                }
                return HotbarSlotResolution.J("AimJob is not current job");
            }
            return HotbarSlotResolution.J("Waiting for AimJob to complete");
        }
        if (BlockPlacementUtility.R(blockCoordinate, itemMappingEntry)) {
            return HotbarSlotResolution.j("AimJob completed by looking at valid block");
        }
        return HotbarSlotResolution.W("AimJob is null").A();
    }

    public AutoMLGPlacementController(AutoMLG autoMLG) {
        this.k = autoMLG;
    }

    private <T extends FixedRotationController> T b(T t) {
        t.k(true);
        t.t(0.1f);
        t.A(true);
        t.U(true);
        t.w(true);
        t.z(true);
        t.s(true);
        t.Y(((Double)this.k.c.K()).floatValue());
        t.D(true);
        if (t instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)t).b(false);
        }
        return t;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public HotbarSlotResolutionWithValue<BlockPos> D(@NotNull ItemMappingEntry itemMappingEntry, @Nullable BlockCoordinate blockCoordinate, @Nullable TimerUtil timerUtil) {
        HotbarSlotResolution hotbarSlotResolution;
        HotbarSlotResolutionWithValue hotbarSlotResolutionWithValue = new HotbarSlotResolutionWithValue();
        if (timerUtil != null && timerUtil.hasTimeElapsed(1000L)) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.Q("Timed out while trying to place item");
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.f("Player is unavailable");
        }
        if (blockCoordinate != null) {
            // empty if block
        }
        if ((hotbarSlotResolution = this.k.f$src$Lgg_vape_module_blatant_blockin_HotbarSlotResolu$1985fcl()).h()) {
            return (HotbarSlotResolutionWithValue)((HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.Q("Failed to close GUI due to: " + hotbarSlotResolution.b())).i(hotbarSlotResolution.B());
        }
        if (hotbarSlotResolution.Q()) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.f("Waiting to close GUI");
        }
        RayTraceResult rayTraceResult = BlockPlacementUtility.A(itemMappingEntry);
        if (rayTraceResult.isNull()) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.f("Waiting for mouse over to not be null");
        }
        if (!rayTraceResult.isBlockHit()) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.f("Waiting for mouse over to be a block");
        }
        if (!BlockPlacementUtility.R(blockCoordinate, itemMappingEntry)) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.f("Waiting to look at valid block");
        }
        BlockPos blockPos = BlockPlacementUtility.q(itemMappingEntry);
        KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
        KeyBindingHelper.d(keyBinding, true);
        KeyBindingHelper.v(keyBinding, false, false);
        if (blockPos != null) {
            // empty if block
        }
        return ((HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.m("Placed MLG Item")).q(blockPos);
    }

    @NotNull
    public FixedRotationController V(@Nullable BlockCoordinate blockCoordinate, @Nullable ItemMappingEntry itemMappingEntry) {
        FixedRotationController fixedRotationController;
        if (blockCoordinate == null) {
            fixedRotationController = this.k.F.L() != false ? new AdaptiveRotationController(-999.0f, 90.0f) : new FixedRotationController(-999.0f, 90.0f);
        } else {
            Vec3 vec3 = BlockPlacementUtility.D(blockCoordinate, itemMappingEntry);
            fixedRotationController = this.k.F.L() != false ? new AdaptiveRotationController(vec3) : new PointRotationController(vec3);
        }
        return this.b(fixedRotationController);
    }
}

