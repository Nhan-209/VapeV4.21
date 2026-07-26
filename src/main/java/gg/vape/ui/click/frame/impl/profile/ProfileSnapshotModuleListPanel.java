package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.ProfileModuleSnapshot;
import gg.vape.config.ProfileSnapshot;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotApplyBarComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotFrame;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotModuleRowComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotModuleSearchInputComponent;
import gg.vape.value.ModuleNameSuggestionProvider;
import java.util.ArrayList;

public class ProfileSnapshotModuleListPanel
extends PanelComponent {
    private final PanelComponent rQ;
    private final ProfileSnapshotApplyBarComponent rH;
    private final ArrayList<ProfileSnapshotModuleRowComponent> r8;
    private final LabeledTextInputComponent rs = new ProfileSnapshotModuleSearchInputComponent(this, "Search modules...", false, true);
    private final IconButtonComponent rf = new IconButtonComponent("filter_search", 0.5, 16.0, 16.0);
    private final TruncatedTextComponent rZ;
    private boolean r9 = false;
    private ProfileSnapshot rB;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void h() {
        this.r8.clear();
        for (ProfileModuleSnapshot object : this.rB.Z(true)) {
            ProfileSnapshotModuleRowComponent profileSnapshotModuleRowComponent = new ProfileSnapshotModuleRowComponent(object);
            profileSnapshotModuleRowComponent.r(() -> this.lambda$updateModuleButtons$1(profileSnapshotModuleRowComponent, object));
            this.r8.add(profileSnapshotModuleRowComponent);
        }
        this.p();
        this.rQ.S();
        for (ProfileSnapshotModuleRowComponent profileSnapshotModuleRowComponent : this.r8) {
            this.rQ.h(profileSnapshotModuleRowComponent, new Object[0]);
        }
    }

    static LabeledTextInputComponent R(ProfileSnapshotModuleListPanel profileSnapshotModuleListPanel) {
        return profileSnapshotModuleListPanel.rs;
    }

    private void lambda$updateModuleButtons$1(ProfileSnapshotModuleRowComponent profileSnapshotModuleRowComponent, ProfileModuleSnapshot profileModuleSnapshot) {
        for (ProfileSnapshotModuleRowComponent profileSnapshotModuleRowComponent2 : this.r8) {
            profileSnapshotModuleRowComponent2.g(false);
        }
        profileSnapshotModuleRowComponent.g(true);
        ClientSettings.g(ProfileSnapshotFrame.class).I(profileModuleSnapshot);
    }

    static ArrayList<ProfileSnapshotModuleRowComponent> S(ProfileSnapshotModuleListPanel profileSnapshotModuleListPanel) {
        return profileSnapshotModuleListPanel.r8;
    }

    static void b(ProfileSnapshotModuleListPanel profileSnapshotModuleListPanel) {
        profileSnapshotModuleListPanel.p();
    }

    public ProfileSnapshotModuleListPanel() {
        super(108.0, 159.0);
        this.rH = new ProfileSnapshotApplyBarComponent(null, 100.0, true);
        this.rQ = new PanelComponent(108.0, 108.0);
        this.r8 = new ArrayList();
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.rZ = new TruncatedTextComponent("Profile Name", "...", 108.0, 1.2, ProfileSnapshotModuleListPanel.J.A, true);
        this.rZ.C(0.0);
        this.rZ.M(1.2);
        this.rs.O(0.0f);
        this.rs.H(0.0f);
        this.rs.o(88.0);
        this.rs.Y(16.0);
        this.rs.E(new ModuleNameSuggestionProvider());
        this.rf.l(ProfileSnapshotModuleListPanel.J.y);
        this.rH.T(2.0f);
        PanelComponent panelComponent = new PanelComponent(108.0, 20.0);
        panelComponent.h(this.rs, new Object[0]);
        panelComponent.h(new SpacerComponent(2.0, 0.0), new Object[0]);
        panelComponent.h(this.rf, new Object[0]);
        this.rQ.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.rQ.t(108.0);
        this.h(this.rZ, new Object[0]);
        this.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        this.h(panelComponent, new Object[0]);
        this.h(this.rH, new Object[0]);
        this.h(this.rQ, new Object[0]);
        this.rf.w("Toggle showing all modules or only modules with non-default values");
        this.rf.r(this::lambda$new$0);
    }

    @Override
    public void c() {
        super.c();
    }

    public void y(ProfileModuleSnapshot profileModuleSnapshot) {
        for (ProfileSnapshotModuleRowComponent profileSnapshotModuleRowComponent : this.r8) {
            profileSnapshotModuleRowComponent.g(profileSnapshotModuleRowComponent.b$src$Lgg_vape_config_ProfileModuleSnapshot_$6v7veg().equals(profileModuleSnapshot));
        }
    }

    public void d$src$V$sx0x0a() {
        InteractiveComponent interactiveComponent = null;
        for (ProfileSnapshotModuleRowComponent profileSnapshotModuleRowComponent : this.r8) {
            if (!profileSnapshotModuleRowComponent.V$src$Z$1xhop3l()) continue;
            interactiveComponent = profileSnapshotModuleRowComponent;
            break;
        }
        if (interactiveComponent != null) {
            interactiveComponent.P$src$V$q7uwbv();
            this.c(false);
        } else {
            this.r8.get(0).P$src$V$q7uwbv();
            this.c(true);
        }
    }

    private void lambda$new$0() {
        this.c(!this.r9);
    }

    private void c(boolean bl) {
        this.r9 = bl;
        this.rf.H(bl ? "filter_search_2" : "filter_search");
        this.rH.v(bl);
        this.p();
    }

    public void A(ProfileSnapshot profileSnapshot) {
        this.rB = profileSnapshot;
        this.rZ.O(profileSnapshot.d().n$src$Ljava_lang_String_$xqhelw());
        this.rH.K(profileSnapshot);
        this.h();
    }

    private void p() {
        for (ProfileSnapshotModuleRowComponent profileSnapshotModuleRowComponent : this.r8) {
            if (this.r9 || profileSnapshotModuleRowComponent.b$src$Lgg_vape_config_ProfileModuleSnapshot_$6v7veg().j()) {
                profileSnapshotModuleRowComponent.Z(true);
                continue;
            }
            profileSnapshotModuleRowComponent.Z(false);
        }
        this.H(true);
    }
}
