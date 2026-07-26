package gg.vape.ui.click.component.input;

import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.input.BindableInputComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.Bendable;
import gg.vape.value.BindValue;
import java.awt.Color;

public class BindValueRowComponent
extends GuiComponent {
    private BindableInputComponent b;
    private boolean o;
    private String R;

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void I() {
    }

    @Override
    public double C() {
        return 20.0;
    }

    public BindValueRowComponent(BindValue bindValue) {
        this(bindValue.getName(), (Bendable)bindValue.K());
    }

    @Override
    public void H() {
        this.onDisable();
        SmoothFontRenderer smoothFontRenderer = this.O(0.9);
        double d = smoothFontRenderer.d(this.R);
        smoothFontRenderer.d(this.R, this.G$src$D$1b2f02a() + 5.0, this.n() + this.L() / 2.0 - d / 2.0, BindValueRowComponent.J.Z);
        this.b.Q$src$Lgg_vape_ui_click_component_TruncatedTextCompone$6s53nl().D(this.A() - 12.5);
        this.b.K(this.G$src$D$1b2f02a() + this.A() - 5.0 - this.b.A());
        this.b.S(this.n() + 5.0);
    }

    @Override
    public void F() {
    }

    public BindValueRowComponent(String string, Bendable bendable, Color color) {
        this.R = string;
        this.b = new BindableInputComponent(bendable, color);
        this.H(this.b);
    }

    @Override
    public void u() {
    }

    public BindValueRowComponent(String string, Bendable bendable) {
        this(string, bendable, null);
    }

    public BindableInputComponent K$src$Lgg_vape_ui_click_component_input_BindableInputC$1pa6t6b() {
        return this.b;
    }

    @Override
    public double x() {
        return 110.0;
    }
}

