package gg.vape.notification;

import gg.vape.notification.Notification;
import gg.vape.notification.NotificationType;
import gg.vape.notification.TextNotificationContent;

public class SettingsSyncStatusNotification
extends Notification {
    private boolean N = true;
    private String F;
    private String K;
    private int z;
    private String Z;
    private int D;

    public void F(int n) {
        this.D = n;
        this.F = null;
    }

    private void j(String string) {
        ((TextNotificationContent)super.X$src$Lgg_vape_notification_NotificationContent_$1gg6y56()).k(string);
        this.T(-this.X());
    }

    @Override
    public double C() {
        return this.N ? 100.0 : super.C();
    }

    public Integer v$src$Ljava_lang_Integer_$1kwyf2e() {
        return this.D;
    }

    public void I(int n) {
        this.z = n;
    }

    public void K(String string) {
        this.Z = string;
    }

    public Integer i() {
        return this.z;
    }

    public void B() {
        this.N = false;
        if (!this.t$src$Z$1jerbif()) {
            this.d(5000L);
            this.j("Your settings have been successfully saved!");
            return;
        }
        this.d(15000L);
        String string = "Your settings failed to save\nError " + this.z + ":" + this.D + " ";
        string = string + (this.Z != null ? this.Z + " " + this.K : "1");
        if (this.F != null) {
            string = string + "\n" + this.F;
        }
        string = string + "\nPlease contact support";
        this.j(string);
        super.F(NotificationType.WARNING);
    }

    public boolean t$src$Z$1jerbif() {
        return this.z != 1 || this.D != 1;
    }

    public void f(String string) {
        this.F = string;
    }

    public void Z(String string) {
        this.K = string;
    }


    public SettingsSyncStatusNotification() {
        super(NotificationType.INFO, "Saving settings", new TextNotificationContent("Please wait while your settings save..."), 0.0, 0.0, 10000L);
    }
}

