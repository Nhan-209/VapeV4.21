package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileSummary;
import gg.vape.manager.client.PublicProfileManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    final PublicProfileSummary p;
    final AtomicBoolean Q;
    final PublicProfilesFrame J;
    static final boolean c = !PublicProfilesFrame.class.desiredAssertionStatus();

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private static ApiResponse lambda$onClick$1(Throwable throwable) {
        Vape.logThrowable(throwable);
        return null;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        if (!this.Q.get()) {
            return;
        }
        this.Q.set(false);
        PublicProfileListingDetailsPanel publicProfileListingDetailsPanel = this.J.l((PublicProfile)null);
        publicProfileListingDetailsPanel.T(ApiServices.d().R().x(this.p.h()).whenCompleteAsync((arg_0, arg_1) -> this.lambda$onClick$0(this.Q, publicProfileListingDetailsPanel, this.p, arg_0, arg_1), (Executor)ClientSettings.f6).exceptionally(PublicProfileListingResultOpenClickHandler::lambda$onClick$1));
    }

    private void lambda$onClick$0(AtomicBoolean atomicBoolean, PublicProfileListingDetailsPanel publicProfileListingDetailsPanel, PublicProfileSummary publicProfileSummary, ApiResponse apiResponse, Throwable throwable) {
        atomicBoolean.set(true);
        if (publicProfileListingDetailsPanel.R$src$Ljava_util_concurrent_CompletableFuture_$1ccqvok().isCancelled()) {
            return;
        }
        publicProfileListingDetailsPanel.T((CompletableFuture<?>)null);
        if (throwable != null) {
            Vape.logThrowable(throwable);
            PublicProfilesFrame.k(this.J, publicProfileListingDetailsPanel.E());
            return;
        }
        if (!apiResponse.t()) {
            Vape.debugLog("Failed to get public response details of " + publicProfileSummary.h() + ": " + apiResponse.N());
            PublicProfileManager.b("Failed to view profile: " + apiResponse.N());
            PublicProfilesFrame.k(this.J, publicProfileListingDetailsPanel.E());
            return;
        }
        if (!c && apiResponse.T() == null) {
            throw new AssertionError();
        }
        Vape.INSTANCE.getPublicProfileManager().Z((PublicProfile)apiResponse.T());
        this.J.l((PublicProfile)apiResponse.T());
    }

    PublicProfileListingResultOpenClickHandler(PublicProfilesFrame publicProfilesFrame, AtomicBoolean atomicBoolean, PublicProfileSummary publicProfileSummary) {
        this.J = publicProfilesFrame;
        this.Q = atomicBoolean;
        this.p = publicProfileSummary;
    }
}
