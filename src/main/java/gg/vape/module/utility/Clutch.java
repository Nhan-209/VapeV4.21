package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.input.MovementInputLock;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.blockin.BlockPlacementPathSegmentState;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.utility.clutch.BlockPathSearchStrategy;
import gg.vape.module.utility.clutch.BlockPlacementNode;
import gg.vape.module.utility.clutch.ClutchBlockPlacementPathSearchStrategy;
import gg.vape.module.utility.clutch.ClutchPlacementPathUtils;
import gg.vape.module.utility.clutch.ClutchSolidBlockPathSearchStrategy;
import gg.vape.module.utility.clutch.PlacementTarget;
import gg.vape.movement.PlayerMovementTaskManager;
import gg.vape.movement.TargetPositionMovementTask;
import gg.vape.notification.NotificationType;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.PointRotationController;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.unmap.ItemLimitData;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.utils.datas.BlockData;
import gg.vape.utils.datas.DirectionalPosition;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.Enchantment;
import gg.vape.wrapper.impl.EnchantmentHelper;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionRegistry;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;
import java.util.Vector;
import org.lwjgl.opengl.GL11;

public class Clutch
extends Mod {
    private final BooleanValue keepSneak;
    private final ArrayList<String> blockPriorityNames;
    private final MovementInputLock movementLock;
    private TargetPositionMovementTask movementTask;
    private final ModeOption lowestCostMode = new ModeOption("Lowest cost");
    private final NumberValue aimSpeed;
    private final ArrayList<BlockPlacementNode> placementNodes;
    private final BooleanValue returnToLastSlot;
    private FixedRotationController rotationController;
    private final RotationControlClaim rotationClaim;
    private final int[][] faceOffsets;
    private int previousSlot = -1;
    private final ModeOption hardestMode = new ModeOption("Hardest");
    private final BooleanValue useBlacklist;
    private final RandomValue placeDelay;
    private PlacementTarget currentTarget;
    private final TimerUtil clickTimer;
    private final EnumFacing[] horizontalFacings = EnumFacing.c$src$ALgg_vape_wrapper_impl_EnumFacing_$1i3g4ft();
    private final BooleanValue silentAim;
    private final BooleanValue sneak;
    private final TimerUtil aimTimer;
    private boolean paused;
    private final ModeValue blockPriorityMode;
    private boolean sneaking;
    private final BooleanValue bedFinder;
    private final EnumFacing[] verticalFacings = EnumFacing.t();
    private final LimitValue blockBlacklist;
    private int blockSlot = -1;

    private int countTargets(World world, Vector<PlacementTarget> vector) {
        int n = vector.size();
        return n;
    }

    private static int lambda$findPlacePath$0(int n, BlockPlacementPathSegmentState blockPlacementPathSegmentState, BlockPlacementPathSegmentState blockPlacementPathSegmentState2) {
        int n2 = Math.abs(n - blockPlacementPathSegmentState.W.Y());
        int n3 = Math.abs(n - blockPlacementPathSegmentState2.W.Y());
        return Integer.compare(n3, n2);
    }

    public static int F(Clutch clutch, World world, Vector vector) {
        return clutch.countTargets(world, vector);
    }

    private void computeMinToolTicks(EntityPlayerSP entityPlayerSP, World world, BlockData blockData) {
        int n = Integer.MAX_VALUE;
        InventoryPlayer inventoryPlayer = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        for (int i = 0; i < 9; ++i) {
            int n2;
            ItemStack itemStack = inventoryPlayer.c(i);
            float f = this.computeBreakScore(entityPlayerSP, world, blockData, itemStack);
            if (f == 0.0f || (n2 = (int)(Math.floor(1.0 / (double)f) + 1.0)) >= n) continue;
            n = n2;
        }
    }

    @Override
    public boolean X() {
        return true;
    }

    private boolean isUsableBlockSlot(EntityPlayerSP entityPlayerSP, World world, int n) {
        if (entityPlayerSP.isNull() || world.isNull()) {
            return false;
        }
        ItemStack itemStack = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(n);
        if (itemStack.isNull() || itemStack.getItem().isNull() || itemStack.t() <= 0) {
            return false;
        }
        return this.isAllowedBlock(itemStack);
    }


    public Clutch() {
        super("Block-In", 8191953, Category.m, "Automatically blocks you in by building walls around you");
        this.faceOffsets = new int[][]{null, null, {5, 4}, {5, 4}, {2, 3}, {2, 3}};
        this.silentAim = BooleanValue.create(this, "Silent aim", false, "Uses Silent Aim system");
        this.sneak = BooleanValue.create(this, "Sneak", false, "Sneak when placing blocks");
        this.keepSneak = BooleanValue.create(this, "Keep sneak", true, "Keeps sneak held after placing blocks.\nMust press sneak again to release");
        this.bedFinder = BooleanValue.create(this, "Bed finder", true, "Finds nearby beds to block in towards");
        this.blockPriorityMode = ModeValue.create((Object)this, "Block priority", "Lowest cost - Prioritizes by block type: Wool > Stone > Wood > Sandstone > Stained Clay > End Stone > Obsidian\nHardest - uses the block hardness value.", (ModeSelection)this.lowestCostMode, this.lowestCostMode, this.hardestMode);
        this.placeDelay = RandomValue.G(this, "Place delay", "#", "ms", 0.0, 0.0, 30.0, 250.0, 1.0, "Delay between each block placement");
        this.aimSpeed = NumberValue.create(this, "Aim speed", "#.#", "", 1.0, 12.0, 25.0, 0.1, "Speed of aim when placing blocks");
        this.returnToLastSlot = BooleanValue.create(this, "Return to last slot", true, "Returns hotbar to previous slot when completed");
        this.useBlacklist = BooleanValue.create(this, "Use blacklist", true, "BlockIn will not use these blocks");
        this.blockBlacklist = LimitValue.n(this, "blockin-blacklist", "Block blacklist", LimitValue.G, ItemLimitData.P);
        this.blockPriorityNames = new ArrayList<String>(Arrays.asList("Wool", "Stone", "Wood Planks", "Red Sandstone", "Stained Clay", "End Stone", "Obsidian"));
        this.rotationClaim = SharedModuleControlClaims.I;
        this.movementLock = SharedModuleControlClaims.l;
        this.clickTimer = new TimerUtil();
        this.aimTimer = new TimerUtil();
        this.placementNodes = new ArrayList();
        this.useBlacklist.K(this.blockBlacklist);
        this.sneak.K(this.keepSneak);
        this.addValue(this.aimSpeed, this.placeDelay, this.blockPriorityMode, this.silentAim, this.sneak, this.keepSneak, this.bedFinder, this.returnToLastSlot, this.useBlacklist, this.blockBlacklist);
        this.rotationClaim.l(this, 9);
    }

    @EventHandler
    public void O(EventKeyPress eventKeyPress) {
        if (!this.sneak.L().booleanValue()) {
            return;
        }
        KeyBinding keyBinding = Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
        int n = ClientSettings.H(keyBinding);
        if (n == eventKeyPress.getKey()) {
            eventKeyPress.setCancelled(true);
        }
    }

    @Override
    public void onDisable() {
        if (this.sneaking && !this.keepSneak.L().booleanValue()) {
            this.sneaking = false;
            KeyBinding keyBinding = Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
            keyBinding.Z();
        }
    }

    private Stack<BlockPlacementNode> findBestPath(BlockData blockData, EnumFacing enumFacing, World world, BlockPathSearchStrategy blockPathSearchStrategy) {
        Object object;
        int n;
        Stack<BlockPlacementNode> stack = null;
        int n2 = Integer.MAX_VALUE;
        for (n = 0; n < 4; ++n) {
            Stack<BlockPlacementNode> stack2;
            int n3 = enumFacing.c();
            int n4 = (n3 + n) % 4;
            object = this.horizontalFacings[n4];
            if (((Wrapper)object).equals(enumFacing.getOpposite()) || (stack2 = ClutchPlacementPathUtils.o(blockData, null, enumFacing, blockPathSearchStrategy, 0)) == null || stack2.isEmpty()) continue;
            int n5 = this.computePathCost(world, stack2);
            if (stack != null && n5 >= n2) continue;
            n2 = n5;
            stack = stack2;
        }
        if (stack != null && !stack.isEmpty()) {
            for (n = stack.size() - 1; n >= 2; --n) {
                BlockPlacementNode blockPlacementNode = (BlockPlacementNode)stack.get(n);
                BlockPlacementNode blockPlacementNode2 = (BlockPlacementNode)stack.get(n - 1);
                object = (BlockPlacementNode)stack.get(n - 2);
                if (!blockPlacementNode.w() || blockPlacementNode2.w() || !((BlockPlacementNode)object).w()) continue;
                blockPlacementNode.w(blockPlacementNode2.r);
                ((BlockPlacementNode)object).W(((BlockPlacementNode)object).P);
            }
        }
        return stack;
    }

    private int[] faceOffsetsFor(EnumFacing enumFacing) {
        return this.faceOffsets[enumFacing.Y()];
    }

    private void reset() {
        if (this.returnToLastSlot.L().booleanValue() && this.previousSlot != -1) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            if (entityPlayerSP.isNotNull() && this.previousSlot != entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v()) {
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.previousSlot);
            }
            this.previousSlot = -1;
        }
        this.currentTarget = null;
        this.blockSlot = -1;
        this.placementNodes.removeIf(BlockPlacementNode::D);
        this.clearMovementTask();
        this.updateRotationController();
        if (this.r$src$Z$14eylz9() && !this.paused) {
            super.Y(false);
        }
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        WorldClient worldClient = eventPreTick.getWorld();
        if (entityPlayerSP.isNull() || worldClient.isNull()) {
            this.reset();
            return;
        }
        if (this.paused || this.rotationClaim.e(this)) {
            this.reset();
            return;
        }
        if (!this.rotationClaim.U(this) && !this.rotationClaim.h(this, this.silentAim.L())) {
            return;
        }
        if (this.blockSlot == -1 || !this.isUsableBlockSlot(entityPlayerSP, worldClient, this.blockSlot)) {
            this.blockSlot = this.selectBestBlockSlot(entityPlayerSP, worldClient);
        }
        if (this.blockSlot != -1 && !this.paused && this.placementNodes.isEmpty()) {
            this.buildPlacementNodes(entityPlayerSP, worldClient);
        }
        if (this.blockSlot != -1 && !this.placementNodes.isEmpty() && !this.paused) {
            Object object;
            BlockPlacementNode blockPlacementNode = this.placementNodes.get(0);
            BlockData blockData = blockPlacementNode.h;
            if (!ClutchPlacementPathUtils.V(worldClient, entityPlayerSP, blockData)) {
                this.reset();
                Vape.INSTANCE.getNotificationManager().t("Block-In Disabled", "Entity in the way!", NotificationType.WARNING, 2000L);
                return;
            }
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.blockSlot);
            boolean bl = BlockUtil.e(entityPlayerSP, blockData);
            if (!bl) {
                double d = (double)blockData.D() + 0.5;
                double d2 = (double)blockData.G() + 0.5;
                double d3 = entityPlayerSP.i(d, entityPlayerSP.N(), d2);
                if (d3 > 1.75) {
                    this.reset();
                    return;
                }
                this.movementLock.K(this);
                if (this.movementTask == null) {
                    this.movementTask = new TargetPositionMovementTask(d, d2);
                    this.movementTask.v(0.075);
                    this.movementTask.g(false);
                    PlayerMovementTaskManager.G.i(this.movementTask);
                }
            } else {
                if (this.sneak.L().booleanValue() && !this.sneaking) {
                    KeyBinding keyBinding = Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
                    keyBinding.setPressed(true);
                    this.sneaking = true;
                }
                this.clearMovementTask();
            }
            int n = 0;
            for (int i = this.placementNodes.size() - 1; i >= 0; --i) {
                BlockPlacementNode blockPlacementNode2 = this.placementNodes.get(i);
                if (!blockPlacementNode2.q.isEmpty() || blockPlacementNode2.d != 0) continue;
                this.computePlacementPaths(blockData, blockPlacementNode2, entityPlayerSP, worldClient);
                if (!blockPlacementNode2.q.isEmpty() || !blockPlacementNode2.w()) continue;
                ++n;
            }
            double d = ForgeVersion.MC_1_7_10.Y() ? (double)entityPlayerSP.X() : 0.0;
            Vec3 vec3 = Vec3.create(entityPlayerSP.z(), entityPlayerSP.A() + d, entityPlayerSP.h());
            this.currentTarget = null;
            int n2 = 0;
            for (int i = this.placementNodes.size() - 1; i >= 0; --i) {
                BlockPlacementNode blockPlacementNode3 = this.placementNodes.get(i);
                if (blockPlacementNode3.q.isEmpty() || this.currentTarget != null) continue;
                ArrayList<BlockPlacementPathSegmentState> arrayList = blockPlacementNode3.q;
                int n3 = 0;
                block2: while (!arrayList.isEmpty()) {
                    object = arrayList.get(0);
                    Vector<PlacementTarget> vector = ((BlockPlacementPathSegmentState)object).M;
                    while (!vector.isEmpty()) {
                        PlacementTarget placementTarget = vector.firstElement();
                        if (placementTarget == null) continue;
                        BlockData blockData2 = placementTarget.s();
                        Block block = worldClient.getBlockByPos(blockData2.D(), blockData2.B(), blockData2.G());
                        if (BlockUtil.u(block)) {
                            if (ClutchPlacementPathUtils.V(worldClient, entityPlayerSP, blockData2)) {
                                Vec3 vec32 = ClutchPlacementPathUtils.Y(entityPlayerSP, worldClient, vec3, placementTarget);
                                if (vec32 != null) {
                                    AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(blockPlacementNode3.h.D(), blockPlacementNode3.h.B(), blockPlacementNode3.h.G(), blockPlacementNode3.h.D() + 1, blockPlacementNode3.h.B() + (blockPlacementNode3.w() ? 2 : 1), blockPlacementNode3.h.G() + 1);
                                    if (ClutchPlacementPathUtils.Q(worldClient, entityPlayerSP, axisAlignedBB = axisAlignedBB.expand(1.0, 1.0, 1.0), vec3, vec32)) {
                                        this.currentTarget = placementTarget;
                                        this.currentTarget.v = vec32;
                                        break block2;
                                    }
                                    if (blockPlacementNode3.d == 0) {
                                        ++n3;
                                    } else {
                                        ++n2;
                                    }
                                    arrayList.remove(0);
                                    break;
                                }
                                if (blockPlacementNode3.d == 0) {
                                    ++n3;
                                } else {
                                    ++n2;
                                }
                                arrayList.remove(0);
                                break;
                            }
                            if (blockPlacementNode3.d == 0) {
                                ++n3;
                            } else {
                                ++n2;
                            }
                            arrayList.remove(0);
                            break;
                        }
                        vector.removeElementAt(0);
                    }
                    if (this.currentTarget != null) break;
                    if (arrayList.isEmpty() || n3 != 0) continue;
                    arrayList.remove(0);
                    blockPlacementNode3.d = -1;
                }
                if (n3 <= 0 || this.currentTarget != null || !blockPlacementNode3.q.isEmpty() || blockPlacementNode3.d != 0) continue;
                this.computePlacementPaths(blockData, blockPlacementNode3, entityPlayerSP, worldClient);
                ++blockPlacementNode3.d;
                ++i;
            }
            if (this.currentTarget != null) {
                boolean bl2;
                BlockData blockData3 = this.currentTarget.s();
                boolean bl3 = bl || !BlockUtil.e(entityPlayerSP, blockData3);
                boolean bl4 = bl2 = bl3 && this.isFacingTarget();
                if (!bl2) {
                    if (this.rotationController == null) {
                        this.rotationController = this.silentAim.L() != false ? new AdaptiveRotationController() : new PointRotationController(this.currentTarget.v);
                    }
                    this.rotationController.t(0.0f);
                    this.rotationController.k(true);
                    this.rotationController.w(true);
                    this.rotationController.s(true);
                    this.rotationController.U(true);
                    this.rotationController.Y(((Double)this.aimSpeed.K()).floatValue());
                    this.rotationController.D(false);
                    if (this.rotationController instanceof PointRotationController) {
                        ((PointRotationController)this.rotationController).E(false);
                    }
                    if (this.aimTimer.hasTimeElapsed((long)this.placeDelay.B() / 2L)) {
                        if (this.rotationController instanceof PointRotationController) {
                            ((PointRotationController)this.rotationController).J(this.currentTarget.v);
                        } else if (this.rotationController instanceof AdaptiveRotationController) {
                            ((AdaptiveRotationController)this.rotationController).J(this.currentTarget.v);
                        }
                    } else {
                        this.rotationController.g(-999.0f, -999.0f);
                    }
                    RotationManager.b.S(this.rotationController);
                } else {
                    if (this.rotationController != null) {
                        if (this.rotationController instanceof PointRotationController) {
                            RotationManager.b.v(this.rotationController);
                        } else {
                            this.rotationController.g(-999.0f, -999.0f);
                        }
                    }
                    if (this.clickTimer.hasTimeElapsed((long)this.placeDelay.B())) {
                        KeyBinding keyBinding = Minecraft.gameSettings().F();
                        if (keyBinding.u() || keyBinding.isPressed()) {
                            KeyBinding.setKeyBindState(keyBinding, false);
                        }
                        object = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                        ((KeyBinding)object).onTick(1);
                        this.clickTimer.reset();
                        this.aimTimer.reset();
                    }
                }
            } else {
                this.reset();
                if (n2 > 0) {
                    Vape.INSTANCE.getNotificationManager().t("Block-In Disabled", "Failed to place " + n2 + " block(s)!", NotificationType.WARNING, 2000L);
                } else if (n > 0) {
                    Vape.INSTANCE.getNotificationManager().t("Block-In Disabled", "No valid path found!", NotificationType.WARNING, 2000L);
                }
            }
        } else {
            boolean bl;
            boolean bl5 = bl = !this.placementNodes.isEmpty();
            String string = this.blockSlot == -1 ? (bl ? "Ran out of blocks!" : "No blocks in hotbar!") : "Could not block in!";
            Vape.INSTANCE.getNotificationManager().t("Block-In Disabled", string, NotificationType.WARNING, 2000L);
            this.reset();
        }
    }

    private void drawFaceQuad(AxisAlignedBB axisAlignedBB, Color color, Color color2, int n) {
        GL11.glBegin((int)7);
        if (n == 0) {
            GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
        } else {
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        }
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        if (n == 1) {
            GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
        } else {
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        }
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        if (n == 2) {
            GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
        } else {
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        }
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        if (n == 5) {
            GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
        } else {
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        }
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        if (n == 3) {
            GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
        } else {
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        }
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        if (n == 4) {
            GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
        } else {
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        }
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glEnd();
    }

    @Override
    public void s(boolean bl, boolean bl2) {
        if (!bl && this.rotationController instanceof AdaptiveRotationController) {
            this.paused = !this.paused;
        } else {
            super.s(bl, bl2);
            this.paused = false;
            if (!bl) {
                this.reset();
            } else {
                this.sneaking = false;
            }
        }
    }

    private int blockPriorityIndex(ItemStack itemStack) {
        int n = Integer.MAX_VALUE;
        if (itemStack.isNull() || itemStack.getItem().isNull()) {
            return n;
        }
        n = this.blockPriorityNames.size();
        for (int i = 0; i < this.blockPriorityNames.size(); ++i) {
            String string = this.blockPriorityNames.get(i);
            if (!itemStack.x().contains(string)) continue;
            n = i;
            break;
        }
        return n;
    }

    private DirectionalPosition findClutchPosition(EntityPlayerSP entityPlayerSP, World world) {
        DirectionalPosition directionalPosition = null;
        double d = Double.MAX_VALUE;
        DirectionalPosition directionalPosition2 = new DirectionalPosition(entityPlayerSP.z(), entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY(), entityPlayerSP.h(), 0);
        Stack<BlockPlacementNode> stack = new Stack<BlockPlacementNode>();
        for (int i = 0; i > -2; --i) {
            BlockCoordinate blockCoordinate = directionalPosition2.F(0, i, 0);
            for (int j = 0; j < 4; ++j) {
                double d2;
                int n = entityPlayerSP.J$src$Lgg_vape_wrapper_impl_EnumFacing_$10aeq5x().c();
                int n2 = (n + j) % 4;
                EnumFacing enumFacing = this.horizontalFacings[n2];
                BlockCoordinate blockCoordinate2 = blockCoordinate.i(enumFacing);
                Block block = world.getBlockByPos(blockCoordinate2.B(), blockCoordinate2.E(), blockCoordinate2.A());
                if (BlockUtil.p(block) && BlockUtil.b(world.getBlockByPos(blockCoordinate2.B(), blockCoordinate2.E() - 1, blockCoordinate2.A()))) {
                    int[] nArray = this.faceOffsetsFor(enumFacing);
                    if (nArray == null) continue;
                    for (int n3 : nArray) {
                        BlockCoordinate blockCoordinate3 = blockCoordinate2.i(EnumFacing.T(n3));
                        Block block2 = world.getBlockByPos(blockCoordinate3.B(), blockCoordinate3.E(), blockCoordinate3.A());
                        if (!BlockUtil.b(block2)) continue;
                        stack.clear();
                        stack.push(new BlockPlacementNode(blockCoordinate2.O(), enumFacing));
                        double d3 = this.computePathCost(world, stack);
                        if (!(d3 < d)) continue;
                        d = d3;
                        directionalPosition = new DirectionalPosition(blockCoordinate2, n3);
                    }
                    continue;
                }
                if (!BlockUtil.b(block) || !((d2 = (double)this.computePathCost(world, stack)) < d)) continue;
                d = d2;
                directionalPosition = new DirectionalPosition(directionalPosition2, enumFacing.Y());
            }
        }
        if (directionalPosition == null) {
            directionalPosition = directionalPosition2;
        }
        return directionalPosition;
    }

    private boolean isFacingTarget() {
        RayTraceResult rayTraceResult;
        boolean bl = false;
        if (this.currentTarget != null && (rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic()).isBlockHit()) {
            boolean bl2;
            if (ForgeVersion.MC_1_7_10.Y()) {
                bl2 = rayTraceResult.getBlockPos().equals(BlockPos.d(this.currentTarget.k));
            } else {
                boolean bl3 = bl2 = rayTraceResult.g() == this.currentTarget.k.D() && rayTraceResult.T() == this.currentTarget.k.B() && rayTraceResult.a$src$I$8nuo9d() == this.currentTarget.k.G();
            }
            if (bl2) {
                boolean bl4;
                EnumFacing enumFacing = this.currentTarget.M ? this.currentTarget.G : null;
                boolean bl5 = bl4 = enumFacing == null;
                if (enumFacing != null && enumFacing.equals(rayTraceResult.getSideHit()) && this.currentTarget.v.distanceTo(rayTraceResult.getHitVec()) <= 0.3) {
                    bl4 = true;
                }
                if (bl4) {
                    bl = true;
                }
            }
        }
        return bl;
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (ForgeVersion.MC_1_17.d()) {
            return;
        }
    }

    private int computePathCost(World world, Vector<BlockPlacementNode> vector) {
        int n = vector.size();
        for (BlockPlacementNode blockPlacementNode : vector) {
            for (EnumFacing enumFacing : this.horizontalFacings) {
                BlockData blockData;
                Block block;
                BlockData blockData2;
                Block block2;
                if (blockPlacementNode.r != null && blockPlacementNode.r.Y() == enumFacing.Y() || blockPlacementNode.P != null && blockPlacementNode.P.Y() == enumFacing.Y()) continue;
                if (blockPlacementNode.w()) {
                    if (!blockPlacementNode.M(enumFacing) && BlockUtil.u(block2 = world.getBlockByPos((blockData2 = blockPlacementNode.F.R(enumFacing)).D(), blockData2.B(), blockData2.G()))) {
                        ++n;
                    }
                    if (!BlockUtil.u(block2 = world.getBlockByPos((blockData2 = blockPlacementNode.F).D(), blockData2.B(), blockData2.G())) && !BlockUtil.f(block2)) {
                        ++n;
                    }
                }
                if (!BlockUtil.u(block2 = world.getBlockByPos((blockData2 = blockPlacementNode.h).D(), blockData2.B(), blockData2.G())) && !BlockUtil.f(block2)) {
                    ++n;
                }
                if (blockPlacementNode.b(enumFacing) || !BlockUtil.u(block = world.getBlockByPos((blockData = blockPlacementNode.h.R(enumFacing)).D(), blockData.B(), blockData.G()))) continue;
                ++n;
            }
        }
        return n;
    }

    private void clearMovementTask() {
        if (this.movementTask != null) {
            this.movementLock.T(this);
            PlayerMovementTaskManager.G.Q(this.movementTask);
            this.movementTask = null;
        }
    }

    private void buildPlacementNodes(EntityPlayerSP entityPlayerSP, World world) {
        if (!this.placementNodes.isEmpty()) {
            return;
        }
        ClutchSolidBlockPathSearchStrategy clutchSolidBlockPathSearchStrategy = new ClutchSolidBlockPathSearchStrategy(this, world);
        if (this.isStandingOnSolid(entityPlayerSP, world)) {
            Object object;
            Stack<BlockPlacementNode> path;
            DirectionalPosition directionalPosition;
            DirectionalPosition directionalPosition2 = this.findClutchPosition(entityPlayerSP, world);
            BlockData blockData = directionalPosition2.O();
            int n = directionalPosition2.X();
            BlockPlacementNode blockPlacementNode = new BlockPlacementNode(blockData, null);
            if (this.bedFinder.L().booleanValue() && n != 0 && (directionalPosition = this.findNearestBed(world, blockData)) != null && (path = this.findBestPath(blockData, (EnumFacing)(object = directionalPosition.L()), world, clutchSolidBlockPathSearchStrategy)) != null && !path.isEmpty()) {
                Collections.reverse(path);
                this.placementNodes.addAll(path);
            }
            if (this.placementNodes.isEmpty()) {
                this.placementNodes.add(blockPlacementNode);
            }
            boolean bl = blockData.B() == MathUtil.floor(entityPlayerSP.N());
            object = new ArrayList();
            for (BlockPlacementNode blockPlacementNode2 : this.placementNodes) {
                if (!blockPlacementNode2.w()) continue;
                ((ArrayList)object).add(new BlockPlacementNode(blockPlacementNode2.F.y(0, 1, 0)));
            }
            if (bl) {
                this.placementNodes.addAll((Collection<BlockPlacementNode>)object);
            } else {
                this.placementNodes.addAll(0, (Collection<BlockPlacementNode>)object);
            }
        }
    }

    private void computePlacementPaths(BlockData blockData, BlockPlacementNode blockPlacementNode, EntityPlayerSP entityPlayerSP, World world) {
        boolean bl;
        Object n2;
        Object object3;
        Object object4;
        if (blockPlacementNode == null) {
            return;
        }
        if (blockPlacementNode.q != null && !blockPlacementNode.q.isEmpty()) {
            return;
        }
        int n = entityPlayerSP.J$src$Lgg_vape_wrapper_impl_EnumFacing_$10aeq5x().c();
        if (blockPlacementNode.r != null && ((EnumFacing)(object4 = blockPlacementNode.r)).c() != -1) {
            n = ((EnumFacing)object4).getOpposite().c();
        }
        object4 = new HashSet();
        HashSet<BlockData> hashSet = new HashSet<BlockData>();
        for (int i = this.placementNodes.size() - 1; i >= 0; --i) {
            object3 = this.placementNodes.get(i);
            if (((BlockPlacementNode)object3).w()) {
                hashSet.add(((BlockPlacementNode)object3).h);
                hashSet.add(((BlockPlacementNode)object3).F);
            }
            for (BlockPlacementPathSegmentState object22 : ((BlockPlacementNode)object3).q) {
                for (PlacementTarget bl2 : object22.M) {
                    if (bl2 == null) continue;
                    n2 = bl2.s();
                    ((HashSet)object4).add(n2);
                }
            }
        }
        BlockData blockData2 = blockPlacementNode.h;
        object3 = blockPlacementNode.F;
        EnumFacing enumFacing = blockPlacementNode.P;
        EnumFacing enumFacing2 = blockPlacementNode.r;
        ClutchBlockPlacementPathSearchStrategy clutchBlockPlacementPathSearchStrategy = new ClutchBlockPlacementPathSearchStrategy(this, hashSet, blockPlacementNode, world, entityPlayerSP, (HashSet)object4);
        boolean bl3 = bl = !blockPlacementNode.w() && (double)blockData2.B() > entityPlayerSP.N();
        if (!bl) {
            int n3;
            for (n3 = 0; n3 < 4; ++n3) {
                Object object;
                int block = (n + n3) % 4;
                EnumFacing bl6 = this.horizontalFacings[block];
                if (enumFacing != null && bl6.Y() == enumFacing.Y() || enumFacing2 != null && bl6.Y() == enumFacing2.Y()) continue;
                BlockData d = blockData2.R(bl6);
                Block block2 = world.getBlockByPos(d.D(), d.B(), d.G());
                boolean vec3 = ((HashSet)object4).contains(d) || blockPlacementNode.b(bl6) || BlockUtil.b(block2);
                boolean vector = true;
                if (blockPlacementNode.w()) {
                    object = ((BlockData)object3).R(bl6);
                    Block d2 = world.getBlockByPos(((BlockData)object).D(), ((BlockData)object).B(), ((BlockData)object).G());
                    boolean bl4 = vector = ((HashSet)object4).contains(object) || blockPlacementNode.M(bl6) || BlockUtil.b(d2);
                }
                if (vec3 && vector) continue;
                object = vec3 ? object3 : blockData2;
                double d3 = ForgeVersion.MC_1_7_10.Y() ? (double)entityPlayerSP.X() : 0.0;
                Vec3 vec32 = Vec3.create((float)blockData.D() + 0.5f, entityPlayerSP.A() + d3, (float)blockData.G() + 0.5f);
                Vector<PlacementTarget> vector2 = ClutchPlacementPathUtils.l(blockData, vec32, entityPlayerSP, world, ((BlockData)object).R(bl6), bl6, bl6, clutchBlockPlacementPathSearchStrategy, 0);
                if (vector2 == null || vector2.isEmpty()) {
                    if (vec3 || !vector && ((vector2 = ClutchPlacementPathUtils.l(blockData, vec32, entityPlayerSP, world, ((BlockData)(object = object3)).R(bl6), bl6, bl6, clutchBlockPlacementPathSearchStrategy, 0)) == null || vector2.isEmpty())) {
                        continue;
                    }
                } else if (!vec3 && !vector) {
                    PlacementTarget placementTarget = new PlacementTarget(blockData2.R(bl6), this.verticalFacings[1]);
                    placementTarget.Y = vector2.size();
                    vector2.add(placementTarget);
                }
                if (vector2 == null || vector2.isEmpty()) continue;
                blockPlacementNode.q.add(new BlockPlacementPathSegmentState(bl6, vector2));
            }
            n3 = entityPlayerSP.J$src$Lgg_vape_wrapper_impl_EnumFacing_$10aeq5x().c();
            int facingIndex = n3;
            blockPlacementNode.q.sort((arg_0, arg_1) -> Clutch.lambda$findPlacePath$0(facingIndex, arg_0, arg_1));
        } else {
            boolean bl5;
            n2 = EnumFacing.F$src$Lgg_vape_wrapper_impl_EnumFacing_$glfxl5();
            Block block = world.getBlockByPos(blockData2.D(), blockData2.B(), blockData2.G());
            boolean bl6 = bl5 = !BlockUtil.u(block);
            if (!bl5) {
                double d = ForgeVersion.MC_1_7_10.Y() ? (double)entityPlayerSP.X() : 0.0;
                Vec3 vec3 = Vec3.create((float)blockData.D() + 0.5f, entityPlayerSP.A() + d, (float)blockData.G() + 0.5f);
                Vector<PlacementTarget> vector = ClutchPlacementPathUtils.l(blockData, vec3, entityPlayerSP, world, blockData2, (EnumFacing)n2, ((EnumFacing)n2).getOpposite(), clutchBlockPlacementPathSearchStrategy, 0);
                if (vector != null && !vector.isEmpty()) {
                    blockPlacementNode.q.add(new BlockPlacementPathSegmentState((EnumFacing)n2, vector));
                }
            }
        }
    }

    private boolean isAllowedBlock(ItemStack itemStack) {
        if (itemStack.isNull() || itemStack.getItem().isNull()) {
            return false;
        }
        Item item = itemStack.getItem();
        if (!item.isInstance(MappedClasses.Vw)) {
            return false;
        }
        return this.useBlacklist.L() == false || this.blockBlacklist.k(itemStack);
    }

    private int selectBestBlockSlot(EntityPlayerSP entityPlayerSP, World world) {
        boolean bl;
        if (entityPlayerSP.isNull() || world.isNull()) {
            return -1;
        }
        int n = -1;
        boolean bl2 = bl = this.blockPriorityMode.K() == this.hardestMode;
        if (bl) {
            float f = -1.0f;
            int n2 = 0;
            for (int i = 0; i < 9; ++i) {
                if (!this.isUsableBlockSlot(entityPlayerSP, world, i)) continue;
                ItemStack itemStack = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
                float f2 = BlockUtil.O(itemStack);
                int n3 = itemStack.t();
                if (!(f2 > f) && (f2 != f || n3 <= n2)) continue;
                f = f2;
                n2 = n3;
                n = i;
            }
        } else {
            int n4 = Integer.MAX_VALUE;
            int n5 = 0;
            for (int i = 0; i < 9; ++i) {
                if (!this.isUsableBlockSlot(entityPlayerSP, world, i)) continue;
                ItemStack itemStack = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
                int n6 = this.blockPriorityIndex(itemStack);
                int n7 = itemStack.t();
                if (n6 >= n4 && (n6 != n4 || n7 <= n5)) continue;
                n4 = n6;
                n5 = n7;
                n = i;
            }
        }
        return n;
    }

    private float computeBreakScore(EntityPlayerSP entityPlayerSP, World world, BlockData blockData, ItemStack itemStack) {
        Block block = world.getBlockByPos(blockData.D(), blockData.B(), blockData.G());
        float f = 1.0f;
        float f2 = this.getDigSpeed(entityPlayerSP, itemStack, blockData);
        return f < 0.0f ? 0.0f : (!this.isRightTool(block, itemStack) ? f2 / f / 100.0f : f2 / f / 30.0f);
    }

    public float getToolStrength(ItemStack itemStack, BlockData blockData) {
        float f = 1.0f;
        if (itemStack.isNotNull()) {
            f *= itemStack.V(blockData.D(), blockData.B(), blockData.G());
        }
        return f;
    }

    public float getDigSpeed(EntityPlayerSP entityPlayerSP, ItemStack itemStack, BlockData blockData) {
        int n;
        float f = this.getToolStrength(itemStack, blockData);
        if (f > 1.0f && (n = EnchantmentHelper.q(Enchantment.efficiency().getId(), itemStack)) > 0 && itemStack.isNotNull()) {
            f += (float)(n * n + 1);
        }
        if (entityPlayerSP.i(PotionRegistry.E)) {
            f *= 1.0f + (float)(entityPlayerSP.b(PotionRegistry.E).L() + 1) * 0.2f;
        }
        if (entityPlayerSP.i(PotionRegistry.u)) {
            float f2 = 1.0f;
            switch (entityPlayerSP.b(PotionRegistry.u).L()) {
                case 0: {
                    f2 = 0.3f;
                    break;
                }
                case 1: {
                    f2 = 0.09f;
                    break;
                }
                case 2: {
                    f2 = 0.0027f;
                    break;
                }
                default: {
                    f2 = 8.1E-4f;
                }
            }
            f *= f2;
        }
        if (!entityPlayerSP.b$src$Z$fqlxe4()) {
            f /= 5.0f;
        }
        return f;
    }

    private void updateRotationController() {
        if (this.rotationController != null) {
            this.rotationController.U(true);
            this.rotationController.D(false);
            this.rotationController.s(true);
            this.rotationController.Y(6.0f);
            RotationManager.b.v(this.rotationController);
        }
        if (RotationManager.b.w() == null || RotationManager.b.w() != this.rotationController || this.rotationController != null && !this.rotationController.v() && this.rotationController.V$src$Z$lb4tvc()) {
            this.rotationController = null;
            this.rotationClaim.X(this);
            if (this.paused) {
                this.paused = false;
                super.s(false, true);
            }
        }
    }

    public boolean isRightTool(Block block, ItemStack itemStack) {
        if (block.H().s()) {
            return true;
        }
        return true;
    }

    private DirectionalPosition findNearestBed(World world, BlockData blockData) {
        DirectionalPosition directionalPosition = null;
        double d = Double.MAX_VALUE;
        int n = blockData.D();
        int n2 = blockData.B();
        int n3 = blockData.G();
        int n4 = 10;
        for (int i = -n4; i < n4; ++i) {
            for (int j = -n4; j < n4; ++j) {
                for (int k = -3; k < 3; ++k) {
                    int n5 = n + i;
                    int n6 = n2 + k;
                    int n7 = n3 + j;
                    double d2 = Math.sqrt(Math.pow(n5 - n, 2.0) + Math.pow(n7 - n3, 2.0));
                    if (!BlockUtil.f(world.getBlockByPos(n5, n6, n7)) || !(d2 < d)) continue;
                    double d3 = Math.toDegrees(Math.atan2(n7 - n3, n5 - n)) - 90.0;
                    EnumFacing enumFacing = EnumFacing.p(d3);
                    d = d2;
                    directionalPosition = new DirectionalPosition(n5, n6, n7, enumFacing.Y());
                }
            }
        }
        return directionalPosition;
    }

    public static int T(Clutch clutch, World world, Vector vector) {
        return clutch.computePathCost(world, vector);
    }

    private boolean isStandingOnSolid(EntityPlayerSP entityPlayerSP, World world) {
        int n = MathUtil.floor(entityPlayerSP.z());
        int n2 = MathUtil.floor(entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY() - 1.0);
        int n3 = MathUtil.floor(entityPlayerSP.h());
        Block block = world.getBlockByPos(n, n2, n3);
        return BlockUtil.b(block);
    }

    private void drawOutline(AxisAlignedBB axisAlignedBB) {
        GL11.glBegin((int)1);
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glEnd();
    }
}
