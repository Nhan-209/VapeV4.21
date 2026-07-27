package gg.vape.module.render;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventRender2D;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.render.OffscreenRenderContext;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.MathUtil;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.FontRenderer;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class Arrows
extends Mod {
    private final BooleanValue O;
    private final NumberValue Y;
    private final ColorValue a = ColorValue.L(this, "Color", new Color(255, 0, 0));
    private static final long p = 6320396365828000318L;
    private final BooleanValue L;
    private final Map<Entity, double[]> j;

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        EntityPlayerSP entityPlayerSP = eventRender2D.getThePlayer();
        if (entityPlayerSP.isNull() || eventRender2D.getWorld().isNull()) {
            return;
        }
        int n = Minecraft.J();
        int n2 = Minecraft.h();
        float f = (float)n / 4.0f;
        float f2 = (float)n2 / 4.0f;
        GuiRenderPrimitives.u(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Color.WHITE);
        FontRenderer fontRenderer = eventRender2D.getFontRenderer();
        for (Entity entity : this.j.keySet()) {
            Color color;
            int n3;
            Object object;
            int n4;
            double[] dArray = this.j.get(entity);
            double d = dArray[0];
            double d2 = (double)Minecraft.h() - dArray[1];
            if (dArray[2] < 1.0 && this.isOnScreen(d / 2.0, d2 / 2.0, n, n2)) continue;
            float f3 = this.getAngle(f, d / 2.0, f2, d2 / 2.0) + (float)(dArray[2] > 1.0 ? 180 : 0);
            double d3 = (double)f * (Double)this.Y.K();
            double d4 = (double)f2 * (Double)this.Y.K();
            double d5 = Math.sqrt(1.0 / (1.0 / (d3 * d3) + Math.pow(Math.tan(Math.toRadians(f3)), 2.0) / (d4 * d4)));
            double d6 = Math.tan(Math.toRadians(f3)) * d5;
            float f4 = MathUtil.wrapAngleTo180(f3 + 90.0f);
            if (f4 < 0.0f) {
                d5 = -d5;
                if (f4 > -180.0f) {
                    d6 = -d6;
                }
            }
            if ((n4 = (int)((double)entityPlayerSP.getDistanceToEntity(entity) * 1.5)) > 255) {
                n4 = 255;
            }
            if (this.O.L().booleanValue() && n4 < 255) {
                OpenGlBackendHolder.d.m();
                RenderUtils.g();
                object = (int)entityPlayerSP.getDistanceToEntity(entity) + "m";
                OpenGlBackendHolder.d.I(d5 + (double)((float)n / 4.0f), d6 + (double)((float)n2 / 4.0f), 0.0);
                OpenGlBackendHolder.d.G(0.5, 0.5, 0.0);
                n3 = OpenGlBackendHolder.d.L(3042) ? 1 : 0;
                GlStateManager.enableBlend();
                color = new Color(255, 255, 255, 255 - (this.L.L() != false ? n4 : 0));
                if (GuiRenderPrimitives.d()) {
                    MatrixStack matrixStack = MatrixStack.A();
                    matrixStack.H();
                    float f5 = (float)Minecraft.p().k(Minecraft.gameSettings().T(), false) / 2.0f;
                    float f6 = 1.0f;
                    float f7 = 1.0f / f5;
                    d5 = Math.ceil(d5);
                    d6 = Math.ceil(d6);
                    d5 /= (double)f6;
                    d6 /= (double)f6;
                    matrixStack.S(f7, f7, f7);
                    matrixStack.S(f6, f6, f6);
                    matrixStack.i(BufferedGuiRenderPrimitives.X.c().u());
                    fontRenderer.V((String)object, (float)(-fontRenderer.getStringWidth((String)object)) / 2.0f, -fontRenderer.getHalfFontHeight((String)object), ColorUtil.n(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()), matrixStack);
                } else {
                    fontRenderer.drawStringWithShadow((String)object, (double)((float)(-fontRenderer.getStringWidth((String)object)) / 2.0f), -fontRenderer.getHalfFontHeight((String)object), ColorUtil.n(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
                }
                if (n3 == 0) {
                    GlStateManager.disableBlend();
                }
                RenderUtils.f();
                OpenGlBackendHolder.d.F();
            }
            OpenGlBackendHolder.d.m();
            RenderUtils.g();
            OpenGlBackendHolder.d.I(d5 + (double)((float)n / 4.0f), d6 + (double)((float)n2 / 4.0f), 0.0);
            OpenGlBackendHolder.d.X(f3 - 90.0f, 0.0f, 0.0f, 1.0f);
            OpenGlBackendHolder.d.G(0.375, 0.5, 0.0);
            object = this.a.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            n3 = ((Color)object).getAlpha();
            if (this.L.L().booleanValue()) {
                n3 = 255 - n4;
            }
            color = new Color(((Color)object).getRed(), ((Color)object).getGreen(), ((Color)object).getBlue(), n3);
            ImageRenderer.drawResWithShadow(color, -16.0f, 0.0f, "exo", 1.0f, false);
            RenderUtils.f();
            OpenGlBackendHolder.d.F();
        }
        this.j.clear();
    }


    private boolean isOnScreen(double d, double d2, int n, int n2) {
        return d > 0.0 && d2 > 0.0 && d < (double)(n / 2) && d2 < (double)(n2 / 2);
    }

    @EventHandler
    public void onRender3D(EventRender3D eventRender3D) {
        this.j.clear();
        RenderUtil.d();
        for (Object e : Minecraft.theWorld().z()) {
            Entity entity = new Entity(e);
            if (!this.L(entity)) continue;
            double d = entity.M() + (entity.z() - entity.M()) * (double)eventRender3D.getTicks() - RenderManager.getInterpolatedRenderPosX();
            double d2 = entity.W() + (entity.N() - entity.W()) * (double)eventRender3D.getTicks() - RenderManager.getInterpolatedRenderPosY();
            double d3 = entity.m$src$D$fwnne5() + (entity.h() - entity.m$src$D$fwnne5()) * (double)eventRender3D.getTicks() - RenderManager.getInterpolatedRenderPosZ();
            double[] dArray = RenderUtil.W(d, d2, d3);
            this.j.put(entity, dArray);
        }
        RenderUtil.Y();
    }

    public boolean L(Entity entity) {
        if (OffscreenRenderContext.W()) {
            return false;
        }
        if (!entity.isInstance(MappedClasses.Yl)) {
            return false;
        }
        if (entity.isInstance(MappedClasses.z5)) {
            return false;
        }
        EntityPlayer entityPlayer = new EntityPlayer(entity.getObject());
        if (Vape.INSTANCE.getClientSettings().J(entityPlayer)) {
            return false;
        }
        if (Vape.INSTANCE.getClientSettings().S(entityPlayer)) {
            return false;
        }
        return !Vape.INSTANCE.getFriendManager().E(entityPlayer.getName());
    }

    public Arrows() {
        super("Arrows", (int)p, Category.k, "Draws arrows on screen when entities\nare out of your field of view.");
        this.O = BooleanValue.create(this, "Show Distance", false, "Renders the distance next to the arrow.");
        this.L = BooleanValue.create(this, "Scale Opacity", false, "Lowers the opacity the farther they are.");
        this.Y = NumberValue.create((Object)this, "Radius Scale", "#.##", "x", 0.0, 0.5, 1.0, 0.05);
        this.j = new HashMap<Entity, double[]>();
        this.addValue(this.a, this.Y, this.O, this.L);
    }

    private float getAngle(double d, double d2, double d3, double d4) {
        return (float)Math.toDegrees(Math.atan2(d4 - d3, d2 - d));
    }
}

