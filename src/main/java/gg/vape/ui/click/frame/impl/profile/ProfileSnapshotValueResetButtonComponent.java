package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.AnimatedRingIconButtonComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotGuiBuilder;
import gg.vape.value.ValueSnapshot;
import java.awt.Color;

class ProfileSnapshotValueResetButtonComponent
extends AnimatedRingIconButtonComponent {
    final ProfileSnapshotGuiBuilder eg;
    final GuiComponent eC;
    final ValueSnapshot eG;

    ProfileSnapshotValueResetButtonComponent(ProfileSnapshotGuiBuilder profileSnapshotGuiBuilder, String string, Color color, double d, double d2, double d3, GuiComponent guiComponent, ValueSnapshot valueSnapshot) {
        super(string, color, d, d2, d3);
        this.eg = profileSnapshotGuiBuilder;
        this.eC = guiComponent;
        this.eG = valueSnapshot;
    }

    @Override
    public void u() {
        super.u();
        this.Z(this.eC.V$src$Z$1xhop3l() && !this.eG.h());
    }

}
