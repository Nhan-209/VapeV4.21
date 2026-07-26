package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.config.ProfileModuleSnapshot;
import gg.vape.config.ProfileSnapshot;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.FrameScrollbarPlacement;
import gg.vape.ui.click.frame.impl.profile.ProfileModuleSnapshotListStyle;
import gg.vape.ui.click.frame.impl.profile.ProfileModuleSnapshotRowComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotApplyBarComponent;

public class ProfileModuleSnapshotListComponent
extends FlowLayoutComponent {
    private Profile bg;
    private long by = -1L;
    private final ProfileSnapshotApplyBarComponent b_;
    private final PanelComponent bc;
    private ProfileModuleSnapshotListStyle bZ = ProfileModuleSnapshotListStyle.LEGACY;

    public ProfileModuleSnapshotListStyle p() {
        return this.bZ;
    }

    public ProfileModuleSnapshotListComponent(Profile profile, double d, double d2) {
        super(d);
        this.bg = profile;
        this.d(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.bc = new PanelComponent(d, d2);
        this.bc.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.bc.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.bc.t(this.bc.L());
        this.bc.d(false);
        this.bc.F(FrameScrollbarPlacement.OUTSIDE);
        this.b_ = new ProfileSnapshotApplyBarComponent(null, d, false);
        this.h(this.b_, new Object[0]);
        this.h(this.bc, new Object[0]);
        this.L$src$V$1gx4mkb();
        this.by = profile.n();
    }

    private void k$src$V$1he68yi() {
        for (GuiComponent guiComponent : this.bc.f()) {
            if (!(guiComponent instanceof ProfileModuleSnapshotRowComponent)) continue;
            this.O((ProfileModuleSnapshotRowComponent)guiComponent);
        }
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void O(ProfileModuleSnapshotRowComponent profileModuleSnapshotRowComponent) {
        if (this.bZ == ProfileModuleSnapshotListStyle.MODERN) {
            profileModuleSnapshotRowComponent.v(ProfileModuleSnapshotListComponent.J.S, ProfileModuleSnapshotListComponent.J.l, ProfileModuleSnapshotListComponent.J.Z, ProfileModuleSnapshotListComponent.J.A);
        } else {
            profileModuleSnapshotRowComponent.v(ProfileModuleSnapshotListComponent.J.m, ProfileModuleSnapshotListComponent.J.l, ProfileModuleSnapshotListComponent.J.Z, ProfileModuleSnapshotListComponent.J.A);
        }
    }

    public void j(Profile profile) {
        if (profile == null) {
            return;
        }
        this.bg = profile;
        this.L$src$V$1gx4mkb();
        this.by = profile.n();
    }

    private void L$src$V$1gx4mkb() {
        this.bc.S();
        ProfileSnapshot profileSnapshot = this.bg.n(false);
        if (profileSnapshot != null) {
            for (ProfileModuleSnapshot profileModuleSnapshot : profileSnapshot.Z(false)) {
                ProfileModuleSnapshotRowComponent profileModuleSnapshotRowComponent = new ProfileModuleSnapshotRowComponent(this.A(), this.bg, profileSnapshot, profileModuleSnapshot);
                this.O(profileModuleSnapshotRowComponent);
                this.bc.h(profileModuleSnapshotRowComponent, new Object[0]);
            }
            this.b_.K(profileSnapshot);
        } else {
            this.b_.K((ProfileSnapshot)null);
        }
    }

    public void o(ProfileModuleSnapshotListStyle profileModuleSnapshotListStyle) {
        this.bZ = profileModuleSnapshotListStyle;
        this.k$src$V$1he68yi();
    }

    @Override
    public void u() {
        if (!this.bg.equals(Vape.INSTANCE.getProfilesManager().M())) {
            return;
        }
        long l = this.bg.n();
        if (l != this.by) {
            this.by = l;
            this.L$src$V$1gx4mkb();
        }
    }
}

