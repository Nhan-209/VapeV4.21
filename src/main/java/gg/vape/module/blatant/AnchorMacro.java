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
    private final BooleanValue O;
    private final BooleanValue F;
    private BlockPos ra;
    private boolean A;
    private final NumberValue S;
    private int r;
    private AnchorMacroState rR;
    private final BooleanValue U;
    private EnumFacing r3;
    private final RotationControlClaim K = SharedModuleControlClaims.I;
    private BlockData a;
    private int c = -1;
    private static final int J;
    private static final int D;
    private final ModeOption o = new ModeOption("On bind");
    private final BooleanValue rP;
    private final TimerUtil b;
    private int rQ = -1;
    private static final int H;
    private int rK;
    private int j;
    private final RandomValue P;
    private BlockPos k;
    private final ModeValue I;
    private boolean rO;
    private int s;
    private static final int r0;
    private final ModeOption rV = new ModeOption("On place");
    private int rr;
    private EnumFacing rL;
    private boolean v;
    private boolean ru;
    private final BooleanValue rC;
    private FixedRotationController t;
    private BlockPos Z;
    private int L = -1;
    private int V = -1;
    private final LimitValue C;
    private long p;
    private static final int Y;

    private float y(Vec3 vec3, BlockData blockData, EntityPlayerSP entityPlayerSP) {
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

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private Vec3 w(BlockPos blockPos) {
        return Vec3.create((double)blockPos.P() + 0.5, (double)blockPos.o() + 0.5, (double)blockPos.d() + 0.5);
    }

    public AnchorMacro() {
        super("AutoAnchor", -8109323, Category.Y, "Places, charges, and detonates a respawn anchor");
        this.I = ModeValue.create((Object)this, "Mode", "On bind - places, charges, and detonates on keybind press\nOn place - automatically charges and detonates after you place an anchor", (ModeSelection)this.o, this.o, this.rV);
        this.rC = BooleanValue.create(this, "Double anchor", false, "Places a second anchor on detonation, then charges it\nWill require lower delay to work reliably");
        this.F = BooleanValue.create(this, "Safe anchor", false, "Places a glowstone block between you and the anchor before charging\nto reduce explosion damage by providing block cover");
        this.O = BooleanValue.create(this, "Explosion item whitelist", false, "Swaps to the first whitelisted hotbar item before exploding\ninstead of swapping back to the anchor");
        this.C = LimitValue.N(this, "autoanchor-explosionitemwhitelist", "Explosion item", LimitValue.r, new ItemLimitData("totem of undying"));
        this.U = BooleanValue.create(this, "Aim assist", true, "Holds center of the anchor target");
        this.rP = BooleanValue.create(this, "Silent aim", true, "Uses Silent Aim system");
        this.S = NumberValue.create(this, "Aim speed", "#.#", "", 1.0, 12.0, 15.0, 0.1, "Speed of aim when placing/charging anchors");
        this.P = RandomValue.G(this, "Delay", "#", "ms", 0.0, 50.0, 100.0, 500.0, 1.0, "Delay between each action");
        this.b = new TimerUtil();
        this.rR = AnchorMacroState.FINDING_ITEMS;
        this.addValue(this.I, this.rC, this.F, this.O, this.C, this.U, this.rP, this.S, this.P);
        this.O.K(this.C);
        this.U.K(this.rP, this.S);
        this.K.l(this, 8);
    }

    static {
        D = 20;
        H = 4;
        J = 4;
        r0 = 4;
        Y = 4;
    }

    private boolean s() {
        return this.K.U(this) || this.K.h(this, this.rP.L());
    }

    private boolean i() {
        if (this.a == null) {
            return false;
        }
        Block block = Minecraft.thePlayer().getWorld().getBlockByPos(this.a.D(), this.a.B(), this.a.G());
        return block.isNotNull() && !BlockUtil.u(block);
    }

    private Vec3 Y(EntityPlayerSP entityPlayerSP) {
        if (this.ra == null || this.ra.isNull()) {
            return this.y$src$Lgg_vape_wrapper_impl_Vec3_$l9vcn5();
        }
        BlockData blockData = this.a;
        Vec3 vec3 = this.J(entityPlayerSP, blockData);
        if (vec3 != null) {
            return vec3;
        }
        return this.w(this.ra);
    }

    private void V$src$V$rmlri5() {
        this.k$src$V$ry5fyq();
        this.P();
        if (this.rV.o()) {
            this.U();
            this.rR = AnchorMacroState.IDLE;
        } else {
            this.s(false, true);
        }
    }

    private BlockPos Z() {
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

    private boolean m() {
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull() || !rayTraceResult.isBlockHit() || this.k == null || this.r3 == null) {
            return false;
        }
        BlockPos blockPos = rayTraceResult.getBlockPos();
        if (blockPos == null || blockPos.isNull()) {
            return false;
        }
        if (blockPos.equals(this.k)) {
            return true;
        }
        return this.ra != null && blockPos.equals(this.ra);
    }

    private Vec3 y$src$Lgg_vape_wrapper_impl_Vec3_$l9vcn5() {
        return this.E(this.k);
    }

    private void P() {
        if (this.V == -1) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNotNull() && entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().isNotNull()) {
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.V);
        }
        this.V = -1;
    }

    private void k$src$V$ry5fyq() {
        if (this.t != null) {
            RotationManager.b.v(this.t);
            this.t = null;
        }
        this.K.X(this);
    }

    @Override
    public void onEnable() {
        this.U();
        if (this.rV.o()) {
            this.rR = AnchorMacroState.IDLE;
        }
    }

    private boolean H() {
        return this.t == null || this.t.V$src$Z$lb4tvc();
    }

    private AnchorBlockHitTarget V$src$Lgg_vape_module_blatant_anchormacro_AnchorBlockH$cxsgzc() {
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

    private void b(String string) {
        Vape.INSTANCE.getNotificationManager().t("AutoAnchor", string + " not in hotbar", NotificationType.WARNING, 3000L);
    }

    private boolean a(Vec3 vec3, BlockData blockData) {
        Vec3[] vec3Array;
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(blockData.D(), blockData.B(), blockData.G(), (double)blockData.D() + 1.0, (double)blockData.B() + 1.0, (double)blockData.G() + 1.0);
        int n = this.ra.P();
        int n2 = this.ra.o();
        int n3 = this.ra.d();
        for (Vec3 vec32 : vec3Array = new Vec3[]{Vec3.create((double)n + 0.5, (double)n2 + 0.5, (double)n3 + 0.5), Vec3.create((double)n + 0.5, (double)n2 + 1.0, (double)n3 + 0.5), Vec3.create((double)n + 0.5, n2, (double)n3 + 0.5), Vec3.create(n, (double)n2 + 0.5, (double)n3 + 0.5), Vec3.create((double)n + 1.0, (double)n2 + 0.5, (double)n3 + 0.5), Vec3.create((double)n + 0.5, (double)n2 + 0.5, n3), Vec3.create((double)n + 0.5, (double)n2 + 0.5, (double)n3 + 1.0)}) {
            RayTraceResult rayTraceResult = axisAlignedBB.calculateIntercept(vec3, vec32);
            if (rayTraceResult != null && !rayTraceResult.isNull()) continue;
            return false;
        }
        return true;
    }

    private void W(Vec3 vec3) {
        if (!this.U.L().booleanValue() || vec3 == null || vec3.isNull()) {
            return;
        }
        if (this.t == null) {
            if (this.rP.L().booleanValue()) {
                AdaptiveRotationController adaptiveRotationController = new AdaptiveRotationController();
                adaptiveRotationController.d(false);
                this.t = adaptiveRotationController;
            } else {
                PointRotationController pointRotationController = new PointRotationController(vec3);
                pointRotationController.E(false);
                this.t = pointRotationController;
            }
            this.t.w(true);
            this.t.k(true);
            this.t.t(0.0f);
            this.t.Y(((Double)this.S.K()).floatValue());
            this.t.A(true);
            this.t.U(false);
            this.t.s(true);
            this.t.z(true);
        }
        this.t.Y(((Double)this.S.K()).floatValue());
        if (this.t instanceof PointRotationController) {
            ((PointRotationController)this.t).J(vec3);
        } else if (this.t instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)this.t).J(vec3);
        }
        RotationManager.b.S(this.t);
    }

    private int s(String string) {
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
        this.k$src$V$ry5fyq();
        this.P();
        this.U();
    }

    private void s(EntityPlayerSP entityPlayerSP, InventoryPlayer inventoryPlayer) {
        Object object;
        ItemStack heldStack;
        if (!this.v) {
            this.v = true;
            heldStack = inventoryPlayer.c(this.c);
            if (heldStack.isNull() || heldStack.t() < 2) {
                this.rR = AnchorMacroState.CHARGING_ANCHOR;
                this.X$src$V$rnpcov();
                this.j = 0;
                return;
            }
            object = this.V(entityPlayerSP);
            if (object == null) {
                this.rR = AnchorMacroState.CHARGING_ANCHOR;
                this.X$src$V$rnpcov();
                this.j = 0;
                return;
            }
            this.a = AnchorObstructionPlacementCandidate.b((AnchorObstructionPlacementCandidate)object);
            this.Z = BlockPos.d(AnchorObstructionPlacementCandidate.k((AnchorObstructionPlacementCandidate)object));
            this.rL = AnchorObstructionPlacementCandidate.O((AnchorObstructionPlacementCandidate)object);
            this.ru = false;
            this.rK = 0;
            this.s = 0;
        }
        if (this.a == null || this.Z == null || this.rL == null) {
            this.rR = AnchorMacroState.CHARGING_ANCHOR;
            this.X$src$V$rnpcov();
            this.j = 0;
            return;
        }
        Vec3 aimPoint = this.n(this.Z, this.rL);
        this.W(aimPoint);
        if (!this.b.hasTimeElapsed(this.p)) {
            return;
        }
        if (this.s < 1) {
            ++this.s;
            return;
        }
        if (!this.ru) {
            object = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
            if (((Wrapper)object).isNull() || !((RayTraceResult)object).isBlockHit()) {
                if (++this.j > 4) {
                    this.rR = AnchorMacroState.CHARGING_ANCHOR;
                    this.X$src$V$rnpcov();
                    this.j = 0;
                }
                return;
            }
            inventoryPlayer.g(this.c);
            this.G();
            this.ru = true;
            this.rK = 0;
            return;
        }
        if (!this.i()) {
            if (++this.rK > 4) {
                this.rR = AnchorMacroState.CHARGING_ANCHOR;
                this.X$src$V$rnpcov();
                this.j = 0;
            }
            return;
        }
        this.rR = AnchorMacroState.CHARGING_ANCHOR;
        this.X$src$V$rnpcov();
        this.j = 0;
    }

    private boolean f(InventoryPlayer inventoryPlayer) {
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

    private Vec3 J(EntityPlayerSP entityPlayerSP, BlockData blockData) {
        if (this.ra == null || this.ra.isNull()) {
            return null;
        }
        World world = entityPlayerSP.getWorld();
        if (world.isNull()) {
            return null;
        }
        int n = this.ra.P();
        int n2 = this.ra.o();
        int n3 = this.ra.d();
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

    private Vec3 E(BlockPos blockPos) {
        return Vec3.create((double)blockPos.P() + 0.5, (double)blockPos.o() + 1.0, (double)blockPos.d() + 0.5);
    }

    private int s(InventoryPlayer inventoryPlayer) {
        int n;
        if (this.O.L().booleanValue() && (n = this.o(this.C, inventoryPlayer)) != -1) {
            return n;
        }
        return this.rQ;
    }

    private Vec3 n(BlockPos blockPos, EnumFacing enumFacing) {
        Vec3i vec3i = enumFacing.w$src$Lgg_vape_wrapper_impl_Vec3i_$ixeccr();
        return Vec3.create((double)blockPos.P() + 0.5 + (double)vec3i.P() * 0.5, (double)blockPos.o() + 0.5 + (double)vec3i.o() * 0.5, (double)blockPos.d() + 0.5 + (double)vec3i.d() * 0.5);
    }

    private AnchorMacroState x() {
        if (this.F.L().booleanValue()) {
            return AnchorMacroState.PLACING_SHIELD;
        }
        return AnchorMacroState.CHARGING_ANCHOR;
    }

    private void X$src$V$rnpcov() {
        this.b.reset();
        this.p = (long)this.P.B();
    }

    private boolean B$src$Z$rblvqd() {
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull() || !rayTraceResult.isBlockHit() || this.k == null || this.r3 == null) {
            return false;
        }
        BlockPos blockPos = rayTraceResult.getBlockPos();
        EnumFacing enumFacing = rayTraceResult.getSideHit();
        if (blockPos == null || blockPos.isNull() || enumFacing == null || enumFacing.isNull()) {
            return false;
        }
        return blockPos.equals(this.k) && enumFacing.equals(this.r3);
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
        if (this.rR == AnchorMacroState.IDLE) {
            if (this.o.o()) {
                this.s(false, true);
            }
            return;
        }
        if (!this.U.L().booleanValue() && this.t != null) {
            this.k$src$V$ry5fyq();
        }
        if (this.U.L().booleanValue() && !this.s()) {
            return;
        }
        switch (this.rR) {
            case FINDING_ITEMS: {
                this.rQ = this.s("respawn_anchor");
                this.c = this.s("glowstone");
                if (this.rQ == -1 || this.c == -1) {
                    if (this.rQ == -1) {
                        this.b("Respawn anchor");
                    }
                    if (this.c == -1) {
                        this.b("Glowstone");
                    }
                    this.s(false, true);
                    return;
                }
                this.V = inventoryPlayer.v();
                this.rR = AnchorMacroState.PLACING_ANCHOR;
                break;
            }
            case PLACING_ANCHOR: {
                if (this.ra == null || this.k == null || this.r3 == null) {
                    if (this.j$src$Z$rxlngt()) {
                        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
                        this.k = this.ra = rayTraceResult.getBlockPos();
                        this.rR = this.x();
                        this.X$src$V$rnpcov();
                        this.j = 0;
                        break;
                    }
                    AnchorBlockHitTarget anchorBlockHitTarget = this.V$src$Lgg_vape_module_blatant_anchormacro_AnchorBlockH$cxsgzc();
                    if (anchorBlockHitTarget == null) {
                        if (++this.j <= 20) break;
                        this.rR = AnchorMacroState.FINISH;
                        break;
                    }
                    this.k = AnchorBlockHitTarget.q(anchorBlockHitTarget);
                    this.r3 = AnchorBlockHitTarget.O(anchorBlockHitTarget);
                    this.ra = this.k.offset(this.r3);
                    this.j = 0;
                }
                this.W(this.y$src$Lgg_vape_wrapper_impl_Vec3_$l9vcn5());
                if (!this.A && !this.m()) {
                    if (++this.j <= 20) break;
                    this.rR = AnchorMacroState.FINISH;
                    break;
                }
                if (!this.A) {
                    inventoryPlayer.g(this.rQ);
                    this.G();
                    this.A = true;
                    this.rr = 0;
                    break;
                }
                if (!this.c()) {
                    if (++this.rr <= 4) break;
                    this.A = false;
                    this.rr = 0;
                    if (++this.r <= 4) break;
                    this.rR = AnchorMacroState.FINISH;
                    break;
                }
                this.A = false;
                this.rr = 0;
                this.r = 0;
                this.rR = this.x();
                this.X$src$V$rnpcov();
                this.j = 0;
                break;
            }
            case WAITING_FOR_ANCHOR: {
                if (this.c()) {
                    this.rR = this.x();
                    this.X$src$V$rnpcov();
                    this.j = 0;
                    break;
                }
                BlockPos blockPos = this.Z();
                if (blockPos != null && blockPos.isNotNull()) {
                    this.ra = blockPos;
                    this.rR = this.x();
                    this.X$src$V$rnpcov();
                    this.j = 0;
                    break;
                }
                if (++this.rr <= 4) break;
                this.V$src$V$rmlri5();
                break;
            }
            case PLACING_SHIELD: {
                this.s(entityPlayerSP, inventoryPlayer);
                break;
            }
            case CHARGING_ANCHOR: {
                this.W(this.Y(entityPlayerSP));
                if (!this.b.hasTimeElapsed(this.p)) break;
                if (!this.j$src$Z$rxlngt()) {
                    ++this.j;
                    if (this.j <= 4) break;
                    this.rR = AnchorMacroState.FINISH;
                    break;
                }
                inventoryPlayer.g(this.c);
                this.G();
                this.rR = AnchorMacroState.SWAPPING_TO_EXPLOSION_ITEM;
                this.X$src$V$rnpcov();
                this.j = 0;
                break;
            }
            case SWAPPING_TO_EXPLOSION_ITEM: {
                this.W(this.Y(entityPlayerSP));
                if (!this.b.hasTimeElapsed(this.p)) break;
                if (!this.j$src$Z$rxlngt()) {
                    ++this.j;
                    if (this.j <= 4) break;
                    this.rR = AnchorMacroState.FINISH;
                    break;
                }
                this.L = this.s(inventoryPlayer);
                inventoryPlayer.g(this.L);
                this.rR = AnchorMacroState.DETONATING_ANCHOR;
                this.X$src$V$rnpcov();
                this.j = 0;
                break;
            }
            case DETONATING_ANCHOR: {
                this.W(this.Y(entityPlayerSP));
                if (!this.b.hasTimeElapsed(this.p)) break;
                if (!this.j$src$Z$rxlngt()) {
                    ++this.j;
                    if (this.j <= 4) break;
                    this.rR = AnchorMacroState.FINISH;
                    break;
                }
                this.G();
                if (this.rC.L().booleanValue() && !this.rO) {
                    this.W(this.Y(entityPlayerSP));
                    this.G();
                    this.rO = true;
                    this.rR = AnchorMacroState.CHARGING_ANCHOR;
                    this.X$src$V$rnpcov();
                    this.j = 0;
                    break;
                }
                this.rR = AnchorMacroState.FINISH;
                break;
            }
            case FINISH: {
                this.V$src$V$rmlri5();
            }
        }
    }

    private int o(LimitValue limitValue, InventoryPlayer inventoryPlayer) {
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
        return this.o.o();
    }

    @EventHandler
    public void J(EventRightClickMouse eventRightClickMouse) {
        if (!this.rV.o() || this.rR != AnchorMacroState.IDLE) {
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
        if (!this.f(inventoryPlayer)) {
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
        this.c = this.s("glowstone");
        if (this.c == -1) {
            this.b("Glowstone");
            return;
        }
        this.rQ = inventoryPlayer.v();
        this.k = blockPos;
        this.r3 = enumFacing;
        this.ra = blockPos.offset(enumFacing);
        this.V = inventoryPlayer.v();
        this.rR = AnchorMacroState.WAITING_FOR_ANCHOR;
        this.j = 0;
        this.rr = 0;
    }

    private void G() {
        KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
        KeyBinding.setKeyBindState(keyBinding, true);
        KeyBinding.onTick(keyBinding);
        KeyBinding.setKeyBindState(keyBinding, false);
    }

    private boolean j$src$Z$rxlngt() {
        RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
        if (rayTraceResult.isNull() || !rayTraceResult.isBlockHit()) {
            return false;
        }
        String string = rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a().U();
        return string != null && string.toLowerCase().contains("respawn_anchor");
    }

    private void U() {
        this.rR = AnchorMacroState.FINDING_ITEMS;
        this.rQ = -1;
        this.c = -1;
        this.L = -1;
        this.j = 0;
        this.rO = false;
        this.A = false;
        this.rr = 0;
        this.r = 0;
        this.k = null;
        this.r3 = null;
        this.ra = null;
        this.a = null;
        this.Z = null;
        this.rL = null;
        this.ru = false;
        this.rK = 0;
        this.v = false;
        this.s = 0;
    }

    private AnchorObstructionPlacementCandidate V(EntityPlayerSP entityPlayerSP) {
        double d;
        double d2;
        if (this.ra == null) {
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
        Vec3 vec32 = this.J(entityPlayerSP, null);
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
            if (!hashSet.add(l) || n3 == this.ra.P() && n2 == this.ra.o() && n == this.ra.d()) continue;
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
            if (axisAlignedBB.isNotNull() && axisAlignedBB2.isNotNull() && axisAlignedBB.intersects(axisAlignedBB2) || !this.F(world, blockData, enumFacingArray = new EnumFacing[1], blockDataArray = new BlockData[1]) || this.a(vec3, blockData) || (vec33 = this.J(entityPlayerSP, blockData)) == null) continue;
            float f3 = this.y(vec33, blockData, entityPlayerSP);
            if (f3 >= 0.15f) {
                return new AnchorObstructionPlacementCandidate(blockData, blockDataArray[0], enumFacingArray[0], f3, vec33, null);
            }
            if (!(f3 > f2)) continue;
            f2 = f3;
            anchorObstructionPlacementCandidate = new AnchorObstructionPlacementCandidate(blockData, blockDataArray[0], enumFacingArray[0], f3, vec33, null);
        }
        return anchorObstructionPlacementCandidate;
    }

    private boolean c() {
        if (this.ra == null || this.ra.isNull()) {
            return false;
        }
        String string = Minecraft.thePlayer().getWorld().getBlockByPos(this.ra.P(), this.ra.o(), this.ra.d()).U();
        return string != null && string.toLowerCase().contains("respawn_anchor");
    }

    private boolean F(World world, BlockData blockData, EnumFacing[] enumFacingArray, BlockData[] blockDataArray) {
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
