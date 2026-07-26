package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventClickMouse;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreLocalPlayerTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender2D;
import gg.vape.event.impl.EventRender3D;
import gg.vape.input.KeyboardCodeUtil;
import gg.vape.input.MovementInputLock;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.ModDisplayInfo;
import gg.vape.module.VisibleModuleList;
import gg.vape.module.blatant.blockin.BlockInBooleanState;
import gg.vape.module.blatant.blockin.BlockInPlacementSearchStrategy;
import gg.vape.module.blatant.blockin.BlockInSearchPlanner;
import gg.vape.module.blatant.blockin.BlockInTargetRotationState;
import gg.vape.module.blatant.blockin.BlockInThresholdRotationController;
import gg.vape.module.blatant.blockin.BlockPathPlanner;
import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.module.blatant.blockin.BlockPlacementPathSegment;
import gg.vape.module.blatant.blockin.BlockPlacementPathSegmentState;
import gg.vape.module.blatant.blockin.EntityFixedRotationController;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.FreeLookHudModule;
import gg.vape.module.utility.clutch.ClutchPlacementPathUtils;
import gg.vape.module.utility.clutch.PlacementTarget;
import gg.vape.movement.MovementInputHelper;
import gg.vape.notification.Notification;
import gg.vape.notification.NotificationType;
import gg.vape.notification.TextNotificationContent;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.ui.click.frame.impl.hud.ActiveModuleStackFrame;
import gg.vape.ui.theme.ThemeColors;
import gg.vape.unmap.ItemLimitData;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.PlayerSimulationUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.RotationVectorMath;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.Vec3d;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.utils.datas.BlockData;
import gg.vape.utils.datas.DirectionalPosition;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.PacketVelocityBridge;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.SPacketBlockChange;
import gg.vape.wrapper.impl.SPacketEntityVelocity;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.Vector;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class BlockIn
extends Mod {
    private float OI;
    private BlockPlacementGraph Ow;
    private final LimitValue Og;
    private final VisibleModuleList<BlockData> O9;
    private final BooleanValue Oj;
    private DirectionalPosition OK;
    private final RandomValue s;
    private final BooleanValue OT;
    private boolean v;
    private TimerUtil OF;
    private final RandomValue OW;
    private boolean OX;
    private final BooleanValue Ox;
    private BlockPlacementGraph A;
    private boolean b = false;
    private boolean OY;
    private Notification H;
    private Object Of;
    private ArrayList<String> Z;
    private final BooleanValue OV;
    private FixedRotationController U;
    private Boolean Os;
    private final RandomValue Or;
    private double OR = -999.0;
    private final NumberValue Oi;
    private boolean K;
    private List<BlockPlacementGraph> Ob;
    private double OH = -999.0;
    private int r;
    private TimerUtil OD;
    private Boolean O3;
    private boolean F;
    private int Op;
    private final NumberValue On;
    private final ArrayList<BlockPlacementPathSegment> Ou;
    private boolean OJ;
    private int O7 = 0;
    private HashSet<BlockData> O5;
    private final MovementInputLock OG;
    private BlockPathPlanner L;
    private boolean O2;
    private final BooleanValue OQ;
    private final BooleanValue S;
    private boolean OP;
    private int OA = 0;
    private int OS;
    private Boolean O_;
    private TimerUtil OB;
    private HashSet<BlockData> o;
    private final ArrayList<BlockPlacementPathSegment> p;
    private float O4;
    private boolean Oe;
    private final BooleanValue OE;
    private int Oh;
    boolean O0;
    private boolean Oy;
    private PlacementTarget t;
    private BlockPathPlanner OO;
    private int c;
    private final LimitValue P;
    private RayTraceResult C;
    public static final ArrayList<Vector<PlacementTarget>> O = new ArrayList();
    private double Oa = -999.0;
    private String ON;
    private boolean V;
    private static final boolean Ol = false;
    private boolean OL;
    private boolean Y;
    private Boolean I;
    private final BooleanValue Od;
    private final NumberValue O6;
    private final RotationControlClaim a;
    private final BooleanValue D = BooleanValue.create(this, "On void", true, "Catches the player if they are about to fall into the void");
    private boolean Ok = false;
    private BlockPlacementPathSegment J;
    private final NumberValue Oo;
    private int Oq;
    private final HashMap<BlockData, HashSet<BlockData>> k;
    private int j = -1;
    private final BooleanValue Om;
    private final BooleanValue Oz;

    private void n$src$V$tzbhlc() {
        this.ON = null;
        this.r = 0;
    }

    private void V$src$V$tm4fco() {
        if (Minecraft.currentScreen().getObject() == null) {
            KeyboardCodeUtil.v();
        }
        MovementInputHelper.D(false);
        this.K = false;
        this.v = false;
        this.Oy = false;
        this.OP = false;
    }

    private boolean z() {
        return this.J != null && this.J.g != null && !this.J.g.M.isEmpty();
    }

    private int f(BlockPlacementPathSegment blockPlacementPathSegment, PlacementTarget placementTarget, EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP, World world, float f, float f2, BlockPlacementGraph blockPlacementGraph) {
        Object object;
        Object object2;
        BlockPathPlanner blockPathPlanner = new BlockPathPlanner(entityPlayer, entityPlayerSP, world, blockPlacementGraph);
        if (this.OL) {
            blockPathPlanner.t();
        } else {
            blockPathPlanner.l();
        }
        EntityPlayer entityPlayer2 = blockPathPlanner.T();
        entityPlayer2.H(f);
        entityPlayer2.z(f);
        entityPlayer2.C(f2);
        double d = -0.0784000015258789;
        if (this.V && !this.OJ && entityPlayer.b$src$Z$fqlxe4() && entityPlayer.q() == d) {
            blockPathPlanner.h();
        }
        double d2 = ForgeVersion.MC_1_7_10.Y() ? (double)entityPlayerSP.X() : 0.0;
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        int n = 0;
        while (n < 6) {
            boolean bl4;
            boolean bl5;
            boolean bl6;
            boolean bl7;
            ++n;
            Vec3d vec3d = blockPathPlanner.f();
            Vec3 vec3 = Vec3.create(vec3d.Y(), vec3d.t() + d2, vec3d.o());
            if (ClutchPlacementPathUtils.P(vec3, world, placementTarget.k, placementTarget.G)) {
                double d3;
                bl2 = true;
                Vec3 placementHit = ClutchPlacementPathUtils.n(entityPlayerSP, world, vec3, placementTarget, f, f2);
                if (placementHit != null && (d3 = vec3.distanceTo(placementHit)) <= 5.0) {
                    bl3 = true;
                    placementTarget.v = placementHit;
                    object2 = blockPathPlanner.f();
                    blockPlacementPathSegment.V = Vec3.create(((Vec3d)object2).Y(), ((Vec3d)object2).t() + d2, ((Vec3d)object2).o());
                    break;
                }
            }
            double d4 = (double)placementTarget.k.D() + 0.5;
            double d5 = (double)placementTarget.k.G() + 0.5;
            float f3 = (float)RotationUtil.N(vec3.getX(), vec3.getZ(), f, d4, d5);
            float f4 = (float)Math.abs(MathUtil.wrapAngleTo180((double)f3));
            bl = f4 > 110.0f;
            if (vec3d.t() <= (double)blockPlacementPathSegment.R.E()) break;
            object = blockPathPlanner.E().U();
            BlockInBooleanState blockInBooleanState = null;
            if (!this.OT.L().booleanValue() && (blockInBooleanState = this.j(entityPlayer2, this.OI, bl7 = this.A.M, bl6 = this.A.D, bl5 = this.A.R, bl4 = this.A.Y)) != null) {
                blockPathPlanner.G(blockInBooleanState.L, blockInBooleanState.v, blockInBooleanState.h, blockInBooleanState.c);
            }
            blockPathPlanner.B();
            if (blockInBooleanState == null) continue;
            blockPathPlanner.G(blockInBooleanState.b, blockInBooleanState.f, blockInBooleanState.q, blockInBooleanState.d);
        }
        Vec3d finalPosition = blockPathPlanner.f();
        if (bl3) {
            blockPlacementPathSegment.V = Vec3.create(finalPosition.Y(), finalPosition.t() + d2, finalPosition.o());
            boolean bl8 = placementTarget.k.L(blockPlacementPathSegment.t.O());
            if (bl8 && entityPlayer.b$src$Z$fqlxe4()) {
                return bl ? 2 : 1;
            }
            return n;
        }
        BlockData blockData = placementTarget.k;
        EnumFacing enumFacing = placementTarget.G;
        BlockData blockData2 = placementTarget.s();
        object2 = blockData;
        Vec3 vec3 = Vec3.create((double)blockData2.D() + 0.5, entityPlayer.N() + d2, (double)blockData2.G() + 0.5);
        Vec3 finalHit = ClutchPlacementPathUtils.n(entityPlayerSP, world, vec3, placementTarget, f, f2);
        blockPlacementPathSegment.V = vec3;
        placementTarget.v = finalHit == null ? RotationUtil.M(vec3, BlockUtil.F(world, blockData), 0.0, 0.0, 0.0).n() : finalHit;
        return bl ? 2 : 2;
    }

    private void x(String string) {
        this.ON = string;
        if (this.r == 0) {
            this.r = Math.min(3, Math.max(1, ((Double)this.Oo.K()).intValue() / 50));
        }
    }

    private boolean y(ItemStack itemStack) {
        if (itemStack.isNull() || itemStack.getItem().isNull()) {
            return false;
        }
        Item item = itemStack.getItem();
        if (!item.isInstance(MappedClasses.Vw)) {
            return false;
        }
        return this.OE.L() == false || this.Og.k(itemStack);
    }

    private void G() {
        if (this.r > 0) {
            --this.r;
            if (this.r == 0 && this.ON != null) {
                this.Z(this.ON, false);
                this.ON = null;
            }
        }
    }

    private BlockCoordinate B(int n, EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP, ArrayList<Vec3d> arrayList) {
        boolean bl;
        boolean bl2;
        int n2 = ForgeVersion.MC_1_20_6.d() ? entityPlayerSP.getWorld().R() : 0;
        BlockPathPlanner blockPathPlanner = new BlockPathPlanner(entityPlayer, entityPlayerSP, entityPlayerSP.getWorld(), this.A);
        blockPathPlanner.U(this.A);
        blockPathPlanner.l();
        if (arrayList != null) {
            arrayList.add(blockPathPlanner.f());
        }
        boolean bl3 = this.A.M || this.A.D || this.A.R || this.A.Y;
        boolean bl4 = bl2 = this.D.L() == false;
        if (bl2) {
            int n3;
            int n4;
            int n5;
            boolean bl5;
            boolean bl6 = bl5 = this.Om.L() == false;
            if (bl5) {
                int n6;
                int n7;
                int n8;
                int n9 = 1;
                EntityPlayer entityPlayer2 = blockPathPlanner.T();
                int n10 = 0;
                BlockCoordinate blockCoordinate = null;
                Vec3 vec3 = Vec3.create(entityPlayer2.z(), entityPlayer2.N(), entityPlayer2.h());
                for (n8 = 0; n8 <= n; ++n8) {
                    boolean onGroundBeforeStep = entityPlayer2.b$src$Z$fqlxe4();
                    blockPathPlanner.B();
                    if (arrayList != null) {
                        arrayList.add(blockPathPlanner.f());
                    }
                    vec3.N(entityPlayer2.z());
                    vec3.m(entityPlayer2.N());
                    vec3.Z(entityPlayer2.h());
                    n6 = entityPlayer2.b$src$Z$fqlxe4() ? 1 : 0;
                    int n11 = MathUtil.floor(entityPlayer2.z());
                    int n12 = MathUtil.floor(entityPlayer2.N() - 0.015625);
                    int n13 = MathUtil.floor(entityPlayer2.h());
                    boolean bl7 = false;
                    Block block = entityPlayerSP.getWorld().getBlockByPos(n11, n12, n13);
                    if (block.isNotNull() && (block.isInstance(MappedClasses.q_) || block.isInstance(MappedClasses.b))) {
                        bl7 = true;
                    }
                    if (n6 != 0 || bl7) {
                        blockCoordinate = new BlockCoordinate(n11, n12, n13);
                        if (++n10 >= n9 || !entityPlayer2.e$src$Z$15bd4i1()) {
                            // empty if block
                        }
                        return blockCoordinate;
                    }
                    blockCoordinate = null;
                    n10 = 0;
                    if (!(blockPathPlanner.T().N() <= (double)n2)) continue;
                    return blockCoordinate;
                }
                n8 = MathUtil.floor(entityPlayer2.z());
                n7 = MathUtil.floor(entityPlayer2.N()) - 1;
                n6 = MathUtil.floor(entityPlayer2.h());
                return new BlockCoordinate(n8, n7, n6);
            }
            int n14 = 3;
            EntityPlayer entityPlayer3 = blockPathPlanner.T();
            int n15 = 0;
            BlockCoordinate blockCoordinate = null;
            Vec3 vec3 = Vec3.create(entityPlayer3.z(), entityPlayer3.N(), entityPlayer3.h());
            for (n5 = 0; n5 <= n; ++n5) {
                boolean onGroundBeforeStep = entityPlayer3.b$src$Z$fqlxe4();
                blockPathPlanner.B();
                if (arrayList != null) {
                    arrayList.add(blockPathPlanner.f());
                }
                vec3.N(entityPlayer3.z());
                vec3.m(entityPlayer3.N());
                vec3.Z(entityPlayer3.h());
                n3 = entityPlayer3.b$src$Z$fqlxe4() ? 1 : 0;
                int n16 = MathUtil.floor(entityPlayer3.z());
                int n17 = MathUtil.floor(entityPlayer3.N() - 0.015625);
                int n18 = MathUtil.floor(entityPlayer3.h());
                boolean bl8 = false;
                Block block = entityPlayerSP.getWorld().getBlockByPos(n16, n17, n18);
                if (block.isNotNull() && (block.isInstance(MappedClasses.q_) || block.isInstance(MappedClasses.b))) {
                    bl8 = true;
                }
                if (n3 != 0 || bl8) {
                    ++n15;
                    if (blockCoordinate == null) {
                        blockCoordinate = new BlockCoordinate(n16, n17, n18);
                    }
                    if (n15 >= n14 || entityPlayer3.e$src$Z$15bd4i1()) {
                        return blockCoordinate;
                    }
                } else {
                    blockCoordinate = null;
                    n15 = 0;
                }
                if (!(blockPathPlanner.T().N() <= (double)n2)) continue;
                return blockCoordinate;
            }
            n5 = MathUtil.floor(entityPlayer3.z());
            n4 = MathUtil.floor(entityPlayer3.N()) - 1;
            n3 = MathUtil.floor(entityPlayer3.h());
            return new BlockCoordinate(n5, n4, n3);
        }
        boolean bl9 = bl = this.Om.L() == false;
        if (bl) {
            int n19 = 1;
            EntityPlayer entityPlayer4 = blockPathPlanner.T();
            int n20 = 0;
            BlockCoordinate blockCoordinate = null;
            Vec3 vec3 = Vec3.create(entityPlayer4.z(), entityPlayer4.N(), entityPlayer4.h());
            for (int i = 0; i <= n; ++i) {
                boolean bl10 = entityPlayer4.b$src$Z$fqlxe4();
                blockPathPlanner.B();
                if (arrayList != null) {
                    arrayList.add(blockPathPlanner.f());
                }
                vec3.N(entityPlayer4.z());
                vec3.m(entityPlayer4.N());
                vec3.Z(entityPlayer4.h());
                boolean bl11 = entityPlayer4.b$src$Z$fqlxe4();
                int n21 = MathUtil.floor(entityPlayer4.z());
                int n22 = MathUtil.floor(entityPlayer4.N() - 0.015625);
                int n23 = MathUtil.floor(entityPlayer4.h());
                boolean bl12 = false;
                Block block = entityPlayerSP.getWorld().getBlockByPos(n21, n22, n23);
                if (block.isNotNull() && (block.isInstance(MappedClasses.q_) || block.isInstance(MappedClasses.b))) {
                    bl12 = true;
                }
                if (bl11 || bl12) {
                    blockCoordinate = new BlockCoordinate(n21, n22, n23);
                    if (++n20 >= n19 || !entityPlayer4.e$src$Z$15bd4i1()) {
                        // empty if block
                    }
                    return blockCoordinate;
                }
                blockCoordinate = null;
                n20 = 0;
                if (!(blockPathPlanner.T().N() <= (double)n2)) continue;
                return blockCoordinate;
            }
            return null;
        }
        int n24 = 3;
        EntityPlayer entityPlayer5 = blockPathPlanner.T();
        int n25 = 0;
        BlockCoordinate blockCoordinate = null;
        Vec3 vec3 = Vec3.create(entityPlayer5.z(), entityPlayer5.N(), entityPlayer5.h());
        for (int i = 0; i <= n; ++i) {
            boolean bl13 = entityPlayer5.b$src$Z$fqlxe4();
            blockPathPlanner.B();
            if (arrayList != null) {
                arrayList.add(blockPathPlanner.f());
            }
            vec3.N(entityPlayer5.z());
            vec3.m(entityPlayer5.N());
            vec3.Z(entityPlayer5.h());
            boolean bl14 = entityPlayer5.b$src$Z$fqlxe4();
            int n26 = MathUtil.floor(entityPlayer5.z());
            int n27 = MathUtil.floor(entityPlayer5.N() - 0.015625);
            int n28 = MathUtil.floor(entityPlayer5.h());
            boolean bl15 = false;
            Block block = entityPlayerSP.getWorld().getBlockByPos(n26, n27, n28);
            if (block.isNotNull() && (block.isInstance(MappedClasses.q_) || block.isInstance(MappedClasses.b))) {
                bl15 = true;
            }
            if (bl14 || bl15) {
                ++n25;
                if (blockCoordinate == null) {
                    blockCoordinate = new BlockCoordinate(n26, n27, n28);
                }
                if (n25 >= n24 || entityPlayer5.e$src$Z$15bd4i1()) {
                    return blockCoordinate;
                }
            } else {
                blockCoordinate = null;
                n25 = 0;
            }
            if (!(blockPathPlanner.T().N() <= (double)n2)) continue;
            return blockCoordinate;
        }
        return null;
    }

    private void p() {
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        if (Minecraft.currentScreen().getObject() == null) {
            KeyboardCodeUtil.v();
        }
        if (this.O_ != null) {
            MovementInputHelper.Q(this.O_, this.Os, this.O3, this.I);
        } else {
            MovementInputHelper.Q(this.K, this.OP, this.Oy, this.v);
        }
        if (this.V && !this.OJ) {
            MovementInputHelper.A(false);
        } else {
            MovementInputHelper.A(this.A.l);
        }
    }

    @EventHandler
    public void L(EventMouseButton eventMouseButton) {
        GameSettings gameSettings = eventMouseButton.getGameSettings();
        if (this.J != null && eventMouseButton.getButton() == EventMouseButton.E && eventMouseButton.getButtonState()) {
            eventMouseButton.setCancelled(true);
            gameSettings.F().e();
        }
    }

    public static RotationControlClaim E(BlockIn blockIn) {
        return blockIn.a;
    }

    public BlockIn() {
        super("Clutch", -65404, Category.Y, "Saves yourself from falling");
        this.Od = BooleanValue.create(this, "On lethal fall", true, "Catches the player if they are about to die due to fall damage");
        this.Om = BooleanValue.create(this, "On more than x blocks", false, "Catches the player if their landing block is more than x amount of blocks");
        this.On = NumberValue.create((Object)this, "Blocks", "#", "", 3.0, 6.0, 10.0, 1.0);
        this.O6 = NumberValue.create(this, "Speed", "#.#", "", 1.0, 3.5, 10.0, 0.1, "Maximum flick speed when placing blocks");
        this.OT = BooleanValue.create(this, "Silent aim", false, "Silent aim when placing blocks");
        this.Oz = BooleanValue.create(this, "Allow staircase up", true, "Allows clutch to staircase on repeat jumps");
        this.Or = RandomValue.G(this, "Clutch move delay", "#", "tick", 0.0, 3.0, 6.0, 10.0, 1.0, "Freezes movement for a few ticks after completing a long clutch\nNOTE: Only affects clutches related to high velocity.");
        this.Oo = NumberValue.create(this, "Fail delay", "#", "ms", 0.0, 100.0, 500.0, 50.0, "Delay before retrying to find a clutch path");
        this.OQ = BooleanValue.create(this, "Show block count", false);
        this.Oj = BooleanValue.create(this, "Limit blocks", false, "Only clutch if it requires fewer than the max number of blocks");
        this.Oi = NumberValue.create(this, "Max blocks", "#", "", 1.0, 5.0, 10.0, 1.0, "Maximum blocks allowed for a clutch");
        this.S = BooleanValue.create(this, "Return to last slot", true, "Selects previously selected slot when clutch is completed");
        this.OW = RandomValue.G(this, "Return delay", "#", "tick", 0.0, 3.0, 6.0, 10.0, 1.0, "Delay before returning to the last slot");
        this.OV = BooleanValue.create(this, "Reset angle", true, "Looks back to your original angle after clutching\nNOTE: Only affects non silent aim.");
        this.s = RandomValue.G(this, "Reset angle delay", "#", "tick", 0.0, 3.0, 6.0, 10.0, 1.0, "Delay before resetting your angles after clutching");
        this.OE = BooleanValue.create(this, "Blacklist", true, "Clutch will not use these blocks");
        this.Og = LimitValue.n(this, "clutch-blacklist", "Block blacklist", LimitValue.G, ItemLimitData.P);
        this.Ox = BooleanValue.create(this, "Held whitelist", false, "Only activates clutch when a whitelisted block is held\nWill only use held block for Clutching");
        this.P = LimitValue.N(this, "clutch-allowedblocks", "Held block whitelist", LimitValue.r, new ItemLimitData("blocks"));
        this.Z = new ArrayList<String>(Arrays.asList("Wool", "Stone", "Wood Planks", "Red Sandstone", "Stained Clay", "End Stone", "Obsidian"));
        this.a = SharedModuleControlClaims.I;
        this.OG = SharedModuleControlClaims.l;
        this.OB = new TimerUtil();
        this.OF = new TimerUtil();
        this.OD = new TimerUtil();
        this.Ob = new ArrayList<BlockPlacementGraph>();
        this.O9 = new VisibleModuleList(5000L);
        this.O5 = new HashSet();
        this.o = new HashSet();
        this.p = new ArrayList();
        this.Ou = new ArrayList();
        this.k = new HashMap();
        this.OE.K(this.Og);
        this.Ox.K(this.P);
        this.Om.K(this.On);
        this.S.K(this.OW);
        this.Oj.K(this.Oi);
        this.OT.C().z(this.OV);
        this.OV.C(ThemeColors.J.r);
        this.addValue(this.D, this.Od, this.Om, this.On, this.O6, this.OT, this.OV, this.s, this.S, this.OW, this.Or, this.Oo, this.Oz, this.OQ, this.Oj, this.Oi, this.OE, this.Og, this.Ox, this.P);
        this.a.l(this, 50);
    }

    @EventHandler
    public void u(EventPostTick eventPostTick) {
        EntityPlayerSP entityPlayerSP = eventPostTick.getThePlayer();
        WorldClient worldClient = eventPostTick.getWorld();
        if (entityPlayerSP.isNull() || worldClient.isNull()) {
            this.J$src$V$tfiw8c();
            return;
        }
        boolean bl = entityPlayerSP.b$src$Z$fqlxe4();
        if (bl) {
            if (this.Oq > 0) {
                --this.Oq;
                this.l("Knockback ticks: " + this.Oq + " " + this.A.A);
            }
            if (this.J != null) {
                if (this.Oq == 0) {
                    this.l("Reset knockback");
                    this.Oq = 0;
                }
                if (this.V && this.OJ) {
                    this.l("Revert to original yaw");
                    this.OI = this.O4;
                }
            }
            this.Ow = new BlockPlacementGraph(entityPlayerSP);
            if (this.J != null && this.J.g != null && this.t != null) {
                float f;
                float f2;
                if (this.U == null) {
                    f2 = entityPlayerSP.J();
                    f = entityPlayerSP.V();
                } else if (this.U instanceof AdaptiveRotationController) {
                    f2 = ((AdaptiveRotationController)this.U).J();
                    f = ((AdaptiveRotationController)this.U).X();
                } else {
                    f2 = this.U.k();
                    f = this.U.d();
                }
                int n = this.f(this.J, this.t, entityPlayerSP, entityPlayerSP, worldClient, f2, f, this.Ow);
                Vec3 vec3 = this.J.V;
                this.U = this.b(entityPlayerSP, vec3, this.t.v, this.U, n, this.OI);
                RotationManager.b.S(this.U);
            }
            return;
        }
        if (this.Oq > 0) {
            --this.Oq;
            this.l("Knockback ticks: " + this.Oq + " " + this.A.A);
        }
        if (this.J != null && this.Oq == 0 && this.V && this.OJ) {
            this.l("Revert to original yaw");
            this.OI = this.O4;
        }
        this.Ow = new BlockPlacementGraph(entityPlayerSP);
        if (this.J != null && this.J.g != null && this.t != null) {
            float f;
            float f3;
            if (this.U == null) {
                f3 = entityPlayerSP.J();
                f = entityPlayerSP.V();
            } else if (this.U instanceof AdaptiveRotationController) {
                f3 = ((AdaptiveRotationController)this.U).J();
                f = ((AdaptiveRotationController)this.U).X();
            } else {
                f3 = this.U.k();
                f = this.U.d();
            }
            int n = this.f(this.J, this.t, entityPlayerSP, entityPlayerSP, worldClient, f3, f, this.Ow);
            Vec3 vec3 = this.J.V;
            this.U = this.b(entityPlayerSP, vec3, this.t.v, this.U, n, this.OI);
            RotationManager.b.S(this.U);
        }
    }

    @Override
    public void onDisable() {
        ClientSettings.g(ActiveModuleStackFrame.class).w(this);
    }

    private void X$src$V$tn80je() {
    }

    private int J(BlockPlacementPathSegment blockPlacementPathSegment, ArrayList<Vec3d> arrayList, EntityPlayerSP entityPlayerSP, World world, Vector<PlacementTarget> vector, int n) {
        if (vector == null || vector.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        int n2 = 0;
        Stack<PlacementTarget> stack = new Stack<PlacementTarget>();
        for (PlacementTarget placementTarget : vector) {
            stack.push(placementTarget);
        }
        boolean bl = true;
        boolean bl2 = false;
        double d = vector.size() * 100;
        for (Vec3d vec3d : arrayList) {
            if (stack.isEmpty()) break;
            PlacementTarget placementTarget = (PlacementTarget)stack.peek();
            Vec3 vec3 = Vec3.create(vec3d.Y(), vec3d.t(), vec3d.o());
            if (ClutchPlacementPathUtils.P(vec3, world, placementTarget.k, placementTarget.G)) {
                stack.pop();
                bl = false;
            } else {
                d += 100000.0;
            }
            BlockData blockData = placementTarget.s();
            Vec3 vec32 = Vec3.create((double)blockData.D() + 0.5, vec3.getY(), (double)blockData.G() + 0.5);
            double d2 = vec3.distanceTo(vec32);
            d += d2 * 50000.0;
            ++n2;
        }
        int n3 = stack.size();
        d += (double)(n3 * 100000);
        if (bl) {
            d = 2.147483647E9;
        }
        return (int)d;
    }

    private void v(BlockPlacementPathSegment blockPlacementPathSegment, int n, EntityPlayerSP entityPlayerSP, World world, ArrayList<Vec3d> arrayList) {
        BlockInSearchPlanner blockInSearchPlanner;
        Vector<PlacementTarget> vector;
        if (blockPlacementPathSegment == null) {
            return;
        }
        if (blockPlacementPathSegment.g != null) {
            return;
        }
        BlockData blockData = blockPlacementPathSegment.t.O();
        BlockData blockData2 = blockPlacementPathSegment.R.O();
        int n2 = Math.abs(blockData.D() - blockData2.D());
        int n3 = Math.abs(blockData.G() - blockData2.G());
        int n4 = Math.abs(blockData.B() - blockData2.B());
        BlockInPlacementSearchStrategy blockInPlacementSearchStrategy = new BlockInPlacementSearchStrategy(this, n2, n3, n4, world, entityPlayerSP, blockPlacementPathSegment, arrayList);
        Block block = world.getBlockByPos(blockData2.D(), blockData2.B(), blockData2.G());
        boolean bl = BlockUtil.u(block);
        if (bl && (vector = (blockInSearchPlanner = new BlockInSearchPlanner(blockInPlacementSearchStrategy)).P(blockData, blockData2)) != null && !vector.isEmpty()) {
            blockPlacementPathSegment.g = new BlockPlacementPathSegmentState(vector.lastElement().G, vector);
        }
    }

    private BlockInBooleanState j(EntityPlayer entityPlayer, float f, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        BlockInBooleanState blockInBooleanState = new BlockInBooleanState();
        boolean bl5 = bl || bl2 || bl3 || bl4;
        float f2 = FreeLookHudModule.z() ? FreeLookHudModule.L$src$F$1jnmc2m() : entityPlayer.J();
        float f3 = RotationManager.b.G(f2, bl, bl2, bl3, bl4);
        if (bl5) {
            blockInBooleanState.b = bl;
            blockInBooleanState.f = bl2;
            blockInBooleanState.q = bl3;
            blockInBooleanState.d = bl4;
            float f4 = MathUtil.wrapAngleTo180(MathUtil.wrapAngleTo180(f3) - f);
            float f5 = f4 * ((float)Math.PI / 180);
            float f6 = (float)Math.cos(f5);
            float f7 = (float)(-Math.sin(f5));
            double d = 0.45f;
            boolean bl6 = (double)f6 >= d;
            boolean bl7 = (double)f7 >= d;
            boolean bl8 = (double)f7 <= -d;
            boolean bl9 = (double)f6 <= -d;
            blockInBooleanState.L = bl6;
            blockInBooleanState.v = bl9;
            blockInBooleanState.h = bl8;
            blockInBooleanState.c = bl7;
            return blockInBooleanState;
        }
        return null;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private boolean q(EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP, World world, BlockPathPlanner blockPathPlanner, BlockPlacementPathSegment blockPlacementPathSegment) {
        boolean bl = false;
        boolean bl2 = entityPlayer.b$src$Z$fqlxe4();
        double d = entityPlayer.N() - 0.015625;
        Block block = world.getBlockByPos(MathUtil.floor(entityPlayer.z()), MathUtil.floor(d), MathUtil.floor(entityPlayer.h()));
        boolean bl3 = block.isNotNull() && BlockUtil.b(block);
        BlockCoordinate blockCoordinate = blockPlacementPathSegment.R;
        int n = 0;
        EntityPlayer entityPlayer2 = blockPathPlanner.T();
        blockPathPlanner.l();
        for (int i = 0; i < 10; ++i) {
            blockPathPlanner.B();
            double d2 = entityPlayer2.t();
            double d3 = entityPlayer2.T();
            if (entityPlayer2.b$src$Z$fqlxe4()) {
                boolean bl4;
                ++n;
                boolean bl5 = entityPlayer2.z() > (double)blockCoordinate.B() && entityPlayer2.z() < (double)(blockCoordinate.B() + 1);
                boolean bl6 = entityPlayer2.h() > (double)blockCoordinate.A() && entityPlayer2.h() < (double)(blockCoordinate.A() + 1);
                boolean bl7 = bl4 = bl5 && bl6;
                if (Math.abs(d2) < 0.005 && Math.abs(d3) < 0.005 && bl4) {
                    bl = false;
                    break;
                }
            } else {
                if (bl2) {
                    bl = true;
                    break;
                }
                n = 0;
            }
            bl2 = blockPathPlanner.T().b$src$Z$fqlxe4();
        }
        return bl;
    }

    @Override
    public ModDisplayInfo J() {
        String string;
        if (!this.OQ.L().booleanValue()) {
            return null;
        }
        int n = this.J == null ? this.O$src$I$ti9uvy() : this.J.w();
        Color color = new Color(255, 20, 20);
        if (n >= 32) {
            color = new Color(2, 190, 58);
        } else if (n >= 16) {
            color = new Color(255, 249, 18);
        }
        if (this.J != null) {
            string = "\u00a7f\u00a7l";
        } else {
            boolean bl = this.Oh > 0 && this.s.y() > 0;
            boolean bl2 = this.j != -1 && this.S.L() != false && this.OS > 0 && this.OW.y() > 0;
            boolean bl3 = this.Op > 0 && this.Or.y() > 0;
            string = bl || bl2 || bl3 ? "\u00a7e" : "\u00a77";
        }
        String string2 = (this.J == null ? "\u00a7r" : "\u00a76\u00a7l") + n;
        String string3 = " " + string + "(" + this.getName() + ")";
        return new ModDisplayInfo(string2, color, string3);
    }

    private Vec3 M(EntityPlayer entityPlayer, World world, BlockData blockData, EnumFacing enumFacing) {
        Vec3 vec3;
        double d = entityPlayer.z();
        double d2 = entityPlayer.h();
        if (enumFacing == null || enumFacing.isNull()) {
            vec3 = Vec3.create((double)blockData.D() + 0.5, (double)blockData.B() + 0.5, (double)blockData.G() + 0.5);
        } else {
            AxisAlignedBB axisAlignedBB = BlockUtil.F(world, blockData);
            double d3 = axisAlignedBB.getMinX() + (axisAlignedBB.getMaxX() - axisAlignedBB.getMinX()) / 2.0;
            double d4 = axisAlignedBB.getMinY() + (axisAlignedBB.getMaxY() - axisAlignedBB.getMinY()) / 2.0;
            double d5 = axisAlignedBB.getMinZ() + (axisAlignedBB.getMaxZ() - axisAlignedBB.getMinZ()) / 2.0;
            double d6 = d3 - d;
            double d7 = d5 - d2;
            switch (enumFacing.Y()) {
                case 2: {
                    d5 = axisAlignedBB.getMinZ();
                    d3 = Math.abs(d6) < (axisAlignedBB.getMaxX() - axisAlignedBB.getMinX()) / 2.0 ? d : (d < (double)blockData.D() ? axisAlignedBB.getMinX() : axisAlignedBB.getMaxX());
                    break;
                }
                case 3: {
                    d5 = axisAlignedBB.getMaxZ();
                    d3 = Math.abs(d6) < (axisAlignedBB.getMaxX() - axisAlignedBB.getMinX()) / 2.0 ? d : (d < (double)blockData.D() ? axisAlignedBB.getMinX() : axisAlignedBB.getMaxX());
                    break;
                }
                case 4: {
                    d3 = axisAlignedBB.getMinX();
                    d5 = Math.abs(d7) < (axisAlignedBB.getMaxZ() - axisAlignedBB.getMinZ()) / 2.0 ? d2 : (d2 < (double)blockData.G() ? axisAlignedBB.getMinZ() : axisAlignedBB.getMaxZ());
                    break;
                }
                case 5: {
                    d3 = axisAlignedBB.getMaxX();
                    d5 = Math.abs(d7) < (axisAlignedBB.getMaxZ() - axisAlignedBB.getMinZ()) / 2.0 ? d2 : (d2 < (double)blockData.G() ? axisAlignedBB.getMinZ() : axisAlignedBB.getMaxZ());
                    break;
                }
                case 1: {
                    d4 = axisAlignedBB.getMaxY();
                    break;
                }
                case 0: {
                    d4 = axisAlignedBB.getMinY();
                }
            }
            vec3 = Vec3.create(d3, d4, d5);
        }
        return vec3;
    }

    private void v(int n) {
        Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
    }

    private void Z(String string, boolean bl) {
        boolean bl2;
        boolean bl3 = false;
        boolean bl4 = bl2 = this.H != null && this.H.M();
        if (this.H == null) {
            this.H = new Notification(NotificationType.ALERT, "Clutch Failed", new TextNotificationContent(string), 0.0, 0.0, 3500L);
            bl3 = true;
        } else if (bl2 || bl) {
            bl3 = bl2;
            TextNotificationContent textNotificationContent = (TextNotificationContent)this.H.X$src$Lgg_vape_notification_NotificationContent_$1gg6y56();
            textNotificationContent.k(string);
            this.H.d(3500L);
        }
        if (bl3) {
            Vape.INSTANCE.getNotificationManager().x(this.H, false);
        }
    }

    private boolean M$src$Z$th6a3v() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return false;
        }
        return entityPlayerSP.t() != 0.0 || entityPlayerSP.q() != 0.0 || entityPlayerSP.T() != 0.0;
    }

    private BlockPlacementPathSegment q(int n, BlockPlacementPathSegment blockPlacementPathSegment, World world, ItemStack itemStack, ArrayList<BlockData> arrayList, EntityPlayer entityPlayer, EntityPlayerSP entityPlayerSP) {
        int n2;
        Object object;
        int n3 = ForgeVersion.MC_1_20_6.d() ? world.R() : 0;
        long l = System.nanoTime();
        BlockState blockState = BlockUtil.E(itemStack);
        HashMap<Object, BlockState> hashMap = new HashMap<Object, BlockState>();
        int n4 = blockPlacementPathSegment.g.M.size();
        BlockPathPlanner blockPathPlanner = new BlockPathPlanner(entityPlayerSP, entityPlayerSP, world, this.A, this.L);
        PlayerSimulationUtil.s(blockPathPlanner.T(), entityPlayerSP);
        blockPathPlanner.U(this.A);
        EntityPlayer entityPlayer2 = blockPathPlanner.T();
        if (this.OL) {
            blockPathPlanner.t();
        } else {
            blockPathPlanner.l();
        }
        int n5 = blockPlacementPathSegment.g.M.size();
        boolean bl = false;
        BlockPlacementPathSegment blockPlacementPathSegment2 = new BlockPlacementPathSegment(blockPlacementPathSegment.t, blockPlacementPathSegment.R, new ArrayList<Vec3d>());
        blockPlacementPathSegment2.g = new BlockPlacementPathSegmentState(blockPlacementPathSegment.g.W, new Vector<PlacementTarget>(blockPlacementPathSegment.g.M));
        blockPlacementPathSegment2.b.add(blockPathPlanner.f());
        FixedRotationController fixedRotationController = null;
        if (this.U != null && this.U instanceof AdaptiveRotationController && RotationManager.b.u()) {
            fixedRotationController = new AdaptiveRotationController(entityPlayer2);
            ((AdaptiveRotationController)fixedRotationController).I((AdaptiveRotationController)this.U);
            blockPathPlanner.y(fixedRotationController);
        }
        int n6 = 0;
        boolean bl2 = RotationManager.b.u();
        int n7 = this.Oq;
        boolean bl3 = false;
        boolean bl4 = false;
        boolean bl5 = false;
        try {
            for (int i = 0; i <= n; ++i) {
                boolean d;
                boolean bl6;
                boolean bl7;
                boolean bl8;
                float f;
                Vector<PlacementTarget> n12;
                Wrapper f8;
                Object object2;
                Object object3;
                BlockInTargetRotationState object4 = blockPathPlanner.E();
                object = object4.U();
                RayTraceResult rayTraceResult = blockPathPlanner.C(3.0, 0.0f, false);
                object4.R(rayTraceResult);
                blockPlacementPathSegment2.P.add(object4);
                if (blockPlacementPathSegment2 != null) {
                    if (entityPlayer2.b$src$Z$fqlxe4()) {
                        BlockPathPlanner blockPathPlanner2 = new BlockPathPlanner(entityPlayer2, entityPlayerSP, world, (BlockPlacementGraph)object);
                        boolean bl9 = this.q(entityPlayer2, entityPlayerSP, world, blockPathPlanner2, blockPlacementPathSegment2);
                        if (++n6 >= 5 || !bl9) {
                            bl4 = true;
                            break;
                        }
                    } else {
                        n6 = 0;
                    }
                }
                Object object5 = null;
                if (blockPlacementPathSegment2.g != null) {
                    object5 = this.t(blockPlacementPathSegment2, entityPlayerSP, world, blockState);
                    if (object5 != null) {
                        float f2;
                        float f3;
                        if (fixedRotationController == null) {
                            if (RotationManager.b.u()) {
                                object3 = (AdaptiveRotationController)RotationManager.b.w();
                                f3 = ((AdaptiveRotationController)object3).J();
                                f2 = ((AdaptiveRotationController)object3).X();
                            } else {
                                f3 = entityPlayerSP.J();
                                f2 = entityPlayerSP.V();
                            }
                        } else if (fixedRotationController instanceof AdaptiveRotationController) {
                            f3 = ((AdaptiveRotationController)fixedRotationController).J();
                            f2 = ((AdaptiveRotationController)fixedRotationController).X();
                        } else {
                            f3 = fixedRotationController.k();
                            f2 = fixedRotationController.d();
                        }
                        boolean bl9 = this.U((PlacementTarget)object5, blockPathPlanner);
                        if (bl9) {
                            object2 = ((PlacementTarget)object5).s();
                            f8 = BlockPos.d((BlockData)object2);
                            hashMap.put(object2, world.getBlockState((BlockPos)f8));
                            BlockUtil.z(world, (BlockPos)f8, blockState);
                            if (blockPlacementPathSegment2.g != null) {
                                Object f9;
                                Object vec3;
                                object5 = null;
                                BlockPlacementPathSegmentState f7 = blockPlacementPathSegment2.g;
                                n12 = f7.M;
                                if (!n12.isEmpty()) {
                                    n12.removeElementAt(0);
                                }
                                if (n12.isEmpty() && n5 > 3 && !this.OL && !this.A.A && this.q(entityPlayer2, entityPlayerSP, world, (BlockPathPlanner)(vec3 = new BlockPathPlanner(entityPlayer2, entityPlayerSP, world, (BlockPlacementGraph)object)), blockPlacementPathSegment2)) {
                                    f9 = ((BlockPathPlanner)vec3).T();
                                    int f6 = MathUtil.floor(((Entity)f9).z());
                                    int n8 = MathUtil.floor(((Entity)f9).h());
                                    int n9 = f6 - blockPlacementPathSegment2.R.B();
                                    int n10 = n8 - blockPlacementPathSegment2.R.A();
                                    if (Math.abs(n9) + Math.abs(n10) < 3) {
                                        BlockCoordinate blockCoordinate = blockPlacementPathSegment2.R;
                                        BlockCoordinate blockCoordinate2 = new BlockCoordinate(f6, blockPlacementPathSegment2.R.E(), n8);
                                        BlockPlacementPathSegment blockPlacementPathSegment3 = new BlockPlacementPathSegment(blockCoordinate, blockCoordinate2, new ArrayList<Vec3d>());
                                        this.v(blockPlacementPathSegment3, 3, entityPlayerSP, world, new ArrayList<Vec3d>());
                                        if (blockPlacementPathSegment3.g != null && blockPlacementPathSegment3.g.M != null && !blockPlacementPathSegment3.g.M.isEmpty()) {
                                            n12.addAll(blockPlacementPathSegment3.g.M);
                                            blockPlacementPathSegment.g.Z(blockPlacementPathSegment3.g.M);
                                            blockPlacementPathSegment.R = blockPlacementPathSegment3.R;
                                            bl5 = true;
                                        }
                                    }
                                }
                                while (!n12.isEmpty()) {
                                    vec3 = (PlacementTarget)n12.firstElement();
                                    if (vec3 == null) continue;
                                    f9 = ((PlacementTarget)vec3).s();
                                    Block d3 = world.getBlockByPos(((BlockData)f9).D(), ((BlockData)f9).B(), ((BlockData)f9).G());
                                    if (BlockUtil.u(d3)) {
                                        if (ClutchPlacementPathUtils.V(world, entityPlayerSP, (BlockData)f9)) {
                                            object5 = vec3;
                                            break;
                                        }
                                        blockPlacementPathSegment2.I("[SIM] Entity colision");
                                        break;
                                    }
                                    blockPlacementPathSegment2.V = null;
                                    n12.removeElementAt(0);
                                }
                                if (object5 == null && this.V && fixedRotationController != null) {
                                    float d2 = this.OJ ? this.O4 : this.OI;
                                    float f4 = Math.abs(MathUtil.wrapAngleTo180(d2 - f3));
                                    float f5 = Math.abs(f4) / 1.8f / 3.0f;
                                    fixedRotationController.Y(f5);
                                    fixedRotationController.g(d2, fixedRotationController.s$src$F$15o72go());
                                }
                            }
                        }
                    } else if (blockPlacementPathSegment2.g != null && blockPlacementPathSegment2.C() > n) break;
                }
                boolean f3 = entityPlayer2.b$src$Z$fqlxe4();
                BlockInBooleanState f2 = null;
                if (fixedRotationController != null && !(fixedRotationController instanceof AdaptiveRotationController) && (f2 = this.j(entityPlayer2, f = this.OJ && bl3 ? this.O4 : this.OI, bl8 = this.A.M, bl7 = this.A.D, bl6 = this.A.R, d = this.A.Y)) != null) {
                    blockPathPlanner.G(f2.L, f2.v, f2.h, f2.c);
                }
                blockPathPlanner.I(false);
                if (f2 != null) {
                    blockPathPlanner.G(f2.b, f2.f, f2.q, f2.d);
                    ((BlockPlacementGraph)object).M = f2.b;
                    ((BlockPlacementGraph)object).D = f2.f;
                    ((BlockPlacementGraph)object).R = f2.q;
                    ((BlockPlacementGraph)object).Y = f2.d;
                }
                if (!f3 && entityPlayer2.b$src$Z$fqlxe4()) {
                    if (MathUtil.floor(entityPlayer2.N()) >= blockPlacementPathSegment2.R.E() + 1) {
                        bl = true;
                        if (!this.V || this.OJ && this.A.A) {
                            bl4 = true;
                        }
                    }
                } else if (f3 && !entityPlayer2.b$src$Z$fqlxe4() && bl5) {
                    bl4 = false;
                }
                if (this.OJ && (entityPlayer2.b$src$Z$fqlxe4() || --n7 == 0)) {
                    bl3 = true;
                }
                object3 = blockPathPlanner.E();
                object2 = object4.U();
                ((BlockInTargetRotationState)object3).R(rayTraceResult);
                if (object5 != null) {
                    float f6;
                    float f7;
                    if (fixedRotationController == null) {
                        if (RotationManager.b.u()) {
                            AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)RotationManager.b.w();
                            f7 = adaptiveRotationController.J();
                            f6 = adaptiveRotationController.X();
                        } else {
                            f7 = entityPlayerSP.J();
                            f6 = entityPlayerSP.V();
                        }
                    } else if (fixedRotationController instanceof AdaptiveRotationController) {
                        f7 = ((AdaptiveRotationController)fixedRotationController).J();
                        f6 = ((AdaptiveRotationController)fixedRotationController).X();
                    } else {
                        f7 = fixedRotationController.k();
                        f6 = fixedRotationController.d();
                    }
                    if (((PlacementTarget)object5).v != null) {
                        object4.M(((PlacementTarget)object5).v.toVec3d());
                        object4.e(blockPlacementPathSegment2.V.toVec3d());
                    }
                    int n11 = this.f(blockPlacementPathSegment2, (PlacementTarget)object5, entityPlayer2, entityPlayerSP, world, f7, f6, (BlockPlacementGraph)object2);
                    Vec3 vec3 = blockPlacementPathSegment2.V;
                    float f9 = this.OJ && bl3 ? this.O4 : this.OI;
                    fixedRotationController = this.b(entityPlayer2, vec3, ((PlacementTarget)object5).v, fixedRotationController, n11, f9);
                    blockPathPlanner.y(fixedRotationController);
                }
                blockPathPlanner.d();
                if (this.V && !this.OJ) {
                    blockPathPlanner.K();
                }
                blockPlacementPathSegment2.b.add(blockPathPlanner.f());
                if (blockPathPlanner.T().N() <= (double)n3) {
                    blockPlacementPathSegment2.I("Player would be below the world");
                    this.l("[SIM] Player is below the world at tick " + i);
                } else {
                    if (object5 != null) {
                        f8 = BlockUtil.F(world, ((PlacementTarget)object5).s());
                        double d2 = ((AxisAlignedBB)f8).getMinX();
                        double d3 = ((AxisAlignedBB)f8).getMaxX();
                        double d4 = ((AxisAlignedBB)f8).getMinZ();
                        double d5 = ((AxisAlignedBB)f8).getMaxZ();
                        double d6 = ((AxisAlignedBB)f8).getMinY();
                        double d7 = ((AxisAlignedBB)f8).getMaxY();
                    }
                    if (entityPlayer2.N() < (double)(blockPlacementPathSegment2.R.E() - 1) && entityPlayer2.N() < (double)(blockPlacementPathSegment2.t.E() - 1)) {
                        if (bl && !entityPlayer2.b$src$Z$fqlxe4()) {
                            blockPlacementPathSegment2.I("Player would fall off after landing");
                        } else {
                            blockPlacementPathSegment2.I("Player would be too low to land");
                        }
                        this.l("[SIM] Player is too low to land on block at tick " + i + " " + bl);
                    } else if ((double)blockPlacementPathSegment.R.E() > entityPlayer2.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY() && (double)blockPlacementPathSegment.t.E() > entityPlayer2.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY()) {
                        if (bl && !entityPlayer2.b$src$Z$fqlxe4()) {
                            blockPlacementPathSegment2.I("Player would fall off after landing");
                        } else {
                            blockPlacementPathSegment2.I("Player would be too low to land");
                        }
                        this.l("[SIM] Player is too low to land on block at tick " + i + " " + bl);
                    } else if (object5 != null || blockPlacementPathSegment2.g != null || !bl4) {
                        continue;
                    }
                }
                break;
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            object = BlockPos.d((BlockData)entry.getKey());
            long l2 = System.nanoTime();
            BlockUtil.z(world, (BlockPos)object, (BlockState)entry.getValue());
        }
        blockPlacementPathSegment.P.addAll(blockPlacementPathSegment2.P);
        if (bl && (entityPlayer2.b$src$Z$fqlxe4() || bl4)) {
            blockPlacementPathSegment.b.clear();
            blockPlacementPathSegment.b.addAll(blockPlacementPathSegment2.b);
            return blockPlacementPathSegment;
        }
        int n12 = n2 = blockPlacementPathSegment2.g != null ? blockPlacementPathSegment2.g.M.size() : -1;
        if (blockPlacementPathSegment2.u()) {
            blockPlacementPathSegment.p = blockPlacementPathSegment2.p;
            return blockPlacementPathSegment;
        }
        if (!blockPlacementPathSegment2.u() && n2 > 0) {
            blockPlacementPathSegment.p = blockPlacementPathSegment2.p;
            return blockPlacementPathSegment;
        }
        double d = (double)(System.nanoTime() - l) / 1000000.0;
        this.l("Failed to simulate clutch path for " + n + " ticks in " + d + "ms with " + n2 + "/" + n5 + " blocks remaining");
        return null;
    }

    private boolean C(ItemStack itemStack) {
        if (itemStack.isNull() || itemStack.getItem().isNull()) {
            return false;
        }
        return this.P.A(itemStack);
    }

    private PlacementTarget Q(BlockPlacementPathSegment blockPlacementPathSegment, EntityPlayer entityPlayer, World world) {
        return this.t(blockPlacementPathSegment, entityPlayer, world, null);
    }

    private void C(ArrayList<BlockPlacementPathSegment> arrayList, Vec3d vec3d, EntityPlayerSP entityPlayerSP) {
        arrayList.sort(Comparator.comparingDouble(arg_0 -> this.lambda$sortClutchPaths$0(vec3d, entityPlayerSP, arg_0)));
    }

    public void F(Vec3 vec3, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glDisable((int)2929);
        double d = Minecraft.D().getRenderPosX();
        double d2 = Minecraft.D().getRenderPosY();
        double d3 = Minecraft.D().getRenderPosZ();
        Vec3 vec32 = vec3;
        double d4 = vec32.getX() - 0.05;
        double d5 = vec32.getY() - 0.05;
        double d6 = vec32.getZ() - 0.05;
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(d4, d5, d6, d4 + 0.1, d5 + 0.1, d6 + 0.1).A(-d, -d2, -d3);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.25f);
        this.l(axisAlignedBB);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    private boolean W(EntityPlayerSP entityPlayerSP) {
        int n = this.c();
        if (n == -1) {
            return false;
        }
        if (this.j == -1) {
            this.j = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        }
        this.v(n);
        return true;
    }

    public static int v(BlockIn blockIn, BlockPlacementPathSegment blockPlacementPathSegment, ArrayList arrayList, EntityPlayerSP entityPlayerSP, World world, Vector vector, int n) {
        return blockIn.J(blockPlacementPathSegment, arrayList, entityPlayerSP, world, vector, n);
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
    }

    @EventHandler
    public void s(EventClickMouse eventClickMouse) {
        GameSettings gameSettings = eventClickMouse.getGameSettings();
        if (this.J != null) {
            eventClickMouse.setCancelled(true);
            gameSettings.F().e();
        }
    }

    private int c() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < 9; ++i) {
            ItemStack object = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
            if (!object.isNotNull() || !this.y(object)) continue;
            arrayList.add(i);
        }
        if (arrayList.isEmpty()) {
            return -1;
        }
        for (String string : this.Z) {
            for (Integer n : arrayList) {
                if (!Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(n).x().contains(string)) continue;
                return n;
            }
        }
        return (Integer)arrayList.get(0);
    }

    private int O$src$I$ti9uvy() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        InventoryPlayer inventoryPlayer = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        if (inventoryPlayer.isNull() || entityPlayerSP.isNull()) {
            return 0;
        }
        int n = 0;
        if (this.Ox.L().booleanValue()) {
            if (entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt().isNull() || entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt().getItem().isNull()) {
                return 0;
            }
            if (!this.C(entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt())) {
                return 0;
            }
            if (!this.y(entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt())) {
                return 0;
            }
            return entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt().t();
        }
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = inventoryPlayer.c(i);
            if (itemStack.isNull() || !itemStack.getItem().isInstance(MappedClasses.Vw)) continue;
            if (this.y(itemStack)) {
                n += itemStack.t();
                continue;
            }
            for (String string : this.Z) {
                if (!itemStack.x().contains(string)) continue;
                n += itemStack.t();
            }
        }
        return n;
    }

    public static double B(BlockIn blockIn, double d) {
        blockIn.OR = d;
        return blockIn.OR;
    }

    private void U(World world, EntityPlayerSP entityPlayerSP) {
        if (this.J != null || !this.Ou.isEmpty()) {
            return;
        }
        ItemStack itemStack = this.p(entityPlayerSP);
        if (this.M$src$Z$th6a3v() && this.J == null) {
            boolean bl;
            boolean bl2 = bl = !entityPlayerSP.b$src$Z$fqlxe4() || entityPlayerSP.q() >= 0.0;
            if (bl) {
                BlockCoordinate blockCoordinate = this.U(50, entityPlayerSP);
                boolean bl3 = false;
                boolean bl4 = false;
                boolean bl5 = false;
                if (blockCoordinate != null) {
                    if (this.Od.L().booleanValue() && entityPlayerSP.N() - (double)blockCoordinate.E() - 3.0 > (double)entityPlayerSP.w$src$F$15l9epb()) {
                        bl4 = true;
                    }
                    if (this.Om.L().booleanValue() && entityPlayerSP.N() - (double)(blockCoordinate.E() + 1) >= (Double)this.On.K()) {
                        bl5 = true;
                    }
                } else {
                    bl3 = this.D.L();
                }
                if (bl3 || bl4 || bl5) {
                    if (!this.a.U(this) && !this.a.h(this, this.OT.L())) {
                        return;
                    }
                    this.J = this.z(world, entityPlayerSP, itemStack);
                }
            } else {
                this.Oa = entityPlayerSP.N();
            }
        }
    }

    public static VisibleModuleList q(BlockIn blockIn) {
        return blockIn.O9;
    }

    private void f(EntityPlayerSP entityPlayerSP) {
        boolean bl = true;
        if (this.OV.L().booleanValue() && !this.OT.L().booleanValue() && !this.a.e(this) && this.OR != -999.0 && this.U != null) {
            RotationManager.b.v(this.U);
            this.U = null;
            float f = MathUtil.wrapAngleTo180(entityPlayerSP.J() - (float)this.OR);
            float f2 = f / 90.0f * 5.0f;
            f2 = Math.max(f2, 1.0f);
            float f3 = entityPlayerSP.J() - (float)this.OR;
            BlockInThresholdRotationController blockInThresholdRotationController = new BlockInThresholdRotationController(this, Minecraft.thePlayer(), f3, entityPlayerSP.V() - (float)this.OH);
            blockInThresholdRotationController.D(true);
            blockInThresholdRotationController.U(true);
            blockInThresholdRotationController.s(true);
            blockInThresholdRotationController.Y(f2);
            RotationManager.b.S(blockInThresholdRotationController);
            bl = false;
        }
        if (bl && this.U != null) {
            this.U.k(true);
            this.U.z(true);
            this.U.U(true);
            this.U.t(0.0f);
            this.U.Y(3.0f);
            RotationManager.b.v(this.U);
        }
    }

    private boolean U(PlacementTarget placementTarget, BlockPathPlanner blockPathPlanner) {
        boolean bl = false;
        if (placementTarget != null) {
            EntityPlayer entityPlayer = blockPathPlanner.T();
            AxisAlignedBB axisAlignedBB = BlockUtil.F(entityPlayer.getWorld(), placementTarget.s());
            AxisAlignedBB axisAlignedBB2 = entityPlayer.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu();
            if (axisAlignedBB2.intersects(axisAlignedBB)) {
                return bl;
            }
            RayTraceResult rayTraceResult = blockPathPlanner.C(3.0, 0.0f, false);
            if (rayTraceResult.isBlockHit()) {
                boolean bl2;
                if (ForgeVersion.MC_1_7_10.Y()) {
                    bl2 = rayTraceResult.getBlockPos().equals(BlockPos.d(placementTarget.k));
                } else {
                    boolean bl3 = bl2 = rayTraceResult.g() == placementTarget.k.D() && rayTraceResult.T() == placementTarget.k.B() && rayTraceResult.a$src$I$8nuo9d() == placementTarget.k.G();
                }
                if (bl2) {
                    boolean bl4;
                    EnumFacing enumFacing = placementTarget.M ? placementTarget.G : null;
                    boolean bl5 = bl4 = enumFacing == null;
                    if (enumFacing != null && enumFacing.equals(rayTraceResult.getSideHit())) {
                        bl4 = true;
                    }
                    if (bl4) {
                        bl = true;
                    }
                } else {
                    BlockPos blockPos = rayTraceResult.getBlockPos();
                    BlockData blockData = placementTarget.s();
                    if (blockData.y(blockPos.offset(rayTraceResult.getSideHit()))) {
                        bl = true;
                    }
                }
            }
        }
        return bl;
    }

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        EntityPlayerSP entityPlayerSP = eventRender2D.getThePlayer();
    }

    private void E(EntityPlayerSP entityPlayerSP) {
        boolean bl = entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying();
    }

    private BlockPlacementPathSegment D(World world, BlockPathPlanner blockPathPlanner, ItemStack itemStack) {
        int n;
        int n2;
        int n3;
        Object object;
        FixedRotationController fixedRotationController;
        Vec3 vec3;
        Object object2;
        BlockData blockData;
        boolean bl;
        int n4 = JavassistMappingTask.U();
        if (!this.Ou.isEmpty()) {
            return null;
        }
        int n5 = ForgeVersion.MC_1_20_6.d() ? world.R() : 0;
        BlockPlacementGraph blockPlacementGraph = blockPathPlanner.E().U();
        HashMap<Integer, BlockData> hashMap = new HashMap<Integer, BlockData>();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        EntityPlayer entityPlayer = blockPathPlanner.T();
        int n6 = 0;
        double d = entityPlayer.N();
        double d2 = entityPlayer.q();
        boolean bl2 = bl = this.OY && entityPlayer.q() > 0.0;
        if (bl) {
            int blockPlacementPathSegment3;
            int n7;
            int n8;
            Object object3;
            FixedRotationController fixedRotationController2;
            Vec3 vec32;
            Object object4;
            BlockData blockData2;
            this.Oa = entityPlayerSP.N() + entityPlayer.q();
            boolean bl3 = Math.abs(this.Oa - entityPlayerSP.N()) > 1.0;
            while (true) {
                d += d2;
                if (!bl3 && !(d >= this.Oa) || n6 >= 20) break;
                d2 -= 0.08;
                d2 *= (double)0.98f;
                ++n6;
            }
            double d3 = entityPlayer.q();
            boolean bl4 = entityPlayer.b$src$Z$fqlxe4();
            double d4 = bl4 && d3 > 0.0 ? (double)0.6f : 1.0;
            double d5 = d4 * (double)0.91f;
            blockPathPlanner.l();
            blockPathPlanner.B();
            double d6 = entityPlayer.z() - entityPlayer.f();
            double d7 = entityPlayer.h() - entityPlayer.R();
            double d8 = entityPlayer.t();
            double d9 = entityPlayer.T();
            blockPathPlanner.U(blockPlacementGraph);
            for (int i = 0; i < n6 - 1; ++i) {
                d4 = bl4 && d3 > 0.0 ? (double)0.6f : 1.0;
                d5 = d4 * (double)0.91f;
                if (Math.abs(d8) < 0.005) {
                    d8 = 0.0;
                }
                if (Math.abs(d9) < 0.005) {
                    d9 = 0.0;
                }
                d6 += d8;
                d7 += d9;
                d8 *= d5;
                d9 *= d5;
                bl4 = false;
            }
            double d10 = Math.abs(d6);
            double d11 = Math.abs(d7);
            double d12 = Math.abs(d10 - d11);
            double d13 = Math.sqrt(d10 * d10 + d11 * d11);
            int n10 = (int)Math.round(d13);
            if (n10 >= 4) {
                boolean bl5;
                this.l("Clutch is not possible standing still. Forcing counter motion.");
                this.A.A = bl5 = this.OJ && gg.vape.config.ClientSettings.B(Minecraft.gameSettings().O());
                this.A.M = true;
                this.A.D = false;
                this.A.R = false;
                this.A.Y = false;
                this.OL = true;
                this.V = true;
                this.OI = MovementInputHelper.U(entityPlayerSP);
            } else {
                this.V = false;
                this.OL = false;
            }
            if (this.OL) {
                blockPathPlanner.t();
            } else {
                blockPathPlanner.l();
            }
            this.l("Estimated ticks: " + n6 + " Blocks: " + n10);
            double d14 = entityPlayer.t();
            double d15 = entityPlayer.T();
            boolean bl6 = false;
            int n11 = MathUtil.floor(entityPlayer.z());
            int n12 = MathUtil.floor(entityPlayer.N() - 0.015625);
            int n13 = MathUtil.floor(entityPlayer.h());
            int n14 = Objects.hash(n11, n12, n13);
            if (hashMap.containsKey(n14)) {
                blockData2 = (BlockData)hashMap.get(n14);
            } else {
                blockData2 = new BlockData(n11, n12, n13);
                hashMap.put(n14, blockData2);
            }
            Block block = world.getBlockByPos(blockData2.D(), blockData2.B(), blockData2.G());
            if (!BlockUtil.u(block)) {
                bl6 = true;
            }
            if (this.V) {
                object4 = Vec3.create(entityPlayer.z(), entityPlayer.N() + (double)entityPlayer.X(), entityPlayer.h());
                vec32 = ((Vec3)object4).addVector(-d14, 0.0, -d15);
                fixedRotationController2 = this.b(entityPlayer, (Vec3)object4, vec32, null, 1, this.OI);
                blockPathPlanner.y(fixedRotationController2);
            } else {
                object4 = Vec3.create(entityPlayer.z(), entityPlayer.N() + (double)entityPlayer.X(), entityPlayer.h());
                vec32 = entityPlayer.J(1.0f);
                Vec3 vec33 = ((Vec3)object4).addVector(vec32.getX() * 5.0, vec32.getY() * 5.0, vec32.getZ() * 5.0);
                fixedRotationController2 = this.b(entityPlayer, (Vec3)object4, vec33, null, 1, this.OI);
                blockPathPlanner.y(fixedRotationController2);
            }
            HashMap<BlockData, Double> pathScores = new HashMap<BlockData, Double>();
            int n15 = 4;
            int n16 = bl6 ? 1 : n15;
            Vec3d vec3d = blockPathPlanner.f();
            arrayList.add(vec3d);
            int n17 = entityPlayerSP.b$src$Z$fqlxe4() ? -2 : (entityPlayerSP.q() > 0.0 ? -3 : -1);
            boolean bl7 = false;
            int n18 = 0;
            int n19 = this.Oq;
            long l = System.nanoTime();
            for (int i = 0; i <= Math.max(n6, 15); ++i) {
                int n20;
                int n21;
                int n22;
                double d16 = entityPlayer.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY();
                ++n18;
                for (int j = 0; j >= n17; --j) {
                    for (int k = 0; k < n15; ++k) {
                        int n23 = d14 >= 0.0 ? -n16 : -k;
                        int n24 = d14 >= 0.0 ? k : n16;
                        int n25 = d15 >= 0.0 ? -n16 : -k;
                        int n26 = d15 >= 0.0 ? k : n16;
                        for (n22 = n23; n22 <= n24; ++n22) {
                            for (n21 = n25; n21 <= n26; ++n21) {
                                boolean bl8;
                                BlockData blockData3;
                                if (Math.abs(n22) != k && Math.abs(n21) != k) continue;
                                n20 = MathUtil.floor(entityPlayer.z()) + n22;
                                int n27 = MathUtil.floor(entityPlayer.N()) + j;
                                int n28 = MathUtil.floor(entityPlayer.h()) + n21;
                                int n29 = Objects.hash(n20, n27, n28);
                                if (hashMap.containsKey(n29)) {
                                    blockData3 = (BlockData)hashMap.get(n29);
                                } else {
                                    blockData3 = new BlockData(n20, n27, n28);
                                    hashMap.put(n29, blockData3);
                                }
                                if (this.O5.contains(blockData3)) continue;
                                Block block2 = world.getBlockByPos(blockData3.D(), blockData3.B(), blockData3.G());
                                if (!this.o.contains(blockData3) && BlockUtil.p(block2) || BlockUtil.C(block2) || !BlockUtil.b(block2)) {
                                    this.O5.add(blockData3);
                                    continue;
                                }
                                if ((double)(blockData3.B() + 1) > d16) continue;
                                boolean bl5 = bl8 = this.o.contains(blockData3) || !BlockUtil.u(block2) && !ClutchPlacementPathUtils.e(block2);
                                if (bl8) {
                                    this.o.add(blockData3);
                                    object3 = Vec3.create((double)blockData3.D() + 0.5, (double)blockData3.B() + 0.5, (double)blockData3.G() + 0.5);
                                    Vec3 vec34 = vec3d.n().addVector(0.0, entityPlayer.X(), 0.0);
                                    RotationAngles rotationAngles = RotationVectorMath.d(vec34, (Vec3)object3, fixedRotationController2.k(), fixedRotationController2.d());
                                    double l5 = Math.abs(MathUtil.wrapAngleTo180(rotationAngles.z() - fixedRotationController2.k()));
                                    if (Math.abs(l5) > 120.0) {
                                        l5 = Math.abs(MathUtil.wrapAngleTo180(l5 + 180.0));
                                    }
                                    this.k.putIfAbsent(blockData3, new HashSet());
                                    if (pathScores.get(blockData3) != null && !(l5 < pathScores.get(blockData3))) continue;
                                    pathScores.put(blockData3, l5);
                                    continue;
                                }
                                this.O5.add(blockData3);
                            }
                        }
                    }
                }
                BlockPlacementGraph blockPlacementGraph2 = blockPathPlanner.E().U();
                BlockInBooleanState blockInBooleanState = null;
                if (!this.OT.L().booleanValue()) {
                    boolean graphMove = this.A.M;
                    boolean graphDown = this.A.D;
                    boolean graphRight = this.A.R;
                    boolean bl10 = this.A.Y;
                    float f = bl7 ? this.O4 : this.OI;
                    blockInBooleanState = this.j(blockPathPlanner.T(), this.OI, graphMove, graphDown, graphRight, bl10);
                    if (blockInBooleanState != null) {
                        blockPathPlanner.G(blockInBooleanState.L, blockInBooleanState.v, blockInBooleanState.h, blockInBooleanState.c);
                    }
                }
                if (bl7 && fixedRotationController2 instanceof AdaptiveRotationController) {
                    ((AdaptiveRotationController)fixedRotationController2).C(Float.valueOf(this.O4));
                }
                blockPathPlanner.B();
                if (blockInBooleanState != null) {
                    blockPathPlanner.G(blockInBooleanState.b, blockInBooleanState.f, blockInBooleanState.q, blockInBooleanState.d);
                    blockPlacementGraph2.M = blockInBooleanState.b;
                    blockPlacementGraph2.D = blockInBooleanState.f;
                    blockPlacementGraph2.R = blockInBooleanState.q;
                    blockPlacementGraph2.Y = blockInBooleanState.d;
                }
                blockPathPlanner.K();
                vec3d = blockPathPlanner.f();
                arrayList.add(vec3d);
                if (this.OJ && n19 > 0 && --n19 == 0) {
                    bl7 = true;
                }
                if (entityPlayer.q() <= 0.0) {
                    n22 = MathUtil.floor(entityPlayer.N()) - 1;
                    double d18 = 2.5;
                    double d19 = 0.49;
                    double d20 = MathUtil.clamp(entityPlayer.t() * (d18 * d19), -1.0, 1.0);
                    double d21 = MathUtil.clamp(entityPlayer.T() * (d18 * d19), -1.0, 1.0);
                    object3 = new BlockData(MathUtil.floor(entityPlayer.z()), n22, MathUtil.floor(entityPlayer.h()));
                    for (BlockData n32 : this.k.keySet()) {
                        boolean d37;
                        boolean bl8 = d37 = n22 >= n32.B() || n22 == n32.B();
                        n8 = Math.abs(n32.D() - ((BlockData)object3).D());
                        int n58 = n8 + (n7 = Math.abs(n32.G() - ((BlockData)object3).G())) + (blockPlacementPathSegment3 = Math.abs(n32.B() - ((BlockData)object3).B())) - 1;
                        if (n58 > n18 || !d37 || n32.L((BlockData)object3)) continue;
                        this.k.get(n32).add((BlockData)object3);
                    }
                }
                if (blockPathPlanner.T().N() <= (double)n5) break;
            }
            ArrayList<Map.Entry<BlockData, Double>> arrayList2 = new ArrayList<Map.Entry<BlockData, Double>>(pathScores.entrySet());
            EntityPlayerSP entityPlayerSP2 = Minecraft.thePlayer();
            Vec3d vec3d2 = new Vec3d(entityPlayerSP2.z(), entityPlayerSP2.N(), entityPlayerSP2.h());
            double d22 = Double.MAX_VALUE;
            l = System.nanoTime();
            ArrayList<BlockPlacementPathSegment> arrayList3 = new ArrayList<BlockPlacementPathSegment>();
            for (Map.Entry<BlockData, Double> entry : arrayList2) {
                HashSet<BlockData> hashSet;
                ArrayList<BlockPlacementPathSegment> arrayList4 = new ArrayList<BlockPlacementPathSegment>();
                BlockData blockData4 = (BlockData)entry.getKey();
                if (blockData4 == null || (hashSet = this.k.get(blockData4)) == null || hashSet.isEmpty()) continue;
                block9: for (BlockData blockData5 : hashSet) {
                    int[] nArray;
                    BlockCoordinate blockCoordinate = new BlockCoordinate(blockData4.D(), blockData4.B(), blockData4.G());
                    object3 = new BlockCoordinate(blockData5.D(), blockData5.B(), blockData5.G());
                    int n31 = ((BlockCoordinate)object3).B() - blockCoordinate.B();
                    int blockPlacementPathSegment = ((BlockCoordinate)object3).E() - blockCoordinate.E();
                    int bl22 = ((BlockCoordinate)object3).A() - blockCoordinate.A();
                    int n9 = n31 > 0 ? 5 : (n8 = n31 < 0 ? 4 : -1);
                    int n20 = blockPlacementPathSegment > 0 ? 1 : (n7 = blockPlacementPathSegment < 0 ? 0 : -1);
                    blockPlacementPathSegment3 = bl22 > 0 ? 3 : (bl22 < 0 ? 2 : -1);
                    for (int n21 : nArray = new int[]{n9, n20, blockPlacementPathSegment3}) {
                        Block block2;
                        if (n21 == -1) continue;
                        EnumFacing enumFacing = EnumFacing.t()[n21];
                        BlockData blockData3 = blockCoordinate.O().R(enumFacing);
                        if (this.O9.Y(blockData3) || !BlockUtil.u(block2 = world.getBlockByPos(blockData3.D(), blockData3.B(), blockData3.G()))) continue;
                        arrayList4.add(new BlockPlacementPathSegment(blockCoordinate, (BlockCoordinate)object3, new ArrayList<Vec3d>()));
                        continue block9;
                    }
                }
                if (arrayList4.isEmpty()) continue;
                this.C(arrayList4, vec3d2, entityPlayerSP);
                for (int i = 0; i < Math.min(1, arrayList4.size()); ++i) {
                    arrayList3.add(arrayList4.get(i));
                }
            }
            if (arrayList3.isEmpty()) {
                return null;
            }
            this.l("Temp paths: " + arrayList3.size());
            this.C(arrayList3, vec3d2, entityPlayerSP);
            l = System.nanoTime();
            long l2 = Long.MAX_VALUE;
            int n37 = Integer.MAX_VALUE;
            long l3 = Long.MIN_VALUE;
            long l4 = Integer.MIN_VALUE;
            int n38 = 0;
            object3 = null;
            Iterator<BlockPlacementPathSegment> pathIterator = arrayList3.iterator();
            while (pathIterator.hasNext()) {
                BlockPlacementPathSegment object7 = pathIterator.next();
                if (n38 > 3) {
                    this.l("Checked 3 paths already");
                    break;
                }
                this.v(object7, Math.max(n6, 2), entityPlayerSP, world, arrayList);
                if (object7.g == null || object7.g.M.isEmpty()) continue;
                ++n38;
                long n61 = System.nanoTime();
                object7.a = n6;
                n7 = (double)object7.R.E() < entityPlayerSP.N() ? (int)Math.ceil((entityPlayerSP.N() - (double)object7.R.E()) / 0.08) : 7;
                BlockPlacementPathSegment blockPlacementPathSegment = this.q(n6 + Math.min(n7, 25), object7, world, itemStack, null, entityPlayerSP, entityPlayerSP);
                long l11 = System.nanoTime() - n61;
                if (l11 < l2) {
                    n37 = object7.w();
                    l2 = l11;
                }
                if (l11 > l3) {
                    l4 = object7.w();
                    l3 = l11;
                }
                if (blockPlacementPathSegment == null || blockPlacementPathSegment.u()) continue;
                return blockPlacementPathSegment;
            }
            if (!arrayList3.isEmpty()) {
                BlockPlacementPathSegment firstPath = arrayList3.get(0);
                if (firstPath.u()) {
                    return firstPath;
                }
            }
            return null;
        }
        boolean bl13 = Math.abs(this.Oa - entityPlayerSP.N()) > 1.0;
        while (true) {
            d += d2;
            if (!bl13 && !(d >= this.Oa) || n6 >= 20) break;
            d2 -= 0.08;
            d2 *= (double)0.98f;
            ++n6;
        }
        double d23 = entityPlayer.q();
        boolean bl14 = entityPlayer.b$src$Z$fqlxe4();
        double d24 = bl14 && d23 > 0.0 ? (double)0.6f : 1.0;
        double d25 = d24 * (double)0.91f;
        blockPathPlanner.l();
        blockPathPlanner.B();
        double d26 = entityPlayer.z() - entityPlayer.f();
        double d27 = entityPlayer.h() - entityPlayer.R();
        double d28 = entityPlayer.t();
        double d29 = entityPlayer.T();
        blockPathPlanner.U(blockPlacementGraph);
        for (int i = 0; i < n6 - 1; ++i) {
            d24 = bl14 && d23 > 0.0 ? (double)0.6f : 1.0;
            d25 = d24 * (double)0.91f;
            if (Math.abs(d28) < 0.005) {
                d28 = 0.0;
            }
            if (Math.abs(d29) < 0.005) {
                d29 = 0.0;
            }
            d26 += d28;
            d27 += d29;
            d28 *= d25;
            d29 *= d25;
            bl14 = false;
        }
        double d30 = Math.abs(d26);
        double d31 = Math.abs(d27);
        double d32 = Math.abs(d30 - d31);
        double d33 = Math.sqrt(d30 * d30 + d31 * d31);
        int n39 = (int)Math.round(d33);
        if (n39 >= 4) {
            boolean bl15;
            this.l("Clutch is not possible standing still. Forcing counter motion.");
            this.A.A = bl15 = this.OJ && gg.vape.config.ClientSettings.B(Minecraft.gameSettings().O());
            this.A.M = true;
            this.A.D = false;
            this.A.R = false;
            this.A.Y = false;
            this.OL = true;
            this.V = true;
            this.OI = MovementInputHelper.U(entityPlayerSP);
        } else {
            this.V = false;
            this.OL = false;
        }
        if (this.OL) {
            blockPathPlanner.t();
        } else {
            blockPathPlanner.l();
        }
        this.l("Estimated ticks: " + n6 + " Blocks: " + n39);
        double d34 = entityPlayer.t();
        double d35 = entityPlayer.T();
        boolean bl16 = false;
        int n40 = MathUtil.floor(entityPlayer.z());
        int n41 = MathUtil.floor(entityPlayer.N() - 0.015625);
        int n42 = MathUtil.floor(entityPlayer.h());
        int n43 = Objects.hash(n40, n41, n42);
        if (hashMap.containsKey(n43)) {
            blockData = (BlockData)hashMap.get(n43);
        } else {
            blockData = new BlockData(n40, n41, n42);
            hashMap.put(n43, blockData);
        }
        Block block = world.getBlockByPos(blockData.D(), blockData.B(), blockData.G());
        if (!BlockUtil.u(block)) {
            bl16 = true;
        }
        if (this.V) {
            object2 = Vec3.create(entityPlayer.z(), entityPlayer.N() + (double)entityPlayer.X(), entityPlayer.h());
            vec3 = ((Vec3)object2).addVector(-d34, 0.0, -d35);
            fixedRotationController = this.b(entityPlayer, (Vec3)object2, vec3, null, 1, this.OI);
            blockPathPlanner.y(fixedRotationController);
        } else {
            object2 = Vec3.create(entityPlayer.z(), entityPlayer.N() + (double)entityPlayer.X(), entityPlayer.h());
            vec3 = entityPlayer.J(1.0f);
            Vec3 vec35 = ((Vec3)object2).addVector(vec3.getX() * 5.0, vec3.getY() * 5.0, vec3.getZ() * 5.0);
            fixedRotationController = this.b(entityPlayer, (Vec3)object2, vec35, null, 1, this.OI);
            blockPathPlanner.y(fixedRotationController);
        }
        HashMap<BlockData, Double> pathScores = new HashMap<BlockData, Double>();
        int n44 = 4;
        int n45 = bl16 ? 1 : n44;
        Vec3d vec3d = blockPathPlanner.f();
        arrayList.add(vec3d);
        int n46 = entityPlayerSP.b$src$Z$fqlxe4() ? -2 : (entityPlayerSP.q() > 0.0 ? -3 : -1);
        boolean bl17 = false;
        int n47 = 0;
        int n48 = this.Oq;
        long l = System.nanoTime();
        for (int i = 0; i <= Math.max(n6, 15); ++i) {
            int n49;
            int n50;
            double d36 = entityPlayer.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY();
            ++n47;
            for (int j = 0; j >= n46; --j) {
                for (int k = 0; k < n44; ++k) {
                    int n51 = d34 >= 0.0 ? -n45 : -k;
                    int n52 = d34 >= 0.0 ? k : n45;
                    int n53 = d35 >= 0.0 ? -n45 : -k;
                    int n54 = d35 >= 0.0 ? k : n45;
                    for (n50 = n51; n50 <= n52; ++n50) {
                        for (int i2 = n53; i2 <= n54; ++i2) {
                            boolean bl18;
                            BlockData blockData7;
                            if (Math.abs(n50) != k && Math.abs(i2) != k) continue;
                            n49 = MathUtil.floor(entityPlayer.z()) + n50;
                            int n55 = MathUtil.floor(entityPlayer.N()) + j;
                            int n56 = MathUtil.floor(entityPlayer.h()) + i2;
                            int n57 = Objects.hash(n49, n55, n56);
                            if (hashMap.containsKey(n57)) {
                                blockData7 = (BlockData)hashMap.get(n57);
                            } else {
                                blockData7 = new BlockData(n49, n55, n56);
                                hashMap.put(n57, blockData7);
                            }
                            if (this.O5.contains(blockData7)) continue;
                            Block block4 = world.getBlockByPos(blockData7.D(), blockData7.B(), blockData7.G());
                            if (!this.o.contains(blockData7) && BlockUtil.p(block4) || BlockUtil.C(block4) || !BlockUtil.b(block4)) {
                                this.O5.add(blockData7);
                                continue;
                            }
                            if ((double)(blockData7.B() + 1) > d36) continue;
                            boolean bl9 = bl18 = this.o.contains(blockData7) || !BlockUtil.u(block4) && !ClutchPlacementPathUtils.e(block4);
                            if (bl18) {
                                this.o.add(blockData7);
                                object = Vec3.create((double)blockData7.D() + 0.5, (double)blockData7.B() + 0.5, (double)blockData7.G() + 0.5);
                                Vec3 vec36 = vec3d.n().addVector(0.0, entityPlayer.X(), 0.0);
                                RotationAngles blockPlacementPathSegment = RotationVectorMath.d(vec36, (Vec3)object, fixedRotationController.k(), fixedRotationController.d());
                                double l10 = Math.abs(MathUtil.wrapAngleTo180(blockPlacementPathSegment.z() - fixedRotationController.k()));
                                if (Math.abs(l10) > 120.0) {
                                    l10 = Math.abs(MathUtil.wrapAngleTo180(l10 + 180.0));
                                }
                                this.k.putIfAbsent(blockData7, new HashSet());
                                if (pathScores.get(blockData7) != null && !(l10 < pathScores.get(blockData7))) continue;
                                pathScores.put(blockData7, l10);
                                continue;
                            }
                            this.O5.add(blockData7);
                        }
                    }
                }
            }
            BlockPlacementGraph blockPlacementGraph3 = blockPathPlanner.E().U();
            BlockInBooleanState blockInBooleanState = null;
            if (!this.OT.L().booleanValue()) {
                boolean graphMove = this.A.M;
                boolean bl20 = this.A.D;
                boolean graphRight = this.A.R;
                boolean bl21 = this.A.Y;
                float f = bl17 ? this.O4 : this.OI;
                blockInBooleanState = this.j(blockPathPlanner.T(), this.OI, graphMove, bl20, graphRight, bl21);
                if (blockInBooleanState != null) {
                    blockPathPlanner.G(blockInBooleanState.L, blockInBooleanState.v, blockInBooleanState.h, blockInBooleanState.c);
                }
            }
            if (bl17 && fixedRotationController instanceof AdaptiveRotationController) {
                ((AdaptiveRotationController)fixedRotationController).C(Float.valueOf(this.O4));
            }
            blockPathPlanner.B();
            if (blockInBooleanState != null) {
                blockPathPlanner.G(blockInBooleanState.b, blockInBooleanState.f, blockInBooleanState.q, blockInBooleanState.d);
                blockPlacementGraph3.M = blockInBooleanState.b;
                blockPlacementGraph3.D = blockInBooleanState.f;
                blockPlacementGraph3.R = blockInBooleanState.q;
                blockPlacementGraph3.Y = blockInBooleanState.d;
            }
            blockPathPlanner.K();
            vec3d = blockPathPlanner.f();
            arrayList.add(vec3d);
            if (this.OJ && n48 > 0 && --n48 == 0) {
                bl17 = true;
            }
            if (entityPlayer.q() <= 0.0) {
                n50 = MathUtil.floor(entityPlayer.N()) - 1;
                double d38 = 3.0;
                double d39 = 0.49;
                double d40 = MathUtil.clamp(entityPlayer.t() * (d38 * d39), -1.0, 1.0);
                double d41 = MathUtil.clamp(entityPlayer.T() * (d38 * d39), -1.0, 1.0);
                object = new BlockData(MathUtil.floor(entityPlayer.z()), n50, MathUtil.floor(entityPlayer.h()));
                for (BlockData blockData4 : this.k.keySet()) {
                    boolean bl10;
                    boolean bl11 = bl10 = n50 == blockData4.B();
                    n3 = Math.abs(blockData4.D() - ((BlockData)object).D());
                    int n22 = n3 + (n2 = Math.abs(blockData4.G() - ((BlockData)object).G())) + (n = Math.abs(blockData4.B() - ((BlockData)object).B())) - 1;
                    if (n22 > n47 || !bl10 || blockData4.L((BlockData)object)) continue;
                    this.k.get(blockData4).add((BlockData)object);
                }
            }
            if (blockPathPlanner.T().N() <= (double)n5) break;
        }
        ArrayList<Map.Entry<BlockData, Double>> arrayList5 = new ArrayList<Map.Entry<BlockData, Double>>(pathScores.entrySet());
        EntityPlayerSP entityPlayerSP3 = Minecraft.thePlayer();
        Vec3d vec3d3 = new Vec3d(entityPlayerSP3.z(), entityPlayerSP3.N(), entityPlayerSP3.h());
        double d42 = Double.MAX_VALUE;
        l = System.nanoTime();
        ArrayList<BlockPlacementPathSegment> arrayList6 = new ArrayList<BlockPlacementPathSegment>();
        for (Map.Entry<BlockData, Double> entry : arrayList5) {
            HashSet<BlockData> hashSet;
            ArrayList<BlockPlacementPathSegment> arrayList7 = new ArrayList<BlockPlacementPathSegment>();
            BlockData blockData8 = (BlockData)entry.getKey();
            if (blockData8 == null || (hashSet = this.k.get(blockData8)) == null || hashSet.isEmpty()) continue;
            block22: for (BlockData blockData9 : hashSet) {
                int[] nArray;
                BlockCoordinate blockCoordinate = new BlockCoordinate(blockData8.D(), blockData8.B(), blockData8.G());
                object = new BlockCoordinate(blockData9.D(), blockData9.B(), blockData9.G());
                int n59 = ((BlockCoordinate)object).B() - blockCoordinate.B();
                int n23 = ((BlockCoordinate)object).E() - blockCoordinate.E();
                int n24 = ((BlockCoordinate)object).A() - blockCoordinate.A();
                int n25 = n59 > 0 ? 5 : (n3 = n59 < 0 ? 4 : -1);
                int n26 = n23 > 0 ? 1 : (n2 = n23 < 0 ? 0 : -1);
                n = n24 > 0 ? 3 : (n24 < 0 ? 2 : -1);
                for (int n27 : nArray = new int[]{n25, n26, n}) {
                    Block block3;
                    if (n27 == -1) continue;
                    EnumFacing enumFacing = EnumFacing.t()[n27];
                    BlockData blockData5 = blockCoordinate.O().R(enumFacing);
                    if (this.O9.Y(blockData5) || !BlockUtil.u(block3 = world.getBlockByPos(blockData5.D(), blockData5.B(), blockData5.G()))) continue;
                    arrayList7.add(new BlockPlacementPathSegment(blockCoordinate, (BlockCoordinate)object, new ArrayList<Vec3d>()));
                    continue block22;
                }
            }
            if (arrayList7.isEmpty()) continue;
            this.C(arrayList7, vec3d3, entityPlayerSP);
            for (int i = 0; i < Math.min(1, arrayList7.size()); ++i) {
                arrayList6.add(arrayList7.get(i));
            }
        }
        if (arrayList6.isEmpty()) {
            return null;
        }
        this.l("Temp paths: " + arrayList6.size());
        this.C(arrayList6, vec3d3, entityPlayerSP);
        l = System.nanoTime();
        long l7 = Long.MAX_VALUE;
        int n65 = Integer.MAX_VALUE;
        long l8 = Long.MIN_VALUE;
        long l9 = Integer.MIN_VALUE;
        int n66 = 0;
        object = null;
        Iterator<BlockPlacementPathSegment> pathIterator = arrayList6.iterator();
        while (pathIterator.hasNext()) {
            BlockPlacementPathSegment blockPlacementPathSegment = pathIterator.next();
            if (n66 > 3) {
                this.l("Checked 3 paths already");
                break;
            }
            this.v(blockPlacementPathSegment, Math.max(n6, 2), entityPlayerSP, world, arrayList);
            if (blockPlacementPathSegment.g == null || blockPlacementPathSegment.g.M.isEmpty()) continue;
            ++n66;
            long l2 = System.nanoTime();
            blockPlacementPathSegment.a = n6;
            n2 = (double)blockPlacementPathSegment.R.E() < entityPlayerSP.N() ? (int)Math.ceil((entityPlayerSP.N() - (double)blockPlacementPathSegment.R.E()) / 0.08) : 7;
            BlockPlacementPathSegment blockPlacementPathSegment2 = this.q(n6 + Math.min(n2, 25), blockPlacementPathSegment, world, itemStack, null, entityPlayerSP, entityPlayerSP);
            long l3 = System.nanoTime() - l2;
            if (l3 < l7) {
                n65 = blockPlacementPathSegment.w();
                l7 = l3;
            }
            if (l3 > l8) {
                l9 = blockPlacementPathSegment.w();
                l8 = l3;
            }
            if (blockPlacementPathSegment2 == null || blockPlacementPathSegment2.u()) continue;
            return blockPlacementPathSegment2;
        }
        if (!arrayList6.isEmpty()) {
            BlockPlacementPathSegment firstPath = arrayList6.get(0);
            if (firstPath.u()) {
                return firstPath;
            }
        }
        return null;
    }

    @Override
    public void onEnable() {
        ClientSettings.g(ActiveModuleStackFrame.class).c(this);
    }

    private void J(EntityPlayerSP entityPlayerSP) {
        this.J = null;
        this.t = null;
        this.OS = (int)Math.round(this.OW.B());
        if (this.U != null) {
            this.f(entityPlayerSP);
        }
    }

    private double lambda$sortClutchPaths$0(Vec3d vec3d, EntityPlayerSP entityPlayerSP, BlockPlacementPathSegment blockPlacementPathSegment) {
        BlockData blockData = blockPlacementPathSegment.R.O();
        BlockData blockData2 = blockPlacementPathSegment.t.O();
        double d = 0.0;
        int n = 0;
        int n2 = Math.abs(blockData2.D() - blockData.D());
        int n3 = Math.abs(blockData2.G() - blockData.G());
        n += Math.abs(n2);
        d += (double)((n += Math.abs(n3)) * 100);
        if (blockData.B() > blockData2.B()) {
            d -= (double)((blockData.B() - blockData2.B()) * 200);
        }
        if (this.Y) {
            d += Math.sqrt(Math.pow((double)blockData2.D() + 0.5 - vec3d.Y(), 2.0) + Math.pow((double)blockData2.G() + 0.5 - vec3d.o(), 2.0)) * 1000.0;
        }
        EntityPlayerSP entityPlayerSP2 = Minecraft.thePlayer();
        double d2 = RotationUtil.N(entityPlayerSP.z(), entityPlayerSP.h(), entityPlayerSP.J(), (double)blockData2.D() + 0.5, (double)blockData2.G() + 0.5);
        return d += Math.abs((double)(blockData2.B() + 1) - this.Oa) * 200.0;
    }

    private void h$src$V$tw0q16() {
        if (this.OL) {
            this.K = true;
            this.OP = false;
            this.Oy = false;
            this.v = false;
        } else {
            this.K = this.A.M;
            this.OP = this.A.D;
            this.Oy = this.A.R;
            this.v = this.A.Y;
        }
    }

    private void Z(AxisAlignedBB axisAlignedBB) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
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
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    private PlacementTarget t(BlockPlacementPathSegment blockPlacementPathSegment, EntityPlayer entityPlayer, World world, @Nullable BlockState blockState) {
        PlacementTarget placementTarget = null;
        BlockPlacementPathSegmentState blockPlacementPathSegmentState = blockPlacementPathSegment.g;
        Vector<PlacementTarget> vector = blockPlacementPathSegmentState.M;
        while (!vector.isEmpty()) {
            PlacementTarget placementTarget2 = vector.firstElement();
            if (placementTarget2 == null) continue;
            BlockData blockData = placementTarget2.k;
            Block block = world.getBlockByPos(blockData.D(), blockData.B(), blockData.G());
            if (blockState != null && BlockUtil.p(block) && !BlockUtil.p(blockState.getBlock())) {
                block = blockState.getBlock();
            }
            if (BlockUtil.u(block) && (placementTarget2.M || BlockUtil.J(block))) {
                blockPlacementPathSegment.I("Block to click on was removed");
                return null;
            }
            BlockData blockData2 = placementTarget2.s();
            Block block2 = world.getBlockByPos(blockData2.D(), blockData2.B(), blockData2.G());
            if (BlockUtil.u(block2)) {
                if (ClutchPlacementPathUtils.V(world, entityPlayer, blockData2)) {
                    placementTarget = placementTarget2;
                    break;
                }
                blockPlacementPathSegment.I("Entity blocking placement");
                break;
            }
            blockPlacementPathSegment.V = null;
            vector.removeElementAt(0);
        }
        return placementTarget;
    }

    private void J$src$V$tfiw8c() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (this.F) {
            this.N();
            if (Minecraft.currentScreen().isNull()) {
                MovementInputHelper.D(false);
            }
            this.F = false;
        }
        if (this.J != null) {
            if (this.Oq <= 0) {
                this.OJ = false;
                this.V = false;
                this.l("\nNO LONGER TAKING KNOCKBACK\n");
            }
            if (this.V && !this.OJ && this.Or.y() > 0) {
                this.v = false;
                this.Oy = false;
                this.OP = false;
                this.K = false;
                this.p();
                this.V = false;
                this.Op = (int)Math.round(this.Or.B());
            } else {
                MovementInputHelper.D(false);
            }
            this.OS = (int)Math.round(this.OW.B());
            this.Oh = (int)Math.round(this.s.B());
        }
        if (this.J != null) {
            this.OB.reset();
        }
        this.J = null;
        if (this.b) {
            this.b = false;
            SharedModuleControlClaims.h.Q(this);
        }
        this.t = null;
        this.k.clear();
        this.Ou.clear();
        O.clear();
        this.C = null;
        this.Of = null;
        if (RotationManager.b.w() == null || RotationManager.b.w() != this.U || this.U != null && !this.U.v() && this.U.V$src$Z$lb4tvc()) {
            this.U = null;
            this.a.X(this);
            if (this.O0) {
                this.O0 = false;
                super.s(false, true);
            }
        }
        this.V = false;
        this.OL = false;
        this.OG.T(this);
    }

    private BlockPlacementPathSegment z(World world, EntityPlayerSP entityPlayerSP, ItemStack itemStack) {
        int n;
        float f;
        this.O5.clear();
        this.o.clear();
        this.k.clear();
        this.Ou.clear();
        GameSettings gameSettings = Minecraft.gameSettings();
        boolean bl = gameSettings.Y().isKeyDown();
        boolean bl2 = gameSettings.s().isKeyDown();
        boolean bl3 = gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg().isKeyDown();
        boolean bl4 = gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3().isKeyDown();
        this.OI = f = this.OR != -999.0 ? (float)this.OR : (FreeLookHudModule.z() ? FreeLookHudModule.L$src$F$1jnmc2m() : entityPlayerSP.J());
        this.O4 = f;
        this.OL = false;
        this.V = false;
        this.Y = !this.OB.hasTimeElapsed(250L);
        this.L = new BlockPathPlanner(entityPlayerSP, entityPlayerSP, world, this.A);
        this.OO = new BlockPathPlanner(entityPlayerSP, entityPlayerSP, world, this.A);
        BlockPathPlanner blockPathPlanner = new BlockPathPlanner(entityPlayerSP, entityPlayerSP, world, this.A);
        boolean bl5 = false;
        long l = System.nanoTime();
        ItemStack itemStack2 = itemStack;
        BlockPathPlanner blockPathPlanner2 = blockPathPlanner;
        World world2 = world;
        BlockIn blockIn = this;
        BlockPlacementPathSegment blockPlacementPathSegment = blockIn.D(world2, blockPathPlanner2, itemStack2);
        long l2 = System.nanoTime();
        if (blockPlacementPathSegment != null && !blockPlacementPathSegment.u() && this.Oj.L().booleanValue() && (n = blockPlacementPathSegment.w()) > ((Double)this.Oi.K()).intValue()) {
            blockPlacementPathSegment.I("Requires " + n + " blocks (max: " + ((Double)this.Oi.K()).intValue() + ")");
        }
        return blockPlacementPathSegment;
    }

    private boolean F$src$Z$tdbpyc() {
        boolean bl = false;
        if (this.t != null) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            AxisAlignedBB axisAlignedBB = BlockUtil.F(entityPlayerSP.getWorld(), this.t.s());
            AxisAlignedBB axisAlignedBB2 = entityPlayerSP.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu();
            if (axisAlignedBB2.intersects(axisAlignedBB)) {
                return bl;
            }
            RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
            if (rayTraceResult.isBlockHit()) {
                boolean bl2;
                if (ForgeVersion.MC_1_7_10.Y()) {
                    bl2 = rayTraceResult.getBlockPos().equals(BlockPos.d(this.t.k));
                } else {
                    boolean bl3 = bl2 = rayTraceResult.g() == this.t.k.D() && rayTraceResult.T() == this.t.k.B() && rayTraceResult.a$src$I$8nuo9d() == this.t.k.G();
                }
                if (bl2) {
                    boolean bl4;
                    EnumFacing enumFacing = this.t.M ? this.t.G : null;
                    boolean bl5 = bl4 = enumFacing == null;
                    if (enumFacing != null && enumFacing.equals(rayTraceResult.getSideHit())) {
                        bl4 = true;
                    }
                    if (bl4) {
                        bl = true;
                    }
                } else {
                    BlockPos blockPos = rayTraceResult.getBlockPos();
                    BlockData blockData = this.t.s();
                    if (blockData.y(blockPos.offset(rayTraceResult.getSideHit()))) {
                        bl = true;
                    }
                }
            }
        }
        return bl;
    }

    private void C$src$V$tboc2t() {
    }

    private void l(AxisAlignedBB axisAlignedBB) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMaxX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMinZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMinY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMaxZ());
        GL11.glVertex3d((double)axisAlignedBB.getMinX(), (double)axisAlignedBB.getMaxY(), (double)axisAlignedBB.getMinZ());
        GL11.glEnd();
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    private void N() {
        this.O_ = null;
        this.Os = null;
        this.O3 = null;
        this.I = null;
    }

    private void N(AxisAlignedBB axisAlignedBB, Color color, Color color2, int n) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
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
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    private void l(String string) {
    }

    private ItemStack p(EntityPlayerSP entityPlayerSP) {
        InventoryPlayer inventoryPlayer = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        if (inventoryPlayer.isNull() || entityPlayerSP.isNull()) {
            return null;
        }
        if (this.Ox.L().booleanValue()) {
            if (entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt().isNull() || entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt().getItem().isNull()) {
                return null;
            }
            if (!this.C(entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt())) {
                return null;
            }
            return entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt();
        }
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = inventoryPlayer.c(i);
            if (itemStack.isNull() || !itemStack.getItem().isInstance(MappedClasses.Vw)) continue;
            if (this.y(itemStack)) {
                return itemStack;
            }
            for (String string : this.Z) {
                if (!itemStack.x().contains(string)) continue;
                return itemStack;
            }
        }
        return null;
    }

    private BlockCoordinate U(int n, EntityPlayerSP entityPlayerSP) {
        return this.B(n, entityPlayerSP, entityPlayerSP, null);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        GuiScreen guiScreen = eventPreTick.getCurrentScreen();
        GameSettings gameSettings = eventPreTick.getGameSettings();
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        WorldClient worldClient = eventPreTick.getWorld();
        if (entityPlayerSP.isNull() || worldClient.isNull()) {
            this.J$src$V$tfiw8c();
            return;
        }
        if (this.Ok) {
            this.Z("Server rejected block placement!", true);
            this.J(entityPlayerSP);
            this.Ok = false;
        }
        this.j(entityPlayerSP, gameSettings, guiScreen);
        double d = -0.0784000015258789;
        if (entityPlayerSP.b$src$Z$fqlxe4() && entityPlayerSP.q() == d) {
            this.Oa = entityPlayerSP.N();
        }
        if (Minecraft.currentScreen().getObject() == null) {
            KeyboardCodeUtil.v();
        }
        boolean bl = gg.vape.config.ClientSettings.B(Minecraft.gameSettings().Y());
        this.A = new BlockPlacementGraph(entityPlayerSP);
        if (this.F) {
            this.A.M = this.O_;
            this.A.D = this.Os;
            this.A.R = this.O3;
            this.A.Y = this.I;
            this.N();
            this.F = false;
            if (guiScreen.isNull()) {
                MovementInputHelper.D(false);
            }
        }
        this.A.M = bl;
        boolean bl2 = entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isFlying();
        if (this.O0 || this.OG.s() || this.a.e(this) || Minecraft.currentScreen().isNotNull()) {
            this.J$src$V$tfiw8c();
            return;
        }
        ItemStack itemStack = this.p(entityPlayerSP);
        if (bl2 || entityPlayerSP.S$src$Z$151gttj() || entityPlayerSP.f$src$Z$fst3rk() || itemStack == null) {
            this.J$src$V$tfiw8c();
            return;
        }
        if (!(this.D.L().booleanValue() || this.Od.L().booleanValue() || this.Om.L().booleanValue())) {
            this.J$src$V$tfiw8c();
            return;
        }
        boolean bl3 = gg.vape.config.ClientSettings.B(eventPreTick.getGameSettings().O());
        if (bl3) {
            if (this.J != null) {
                if (entityPlayerSP.b$src$Z$fqlxe4()) {
                    BlockPathPlanner blockPathPlanner = new BlockPathPlanner(entityPlayerSP, entityPlayerSP, worldClient, this.A);
                    boolean bl4 = this.q(entityPlayerSP, entityPlayerSP, worldClient, blockPathPlanner, this.J);
                    if (++this.c >= 5 || !bl4) {
                        this.J$src$V$tfiw8c();
                    }
                } else {
                    this.c = 0;
                }
            }
            if (this.Oz.L().booleanValue()) {
                if (this.Oe != bl3) {
                    // empty if block
                }
                if (!this.Oe && !this.OD.hasTimeElapsed(500L)) {
                    this.OY = true;
                }
                this.Oe = bl3;
            } else {
                this.Oe = false;
                this.OY = false;
            }
            if (this.J != null) {
                if (entityPlayerSP.N() < (double)this.J.t.E()) {
                    this.J$src$V$tfiw8c();
                } else {
                    double d2;
                    double d3 = RotationUtil.V(entityPlayerSP.z(), entityPlayerSP.h(), (double)this.J.R.B() + 0.5, (double)this.J.R.A() + 0.5);
                    if (d3 > (d2 = RotationUtil.V(entityPlayerSP.f(), entityPlayerSP.R(), (double)this.J.R.B() + 0.5, (double)this.J.R.A() + 0.5)) && d3 > 1.2 && !entityPlayerSP.b$src$Z$fqlxe4()) {
                        this.J$src$V$tfiw8c();
                    }
                }
            }
            if (this.M$src$Z$th6a3v() && this.J == null) {
                boolean bl5;
                boolean bl6 = bl5 = !entityPlayerSP.b$src$Z$fqlxe4() || entityPlayerSP.q() >= 0.0;
                if (bl5 && !entityPlayerSP.S$src$Z$151gttj() && !entityPlayerSP.f$src$Z$fst3rk() && !entityPlayerSP.h$src$Z$ftwoya() && this.OF.hasTimeElapsed(((Double)this.Oo.K()).longValue())) {
                    BlockCoordinate blockCoordinate = this.U(50, entityPlayerSP);
                    boolean bl7 = false;
                    boolean bl8 = false;
                    boolean bl9 = false;
                    if (blockCoordinate != null) {
                        if (this.Od.L().booleanValue() && entityPlayerSP.N() - (double)blockCoordinate.E() - 3.0 > (double)entityPlayerSP.w$src$F$15l9epb()) {
                            bl8 = true;
                        }
                        if (this.Om.L().booleanValue() && entityPlayerSP.N() - (double)(blockCoordinate.E() + 1) >= (Double)this.On.K()) {
                            bl9 = true;
                        }
                    } else {
                        bl7 = this.D.L();
                    }
                    if (bl7 || bl8 || bl9) {
                        if (!this.a.U(this) && !this.a.h(this, this.OT.L())) {
                            return;
                        }
                        long l = System.nanoTime();
                        BlockPlacementPathSegment clutchPath = this.z(worldClient, entityPlayerSP, itemStack);
                        long l2 = System.nanoTime();
                        if (clutchPath != null && !clutchPath.u()) {
                            this.l("\n\n\nFound Clutch Path (" + (double)(l2 - l) / 1000000.0 + "ms)\n\n\n");
                            this.h$src$V$tw0q16();
                            this.n$src$V$tzbhlc();
                            this.c = 0;
                            this.O7 = 0;
                            this.Oh = 0;
                            this.Op = 0;
                            this.OS = 0;
                            this.J = clutchPath;
                            this.Oe = bl3;
                            gameSettings.F().e();
                            if (!this.OT.L().booleanValue()) {
                                if (this.OR == -999.0) {
                                    this.OR = FreeLookHudModule.z() ? (double)FreeLookHudModule.L$src$F$1jnmc2m() : (double)entityPlayerSP.J();
                                    this.OH = entityPlayerSP.V();
                                }
                            } else {
                                this.OR = -999.0;
                            }
                            this.Ob.clear();
                            this.X$src$V$tn80je();
                        } else {
                            String string = null;
                            if (clutchPath == null) {
                                if (this.k.size() > 0) {
                                    string = "Could not find a clutch path!";
                                }
                            } else {
                                string = clutchPath.p;
                                if (string == null) {
                                    string = "Could not find a clutch path!";
                                }
                            }
                            if (string != null && !string.isEmpty()) {
                                this.x(string);
                            }
                            this.V$src$V$tm4fco();
                            this.J = null;
                            this.OF.reset();
                        }
                    }
                }
            } else if (this.J != null) {
                this.W();
            }
            this.G();
            this.C = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
            if (this.J != null) {
                if (!this.b) {
                    this.b = true;
                    SharedModuleControlClaims.h.S(this);
                }
                this.t = null;
                if (this.J.g != null) {
                    this.t = this.Q(this.J, entityPlayerSP, worldClient);
                    if (this.t == null && this.J.g != null) {
                        this.J.I("Failed to find a place target");
                    }
                } else if (!this.V || this.OJ) {
                    this.J$src$V$tfiw8c();
                    return;
                }
            } else {
                this.J$src$V$tfiw8c();
                return;
            }
            if (this.t != null && this.W(entityPlayerSP)) {
                float f;
                float f2;
                this.OO = new BlockPathPlanner(entityPlayerSP, entityPlayerSP, worldClient, this.A);
                if (RotationManager.b.w() == null) {
                    this.U = null;
                }
                if (this.U == null) {
                    if (RotationManager.b.u()) {
                        AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)RotationManager.b.w();
                        f2 = adaptiveRotationController.J();
                        f = adaptiveRotationController.X();
                    } else {
                        f2 = entityPlayerSP.J();
                        f = entityPlayerSP.V();
                    }
                } else if (this.U instanceof AdaptiveRotationController) {
                    f2 = ((AdaptiveRotationController)this.U).J();
                    f = ((AdaptiveRotationController)this.U).X();
                } else {
                    f2 = this.U.k();
                    f = this.U.d();
                }
                boolean bl10 = this.F$src$Z$tdbpyc();
                if (bl10) {
                    KeyBinding keyBinding = Minecraft.gameSettings().F();
                    if (keyBinding.u() || keyBinding.isPressed()) {
                        KeyBinding.setKeyBindState(keyBinding, false);
                    }
                    KeyBinding keyBinding2 = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                    KeyBinding.setKeyBindState(keyBinding2, true);
                    KeyBinding.onTick(keyBinding2);
                    KeyBinding.setKeyBindState(keyBinding2, false);
                    if (this.J.g != null) {
                        this.t = null;
                        BlockPlacementPathSegmentState blockPlacementPathSegmentState = this.J.g;
                        Vector<PlacementTarget> vector = blockPlacementPathSegmentState.M;
                        if (!vector.isEmpty()) {
                            vector.removeElementAt(0);
                        }
                        while (!vector.isEmpty()) {
                            PlacementTarget placementTarget = vector.firstElement();
                            if (placementTarget == null) continue;
                            BlockData blockData = placementTarget.s();
                            Block block = worldClient.getBlockByPos(blockData.D(), blockData.B(), blockData.G());
                            if (BlockUtil.u(block)) {
                                if (ClutchPlacementPathUtils.V(worldClient, entityPlayerSP, blockData)) {
                                    this.t = placementTarget;
                                    break;
                                }
                                this.J.I("Entity blocking placement");
                                break;
                            }
                            this.J.V = null;
                            vector.removeElementAt(0);
                        }
                        if (this.t == null && this.V && this.U != null) {
                            float f3 = this.OJ ? this.O4 : this.OI;
                            float f4 = Math.abs(MathUtil.wrapAngleTo180(f3 - f2));
                            float f5 = Math.abs(f4) / 1.8f / 3.0f;
                            this.U.Y(f5);
                            this.U.g(f3, this.U.s$src$F$15o72go());
                        }
                    }
                }
            }
            return;
        }
        if (this.J != null) {
            if (entityPlayerSP.b$src$Z$fqlxe4()) {
                BlockPathPlanner blockPathPlanner = new BlockPathPlanner(entityPlayerSP, entityPlayerSP, worldClient, this.A);
                boolean bl11 = this.q(entityPlayerSP, entityPlayerSP, worldClient, blockPathPlanner, this.J);
                if (++this.c >= 5 || !bl11) {
                    this.J$src$V$tfiw8c();
                }
            } else {
                this.c = 0;
            }
        }
        if (this.Oz.L().booleanValue()) {
            if (this.Oe != bl3) {
                if (this.OY) {
                    this.OY = false;
                } else {
                    this.OD.reset();
                }
            }
            if (!this.Oe) {
                // empty if block
            }
            this.Oe = bl3;
        } else {
            this.Oe = false;
            this.OY = false;
        }
        if (this.J != null) {
            if (entityPlayerSP.N() < (double)this.J.t.E()) {
                this.J$src$V$tfiw8c();
            } else {
                double d4;
                double d5 = RotationUtil.V(entityPlayerSP.z(), entityPlayerSP.h(), (double)this.J.R.B() + 0.5, (double)this.J.R.A() + 0.5);
                if (d5 > (d4 = RotationUtil.V(entityPlayerSP.f(), entityPlayerSP.R(), (double)this.J.R.B() + 0.5, (double)this.J.R.A() + 0.5)) && d5 > 1.2 && !entityPlayerSP.b$src$Z$fqlxe4()) {
                    this.J$src$V$tfiw8c();
                }
            }
        }
        if (this.M$src$Z$th6a3v() && this.J == null) {
            boolean bl12;
            boolean bl13 = bl12 = !entityPlayerSP.b$src$Z$fqlxe4() || entityPlayerSP.q() >= 0.0;
            if (bl12 && !entityPlayerSP.S$src$Z$151gttj() && !entityPlayerSP.f$src$Z$fst3rk() && !entityPlayerSP.h$src$Z$ftwoya() && this.OF.hasTimeElapsed(((Double)this.Oo.K()).longValue())) {
                BlockCoordinate blockCoordinate = this.U(50, entityPlayerSP);
                boolean bl14 = false;
                boolean bl15 = false;
                boolean bl16 = false;
                if (blockCoordinate != null) {
                    if (this.Od.L().booleanValue() && entityPlayerSP.N() - (double)blockCoordinate.E() - 3.0 > (double)entityPlayerSP.w$src$F$15l9epb()) {
                        bl15 = true;
                    }
                    if (this.Om.L().booleanValue() && entityPlayerSP.N() - (double)(blockCoordinate.E() + 1) >= (Double)this.On.K()) {
                        bl16 = true;
                    }
                } else {
                    bl14 = this.D.L();
                }
                if (bl14 || bl15 || bl16) {
                    if (!this.a.U(this) && !this.a.h(this, this.OT.L())) {
                        return;
                    }
                    long l = System.nanoTime();
                    BlockPlacementPathSegment clutchPath = this.z(worldClient, entityPlayerSP, itemStack);
                    long l3 = System.nanoTime();
                    if (clutchPath != null && !clutchPath.u()) {
                        this.l("\n\n\nFound Clutch Path (" + (double)(l3 - l) / 1000000.0 + "ms)\n\n\n");
                        this.h$src$V$tw0q16();
                        this.n$src$V$tzbhlc();
                        this.c = 0;
                        this.O7 = 0;
                        this.Oh = 0;
                        this.Op = 0;
                        this.OS = 0;
                        this.J = clutchPath;
                        this.Oe = bl3;
                        gameSettings.F().e();
                        if (!this.OT.L().booleanValue()) {
                            if (this.OR == -999.0) {
                                this.OR = FreeLookHudModule.z() ? (double)FreeLookHudModule.L$src$F$1jnmc2m() : (double)entityPlayerSP.J();
                                this.OH = entityPlayerSP.V();
                            }
                        } else {
                            this.OR = -999.0;
                        }
                        this.Ob.clear();
                        this.X$src$V$tn80je();
                    } else {
                        String string = null;
                        if (clutchPath == null) {
                            if (this.k.size() > 0) {
                                string = "Could not find a clutch path!";
                            }
                        } else {
                            string = clutchPath.p;
                            if (string == null) {
                                string = "Could not find a clutch path!";
                            }
                        }
                        if (string != null && !string.isEmpty()) {
                            this.x(string);
                        }
                        this.V$src$V$tm4fco();
                        this.J = null;
                        this.OF.reset();
                    }
                }
            }
        } else if (this.J != null) {
            this.W();
        }
        this.G();
        this.C = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (this.J != null) {
            if (!this.b) {
                this.b = true;
                SharedModuleControlClaims.h.S(this);
            }
            this.t = null;
            if (this.J.g != null) {
                this.t = this.Q(this.J, entityPlayerSP, worldClient);
                if (this.t == null && this.J.g != null) {
                    this.J.I("Failed to find a place target");
                }
            } else if (!this.V || this.OJ) {
                this.J$src$V$tfiw8c();
                return;
            }
        } else {
            this.J$src$V$tfiw8c();
            return;
        }
        if (this.t != null && this.W(entityPlayerSP)) {
            float f;
            float f6;
            this.OO = new BlockPathPlanner(entityPlayerSP, entityPlayerSP, worldClient, this.A);
            if (RotationManager.b.w() == null) {
                this.U = null;
            }
            if (this.U == null) {
                if (RotationManager.b.u()) {
                    AdaptiveRotationController adaptiveRotationController = (AdaptiveRotationController)RotationManager.b.w();
                    f6 = adaptiveRotationController.J();
                    f = adaptiveRotationController.X();
                } else {
                    f6 = entityPlayerSP.J();
                    f = entityPlayerSP.V();
                }
            } else if (this.U instanceof AdaptiveRotationController) {
                f6 = ((AdaptiveRotationController)this.U).J();
                f = ((AdaptiveRotationController)this.U).X();
            } else {
                f6 = this.U.k();
                f = this.U.d();
            }
            boolean bl17 = this.F$src$Z$tdbpyc();
            if (bl17) {
                KeyBinding keyBinding = Minecraft.gameSettings().F();
                if (keyBinding.u() || keyBinding.isPressed()) {
                    KeyBinding.setKeyBindState(keyBinding, false);
                }
                KeyBinding keyBinding3 = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                KeyBinding.setKeyBindState(keyBinding3, true);
                KeyBinding.onTick(keyBinding3);
                KeyBinding.setKeyBindState(keyBinding3, false);
                if (this.J.g != null) {
                    this.t = null;
                    BlockPlacementPathSegmentState blockPlacementPathSegmentState = this.J.g;
                    Vector<PlacementTarget> vector = blockPlacementPathSegmentState.M;
                    if (!vector.isEmpty()) {
                        vector.removeElementAt(0);
                    }
                    while (!vector.isEmpty()) {
                        PlacementTarget placementTarget = vector.firstElement();
                        if (placementTarget == null) continue;
                        BlockData blockData = placementTarget.s();
                        Block block = worldClient.getBlockByPos(blockData.D(), blockData.B(), blockData.G());
                        if (BlockUtil.u(block)) {
                            if (ClutchPlacementPathUtils.V(worldClient, entityPlayerSP, blockData)) {
                                this.t = placementTarget;
                                break;
                            }
                            this.J.I("Entity blocking placement");
                            break;
                        }
                        this.J.V = null;
                        vector.removeElementAt(0);
                    }
                    if (this.t == null && this.V && this.U != null) {
                        float f7 = this.OJ ? this.O4 : this.OI;
                        float f8 = Math.abs(MathUtil.wrapAngleTo180(f7 - f6));
                        float f9 = Math.abs(f8) / 1.8f / 3.0f;
                        this.U.Y(f9);
                        this.U.g(f7, this.U.s$src$F$15o72go());
                    }
                }
            }
        }
    }

    private void W() {
    }

    private void k(String string, String string2) {
        try {
            File file = new File(string);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(string2.getBytes());
            fileOutputStream.close();
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    @Override
    public void s(boolean bl, boolean bl2) {
        if (!bl && this.U instanceof AdaptiveRotationController) {
            this.O0 = !this.O0;
        } else {
            super.s(bl, bl2);
            this.O0 = false;
            if (!bl) {
                this.f(Minecraft.thePlayer());
                this.OL = false;
                this.V = false;
                this.Oh = 0;
                this.Op = 0;
                this.OS = 0;
                this.Ou.clear();
                this.p.clear();
                O.clear();
                this.J$src$V$tfiw8c();
            } else {
                this.Ou.clear();
                this.p.clear();
                O.clear();
            }
        }
    }

    private void m(BlockCoordinate blockCoordinate, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glDisable((int)2929);
        double d = Minecraft.D().getRenderPosX();
        double d2 = Minecraft.D().getRenderPosY();
        double d3 = Minecraft.D().getRenderPosZ();
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(blockCoordinate.B(), blockCoordinate.E(), blockCoordinate.A(), blockCoordinate.B() + 1, blockCoordinate.E() + 1, blockCoordinate.A() + 1).A(-d, -d2, -d3);
        Color color2 = Color.yellow;
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.45f);
        this.l(axisAlignedBB);
        GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)0.5f);
        this.Z(axisAlignedBB);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    private FixedRotationController b(EntityPlayer entityPlayer, Vec3 vec3, Vec3 vec32, FixedRotationController fixedRotationController, int n, float f) {
        float f2;
        float f3;
        boolean bl;
        float f4;
        float f5;
        Object object;
        FixedRotationController fixedRotationController2 = fixedRotationController;
        if (fixedRotationController == null && !entityPlayer.isInstance(MappedClasses.z5) && RotationManager.b.u()) {
            object = (AdaptiveRotationController)RotationManager.b.w();
            f5 = ((AdaptiveRotationController)object).J();
            f4 = ((AdaptiveRotationController)object).X();
        } else if (fixedRotationController == null) {
            f5 = entityPlayer.J();
            f4 = entityPlayer.V();
        } else {
            f5 = fixedRotationController.k();
            f4 = fixedRotationController.d();
        }
        object = RotationVectorMath.d(vec3, vec32, f5, f4);
        if (fixedRotationController == null) {
            fixedRotationController2 = this.OT.L() != false ? new AdaptiveRotationController(entityPlayer) : new EntityFixedRotationController(this, (RotationAngles)object, entityPlayer);
        }
        fixedRotationController2.b((RotationAngles)object);
        if (fixedRotationController2 instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)fixedRotationController2).C(Float.valueOf(f));
            ((AdaptiveRotationController)fixedRotationController2).b(false);
        }
        if (bl = fixedRotationController2 instanceof AdaptiveRotationController) {
            f3 = ((AdaptiveRotationController)fixedRotationController2).J();
            f2 = ((AdaptiveRotationController)fixedRotationController2).X();
        } else {
            f3 = entityPlayer.J();
            f2 = entityPlayer.V();
        }
        float f6 = (float)vec3.distanceTo(vec32);
        float f7 = Math.abs(MathUtil.wrapAngleTo180(((RotationAngles)object).z() - f3));
        float f8 = Math.abs(((RotationAngles)object).N() - f2);
        float f9 = Math.abs(f7) + Math.abs(f8);
        float f10 = f9 / 1.8f / (float)Math.max(n, 1);
        float f11 = 15.0f + 85.0f * (((Double)this.O6.K()).floatValue() / 10.0f);
        f10 = Math.min(f11 + (float)((int)(entityPlayer.N() * 100.0) % 5), f10);
        fixedRotationController2.j(true);
        fixedRotationController2.Y(f10);
        fixedRotationController2.t(0.0f);
        fixedRotationController2.k(true);
        fixedRotationController2.z(false);
        fixedRotationController2.s(false);
        fixedRotationController2.U(true);
        fixedRotationController2.D(false);
        fixedRotationController2.w(true);
        return fixedRotationController2;
    }

    private void j(EntityPlayerSP entityPlayerSP, GameSettings gameSettings, GuiScreen guiScreen) {
        if (this.J == null) {
            if (this.Op-- >= 0) {
                if (this.Op <= 0) {
                    if (guiScreen.isNull()) {
                        MovementInputHelper.D(false);
                    }
                    this.Op = -1;
                } else {
                    MovementInputHelper.I(false);
                }
            }
            if (this.S.L().booleanValue() && this.j != -1 && this.OS-- <= 0) {
                this.v(this.j);
                this.j = -1;
            }
            if (this.Oh >= 0) {
                if (this.Oh-- <= 0) {
                    this.f(entityPlayerSP);
                } else if (this.U != null && !this.U.V$src$Z$lb4tvc()) {
                    this.U.Y(((Double)this.O6.K()).floatValue() / (float)this.Oh);
                    this.U.D(true);
                }
            }
        }
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void s(EventPreLocalPlayerTick eventPreLocalPlayerTick) {
        EntityPlayerSP entityPlayerSP = eventPreLocalPlayerTick.getThePlayer();
        if (this.J != null && Minecraft.currentScreen().isNull() && entityPlayerSP.isNotNull()) {
            boolean bl;
            boolean bl2;
            boolean bl3;
            boolean bl4;
            BlockInBooleanState blockInBooleanState;
            if (this.U != null && !(this.U instanceof AdaptiveRotationController) && (blockInBooleanState = this.j(entityPlayerSP, this.OI, bl4 = this.K, bl3 = this.OP, bl2 = this.Oy, bl = this.v)) != null) {
                this.F = true;
                this.O_ = blockInBooleanState.L;
                this.I = blockInBooleanState.c;
                this.O3 = blockInBooleanState.h;
                this.Os = blockInBooleanState.v;
            }
            this.p();
        }
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        EntityPlayerSP entityPlayerSP = eventPacketReceive.getThePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        if (!eventPacketReceive.isCanceled()) {
            Packet packet = eventPacketReceive.getPacket();
            if (packet.isInstance(MappedClasses.YX) || packet.isInstance(MappedClasses.qe)) {
                boolean bl;
                if (packet.isInstance(MappedClasses.YX)) {
                    SPacketEntityVelocity sPacketEntityVelocity = new SPacketEntityVelocity(packet.getObject());
                    boolean bl2 = bl = sPacketEntityVelocity.getEntityId() == entityPlayerSP.S();
                    if (bl) {
                        double d = (double)sPacketEntityVelocity.getMotionX() / 8000.0;
                        double d2 = (double)sPacketEntityVelocity.getMotionZ() / 8000.0;
                        double d3 = Math.sqrt(d * d + d2 * d2);
                        double d4 = Math.sqrt(entityPlayerSP.t() * entityPlayerSP.t() + entityPlayerSP.T() * entityPlayerSP.T());
                        double d5 = d3 + d4;
                        this.l("Total velocity: " + d3 + " Player velocity: " + d4 + " New velocity: " + d5);
                        this.OJ = true;
                        this.Oq = 4;
                    }
                } else {
                    PacketVelocityBridge packetVelocityBridge = new PacketVelocityBridge(packet.getObject());
                    double d = packetVelocityBridge.getMotionX();
                    double d6 = packetVelocityBridge.getMotionY();
                    double d7 = packetVelocityBridge.getMotionZ();
                    boolean bl3 = bl = Math.abs(d) >= 0.005 || Math.abs(d6) >= 0.005 || Math.abs(d7) >= 0.005;
                }
                if (bl) {
                    this.J(entityPlayerSP);
                }
            } else if (packet.isInstance(MappedClasses.zw)) {
                if (this.J != null && this.J.g != null) {
                    this.Z("Server teleported you!", true);
                    this.J(entityPlayerSP);
                }
            } else if (packet.isInstance(MappedClasses.DD) && this.J != null && this.J.g != null) {
                SPacketBlockChange sPacketBlockChange = new SPacketBlockChange(packet.getObject());
                BlockState blockState = sPacketBlockChange.x();
                BlockPos blockPos = sPacketBlockChange.B();
                if (BlockUtil.p(blockState.getBlock()) && this.J.g.X(blockPos.P(), blockPos.o(), blockPos.d())) {
                    this.Ok = true;
                    this.O9.N(new BlockData(blockPos.P(), blockPos.o(), blockPos.d()));
                }
            }
        }
    }
}
