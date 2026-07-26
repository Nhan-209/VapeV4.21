package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.unmap.ImageParser$Format;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtils;
import gg.vape.utils.render.TextureAtlas;
import gg.vape.utils.render.TextureAtlasRegion;
import gg.vape.utils.render.TextureAtlasRegistry;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import org.lwjgl.opengl.GL11;

public class ImageRenderer {
    public static HashMap<String, GlImageTexture> f;
    private static boolean u;
    private static boolean y;
    private static boolean J;
    private static GuiComponent[] k;

    public static double j(String string) {
        if (GuiRenderPrimitives.d()) {
            TextureAtlasRegion textureAtlasRegion;
            TextureAtlas textureAtlas = TextureAtlasRegistry.w().m("vape_texture");
            if (textureAtlas.B(string) == null) {
                ImageRenderer.r(string);
            }
            return (textureAtlasRegion = textureAtlas.B(string)) != null ? (double)textureAtlasRegion.g : 0.0;
        }
        GlImageTexture glImageTexture = ImageRenderer.r(string);
        return glImageTexture != null ? (double)glImageTexture.N : 0.0;
    }

    private static GlImageTexture r(String string) {
        return ImageRenderer.loadResource(string, false, false);
    }

    public static void drawRes(Color color, float f, float f2, String string, float f3) {
        ImageRenderer.drawResWithShadow(color, f, f2, string, f3, true);
    }

    public static void e() {
        y = true;
        OpenGlBackendHolder.d.m();
        J = OpenGlBackendHolder.d.L(3042);
        u = OpenGlBackendHolder.d.L(3553);
        if (!J) {
            OpenGlBackendHolder.d.l(3042);
        }
        if (!u) {
            OpenGlBackendHolder.d.l(3553);
        }
    }

    public static void m() {
        RenderUtils.w(Color.white);
        if (!J) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3042);
        }
        if (!u) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3553);
        }
        OpenGlBackendHolder.d.F();
        y = false;
    }

    public static GlImageTexture loadResource(String string, boolean bl, boolean bl2) {
        TextureAtlas textureAtlas;
        TextureAtlas textureAtlas2 = textureAtlas = GuiRenderPrimitives.d() ? TextureAtlasRegistry.w().r() : null;
        if (textureAtlas != null) {
            TextureAtlasRegion textureAtlasRegion = textureAtlas.B(string);
            if (textureAtlasRegion != null) {
                return ImageRenderer.v(textureAtlas, textureAtlasRegion);
            }
        } else {
            GlImageTexture cachedTexture = f.get(string);
            if (cachedTexture != null) {
                return cachedTexture;
            }
        }
        try {
            String string2 = "textures/" + string + ".png";
            byte[] resourceData = Vape.readResource(string2);
            if (resourceData == null || resourceData.length == 0) {
                GlImageTexture glImageTexture = ImageRenderer.r("world");
                f.put(string, glImageTexture);
                return glImageTexture;
            }
            if (textureAtlas != null) {
                textureAtlas.P(string, resourceData, bl2);
                return ImageRenderer.v(textureAtlas, textureAtlas.B(string));
            }
            GlImageTexture glImageTexture = new GlImageTexture(new ByteArrayInputStream(resourceData), bl ? 9987 : 9729, bl2 ? ImageParser$Format.WHITE : ImageParser$Format.RGBA);
            f.put(string, glImageTexture);
            return glImageTexture;
        }
        catch (IOException iOException) {
            Vape.logThrowable(iOException);
            return null;
        }
    }

    static {
        ImageRenderer.E(new GuiComponent[5]);
        f = new HashMap();
        J = false;
        u = false;
    }

    public static String xorString(String string, int n) {
        String string2 = "";
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            string2 = string2 + String.valueOf((char)(c ^ n));
        }
        return string2;
    }

    public static void n(GlImageTexture glImageTexture, float f, float f2, float f3, float f4, Color color, Color color2) {
        float f5 = (float)glImageTexture.l / (float)glImageTexture.N;
        f3 *= f5;
        glImageTexture.F();
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 1.0f;
        float f9 = 1.0f;
        GL11.glShadeModel((int)7425);
        GL11.glBegin((int)7);
        GL11.glColor4d((double)((double)color.getRed() / 255.0), (double)((double)color.getGreen() / 255.0), (double)((double)color.getBlue() / 255.0), (double)((double)color.getAlpha() / 255.0));
        GL11.glTexCoord2f((float)f8, (float)f7);
        GL11.glVertex2f((float)(f + f3), (float)f2);
        GL11.glTexCoord2f((float)f6, (float)f7);
        GL11.glVertex2f((float)f, (float)f2);
        GL11.glColor4d((double)((double)color2.getRed() / 255.0), (double)((double)color2.getGreen() / 255.0), (double)((double)color2.getBlue() / 255.0), (double)((double)color2.getAlpha() / 255.0));
        GL11.glTexCoord2f((float)f6, (float)f9);
        GL11.glVertex2f((float)f, (float)(f2 + f4));
        GL11.glTexCoord2f((float)f8, (float)f9);
        GL11.glVertex2f((float)(f + f3), (float)(f2 + f4));
        GL11.glEnd();
        GL11.glShadeModel((int)7424);
    }

    public static void a() {
        ImageRenderer.loadResource("vapelogo", true, false);
        ImageRenderer.loadResource("v4", true, false);
        ImageRenderer.loadResource("lmb", true, false);
        ImageRenderer.loadResource("rmb", true, false);
        ImageRenderer.loadResource("mmb", true, false);
        ImageRenderer.loadResource("up", false, true);
        ImageRenderer.loadResource("up2", false, true);
        ImageRenderer.loadResource("down", false, true);
        ImageRenderer.loadResource("left", false, true);
        ImageRenderer.loadResource("right", false, true);
        ImageRenderer.loadResource("party1@2x", false, true);
        ImageRenderer.loadResource("synced@2x", false, true);
        ImageRenderer.loadResource("party@2x", false, true);
        ImageRenderer.loadResource("default_user", false, false);
        ImageRenderer.loadResource("chat@2x", false, true);
        ImageRenderer.loadResource("triangle", true, false);
        ImageRenderer.loadResource("ping_location", true, false);
        ImageRenderer.loadResource("icons8_downloading_updates", false, true);
        ImageRenderer.loadResource("submit@2x", false, true);
        ImageRenderer.loadResource("legit_primary", true, true);
        for (InventoryItemMatcherGroup iNamed : InventoryItemMatcherGroup.VALUES) {
            if (iNamed.u() == null) continue;
            ImageRenderer.loadResource(iNamed.u(), false, true);
        }
        for (InventoryItemMatcher inventoryItemMatcher : InventoryItemMatcherRegistry.Y()) {
            if (inventoryItemMatcher.Z() == null) continue;
            ImageRenderer.loadResource(inventoryItemMatcher.Z(), false, true);
        }
        ImageRenderer.loadResource("other@2x", false, true);
    }

    public static double m(String string) {
        if (GuiRenderPrimitives.d()) {
            TextureAtlasRegion textureAtlasRegion;
            TextureAtlas textureAtlas = TextureAtlasRegistry.w().m("vape_texture");
            if (textureAtlas.B(string) == null) {
                ImageRenderer.r(string);
            }
            return (textureAtlasRegion = textureAtlas.B(string)) != null ? (double)textureAtlasRegion.q : 0.0;
        }
        GlImageTexture glImageTexture = ImageRenderer.r(string);
        return glImageTexture != null ? (double)glImageTexture.l : 0.0;
    }

    public static void E(Color color, Color color2, float f, float f2, String string, float f3, float f4, boolean bl) {
        GlImageTexture glImageTexture = ImageRenderer.r(string);
        ImageRenderer.i(color, color2, f, f2, glImageTexture, string, f3, f4, bl, -1.0f);
    }

    public static void drawResWithShadow(Color color, float f, float f2, String string, float f3, boolean bl) {
        GlImageTexture glImageTexture = ImageRenderer.r(string);
        double d = 1.0 / (double)f3;
        f = (float)((double)f * d);
        f2 = (float)((double)f2 * d);
        ImageRenderer.i(color, null, f, f2, glImageTexture, string, 32.0f, 32.0f, bl, f3);
    }

    public static void E(Color color, float f, float f2, String string, float f3, float f4, boolean bl) {
        GlImageTexture glImageTexture = ImageRenderer.r(string);
        ImageRenderer.i(color, null, f, f2, glImageTexture, string, f3, f4, bl, -1.0f);
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public static GuiComponent[] C() {
        return k;
    }

    public static void i(Color color, Color color2, float f, float f2, GlImageTexture glImageTexture, String string, float f3, float f4, boolean bl, float f5) {
        if (GuiRenderPrimitives.d()) {
            BufferedGuiRenderPrimitives.s(f, f2, f3, f4, glImageTexture, string, f5, color, color2, bl);
            return;
        }
        boolean bl2 = false;
        boolean bl3 = false;
        if (!y) {
            GL11.glPushMatrix();
            bl2 = GL11.glIsEnabled((int)3042);
            bl3 = GL11.glIsEnabled((int)3553);
            if (!bl2) {
                OpenGlBackendHolder.d.l(3042);
            }
            if (!bl3) {
                OpenGlBackendHolder.d.l(3553);
            }
        }
        if (Math.signum(f5) >= 0.0f) {
            GL11.glScaled((double)f5, (double)f5, (double)f5);
        }
        if (bl) {
            RenderUtils.w(new Color(0, 0, 0, 150));
            ImageRenderer.F(glImageTexture, f + 0.5f, f2 + 0.5f, f3, f4);
        }
        RenderUtils.w(color);
        if (color2 == null) {
            ImageRenderer.F(glImageTexture, f, f2, f3, f4);
        } else {
            ImageRenderer.n(glImageTexture, f, f2, f3, f4, color, color2);
        }
        if (Math.signum(f5) >= 0.0f) {
            GL11.glScaled((double)1.0, (double)1.0, (double)1.0);
        }
        if (!y) {
            RenderUtils.w(Color.white);
            if (!bl2) {
                OpenGlBackendHolder.d.u$src$V$hntn98(3042);
            }
            if (!bl3) {
                OpenGlBackendHolder.d.u$src$V$hntn98(3553);
            }
            GL11.glPopMatrix();
        }
    }

    public static void u(Color color, float f, float f2, GlImageTexture glImageTexture, float f3, float f4, boolean bl) {
        ImageRenderer.i(color, null, f, f2, glImageTexture, null, f3, f4, bl, -1.0f);
    }

    private static GlImageTexture v(TextureAtlas textureAtlas, TextureAtlasRegion textureAtlasRegion) {
        GlImageTexture glImageTexture = textureAtlas.d();
        if (glImageTexture == null || textureAtlasRegion == null) {
            return glImageTexture;
        }
        return new GlImageTexture(glImageTexture.F, textureAtlasRegion.q, textureAtlasRegion.g, textureAtlasRegion.d, textureAtlasRegion.s, textureAtlasRegion.X, textureAtlasRegion.n);
    }

    public static void F(GlImageTexture glImageTexture, float f, float f2, float f3, float f4) {
        float f5;
        if (f3 == f4) {
            f5 = (float)glImageTexture.l / (float)glImageTexture.N;
            f3 *= f5;
        }
        glImageTexture.F();
        f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 1.0f;
        float f8 = 1.0f;
        GL11.glBegin((int)7);
        GL11.glTexCoord2f((float)f7, (float)f6);
        GL11.glVertex2f((float)(f + f3), (float)f2);
        GL11.glTexCoord2f((float)f5, (float)f6);
        GL11.glVertex2f((float)f, (float)f2);
        GL11.glTexCoord2f((float)f5, (float)f8);
        GL11.glVertex2f((float)f, (float)(f2 + f4));
        GL11.glTexCoord2f((float)f7, (float)f8);
        GL11.glVertex2f((float)(f + f3), (float)(f2 + f4));
        GL11.glEnd();
    }

    public static void E(GuiComponent[] guiComponentArray) {
        k = guiComponentArray;
    }
}
