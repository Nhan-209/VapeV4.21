package gg.vape.notification;

import gg.vape.notification.Notification;
import gg.vape.notification.NotificationType;
import gg.vape.notification.TextNotificationContent;

public class ReusableTextNotification
extends Notification {
    private long k;

    public ReusableTextNotification(NotificationType notificationType, String string, String string2, long l) {
        super(notificationType, string, new TextNotificationContent(string2), 0.0, 0.0, l);
        this.k = l;
    }

    public ReusableTextNotification S(String string) {
        this.T(string);
        return this;
    }

    public void p(long l) {
        this.k = l;
    }

    public ReusableTextNotification m(String string) {
        ((TextNotificationContent)super.X$src$Lgg_vape_notification_NotificationContent_$1gg6y56()).k(string);
        return this;
    }

    public ReusableTextNotification B() {
        this.d(this.k);
        this.T(-this.X());
        return this;
    }
}

