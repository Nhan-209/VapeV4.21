package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOwnerDetailsPanel;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

class PublicProfileOwnerDetailsUnderlineIconComponent
extends GlyphIconComponent {
    final PublicProfileOwnerDetailsPanel G2;

    PublicProfileOwnerDetailsUnderlineIconComponent(PublicProfileOwnerDetailsPanel publicProfileOwnerDetailsPanel, String string, double d, double d2, double d3, double d4, Color color, Color color2, Color color3) {
        super(string, d, d2, d3, d4, color, color2, color3);
        this.G2 = publicProfileOwnerDetailsPanel;
    }

    @Override
    public void c() {
        super.c();
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() - 6.0, this.n(), 6.0, 1.0f, PublicProfileOwnerDetailsUnderlineIconComponent.J.y);
    }
}
