package gg.vape.ui.click.component;

import func.skidline.RectData;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.text.TextTruncationIndexCache;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.GlStateManager;
import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class FadingTruncatedTextComponent
extends TruncatedTextComponent {
    private Color i;

    @Override
    public double u$src$D$ivbecn() {
        SmoothFontRenderer smoothFontRenderer;
        int n = TextTruncationIndexCache.J.d(this.I);
        SmoothFontRenderer smoothFontRenderer2 = smoothFontRenderer = this.I.q() ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.I.N()) : this.O(this.I.N());
        if (n >= 0) {
            return smoothFontRenderer.N(this.S$src$Ljava_lang_String_$1bp7ddx().substring(0, n));
        }
        return 0.0;
    }

    public FadingTruncatedTextComponent(String string, double d, double d2, Color color, Color color2, boolean bl, boolean bl2) {
        super(string, "", d, d2, color, bl, bl2);
        this.i = color2;
    }

    public Color J$src$Ljava_awt_Color_$1ku31x1() {
        return this.i;
    }

    @Override
    public void V(double d, double d2) {
        String string;
        String string2;
        StringBuilder stringBuilder;
        SmoothFontRenderer smoothFontRenderer;
        int n = TextTruncationIndexCache.J.d(this.I);
        if (n == this.S$src$Ljava_lang_String_$1bp7ddx().length() - 1) {
            SmoothFontRenderer smoothFontRenderer2 = smoothFontRenderer = this.I.q() ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.I.N()) : this.O(this.I.N());
            if (this.G) {
                smoothFontRenderer.v(this.S$src$Ljava_lang_String_$1bp7ddx(), d, d2, this.o);
            } else {
                smoothFontRenderer.d(this.S$src$Ljava_lang_String_$1bp7ddx(), d, d2, this.o);
            }
        } else if (n >= 0) {
            smoothFontRenderer = this.I.q() ? this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(this.I.N()) : this.O(this.I.N());
            String string3 = this.S$src$Ljava_lang_String_$1bp7ddx().substring(0, n);
            double d3 = smoothFontRenderer.N(string3);
            double d4 = smoothFontRenderer.d(string3);
            RectData rectData = new RectData(d, d2, this.v(), d4);
            RenderUtils.m(rectData.o() - 1.0, rectData.W(), rectData.e() + 1.0, rectData.R());
            if (this.G) {
                smoothFontRenderer.v(string3, rectData.o(), rectData.W(), this.o);
            } else {
                smoothFontRenderer.d(string3, rectData.o(), rectData.W(), this.o);
            }
            RenderUtils.T();
            if (GuiRenderPrimitives.d()) {
                MutableColor mutableColor = new MutableColor(this.i);
                mutableColor.withAlpha(0);
                BufferedGuiRenderPrimitives.N(rectData.o() + rectData.e() - 6.0, rectData.W(), rectData.o() + rectData.e() - 6.0, rectData.W() + rectData.R(), rectData.o() + rectData.e(), rectData.W() + rectData.R(), rectData.o() + rectData.e(), rectData.W(), mutableColor, this.i == null ? new Color(0, 0, 0, 0) : this.i);
            } else {
                boolean bl = GL11.glIsEnabled((int)3042);
                boolean bl2 = GL11.glIsEnabled((int)3553);
                boolean bl3 = GL11.glIsEnabled((int)2896);
                boolean bl4 = GL11.glIsEnabled((int)3008);
                boolean bl5 = GL11.glIsEnabled((int)2884);
                if (!bl) {
                    GlStateManager.enableBlend();
                }
                if (bl2) {
                    GlStateManager.disableTexture2D();
                }
                if (bl3) {
                    GlStateManager.disableLighting();
                }
                if (!bl4) {
                    GlStateManager.enableAlpha();
                }
                if (bl5) {
                    GlStateManager.Y();
                }
                GL11.glShadeModel((int)7425);
                MutableColor mutableColor = new MutableColor(this.i);
                mutableColor.withAlpha(0);
                RenderUtils.w(mutableColor);
                GL11.glBegin((int)7);
                GL11.glVertex2d((double)(rectData.o() + rectData.e() - 6.0), (double)rectData.W());
                GL11.glVertex2d((double)(rectData.o() + rectData.e() - 6.0), (double)(rectData.W() + rectData.R()));
                RenderUtils.w(this.i == null ? new Color(0, 0, 0, 0) : this.i);
                GL11.glVertex2d((double)(rectData.o() + rectData.e()), (double)(rectData.W() + rectData.R()));
                GL11.glVertex2d((double)(rectData.o() + rectData.e()), (double)rectData.W());
                GL11.glEnd();
                GL11.glColor4d((double)1.0, (double)1.0, (double)1.0, (double)1.0);
                GL11.glShadeModel((int)7424);
                if (!bl4) {
                    GlStateManager.disableAlpha();
                }
                if (bl3) {
                    GlStateManager.enableLighting();
                }
                if (!bl) {
                    GlStateManager.disableBlend();
                }
                if (bl5) {
                    GlStateManager.L();
                }
                if (bl2) {
                    GlStateManager.enableTexture2D();
                }
            }
        }
        boolean bl = n < this.I.g().length() - 1;
        boolean bl6 = !this.a.equals("");
        StringBuilder stringBuilder2 = new StringBuilder();
        if (bl) {
            stringBuilder = stringBuilder2;
            string2 = this.I.g();
        } else {
            stringBuilder = stringBuilder2;
            string2 = "";
        }
        StringBuilder stringBuilder3 = stringBuilder.append(string2);
        if (bl6) {
            StringBuilder stringBuilder4 = stringBuilder3;
            if (bl) {
                stringBuilder3 = stringBuilder4;
                string = "\n" + this.a;
            } else {
                stringBuilder3 = stringBuilder4;
                string = this.a;
            }
        } else {
            string = "";
        }
        String string4 = stringBuilder3.append(string).toString();
        this.w(string4);
    }

    public void C(Color color) {
        this.i = color;
    }

}

