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
    private final NumberValue s = NumberValue.E(this, "Aim speed", "#.#", "", 1.0, 4.5, 10.0, "Aim rotation speed");
    private final TimerUtil Z;
    private FixedRotationController p;
    private final TimerUtil KF;
    private CrystalAuraActionState t;
    private final ModeOption L;
    private static final Color r;
    private final NumberValue D;
    private final BooleanValue K_;
    private CrystalAura V;
    private final ModeOption C;
    private final ModeOption o;
    private int Kl = -1;
    private EnumFacing Kx;
    private Entity J;
    private String O = "";
    private boolean I;
    private BlockData v;
    private final RotationControlClaim A;
    private final BooleanValue P;
    private int F = -1;
    private final ModeValue U;
    private static final Color K;
    private final RandomValue c;
    private static final Color K3;
    private int H = -1;
    private int S = -1;
    private BlockData KA;
    private final RotationManager Kn;
    private final BooleanValue b = BooleanValue.create(this, "Anti suicide", true, "Prevents breaking if it will result in fatal damage");

    private void d() {
        this.t = CrystalAuraActionState.IDLE;
        this.KA = null;
        this.J = null;
        this.Kl = -1;
        this.F = -1;
        this.H = -1;
        this.M$src$V$iomsrj();
        this.KF.reset();
        this.Z.reset();
        this.O = "";
    }

    private Entity i(World world, BlockData blockData) {
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

    private Color D(EntityPlayerSP entityPlayerSP, World world) {
        Vec3 vec3;
        if (this.KA == null) {
            return K;
        }
        if (this.t == CrystalAuraActionState.PLACING_OBSIDIAN) {
            return K3;
        }
        if (!this.j(entityPlayerSP)) {
            return K;
        }
        BlockPos blockPos = BlockPos.create(this.KA.D(), this.KA.B(), this.KA.G());
        BlockState blockState = world.getBlockState(blockPos);
        if (!this.V.X(blockState)) {
            return K;
        }
        if (this.t == CrystalAuraActionState.PLACING_CRYSTAL && !this.X(world, this.KA)) {
            return K;
        }
        Vec3 vec32 = vec3 = this.t == CrystalAuraActionState.BREAKING_CRYSTAL && this.J != null && this.J.isNotNull() && !this.J.M$src$Z$ff28xj() ? this.J.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk() : Vec3.create((double)this.KA.D() + 0.5, this.KA.B() + 1, (double)this.KA.G() + 0.5);
        if (!this.V.n(vec3, ExplosionType.Q, entityPlayerSP, world, this.b.L(), ((Double)this.D.K()).floatValue())) {
            return K;
        }
        return r;
    }

    private void w(String string) {
        boolean bl = this.t != CrystalAuraActionState.IDLE || this.KA != null || this.J != null || this.p != null;
        this.z();
        this.d();
        if (bl) {
            this.X("cleared tracking: " + string);
        }
    }

    private void X(CrystalAuraActionState crystalAuraActionState, String string) {
        if (this.t == crystalAuraActionState) {
            return;
        }
        this.t = crystalAuraActionState;
        this.Z.reset();
        this.X("state -> " + (Object)((Object)crystalAuraActionState) + " (" + string + ")");
        if (crystalAuraActionState == CrystalAuraActionState.IDLE) {
            this.KA = null;
            this.J = null;
            this.Kl = -1;
            this.z();
        }
    }

    @Override
    public String r() {
        return "Manual";
    }

    private void X(String string) {
    }

    private void Y(EntityPlayerSP entityPlayerSP, World world) {
        if (!this.X(world, this.KA)) {
            this.w("target obsidian no longer has valid crystal placement space");
            return;
        }
        if (!this.KF.hasTimeElapsed((long)this.c.B())) {
            KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
            if (keyBinding.u() || keyBinding.isPressed()) {
                KeyBinding.setKeyBindState(keyBinding, false);
            }
            return;
        }
        Entity entity = this.i(world, this.KA);
        if (entity != null) {
            this.J = entity;
            this.X(CrystalAuraActionState.BREAKING_CRYSTAL, "crystal already exists while placing");
            return;
        }
        if (!this.A.U(this.V) && !this.A.h(this.V, true)) {
            return;
        }
        if (entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v() != this.Kl) {
            if (this.F == -1) {
                this.F = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            }
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.Kl);
        }
        if (this.p != null && this.Kn.w() == this.p) {
            RayTraceResult rayTraceResult = this.Kn.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
            if (rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.entity())) {
                Entity hitEntity = rayTraceResult.getEntity();
                if (hitEntity.isNotNull() && hitEntity.isInstance(MappedClasses.Ze)) {
                    this.J = hitEntity;
                    this.X(CrystalAuraActionState.BREAKING_CRYSTAL, "spoofed mouse-over hit crystal while placing");
                    return;
                }
            }
            if (rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
                BlockPos blockPos = rayTraceResult.getBlockPos();
                int n = blockPos.P();
                int n2 = blockPos.o();
                int n3 = blockPos.d();
                BlockData blockData = this.KA;
                if (n == blockData.D() && n2 == blockData.B() && n3 == blockData.G()) {
                    KeyBinding keyBinding = Minecraft.gameSettings().F();
                    if (keyBinding.u() || keyBinding.isPressed()) {
                        KeyBinding.setKeyBindState(keyBinding, false);
                    }
                    KeyBinding keyBinding2 = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                    KeyBinding.setKeyBindState(keyBinding2, true);
                    KeyBinding.onTick(keyBinding2);
                    KeyBinding.setKeyBindState(keyBinding2, false);
                    this.X(CrystalAuraActionState.BREAKING_CRYSTAL, "placement click sent, waiting for crystal");
                }
            }
        }
    }

    private String O(BlockPos blockPos) {
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

    private void y(Vec3 vec3, Vec3 vec32) {
        if (this.p == null || this.p.V$src$Z$lb4tvc() || this.p instanceof AdaptiveRotationController && ((AdaptiveRotationController)this.p).O$src$Z$1lvi05g()) {
            this.p = new AdaptiveRotationController();
        }
        this.p.Y(((Double)this.s.K()).floatValue());
        this.p.u(false);
        this.p.w(true);
        this.p.k(true);
        this.p.t(0.0f);
        this.p.U(true);
        this.p.s(true);
        if (this.p instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)this.p).b(false);
        }
        float f = this.Kn.V();
        float f2 = this.Kn.x();
        RotationAngles rotationAngles = RotationVectorMath.d(vec3, vec32, f, f2);
        this.p.b(rotationAngles);
        if (this.Kn.w() != this.p) {
            this.Kn.S(this.p);
        }
    }

    private void A(EntityPlayerSP entityPlayerSP, World world) {
        RayTraceResult rayTraceResult;
        BlockData blockData;
        Block block;
        if (this.v == null || this.Kx == null) {
            this.w("obsidian placement data lost");
            return;
        }
        if (this.S == -1 || !this.V.p(entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(this.S))) {
            this.S = this.V.z(entityPlayerSP);
            if (this.S == -1) {
                this.w("lost obsidian from hotbar");
                return;
            }
        }
        if (!BlockUtil.u(block = world.getBlockByPos((blockData = this.KA).D(), blockData.B(), blockData.G()))) {
            BlockState blockState = world.getBlockState(BlockPos.create(blockData.D(), blockData.B(), blockData.G()));
            if (this.V.X(blockState)) {
                this.v = null;
                this.Kx = null;
                this.I = false;
                if (this.Kl != -1) {
                    entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.Kl);
                }
                this.X(CrystalAuraActionState.PLACING_CRYSTAL, "obsidian placed successfully");
            } else {
                this.w("unexpected block at obsidian placement position");
            }
            return;
        }
        if (!this.A.U(this.V) && !this.A.h(this.V, true)) {
            return;
        }
        KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
        if (keyBinding.u() || keyBinding.isPressed()) {
            KeyBinding.setKeyBindState(keyBinding, false);
        }
        if (entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v() != this.S) {
            if (this.F == -1) {
                this.F = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            }
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.S);
        }
        if (this.p != null && this.Kn.w() == this.p && (rayTraceResult = this.Kn.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic()).getTypeOfHit().equals(RayTraceResult_type.block())) {
            BlockPos blockPos = rayTraceResult.getBlockPos();
            int n = blockPos.P();
            int n2 = blockPos.o();
            int n3 = blockPos.d();
            int n4 = rayTraceResult.Z();
            if (n == this.v.D() && n2 == this.v.B() && n3 == this.v.G() && n4 == this.Kx.Y()) {
                KeyBinding keyBinding2 = Minecraft.gameSettings().F();
                if (keyBinding2.u() || keyBinding2.isPressed()) {
                    KeyBinding.setKeyBindState(keyBinding2, false);
                }
                KeyBinding.setKeyBindState(keyBinding, true);
                KeyBinding.onTick(keyBinding);
                KeyBinding.setKeyBindState(keyBinding, false);
                this.I = true;
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
                        this.w("world/player/screen invalid");
                    }
                    return;
                }
                boolean bl2 = KeyBindingInputState.q$src$Z$1enyqt3();
                if (!bl2) {
                    if (this.t != CrystalAuraActionState.IDLE || this.KA != null || this.J != null) {
                        this.w("right click released");
                    }
                    return;
                }
                if (!this.j(entityPlayerSP) && this.t != CrystalAuraActionState.PLACING_OBSIDIAN) {
                    if (this.t != CrystalAuraActionState.IDLE || this.KA != null || this.J != null) {
                        this.w("not holding crystal");
                    }
                    this.X("idle: not holding crystal");
                    return;
                }
                rayTraceResult = this.H(entityPlayerSP, worldClient);
                if (this.t == CrystalAuraActionState.IDLE) {
                    if (rayTraceResult.isNull()) {
                        this.X("idle: no mouse over");
                        return;
                    }
                    wrapper = null;
                    if (!rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
                        this.X("idle: screen crosshair not hitting a block");
                        return;
                    }
                    wrapper = rayTraceResult.getBlockPos();
                    if (this.U(worldClient, (BlockPos)wrapper)) {
                        this.Kl = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
                        BlockData blockData2 = new BlockData(((Vec3i)wrapper).P(), ((Vec3i)wrapper).o(), ((Vec3i)wrapper).d());
                        Entity crystal = this.i(worldClient, blockData2);
                        if (crystal != null) {
                            this.KA = blockData2;
                            this.J = crystal;
                            this.X(CrystalAuraActionState.BREAKING_CRYSTAL, "found crystal above hovered obsidian");
                            this.X("idle: 1 locked block for breaking at " + this.O((BlockPos)wrapper));
                        } else {
                            if (!this.X(worldClient, blockData2)) {
                                this.X("idle: rejected blocked crystal space at " + this.O((BlockPos)wrapper));
                                return;
                            }
                            this.KA = blockData2;
                            this.X(CrystalAuraActionState.PLACING_CRYSTAL, "hovered obsidian valid for crystal placement");
                            this.X("idle: 2 locked block for placing at " + this.O((BlockPos)wrapper));
                        }
                    } else {
                        if (!this.P.L().booleanValue()) {
                            this.X("idle: rejected invalid base block at " + this.O((BlockPos)wrapper));
                            return;
                        }
                        int n4 = this.V.z(entityPlayerSP);
                        if (n4 == -1) {
                            this.X("idle: place obsidian enabled but no obsidian in hotbar");
                            return;
                        }
                        BlockPos hoveredPos = rayTraceResult.getBlockPos();
                        block2 = worldClient.getBlockState(hoveredPos).getBlock();
                        if (BlockUtil.u(block2)) {
                            this.X("idle: hovered block is replaceable, cannot place obsidian on it");
                            return;
                        }
                        n3 = rayTraceResult.Z();
                        EnumFacing enumFacing = EnumFacing.T(n3);
                        blockData = new BlockData(hoveredPos.P(), hoveredPos.o(), hoveredPos.d());
                        BlockData placementTarget = blockData.R(enumFacing);
                        object2 = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B(), placementTarget.G());
                        if (!BlockUtil.u((Block)object2)) {
                            this.X("idle: obsidian placement target is not replaceable");
                            return;
                        }
                        object = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B() + 1, placementTarget.G());
                        block = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B() + 2, placementTarget.G());
                        if (!BlockUtil.u((Block)object) || !BlockUtil.u(block)) {
                            this.X("idle: no room for crystal above obsidian placement");
                            return;
                        }
                        this.Kl = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
                        this.S = n4;
                        this.v = blockData;
                        this.Kx = enumFacing;
                        this.I = false;
                        this.KA = placementTarget;
                        this.X(CrystalAuraActionState.PLACING_OBSIDIAN, "placing obsidian on hovered surface");
                        this.X("idle: placing obsidian at " + placementTarget.D() + "," + placementTarget.B() + "," + placementTarget.G());
                    }
                }
                if (this.KA != null && this.t != CrystalAuraActionState.PLACING_OBSIDIAN && !this.V.X((BlockState)(wrapper = worldClient.getBlockState(BlockPos.create(this.KA.D(), this.KA.B(), this.KA.G()))))) {
                    this.w("target obsidian no longer valid base block");
                    return;
                }
                if (this.t == CrystalAuraActionState.IDLE || !this.P.L().booleanValue() || !rayTraceResult.isNotNull() || !rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) break block39;
                wrapper = rayTraceResult.getBlockPos();
                boolean bl3 = this.KA != null && ((Vec3i)wrapper).P() == this.KA.D() && ((Vec3i)wrapper).o() == this.KA.B() && ((Vec3i)wrapper).d() == this.KA.G();
                boolean bl4 = bl = this.t == CrystalAuraActionState.PLACING_OBSIDIAN && this.v != null && ((Vec3i)wrapper).P() == this.v.D() && ((Vec3i)wrapper).o() == this.v.B() && ((Vec3i)wrapper).d() == this.v.G();
                if (bl3 || bl) break block39;
                block2 = worldClient.getBlockState((BlockPos)wrapper).getBlock();
                n3 = this.U(worldClient, (BlockPos)wrapper) ? 1 : 0;
                if (n3 == 0) break block40;
                int n5 = this.Kl;
                this.z();
                this.M$src$V$iomsrj();
                this.J = null;
                blockData = new BlockData(((Vec3i)wrapper).P(), ((Vec3i)wrapper).o(), ((Vec3i)wrapper).d());
                this.Kl = n5;
                Entity crystal = this.i(worldClient, blockData);
                if (crystal != null) {
                    this.KA = blockData;
                    this.J = crystal;
                    this.X(CrystalAuraActionState.BREAKING_CRYSTAL, "switched to new obsidian target (crystal found)");
                    break block39;
                } else if (this.X(worldClient, blockData)) {
                    this.KA = blockData;
                    this.X(CrystalAuraActionState.PLACING_CRYSTAL, "switched to new obsidian target");
                }
                break block39;
            }
            if (!BlockUtil.u(block2) && (n2 = this.V.z(entityPlayerSP)) != -1) {
                int n6 = rayTraceResult.Z();
                EnumFacing enumFacing = EnumFacing.T(n6);
                BlockData supportBlock = new BlockData(((Vec3i)wrapper).P(), ((Vec3i)wrapper).o(), ((Vec3i)wrapper).d());
                BlockData placementTarget = supportBlock.R(enumFacing);
                block = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B(), placementTarget.G());
                Block block3 = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B() + 1, placementTarget.G());
                Block block4 = worldClient.getBlockByPos(placementTarget.D(), placementTarget.B() + 2, placementTarget.G());
                if (BlockUtil.u(block) && BlockUtil.u(block3) && BlockUtil.u(block4)) {
                    int n7 = this.Kl;
                    this.z();
                    this.M$src$V$iomsrj();
                    this.J = null;
                    this.Kl = n7;
                    this.S = n2;
                    this.v = supportBlock;
                    this.Kx = enumFacing;
                    this.I = false;
                    this.KA = placementTarget;
                    this.X(CrystalAuraActionState.PLACING_OBSIDIAN, "switched to new obsidian placement target");
                }
            }
        }
        if (this.t != CrystalAuraActionState.PLACING_OBSIDIAN && !this.j(entityPlayerSP)) {
            this.w("lost held crystal");
            return;
        }
        if (this.t != CrystalAuraActionState.PLACING_OBSIDIAN) {
            this.Kl = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        }
        switch (this.t) {
            case BREAKING_CRYSTAL: {
                this.e(entityPlayerSP, worldClient);
                break;
            }
            case PLACING_CRYSTAL: {
                this.Y(entityPlayerSP, worldClient);
                break;
            }
            case PLACING_OBSIDIAN: {
                this.A(entityPlayerSP, worldClient);
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

    private boolean U(World world, BlockPos blockPos) {
        return blockPos != null && this.V.X(world.getBlockState(blockPos));
    }

    private Vec3 k(EntityPlayerSP entityPlayerSP, World world, Vec3 vec3, BlockData blockData, float f, float f2) {
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
                if (this.t == CrystalAuraActionState.BREAKING_CRYSTAL && (bl3 = entity.isNotNull()) && (bl2 = entity.isInstance(MappedClasses.Ze))) {
                    if (((ModeSelection)this.U.K()).equals(this.o) && !Minecraft.V()) {
                        this.H = entity.S();
                    }
                    this.J = null;
                    this.X(CrystalAuraActionState.PLACING_CRYSTAL, "attack packet sent");
                    this.KF.reset();
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
            if (this.t == CrystalAuraActionState.BREAKING_CRYSTAL) {
                boolean bl4 = entity.isNotNull();
                boolean bl5 = bl4;
                if (bl5 && !Minecraft.V()) {
                    this.H = entity.S();
                }
                this.J = null;
                this.X(CrystalAuraActionState.PLACING_CRYSTAL, "attack packet sent");
                this.KF.reset();
            }
        }
    }

    @EventHandler
    public void D(EventWorldChange eventWorldChange) {
        this.z();
        this.d();
    }

    private void z() {
        if (this.p != null) {
            RotationManager.b.v(this.p);
            if (this.Kn.w() == this.p) {
                this.p.w(false);
                this.p.u(true);
                if (this.p instanceof AdaptiveRotationController) {
                    ((AdaptiveRotationController)this.p).b(true);
                }
            }
            this.p = null;
        }
        this.A.X(this.V);
        if (this.F != -1) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            if (!entityPlayerSP.isNull()) {
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.F);
            }
            this.F = -1;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (!this.K_.L().booleanValue()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = eventRender3D.getThePlayer();
        WorldClient worldClient = eventRender3D.getWorld();
        if (entityPlayerSP.isNull() || worldClient.isNull() || this.t == CrystalAuraActionState.IDLE || this.KA == null || !KeyBindingInputState.q$src$Z$1enyqt3()) {
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
            RenderUtil.w(RenderManager.getInterpolatedRenderPosX(), RenderManager.getInterpolatedRenderPosY(), RenderManager.getInterpolatedRenderPosZ(), this.KA.D(), this.KA.B(), this.KA.G(), this.D(entityPlayerSP, worldClient));
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
    public void z(EventPostTick eventPostTick) {
        Wrapper wrapper;
        WorldClient worldClient = eventPostTick.getWorld();
        int n = ExplosionType.q();
        EntityPlayerSP entityPlayerSP = eventPostTick.getThePlayer();
        if (this.H != -1 && worldClient.isNotNull()) {
            wrapper = ((World)worldClient).V(this.H);
            if (wrapper.isNotNull()) {
                worldClient.M((Entity)wrapper);
            }
            this.H = -1;
        }
        if (worldClient.isNull() || entityPlayerSP.isNull() || this.t == CrystalAuraActionState.IDLE || this.KA == null) {
            return;
        }
        if (!this.A.U(this.V) && !this.A.h(this.V, true)) {
            return;
        }
        wrapper = entityPlayerSP.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk().addVector(0.0, entityPlayerSP.X(), 0.0);
        if (this.t == CrystalAuraActionState.BREAKING_CRYSTAL && this.J != null && this.J.isNotNull() && !this.J.M$src$Z$ff28xj()) {
            Vec3 vec3 = RotationUtil.M((Vec3)wrapper, this.J.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0).n();
            this.y((Vec3)wrapper, vec3);
        } else if (this.t == CrystalAuraActionState.BREAKING_CRYSTAL) {
            BlockData blockData = this.KA;
            Vec3 vec3 = Vec3.create((double)blockData.D() + 0.5, blockData.B() + 1, (double)blockData.G() + 0.5);
            this.y((Vec3)wrapper, vec3);
        } else if (this.t == CrystalAuraActionState.PLACING_CRYSTAL) {
            float f;
            BlockData blockData = this.KA;
            float f2 = this.Kn.V();
            Vec3 vec3 = this.k(entityPlayerSP, worldClient, (Vec3)wrapper, blockData, f2, f = this.Kn.x());
            if (vec3 == null) {
                vec3 = Vec3.create((double)blockData.D() + 0.5, blockData.B() + 1, (double)blockData.G() + 0.5);
            }
            this.y((Vec3)wrapper, vec3);
        } else if (this.t == CrystalAuraActionState.PLACING_OBSIDIAN && this.v != null && this.Kx != null) {
            float f;
            PlacementTarget placementTarget = new PlacementTarget(this.v, this.Kx);
            float f3 = this.Kn.V();
            Vec3 vec3 = ClutchPlacementPathUtils.D(entityPlayerSP, worldClient, (Vec3)wrapper, placementTarget, f3, f = this.Kn.x());
            if (vec3 == null) {
                vec3 = Vec3.create((double)this.v.D() + 0.5 + (double)this.Kx.w$src$Lgg_vape_wrapper_impl_Vec3i_$ixeccr().P() * 0.5, (double)this.v.B() + 0.5 + (double)this.Kx.w$src$Lgg_vape_wrapper_impl_Vec3i_$ixeccr().o() * 0.5, (double)this.v.G() + 0.5 + (double)this.Kx.w$src$Lgg_vape_wrapper_impl_Vec3i_$ixeccr().d() * 0.5);
            }
            this.y((Vec3)wrapper, vec3);
        }
    }

    private void e(EntityPlayerSP entityPlayerSP, World world) {
        Entity entity;
        RayTraceResult rayTraceResult;
        Wrapper wrapper;
        if (this.J == null || this.J.isNull() || this.J.M$src$Z$ff28xj()) {
            this.J = null;
            Entity crystal = this.i(world, this.KA);
            if (crystal != null) {
                this.J = crystal;
            } else {
                this.X(CrystalAuraActionState.PLACING_CRYSTAL, "crystal missing above obsidian");
                this.KF.reset();
                return;
            }
        }
        if (!this.V.n((Vec3)(wrapper = this.J.I$src$Lgg_vape_wrapper_impl_Vec3_$q14opk()), ExplosionType.Q, entityPlayerSP, world, this.b.L(), ((Double)this.D.K()).floatValue())) {
            this.w("unsafe self damage while breaking crystal");
            return;
        }
        if (!this.A.U(this.V) && !this.A.h(this.V, true)) {
            return;
        }
        KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
        if (keyBinding.u() || keyBinding.isPressed()) {
            KeyBinding.setKeyBindState(keyBinding, false);
        }
        if (this.p != null && this.Kn.w() == this.p && (rayTraceResult = this.Kn.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic()).getTypeOfHit().equals(RayTraceResult_type.entity()) && (entity = rayTraceResult.getEntity()).isInstance(MappedClasses.Ze)) {
            KeyBinding keyBinding2 = Minecraft.gameSettings().F();
            if (keyBinding2.u() || keyBinding2.isPressed()) {
                KeyBinding.setKeyBindState(keyBinding2, false);
            }
            if (((ModeSelection)this.U.K()).equals(this.L)) {
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

    private boolean X(World world, BlockData blockData) {
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

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean m(RayTraceResult rayTraceResult, World world, BlockData blockData) {
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
        return this.V.X(world.getBlockState(blockPos));
    }

    static {
        K = new Color(255, 0, 0, 25);
        r = new Color(0, 255, 255, 25);
        K3 = new Color(0, 0, 255, 25);
    }

    public CrystalAuraTargetSubModule(Mod mod, String string) {
        super(mod, string);
        this.D = NumberValue.create(this, "Max self damage", "#", "HP", 0.0, 19.0, 20.0, 1.0, "Maximum self damage allowed");
        this.c = RandomValue.G(this, "Delay", "#", "ms", 0.0, 0.0, 100.0, 500.0, 1.0, "Delay between break/place cycles");
        this.L = new ModeOption("Rapid fire");
        this.o = new ModeOption("Predict");
        this.C = new ModeOption("None");
        this.U = ModeValue.create((Object)this, "Optimization", "Controls crystal optimization behavior\nNone - No crystal optimization\nRapid fire - Crystals are broken and replaced in same tick when possible\nPredict - Predicts explosion timing and pre-removes crystal for faster placement(potentially unsafe)", (ModeSelection)this.C, this.L, this.o, this.C);
        this.P = BooleanValue.create(this, "Place obsidian", false, "Automatically places obsidian from hotbar when hovering a valid placement surface");
        this.K_ = BooleanValue.create(this, "Show target block", true, "Renders a highlight on the target obsidian block");
        this.Kn = RotationManager.b;
        this.A = SharedModuleControlClaims.I;
        this.t = CrystalAuraActionState.IDLE;
        this.KF = new TimerUtil();
        this.Z = new TimerUtil();
        this.V = (CrystalAura)mod;
        this.addValue(this.s, this.b, this.D, this.c, this.U, this.P, this.K_);
    }

    @Override
    public void onDisable() {
        this.z();
        this.d();
    }

    private void M$src$V$iomsrj() {
        this.S = -1;
        this.v = null;
        this.Kx = null;
        this.I = false;
    }

    private boolean j(EntityPlayerSP entityPlayerSP) {
        return entityPlayerSP.isNotNull() && this.V.u(entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt());
    }

    @Override
    public void onEnable() {
        this.d();
    }

    private RayTraceResult H(EntityPlayerSP entityPlayerSP, World world) {
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
