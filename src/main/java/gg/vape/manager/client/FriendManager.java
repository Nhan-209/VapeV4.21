package gg.vape.manager.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import gg.vape.Vape;
import gg.vape.friend.ExternalFriend;
import gg.vape.friend.Friend;
import gg.vape.friend.FriendEntry;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.mapping.MappedClasses;
import gg.vape.utils.RayTraceUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

public class FriendManager {
    public BooleanValue z;
    public BooleanValue C;
    private static String G;
    public ColorValue R;
    public BooleanValue J;
    private final Set<FriendEntry> E = new HashSet<FriendEntry>();
    public BooleanValue q;

    public static String b() {
        return G;
    }

    public static void I(String string) {
        G = string;
    }

    public void m() {
        if (Minecraft.theWorld().isNull()) {
            return;
        }
        for (Object e : Minecraft.theWorld().X()) {
            if (e == null) {
                return;
            }
            new EntityPlayer(e).w$src$V$1iu649y();
        }
    }

    @Nullable
    public FriendEntry T(String string, boolean bl) {
        if (this.z.getEffectiveValue().booleanValue() || !bl) {
            ArrayList<FriendEntry> arrayList = this.C(string);
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList.stream().filter(FriendEntry::c).findFirst().orElse(null);
        }
        return null;
    }

    static {
        FriendManager.I("p5mJgc");
    }

    public void E(FriendEntry friendEntry) {
        this.E.remove(friendEntry);
        this.m();
        OnlineFriendUiHelper.U();
    }

    public JsonArray toJson() {
        JsonArray jsonArray = new JsonArray();
        for (FriendEntry friendEntry : this.getFriends()) {
            if (!friendEntry.m()) continue;
            jsonArray.add((JsonElement)friendEntry.toJson());
        }
        return jsonArray;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public void r() {
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        RayTraceResult rayTraceResult = RayTraceUtil.o();
        if (rayTraceResult.isNull()) {
            return;
        }
        Entity entity = rayTraceResult.getEntity();
        if (entity.isNull()) {
            return;
        }
        if (entity.isInstance(MappedClasses.lG)) {
            EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(entity);
            String string = entityOtherPlayerMP.getName();
            ArrayList<FriendEntry> arrayList = this.C(string);
            if (arrayList.isEmpty()) {
                this.u(new Friend(string, string));
                Vape.INSTANCE.getNotificationManager().showInfo("\u00a7aAdded\u00a7r " + string + " to friends", "", 2000L);
            } else {
                this.E(arrayList.get(0));
                Vape.INSTANCE.getNotificationManager().showInfo("\u00a7cRemoved\u00a7r " + string + " from friends", "", 2000L);
            }
        }
    }

    @Nullable
    public FriendEntry O(String string) {
        return this.T(string, true);
    }

    public boolean isFriend(EntityLivingBase entityLivingBase) {
        boolean bl = this.E(entityLivingBase.getName());
        boolean bl2 = this.E(entityLivingBase.X$src$Ljava_util_UUID_$1o5dyg6().toString());
        boolean bl3 = bl || bl2;
        return bl3;
    }

    public boolean E(String string) {
        if (!this.z.getEffectiveValue().booleanValue()) {
            return false;
        }
        ArrayList<FriendEntry> arrayList = this.C(string);
        return !arrayList.isEmpty() && arrayList.stream().anyMatch(FriendEntry::c);
    }

    public ArrayList<FriendEntry> C(String string) {
        ArrayList<FriendEntry> arrayList = new ArrayList<FriendEntry>();
        for (FriendEntry friendEntry : this.E) {
            if (!friendEntry.s().equalsIgnoreCase(string)) continue;
            arrayList.add(friendEntry);
        }
        return arrayList;
    }

    public void u(FriendEntry friendEntry) {
        if (friendEntry == null) {
            return;
        }
        ArrayList<FriendEntry> arrayList = this.C(friendEntry.s());
        if (!arrayList.isEmpty()) {
            for (FriendEntry friendEntry2 : arrayList) {
                if (friendEntry instanceof ExternalFriend && !(friendEntry2 instanceof ExternalFriend) || !(friendEntry instanceof ExternalFriend) && friendEntry2 instanceof ExternalFriend) continue;
                this.E(friendEntry2);
            }
        }
        this.E.add(friendEntry);
        this.m();
        OnlineFriendUiHelper.U();
    }

    private void lambda$new$0(BooleanValue booleanValue) {
        this.m();
    }

    public void loadFriends(JsonArray jsonArray) {
        if (jsonArray.size() == 0) {
            return;
        }
        this.c();
        for (int i = 0; i < jsonArray.size(); ++i) {
            try {
                if (i > 100) break;
                JsonElement jsonElement = jsonArray.get(i);
                if (!jsonElement.isJsonObject() || jsonElement.isJsonNull()) continue;
                Friend friend = new Friend("", "").loadJson(jsonElement.getAsJsonObject());
                this.u(friend);
                continue;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public Set<FriendEntry> getFriends() {
        return this.E;
    }

    public void c() {
        this.getFriends().clear();
    }

    public FriendManager() {
        this.z = BooleanValue.create(this, "Use friends", true, "If enabled, any usernames inside your Minecraft friends list will be excluded from certain modules\nFor example they will not be targeted by KillAura");
        this.J = BooleanValue.create(this, "Use alias", true);
        this.C = BooleanValue.create(this, "Spoof alias", false, "Replace the friend's name in chat, tablist, and regular nametags with their alias.");
        this.q = BooleanValue.create(this, "Recolor visuals", true, "Re-colors certain render modules to use \"Friends Color\" on friends");
        this.R = ColorValue.create(this, "Friends Color", new Color(66, 244, 137));
        this.C.addChangeListener(this::lambda$new$0);
        this.q.addDependentValues(this.R);
        this.J.addDependentValues(this.C);
    }
}

