package gg.vape.module.render;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRender2D;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.render.proj.ArrowProjectile;
import gg.vape.module.render.proj.EnderPearlProjectileBridge;
import gg.vape.module.render.proj.IProjectile;
import gg.vape.module.render.proj.PotionProjectile;
import gg.vape.module.render.proj.Projectile;
import gg.vape.render.OffscreenRenderContext;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ColorUtil;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.MathUtil;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.ItemStackRenderUtils;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityArrow;
import gg.vape.wrapper.impl.EntityEnderPearl;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.FontRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameProfile;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Indicators
extends Mod {
    private final BooleanValue H;
    private static final long ib = 68822152240924204L;
    private final BooleanValue j;
    private ItemStack J;
    private final ArrowProjectile F = new ArrowProjectile();
    private final ModeOption b;
    private final BooleanValue O;
    private final Map<Entity, double[]> I;
    private final BooleanValue r;
    private EntityOtherPlayerMP a;
    private final BooleanValue S;
    private ItemStack Y;
    private final ModeOption D;
    private final PotionProjectile c = new PotionProjectile();
    private final ColorValue V;
    private ItemStack o;
    private final BooleanValue A;
    private final ModeValue k;
    private final NumberValue p;
    private ItemStack v;
    private final Projectile P;
    private final Projectile K = new Projectile(Collections.singleton(MappedClasses.Zg), new Color(173, 12, 255));
    private ItemStack U;
    private ItemStack t;
    private final Projectile C = new Projectile(Collections.singleton(MappedClasses.l2), new Color(255, 238, 154));
    private final Projectile s = new Projectile(Collections.singleton(MappedClasses.YZ), new Color(255, 255, 255));
    private final BooleanValue L;
    private final ModeOption Z;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private IProjectile B(EntityEnderPearl entityEnderPearl) {
        if (entityEnderPearl.b$src$Z$fqlxe4()) {
            return null;
        }
        if (!entityEnderPearl.isInstance(MappedClasses.qZ) && entityEnderPearl.z() == entityEnderPearl.M() && entityEnderPearl.h() == entityEnderPearl.m$src$D$fwnne5()) {
            return null;
        }
        for (IProjectile iProjectile : this.g$src$Ljava_util_List_$yd260m()) {
            if (!iProjectile.matches(entityEnderPearl)) continue;
            return iProjectile;
        }
        return null;
    }

    public boolean P(EntityPlayerSP entityPlayerSP, WorldClient worldClient, Entity entity) {
        EnderPearlProjectileBridge enderPearlProjectileBridge;
        IProjectile iProjectile;
        if (OffscreenRenderContext.W()) {
            return false;
        }
        if (entity.isInstance(MappedClasses.qZ) && this.r.L().booleanValue()) {
            if (entityPlayerSP.getDistanceToEntity(entity) > 1000.0f) {
                return false;
            }
            double d = this.T(entityPlayerSP, worldClient, new EntityArrow(entity.getObject()));
            if (this.k.K() == this.D) {
                return d == 0.0;
            }
            return d != -1.0;
        }
        if (entity.isInstance(MappedClasses.lv) && (iProjectile = this.B(enderPearlProjectileBridge = new EnderPearlProjectileBridge(entity.getObject()))) != null) {
            double d = this.G(entityPlayerSP, worldClient, enderPearlProjectileBridge, iProjectile);
            if (this.k.K() == this.D) {
                return d == 0.0;
            }
            return d != -1.0;
        }
        return false;
    }

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        EntityPlayerSP entityPlayerSP = eventRender2D.getThePlayer();
        if (entityPlayerSP.isNull() || eventRender2D.getWorld().isNull()) {
            return;
        }
        FontRenderer fontRenderer = eventRender2D.getFontRenderer();
        float f = (float)eventRender2D.getDisplayWidth() / 2.0f;
        float f2 = (float)eventRender2D.getDisplayHeight() / 2.0f;
        GuiRenderPrimitives.u(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Color.WHITE);
        for (Entity entity : this.I.keySet()) {
            Color color;
            Object object;
            ItemStack itemStack;
            Object object2;
            boolean bl = this.k.K() == this.D;
            double d = 0.0;
            double d2 = 0.0;
            if (!bl) {
                if (entity.isInstance(MappedClasses.lv)) {
                    object2 = new EnderPearlProjectileBridge(entity.getObject());
                    IProjectile iProjectile = this.B((EntityEnderPearl)object2);
                    if (iProjectile != null) {
                        d = this.G(entityPlayerSP, eventRender2D.getWorld(), (EntityEnderPearl)object2, iProjectile);
                        bl = d == 0.0;
                        d2 = 5.0;
                    }
                } else if (entity.isInstance(MappedClasses.qZ)) {
                    d = this.T(entityPlayerSP, eventRender2D.getWorld(), new EntityArrow(entity.getObject()));
                    bl = d < 3.0;
                    d2 = 12.0;
                }
            }
            if (this.k.K() == this.b && d > d2) continue;
            double[] dArray = (double[])this.I.get(entity);
            double d8 = dArray[0];
            double d3 = (double)Minecraft.h() - dArray[1];
            if (dArray[2] < 1.0 && this.d(d8 / 2.0, d3 / 2.0, eventRender2D.getDisplayWidth(), eventRender2D.getDisplayHeight())) continue;
            float f3 = this.i(f, d8 / 2.0, f2, d3 / 2.0) + (float)(dArray[2] > 1.0 ? 180 : 0);
            double d4 = (double)f * (Double)this.p.K();
            double d5 = (double)f2 * (Double)this.p.K();
            double d6 = Math.sqrt(1.0 / (1.0 / (d4 * d4) + Math.pow(Math.tan(Math.toRadians(f3)), 2.0) / (d5 * d5)));
            double d7 = Math.tan(Math.toRadians(f3)) * d6;
            float f4 = MathUtil.wrapAngleTo180(f3 + 90.0f);
            if (f4 < 0.0f) {
                d6 = -d6;
                if (f4 > -180.0f) {
                    d7 = -d7;
                }
            }
            int n = 0;
            if (!bl && this.k.K() == this.b) {
                n = (int)(entityPlayerSP.getDistanceToEntity(entity) * 2.0f);
            }
            if (n > 200) {
                n = 200;
            }
            if ((itemStack = this.Y(entity)) == null || itemStack.isNull()) {
                return;
            }
            OpenGlBackendHolder.d.m();
            OpenGlBackendHolder.d.I(d6 + (double)((float)eventRender2D.getDisplayWidth() / 4.0f), d7 + (double)((float)eventRender2D.getDisplayHeight() / 4.0f), 0.0);
            if (bl) {
                OpenGlBackendHolder.d.G(1.5, 1.5, 1.0);
            }
            OpenGlBackendHolder.d.m();
            if (GuiRenderPrimitives.d()) {
                OpenGlBackendHolder.d.I(d6 + (double)((float)eventRender2D.getDisplayWidth() / 4.0f), d7 + (double)((float)eventRender2D.getDisplayHeight() / 4.0f), 0.0);
                if (bl) {
                    OpenGlBackendHolder.d.G(1.5, 1.5, 1.0);
                }
            }
            OpenGlBackendHolder.d.X(f3 - 90.0f, 0.0f, 0.0f, 1.0f);
            ItemStackRenderUtils.g(itemStack, -8, -20);
            OpenGlBackendHolder.d.F();
            if (this.O.L().booleanValue() && n < 200) {
                OpenGlBackendHolder.d.m();
                if (GuiRenderPrimitives.d()) {
                    OpenGlBackendHolder.d.I(d6 + (double)((float)eventRender2D.getDisplayWidth() / 4.0f), d7 + (double)((float)eventRender2D.getDisplayHeight() / 4.0f), 0.0);
                    if (bl) {
                        OpenGlBackendHolder.d.G(1.5, 1.5, 1.0);
                    }
                }
                object = (bl ? "\u00a7l" : "") + (int)entityPlayerSP.getDistanceToEntity(entity) + "m";
                OpenGlBackendHolder.d.G(0.5, 0.5, 0.0);
                GlStateManager.enableBlend();
                Color color2 = color = bl ? new Color(255, 0, 0) : new Color(255, 255, 255, 255 - n);
                if (GuiRenderPrimitives.d()) {
                    MatrixStack matrixStack = MatrixStack.A();
                    matrixStack.H();
                    matrixStack.i(BufferedGuiRenderPrimitives.X.c().u());
                    fontRenderer.V((String)object, (float)(-fontRenderer.getStringWidth((String)object)) / 2.0f, -fontRenderer.getHalfFontHeight((String)object), ColorUtil.n(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()), matrixStack);
                } else {
                    fontRenderer.drawStringWithShadow((String)object, (double)((float)(-fontRenderer.getStringWidth((String)object)) / 2.0f), -fontRenderer.getHalfFontHeight((String)object), ColorUtil.n(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
                }
                GlStateManager.disableBlend();
                OpenGlBackendHolder.d.F();
            }
            OpenGlBackendHolder.d.X(f3 - 90.0f, 0.0f, 0.0f, 1.0f);
            OpenGlBackendHolder.d.G(0.375, 0.5, 0.0);
            object = this.P(entity);
            color = new Color(((Color)object).getRed(), ((Color)object).getGreen(), ((Color)object).getBlue(), 255 - n);
            ImageRenderer.drawResWithShadow(color, -16.0f, 0.0f, "exo", 1.0f, false);
            OpenGlBackendHolder.d.F();
        }
        this.I.clear();
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        if (this.a == null) {
            this.a = EntityOtherPlayerMP.create(eventRender3D.getWorld(), GameProfile.create(UUID.randomUUID(), "nig"));
        }
        this.I.clear();
        RenderUtil.d();
        for (Object e : eventRender3D.getWorld().z()) {
            Entity entity = new Entity(e);
            if (!this.P(eventRender3D.getThePlayer(), eventRender3D.getWorld(), entity)) continue;
            double d = entity.M() + (entity.z() - entity.M()) * (double)eventRender3D.getTicks() - RenderManager.getInterpolatedRenderPosX();
            double d2 = entity.W() + (entity.N() - entity.W()) * (double)eventRender3D.getTicks() - RenderManager.getInterpolatedRenderPosY();
            double d3 = entity.m$src$D$fwnne5() + (entity.h() - entity.m$src$D$fwnne5()) * (double)eventRender3D.getTicks() - RenderManager.getInterpolatedRenderPosZ();
            double[] dArray = RenderUtil.W(d, d2, d3);
            this.I.put(entity, dArray);
        }
        RenderUtil.Y();
    }

    private float i(double d, double d2, double d3, double d4) {
        return (float)Math.toDegrees(Math.atan2(d4 - d3, d2 - d));
    }

    private double G(EntityPlayerSP entityPlayerSP, WorldClient worldClient, EntityEnderPearl entityEnderPearl, IProjectile iProjectile) {
        if (!entityEnderPearl.isInstance(MappedClasses.lv)) {
            return -1.0;
        }
        double d = entityEnderPearl.z();
        double d2 = entityEnderPearl.N();
        double d3 = entityEnderPearl.h();
        double d4 = entityEnderPearl.t();
        double d5 = entityEnderPearl.q();
        double d6 = entityEnderPearl.T();
        double d7 = 1000.0;
        while (true) {
            Vec3 vec3;
            float f = iProjectile.getCollisionRadius();
            float f2 = iProjectile.getCollisionHeight();
            AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(d - (double)f, d2, d3 - (double)f, d + (double)f, d2 + (double)f2, d3 + (double)f);
            Vec3 vec32 = Vec3.create(d, d2, d3);
            RayTraceResult rayTraceResult = worldClient.K(vec32, vec3 = Vec3.create(d + d4, d2 + d5, d3 + d6), false, entityEnderPearl.isInstance(MappedClasses.F), false, entityEnderPearl);
            if (rayTraceResult.isNotNull()) {
                vec3 = Vec3.create(rayTraceResult.getHitVec().getX(), rayTraceResult.getHitVec().getY(), rayTraceResult.getHitVec().getZ());
            }
            List list = worldClient.F(this.a, axisAlignedBB.addCoord(d4, d5, d6).expand(1.0, 1.0, 1.0));
            double d8 = 0.0;
            for (Object e : list) {
                double d9;
                Entity entity = new Entity(e);
                if (!entity.isInstance(MappedClasses.zm) || entity.isInstance(MappedClasses.uz) || !entity.n$src$Z$fx7gig() || !entity.equals(entityPlayerSP)) continue;
                axisAlignedBB = entity.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().expand(0.3, 0.3, 0.3);
                RayTraceResult rayTraceResult2 = axisAlignedBB.calculateIntercept(vec32, vec3);
                if (ForgeVersion.MC_1_16_5.d()) {
                    if (!rayTraceResult2.isNotNull() || !rayTraceResult2.getTypeOfHit().equals(RayTraceResult_type.miss()) || !((d9 = vec32.distanceTo(rayTraceResult2.getHitVec())) < d8) && d8 != 0.0) continue;
                    d8 = d9;
                    rayTraceResult2.setEntity(entity);
                    rayTraceResult = rayTraceResult2;
                    continue;
                }
                if (!rayTraceResult2.isNotNull() || !((d9 = vec32.distanceTo(rayTraceResult2.getHitVec())) < d8) && d8 != 0.0) continue;
                d8 = d9;
                rayTraceResult2.setEntity(entity);
                rayTraceResult = rayTraceResult2;
            }
            double d10 = entityPlayerSP.i(d += d4, d2 += d5, d3 += d6);
            if (d10 < d7) {
                d7 = d10;
            }
            if (rayTraceResult.isNotNull()) {
                d = rayTraceResult.getHitVec().getX();
                d2 = rayTraceResult.getHitVec().getY();
                d3 = rayTraceResult.getHitVec().getZ();
                if (!rayTraceResult.getEntity().isNotNull()) break;
                return rayTraceResult.getEntity().isInstance(MappedClasses.z5) ? 0.0 : entityPlayerSP.i(d, d2, d3);
            }
            if (d2 < -128.0) break;
            d4 *= entityEnderPearl.h$src$Z$ftwoya() ? 0.8 : 0.99;
            d5 *= entityEnderPearl.h$src$Z$ftwoya() ? 0.8 : 0.99;
            d6 *= entityEnderPearl.h$src$Z$ftwoya() ? 0.8 : 0.99;
            d5 -= 0.05;
        }
        return d7;
    }

    private List<IProjectile> g$src$Ljava_util_List_$yd260m() {
        ArrayList<IProjectile> arrayList = new ArrayList<IProjectile>();
        if (this.j.L().booleanValue()) {
            arrayList.add(this.F);
        }
        if (this.L.L().booleanValue()) {
            arrayList.add(this.c);
        }
        if (this.S.L().booleanValue()) {
            arrayList.add(this.K);
        }
        if (this.H.L().booleanValue()) {
            arrayList.add(this.C);
        }
        if (this.A.L().booleanValue()) {
            arrayList.add(this.s);
        }
        if (this.r.L().booleanValue()) {
            arrayList.add(this.P);
        }
        return arrayList;
    }

    public ItemStack Y(Entity entity) {
        this.U = null;
        if (this.U == null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                this.U = ItemStack.S(Item.L("minecraft:arrow"));
                this.o = ItemStack.S(Item.L("minecraft:ender_pearl"));
                this.Y = ItemStack.S(Item.L("minecraft:potion"));
                this.J = ItemStack.S(Item.L("minecraft:egg"));
                this.v = ItemStack.S(Item.L("minecraft:snowball"));
                this.t = ItemStack.S(Item.L("minecraft:fire_charge"));
            } else {
                this.U = ItemStack.S(Item.T(262));
                this.o = ItemStack.S(Item.T(368));
                this.Y = ItemStack.S(Item.T(438));
                this.J = ItemStack.S(Item.T(344));
                this.v = ItemStack.S(Item.T(332));
                this.t = ItemStack.S(Item.T(385));
            }
        }
        if (entity.isInstance(MappedClasses.F)) {
            return this.U;
        }
        if (entity.isInstance(MappedClasses.Zg)) {
            return this.o;
        }
        if (entity.isInstance(MappedClasses.Zf)) {
            return this.Y;
        }
        if (entity.isInstance(MappedClasses.l2)) {
            return this.J;
        }
        if (entity.isInstance(MappedClasses.YZ)) {
            return this.v;
        }
        return this.t;
    }

    private boolean d(double d, double d2, int n, int n2) {
        return d > 0.0 && d2 > 0.0 && d < (double)(n / 2) && d2 < (double)(n2 / 2);
    }

    private double T(EntityPlayerSP entityPlayerSP, WorldClient worldClient, EntityArrow entityArrow) {
        if (!entityArrow.isInstance(MappedClasses.qZ)) {
            return -1.0;
        }
        double d = entityArrow.z();
        double d2 = entityArrow.N();
        double d3 = entityArrow.h();
        double d4 = entityArrow.t();
        double d5 = entityArrow.q();
        double d6 = entityArrow.T();
        double d7 = 1000.0;
        for (int i = 0; i < 100; ++i) {
            float f = entityArrow.Y();
            float f2 = entityArrow.f$src$F$fst3ac();
            AxisAlignedBB axisAlignedBB = AxisAlignedBB.create(d - (double)f, d2, d3 - (double)f, d + (double)f, d2 + (double)f2, d3 + (double)f);
            Vec3 vec3 = Vec3.create(d, d2, d3);
            Vec3 vec32 = Vec3.create(d + d4, d2 + d5, d3 + d6);
            RayTraceResult rayTraceResult = worldClient.K(vec3, vec32, false, true, false, entityArrow);
            if (ForgeVersion.MC_1_16_5.d()) {
                if (rayTraceResult.isNotNull() && !rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.miss())) {
                    vec32 = Vec3.create(rayTraceResult.getHitVec().getX(), rayTraceResult.getHitVec().getY(), rayTraceResult.getHitVec().getZ());
                }
            } else if (rayTraceResult.isNotNull()) {
                vec32 = Vec3.create(rayTraceResult.getHitVec().getX(), rayTraceResult.getHitVec().getY(), rayTraceResult.getHitVec().getZ());
            }
            List list = worldClient.F(this.a, axisAlignedBB.addCoord(d4, d5, d6).expand(1.0, 1.0, 1.0));
            double d8 = 0.0;
            for (Object e : list) {
                double d9;
                Entity entity = new Entity(e);
                if (!entity.isInstance(MappedClasses.zm) || entity.isInstance(MappedClasses.uz) || !entity.n$src$Z$fx7gig() || !entity.equals(entityPlayerSP)) continue;
                axisAlignedBB = entity.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().expand(0.3, 0.3, 0.3);
                RayTraceResult rayTraceResult2 = axisAlignedBB.calculateIntercept(vec3, vec32);
                if (ForgeVersion.MC_1_16_5.d()) {
                    if (!rayTraceResult2.isNotNull() || !rayTraceResult2.getTypeOfHit().equals(RayTraceResult_type.miss()) || !((d9 = vec3.distanceTo(rayTraceResult2.getHitVec())) < d8) && d8 != 0.0) continue;
                    d8 = d9;
                    rayTraceResult2.setEntity(entity);
                    rayTraceResult = rayTraceResult2;
                    continue;
                }
                if (!rayTraceResult2.isNotNull() || !((d9 = vec3.distanceTo(rayTraceResult2.getHitVec())) < d8) && d8 != 0.0) continue;
                d8 = d9;
                rayTraceResult2.setEntity(entity);
                rayTraceResult = rayTraceResult2;
            }
            double d10 = entityPlayerSP.i(d += d4, d2 += d5, d3 += d6);
            if (d10 < d7) {
                d7 = d10;
            }
            if (!rayTraceResult.isNotNull()) continue;
            d = rayTraceResult.getHitVec().getX();
            d2 = rayTraceResult.getHitVec().getY();
            d3 = rayTraceResult.getHitVec().getZ();
            if (!rayTraceResult.getEntity().isNotNull()) break;
            return rayTraceResult.getEntity().isInstance(MappedClasses.z5) ? 0.0 : entityPlayerSP.i(d, d2, d3);
        }
        return d7;
    }

    private Color P(Entity entity) {
        if (entity.isInstance(MappedClasses.lv)) {
            EnderPearlProjectileBridge enderPearlProjectileBridge = new EnderPearlProjectileBridge(entity.getObject());
            Color color = this.B(enderPearlProjectileBridge).getColor(enderPearlProjectileBridge.getObject());
            if (color == null) {
                color = new Color(255, 255, 255);
            }
            return color;
        }
        if (entity.isInstance(MappedClasses.uf)) {
            return new Color(255, 109, 0);
        }
        return this.V.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
    }

    public Indicators() {
        super("Indicators", (int)ib, Category.k, "Draws arrows on screen when projectiles\nare nearby/hitting you.");
        this.P = new Projectile(Collections.singleton(MappedClasses.qZ), new Color(255, 0, 0));
        this.Z = new ModeOption("Always");
        this.b = new ModeOption("Threat");
        this.D = new ModeOption("Hit Only");
        this.k = ModeValue.create((Object)this, "Alert Type", this.b, this.Z, this.b, this.D);
        this.V = ColorValue.L(this, "Uncommon Projectile Color", new Color(255, 0, 0));
        this.O = BooleanValue.create(this, "Show Distance", false, "Renders the distance next to the arrow.");
        this.p = NumberValue.create((Object)this, "Radius Scale", "#.##", "x", 0.0, 0.15, 1.0, 0.05);
        this.j = BooleanValue.create(this, "Show Arrows", true);
        this.S = BooleanValue.create(this, "Show Pearls", false);
        this.L = BooleanValue.create(this, "Show Potions", false);
        this.H = BooleanValue.create(this, "Show Eggs", true);
        this.A = BooleanValue.create(this, "Show Snowballs", true);
        this.r = BooleanValue.create(this, "Show Fireballs", true);
        this.I = new HashMap<Entity, double[]>();
        this.addValue(this.k, this.V, this.j, this.S, this.L, this.H, this.A, this.r, this.p, this.O);
    }
}
