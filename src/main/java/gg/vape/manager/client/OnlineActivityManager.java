package gg.vape.manager.client;

import gg.vape.Vape;
import gg.vape.friend.LocalOnlineFriend;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineFriendActivityState;
import gg.vape.friend.PartyState;
import gg.vape.friend.activity.ActivityItemStack;
import gg.vape.friend.activity.ActivitySnapshotPayload;
import gg.vape.friend.ping.PingManager;
import gg.vape.manager.client.OnlineInventoryTracker;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.ClientActivitySnapshotPacket;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.World;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.Nullable;

public class OnlineActivityManager {
    private final Map<Long, OnlineFriend> H;
    private int R;
    private final Map<Long, OnlineFriendActivityState> m;
    private final Map<Long, Long> A = new ConcurrentHashMap<Long, Long>();
    private int n;
    private final Set<Long> S;
    private final ArrayList<Integer> C;
    private final Map<Long, Long> G;

    public OnlineActivityManager() {
        this.m = new ConcurrentHashMap<Long, OnlineFriendActivityState>();
        this.H = new ConcurrentHashMap<Long, OnlineFriend>();
        this.G = new ConcurrentHashMap<Long, Long>();
        this.S = new LinkedHashSet<Long>();
        this.C = new ArrayList();
    }

    public boolean J(OnlineFriend onlineFriend) {
        return this.o(onlineFriend.S().g());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void o(OnlineFriend onlineFriend) {
        if (onlineFriend.S().g() == Vape.INSTANCE.getOnlineManager().r().S().g()) {
            return;
        }
        this.G.remove(onlineFriend.S().g());
        Set<Long> set = this.S;
        synchronized (set) {
            this.S.remove(onlineFriend.S().g());
        }
        if (!this.m.containsKey(onlineFriend.S().g())) {
            this.m.put(onlineFriend.S().g(), new OnlineFriendActivityState(onlineFriend));
        }
    }

    public void T() {
        this.R(true);
    }

    public void e(ActivitySnapshotPayload activitySnapshotPayload) {
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (this.n++ % 20 == 19 || activitySnapshotPayload.g().L() == 10) {
            if (partyState != null) {
                ZeusConnectionManager.T().u().V(new ClientActivitySnapshotPacket(activitySnapshotPayload));
            }
            this.n = 0;
        }
        this.z();
        this.u();
    }

    private void u() {
        for (Long l : this.m.keySet()) {
            OnlineFriendActivityState onlineFriendActivityState = this.m.get(l);
            if (onlineFriendActivityState.p() <= 0) continue;
            onlineFriendActivityState.z(onlineFriendActivityState.p() - 1);
        }
    }

    private void z() {
        for (Long l : this.m.keySet()) {
            OnlineFriendActivityState onlineFriendActivityState = this.m.get(l);
            if (onlineFriendActivityState.R() <= 0) continue;
            onlineFriendActivityState.m(onlineFriendActivityState.R() - 1);
        }
    }

    public Collection<OnlineFriendActivityState> X() {
        return this.m.values();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void R(boolean bl) {
        this.A.clear();
        this.m.clear();
        this.H.clear();
        this.G.clear();
        Set<Long> set = this.S;
        synchronized (set) {
            this.S.clear();
        }
        this.R = 0;
        PingManager.B.a();
        if (bl) {
            ZeusConnectionManager.T().u().B();
        }
    }

    public void h(EntityPlayer entityPlayer) {
        if (this.R++ % 20 == 19 && !this.H.isEmpty()) {
            long[] lArray = new long[this.H.size()];
            int n = 0;
            for (Long l : this.H.keySet()) {
                lArray[n++] = l;
            }
            this.H.clear();
            ZeusConnectionManager.T().u().h(lArray);
            this.R = 0;
        }
        OnlineFriendActivityState onlineFriendActivityState = Vape.INSTANCE.getOnlineManager().r().E();
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        OnlineInventoryTracker onlineInventoryTracker = Vape.INSTANCE.getOnlineManager().N();
        if (partyState != null && !this.x()) {
            int n;
            boolean bl = !onlineInventoryTracker.F();
            boolean bl2 = onlineInventoryTracker.g() % 20 == 19;
            boolean bl3 = bl || bl2;
            int n2 = onlineInventoryTracker.U();
            Map<Integer, ActivityItemStack> map = onlineInventoryTracker.r(entityPlayer, bl3);
            int n3 = onlineInventoryTracker.U();
            for (n = 0; n < onlineInventoryTracker.e().length; ++n) {
                onlineFriendActivityState.I()[n] = onlineInventoryTracker.e()[n];
            }
            for (n = 0; n < onlineInventoryTracker.M().length; ++n) {
                onlineFriendActivityState.N$src$ALgg_vape_friend_activity_ActivityItemStack_$1nvfl9h()[n] = onlineInventoryTracker.M()[n];
            }
            if (n2 != n3) {
                ZeusConnectionManager.T().u().Z(n3);
                onlineFriendActivityState.D(n3);
            }
            if (!map.isEmpty() || bl) {
                if (bl) {
                    onlineInventoryTracker.X();
                } else {
                    onlineInventoryTracker.p(map);
                }
                onlineInventoryTracker.A(0);
            } else {
                onlineInventoryTracker.A(onlineInventoryTracker.g() + 1);
            }
        }
    }

    @Nullable
    public OnlineFriendActivityState J(long l) {
        return this.m.get(l);
    }

    public boolean o(long l) {
        return this.m.containsKey(l);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Nullable
    public OnlineFriendActivityState X(String string) {
        for (OnlineFriendActivityState onlineFriendActivityState : this.X()) {
            if (!string.equals(onlineFriendActivityState.a().I())) continue;
            return onlineFriendActivityState;
        }
        return null;
    }

    @Nullable
    public OnlineFriendActivityState m(OnlineFriend onlineFriend) {
        return this.J(onlineFriend.S().g());
    }

    public boolean x() {
        return this.m.isEmpty();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void o(EntityPlayer entityPlayer, World world) {
        if (world.isNull()) {
            return;
        }
        LocalOnlineFriend localOnlineFriend = Vape.INSTANCE.getOnlineManager().r();
        LinkedHashSet<OnlineFriend> linkedHashSet = new LinkedHashSet<OnlineFriend>(Vape.INSTANCE.getOnlineFriendManager().g());
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState != null) {
            linkedHashSet.addAll(partyState.c());
        }
        LinkedHashMap<String, OnlineFriend> linkedHashMap = new LinkedHashMap<String, OnlineFriend>();
        for (OnlineFriend onlineFriend : linkedHashSet) {
            if (onlineFriend.S().g() == localOnlineFriend.S().g() || onlineFriend.I().isEmpty()) continue;
            linkedHashMap.put(onlineFriend.I(), onlineFriend);
        }
        for (Object object : world.X()) {
            Long l;
            EntityPlayer entityPlayer2 = new EntityPlayer(object);
            OnlineFriend onlineFriend2 = (OnlineFriend)linkedHashMap.get(entityPlayer2.getName());
            if (onlineFriend2 == null || entityPlayer2.l() < 20) continue;
            Long l2 = this.A.get(onlineFriend2.S().g());
            if (l2 != null) {
                long l3 = System.currentTimeMillis() - l2;
                if (l3 <= 2000L) continue;
                this.A.remove(onlineFriend2.S().g());
            }
            if ((l = this.G.get(onlineFriend2.S().g())) != null) {
                long l4 = System.currentTimeMillis() - l;
                if (l4 <= 2000L) continue;
                Set<Long> set = this.S;
                synchronized (set) {
                    if (this.S.contains(onlineFriend2.S().g())) {
                        continue;
                    }
                    this.S.add(onlineFriend2.S().g());
                    ZeusConnectionManager.T().u().R(onlineFriend2.S().g(), (int)entityPlayer2.z(), (int)entityPlayer2.N(), (int)entityPlayer2.h());
                    continue;
                }
            }
            if (this.J(onlineFriend2) || this.H.containsKey(onlineFriend2.S().g()) || this.G.containsKey(onlineFriend2.S().g())) continue;
            this.G.put(onlineFriend2.S().g(), System.currentTimeMillis());
            this.H.put(onlineFriend2.S().g(), onlineFriend2);
        }
        this.h(entityPlayer);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void m(long l) {
        this.A.put(l, System.currentTimeMillis() + 2000L);
        this.m.remove(l);
        this.H.remove(l);
        this.G.remove(l);
        Set<Long> set = this.S;
        synchronized (set) {
            this.S.remove(l);
        }
    }
}
