package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.Profile;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.input.BindableInputComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import gg.vape.ui.click.frame.impl.profile.ProfileGlyphIconPanel;
import gg.vape.unmap.Bendable;
import org.jetbrains.annotations.Nullable;

public class ProfilesPageOverlayController
extends ClickGuiSidecarPanelBase {
    @Nullable
    private Runnable DZ;
    private final BindableInputComponent DL;
    private final ProfileGlyphIconPanel DT;

    public ProfileGlyphIconPanel S$src$Lgg_vape_ui_click_frame_impl_profile_ProfileGlyp$1mj4u6r() {
        return this.DT;
    }

    public void E(boolean bl) {
        this.DL.w(bl);
    }

    @Override
    public void B(@Nullable String string) {
    }

    public void H(boolean bl) {
        this.DT.Z(bl);
    }

    @Override
    public void N(@Nullable Runnable runnable) {
        Runnable runnable2 = runnable != null ? runnable : this.DZ;
        super.C(runnable2);
        this.v().W("moduleback");
        this.v().Z(true);
        this.v().k$src$V$qmpccm();
        this.v().r$src$V$1x8vu68();
        this.k().Z(false);
    }

    public void u(boolean bl) {
        this.DL.Z(bl);
    }

    @Override
    public void y(@Nullable Runnable runnable) {
        this.DZ = runnable;
        super.y(runnable);
    }

    public void o(@Nullable Bendable bendable) {
        if (bendable != null) {
            this.DL.r(bendable);
        }
    }

    public ProfilesPageOverlayController(@Nullable Runnable runnable, @Nullable Profile profile) {
        this.DZ = runnable;
        this.k().Z(false);
        this.v().W("moduleback");
        this.v().Z(true);
        this.C(runnable);
        this.DT = new ProfileGlyphIconPanel(profile);
        this.DT.o(6.0);
        this.DT.Y(6.0);
        this.DL = new BindableInputComponent(profile, ProfilesPageOverlayController.J.A);
        this.DL.f(false);
        this.DL.Z(true);
        this.DL.o(10.0);
        this.DL.Y(10.0);
        this.H(this.DT, this.DL);
        this.e(this.DL);
        this.e(this.DT);
        this.X(4.0);
        super.y(runnable);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public BindableInputComponent W() {
        return this.DL;
    }
}

