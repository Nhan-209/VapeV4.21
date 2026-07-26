package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleSearchKeyHandler;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorFrame;

public class HudModuleSearchBox
extends GuiComponent {
    private LabeledTextInputComponent a = new LabeledTextInputComponent(i);
    private static final String i = "Search mods";

    @Override
    public void I() {
    }

    @Override
    public double C() {
        return 20.0;
    }

    public static LabeledTextInputComponent N(HudModuleSearchBox hudModuleSearchBox) {
        return hudModuleSearchBox.a;
    }

    @Override
    public double x() {
        return 132.0;
    }

    @Override
    public void F() {
    }

    @Override
    public void u() {
    }

    @Override
    public void H() {
        this.a.K(this.G$src$D$1b2f02a());
        this.a.S(this.n());
        this.a.o(this.x());
        this.a.Y(this.L());
    }

    public HudModuleSearchBox(HudModuleSelectorFrame hudModuleSelectorFrame) {
        this.a.o(new HudModuleSearchKeyHandler(this, hudModuleSelectorFrame));
        this.H(this.a);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }
}

