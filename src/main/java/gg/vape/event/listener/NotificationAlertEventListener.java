package gg.vape.event.listener;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.notification.NotificationType;
import org.lwjgl.opengl.GL11;

public class NotificationAlertEventListener
implements EventListener {
    private boolean e = false;
    private static int I;

    public static void E(int n) {
        I = n;
    }

    public static int Q() {
        return I;
    }

    public static int j() {
        int n = NotificationAlertEventListener.Q();
        return 0;
    }


    static {
        NotificationAlertEventListener.E(77);
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void t(EventPostRenderTick eventPostRenderTick) {
        int n;
        if (!this.e && (n = GL11.glGetError()) != 0) {
            Vape.INSTANCE.getNotificationManager().t("GL Error " + n, "Please contact support and report this error code", NotificationType.ALERT, 10000L);
            this.e = true;
        }
    }
}

