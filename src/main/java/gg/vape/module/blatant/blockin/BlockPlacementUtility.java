package gg.vape.module.blatant.blockin;

import gg.vape.Vape;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.blatant.blockin.BlockPathPlanner;
import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.module.utility.MLGImpactState;
import gg.vape.module.utility.inventory.ItemStackActionPredicate;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.rotation.WorldPointRotationTarget;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.BlockStatePredicate;
import gg.vape.wrapper.impl.DamageSource;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionRegistry;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.Slot;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.World;
import java.util.List;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class BlockPlacementUtility {
    @Nullable
    private static ItemMappingEntry W = null;
    @Nullable
    private static ItemMappingEntry G = null;
    @Nullable
    private static ItemMappingEntry Z = null;

    public static float u(EntityPlayerSP entityPlayerSP, boolean bl, boolean bl2, BlockPlacementGraph blockPlacementGraph) {
        if (entityPlayerSP.b$src$Z$fqlxe4()) {
            return 0.0f;
        }
        if (entityPlayerSP.q() == 0.0) {
            return 0.0f;
        }
        if (bl) {
            World world = entityPlayerSP.getWorld();
            int n = MathUtil.floor(entityPlayerSP.z());
            int n2 = MathUtil.floor(entityPlayerSP.h());
            for (int i = MathUtil.floor(entityPlayerSP.N()); i > 0; --i) {
                Block block = world.getBlockByPos(n, i, n2);
                if (block.isNull() || BlockUtil.p(block)) continue;
                return (float)(entityPlayerSP.N() - (double)i);
            }
        } else {
            BlockCoordinate blockCoordinate = BlockPlacementUtility.S(bl2, 50, entityPlayerSP, blockPlacementGraph);
            if (blockCoordinate != null) {
                return (float)(entityPlayerSP.N() - (double)(blockCoordinate.E() + 1));
            }
        }
        return 100000.0f;
    }

    public static RayTraceResult A(@NotNull ItemMappingEntry itemMappingEntry) {
        if (ForgeVersion.MC_1_21_4.d()) {
            return RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        }
        return itemMappingEntry.equals(BlockPlacementUtility.e()) ? RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic() : RotationManager.b.F(itemMappingEntry.equals(BlockPlacementUtility.U()));
    }

    @Nullable
    public static ItemMappingEntry l(@Nullable Slot slot) {
        return slot == null || slot.isNull() || slot.I() == null || slot.I().isNull() ? null : Vape.INSTANCE.getItemStackResolver().j(slot.I());
    }

    public static boolean R(@Nullable BlockCoordinate blockCoordinate, @NotNull ItemMappingEntry itemMappingEntry) {
        RayTraceResult rayTraceResult = BlockPlacementUtility.A(itemMappingEntry);
        if (rayTraceResult.isNull() || !rayTraceResult.isBlockHit()) {
            return false;
        }
        BlockPos blockPos = rayTraceResult.getBlockPos();
        if (itemMappingEntry.equals(BlockPlacementUtility.U())) {
            Block block = rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a();
            BlockStatePredicate blockStatePredicate = block.a();
            if (block.isNotNull() && BlockUtil.C(block) && blockStatePredicate.isNotNull() && blockStatePredicate.toString().contains("level=0")) {
                return true;
            }
        }
        if (!EnumFacing.F$src$Lgg_vape_wrapper_impl_EnumFacing_$glfxl5().equals(rayTraceResult.getSideHit()) || blockPos.isNull()) {
            return false;
        }
        return blockCoordinate == null || BlockPlacementUtility.P(blockPos, blockCoordinate, itemMappingEntry);
    }

    private static boolean lambda$createItemPredicate$0(ItemMappingEntry itemMappingEntry, Slot slot) {
        return slot != null && slot.isNotNull() && slot.I().isNotNull() && itemMappingEntry.equals(Vape.INSTANCE.getItemStackResolver().j(slot.I()));
    }

    public static ItemMappingEntry U() {
        if (G == null) {
            G = Vape.INSTANCE.getItemStackResolver().b("minecraft:bucket");
        }
        return G;
    }

    @NotNull
    static Predicate<Slot> F(@NotNull ItemMappingEntry itemMappingEntry) {
        return arg_0 -> BlockPlacementUtility.lambda$createItemPredicate$0(itemMappingEntry, arg_0);
    }

    @Nullable
    static Slot Z(ItemMappingEntry itemMappingEntry) {
        return ItemStackActionPredicate.a(BlockPlacementUtility.F(itemMappingEntry), MLGImpactState.D);
    }

    public static float Z(EntityPlayerSP entityPlayerSP, float f) {
        PotionEffect potionEffect = entityPlayerSP.b(PotionRegistry.Z);
        float f2 = potionEffect != null && potionEffect.isNotNull() ? (float)(potionEffect.L() + 1) : 0.0f;
        int n = MathUtil.ceil(f - 3.0f - f2);
        DamageSource damageSource = ForgeVersion.MC_1_21_4.d() ? entityPlayerSP.B().O() : DamageSource.m$src$Lgg_vape_wrapper_impl_DamageSource_$z0ibym();
        return RotationUtil.y(entityPlayerSP, damageSource, n, false, false);
    }

    @Nullable
    public static BlockCoordinate S(boolean bl, int n, EntityPlayerSP entityPlayerSP, BlockPlacementGraph blockPlacementGraph) {
        int n2 = ForgeVersion.MC_1_20_6.d() ? entityPlayerSP.getWorld().R() : 0;
        BlockPathPlanner blockPathPlanner = new BlockPathPlanner(entityPlayerSP, entityPlayerSP, entityPlayerSP.getWorld(), blockPlacementGraph);
        blockPathPlanner.U(blockPlacementGraph);
        blockPathPlanner.l();
        boolean bl2 = blockPlacementGraph.M || blockPlacementGraph.D || blockPlacementGraph.R || blockPlacementGraph.Y;
        int n3 = bl ? 1 : 3;
        EntityPlayer entityPlayer = blockPathPlanner.T();
        World world = entityPlayer.getWorld();
        int n4 = 0;
        BlockCoordinate blockCoordinate = null;
        Vec3 vec3 = Vec3.create(entityPlayer.z(), entityPlayer.N(), entityPlayer.h());
        boolean bl3 = ForgeVersion.MC_1_12_2.d();
        for (int i = 0; i <= n; ++i) {
            boolean bl4;
            Wrapper wrapper;
            boolean bl5 = entityPlayer.b$src$Z$fqlxe4();
            blockPathPlanner.B();
            vec3.N(entityPlayer.z());
            vec3.m(entityPlayer.N());
            vec3.Z(entityPlayer.h());
            boolean bl6 = entityPlayer.b$src$Z$fqlxe4();
            int n5 = MathUtil.floor(entityPlayer.z());
            double d = entityPlayer.z();
            int n6 = MathUtil.floor(entityPlayer.N() - 0.015625);
            double d2 = entityPlayer.N() - 0.015625;
            int n7 = MathUtil.floor(entityPlayer.h());
            double d3 = entityPlayer.h();
            AxisAlignedBB axisAlignedBB = entityPlayer.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().expand(0.0, -2.0, 0.0);
            List list = world.i(entityPlayer, axisAlignedBB);
            if (!list.isEmpty()) {
                wrapper = null;
                for (Object e : list) {
                    AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(e);
                    BlockData blockData = BlockData.P(axisAlignedBB2);
                    BlockPos blockPos = BlockPos.d(blockData);
                    if (ForgeVersion.MC_1_16_5.v() && bl3) {
                        BlockState blockState = world.getBlockState(blockPos);
                        BlockState blockState2 = world.getBlockState(blockPos.X$src$Lgg_vape_wrapper_impl_BlockPos_$jlnp6b());
                        boolean bl7 = !world.getBlockState(blockPos).getBlock().X(blockState) && !world.getBlockState(blockPos.X$src$Lgg_vape_wrapper_impl_BlockPos_$jlnp6b()).getBlock().X(blockState2);
                        if (bl7) continue;
                    }
                    if (wrapper != null && !(axisAlignedBB2.getMaxY() > ((AxisAlignedBB)wrapper).getMaxY())) continue;
                    wrapper = axisAlignedBB2;
                }
                if (wrapper != null) {
                    return new BlockCoordinate(BlockData.P((AxisAlignedBB)wrapper));
                }
            }
            boolean bl8 = bl4 = (wrapper = entityPlayerSP.getWorld().getBlockByPos(n5, n6, n7)).isNotNull() && (wrapper.isInstance(MappedClasses.q_) || wrapper.isInstance(MappedClasses.b));
            if (bl6 || bl4) {
                ++n4;
                if (blockCoordinate == null) {
                    blockCoordinate = new BlockCoordinate(n5, n6, n7);
                }
                if (n4 >= n3 || entityPlayer.e$src$Z$15bd4i1()) {
                    return blockCoordinate;
                }
            } else {
                blockCoordinate = null;
                n4 = 0;
            }
            if (!(blockPathPlanner.T().N() <= (double)n2)) continue;
            return blockCoordinate;
        }
        return null;
    }

    @Nullable
    public static BlockPos q(@NotNull ItemMappingEntry itemMappingEntry) {
        RayTraceResult rayTraceResult = BlockPlacementUtility.A(itemMappingEntry);
        if (rayTraceResult.isNull() || !rayTraceResult.isBlockHit()) {
            return null;
        }
        BlockPos blockPos = rayTraceResult.getBlockPos();
        EnumFacing enumFacing = rayTraceResult.getSideHit();
        if (blockPos.isNull() || enumFacing.isNull()) {
            return null;
        }
        return blockPos.offset(enumFacing);
    }

    public static Vec3 D(BlockCoordinate blockCoordinate, @Nullable ItemMappingEntry itemMappingEntry) {
        boolean bl = BlockPlacementUtility.U().equals(itemMappingEntry);
        if (!bl) {
            return blockCoordinate.P().addVector(0.5, 1.0, 0.5);
        }
        return blockCoordinate.P().addVector(0.5, 0.5, 0.5);
    }

    public static ItemMappingEntry Y() {
        if (Z == null) {
            Z = Vape.INSTANCE.getItemStackResolver().b("minecraft:water_bucket");
        }
        return Z;
    }

    public static void y(WorldPointRotationTarget worldPointRotationTarget, BlockCoordinate blockCoordinate, ItemMappingEntry itemMappingEntry) {
        Vec3 vec3;
        Vec3 vec32 = BlockPlacementUtility.D(blockCoordinate, itemMappingEntry);
        if (!vec32.equals(vec3 = worldPointRotationTarget.w())) {
            worldPointRotationTarget.J(vec32);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private static void v(FixedRotationController fixedRotationController, boolean bl, boolean bl2, @Nullable RotationControlClaim rotationControlClaim, @Nullable Mod mod) {
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
        if (bl && rotationControlClaim != null && mod != null) {
            rotationControlClaim.X(mod);
        }
    }

    static ItemMappingEntry e() {
        if (W == null) {
            W = Vape.INSTANCE.getItemStackResolver().b("minecraft:cobweb");
        }
        return W;
    }

    private BlockPlacementUtility() {
    }

    private static boolean P(@Nullable BlockPos blockPos, @Nullable BlockCoordinate blockCoordinate, @Nullable ItemMappingEntry itemMappingEntry) {
        if (blockPos == null || blockCoordinate == null) {
            return false;
        }
        int n = blockPos.P();
        int n2 = blockCoordinate.B();
        int n3 = blockPos.o();
        int n4 = blockCoordinate.E();
        int n5 = blockPos.d();
        int n6 = blockCoordinate.A();
        int n7 = Math.abs(n - n2);
        int n8 = Math.abs(n3 - n4);
        int n9 = Math.abs(n5 - n6);
        if (itemMappingEntry != null && itemMappingEntry.equals(BlockPlacementUtility.U())) {
            return (double)n7 <= 0.5 && n8 <= 1 && (double)n9 <= 0.5;
        }
        return n7 <= 1 && n8 <= 1 && n9 <= 1;
    }

    public static void r(FixedRotationController fixedRotationController, boolean bl, boolean bl2) {
        BlockPlacementUtility.v(fixedRotationController, bl, bl2, null, null);
    }
}

