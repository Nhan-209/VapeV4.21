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
    private final ModeOption S;
    private final SilentAuraAimJitter s;
    private long V = 0L;
    private final SilentAuraAimJitter Z1;
    public final ModeOption U;
    public final BooleanValue ZZ;
    private boolean p = false;
    public final RandomClickDelayValue Ze;
    private float c = 1.0f;
    public final ModeOption Z_;
    public final NumberValue P;
    private AdaptiveRotationController Z8;
    public final EntityTargetFilterValue ZU = EntityTargetFilterValue.W(this);
    public final ModeOption A;
    private final BooleanValue o;
    public final BooleanValue j;
    public final ModeOption ZE;
    private final RotationControlClaim ZO;
    private EntityLivingBase H = null;
    private static SilentAuraClicker Zv;
    private final RandomValue a;
    private boolean ZK;
    public final ModeOption O;
    public final BooleanValue Z5;
    public final LimitValue ZP;
    private float Za = 0.0f;
    private float I = 0.0f;
    private final BooleanValue Zn;
    public final ModeOption L;
    private float Y = 1.0f;
    private final ColorValue Z;
    public final NumberValue Zt = NumberValue.E(this, "Aim speed", "#.#", "", 1.0, 7.0, 10.0, "How fast aiming will be done silently");
    public final ModeOption r;
    private boolean Zh = false;
    private final BooleanValue Zp;
    public final BooleanValue Z7;
    private final SilentAuraAimJitter Zy;
    private boolean v;
    private boolean Zr = false;
    public final ModeOption K;
    public final NumberValue Zi = NumberValue.E(this, "Extra swing distance", "#.#", "", 0.0, 1.0, 3.0, "Extra distance past attack range at which aura will begin to engage, before attacking");
    private final BooleanValue Z2;
    private static Freecam D;
    private final LimitValue F;
    private final ColorValue t;
    private final Random b;
    private final TimerUtil k;
    public ModeValue C;
    private float Zs = 1.0f;
    private float Zx = 1.0f;
    private static final long ib;
    public ModeValue J;
    public ModeValue ZA;
    private boolean Zf = false;

    public EntityLivingBase j$src$Lgg_vape_wrapper_impl_EntityLivingBase_$si0dgx() {
        return this.H;
    }

    public static EntityLivingBase B(SilentAura silentAura) {
        return silentAura.H;
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (this.ZZ.L().booleanValue() && this.H != null && Minecraft.currentScreen().isNull()) {
            float f;
            float f2 = f = this.H.isInstance(MappedClasses.Yl) || this.H.isInstance(MappedClasses.lG) ? 0.7f : this.H.f$src$F$fst3ac();
            if (this.O.o()) {
                GuiRenderPrimitives.R(this.H.c(), this.H.A(), this.H.Z(), 50.0f, f, this.H.Y(), this.l() ? this.Z.q$src$Lgg_vape_utils_MutableColor_$1dowyd3() : this.t.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            } else {
                RenderUtil.k(this.H, 1.0, null, this.l() ? this.Z.q$src$Lgg_vape_utils_MutableColor_$1dowyd3() : this.t.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), eventRender3D.getTicks());
            }
        }
    }

    private boolean Y(EntityLivingBase entityLivingBase) {
        if (this.j.L().booleanValue()) {
            ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
            if (!this.ZP.isValid(itemStack, false)) {
                return false;
            }
            return this.ZU.c(entityLivingBase);
        }
        return this.ZU.c(entityLivingBase);
    }

    @Override
    public String r() {
        if (ForgeVersion.MC_1_12_2.d() && this.Zn.L().booleanValue()) {
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
        if (!this.y(entityLivingBase)) {
            return false;
        }
        if (RotationUtil.a(Minecraft.thePlayer(), entityLivingBase) > ((Double)this.P.K()).intValue() / 2) {
            return false;
        }
        if (Vape.INSTANCE.getFriendManager().isFriend(entityLivingBase)) {
            return false;
        }
        if (entityLivingBase.equals(Minecraft.thePlayer().S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12())) {
            return false;
        }
        return this.Y(entityLivingBase);
    }

    private boolean y(EntityLivingBase entityLivingBase) {
        double[] dArray = this.m(entityLivingBase);
        double d = dArray[0];
        double d2 = dArray[1];
        double d3 = dArray[2];
        double d4 = Minecraft.thePlayer().i(d, d2, d3);
        return d4 <= this.b$src$D$1dhke8s();
    }

    private void U$src$V$1daf2yl() {
        this.Y = 0.85f + this.b.nextFloat() * 0.3f;
        this.Zx = 0.85f + this.b.nextFloat() * 0.3f;
        this.Zs = 0.8f + this.b.nextFloat() * 0.4f;
        this.c = 0.85f + this.b.nextFloat() * 0.3f;
    }

    public boolean U() {
        if (ForgeVersion.MC_1_12_2.d() && this.Zn.L().booleanValue()) {
            float f = Minecraft.thePlayer().getCooledAttackStrength(0.0f);
            return f == 1.0f;
        }
        return this.Ze.R();
    }

    private boolean w$src$Z$1dt438b() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return false;
        }
        if (this.Z2.L().booleanValue() && this.k.hasTimeElapsed(this.V)) {
            if (this.Zp.L().booleanValue() && !this.F.A(entityPlayerSP.getHeldItemHand())) {
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
            this.V = (long)this.a.B();
            this.k.reset();
        }
        return false;
    }

    public SilentAura() {
        super("SilentAura", (int)ib, Category.g, "Simulates feel of Killaura\nAttacks and aims safely using built in AutoClicker to click, and Silent Aim system to aim");
        this.Z5 = BooleanValue.create(this, "Require mouse down", false);
        this.Z7 = BooleanValue.create(this, "Disable on death", false);
        this.ZZ = BooleanValue.create(this, "Show target", false);
        this.o = BooleanValue.create(this, "Switch", false, "Attacks other targets while current target is in hit cooldown");
        this.j = BooleanValue.create(this, "Limit to items", false, "Aura functions only while holding selected items");
        this.Z2 = BooleanValue.create(this, "Break blocks", false, "Prevents from aiming while attempting to break blocks");
        this.a = RandomValue.G(this, "Break blocks delay", "#", "", 0.0, 0.0, 10.0, 2000.0, 1.0, "Delay in milliseconds before breaking blocks");
        this.Zp = BooleanValue.create(this, "Break blocks whitelist", false);
        this.F = LimitValue.n(this, "SilentBlockBreakingItems", "Block breaking items", LimitValue.r, Arrays.asList(new ItemLimitData("pickaxes"), new ItemLimitData("shovels")));
        this.Ze = RandomClickDelayValue.M(this, "Attacks per Second", "#.#", "", 1.0, 6.0, 13.0, 20.0);
        this.P = NumberValue.create(this, "Max angle", "#", "", 1.0, 120.0, 360.0, 5.0, "Angle at which targets will be acquired and aimed at\n(From your cursor)");
        this.t = ColorValue.b(this, "Target color", new Color(255, 200, 112), 50);
        this.Z = ColorValue.L(this, "Attack color", new Color(255, 0, 0, 100));
        this.S = new ModeOption("Distance");
        this.ZE = new ModeOption("Yaw");
        this.U = new ModeOption("Armor");
        this.Z_ = new ModeOption("Threat");
        this.r = new ModeOption("Health");
        this.ZA = ModeValue.create((Object)this, "Target mode", "How Aura should prioritize targets\nArmor/Threat will default to Distance for non player targets", (ModeSelection)this.S, this.S, this.ZE, this.U, this.Z_, this.r);
        this.ZP = LimitValue.n(this, "silentaura-alloweditems", "Allowed Items", LimitValue.r, Collections.emptyList());
        this.L = new ModeOption("Center");
        this.A = new ModeOption("Closest");
        this.J = ModeValue.create((Object)this, "Target area", "Where Aura will aim towards\nCenter: Center of entity\nClosest: Closest position on entity hitbox", (ModeSelection)this.L, this.L, this.A);
        this.O = new ModeOption("Ring");
        this.K = new ModeOption("Box");
        this.C = ModeValue.create((Object)this, "Render type", this.O, this.O, this.K);
        this.Zn = BooleanValue.create(this, "Perfect swing", false, "Only attacks when there is no attack cooldown\nAdditionally, only swings when hovering(trigger)");
        this.k = new TimerUtil();
        this.ZO = SharedModuleControlClaims.I;
        this.b = new Random();
        this.Z1 = new SilentAuraAimJitter(-0.3, 0.25);
        this.s = new SilentAuraAimJitter(-0.15, 0.15);
        this.Zy = new SilentAuraAimJitter(-0.15, 0.15);
        this.Zn.U(false).z(this.Ze);
        this.addValue(this.ZU, this.Zt, this.Ze, this.Zi, this.P, this.ZA, this.J);
        this.ZZ.K(this.t, this.Z, this.C);
        this.Z2.K(this.a, this.Zp);
        this.Zp.K(this.F);
        this.U(this.Zn, ForgeVersion.MC_1_8_9.N());
        this.U(this.o, ForgeVersion.MC_1_8_9.H());
        this.addValue(new Value[]{this.Z7, this.Z2, this.a, this.Zp, this.F, this.Z5, this.ZZ, this.t, this.Z, this.C, this.j.K(this.ZP), this.ZP});
        this.j.l(this.ZP);
        this.ZO.l(this, 5);
        this.Ze.V(0);
    }

    static {
        ib = 267655872188715318L;
    }

    public boolean H() {
        if (Minecraft.theWorld().isNull()) {
            return false;
        }
        if (!this.U()) {
            return false;
        }
        if (!this.ZK) {
            return false;
        }
        if (Minecraft.currentScreen().isNotNull()) {
            return false;
        }
        return !this.v;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean P() {
        if (ForgeVersion.MC_1_12_2.d() && this.Zn.L().booleanValue()) {
            return false;
        }
        return this.H();
    }

    public static RotationControlClaim A(SilentAura silentAura) {
        return silentAura.ZO;
    }

    private void K() {
        if (this.H != null && this.N$src$Z$1d6kiwi()) {
            this.Zh = this.l();
            this.Z8.T(this.Z.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            this.Z8.z(this.t.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            this.Z8.L(this.Zh);
        }
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void onTick(EventPreTick eventPreTick) {
        this.v = this.w$src$Z$1dt438b();
        if (this.p) {
            this.p = false;
            AttackKeyController.Q();
        }
        if (Minecraft.thePlayer().isNull()) {
            this.N();
            return;
        }
        if (this.Zr || !this.r$src$Z$14eylz9() || this.v) {
            this.N();
            return;
        }
        if (Zv == null) {
            Zv = Vape.INSTANCE.getModManager().getMod(SilentAuraClicker.class);
        }
        if (!Zv.r$src$Z$14eylz9()) {
            Zv.Y(true);
        }
        this.K();
        if (this.Z7.L().booleanValue()) {
            if (Minecraft.thePlayer().M$src$Z$ff28xj() || Minecraft.thePlayer().w$src$F$15l9epb() <= 0.0f) {
                this.F();
                return;
            }
            if (ForgeVersion.MC_1_16_5.d()) {
                GuiScreen guiScreen = Minecraft.currentScreen();
                if (guiScreen.isNotNull()) {
                    if (!this.Zf && guiScreen.isInstance(MappedClasses.D2)) {
                        this.Zf = true;
                        this.F();
                        return;
                    }
                    this.Zf = false;
                }
            } else {
                TitledScreen titledScreen = Minecraft.k();
                if (titledScreen.isNotNull()) {
                    String string = titledScreen.E();
                    if (!this.Zf && string != null && (string.toLowerCase().contains("died") || string.toLowerCase().contains("dead"))) {
                        this.Zf = true;
                        this.F();
                        return;
                    }
                    if (string == null || string.equals("")) {
                        this.Zf = false;
                    }
                }
            }
        }
        if (!InputEventDispatcher.getInstance().getFocusState().isFocused() || this.Z5.L().booleanValue() && !ClientSettings.M()) {
            this.N();
            return;
        }
        this.x();
        this.L$src$V$1d5gxmc();
        if (ForgeVersion.MC_1_12_2.d() && this.Zn.L().booleanValue() && this.H()) {
            AttackKeyController.Q();
            this.p = AttackKeyController.u(this);
        }
    }

    private boolean N$src$Z$1d6kiwi() {
        MouseRotationController mouseRotationController = RotationManager.b.w();
        return mouseRotationController != null && mouseRotationController.equals(this.Z8);
    }

    private void x() {
        Wrapper wrapper;
        ArrayList<Entity> arrayList = new ArrayList<Entity>();
        if (this.z()) {
            return;
        }
        ArrayList arrayList2 = new ArrayList(Minecraft.theWorld().z());
        for (Object e : arrayList2) {
            EntityLivingBase entityLivingBase;
            wrapper = new Entity(e);
            if (ClientSettings.H && wrapper.isInstance(MappedClasses.FT) || !wrapper.isInstance(MappedClasses.zm) || !this.k(entityLivingBase = new EntityLivingBase(e))) continue;
            arrayList.add(entityLivingBase);
        }
        if (this.ZA.K() == this.ZE) {
            arrayList.sort(new EntityAngleComparator());
        } else if (this.ZA.K() == this.S) {
            arrayList.sort(new EntityDistanceComparator());
        } else if (this.ZA.K() == this.Z_) {
            arrayList.sort(new EntityArmorValueComparator());
        } else if (this.ZA.K() == this.U) {
            arrayList.sort(new EntityEquipmentValueComparator());
        } else if (this.ZA.K() == this.r) {
            arrayList.sort(new EntityHealthComparator());
        }
        if (this.o.L().booleanValue()) {
            arrayList.sort(new SilentAuraEntityIdComparator(this));
        }
        if (!arrayList.isEmpty()) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)arrayList.get(0);
            boolean bl = false;
            if (this.H != null && !this.H.equals(entityLivingBase)) {
                bl = true;
            }
            if (this.H == null || bl) {
                AttackKeyController.Q();
                this.U$src$V$1daf2yl();
            }
            this.H = entityLivingBase;
            if (!this.ZO.U(this)) {
                this.ZO.h(this, true);
            }
            this.ZK = false;
            if (this.Z8 != null) {
                if (ForgeVersion.MC_1_12_2.d() && this.Zn.L().booleanValue()) {
                    wrapper = RotationManager.b.n();
                    if (wrapper.isNotNull() && ((RayTraceResult)wrapper).getEntity().isNotNull() && ((RayTraceResult)wrapper).getEntity().equals(this.H)) {
                        this.ZK = true;
                    }
                } else {
                    double d = RotationUtil.L(this.H);
                    if (d < 3.0 && this.y(this.H)) {
                        this.ZK = true;
                    }
                }
            }
        } else {
            this.N();
        }
    }

    private double[] m(EntityLivingBase entityLivingBase) {
        if (this.J.K() == this.A) {
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

    private void L$src$V$1d5gxmc() {
        if (Minecraft.theWorld().isNull()) {
            this.N();
            return;
        }
        boolean bl = this.H != null && this.k(this.H);
        boolean bl2 = this.ZO.U(this);
        if (bl && bl2) {
            this.s();
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            double[] dArray = this.m(this.H);
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
            double d11 = d + this.s.b() * (1.0 + d10);
            double d12 = d3 + this.Zy.b() * (1.0 + d10);
            double d13 = d2;
            double d14 = this.H.Y();
            double d15 = entityPlayerSP.N() + 1.62;
            double d16 = d15 < d13 ? d13 + this.Z1.b() * 0.5 : Math.min(d15, d13 + d14) - 0.275 + this.Z1.b();
            if (this.Z8 == null) {
                this.Z8 = new SilentAuraRotationController(this);
                this.Z8.b(false);
                this.Z8.w(true);
                this.Z8.k(true);
                this.Z8.t(0.0f);
                this.Z8.U(false);
                this.Z8.s(false);
                RotationManager.b.S(this.Z8);
            } else {
                this.Z8.b(false);
                this.Z8.U(false);
                this.Z8.s(false);
                RotationAngles rotationAngles = this.Z8.j(Vec3.create(d11, d16, d12));
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
                float f9 = 0.45f * this.Y;
                float f10 = 0.91f * this.Zx;
                float f11 = (this.Zh ? 0.05f : 0.1f) * this.Zs;
                float f12 = 0.33f * this.c;
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
                this.I += f13 * f8;
                this.Za += f14 * f8;
                float f15 = f9 * f13 + f10 * this.I + 0.0f;
                float f16 = f11 * f14 + f12 * this.Za + 0.0f;
                if (Math.abs(f4) > 120.0f) {
                    this.Za = 0.0f;
                    f16 = 0.0f;
                }
                if (Minecraft.currentScreen().isNotNull()) {
                    this.Za = 0.0f;
                    this.I = 0.0f;
                }
                this.Z8.g(f2 + f4 + f16 / 3.0f, f3 + f15);
            }
            if (RotationManager.b.w() == null || !this.N$src$Z$1d6kiwi() && RotationManager.b.u()) {
                RotationManager.b.S(this.Z8);
            }
        } else {
            this.N();
        }
    }

    @Override
    public void s(boolean bl, boolean bl2) {
        this.I = 0.0f;
        this.Za = 0.0f;
        if (!bl && this.N$src$Z$1d6kiwi()) {
            this.Zr = !this.Zr;
        } else {
            this.Zr = false;
            super.s(bl, bl2);
        }
    }

    private boolean l() {
        RayTraceResult rayTraceResult;
        boolean bl = false;
        if (this.H != null && this.N$src$Z$1d6kiwi() && this.y(this.H) && (rayTraceResult = RotationManager.b.n()) != null && rayTraceResult.isNotNull() && this.H.equals(rayTraceResult.getEntity())) {
            bl = true;
        }
        return bl;
    }

    public void N() {
        this.I = 0.0f;
        this.Za = 0.0f;
        this.H = null;
        this.ZK = false;
        if (this.Z8 != null && this.N$src$Z$1d6kiwi()) {
            this.Z8.U(true);
            this.Z8.s(true);
            RotationManager.b.v(this.Z8);
        }
        if (RotationManager.b.w() == null || RotationManager.b.w() != this.Z8 || this.Z8 != null && this.Z8.O$src$Z$1lvi05g() && this.Z8.V$src$Z$lb4tvc()) {
            this.Z8 = null;
            this.ZO.X(this);
            if (this.Zr) {
                this.Zr = false;
                super.s(false, true);
            }
        }
    }

    private double b$src$D$1dhke8s() {
        return 3.0 + (Double)this.Zi.K();
    }

    private void s() {
        this.Z1.v();
        this.s.v();
        this.Zy.v();
    }

    private boolean z() {
        if (D == null) {
            D = Vape.INSTANCE.getModManager().getMod(Freecam.class);
        }
        return this.Zr || D != null && D.r$src$Z$14eylz9() || this.v || this.ZO.e(this) && !this.ZO.h(this, true);
    }

    @Override
    public void onDisable() {
        if (this.Z8 != null) {
            this.Z8 = null;
        }
        if (this.p) {
            AttackKeyController.Q();
            this.p = false;
        }
        this.ZO.X(this);
    }
}

