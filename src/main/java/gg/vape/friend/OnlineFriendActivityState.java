package gg.vape.friend;

import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineFriendActivityListener;
import gg.vape.friend.OnlineFriendActivityType;
import gg.vape.friend.activity.ActivityHealthData;
import gg.vape.friend.activity.ActivityItemStack;
import gg.vape.friend.activity.ActivityItemStackPayload;
import gg.vape.friend.activity.ActivityPositionData;
import gg.vape.friend.activity.ActivitySnapshotPayload;
import gg.vape.friend.activity.ActivitySnapshotPayloadBuilder;
import gg.vape.friend.activity.ActivityTargetData;
import gg.vape.mapping.MappedClasses;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.utils.EnchantmentUtil;
import gg.vape.wrapper.impl.Enchantment;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionEntry;
import gg.vape.wrapper.impl.PotionRegistry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.Nullable;

public class OnlineFriendActivityState {
    private float a;
    private final ActivityItemStack[] o;
    private double F;
    private int f;
    private boolean R;
    HashMap<Integer, Integer> D;
    private int O;
    private int g;
    private int S;
    private int l;
    private final Map<PotionEntry, Integer> G;
    private double m;
    @Nullable
    private UUID K;
    private boolean t;
    private double E;
    @Nullable
    private String r;
    private AtomicBoolean i;
    private float y;
    private HashSet<OnlineFriendActivityType> v;
    private DoubleAnimation b;
    private final ActivityItemStack[] C;
    private int A;
    private int X;
    private float x;
    private final OnlineFriend H;
    private int L;

    public int R() {
        return this.L;
    }

    public void E(boolean bl) {
    }

    public int p() {
        return this.X;
    }

    @Nullable
    public static ActivityItemStack o(ItemStack itemStack) {
        ActivityItemStackPayload activityItemStackPayload = OnlineFriendActivityState.p(itemStack);
        if (activityItemStackPayload == null) {
            return null;
        }
        return ActivityItemStack.C(activityItemStackPayload);
    }

    public int q() {
        return this.f;
    }

    public float l(EntityPlayer entityPlayer) {
        return this.y;
    }


    @Nullable
    public String O() {
        return this.r;
    }

    public int f(EntityPlayer entityPlayer) {
        if (entityPlayer == null) {
            return this.X;
        }
        return entityPlayer.i();
    }

    public void B(OnlineFriendActivityType onlineFriendActivityType) {
        this.v.remove((Object)onlineFriendActivityType);
    }


    public void D() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        int n = 600;
        if (entityPlayerSP.V() != entityPlayerSP.a$src$F$1txy325() || entityPlayerSP.J() != entityPlayerSP.g()) {
            this.O = 0;
        }
        if (this.O > n) {
            this.K(OnlineFriendActivityType.AFK);
        } else {
            this.B(OnlineFriendActivityType.AFK);
        }
        if (!this.D.isEmpty()) {
            this.s(this.D.size());
            this.K(OnlineFriendActivityType.COMBAT);
            this.h();
        } else {
            this.B(OnlineFriendActivityType.COMBAT);
        }
        int n2 = 160;
        ActivityItemStack activityItemStack = this.N$src$ALgg_vape_friend_activity_ActivityItemStack_$1nvfl9h()[this.N()];
        if (activityItemStack != null) {
            Item item = Item.L(String.valueOf(activityItemStack.I()));
            if (item.isInstance(MappedClasses.Vw) && Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362().isKeyDown()) {
                this.S = 0;
            }
            if (item.isInstance(MappedClasses.Vw) && this.S < n2) {
                this.K(OnlineFriendActivityType.BUILDING);
            } else {
                this.B(OnlineFriendActivityType.BUILDING);
            }
        } else {
            this.B(OnlineFriendActivityType.BUILDING);
        }
        if (entityPlayerSP.t() != 0.0 || entityPlayerSP.q() > 0.0 || entityPlayerSP.T() != 0.0) {
            this.l = 0;
        }
        if (this.l < 50) {
            this.K(OnlineFriendActivityType.MOVING);
        } else {
            this.B(OnlineFriendActivityType.MOVING);
        }
        ++this.O;
        ++this.S;
        ++this.l;
    }

    public boolean k() {
        return this.t;
    }

    public ActivityItemStack[] N$src$ALgg_vape_friend_activity_ActivityItemStack_$1nvfl9h() {
        return this.o;
    }

    public static ActivitySnapshotPayload f$src$Lgg_vape_friend_activity_ActivitySnapshotPayload$cbdquh(EntityPlayer entityPlayer) {
        ActivitySnapshotPayloadBuilder activitySnapshotPayloadBuilder = new ActivitySnapshotPayloadBuilder();
        activitySnapshotPayloadBuilder.H(new ActivityPositionData(entityPlayer.z(), entityPlayer.N(), entityPlayer.h()));
        LinkedHashMap<Short, Integer> linkedHashMap = new LinkedHashMap<Short, Integer>();
        for (Object potionEffectHandle : entityPlayer.B$src$Ljava_util_Collection_$1uxz2f9()) {
            PotionEntry potionEntry;
            PotionEffect potionEffect = new PotionEffect(potionEffectHandle);
            if (potionEffect.isNull() || (potionEntry = PotionRegistry.R(potionEffect)) == null) continue;
            linkedHashMap.put(potionEntry.T(), potionEffect.k());
        }
        activitySnapshotPayloadBuilder.M(new ActivityHealthData(entityPlayer.w$src$F$15l9epb(), entityPlayer.I$src$F$14vyvep(), entityPlayer.p(), entityPlayer.c$src$I$15a9iwo(), linkedHashMap));
        OnlineFriendActivityListener onlineFriendActivityListener = OnlineFriendActivityListener.X;
        EntityPlayer target = onlineFriendActivityListener.M();
        if (target != null) {
            activitySnapshotPayloadBuilder.t(new ActivityTargetData(target.X$src$Ljava_util_UUID_$1o5dyg6(), target.getName()));
        } else {
            activitySnapshotPayloadBuilder.t(null);
        }
        return activitySnapshotPayloadBuilder.X();
    }

    public void y(boolean bl) {
        this.t = bl;
    }

    public void N(int n) {
        this.S = n;
    }

    public void m(int n) {
        this.L = n;
    }

    public void P(int n) {
        this.D.put(n, 0);
    }

    public void z(int n) {
        this.X = n;
    }

    public float I(EntityPlayer entityPlayer) {
        if (entityPlayer == null) {
            return this.x;
        }
        return entityPlayer.I$src$F$14vyvep();
    }

    public void K(OnlineFriendActivityType onlineFriendActivityType) {
        this.v.add(onlineFriendActivityType);
    }

    public float F(EntityPlayer entityPlayer) {
        if (entityPlayer == null) {
            return this.a;
        }
        return entityPlayer.p();
    }

    public void n(int n) {
        this.O = n;
    }

    public int N() {
        return this.A;
    }

    public void d() {
        this.v.clear();
        this.D.clear();
    }

    public double X(EntityPlayer entityPlayer) {
        if (entityPlayer == null) {
            return this.F;
        }
        return entityPlayer.A();
    }

    public int e(EntityPlayer entityPlayer) {
        if (entityPlayer == null) {
            return this.L;
        }
        return entityPlayer.c$src$I$15a9iwo();
    }

    @Nullable
    public UUID m() {
        return this.K;
    }

    public OnlineFriendActivityState(OnlineFriend onlineFriend) {
        this.v = new HashSet<OnlineFriendActivityType>(Arrays.asList(new OnlineFriendActivityType[0]));
        this.O = 0;
        this.S = 1000000;
        this.l = 100000;
        this.f = 0;
        this.i = new AtomicBoolean(false);
        this.D = new HashMap();
        this.y = 15.0f;
        this.x = 20.0f;
        this.a = 2.0f;
        this.X = 0;
        this.b = new DoubleAnimation(0.05, 0.0, 1.0);
        this.g = 0;
        this.m = 0.0;
        this.F = 64.0;
        this.E = 0.0;
        this.C = new ActivityItemStack[4];
        this.o = new ActivityItemStack[36];
        this.G = new LinkedHashMap<PotionEntry, Integer>();
        this.t = false;
        this.H = onlineFriend;
    }

    public void O(int n) {
        this.g = n;
    }

    public void D(int n) {
        this.A = n;
    }

    @Nullable
    public static ActivityItemStackPayload p(ItemStack itemStack) {
        if (itemStack.isNull() || itemStack.getItem().P() == 0) {
            return null;
        }
        Map<Enchantment, Short> map = EnchantmentUtil.A(itemStack);
        HashMap<Short, Short> hashMap = new HashMap<Short, Short>();
        for (Map.Entry<Enchantment, Short> entry : map.entrySet()) {
            Short s = EnchantmentUtil.c(entry.getKey());
            if (s == null) continue;
            hashMap.put(s, entry.getValue());
        }
        return new ActivityItemStackPayload(itemStack.getItem().P(), itemStack.t(), itemStack.L(), hashMap);
    }

    public void h() {
        int n = 150;
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        hashMap.putAll(this.D);
        Set<Integer> set = hashMap.keySet();
        for (Integer n2 : set) {
            if (this.D.get(n2) > n) {
                this.D.remove(n2);
                continue;
            }
            this.D.put(n2, this.D.get(n2) + 1);
        }
    }

    public ActivityItemStack[] I() {
        return this.C;
    }

    public boolean Q() {
        return this.m() != null && this.O() != null;
    }

    public int L() {
        return this.g;
    }

    public int j() {
        return this.S;
    }

    public OnlineFriendActivityType P() {
        if (this.v.contains((Object)OnlineFriendActivityType.DEAD)) {
            return OnlineFriendActivityType.DEAD;
        }
        if (this.v.contains((Object)OnlineFriendActivityType.AFK)) {
            return OnlineFriendActivityType.AFK;
        }
        if (this.v.contains((Object)OnlineFriendActivityType.COMBAT)) {
            return OnlineFriendActivityType.COMBAT;
        }
        if (this.v.contains((Object)OnlineFriendActivityType.BUILDING)) {
            return OnlineFriendActivityType.BUILDING;
        }
        if (this.v.contains((Object)OnlineFriendActivityType.MOVING)) {
            return OnlineFriendActivityType.MOVING;
        }
        return OnlineFriendActivityType.NONE;
    }

    public DoubleAnimation v() {
        return this.b;
    }

    public double v(EntityPlayer entityPlayer) {
        if (entityPlayer == null) {
            return this.m;
        }
        return entityPlayer.c();
    }

    public void N(ActivitySnapshotPayload activitySnapshotPayload) {
        ActivityHealthData activityHealthData;
        this.t = true;
        ActivityPositionData activityPositionData = activitySnapshotPayload.Q();
        if (activityPositionData != null) {
            this.m = activityPositionData.d();
            this.F = activityPositionData.Q();
            this.E = activityPositionData.h();
        }
        if ((activityHealthData = activitySnapshotPayload.g()) != null) {
            this.y = activityHealthData.j();
            this.x = activityHealthData.V();
            this.a = activityHealthData.H();
            this.L = activityHealthData.L();
            this.G.clear();
            for (Map.Entry<Short, Integer> entry : activityHealthData.G().entrySet()) {
                PotionEntry potionEntry = PotionRegistry.A(entry.getKey());
                if (potionEntry == null) continue;
                this.G.put(potionEntry, entry.getValue());
            }
        }
        ActivityTargetData targetData = activitySnapshotPayload.J();
        if (targetData != null) {
            this.K = targetData.B();
            this.r = targetData.a();
        } else {
            this.K = null;
            this.r = null;
        }
    }

    public void s(int n) {
        this.f = n;
    }

    public OnlineFriend a() {
        return this.H;
    }

    public boolean H() {
        return this.R;
    }

    public double W(EntityPlayer entityPlayer) {
        if (entityPlayer == null) {
            return this.E;
        }
        return entityPlayer.Z();
    }
}
