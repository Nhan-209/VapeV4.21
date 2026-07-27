package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.input.AttackKeyController;
import gg.vape.input.InputEventDispatcher;
import gg.vape.input.KeyBindingHelper;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.combat.silentaura.SilentAuraAimJitter;
import gg.vape.module.combat.silentaura.SilentAuraClicker;
import gg.vape.module.combat.silentaura.SilentAuraEntityIdComparator;
import gg.vape.module.combat.silentaura.SilentAuraRotationController;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.render.Freecam;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.MouseRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ItemLimitData;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.EntityAngleComparator;
import gg.vape.utils.EntityArmorValueComparator;
import gg.vape.utils.EntityDistanceComparator;
import gg.vape.utils.EntityEquipmentValueComparator;
import gg.vape.utils.EntityHealthComparator;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RayTraceUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.Vec3d;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.EntityTargetFilterValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomClickDelayValue;
import gg.vape.value.RandomValue;
import gg.vape.value.Value;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.TitledScreen;
import gg.vape.wrapper.impl.Vec3;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SilentAura
extends Mod {
    private final ModeOption distanceMode;
    private final SilentAuraAimJitter xJitter;
    private long nextBreakBlockTime = 0L;
    private final SilentAuraAimJitter pitchJitter;
    public final ModeOption armorMode;
    public final BooleanValue showTarget;
    private boolean perfectSwingAttackPending = false;
    public final RandomClickDelayValue Ze;
    private float pitchGainFactor = 1.0f;
    public final ModeOption threatMode;
    public final NumberValue maxAngle;
    private AdaptiveRotationController rotationController;
    public final EntityTargetFilterValue targetFilter = EntityTargetFilterValue.W(this);
    public final ModeOption closestMode;
    private final BooleanValue switchTargets;
    public final BooleanValue j;
    public final ModeOption yawMode;
    private final RotationControlClaim rotationClaim;
    private EntityLivingBase target = null;
    private static SilentAuraClicker clicker;
    private final RandomValue breakBlocksDelay;
    private boolean readyToAttack;
    public final ModeOption ringMode;
    public final BooleanValue Z5;
    public final LimitValue ZP;
    private float pitchAccel = 0.0f;
    private float yawAccel = 0.0f;
    private final BooleanValue perfectSwing;
    public final ModeOption centerMode;
    private float yawGainFactor = 1.0f;
    private final ColorValue attackColor;
    public final NumberValue Zt = NumberValue.E(this, "Aim speed", "#.#", "", 1.0, 7.0, 10.0, "How fast aiming will be done silently");
    public final ModeOption healthMode;
    private boolean onTarget = false;
    private final BooleanValue breakBlocksWhitelist;
    public final BooleanValue disableOnDeath;
    private final SilentAuraAimJitter zJitter;
    private boolean breakingBlocks;
    private boolean toggledOff = false;
    public final ModeOption boxMode;
    public final NumberValue extraSwingDistance = NumberValue.E(this, "Extra swing distance", "#.#", "", 0.0, 1.0, 3.0, "Extra distance past attack range at which aura will begin to engage, before attacking");
    private final BooleanValue breakBlocks;
    private static Freecam freecam;
    private final LimitValue blockBreakItems;
    private final ColorValue targetColor;
    private final Random random;
    private final TimerUtil breakBlocksTimer;
    public ModeValue renderType;
    private float smoothGainFactor = 1.0f;
    private float yawSmoothFactor = 1.0f;
    private static final long MODULE_ID;
    public ModeValue targetArea;
    public ModeValue targetMode;
    private boolean deathHandled = false;

    public EntityLivingBase j$src$Lgg_vape_wrapper_impl_EntityLivingBase_$si0dgx() {
        return this.target;
    }

    public static EntityLivingBase B(SilentAura silentAura) {
        return silentAura.target;
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (this.showTarget.L().booleanValue() && this.target != null && Minecraft.currentScreen().isNull()) {
            float f;
            float f2 = f = this.target.isInstance(MappedClasses.Yl) || this.target.isInstance(MappedClasses.lG) ? 0.7f : this.target.f$src$F$fst3ac();
            if (this.ringMode.o()) {
                GuiRenderPrimitives.R(this.target.c(), this.target.A(), this.target.Z(), 50.0f, f, this.target.Y(), this.isLookingAtTarget() ? this.attackColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3() : this.targetColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            } else {
                RenderUtil.k(this.target, 1.0, null, this.isLookingAtTarget() ? this.attackColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3() : this.targetColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), eventRender3D.getTicks());
            }
        }
    }

    private boolean passesItemFilter(EntityLivingBase entityLivingBase) {
        if (this.j.L().booleanValue()) {
            ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
            if (!this.ZP.isValid(itemStack, false)) {
                return false;
            }
            return this.targetFilter.c(entityLivingBase);
        }
        return this.targetFilter.c(entityLivingBase);
    }

    @Override
    public String r() {
        if (ForgeVersion.MC_1_12_2.d() && this.perfectSwing.L().booleanValue()) {
            float f = Minecraft.thePlayer().getCooledAttackStrength(0.0f);
            if (f == 1.0f) {
                return "\u00a76Ready";
            }
            return String.format("%.1f", Float.valueOf(f));
        }
        return this.Ze.c() + "cps";
    }

    public boolean k(EntityLivingBase entityLivingBase) {
        if (entityLivingBase.isNull()) {
            return false;
        }
        if (entityLivingBase.equals(Minecraft.thePlayer())) {
            return false;
        }
        if (entityLivingBase.w$src$F$15l9epb() <= 0.0f || entityLivingBase.M$src$Z$ff28xj()) {
            return false;
        }
        if (!this.isInRange(entityLivingBase)) {
            return false;
        }
        if (RotationUtil.a(Minecraft.thePlayer(), entityLivingBase) > ((Double)this.maxAngle.K()).intValue() / 2) {
            return false;
        }
        if (Vape.INSTANCE.getFriendManager().isFriend(entityLivingBase)) {
            return false;
        }
        if (entityLivingBase.equals(Minecraft.thePlayer().S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12())) {
            return false;
        }
        return this.passesItemFilter(entityLivingBase);
    }

    private boolean isInRange(EntityLivingBase entityLivingBase) {
        double[] dArray = this.computeAimCoords(entityLivingBase);
        double d = dArray[0];
        double d2 = dArray[1];
        double d3 = dArray[2];
        double d4 = Minecraft.thePlayer().i(d, d2, d3);
        return d4 <= this.getAttackRange();
    }

    private void randomizeGainFactors() {
        this.yawGainFactor = 0.85f + this.random.nextFloat() * 0.3f;
        this.yawSmoothFactor = 0.85f + this.random.nextFloat() * 0.3f;
        this.smoothGainFactor = 0.8f + this.random.nextFloat() * 0.4f;
        this.pitchGainFactor = 0.85f + this.random.nextFloat() * 0.3f;
    }

    public boolean U() {
        if (ForgeVersion.MC_1_12_2.d() && this.perfectSwing.L().booleanValue()) {
            float f = Minecraft.thePlayer().getCooledAttackStrength(0.0f);
            return f == 1.0f;
        }
        return this.Ze.R();
    }

    private boolean handleBreakBlocks() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return false;
        }
        if (this.breakBlocks.L().booleanValue() && this.breakBlocksTimer.hasTimeElapsed(this.nextBreakBlockTime)) {
            if (this.breakBlocksWhitelist.L().booleanValue() && !this.blockBreakItems.A(entityPlayerSP.getHeldItemHand())) {
                return false;
            }
            if (Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
                return false;
            }
            RayTraceResult rayTraceResult = RayTraceUtil.o();
            if (rayTraceResult.isNotNull() && rayTraceResult.isBlockHit() && ClientSettings.M()) {
                KeyBinding keyBinding = Minecraft.gameSettings().F();
                KeyBindingHelper.v(keyBinding, true, false);
                return true;
            }
            this.nextBreakBlockTime = (long)this.breakBlocksDelay.B();
            this.breakBlocksTimer.reset();
        }
        return false;
    }

    public SilentAura() {
        super("SilentAura", (int)MODULE_ID, Category.g, "Simulates feel of Killaura\nAttacks and aims safely using built in AutoClicker to click, and Silent Aim system to aim");
        this.Z5 = BooleanValue.create(this, "Require mouse down", false);
        this.disableOnDeath = BooleanValue.create(this, "Disable on death", false);
        this.showTarget = BooleanValue.create(this, "Show target", false);
        this.switchTargets = BooleanValue.create(this, "Switch", false, "Attacks other targets while current target is in hit cooldown");
        this.j = BooleanValue.create(this, "Limit to items", false, "Aura functions only while holding selected items");
        this.breakBlocks = BooleanValue.create(this, "Break blocks", false, "Prevents from aiming while attempting to break blocks");
        this.breakBlocksDelay = RandomValue.G(this, "Break blocks delay", "#", "", 0.0, 0.0, 10.0, 2000.0, 1.0, "Delay in milliseconds before breaking blocks");
        this.breakBlocksWhitelist = BooleanValue.create(this, "Break blocks whitelist", false);
        this.blockBreakItems = LimitValue.n(this, "SilentBlockBreakingItems", "Block breaking items", LimitValue.r, Arrays.asList(new ItemLimitData("pickaxes"), new ItemLimitData("shovels")));
        this.Ze = RandomClickDelayValue.M(this, "Attacks per Second", "#.#", "", 1.0, 6.0, 13.0, 20.0);
        this.maxAngle = NumberValue.create(this, "Max angle", "#", "", 1.0, 120.0, 360.0, 5.0, "Angle at which targets will be acquired and aimed at\n(From your cursor)");
        this.targetColor = ColorValue.b(this, "Target color", new Color(255, 200, 112), 50);
        this.attackColor = ColorValue.L(this, "Attack color", new Color(255, 0, 0, 100));
        this.distanceMode = new ModeOption("Distance");
        this.yawMode = new ModeOption("Yaw");
        this.armorMode = new ModeOption("Armor");
        this.threatMode = new ModeOption("Threat");
        this.healthMode = new ModeOption("Health");
        this.targetMode = ModeValue.create((Object)this, "Target mode", "How Aura should prioritize targets\nArmor/Threat will default to Distance for non player targets", (ModeSelection)this.distanceMode, this.distanceMode, this.yawMode, this.armorMode, this.threatMode, this.healthMode);
        this.ZP = LimitValue.n(this, "silentaura-alloweditems", "Allowed Items", LimitValue.r, Collections.emptyList());
        this.centerMode = new ModeOption("Center");
        this.closestMode = new ModeOption("Closest");
        this.targetArea = ModeValue.create((Object)this, "Target area", "Where Aura will aim towards\nCenter: Center of entity\nClosest: Closest position on entity hitbox", (ModeSelection)this.centerMode, this.centerMode, this.closestMode);
        this.ringMode = new ModeOption("Ring");
        this.boxMode = new ModeOption("Box");
        this.renderType = ModeValue.create((Object)this, "Render type", this.ringMode, this.ringMode, this.boxMode);
        this.perfectSwing = BooleanValue.create(this, "Perfect swing", false, "Only attacks when there is no attack cooldown\nAdditionally, only swings when hovering(trigger)");
        this.breakBlocksTimer = new TimerUtil();
        this.rotationClaim = SharedModuleControlClaims.I;
        this.random = new Random();
        this.pitchJitter = new SilentAuraAimJitter(-0.3, 0.25);
        this.xJitter = new SilentAuraAimJitter(-0.15, 0.15);
        this.zJitter = new SilentAuraAimJitter(-0.15, 0.15);
        this.perfectSwing.U(false).z(this.Ze);
        this.addValue(this.targetFilter, this.Zt, this.Ze, this.extraSwingDistance, this.maxAngle, this.targetMode, this.targetArea);
        this.showTarget.K(this.targetColor, this.attackColor, this.renderType);
        this.breakBlocks.K(this.breakBlocksDelay, this.breakBlocksWhitelist);
        this.breakBlocksWhitelist.K(this.blockBreakItems);
        this.U(this.perfectSwing, ForgeVersion.MC_1_8_9.N());
        this.U(this.switchTargets, ForgeVersion.MC_1_8_9.H());
        this.addValue(new Value[]{this.disableOnDeath, this.breakBlocks, this.breakBlocksDelay, this.breakBlocksWhitelist, this.blockBreakItems, this.Z5, this.showTarget, this.targetColor, this.attackColor, this.renderType, this.j.K(this.ZP), this.ZP});
        this.j.l(this.ZP);
        this.rotationClaim.l(this, 5);
        this.Ze.V(0);
    }

    static {
        MODULE_ID = 267655872188715318L;
    }

    public boolean H() {
        if (Minecraft.theWorld().isNull()) {
            return false;
        }
        if (!this.U()) {
            return false;
        }
        if (!this.readyToAttack) {
            return false;
        }
        if (Minecraft.currentScreen().isNotNull()) {
            return false;
        }
        return !this.breakingBlocks;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean P() {
        if (ForgeVersion.MC_1_12_2.d() && this.perfectSwing.L().booleanValue()) {
            return false;
        }
        return this.H();
    }

    public static RotationControlClaim A(SilentAura silentAura) {
        return silentAura.rotationClaim;
    }

    private void updateRotationColors() {
        if (this.target != null && this.isControllingRotation()) {
            this.onTarget = this.isLookingAtTarget();
            this.rotationController.T(this.attackColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            this.rotationController.z(this.targetColor.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            this.rotationController.L(this.onTarget);
        }
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void onTick(EventPreTick eventPreTick) {
        this.breakingBlocks = this.handleBreakBlocks();
        if (this.perfectSwingAttackPending) {
            this.perfectSwingAttackPending = false;
            AttackKeyController.Q();
        }
        if (Minecraft.thePlayer().isNull()) {
            this.N();
            return;
        }
        if (this.toggledOff || !this.r$src$Z$14eylz9() || this.breakingBlocks) {
            this.N();
            return;
        }
        if (clicker == null) {
            clicker = Vape.INSTANCE.getModManager().getMod(SilentAuraClicker.class);
        }
        if (!clicker.r$src$Z$14eylz9()) {
            clicker.Y(true);
        }
        this.updateRotationColors();
        if (this.disableOnDeath.L().booleanValue()) {
            if (Minecraft.thePlayer().M$src$Z$ff28xj() || Minecraft.thePlayer().w$src$F$15l9epb() <= 0.0f) {
                this.F();
                return;
            }
            if (ForgeVersion.MC_1_16_5.d()) {
                GuiScreen guiScreen = Minecraft.currentScreen();
                if (guiScreen.isNotNull()) {
                    if (!this.deathHandled && guiScreen.isInstance(MappedClasses.D2)) {
                        this.deathHandled = true;
                        this.F();
                        return;
                    }
                    this.deathHandled = false;
                }
            } else {
                TitledScreen titledScreen = Minecraft.k();
                if (titledScreen.isNotNull()) {
                    String string = titledScreen.E();
                    if (!this.deathHandled && string != null && (string.toLowerCase().contains("died") || string.toLowerCase().contains("dead"))) {
                        this.deathHandled = true;
                        this.F();
                        return;
                    }
                    if (string == null || string.equals("")) {
                        this.deathHandled = false;
                    }
                }
            }
        }
        if (!InputEventDispatcher.getInstance().getFocusState().isFocused() || this.Z5.L().booleanValue() && !ClientSettings.M()) {
            this.N();
            return;
        }
        this.updateTarget();
        this.updateAim();
        if (ForgeVersion.MC_1_12_2.d() && this.perfectSwing.L().booleanValue() && this.H()) {
            AttackKeyController.Q();
            this.perfectSwingAttackPending = AttackKeyController.u(this);
        }
    }

    private boolean isControllingRotation() {
        MouseRotationController mouseRotationController = RotationManager.b.w();
        return mouseRotationController != null && mouseRotationController.equals(this.rotationController);
    }

    private void updateTarget() {
        Wrapper wrapper;
        ArrayList<Entity> arrayList = new ArrayList<Entity>();
        if (this.shouldSkip()) {
            return;
        }
        ArrayList arrayList2 = new ArrayList(Minecraft.theWorld().z());
        for (Object e : arrayList2) {
            EntityLivingBase entityLivingBase;
            wrapper = new Entity(e);
            if (ClientSettings.H && wrapper.isInstance(MappedClasses.FT) || !wrapper.isInstance(MappedClasses.zm) || !this.k(entityLivingBase = new EntityLivingBase(e))) continue;
            arrayList.add(entityLivingBase);
        }
        if (this.targetMode.K() == this.yawMode) {
            arrayList.sort(new EntityAngleComparator());
        } else if (this.targetMode.K() == this.distanceMode) {
            arrayList.sort(new EntityDistanceComparator());
        } else if (this.targetMode.K() == this.threatMode) {
            arrayList.sort(new EntityArmorValueComparator());
        } else if (this.targetMode.K() == this.armorMode) {
            arrayList.sort(new EntityEquipmentValueComparator());
        } else if (this.targetMode.K() == this.healthMode) {
            arrayList.sort(new EntityHealthComparator());
        }
        if (this.switchTargets.L().booleanValue()) {
            arrayList.sort(new SilentAuraEntityIdComparator(this));
        }
        if (!arrayList.isEmpty()) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)arrayList.get(0);
            boolean bl = false;
            if (this.target != null && !this.target.equals(entityLivingBase)) {
                bl = true;
            }
            if (this.target == null || bl) {
                AttackKeyController.Q();
                this.randomizeGainFactors();
            }
            this.target = entityLivingBase;
            if (!this.rotationClaim.U(this)) {
                this.rotationClaim.h(this, true);
            }
            this.readyToAttack = false;
            if (this.rotationController != null) {
                if (ForgeVersion.MC_1_12_2.d() && this.perfectSwing.L().booleanValue()) {
                    wrapper = RotationManager.b.n();
                    if (wrapper.isNotNull() && ((RayTraceResult)wrapper).getEntity().isNotNull() && ((RayTraceResult)wrapper).getEntity().equals(this.target)) {
                        this.readyToAttack = true;
                    }
                } else {
                    double d = RotationUtil.L(this.target);
                    if (d < 3.0 && this.isInRange(this.target)) {
                        this.readyToAttack = true;
                    }
                }
            }
        } else {
            this.N();
        }
    }

    private double[] computeAimCoords(EntityLivingBase entityLivingBase) {
        if (this.targetArea.K() == this.closestMode) {
            Vec3d vec3d = RotationUtil.T(Minecraft.thePlayer(), entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0);
            double d = entityLivingBase.z() - entityLivingBase.f();
            double d2 = entityLivingBase.N() - entityLivingBase.H();
            double d3 = entityLivingBase.h() - entityLivingBase.R();
            double d4 = vec3d.Y();
            double d5 = entityLivingBase.N();
            double d6 = vec3d.o();
            double d7 = d4 - d;
            double d8 = entityLivingBase.H();
            double d9 = d6 - d3;
            return new double[]{d4, d5, d6, d7, d8, d9};
        }
        return new double[]{entityLivingBase.z(), entityLivingBase.N(), entityLivingBase.h(), entityLivingBase.f(), entityLivingBase.H(), entityLivingBase.R()};
    }

    private void updateAim() {
        if (Minecraft.theWorld().isNull()) {
            this.N();
            return;
        }
        boolean bl = this.target != null && this.k(this.target);
        boolean bl2 = this.rotationClaim.U(this);
        if (bl && bl2) {
            this.resetJitter();
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            double[] dArray = this.computeAimCoords(this.target);
            double d = dArray[0];
            double d2 = dArray[1];
            double d3 = dArray[2];
            double d4 = dArray[3];
            double d5 = dArray[4];
            double d6 = dArray[5];
            double d7 = entityPlayerSP.i(d, d2, d3);
            double d8 = d - d4;
            double d9 = d3 - d6;
            double d10 = Math.sqrt(d8 * d8 + d9 * d9);
            double d11 = d + this.xJitter.b() * (1.0 + d10);
            double d12 = d3 + this.zJitter.b() * (1.0 + d10);
            double d13 = d2;
            double d14 = this.target.Y();
            double d15 = entityPlayerSP.N() + 1.62;
            double d16 = d15 < d13 ? d13 + this.pitchJitter.b() * 0.5 : Math.min(d15, d13 + d14) - 0.275 + this.pitchJitter.b();
            if (this.rotationController == null) {
                this.rotationController = new SilentAuraRotationController(this);
                this.rotationController.b(false);
                this.rotationController.w(true);
                this.rotationController.k(true);
                this.rotationController.t(0.0f);
                this.rotationController.U(false);
                this.rotationController.s(false);
                RotationManager.b.S(this.rotationController);
            } else {
                this.rotationController.b(false);
                this.rotationController.U(false);
                this.rotationController.s(false);
                RotationAngles rotationAngles = this.rotationController.j(Vec3.create(d11, d16, d12));
                float f = (float)RotationUtil.h(entityPlayerSP, d, d16, d3);
                float f2 = RotationManager.b.V();
                float f3 = RotationManager.b.x();
                float f4 = MathUtil.wrapAngleTo180(rotationAngles.z() - f2);
                float f5 = MathUtil.wrapAngleTo180(f - f3);
                float f6 = f3 - RotationManager.b.H();
                float f7 = f2 - RotationManager.b.s();
                float f8 = 0.05f;
                boolean bl3 = Math.signum(f4) == Math.signum(f7);
                double d17 = Math.sqrt(entityPlayerSP.t() * entityPlayerSP.t() + entityPlayerSP.T() * entityPlayerSP.T());
                float f9 = 0.45f * this.yawGainFactor;
                float f10 = 0.91f * this.yawSmoothFactor;
                float f11 = (this.onTarget ? 0.05f : 0.1f) * this.smoothGainFactor;
                float f12 = 0.33f * this.pitchGainFactor;
                double d18 = entityPlayerSP.q();
                if (Math.abs(d18) > 0.1) {
                    f5 *= (float)(1.0 + Math.random() * 0.32);
                }
                if (bl3 && Math.abs(f4) < 20.0f) {
                    f11 *= 2.5f;
                    f7 *= (float)(1.0 + Math.min(d10 + d17, 0.25));
                }
                if (d7 < 0.8) {
                    double d19 = d7 / 0.8;
                    f5 *= (float)(d19 * d19);
                    f4 *= (float)d19;
                }
                float f13 = f5 - f6 + f7 * f8 * (float)(Math.random() >= 0.5 ? -1 : 1);
                float f14 = f4 - f7;
                this.yawAccel += f13 * f8;
                this.pitchAccel += f14 * f8;
                float f15 = f9 * f13 + f10 * this.yawAccel + 0.0f;
                float f16 = f11 * f14 + f12 * this.pitchAccel + 0.0f;
                if (Math.abs(f4) > 120.0f) {
                    this.pitchAccel = 0.0f;
                    f16 = 0.0f;
                }
                if (Minecraft.currentScreen().isNotNull()) {
                    this.pitchAccel = 0.0f;
                    this.yawAccel = 0.0f;
                }
                this.rotationController.g(f2 + f4 + f16 / 3.0f, f3 + f15);
            }
            if (RotationManager.b.w() == null || !this.isControllingRotation() && RotationManager.b.u()) {
                RotationManager.b.S(this.rotationController);
            }
        } else {
            this.N();
        }
    }

    @Override
    public void s(boolean bl, boolean bl2) {
        this.yawAccel = 0.0f;
        this.pitchAccel = 0.0f;
        if (!bl && this.isControllingRotation()) {
            this.toggledOff = !this.toggledOff;
        } else {
            this.toggledOff = false;
            super.s(bl, bl2);
        }
    }

    private boolean isLookingAtTarget() {
        RayTraceResult rayTraceResult;
        boolean bl = false;
        if (this.target != null && this.isControllingRotation() && this.isInRange(this.target) && (rayTraceResult = RotationManager.b.n()) != null && rayTraceResult.isNotNull() && this.target.equals(rayTraceResult.getEntity())) {
            bl = true;
        }
        return bl;
    }

    public void N() {
        this.yawAccel = 0.0f;
        this.pitchAccel = 0.0f;
        this.target = null;
        this.readyToAttack = false;
        if (this.rotationController != null && this.isControllingRotation()) {
            this.rotationController.U(true);
            this.rotationController.s(true);
            RotationManager.b.v(this.rotationController);
        }
        if (RotationManager.b.w() == null || RotationManager.b.w() != this.rotationController || this.rotationController != null && this.rotationController.O$src$Z$1lvi05g() && this.rotationController.V$src$Z$lb4tvc()) {
            this.rotationController = null;
            this.rotationClaim.X(this);
            if (this.toggledOff) {
                this.toggledOff = false;
                super.s(false, true);
            }
        }
    }

    private double getAttackRange() {
        return 3.0 + (Double)this.extraSwingDistance.K();
    }

    private void resetJitter() {
        this.pitchJitter.v();
        this.xJitter.v();
        this.zJitter.v();
    }

    private boolean shouldSkip() {
        if (freecam == null) {
            freecam = Vape.INSTANCE.getModManager().getMod(Freecam.class);
        }
        return this.toggledOff || freecam != null && freecam.r$src$Z$14eylz9() || this.breakingBlocks || this.rotationClaim.e(this) && !this.rotationClaim.h(this, true);
    }

    @Override
    public void onDisable() {
        if (this.rotationController != null) {
            this.rotationController = null;
        }
        if (this.perfectSwingAttackPending) {
            AttackKeyController.Q();
            this.perfectSwingAttackPending = false;
        }
        this.rotationClaim.X(this);
    }
}

