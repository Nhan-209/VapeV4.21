package gg.vape.module.utility.clutch;

import gg.vape.module.blatant.BlockIn;
import gg.vape.module.utility.clutch.BlockPathSearchStrategy;
import gg.vape.module.utility.clutch.BlockPlacementNode;
import gg.vape.module.utility.clutch.ClutchPlacementCoordinate;
import gg.vape.module.utility.clutch.PlacementTarget;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ItemLimitData;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.FastAtanMath;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RayTraceUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.Vec3d;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.Vec3i;
import gg.vape.wrapper.impl.World;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

public class ClutchPlacementPathUtils {
    private static EnumFacing[] horizontalFacings;
    private static EnumFacing[] allFacings;

    public static Vector<PlacementTarget> l(BlockData blockData, Vec3 vec3, EntityPlayerSP entityPlayerSP, World world, BlockData blockData2, EnumFacing enumFacing, EnumFacing enumFacing2, BlockPathSearchStrategy<PlacementTarget> blockPathSearchStrategy, int n) {
        int n2;
        if (n > blockPathSearchStrategy.w() || !blockPathSearchStrategy.V(blockData2)) {
            return null;
        }
        Block block = world.getBlockByPos(blockData2.D(), blockData2.B(), blockData2.G());
        boolean isSolid = BlockUtil.J(block);
        boolean isReplaceable = BlockUtil.u(block);
        if (!isSolid) {
            if (isReplaceable && ClutchPlacementPathUtils.J(blockData, entityPlayerSP, world, blockData2, enumFacing2.getOpposite())) {
                Vector<PlacementTarget> vector = new Vector<PlacementTarget>();
                PlacementTarget placementTarget = new PlacementTarget(blockData2, enumFacing2.getOpposite(), false);
                placementTarget.Y = n;
                vector.add(placementTarget);
                return vector;
            }
            return null;
        }
        if (allFacings == null) {
            allFacings = EnumFacing.t();
            horizontalFacings = EnumFacing.c$src$ALgg_vape_wrapper_impl_EnumFacing_$1i3g4ft();
        }
        if ((n2 = enumFacing.c()) == -1) {
            n2 = 0;
        }
        int n3 = horizontalFacings[n2].Y();
        int n4 = horizontalFacings[(n2 + 1) % 4].Y();
        int n5 = horizontalFacings[(n2 + 3) % 4].Y();
        int n6 = horizontalFacings[(n2 + 2) % 4].Y();
        int[] nArray = new int[]{0, n3, n4, n5, n6, 1};
        int n7 = Integer.MAX_VALUE;
        Vector<PlacementTarget> vector = null;
        for (int n8 : nArray) {
            PlacementTarget placementTarget;
            int n9;
            EnumFacing enumFacing3;
            EnumFacing enumFacing4 = allFacings[n8];
            if (enumFacing4.Y() == enumFacing.getOpposite().Y()) continue;
            BlockData blockData3 = blockData2.R(enumFacing4);
            Block block2 = world.getBlockByPos(blockData3.D(), blockData3.B(), blockData3.G());
            EnumFacing enumFacing5 = enumFacing3 = BlockUtil.u(block2) ? null : enumFacing4.getOpposite();
            if (!ClutchPlacementPathUtils.P(vec3, world, blockData3, enumFacing3)) continue;
            Vector<PlacementTarget> vector2 = new Vector<PlacementTarget>();
            if (blockPathSearchStrategy.B(blockData3)) {
                boolean solid = BlockUtil.J(block2);
                if (!solid && !ClutchPlacementPathUtils.J(blockData, entityPlayerSP, world, blockData3, enumFacing3)) continue;
                enumFacing3 = solid ? enumFacing4.getOpposite() : enumFacing3;
                placementTarget = new PlacementTarget(blockData3, enumFacing3);
                placementTarget.Y = n;
                vector2.addElement(placementTarget);
            } else {
                Vector<PlacementTarget> vector3 = ClutchPlacementPathUtils.l(blockData, vec3, entityPlayerSP, world, blockData3, enumFacing, enumFacing4, blockPathSearchStrategy, n + 1);
                if (vector3 != null && !vector3.isEmpty()) {
                    placementTarget = new PlacementTarget(blockData3, enumFacing4.getOpposite());
                    placementTarget.Y = n;
                    vector2.addAll(vector3);
                    vector2.add(placementTarget);
                }
            }
            if (vector2.isEmpty()) continue;
            n9 = blockPathSearchStrategy.t(vector2);
            if (vector != null && n9 >= n7) continue;
            vector = vector2;
            n7 = n9;
        }
        return vector;
    }

    public static Vec3 D(EntityPlayerSP entityPlayerSP, World world, Vec3 vec3, PlacementTarget placementTarget, float f, float f2) {
        Vec3d vec3d = null;
        boolean bl = ForgeVersion.MC_1_16_5.v();
        BlockData blockData = placementTarget.k;
        EnumFacing enumFacing = placementTarget.G;
        AxisAlignedBB axisAlignedBB = BlockUtil.F(world, blockData);
        double d = 0.002f;
        if (enumFacing == null) {
            return RotationUtil.M(vec3, axisAlignedBB, 0.0, 0.0, 0.0).n();
        }
        Vec3d vec3d2 = new Vec3d(vec3.getX(), vec3.getY(), vec3.getZ());
        Vec3 vec32 = Vec3.create(vec3.getX(), vec3.getY(), vec3.getZ());
        Vec3i vec3i = enumFacing.w$src$Lgg_vape_wrapper_impl_Vec3i_$ixeccr();
        axisAlignedBB = axisAlignedBB.contract(d, d, d);
        double d2 = axisAlignedBB.getMaxX() - axisAlignedBB.getMinX();
        double d3 = axisAlignedBB.getMaxZ() - axisAlignedBB.getMinZ();
        double d4 = axisAlignedBB.getMaxY() - axisAlignedBB.getMinY();
        double d5 = vec3i.P();
        double d6 = vec3i.o();
        double d7 = vec3i.d();
        double d8 = Double.MAX_VALUE;
        Vec3 vec33 = Vec3.create(0.0, 0.0, 0.0);
        Vec3d[] vec3dArray = new Vec3d[]{new Vec3d(0.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 0.0)};
        for (int i = 0; i <= 70; i += 10) {
            double d9 = 1.0 - (double)i / 100.0;
            double d10 = d2 / 2.0 * d9;
            double d11 = d4 / 2.0 * d9;
            double d12 = axisAlignedBB.getMinX() + (d5 > 0.0 ? d2 : (d5 < 0.0 ? 0.0 : d10));
            double d13 = axisAlignedBB.getMinZ() + (d7 > 0.0 ? d3 : (d7 < 0.0 ? 0.0 : d11));
            double d14 = axisAlignedBB.getMaxX() - (d5 < 0.0 ? d2 : (d5 > 0.0 ? 0.0 : d10));
            double d15 = axisAlignedBB.getMaxZ() - (d7 < 0.0 ? d3 : (d7 > 0.0 ? 0.0 : d11));
            block1: for (int j = 0; j <= 70; j += 10) {
                double d16 = 1.0 - (double)j / 100.0;
                double d17 = d3 / 2.0 * d16;
                double d18 = axisAlignedBB.getMinY() + (d6 > 0.0 ? d4 : (d6 < 0.0 ? 0.0 : d17));
                double d19 = axisAlignedBB.getMaxY() - (d6 < 0.0 ? d4 : (d6 > 0.0 ? 0.0 : d17));
                int n = enumFacing.Y();
                if (n == 0) {
                    vec3dArray[0].B(d12, d18, d13);
                    vec3dArray[1].B(d12, d18, d15);
                    vec3dArray[2].B(d14, d18, d15);
                    vec3dArray[3].B(d14, d18, d13);
                }
                if (n == 1) {
                    vec3dArray[0].B(d12, d19, d13);
                    vec3dArray[1].B(d14, d19, d13);
                    vec3dArray[2].B(d14, d19, d15);
                    vec3dArray[3].B(d12, d19, d15);
                }
                if (n == 2) {
                    vec3dArray[0].B(d12, d18, d13);
                    vec3dArray[1].B(d14, d18, d13);
                    vec3dArray[2].B(d14, d19, d13);
                    vec3dArray[3].B(d12, d19, d13);
                }
                if (n == 5) {
                    vec3dArray[0].B(d14, d18, d13);
                    vec3dArray[1].B(d14, d18, d15);
                    vec3dArray[2].B(d14, d19, d15);
                    vec3dArray[3].B(d14, d19, d13);
                }
                if (n == 3) {
                    vec3dArray[0].B(d12, d18, d15);
                    vec3dArray[1].B(d14, d18, d15);
                    vec3dArray[2].B(d14, d19, d15);
                    vec3dArray[3].B(d12, d19, d15);
                }
                if (n == 4) {
                    vec3dArray[0].B(d12, d18, d13);
                    vec3dArray[1].B(d12, d18, d15);
                    vec3dArray[2].B(d12, d19, d15);
                    vec3dArray[3].B(d12, d19, d13);
                }
                for (Vec3d vec3d3 : vec3dArray) {
                    double d20 = ClutchPlacementPathUtils.Z(vec3d2, vec3d3, f, f2);
                    if (d20 < d8 && d20 > 0.5) {
                        vec33.N(vec3d3.H);
                        vec33.m(vec3d3.B);
                        vec33.Z(vec3d3.i);
                        RayTraceResult rayTraceResult = RayTraceUtil.b(vec32, vec33, world, entityPlayerSP, false, false, bl, null);
                        boolean bl2 = false;
                        if (rayTraceResult.isBlockHit() && rayTraceResult.getSideHit().Y() == n) {
                            if (ForgeVersion.MC_1_7_10.Y()) {
                                bl2 = rayTraceResult.getBlockPos().equals(BlockPos.d(blockData));
                            } else {
                                boolean bl3 = bl2 = rayTraceResult.g() == blockData.D() && rayTraceResult.T() == blockData.B() && rayTraceResult.a$src$I$8nuo9d() == blockData.G();
                            }
                        }
                        if (bl2) {
                            d8 = d20;
                            if (vec3d == null) {
                                vec3d = new Vec3d();
                            }
                            vec3d.B(vec3d3.H, vec3d3.B, vec3d3.i);
                            if (d8 < 1.0) {
                                return vec3d.n();
                            }
                        }
                    }
                    if (j == 0) continue block1;
                }
            }
        }
        return vec3d != null ? vec3d.n() : null;
    }

    private static void sampleNeighbors(ClutchPlacementCoordinate clutchPlacementCoordinate, int n, Vec3d vec3d, float f, float f2, PriorityQueue<ClutchPlacementCoordinate> priorityQueue, Set<ClutchPlacementCoordinate> set) {
        double d = 0.05;
        switch (n) {
            case 0: 
            case 1: {
                for (double d2 = -d; d2 <= d; d2 += d) {
                    for (double d3 = -d; d3 <= d; d3 += d) {
                        if (d2 == 0.0 && d3 == 0.0) continue;
                        double d4 = clutchPlacementCoordinate.x + d2;
                        double d5 = clutchPlacementCoordinate.z + d3;
                        if (set.contains(priorityQueue)) continue;
                        ClutchPlacementPathUtils.addCandidate(d4, clutchPlacementCoordinate.y, d5, vec3d, f, f2, priorityQueue);
                    }
                }
                break;
            }
            case 2: 
            case 3: {
                for (double d6 = -d; d6 <= d; d6 += d) {
                    for (double d7 = -d; d7 <= d; d7 += d) {
                        if (d6 == 0.0 && d7 == 0.0) continue;
                        double d8 = clutchPlacementCoordinate.x + d6;
                        double d9 = clutchPlacementCoordinate.y + d7;
                        if (set.contains(priorityQueue)) continue;
                        ClutchPlacementPathUtils.addCandidate(d8, d9, clutchPlacementCoordinate.z, vec3d, f, f2, priorityQueue);
                    }
                }
                break;
            }
            case 4: 
            case 5: {
                for (double d10 = -d; d10 <= d; d10 += d) {
                    for (double d11 = -d; d11 <= d; d11 += d) {
                        if (d10 == 0.0 && d11 == 0.0) continue;
                        double d12 = clutchPlacementCoordinate.y + d10;
                        double d13 = clutchPlacementCoordinate.z + d11;
                        if (set.contains(priorityQueue)) continue;
                        ClutchPlacementPathUtils.addCandidate(clutchPlacementCoordinate.x, d12, d13, vec3d, f, f2, priorityQueue);
                    }
                }
                break;
            }
        }
    }

    public static Vec3 Y(EntityPlayerSP entityPlayerSP, World world, Vec3 vec3, PlacementTarget placementTarget) {
        Vec3 vec32 = null;
        boolean bl = ForgeVersion.MC_1_16_5.v();
        BlockData blockData = placementTarget.k;
        EnumFacing enumFacing = placementTarget.G;
        AxisAlignedBB axisAlignedBB = BlockUtil.F(world, blockData);
        double d = 0.002f;
        if (enumFacing == null) {
            vec32 = RotationUtil.M(vec3, axisAlignedBB, 0.0, 0.0, 0.0).n();
        } else {
            Vec3i vec3i = enumFacing.w$src$Lgg_vape_wrapper_impl_Vec3i_$ixeccr();
            axisAlignedBB = axisAlignedBB.contract(d, d, d);
            double d2 = axisAlignedBB.getMaxX() - axisAlignedBB.getMinX();
            double d3 = axisAlignedBB.getMaxZ() - axisAlignedBB.getMinZ();
            double d4 = axisAlignedBB.getMaxY() - axisAlignedBB.getMinY();
            double d5 = vec3i.P();
            double d6 = vec3i.o();
            double d7 = vec3i.d();
            for (int i = 0; i <= 100; i += 10) {
                double d8 = 1.0 - (double)i / 100.0;
                double d9 = d2 / 2.0 * d8;
                double d10 = d3 / 2.0 * d8;
                double d11 = d4 / 2.0 * d8;
                double d12 = axisAlignedBB.getMinX() + (d5 > 0.0 ? d2 : (d5 < 0.0 ? 0.0 : d9));
                double d13 = axisAlignedBB.getMinY() + (d6 > 0.0 ? d4 : (d6 < 0.0 ? 0.0 : d10));
                double d14 = axisAlignedBB.getMinZ() + (d7 > 0.0 ? d3 : (d7 < 0.0 ? 0.0 : d11));
                double d15 = axisAlignedBB.getMaxX() - (d5 < 0.0 ? d2 : (d5 > 0.0 ? 0.0 : d9));
                double d16 = axisAlignedBB.getMaxY() - (d6 < 0.0 ? d4 : (d6 > 0.0 ? 0.0 : d10));
                double d17 = axisAlignedBB.getMaxZ() - (d7 < 0.0 ? d3 : (d7 > 0.0 ? 0.0 : d11));
                Vec3[] vec3Array = new Vec3[]{};
                int n = enumFacing.Y();
                if (n == 0) {
                    vec3Array = new Vec3[]{Vec3.create(d12, d13, d14), Vec3.create(d12, d13, d17), Vec3.create(d15, d13, d17), Vec3.create(d15, d13, d14)};
                }
                if (n == 1) {
                    vec3Array = new Vec3[]{Vec3.create(d12, d16, d14), Vec3.create(d15, d16, d14), Vec3.create(d15, d16, d17), Vec3.create(d12, d16, d17)};
                }
                if (n == 2) {
                    vec3Array = new Vec3[]{Vec3.create(d12, d13, d14), Vec3.create(d15, d13, d14), Vec3.create(d15, d16, d14), Vec3.create(d12, d16, d14)};
                }
                if (n == 5) {
                    vec3Array = new Vec3[]{Vec3.create(d15, d13, d14), Vec3.create(d15, d13, d17), Vec3.create(d15, d16, d17), Vec3.create(d15, d16, d14)};
                }
                if (n == 3) {
                    vec3Array = new Vec3[]{Vec3.create(d12, d13, d17), Vec3.create(d15, d13, d17), Vec3.create(d15, d16, d17), Vec3.create(d12, d16, d17)};
                }
                if (n == 4) {
                    vec3Array = new Vec3[]{Vec3.create(d12, d13, d14), Vec3.create(d12, d13, d17), Vec3.create(d12, d16, d17), Vec3.create(d12, d16, d14)};
                }
                for (Vec3 vec33 : vec3Array) {
                    Vec3 vec34 = Vec3.create(vec3.getX(), vec3.getY(), vec3.getZ());
                    RayTraceResult rayTraceResult = world.K(vec34, vec33, false, false, bl, entityPlayerSP);
                    boolean bl2 = false;
                    if (rayTraceResult.isBlockHit() && rayTraceResult.getSideHit().Y() == n) {
                        if (ForgeVersion.MC_1_7_10.Y()) {
                            bl2 = rayTraceResult.getBlockPos().equals(BlockPos.d(blockData));
                        } else {
                            boolean bl3 = bl2 = rayTraceResult.g() == blockData.D() && rayTraceResult.T() == blockData.B() && rayTraceResult.a$src$I$8nuo9d() == blockData.G();
                        }
                    }
                    if (!bl2) continue;
                    vec32 = vec33;
                    break;
                }
                if (vec32 != null) break;
            }
        }
        return vec32;
    }

    public static Stack<BlockPlacementNode> o(BlockData blockData, EnumFacing enumFacing, EnumFacing enumFacing2, BlockPathSearchStrategy<BlockPlacementNode> blockPathSearchStrategy, int n) {
        if (n > blockPathSearchStrategy.w() || !blockPathSearchStrategy.V(blockData)) {
            return null;
        }
        if (allFacings == null) {
            allFacings = EnumFacing.t();
            horizontalFacings = EnumFacing.c$src$ALgg_vape_wrapper_impl_EnumFacing_$1i3g4ft();
        }
        int n2 = enumFacing2.c();
        int n3 = horizontalFacings[n2].Y();
        int n4 = horizontalFacings[n2 == 0 ? 3 : n2 - 1].Y();
        int n5 = horizontalFacings[(n2 + 1) % 4].Y();
        int n6 = 0;
        int[] nArray = new int[]{n3, n4, n5, n6};
        if (enumFacing != null && enumFacing.Y() != 0 && enumFacing.Y() != enumFacing2.Y()) {
            nArray = new int[]{n3};
        }
        EnumFacing enumFacing3 = enumFacing == null ? null : enumFacing.getOpposite();
        int n7 = Integer.MAX_VALUE;
        Stack<BlockPlacementNode> stack = null;
        for (int n8 : nArray) {
            EnumFacing enumFacing4 = allFacings[n8];
            if (enumFacing3 != null && enumFacing4.Y() == enumFacing3.Y() || enumFacing4.Y() == enumFacing2.getOpposite().Y()) continue;
            BlockData blockData2 = blockData.R(enumFacing4);
            boolean bl = enumFacing == null || enumFacing.Y() != 0;
            Stack<BlockPlacementNode> stack3 = new Stack<BlockPlacementNode>();
            if (blockPathSearchStrategy.B(blockData2)) {
                BlockPlacementNode node = new BlockPlacementNode(
                        blockData, enumFacing == null ? null : enumFacing3, bl);
                node.r = enumFacing4;
                stack3.add(node);
            } else {
                Stack<BlockPlacementNode> stack2 = ClutchPlacementPathUtils.o(
                        blockData2, enumFacing4, enumFacing2, blockPathSearchStrategy, n + 1);
                if (stack2 != null && !stack2.isEmpty()) {
                    BlockPlacementNode node = new BlockPlacementNode(
                            blockData, enumFacing == null ? null : enumFacing3, bl);
                    node.r = enumFacing4;
                    stack3.addAll(stack2);
                    stack3.add(node);
                }
            }
            if (stack3.isEmpty()) continue;
            int n9 = blockPathSearchStrategy.t(stack3);
            if (stack != null && n9 >= n7) continue;
            stack = stack3;
            n7 = n9;
        }
        return stack;
    }

    private static ObfuscatedRuntimeException passThrough(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static float Z(Vec3d vec3d, Vec3d vec3d2, float f, float f2) {
        double d = vec3d.Y() - vec3d2.Y();
        double d2 = vec3d.o() - vec3d2.o();
        double d3 = vec3d.t() - vec3d2.t();
        double d4 = MathUtil.sqrt(d * d + d2 * d2);
        float f3 = (float)RotationUtil.N(vec3d.Y(), vec3d.o(), f, vec3d2.Y(), vec3d2.o());
        float f4 = MathUtil.wrapAngleTo180((float)Math.toDegrees(FastAtanMath.r((float)d3, (float)d4)));
        float f5 = Math.abs(MathUtil.wrapAngleTo180(f3));
        float f6 = Math.abs(MathUtil.wrapAngleTo180(f4 - f2));
        return (float)Math.sqrt(f5 * f5 + f6 * f6);
    }

    public static boolean Q(World world, Entity entity, AxisAlignedBB axisAlignedBB, Vec3 vec3, Vec3 vec32) {
        boolean bl = true;
        List list = world.F(entity, axisAlignedBB);
        if (!list.isEmpty()) {
            for (Object e : list) {
                Entity entity2;
                if (e == null || !(entity2 = new Entity(e)).n$src$Z$fx7gig()) continue;
                float f = entity2.b();
                AxisAlignedBB axisAlignedBB2 = entity2.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().expand(f, f, f);
                RayTraceResult rayTraceResult = axisAlignedBB2.calculateIntercept(vec3, vec32);
                if (!axisAlignedBB2.isVecInside(vec3) && (rayTraceResult == null || !rayTraceResult.isNotNull())) continue;
                bl = false;
            }
        }
        return bl;
    }

    public static boolean P(Vec3 vec3, World world, BlockData blockData, EnumFacing enumFacing) {
        boolean bl = false;
        if (enumFacing != null) {
            int n = enumFacing.Y();
            AxisAlignedBB axisAlignedBB = BlockUtil.F(world, blockData);
            double d = vec3.getX();
            double d2 = vec3.getZ();
            double d3 = vec3.getY();
            switch (n) {
                case 1: {
                    if (!(d3 > axisAlignedBB.getMaxY())) break;
                    bl = true;
                    break;
                }
                case 0: {
                    if (!(axisAlignedBB.getMinY() > d3)) break;
                    bl = true;
                    break;
                }
                case 2: {
                    if (!(axisAlignedBB.getMinZ() > d2)) break;
                    bl = true;
                    break;
                }
                case 3: {
                    if (!(d2 > axisAlignedBB.getMaxZ())) break;
                    bl = true;
                    break;
                }
                case 4: {
                    if (!(axisAlignedBB.getMinX() > d)) break;
                    bl = true;
                    break;
                }
                case 5: {
                    if (!(d > axisAlignedBB.getMaxX())) break;
                    bl = true;
                }
            }
        } else {
            bl = true;
        }
        return bl;
    }

    public static boolean S(Vec3 vec3, EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP, World world, BlockData blockData, EnumFacing enumFacing) {
        boolean bl;
        block14: {
            AxisAlignedBB axisAlignedBB;
            boolean bl2;
            block13: {
                bl = false;
                bl2 = ForgeVersion.MC_1_16_5.v();
                axisAlignedBB = BlockUtil.F(world, blockData);
                axisAlignedBB = axisAlignedBB.contract(0.002f, 0.002f, 0.002f);
                if (enumFacing != null) break block13;
                Vec3 vec32 = RotationUtil.T(entityPlayer, axisAlignedBB, 0.0, 0.0, 0.0).n();
                RayTraceResult rayTraceResult = world.K(vec3, vec32, false, false, bl2, entityPlayerSP);
                boolean bl3 = rayTraceResult.isNull() || rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.miss());
                boolean bl4 = false;
                if (!bl3 && rayTraceResult.isBlockHit()) {
                    if (ForgeVersion.MC_1_7_10.Y()) {
                        bl4 = rayTraceResult.getBlockPos().equals(BlockPos.d(blockData));
                    } else {
                        boolean bl5 = bl4 = rayTraceResult.g() == blockData.D() && rayTraceResult.T() == blockData.B() && rayTraceResult.a$src$I$8nuo9d() == blockData.G();
                    }
                }
                if (!bl3 && !bl4) break block14;
                bl = true;
                break block14;
            }
            double d = axisAlignedBB.getMinX();
            double d2 = axisAlignedBB.getMinY();
            double d3 = axisAlignedBB.getMinZ();
            double d4 = axisAlignedBB.getMaxX();
            double d5 = axisAlignedBB.getMaxY();
            double d6 = axisAlignedBB.getMaxZ();
            Vec3[] vec3Array = new Vec3[]{};
            int n = enumFacing.Y();
            if (n == 0) {
                vec3Array = new Vec3[]{Vec3.create(d, d2, d3), Vec3.create(d, d2, d6), Vec3.create(d4, d2, d6), Vec3.create(d4, d2, d3)};
            }
            if (n == 1) {
                vec3Array = new Vec3[]{Vec3.create(d, d5, d3), Vec3.create(d4, d5, d3), Vec3.create(d4, d5, d6), Vec3.create(d, d5, d6)};
            }
            if (n == 2) {
                vec3Array = new Vec3[]{Vec3.create(d, d2, d3), Vec3.create(d4, d2, d3), Vec3.create(d4, d5, d3), Vec3.create(d, d5, d3)};
            }
            if (n == 5) {
                vec3Array = new Vec3[]{Vec3.create(d4, d2, d3), Vec3.create(d4, d2, d6), Vec3.create(d4, d5, d6), Vec3.create(d4, d5, d3)};
            }
            if (n == 3) {
                vec3Array = new Vec3[]{Vec3.create(d, d2, d6), Vec3.create(d4, d2, d6), Vec3.create(d4, d5, d6), Vec3.create(d, d5, d6)};
            }
            if (n == 4) {
                vec3Array = new Vec3[]{Vec3.create(d, d2, d3), Vec3.create(d, d2, d6), Vec3.create(d, d5, d6), Vec3.create(d, d5, d3)};
            }
            for (Vec3 vec33 : vec3Array) {
                Vec3 vec34 = Vec3.create(vec3.getX(), vec3.getY(), vec3.getZ());
                RayTraceResult rayTraceResult = world.K(vec34, vec33, false, false, bl2, entityPlayerSP);
                boolean bl6 = rayTraceResult.isNull() || rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.miss());
                boolean bl7 = false;
                if (!bl6 && rayTraceResult.isBlockHit()) {
                    if (ForgeVersion.MC_1_7_10.Y()) {
                        bl7 = rayTraceResult.getBlockPos().equals(BlockPos.d(blockData));
                    } else {
                        boolean bl8 = bl7 = rayTraceResult.g() == blockData.D() && rayTraceResult.T() == blockData.B() && rayTraceResult.a$src$I$8nuo9d() == blockData.G();
                    }
                }
                if (!bl6 && !bl7) continue;
                bl = true;
                break;
            }
        }
        return bl;
    }

    private static void addFaceCenter(AxisAlignedBB axisAlignedBB, int n, Vec3d vec3d, float f, float f2, PriorityQueue<ClutchPlacementCoordinate> priorityQueue) {
        double d = (axisAlignedBB.getMinX() + axisAlignedBB.getMaxX()) / 2.0;
        double d2 = (axisAlignedBB.getMinY() + axisAlignedBB.getMaxY()) / 2.0;
        double d3 = (axisAlignedBB.getMinZ() + axisAlignedBB.getMaxZ()) / 2.0;
        switch (n) {
            case 0: {
                ClutchPlacementPathUtils.addCandidate(d, axisAlignedBB.getMinY(), d3, vec3d, f, f2, priorityQueue);
                break;
            }
            case 1: {
                ClutchPlacementPathUtils.addCandidate(d, axisAlignedBB.getMaxY(), d3, vec3d, f, f2, priorityQueue);
                break;
            }
            case 2: {
                ClutchPlacementPathUtils.addCandidate(d, d2, axisAlignedBB.getMinZ(), vec3d, f, f2, priorityQueue);
                break;
            }
            case 3: {
                ClutchPlacementPathUtils.addCandidate(d, d2, axisAlignedBB.getMaxZ(), vec3d, f, f2, priorityQueue);
                break;
            }
            case 4: {
                ClutchPlacementPathUtils.addCandidate(axisAlignedBB.getMinX(), d2, d3, vec3d, f, f2, priorityQueue);
                break;
            }
            case 5: {
                ClutchPlacementPathUtils.addCandidate(axisAlignedBB.getMaxX(), d2, d3, vec3d, f, f2, priorityQueue);
            }
        }
    }

    public static boolean e(Block block) {
        boolean bl = false;
        for (ItemLimitData itemLimitData : ItemLimitData.g) {
            if (!block.U().equalsIgnoreCase(itemLimitData.getName())) continue;
            bl = true;
            break;
        }
        return bl;
    }

    private static void addCandidate(double d, double d2, double d3, Vec3d vec3d, float f, float f2, PriorityQueue<ClutchPlacementCoordinate> priorityQueue) {
        double d4 = ClutchPlacementPathUtils.Z(vec3d, new Vec3d(d, d2, d3), f, f2);
        priorityQueue.add(new ClutchPlacementCoordinate(d, d2, d3, d4));
    }

    public static Vector<PlacementTarget> o(BlockData blockData, BlockData blockData2, BlockPathSearchStrategy<PlacementTarget> blockPathSearchStrategy, int n) {
        int n2;
        int n3;
        if (n > blockPathSearchStrategy.w()) {
            return null;
        }
        if (allFacings == null) {
            allFacings = EnumFacing.t();
            horizontalFacings = EnumFacing.c$src$ALgg_vape_wrapper_impl_EnumFacing_$1i3g4ft();
        }
        int n4 = blockData2.D() - blockData.D();
        int n5 = blockData2.B() - blockData.B();
        int n6 = blockData2.G() - blockData.G();
        int n7 = n4 > 0 ? 5 : (n3 = n4 < 0 ? 4 : -1);
        int n8 = n5 > 0 ? 1 : (n2 = n5 < 0 ? 0 : -1);
        int n9 = n6 > 0 ? 3 : (n6 < 0 ? 2 : -1);
        int[] nArray = new int[]{n7, n8, n9};
        int n10 = Integer.MAX_VALUE;
        Vector<PlacementTarget> vector = null;
        for (int n11 : nArray) {
            if (n11 == -1) continue;
            EnumFacing enumFacing = allFacings[n11];
            BlockData blockData3 = blockData;
            BlockData blockData4 = blockData3.R(enumFacing);
            Vector<PlacementTarget> vector2 = new Vector<PlacementTarget>();
            if (blockPathSearchStrategy.B(blockData4)) {
                if (blockData4.equals(blockData2)) {
                    PlacementTarget placementTarget = new PlacementTarget(blockData3, enumFacing);
                    placementTarget.Y = n;
                    vector2.addElement(placementTarget);
                } else {
                    Vector<PlacementTarget> vector3 = ClutchPlacementPathUtils.o(blockData4, blockData2, blockPathSearchStrategy, n + 1);
                    if (vector3 != null && !vector3.isEmpty()) {
                        PlacementTarget placementTarget = new PlacementTarget(blockData3, enumFacing);
                        placementTarget.Y = n;
                        vector2.addAll(vector3);
                        vector2.add(placementTarget);
                    }
                }
            }
            if (vector2.isEmpty()) continue;
            if (n == 0) {
                BlockIn.O.add(new Vector(vector2));
            }
            int n12 = blockPathSearchStrategy.g(vector2, n);
            if (vector != null && n12 >= n10) continue;
            vector = vector2;
            n10 = n12;
        }
        return vector;
    }


    public static float y(Vec3 vec3, Vec3 vec32, float f, float f2) {
        double d = vec3.getX() - vec32.getX();
        double d2 = vec3.getZ() - vec32.getZ();
        double d3 = vec3.getY() - vec32.getY();
        double d4 = MathUtil.sqrt(d * d + d2 * d2);
        float f3 = (float)RotationUtil.N(vec3.getX(), vec3.getZ(), f, vec32.getX(), vec32.getZ());
        float f4 = MathUtil.wrapAngleTo180((float)Math.toDegrees(FastAtanMath.r((float)d3, (float)d4)));
        float f5 = Math.abs(MathUtil.wrapAngleTo180(f3));
        float f6 = Math.abs(MathUtil.wrapAngleTo180(f4 - f2));
        return f5 * f5 + f6 * f6;
    }

    public static boolean V(World world, Entity entity, BlockData blockData) {
        boolean bl = true;
        AxisAlignedBB axisAlignedBB = BlockUtil.F(world, blockData);
        List list = world.F(entity, axisAlignedBB);
        if (!list.isEmpty()) {
            for (Object e : list) {
                AxisAlignedBB axisAlignedBB2;
                Entity entity2;
                if (e == null || (entity2 = new Entity(e)).M$src$Z$ff28xj() || !entity2.t$src$Z$g0i82m() || entity2.equals(entity) || !(axisAlignedBB2 = entity2.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl()).intersects(axisAlignedBB)) continue;
                bl = false;
                break;
            }
        }
        return bl;
    }

    private static void addFaceCorners(AxisAlignedBB axisAlignedBB, int n, Vec3d vec3d, float f, float f2, PriorityQueue<ClutchPlacementCoordinate> priorityQueue) {
        double d = axisAlignedBB.getMinX();
        double d2 = axisAlignedBB.getMinY();
        double d3 = axisAlignedBB.getMinZ();
        double d4 = axisAlignedBB.getMaxX();
        double d5 = axisAlignedBB.getMaxY();
        double d6 = axisAlignedBB.getMaxZ();
        switch (n) {
            case 0: {
                ClutchPlacementPathUtils.addCandidate(d, d2, d3, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d, d2, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d2, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d2, d3, vec3d, f, f2, priorityQueue);
                break;
            }
            case 1: {
                ClutchPlacementPathUtils.addCandidate(d, d5, d3, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d5, d3, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d5, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d, d5, d6, vec3d, f, f2, priorityQueue);
                break;
            }
            case 2: {
                ClutchPlacementPathUtils.addCandidate(d, d2, d3, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d2, d3, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d5, d3, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d, d5, d3, vec3d, f, f2, priorityQueue);
                break;
            }
            case 5: {
                ClutchPlacementPathUtils.addCandidate(d4, d2, d3, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d2, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d5, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d5, d3, vec3d, f, f2, priorityQueue);
                break;
            }
            case 3: {
                ClutchPlacementPathUtils.addCandidate(d, d2, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d2, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d5, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d, d5, d6, vec3d, f, f2, priorityQueue);
                break;
            }
            case 4: {
                ClutchPlacementPathUtils.addCandidate(d, d2, d3, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d, d2, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d, d5, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d, d5, d3, vec3d, f, f2, priorityQueue);
            }
        }
    }

    private static void addFaceEdges(AxisAlignedBB axisAlignedBB, int n, Vec3d vec3d, float f, float f2, PriorityQueue<ClutchPlacementCoordinate> priorityQueue) {
        double d = axisAlignedBB.getMinX();
        double d2 = axisAlignedBB.getMinY();
        double d3 = axisAlignedBB.getMinZ();
        double d4 = axisAlignedBB.getMaxX();
        double d5 = axisAlignedBB.getMaxY();
        double d6 = axisAlignedBB.getMaxZ();
        double d7 = (d + d4) / 2.0;
        double d8 = (d2 + d5) / 2.0;
        double d9 = (d3 + d6) / 2.0;
        switch (n) {
            case 0: 
            case 1: {
                double d10 = n == 0 ? d2 : d5;
                ClutchPlacementPathUtils.addCandidate(d7, d10, d3, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d7, d10, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d, d10, d9, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d10, d9, vec3d, f, f2, priorityQueue);
                break;
            }
            case 2: 
            case 3: {
                double d11 = n == 2 ? d3 : d6;
                ClutchPlacementPathUtils.addCandidate(d7, d2, d11, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d7, d5, d11, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d, d8, d11, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d4, d8, d11, vec3d, f, f2, priorityQueue);
                break;
            }
            case 4: 
            case 5: {
                double d12 = n == 4 ? d : d4;
                ClutchPlacementPathUtils.addCandidate(d12, d8, d3, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d12, d8, d6, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d12, d2, d9, vec3d, f, f2, priorityQueue);
                ClutchPlacementPathUtils.addCandidate(d12, d5, d9, vec3d, f, f2, priorityQueue);
            }
        }
    }

    public static Vec3 n(EntityPlayerSP entityPlayerSP, World world, Vec3 vec3, PlacementTarget placementTarget, float f, float f2) {
        Vec3d vec3d = null;
        boolean bl = ForgeVersion.MC_1_16_5.v();
        BlockData blockData = placementTarget.k;
        EnumFacing enumFacing = placementTarget.G;
        AxisAlignedBB axisAlignedBB = BlockUtil.F(world, blockData);
        double d = 0.002f;
        if (enumFacing == null) {
            return RotationUtil.M(vec3, axisAlignedBB, 0.0, 0.0, 0.0).n();
        }
        Vec3d vec3d2 = new Vec3d(vec3.getX(), vec3.getY(), vec3.getZ());
        Vec3 vec32 = Vec3.create(vec3.getX(), vec3.getY(), vec3.getZ());
        Vec3i vec3i = enumFacing.w$src$Lgg_vape_wrapper_impl_Vec3i_$ixeccr();
        axisAlignedBB = axisAlignedBB.contract(d, d, d);
        double d2 = axisAlignedBB.getMaxX() - axisAlignedBB.getMinX();
        double d3 = axisAlignedBB.getMaxZ() - axisAlignedBB.getMinZ();
        double d4 = axisAlignedBB.getMaxY() - axisAlignedBB.getMinY();
        double d5 = vec3i.P();
        double d6 = vec3i.o();
        double d7 = vec3i.d();
        double d8 = Double.MAX_VALUE;
        Vec3 vec33 = Vec3.create(0.0, 0.0, 0.0);
        Vec3d[] vec3dArray = new Vec3d[]{new Vec3d(0.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 0.0)};
        for (int i = 0; i <= 70; i += 10) {
            double d9 = 1.0 - (double)i / 100.0;
            double d10 = d2 / 2.0 * d9;
            double d11 = d4 / 2.0 * d9;
            double d12 = axisAlignedBB.getMinX() + (d5 > 0.0 ? d2 : (d5 < 0.0 ? 0.0 : d10));
            double d13 = axisAlignedBB.getMinZ() + (d7 > 0.0 ? d3 : (d7 < 0.0 ? 0.0 : d11));
            double d14 = axisAlignedBB.getMaxX() - (d5 < 0.0 ? d2 : (d5 > 0.0 ? 0.0 : d10));
            double d15 = axisAlignedBB.getMaxZ() - (d7 < 0.0 ? d3 : (d7 > 0.0 ? 0.0 : d11));
            block1: for (int j = 0; j <= 70; j += 10) {
                double d16 = 1.0 - (double)j / 100.0;
                double d17 = d3 / 2.0 * d16;
                double d18 = axisAlignedBB.getMinY() + (d6 > 0.0 ? d4 : (d6 < 0.0 ? 0.0 : d17));
                double d19 = axisAlignedBB.getMaxY() - (d6 < 0.0 ? d4 : (d6 > 0.0 ? 0.0 : d17));
                int n = enumFacing.Y();
                if (n == 0) {
                    vec3dArray[0].B(d12, d18, d13);
                    vec3dArray[1].B(d12, d18, d15);
                    vec3dArray[2].B(d14, d18, d15);
                    vec3dArray[3].B(d14, d18, d13);
                }
                if (n == 1) {
                    vec3dArray[0].B(d12, d19, d13);
                    vec3dArray[1].B(d14, d19, d13);
                    vec3dArray[2].B(d14, d19, d15);
                    vec3dArray[3].B(d12, d19, d15);
                }
                if (n == 2) {
                    vec3dArray[0].B(d12, d18, d13);
                    vec3dArray[1].B(d14, d18, d13);
                    vec3dArray[2].B(d14, d19, d13);
                    vec3dArray[3].B(d12, d19, d13);
                }
                if (n == 5) {
                    vec3dArray[0].B(d14, d18, d13);
                    vec3dArray[1].B(d14, d18, d15);
                    vec3dArray[2].B(d14, d19, d15);
                    vec3dArray[3].B(d14, d19, d13);
                }
                if (n == 3) {
                    vec3dArray[0].B(d12, d18, d15);
                    vec3dArray[1].B(d14, d18, d15);
                    vec3dArray[2].B(d14, d19, d15);
                    vec3dArray[3].B(d12, d19, d15);
                }
                if (n == 4) {
                    vec3dArray[0].B(d12, d18, d13);
                    vec3dArray[1].B(d12, d18, d15);
                    vec3dArray[2].B(d12, d19, d15);
                    vec3dArray[3].B(d12, d19, d13);
                }
                for (Vec3d vec3d3 : vec3dArray) {
                    double d20 = ClutchPlacementPathUtils.Z(vec3d2, vec3d3, f, f2);
                    if (d20 < d8 && d20 > 0.5) {
                        vec33.N(vec3d3.H);
                        vec33.m(vec3d3.B);
                        vec33.Z(vec3d3.i);
                        RayTraceResult rayTraceResult = world.K(vec32, vec33, false, false, bl, entityPlayerSP);
                        boolean bl2 = false;
                        if (rayTraceResult.isBlockHit() && rayTraceResult.getSideHit().Y() == n) {
                            if (ForgeVersion.MC_1_7_10.Y()) {
                                bl2 = rayTraceResult.getBlockPos().equals(BlockPos.d(blockData));
                            } else {
                                boolean bl3 = bl2 = rayTraceResult.g() == blockData.D() && rayTraceResult.T() == blockData.B() && rayTraceResult.a$src$I$8nuo9d() == blockData.G();
                            }
                        }
                        if (bl2) {
                            d8 = d20;
                            if (vec3d == null) {
                                vec3d = new Vec3d();
                            }
                            vec3d.B(vec3d3.H, vec3d3.B, vec3d3.i);
                            if (d8 < 1.0) {
                                return vec3d.n();
                            }
                        }
                    }
                    if (j == 0) continue block1;
                }
            }
        }
        return vec3d != null ? vec3d.n() : null;
    }

    public static boolean J(BlockData blockData, EntityPlayerSP entityPlayerSP, World world, BlockData blockData2, EnumFacing enumFacing) {
        boolean bl;
        block14: {
            double d;
            AxisAlignedBB axisAlignedBB;
            boolean bl2;
            block13: {
                Vec3 vec3;
                bl = false;
                bl2 = ForgeVersion.MC_1_16_5.v();
                axisAlignedBB = BlockUtil.F(world, blockData2);
                axisAlignedBB = axisAlignedBB.contract(0.002f, 0.002f, 0.002f);
                double d2 = d = ForgeVersion.MC_1_7_10.Y() ? (double)entityPlayerSP.X() : 0.0;
                if (enumFacing != null) break block13;
                Vec3 vec32 = Vec3.create((double)blockData.D() + 0.5, entityPlayerSP.A() + d, (double)blockData.G() + 0.5);
                RayTraceResult rayTraceResult = world.K(vec32, vec3 = RotationUtil.T(entityPlayerSP, axisAlignedBB, 0.0, 0.0, 0.0).n(), false, false, bl2, entityPlayerSP);
                boolean bl3 = rayTraceResult.isNull() || rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.miss());
                boolean bl4 = false;
                if (!bl3 && rayTraceResult.isBlockHit()) {
                    if (ForgeVersion.MC_1_7_10.Y()) {
                        bl4 = rayTraceResult.getBlockPos().equals(BlockPos.d(blockData2));
                    } else {
                        boolean bl5 = bl4 = rayTraceResult.g() == blockData2.D() && rayTraceResult.T() == blockData2.B() && rayTraceResult.a$src$I$8nuo9d() == blockData2.G();
                    }
                }
                if (!bl3 && !bl4) break block14;
                bl = true;
                break block14;
            }
            Vec3 vec3 = Vec3.create((double)blockData.D() + 0.5, entityPlayerSP.A() + d, (double)blockData.G() + 0.5);
            double d3 = axisAlignedBB.getMinX();
            double d4 = axisAlignedBB.getMinY();
            double d5 = axisAlignedBB.getMinZ();
            double d6 = axisAlignedBB.getMaxX();
            double d7 = axisAlignedBB.getMaxY();
            double d8 = axisAlignedBB.getMaxZ();
            Vec3[] vec3Array = new Vec3[]{};
            int n = enumFacing.Y();
            if (n == 0) {
                vec3Array = new Vec3[]{Vec3.create(d3, d4, d5), Vec3.create(d3, d4, d8), Vec3.create(d6, d4, d8), Vec3.create(d6, d4, d5)};
            }
            if (n == 1) {
                vec3Array = new Vec3[]{Vec3.create(d3, d7, d5), Vec3.create(d6, d7, d5), Vec3.create(d6, d7, d8), Vec3.create(d3, d7, d8)};
            }
            if (n == 2) {
                vec3Array = new Vec3[]{Vec3.create(d3, d4, d5), Vec3.create(d6, d4, d5), Vec3.create(d6, d7, d5), Vec3.create(d3, d7, d5)};
            }
            if (n == 5) {
                vec3Array = new Vec3[]{Vec3.create(d6, d4, d5), Vec3.create(d6, d4, d8), Vec3.create(d6, d7, d8), Vec3.create(d6, d7, d5)};
            }
            if (n == 3) {
                vec3Array = new Vec3[]{Vec3.create(d3, d4, d8), Vec3.create(d6, d4, d8), Vec3.create(d6, d7, d8), Vec3.create(d3, d7, d8)};
            }
            if (n == 4) {
                vec3Array = new Vec3[]{Vec3.create(d3, d4, d5), Vec3.create(d3, d4, d8), Vec3.create(d3, d7, d8), Vec3.create(d3, d7, d5)};
            }
            for (Vec3 vec33 : vec3Array) {
                Vec3 vec34 = Vec3.create(vec3.getX(), vec3.getY(), vec3.getZ());
                RayTraceResult rayTraceResult = world.K(vec34, vec33, false, false, bl2, entityPlayerSP);
                boolean bl6 = rayTraceResult.isNull() || rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.miss());
                boolean bl7 = false;
                if (!bl6 && rayTraceResult.isBlockHit()) {
                    if (ForgeVersion.MC_1_7_10.Y()) {
                        bl7 = rayTraceResult.getBlockPos().equals(BlockPos.d(blockData2));
                    } else {
                        boolean bl8 = bl7 = rayTraceResult.g() == blockData2.D() && rayTraceResult.T() == blockData2.B() && rayTraceResult.a$src$I$8nuo9d() == blockData2.G();
                    }
                }
                if (!bl6 && !bl7) continue;
                bl = true;
                break;
            }
        }
        return bl;
    }
}
