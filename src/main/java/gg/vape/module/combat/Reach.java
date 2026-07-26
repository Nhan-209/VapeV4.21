package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPostAttack;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.combat.autoclicker.AutoClickerEntityPositionState;
import gg.vape.notification.NotificationType;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.RayTraceUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.EffectRenderer;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityFX;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Reach
extends Mod {
    private int L = 0;
    private final Random A;
    private final BooleanValue I;
    private final BooleanValue c;
    private final NumberValue t;
    private final Map<Integer, AutoClickerEntityPositionState> p;
    private final RandomValue D = RandomValue.G(this, "Range", "#.##", "", 3.0, 3.0, 3.1, 4.0, 0.01, "The range in which your reach will be increased to.");
    private final BooleanValue J;
    private final ModeValue k;
    private final BooleanValue a;
    private boolean Z = false;
    private final BooleanValue C;
    private final ModeOption v;
    private Entity U;
    private long S;
    private final ModeOption o;
    private boolean j;

    @EventHandler
    public void d(EventPostAttack eventPostAttack) {
        if (Packet.h() && !this.o.o()) {
            return;
        }
        this.Z = false;
    }

    @EventHandler
    public void R(EventPreRenderTick eventPreRenderTick) {
        if (!this.I.L().booleanValue()) {
            return;
        }
        this.y(true);
    }

    private boolean c() {
        boolean bl = Packet.A();
        if (bl) {
            boolean bl2;
            boolean bl3 = bl2 = this.o.o();
            return bl3;
        }
        boolean bl4 = this.o.o();
        if (bl4) {
            return this.Z;
        }
        double d = (Double)this.t.K() - (double)this.A.nextInt(100);
        double d2 = d == 0.0 ? 0 : (d > 0.0 ? 1 : -1);
        return d2 > 0;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (Vape.INSTANCE.isOnlineConnected()) {
            Vape.INSTANCE.getNotificationManager().t("Reach is unsafe", "Reach is generally unsafe and detected by most servers, it is advised to avoid using it", NotificationType.ALERT, 15000L);
        }
    }

    private void x() {
        float f = this.a.L() != false ? -90.0f : 90.0f;
        double d = this.D.q$src$D$vgz097() - 3.0;
        EffectRenderer effectRenderer = Minecraft.z();
        if (effectRenderer.isNull()) {
            return;
        }
        try {
            List<EntityFX>[][] listArray;
            List<EntityFX>[][] listArray2 = listArray = effectRenderer.getFxLayers();
            int n = listArray2.length;
            for (int i = 0; i < n; ++i) {
                List<EntityFX>[] listArray3;
                for (List<EntityFX> list : listArray3 = listArray2[i]) {
                    for (EntityFX entityFX : list) {
                        EntityPlayerSP entityPlayerSP;
                        if (entityFX.isNull() || !entityFX.isInstance(MappedClasses.Fy) && !entityFX.isInstance(MappedClasses.Vc) || (entityPlayerSP = Minecraft.thePlayer()).isNull() || !((double)entityPlayerSP.getDistanceToEntity(entityFX) < this.D.M() + 2.0) || !(entityFX.N() < entityPlayerSP.N() + 2.5) || RotationUtil.S(entityPlayerSP, entityFX)) continue;
                        float f2 = this.x(entityPlayerSP.z(), entityPlayerSP.h(), entityFX.z(), entityFX.h());
                        double d2 = Math.cos(Math.toRadians(f2 + f)) * d;
                        double d3 = Math.sin(Math.toRadians(f2 + f)) * d;
                        entityFX.H(entityFX.z() - d2);
                        entityFX.l(entityFX.h() - d3);
                        entityFX.C(entityFX.M() - d2);
                        entityFX.s(entityFX.m$src$D$fwnne5() - d3);
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public BooleanValue S$src$Lgg_vape_value_BooleanValue_$ifrxg2() {
        return this.I;
    }

    @EventHandler
    public void W(EventPreTick eventPreTick) {
        if (this.o.o()) {
            Reach reach = this;
            reach.R();
            if (this.j) {
                this.S = System.currentTimeMillis();
                this.j = false;
            }
        }
    }

    private float x(double d, double d2, double d3, double d4) {
        double d5 = d3 - d;
        double d6 = d4 - d2;
        float f = (float)Math.toDegrees(-Math.atan(d5 / d6));
        if (d6 < 0.0 && d5 < 0.0) {
            f = (float)(90.0 + Math.toDegrees(Math.atan(d6 / d5)));
        } else if (d6 < 0.0 && d5 > 0.0) {
            f = (float)(-90.0 + Math.toDegrees(Math.atan(d6 / d5)));
        }
        return f;
    }

    @EventHandler
    public void G(EventPostRenderTick eventPostRenderTick) {
        if (!this.I.L().booleanValue()) {
            return;
        }
        this.h(true);
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private void y(boolean bl) {
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return;
        }
        for (Object e : worldClient.z()) {
            Entity entity = new Entity(e);
            if (!entity.isInstance(MappedClasses.lG) || entity.isInstance(MappedClasses.z5)) continue;
            EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(e);
            int n = entity.S();
            double d = entity.z();
            double d2 = entity.h();
            AutoClickerEntityPositionState autoClickerEntityPositionState = null;
            if (this.p.containsKey(n)) {
                autoClickerEntityPositionState = this.p.get(n);
            } else {
                autoClickerEntityPositionState = new AutoClickerEntityPositionState();
                autoClickerEntityPositionState.O = n;
            }
            this.p.put(n, autoClickerEntityPositionState);
            autoClickerEntityPositionState.L = d;
            autoClickerEntityPositionState.w = d2;
            autoClickerEntityPositionState.T = entity.M();
            autoClickerEntityPositionState.r = entity.m$src$D$fwnne5();
            autoClickerEntityPositionState.a = entityOtherPlayerMP.C$src$D$1i1kszo();
            autoClickerEntityPositionState.o = entityOtherPlayerMP.L$src$D$1i6iybx();
            autoClickerEntityPositionState.G = entityOtherPlayerMP.a$src$D$1ii2msi();
            autoClickerEntityPositionState.M = entityOtherPlayerMP.G$src$D$1i3rzd4();
            if (bl) {
                entityOtherPlayerMP.H(autoClickerEntityPositionState.U);
                entityOtherPlayerMP.l(autoClickerEntityPositionState.S);
                entityOtherPlayerMP.C(autoClickerEntityPositionState.m);
                entityOtherPlayerMP.s(autoClickerEntityPositionState.C);
                entityOtherPlayerMP.S(autoClickerEntityPositionState.f);
                entityOtherPlayerMP.c(autoClickerEntityPositionState.v);
                entityOtherPlayerMP.B(entity.z(), entity.N(), entity.h());
                continue;
            }
            entityOtherPlayerMP.H(autoClickerEntityPositionState.U);
            entityOtherPlayerMP.l(autoClickerEntityPositionState.S);
            entityOtherPlayerMP.B(entity.z(), entity.N(), entity.h());
            entityOtherPlayerMP.H(autoClickerEntityPositionState.L);
            entityOtherPlayerMP.l(autoClickerEntityPositionState.w);
        }
    }

    private boolean Y() {
        boolean bl;
        boolean bl2;
        boolean bl3;
        boolean bl4 = Packet.h();
        if (bl4) {
            boolean bl5;
            boolean bl6 = this.r$src$Z$14eylz9();
            boolean bl7 = !bl6 || this.I.L() != false || this.c.L() != false && (Minecraft.thePlayer().h$src$Z$ftwoya() || Minecraft.thePlayer().Q$src$Z$fh9faz());
            boolean bl8 = bl7;
            if (!bl8 && this.C.L().booleanValue()) {
                bl7 = !Minecraft.thePlayer().B$src$Z$f90iek();
            }
            return !(bl5 = bl7);
        }
        boolean bl9 = bl3 = (bl2 = (bl = this.r$src$Z$14eylz9()));
        return bl9;
    }

    @EventHandler
    public void c(EventPostTick eventPostTick) {
        if (!this.I.L().booleanValue()) {
            return;
        }
        this.m();
        this.h(false);
        if (ForgeVersion.MC_1_8_9.B()) {
            this.x();
        }
    }

    private void m() {
        WorldClient worldClient = Minecraft.theWorld();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (worldClient.isNull()) {
            return;
        }
        float f = this.a.L() != false ? -90.0f : 90.0f;
        for (Object e : worldClient.z()) {
            double d;
            Entity entity = new Entity(e);
            if (!entity.isInstance(MappedClasses.lG) || entity.isInstance(MappedClasses.z5)) continue;
            EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(e);
            double d2 = this.D.q$src$D$vgz097() - 3.0;
            double d3 = Math.hypot(entityPlayerSP.z() - entity.z(), entityPlayerSP.h() - entity.h());
            float f2 = this.x(entityPlayerSP.z(), entityPlayerSP.h(), entity.z(), entity.h());
            double d4 = d3 - d2;
            if (d4 < 0.5 && (d2 += (d = d4 - 0.5)) < 0.0) {
                d2 = 0.0;
            }
            d = Math.cos(Math.toRadians(f2 + f)) * d2;
            double d5 = Math.sin(Math.toRadians(f2 + f)) * d2;
            int n = entity.S();
            double d6 = entity.z();
            double d7 = entity.h();
            AutoClickerEntityPositionState autoClickerEntityPositionState = null;
            boolean bl = false;
            if (this.p.containsKey(n)) {
                autoClickerEntityPositionState = this.p.get(n);
                bl = true;
            } else {
                autoClickerEntityPositionState = new AutoClickerEntityPositionState();
                autoClickerEntityPositionState.O = n;
            }
            this.p.put(n, autoClickerEntityPositionState);
            autoClickerEntityPositionState.L = d6;
            autoClickerEntityPositionState.w = d7;
            autoClickerEntityPositionState.T = entity.M();
            autoClickerEntityPositionState.r = entity.m$src$D$fwnne5();
            autoClickerEntityPositionState.a = entityOtherPlayerMP.C$src$D$1i1kszo();
            autoClickerEntityPositionState.o = entityOtherPlayerMP.L$src$D$1i6iybx();
            autoClickerEntityPositionState.G = entityOtherPlayerMP.a$src$D$1ii2msi();
            autoClickerEntityPositionState.M = entityOtherPlayerMP.G$src$D$1i3rzd4();
            autoClickerEntityPositionState.U = autoClickerEntityPositionState.L - d;
            autoClickerEntityPositionState.S = autoClickerEntityPositionState.w - d5;
            autoClickerEntityPositionState.m = autoClickerEntityPositionState.T - d;
            autoClickerEntityPositionState.C = autoClickerEntityPositionState.r - d5;
            autoClickerEntityPositionState.f = autoClickerEntityPositionState.a - d;
            autoClickerEntityPositionState.v = autoClickerEntityPositionState.o - d5;
            if (!bl) continue;
            autoClickerEntityPositionState.m = autoClickerEntityPositionState.d;
            autoClickerEntityPositionState.C = autoClickerEntityPositionState.p;
        }
    }

    private void R() {
        Entity entity;
        block10: {
            block9: {
                block8: {
                    entity = null;
                    boolean bl = Packet.h();
                    if (bl) {
                        boolean bl2;
                        Entity entity2;
                        Entity entity3;
                        boolean bl3;
                        RayTraceResult rayTraceResult = RayTraceUtil.F(this.D.M(), 0.0f, true);
                        RayTraceResult rayTraceResult2 = rayTraceResult;
                        if (rayTraceResult2 != null && (bl3 = rayTraceResult.isNotNull()) && rayTraceResult.getEntity().isNotNull()) {
                            entity = rayTraceResult.getEntity();
                        }
                        Reach reach2 = this;
                        if (reach2.L > 0) {
                            --this.L;
                        }
                        if ((entity3 = entity) == null || (entity2 = entity).isNull() || this.U != null && !(bl2 = entity.equals(this.U))) {
                            this.U = null;
                            return;
                        }
                        Reach reach3 = this;
                        Reach reach4 = reach3;
                        if (reach4.Y()) {
                            Reach reach5 = this;
                            if (reach5.L == 0) {
                                boolean bl4 = this.Z = (Double)this.t.K() > (double)this.A.nextInt(100);
                                if (this.Z) {
                                    this.L = 10;
                                }
                            }
                        }
                        this.U = entity;
                        return;
                    }
                    RayTraceResult rayTraceResult = RayTraceUtil.F(this.D.M(), 0.0f, true);
                    RayTraceResult rayTraceResult3 = rayTraceResult;
                    boolean bl5 = rayTraceResult3.isNotNull();
                    if (bl5) {
                        entity = rayTraceResult.getEntity();
                    }
                    Reach reach6 = this;
                    --reach6.L;
                    Entity entity4 = entity;
                    Entity entity5 = entity4;
                    if (entity5 == null) break block8;
                    boolean bl6 = entity.equals(this.U);
                    if (bl6) break block9;
                    break block10;
                }
                break block10;
            }
        }
        this.U = entity;
    }

    @Override
    public String r() {
        return this.D.c();
    }

    @Override
    public void onDisable() {
        this.p.clear();
    }

    public double e() {
        Reach reach;
        Reach reach2 = this;
        if (!reach2.Y() || !(reach = this).c()) {
            return 3.0;
        }
        return this.D.B();
    }

    @EventHandler
    public void e(EventPreTick eventPreTick) {
        if (!this.I.L().booleanValue()) {
            return;
        }
        this.m();
        this.y(false);
    }

    public Reach() {
        super("Reach", -16711936, Category.g, "Extends attack reach");
        this.t = NumberValue.E(this, "Chance", "#", "%", 0.0, 50.0, 100.0, "The chance of reach taking affect when hitting an opponent");
        this.o = new ModeOption("Advanced");
        this.v = new ModeOption("Normal");
        this.k = ModeValue.create((Object)this, "Chance mode", this.o, this.o, this.v);
        this.I = BooleanValue.create(this, "Misplace", false, "Pulls players towards you rather than giving you extra reach distance. Uses the minimum slider value.");
        this.a = BooleanValue.create(this, "Disadvantage", false, "Moves misplaced players in opposite direction. Useful for framing other players");
        this.J = BooleanValue.create(this, "Vertical check", false, "Prevents hitting players which are y0.2 above or below you\nfor more legitimate use");
        this.C = BooleanValue.create(this, "Only while sprinting", false, "Only give extra reach while sprinting");
        this.c = BooleanValue.create(this, "Disable in water", false, "Won't give any extra reach while standing in water");
        this.p = new HashMap<Integer, AutoClickerEntityPositionState>();
        this.A = new Random();
        this.I.K(this.a);
        this.D.c(100.0);
        this.addValue(this.D, this.t, this.k, this.I, this.a, this.J, this.C, this.c);
    }

    private void h(boolean bl) {
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return;
        }
        for (Map.Entry<Integer, AutoClickerEntityPositionState> entry : this.p.entrySet()) {
            AutoClickerEntityPositionState autoClickerEntityPositionState = entry.getValue();
            Entity entity = new Entity(((World)worldClient).V(autoClickerEntityPositionState.O));
            if (!entity.isNotNull() || !entity.isInstance(MappedClasses.lG)) continue;
            EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(((World)worldClient).V(autoClickerEntityPositionState.O));
            if (bl) {
                autoClickerEntityPositionState.d = autoClickerEntityPositionState.U;
                autoClickerEntityPositionState.p = autoClickerEntityPositionState.S;
                entityOtherPlayerMP.H(autoClickerEntityPositionState.L);
                entityOtherPlayerMP.l(autoClickerEntityPositionState.w);
                entityOtherPlayerMP.C(autoClickerEntityPositionState.T);
                entityOtherPlayerMP.s(autoClickerEntityPositionState.r);
                entityOtherPlayerMP.S(autoClickerEntityPositionState.a);
                entityOtherPlayerMP.c(autoClickerEntityPositionState.o);
            }
            entityOtherPlayerMP.B(entityOtherPlayerMP.z(), entityOtherPlayerMP.N(), entityOtherPlayerMP.h());
        }
    }
}
