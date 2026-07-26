package gg.vape.ui.click.component;

import func.skidline.RectData;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.GlStateManager;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class MarqueeTextRendererComponent
extends GuiComponent {
    private TimerUtil o = new TimerUtil();
    GuiComponent R;
    private double a = 0.0;

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void h(String string, double d, double d2, double d3, double d4, Color color) {
        this.j(string, d, d2, d3, d4, false, color, null);
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public void F() {
    }

    public void o(String string, double d, double d2, double d3, double d4, Color color, @Nullable Color color2) {
        this.j(string, d, d2, d3, d4, false, color, color2);
    }

    @Override
    public void H() {
    }

    public MarqueeTextRendererComponent(@Nullable GuiComponent guiComponent) {
        this.R = guiComponent;
    }

    @Override
    public void I() {
    }

    public void j(String string, double d, double d2, double d3, double d4, boolean bl, Color color, @Nullable Color color2) {
        Color color3;
        SmoothFontRenderer smoothFontRenderer = bl ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(d4) : this.O(d4);
        double d5 = smoothFontRenderer.N(string);
        double d6 = smoothFontRenderer.d(string);
        RectData rectData = new RectData(d, d2, d3, d6);
        if (this.R != null && this.R.w$src$Z$e457mb() && d5 > rectData.e()) {
            RenderUtils.m(rectData.o() - 1.0, rectData.W(), rectData.e() + 1.0, rectData.R());
            smoothFontRenderer.d(string + " " + string, rectData.o() - this.a, rectData.W(), color);
            RenderUtils.T();
            if (this.o.hasTimeElapsed(30L)) {
                this.a += 0.25;
                this.o.reset();
            }
            if (this.a >= d5 + smoothFontRenderer.N(" ")) {
                this.a = 0.0;
            }
        } else {
            this.a = 0.0;
            RenderUtils.m(rectData.o() - 1.0, rectData.W(), rectData.e() + 1.0, rectData.R());
            smoothFontRenderer.d(string, rectData.o() - this.a, rectData.W(), color);
            RenderUtils.T();
        }
        Color color4 = color3 = color2 == null ? new Color(0, 0, 0, 0) : color2;
        if (GuiRenderPrimitives.d()) {
            BufferedGuiRenderPrimitives.N(rectData.o() + 1.0, rectData.W(), rectData.o() + 1.0, rectData.W() + rectData.R(), rectData.o() - 3.0, rectData.W() + rectData.R(), rectData.o() - 3.0, rectData.W(), new Color(31, 30, 31, 0), color3);
            BufferedGuiRenderPrimitives.N(rectData.o() + rectData.e() - 3.0, rectData.W(), rectData.o() + rectData.e() - 3.0, rectData.W() + rectData.R(), rectData.o() + rectData.e(), rectData.W() + rectData.R(), rectData.o() + rectData.e(), rectData.W(), new Color(31, 30, 31, 0), color3);
        } else {
            boolean bl2 = GL11.glIsEnabled((int)3042);
            boolean bl3 = GL11.glIsEnabled((int)3553);
            boolean bl4 = GL11.glIsEnabled((int)2896);
            boolean bl5 = GL11.glIsEnabled((int)3008);
            boolean bl6 = GL11.glIsEnabled((int)2884);
            if (!bl2) {
                GlStateManager.enableBlend();
            }
            if (bl3) {
                GlStateManager.disableTexture2D();
            }
            if (bl4) {
                GlStateManager.disableLighting();
            }
            if (!bl5) {
                GlStateManager.enableAlpha();
            }
            if (bl6) {
                GlStateManager.Y();
            }
            GL11.glShadeModel((int)7425);
            RenderUtils.w(new Color(31, 30, 31, 0));
            GL11.glBegin((int)7);
            GL11.glVertex2d((double)(rectData.o() + 1.0), (double)rectData.W());
            GL11.glVertex2d((double)(rectData.o() + 1.0), (double)(rectData.W() + rectData.R()));
            RenderUtils.w(color3);
            GL11.glVertex2d((double)(rectData.o() - 3.0), (double)(rectData.W() + rectData.R()));
            GL11.glVertex2d((double)(rectData.o() - 3.0), (double)rectData.W());
            GL11.glEnd();
            RenderUtils.w(new Color(31, 30, 31, 0));
            GL11.glBegin((int)7);
            GL11.glVertex2d((double)(rectData.o() + rectData.e() - 3.0), (double)rectData.W());
            GL11.glVertex2d((double)(rectData.o() + rectData.e() - 3.0), (double)(rectData.W() + rectData.R()));
            RenderUtils.w(color3);
            GL11.glVertex2d((double)(rectData.o() + rectData.e()), (double)(rectData.W() + rectData.R()));
            GL11.glVertex2d((double)(rectData.o() + rectData.e()), (double)rectData.W());
            GL11.glEnd();
            GL11.glColor4d((double)1.0, (double)1.0, (double)1.0, (double)1.0);
            GL11.glShadeModel((int)7424);
            if (!bl5) {
                GlStateManager.disableAlpha();
            }
            if (bl4) {
                GlStateManager.enableLighting();
            }
            if (!bl2) {
                GlStateManager.disableBlend();
            }
            if (bl6) {
                GlStateManager.L();
            }
            if (bl3) {
                GlStateManager.enableTexture2D();
            }
        }
    }

    @Override
    public void u() {
    }
}

