package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.Profile;
import gg.vape.config.ProfilesSyncPayloadBuilder;
import gg.vape.config.PublicProfile;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.AnimatedIconButtonComponent;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import java.util.Collections;
import java.util.concurrent.Executor;
import org.jetbrains.annotations.Nullable;

public class ProfileListEntryOpenButtonComponent
extends AnimatedIconButtonComponent {
    private Profile profile;
    @Nullable
    private PublicProfile publicProfile;
    private boolean selectedProfile;
    private boolean publishedProfile;
    @Nullable
    private Runnable afterDelete;

    private void refreshActionMode() {
        if (this.profile == null) {
            return;
        }
        this.selectedProfile = Vape.INSTANCE.getProfilesManager().M().equals(this.profile);
        this.publicProfile = this.profile.N();
        boolean nowPublished = this.publicProfile != null;
        if (nowPublished != this.publishedProfile) {
            this.publishedProfile = nowPublished;
            if (!this.publishedProfile) {
                if (this.selectedProfile) {
                    this.w("You cannot delete your selected profile");
                } else {
                    this.w("Delete this profile");
                }
                this.setIconResource("newtrash");
                this.getBackgroundAnimation().setEndColor(ProfileListEntryOpenButtonComponent.J.d);
            } else {
                this.setIconResource("newpublicprofiles");
                this.w("Open this published profile");
                this.getBackgroundAnimation().setEndColor(ProfileListEntryOpenButtonComponent.J.a);
            }
        }
    }


    private void handleClick() {
        if (this.profile == null) {
            return;
        }
        if (!this.publishedProfile) {
            if (this.selectedProfile) {
                return;
            }
            Vape.INSTANCE.getProfilesManager().S(this.profile);
            if (this.profile.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() != null && this.profile.P$src$Ljava_util_UUID_$kdhg08() != null) {
                ApiServices.d().c().F(ProfilesSyncPayloadBuilder.T(null, Collections.singletonList(this.profile.P$src$Ljava_util_UUID_$kdhg08())));
            }
            if (this.afterDelete != null) {
                this.afterDelete.run();
            }
        } else {
            FrameStackManager activeStack = ClientSettings.INSTANCE.getActiveStack();
            if (activeStack instanceof ClickGuiFrameManager) {
                this.openAsOverlay(this.publicProfile.w(), (ClickGuiFrameManager)activeStack);
            } else {
                PublicProfilesFrame.J(true, this.publicProfile.w());
            }
        }
    }

    private void openAsOverlay(long publicProfileId, ClickGuiFrameManager frameManager) {
        PublicProfilesFrame profilesFrame = ClientSettings.getFrame(PublicProfilesFrame.class);
        frameManager.setSidecarFrame(profilesFrame);
        ApiServices.d().R().x(publicProfileId)
            .whenCompleteAsync((response, error) -> handleProfileResponse(profilesFrame, response, error), (Executor)ClientSettings.UI_EXECUTOR)
            .exceptionally(ProfileListEntryOpenButtonComponent::ignoreLoadFailure);
    }

    public ProfileListEntryOpenButtonComponent(Profile profile, @Nullable Runnable afterDelete) {
        super("newtrash", ProfileListEntryOpenButtonComponent.J.d);
        this.profile = profile;
        this.afterDelete = afterDelete;
        this.setBorderRadius(2.0f);
        this.setBorderAlpha(1.0f);
        this.setIconScale(0.85);
        this.addClickListener(this::handleClick);
        this.refreshActionMode();
    }

    public ProfileListEntryOpenButtonComponent useOverlayStyle() {
        this.getBackgroundAnimation().setStartColor(ProfileListEntryOpenButtonComponent.J.l);
        this.setAnimatedBorderColor(ProfileListEntryOpenButtonComponent.J.l);
        this.setDisabledOverlayColor(ProfileListEntryOpenButtonComponent.J.m);
        return this;
    }

    @Override
    public void u() {
        this.refreshActionMode();
    }

    private static ApiResponse ignoreLoadFailure(Throwable error) {
        return null;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    private static void handleProfileResponse(PublicProfilesFrame profilesFrame, ApiResponse response, Throwable error) {
        if (error != null) {
            Vape.logThrowable(error);
            return;
        }
        if (!response.t()) {
            Vape.debugLog("Failed to load public profile data: " + response.N());
            return;
        }
        assert response.T() != null;
        profilesFrame.N((PublicProfile)response.T());
    }

    public Profile getProfile() {
        return this.profile;
    }
}
