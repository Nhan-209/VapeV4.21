package gg.vape.manager.client;

import gg.vape.friend.OnlineFriendActivityState;
import gg.vape.friend.activity.ActivityItemStack;
import gg.vape.friend.activity.ActivityItemStackPayload;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.ItemStack;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class OnlineInventoryTracker {
    private final ActivityItemStack[] y = new ActivityItemStack[4];
    private int E;
    private int d;
    private boolean c;
    private static boolean Z;
    private final ActivityItemStack[] u = new ActivityItemStack[36];

    public ActivityItemStack[] M() {
        return this.u;
    }

    public Map<Integer, @Nullable ActivityItemStack> r(EntityPlayer entityPlayer, boolean bl) {
        int n;
        int n2;
        if (!this.c) {
            this.c = true;
        }
        HashMap<Integer, @Nullable ActivityItemStack> hashMap = new HashMap<Integer, ActivityItemStack>();
        Object[] objectArray2 = entityPlayer.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().i();
        for (n2 = 0; n2 < objectArray2.length; ++n2) {
            ActivityItemStack activityItemStack = this.y[n2];
            ActivityItemStack updatedStack = OnlineFriendActivityState.o(new ItemStack(objectArray2[n2]));
            if (activityItemStack == null && updatedStack == null || updatedStack != null && updatedStack.equals(activityItemStack)) continue;
            this.y[n2] = updatedStack;
            hashMap.put(36 + n2, updatedStack);
        }
        n2 = OnlineConnectionManager.T.S().l().L() == false ? 1 : 0;
        this.E = n = entityPlayer.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        if (bl && n2 == 0) {
            Object[] inventoryContents = entityPlayer.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().M();
            for (int i = 0; i < inventoryContents.length; ++i) {
                ActivityItemStack activityItemStack = this.u[i];
                ActivityItemStack activityItemStack2 = OnlineFriendActivityState.o(new ItemStack(inventoryContents[i]));
                if (activityItemStack == null && activityItemStack2 == null || activityItemStack2 != null && activityItemStack2.equals(activityItemStack)) continue;
                hashMap.put(i, activityItemStack2);
                this.u[i] = activityItemStack2;
            }
        } else {
            ActivityItemStack activityItemStack;
            if (n2 != 0) {
                for (int i = 0; i < this.u.length; ++i) {
                    if (i == n || (activityItemStack = this.u[i]) == null) continue;
                    this.u[i] = null;
                    hashMap.put(i, null);
                }
            }
            ActivityItemStack activityItemStack3 = this.u[n];
            activityItemStack = OnlineFriendActivityState.o(entityPlayer.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt());
            if (!(activityItemStack3 == null && activityItemStack == null || activityItemStack != null && activityItemStack.equals(activityItemStack3))) {
                hashMap.put(n, activityItemStack);
                this.u[n] = activityItemStack;
            }
        }
        return hashMap;
    }

    public void p(Map<Integer, ActivityItemStack> map) {
        HashMap<Integer, ActivityItemStackPayload> hashMap = new HashMap<Integer, ActivityItemStackPayload>();
        for (Map.Entry<Integer, ActivityItemStack> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().W() : null);
        }
        ZeusConnectionManager.T().u().P(hashMap);
    }

    public static void n(boolean bl) {
        Z = bl;
    }


    public void A() {
        this.c = false;
        this.d = 0;
        Arrays.fill(this.y, null);
        Arrays.fill(this.u, null);
    }

    public static boolean R() {
        boolean bl = OnlineInventoryTracker.Q();
        return !bl;
    }

    public boolean F() {
        return this.c;
    }

    static {
        if (OnlineInventoryTracker.R()) {
            OnlineInventoryTracker.n(true);
        }
    }

    public void A(int n) {
        this.d = n;
    }

    public ActivityItemStack[] e() {
        return this.y;
    }

    public static boolean Q() {
        return Z;
    }

    public void X() {
        ActivityItemStack activityItemStack;
        int n;
        HashMap<Integer, @Nullable ActivityItemStackPayload> hashMap = new HashMap<Integer, ActivityItemStackPayload>();
        for (n = 0; n < this.y.length; ++n) {
            activityItemStack = this.y[n];
            if (activityItemStack == null) continue;
            hashMap.put(36 + n, activityItemStack.W());
        }
        for (n = 0; n < this.u.length; ++n) {
            activityItemStack = this.u[n];
            if (activityItemStack == null) continue;
            hashMap.put(n, activityItemStack.W());
        }
        ZeusConnectionManager.T().u().N(this.E, hashMap);
    }

    public int g() {
        return this.d;
    }

    public int U() {
        return this.E;
    }
}
