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
    private final BooleanValue s;
    private final NumberValue k;
    private final NumberValue Eb;
    private boolean V;
    private static final float E1 = 6.0f;
    private boolean Es;
    private boolean b;
    private int E8;
    private final Queue<EventPacketSend> EK;
    private final NumberValue o;
    private final PacketDispatchGuard F;
    private static final float U = 2.0f;
    private boolean L;
    private final EntityTargetFilterValue t = EntityTargetFilterValue.W(this);
    private SilentAuraRotationMode C;
    private static final int A = 5;
    private final RotationControlClaim J;
    private final SharedModuleControlClaimPrimary E4;
    private int Ex;
    private boolean I;
    private AdaptiveRotationController K;
    private final TimerUtil EW;
    private float S;
    private SilentAura Ee;
    private final BooleanValue Et;
    private boolean Y;
    private EntityLivingBase P;
    private boolean v;
    private boolean c;
    private static final float D = 1.875f;
    private final BooleanValue O;
    private final NumberValue a;
    private boolean H;
    private static final int Ec = 7;
    private static final float p = 48.0f;
    private final BooleanValue j = BooleanValue.create(this, "Blink", false, "Chokes outgoing packets during the flick and flushes once the attack is sent");
    private final LimitValue r;
    private final BooleanValue Z;

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
        if (!this.t.c(entityLivingBase)) {
            return false;
        }
        double d = Minecraft.thePlayer().i(entityLivingBase.z(), entityLivingBase.N(), entityLivingBase.h());
        return d <= 5.0;
    }

    private boolean f(EntityLivingBase entityLivingBase) {
        if (this.C != SilentAuraRotationMode.IDLE || !this.R$src$Z$1unci2b()) {
            return false;
        }
        if (!this.N(entityLivingBase)) {
            return false;
        }
        if (this.Et.L().booleanValue() && !this.F(entityLivingBase)) {
            return false;
        }
        if (!this.n$src$Z$1v2qqof()) {
            return false;
        }
        if (!this.J.U(this) && !this.J.h(this, true)) {
            return false;
        }
        this.P = entityLivingBase;
        this.V = true;
        this.S = this.Q$src$F$1umsozq();
        this.C = SilentAuraRotationMode.FLICKING_AWAY;
        this.E8 = 0;
        this.Es = true;
        this.EW.reset();
        this.K$src$V$1ujhxtc();
        this.e();
        this.S$src$V$1unwak8();
        return true;
    }

    private boolean Z() {
        return this.P != null && this.N(this.P);
    }

    @EventHandler
    public void w(EventPostAttack eventPostAttack) {
    }

    public static SilentAuraRotationMode p(SilentAuraTargetingModule silentAuraTargetingModule) {
        return silentAuraTargetingModule.C;
    }

    private void P$src$V$1um8ws5() {
        if (this.b) {
            this.X$src$V$1uqn9j1();
        }
        if (this.L || !this.EK.isEmpty()) {
            this.G();
        }
        this.V = false;
        this.c = false;
        this.b = false;
        this.Es = false;
        this.Y = false;
        this.C = SilentAuraRotationMode.IDLE;
        this.E8 = 0;
        this.S = 0.0f;
        this.P = null;
        RotationManager.b.B(false);
        this.N();
        this.T();
        this.J.X(this);
        if (this.K != null && RotationManager.b.w() == this.K) {
            this.K.U(true);
            this.K.s(true);
            RotationManager.b.v(this.K);
        }
        this.K = null;
    }

    @EventHandler(A=EventPriority.HIGH)
    public void a(EventKeyPress eventKeyPress) {
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
    public void h(EventPreAttack eventPreAttack) {
    }

    private void b$src$V$1uw57gn() {
        if (!this.V || this.c) {
            return;
        }
        boolean bl = AttackKeyController.u(this);
        if (bl) {
            AttackKeyController.Q();
        }
        this.c = true;
        this.b = bl;
        this.V = false;
    }

    private float V$src$F$1upjnyj() {
        return this.S;
    }

    private void k$src$V$1v13csw() {
        this.L = false;
        this.Ex = 0;
        if (this.H) {
            this.E4.Q();
            this.H = false;
        }
    }

    private float l() {
        return this.c(this.R() + 2.0f, 1.0f);
    }

    private RotationAngles K() {
        if (this.P == null || this.K == null) {
            return null;
        }
        double[] dArray = this.q(this.P);
        return this.K.j(Vec3.create(dArray[0], dArray[1], dArray[2]));
    }

    private boolean F$src$Z$1ugqyxz() {
        if (this.C != SilentAuraRotationMode.IDLE) {
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
            this.I = true;
        } else {
            this.I = false;
        }
    }

    private void m() {
        KeyBinding keyBinding = Minecraft.gameSettings().Y();
        if (!ClientSettings.B(keyBinding)) {
            keyBinding.setPressed(true);
            this.v = true;
        } else {
            this.v = false;
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
    public void G(SyntheticAttackRequestEvent syntheticAttackRequestEvent) {
        if (syntheticAttackRequestEvent.getSource() == this) {
            return;
        }
        if (this.F$src$Z$1ugqyxz()) {
            syntheticAttackRequestEvent.setCancelled(true);
        }
    }

    @EventHandler(A=EventPriority.HIGH)
    public void G(EventMouseButton eventMouseButton) {
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
        if (!this.Z.L().booleanValue()) {
            return true;
        }
        ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
        return this.r.isValid(itemStack, false);
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
        String string = this.Eb.c();
        if (this.C != SilentAuraRotationMode.IDLE) {
            string = "\u00a7c" + string;
        }
        return string + "deg";
    }

    private void N() {
        if (this.v) {
            KeyBinding keyBinding = Minecraft.gameSettings().Y();
            keyBinding.setPressed(ClientSettings.B(keyBinding));
            this.v = false;
        }
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (this.Es) {
            eventPrePlayerTick.getThePlayer().R(false);
            this.Y = true;
            this.Es = false;
        }
    }

    @EventHandler
    public void onSetSprinting(EventSetSprinting eventSetSprinting) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            this.Y = false;
            return;
        }
        if (this.Y && eventSetSprinting.isNewStateSprinting() && eventSetSprinting.getEntity().isNotNull() && eventSetSprinting.getEntity().S() == entityPlayerSP.S()) {
            eventSetSprinting.setCancelled(true);
            this.Y = false;
        }
    }

    private EntityLivingBase C$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1gt079j() {
        if (this.Ee == null) {
            this.Ee = Vape.INSTANCE.getModManager().getMod(SilentAura.class);
        }
        if (this.Ee == null || !this.Ee.r$src$Z$14eylz9() || !this.Ee.P()) {
            return new EntityLivingBase(null);
        }
        EntityLivingBase entityLivingBase = this.Ee.j$src$Lgg_vape_wrapper_impl_EntityLivingBase_$si0dgx();
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
        if (this.C != SilentAuraRotationMode.ATTACKING) {
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
        if (!this.j.L().booleanValue() || this.E4.v$src$Z$1r7ksy2()) {
            return;
        }
        this.L = true;
        this.H = true;
        this.Ex = 0;
        this.E4.c();
    }

    public static float f(SilentAuraTargetingModule silentAuraTargetingModule) {
        return silentAuraTargetingModule.C$src$F$1uf3koo();
    }

    private void S$src$V$1unwak8() {
        if (!this.Z()) {
            this.P$src$V$1um8ws5();
            return;
        }
        if (this.K == null) {
            this.K = new SilentAuraAdaptiveRotationController(this);
            this.K.b(false);
            this.K.w(true);
            this.K.k(true);
            this.K.t(0.0f);
            this.K.U(false);
            this.K.s(false);
        }
        double[] dArray = this.q(this.P);
        this.K.z(dArray[0], dArray[1], dArray[2]);
        this.K.x(this.C == SilentAuraRotationMode.FLICKING_AWAY ? this.V$src$F$1upjnyj() : 0.0f);
        this.K.b(false);
        if (RotationManager.b.w() == null || RotationManager.b.w() != this.K) {
            RotationManager.b.S(this.K);
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
        if (this.EK.isEmpty()) {
            this.k$src$V$1v13csw();
            return;
        }
        this.EK.forEach(this.F::o);
        this.EK.clear();
        this.k$src$V$1v13csw();
    }

    private void T() {
        if (this.I) {
            KeyBinding keyBinding = Minecraft.gameSettings().r();
            keyBinding.setPressed(ClientSettings.B(keyBinding));
            this.I = false;
        }
    }

    @EventHandler
    public void onPacketSend(EventPacketSend eventPacketSend) {
        if (eventPacketSend.isCanceled() || eventPacketSend.wasModified()) {
            return;
        }
        Packet packet = eventPacketSend.getPacket();
        if (packet.isNull() || this.F.R(packet)) {
            return;
        }
        if (!this.L) {
            return;
        }
        this.EK.add(eventPacketSend);
        eventPacketSend.setCancelled(true);
        if (this.Z(packet)) {
            this.G();
        }
    }

    public SilentAuraTargetingModule() {
        super("HitFlick", -3580417, Category.g, "Flicks off and on target to displace knockback angle");
        this.Eb = NumberValue.create(this, "Angle", "#", "deg", 0.0, 90.0, 360.0, 1.0, "0 = none, 90 = right, 180 = pull toward, 270 = left");
        this.k = NumberValue.create(this, "Chance", "#", "%", 0.0, 100.0, 100.0, 1.0, "Chance of starting a hit flick for a given attack");
        this.o = NumberValue.create(this, "Flick delay", "#", "ms", 0.0, 250.0, 2000.0, 25.0, "Minimum delay between hit flick attempts");
        this.s = BooleanValue.create(this, "Randomize offset", false, "Randomizes the configured angle by a per-flick range");
        this.a = NumberValue.create(this, "Randomize offset", "#", "deg", 0.0, 0.0, 180.0, 1.0, "Applies a random offset range around Angle\nExample: 10 means Angle +/- 5 degrees per flick");
        this.O = BooleanValue.create(this, "Strafe invert", false, "Flips the flick side when you strafe toward the current push direction");
        this.Et = BooleanValue.create(this, "Select hits", true, "Only start a hit flick when the target is vulnerable");
        this.Z = BooleanValue.create(this, "Limit to items", false, "HitFlick functions only while holding selected items");
        this.r = LimitValue.n(this, "hitflick-alloweditems", "Allowed Items", LimitValue.r, Arrays.asList(new ItemLimitData("swords")));
        this.J = SharedModuleControlClaims.I;
        this.E4 = SharedModuleControlClaims.L;
        this.F = PacketDispatchGuard.b;
        this.EK = new LinkedList<EventPacketSend>();
        this.EW = new TimerUtil();
        this.C = SilentAuraRotationMode.IDLE;
        this.s.K(this.a);
        this.Z.K(this.r);
        this.Z.l(this.r);
        this.addValue(this.t, this.Eb, this.k, this.o, this.s, this.a, this.O, this.Et, this.j, this.Z, this.r);
        this.k.C(0);
        this.EW.x(-10000L);
        this.J.l(this, 6);
    }

    private void U(EventKeyInputBase eventKeyInputBase) {
        if (this.C != SilentAuraRotationMode.IDLE) {
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
        return (Double)this.k.K() >= Math.random() * 100.0;
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
        if (!this.s.L().booleanValue()) {
            return this.P();
        }
        float f = ((Double)this.a.K()).floatValue();
        if (f <= 0.0f) {
            return this.P();
        }
        float f2 = (ThreadLocalRandom.current().nextFloat() - 0.5f) * f;
        return this.J(((Double)this.Eb.K()).floatValue() + f2);
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
        this.b = false;
    }

    private float Q$src$F$1umsozq() {
        boolean bl;
        float f = this.L$src$F$1uk1q0x();
        if (!this.O.L().booleanValue()) {
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
        return this.EW.hasTimeElapsed(((Double)this.o.K()).longValue());
    }

    private float P() {
        return this.J(((Double)this.Eb.K()).floatValue());
    }

    private boolean o$src$Z$1v3aj9s() {
        return Math.abs(MathUtil.wrapAngleTo180(this.P())) > 0.001f || this.s.L() != false && ((Double)this.a.K()).floatValue() > 0.001f;
    }

    @EventHandler(A=EventPriority.LOWEREST)
    public void onTick(EventPreTick eventPreTick) {
        if (Minecraft.currentScreen().isNotNull() || eventPreTick.getThePlayer().isNull()) {
            this.P$src$V$1um8ws5();
            return;
        }
        if (this.C == SilentAuraRotationMode.IDLE) {
            return;
        }
        if (!this.Z()) {
            this.P$src$V$1um8ws5();
            return;
        }
        if (!this.J.U(this) && !this.J.h(this, true)) {
            this.P$src$V$1um8ws5();
            return;
        }
        if (this.L && ++this.Ex >= 7) {
            this.G();
        }
        ++this.E8;
        switch (SilentAuraAdaptiveRotationEntry.O[this.C.ordinal()]) {
            case 1: {
                if (this.E8 < 5 && !this.g$src$Z$1uyw6iw()) break;
                this.C = SilentAuraRotationMode.ATTACKING;
                this.E8 = 0;
                break;
            }
            case 2: {
                if (!this.c) {
                    this.b$src$V$1uw57gn();
                }
                if (this.E8 < 2) break;
                this.P$src$V$1um8ws5();
            }
        }
        this.e();
        this.S$src$V$1unwak8();
    }

    @EventHandler(A=EventPriority.HIGH)
    public void E(EventClickMouse eventClickMouse) {
        if (this.C != SilentAuraRotationMode.IDLE) {
            return;
        }
        EntityLivingBase entityLivingBase = this.C$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1gt079j();
        if (entityLivingBase.isNotNull() && this.f(entityLivingBase)) {
            eventClickMouse.setCancelled(true);
        }
    }
}

