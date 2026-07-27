package gg.vape.notification;

import gg.vape.notification.NotificationContent;
import gg.vape.notification.NotificationType;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.gui.WrappedTextComponent;
import gg.vape.ui.theme.ThemeColors;

public class TextNotificationContent
implements NotificationContent {
    private NotificationType h;
    private boolean g;
    private WrappedTextComponent T;
    private static GuiComponent[] e;

    @Override
    public double C() {
        return this.T.A() + 4.0;
    }

    public TextNotificationContent(String string) {
        this(string, NotificationType.INFO, false);
    }

    public static void P(GuiComponent[] guiComponentArray) {
        e = guiComponentArray;
    }

    public TextNotificationContent(String string, boolean bl) {
        this(string, NotificationType.INFO, bl);
    }

    static {
        if (TextNotificationContent.N() == null) {
            TextNotificationContent.P(new GuiComponent[1]);
        }
    }

    @Override
    public void q(double d, double d2) {
        this.T.K(d);
        this.T.S(d2);
        this.T.c();
    }


    public String p() {
        return this.T.c$src$Ljava_lang_String_$1q00otb();
    }

    public TextNotificationContent(String string, NotificationType notificationType) {
        this(string, notificationType, false);
    }

    public void k(String string) {
        this.T.G(string);
    }

    public NotificationType I$src$Lgg_vape_notification_NotificationType_$1x1s4wm() {
        return this.h;
    }

    public TextNotificationContent(String string, NotificationType notificationType, boolean bl) {
        this.T = new WrappedTextComponent(string, 0.9, ThemeColors.J.Z, false);
        this.T.c(100.0);
        this.T.K(true);
        this.h = notificationType;
        this.g = bl;
    }

    public boolean I$src$Z$17jfynu() {
        return this.g;
    }

    public void n(NotificationType notificationType) {
        this.h = notificationType;
    }

    public static GuiComponent[] N() {
        return e;
    }

    @Override
    public double I() {
        if (this.T.c$src$Ljava_lang_String_$1q00otb().equals("")) {
            return 0.0;
        }
        return this.T.C() + 4.0;
    }
}

