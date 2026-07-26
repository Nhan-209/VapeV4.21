package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.AnchoredHudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrameHeaderActionComponent;

public class AnchoredHudModuleConfigCloseHeaderButton
extends PublicProfilesFrameHeaderActionComponent {
    final AnchoredHudModuleConfigFrame O;

    public AnchoredHudModuleConfigCloseHeaderButton(AnchoredHudModuleConfigFrame anchoredHudModuleConfigFrame, Frame frame, String string, String string2, double d) {
        super(frame, string, string2, d);
        this.O = anchoredHudModuleConfigFrame;
    }

    @Override
    public double L() {
        return 13.0;
    }

    @Override
    public double C() {
        return 0.0;
    }
}
