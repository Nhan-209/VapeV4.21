package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileSummary;
import gg.vape.manager.client.PublicProfileManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.frame.impl.profile.PublicProfileListingDetailsPanel;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import java.awt.Point;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

class PublicProfileListingResultOpenClickHandler
implements GuiMouseListener {
    final PublicProfileSummary profileSummary;
    final AtomicBoolean clickPending;
    final PublicProfilesFrame profilesFrame;
    static final boolean ASSERTIONS_DISABLED = !PublicProfilesFrame.class.desiredAssertionStatus();


    private static ApiResponse lambda$onClick$1(Throwable throwable) {
        Vape.logThrowable(throwable);
        return null;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        if (!this.clickPending.get()) {
            return;
        }
        this.clickPending.set(false);
        PublicProfileListingDetailsPanel detailsPanel = this.profilesFrame.l((PublicProfile)null);
        detailsPanel.T(ApiServices.d().R().x(this.profileSummary.h()).whenCompleteAsync((response, error) -> this.handleProfileLoad(this.clickPending, detailsPanel, this.profileSummary, response, error), (Executor)ClientSettings.UI_EXECUTOR).exceptionally(PublicProfileListingResultOpenClickHandler::lambda$onClick$1));
    }

    private void handleProfileLoad(AtomicBoolean clickState, PublicProfileListingDetailsPanel detailsPanel, PublicProfileSummary summary, ApiResponse apiResponse, Throwable throwable) {
        clickState.set(true);
        if (detailsPanel.R$src$Ljava_util_concurrent_CompletableFuture_$1ccqvok().isCancelled()) {
            return;
        }
        detailsPanel.T((CompletableFuture<?>)null);
        if (throwable != null) {
            Vape.logThrowable(throwable);
            PublicProfilesFrame.k(this.profilesFrame, detailsPanel.E());
            return;
        }
        if (!apiResponse.t()) {
            Vape.debugLog("Failed to get public response details of " + summary.h() + ": " + apiResponse.N());
            PublicProfileManager.b("Failed to view profile: " + apiResponse.N());
            PublicProfilesFrame.k(this.profilesFrame, detailsPanel.E());
            return;
        }
        if (!ASSERTIONS_DISABLED && apiResponse.T() == null) {
            throw new AssertionError();
        }
        Vape.INSTANCE.getPublicProfileManager().Z((PublicProfile)apiResponse.T());
        this.profilesFrame.l((PublicProfile)apiResponse.T());
    }

    PublicProfileListingResultOpenClickHandler(PublicProfilesFrame publicProfilesFrame, AtomicBoolean atomicBoolean, PublicProfileSummary publicProfileSummary) {
        this.profilesFrame = publicProfilesFrame;
        this.clickPending = atomicBoolean;
        this.profileSummary = publicProfileSummary;
    }
}
