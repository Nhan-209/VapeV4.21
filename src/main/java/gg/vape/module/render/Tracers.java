package gg.vape.module.render;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRenderTracers3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.module.render.entity.RenderEntityContextCache;
import gg.vape.module.render.entity.RenderEntityContextEntry;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MutableColor;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.Vec3d;
import gg.vape.utils.render.BufferedRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.ActiveRenderInfo;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.opengl.GL11;

public class Tracers
extends Mod {
    private final BooleanValue H;
    private final RandomValue L;
    private double k;
    private final BooleanValue V;
    private final BooleanValue K;
    private static final long P = 6957373267435107050L;
    private final BooleanValue r;
    private final BooleanValue t;
    private final Map<EntityLivingBase, RenderEntityContextEntry> O;
    private final ColorValue Z;
    private final RandomValue D;
    private final BooleanValue p;
    private final ColorValue a;
    private final BooleanValue J;
    private final BooleanValue b;
    private final BooleanValue F = BooleanValue.create(this, "Enemy Only", false);
    private final BooleanValue j = BooleanValue.create(this, "Enemies List Only", false);
    private final RandomValue A;
    private List<EntityLivingBase> S;
    private final ColorValue c;
    private final BooleanValue I;

    private static int lambda$getMedianDistance$0(EntityPlayerSP entityPlayerSP, EntityLivingBase entityLivingBase, EntityLivingBase entityLivingBase2) {
        double d;
        double d2 = RenderEntityContextCache.V(entityLivingBase, entityPlayerSP).e();
        if (d2 == (d = RenderEntityContextCache.V(entityLivingBase2, entityPlayerSP).e())) {
            return 0;
        }
        return d2 > d ? 1 : -1;
    }

    private void updateMedianDistance(EntityPlayerSP entityPlayerSP) {
        this.S.sort((arg_0, arg_1) -> Tracers.lambda$getMedianDistance$0(entityPlayerSP, arg_0, arg_1));
        ArrayList<Double> arrayList = new ArrayList<Double>();
        for (Map.Entry<EntityLivingBase, RenderEntityContextEntry> entry : this.O.entrySet()) {
            arrayList.add(entry.getValue().Y().e());
        }
        Collections.sort(arrayList);
        this.k = (Double)arrayList.get(arrayList.size() / 2);
    }

    private void collectEntities(EntityPlayerSP entityPlayerSP, WorldClient worldClient) {
        for (Object e : worldClient.z()) {
            EntityLivingBase entityLivingBase;
            RenderEntityContext renderEntityContext;
            Entity entity = new Entity(e);
            if (!entity.isInstance(MappedClasses.zm) || entity.equals(entityPlayerSP) || (renderEntityContext = RenderEntityContextCache.V(entityLivingBase = new EntityLivingBase(entity), entityPlayerSP)).P() || !this.H.L().booleanValue() && renderEntityContext.o$src$Z$1y639j7() || renderEntityContext.D() || this.F.L().booleanValue() && (this.j.L() != false ? !renderEntityContext.f() : !Vape.INSTANCE.getClientSettings().g(entity, false))) continue;
            float f = (float)renderEntityContext.e();
            if (entity.isInstance(MappedClasses.lG)) {
                if (!this.J.L().booleanValue() || this.I.L().booleanValue() && ((double)f < this.A.q$src$D$vgz097() || (double)f > this.A.M())) continue;
                if (renderEntityContext.K$src$Z$1xmao67() && Vape.INSTANCE.getFriendManager().q.L().booleanValue()) {
                    this.O.put(entityLivingBase, new RenderEntityContextEntry(renderEntityContext, Vape.INSTANCE.getFriendManager().R.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), null));
                    continue;
                }
                if (renderEntityContext.f() && Vape.INSTANCE.getEnemyManager().p.L().booleanValue()) {
                    this.O.put(entityLivingBase, new RenderEntityContextEntry(renderEntityContext, Vape.INSTANCE.getEnemyManager().i.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), null));
                    continue;
                }
                this.O.put(entityLivingBase, new RenderEntityContextEntry(renderEntityContext, this.Z.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), null));
                continue;
            }
            boolean bl = false;
            if (ForgeVersion.MC_1_17.d()) {
                if (entity.isInstance(MappedClasses.Yw) || entity.isInstance(MappedClasses.Zo)) {
                    bl = true;
                }
            } else if (entity.isInstance(MappedClasses.Fr) || entity.isInstance(MappedClasses.Zo)) {
                bl = true;
            }
            if (bl) {
                if (!this.b.L().booleanValue() || this.r.L().booleanValue() && ((double)f < this.D.q$src$D$vgz097() || (double)f > this.D.M())) continue;
                this.O.put(entityLivingBase, new RenderEntityContextEntry(renderEntityContext, this.a.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), null));
                continue;
            }
            if (!this.t.L().booleanValue() || entity.isInstance(MappedClasses.Zo) || this.p.L().booleanValue() && ((double)f < this.L.q$src$D$vgz097() || (double)f > this.L.M())) continue;
            this.O.put(entityLivingBase, new RenderEntityContextEntry(renderEntityContext, this.c.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), null));
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Tracers() {
        super("Tracers", (int)P, Category.k);
        this.J = BooleanValue.create(this, "Render Players", true);
        this.b = BooleanValue.create(this, "Render Mobs", false);
        this.t = BooleanValue.create(this, "Render Animals", false);
        this.I = BooleanValue.create(this, "Distance Check", false);
        this.r = BooleanValue.create(this, "Distance Check", false);
        this.p = BooleanValue.create(this, "Distance Check", false);
        this.H = BooleanValue.create(this, "Invisibles", false);
        this.K = BooleanValue.create(this, "Color by distance", false);
        this.V = BooleanValue.create(this, "Highlight if focusing", false, "If another player is looking at you their tracer will be highlighted");
        this.A = RandomValue.create(this, "Player Distance", "#", "", 0.0, 0.0, 32.0, 256.0);
        this.D = RandomValue.create(this, "Mob Distance", "#", "", 0.0, 0.0, 32.0, 256.0);
        this.L = RandomValue.create(this, "Animal Distance", "#", "", 0.0, 0.0, 32.0, 256.0);
        this.Z = ColorValue.L(this, "Player Color", new Color(0, 150, 255, 255));
        this.a = ColorValue.L(this, "Mob Color", new Color(255, 154, 0));
        this.c = ColorValue.L(this, "Animal Color", new Color(255, 255, 255));
        this.O = new HashMap<EntityLivingBase, RenderEntityContextEntry>();
        this.J.K(this.F, this.I, this.Z);
        this.b.K(this.r, this.a);
        this.t.K(this.p, this.c);
        this.I.K(this.A);
        this.r.K(this.D);
        this.p.K(this.L);
        this.addValue(this.H, this.K, this.V, this.J, this.F, this.I, this.A, this.Z, this.t, this.p, this.L, this.c, this.b, this.r, this.D, this.a);
    }

    private void drawTracer(EntityPlayerSP entityPlayerSP, Entity entity, Color color, float f, float f2, double d, double d2, double d3, double d4, boolean bl) {
        double d5 = entity.M() + (entity.z() - entity.M()) * (double)f2 - d;
        double d6 = entity.W() + (entity.N() - entity.W()) * (double)f2 - d2;
        double d7 = entity.m$src$D$fwnne5() + (entity.h() - entity.m$src$D$fwnne5()) * (double)f2 - d3;
        boolean bl2 = false;
        boolean bl3 = false;
        bl2 = OpenGlBackendHolder.d.L(3042);
        bl3 = OpenGlBackendHolder.d.L(2896);
        GL11.glBlendFunc((int)770, (int)771);
        if (!bl2) {
            OpenGlBackendHolder.d.l(3042);
        }
        if (bl3) {
            OpenGlBackendHolder.d.u$src$V$hntn98(2896);
        }
        GL11.glBlendFunc((int)770, (int)771);
        OpenGlBackendHolder.d.l(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        double d8 = 0.0;
        double d9 = 0.0;
        if (ForgeVersion.MC_1_12_2.d()) {
            Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0);
            if (ForgeVersion.MC_1_16_5.d()) {
                vec3d.k((float)(-Math.toRadians(Minecraft.D().getPlayerViewY())));
                vec3d.Y((float)(-Math.toRadians(Minecraft.D().getPlayerViewX())));
            } else {
                vec3d.k((float)(-Math.toRadians(entityPlayerSP.V())));
                vec3d.Y((float)(-Math.toRadians(entityPlayerSP.J())));
            }
            d8 = vec3d.Y();
            d4 += ForgeVersion.MC_1_16_5.d() ? vec3d.t() - (double)entityPlayerSP.X() : vec3d.t();
            d9 = vec3d.o();
            if (ForgeVersion.MC_1_16_5.d() && Minecraft.gameSettings().x() != 0) {
                ActiveRenderInfo activeRenderInfo = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l();
                double d10 = RenderManager.getInterpolatedRenderPosX() - activeRenderInfo.o().getX();
                double d11 = RenderManager.getInterpolatedRenderPosY() - activeRenderInfo.o().getY();
                double d12 = RenderManager.getInterpolatedRenderPosZ() - activeRenderInfo.o().getZ();
                d5 += d10;
                d6 += d11;
                d7 += d12;
            }
        }
        d6 += (double)entity.X();
        if (GuiRenderPrimitives.d()) {
            if (bl) {
                BufferedRenderPrimitives.Q(d8, d4, d9, d5, d6, d7, f + f * 0.5f, Color.black);
            }
            BufferedRenderPrimitives.Q(d8, d4, d9, d5, d6, d7, f, color);
        } else {
            if (bl) {
                GL11.glLineWidth((float)(f + f * 0.5f));
                GL11.glBegin((int)1);
                RenderUtils.w(Color.black);
                GL11.glVertex3d((double)d8, (double)d4, (double)d9);
                GL11.glVertex3d((double)d5, (double)d6, (double)d7);
                GL11.glEnd();
            }
            GL11.glLineWidth((float)f);
            GL11.glBegin((int)1);
            RenderUtils.w(color);
            GL11.glVertex3d((double)d8, (double)d4, (double)d9);
            GL11.glVertex3d((double)d5, (double)d6, (double)d7);
            GL11.glEnd();
        }
        if (bl3) {
            OpenGlBackendHolder.d.l(2896);
        }
        if (!bl2) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        }
        OpenGlBackendHolder.d.l(3553);
        OpenGlBackendHolder.d.u$src$V$hntn98(2848);
    }

    private void applyFocusHighlight(EntityPlayerSP entityPlayerSP) {
        if (this.V.L().booleanValue()) {
            ArrayList<EntityLivingBase> arrayList = new ArrayList<EntityLivingBase>();
            for (EntityLivingBase entityLivingBase : this.S) {
                if (!(RotationUtil.M(entityLivingBase, entityPlayerSP) < 5.0)) continue;
                arrayList.add(entityLivingBase);
            }
            if (!arrayList.isEmpty()) {
                for (EntityLivingBase entityLivingBase : this.S) {
                    RenderEntityContextEntry renderEntityContextEntry = this.O.get(entityLivingBase);
                    if (!renderEntityContextEntry.Y().Y()) continue;
                    if (arrayList.contains(entityLivingBase)) {
                        renderEntityContextEntry.S(3.0);
                        renderEntityContextEntry.I(true);
                        continue;
                    }
                    renderEntityContextEntry.S(0.75);
                }
            }
        }
    }

    @EventHandler
    public void onRender(EventRenderTracers3D eventRenderTracers3D) {
        EntityPlayerSP entityPlayerSP = eventRenderTracers3D.getThePlayer();
        this.collectEntities(entityPlayerSP, eventRenderTracers3D.getWorld());
        if (this.O.isEmpty()) {
            return;
        }
        RenderUtil.d();
        RenderUtils.g();
        OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        eventRenderTracers3D.getEntityRenderer().B(0.0);
        double d = RenderManager.getInterpolatedRenderPosX();
        double d2 = RenderManager.getInterpolatedRenderPosY();
        double d3 = RenderManager.getInterpolatedRenderPosZ();
        double d4 = ForgeVersion.MC_1_7_10.Y() ? (double)entityPlayerSP.X() : 0.0;
        this.S = new ArrayList<EntityLivingBase>(this.O.keySet());
        this.updateMedianDistance(entityPlayerSP);
        this.applyDistanceColors(entityPlayerSP);
        this.applyFocusHighlight(entityPlayerSP);
        Collections.reverse(this.S);
        for (EntityLivingBase entityLivingBase : this.S) {
            MutableColor mutableColor;
            RenderEntityContextEntry renderEntityContextEntry = this.O.get(entityLivingBase);
            Color color = renderEntityContextEntry.y();
            if (entityLivingBase.isInstance(MappedClasses.Yl) && (mutableColor = renderEntityContextEntry.Y().E(false)) != null) {
                color = mutableColor;
            }
            this.drawTracer(entityPlayerSP, entityLivingBase, color, (float)renderEntityContextEntry.b(), eventRenderTracers3D.getTicks(), d, d2, d3, d4, renderEntityContextEntry.F());
        }
        OpenGlBackendHolder.d.b(1.0, 1.0f, 1.0f);
        eventRenderTracers3D.getEntityRenderer().O(0.0);
        OpenGlBackendHolder.d.l(2929);
        RenderUtils.f();
        OpenGlBackendHolder.d.F();
        this.O.clear();
    }

    private void applyDistanceColors(EntityPlayerSP entityPlayerSP) {
        if (this.K.L().booleanValue()) {
            for (EntityLivingBase entityLivingBase : this.S) {
                double d = RenderEntityContextCache.V(entityLivingBase, entityPlayerSP).e();
                float f = 0.35f;
                double d2 = (double)(Math.round(d / 3.0) * 3L) - this.k / 3.0;
                float f2 = (float)((double)f * (d2 / this.k));
                if (d > this.k) {
                    f2 = f;
                }
                Color color = new Color(Color.HSBtoRGB(f2, 1.0f, 1.0f));
                int n = 255;
                if (d > this.k && (n = (int)(255.0 / (d / this.k))) < 150) {
                    n = 150;
                }
                color = new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
                this.O.get(entityLivingBase).Q(color);
            }
        }
    }
}

