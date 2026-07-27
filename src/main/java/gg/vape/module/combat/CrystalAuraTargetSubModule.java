package gg.vape.module.combat;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.event.impl.EventWorldChange;
import gg.vape.input.KeyBindingInputState;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.module.combat.CrystalAura;
import gg.vape.module.combat.crystalaura.CrystalAuraActionState;
import gg.vape.module.combat.crystalaura.ExplosionType;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.utility.clutch.ClutchPlacementPathUtils;
import gg.vape.module.utility.clutch.PlacementTarget;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.RotationVectorMath;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.datas.BlockData;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockState;
import gg.vape.wrapper.impl.CPacketUseEntity;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.GuiScreen;
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
import org.lwjgl.opengl.GL11;

public class CrystalAuraTargetSubModule
extends SubModule<CrystalAura> {
    private final NumberValue aimSpeed = NumberValue.E(this, "Aim speed", "#.#", "", 1.0, 4.5, 10.0, "Aim rotation speed");
    private final TimerUtil stateTimer;
    private FixedRotationController rotationController;
    private final TimerUtil actionTimer;
    private CrystalAuraActionState state;
    private final ModeOption rapidFireMode;
    private static final Color SAFE_COLOR;
    private final NumberValue maxSelfDamage;
    private final BooleanValue showTargetBlock;
    private CrystalAura crystalAura;
    private final ModeOption noneMode;
    private final ModeOption predictMode;
    private int crystalSlot = -1;
    private EnumFacing obsidianFacing;
    private Entity trackedCrystal;
    private String debugMessage = "";
    private boolean obsidianPlaced;
    private BlockData obsidianSupport;
    private final RotationControlClaim rotationClaim;
    private final BooleanValue placeObsidian;
    private int savedSlot = -1;
    private final ModeValue optimizationMode;
    private static final Color DEFAULT_COLOR;
    private final RandomValue delay;
    private static final Color PLACING_COLOR;
    private int pendingRemoveEntityId = -1;
    private int obsidianSlot = -1;
    private BlockData targetObsidian;
    private final RotationManager rotationManager;
    private final BooleanValue antiSuicide = BooleanValue.create(this, "Anti suicide", true, "Prevents breaking if it will result in fatal damage");

    private void resetState() {
        this.state = CrystalAuraActionState.IDLE;
        this.targetObsidian = null;
        this.trackedCrystal = null;
        this.crystalSlot = -1;
        this.savedSlot = -1;
        this.pendingRemoveEntityId = -1;
        this.resetObsidianState();
        this.actionTimer.reset();
        this.stateTimer.reset();
        this.debugMessage = "";
    }

    private Entity findCrystalAbove(World world, BlockData blockData) {
        int n = blockData.D();
        int n2 = blockData.B();
        int n3 = blockData.G();
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(n, n2 + 1, n3, (double)n + 1.0, (double)n2 + 3.0, (double)n3 + 1.0);
        ArrayList arrayList = new ArrayList();
        world.A().p(axisAlignedBB, arg_0 -> CrystalAuraTargetSubModule.lambda$findCrystalAboveObsidian$0(arrayList, arg_0));
        if (!arrayList.isEmpty()) {
            return (Entity)arrayList.get(0);
        }
        return null;
    }

    private Color computeRenderColor(EntityPlayerSP entityPlayerSP, World world) {
        Vec3 vec3;
        if (this.targetObsidian == null) {
            return DEFAULT_COLOR;
        }
        if (this.state == CrystalAuraActionState.PLACING_OBSIDIAN) {
            return PLACING_COLOR;
        }
        if (!this.isHoldingCrystal(entityPlayerSP)) {
            return DEFAULT_COLOR;
        }
        BlockPos blockPos = BlockPos.create(this.targetObsidian.D(), this.targetObsidian.B(), this.targetObsidian.G());
        BlockState blockState = world.getBlockState(blockPos);
        if (!this.crystalAura.X(blockState)) {
            return DEFAULT_COLOR;
        }
        if (this.state == CrystalAuraActionState.PLACING_CRYSTAL && !this.canPlaceCrystalAbove(world, this.targetObsidian)) {
            return DEFAULT_COLOR;
        }
        Vec3 vec32 = vec3 = this.state == CrystalAuraActionState.BREAKING_CRYSTAL && this.trackedCrystal != null && this.trackedCrystal.isNotNull() && !this.trackedCrystal.M$src$Z$ff28xj() ? this.trackedCrystal.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk() : Vec3.create((double)this.targetObsidian.D() + 0.5, this.targetObsidian.B() + 1, (double)this.targetObsidian.G() + 0.5);
        if (!this.crystalAura.n(vec3, ExplosionType.Q, entityPlayerSP, world, this.antiSuicide.L(), ((Double)this.maxSelfDamage.K()).floatValue())) {
            return DEFAULT_COLOR;
        }
        return SAFE_COLOR;
    }

    private void clearTracking(String string) {
        boolean bl = this.state != CrystalAuraActionState.IDLE || this.targetObsidian != null || this.trackedCrystal != null || this.rotationController != null;
        this.releaseRotation();
        this.resetState();
        if (bl) {
            this.debug("cleared tracking: " + string);
        }
    }

    private void setState(CrystalAuraActionState crystalAuraActionState, String string) {
        if (this.state == crystalAuraActionState) {
            return;
        }
        this.state = crystalAuraActionState;
        this.stateTimer.reset();
        this.debug("state -> " + (Object)((Object)crystalAuraActionState) + " (" + string + ")");
        if (crystalAuraActionState == CrystalAuraActionState.IDLE) {
            this.targetObsidian = null;
            this.trackedCrystal = null;
            this.crystalSlot = -1;
            this.releaseRotation();
        }
    }

    @Override
    public String r() {
        return "Manual";
    }

    private void debug(String string) {
    }

    private void handlePlacingCrystal(EntityPlayerSP entityPlayerSP, World world) {
        if (!this.canPlaceCrystalAbove(world, this.targetObsidian)) {
            this.clearTracking("target obsidian no longer has valid crystal placement space");
            return;
        }
        if (!this.actionTimer.hasTimeElapsed((long)this.delay.B())) {
            KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
            if (keyBinding.u() || keyBinding.isPressed()) {
                KeyBinding.setKeyBindState(keyBinding, false);
            }
            return;
        }
        Entity entity = this.findCrystalAbove(world, this.targetObsidian);
        if (entity != null) {
            this.trackedCrystal = entity;
            this.setState(CrystalAuraActionState.BREAKING_CRYSTAL, "crystal already exists while placing");
            return;
        }
        if (!this.rotationClaim.U(this.crystalAura) && !this.rotationClaim.h(this.crystalAura, true)) {
            return;
        }
        if (entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v() != this.crystalSlot) {
            if (this.savedSlot == -1) {
                this.savedSlot = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            }
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.crystalSlot);
        }
        if (this.rotationController != null && this.rotationManager.w() == this.rotationController) {
            RayTraceResult rayTraceResult = this.rotationManager.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
            if (rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.entity())) {
                Entity hitEntity = rayTraceResult.getEntity();
                if (hitEntity.isNotNull() && hitEntity.isInstance(MappedClasses.Ze)) {
                    this.trackedCrystal = hitEntity;
                    this.setState(CrystalAuraActionState.BREAKING_CRYSTAL, "spoofed mouse-over hit crystal while placing");
                    return;
                }
            }
            if (rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
                BlockPos blockPos = rayTraceResult.getBlockPos();
                int n = blockPos.P();
                int n2 = blockPos.o();
                int n3 = blockPos.d();
                BlockData blockData = this.targetObsidian;
                if (n == blockData.D() && n2 == blockData.B() && n3 == blockData.G()) {
                    KeyBinding keyBinding = Minecraft.gameSettings().F();
                    if (keyBinding.u() || keyBinding.isPressed()) {
                        KeyBinding.setKeyBindState(keyBinding, false);
                    }
                    KeyBinding keyBinding2 = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                    KeyBinding.setKeyBindState(keyBinding2, true);
                    KeyBinding.onTick(keyBinding2);
                    KeyBinding.setKeyBindState(keyBinding2, false);
                    this.setState(CrystalAuraActionState.BREAKING_CRYSTAL, "placement click sent, waiting for crystal");
                }
            }
        }
    }

    private String formatPos(BlockPos blockPos) {
        if (blockPos == null) {
            return "null";
        }
        return blockPos.P() + "," + blockPos.o() + "," + blockPos.d();
    }

    private static void lambda$canPlaceCrystalAboveObsidian$1(boolean[] blArray, Object object) {
        Entity entity = new Entity(object);
        if (entity.n$src$Z$fx7gig() || entity.isInstance(MappedClasses.Ze)) {
            blArray[0] = true;
        }
    }

    private void aimAt(Vec3 vec3, Vec3 vec32) {
        if (this.rotationController == null || this.rotationController.V$src$Z$lb4tvc() || this.rotationController instanceof AdaptiveRotationController && ((AdaptiveRotationController)this.rotationController).O$src$Z$1lvi05g()) {
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
        RotationAngles rotationAngles = RotationVectorMath.d(vec3, vec32, f, f2);
        this.rotationController.b(rotationAngles);
        if (this.rotationManager.w() != this.rotationController) {
            this.rotationManager.S(this.rotationController);
        }
    }

    private void handlePlacingObsidian(EntityPlayerSP entityPlayerSP, World world) {
        RayTraceResult rayTraceResult;
        BlockData blockData;
        Block block;
        if (this.obsidianSupport == null || this.obsidianFacing == null) {
            this.clearTracking("obsidian placement data lost");
            return;
        }
        if (this.obsidianSlot == -1 || !this.crystalAura.p(entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(this.obsidianSlot))) {
            this.obsidianSlot = this.crystalAura.z(entityPlayerSP);
            if (this.obsidianSlot == -1) {
                this.clearTracking("lost obsidian from hotbar");
                return;
            }
        }
        if (!BlockUtil.u(block = world.getBlockByPos((blockData = this.targetObsidian).D(), blockData.B(), blockData.G()))) {
            BlockState blockState = world.getBlockState(BlockPos.create(blockData.D(), blockData.B(), blockData.G()));
            if (this.crystalAura.X(blockState)) {
                this.obsidianSupport = null;
                this.obsidianFacing = null;
                this.obsidianPlaced = false;
                if (this.crystalSlot != -1) {
                    entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.crystalSlot);
                }
                this.setState(CrystalAuraActionState.PLACING_CRYSTAL, "obsidian placed successfully");
            } else {
                this.clearTracking("unexpected block at obsidian placement position");
            }
            return;
        }
        if (!this.rotationClaim.U(this.crystalAura) && !this.rotationClaim.h(this.crystalAura, true)) {
            return;
        }
        KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
        if (keyBinding.u() || keyBinding.isPressed()) {
            KeyBinding.setKeyBindState(keyBinding, false);
        }
        if (entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v() != this.obsidianSlot) {
            if (this.savedSlot == -1) {
                this.savedSlot = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            }
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.obsidianSlot);
        }
        if (this.rotationController != null && this.rotationManager.w() == this.rotationController && (rayTraceResult = this.rotationManager.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic()).getTypeOfHit().equals(RayTraceResult_type.block())) {
            BlockPos blockPos = rayTraceResult.getBlockPos();
            int n = blockPos.P();
            int n2 = blockPos.o();
            int n3 = blockPos.d();
            int n4 = rayTraceResult.Z();
            if (n == this.obsidianSupport.D() && n2 == this.obsidianSupport.B() && n3 == this.obsidianSupport.G() && n4 == this.obsidianFacing.Y()) {
                KeyBinding keyBinding2 = Minecraft.gameSettings().F();
                if (keyBinding2.u() || keyBinding2.isPressed()) {
                    KeyBinding.setKeyBindState(keyBinding2, false);
                }
                KeyBinding.setKeyBindState(keyBinding, true);
                KeyBinding.onTick(keyBinding);
                KeyBinding.setKeyBindState(keyBinding, false);
                this.obsidianPlaced = true;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        EntityPlayerSP entityPlayerSP;
        int n;
        WorldClient worldClient;
        block39: {
            int n2;
            Block block;
            Object object;
            Object object2;
            Object object3;
            Block block2;
            Wrapper wrapper;
            RayTraceResult rayTraceResult;
            block40: {
                boolean bl;
                BlockData blockData;
                int n3;
                block38: {
                    block37: {
                        worldClient = eventPreTick.getWorld();
                        n = ExplosionType.R();
                        entityPlayerSP = eventPreTick.getThePlayer();
                        GuiScreen guiScreen = eventPreTick.getCurrentScreen();
                        if (n != 0) break block37;
                        if (!worldClient.isNull() && !entityPlayerSP.isNull() && guiScreen.isNull()) break block38;
                        this.clearTracking("world/player/screen invalid");
                    }
                    return;
                }
                boolean bl2 = KeyBindingInputState.q$src$Z$1enyqt3();
                if (!bl2) {
                    if (this.state != CrystalAuraActionState.IDLE || this.targetObsidian != null || this.trackedCrystal != null) {
                        this.clearTracking("right click released");
                    }
                    return;
                }
                if (!this.isHoldingCrystal(entityPlayerSP) && this.state != CrystalAuraActionState.PLACING_OBSIDIAN) {
                    if (this.state != CrystalAuraActionState.IDLE || this.targetObsidian != null || this.trackedCrystal != null) {
                        this.clearTracking("not holding crystal");
                    }
                    this.debug("idle: not holding crystal");
                    return;
                }
                rayTraceResult = this.rayTraceScreenCenter(entityPlayerSP, worldClient);
                if (this.state == CrystalAuraActionState.IDLE) {
                    if (rayTraceResult.isNull()) {
                        this.debug("idle: no mouse over");
                        return;
                    }
                    wrapper = null;
                    if (!rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
                        this.debug("idle: screen crosshair not hitting a block");
                        return;
                    }
                    wrapper = rayTraceResult.getBlockPos();
                    if (this.isValidBaseBlock(worldClient, (BlockPos)wrapper)) {
                        this.crystalSlot = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
                        BlockData blockData2 = new BlockData(((Vec3i)wrapper).P(), ((Vec3i)wrapper).o(), ((Vec3i)wrapper).d());
                        Entity crystal = this.findCrystalAbove(worldClient, blockData2);
                        if (crystal != null) {
                            this.targetObsidian = blockData2;
                            this.trackedCrystal = crystal;
                            this.setState(CrystalAuraActionState.BREAKING_CRYSTAL, "found crystal above hovered obsidian");
                            this.debug("idle: 1 locked block for breaking at " + this.formatPos((BlockPos)wrapper));
                        } else {
                            if (!this.canPlaceCrystalAbove(worldClient, blockData2)) {
                                this.debug("idle: rejected blocked crystal space at " + this.formatPos((BlockPos)wrapper));
                                return;
                            }
                            this.targetObsidian = blockData2;
                            this.setState(CrystalAuraActionState.PLACING_CRYSTAL, "hovered obsidian valid for crystal placement");
                            this.debug("idle: 2 locked block for placing at " + this.formatPos((BlockPos)wrapper));
                        }
                    } else {
                        if (!this.placeObsidian.L().booleanValue()) {
                            this.debug("idle: rejected invalid base block at " + this.formatPos((BlockPos)wrapper));
                            return;
                        }
                        int n4 = this.crystalAura.z(entityPlayerSP);
                        if (n4 == -1) {
                            this.debug("idle: place obsidian enabled but no obsidian in hotbar");
                            return;
                        }
                        BlockPos hoveredPos = rayTraceResult.getBlockPos();
                        block2 = worldClient.getBlockState(hoveredPos).getBlock();
                        if (BlockUtil.u(block2)) {
                            this.debug("idle: hovered block is replaceable, cannot place obsidian on it");
                            return;
                        }
                        n3 = rayTraceResult.Z();
                        EnumFacing enumFacing = EnumFacing.T(n3);
                        blockData = new BlockData(hoveredPos.P(), hoveredPos.o(), hoveredPos.d());
                        BlockData placementTarget = blockData.R(enumFacing);
                        object2 = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B(), placementTarget.G());
                        if (!BlockUtil.u((Block)object2)) {
                            this.debug("idle: obsidian placement target is not replaceable");
                            return;
                        }
                        object = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B() + 1, placementTarget.G());
                        block = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B() + 2, placementTarget.G());
                        if (!BlockUtil.u((Block)object) || !BlockUtil.u(block)) {
                            this.debug("idle: no room for crystal above obsidian placement");
                            return;
                        }
                        this.crystalSlot = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
                        this.obsidianSlot = n4;
                        this.obsidianSupport = blockData;
                        this.obsidianFacing = enumFacing;
                        this.obsidianPlaced = false;
                        this.targetObsidian = placementTarget;
                        this.setState(CrystalAuraActionState.PLACING_OBSIDIAN, "placing obsidian on hovered surface");
                        this.debug("idle: placing obsidian at " + placementTarget.D() + "," + placementTarget.B() + "," + placementTarget.G());
                    }
                }
                if (this.targetObsidian != null && this.state != CrystalAuraActionState.PLACING_OBSIDIAN && !this.crystalAura.X((BlockState)(wrapper = worldClient.getBlockState(BlockPos.create(this.targetObsidian.D(), this.targetObsidian.B(), this.targetObsidian.G()))))) {
                    this.clearTracking("target obsidian no longer valid base block");
                    return;
                }
                if (this.state == CrystalAuraActionState.IDLE || !this.placeObsidian.L().booleanValue() || !rayTraceResult.isNotNull() || !rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) break block39;
                wrapper = rayTraceResult.getBlockPos();
                boolean bl3 = this.targetObsidian != null && ((Vec3i)wrapper).P() == this.targetObsidian.D() && ((Vec3i)wrapper).o() == this.targetObsidian.B() && ((Vec3i)wrapper).d() == this.targetObsidian.G();
                boolean bl4 = bl = this.state == CrystalAuraActionState.PLACING_OBSIDIAN && this.obsidianSupport != null && ((Vec3i)wrapper).P() == this.obsidianSupport.D() && ((Vec3i)wrapper).o() == this.obsidianSupport.B() && ((Vec3i)wrapper).d() == this.obsidianSupport.G();
                if (bl3 || bl) break block39;
                block2 = worldClient.getBlockState((BlockPos)wrapper).getBlock();
                n3 = this.isValidBaseBlock(worldClient, (BlockPos)wrapper) ? 1 : 0;
                if (n3 == 0) break block40;
                int n5 = this.crystalSlot;
                this.releaseRotation();
                this.resetObsidianState();
                this.trackedCrystal = null;
                blockData = new BlockData(((Vec3i)wrapper).P(), ((Vec3i)wrapper).o(), ((Vec3i)wrapper).d());
                this.crystalSlot = n5;
                Entity crystal = this.findCrystalAbove(worldClient, blockData);
                if (crystal != null) {
                    this.targetObsidian = blockData;
                    this.trackedCrystal = crystal;
                    this.setState(CrystalAuraActionState.BREAKING_CRYSTAL, "switched to new obsidian target (crystal found)");
                    break block39;
                } else if (this.canPlaceCrystalAbove(worldClient, blockData)) {
                    this.targetObsidian = blockData;
                    this.setState(CrystalAuraActionState.PLACING_CRYSTAL, "switched to new obsidian target");
                }
                break block39;
            }
            if (!BlockUtil.u(block2) && (n2 = this.crystalAura.z(entityPlayerSP)) != -1) {
                int n6 = rayTraceResult.Z();
                EnumFacing enumFacing = EnumFacing.T(n6);
                BlockData supportBlock = new BlockData(((Vec3i)wrapper).P(), ((Vec3i)wrapper).o(), ((Vec3i)wrapper).d());
                BlockData placementTarget = supportBlock.R(enumFacing);
                block = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B(), placementTarget.G());
                Block block3 = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B() + 1, placementTarget.G());
                Block block4 = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B() + 2, placementTarget.G());
                if (BlockUtil.u(block) && BlockUtil.u(block3) && BlockUtil.u(block4)) {
                    int n7 = this.crystalSlot;
                    this.releaseRotation();
                    this.resetObsidianState();
                    this.trackedCrystal = null;
                    this.crystalSlot = n7;
                    this.obsidianSlot = n2;
                    this.obsidianSupport = supportBlock;
                    this.obsidianFacing = enumFacing;
                    this.obsidianPlaced = false;
                    this.targetObsidian = placementTarget;
                    this.setState(CrystalAuraActionState.PLACING_OBSIDIAN, "switched to new obsidian placement target");
                }
            }
        }
        if (this.state != CrystalAuraActionState.PLACING_OBSIDIAN && !this.isHoldingCrystal(entityPlayerSP)) {
            this.clearTracking("lost held crystal");
            return;
        }
        if (this.state != CrystalAuraActionState.PLACING_OBSIDIAN) {
            this.crystalSlot = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        }
        switch (this.state) {
            case BREAKING_CRYSTAL: {
                this.handleBreakingCrystal(entityPlayerSP, worldClient);
                break;
            }
            case PLACING_CRYSTAL: {
                this.handlePlacingCrystal(entityPlayerSP, worldClient);
                break;
            }
            case PLACING_OBSIDIAN: {
                this.handlePlacingObsidian(entityPlayerSP, worldClient);
                break;
            }
        }
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            ExplosionType.G(++n);
        }
    }

    private static void lambda$findCrystalAboveObsidian$0(ArrayList arrayList, Object object) {
        if (MappedClasses.Ze.isAssignableFrom(object.getClass())) {
            arrayList.add(new Entity(object));
        }
    }

    private boolean isValidBaseBlock(World world, BlockPos blockPos) {
        return blockPos != null && this.crystalAura.X(world.getBlockState(blockPos));
    }

    private Vec3 computeCrystalHitVec(EntityPlayerSP entityPlayerSP, World world, Vec3 vec3, BlockData blockData, float f, float f2) {
        EnumFacing[] enumFacingArray;
        for (EnumFacing enumFacing : enumFacingArray = new EnumFacing[]{EnumFacing.F$src$Lgg_vape_wrapper_impl_EnumFacing_$glfxl5(), EnumFacing.w(), EnumFacing.M(), EnumFacing.X(), EnumFacing.g$src$Lgg_vape_wrapper_impl_EnumFacing_$1ii8mzu(), EnumFacing.B()}) {
            PlacementTarget placementTarget = new PlacementTarget(blockData, enumFacing);
            Vec3 vec32 = ClutchPlacementPathUtils.D(entityPlayerSP, world, vec3, placementTarget, f, f2);
            if (vec32 == null) continue;
            return vec32;
        }
        return null;
    }

    @EventHandler
    public void onPacketSend(EventPacketSend eventPacketSend) {
        EntityPlayerSP entityPlayerSP = eventPacketSend.getThePlayer();
        int n = ExplosionType.q();
        if (n != 0) {
            UseEntityPacketBridge useEntityPacketBridge;
            UseEntityPacketBridge useEntityPacketBridge2;
            WorldClient worldClient = eventPacketSend.getWorld();
            boolean bl = entityPlayerSP.isNull();
            if (bl || worldClient.isNull()) {
                return;
            }
            Packet packet = eventPacketSend.getPacket();
            if (packet.isInstance(MappedClasses.Fa) && (useEntityPacketBridge2 = (useEntityPacketBridge = new UseEntityPacketBridge(packet.getObject()))).U().equals(CPacketUseEntity.T())) {
                boolean bl2;
                boolean bl3;
                Entity entity = useEntityPacketBridge.C(eventPacketSend.getWorld());
                if (this.state == CrystalAuraActionState.BREAKING_CRYSTAL && (bl3 = entity.isNotNull()) && (bl2 = entity.isInstance(MappedClasses.Ze))) {
                    if (((ModeSelection)this.optimizationMode.K()).equals(this.predictMode) && !Minecraft.V()) {
                        this.pendingRemoveEntityId = entity.S();
                    }
                    this.trackedCrystal = null;
                    this.setState(CrystalAuraActionState.PLACING_CRYSTAL, "attack packet sent");
                    this.actionTimer.reset();
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
        if (packet.isInstance(MappedClasses.Fa)) {
            UseEntityPacketBridge useEntityPacketBridge;
            UseEntityPacketBridge useEntityPacketBridge3 = useEntityPacketBridge = new UseEntityPacketBridge(packet.getObject());
            Entity entity = useEntityPacketBridge3.C(eventPacketSend.getWorld());
            if (this.state == CrystalAuraActionState.BREAKING_CRYSTAL) {
                boolean bl4 = entity.isNotNull();
                boolean bl5 = bl4;
                if (bl5 && !Minecraft.V()) {
                    this.pendingRemoveEntityId = entity.S();
                }
                this.trackedCrystal = null;
                this.setState(CrystalAuraActionState.PLACING_CRYSTAL, "attack packet sent");
                this.actionTimer.reset();
            }
        }
    }

    @EventHandler
    public void onWorldChange(EventWorldChange eventWorldChange) {
        this.releaseRotation();
        this.resetState();
    }

    private void releaseRotation() {
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
        if (this.savedSlot != -1) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            if (!entityPlayerSP.isNull()) {
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.savedSlot);
            }
            this.savedSlot = -1;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (!this.showTargetBlock.L().booleanValue()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = eventRender3D.getThePlayer();
        WorldClient worldClient = eventRender3D.getWorld();
        if (entityPlayerSP.isNull() || worldClient.isNull() || this.state == CrystalAuraActionState.IDLE || this.targetObsidian == null || !KeyBindingInputState.q$src$Z$1enyqt3()) {
            return;
        }
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
            RenderUtil.w(RenderManager.getInterpolatedRenderPosX(), RenderManager.getInterpolatedRenderPosY(), RenderManager.getInterpolatedRenderPosZ(), this.targetObsidian.D(), this.targetObsidian.B(), this.targetObsidian.G(), this.computeRenderColor(entityPlayerSP, worldClient));
        }
        finally {
            if (bl) {
                GlStateManager.enableBlend();
            } else {
                GlStateManager.disableBlend();
            }
            if (bl2) {
                GlStateManager.r();
            } else {
                GlStateManager.P();
            }
            RenderUtils.f();
            RenderUtil.Y();
        }
    }

    @EventHandler
    public void onPostTick(EventPostTick eventPostTick) {
        Wrapper wrapper;
        WorldClient worldClient = eventPostTick.getWorld();
        int n = ExplosionType.q();
        EntityPlayerSP entityPlayerSP = eventPostTick.getThePlayer();
        if (this.pendingRemoveEntityId != -1 && worldClient.isNotNull()) {
            wrapper = ((World)worldClient).V(this.pendingRemoveEntityId);
            if (wrapper.isNotNull()) {
                worldClient.M((Entity)wrapper);
            }
            this.pendingRemoveEntityId = -1;
        }
        if (worldClient.isNull() || entityPlayerSP.isNull() || this.state == CrystalAuraActionState.IDLE || this.targetObsidian == null) {
            return;
        }
        if (!this.rotationClaim.U(this.crystalAura) && !this.rotationClaim.h(this.crystalAura, true)) {
            return;
        }
        wrapper = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
        if (this.state == CrystalAuraActionState.BREAKING_CRYSTAL && this.trackedCrystal != null && this.trackedCrystal.isNotNull() && !this.trackedCrystal.M$src$Z$ff28xj()) {
            Vec3 vec3 = RotationUtil.M((Vec3)wrapper, this.trackedCrystal.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0).n();
            this.aimAt((Vec3)wrapper, vec3);
        } else if (this.state == CrystalAuraActionState.BREAKING_CRYSTAL) {
            BlockData blockData = this.targetObsidian;
            Vec3 vec3 = Vec3.create((double)blockData.D() + 0.5, blockData.B() + 1, (double)blockData.G() + 0.5);
            this.aimAt((Vec3)wrapper, vec3);
        } else if (this.state == CrystalAuraActionState.PLACING_CRYSTAL) {
            float f;
            BlockData blockData = this.targetObsidian;
            float f2 = this.rotationManager.V();
            Vec3 vec3 = this.computeCrystalHitVec(entityPlayerSP, worldClient, (Vec3)wrapper, blockData, f2, f = this.rotationManager.x());
            if (vec3 == null) {
                vec3 = Vec3.create((double)blockData.D() + 0.5, blockData.B() + 1, (double)blockData.G() + 0.5);
            }
            this.aimAt((Vec3)wrapper, vec3);
        } else if (this.state == CrystalAuraActionState.PLACING_OBSIDIAN && this.obsidianSupport != null && this.obsidianFacing != null) {
            float f;
            PlacementTarget placementTarget = new PlacementTarget(this.obsidianSupport, this.obsidianFacing);
            float f3 = this.rotationManager.V();
            Vec3 vec3 = ClutchPlacementPathUtils.D(entityPlayerSP, worldClient, (Vec3)wrapper, placementTarget, f3, f = this.rotationManager.x());
            if (vec3 == null) {
                vec3 = Vec3.create((double)this.obsidianSupport.D() + 0.5 + (double)this.obsidianFacing.w$src$Lgg_vape_wrapper_impl_Vec3i_$ixeccr().P() * 0.5, (double)this.obsidianSupport.B() + 0.5 + (double)this.obsidianFacing.w$src$Lgg_vape_wrapper_impl_Vec3i_$ixeccr().o() * 0.5, (double)this.obsidianSupport.G() + 0.5 + (double)this.obsidianFacing.w$src$Lgg_vape_wrapper_impl_Vec3i_$ixeccr().d() * 0.5);
            }
            this.aimAt((Vec3)wrapper, vec3);
        }
    }

    private void handleBreakingCrystal(EntityPlayerSP entityPlayerSP, World world) {
        Entity entity;
        RayTraceResult rayTraceResult;
        Wrapper wrapper;
        if (this.trackedCrystal == null || this.trackedCrystal.isNull() || this.trackedCrystal.M$src$Z$ff28xj()) {
            this.trackedCrystal = null;
            Entity crystal = this.findCrystalAbove(world, this.targetObsidian);
            if (crystal != null) {
                this.trackedCrystal = crystal;
            } else {
                this.setState(CrystalAuraActionState.PLACING_CRYSTAL, "crystal missing above obsidian");
                this.actionTimer.reset();
                return;
            }
        }
        if (!this.crystalAura.n((Vec3)(wrapper = this.trackedCrystal.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk()), ExplosionType.Q, entityPlayerSP, world, this.antiSuicide.L(), ((Double)this.maxSelfDamage.K()).floatValue())) {
            this.clearTracking("unsafe self damage while breaking crystal");
            return;
        }
        if (!this.rotationClaim.U(this.crystalAura) && !this.rotationClaim.h(this.crystalAura, true)) {
            return;
        }
        KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
        if (keyBinding.u() || keyBinding.isPressed()) {
            KeyBinding.setKeyBindState(keyBinding, false);
        }
        if (this.rotationController != null && this.rotationManager.w() == this.rotationController && (rayTraceResult = this.rotationManager.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic()).getTypeOfHit().equals(RayTraceResult_type.entity()) && (entity = rayTraceResult.getEntity()).isInstance(MappedClasses.Ze)) {
            KeyBinding keyBinding2 = Minecraft.gameSettings().F();
            if (keyBinding2.u() || keyBinding2.isPressed()) {
                KeyBinding.setKeyBindState(keyBinding2, false);
            }
            if (((ModeSelection)this.optimizationMode.K()).equals(this.rapidFireMode)) {
                KeyBinding keyBinding3 = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                KeyBinding.setKeyBindState(keyBinding3, true);
                KeyBinding.onTick(keyBinding3);
                KeyBinding.setKeyBindState(keyBinding3, false);
            }
            KeyBinding.setKeyBindState(keyBinding2, true);
            KeyBinding.onTick(keyBinding2);
            KeyBinding.setKeyBindState(keyBinding2, false);
        }
    }

    private boolean canPlaceCrystalAbove(World world, BlockData blockData) {
        if (blockData == null) {
            return false;
        }
        Block block = world.getBlockByPos(blockData.D(), blockData.B() + 1, blockData.G());
        Block block2 = world.getBlockByPos(blockData.D(), blockData.B() + 2, blockData.G());
        if (!BlockUtil.u(block) || !BlockUtil.u(block2)) {
            return false;
        }
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(blockData.D(), blockData.B() + 1, blockData.G(), (double)blockData.D() + 1.0, (double)blockData.B() + 3.0, (double)blockData.G() + 1.0);
        boolean[] blArray = new boolean[]{false};
        world.A().p(axisAlignedBB, arg_0 -> CrystalAuraTargetSubModule.lambda$canPlaceCrystalAboveObsidian$1(blArray, arg_0));
        return !blArray[0];
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean isLookingAtObsidian(RayTraceResult rayTraceResult, World world, BlockData blockData) {
        if (rayTraceResult == null || rayTraceResult.isNull() || blockData == null) {
            return false;
        }
        if (!rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
            return false;
        }
        BlockPos blockPos = rayTraceResult.getBlockPos();
        if (blockPos == null) {
            return false;
        }
        if (blockPos.P() != blockData.D() || blockPos.o() != blockData.B() || blockPos.d() != blockData.G()) {
            return false;
        }
        return this.crystalAura.X(world.getBlockState(blockPos));
    }

    static {
        DEFAULT_COLOR = new Color(255, 0, 0, 25);
        SAFE_COLOR = new Color(0, 255, 255, 25);
        PLACING_COLOR = new Color(0, 0, 255, 25);
    }

    public CrystalAuraTargetSubModule(Mod mod, String string) {
        super(mod, string);
        this.maxSelfDamage = NumberValue.create(this, "Max self damage", "#", "HP", 0.0, 19.0, 20.0, 1.0, "Maximum self damage allowed");
        this.delay = RandomValue.G(this, "Delay", "#", "ms", 0.0, 0.0, 100.0, 500.0, 1.0, "Delay between break/place cycles");
        this.rapidFireMode = new ModeOption("Rapid fire");
        this.predictMode = new ModeOption("Predict");
        this.noneMode = new ModeOption("None");
        this.optimizationMode = ModeValue.create((Object)this, "Optimization", "Controls crystal optimization behavior\nNone - No crystal optimization\nRapid fire - Crystals are broken and replaced in same tick when possible\nPredict - Predicts explosion timing and pre-removes crystal for faster placement(potentially unsafe)", (ModeSelection)this.noneMode, this.rapidFireMode, this.predictMode, this.noneMode);
        this.placeObsidian = BooleanValue.create(this, "Place obsidian", false, "Automatically places obsidian from hotbar when hovering a valid placement surface");
        this.showTargetBlock = BooleanValue.create(this, "Show target block", true, "Renders a highlight on the target obsidian block");
        this.rotationManager = RotationManager.b;
        this.rotationClaim = SharedModuleControlClaims.I;
        this.state = CrystalAuraActionState.IDLE;
        this.actionTimer = new TimerUtil();
        this.stateTimer = new TimerUtil();
        this.crystalAura = (CrystalAura)mod;
        this.addValue(this.aimSpeed, this.antiSuicide, this.maxSelfDamage, this.delay, this.optimizationMode, this.placeObsidian, this.showTargetBlock);
    }

    @Override
    public void onDisable() {
        this.releaseRotation();
        this.resetState();
    }

    private void resetObsidianState() {
        this.obsidianSlot = -1;
        this.obsidianSupport = null;
        this.obsidianFacing = null;
        this.obsidianPlaced = false;
    }

    private boolean isHoldingCrystal(EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.isNotNull() && this.crystalAura.u(entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt());
    }

    @Override
    public void onEnable() {
        this.resetState();
    }

    private RayTraceResult rayTraceScreenCenter(EntityPlayerSP entityPlayerSP, World world) {
        EntityLivingBase entityLivingBase = Minecraft.F();
        if (entityLivingBase.isNull()) {
            return new RayTraceResult(null);
        }
        float f = entityLivingBase.J();
        float f2 = entityLivingBase.V();
        double d = entityPlayerSP.z();
        double d2 = entityPlayerSP.N() + (double)entityPlayerSP.X();
        double d3 = entityPlayerSP.h();
        Vec3 vec3 = Vec3.create(d, d2, d3);
        float f3 = MathUtil.cos(-f * ((float)Math.PI / 180) - (float)Math.PI);
        float f4 = MathUtil.sin(-f * ((float)Math.PI / 180) - (float)Math.PI);
        float f5 = -MathUtil.cos(-f2 * ((float)Math.PI / 180));
        float f6 = MathUtil.sin(-f2 * ((float)Math.PI / 180));
        float f7 = f4 * f5;
        float f8 = f6;
        float f9 = f3 * f5;
        double d4 = 5.0;
        Vec3 vec32 = vec3.addVector((double)f7 * d4, (double)f8 * d4, (double)f9 * d4);
        return world.K(vec3, vec32, false, true, false, entityPlayerSP);
    }
}
