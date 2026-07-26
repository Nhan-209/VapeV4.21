package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.config.ProfileModuleSnapshot;
import gg.vape.config.ProfileSnapshot;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotModuleDetailsPanel;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotModuleListPanel;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class ProfileSnapshotFrame
extends Frame {
    private ProfileSnapshot Cz;
    private final ProfileSnapshotModuleDetailsPanel CE;
    @Nullable
    private FrameStackManager CT;
    private final PanelComponent C8;
    private static int Cq;
    private final ProfileSnapshotModuleListPanel Ci;
    private final PanelComponent CS = new PanelComponent(332.0, 182.0);
    private final IconButtonComponent CM;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void v() {
    }

    public static void f(int n) {
        Cq = n;
    }

    private void lambda$null$0() {
        this.o$src$V$tv7efs();
        this.K$src$V$tbet2s();
    }

    public void V(ProfileSnapshot profileSnapshot) {
        this.Cz = profileSnapshot;
        this.CE.N(profileSnapshot);
        this.Ci.A(profileSnapshot);
        this.Ci.d$src$V$sx0x0a();
    }

    public ProfileSnapshotFrame() {
        this.C8 = new PanelComponent(206.0, 182.0);
        this.CM = new SquareIconButtonComponent("newclose", 1.0, new Color(255, 255, 255, 0), new Color(255, 255, 255, 25), 10.0, 10.0);
        this.Ci = new ProfileSnapshotModuleListPanel();
        this.CE = new ProfileSnapshotModuleDetailsPanel();
        this.g(true);
        PaddedComponent paddedComponent = new PaddedComponent(4.0, 4.0, 4.0, 0.0, this.CS);
        this.h(paddedComponent, new Object[0]);
        PaddedComponent paddedComponent2 = new PaddedComponent(8.0, this.Ci);
        PanelComponent panelComponent = new PanelComponent(14.0, 10.0);
        panelComponent.h(this.CM, new Object[0]);
        this.CS.h(paddedComponent2, new Object[0]);
        this.CS.h(new SpacerComponent(2.0, 0.0), new Object[0]);
        this.CS.h(this.C8, new Object[0]);
        this.C8.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.C8.h(panelComponent, "alignright");
        this.C8.h(this.CE, new Object[0]);
        this.CM.r(this::lambda$new$2);
    }

    private void o$src$V$tv7efs() {
        this.Cz.D();
        Profile profile = Vape.INSTANCE.getProfilesManager().o();
        if (this.Cz.d() != null && this.Cz.d().equals(profile)) {
            Vape.INSTANCE.getProfilesManager().L(this.Cz.d());
        }
    }

    private void K$src$V$tbet2s() {
        FrameStackManager frameStackManager = this.CT;
        if (frameStackManager != null) {
            if (frameStackManager instanceof ClickGuiFrameManager) {
                ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)frameStackManager;
                clickGuiFrameManager.G();
            } else {
                ClientSettings.fW.I(frameStackManager);
            }
            this.CT = null;
        } else {
            ClientSettings.fW.I(ClientSettings.a);
        }
    }

    @Override
    public String getName() {
        return "profileEditor";
    }

    public static int j$src$I$tsgf5s() {
        return Cq;
    }

    @Override
    public void c() {
        this.H(true);
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a() - 0.5, this.n() - 0.5, this.A() + 1.0, this.L() + 1.0 + 2.0, ProfileSnapshotFrame.J.y, 2.0f, 0.8f, 1.0f);
        super.c();
    }

    @Nullable
    public FrameStackManager Q$src$Lgg_vape_ui_click_frame_FrameStackManager_$1lj7mlh() {
        return this.CT;
    }

    private void lambda$null$1() {
        this.K$src$V$tbet2s();
    }

    public void I(ProfileModuleSnapshot profileModuleSnapshot) {
        this.CE.K(profileModuleSnapshot);
        this.Ci.y(profileModuleSnapshot);
    }

    public ProfileSnapshot v$src$Lgg_vape_config_ProfileSnapshot_$1tlunqq() {
        return this.Cz;
    }

    public static int h() {
        int n = ProfileSnapshotFrame.j$src$I$tsgf5s();
        return 122;
    }

    public void A(@Nullable FrameStackManager frameStackManager) {
        this.CT = frameStackManager;
    }

    private void lambda$new$2() {
        ConfirmationDialogComponent.x(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), "Do you want to apply changes to profile?", "Apply", null, this::lambda$null$0, 80.0, "Discard", this::lambda$null$1);
    }

    static {
        ProfileSnapshotFrame.f(0);
    }

    public void s$src$V$txekt8() {
        if (this.Cz == null) {
            return;
        }
        this.Cz.C().H();
        this.Ci.h();
    }
}

