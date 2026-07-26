package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.event.impl.EventWorldChange;
import gg.vape.friend.FriendEntry;
import gg.vape.manager.client.FriendManager;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.ModDisplayInfo;
import gg.vape.module.SubModule;
import gg.vape.module.blatant.blockin.BlockPathPlanner;
import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.module.combat.CrystalAura;
import gg.vape.module.combat.crystalaura.CrystalAuraAction;
import gg.vape.module.combat.crystalaura.CrystalAuraActionCandidate;
import gg.vape.module.combat.crystalaura.CrystalAuraPlacementSearchState;
import gg.vape.module.combat.crystalaura.ExplosionType;
import gg.vape.module.control.MouseOverUpdateControlClaim;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.utility.clutch.ClutchPlacementPathUtils;
import gg.vape.module.utility.clutch.PlacementTarget;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.EntityAngleComparator;
import gg.vape.utils.EntityDistanceComparator;
import gg.vape.utils.EntityEquipmentValueComparator;
import gg.vape.utils.EntityHealthComparator;
import gg.vape.utils.MathUtil;
import gg.vape.utils.MutableColor;
import gg.vape.utils.RayTraceUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.RotationVectorMath;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.Vec3d;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.utils.datas.BlockData;
import gg.vape.utils.datas.DirectionalPosition;
import gg.vape.utils.math.NumericMathUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.EntityTargetFilterValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.CPacketPlayerBlockPlacement;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.UseEntityPacketBridge;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.Vec3i;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class CrystalAuraPlacementSubModule
extends SubModule<CrystalAura> {
    private final NumberValue Gt;
    private FixedRotationController Gv;
    private final ModeOption G0;
    private final RotationControlClaim Go;
    public final ModeValue C;
    private final BooleanValue GZ;
    private final ModeValue GE;
    private final RotationManager GD;
    private final BooleanValue Gn;
    private int t = -1;
    private EntityLivingBase Gr;
    private CrystalAuraActionCandidate s;
    public final ModeOption c;
    private final MouseOverUpdateControlClaim V;
    private final ColorValue K;
    private static final Color O;
    private int F = -1;
    private final NumberValue D;
    private CrystalAura G_;
    public final EntityTargetFilterValue GQ;
    private final TimerUtil GC;
    private final NumberValue L = NumberValue.E(this, "Aim speed", "#.#", "", 1.0, 4.5, 10.0, "Aim rotation speed");
    private int Gm = -1;
    private final ColorValue A;
    private final ModeOption S;
    private long G4;
    private final BooleanValue Gw;
    private final ModeOption r;
    private final FriendManager I;
    public final ModeOption b;
    private final RandomValue v;
    private static final Color o;
    private final BooleanValue G2;
    private static final Color Gl;
    private boolean p;
    private final ModeOption Gq;
    private final BooleanValue Z;
    private boolean J;
    private EntityLivingBase G9;
    public final ModeOption H;
    private boolean Gk;
    private final NumberValue Gh;
    public final NumberValue GK;
    private final NumberValue P;
    private static final long GL = 1200L;
    private String U;
    private boolean Gb;
    private boolean Gc;

    private void q(EntityPlayerSP entityPlayerSP, World world) {
        if (this.s != null && this.Gv != null && this.GD.w() == this.Gv) {
            Object object;
            Wrapper wrapper;
            double d;
            RayTraceResult rayTraceResult = this.GD.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
            CrystalAuraAction crystalAuraAction = this.s.R();
            DirectionalPosition directionalPosition = this.s.s;
            int n = directionalPosition.B();
            int n2 = directionalPosition.E();
            int n3 = directionalPosition.A();
            Vec3 vec3 = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
            Vec3 vec32 = this.G_.B(directionalPosition);
            boolean bl = crystalAuraAction != CrystalAuraAction.ATTACKING_CRYSTAL;
            double d2 = d = bl ? 4.0 : 4.0;
            if (vec3.distanceTo(vec32) > d) {
                Vec3 vec33 = Vec3.create(entityPlayerSP.z(), vec32.getY(), entityPlayerSP.h());
                Vec3 vec34 = Vec3.create(entityPlayerSP.M(), vec32.getY(), entityPlayerSP.m$src$D$fwnne5());
                double d3 = vec34.distanceTo(vec32);
                double d4 = vec33.distanceTo(vec32);
                if (d4 >= d3 || d4 > 4.5) {
                    this.s = null;
                    this.p = false;
                }
                return;
            }
            if (crystalAuraAction == CrystalAuraAction.PLACING_OBSIDIAN) {
                wrapper = AxisAlignedBB.create(n, n2 + 1, n3, (double)n + 1.0, (double)n2 + 2.0, (double)n3 + 1.0);
                ArrayList<Entity> entities = new ArrayList<Entity>();
                world.A().p((AxisAlignedBB)wrapper, arg_0 -> CrystalAuraPlacementSubModule.lambda$handleExplosive$6(entities, arg_0));
                if (!entities.isEmpty()) {
                    boolean bl2 = false;
                    Iterator<Entity> iterator = entities.iterator();
                    while (iterator.hasNext()) {
                        Entity entity = (Entity)iterator.next();
                        if (!entity.n$src$Z$fx7gig() && !entity.isInstance(MappedClasses.Ze)) continue;
                        bl2 = true;
                    }
                    if (bl2) {
                        this.s = null;
                        this.p = false;
                        return;
                    }
                }
                if (rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
                    Wrapper wrapper2;
                    Object object2;
                    Object object3;
                    Object object4;
                    boolean bl3;
                    BlockPos blockPos = rayTraceResult.getBlockPos();
                    int n4 = blockPos.P();
                    int n5 = blockPos.o();
                    int n6 = blockPos.d();
                    DirectionalPosition directionalPosition2 = new DirectionalPosition(n4, n5, n6, rayTraceResult.Z());
                    Block block = rayTraceResult.Z$src$Lgg_vape_wrapper_impl_Block_$6x2c9a();
                    boolean bl4 = false;
                    boolean bl5 = bl3 = BlockUtil.u(block) && !BlockUtil.J(block);
                    if (directionalPosition2.equals(directionalPosition)) {
                        bl4 = true;
                    } else if (bl3 && ((BlockCoordinate)(object4 = new DirectionalPosition(((BlockData)(object3 = new BlockData(n4, n5, n6).R((EnumFacing)(object2 = ((EnumFacing)(wrapper2 = EnumFacing.T(rayTraceResult.Z()))).getOpposite())))).D(), ((BlockData)object3).B(), ((BlockData)object3).G(), ((EnumFacing)wrapper2).Y()))).equals(directionalPosition)) {
                        bl4 = true;
                    }
                    if (bl4) {
                        wrapper2 = Minecraft.gameSettings().F();
                        if (((KeyBinding)wrapper2).u() || ((KeyBinding)wrapper2).isPressed()) {
                            KeyBinding.setKeyBindState((KeyBinding)wrapper2, false);
                        }
                        object2 = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                        KeyBinding.setKeyBindState((KeyBinding)object2, true);
                        KeyBinding.onTick((KeyBinding)object2);
                        KeyBinding.setKeyBindState((KeyBinding)object2, false);
                    } else {
                        wrapper2 = world.getBlockState(blockPos).getBlock();
                        object2 = new BlockData(directionalPosition.B(), directionalPosition.E(), directionalPosition.A());
                        object3 = EnumFacing.T(directionalPosition.X());
                        object4 = new PlacementTarget((BlockData)object2, (EnumFacing)object3);
                        if (!ClutchPlacementPathUtils.P(vec3, world, (BlockData)object2, (EnumFacing)object3) && ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, (PlacementTarget)object4, this.GD.V(), this.GD.x()) == null) {
                            this.s = null;
                            this.p = false;
                        }
                    }
                }
            } else if (crystalAuraAction == CrystalAuraAction.PLACING_CRYSTAL) {
                wrapper = AxisAlignedBB.create(n, n2, n3, (double)n + 1.0, (double)n2 + 2.0, (double)n3 + 1.0);
                ArrayList<Entity> entities = new ArrayList<Entity>();
                world.A().p((AxisAlignedBB)wrapper, arg_0 -> CrystalAuraPlacementSubModule.lambda$handleExplosive$7(entities, arg_0));
                if (!entities.isEmpty() && this.z(this.G9, directionalPosition, entityPlayerSP, world)) {
                    boolean bl6 = false;
                    Iterator<Entity> iterator = entities.iterator();
                    while (iterator.hasNext()) {
                        Entity entity = (Entity)iterator.next();
                        if (!entity.isInstance(MappedClasses.Ze)) continue;
                        this.s.D(CrystalAuraAction.ATTACKING_CRYSTAL);
                        bl6 = entities.size() == 1;
                    }
                    if (!bl6) {
                        this.s = null;
                        this.p = false;
                    }
                    return;
                }
                if (rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
                    int n7;
                    int n8;
                    BlockPos blockPos = rayTraceResult.getBlockPos();
                    int n9 = blockPos.P();
                    if (directionalPosition.equals(new DirectionalPosition(n9, n8 = blockPos.o(), n7 = blockPos.d(), rayTraceResult.Z()))) {
                        KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                        KeyBinding keyBinding2 = Minecraft.gameSettings().F();
                        if (keyBinding2.u() || keyBinding2.isPressed()) {
                            KeyBinding.setKeyBindState(keyBinding2, false);
                        }
                        if (keyBinding.u() || keyBinding.isPressed()) {
                            KeyBinding.setKeyBindState(keyBinding, false);
                        }
                        KeyBinding.setKeyBindState(keyBinding, true);
                        KeyBinding.onTick(keyBinding);
                        KeyBinding.setKeyBindState(keyBinding, false);
                    } else {
                        Block block = world.getBlockState(blockPos).getBlock();
                        BlockData blockData = new BlockData(directionalPosition.B(), directionalPosition.E(), directionalPosition.A());
                        EnumFacing enumFacing = EnumFacing.T(directionalPosition.X());
                        PlacementTarget placementTarget = new PlacementTarget(blockData, enumFacing);
                        if (!ClutchPlacementPathUtils.P(vec3, world, blockData, enumFacing) || ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, placementTarget, this.GD.V(), this.GD.x()) == null) {
                            this.s = null;
                            this.p = false;
                        }
                    }
                } else if (rayTraceResult.isEntityHit() && rayTraceResult.getEntity().isInstance(MappedClasses.Ze)) {
                    this.s.D(CrystalAuraAction.ATTACKING_CRYSTAL);
                    crystalAuraAction = CrystalAuraAction.ATTACKING_CRYSTAL;
                } else {
                    BlockData blockData = new BlockData(directionalPosition.B(), directionalPosition.E(), directionalPosition.A());
                    EnumFacing enumFacing = EnumFacing.T(directionalPosition.X());
                    PlacementTarget placementTarget = new PlacementTarget(blockData, enumFacing);
                    if (!ClutchPlacementPathUtils.P(vec3, world, blockData, enumFacing) || ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, placementTarget, this.GD.V(), this.GD.x()) == null) {
                        this.s = null;
                        this.p = false;
                    }
                }
            }
            if (crystalAuraAction == CrystalAuraAction.ATTACKING_CRYSTAL) {
                if (this.G9 == null || this.G9.M$src$Z$ff28xj() || this.G9.w$src$F$15l9epb() <= 0.0f) {
                    this.s = null;
                    this.p = false;
                    return;
                }
                if (rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.entity()) && (wrapper = rayTraceResult.getEntity()).isInstance(MappedClasses.Ze)) {
                    object = Minecraft.gameSettings().F();
                    if (((KeyBinding)object).u() || ((KeyBinding)object).isPressed()) {
                        KeyBinding.setKeyBindState((KeyBinding)object, false);
                    }
                    KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                    if (((ModeSelection)this.GE.K()).equals(this.Gq) && this.s.Q() >= (Double)this.D.K() / 100.0) {
                        KeyBinding.setKeyBindState(keyBinding, true);
                        KeyBinding.onTick(keyBinding);
                    }
                    if (keyBinding.u() || keyBinding.isPressed()) {
                        KeyBinding.setKeyBindState(keyBinding, false);
                    }
                    KeyBinding.setKeyBindState((KeyBinding)object, true);
                    KeyBinding.onTick((KeyBinding)object);
                    KeyBinding.setKeyBindState((KeyBinding)object, false);
                }
            }
        }
    }

    private CrystalAuraActionCandidate D(EntityPlayerSP entityPlayerSP, World world, double d, boolean bl) {
        int n = MathUtil.floor(entityPlayerSP.N());
        CrystalAuraActionCandidate crystalAuraActionCandidate = this.J(entityPlayerSP, world, d, bl, n);
        if (crystalAuraActionCandidate != null) {
            return crystalAuraActionCandidate;
        }
        String string = this.U;
        int n2 = n + 1;
        CrystalAuraActionCandidate crystalAuraActionCandidate2 = this.J(entityPlayerSP, world, d, bl, n2);
        if (crystalAuraActionCandidate2 != null) {
            return crystalAuraActionCandidate2;
        }
        if (this.o(string) > this.o(this.U)) {
            this.U = string;
        }
        return null;
    }

    static {
        o = new Color(255, 0, 0, 25);
        Gl = new Color(0, 255, 255, 25);
        O = new Color(0, 0, 255, 25);
    }

    @EventHandler
    public void onPacketSend(EventPacketSend eventPacketSend) {
        EntityPlayerSP entityPlayerSP = eventPacketSend.getThePlayer();
        int n = ExplosionType.q();
        if (n != 0) {
            WorldClient worldClient = eventPacketSend.getWorld();
            boolean bl = entityPlayerSP.isNull();
            if (bl || worldClient.isNull()) {
                return;
            }
            Packet packet = eventPacketSend.getPacket();
            boolean bl2 = UseEntityPacketBridge.h(packet);
            if (bl2) {
                UseEntityPacketBridge useEntityPacket = new UseEntityPacketBridge(packet.getObject());
                if (useEntityPacket.S()) {
                    Entity entity = useEntityPacket.C(eventPacketSend.getWorld());
                    if (this.GZ.L().booleanValue() && entity.isNotNull() && entity.isInstance(MappedClasses.zm) && entity.V$src$I$fk0dv5() <= 13) {
                        this.Gc = true;
                    }
                    if (this.s != null && this.s.R() == CrystalAuraAction.ATTACKING_CRYSTAL && entity.isNotNull() && entity.isInstance(MappedClasses.Ze)) {
                        if (((ModeSelection)this.GE.K()).equals(this.S) && !Minecraft.V()) {
                            this.F = entity.S();
                        }
                        this.s = null;
                        this.p = false;
                    }
                }
            }
            if (packet.isInstance(MappedClasses.YB)) {
                CrystalAuraAction crystalAuraAction;
                CrystalAuraActionCandidate crystalAuraActionCandidate = this.s;
                if (crystalAuraActionCandidate != null) {
                    CrystalAuraAction action = this.s.R();
                    if (action == (crystalAuraAction = CrystalAuraAction.PLACING_OBSIDIAN) || action == CrystalAuraAction.PLACING_CRYSTAL) {
                        this.p = true;
                    }
                }
            }
            return;
        }
        WorldClient worldClient = eventPacketSend.getWorld();
        boolean bl = entityPlayerSP.isNull();
        if (bl) {
            return;
        }
        Packet packet = eventPacketSend.getPacket();
        boolean bl3 = UseEntityPacketBridge.h(packet);
        if (bl3) {
            CrystalAuraAction crystalAuraAction;
            CPacketPlayerBlockPlacement cPacketPlayerBlockPlacement = new CPacketPlayerBlockPlacement(packet.getObject());
            CrystalAuraActionCandidate crystalAuraActionCandidate = this.s;
            CrystalAuraAction crystalAuraAction2 = crystalAuraActionCandidate.R();
            CrystalAuraAction crystalAuraAction3 = crystalAuraAction2;
            if (crystalAuraAction3 == (crystalAuraAction = CrystalAuraAction.PLACING_OBSIDIAN)) {
                this.p = true;
            }
        }
    }

    private float k(EntityLivingBase entityLivingBase, ExplosionType explosionType, Vec3 vec3, EntityPlayerSP entityPlayerSP, World world) {
        double d;
        int n;
        double d2;
        double d3;
        EntityLivingBase entityLivingBase2;
        float f = explosionType.I() * 2.0f;
        double d4 = vec3.getX();
        double d5 = vec3.getY();
        double d6 = vec3.getZ();
        if (entityLivingBase.isInstance(MappedClasses.Yl)) {
            EntityPlayer entityPlayer = new EntityPlayer(entityLivingBase);
            BlockPathPlanner blockPathPlanner = new BlockPathPlanner(entityPlayer, entityPlayerSP, world, new BlockPlacementGraph(entityPlayerSP));
            entityLivingBase2 = blockPathPlanner.T();
            blockPathPlanner.h();
            blockPathPlanner.K();
            d3 = entityLivingBase2.z() - entityLivingBase2.M();
            d2 = entityLivingBase2.N() - entityLivingBase2.W();
            double d7 = entityLivingBase2.h() - entityLivingBase2.m$src$D$fwnne5();
            if (this.GZ.L().booleanValue() && this.Gc && entityLivingBase.equals(this.G9)) {
                float f2 = 0.4f;
                d2 = entityLivingBase.b$src$Z$fqlxe4() ? Math.min(0.4, d2 / 2.0 + (double)f2) : (double)f2;
            }
            entityLivingBase2.F(d3, d2, d7);
            for (n = 0; n < 3; ++n) {
                blockPathPlanner.B();
            }
        } else {
            entityLivingBase2 = entityLivingBase;
        }
        double d8 = entityLivingBase2.z();
        d3 = entityLivingBase2.N();
        d2 = entityLivingBase2.h();
        int n2 = NumericMathUtil.r(d4 - (double)f - 1.0);
        int n3 = NumericMathUtil.r(d4 + (double)f + 1.0);
        n = NumericMathUtil.r(d5 - (double)f - 1.0);
        int n4 = NumericMathUtil.r(d5 + (double)f + 1.0);
        int n5 = NumericMathUtil.r(d6 - (double)f - 1.0);
        int n6 = NumericMathUtil.r(d6 + (double)f + 1.0);
        if (MathUtil.e(d8, (double)n2, (double)n3) && MathUtil.e(d3, (double)n, (double)n4) && MathUtil.e(d2, (double)n5, (double)n6) && (d = ForgeVersion.MC_1_16_5.d() ? Math.sqrt(entityLivingBase2.g(vec3)) / (double)f : entityLivingBase2.i(d4, d5, d6) / (double)f) <= 1.0) {
            float f3 = CrystalAura.O(vec3, entityLivingBase2, world);
            double d9 = ForgeVersion.MC_1_12_2.d() ? 7.0 : 8.0;
            float f4 = (float)(d9 * (double)f + 1.0);
            double d10 = (1.0 - d) * (double)f3;
            float f5 = (float)((d10 * d10 + d10) / 2.0 * d9 * (double)f + 1.0);
            return f5 / f4;
        }
        return -1.0f;
    }

    private void I(EntityPlayerSP entityPlayerSP, World world) {
        ArrayList<Entity> arrayList = new ArrayList<Entity>();
        if (this.G_.W()) {
            return;
        }
        List list = world.z();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            EntityLivingBase entityLivingBase;
            Object e = iterator.next();
            Entity entity = new Entity(e);
            if (ClientSettings.H && entity.isInstance(MappedClasses.FT) || !entity.isInstance(MappedClasses.zm) || !this.H(entityLivingBase = new EntityLivingBase(e), entityPlayerSP)) continue;
            arrayList.add(entityLivingBase);
        }
        if (this.C.K() == this.c) {
            arrayList.sort(new EntityAngleComparator());
        } else if (this.C.K() == this.G0) {
            arrayList.sort(new EntityDistanceComparator());
        } else if (this.C.K() == this.H) {
            arrayList.sort(new EntityEquipmentValueComparator());
        } else if (this.C.K() == this.b) {
            arrayList.sort(new EntityHealthComparator());
        }
        if (!arrayList.isEmpty()) {
            EntityLivingBase target = (EntityLivingBase)arrayList.get(0);
            this.G9 = target;
            this.X(target);
        } else {
            this.n$src$V$1jw7x72();
        }
    }

    @Override
    public void onEnable() {
    }

    @Override
    public ModDisplayInfo J() {
        if (!this.G2.L().booleanValue()) {
            return null;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return null;
        }
        int n = this.G_.U(entityPlayerSP);
        Color color = new Color(255, 20, 20);
        if (n >= 32) {
            color = new Color(2, 190, 58);
        } else if (n >= 16) {
            color = new Color(255, 249, 18);
        }
        String string = this.s != null ? "\u00a7f\u00a7l" : "\u00a77";
        String string2 = (this.s == null ? "\u00a7r" : "\u00a75\u00a7l") + n;
        if (this.s == null && this.U != null && ("no target".equals(this.U) || "no crystals".equals(this.U) || this.R() != null)) {
            string2 = string2 + " \u00a7c[" + this.U + "]";
        }
        String string3 = " " + string + "(CrystalAura)";
        return new ModDisplayInfo(string2, color, string3);
    }

    private void n$src$V$1jw7x72() {
        this.X(this.G9);
        this.G9 = null;
        this.s = null;
        this.p = false;
        this.Gk = false;
        this.Gc = false;
        this.GC.reset();
        if (this.Gv != null) {
            this.Gv.k(true);
            this.Gv.z(true);
            this.Gv.U(true);
            this.Gv.t(0.0f);
            this.Gv.Y(5.0f);
            RotationManager.b.v(this.Gv);
        }
        if (this.Gm != -1) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            if (!entityPlayerSP.isNull()) {
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.Gm);
            }
            this.Gm = -1;
        }
        if (this.GD.w() == null || this.GD.w() != this.Gv || this.Gv != null && !this.Gv.v() && this.Gv.V$src$Z$lb4tvc()) {
            this.Gv = null;
            this.Go.X(this.G_);
            if (this.J) {
                this.J = false;
                this.G_.s(false, true);
            }
        }
    }

    @Override
    public String r() {
        if (this.s != null) {
            return this.s.R().toString();
        }
        return "";
    }

    private static void lambda$getAimJob$2(ArrayList arrayList, Object object) {
        if (MappedClasses.Ze.isAssignableFrom(object.getClass())) {
            arrayList.add(new Entity(object));
        }
    }

    private static void lambda$findObsidianAtYLevel$3(ArrayList arrayList, Object object) {
        if (MappedClasses.Ze.isAssignableFrom(object.getClass())) {
            arrayList.add(new Entity(object));
        }
    }

    private int o(String string) {
        if (string == null) {
            return 0;
        }
        if ("low eff".equals(string)) {
            return 6;
        }
        if ("blocked".equals(string) || "out range".equals(string) || "no place".equals(string)) {
            return 5;
        }
        if ("no candidate".equals(string)) {
            return 4;
        }
        if ("no placement block".equals(string)) {
            return 3;
        }
        if ("no target".equals(string)) {
            return 2;
        }
        return 1;
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        WorldClient worldClient = eventPacketReceive.getWorld();
        EntityPlayerSP entityPlayerSP = eventPacketReceive.getThePlayer();
        GuiScreen guiScreen = eventPacketReceive.getCurrentScreen();
        if (worldClient.isNull() || entityPlayerSP.isNull()) {
            return;
        }
    }

    private boolean v(EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP) {
        return (double)entityLivingBase.getDistanceToEntity(entityPlayerSP) <= (Double)this.Gt.K();
    }

    private boolean L(EntityLivingBase entityLivingBase) {
        return entityLivingBase != null && entityLivingBase.isNotNull() && !entityLivingBase.M$src$Z$ff28xj() && entityLivingBase.w$src$F$15l9epb() > 0.0f;
    }

    private static void lambda$handleExplosive$6(ArrayList arrayList, Object object) {
        arrayList.add(new Entity(object));
    }

    public boolean H(EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP) {
        if (entityLivingBase.isNull()) {
            return false;
        }
        if (entityLivingBase.equals(entityPlayerSP)) {
            return false;
        }
        if (entityLivingBase.w$src$F$15l9epb() <= 0.0f || entityLivingBase.M$src$Z$ff28xj()) {
            return false;
        }
        if (!this.v(entityLivingBase, entityPlayerSP)) {
            return false;
        }
        if (RotationUtil.a(entityPlayerSP, entityLivingBase) > ((Double)this.GK.K()).intValue() / 2) {
            return false;
        }
        FriendEntry friendEntry = this.I.O(entityLivingBase.getName());
        if (friendEntry != null && !friendEntry.c()) {
            return false;
        }
        if (entityLivingBase.equals(entityPlayerSP.S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12())) {
            return false;
        }
        return this.GQ.c(entityLivingBase);
    }

    private static void lambda$onClientTickPost$1(ArrayList arrayList, Object object) {
        if (MappedClasses.Ze.isAssignableFrom(object.getClass())) {
            arrayList.add(new Entity(object));
        }
    }

    @EventHandler
    public void S(EventWorldChange eventWorldChange) {
        this.K();
        this.G9 = null;
        this.Gr = null;
        this.G4 = 0L;
        this.Gb = false;
        this.U = null;
        this.s = null;
        this.p = false;
    }

    private AxisAlignedBB d(BlockCoordinate blockCoordinate) {
        double d = blockCoordinate.B();
        double d2 = blockCoordinate.E() + 1;
        double d3 = blockCoordinate.A();
        return AxisAlignedBB.create(d, d2, d3, d + 1.0, d2 + 2.0, d3 + 1.0);
    }

    private static void lambda$handleExplosive$7(ArrayList arrayList, Object object) {
        arrayList.add(new Entity(object));
    }

    private EntityLivingBase R() {
        EntityLivingBase entityLivingBase;
        if (this.L(this.G9)) {
            this.X(this.G9);
            entityLivingBase = this.G9;
        } else {
            entityLivingBase = this.L(this.Gr) && System.currentTimeMillis() - this.G4 <= 1200L ? this.Gr : null;
        }
        if (entityLivingBase == null) {
            this.Gr = null;
        }
        return entityLivingBase;
    }

    private static void lambda$findObsidianAtYLevel$4(ArrayList arrayList, Object object) {
        Entity entity = new Entity(object);
        if (entity.n$src$Z$fx7gig()) {
            arrayList.add(entity);
        }
    }

    @Override
    public void onDisable() {
        this.K();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        EntityPlayerSP entityPlayerSP = eventRender3D.getThePlayer();
        WorldClient worldClient = eventRender3D.getWorld();
        EntityLivingBase entityLivingBase = this.R();
        boolean bl = GL11.glIsEnabled((int)3042);
        boolean bl2 = GL11.glIsEnabled((int)2848);
        boolean bl3 = GL11.glIsEnabled((int)2929);
        boolean bl4 = GL11.glGetBoolean((int)2930);
        try {
            RenderUtil.d();
            RenderUtils.g();
            GlStateManager.enableBlend();
            OpenGlBackendHolder.d.l(3042);
            GL11.glBlendFunc((int)770, (int)771);
            GlStateManager.disableTexture2D();
            GlStateManager.r();
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
            double d = RenderManager.getInterpolatedRenderPosX();
            double d2 = RenderManager.getInterpolatedRenderPosY();
            double d3 = RenderManager.getInterpolatedRenderPosZ();
            if (this.s != null && this.s.s != null) {
                Color color = this.s.R() == CrystalAuraAction.ATTACKING_CRYSTAL ? o : (this.s.R() == CrystalAuraAction.PLACING_CRYSTAL ? Gl : O);
                try {
                    RenderUtil.w(d, d2, d3, this.s.s.B(), this.s.s.E(), this.s.s.A(), color);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
        finally {
            if (bl) {
                GlStateManager.enableBlend();
            } else {
                GlStateManager.disableBlend();
            }
            if (bl3) {
                GlStateManager.enableDepth();
            } else {
                GlStateManager.disableDepth();
            }
            if (bl2) {
                GlStateManager.r();
            } else {
                GlStateManager.P();
            }
            GlStateManager.depthMask(bl4);
            RenderUtils.f();
            RenderUtil.Y();
        }
        if (this.Gw.L().booleanValue() && entityLivingBase != null && Minecraft.currentScreen().isNull()) {
            float f = entityLivingBase.isInstance(MappedClasses.Yl) || entityLivingBase.isInstance(MappedClasses.lG) ? 0.7f : entityLivingBase.f$src$F$fst3ac();
            MutableColor mutableColor = this.s != null && this.s.R() == CrystalAuraAction.ATTACKING_CRYSTAL ? this.K.q$src$Lgg_vape_utils_MutableColor_$1dowyd3() : this.A.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            GuiRenderPrimitives.R(entityLivingBase.c(), entityLivingBase.A(), entityLivingBase.Z(), 50.0f, f, entityLivingBase.Y(), mutableColor);
        }
    }

    @EventHandler
    public void X(EventPostTick eventPostTick) {
        WorldClient worldClient = eventPostTick.getWorld();
        int n = ExplosionType.q();
        EntityPlayerSP entityPlayerSP = eventPostTick.getThePlayer();
        if (this.F != -1) {
            Entity entity = ((World)worldClient).V(this.F);
            if (entity.isNotNull()) {
                worldClient.M(entity);
            }
            this.F = -1;
        }
        if (worldClient.isNull() || entityPlayerSP.isNull() || this.s == null) {
            return;
        }
        EntityPlayerSP entityPlayerSP2 = entityPlayerSP;
        CrystalAuraPlacementSubModule crystalAuraPlacementSubModule = this;
        crystalAuraPlacementSubModule.g(entityPlayerSP2);
        if (!this.p) {
            return;
        }
        CrystalAuraAction action = this.s.R();
        if (action == CrystalAuraAction.PLACING_OBSIDIAN) {
            DirectionalPosition directionalPosition = this.s.s;
            EnumFacing enumFacing = EnumFacing.T(directionalPosition.X());
            BlockData blockData = new BlockData(directionalPosition.B(), directionalPosition.E(), directionalPosition.A()).R(enumFacing);
            BlockPos blockPos = BlockPos.create(blockData.D(), blockData.B(), blockData.G());
            BlockState blockState = worldClient.getBlockState(blockPos);
            if (blockState.isNotNull() && blockState.getBlock().U().toLowerCase().contains("obsidian")) {
                this.s.D(CrystalAuraAction.PLACING_CRYSTAL);
                BlockCoordinate blockCoordinate = new BlockCoordinate(blockPos.P(), blockPos.o(), blockPos.d());
                this.s.s = new DirectionalPosition(blockCoordinate, 1);
                EntityPlayerSP entityPlayerSP3 = entityPlayerSP;
                CrystalAuraPlacementSubModule crystalAuraPlacementSubModule2 = this;
                crystalAuraPlacementSubModule2.g(entityPlayerSP3);
                this.p = false;
            } else {
                this.s = null;
                this.p = false;
            }
        } else if (action == CrystalAuraAction.PLACING_CRYSTAL) {
            DirectionalPosition directionalPosition = this.s.s;
            BlockCoordinate blockCoordinate = new BlockCoordinate(directionalPosition.B(), directionalPosition.E() + 1, directionalPosition.A());
            AxisAlignedBB axisAlignedBB = AxisAlignedBB.create((double)blockCoordinate.B() - 0.5, blockCoordinate.E(), (double)blockCoordinate.A() - 0.5, (double)blockCoordinate.B() + 0.5, (double)blockCoordinate.E() + 2.0, (double)blockCoordinate.A() + 0.5);
            ArrayList arrayList = new ArrayList();
            worldClient.A().p(axisAlignedBB, arg_0 -> CrystalAuraPlacementSubModule.lambda$onClientTickPost$1(arrayList, arg_0));
            if (!arrayList.isEmpty()) {
                this.s.D(CrystalAuraAction.ATTACKING_CRYSTAL);
                this.s.J((Entity)arrayList.get(0));
                this.p = false;
            } else {
                Vec3 vec3;
                Vec3 vec32 = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
                double d = vec32.distanceTo(vec3 = this.G_.B(this.s.s));
                if (d > 3.0) {
                    Vec3 vec33 = Vec3.create(entityPlayerSP.z(), vec3.getY(), entityPlayerSP.h());
                    Vec3 vec34 = Vec3.create(entityPlayerSP.M(), vec3.getY(), entityPlayerSP.m$src$D$fwnne5());
                    double d2 = vec34.distanceTo(vec3);
                    double d3 = vec33.distanceTo(vec3);
                    if (d3 > d2) {
                        this.s = null;
                        this.p = false;
                    }
                }
            }
        }
    }

    private static void lambda$findObsidianAtYLevel$5(ArrayList arrayList, Object object) {
        Entity entity = new Entity(object);
        if (entity.n$src$Z$fx7gig()) {
            arrayList.add(entity);
        }
    }

    private boolean z(EntityLivingBase entityLivingBase, BlockCoordinate blockCoordinate, EntityPlayerSP entityPlayerSP, World world) {
        boolean bl = false;
        AxisAlignedBB axisAlignedBB = this.d(blockCoordinate);
        if (entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().intersects(axisAlignedBB)) {
            bl = true;
        }
        if (entityLivingBase.isInstance(MappedClasses.Yl)) {
            EntityPlayer entityPlayer = new EntityPlayer(entityLivingBase);
            BlockPathPlanner blockPathPlanner = new BlockPathPlanner(entityPlayer, entityPlayerSP, world, new BlockPlacementGraph(entityPlayerSP));
            EntityPlayer entityPlayer2 = blockPathPlanner.T();
            blockPathPlanner.h();
            blockPathPlanner.K();
            double d = entityPlayer2.z() - entityPlayer2.M();
            double d2 = entityPlayer2.N() - entityPlayer2.W();
            double d3 = entityPlayer2.h() - entityPlayer2.m$src$D$fwnne5();
            if (this.GZ.L().booleanValue() && this.Gc && entityLivingBase.equals(this.G9)) {
                float f = 0.4f;
                d2 = entityLivingBase.b$src$Z$fqlxe4() ? Math.min(0.4, d2 / 2.0 + (double)f) : (double)f;
                double d4 = entityPlayerSP.z() - entityLivingBase.z();
                double d5 = entityPlayerSP.h() - entityLivingBase.h();
                d = d / 2.0 - d4 / Math.sqrt(d4 * d4 + d5 * d5) * 0.3;
                d3 = d3 / 2.0 - d5 / Math.sqrt(d4 * d4 + d5 * d5) * 0.3;
            }
            entityPlayer2.F(d, d2, d3);
            for (int i = 0; i < 3; ++i) {
                blockPathPlanner.B();
            }
            if (entityPlayer2.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().intersects(axisAlignedBB)) {
                bl = true;
            }
        }
        return bl;
    }

    private void g(EntityPlayerSP entityPlayerSP) {
        Vec3 vec3;
        Object object;
        int n = ExplosionType.R();
        if (!this.Go.U(this.G_)) {
            return;
        }
        if (this.Gv == null) {
            this.Gv = new AdaptiveRotationController();
        }
        this.Gv.Y(((Double)this.L.K()).floatValue());
        this.Gv.u(false);
        this.Gv.w(true);
        this.Gv.k(true);
        this.Gv.t(0.0f);
        this.Gv.U(true);
        this.Gv.s(true);
        if (this.Gv instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)this.Gv).b(false);
        }
        float f = this.GD.V();
        float f2 = this.GD.x();
        Vec3 vec32 = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
        DirectionalPosition directionalPosition = this.s.s;
        int n2 = directionalPosition.X();
        EnumFacing enumFacing = EnumFacing.T(n2);
        if (this.s.R() == CrystalAuraAction.ATTACKING_CRYSTAL) {
            object = this.s.s;
            BlockCoordinate blockCoordinate = new BlockCoordinate(((BlockCoordinate)object).B(), ((BlockCoordinate)object).E() + 1, ((BlockCoordinate)object).A());
            AxisAlignedBB axisAlignedBB = AxisAlignedBB.create((double)blockCoordinate.B() - 0.5, blockCoordinate.E(), (double)blockCoordinate.A() - 0.5, (double)blockCoordinate.B() + 0.5, (double)blockCoordinate.E() + 2.0, (double)blockCoordinate.A() + 0.5);
            ArrayList arrayList = new ArrayList();
            entityPlayerSP.getWorld().A().p(axisAlignedBB, arg_0 -> CrystalAuraPlacementSubModule.lambda$getAimJob$2(arrayList, arg_0));
            boolean bl = ClutchPlacementPathUtils.P(vec32, entityPlayerSP.getWorld(), new BlockData(((BlockCoordinate)object).B(), ((BlockCoordinate)object).E(), ((BlockCoordinate)object).A()), enumFacing);
            vec3 = !arrayList.isEmpty() && !bl ? RotationUtil.M(vec32, ((Entity)arrayList.get(0)).R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0).n() : this.G_.B(directionalPosition);
        } else {
            object = new PlacementTarget(new BlockData(directionalPosition.B(), directionalPosition.E(), directionalPosition.A()), enumFacing);
            Vec3 vec33 = ClutchPlacementPathUtils.D(entityPlayerSP, entityPlayerSP.getWorld(), vec32, (PlacementTarget)object, f, f2);
            vec3 = vec33 != null ? vec33 : this.G_.B(directionalPosition);
        }
        object = RotationVectorMath.d(vec32, vec3, f, f2);
        this.Gv.b((RotationAngles)object);
        if (this.GD.w() != this.Gv) {
            // empty if block
        }
        this.GD.S(this.Gv);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private CrystalAuraActionCandidate J(EntityPlayerSP entityPlayerSP, World world, double d, boolean bl, int n) {
        CrystalAuraActionCandidate crystalAuraActionCandidate;
        int n2;
        int n3;
        ItemStack itemStack;
        if (this.G9 == null) {
            this.Gb = false;
            this.U = null;
            return null;
        }
        Vec3 vec3 = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
        int n4 = n;
        int n5 = MathUtil.floor(entityPlayerSP.z());
        int n6 = MathUtil.floor(entityPlayerSP.h());
        CrystalAuraActionCandidate crystalAuraActionCandidate2 = null;
        CrystalAuraActionCandidate crystalAuraActionCandidate3 = null;
        boolean bl2 = false;
        float f = 0.0f;
        float f2 = 0.0f;
        double d2 = (double)((Double)this.Gh.K()).intValue() / 100.0;
        double d3 = 0.1;
        CrystalAuraPlacementSearchState crystalAuraPlacementSearchState = new CrystalAuraPlacementSearchState();
        boolean bl3 = false;
        BlockState blockState = null;
        int n7 = this.G_.z(entityPlayerSP);
        if (n7 != -1 && !(itemStack = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(n7)).isNull()) {
            blockState = BlockUtil.E(itemStack);
        }
        if (this.Z.L().booleanValue()) {
            boolean bl4 = bl3 = blockState != null;
            if (!bl3) {
                crystalAuraPlacementSearchState.t();
            }
        } else {
            int n8 = n5 - (int)d;
            int n9 = n5 + (int)d;
            int n10 = n6 - (int)d;
            int n11 = n6 + (int)d;
            for (int i = n8; i <= n9 && !bl2; ++i) {
                for (int j = n10; j <= n11 && !bl2; ++j) {
                    BlockPos blockPos = BlockPos.D(i, n4, j);
                    BlockState blockState2 = world.getBlockState(blockPos);
                    if (blockState2.isNull()) continue;
                    Block block = blockState2.getBlock();
                    if (!this.G_.X(blockState2)) continue;
                    bl2 = true;
                }
            }
        }
        double d4 = RenderManager.getInterpolatedRenderPosX();
        double d5 = RenderManager.getInterpolatedRenderPosY();
        double d6 = RenderManager.getInterpolatedRenderPosZ();
        double d7 = entityPlayerSP.z();
        double d8 = entityPlayerSP.h();
        double d9 = this.G9.z();
        double d10 = this.G9.h();
        double d11 = d9 - d7;
        double d12 = d10 - d8;
        Color color = new Color(0, 0, 255, 25);
        Color color2 = new Color(9, 255, 0, 25);
        double d13 = entityPlayerSP.getDistanceToEntity(this.G9);
        boolean bl5 = d13 <= 1.75;
        for (int i = 0; i < 2; ++i) {
            boolean bl6 = i == 0 && !bl5;
            for (n3 = n5 - (int)d; n3 <= n5 + (int)d; ++n3) {
                for (n2 = n6 - (int)d; n2 <= n6 + (int)d; ++n2) {
                    Object object;
                    Object object2;
                    Object object3;
                    Object object4;
                    Wrapper wrapper;
                    int n12;
                    boolean bl7;
                    BlockData blockData;
                    Wrapper wrapper2;
                    Wrapper wrapper3;
                    if (n3 == n5 && n2 == n6) continue;
                    double d14 = (double)n3 - d7;
                    double d15 = (double)n2 - d8;
                    if (bl6 && d11 * d14 + d12 * d15 < 0.0) continue;
                    AxisAlignedBB searchBox = AxisAlignedBB.create((double)n3 - d3, (double)n4 + 1.0, (double)n2 - d3, (double)n3 + 1.0 + d3, (double)n4 + 3.0, (double)n2 + 1.0 + d3);
                    ArrayList<Entity> entities = new ArrayList<Entity>();
                    world.A().p(searchBox, arg_0 -> CrystalAuraPlacementSubModule.lambda$findObsidianAtYLevel$3(entities, arg_0));
                    Iterator<Entity> entityIterator = entities.iterator();
                    while (entityIterator.hasNext()) {
                        Object object8;
                        Wrapper wrapper4;
                        wrapper3 = entityIterator.next();
                        Vec3 vec32 = ((Entity)wrapper3).I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk();
                        if (!(vec3.distanceTo(vec32) <= 4.0) || !MathUtil.e(((Entity)wrapper3).z(), (double)n3, (double)(n3 + 1)) || !MathUtil.e(((Entity)wrapper3).h(), (double)n2, (double)(n2 + 1)) || !this.N(vec32, ExplosionType.Q, entityPlayerSP, world)) continue;
                        float f3 = this.k(this.G9, ExplosionType.Q, vec32, entityPlayerSP, world);
                        wrapper2 = RotationUtil.M(vec3, ((Entity)wrapper3).R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().contract(0.01, 0.0, 0.01), 0.0, 0.0, 0.0).n();
                        if (vec3.distanceTo((Vec3)wrapper2) > 3.5) {
                            wrapper4 = Vec3.create(entityPlayerSP.z(), ((Vec3)wrapper2).getY(), entityPlayerSP.h());
                            object8 = Vec3.create(entityPlayerSP.M(), ((Vec3)wrapper2).getY(), entityPlayerSP.m$src$D$fwnne5());
                            double d16 = ((Vec3)object8).distanceTo((Vec3)wrapper2);
                            double d17 = ((Vec3)wrapper4).distanceTo((Vec3)wrapper2);
                            if (d17 >= d16) {
                                crystalAuraPlacementSearchState.R();
                                continue;
                            }
                        }
                        if (!((RayTraceResult)(wrapper4 = RayTraceUtil.b(vec3, (Vec3)wrapper2, world, entityPlayerSP, false, false, false, null))).isEntityHit() || !((RayTraceResult)wrapper4).getEntity().equals(wrapper3)) continue;
                        CrystalAuraActionCandidate candidate = new CrystalAuraActionCandidate(ExplosionType.Q, new DirectionalPosition(n3, n4, n2, 1), vec32, f3, 0.0);
                        candidate.D(CrystalAuraAction.ATTACKING_CRYSTAL);
                        candidate.J((Entity)wrapper3);
                        return candidate;
                    }
                    Object object5 = new BlockData(n3, n4, n2);
                    Object object6 = BlockPos.D(n3, n4, n2);
                    Object object7 = world.getBlockState((BlockPos)object6);
                    if (((Wrapper)object7).isNull()) continue;
                    wrapper3 = ((BlockState)object7).getBlock();
                    boolean bl8 = this.G_.X((BlockState)object7);
                    if (bl8) {
                        bl2 = true;
                    }
                    if (!BlockUtil.u((Block)(wrapper2 = world.getBlockByPos((blockData = ((BlockData)object5).R(EnumFacing.F$src$Lgg_vape_wrapper_impl_EnumFacing_$glfxl5())).D(), blockData.B(), blockData.G())))) {
                        crystalAuraPlacementSearchState.O();
                        continue;
                    }
                    boolean bl9 = bl7 = bl3 && BlockUtil.u((Block)wrapper3);
                    if (!bl8 && !bl7) continue;
                    int n13 = n3 - MathUtil.floor(entityPlayerSP.z());
                    int n14 = n2 - MathUtil.floor(entityPlayerSP.h());
                    int n8 = n13 > 0 ? 5 : (n12 = n13 < 0 ? 4 : -1);
                    int n16 = n14 > 0 ? 3 : (n14 < 0 ? 2 : -1);
                    int[] nArray = new int[]{0, n8, n16};
                    Object object9 = null;
                    EnumFacing enumFacing = null;
                    if (bl7) {
                        for (int blockData3 : nArray) {
                            if (blockData3 == -1) continue;
                            wrapper = EnumFacing.t()[blockData3];
                            object4 = ((BlockData)object5).R((EnumFacing)wrapper);
                            object3 = world.getBlockByPos(((Vec3i)object6).P(), ((Vec3i)object6).o(), ((Vec3i)object6).d());
                            object2 = world.getBlockByPos(((BlockData)object4).D(), ((BlockData)object4).B(), ((BlockData)object4).G());
                            if (BlockUtil.u((Block)object2) || !BlockUtil.u((Block)object3)) continue;
                            object9 = object4;
                            enumFacing = ((EnumFacing)wrapper).getOpposite();
                            break;
                        }
                    } else {
                        object9 = object5;
                        for (int n9 : nArray) {
                            if (n9 == -1) continue;
                            EnumFacing enumFacing2 = EnumFacing.t()[n9];
                            BlockData blockData2 = ((BlockData)object9).R(enumFacing2.getOpposite());
                            object3 = world.getBlockByPos(((BlockData)object9).D(), ((BlockData)object9).B(), ((BlockData)object9).G());
                            Block block = world.getBlockByPos(blockData2.D(), blockData2.B(), blockData2.G());
                            if (BlockUtil.u((Block)object3) || !BlockUtil.u(block)) continue;
                            enumFacing = enumFacing2.getOpposite();
                            break;
                        }
                    }
                    if (enumFacing == null) {
                        crystalAuraPlacementSearchState.t();
                        continue;
                    }
                    DirectionalPosition directionalPosition = new DirectionalPosition(n3, n4, n2, enumFacing.Y());
                    DirectionalPosition directionalPosition2 = new DirectionalPosition(((BlockData)object9).D(), ((BlockData)object9).B(), ((BlockData)object9).G(), enumFacing.Y());
                    Vec3 vec32 = this.G_.B(directionalPosition);
                    if (vec3.distanceTo(vec32) > 4.0) {
                        Vec3 vec33 = Vec3.create(entityPlayerSP.z(), vec32.getY(), entityPlayerSP.h());
                        wrapper = Vec3.create(entityPlayerSP.M(), vec32.getY(), entityPlayerSP.m$src$D$fwnne5());
                        double d16 = ((Vec3)wrapper).distanceTo(vec32);
                        double d17 = vec33.distanceTo(vec32);
                        if (!(d17 >= d16)) continue;
                        crystalAuraPlacementSearchState.R();
                        continue;
                    }
                    if (bl8) {
                        boolean bl10;
                        float f3 = this.k(this.G9, ExplosionType.Q, vec32, entityPlayerSP, world);
                        if (f3 > 0.0f && (double)f3 < d2) {
                            crystalAuraPlacementSearchState.G();
                        }
                        boolean bl11 = bl10 = !this.N(vec32, ExplosionType.Q, entityPlayerSP, world);
                        if (bl10 || (double)f3 < d2 || f3 <= f) continue;
                        object4 = AxisAlignedBB.create((double)n3 - d3, (double)n4 + 1.0, (double)n2 - d3, (double)n3 + 1.0 + d3, n4 + 2, (double)n2 + 1.0 + d3);
                        object3 = new ArrayList();
                        ArrayList nearbyEntities = (ArrayList)object3;
                        world.A().p((AxisAlignedBB)object4, arg_0 -> CrystalAuraPlacementSubModule.lambda$findObsidianAtYLevel$4(nearbyEntities, arg_0));
                        if (!((ArrayList)object3).isEmpty()) {
                            crystalAuraPlacementSearchState.O();
                            continue;
                        }
                        object2 = new BlockData(n3, n4, n2);
                        object = new PlacementTarget((BlockData)object2, enumFacing);
                        if (!ClutchPlacementPathUtils.P(vec3, world, ((PlacementTarget)object).k, ((PlacementTarget)object).G)) {
                            crystalAuraPlacementSearchState.R();
                            continue;
                        }
                        Vec3 vec34 = ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, (PlacementTarget)object, this.GD.V(), this.GD.x());
                        if (vec34 == null || vec3.distanceTo(vec34) > d) {
                            crystalAuraPlacementSearchState.R();
                            continue;
                        }
                        RayTraceResult rayTraceResult = RayTraceUtil.b(vec3, vec34, world, entityPlayerSP, false, false, false, null);
                        if (!rayTraceResult.isBlockHit() || !rayTraceResult.getBlockPos().equals(object6)) {
                            crystalAuraPlacementSearchState.R();
                            continue;
                        }
                        f = f3;
                        crystalAuraActionCandidate2 = new CrystalAuraActionCandidate(ExplosionType.Q, directionalPosition2, vec32, f3, 0.0);
                        if (!bl) continue;
                        RenderUtil.w(d4, d5, d6, n3, n4, n2, color);
                        continue;
                    }
                    BlockData blockData3 = new BlockData(n3, n4 - 1, n2);
                    wrapper = BlockPos.D(n3, n4 - 1, n2);
                    object4 = world.getBlockState((BlockPos)wrapper);
                    if (((Wrapper)object4).isNull() || BlockUtil.p(((BlockState)object4).getBlock())) {
                        crystalAuraPlacementSearchState.t();
                        continue;
                    }
                    object3 = vec32.C(0.0, 1.0, 0.0);
                    if (vec3.distanceTo((Vec3)object3) > 4.0) {
                        object2 = Vec3.create(entityPlayerSP.z(), ((Vec3)object3).getY() - 1.0, entityPlayerSP.h());
                        object = Vec3.create(entityPlayerSP.M(), ((Vec3)object3).getY() - 1.0, entityPlayerSP.m$src$D$fwnne5());
                        if (((Vec3)object2).distanceTo((Vec3)object3) >= ((Vec3)object).distanceTo((Vec3)object3)) {
                            crystalAuraPlacementSearchState.R();
                            continue;
                        }
                    }
                    boolean bl12 = false;
                    object = world.getBlockState((BlockPos)object6);
                    BlockUtil.z(world, (BlockPos)object6, blockState);
                    try {
                        Wrapper wrapper4;
                        float f4 = this.k(this.G9, ExplosionType.Q, vec32, entityPlayerSP, world);
                        if (f4 > 0.0f && (double)f4 < d2) {
                            crystalAuraPlacementSearchState.G();
                        }
                        boolean bl13 = !this.N(vec32, ExplosionType.Q, entityPlayerSP, world);
                        BlockData blockData4 = new BlockData(n3, n4, n2);
                        PlacementTarget placementTarget = new PlacementTarget(blockData4, EnumFacing.F$src$Lgg_vape_wrapper_impl_EnumFacing_$glfxl5());
                        boolean bl14 = ClutchPlacementPathUtils.P(vec3, world, placementTarget.k, placementTarget.G);
                        if (bl14) {
                            wrapper4 = ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, placementTarget, this.GD.V(), this.GD.x());
                            bl14 = wrapper4 != null && vec3.distanceTo((Vec3)wrapper4) <= d;
                        }
                        BlockUtil.z(world, (BlockPos)object6, (BlockState)object);
                        bl12 = true;
                        if (bl13 || (double)f4 < d2 || f4 <= f2) continue;
                        if (!bl14) {
                            crystalAuraPlacementSearchState.R();
                            continue;
                        }
                        wrapper4 = AxisAlignedBB.create((double)n3 - d3, (double)n4 + 1.0, (double)n2 - d3, (double)n3 + 1.0 + d3, n4 + 2, (double)n2 + 1.0 + d3);
                        ArrayList arrayList = new ArrayList();
                        world.A().p((AxisAlignedBB)wrapper4, arg_0 -> CrystalAuraPlacementSubModule.lambda$findObsidianAtYLevel$5(arrayList, arg_0));
                        if (!arrayList.isEmpty() || this.z(this.G9, directionalPosition, entityPlayerSP, world)) {
                            crystalAuraPlacementSearchState.O();
                            continue;
                        }
                        PlacementTarget placementTarget2 = new PlacementTarget(blockData3, enumFacing);
                        if (!ClutchPlacementPathUtils.P(vec3, world, placementTarget2.k, placementTarget2.G)) {
                            crystalAuraPlacementSearchState.R();
                            continue;
                        }
                        Vec3 vec35 = ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, placementTarget2, this.GD.V(), this.GD.x());
                        if (vec35 == null || vec3.distanceTo(vec35) > d) {
                            crystalAuraPlacementSearchState.R();
                            continue;
                        }
                        RayTraceResult rayTraceResult = RayTraceUtil.b(vec3, vec35, world, entityPlayerSP, false, false, false, null);
                        if (!rayTraceResult.isBlockHit() || rayTraceResult.getSideHit().Y() != enumFacing.Y() || !rayTraceResult.getBlockPos().equals(wrapper)) {
                            crystalAuraPlacementSearchState.R();
                            continue;
                        }
                        f2 = f4;
                        BlockCoordinate blockCoordinate = new BlockCoordinate(n3, n4 - 1, n2);
                        crystalAuraActionCandidate3 = new CrystalAuraActionCandidate(ExplosionType.Q, new DirectionalPosition(blockCoordinate, enumFacing.Y()), vec32, f4, 0.0);
                        crystalAuraActionCandidate3.H = true;
                        if (!bl) continue;
                        RenderUtil.w(d4, d5, d6, n3, n4 - 1, n2, color2);
                        continue;
                    }
                    catch (Throwable throwable) {
                        if (!bl12) {
                            BlockUtil.z(world, (BlockPos)object6, (BlockState)object);
                        }
                        throw throwable;
                    }
                }
            }
            if (crystalAuraActionCandidate2 != null || crystalAuraActionCandidate3 != null || i == 0 && bl5) break;
        }
        CrystalAuraActionCandidate crystalAuraActionCandidate4 = crystalAuraActionCandidate = crystalAuraActionCandidate2 != null ? crystalAuraActionCandidate2 : crystalAuraActionCandidate3;
        if (bl && crystalAuraActionCandidate != null) {
            Color color3 = new Color(255, 255, 255, 255);
            n3 = crystalAuraActionCandidate.s.B();
            n2 = crystalAuraActionCandidate.s.E();
            int n19 = crystalAuraActionCandidate.s.A();
            RenderUtil.u(n3, n2, n19, 1.0, 1.0, 1.0, 0.1, color3, null, d4, d5, d6);
        }
        boolean bl15 = this.Gb = crystalAuraActionCandidate == null && crystalAuraPlacementSearchState.T;
        this.U = crystalAuraActionCandidate == null ? (crystalAuraPlacementSearchState.T ? "low eff" : (crystalAuraPlacementSearchState.z ? "blocked" : (crystalAuraPlacementSearchState.X ? "out range" : (crystalAuraPlacementSearchState.h ? (this.Z.L().booleanValue() && n7 == -1 ? "no obsidian" : "no place") : (!this.Z.L().booleanValue() && !bl2 ? "no placement block" : "no candidate"))))) : null;
        return crystalAuraActionCandidate;
    }

    private void X(EntityLivingBase entityLivingBase) {
        if (entityLivingBase != null && entityLivingBase.isNotNull()) {
            this.Gr = entityLivingBase;
            this.G4 = System.currentTimeMillis();
        }
    }

    public CrystalAuraPlacementSubModule(Mod mod, String string) {
        super(mod, string);
        this.GQ = EntityTargetFilterValue.W(this);
        this.G2 = BooleanValue.create(this, "Center screen", true, "Renders crystal info on the center of your screen");
        this.Gw = BooleanValue.create(this, "Show target", false);
        this.A = ColorValue.b(this, "Target color", new Color(255, 40, 255), 50);
        this.K = ColorValue.L(this, "Attack color", new Color(169, 0, 255, 255));
        this.Gt = NumberValue.E(this, "Range", "#.#", "m", 0.0, 4.5, 6.0, "Range to check for targets");
        this.GK = NumberValue.create(this, "Max angle", "#", "", 1.0, 120.0, 360.0, 5.0, "Angle at which targets will be acquired and aimed at\n(From your cursor)");
        this.G0 = new ModeOption("Distance");
        this.c = new ModeOption("Yaw");
        this.H = new ModeOption("Armor");
        this.b = new ModeOption("Health");
        this.C = ModeValue.create((Object)this, "Target Mode", "How targets will be prioritized\nArmor will default to Distance for non player targets", (ModeSelection)this.G0, this.G0, this.c, this.H, this.b);
        this.v = RandomValue.G(this, "Delay", "#", "ms", 50.0, 50.0, 150.0, 500.0, 1.0, "Delay before activating");
        this.Gn = BooleanValue.create(this, "Anti suicide", true, "Prevents placing/breaking if it will result in fatal damage");
        this.P = NumberValue.create(this, "Max self damage", "#", "HP", 0.0, 19.0, 20.0, 1.0, "Maximum self damage allowed");
        this.Gq = new ModeOption("Rapid fire");
        this.S = new ModeOption("Predict");
        this.r = new ModeOption("None");
        this.GE = ModeValue.create((Object)this, "Optimization", "Controls crystal optimization behavior\nNone - No crystal optimization\nRapid fire - Crystals are broken and replaced in same tick when possible\nPredict - Predicts explosion timing and pre-removes crystal for faster placement(potentially unsafe)", (ModeSelection)this.r, this.Gq, this.S, this.r);
        this.D = NumberValue.create(this, "Rapid min efficiency", "#", "%", 0.0, 50.0, 100.0, 1.0, "Minimum damage efficiency (0-100%) to trigger rapid fire when placing/breaking crystals");
        this.GZ = BooleanValue.create(this, "Predict attack velocity", true, "Predicts target movement when calculating damage after successfully attacking");
        this.Z = BooleanValue.create(this, "Auto obsidian", false, "Automatically places obsidian to place crystals");
        this.Gh = NumberValue.create(this, "Min efficiency", "#", "%", 0.0, 50.0, 100.0, 1.0, "Minimum damage efficiency (0-100%) for placing and breaking crystals");
        this.GD = RotationManager.b;
        this.Go = SharedModuleControlClaims.I;
        this.V = SharedModuleControlClaims.a;
        this.I = Vape.INSTANCE.getFriendManager();
        this.GC = new TimerUtil();
        this.G_ = (CrystalAura)mod;
        this.Gw.K(this.A, this.K);
        this.GE.f(this.Gq, this.D);
        this.addValue(this.GQ, this.C, this.Gt, this.GK, this.L, this.Gh, this.v, this.P, this.Z, this.Gn, this.GE, this.D, this.GZ, this.G2, this.Gw, this.A, this.K);
    }

    private static void lambda$onClientTick$0(ArrayList arrayList, Object object) {
        if (MappedClasses.Ze.isAssignableFrom(object.getClass())) {
            arrayList.add(new Entity(object));
        }
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        int n;
        Object object;
        WorldClient worldClient = eventPreTick.getWorld();
        int n2 = ExplosionType.R();
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        GuiScreen guiScreen = eventPreTick.getCurrentScreen();
        boolean bl = this.GC.hasTimeElapsed((long)this.v.B());
        if (worldClient.isNull() || entityPlayerSP.isNull() || !guiScreen.isNull() || this.J) {
            this.n$src$V$1jw7x72();
            return;
        }
        if (this.Gc && this.Gk) {
            this.Gc = false;
        }
        this.Gk = this.Gc;
        this.t = this.G_.Q(entityPlayerSP);
        if (this.t == -1) {
            this.U = "no crystal";
            this.n$src$V$1jw7x72();
            return;
        }
        if (this.s != null) {
            Object object2;
            Wrapper wrapper;
            Object object3;
            Object object4;
            Wrapper wrapper2;
            Object object5;
            CrystalAuraAction crystalAuraAction = this.s.R();
            if (crystalAuraAction == CrystalAuraAction.PLACING_OBSIDIAN || crystalAuraAction == CrystalAuraAction.PLACING_CRYSTAL) {
                object = this.s.s;
                if (crystalAuraAction == CrystalAuraAction.PLACING_CRYSTAL) {
                    object5 = new BlockCoordinate(((BlockCoordinate)object).B(), ((BlockCoordinate)object).E() + 1, ((BlockCoordinate)object).A());
                    wrapper2 = AxisAlignedBB.create((double)((BlockCoordinate)object5).B() - 0.5, (double)((BlockCoordinate)object5).E() - 0.01, (double)((BlockCoordinate)object5).A() - 0.5, (double)((BlockCoordinate)object5).B() + 0.5, (double)((BlockCoordinate)object5).E() + 2.0, (double)((BlockCoordinate)object5).A() + 0.5);
                    object4 = new ArrayList();
                    ArrayList collidingEntities = (ArrayList)object4;
                    worldClient.A().p((AxisAlignedBB)wrapper2, arg_0 -> CrystalAuraPlacementSubModule.lambda$onClientTick$0(collidingEntities, arg_0));
                    if (!((ArrayList)object4).isEmpty()) {
                        this.s.D(CrystalAuraAction.ATTACKING_CRYSTAL);
                        this.s.J((Entity)((ArrayList)object4).get(0));
                    }
                }
                if (this.s.R() != CrystalAuraAction.ATTACKING_CRYSTAL) {
                    object5 = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
                    wrapper2 = EnumFacing.T(((DirectionalPosition)object).X());
                    object4 = new BlockData(((BlockCoordinate)object).B(), ((BlockCoordinate)object).E(), ((BlockCoordinate)object).A());
                    object3 = new PlacementTarget((BlockData)object4, (EnumFacing)wrapper2);
                    if (!ClutchPlacementPathUtils.P((Vec3)object5, worldClient, (BlockData)object4, (EnumFacing)wrapper2) || ClutchPlacementPathUtils.D(entityPlayerSP, worldClient, (Vec3)object5, (PlacementTarget)object3, this.GD.V(), this.GD.x()) == null) {
                        this.U = "out range";
                        this.s = null;
                        this.p = false;
                    }
                }
            }
            if (this.s != null && this.s.R() == CrystalAuraAction.ATTACKING_CRYSTAL) {
                object = this.s.A();
                if (object == null || ((Wrapper)object).isNull() || ((Entity)object).M$src$Z$ff28xj()) {
                    this.s = null;
                    this.p = false;
                } else {
                    Entity entity;
                    object5 = this.s.s;
                    wrapper2 = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
                    if (ClutchPlacementPathUtils.P((Vec3)wrapper2, worldClient, (BlockData)(object4 = new BlockData(((BlockCoordinate)object5).B(), ((BlockCoordinate)object5).E(), ((BlockCoordinate)object5).A())), (EnumFacing)(object3 = EnumFacing.T(((DirectionalPosition)object5).X()))) && ((RayTraceResult)(wrapper = RayTraceUtil.b((Vec3)wrapper2, (Vec3)(object2 = this.G_.B((DirectionalPosition)object5)), worldClient, entityPlayerSP, false, true, false, null))).isEntityHit() && !(entity = ((RayTraceResult)wrapper).getEntity()).isInstance(MappedClasses.Ze) && entity.n$src$Z$fx7gig()) {
                        this.U = "blocked";
                        this.s = null;
                        this.p = false;
                    }
                    if (((Vec3)wrapper2).distanceTo(((Vec3d)(object2 = RotationUtil.M((Vec3)wrapper2, ((Entity)object).R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0))).n()) > 3.0) {
                        this.U = "out range";
                        this.s = null;
                        this.p = false;
                    }
                }
            }
            if (this.s != null && this.s.R() == CrystalAuraAction.PLACING_CRYSTAL) {
                int n3;
                int n4;
                object = this.s.s;
                object5 = EnumFacing.T(((DirectionalPosition)object).X());
                int n5 = ((BlockCoordinate)object).B();
                object2 = new BlockData(n5, n4 = ((BlockCoordinate)object).E(), n3 = ((BlockCoordinate)object).A());
                wrapper = worldClient.getBlockState(BlockPos.create(((BlockData)object2).D(), ((BlockData)object2).B(), ((BlockData)object2).G()));
                if (!this.G_.X((BlockState)wrapper)) {
                    this.U = "no place";
                    this.s = null;
                    this.p = false;
                }
            }
        }
        this.I(entityPlayerSP, worldClient);
        if (this.G9 == null) {
            this.Gb = false;
            this.U = "no target";
            this.n$src$V$1jw7x72();
            return;
        }
        if (this.s == null && bl) {
            this.s = this.D(entityPlayerSP, worldClient, 4.0, false);
            if (this.s == null) {
                this.n$src$V$1jw7x72();
                if (this.U == null) {
                    this.U = "no candidate";
                }
                return;
            }
            this.Gb = false;
            this.U = null;
            if (this.s.H) {
                this.s.D(CrystalAuraAction.PLACING_OBSIDIAN);
            } else {
                this.s.D(CrystalAuraAction.PLACING_CRYSTAL);
            }
        }
        if (this.s == null) {
            return;
        }
        if (!this.Go.U(this.G_) && !this.Go.h(this.G_, true)) {
            this.U = "aim lock";
            return;
        }
        object = this.s.R();
        if (object == CrystalAuraAction.PLACING_OBSIDIAN) {
            n = this.G_.z(entityPlayerSP);
            if (n == -1) {
                this.U = "no obby";
                this.s = null;
                this.p = false;
                return;
            }
        } else {
            n = object == CrystalAuraAction.PLACING_CRYSTAL ? this.t : this.t;
        }
        if (entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v() != n) {
            if (this.Gm == -1) {
                this.Gm = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            }
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
        }
        this.q(entityPlayerSP, worldClient);
    }

    private boolean N(Vec3 vec3, ExplosionType explosionType, EntityPlayerSP entityPlayerSP, World world) {
        return this.G_.n(vec3, explosionType, entityPlayerSP, world, this.Gn.L(), ((Double)this.P.K()).floatValue());
    }

    public void K() {
        if (this.Gv != null) {
            RotationManager.b.v(this.Gv);
            if (this.GD.w() == this.Gv) {
                this.Gv.w(false);
                this.Gv.u(true);
                if (this.Gv instanceof AdaptiveRotationController) {
                    ((AdaptiveRotationController)this.Gv).b(true);
                }
            }
            this.Gv = null;
        }
        this.Go.X(this.G_);
        if (this.Gm != -1) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            if (!entityPlayerSP.isNull()) {
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.Gm);
            }
            this.Gm = -1;
        }
        this.G9 = null;
        this.s = null;
        this.p = false;
        this.Gk = false;
        this.Gc = false;
        this.J = false;
        this.GC.reset();
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}
