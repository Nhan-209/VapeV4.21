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
    private int hitCooldown = 0;
    private final Random random;
    private final BooleanValue misplace;
    private final BooleanValue disableInWater;
    private final NumberValue chance;
    private final Map<Integer, AutoClickerEntityPositionState> entityStates;
    private final RandomValue range = RandomValue.G(this, "Range", "#.##", "", 3.0, 3.0, 3.1, 4.0, 0.01, "The range in which your reach will be increased to.");
    private final BooleanValue verticalCheck;
    private final ModeValue chanceMode;
    private final BooleanValue disadvantage;
    private boolean reachActive = false;
    private final BooleanValue onlyWhileSprinting;
    private final ModeOption normalMode;
    private Entity lastTarget;
    private long startTime;
    private final ModeOption advancedMode;
    private boolean justStarted;

    @EventHandler
    public void onPostAttack(EventPostAttack eventPostAttack) {
        if (Packet.h() && !this.advancedMode.o()) {
            return;
        }
        this.reachActive = false;
    }

    @EventHandler
    public void onPreRenderTick(EventPreRenderTick eventPreRenderTick) {
        if (!this.misplace.L().booleanValue()) {
            return;
        }
        this.applyMisplace(true);
    }

    private boolean shouldExtendReach() {
        boolean bl = Packet.A();
        if (bl) {
            boolean bl2;
            boolean bl3 = bl2 = this.advancedMode.o();
            return bl3;
        }
        boolean bl4 = this.advancedMode.o();
        if (bl4) {
            return this.reachActive;
        }
        double d = (Double)this.chance.K() - (double)this.random.nextInt(100);
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

    private void misplaceParticles() {
        float f = this.disadvantage.L() != false ? -90.0f : 90.0f;
        double d = this.range.q$src$D$vgz097() - 3.0;
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
                        if (entityFX.isNull() || !entityFX.isInstance(MappedClasses.Fy) && !entityFX.isInstance(MappedClasses.Vc) || (entityPlayerSP = Minecraft.thePlayer()).isNull() || !((double)entityPlayerSP.getDistanceToEntity(entityFX) < this.range.M() + 2.0) || !(entityFX.N() < entityPlayerSP.N() + 2.5) || RotationUtil.S(entityPlayerSP, entityFX)) continue;
                        float f2 = this.computeAngle(entityPlayerSP.z(), entityPlayerSP.h(), entityFX.z(), entityFX.h());
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
        return this.misplace;
    }

    @EventHandler
    public void onPreTick(EventPreTick eventPreTick) {
        if (this.advancedMode.o()) {
            Reach reach = this;
            reach.updateTarget();
            if (this.justStarted) {
                this.startTime = System.currentTimeMillis();
                this.justStarted = false;
            }
        }
    }

    private float computeAngle(double d, double d2, double d3, double d4) {
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
    public void onPostRenderTick(EventPostRenderTick eventPostRenderTick) {
        if (!this.misplace.L().booleanValue()) {
            return;
        }
        this.restorePositions(true);
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private void applyMisplace(boolean bl) {
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
            if (this.entityStates.containsKey(n)) {
                autoClickerEntityPositionState = this.entityStates.get(n);
            } else {
                autoClickerEntityPositionState = new AutoClickerEntityPositionState();
                autoClickerEntityPositionState.O = n;
            }
            this.entityStates.put(n, autoClickerEntityPositionState);
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

    private boolean isReachAllowed() {
        boolean bl;
        boolean bl2;
        boolean bl3;
        boolean bl4 = Packet.h();
        if (bl4) {
            boolean bl5;
            boolean bl6 = this.r$src$Z$14eylz9();
            boolean bl7 = !bl6 || this.misplace.L() != false || this.disableInWater.L() != false && (Minecraft.thePlayer().h$src$Z$ftwoya() || Minecraft.thePlayer().Q$src$Z$fh9faz());
            boolean bl8 = bl7;
            if (!bl8 && this.onlyWhileSprinting.L().booleanValue()) {
                bl7 = !Minecraft.thePlayer().B$src$Z$f90iek();
            }
            return !(bl5 = bl7);
        }
        boolean bl9 = bl3 = (bl2 = (bl = this.r$src$Z$14eylz9()));
        return bl9;
    }

    @EventHandler
    public void onPostTick(EventPostTick eventPostTick) {
        if (!this.misplace.L().booleanValue()) {
            return;
        }
        this.updateMisplacedPositions();
        this.restorePositions(false);
        if (ForgeVersion.MC_1_8_9.B()) {
            this.misplaceParticles();
        }
    }

    private void updateMisplacedPositions() {
        WorldClient worldClient = Minecraft.theWorld();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (worldClient.isNull()) {
            return;
        }
        float f = this.disadvantage.L() != false ? -90.0f : 90.0f;
        for (Object e : worldClient.z()) {
            double d;
            Entity entity = new Entity(e);
            if (!entity.isInstance(MappedClasses.lG) || entity.isInstance(MappedClasses.z5)) continue;
            EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(e);
            double d2 = this.range.q$src$D$vgz097() - 3.0;
            double d3 = Math.hypot(entityPlayerSP.z() - entity.z(), entityPlayerSP.h() - entity.h());
            float f2 = this.computeAngle(entityPlayerSP.z(), entityPlayerSP.h(), entity.z(), entity.h());
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
            if (this.entityStates.containsKey(n)) {
                autoClickerEntityPositionState = this.entityStates.get(n);
                bl = true;
            } else {
                autoClickerEntityPositionState = new AutoClickerEntityPositionState();
                autoClickerEntityPositionState.O = n;
            }
            this.entityStates.put(n, autoClickerEntityPositionState);
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

    private void updateTarget() {
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
                        RayTraceResult rayTraceResult = RayTraceUtil.F(this.range.M(), 0.0f, true);
                        RayTraceResult rayTraceResult2 = rayTraceResult;
                        if (rayTraceResult2 != null && (bl3 = rayTraceResult.isNotNull()) && rayTraceResult.getEntity().isNotNull()) {
                            entity = rayTraceResult.getEntity();
                        }
                        Reach reach2 = this;
                        if (reach2.hitCooldown > 0) {
                            --this.hitCooldown;
                        }
                        if ((entity3 = entity) == null || (entity2 = entity).isNull() || this.lastTarget != null && !(bl2 = entity.equals(this.lastTarget))) {
                            this.lastTarget = null;
                            return;
                        }
                        Reach reach3 = this;
                        Reach reach4 = reach3;
                        if (reach4.isReachAllowed()) {
                            Reach reach5 = this;
                            if (reach5.hitCooldown == 0) {
                                boolean bl4 = this.reachActive = (Double)this.chance.K() > (double)this.random.nextInt(100);
                                if (this.reachActive) {
                                    this.hitCooldown = 10;
                                }
                            }
                        }
                        this.lastTarget = entity;
                        return;
                    }
                    RayTraceResult rayTraceResult = RayTraceUtil.F(this.range.M(), 0.0f, true);
                    RayTraceResult rayTraceResult3 = rayTraceResult;
                    boolean bl5 = rayTraceResult3.isNotNull();
                    if (bl5) {
                        entity = rayTraceResult.getEntity();
                    }
                    Reach reach6 = this;
                    --reach6.hitCooldown;
                    Entity entity4 = entity;
                    Entity entity5 = entity4;
                    if (entity5 == null) break block8;
                    boolean bl6 = entity.equals(this.lastTarget);
                    if (bl6) break block9;
                    break block10;
                }
                break block10;
            }
        }
        this.lastTarget = entity;
    }

    @Override
    public String r() {
        return this.range.c();
    }

    @Override
    public void onDisable() {
        this.entityStates.clear();
    }

    public double e() {
        Reach reach;
        Reach reach2 = this;
        if (!reach2.isReachAllowed() || !(reach = this).shouldExtendReach()) {
            return 3.0;
        }
        return this.range.B();
    }

    @EventHandler
    public void onPreTickMisplace(EventPreTick eventPreTick) {
        if (!this.misplace.L().booleanValue()) {
            return;
        }
        this.updateMisplacedPositions();
        this.applyMisplace(false);
    }

    public Reach() {
        super("Reach", -16711936, Category.g, "Extends attack reach");
        this.chance = NumberValue.E(this, "Chance", "#", "%", 0.0, 50.0, 100.0, "The chance of reach taking affect when hitting an opponent");
        this.advancedMode = new ModeOption("Advanced");
        this.normalMode = new ModeOption("Normal");
        this.chanceMode = ModeValue.create((Object)this, "Chance mode", this.advancedMode, this.advancedMode, this.normalMode);
        this.misplace = BooleanValue.create(this, "Misplace", false, "Pulls players towards you rather than giving you extra reach distance. Uses the minimum slider value.");
        this.disadvantage = BooleanValue.create(this, "Disadvantage", false, "Moves misplaced players in opposite direction. Useful for framing other players");
        this.verticalCheck = BooleanValue.create(this, "Vertical check", false, "Prevents hitting players which are y0.2 above or below you\nfor more legitimate use");
        this.onlyWhileSprinting = BooleanValue.create(this, "Only while sprinting", false, "Only give extra reach while sprinting");
        this.disableInWater = BooleanValue.create(this, "Disable in water", false, "Won't give any extra reach while standing in water");
        this.entityStates = new HashMap<Integer, AutoClickerEntityPositionState>();
        this.random = new Random();
        this.misplace.K(this.disadvantage);
        this.range.c(100.0);
        this.addValue(this.range, this.chance, this.chanceMode, this.misplace, this.disadvantage, this.verticalCheck, this.onlyWhileSprinting, this.disableInWater);
    }

    private void restorePositions(boolean bl) {
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return;
        }
        for (Map.Entry<Integer, AutoClickerEntityPositionState> entry : this.entityStates.entrySet()) {
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
