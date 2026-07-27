package gg.vape.ui.click.frame.impl.profile;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.AnimatedTextButtonComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOverlayPanelBase;

public class PublicProfileOverlayCloseButton
extends AnimatedTextButtonComponent {
    public PublicProfileOverlayCloseButton(String string, double d, PublicProfileOverlayPanelBase publicProfileOverlayPanelBase, Runnable runnable) {
        this(string, d, false, publicProfileOverlayPanelBase, runnable);
    }


    public PublicProfileOverlayCloseButton(String string, double d, boolean bl, PublicProfileOverlayPanelBase publicProfileOverlayPanelBase, Runnable runnable) {
        super(string, d, bl);
        this.N(true);
        this.s(() -> {
            if (!this.equals(publicProfileOverlayPanelBase.s$src$Lgg_vape_ui_click_frame_impl_profile_PublicProfi$urrnyv())) {
                ClientSettings.f6.execute(() -> {
                    runnable.run();
                    this.X(true);
                    publicProfileOverlayPanelBase.K(this);
                });
            }
        });
        if (bl) {
            runnable.run();
            publicProfileOverlayPanelBase.K(this);
        }
    }
}
