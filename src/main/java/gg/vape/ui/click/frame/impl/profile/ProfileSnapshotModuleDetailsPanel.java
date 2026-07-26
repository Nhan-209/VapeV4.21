package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.ProfileModuleSnapshot;
import gg.vape.config.ProfileSnapshot;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.input.BindableInputComponent;
import java.util.List;

public class ProfileSnapshotModuleDetailsPanel
extends PanelComponent {
    private final SimpleTextLabelComponent Cr;
    private final PanelComponent C0 = new PanelComponent(192.0, 10.0);
    private final TextButton CT;
    private static int CP;
    private final BindableInputComponent CN;
    private ProfileModuleSnapshot Cl;
    private final TextButton C2;
    private final PanelComponent CH = new PanelComponent(CP, 144.0);
    private ProfileSnapshot Ce;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ProfileSnapshotModuleDetailsPanel() {
        super(CP, 170.0);
        this.Cr = new SimpleTextLabelComponent("Module Name");
        this.CT = new TextButton("ON", 0.633, ProfileSnapshotModuleDetailsPanel.J.l, ProfileSnapshotModuleDetailsPanel.J.l, 16.0, 10.0);
        this.CN = new BindableInputComponent(null, ProfileSnapshotModuleDetailsPanel.J.A);
        this.C2 = new TextButton("RESET THIS MODULE", 0.633, ProfileSnapshotModuleDetailsPanel.J.i, ProfileSnapshotModuleDetailsPanel.J.i, 52.0, 10.0);
        this.CH.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.CH.t(144.0);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("SETTINGS");
        simpleTextLabelComponent.T$src$V$1orl066(ProfileSnapshotModuleDetailsPanel.J.h);
        simpleTextLabelComponent.l(true);
        this.C2.R(ProfileSnapshotModuleDetailsPanel.J.l);
        this.C2.d(false);
        this.C2.u(0.75f);
        this.Cr.T$src$V$1orl066(ProfileSnapshotModuleDetailsPanel.J.A);
        this.Cr.g(0.0f);
        this.Cr.i(1.0);
        this.CN.Z(false);
        simpleTextLabelComponent.g(0.0f);
        this.C0.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.C0.h(this.Cr, new Object[0]);
        this.C0.h(new SpacerComponent(3.0, 0.0), new Object[0]);
        this.C0.h(this.CT, new Object[0]);
        this.C0.h(new SpacerComponent(3.0, 0.0), new Object[0]);
        this.C0.h(this.CN, new Object[0]);
        this.C0.h(this.C2, "alignright");
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.h(this.C0, new Object[0]);
        this.h(new SpacerComponent(0.0, 4.0), new Object[0]);
        this.h(simpleTextLabelComponent, new Object[0]);
        this.h(this.CH, new Object[0]);
        this.C2.r(this::lambda$new$0);
    }

    public void N(ProfileSnapshot profileSnapshot) {
        this.Ce = profileSnapshot;
    }

    private void lambda$new$0() {
        this.Ce.C().G(this.Cl);
    }

    static {
        long l = 2560396271919562958L;
        CP = (int)l;
    }

    public void K(ProfileModuleSnapshot profileModuleSnapshot) {
        this.Cl = profileModuleSnapshot;
        this.Cr.G(profileModuleSnapshot.getName());
        this.CT.d(profileModuleSnapshot.Q() ? "ON" : "OFF");
        if (profileModuleSnapshot.n()) {
            this.CN.Z(true);
            this.CN.r(profileModuleSnapshot.O().J());
        } else {
            this.CN.Z(false);
        }
        this.CH.S();
        List<GuiComponent> list = this.Ce.C().E(profileModuleSnapshot);
        for (GuiComponent guiComponent : list) {
            this.CH.h(guiComponent, new Object[0]);
        }
        this.H(true);
    }
}

