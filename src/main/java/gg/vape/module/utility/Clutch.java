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
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    private final BooleanValue s;
    private final ArrayList<String> j;
    private final MovementInputLock k;
    private TargetPositionMovementTask O;
    private final ModeOption Z = new ModeOption("Lowest cost");
    private final NumberValue r;
    private final ArrayList<BlockPlacementNode> o;
    private final BooleanValue C;
    private FixedRotationController I;
    private final RotationControlClaim t;
    private final int[][] V;
    private int b = -1;
    private final ModeOption H = new ModeOption("Hardest");
    private final BooleanValue S;
    private final RandomValue F;
    private PlacementTarget A;
    private final TimerUtil K;
    private final EnumFacing[] p = EnumFacing.c$src$ALgg_vape_wrapper_impl_EnumFacing_$1i3g4ft();
    private final BooleanValue P;
    private final BooleanValue D;
    private final TimerUtil L;
    private boolean EV;
    private final ModeValue U;
    private boolean Y;
    private final BooleanValue a;
    private final EnumFacing[] c = EnumFacing.t();
    private final LimitValue J;
    private int v = -1;

    private int m(World world, Vector<PlacementTarget> vector) {
        int n = vector.size();
        return n;
    }

    private static int lambda$findPlacePath$0(int n, BlockPlacementPathSegmentState blockPlacementPathSegmentState, BlockPlacementPathSegmentState blockPlacementPathSegmentState2) {
        int n2 = Math.abs(n - blockPlacementPathSegmentState.W.Y());
        int n3 = Math.abs(n - blockPlacementPathSegmentState2.W.Y());
        return Integer.compare(n3, n2);
    }

    public static int F(Clutch clutch, World world, Vector vector) {
        return clutch.m(world, vector);
    }

    private void S(EntityPlayerSP entityPlayerSP, World world, BlockData blockData) {
        int n = Integer.MAX_VALUE;
        InventoryPlayer inventoryPlayer = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        for (int i = 0; i < 9; ++i) {
            int n2;
            ItemStack itemStack = inventoryPlayer.c(i);
            float f = this.z(entityPlayerSP, world, blockData, itemStack);
            if (f == 0.0f || (n2 = (int)(Math.floor(1.0 / (double)f) + 1.0)) >= n) continue;
            n = n2;
        }
    }

    @Override
    public boolean X() {
        return true;
    }

    private boolean Y(EntityPlayerSP entityPlayerSP, World world, int n) {
        if (entityPlayerSP.isNull() || world.isNull()) {
            return false;
        }
        ItemStack itemStack = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(n);
        if (itemStack.isNull() || itemStack.getItem().isNull() || itemStack.t() <= 0) {
            return false;
        }
        return this.O(itemStack);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Clutch() {
        super("Block-In", 8191953, Category.m, "Automatically blocks you in by building walls around you");
        this.V = new int[][]{null, null, {5, 4}, {5, 4}, {2, 3}, {2, 3}};
        this.P = BooleanValue.create(this, "Silent aim", false, "Uses Silent Aim system");
        this.D = BooleanValue.create(this, "Sneak", false, "Sneak when placing blocks");
        this.s = BooleanValue.create(this, "Keep sneak", true, "Keeps sneak held after placing blocks.\nMust press sneak again to release");
        this.a = BooleanValue.create(this, "Bed finder", true, "Finds nearby beds to block in towards");
        this.U = ModeValue.create((Object)this, "Block priority", "Lowest cost - Prioritizes by block type: Wool > Stone > Wood > Sandstone > Stained Clay > End Stone > Obsidian\nHardest - uses the block hardness value.", (ModeSelection)this.Z, this.Z, this.H);
        this.F = RandomValue.G(this, "Place delay", "#", "ms", 0.0, 0.0, 30.0, 250.0, 1.0, "Delay between each block placement");
        this.r = NumberValue.create(this, "Aim speed", "#.#", "", 1.0, 12.0, 25.0, 0.1, "Speed of aim when placing blocks");
        this.C = BooleanValue.create(this, "Return to last slot", true, "Returns hotbar to previous slot when completed");
        this.S = BooleanValue.create(this, "Use blacklist", true, "BlockIn will not use these blocks");
        this.J = LimitValue.n(this, "blockin-blacklist", "Block blacklist", LimitValue.G, ItemLimitData.P);
        this.j = new ArrayList<String>(Arrays.asList("Wool", "Stone", "Wood Planks", "Red Sandstone", "Stained Clay", "End Stone", "Obsidian"));
        this.t = SharedModuleControlClaims.I;
        this.k = SharedModuleControlClaims.l;
        this.K = new TimerUtil();
        this.L = new TimerUtil();
        this.o = new ArrayList();
        this.S.K(this.J);
        this.D.K(this.s);
        this.addValue(this.r, this.F, this.U, this.P, this.D, this.s, this.a, this.C, this.S, this.J);
        this.t.l(this, 9);
    }

    @EventHandler
    public void O(EventKeyPress eventKeyPress) {
        if (!this.D.L().booleanValue()) {
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
        if (this.Y && !this.s.L().booleanValue()) {
            this.Y = false;
            KeyBinding keyBinding = Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
            keyBinding.Z();
        }
    }

    private Stack<BlockPlacementNode> U(BlockData blockData, EnumFacing enumFacing, World world, BlockPathSearchStrategy blockPathSearchStrategy) {
        Object object;
        int n;
        Stack<BlockPlacementNode> stack = null;
        int n2 = Integer.MAX_VALUE;
        for (n = 0; n < 4; ++n) {
            Stack<BlockPlacementNode> stack2;
            int n3 = enumFacing.c();
            int n4 = (n3 + n) % 4;
            object = this.p[n4];
            if (((Wrapper)object).equals(enumFacing.getOpposite()) || (stack2 = ClutchPlacementPathUtils.o(blockData, null, enumFacing, blockPathSearchStrategy, 0)) == null || stack2.isEmpty()) continue;
            int n5 = this.p(world, stack2);
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

    private int[] x(EnumFacing enumFacing) {
        return this.V[enumFacing.Y()];
    }

    private void e() {
        if (this.C.L().booleanValue() && this.b != -1) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            if (entityPlayerSP.isNotNull() && this.b != entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v()) {
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.b);
            }
            this.b = -1;
        }
        this.A = null;
        this.v = -1;
        this.o.removeIf(BlockPlacementNode::D);
        this.b$src$V$u3rbwt();
        this.V$src$V$tx5ssh();
        if (this.r$src$Z$14eylz9() && !this.EV) {
            super.Y(false);
        }
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        WorldClient worldClient = eventPreTick.getWorld();
        if (entityPlayerSP.isNull() || worldClient.isNull()) {
            this.e();
            return;
        }
        if (this.EV || this.t.e(this)) {
            this.e();
            return;
        }
        if (!this.t.U(this) && !this.t.h(this, this.P.L())) {
            return;
        }
        if (this.v == -1 || !this.Y(entityPlayerSP, worldClient, this.v)) {
            this.v = this.B(entityPlayerSP, worldClient);
        }
        if (this.v != -1 && !this.EV && this.o.isEmpty()) {
            this.v(entityPlayerSP, worldClient);
        }
        if (this.v != -1 && !this.o.isEmpty() && !this.EV) {
            Object object;
            BlockPlacementNode blockPlacementNode = this.o.get(0);
            BlockData blockData = blockPlacementNode.h;
            if (!ClutchPlacementPathUtils.V(worldClient, entityPlayerSP, blockData)) {
                this.e();
                Vape.INSTANCE.getNotificationManager().t("Block-In Disabled", "Entity in the way!", NotificationType.WARNING, 2000L);
                return;
            }
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.v);
            boolean bl = BlockUtil.e(entityPlayerSP, blockData);
            if (!bl) {
                double d = (double)blockData.D() + 0.5;
                double d2 = (double)blockData.G() + 0.5;
                double d3 = entityPlayerSP.i(d, entityPlayerSP.N(), d2);
                if (d3 > 1.75) {
                    this.e();
                    return;
                }
                this.k.K(this);
                if (this.O == null) {
                    this.O = new TargetPositionMovementTask(d, d2);
                    this.O.v(0.075);
                    this.O.g(false);
                    PlayerMovementTaskManager.G.i(this.O);
                }
            } else {
                if (this.D.L().booleanValue() && !this.Y) {
                    KeyBinding keyBinding = Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0();
                    keyBinding.setPressed(true);
                    this.Y = true;
                }
                this.b$src$V$u3rbwt();
            }
            int n = 0;
            for (int i = this.o.size() - 1; i >= 0; --i) {
                BlockPlacementNode blockPlacementNode2 = this.o.get(i);
                if (!blockPlacementNode2.q.isEmpty() || blockPlacementNode2.d != 0) continue;
                this.C(blockData, blockPlacementNode2, entityPlayerSP, worldClient);
                if (!blockPlacementNode2.q.isEmpty() || !blockPlacementNode2.w()) continue;
                ++n;
            }
            double d = ForgeVersion.MC_1_7_10.Y() ? (double)entityPlayerSP.X() : 0.0;
            Vec3 vec3 = Vec3.create(entityPlayerSP.z(), entityPlayerSP.A() + d, entityPlayerSP.h());
            this.A = null;
            int n2 = 0;
            for (int i = this.o.size() - 1; i >= 0; --i) {
                BlockPlacementNode blockPlacementNode3 = this.o.get(i);
                if (blockPlacementNode3.q.isEmpty() || this.A != null) continue;
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
                                        this.A = placementTarget;
                                        this.A.v = vec32;
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
                    if (this.A != null) break;
                    if (arrayList.isEmpty() || n3 != 0) continue;
                    arrayList.remove(0);
                    blockPlacementNode3.d = -1;
                }
                if (n3 <= 0 || this.A != null || !blockPlacementNode3.q.isEmpty() || blockPlacementNode3.d != 0) continue;
                this.C(blockData, blockPlacementNode3, entityPlayerSP, worldClient);
                ++blockPlacementNode3.d;
                ++i;
            }
            if (this.A != null) {
                boolean bl2;
                BlockData blockData3 = this.A.s();
                boolean bl3 = bl || !BlockUtil.e(entityPlayerSP, blockData3);
                boolean bl4 = bl2 = bl3 && this.B$src$Z$tm5x0p();
                if (!bl2) {
                    if (this.I == null) {
                        this.I = this.P.L() != false ? new AdaptiveRotationController() : new PointRotationController(this.A.v);
                    }
                    this.I.t(0.0f);
                    this.I.k(true);
                    this.I.w(true);
                    this.I.s(true);
                    this.I.U(true);
                    this.I.Y(((Double)this.r.K()).floatValue());
                    this.I.D(false);
                    if (this.I instanceof PointRotationController) {
                        ((PointRotationController)this.I).E(false);
                    }
                    if (this.L.hasTimeElapsed((long)this.F.B() / 2L)) {
                        if (this.I instanceof PointRotationController) {
                            ((PointRotationController)this.I).J(this.A.v);
                        } else if (this.I instanceof AdaptiveRotationController) {
                            ((AdaptiveRotationController)this.I).J(this.A.v);
                        }
                    } else {
                        this.I.g(-999.0f, -999.0f);
                    }
                    RotationManager.b.S(this.I);
                } else {
                    if (this.I != null) {
                        if (this.I instanceof PointRotationController) {
                            RotationManager.b.v(this.I);
                        } else {
                            this.I.g(-999.0f, -999.0f);
                        }
                    }
                    if (this.K.hasTimeElapsed((long)this.F.B())) {
                        KeyBinding keyBinding = Minecraft.gameSettings().F();
                        if (keyBinding.u() || keyBinding.isPressed()) {
                            KeyBinding.setKeyBindState(keyBinding, false);
                        }
                        object = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                        ((KeyBinding)object).onTick(1);
                        this.K.reset();
                        this.L.reset();
                    }
                }
            } else {
                this.e();
                if (n2 > 0) {
                    Vape.INSTANCE.getNotificationManager().t("Block-In Disabled", "Failed to place " + n2 + " block(s)!", NotificationType.WARNING, 2000L);
                } else if (n > 0) {
                    Vape.INSTANCE.getNotificationManager().t("Block-In Disabled", "No valid path found!", NotificationType.WARNING, 2000L);
                }
            }
        } else {
            boolean bl;
            boolean bl5 = bl = !this.o.isEmpty();
            String string = this.v == -1 ? (bl ? "Ran out of blocks!" : "No blocks in hotbar!") : "Could not block in!";
            Vape.INSTANCE.getNotificationManager().t("Block-In Disabled", string, NotificationType.WARNING, 2000L);
            this.e();
        }
    }

    private void o(AxisAlignedBB axisAlignedBB, Color color, Color color2, int n) {
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
        if (!bl && this.I instanceof AdaptiveRotationController) {
            this.EV = !this.EV;
        } else {
            super.s(bl, bl2);
            this.EV = false;
            if (!bl) {
                this.e();
            } else {
                this.Y = false;
            }
        }
    }

    private int t(ItemStack itemStack) {
        int n = Integer.MAX_VALUE;
        if (itemStack.isNull() || itemStack.getItem().isNull()) {
            return n;
        }
        n = this.j.size();
        for (int i = 0; i < this.j.size(); ++i) {
            String string = this.j.get(i);
            if (!itemStack.x().contains(string)) continue;
            n = i;
            break;
        }
        return n;
    }

    private DirectionalPosition a(EntityPlayerSP entityPlayerSP, World world) {
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
                EnumFacing enumFacing = this.p[n2];
                BlockCoordinate blockCoordinate2 = blockCoordinate.i(enumFacing);
                Block block = world.getBlockByPos(blockCoordinate2.B(), blockCoordinate2.E(), blockCoordinate2.A());
                if (BlockUtil.p(block) && BlockUtil.b(world.getBlockByPos(blockCoordinate2.B(), blockCoordinate2.E() - 1, blockCoordinate2.A()))) {
                    int[] nArray = this.x(enumFacing);
                    if (nArray == null) continue;
                    for (int n3 : nArray) {
                        BlockCoordinate blockCoordinate3 = blockCoordinate2.i(EnumFacing.T(n3));
                        Block block2 = world.getBlockByPos(blockCoordinate3.B(), blockCoordinate3.E(), blockCoordinate3.A());
                        if (!BlockUtil.b(block2)) continue;
                        stack.clear();
                        stack.push(new BlockPlacementNode(blockCoordinate2.O(), enumFacing));
                        double d3 = this.p(world, stack);
                        if (!(d3 < d)) continue;
                        d = d3;
                        directionalPosition = new DirectionalPosition(blockCoordinate2, n3);
                    }
                    continue;
                }
                if (!BlockUtil.b(block) || !((d2 = (double)this.p(world, stack)) < d)) continue;
                d = d2;
                directionalPosition = new DirectionalPosition(directionalPosition2, enumFacing.Y());
            }
        }
        if (directionalPosition == null) {
            directionalPosition = directionalPosition2;
        }
        return directionalPosition;
    }

    private boolean B$src$Z$tm5x0p() {
        RayTraceResult rayTraceResult;
        boolean bl = false;
        if (this.A != null && (rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic()).isBlockHit()) {
            boolean bl2;
            if (ForgeVersion.MC_1_7_10.Y()) {
                bl2 = rayTraceResult.getBlockPos().equals(BlockPos.d(this.A.k));
            } else {
                boolean bl3 = bl2 = rayTraceResult.g() == this.A.k.D() && rayTraceResult.T() == this.A.k.B() && rayTraceResult.a$src$I$8nuo9d() == this.A.k.G();
            }
            if (bl2) {
                boolean bl4;
                EnumFacing enumFacing = this.A.M ? this.A.G : null;
                boolean bl5 = bl4 = enumFacing == null;
                if (enumFacing != null && enumFacing.equals(rayTraceResult.getSideHit()) && this.A.v.distanceTo(rayTraceResult.getHitVec()) <= 0.3) {
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

    private int p(World world, Vector<BlockPlacementNode> vector) {
        int n = vector.size();
        for (BlockPlacementNode blockPlacementNode : vector) {
            for (EnumFacing enumFacing : this.p) {
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

    private void b$src$V$u3rbwt() {
        if (this.O != null) {
            this.k.T(this);
            PlayerMovementTaskManager.G.Q(this.O);
            this.O = null;
        }
    }

    private void v(EntityPlayerSP entityPlayerSP, World world) {
        if (!this.o.isEmpty()) {
            return;
        }
        ClutchSolidBlockPathSearchStrategy clutchSolidBlockPathSearchStrategy = new ClutchSolidBlockPathSearchStrategy(this, world);
        if (this.q(entityPlayerSP, world)) {
            Object object;
            Stack<BlockPlacementNode> path;
            DirectionalPosition directionalPosition;
            DirectionalPosition directionalPosition2 = this.a(entityPlayerSP, world);
            BlockData blockData = directionalPosition2.O();
            int n = directionalPosition2.X();
            BlockPlacementNode blockPlacementNode = new BlockPlacementNode(blockData, null);
            if (this.a.L().booleanValue() && n != 0 && (directionalPosition = this.e(world, blockData)) != null && (path = this.U(blockData, (EnumFacing)(object = directionalPosition.L()), world, clutchSolidBlockPathSearchStrategy)) != null && !path.isEmpty()) {
                Collections.reverse(path);
                this.o.addAll(path);
            }
            if (this.o.isEmpty()) {
                this.o.add(blockPlacementNode);
            }
            boolean bl = blockData.B() == MathUtil.floor(entityPlayerSP.N());
            object = new ArrayList();
            for (BlockPlacementNode blockPlacementNode2 : this.o) {
                if (!blockPlacementNode2.w()) continue;
                ((ArrayList)object).add(new BlockPlacementNode(blockPlacementNode2.F.y(0, 1, 0)));
            }
            if (bl) {
                this.o.addAll((Collection<BlockPlacementNode>)object);
            } else {
                this.o.addAll(0, (Collection<BlockPlacementNode>)object);
            }
        }
    }

    private void C(BlockData blockData, BlockPlacementNode blockPlacementNode, EntityPlayerSP entityPlayerSP, World world) {
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
        for (int i = this.o.size() - 1; i >= 0; --i) {
            object3 = this.o.get(i);
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
                EnumFacing bl6 = this.p[block];
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
                    PlacementTarget placementTarget = new PlacementTarget(blockData2.R(bl6), this.c[1]);
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

    private boolean O(ItemStack itemStack) {
        if (itemStack.isNull() || itemStack.getItem().isNull()) {
            return false;
        }
        Item item = itemStack.getItem();
        if (!item.isInstance(MappedClasses.Vw)) {
            return false;
        }
        return this.S.L() == false || this.J.k(itemStack);
    }

    private int B(EntityPlayerSP entityPlayerSP, World world) {
        boolean bl;
        if (entityPlayerSP.isNull() || world.isNull()) {
            return -1;
        }
        int n = -1;
        boolean bl2 = bl = this.U.K() == this.H;
        if (bl) {
            float f = -1.0f;
            int n2 = 0;
            for (int i = 0; i < 9; ++i) {
                if (!this.Y(entityPlayerSP, world, i)) continue;
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
                if (!this.Y(entityPlayerSP, world, i)) continue;
                ItemStack itemStack = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
                int n6 = this.t(itemStack);
                int n7 = itemStack.t();
                if (n6 >= n4 && (n6 != n4 || n7 <= n5)) continue;
                n4 = n6;
                n5 = n7;
                n = i;
            }
        }
        return n;
    }

    private float z(EntityPlayerSP entityPlayerSP, World world, BlockData blockData, ItemStack itemStack) {
        Block block = world.getBlockByPos(blockData.D(), blockData.B(), blockData.G());
        float f = 1.0f;
        float f2 = this.N(entityPlayerSP, itemStack, blockData);
        return f < 0.0f ? 0.0f : (!this.p(block, itemStack) ? f2 / f / 100.0f : f2 / f / 30.0f);
    }

    public float o(ItemStack itemStack, BlockData blockData) {
        float f = 1.0f;
        if (itemStack.isNotNull()) {
            f *= itemStack.V(blockData.D(), blockData.B(), blockData.G());
        }
        return f;
    }

    public float N(EntityPlayerSP entityPlayerSP, ItemStack itemStack, BlockData blockData) {
        int n;
        float f = this.o(itemStack, blockData);
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

    private void V$src$V$tx5ssh() {
        if (this.I != null) {
            this.I.U(true);
            this.I.D(false);
            this.I.s(true);
            this.I.Y(6.0f);
            RotationManager.b.v(this.I);
        }
        if (RotationManager.b.w() == null || RotationManager.b.w() != this.I || this.I != null && !this.I.v() && this.I.V$src$Z$lb4tvc()) {
            this.I = null;
            this.t.X(this);
            if (this.EV) {
                this.EV = false;
                super.s(false, true);
            }
        }
    }

    public boolean p(Block block, ItemStack itemStack) {
        if (block.H().s()) {
            return true;
        }
        return true;
    }

    private DirectionalPosition e(World world, BlockData blockData) {
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
        return clutch.p(world, vector);
    }

    private boolean q(EntityPlayerSP entityPlayerSP, World world) {
        int n = MathUtil.floor(entityPlayerSP.z());
        int n2 = MathUtil.floor(entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY() - 1.0);
        int n3 = MathUtil.floor(entityPlayerSP.h());
        Block block = world.getBlockByPos(n, n2, n3);
        return BlockUtil.b(block);
    }

    private void H(AxisAlignedBB axisAlignedBB) {
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
