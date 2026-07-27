package gg.vape.manager.client;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.Profile;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileShareInfo;
import gg.vape.config.PublicProfileSummary;
import gg.vape.event.impl.PublicProfileCreatedEvent;
import gg.vape.event.impl.PublicProfileDeletedEvent;
import gg.vape.event.impl.PublicProfileTagsUpdatedEvent;
import gg.vape.notification.NotificationType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.UnmodifiableView;

public class PublicProfileManager {
    private final Map<String, String> a = new LinkedHashMap<String, String>();
    public static final int v;
    private final Map<Long, PublicProfile> g = new LinkedHashMap<Long, PublicProfile>();
    static final boolean m;


    private static ApiResponse lambda$new$1(Throwable throwable) {
        Vape.logThrowable(throwable);
        return null;
    }

    private void lambda$new$0(ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            return;
        }
        if (!m && apiResponse.T() == null) {
            throw new AssertionError();
        }
        this.K((Collection)apiResponse.T());
    }

    public List<Profile> T() {
        ArrayList<Profile> arrayList = new ArrayList<Profile>();
        for (PublicProfile publicProfile : this.g.values()) {
            Profile profile;
            PublicProfileShareInfo publicProfileShareInfo = publicProfile.c();
            if (publicProfileShareInfo == null || publicProfileShareInfo.v() == null || (profile = Vape.INSTANCE.getProfilesManager().H(publicProfileShareInfo.v())) == null) continue;
            arrayList.add(profile);
        }
        return arrayList;
    }

    public void Z(PublicProfile publicProfile) {
        this.l(publicProfile.X());
    }

    public void K(Collection<String> collection) {
        this.a.clear();
        this.l(collection);
    }

    public void I(PublicProfile publicProfile) {
        this.g.put(publicProfile.w(), publicProfile);
        if (!publicProfile.X().isEmpty()) {
            this.l(publicProfile.X());
        }
        new PublicProfileCreatedEvent(publicProfile).fire();
    }

    public PublicProfileManager() {
        ApiServices.d().R().F().whenCompleteAsync(this::lambda$new$0).exceptionally(PublicProfileManager::lambda$new$1);
    }

    public Collection<String> f() {
        return this.a.values();
    }

    public static void M(String string) {
        Vape.INSTANCE.getNotificationManager().K("Public Profiles", string, NotificationType.INFO, 5000L, true);
    }

    public void l(Collection<String> collection) {
        for (String string : collection) {
            if (this.a.containsKey(string.toLowerCase())) continue;
            this.a.put(string.toLowerCase(), string);
        }
        new PublicProfileTagsUpdatedEvent(collection).fire();
    }

    static {
        long l = 2496869938925404163L;
        v = (int)l;
        m = !PublicProfileManager.class.desiredAssertionStatus();
    }

    public void y(PublicProfileSummary publicProfileSummary) {
        this.l(publicProfileSummary.y());
    }

    public void m(PublicProfile publicProfile, PublicProfile publicProfile2) {
        PublicProfile publicProfile3 = this.g.put(publicProfile2.w(), publicProfile2);
        if (publicProfile3 == null) {
            this.I(publicProfile2);
            return;
        }
        if (!publicProfile2.X().isEmpty()) {
            this.l(publicProfile2.X());
        }
        new PublicProfileDeletedEvent(publicProfile).fire();
        new PublicProfileCreatedEvent(publicProfile2).fire();
    }

    public void Q(PublicProfile publicProfile) {
        this.g.remove(publicProfile.w());
        new PublicProfileDeletedEvent(publicProfile).fire();
    }

    public static void b(String string) {
        Vape.INSTANCE.getNotificationManager().K("Public Profiles", string, NotificationType.WARNING, 5000L, true);
    }

    public void j(Collection<PublicProfile> collection) {
        this.g.clear();
        for (PublicProfile publicProfile : collection) {
            this.g.put(publicProfile.w(), publicProfile);
        }
    }

    public @UnmodifiableView Map<Long, PublicProfile> A() {
        return this.g;
    }
}
