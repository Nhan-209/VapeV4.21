package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.Profile;
import gg.vape.config.ProfilesSyncPayloadBuilder;
import gg.vape.config.PublicProfile;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.AnimatedIconButtonComponent;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.jetbrains.annotations.Nullable;

public class ProfileListEntryOpenButtonComponent
extends AnimatedIconButtonComponent {
    private Profile qx;
    @Nullable
    private PublicProfile qJ;
    static final boolean qg = !ProfileListEntryOpenButtonComponent.class.desiredAssertionStatus();
    private boolean qp;
    private boolean qU;
    @Nullable
    private Runnable qf;

    private void z() {
        boolean bl;
        if (this.qx == null) {
            return;
        }
        this.qp = Vape.INSTANCE.getProfilesManager().M().equals(this.qx);
        this.qJ = this.qx.N();
        boolean bl2 = bl = this.qJ != null;
        if (bl != this.qU) {
            boolean bl3 = this.qU = this.qJ != null;
            if (!this.qU) {
                if (this.qp) {
                    this.w("You cannot delete your selected profile");
                } else {
                    this.w("Delete this profile");
                }
                this.H("newtrash");
                this.d$src$Lgg_vape_ui_click_animation_ColorAnimation_$10kme50().setEndColor(ProfileListEntryOpenButtonComponent.J.d);
            } else {
                this.H("newpublicprofiles");
                this.w("Open this published profile");
                this.d$src$Lgg_vape_ui_click_animation_ColorAnimation_$10kme50().setEndColor(ProfileListEntryOpenButtonComponent.J.a);
            }
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void d$src$V$1k3o55t() {
        if (this.qx == null) {
            return;
        }
        if (!this.qU) {
            if (this.qp) {
                return;
            }
            Vape.INSTANCE.getProfilesManager().S(this.qx);
            if (this.qx.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() != null && this.qx.P$src$Ljava_util_UUID_$kdhg08() != null) {
                ApiServices.d().c().F(ProfilesSyncPayloadBuilder.T(null, Collections.singletonList(this.qx.P$src$Ljava_util_UUID_$kdhg08())));
            }
            if (this.qf != null) {
                this.qf.run();
            }
        } else {
            FrameStackManager frameStackManager = ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
            if (frameStackManager instanceof ClickGuiFrameManager) {
                this.d(this.qJ.w(), (ClickGuiFrameManager)frameStackManager);
            } else {
                PublicProfilesFrame.J(true, this.qJ.w());
            }
        }
    }

    private void d(long l, ClickGuiFrameManager clickGuiFrameManager) {
        PublicProfilesFrame publicProfilesFrame = ClientSettings.g(PublicProfilesFrame.class);
        clickGuiFrameManager.K(publicProfilesFrame);
        ApiServices.d().R().x(l).whenCompleteAsync((arg_0, arg_1) -> ProfileListEntryOpenButtonComponent.lambda$openAsOverlay$0(publicProfilesFrame, arg_0, arg_1), (Executor)ClientSettings.f6).exceptionally(ProfileListEntryOpenButtonComponent::lambda$openAsOverlay$1);
    }

    public ProfileListEntryOpenButtonComponent(Profile profile, @Nullable Runnable runnable) {
        super("newtrash", ProfileListEntryOpenButtonComponent.J.d);
        this.qx = profile;
        this.qf = runnable;
        this.E(2.0f);
        this.A(1.0f);
        this.A(0.85);
        this.r(this::d$src$V$1k3o55t);
        this.z();
    }

    public ProfileListEntryOpenButtonComponent h$src$Lgg_vape_ui_click_frame_impl_profile_ProfileList$h82ue1() {
        this.d$src$Lgg_vape_ui_click_animation_ColorAnimation_$10kme50().setStartColor(ProfileListEntryOpenButtonComponent.J.l);
        this.X(ProfileListEntryOpenButtonComponent.J.l);
        this.T(ProfileListEntryOpenButtonComponent.J.m);
        return this;
    }

    @Override
    public void u() {
        this.z();
    }

    private static ApiResponse lambda$openAsOverlay$1(Throwable throwable) {
        return null;
    }

    public void m(Profile profile) {
        this.qx = profile;
    }

    private static void lambda$openAsOverlay$0(PublicProfilesFrame publicProfilesFrame, ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            Vape.debugLog("Failed to load public profile data: " + apiResponse.N());
            return;
        }
        if (!qg && apiResponse.T() == null) {
            throw new AssertionError();
        }
        publicProfilesFrame.N((PublicProfile)apiResponse.T());
    }

    public Profile g$src$Lgg_vape_config_Profile_$94fhm1() {
        return this.qx;
    }
}
