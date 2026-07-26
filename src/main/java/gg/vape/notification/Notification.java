package gg.vape.notification;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.notification.AbstractNotification;
import gg.vape.notification.NotificationContent;
import gg.vape.notification.NotificationType;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.theme.ThemeColors;
import gg.vape.utils.render.BlurRegionRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class Notification
extends AbstractNotification {
    private boolean C;
    private long q = Long.MAX_VALUE;
    private static final String d = "noti_alert_large";
    private final BlurRegionRenderer t = new BlurRegionRenderer(0, 0);
    private long I;

    private double c$src$D$1oj5l18() {
        return this.F() + 3.0 + (double)Minecraft.J() / Vape.INSTANCE.getClientSettings().s() / 2.0;
    }

    public long getTime() {
        return this.q;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean P() {
        return this.F() >= 1.0;
    }

    @Override
    public void z(double d, double d2) {
        if (!this.Y().J(d, d2)) {
            return;
        }
        this.X$src$V$1od3uxr();
    }

    public Notification(NotificationType notificationType, String string, NotificationContent notificationContent, double d, double d2, long l) {
        super(notificationType, string, notificationContent, d, d2);
        this.I = l;
    }

    private double O() {
        return this.v() + (double)Minecraft.h() / Vape.INSTANCE.getClientSettings().s() / 2.0;
    }

    public double C() {
        return Math.max(Math.min((double)(this.q - System.currentTimeMillis()) / (double)this.I, 1.0), 0.0);
    }

    public void d(long l) {
        this.I = l;
        this.q = System.currentTimeMillis() + l;
    }

    @Override
    public double X() {
        double d = 18.0;
        double d2 = 100.0 + d;
        return Math.max(d2, super.X()) + d;
    }

    @Override
    public double t() {
        return super.t();
    }

    public void X$src$V$1od3uxr() {
        this.T(5.0);
    }

    @Override
    public void W() {
        if (!this.C) {
            this.C = true;
            this.d(this.I);
        }
        float f = (float)(1.0 / Vape.INSTANCE.getClientSettings().s());
        boolean bl = GL11.glIsEnabled((int)3042);
        float f2 = 1.0f / f;
        if (GuiRenderPrimitives.d()) {
            OpenGlBackendHolder.d.H(f2, f2, f2);
        }
        double d = this.c$src$D$1oj5l18();
        double d2 = this.O();
        double d3 = this.X();
        double d4 = this.t();
        boolean bl2 = this.Y().Z(RenderUtils.h());
        this.t.L((int)d3 * 2, (int)d4 * 2);
        this.t.t((int)d, (int)d2, 20.0f, 3.0f);
        Color color = this.y();
        Color color2 = new Color(0, 0, 0, 173);
        Color color3 = new Color(48, 48, 48, 255);
        if (bl2) {
            color2 = new Color(0, 0, 0, 200);
            color3 = new Color(60, 60, 60, 255);
        }
        GuiRenderPrimitives.e(d, d2, d3, d4, color2, true, 3.0f, 1.0f);
        GuiRenderPrimitives.P(d, d2, d3, d4, color3, 3.0f, 1.0f, 1.0f);
        ImageRenderer.drawResWithShadow(color, (float)d - 4.0f, (float)d2 - 6.0f, this.H(), 1.0f, true);
        if (this.c().equals((Object)NotificationType.ALERT)) {
            ImageRenderer.drawResWithShadow(color, (float)d - 2.0f, (float)d2, Notification.d, 0.65f, false);
        }
        Vape.INSTANCE.getFontManager().W(0.9, true).d(this.R(), d + 23.0, d2 + 8.0, this.c().equals((Object)NotificationType.ALERT) ? new Color(this.c().v()) : ThemeColors.J.A);
        this.X$src$Lgg_vape_notification_NotificationContent_$1gg6y56().q(d + 23.0, d2 + 21.0);
        double d5 = this.C();
        if (d5 < 100.0) {
            GuiRenderPrimitives.e(d + 1.0, d2 + d4 - 1.5, d3 * d5 - 1.0, 0.5, new Color(this.c().v()), false, 1.0f, 1.0f);
        }
        if (d5 <= 0.0) {
            this.X$src$V$1od3uxr();
        }
        if (GuiRenderPrimitives.d()) {
            OpenGlBackendHolder.d.H(f, f, f);
        }
        OpenGlBackendHolder.d.q(1.0f, 1.0f, 1.0f, 1.0f);
        if (bl) {
            GL11.glEnable((int)3042);
        }
    }

    private RectData Y() {
        return new RectData(this.c$src$D$1oj5l18(), this.O(), this.X(), this.t());
    }

    public boolean M() {
        return System.currentTimeMillis() >= this.q;
    }
}

