package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.hud.CompassHudFrame;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;

public class CompassStripComponent
extends GuiComponent {
    private CompassHudFrame Q;
    private String K = "compassbigpoint";
    private String i = "compassarrow";
    private String O = "compasssmallpoint";
    private String R = "compasspointer";

    @Override
    public void I() {
        this.H();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private float l$src$F$uzmaa0() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        float f = entityPlayerSP.J() % 360.0f;
        if (f < -180.0f) {
            f += 360.0f;
        }
        if (f > 180.0f) {
            f -= 360.0f;
        }
        return f + 180.0f;
    }

    private void Y$src$V$up67dx() {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        RenderUtils.m(this.Q.G$src$D$1b2f02a(), this.Q.n() - 10.0, this.Q.A(), this.Q.L() + 10.0);
        float f = this.l$src$F$uzmaa0();
        float f2 = f - 70.0f;
        float f3 = f + 70.0f;
        ImageRenderer.e();
        double d = (this.Q.A() + 4.0) / 1400.0 * Vape.INSTANCE.getClientSettings().s();
        double d2 = this.Q.G$src$D$1b2f02a() * Vape.INSTANCE.getClientSettings().s();
        float f4 = f2;
        while (f4 < f3) {
            float f5 = (float)((int)(f4 * 10.0f)) / 10.0f;
            this.S(d2 += d, f5);
            float f6 = f5 + 0.1f;
            this.S(d2 += d, f6);
            f4 = (float)((double)f4 + 0.2);
        }
        ImageRenderer.m();
        this.n(this.i, this.Q.G$src$D$1b2f02a() - 5.0 + 2.3 + this.Q.A() / 2.0, this.Q.n() - 2.5, this.Q.m$src$Ljava_awt_Color_$ppsp8z(), 0.5);
        String string = String.valueOf((int)f);
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().W(0.8, false);
        smoothFontRenderer.T(string, this.Q.G$src$D$1b2f02a() + this.Q.A() / 2.0 + 2.3 - smoothFontRenderer.N(string) / 2.0, this.Q.n() - 8.0, this.Q.l(new Color(230, 230, 230)), this.Q.l(new Color(0, 0, 0, 180)));
        RenderUtils.T();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public void J() {
        Vape.debugLog("Hovering");
    }

    @Override
    public double x() {
        return 0.0;
    }

    private void S(double d, float f) {
        double d2 = this.Q.n() * Vape.INSTANCE.getClientSettings().s();
        double d3 = this.Q.L() * Vape.INSTANCE.getClientSettings().s();
        if (f < 0.0f) {
            f = 360.0f + f;
        } else if (f > 359.0f) {
            f = 360.0f - f;
        }
        f = Math.abs(f);
        if (f % 45.0f == 0.0f) {
            if (!this.Q.m()) {
                GuiRenderPrimitives.F(this.K, (double)((float)d), (double)((float)d2) + d3 / 2.0 - 6.0, 7.0, 10.0, this.Q.l(new Color(0, 0, 0, 100)));
            }
            GuiRenderPrimitives.F(this.K, (double)((float)d), (double)((float)d2) + d3 / 2.0 - 6.0, 7.0, 10.0, this.Q.l(Color.WHITE));
        } else if (f % 5.0f == 0.0f) {
            if (!this.Q.m()) {
                GuiRenderPrimitives.h(this.O, (float)d, (float)(d2 + d3 / 2.0 - 6.0), 7.0f, 8.0f, this.Q.l(new Color(0, 0, 0, 50)));
            }
            GuiRenderPrimitives.h(this.O, (float)d, (float)(d2 + d3 / 2.0 - 6.0), 7.0f, 8.0f, this.Q.l(Color.WHITE));
        }
        if (f % 45.0f == 0.0f) {
            String string = this.t(f);
            if (string != null) {
                double d4 = this.O(0.7).N(string) / 2.0;
                this.z(string, d - d4, d2 + d3 / 2.0, this.Q.l(Color.WHITE), true);
            }
        } else if (f % 15.0f == 0.0f) {
            String string = String.valueOf((int)f);
            double d5 = this.O(0.7).N(string) / 2.0;
            this.z(string, d - d5, d2 + d3 / 2.0, this.Q.l(CompassStripComponent.J.Z), false);
        }
    }

    public CompassStripComponent(CompassHudFrame compassHudFrame) {
        this.Q = compassHudFrame;
    }

    private void n(String string, double d, double d2, Color color, double d3) {
        ImageRenderer.drawRes(color, (float)d, (float)d2, string, (float)d3);
    }

    private void z(String string, double d, double d2, Color color, boolean bl) {
        if (!this.Q.m()) {
            bl = true;
        }
        SmoothFontRenderer smoothFontRenderer = bl ? Vape.INSTANCE.getFontManager().W(0.7, false) : Vape.INSTANCE.getFontManager().Y(0.7);
        if (this.Q.m()) {
            smoothFontRenderer.T(string, d, d2, this.Q.l(color), this.Q.l(new Color(0, 0, 0, 30)));
        } else {
            smoothFontRenderer.T(string, d, d2, this.Q.l(new Color(230, 230, 230)), this.Q.l(new Color(0, 0, 0, 150)));
        }
    }

    @Override
    public void u() {
    }

    @Override
    public void F() {
    }

    @Override
    public void H() {
        this.Y$src$V$up67dx();
    }

    private String t(float f) {
        if ((double)f == 0.0) {
            return "N";
        }
        if ((double)f == 45.0) {
            return "NE";
        }
        if ((double)f == 90.0) {
            return "E";
        }
        if ((double)f == 135.0) {
            return "SE";
        }
        if ((double)f == 180.0) {
            return "S";
        }
        if ((double)f == 225.0) {
            return "SW";
        }
        if ((double)f == 270.0) {
            return "W";
        }
        if ((double)f == 315.0) {
            return "NW";
        }
        return null;
    }
}

