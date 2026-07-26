package gg.vape.notification;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.notification.FriendNotificationSettings;
import gg.vape.notification.INotification;
import gg.vape.notification.Notification;
import gg.vape.notification.NotificationGroup;
import gg.vape.notification.NotificationSounds;
import gg.vape.notification.NotificationType;
import gg.vape.notification.SoundClip;
import gg.vape.notification.TextNotificationContent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.utils.MathUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

public class NotificationManager
implements EventListener {
    private final Map<NotificationGroup, BooleanValue> v;
    private final ArrayBlockingQueue<INotification> e = new ArrayBlockingQueue(20);
    private static final long a = ZkmLongKeyState.a(-6236732698269716720L, 7787865171980094945L, MethodHandles.lookup().lookupClass()).a(213280198486620L);
    private long p;
    private static int l;
    private final Map<NotificationType, SoundClip> C = new HashMap<NotificationType, SoundClip>();

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean X(INotification iNotification) {
        FriendNotificationSettings friendNotificationSettings = OnlineConnectionManager.T.S().m();
        if (iNotification.c() == NotificationType.FRIENDS_GENERAL && !friendNotificationSettings.K.L().booleanValue()) {
            return false;
        }
        if (iNotification.c() == NotificationType.FRIENDS_NEW_REQUEST && !friendNotificationSettings.P.L().booleanValue()) {
            return false;
        }
        if (iNotification.c() == NotificationType.FRIENDS_NEW_CHAT && !friendNotificationSettings.I.L().booleanValue()) {
            return false;
        }
        if (iNotification.c() == NotificationType.FRIENDS_ONLINE && !friendNotificationSettings.p.L().booleanValue()) {
            return false;
        }
        if (iNotification.c() == NotificationType.FRIENDS_PARTY_GENERAL && !friendNotificationSettings.Q.L().booleanValue()) {
            return false;
        }
        return iNotification.c() != NotificationType.FRIENDS_PARTY_INVITE || friendNotificationSettings.q.L() != false;
    }

    public void K(String string, String string2, NotificationType notificationType, long l, boolean bl) {
        this.x(new Notification(notificationType, string, new TextNotificationContent(string2), 0.0, 0.0, l), bl);
    }

    public ArrayBlockingQueue<INotification> f() {
        return this.e;
    }

    private boolean Z(NotificationGroup notificationGroup) {
        if (!this.v.containsKey((Object)notificationGroup)) {
            return true;
        }
        return this.v.get((Object)notificationGroup).L();
    }

    static {
        if (NotificationManager.l() == 0) {
            NotificationManager.m(82);
        }
    }

    public void J() {
        long l = a ^ 0xC2365663BB5L;
        int n = NotificationManager.i();
        if (Minecraft.theWorld().isNull() || Minecraft.thePlayer().isNull()) {
            return;
        }
        int n2 = OpenGlBackendHolder.d.K(3009);
        float f = OpenGlBackendHolder.d.u(3010);
        OpenGlBackendHolder.d.k(516, 0.0f);
        OpenGlBackendHolder.d.m();
        if (System.currentTimeMillis() > 1805274154878L) {
            float f2 = (float)Math.abs(Minecraft.thePlayer().z());
            Color color = new Color(0, 0, 0, (int)Math.min(240.0f, f2));
            GuiRenderPrimitives.y(0.0f, 0.0f, Minecraft.J(), Minecraft.h(), color);
        }
        double d = -14.0;
        long l2 = System.currentTimeMillis() - this.p;
        this.p = System.currentTimeMillis();
        ArrayList<INotification> arrayList = new ArrayList<INotification>();
        for (INotification iNotification : this.e) {
            iNotification.I(d -= iNotification.t() + 2.0);
            int n3 = (int)(Math.abs(iNotification.k() - iNotification.F()) * 0.3);
            int n4 = (int)(Math.abs(iNotification.E() - iNotification.v()) * 0.3);
            double d2 = NotificationManager.w(iNotification.k(), iNotification.F(), l2, n3);
            double d3 = NotificationManager.w(iNotification.E(), iNotification.v(), l2, n4);
            iNotification.r(d2);
            iNotification.S(d3);
            iNotification.W();
            if (!iNotification.P()) continue;
            arrayList.add(iNotification);
        }
        for (INotification iNotification : arrayList) {
            this.e.remove(iNotification);
        }
        OpenGlBackendHolder.d.k(n2, f);
        OpenGlBackendHolder.d.F();
    }

    public static int l() {
        int n = NotificationManager.i();
        if (n == 0) {
            return 112;
        }
        return 0;
    }

    public void m(INotification iNotification) {
        this.x(iNotification, false);
    }

    public void k(String string, String string2, long l) {
        this.t(string, string2, NotificationType.INFO, l);
    }

    public static void m(int n) {
        l = n;
    }

    public void t(String string, String string2, NotificationType notificationType, long l) {
        this.K(string, string2, notificationType, l, false);
    }

    @EventHandler
    public void r(GuiMouseEvent guiMouseEvent) {
        if (!guiMouseEvent.getAction().equals((Object)MouseButton.LEFT_CLICK)) {
            return;
        }
        for (INotification iNotification : this.e) {
            iNotification.z(guiMouseEvent.getX(), guiMouseEvent.getY());
        }
    }

    public static double w(double d, double d2, long l, double d3) {
        if (d == d2) {
            return d;
        }
        double d4 = Math.max(d3 * (double)Math.max(1L, l) / 16.666666666666668, 0.1);
        return d2 + MathUtil.clamp(d - d2, -d4, d4);
    }

    public static int i() {
        return l;
    }

    public void x(INotification iNotification, boolean bl) {
        if (!this.X(iNotification)) {
            return;
        }
        if (!Vape.INSTANCE.getPublicProfileSettings().R.L().booleanValue() && !bl) {
            return;
        }
        if (!this.Z(iNotification.c().T())) {
            return;
        }
        if (this.C.containsKey((Object)iNotification.c())) {
            Vape.INSTANCE.getNotificationSoundPlayer().q(this.C.get((Object)iNotification.c()));
        }
        double d = iNotification.t() + 16.0;
        for (INotification iNotification2 : this.e) {
            d += iNotification2.t() + 2.0;
        }
        iNotification.S(-d);
        iNotification.r(0.0);
        iNotification.T(-iNotification.X());
        if (this.e.remainingCapacity() == 0) {
            this.e.remove();
        }
        if (!this.e.contains(iNotification)) {
            this.e.add(iNotification);
        }
    }

    public NotificationManager() {
        this.v = new HashMap<NotificationGroup, BooleanValue>();
        this.C.put(NotificationType.FRIENDS_NEW_CHAT, NotificationSounds.F);
        this.C.put(NotificationType.FRIENDS_PARTY_INVITE, NotificationSounds.P);
        this.v.put(NotificationGroup.FRIENDS, Vape.INSTANCE.getPublicProfileSettings().H);
    }
}

