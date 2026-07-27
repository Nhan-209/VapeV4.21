package gg.vape.module.render.proj;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.render.proj.ArrowProjectile;
import gg.vape.module.render.proj.EnderPearlProjectileBridge;
import gg.vape.module.render.proj.IProjectile;
import gg.vape.module.render.proj.PotionProjectile;
import gg.vape.module.render.proj.Projectile;
import gg.vape.utils.render.BufferedRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityEnderPearl;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class Projectiles
extends Mod {
    private final List<Float> A;
    private final BooleanValue j;
    private final ArrowProjectile V = new ArrowProjectile();
    private IntBuffer s = null;
    private final Projectile a;
    private final RenderManager t;
    private final BooleanValue k;
    private final List<Integer> Z;
    private IntBuffer K = null;
    private final Projectile J;
    private final BooleanValue c;
    private final PotionProjectile o = new PotionProjectile();
    private FloatBuffer b = null;
    private final BooleanValue S;
    private final Projectile H = new Projectile(Collections.singleton(MappedClasses.Zg), new Color(173, 12, 255));
    private final List<Integer> O;
    private final BooleanValue P;

    private IProjectile C(EntityEnderPearl entityEnderPearl) {
        if (entityEnderPearl.b$src$Z$fqlxe4()) {
            return null;
        }
        if (entityEnderPearl.z() == entityEnderPearl.M() && entityEnderPearl.h() == entityEnderPearl.m$src$D$fwnne5()) {
            return null;
        }
        for (IProjectile iProjectile : this.p()) {
            if (!iProjectile.matches(entityEnderPearl)) continue;
            return iProjectile;
        }
        return null;
    }

    private void n(double d, double d2, double d3, Color color) {
        double d4 = RenderManager.getInterpolatedRenderPosX();
        double d5 = RenderManager.getInterpolatedRenderPosY();
        double d6 = RenderManager.getInterpolatedRenderPosZ();
        this.A.add(Float.valueOf((float)(d - d4)));
        this.A.add(Float.valueOf((float)(d2 - d5)));
        this.A.add(Float.valueOf((float)(d3 - d6)));
        this.A.add(Float.valueOf((float)color.getRed() / 255.0f));
        this.A.add(Float.valueOf((float)color.getGreen() / 255.0f));
        this.A.add(Float.valueOf((float)color.getBlue() / 255.0f));
        this.A.add(Float.valueOf((float)color.getAlpha() / 255.0f));
    }

    private void Z() {
        if (this.b == null || this.b.capacity() < this.A.size()) {
            this.b = BufferUtils.createFloatBuffer((int)this.A.size());
        } else {
            this.b.clear();
        }
        for (Float comparable2 : this.A) {
            this.b.put(comparable2.floatValue());
        }
        this.b.flip();
        GL11.glEnableClientState((int)32884);
        GL11.glEnableClientState((int)32886);
        int n = 28;
        FloatBuffer floatBuffer = this.b.duplicate();
        floatBuffer.position(0);
        GL11.glVertexPointer((int)3, (int)n, (FloatBuffer)floatBuffer);
        FloatBuffer floatBuffer2 = this.b.duplicate();
        floatBuffer2.position(3);
        GL11.glColorPointer((int)4, (int)n, (FloatBuffer)floatBuffer2);
        int n2 = this.Z.size();
        if (this.K == null || this.K.capacity() < n2) {
            this.K = BufferUtils.createIntBuffer((int)n2);
        } else {
            this.K.clear();
        }
        if (this.s == null || this.s.capacity() < n2) {
            this.s = BufferUtils.createIntBuffer((int)n2);
        } else {
            this.s.clear();
        }
        for (int i = 0; i < n2; ++i) {
            this.K.put(this.Z.get(i));
            this.s.put(this.O.get(i));
        }
        this.K.flip();
        this.s.flip();
        GL14.glMultiDrawArrays((int)3, (IntBuffer)this.K, (IntBuffer)this.s);
        GL11.glDisableClientState((int)32884);
        GL11.glDisableClientState((int)32886);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        this.A.clear();
        this.Z.clear();
        this.O.clear();
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return;
        }
        for (Object e : worldClient.S()) {
            Entity entity = new Entity(e);
            if (!entity.isInstance(MappedClasses.lv)) continue;
            EnderPearlProjectileBridge enderPearlProjectileBridge = new EnderPearlProjectileBridge(entity.getObject());
            EntityEnderPearl entityEnderPearl = new EntityEnderPearl(entity.getObject());
            IProjectile iProjectile = this.C(enderPearlProjectileBridge);
            if (iProjectile == null) continue;
            this.Q(entityEnderPearl, iProjectile);
        }
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (this.A.isEmpty()) {
            return;
        }
        OpenGlBackendHolder.d.r(1.5f);
        OpenGlBackendHolder.d.l(2848);
        boolean bl = OpenGlBackendHolder.d.L(3042);
        if (bl) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            OpenGlBackendHolder.d.u$src$V$hntn98(2929);
            RenderUtil.d();
            if (GuiRenderPrimitives.d()) {
                this.M$src$V$zpanub();
            } else {
                this.Z();
            }
            RenderUtil.Y();
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
            OpenGlBackendHolder.d.l(3553);
            OpenGlBackendHolder.d.l(2929);
            return;
        }
        OpenGlBackendHolder.d.l(3042);
        OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        RenderUtil.d();
        if (GuiRenderPrimitives.d()) {
            this.M$src$V$zpanub();
        } else {
            this.Z();
        }
        RenderUtil.Y();
        OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        OpenGlBackendHolder.d.l(3553);
        OpenGlBackendHolder.d.l(2929);
    }

    public Projectiles() {
        super("Projectiles", -16535661, Category.k, "Shows projectile trajectories while in air");
        this.a = new Projectile(Collections.singleton(MappedClasses.l2), new Color(255, 238, 154));
        this.J = new Projectile(Collections.singleton(MappedClasses.YZ), new Color(255, 255, 255));
        this.k = BooleanValue.create(this, "Show Arrows", true);
        this.j = BooleanValue.create(this, "Show Pearls", true);
        this.c = BooleanValue.create(this, "Show Potions", false);
        this.P = BooleanValue.create(this, "Show Eggs", false);
        this.S = BooleanValue.create(this, "Show Snowballs", false);
        this.A = new ArrayList<Float>();
        this.Z = new ArrayList<Integer>();
        this.O = new ArrayList<Integer>();
        this.t = Minecraft.D();
        this.addValue(this.k, this.j, this.c, this.P, this.S);
    }


    private void Q(EntityEnderPearl entityEnderPearl, IProjectile iProjectile) {
        if (!entityEnderPearl.isInstance(MappedClasses.lv)) {
            return;
        }
        int n = this.A.size() / 7;
        Color color = iProjectile.getColor(entityEnderPearl.getObject());
        if (color == null) {
            color = new Color(255, 255, 255);
        }
        this.n(entityEnderPearl.z(), entityEnderPearl.N(), entityEnderPearl.h(), color);
        double d = entityEnderPearl.z();
        double d2 = entityEnderPearl.N();
        double d3 = entityEnderPearl.h();
        double d4 = entityEnderPearl.t();
        double d5 = entityEnderPearl.q();
        double d6 = entityEnderPearl.T();
        WorldClient worldClient = Minecraft.theWorld();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        while (true) {
            float f = iProjectile.getCollisionRadius();
            float f2 = iProjectile.getCollisionHeight();
            AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(d - (double)f, d2, d3 - (double)f, d + (double)f, d2 + (double)f2, d3 + (double)f);
            Vec3 vec3 = Vec3.create(d, d2, d3);
            Vec3 vec32 = Vec3.create(d + d4, d2 + d5, d3 + d6);
            RayTraceResult rayTraceResult = worldClient.K(vec3, vec32, false, entityEnderPearl.isInstance(MappedClasses.F), false, entityEnderPearl);
            if (ForgeVersion.MC_1_16_5.d()) {
                if (!rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.miss())) {
                    vec32 = Vec3.create(rayTraceResult.getHitVec().getX(), rayTraceResult.getHitVec().getY(), rayTraceResult.getHitVec().getZ());
                }
            } else if (rayTraceResult.isNotNull()) {
                vec32 = Vec3.create(rayTraceResult.getHitVec().getX(), rayTraceResult.getHitVec().getY(), rayTraceResult.getHitVec().getZ());
            }
            List list = worldClient.F(entityPlayerSP, axisAlignedBB.addCoord(d4, d5, d6).expand(1.0, 1.0, 1.0));
            double d7 = 0.0;
            for (Object e : list) {
                RayTraceResult rayTraceResult2;
                Entity entity = new Entity(e);
                if (!entity.isInstance(MappedClasses.zm) || entity.isInstance(MappedClasses.uz) || !entity.n$src$Z$fx7gig() || entity.equals(entityPlayerSP) || !(rayTraceResult2 = (axisAlignedBB = entity.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().expand(0.3, 0.3, 0.3)).calculateIntercept(vec3, vec32)).isNotNull()) continue;
                double d8 = vec3.distanceTo(rayTraceResult2.getHitVec());
                if (d7 != 0.0 && !(d8 < d7)) continue;
                d7 = d8;
                rayTraceResult2.setEntity(entity);
                rayTraceResult = rayTraceResult2;
            }
            d += d4;
            d3 += d6;
            if ((!ForgeVersion.MC_1_16_5.d() ? rayTraceResult.isNotNull() : !rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.miss())) || (d2 += d5) < -128.0) break;
            d5 *= entityEnderPearl.h$src$Z$ftwoya() ? 0.8 : 0.99;
            this.n(d + (d4 *= entityEnderPearl.h$src$Z$ftwoya() ? 0.8 : 0.99), d2 + (d5 -= 0.05), d3 + (d6 *= entityEnderPearl.h$src$Z$ftwoya() ? 0.8 : 0.99), color);
        }
        int n2 = this.A.size() / 7 - n;
        this.Z.add(n);
        this.O.add(n2);
    }

    private void M$src$V$zpanub() {
        if (this.A.size() < 14) {
            return;
        }
        int n = 7;
        for (int i = 0; i < this.Z.size(); ++i) {
            int n2;
            for (int j = n2 = this.Z.get(i) * n; j < this.O.get(i) * n + n2 && j + n + 1 <= this.O.get(i) * n + n2; j += n) {
                Color color = new Color(this.A.get(j + 3).floatValue(), this.A.get(j + 4).floatValue(), this.A.get(j + 5).floatValue(), this.A.get(j + 6).floatValue());
                float f = this.A.get(j).floatValue();
                float f2 = this.A.get(j + 1).floatValue();
                float f3 = this.A.get(j + 2).floatValue();
                float f4 = this.A.get(j + 7).floatValue();
                float f5 = this.A.get(j + 8).floatValue();
                float f6 = this.A.get(j + 9).floatValue();
                BufferedRenderPrimitives.X(f, f2, f3, f4, f5, f6, 1.5f, color);
            }
        }
    }

    private List<IProjectile> p() {
        ArrayList<IProjectile> arrayList = new ArrayList<IProjectile>();
        if (this.k.L().booleanValue()) {
            arrayList.add(this.V);
        }
        if (this.c.L().booleanValue()) {
            arrayList.add(this.o);
        }
        if (this.j.L().booleanValue()) {
            arrayList.add(this.H);
        }
        if (this.P.L().booleanValue()) {
            arrayList.add(this.a);
        }
        if (this.S.L().booleanValue()) {
            arrayList.add(this.J);
        }
        return arrayList;
    }
}

