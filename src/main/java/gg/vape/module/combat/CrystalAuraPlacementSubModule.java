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
    private final NumberValue range;
    private FixedRotationController rotationController;
    private final ModeOption distanceMode;
    private final RotationControlClaim rotationClaim;
    public final ModeValue targetMode;
    private final BooleanValue predictAttackVelocity;
    private final ModeValue optimizationMode;
    private final RotationManager rotationManager;
    private final BooleanValue antiSuicide;
    private int crystalSlot = -1;
    private EntityLivingBase lastTarget;
    private CrystalAuraActionCandidate currentAction;
    public final ModeOption yawMode;
    private final MouseOverUpdateControlClaim mouseOverClaim;
    private final ColorValue attackColor;
    private static final Color OBSIDIAN_COLOR;
    private int pendingRemoveEntityId = -1;
    private final NumberValue rapidMinEfficiency;
    private CrystalAura crystalAura;
    public final EntityTargetFilterValue targetFilter;
    private final TimerUtil delayTimer;
    private final NumberValue aimSpeed = NumberValue.E(this, "Aim speed", "#.#", "", 1.0, 4.5, 10.0, "Aim rotation speed");
    private int previousSlot = -1;
    private final ColorValue targetColor;
    private final ModeOption predictMode;
    private long lastTargetTime;
    private final BooleanValue showTarget;
    private final ModeOption noneMode;
    private final FriendManager friendManager;
    public final ModeOption healthMode;
    private final RandomValue activationDelay;
    private static final Color ATTACK_COLOR;
    private final BooleanValue centerScreen;
    private static final Color PLACE_CRYSTAL_COLOR;
    private boolean placementSent;
    private final ModeOption rapidFireMode;
    private final BooleanValue autoObsidian;
    private boolean toggledOff;
    private EntityLivingBase target;
    public final ModeOption armorMode;
    private boolean attackedLastTick;
    private final NumberValue minEfficiency;
    public final NumberValue maxAngle;
    private final NumberValue maxSelfDamage;
    private static final long TARGET_MEMORY_MS = 1200L;
    private String statusLabel;
    private boolean lowEfficiency;
    private boolean attacked;

    private void validateAction(EntityPlayerSP entityPlayerSP, World world) {
        if (this.currentAction != null && this.rotationController != null && this.rotationManager.w() == this.rotationController) {
            Object object;
            Wrapper wrapper;
            double d;
            RayTraceResult rayTraceResult = this.rotationManager.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
            CrystalAuraAction crystalAuraAction = this.currentAction.R();
            DirectionalPosition directionalPosition = this.currentAction.s;
            int n = directionalPosition.B();
            int n2 = directionalPosition.E();
            int n3 = directionalPosition.A();
            Vec3 vec3 = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
            Vec3 vec32 = this.crystalAura.B(directionalPosition);
            boolean bl = crystalAuraAction != CrystalAuraAction.ATTACKING_CRYSTAL;
            double d2 = d = bl ? 4.0 : 4.0;
            if (vec3.distanceTo(vec32) > d) {
                Vec3 vec33 = Vec3.create(entityPlayerSP.z(), vec32.getY(), entityPlayerSP.h());
                Vec3 vec34 = Vec3.create(entityPlayerSP.M(), vec32.getY(), entityPlayerSP.m$src$D$fwnne5());
                double d3 = vec34.distanceTo(vec32);
                double d4 = vec33.distanceTo(vec32);
                if (d4 >= d3 || d4 > 4.5) {
                    this.currentAction = null;
                    this.placementSent = false;
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
                        this.currentAction = null;
                        this.placementSent = false;
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
                        if (!ClutchPlacementPathUtils.P(vec3, world, (BlockData)object2, (EnumFacing)object3) && ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, (PlacementTarget)object4, this.rotationManager.V(), this.rotationManager.x()) == null) {
                            this.currentAction = null;
                            this.placementSent = false;
                        }
                    }
                }
            } else if (crystalAuraAction == CrystalAuraAction.PLACING_CRYSTAL) {
                wrapper = AxisAlignedBB.create(n, n2, n3, (double)n + 1.0, (double)n2 + 2.0, (double)n3 + 1.0);
                ArrayList<Entity> entities = new ArrayList<Entity>();
                world.A().p((AxisAlignedBB)wrapper, arg_0 -> CrystalAuraPlacementSubModule.lambda$handleExplosive$7(entities, arg_0));
                if (!entities.isEmpty() && this.wouldIntersect(this.target, directionalPosition, entityPlayerSP, world)) {
                    boolean bl6 = false;
                    Iterator<Entity> iterator = entities.iterator();
                    while (iterator.hasNext()) {
                        Entity entity = (Entity)iterator.next();
                        if (!entity.isInstance(MappedClasses.Ze)) continue;
                        this.currentAction.D(CrystalAuraAction.ATTACKING_CRYSTAL);
                        bl6 = entities.size() == 1;
                    }
                    if (!bl6) {
                        this.currentAction = null;
                        this.placementSent = false;
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
                        if (!ClutchPlacementPathUtils.P(vec3, world, blockData, enumFacing) || ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, placementTarget, this.rotationManager.V(), this.rotationManager.x()) == null) {
                            this.currentAction = null;
                            this.placementSent = false;
                        }
                    }
                } else if (rayTraceResult.isEntityHit() && rayTraceResult.getEntity().isInstance(MappedClasses.Ze)) {
                    this.currentAction.D(CrystalAuraAction.ATTACKING_CRYSTAL);
                    crystalAuraAction = CrystalAuraAction.ATTACKING_CRYSTAL;
                } else {
                    BlockData blockData = new BlockData(directionalPosition.B(), directionalPosition.E(), directionalPosition.A());
                    EnumFacing enumFacing = EnumFacing.T(directionalPosition.X());
                    PlacementTarget placementTarget = new PlacementTarget(blockData, enumFacing);
                    if (!ClutchPlacementPathUtils.P(vec3, world, blockData, enumFacing) || ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, placementTarget, this.rotationManager.V(), this.rotationManager.x()) == null) {
                        this.currentAction = null;
                        this.placementSent = false;
                    }
                }
            }
            if (crystalAuraAction == CrystalAuraAction.ATTACKING_CRYSTAL) {
                if (this.target == null || this.target.M$src$Z$ff28xj() || this.target.w$src$F$15l9epb() <= 0.0f) {
                    this.currentAction = null;
                    this.placementSent = false;
                    return;
                }
                if (rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.entity()) && (wrapper = rayTraceResult.getEntity()).isInstance(MappedClasses.Ze)) {
                    object = Minecraft.gameSettings().F();
                    if (((KeyBinding)object).u() || ((KeyBinding)object).isPressed()) {
                        KeyBinding.setKeyBindState((KeyBinding)object, false);
                    }
                    KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                    if (((ModeSelection)this.optimizationMode.K()).equals(this.rapidFireMode) && this.currentAction.Q() >= (Double)this.rapidMinEfficiency.K() / 100.0) {
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

    private CrystalAuraActionCandidate findAction(EntityPlayerSP entityPlayerSP, World world, double d, boolean bl) {
        int n = MathUtil.floor(entityPlayerSP.N());
        CrystalAuraActionCandidate crystalAuraActionCandidate = this.findActionAtYLevel(entityPlayerSP, world, d, bl, n);
        if (crystalAuraActionCandidate != null) {
            return crystalAuraActionCandidate;
        }
        String string = this.statusLabel;
        int n2 = n + 1;
        CrystalAuraActionCandidate crystalAuraActionCandidate2 = this.findActionAtYLevel(entityPlayerSP, world, d, bl, n2);
        if (crystalAuraActionCandidate2 != null) {
            return crystalAuraActionCandidate2;
        }
        if (this.statusPriority(string) > this.statusPriority(this.statusLabel)) {
            this.statusLabel = string;
        }
        return null;
    }

    static {
        ATTACK_COLOR = new Color(255, 0, 0, 25);
        PLACE_CRYSTAL_COLOR = new Color(0, 255, 255, 25);
        OBSIDIAN_COLOR = new Color(0, 0, 255, 25);
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
                    if (this.predictAttackVelocity.L().booleanValue() && entity.isNotNull() && entity.isInstance(MappedClasses.zm) && entity.V$src$I$fk0dv5() <= 13) {
                        this.attacked = true;
                    }
                    if (this.currentAction != null && this.currentAction.R() == CrystalAuraAction.ATTACKING_CRYSTAL && entity.isNotNull() && entity.isInstance(MappedClasses.Ze)) {
                        if (((ModeSelection)this.optimizationMode.K()).equals(this.predictMode) && !Minecraft.V()) {
                            this.pendingRemoveEntityId = entity.S();
                        }
                        this.currentAction = null;
                        this.placementSent = false;
                    }
                }
            }
            if (packet.isInstance(MappedClasses.YB)) {
                CrystalAuraAction crystalAuraAction;
                CrystalAuraActionCandidate crystalAuraActionCandidate = this.currentAction;
                if (crystalAuraActionCandidate != null) {
                    CrystalAuraAction action = this.currentAction.R();
                    if (action == (crystalAuraAction = CrystalAuraAction.PLACING_OBSIDIAN) || action == CrystalAuraAction.PLACING_CRYSTAL) {
                        this.placementSent = true;
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
            CrystalAuraActionCandidate crystalAuraActionCandidate = this.currentAction;
            CrystalAuraAction crystalAuraAction2 = crystalAuraActionCandidate.R();
            CrystalAuraAction crystalAuraAction3 = crystalAuraAction2;
            if (crystalAuraAction3 == (crystalAuraAction = CrystalAuraAction.PLACING_OBSIDIAN)) {
                this.placementSent = true;
            }
        }
    }

    private float computeDamage(EntityLivingBase entityLivingBase, ExplosionType explosionType, Vec3 vec3, EntityPlayerSP entityPlayerSP, World world) {
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
            if (this.predictAttackVelocity.L().booleanValue() && this.attacked && entityLivingBase.equals(this.target)) {
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

    private void updateTarget(EntityPlayerSP entityPlayerSP, World world) {
        ArrayList<Entity> arrayList = new ArrayList<Entity>();
        if (this.crystalAura.W()) {
            return;
        }
        List list = world.z();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            EntityLivingBase entityLivingBase;
            Object e = iterator.next();
            Entity entity = new Entity(e);
            if (ClientSettings.H && entity.isInstance(MappedClasses.FT) || !entity.isInstance(MappedClasses.zm) || !this.isValidTarget(entityLivingBase = new EntityLivingBase(e), entityPlayerSP)) continue;
            arrayList.add(entityLivingBase);
        }
        if (this.targetMode.K() == this.yawMode) {
            arrayList.sort(new EntityAngleComparator());
        } else if (this.targetMode.K() == this.distanceMode) {
            arrayList.sort(new EntityDistanceComparator());
        } else if (this.targetMode.K() == this.armorMode) {
            arrayList.sort(new EntityEquipmentValueComparator());
        } else if (this.targetMode.K() == this.healthMode) {
            arrayList.sort(new EntityHealthComparator());
        }
        if (!arrayList.isEmpty()) {
            EntityLivingBase target = (EntityLivingBase)arrayList.get(0);
            this.target = target;
            this.rememberTarget(target);
        } else {
            this.reset();
        }
    }

    @Override
    public void onEnable() {
    }

    @Override
    public ModDisplayInfo J() {
        if (!this.centerScreen.L().booleanValue()) {
            return null;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return null;
        }
        int n = this.crystalAura.U(entityPlayerSP);
        Color color = new Color(255, 20, 20);
        if (n >= 32) {
            color = new Color(2, 190, 58);
        } else if (n >= 16) {
            color = new Color(255, 249, 18);
        }
        String string = this.currentAction != null ? "\u00a7f\u00a7l" : "\u00a77";
        String string2 = (this.currentAction == null ? "\u00a7r" : "\u00a75\u00a7l") + n;
        if (this.currentAction == null && this.statusLabel != null && ("no target".equals(this.statusLabel) || "no crystals".equals(this.statusLabel) || this.getActiveTarget() != null)) {
            string2 = string2 + " \u00a7c[" + this.statusLabel + "]";
        }
        String string3 = " " + string + "(CrystalAura)";
        return new ModDisplayInfo(string2, color, string3);
    }

    private void reset() {
        this.rememberTarget(this.target);
        this.target = null;
        this.currentAction = null;
        this.placementSent = false;
        this.attackedLastTick = false;
        this.attacked = false;
        this.delayTimer.reset();
        if (this.rotationController != null) {
            this.rotationController.k(true);
            this.rotationController.z(true);
            this.rotationController.U(true);
            this.rotationController.t(0.0f);
            this.rotationController.Y(5.0f);
            RotationManager.b.v(this.rotationController);
        }
        if (this.previousSlot != -1) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            if (!entityPlayerSP.isNull()) {
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.previousSlot);
            }
            this.previousSlot = -1;
        }
        if (this.rotationManager.w() == null || this.rotationManager.w() != this.rotationController || this.rotationController != null && !this.rotationController.v() && this.rotationController.V$src$Z$lb4tvc()) {
            this.rotationController = null;
            this.rotationClaim.X(this.crystalAura);
            if (this.toggledOff) {
                this.toggledOff = false;
                this.crystalAura.s(false, true);
            }
        }
    }

    @Override
    public String r() {
        if (this.currentAction != null) {
            return this.currentAction.R().toString();
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

    private int statusPriority(String string) {
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

    private boolean isInRange(EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP) {
        return (double)entityLivingBase.getDistanceToEntity(entityPlayerSP) <= (Double)this.range.K();
    }

    private boolean isAlive(EntityLivingBase entityLivingBase) {
        return entityLivingBase != null && entityLivingBase.isNotNull() && !entityLivingBase.M$src$Z$ff28xj() && entityLivingBase.w$src$F$15l9epb() > 0.0f;
    }

    private static void lambda$handleExplosive$6(ArrayList arrayList, Object object) {
        arrayList.add(new Entity(object));
    }

    public boolean isValidTarget(EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP) {
        if (entityLivingBase.isNull()) {
            return false;
        }
        if (entityLivingBase.equals(entityPlayerSP)) {
            return false;
        }
        if (entityLivingBase.w$src$F$15l9epb() <= 0.0f || entityLivingBase.M$src$Z$ff28xj()) {
            return false;
        }
        if (!this.isInRange(entityLivingBase, entityPlayerSP)) {
            return false;
        }
        if (RotationUtil.a(entityPlayerSP, entityLivingBase) > ((Double)this.maxAngle.K()).intValue() / 2) {
            return false;
        }
        FriendEntry friendEntry = this.friendManager.O(entityLivingBase.getName());
        if (friendEntry != null && !friendEntry.c()) {
            return false;
        }
        if (entityLivingBase.equals(entityPlayerSP.S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12())) {
            return false;
        }
        return this.targetFilter.c(entityLivingBase);
    }

    private static void lambda$onClientTickPost$1(ArrayList arrayList, Object object) {
        if (MappedClasses.Ze.isAssignableFrom(object.getClass())) {
            arrayList.add(new Entity(object));
        }
    }

    @EventHandler
    public void onWorldChange(EventWorldChange eventWorldChange) {
        this.releaseControl();
        this.target = null;
        this.lastTarget = null;
        this.lastTargetTime = 0L;
        this.lowEfficiency = false;
        this.statusLabel = null;
        this.currentAction = null;
        this.placementSent = false;
    }

    private AxisAlignedBB crystalBoundingBox(BlockCoordinate blockCoordinate) {
        double d = blockCoordinate.B();
        double d2 = blockCoordinate.E() + 1;
        double d3 = blockCoordinate.A();
        return AxisAlignedBB.create(d, d2, d3, d + 1.0, d2 + 2.0, d3 + 1.0);
    }

    private static void lambda$handleExplosive$7(ArrayList arrayList, Object object) {
        arrayList.add(new Entity(object));
    }

    private EntityLivingBase getActiveTarget() {
        EntityLivingBase entityLivingBase;
        if (this.isAlive(this.target)) {
            this.rememberTarget(this.target);
            entityLivingBase = this.target;
        } else {
            entityLivingBase = this.isAlive(this.lastTarget) && System.currentTimeMillis() - this.lastTargetTime <= 1200L ? this.lastTarget : null;
        }
        if (entityLivingBase == null) {
            this.lastTarget = null;
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
        this.releaseControl();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        EntityPlayerSP entityPlayerSP = eventRender3D.getThePlayer();
        WorldClient worldClient = eventRender3D.getWorld();
        EntityLivingBase entityLivingBase = this.getActiveTarget();
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
            if (this.currentAction != null && this.currentAction.s != null) {
                Color color = this.currentAction.R() == CrystalAuraAction.ATTACKING_CRYSTAL ? ATTACK_COLOR : (this.currentAction.R() == CrystalAuraAction.PLACING_CRYSTAL ? PLACE_CRYSTAL_COLOR : OBSIDIAN_COLOR);
                try {
                    RenderUtil.w(d, d2, d3, this.currentAction.s.B(), this.currentAction.s.E(), this.currentAction.s.A(), color);
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
        if (this.showTarget.L().booleanValue() && entityLivingBase != null && Minecraft.currentScreen().isNull()) {
            float f = entityLivingBase.isInstance(MappedClasses.Yl) || entityLivingBase.isInstance(MappedClasses.lG) ? 0.7f : entityLivingBase.f$src$F$fst3ac();
            MutableColor mutableColor = this.currentAction != null && this.currentAction.R() == CrystalAuraAction.ATTACKING_CRYSTAL ? this.attackColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3() : this.targetColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            GuiRenderPrimitives.R(entityLivingBase.c(), entityLivingBase.A(), entityLivingBase.Z(), 50.0f, f, entityLivingBase.Y(), mutableColor);
        }
    }

    @EventHandler
    public void onPostTick(EventPostTick eventPostTick) {
        WorldClient worldClient = eventPostTick.getWorld();
        int n = ExplosionType.q();
        EntityPlayerSP entityPlayerSP = eventPostTick.getThePlayer();
        if (this.pendingRemoveEntityId != -1) {
            Entity entity = ((World)worldClient).V(this.pendingRemoveEntityId);
            if (entity.isNotNull()) {
                worldClient.M(entity);
            }
            this.pendingRemoveEntityId = -1;
        }
        if (worldClient.isNull() || entityPlayerSP.isNull() || this.currentAction == null) {
            return;
        }
        EntityPlayerSP entityPlayerSP2 = entityPlayerSP;
        CrystalAuraPlacementSubModule crystalAuraPlacementSubModule = this;
        crystalAuraPlacementSubModule.aimAtAction(entityPlayerSP2);
        if (!this.placementSent) {
            return;
        }
        CrystalAuraAction action = this.currentAction.R();
        if (action == CrystalAuraAction.PLACING_OBSIDIAN) {
            DirectionalPosition directionalPosition = this.currentAction.s;
            EnumFacing enumFacing = EnumFacing.T(directionalPosition.X());
            BlockData blockData = new BlockData(directionalPosition.B(), directionalPosition.E(), directionalPosition.A()).R(enumFacing);
            BlockPos blockPos = BlockPos.create(blockData.D(), blockData.B(), blockData.G());
            BlockState blockState = worldClient.getBlockState(blockPos);
            if (blockState.isNotNull() && blockState.getBlock().U().toLowerCase().contains("obsidian")) {
                this.currentAction.D(CrystalAuraAction.PLACING_CRYSTAL);
                BlockCoordinate blockCoordinate = new BlockCoordinate(blockPos.P(), blockPos.o(), blockPos.d());
                this.currentAction.s = new DirectionalPosition(blockCoordinate, 1);
                EntityPlayerSP entityPlayerSP3 = entityPlayerSP;
                CrystalAuraPlacementSubModule crystalAuraPlacementSubModule2 = this;
                crystalAuraPlacementSubModule2.aimAtAction(entityPlayerSP3);
                this.placementSent = false;
            } else {
                this.currentAction = null;
                this.placementSent = false;
            }
        } else if (action == CrystalAuraAction.PLACING_CRYSTAL) {
            DirectionalPosition directionalPosition = this.currentAction.s;
            BlockCoordinate blockCoordinate = new BlockCoordinate(directionalPosition.B(), directionalPosition.E() + 1, directionalPosition.A());
            AxisAlignedBB axisAlignedBB = AxisAlignedBB.create((double)blockCoordinate.B() - 0.5, blockCoordinate.E(), (double)blockCoordinate.A() - 0.5, (double)blockCoordinate.B() + 0.5, (double)blockCoordinate.E() + 2.0, (double)blockCoordinate.A() + 0.5);
            ArrayList arrayList = new ArrayList();
            worldClient.A().p(axisAlignedBB, arg_0 -> CrystalAuraPlacementSubModule.lambda$onClientTickPost$1(arrayList, arg_0));
            if (!arrayList.isEmpty()) {
                this.currentAction.D(CrystalAuraAction.ATTACKING_CRYSTAL);
                this.currentAction.J((Entity)arrayList.get(0));
                this.placementSent = false;
            } else {
                Vec3 vec3;
                Vec3 vec32 = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
                double d = vec32.distanceTo(vec3 = this.crystalAura.B(this.currentAction.s));
                if (d > 3.0) {
                    Vec3 vec33 = Vec3.create(entityPlayerSP.z(), vec3.getY(), entityPlayerSP.h());
                    Vec3 vec34 = Vec3.create(entityPlayerSP.M(), vec3.getY(), entityPlayerSP.m$src$D$fwnne5());
                    double d2 = vec34.distanceTo(vec3);
                    double d3 = vec33.distanceTo(vec3);
                    if (d3 > d2) {
                        this.currentAction = null;
                        this.placementSent = false;
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

    private boolean wouldIntersect(EntityLivingBase entityLivingBase, BlockCoordinate blockCoordinate, EntityPlayerSP entityPlayerSP, World world) {
        boolean bl = false;
        AxisAlignedBB axisAlignedBB = this.crystalBoundingBox(blockCoordinate);
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
            if (this.predictAttackVelocity.L().booleanValue() && this.attacked && entityLivingBase.equals(this.target)) {
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

    private void aimAtAction(EntityPlayerSP entityPlayerSP) {
        Vec3 vec3;
        Object object;
        int n = ExplosionType.R();
        if (!this.rotationClaim.U(this.crystalAura)) {
            return;
        }
        if (this.rotationController == null) {
            this.rotationController = new AdaptiveRotationController();
        }
        this.rotationController.Y(((Double)this.aimSpeed.K()).floatValue());
        this.rotationController.u(false);
        this.rotationController.w(true);
        this.rotationController.k(true);
        this.rotationController.t(0.0f);
        this.rotationController.U(true);
        this.rotationController.s(true);
        if (this.rotationController instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)this.rotationController).b(false);
        }
        float f = this.rotationManager.V();
        float f2 = this.rotationManager.x();
        Vec3 vec32 = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
        DirectionalPosition directionalPosition = this.currentAction.s;
        int n2 = directionalPosition.X();
        EnumFacing enumFacing = EnumFacing.T(n2);
        if (this.currentAction.R() == CrystalAuraAction.ATTACKING_CRYSTAL) {
            object = this.currentAction.s;
            BlockCoordinate blockCoordinate = new BlockCoordinate(((BlockCoordinate)object).B(), ((BlockCoordinate)object).E() + 1, ((BlockCoordinate)object).A());
            AxisAlignedBB axisAlignedBB = AxisAlignedBB.create((double)blockCoordinate.B() - 0.5, blockCoordinate.E(), (double)blockCoordinate.A() - 0.5, (double)blockCoordinate.B() + 0.5, (double)blockCoordinate.E() + 2.0, (double)blockCoordinate.A() + 0.5);
            ArrayList arrayList = new ArrayList();
            entityPlayerSP.getWorld().A().p(axisAlignedBB, arg_0 -> CrystalAuraPlacementSubModule.lambda$getAimJob$2(arrayList, arg_0));
            boolean bl = ClutchPlacementPathUtils.P(vec32, entityPlayerSP.getWorld(), new BlockData(((BlockCoordinate)object).B(), ((BlockCoordinate)object).E(), ((BlockCoordinate)object).A()), enumFacing);
            vec3 = !arrayList.isEmpty() && !bl ? RotationUtil.M(vec32, ((Entity)arrayList.get(0)).R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0).n() : this.crystalAura.B(directionalPosition);
        } else {
            object = new PlacementTarget(new BlockData(directionalPosition.B(), directionalPosition.E(), directionalPosition.A()), enumFacing);
            Vec3 vec33 = ClutchPlacementPathUtils.D(entityPlayerSP, entityPlayerSP.getWorld(), vec32, (PlacementTarget)object, f, f2);
            vec3 = vec33 != null ? vec33 : this.crystalAura.B(directionalPosition);
        }
        object = RotationVectorMath.d(vec32, vec3, f, f2);
        this.rotationController.b((RotationAngles)object);
        if (this.rotationManager.w() != this.rotationController) {
            // empty if block
        }
        this.rotationManager.S(this.rotationController);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private CrystalAuraActionCandidate findActionAtYLevel(EntityPlayerSP entityPlayerSP, World world, double d, boolean bl, int n) {
        CrystalAuraActionCandidate crystalAuraActionCandidate;
        int n2;
        int n3;
        ItemStack itemStack;
        if (this.target == null) {
            this.lowEfficiency = false;
            this.statusLabel = null;
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
        double d2 = (double)((Double)this.minEfficiency.K()).intValue() / 100.0;
        double d3 = 0.1;
        CrystalAuraPlacementSearchState crystalAuraPlacementSearchState = new CrystalAuraPlacementSearchState();
        boolean bl3 = false;
        BlockState blockState = null;
        int n7 = this.crystalAura.z(entityPlayerSP);
        if (n7 != -1 && !(itemStack = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(n7)).isNull()) {
            blockState = BlockUtil.E(itemStack);
        }
        if (this.autoObsidian.L().booleanValue()) {
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
                    if (!this.crystalAura.X(blockState2)) continue;
                    bl2 = true;
                }
            }
        }
        double d4 = RenderManager.getInterpolatedRenderPosX();
        double d5 = RenderManager.getInterpolatedRenderPosY();
        double d6 = RenderManager.getInterpolatedRenderPosZ();
        double d7 = entityPlayerSP.z();
        double d8 = entityPlayerSP.h();
        double d9 = this.target.z();
        double d10 = this.target.h();
        double d11 = d9 - d7;
        double d12 = d10 - d8;
        Color color = new Color(0, 0, 255, 25);
        Color color2 = new Color(9, 255, 0, 25);
        double d13 = entityPlayerSP.getDistanceToEntity(this.target);
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
                        if (!(vec3.distanceTo(vec32) <= 4.0) || !MathUtil.e(((Entity)wrapper3).z(), (double)n3, (double)(n3 + 1)) || !MathUtil.e(((Entity)wrapper3).h(), (double)n2, (double)(n2 + 1)) || !this.isSafeDamage(vec32, ExplosionType.Q, entityPlayerSP, world)) continue;
                        float f3 = this.computeDamage(this.target, ExplosionType.Q, vec32, entityPlayerSP, world);
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
                    boolean bl8 = this.crystalAura.X((BlockState)object7);
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
                    Vec3 vec32 = this.crystalAura.B(directionalPosition);
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
                        float f3 = this.computeDamage(this.target, ExplosionType.Q, vec32, entityPlayerSP, world);
                        if (f3 > 0.0f && (double)f3 < d2) {
                            crystalAuraPlacementSearchState.G();
                        }
                        boolean bl11 = bl10 = !this.isSafeDamage(vec32, ExplosionType.Q, entityPlayerSP, world);
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
                        Vec3 vec34 = ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, (PlacementTarget)object, this.rotationManager.V(), this.rotationManager.x());
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
                        float f4 = this.computeDamage(this.target, ExplosionType.Q, vec32, entityPlayerSP, world);
                        if (f4 > 0.0f && (double)f4 < d2) {
                            crystalAuraPlacementSearchState.G();
                        }
                        boolean bl13 = !this.isSafeDamage(vec32, ExplosionType.Q, entityPlayerSP, world);
                        BlockData blockData4 = new BlockData(n3, n4, n2);
                        PlacementTarget placementTarget = new PlacementTarget(blockData4, EnumFacing.F$src$Lgg_vape_wrapper_impl_EnumFacing_$glfxl5());
                        boolean bl14 = ClutchPlacementPathUtils.P(vec3, world, placementTarget.k, placementTarget.G);
                        if (bl14) {
                            wrapper4 = ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, placementTarget, this.rotationManager.V(), this.rotationManager.x());
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
                        if (!arrayList.isEmpty() || this.wouldIntersect(this.target, directionalPosition, entityPlayerSP, world)) {
                            crystalAuraPlacementSearchState.O();
                            continue;
                        }
                        PlacementTarget placementTarget2 = new PlacementTarget(blockData3, enumFacing);
                        if (!ClutchPlacementPathUtils.P(vec3, world, placementTarget2.k, placementTarget2.G)) {
                            crystalAuraPlacementSearchState.R();
                            continue;
                        }
                        Vec3 vec35 = ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, placementTarget2, this.rotationManager.V(), this.rotationManager.x());
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
        boolean bl15 = this.lowEfficiency = crystalAuraActionCandidate == null && crystalAuraPlacementSearchState.T;
        this.statusLabel = crystalAuraActionCandidate == null ? (crystalAuraPlacementSearchState.T ? "low eff" : (crystalAuraPlacementSearchState.z ? "blocked" : (crystalAuraPlacementSearchState.X ? "out range" : (crystalAuraPlacementSearchState.h ? (this.autoObsidian.L().booleanValue() && n7 == -1 ? "no obsidian" : "no place") : (!this.autoObsidian.L().booleanValue() && !bl2 ? "no placement block" : "no candidate"))))) : null;
        return crystalAuraActionCandidate;
    }

    private void rememberTarget(EntityLivingBase entityLivingBase) {
        if (entityLivingBase != null && entityLivingBase.isNotNull()) {
            this.lastTarget = entityLivingBase;
            this.lastTargetTime = System.currentTimeMillis();
        }
    }

    public CrystalAuraPlacementSubModule(Mod mod, String string) {
        super(mod, string);
        this.targetFilter = EntityTargetFilterValue.W(this);
        this.centerScreen = BooleanValue.create(this, "Center screen", true, "Renders crystal info on the center of your screen");
        this.showTarget = BooleanValue.create(this, "Show target", false);
        this.targetColor = ColorValue.b(this, "Target color", new Color(255, 40, 255), 50);
        this.attackColor = ColorValue.L(this, "Attack color", new Color(169, 0, 255, 255));
        this.range = NumberValue.E(this, "Range", "#.#", "m", 0.0, 4.5, 6.0, "Range to check for targets");
        this.maxAngle = NumberValue.create(this, "Max angle", "#", "", 1.0, 120.0, 360.0, 5.0, "Angle at which targets will be acquired and aimed at\n(From your cursor)");
        this.distanceMode = new ModeOption("Distance");
        this.yawMode = new ModeOption("Yaw");
        this.armorMode = new ModeOption("Armor");
        this.healthMode = new ModeOption("Health");
        this.targetMode = ModeValue.create((Object)this, "Target Mode", "How targets will be prioritized\nArmor will default to Distance for non player targets", (ModeSelection)this.distanceMode, this.distanceMode, this.yawMode, this.armorMode, this.healthMode);
        this.activationDelay = RandomValue.G(this, "Delay", "#", "ms", 50.0, 50.0, 150.0, 500.0, 1.0, "Delay before activating");
        this.antiSuicide = BooleanValue.create(this, "Anti suicide", true, "Prevents placing/breaking if it will result in fatal damage");
        this.maxSelfDamage = NumberValue.create(this, "Max self damage", "#", "HP", 0.0, 19.0, 20.0, 1.0, "Maximum self damage allowed");
        this.rapidFireMode = new ModeOption("Rapid fire");
        this.predictMode = new ModeOption("Predict");
        this.noneMode = new ModeOption("None");
        this.optimizationMode = ModeValue.create((Object)this, "Optimization", "Controls crystal optimization behavior\nNone - No crystal optimization\nRapid fire - Crystals are broken and replaced in same tick when possible\nPredict - Predicts explosion timing and pre-removes crystal for faster placement(potentially unsafe)", (ModeSelection)this.noneMode, this.rapidFireMode, this.predictMode, this.noneMode);
        this.rapidMinEfficiency = NumberValue.create(this, "Rapid min efficiency", "#", "%", 0.0, 50.0, 100.0, 1.0, "Minimum damage efficiency (0-100%) to trigger rapid fire when placing/breaking crystals");
        this.predictAttackVelocity = BooleanValue.create(this, "Predict attack velocity", true, "Predicts target movement when calculating damage after successfully attacking");
        this.autoObsidian = BooleanValue.create(this, "Auto obsidian", false, "Automatically places obsidian to place crystals");
        this.minEfficiency = NumberValue.create(this, "Min efficiency", "#", "%", 0.0, 50.0, 100.0, 1.0, "Minimum damage efficiency (0-100%) for placing and breaking crystals");
        this.rotationManager = RotationManager.b;
        this.rotationClaim = SharedModuleControlClaims.I;
        this.mouseOverClaim = SharedModuleControlClaims.a;
        this.friendManager = Vape.INSTANCE.getFriendManager();
        this.delayTimer = new TimerUtil();
        this.crystalAura = (CrystalAura)mod;
        this.showTarget.K(this.targetColor, this.attackColor);
        this.optimizationMode.f(this.rapidFireMode, this.rapidMinEfficiency);
        this.addValue(this.targetFilter, this.targetMode, this.range, this.maxAngle, this.aimSpeed, this.minEfficiency, this.activationDelay, this.maxSelfDamage, this.autoObsidian, this.antiSuicide, this.optimizationMode, this.rapidMinEfficiency, this.predictAttackVelocity, this.centerScreen, this.showTarget, this.targetColor, this.attackColor);
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
        boolean bl = this.delayTimer.hasTimeElapsed((long)this.activationDelay.B());
        if (worldClient.isNull() || entityPlayerSP.isNull() || !guiScreen.isNull() || this.toggledOff) {
            this.reset();
            return;
        }
        if (this.attacked && this.attackedLastTick) {
            this.attacked = false;
        }
        this.attackedLastTick = this.attacked;
        this.crystalSlot = this.crystalAura.Q(entityPlayerSP);
        if (this.crystalSlot == -1) {
            this.statusLabel = "no crystal";
            this.reset();
            return;
        }
        if (this.currentAction != null) {
            Object object2;
            Wrapper wrapper;
            Object object3;
            Object object4;
            Wrapper wrapper2;
            Object object5;
            CrystalAuraAction crystalAuraAction = this.currentAction.R();
            if (crystalAuraAction == CrystalAuraAction.PLACING_OBSIDIAN || crystalAuraAction == CrystalAuraAction.PLACING_CRYSTAL) {
                object = this.currentAction.s;
                if (crystalAuraAction == CrystalAuraAction.PLACING_CRYSTAL) {
                    object5 = new BlockCoordinate(((BlockCoordinate)object).B(), ((BlockCoordinate)object).E() + 1, ((BlockCoordinate)object).A());
                    wrapper2 = AxisAlignedBB.create((double)((BlockCoordinate)object5).B() - 0.5, (double)((BlockCoordinate)object5).E() - 0.01, (double)((BlockCoordinate)object5).A() - 0.5, (double)((BlockCoordinate)object5).B() + 0.5, (double)((BlockCoordinate)object5).E() + 2.0, (double)((BlockCoordinate)object5).A() + 0.5);
                    object4 = new ArrayList();
                    ArrayList collidingEntities = (ArrayList)object4;
                    worldClient.A().p((AxisAlignedBB)wrapper2, arg_0 -> CrystalAuraPlacementSubModule.lambda$onClientTick$0(collidingEntities, arg_0));
                    if (!((ArrayList)object4).isEmpty()) {
                        this.currentAction.D(CrystalAuraAction.ATTACKING_CRYSTAL);
                        this.currentAction.J((Entity)((ArrayList)object4).get(0));
                    }
                }
                if (this.currentAction.R() != CrystalAuraAction.ATTACKING_CRYSTAL) {
                    object5 = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
                    wrapper2 = EnumFacing.T(((DirectionalPosition)object).X());
                    object4 = new BlockData(((BlockCoordinate)object).B(), ((BlockCoordinate)object).E(), ((BlockCoordinate)object).A());
                    object3 = new PlacementTarget((BlockData)object4, (EnumFacing)wrapper2);
                    if (!ClutchPlacementPathUtils.P((Vec3)object5, worldClient, (BlockData)object4, (EnumFacing)wrapper2) || ClutchPlacementPathUtils.D(entityPlayerSP, worldClient, (Vec3)object5, (PlacementTarget)object3, this.rotationManager.V(), this.rotationManager.x()) == null) {
                        this.statusLabel = "out range";
                        this.currentAction = null;
                        this.placementSent = false;
                    }
                }
            }
            if (this.currentAction != null && this.currentAction.R() == CrystalAuraAction.ATTACKING_CRYSTAL) {
                object = this.currentAction.A();
                if (object == null || ((Wrapper)object).isNull() || ((Entity)object).M$src$Z$ff28xj()) {
                    this.currentAction = null;
                    this.placementSent = false;
                } else {
                    Entity entity;
                    object5 = this.currentAction.s;
                    wrapper2 = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
                    if (ClutchPlacementPathUtils.P((Vec3)wrapper2, worldClient, (BlockData)(object4 = new BlockData(((BlockCoordinate)object5).B(), ((BlockCoordinate)object5).E(), ((BlockCoordinate)object5).A())), (EnumFacing)(object3 = EnumFacing.T(((DirectionalPosition)object5).X()))) && ((RayTraceResult)(wrapper = RayTraceUtil.b((Vec3)wrapper2, (Vec3)(object2 = this.crystalAura.B((DirectionalPosition)object5)), worldClient, entityPlayerSP, false, true, false, null))).isEntityHit() && !(entity = ((RayTraceResult)wrapper).getEntity()).isInstance(MappedClasses.Ze) && entity.n$src$Z$fx7gig()) {
                        this.statusLabel = "blocked";
                        this.currentAction = null;
                        this.placementSent = false;
                    }
                    if (((Vec3)wrapper2).distanceTo(((Vec3d)(object2 = RotationUtil.M((Vec3)wrapper2, ((Entity)object).R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0))).n()) > 3.0) {
                        this.statusLabel = "out range";
                        this.currentAction = null;
                        this.placementSent = false;
                    }
                }
            }
            if (this.currentAction != null && this.currentAction.R() == CrystalAuraAction.PLACING_CRYSTAL) {
                int n3;
                int n4;
                object = this.currentAction.s;
                object5 = EnumFacing.T(((DirectionalPosition)object).X());
                int n5 = ((BlockCoordinate)object).B();
                object2 = new BlockData(n5, n4 = ((BlockCoordinate)object).E(), n3 = ((BlockCoordinate)object).A());
                wrapper = worldClient.getBlockState(BlockPos.create(((BlockData)object2).D(), ((BlockData)object2).B(), ((BlockData)object2).G()));
                if (!this.crystalAura.X((BlockState)wrapper)) {
                    this.statusLabel = "no place";
                    this.currentAction = null;
                    this.placementSent = false;
                }
            }
        }
        this.updateTarget(entityPlayerSP, worldClient);
        if (this.target == null) {
            this.lowEfficiency = false;
            this.statusLabel = "no target";
            this.reset();
            return;
        }
        if (this.currentAction == null && bl) {
            this.currentAction = this.findAction(entityPlayerSP, worldClient, 4.0, false);
            if (this.currentAction == null) {
                this.reset();
                if (this.statusLabel == null) {
                    this.statusLabel = "no candidate";
                }
                return;
            }
            this.lowEfficiency = false;
            this.statusLabel = null;
            if (this.currentAction.H) {
                this.currentAction.D(CrystalAuraAction.PLACING_OBSIDIAN);
            } else {
                this.currentAction.D(CrystalAuraAction.PLACING_CRYSTAL);
            }
        }
        if (this.currentAction == null) {
            return;
        }
        if (!this.rotationClaim.U(this.crystalAura) && !this.rotationClaim.h(this.crystalAura, true)) {
            this.statusLabel = "aim lock";
            return;
        }
        object = this.currentAction.R();
        if (object == CrystalAuraAction.PLACING_OBSIDIAN) {
            n = this.crystalAura.z(entityPlayerSP);
            if (n == -1) {
                this.statusLabel = "no obby";
                this.currentAction = null;
                this.placementSent = false;
                return;
            }
        } else {
            n = object == CrystalAuraAction.PLACING_CRYSTAL ? this.crystalSlot : this.crystalSlot;
        }
        if (entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v() != n) {
            if (this.previousSlot == -1) {
                this.previousSlot = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            }
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
        }
        this.validateAction(entityPlayerSP, worldClient);
    }

    private boolean isSafeDamage(Vec3 vec3, ExplosionType explosionType, EntityPlayerSP entityPlayerSP, World world) {
        return this.crystalAura.n(vec3, explosionType, entityPlayerSP, world, this.antiSuicide.L(), ((Double)this.maxSelfDamage.K()).floatValue());
    }

    public void releaseControl() {
        if (this.rotationController != null) {
            RotationManager.b.v(this.rotationController);
            if (this.rotationManager.w() == this.rotationController) {
                this.rotationController.w(false);
                this.rotationController.u(true);
                if (this.rotationController instanceof AdaptiveRotationController) {
                    ((AdaptiveRotationController)this.rotationController).b(true);
                }
            }
            this.rotationController = null;
        }
        this.rotationClaim.X(this.crystalAura);
        if (this.previousSlot != -1) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            if (!entityPlayerSP.isNull()) {
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.previousSlot);
            }
            this.previousSlot = -1;
        }
        this.target = null;
        this.currentAction = null;
        this.placementSent = false;
        this.attackedLastTick = false;
        this.attacked = false;
        this.toggledOff = false;
        this.delayTimer.reset();
    }

    private static Exception rethrow(Exception exception) {
        return exception;
    }
}
