package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;

public class SplitPanelComponent
extends PanelComponent {
    private final PanelComponent GC;
    private static final String jb = "widthwrap";
    private final PanelComponent Gp;

    @Override
    public void o(double d) {
        super.o(d);
        this.j$src$V$aeemu9();
    }

    private void j$src$V$aeemu9() {
        if (this.Gp == null || this.GC == null) {
            return;
        }
        this.Gp.o(this.A() / 3.0);
        this.Gp.q(this.A() / 3.0);
        this.GC.q(this.A() - this.Gp.A() - 10.0);
    }

    @Override
    public void Y(double d) {
        super.Y(d);
        this.W();
    }

    public PanelComponent K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy() {
        return this.GC;
    }

    private void W() {
        if (this.Gp == null || this.GC == null) {
            return;
        }
        this.Gp.Y(this.L());
        this.Gp.u(this.L());
        this.GC.Y(this.L());
        this.GC.u(this.L());
    }

    private static ObfuscatedRuntimeException c(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SplitPanelComponent(double d, double d2, PanelComponent panelComponent, PanelComponent panelComponent2) {
        super(d, d2);
        this.Gp = panelComponent;
        this.GC = panelComponent2;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(jb);
        this.H(this.Gp, new SpacerComponent(10.0, 1.0), this.GC);
    }

    public PanelComponent X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j() {
        return this.Gp;
    }

    @Override
    public void H() {
        super.H();
    }
}

