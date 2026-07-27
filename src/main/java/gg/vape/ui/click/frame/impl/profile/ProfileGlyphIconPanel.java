package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.Profile;
import gg.vape.config.PublicProfile;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import java.awt.Color;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.jetbrains.annotations.Nullable;

public class ProfileGlyphIconPanel
extends GlyphIconComponent {
    static final boolean nO;
    private Profile nL;
    private static final String ng;
    private static final String na;
    private static final double nh = 6.0;
    private static final double nt = 8.0;

    private void q(long l, ClickGuiFrameManager clickGuiFrameManager) {
        PublicProfilesFrame publicProfilesFrame = ClientSettings.g(PublicProfilesFrame.class);
        clickGuiFrameManager.K(publicProfilesFrame);
        ApiServices.d().R().x(l).whenCompleteAsync((arg_0, arg_1) -> ProfileGlyphIconPanel.lambda$openAsOverlay$1(publicProfilesFrame, arg_0, arg_1), (Executor)ClientSettings.f6).exceptionally(ProfileGlyphIconPanel::lambda$openAsOverlay$2);
    }


    public ProfileGlyphIconPanel(@Nullable Profile profile) {
        this(profile, 6.0, 8.0);
    }

    public Profile P$src$Lgg_vape_config_Profile_$1hoiw0e() {
        return this.nL;
    }

    private void lambda$setupClickListener$0() {
        if (this.nL == null || this.nL.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() == null) {
            return;
        }
        long l = this.nL.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0().u();
        FrameStackManager frameStackManager = ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
        if (frameStackManager instanceof ClickGuiFrameManager) {
            this.q(l, (ClickGuiFrameManager)frameStackManager);
        } else {
            PublicProfilesFrame.s(l);
        }
    }

    public void N(Profile profile) {
        this.nL = profile;
    }

    private static void lambda$openAsOverlay$1(PublicProfilesFrame publicProfilesFrame, ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            Vape.debugLog("Failed to load public profile data: " + apiResponse.N());
            return;
        }
        if (!nO && apiResponse.T() == null) {
            throw new AssertionError();
        }
        publicProfilesFrame.l((PublicProfile)apiResponse.T());
    }

    public void L$src$V$14lppcr() {
        if (this.nL != null && this.nL.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() != null) {
            this.Z(true);
            String string = this.nL.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0().O() ? "external link outdated hover@2x" : "external link hover@2x";
            this.W(string);
        } else {
            this.Z(false);
        }
    }

    static {
        na = "external link hover@2x";
        ng = "external link outdated hover@2x";
        boolean bl = ProfileGlyphIconPanel.class.desiredAssertionStatus();
        boolean bl2 = true;
        nO = false;
    }

    public ProfileGlyphIconPanel(@Nullable Profile profile, double d, double d2) {
        super("external link hover@2x", d, d, d2, d2, null, null, null);
        this.nL = profile;
        this.o(Color.WHITE);
        this.w("View public profile");
        this.L$src$V$14lppcr();
        this.Q$src$V$14ogobk();
    }

    private void Q$src$V$14ogobk() {
        this.k$src$V$qmpccm();
        this.r(this::lambda$setupClickListener$0);
    }

    private static ApiResponse lambda$openAsOverlay$2(Throwable throwable) {
        return null;
    }
}
