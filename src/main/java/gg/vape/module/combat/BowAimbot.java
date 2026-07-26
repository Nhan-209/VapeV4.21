package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventEntityJoinWorld;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.InputEventDispatcher;
import gg.vape.input.KeyBindingInputState;
import gg.vape.input.MouseButtonInputLock;
import gg.vape.input.MovementInputLock;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.InvWalk;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.Vec3d;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityArrow;
import gg.vape.wrapper.impl.EntityArrowBridge;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BowAimbot
extends Mod {
    private int O;
    private EntityArrowBridge A;
    private final HashMap<Integer, Entity> p;
    private TimerUtil r;
    private NumberValue k = NumberValue.create((Object)this, "Angle limit", "#", "", 1.0, 45.0, 180.0, 5.0);
    private boolean V;
    private FixedRotationController o;
    public BooleanValue F;
    private final RotationControlClaim D;
    private final MovementInputLock C;
    public NumberValue c = NumberValue.create((Object)this, "Aim speed", "#.#", "", 1.0, 9.0, 10.0, 0.1);
    private BooleanValue J;
    private static final long v = -321105662139610036L;
    private final MouseButtonInputLock t;
    private boolean Z;
    public BooleanValue S;
    private boolean U;

    private void c() {
        if (this.o != null && RotationManager.b.w() == this.o) {
            this.o.Y(Math.min(((Double)this.c.K()).floatValue() * 0.6f, 6.0f));
            this.o.D(false);
            this.o.z(false);
            this.o.A(true);
            RotationManager.b.v(this.o);
        }
    }

    public BowAimbot() {
        super("AntiFireball", (int)v, Category.Y, "Aims and swings at a fireball to reflect it.\nBy default will only attack fireballs heading towards you.");
        this.F = BooleanValue.create(this, "Stop movement", false, "Forces you to stand when attacking fireball");
        this.S = BooleanValue.create(this, "Move on finish", false, "Will repress your movement keys after attacking");
        this.J = BooleanValue.create(this, "Silent aim", false, "Uses Silent Aim system");
        this.p = new HashMap();
        this.r = new TimerUtil();
        this.D = SharedModuleControlClaims.I;
        this.t = SharedModuleControlClaims.h;
        this.C = SharedModuleControlClaims.l;
        this.F.K(this.S);
        this.addValue(this.k, this.c, this.F, this.S, this.J);
        this.D.l(this, 7);
    }

    private boolean s(EntityPlayerSP entityPlayerSP, Entity entity) {
        boolean bl = true;
        if (entity != null && entity.isNotNull() && entity.isInstance(MappedClasses.Yl)) {
            EntityPlayer entityPlayer = new EntityPlayer(entity);
            if (Vape.INSTANCE.getClientSettings().e(entityPlayerSP, entityPlayer) || Vape.INSTANCE.getFriendManager().isFriend(entityPlayer)) {
                bl = false;
            }
        }
        return bl;
    }

    private static boolean lambda$checkOwnerMap$0(World world, Map.Entry entry) {
        int n = (Integer)entry.getKey();
        Entity entity = (Entity)entry.getValue();
        return entity != null && entity.M$src$Z$ff28xj() || world.V(n).isNull();
    }

    private void S$src$V$1lxzzlr() {
        if (this.F.L().booleanValue()) {
            this.C.K(this);
            GameSettings gameSettings = Minecraft.gameSettings();
            gameSettings.Y().setPressed(false);
            gameSettings.s().setPressed(false);
            gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg().setPressed(false);
            gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3().setPressed(false);
        }
    }

    private double r(EntityPlayerSP entityPlayerSP, WorldClient worldClient, EntityArrow entityArrow) {
        double d = 999.0;
        double d2 = entityArrow.z();
        double d3 = entityArrow.N();
        double d4 = entityArrow.h();
        double d5 = entityArrow.t();
        double d6 = entityArrow.q();
        double d7 = entityArrow.T();
        double d8 = entityArrow.L();
        double d9 = entityArrow.X$src$D$xt9pjp();
        double d10 = entityArrow.o();
        double d11 = 0.95f;
        for (int i = 0; i < 10; ++i) {
            Vec3 vec3;
            float f = entityArrow.Y();
            float f2 = entityArrow.f$src$F$fst3ac();
            Vec3 vec32 = Vec3.create(d2, d3, d4);
            RayTraceResult rayTraceResult = worldClient.K(vec32, vec3 = Vec3.create(d2 + d5, d3 + d6, d4 + d7), false, true, false, entityArrow);
            if (rayTraceResult.isBlockHit()) {
                vec3 = Vec3.create(rayTraceResult.getHitVec().getX(), rayTraceResult.getHitVec().getY(), rayTraceResult.getHitVec().getZ());
            }
            AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(d2 - (double)f, d3, d4 - (double)f, d2 + (double)f, d3 + (double)f2, d4 + (double)f);
            List list = worldClient.F(entityArrow, axisAlignedBB.addCoord(d5, d6, d7).expand(1.0, 1.0, 1.0));
            double d12 = 0.0;
            for (Object e : list) {
                double d13;
                Entity entity = new Entity(e);
                if (!entity.isInstance(MappedClasses.zm) || !entity.n$src$Z$fx7gig()) continue;
                axisAlignedBB = entity.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().expand(0.3f, 0.3f, 0.3f);
                RayTraceResult rayTraceResult2 = axisAlignedBB.calculateIntercept(vec32, vec3);
                if (ForgeVersion.MC_1_16_5.d()) {
                    if (!rayTraceResult2.isNotNull() || !rayTraceResult2.getTypeOfHit().equals(RayTraceResult_type.miss()) || !((d13 = vec32.distanceTo(rayTraceResult2.getHitVec())) < d12) && d12 != 0.0) continue;
                    d12 = d13;
                    rayTraceResult2.setEntity(entity);
                    rayTraceResult = rayTraceResult2;
                    continue;
                }
                if (!rayTraceResult2.isNotNull() || !((d13 = vec32.distanceTo(rayTraceResult2.getHitVec())) < d12) && d12 != 0.0) continue;
                d12 = d13;
                rayTraceResult2.setEntity(entity);
                rayTraceResult = rayTraceResult2;
            }
            if (rayTraceResult.isNotNull() && !rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.miss())) {
                if (rayTraceResult.isEntityHit() && rayTraceResult.getEntity().equals(entityPlayerSP)) {
                    return 0.0;
                }
                return entityPlayerSP.i(rayTraceResult.getHitVec().getX(), rayTraceResult.getHitVec().getY(), rayTraceResult.getHitVec().getZ());
            }
            d2 += d5;
            d3 += d6;
            d4 += d7;
            Vec3d vec3d = RotationUtil.T(entityPlayerSP, axisAlignedBB.expand(1.0, 1.0, 1.0), 0.0, 0.0, 0.0);
            double d14 = entityPlayerSP.i(vec3d.H, vec3d.B, vec3d.i);
            if (d14 <= 4.0 && d14 < d) {
                d = d14;
            }
            d5 += d8;
            d6 += d9;
            d7 += d10;
            d5 *= d11;
            d6 *= d11;
            d7 *= d11;
        }
        return d;
    }

    private boolean c$src$Z$1m6sp6z() {
        if (!InputEventDispatcher.getInstance().getFocusState().isFocused()) {
            return false;
        }
        if (this.e()) {
            return false;
        }
        if (this.D.e(this)) {
            return false;
        }
        return !this.U;
    }

    private void O$src$V$1lvst8b() {
        if (this.F.L().booleanValue()) {
            this.C.T(this);
            if (this.S.L().booleanValue()) {
                GameSettings gameSettings = Minecraft.gameSettings();
                KeyBinding[] keyBindingArray = new KeyBinding[]{gameSettings.Y(), gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3(), gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg(), gameSettings.s()};
                boolean bl = this.e();
                for (KeyBinding keyBinding : keyBindingArray) {
                    if (bl) {
                        keyBinding.setPressed(false);
                        continue;
                    }
                    keyBinding.Z();
                }
            }
        }
    }

    @EventHandler
    public void c(EventEntityJoinWorld eventEntityJoinWorld) {
        if (eventEntityJoinWorld.getEntity().isInstance(MappedClasses.uf)) {
            EntityArrowBridge entityArrowBridge = new EntityArrowBridge(eventEntityJoinWorld.getEntity());
            Entity entity = null;
            double d = Double.MAX_VALUE;
            AxisAlignedBB axisAlignedBB = entityArrowBridge.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().expand(2.0, 2.0, 2.0);
            List list = eventEntityJoinWorld.getWorld().F(entityArrowBridge, axisAlignedBB);
            if (!list.isEmpty()) {
                for (Object e : list) {
                    double d2;
                    Entity entity2 = new Entity(e);
                    if (!entity2.isInstance(MappedClasses.Yl) || !((d2 = (double)entity2.getDistanceToEntity(entityArrowBridge)) < d)) continue;
                    entity = entity2;
                    d = d2;
                }
            }
            if (this.s(Minecraft.thePlayer(), entity)) {
                this.p.put(entityArrowBridge.S(), entity);
            }
        }
    }

    private void Q$src$V$1lwwef1() {
        if (this.o != null && !this.o.v() && this.o.V$src$Z$lb4tvc()) {
            this.o = null;
        }
        if (this.o == null) {
            this.D.X(this);
        }
    }

    @Override
    public void s(boolean bl, boolean bl2) {
        if (!bl && this.o instanceof AdaptiveRotationController) {
            this.U = !this.U;
        } else {
            this.U = false;
            super.s(bl, bl2);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void f(World world) {
        this.p.entrySet().removeIf(arg_0 -> BowAimbot.lambda$checkOwnerMap$0(world, arg_0));
    }

    public boolean N(EntityArrowBridge entityArrowBridge, EntityPlayerSP entityPlayerSP) {
        boolean bl;
        double d = entityArrowBridge.z() - entityArrowBridge.f();
        double d2 = entityArrowBridge.N() - entityArrowBridge.H();
        double d3 = entityArrowBridge.h() - entityArrowBridge.R();
        Vec3d vec3d = this.u(entityArrowBridge);
        float f = entityPlayerSP.getDistanceToEntity(entityArrowBridge);
        double d4 = entityPlayerSP.i(vec3d.H, vec3d.B, vec3d.i);
        double d5 = entityPlayerSP.i(entityArrowBridge.z() + d, entityArrowBridge.N() + d2, entityArrowBridge.h() + d3);
        boolean bl2 = bl = d4 < (double)f && d5 < (double)f;
        if (bl) {
            float f2 = RotationUtil.a(entityPlayerSP, entityArrowBridge);
            if (entityArrowBridge.l() <= 4 && (double)f <= 2.5 && Math.abs(f2) > 90.0f) {
                bl = false;
            }
        }
        return bl;
    }

    public float[] b(double d, double d2, double d3) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        float f = RotationUtil.k(entityPlayerSP.z(), entityPlayerSP.h(), d, d3);
        float f2 = (float)RotationUtil.h(entityPlayerSP, d, d2, d3);
        return new float[]{f, f2};
    }

    private void A(EntityPlayerSP entityPlayerSP, WorldClient worldClient) {
        if (this.A == null || this.O > 0) {
            EntityArrowBridge targetArrow = null;
            double d = Double.MAX_VALUE;
            for (Map.Entry<Integer, Entity> entry : this.p.entrySet()) {
                EntityArrowBridge entityArrowBridge = new EntityArrowBridge(worldClient.V(entry.getKey()));
                if (entityArrowBridge.isNull()) continue;
                double d2 = this.r(entityPlayerSP, worldClient, entityArrowBridge);
                if (!this.N(entityArrowBridge, entityPlayerSP) || !(d2 <= 6.0) || !(d2 < d)) continue;
                float[] fArray = this.b(entityArrowBridge.z(), entityArrowBridge.N(), entityArrowBridge.h());
                float f = Math.abs(MathUtil.wrapAngleTo180(-(entityPlayerSP.J() - fArray[0])));
                float f2 = entityPlayerSP.V() - fArray[1];
                if (!((double)f <= (Double)this.k.K()) || !((double)f2 <= (Double)this.k.K() / 2.0)) continue;
                d = d2;
                targetArrow = entityArrowBridge;
            }
            if (targetArrow != null && !targetArrow.equals(this.A) && (this.D.U(this) || this.D.h(this, this.J.L()))) {
                this.O = 0;
                this.Z = false;
                this.A = targetArrow;
                this.r.reset();
            }
        }
    }

    private boolean e() {
        if (Minecraft.currentScreen().isNull()) {
            return false;
        }
        InvWalk invWalk = Vape.INSTANCE.getModManager().getMod(InvWalk.class);
        return invWalk == null || !invWalk.r$src$Z$14eylz9() || !invWalk.g$src$Z$tdg77x();
    }

    private Vec3d u(EntityArrowBridge entityArrowBridge) {
        double d = 0.95f;
        double d2 = entityArrowBridge.z() + (entityArrowBridge.t() + entityArrowBridge.L()) * d;
        double d3 = entityArrowBridge.N() + (entityArrowBridge.q() + entityArrowBridge.X$src$D$xt9pjp()) * d;
        double d4 = entityArrowBridge.h() + (entityArrowBridge.T() + entityArrowBridge.o()) * d;
        return new Vec3d(d2, d3, d4);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        WorldClient worldClient = eventPreTick.getWorld();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        this.f(worldClient);
        if (this.V) {
            this.t.Q(this);
            KeyBindingInputState.r();
            this.V = false;
        }
        boolean bl = RotationManager.b.u() && RotationManager.b.w() == this.o;
        this.Q$src$V$1lwwef1();
        if (this.c$src$Z$1m6sp6z()) {
            this.A(entityPlayerSP, worldClient);
        }
        if (this.J.L().booleanValue() && bl && this.F.L().booleanValue() && !this.S.L().booleanValue()) {
            this.S$src$V$1lxzzlr();
        }
        if (this.A != null) {
            boolean bl2;
            double d = this.A.b();
            float f = this.A.Y();
            float f2 = this.A.f$src$F$fst3ac();
            double d2 = this.A.z();
            double d3 = this.A.N();
            double d4 = this.A.h();
            AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(d2 - (double)f, d3, d4 - (double)f, d2 + (double)f, d3 + (double)f2, d4 + (double)f);
            axisAlignedBB = axisAlignedBB.expand(d, d, d);
            double d5 = this.A.t();
            double d6 = this.A.q();
            double d7 = this.A.T();
            Vec3d vec3d = RotationUtil.T(entityPlayerSP, axisAlignedBB.expand(-1.0, -1.0, -1.0), 0.0, 0.0, 0.0);
            float[] fArray = this.b(vec3d.H, vec3d.B, vec3d.i);
            if (this.o == null) {
                this.o = this.J.L() != false ? new AdaptiveRotationController() : new FixedRotationController(fArray[0], fArray[1]);
                this.o.k(true);
                this.o.t(0.5f);
                this.o.U(true);
                RotationManager.b.S(this.o);
            }
            if (this.o instanceof AdaptiveRotationController) {
                ((AdaptiveRotationController)this.o).b(false);
            }
            this.o.w(true);
            this.o.z(true);
            this.o.Y(((Double)this.c.K()).floatValue() * 1.5f);
            this.o.g(fArray[0], fArray[1]);
            this.o.D(true);
            boolean bl3 = bl2 = !(this.N(this.A, entityPlayerSP) && this.r(entityPlayerSP, worldClient, this.A) <= 6.0 || this.Z);
            if (this.A.M$src$Z$ff28xj() || !this.D.U(this) || !this.c$src$Z$1m6sp6z() || bl2) {
                if (this.A != null && this.t.v$src$Z$1r7ksy2()) {
                    this.t.Q(this);
                }
                this.r.reset();
                this.A = null;
                this.O = 0;
                this.Z = false;
                this.O$src$V$1lvst8b();
                this.c();
                return;
            }
            if (this.F.L().booleanValue()) {
                this.S$src$V$1lxzzlr();
            }
            AxisAlignedBB axisAlignedBB2 = this.A.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            AxisAlignedBB axisAlignedBB3 = ForgeVersion.MC_1_16_5.d() ? axisAlignedBB2 : axisAlignedBB2.A(d5, d6, d7);
            Vec3d vec3d2 = RotationUtil.T(entityPlayerSP, axisAlignedBB3.expand(d - 0.5, d - 0.5, d - 0.5), 0.0, 0.0, 0.0);
            float[] fArray2 = this.b(vec3d2.H, vec3d2.B, vec3d2.i);
            float f3 = entityPlayerSP.J();
            float f4 = entityPlayerSP.V();
            if (ForgeVersion.MC_1_16_5.v()) {
                entityPlayerSP.H(fArray2[0]);
                entityPlayerSP.C(fArray2[1]);
                this.A.D(axisAlignedBB3);
            }
            RayTraceResult rayTraceResult = RotationManager.b.n();
            if (ForgeVersion.MC_1_16_5.v()) {
                this.A.D(axisAlignedBB2);
                entityPlayerSP.H(f3);
                entityPlayerSP.C(f4);
            }
            if (!this.Z && rayTraceResult.isEntityHit() && this.A.equals(rayTraceResult.getEntity())) {
                this.t.S(this);
                GameSettings gameSettings = Minecraft.gameSettings();
                if (gameSettings.F().isKeyDown()) {
                    KeyBindingInputState.r();
                    return;
                }
                KeyBindingInputState.k();
                this.V = true;
                this.Z = true;
                this.O = 0;
            }
            if (this.Z) {
                this.o.Y(2.0f);
                if (this.O++ > 5) {
                    this.O = 0;
                    this.Z = false;
                    this.A = null;
                    this.O$src$V$1lvst8b();
                    this.c();
                }
            }
        } else {
            this.c();
        }
    }
}
