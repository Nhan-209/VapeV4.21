package gg.vape.ui.click.frame.impl.main;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiSection;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class ClickGuiSectionTabComponent
extends InteractiveComponent {
    private boolean I;
    private final SimpleTextLabelComponent Q;
    private static final double K = 6.0;
    private final ClickGuiSection v;
    private static GuiComponent[] Mf;
    private static final double b = 1.0;

    public double V$src$D$1ysbxe7() {
        return this.Q.h() + 4.0;
    }

    public void E(boolean bl) {
        this.I = bl;
    }

    public ClickGuiSection B$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiSectio$14jx3m7() {
        return this.v;
    }

    static {
        if (ClickGuiSectionTabComponent.Z$src$ALgg_vape_ui_click_component_GuiComponent_$1n24vah() != null) {
            ClickGuiSectionTabComponent.u(new GuiComponent[3]);
        }
    }

    public static GuiComponent[] Z$src$ALgg_vape_ui_click_component_GuiComponent_$1n24vah() {
        return Mf;
    }

    public ClickGuiSectionTabComponent(ClickGuiSection clickGuiSection) {
        this.v = clickGuiSection;
        this.Q = new SimpleTextLabelComponent(clickGuiSection.A(), 0.75);
        this.Q.l(true);
        this.Q.T$src$V$1orl066(ClickGuiSectionTabComponent.J.h);
        this.Q.c(0);
        this.Q.g(0.0f);
        this.o(true);
        this.d(false);
        this.H(this.Q);
    }

    @Override
    public void H() {
        Color color;
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        double d3 = this.A();
        double d4 = this.L();
        if (this.I) {
            GuiRenderPrimitives.C(d, d2 + d4 + 0.5, d3, 1.0, ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1());
            color = ClickGuiSectionTabComponent.J.A;
        } else {
            color = this.w$src$Z$e457mb() ? ClickGuiSectionTabComponent.J.Z : ClickGuiSectionTabComponent.J.h;
        }
        this.Q.T$src$V$1orl066(color);
        double d5 = this.Q.y$src$D$idacv3();
        this.Q.K(d + 1.5);
        this.Q.S(d2 + d4 / 2.0 - d5 / 2.0 + 1.0);
        this.Q.o(this.A());
    }

    public static void u(GuiComponent[] guiComponentArray) {
        Mf = guiComponentArray;
    }
}

