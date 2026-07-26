package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrameHeaderActionComponent;

class PublicProfilesFrameHeaderComponent
extends PublicProfilesFrameHeaderActionComponent {
    final PublicProfilesFrame i;

    PublicProfilesFrameHeaderComponent(PublicProfilesFrame publicProfilesFrame, Frame frame, String string, String string2, double d) {
        super(frame, string, string2, d);
        this.i = publicProfilesFrame;
    }

    @Override
    public double x() {
        return this.i.x();
    }
}
