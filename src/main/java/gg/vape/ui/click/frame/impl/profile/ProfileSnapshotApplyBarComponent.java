package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.config.ProfileSnapshot;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotFrame;
import gg.vape.ui.font.SmoothFontRenderer;
import org.jetbrains.annotations.Nullable;

public class ProfileSnapshotApplyBarComponent
extends FlowLayoutComponent {
    private final TextLabel Jz;
    @Nullable
    private FrameStackManager JR;
    private int Jm;
    private float Ji = 6.0f;
    private boolean JN;
    private ProfileSnapshot J6;

    public ProfileSnapshot v$src$Lgg_vape_config_ProfileSnapshot_$e3ok07() {
        return this.J6;
    }

    public void K(ProfileSnapshot profileSnapshot) {
        this.J6 = profileSnapshot;
        this.d$src$V$zcunh4();
        this.Jz.Z(profileSnapshot != null);
        this.l$src$V$1mibm4x();
    }

    @Nullable
    public FrameStackManager Z$src$Lgg_vape_ui_click_frame_FrameStackManager_$5cq39t() {
        return this.JR;
    }

    private void d$src$V$zcunh4() {
        if (this.J6 != null) {
            this.Jm = this.J6.H(this.JN).size();
        }
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void lambda$null$0(ProfileSnapshotFrame profileSnapshotFrame) {
        profileSnapshotFrame.s$src$V$txekt8();
        this.d$src$V$zcunh4();
    }

    public void v(boolean bl) {
        this.JN = bl;
        this.Jm = this.J6.H(bl).size();
    }

    public ProfileSnapshotApplyBarComponent(ProfileSnapshot profileSnapshot, double d, boolean bl) {
        super(110.0);
        this.d(false);
        PanelComponent panelComponent = new PanelComponent(d, 12.0);
        this.H(new PaddedComponent(2.0, panelComponent));
        this.Jz = new TextLabel(bl ? "Reset all" : "edit all", 0.75, false);
        this.Jz.o(20.0);
        this.Jz.Y(14.0);
        panelComponent.h(this.Jz, "alignright");
        panelComponent.d(false);
        this.Jz.l(null);
        this.Jz.r(() -> {
            ProfileSnapshotFrame profileSnapshotFrame = ClientSettings.g(ProfileSnapshotFrame.class);
            if (bl) {
                ConfirmationDialogComponent.U(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), "Are you sure you want to reset all settings?", "Reset", "reset_circle", () -> this.lambda$null$0(profileSnapshotFrame));
            } else {
                Profile profile = Vape.INSTANCE.getProfilesManager().o();
                Profile profile2 = this.v$src$Lgg_vape_config_ProfileSnapshot_$e3ok07().d();
                profile.a();
                if (profile2 != null && profile2.equals(profile)) {
                    this.K(profile2.n(false));
                }
                profileSnapshotFrame.V(this.v$src$Lgg_vape_config_ProfileSnapshot_$e3ok07());
                if (ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v() instanceof ClickGuiFrameManager) {
                    ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
                    profileSnapshotFrame.A(clickGuiFrameManager);
                    clickGuiFrameManager.K(profileSnapshotFrame);
                } else {
                    profileSnapshotFrame.A(this.Z$src$Lgg_vape_ui_click_frame_FrameStackManager_$5cq39t());
                    ClientSettings.fW.I(ClientSettings.fr);
                }
            }
        });
        this.K(profileSnapshot);
    }

    public void T(float f) {
        this.Ji = f;
    }

    @Override
    public void c() {
        super.c();
        SmoothFontRenderer smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.8);
        SmoothFontRenderer smoothFontRenderer2 = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.8);
        double d = this.Jz.S$src$D$83wc3g() + 0.5;
        if (this.JN) {
            smoothFontRenderer.d(this.Jm + " ", this.G$src$D$1b2f02a() + (double)this.Ji, d, ProfileSnapshotApplyBarComponent.J.A);
            smoothFontRenderer2.d("MODULES", this.G$src$D$1b2f02a() + (double)this.Ji + smoothFontRenderer.N(this.Jm + " "), d, ProfileSnapshotApplyBarComponent.J.h);
        } else {
            smoothFontRenderer.d(this.Jm + " ", this.G$src$D$1b2f02a() + (double)this.Ji, d, ProfileSnapshotApplyBarComponent.J.A);
            smoothFontRenderer2.d("AFFECTED MODULES", this.G$src$D$1b2f02a() + (double)this.Ji + smoothFontRenderer.N(this.Jm + " "), d, ProfileSnapshotApplyBarComponent.J.h);
        }
    }

    public void M(@Nullable FrameStackManager frameStackManager) {
        this.JR = frameStackManager;
    }
}
