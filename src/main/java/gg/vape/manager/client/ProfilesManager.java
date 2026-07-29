package gg.vape.manager.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.BuiltinProfile;
import gg.vape.config.BuiltinProfileState;
import gg.vape.config.Minecraft121BuiltinProfile;
import gg.vape.config.Profile;
import gg.vape.config.ProfilesSyncPayloadBuilder;
import gg.vape.config.PublicProfile;
import gg.vape.event.EventBus;
import gg.vape.event.impl.ProfileChangeEvent;
import gg.vape.event.impl.ProfileListMutationAction;
import gg.vape.event.impl.ProfileListMutationEvent;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.none.Search;
import gg.vape.ui.click.component.value.AbstractListValueComponent;
import gg.vape.ui.click.component.value.FloatingValueDropdownLayer;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.ClientSettingsFrame;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;
import gg.vape.value.Value;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class ProfilesManager {
    private final List<Profile> V = new ArrayList<Profile>();
    static final boolean f = !ProfilesManager.class.desiredAssertionStatus();
    private Profile W;
    private final Set<UUID> C = new LinkedHashSet<UUID>();

    public void v() {
        for (Mod object : Vape.INSTANCE.getModManager().s()) {
            if (object.getCategory() == Category.b && !(object instanceof Search) || object.r$src$Z$14eylz9()) {
                // empty if block
            }
            if (object.a().usesOwnKeybindStorage()) {
                object.a().getBoundInputs().clear();
                if (object.M$src$I$13um7m9() != 0) {
                    object.a().getBoundInputs().add(object.M$src$I$13um7m9());
                }
            }
            object.C(object.b());
            for (Value<?, ?> value : object.V()) {
                value.reset();
            }
        }
        ClientSettings.refreshModuleCategoryHeaders();
        for (Value value : Vape.INSTANCE.getValueManager().getValues()) {
            if (value.getDefaultValue() == null) continue;
            value.reset();
        }
        for (Frame frame : ClientSettings.getAllFrames()) {
            if (frame instanceof ClientSettingsFrame) {
                ((ClientSettingsFrame)frame).d$src$V$16knweo();
                continue;
            }
            if (!(frame instanceof FloatingValueDropdownLayer)) continue;
            ((AbstractListValueComponent)((FloatingValueDropdownLayer)frame).getSourceComponent()).setExpanded(false);
        }
    }

    public List<Profile> b() {
        return this.V;
    }

    public Profile H(UUID uUID) {
        for (Profile profile : this.V) {
            if (profile.P$src$Ljava_util_UUID_$kdhg08() == null || !profile.P$src$Ljava_util_UUID_$kdhg08().toString().equalsIgnoreCase(uUID.toString())) continue;
            return profile;
        }
        return null;
    }

    public void S(Profile profile) {
        this.V.remove(profile);
        ClientSettings.getFrame(ProfilesSettingsFrame.class).removeProfile(profile);
        Vape.INSTANCE.saveAndStop();
        new ProfileListMutationEvent(profile, ProfileListMutationAction.REMOVE).fire();
        if (profile.P$src$Ljava_util_UUID_$kdhg08() != null) {
            this.C.add(profile.P$src$Ljava_util_UUID_$kdhg08());
        }
    }

    public void m(Profile profile, boolean bl) {
        if (bl) {
            this.V.add(0, profile);
            for (Profile profile2 : this.V) {
                profile2.U(profile2.L$src$I$1g3udot());
            }
        } else {
            this.V.add(profile);
        }
        ClientSettings.getFrame(ProfilesSettingsFrame.class).addProfile(profile);
        ProfilesSettingsFrame.refreshProfileList();
        Vape.INSTANCE.saveAndStop();
        new ProfileListMutationEvent(profile, ProfileListMutationAction.ADD).fire();
    }

    public JsonObject s(Profile profile) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add((JsonElement)profile.C(true));
        jsonObject.add("updatedProfiles", (JsonElement)jsonArray);
        jsonObject.add("deletedProfiles", (JsonElement)new JsonArray());
        return jsonObject;
    }

    public void C(JsonObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        if (jsonObject.entrySet().isEmpty()) {
            Profile profile = this.L();
            if (profile != null) {
                this.L(profile);
            }
            return;
        }
        for (Map.Entry entry : jsonObject.entrySet()) {
            JsonObject jsonObject2 = ((JsonElement)entry.getValue()).getAsJsonObject();
            Profile profile = new Profile("", "", true).e(jsonObject2);
            this.T(profile);
        }
        try {
            this.V.sort(ProfilesManager::lambda$fromJson$0);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        ProfilesSettingsFrame.refreshProfileList();
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    private Profile L() {
        ArrayList<BuiltinProfile> arrayList = new ArrayList<BuiltinProfile>();
        arrayList.add(new Minecraft121BuiltinProfile());
        arrayList.add(new BuiltinProfileState());
        Profile profile = null;
        for (BuiltinProfile builtinProfile : arrayList) {
            builtinProfile.J();
            this.T(builtinProfile);
            if (profile != null || !builtinProfile.E()) continue;
            profile = builtinProfile;
        }
        if (profile == null && !this.V.isEmpty()) {
            profile = this.V.get(0);
        }
        return profile;
    }

    private static int lambda$fromJson$0(Profile profile, Profile profile2) {
        if (profile.J$src$Ljava_lang_Integer_$vutkyf() != null && profile2.J$src$Ljava_lang_Integer_$vutkyf() != null) {
            return profile.J$src$Ljava_lang_Integer_$vutkyf().compareTo(profile2.J$src$Ljava_lang_Integer_$vutkyf());
        }
        if (profile.J$src$Ljava_lang_Integer_$vutkyf() != null) {
            return 1;
        }
        return -1;
    }

    public Profile G(String string) {
        for (Profile profile : this.V) {
            if (!profile.n$src$Ljava_lang_String_$xqhelw().equalsIgnoreCase(string)) continue;
            return profile;
        }
        return null;
    }

    public JsonObject q(boolean bl) {
        if (bl) {
            ArrayList<Profile> arrayList = new ArrayList<Profile>();
            for (Profile profile : this.b()) {
                if (!profile.W()) continue;
                arrayList.add(profile);
            }
            ArrayList<UUID> arrayList2 = new ArrayList<UUID>(this.C);
            this.C.clear();
            return ProfilesSyncPayloadBuilder.T(arrayList, (List<UUID>)arrayList2);
        }
        JsonObject jsonObject = new JsonObject();
        for (Profile profile : this.b()) {
            jsonObject.add(profile.u().toString(), (JsonElement)profile.C(bl));
        }
        return jsonObject;
    }

    public void X() {
        this.V.clear();
    }

    public void T(Profile profile) {
        this.m(profile, false);
    }

    public void T() {
        Collection<PublicProfile> collection = Vape.INSTANCE.getPublicProfileManager().A().values();
        for (Profile profile : this.V) {
            profile.f(null);
            for (PublicProfile publicProfile : collection) {
                UUID uUID;
                if (publicProfile.c() == null || (uUID = publicProfile.c().v()) == null || profile.P$src$Ljava_util_UUID_$kdhg08() == null || !profile.P$src$Ljava_util_UUID_$kdhg08().toString().equalsIgnoreCase(uUID.toString())) continue;
                profile.f(publicProfile);
            }
        }
    }

    public void L(Profile profile) {
        if (this.W != null && this.W.equals(profile)) {
            return;
        }
        Profile profile2 = this.W;
        this.v();
        this.W = profile;
        this.W.e();
        Vape.INSTANCE.saveAndStop();
        EventBus.getInstance().post(new ProfileChangeEvent(profile2, profile));
    }

    public void U(Profile profile) {
        if (this.M() != null) {
            this.M().a();
        }
        this.L(profile);
    }

    public void M(Profile profile) {
        Profile profile2 = this.M();
        if (profile.equals(profile2)) {
            profile.a();
        } else {
            this.L(profile);
            profile.a();
            this.L(profile2);
        }
    }

    public List<Profile> s() {
        ArrayList<Profile> arrayList = new ArrayList<Profile>();
        for (Profile profile : this.V) {
            if (profile.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() == null) continue;
            arrayList.add(profile);
        }
        return arrayList;
    }

    public Profile M() {
        boolean bl;
        boolean bl2 = bl = this.W == null || !this.b().contains(this.W) && !this.W.Z();
        if (bl) {
            if (!this.b().isEmpty()) {
                this.L(this.b().get(0));
            } else {
                Profile profile = this.L();
                this.L(profile);
            }
        }
        return this.W;
    }

    @Nullable
    public Profile X(long l) {
        for (Profile profile : this.V) {
            if (profile.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() == null) continue;
            if (!f && profile.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() == null) {
                throw new AssertionError();
            }
            if (profile.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0().u() != l) continue;
            return profile;
        }
        return null;
    }

    public void H() {
        this.V.sort(Profile::z);
    }

    public Profile o() {
        return this.W;
    }

    public Profile X(UUID uUID) {
        for (Profile profile : this.V) {
            if (!profile.u().toString().equalsIgnoreCase(uUID.toString())) continue;
            return profile;
        }
        return null;
    }
}
