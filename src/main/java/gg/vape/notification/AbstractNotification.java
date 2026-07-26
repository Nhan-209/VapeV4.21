package gg.vape.notification;

import gg.vape.Vape;
import gg.vape.notification.INotification;
import gg.vape.notification.NotificationContent;
import gg.vape.notification.NotificationType;
import java.awt.Color;

public abstract class AbstractNotification
implements INotification {
    private NotificationType p;
    private final Color M;
    private double l;
    private double U;
    private String Q;
    private final NotificationContent w;
    private double o;
    private double c;

    public Color y() {
        return Color.WHITE;
    }

    @Override
    public void r(double d) {
        this.l = d;
    }

    @Override
    public void T(double d) {
        this.c = d;
    }

    @Override
    public NotificationType c() {
        return this.p;
    }

    @Override
    public double E() {
        return this.U;
    }

    @Override
    public void I(double d) {
        this.U = d;
    }

    public String H() {
        return this.p.i();
    }

    @Override
    public double t() {
        return 25.0 + this.w.I();
    }

    public void T(String string) {
        this.Q = string;
    }

    @Override
    public void S(double d) {
        this.o = d;
    }

    @Override
    public double k() {
        return this.c;
    }

    public String R() {
        return this.Q;
    }

    @Override
    public double X() {
        double d = Vape.INSTANCE.getFontManager().Y().N(this.Q) + 2.0;
        return Math.max(d, this.w.C());
    }

    public void F(NotificationType notificationType) {
        this.p = notificationType;
    }

    public AbstractNotification(NotificationType notificationType, String string, NotificationContent notificationContent, double d, double d2) {
        this.l = this.c = d;
        this.o = this.U = d2;
        this.Q = string;
        this.w = notificationContent;
        this.p = notificationType;
        this.M = new Color(notificationType.v());
    }

    @Override
    public double v() {
        return this.o;
    }

    public NotificationContent X$src$Lgg_vape_notification_NotificationContent_$1gg6y56() {
        return this.w;
    }

    @Override
    public double F() {
        return this.l;
    }
}

