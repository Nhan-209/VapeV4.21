package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventClickMouse;
import gg.vape.event.impl.EventKeyInputBase;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.event.impl.EventPacketSend;
import gg.vape.event.impl.EventPostAttack;
import gg.vape.event.impl.EventPreAttack;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventSetSprinting;
import gg.vape.event.impl.SyntheticAttackRequestEvent;
import gg.vape.input.AttackKeyController;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.combat.AttackPacketTimingTracker;
import gg.vape.module.combat.SilentAura;
import gg.vape.module.combat.silentaura.SilentAuraAdaptiveRotationController;
import gg.vape.module.combat.silentaura.SilentAuraAdaptiveRotationEntry;
import gg.vape.module.combat.silentaura.SilentAuraRotationMode;
import gg.vape.module.control.SharedModuleControlClaimPrimary;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.RotationAngles;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ItemLimitData;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.Vec3d;
import gg.vape.utils.network.PacketDispatchGuard;
import gg.vape.value.BooleanValue;
import gg.vape.value.EntityTargetFilterValue;
import gg.vape.value.LimitValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.UseEntityPacketBridge;
import gg.vape.wrapper.impl.Vec3;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class SilentAuraTargetingModule
extends Mod {
    private final BooleanValue randomizeOffset;
    private final NumberValue chance;
    private final NumberValue angle;
    private boolean attackQueued;
    private static final float E1 = 6.0f;
    private boolean cancelSprint;
    private boolean attackKeyHeld;
    private int stateTicks;
    private final Queue<EventPacketSend> heldPackets;
    private final NumberValue flickDelay;
    private final PacketDispatchGuard dispatchGuard;
    private static final float U = 2.0f;
    private boolean blinking;
    private final EntityTargetFilterValue targetFilter = EntityTargetFilterValue.W(this);
    private SilentAuraRotationMode rotationMode;
    private static final int A = 5;
    private final RotationControlClaim rotationClaim;
    private final SharedModuleControlClaimPrimary primaryClaim;
    private int blinkTicks;
    private boolean forwardKeyForced;
    private AdaptiveRotationController rotationController;
    private final TimerUtil flickDelayTimer;
    private float flickAngle;
    private SilentAura silentAura;
    private final BooleanValue selectHits;
    private boolean sprintCancelPending;
    private EntityLivingBase currentTarget;
    private boolean backKeyForced;
    private boolean attacked;
    private static final float D = 1.875f;
    private final BooleanValue strafeInvert;
    private final NumberValue randomizeOffsetRange;
    private boolean claimHeld;
    private static final int Ec = 7;
    private static final float p = 48.0f;
    private final BooleanValue blink = BooleanValue.create(this, "Blink", false, "Chokes outgoing packets during the flick and flushes once the attack is sent");
    private final LimitValue allowedItems;
    private final BooleanValue limitToItems;

    private double[] q(EntityLivingBase entityLivingBase) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        Vec3d vec3d = RotationUtil.T(entityPlayerSP, entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl(), 0.0, 0.0, 0.0);
        return new double[]{vec3d.Y(), vec3d.t(), vec3d.o()};
    }

    private boolean N(EntityLivingBase entityLivingBase) {
        if (entityLivingBase.isNull()) {
            return false;
        }
        if (entityLivingBase.equals(Minecraft.thePlayer())) {
            return false;
        }
        if (entityLivingBase.w$src$F$15l9epb() <= 0.0f || entityLivingBase.M$src$Z$ff28xj()) {
            return false;
        }
        if (!this.targetFilter.c(entityLivingBase)) {
            return false;
        }
        double d = Minecraft.thePlayer().i(entityLivingBase.z(), entityLivingBase.N(), entityLivingBase.h());
        return d <= 5.0;
    }

    private boolean f(EntityLivingBase entityLivingBase) {
        if (this.rotationMode != SilentAuraRotationMode.IDLE || !this.R$src$Z$1unci2b()) {
            return false;
        }
        if (!this.N(entityLivingBase)) {
            return false;
        }
        if (this.selectHits.L().booleanValue() && !this.F(entityLivingBase)) {
            return false;
        }
        if (!this.n$src$Z$1v2qqof()) {
            return false;
        }
        if (!this.rotationClaim.U(this) && !this.rotationClaim.h(this, true)) {
            return false;
        }
        this.currentTarget = entityLivingBase;
        this.attackQueued = true;
        this.flickAngle = this.Q$src$F$1umsozq();
        this.rotationMode = SilentAuraRotationMode.FLICKING_AWAY;
        this.stateTicks = 0;
        this.cancelSprint = true;
        this.flickDelayTimer.reset();
        this.K$src$V$1ujhxtc();
        this.e();
        this.S$src$V$1unwak8();
        return true;
    }

    private boolean Z() {
        return this.currentTarget != null && this.N(this.currentTarget);
    }

    @EventHandler
    public void onPostAttack(EventPostAttack eventPostAttack) {
    }

    public static SilentAuraRotationMode p(SilentAuraTargetingModule silentAuraTargetingModule) {
        return silentAuraTargetingModule.rotationMode;
    }

    private void P$src$V$1um8ws5() {
        if (this.attackKeyHeld) {
            this.X$src$V$1uqn9j1();
        }
        if (this.blinking || !this.heldPackets.isEmpty()) {
            this.G();
        }
        this.attackQueued = false;
        this.attacked = false;
        this.attackKeyHeld = false;
        this.cancelSprint = false;
        this.sprintCancelPending = false;
        this.rotationMode = SilentAuraRotationMode.IDLE;
        this.stateTicks = 0;
        this.flickAngle = 0.0f;
        this.currentTarget = null;
        RotationManager.b.B(false);
        this.N();
        this.T();
        this.rotationClaim.X(this);
        if (this.rotationController != null && RotationManager.b.w() == this.rotationController) {
            this.rotationController.U(true);
            this.rotationController.s(true);
            RotationManager.b.v(this.rotationController);
        }
        this.rotationController = null;
    }

    @EventHandler(A=EventPriority.HIGH)
    public void onKeyPress(EventKeyPress eventKeyPress) {
        block5: {
            boolean bl;
            block4: {
                boolean bl2 = Packet.A();
                bl = eventKeyPress.isKeybinding(Minecraft.gameSettings().F());
                if (bl2) break block4;
                if (!bl) break block5;
                bl = eventKeyPress.isDown();
            }
            if (bl) {
                this.U(eventKeyPress);
            }
        }
    }

    @EventHandler
    public void onPreAttack(EventPreAttack eventPreAttack) {
    }

    private void b$src$V$1uw57gn() {
        if (!this.attackQueued || this.attacked) {
            return;
        }
        boolean bl = AttackKeyController.u(this);
        if (bl) {
            AttackKeyController.Q();
        }
        this.attacked = true;
        this.attackKeyHeld = bl;
        this.attackQueued = false;
    }

    private float V$src$F$1upjnyj() {
        return this.flickAngle;
    }

    private void k$src$V$1v13csw() {
        this.blinking = false;
        this.blinkTicks = 0;
        if (this.claimHeld) {
            this.primaryClaim.Q();
            this.claimHeld = false;
        }
    }

    private float l() {
        return this.c(this.R() + 2.0f, 1.0f);
    }

    private RotationAngles K() {
        if (this.currentTarget == null || this.rotationController == null) {
            return null;
        }
        double[] dArray = this.q(this.currentTarget);
        return this.rotationController.j(Vec3.create(dArray[0], dArray[1], dArray[2]));
    }

    private boolean F$src$Z$1ugqyxz() {
        if (this.rotationMode != SilentAuraRotationMode.IDLE) {
            return true;
        }
        if (!this.R$src$Z$1unci2b()) {
            return false;
        }
        RayTraceResult rayTraceResult = RotationManager.b.n();
        if (rayTraceResult.isNull() || rayTraceResult.getEntity().isNull() || !rayTraceResult.getEntity().isInstance(MappedClasses.zm)) {
            return false;
        }
        return this.f(new EntityLivingBase(rayTraceResult.getEntity()));
    }

    private void H() {
        KeyBinding keyBinding = Minecraft.gameSettings().r();
        if (!ClientSettings.B(keyBinding)) {
            keyBinding.setPressed(true);
            this.forwardKeyForced = true;
        } else {
            this.forwardKeyForced = false;
        }
    }

    private void m() {
        KeyBinding keyBinding = Minecraft.gameSettings().Y();
        if (!ClientSettings.B(keyBinding)) {
            keyBinding.setPressed(true);
            this.backKeyForced = true;
        } else {
            this.backKeyForced = false;
        }
    }

    public static float H(SilentAuraTargetingModule silentAuraTargetingModule) {
        return silentAuraTargetingModule.l();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private float r(float f) {
        return Math.abs(MathUtil.wrapAngleTo180(RotationManager.b.V() - f));
    }

    @EventHandler(A=EventPriority.HIGH)
    public void onSyntheticAttackRequest(SyntheticAttackRequestEvent syntheticAttackRequestEvent) {
        if (syntheticAttackRequestEvent.getSource() == this) {
            return;
        }
        if (this.F$src$Z$1ugqyxz()) {
            syntheticAttackRequestEvent.setCancelled(true);
        }
    }

    @EventHandler(A=EventPriority.HIGH)
    public void onMouseButton(EventMouseButton eventMouseButton) {
        block5: {
            boolean bl;
            block4: {
                boolean bl2 = Packet.h();
                bl = eventMouseButton.isKeybinding(Minecraft.gameSettings().F());
                if (!bl2) break block4;
                if (!bl) break block5;
                bl = eventMouseButton.isDown();
            }
            if (bl) {
                this.U(eventMouseButton);
            }
        }
    }

    private boolean Z(Packet packet) {
        if (!UseEntityPacketBridge.h(packet)) {
            return false;
        }
        return new UseEntityPacketBridge(packet).S();
    }

    private boolean l$src$Z$1v1n5hp() {
        if (!this.limitToItems.L().booleanValue()) {
            return true;
        }
        ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
        return this.allowedItems.isValid(itemStack, false);
    }

    @Override
    public void onDisable() {
        this.P$src$V$1um8ws5();
    }

    private float c(float f, float f2) {
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        return f / (1.875f * f2);
    }

    @Override
    public String r() {
        String string = this.angle.c();
        if (this.rotationMode != SilentAuraRotationMode.IDLE) {
            string = "\u00a7c" + string;
        }
        return string + "deg";
    }

    private void N() {
        if (this.backKeyForced) {
            KeyBinding keyBinding = Minecraft.gameSettings().Y();
            keyBinding.setPressed(ClientSettings.B(keyBinding));
            this.backKeyForced = false;
        }
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (this.cancelSprint) {
            eventPrePlayerTick.getThePlayer().R(false);
            this.sprintCancelPending = true;
            this.cancelSprint = false;
        }
    }

    @EventHandler
    public void onSetSprinting(EventSetSprinting eventSetSprinting) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            this.sprintCancelPending = false;
            return;
        }
        if (this.sprintCancelPending && eventSetSprinting.isNewStateSprinting() && eventSetSprinting.getEntity().isNotNull() && eventSetSprinting.getEntity().S() == entityPlayerSP.S()) {
            eventSetSprinting.setCancelled(true);
            this.sprintCancelPending = false;
        }
    }

    private EntityLivingBase C$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1gt079j() {
        if (this.silentAura == null) {
            this.silentAura = Vape.INSTANCE.getModManager().getMod(SilentAura.class);
        }
        if (this.silentAura == null || !this.silentAura.r$src$Z$14eylz9() || !this.silentAura.P()) {
            return new EntityLivingBase(null);
        }
        EntityLivingBase entityLivingBase = this.silentAura.j$src$Lgg_vape_wrapper_impl_EntityLivingBase_$si0dgx();
        if (entityLivingBase.isNull()) {
            return entityLivingBase;
        }
        RayTraceResult rayTraceResult = RotationManager.b.n();
        if (rayTraceResult.isNotNull() && rayTraceResult.getEntity().isNotNull() && entityLivingBase.equals(rayTraceResult.getEntity())) {
            return entityLivingBase;
        }
        return new EntityLivingBase(null);
    }

    private void e() {
        if (this.rotationMode != SilentAuraRotationMode.ATTACKING) {
            RotationManager.b.B(false);
            this.N();
            this.T();
            return;
        }
        this.m();
        this.H();
        RotationManager.b.B(true);
    }

    private boolean F(EntityLivingBase entityLivingBase) {
        return entityLivingBase.c$src$I$15a9iwo() <= AttackPacketTimingTracker.a.Z() + 1;
    }

    private float g(float f) {
        return Math.abs(MathUtil.wrapAngleTo180(RotationManager.b.x() - f));
    }

    private float C$src$F$1uf3koo() {
        return Math.max(48.0f, this.c(this.Y(), 1.0f));
    }

    private void K$src$V$1ujhxtc() {
        if (!this.blink.L().booleanValue() || this.primaryClaim.v$src$Z$1r7ksy2()) {
            return;
        }
        this.blinking = true;
        this.claimHeld = true;
        this.blinkTicks = 0;
        this.primaryClaim.c();
    }

    public static float f(SilentAuraTargetingModule silentAuraTargetingModule) {
        return silentAuraTargetingModule.C$src$F$1uf3koo();
    }

    private void S$src$V$1unwak8() {
        if (!this.Z()) {
            this.P$src$V$1um8ws5();
            return;
        }
        if (this.rotationController == null) {
            this.rotationController = new SilentAuraAdaptiveRotationController(this);
            this.rotationController.b(false);
            this.rotationController.w(true);
            this.rotationController.k(true);
            this.rotationController.t(0.0f);
            this.rotationController.U(false);
            this.rotationController.s(false);
        }
        double[] dArray = this.q(this.currentTarget);
        this.rotationController.z(dArray[0], dArray[1], dArray[2]);
        this.rotationController.x(this.rotationMode == SilentAuraRotationMode.FLICKING_AWAY ? this.V$src$F$1upjnyj() : 0.0f);
        this.rotationController.b(false);
        if (RotationManager.b.w() == null || RotationManager.b.w() != this.rotationController) {
            RotationManager.b.S(this.rotationController);
        }
    }

    private float R() {
        RotationAngles rotationAngles = this.K();
        if (rotationAngles == null) {
            return 0.0f;
        }
        float f = this.r(rotationAngles.z());
        float f2 = this.g(rotationAngles.N());
        return Math.max(f, f2);
    }

    private void G() {
        if (this.heldPackets.isEmpty()) {
            this.k$src$V$1v13csw();
            return;
        }
        this.heldPackets.forEach(this.dispatchGuard::o);
        this.heldPackets.clear();
        this.k$src$V$1v13csw();
    }

    private void T() {
        if (this.forwardKeyForced) {
            KeyBinding keyBinding = Minecraft.gameSettings().r();
            keyBinding.setPressed(ClientSettings.B(keyBinding));
            this.forwardKeyForced = false;
        }
    }

    @EventHandler
    public void onPacketSend(EventPacketSend eventPacketSend) {
        if (eventPacketSend.isCanceled() || eventPacketSend.wasModified()) {
            return;
        }
        Packet packet = eventPacketSend.getPacket();
        if (packet.isNull() || this.dispatchGuard.R(packet)) {
            return;
        }
        if (!this.blinking) {
            return;
        }
        this.heldPackets.add(eventPacketSend);
        eventPacketSend.setCancelled(true);
        if (this.Z(packet)) {
            this.G();
        }
    }

    public SilentAuraTargetingModule() {
        super("HitFlick", -3580417, Category.g, "Flicks off and on target to displace knockback angle");
        this.angle = NumberValue.create(this, "Angle", "#", "deg", 0.0, 90.0, 360.0, 1.0, "0 = none, 90 = right, 180 = pull toward, 270 = left");
        this.chance = NumberValue.create(this, "Chance", "#", "%", 0.0, 100.0, 100.0, 1.0, "Chance of starting a hit flick for a given attack");
        this.flickDelay = NumberValue.create(this, "Flick delay", "#", "ms", 0.0, 250.0, 2000.0, 25.0, "Minimum delay between hit flick attempts");
        this.randomizeOffset = BooleanValue.create(this, "Randomize offset", false, "Randomizes the configured angle by a per-flick range");
        this.randomizeOffsetRange = NumberValue.create(this, "Randomize offset", "#", "deg", 0.0, 0.0, 180.0, 1.0, "Applies a random offset range around Angle\nExample: 10 means Angle +/- 5 degrees per flick");
        this.strafeInvert = BooleanValue.create(this, "Strafe invert", false, "Flips the flick side when you strafe toward the current push direction");
        this.selectHits = BooleanValue.create(this, "Select hits", true, "Only start a hit flick when the target is vulnerable");
        this.limitToItems = BooleanValue.create(this, "Limit to items", false, "HitFlick functions only while holding selected items");
        this.allowedItems = LimitValue.n(this, "hitflick-alloweditems", "Allowed Items", LimitValue.r, Arrays.asList(new ItemLimitData("swords")));
        this.rotationClaim = SharedModuleControlClaims.I;
        this.primaryClaim = SharedModuleControlClaims.L;
        this.dispatchGuard = PacketDispatchGuard.b;
        this.heldPackets = new LinkedList<EventPacketSend>();
        this.flickDelayTimer = new TimerUtil();
        this.rotationMode = SilentAuraRotationMode.IDLE;
        this.randomizeOffset.K(this.randomizeOffsetRange);
        this.limitToItems.K(this.allowedItems);
        this.limitToItems.l(this.allowedItems);
        this.addValue(this.targetFilter, this.angle, this.chance, this.flickDelay, this.randomizeOffset, this.randomizeOffsetRange, this.strafeInvert, this.selectHits, this.blink, this.limitToItems, this.allowedItems);
        this.chance.C(0);
        this.flickDelayTimer.x(-10000L);
        this.rotationClaim.l(this, 6);
    }

    private void U(EventKeyInputBase eventKeyInputBase) {
        if (this.rotationMode != SilentAuraRotationMode.IDLE) {
            eventKeyInputBase.setCancelled(true);
            return;
        }
        if (!this.R$src$Z$1unci2b()) {
            return;
        }
        RayTraceResult rayTraceResult = RotationManager.b.n();
        if (rayTraceResult.isNull() || rayTraceResult.getEntity().isNull() || !rayTraceResult.getEntity().isInstance(MappedClasses.zm)) {
            return;
        }
        if (this.f(new EntityLivingBase(rayTraceResult.getEntity()))) {
            eventKeyInputBase.setCancelled(true);
        }
    }

    private boolean n$src$Z$1v2qqof() {
        return (Double)this.chance.K() >= Math.random() * 100.0;
    }

    private boolean g$src$Z$1uyw6iw() {
        float f;
        RotationAngles rotationAngles = this.K();
        if (rotationAngles == null) {
            return false;
        }
        float f2 = rotationAngles.z() + this.V$src$F$1upjnyj();
        float f3 = this.r(f2);
        return Math.max(f3, f = this.g(rotationAngles.N())) <= 6.0f;
    }

    private float L$src$F$1uk1q0x() {
        if (!this.randomizeOffset.L().booleanValue()) {
            return this.P();
        }
        float f = ((Double)this.randomizeOffsetRange.K()).floatValue();
        if (f <= 0.0f) {
            return this.P();
        }
        float f2 = (ThreadLocalRandom.current().nextFloat() - 0.5f) * f;
        return this.J(((Double)this.angle.K()).floatValue() + f2);
    }

    private float J(float f) {
        float f2 = f % 360.0f;
        if (f2 < 0.0f) {
            f2 += 360.0f;
        }
        return f2;
    }

    private void X$src$V$1uqn9j1() {
        AttackKeyController.Q();
        this.attackKeyHeld = false;
    }

    private float Q$src$F$1umsozq() {
        boolean bl;
        float f = this.L$src$F$1uk1q0x();
        if (!this.strafeInvert.L().booleanValue()) {
            return f;
        }
        boolean bl2 = ClientSettings.B(Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg());
        if (bl2 == (bl = ClientSettings.B(Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3()))) {
            return f;
        }
        float f2 = MathUtil.wrapAngleTo180(f);
        if (Math.abs(f2) <= 0.001f || Math.abs(Math.abs(f2) - 180.0f) <= 0.001f) {
            return f;
        }
        if (bl2 && f2 < 0.0f || bl && f2 > 0.0f) {
            return this.J(-f2);
        }
        return f;
    }

    private float Y() {
        return Math.abs(MathUtil.wrapAngleTo180(this.V$src$F$1upjnyj()));
    }

    private boolean R$src$Z$1unci2b() {
        if (!this.o$src$Z$1v3aj9s()) {
            return false;
        }
        if (Minecraft.theWorld().isNull() || Minecraft.thePlayer().isNull()) {
            return false;
        }
        if (Minecraft.currentScreen().isNotNull()) {
            return false;
        }
        if (!this.l$src$Z$1v1n5hp()) {
            return false;
        }
        return this.flickDelayTimer.hasTimeElapsed(((Double)this.flickDelay.K()).longValue());
    }

    private float P() {
        return this.J(((Double)this.angle.K()).floatValue());
    }

    private boolean o$src$Z$1v3aj9s() {
        return Math.abs(MathUtil.wrapAngleTo180(this.P())) > 0.001f || this.randomizeOffset.L() != false && ((Double)this.randomizeOffsetRange.K()).floatValue() > 0.001f;
    }

    @EventHandler(A=EventPriority.LOWEREST)
    public void onTick(EventPreTick eventPreTick) {
        if (Minecraft.currentScreen().isNotNull() || eventPreTick.getThePlayer().isNull()) {
            this.P$src$V$1um8ws5();
            return;
        }
        if (this.rotationMode == SilentAuraRotationMode.IDLE) {
            return;
        }
        if (!this.Z()) {
            this.P$src$V$1um8ws5();
            return;
        }
        if (!this.rotationClaim.U(this) && !this.rotationClaim.h(this, true)) {
            this.P$src$V$1um8ws5();
            return;
        }
        if (this.blinking && ++this.blinkTicks >= 7) {
            this.G();
        }
        ++this.stateTicks;
        switch (SilentAuraAdaptiveRotationEntry.O[this.rotationMode.ordinal()]) {
            case 1: {
                if (this.stateTicks < 5 && !this.g$src$Z$1uyw6iw()) break;
                this.rotationMode = SilentAuraRotationMode.ATTACKING;
                this.stateTicks = 0;
                break;
            }
            case 2: {
                if (!this.attacked) {
                    this.b$src$V$1uw57gn();
                }
                if (this.stateTicks < 2) break;
                this.P$src$V$1um8ws5();
            }
        }
        this.e();
        this.S$src$V$1unwak8();
    }

    @EventHandler(A=EventPriority.HIGH)
    public void onClickMouse(EventClickMouse eventClickMouse) {
        if (this.rotationMode != SilentAuraRotationMode.IDLE) {
            return;
        }
        EntityLivingBase entityLivingBase = this.C$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1gt079j();
        if (entityLivingBase.isNotNull() && this.f(entityLivingBase)) {
            eventClickMouse.setCancelled(true);
        }
    }
}

