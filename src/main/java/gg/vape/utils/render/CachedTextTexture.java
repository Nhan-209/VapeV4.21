package gg.vape.utils.render;

import gg.vape.utils.render.GlFramebuffer;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

class CachedTextTexture {
    GlFramebuffer m;



    void O(float f, float f2, int n, int n2) {
        OpenGlBackendHolder.d.l(2903);
        boolean bl = GL11.glIsEnabled((int)3553);
        boolean bl2 = GL11.glIsEnabled((int)2896);
        boolean bl3 = GL11.glIsEnabled((int)3008);
        boolean bl4 = GL11.glIsEnabled((int)3042);
        if (!bl) {
            GlStateManager.enableTexture2D();
        }
        if (bl2) {
            GlStateManager.disableLighting();
        }
        if (bl2) {
            GlStateManager.disableLighting();
        }
        if (!bl3) {
            GlStateManager.enableAlpha();
        }
        if (!bl4) {
            GlStateManager.enableBlend();
        }
        int n3 = GL11.glGetInteger((int)36006);
        int n4 = GL11.glGetInteger((int)32873);
        this.m.S();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        float f3 = 0.0f;
        float f4 = 1.0f;
        float f5 = 1.0f;
        float f6 = 0.0f;
        GL11.glBegin((int)7);
        GL11.glTexCoord2f((float)f5, (float)f4);
        GL11.glVertex2f((float)(f + (float)n), (float)f2);
        GL11.glTexCoord2f((float)f3, (float)f4);
        GL11.glVertex2f((float)f, (float)f2);
        GL11.glTexCoord2f((float)f3, (float)f6);
        GL11.glVertex2f((float)f, (float)(f2 + (float)n2));
        GL11.glTexCoord2f((float)f5, (float)f6);
        GL11.glVertex2f((float)(f + (float)n), (float)(f2 + (float)n2));
        GL11.glEnd();
        GL11.glPopMatrix();
        this.m.M();
        GlStateManager.bindTexture(n4);
        if (!bl) {
            GlStateManager.disableTexture2D();
        }
        if (bl2) {
            GlStateManager.enableLighting();
        }
        if (bl3) {
            GlStateManager.enableAlpha();
        }
        if (bl4) {
            GlStateManager.enableBlend();
        }
    }

    CachedTextTexture() {
    }

    void D(String string, int n) {
        int n2 = Minecraft.getFontRenderer().getStringWidth(string);
        int n3 = Minecraft.getFontRenderer().FONT_HEIGHT(string);
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer((int)16);
        GL11.glGetFloat((int)2983, (FloatBuffer)floatBuffer);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glPushMatrix();
        GL11.glOrtho((double)0.0, (double)n2, (double)n3, (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        int n4 = GL11.glGetInteger((int)32873);
        this.m = new GlFramebuffer(n2, n3, true);
        this.m.u(n2, n3);
        this.m.f(true);
        Minecraft.getFontRenderer().drawString(string, 0.0, 0.0, n);
        this.m.S();
        this.m.o();
        GlStateManager.bindTexture(n4);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glPopMatrix();
        GL11.glLoadMatrix((FloatBuffer)floatBuffer);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glPopMatrix();
    }
}
