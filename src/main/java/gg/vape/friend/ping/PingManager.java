package gg.vape.friend.ping;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventRender2D;
import gg.vape.event.impl.EventRender3D;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineFriendActivityState;
import gg.vape.friend.ping.EntityPingMarker;
import gg.vape.friend.ping.OnlineFriendPingMarker;
import gg.vape.friend.ping.PingMarker;
import gg.vape.mapping.MappedClasses;
import gg.vape.notification.NotificationType;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.PingResponsePacket;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.RayTraceUtil;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.RenderUtils;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class PingManager
implements EventListener {
    private long n = -1L;
    private final List<PingMarker> C = new CopyOnWriteArrayList<PingMarker>();
    private int i = 0;
    private int O = (int)c;
    private static final long c;
    public static PingManager B;
    private static final String b;
    private PingMarker E;

    @Nullable
    public PingMarker d(OnlineFriend onlineFriend) {
        try {
            return this.J(onlineFriend.S().g());
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
            return null;
        }
    }

    private void lambda$pickPing$1(PingMarker pingMarker, PingResponsePacket pingResponsePacket) {
        this.O = pingResponsePacket.h();
        this.n = System.nanoTime() + pingResponsePacket.p();
        if (pingResponsePacket.v()) {
            this.E = pingMarker;
        } else {
            this.a(pingMarker);
            this.E = null;
            this.k();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void a(PingMarker pingMarker) {
        this.C.remove(pingMarker);
    }

    private void lambda$pickPing$0(boolean bl, PingMarker pingMarker, PingMarker pingMarker2, double[] dArray, PingResponsePacket pingResponsePacket) {
        this.O = pingResponsePacket.h();
        this.n = System.nanoTime() + pingResponsePacket.p();
        if (pingResponsePacket.v()) {
            if (bl) {
                pingMarker.b();
            } else {
                this.a(pingMarker);
                this.E = pingMarker2;
                this.Q(pingMarker2);
            }
        } else {
            pingMarker.n(dArray);
            this.k();
        }
    }

    private void k() {
        Vape.INSTANCE.getNotificationManager().t(b, "", NotificationType.FRIENDS_GENERAL, 2000L);
    }

    private List<PingMarker> c() {
        ArrayList<PingMarker> arrayList = new ArrayList<PingMarker>(this.C);
        Collections.reverse(arrayList);
        return arrayList;
    }

    public void Q(PingMarker pingMarker) {
        PingMarker pingMarker2 = this.d(pingMarker.O());
        if (pingMarker2 != null) {
            this.C.remove(pingMarker2);
        }
        this.C.add(pingMarker);
    }

    public void a() {
        this.C.clear();
    }

    @EventHandler(A=EventPriority.LOW)
    public void O(EventRender3D eventRender3D) {
        double[] dArray;
        OnlineFriendPingMarker onlineFriendPingMarker;
        Object object;
        Object object2;
        if (this.E != null && this.E.r()) {
            this.E = null;
        }
        if (this.n != -1L && System.nanoTime() > this.n) {
            ++this.O;
            this.n = -1L;
        }
        if (this.i < 1) {
            return;
        }
        EntityLivingBase entityLivingBase = RayTraceUtil.l((EntityLivingBase)Minecraft.thePlayer(), 1000.0, 1000.0);
        if (entityLivingBase != null) {
            object2 = null;
            if (entityLivingBase.isInstance(MappedClasses.Yl)) {
                EntityPlayer entityPlayer = new EntityPlayer(entityLivingBase);
                object = Vape.INSTANCE.getOnlineManager().V().X(entityPlayer.getName());
                if (object != null) {
                    object2 = ((OnlineFriendActivityState)object).a().S().g();
                }
            }
            onlineFriendPingMarker = new EntityPingMarker(Vape.INSTANCE.getOnlineManager().r(), (Long)object2, entityLivingBase);
            dArray = onlineFriendPingMarker.A();
        } else {
            object2 = Minecraft.thePlayer().W(1000.0, 1.0f);
            dArray = new double[]{((RayTraceResult)object2).getHitVec().getX(), ((RayTraceResult)object2).getHitVec().getY(), ((RayTraceResult)object2).getHitVec().getZ()};
            onlineFriendPingMarker = new OnlineFriendPingMarker(Vape.INSTANCE.getOnlineManager().r(), dArray);
        }
        object2 = this.E;
        if (object2 != null) {
            if (object2.getClass().equals(onlineFriendPingMarker.getClass())) {
                boolean bl = ((PingMarker)object2).C(dArray);
                object = ((PingMarker)object2).A();
                ((PingMarker)object2).n(dArray);
                PingMarker previousMarker = (PingMarker)object2;
                double[] previousPosition = (double[])object;
                ZeusConnectionManager.T().u().o(((PingMarker)onlineFriendPingMarker).T(), arg_0 -> this.lambda$pickPing$0(bl, previousMarker, onlineFriendPingMarker, previousPosition, arg_0));
            } else {
                this.E = null;
                this.a(onlineFriendPingMarker);
            }
        }
        if (this.E == null) {
            this.E = onlineFriendPingMarker;
            this.Q(onlineFriendPingMarker);
            ZeusConnectionManager.T().u().o(((PingMarker)onlineFriendPingMarker).T(), arg_0 -> this.lambda$pickPing$1(onlineFriendPingMarker, arg_0));
        }
        --this.i;
    }

    @EventHandler
    public void onRender2D(EventRender2D eventRender2D) {
        if (this.C.isEmpty()) {
            return;
        }
        OpenGlBackendHolder.d.m();
        GlStateManager.enableAlpha();
        boolean bl = GL11.glIsEnabled((int)3042);
        RenderUtils.g();
        for (PingMarker pingMarker : this.c()) {
            if (pingMarker.d$src$AD$1it44rn() == null) continue;
            pingMarker.D();
        }
        RenderUtils.f();
        if (bl) {
            GlStateManager.enableBlend();
        } else {
            GlStateManager.disableBlend();
        }
        OpenGlBackendHolder.d.F();
    }

    @EventHandler
    public void W(EventRender3D eventRender3D) {
        if (this.C.isEmpty()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return;
        }
        for (PingMarker pingMarker : this.c()) {
            if (!pingMarker.e()) {
                pingMarker.o();
            }
            if (pingMarker.r()) {
                this.C.remove(pingMarker);
                continue;
            }
            pingMarker.w(worldClient);
            pingMarker.L$src$V$1mon1p8();
            pingMarker.d();
        }
    }

    public void onEnable() {
        if (Vape.INSTANCE.getOnlineManager().B()) {
            return;
        }
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        if (this.O == 0) {
            this.k();
            return;
        }
        ++this.i;
    }

    static {
        b = "Too many pings!";
        c = 6217503732679049226L;
        B = new PingManager();
    }

    @Nullable
    public PingMarker J(long l) {
        try {
            for (PingMarker pingMarker : this.C) {
                if (pingMarker.O().S().g() != l) continue;
                return pingMarker;
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
        return null;
    }
}
