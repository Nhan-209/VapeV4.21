package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.font.SmoothFontRenderer;

public class ProfileSnapshotModuleCountEmptyStateComponent
extends GuiComponent {
    private int K;
    private static final String b = "AFFECTED MODULES";

    @Override
    public void F() {
    }

    @Override
    public double C() {
        return 8.0;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public ProfileSnapshotModuleCountEmptyStateComponent(int n) {
        this.K = n;
    }

    @Override
    public void u() {
    }

    @Override
    public double x() {
        return 110.0;
    }

    @Override
    public void I() {
    }

    @Override
    public void H() {
        SmoothFontRenderer smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.8);
        SmoothFontRenderer smoothFontRenderer2 = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.8);
        smoothFontRenderer.d(this.K + " ", this.G$src$D$1b2f02a() + 10.0, this.n() + 1.0, ProfileSnapshotModuleCountEmptyStateComponent.J.A);
        smoothFontRenderer2.d(b, this.G$src$D$1b2f02a() + 10.0 + smoothFontRenderer.N(this.K + " "), this.n() + 1.0, ProfileSnapshotModuleCountEmptyStateComponent.J.h);
    }
}

