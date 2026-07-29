package gg.vape.tutorial;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.gui.UnderlinedTextLabel;
import gg.vape.ui.click.component.gui.WrappedTextComponent;
import gg.vape.ui.click.frame.FrameComponent;
import java.awt.Color;

public class TutorialActionComponent
extends FrameComponent {
    private final TextButton _J;
    private final SimpleTextLabelComponent _W;
    private final SimpleTextLabelComponent _v;
    private final UnderlinedTextLabel _i;

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public double x() {
        return 120.0;
    }

    public void H(String string) {
        this._v.setText(string);
    }

    @Override
    public void v() {
    }

    public void e(String string) {
        this._W.setText(string);
    }

    public UnderlinedTextLabel c$src$Lgg_vape_ui_click_component_gui_UnderlinedTextLa$npxkh1() {
        return this._i;
    }


    @Override
    public void Y() {
        this._v.S(this.n() + 3.0);
        this._W.S(this._v.n() + this._v.L() + 2.0);
        this._i.Y(12.0);
        this._i.o(40.0);
        this._i.setFontScale(0.7);
        this._i.setTextColor(new Color(90, 90, 90, 255));
        this._i.S(this.n() + this.L() - this._i.L());
        this._J.Y(12.0);
        this._J.o(32.0);
        this._J.K(this.G$src$D$1b2f02a() + this.A() - this._J.A() - 6.0);
        this._J.S(this.n() + this.L() - this._J.L() - 2.0);
        if (!this._J.getDisabledOverlayColor().equals(ClientSettings.INSTANCE.getAccentColor())) {
            this._J.setBackgroundAnimationColors(ClientSettings.INSTANCE.getAccentColor(), ClientSettings.INSTANCE.getAccentColor().brighter());
        }
    }

    public TextButton G$src$Lgg_vape_ui_click_component_gui_TextButton_$82emrx() {
        return this._J;
    }

    @Override
    public void V() {
    }

    @Override
    public double C() {
        double d = 16.0;
        if (this._W.getText().equals("")) {
            d = 12.0;
        }
        return this._v.L() + this._W.C() + d;
    }

    public TutorialActionComponent(String string, String string2) {
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this._v = new WrappedTextComponent(string, 1.0, new Color(209, 209, 209), true, 6.0);
        this._W = new WrappedTextComponent(string2, 0.9, new Color(140, 140, 140, 255), false, 6.0);
        this._i = new UnderlinedTextLabel("Skip tutorial");
        this._J = new TextButton("Ok", Color.BLACK);
        this._i.o(20.0);
        this._i.Y(20.0);
        this._i.setShowDisabledOverlay(true);
        this._i.setDisabledOverlayColor(Color.RED);
        this._J.o(20.0);
        this._J.Y(20.0);
        this._J.setShowDisabledOverlay(true);
        this._J.setDisabledOverlayColor(Color.RED);
        this.o(120.0);
        this._v.setUseExplicitWidth(true);
        this._v.o(120.0);
        this._W.setUseExplicitWidth(true);
        this._W.o(120.0);
        this._W.Y(0.0);
        this.addChildren(this._v, this._W, this._i, this._J);
        this.l$src$V$1mibm4x();
    }
}

