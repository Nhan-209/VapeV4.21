package gg.vape.module.render;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.render.TrajectoriesArrowProjectilePrimary;
import gg.vape.module.render.TrajectoriesArrowProjectileSecondary;
import gg.vape.module.render.TrajectoriesProjectileRenderState;
import gg.vape.module.render.proj.ArrowProjectile;
import gg.vape.module.render.proj.IProjectile;
import gg.vape.module.render.proj.PotionProjectile;
import gg.vape.module.render.proj.Projectile;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.render.BufferedRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.EnumHand;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.TrajectoriesItemBridge;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class Trajectories
extends Mod {
    private final Projectile I;
    private final ColorValue C;
    private final Projectile c;
    private final ArrayList<TrajectoriesProjectileRenderState> A;
    private final ArrowProjectile a;
    private final ArrowProjectile S;
    private final ColorValue k;
    private boolean r;
    private final RenderManager j;
    private final PotionProjectile H;
    private final ColorValue L = ColorValue.L(this, "Aiming Color", new Color(255, 255, 255));
    private Color s;
    private final BooleanValue V;
    private final Projectile Y;

    private void Y(double d, double d2, double d3, double d4, double d5, double d6, float f) {
        this.A.add(new TrajectoriesProjectileRenderState(d, d2, d3, d4, d5, d6, f, null));
    }

    public void s() {
        if (GuiRenderPrimitives.d()) {
            BufferedRenderPrimitives.X(-0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, this.s);
            BufferedRenderPrimitives.X(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.25f, 1.0f, this.s);
            BufferedRenderPrimitives.X(0.0f, 0.0f, -0.25f, 0.0f, 0.0f, 0.0f, 1.0f, this.s);
            BufferedRenderPrimitives.X(0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, this.s);
            BufferedRenderPrimitives.X(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 1.0f, this.s);
            BufferedRenderPrimitives.X(0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f, 1.0f, this.s);
        } else {
            GL11.glBegin((int)1);
            GL11.glVertex3d((double)-0.25, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)-0.25);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.25, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.25);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
            GL11.glEnd();
        }
    }

    public Trajectories() {
        super("Trajectories", -16535661, Category.k, "Shows a path of where your projectile will land\nTarget Color will only be used on the cross if there is an entity intersecting it");
        this.S = new TrajectoriesArrowProjectilePrimary(this);
        this.V = BooleanValue.create(this, "Ghost Bow Charge", false, "Draws a ghost trajectory of a fully charged\nshot when not pulling back bow");
        this.c = new Projectile(Collections.singleton(MappedClasses.l2), new Color(255, 238, 154));
        this.Y = new Projectile(Collections.singleton(MappedClasses.Zg), new Color(173, 12, 255));
        this.I = new Projectile(Collections.singleton(MappedClasses.YZ), new Color(255, 255, 255));
        this.H = new PotionProjectile();
        this.a = new TrajectoriesArrowProjectileSecondary(this);
        this.C = ColorValue.L(this, "Target Color", new Color(0, 0, 255, 255));
        this.k = ColorValue.L(this, "Trajectory Color", new Color(255, 0, 0, 255));
        this.A = new ArrayList();
        this.addValue(this.L, this.k, this.C, this.V);
        this.j = Minecraft.D();
        this.s = this.k.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
    }

    @Nullable
    private IProjectile n(Item item) {
        if (item.isInstance(MappedClasses.Vl)) {
            return this.S;
        }
        if (item.isInstance(MappedClasses.Vb)) {
            return this.I;
        }
        if (item.isInstance(MappedClasses.ZH)) {
            return this.Y;
        }
        if (item.isInstance(MappedClasses.YH)) {
            return this.c;
        }
        if (item.isInstance(MappedClasses.o)) {
            return this.H;
        }
        if (item.isInstance(MappedClasses.YA)) {
            return this.S;
        }
        return null;
    }

    private static boolean lambda$onRenderWorldLast$0(RayTraceResult rayTraceResult) {
        return rayTraceResult != null && rayTraceResult.isNotNull() && !RayTraceResult_type.miss().equals(rayTraceResult.getTypeOfHit());
    }

    private void U(@Nullable Color color) {
        if (GuiRenderPrimitives.d()) {
            for (TrajectoriesProjectileRenderState trajectoriesProjectileRenderState : this.A) {
                BufferedRenderPrimitives.Q(trajectoriesProjectileRenderState.X, trajectoriesProjectileRenderState.N, trajectoriesProjectileRenderState.f, trajectoriesProjectileRenderState.H, trajectoriesProjectileRenderState.x, trajectoriesProjectileRenderState.b, trajectoriesProjectileRenderState.q, color == null ? trajectoriesProjectileRenderState.E : color);
            }
        } else {
            for (TrajectoriesProjectileRenderState trajectoriesProjectileRenderState : this.A) {
                GL11.glVertex3d((double)trajectoriesProjectileRenderState.X, (double)trajectoriesProjectileRenderState.N, (double)trajectoriesProjectileRenderState.f);
            }
        }
        this.A.clear();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        double d;
        double d2;
        Wrapper wrapper;
        Wrapper wrapper2;
        Wrapper wrapper3;
        double d3;
        double d4;
        double d5;
        double d6;
        EntityPlayerSP entityPlayerSP = eventRender3D.getThePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        WorldClient worldClient = eventRender3D.getWorld();
        if (worldClient.isNull()) {
            return;
        }
        ItemStack itemStack = this.R(entityPlayerSP);
        if (itemStack == null || itemStack.isNull()) {
            return;
        }
        Item item = itemStack.getItem();
        if (item.isNull()) {
            return;
        }
        IProjectile iProjectile = this.n(item);
        if (iProjectile == null) {
            return;
        }
        boolean bl = iProjectile == this.S;
        boolean bl2 = item.isInstance(MappedClasses.YA);
        boolean bl3 = iProjectile == this.H;
        float f = entityPlayerSP.V() / 180.0f * (float)Math.PI;
        float f2 = entityPlayerSP.J() / 180.0f * (float)Math.PI;
        double d7 = bl ? 1.0 : 0.4;
        double d8 = d6 = ForgeVersion.MC_1_16_5.d() ? -1.6 : 0.0;
        if (ForgeVersion.MC_1_21_4.d()) {
            d5 = RenderManager.getInterpolatedRenderPosX();
            d4 = RenderManager.getInterpolatedRenderPosY() + (double)entityPlayerSP.X() + d6;
            d3 = RenderManager.getInterpolatedRenderPosZ();
        } else {
            d5 = RenderManager.getInterpolatedRenderPosX() - (double)(MathUtil.cos(f2) * 0.16f);
            d4 = RenderManager.getInterpolatedRenderPosY() + (double)entityPlayerSP.X() - (double)0.1f + d6;
            d3 = RenderManager.getInterpolatedRenderPosZ() - (double)(MathUtil.sin(f2) * 0.16f);
        }
        double d9 = (double)(-MathUtil.sin(f2) * MathUtil.cos(f)) * d7;
        double d10 = (double)(-MathUtil.sin(f)) * d7;
        double d11 = (double)(MathUtil.cos(f2) * MathUtil.cos(f)) * d7;
        boolean bl4 = false;
        int n = 40;
        if (bl2 && !this.V.L().booleanValue() && !TrajectoriesItemBridge.P(itemStack)) {
            return;
        }
        if (!(this.V.L().booleanValue() || entityPlayerSP.j$src$I$1in0s92() > 0 || !bl || bl2 && TrajectoriesItemBridge.P(itemStack))) {
            return;
        }
        if (entityPlayerSP.j$src$I$1in0s92() > 0 && bl || bl2 && TrajectoriesItemBridge.P(itemStack)) {
            n = entityPlayerSP.j$src$I$1in0s92();
            bl4 = true;
        }
        int n2 = 72000 - n;
        float f3 = (float)n2 / 20.0f;
        if ((double)(f3 = (f3 * f3 + f3 * 2.0f) / 3.0f) < 0.1) {
            return;
        }
        if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        RenderUtils.g();
        RenderUtil.d();
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().B(0.0);
        boolean bl5 = OpenGlBackendHolder.d.L(2929);
        boolean bl6 = OpenGlBackendHolder.d.L(3553);
        boolean bl7 = OpenGlBackendHolder.d.L(3042);
        if (bl5) {
            OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        }
        if (bl6) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        }
        OpenGlBackendHolder.d.l(2848);
        GL11.glBlendFunc((int)770, (int)771);
        if (!bl7) {
            OpenGlBackendHolder.d.l(3042);
        }
        float f4 = MathUtil.sqrt(d9 * d9 + d10 * d10 + d11 * d11);
        d9 /= (double)f4;
        d10 /= (double)f4;
        d11 /= (double)f4;
        double d12 = (double)(bl ? f3 * 2.0f : 1.0f) * 1.5;
        d9 *= d12;
        d10 *= d12;
        d11 *= d12;
        OpenGlBackendHolder.d.r(1.5f);
        OpenGlBackendHolder.d.C(3);
        boolean bl8 = false;
        RayTraceResult rayTraceResult = new RayTraceResult(null);
        float f5 = iProjectile.getCollisionRadius();
        float f6 = iProjectile.getCollisionHeight();
        float f7 = f5 / 2.0f;
        if (this.r) {
            RenderUtils.w(this.k.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            this.s = this.k.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
        } else {
            RenderUtils.w(this.L.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
            this.s = this.L.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
        }
        this.r = false;
        Predicate<RayTraceResult> predicate = Trajectories::lambda$onRenderWorldLast$0;
        double d13 = 0.0;
        double d14 = 0.0;
        double d15 = 0.0;
        boolean bl9 = false;
        while (!bl8) {
            wrapper3 = Vec3.create(d5, d4, d3);
            wrapper2 = Vec3.create(d5 + d9, d4 + d10, d3 + d11);
            rayTraceResult = Minecraft.theWorld().K((Vec3)wrapper3, (Vec3)wrapper2, false, bl, false, entityPlayerSP);
            wrapper3 = Vec3.create(d5, d4, d3);
            wrapper2 = Vec3.create(d5 + d9, d4 + d10, d3 + d11);
            if (predicate.test(rayTraceResult)) {
                bl8 = true;
                wrapper = rayTraceResult.getHitVec();
                wrapper2 = Vec3.create(((Vec3)wrapper).getX(), ((Vec3)wrapper).getY(), ((Vec3)wrapper).getZ());
            }
            wrapper = AxisAlignedBB.create(d5 - (double)f7, d4, d3 - (double)f7, d5 + (double)f7, d4 + (double)f6, d3 + (double)f7);
            d2 = 0.0;
            List list = worldClient.F(entityPlayerSP, ((AxisAlignedBB)wrapper).addCoord(d9, d10, d11).expand(1.0, 1.0, 1.0));
            for (Object e : list) {
                AxisAlignedBB axisAlignedBB;
                RayTraceResult rayTraceResult2;
                Entity entity = new Entity(e);
                boolean bl10 = ForgeVersion.MC_1_16_5.d() ? entity.isInstance(MappedClasses.zm) && !entity.isInstance(MappedClasses.uz) && !entity.equals(entityPlayerSP) : entity.n$src$Z$fx7gig() && !entity.equals(entityPlayerSP);
                boolean bl11 = bl10;
                if (!bl11 || !predicate.test(rayTraceResult2 = (axisAlignedBB = entity.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().expand(0.3, 0.3, 0.3)).calculateIntercept((Vec3)wrapper3, (Vec3)wrapper2))) continue;
                double d16 = ((Vec3)wrapper3).A(rayTraceResult2.getHitVec());
                if (d2 != 0.0 && !(d16 < d2)) continue;
                rayTraceResult2.setEntity(entity);
                d2 = d16;
                rayTraceResult = rayTraceResult2;
                bl8 = true;
                this.r = true;
            }
            d5 += d9;
            d4 += d10;
            d3 += d11;
            boolean bl12 = false;
            float f8 = 0.99f;
            float f9 = bl || bl3 ? 0.05f : 0.03f;
            d9 *= (double)f8;
            d10 *= (double)f8;
            d11 *= (double)f8;
            d10 -= (double)f9;
            if (GuiRenderPrimitives.d()) {
                double d17 = d5 - RenderManager.getInterpolatedRenderPosX();
                double d18 = d4 - RenderManager.getInterpolatedRenderPosY();
                d = d3 - RenderManager.getInterpolatedRenderPosZ();
                if (!bl9) {
                    d13 = d17;
                    d14 = d18;
                    d15 = d;
                    bl9 = true;
                    continue;
                }
                this.Y(d13, d14, d15, d17, d18, d, 1.5f);
                d13 = d17;
                d14 = d18;
                d15 = d;
            } else {
                this.T(d5 - RenderManager.getInterpolatedRenderPosX(), d4 - RenderManager.getInterpolatedRenderPosY(), d3 - RenderManager.getInterpolatedRenderPosZ());
            }
            if (!bl8 && !(d4 < -128.0) && !predicate.test(rayTraceResult)) continue;
            break;
        }
        this.U(this.s);
        OpenGlBackendHolder.d.M();
        OpenGlBackendHolder.d.I(d5 - RenderManager.getInterpolatedRenderPosX(), d4 - RenderManager.getInterpolatedRenderPosY(), d3 - RenderManager.getInterpolatedRenderPosZ());
        if (predicate.test(rayTraceResult)) {
            wrapper3 = rayTraceResult.getTypeOfHit();
            wrapper2 = rayTraceResult.getEntity();
            if (ForgeVersion.MC_1_16_5.v() || RayTraceResult_type.block().equals(wrapper3)) {
                wrapper = rayTraceResult.getSideHit();
                if (wrapper.isNotNull()) {
                    switch (((EnumFacing)wrapper).Y()) {
                        case 2: 
                        case 3: {
                            OpenGlBackendHolder.d.X(90.0f, 1.0f, 0.0f, 0.0f);
                            break;
                        }
                        case 4: 
                        case 5: {
                            OpenGlBackendHolder.d.X(90.0f, 0.0f, 0.0f, 1.0f);
                        }
                    }
                }
            } else if (wrapper2.isNotNull()) {
                double d19;
                wrapper = ((Entity)wrapper2).u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu().expand(0.3, 0.3, 0.3);
                d2 = rayTraceResult.getHitVec().getX();
                double d20 = rayTraceResult.getHitVec().getY();
                double d21 = rayTraceResult.getHitVec().getZ();
                double d22 = ((AxisAlignedBB)wrapper).getMinX();
                double d23 = ((AxisAlignedBB)wrapper).getMaxX();
                d = ((AxisAlignedBB)wrapper).getMinY();
                double d24 = ((AxisAlignedBB)wrapper).getMaxY();
                double d25 = ((AxisAlignedBB)wrapper).getMinZ();
                double d26 = ((AxisAlignedBB)wrapper).getMaxZ();
                double d27 = Math.abs(d2 - d22);
                double d28 = Math.abs(d2 - d23);
                double d29 = Math.min(d27, d28);
                double d30 = Math.abs(d21 - d25);
                double d31 = Math.abs(d21 - d26);
                double d32 = Math.min(d30, d31);
                double d33 = Math.abs(d20 - d);
                double d34 = Math.min(d33, d19 = Math.abs(d20 - d24));
                double d35 = Math.min(d34, Math.min(d29, d32));
                if (d35 == d29) {
                    OpenGlBackendHolder.d.X(90.0f, 0.0f, 0.0f, 1.0f);
                } else if (d35 == d32) {
                    OpenGlBackendHolder.d.X(90.0f, 1.0f, 0.0f, 0.0f);
                } else if (d35 == d34) {
                    // empty if block
                }
            }
            if (wrapper2.isNotNull()) {
                RenderUtils.w(this.C.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
                this.s = this.C.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            } else {
                RenderUtils.w(this.L.q$src$Lgg_vape_utils_MutableColor_$1dowyd3());
                this.s = this.L.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            }
        }
        this.s();
        OpenGlBackendHolder.d.u$src$V$hntn98(2929);
        if (!bl7) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        }
        OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        if (bl5) {
            OpenGlBackendHolder.d.l(2929);
        }
        if (bl6) {
            OpenGlBackendHolder.d.l(3553);
        }
        if (!bl7) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        }
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().O(0.0);
        RenderUtils.f();
        OpenGlBackendHolder.d.F();
    }

    private void t(double d, double d2, double d3, double d4, double d5, double d6, float f, Color color) {
        this.A.add(new TrajectoriesProjectileRenderState(d, d2, d3, d4, d5, d6, f, color, null));
    }

    @Nullable
    private ItemStack R(EntityPlayerSP entityPlayerSP) {
        if (ForgeVersion.MC_1_12_2.d()) {
            List<ItemStack> list = Arrays.asList(entityPlayerSP.i(EnumHand.M()), entityPlayerSP.i(EnumHand.p()));
            for (ItemStack itemStack : list) {
                IProjectile iProjectile;
                Item item;
                if (itemStack.isNull() || (item = itemStack.getItem()).isNull() || (iProjectile = this.n(item)) == null) continue;
                return itemStack;
            }
            return null;
        }
        return entityPlayerSP.getHeldItemHand();
    }

    private void T(double d, double d2, double d3) {
        this.A.add(new TrajectoriesProjectileRenderState(d, d2, d3, null));
    }
}

