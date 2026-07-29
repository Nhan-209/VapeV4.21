package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyMemberStatusComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.gui.WrappedTextComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class PartyMemberTextStatusComponent
extends PartyMemberStatusComponent {
    private double _k = 0.0;
    private WrappedTextComponent _Z;
    private PaddedComponent _t;
    private static GuiComponent[] _0;

    public static void K(GuiComponent[] guiComponentArray) {
        _0 = guiComponentArray;
    }

    public PartyMemberTextStatusComponent(String string) {
        this.setShowDisabledOverlay(false);
        GuiComponent[] guiComponentArray = new GuiComponent[1];
        this._Z = new WrappedTextComponent(string, 1.0);
        this._t = new PaddedComponent(3.0, this._Z);
        guiComponentArray[0] = this._t;
        this.addChildren(guiComponentArray);
        this._Z.setWrappingEnabled(false);
        this._Z.setWrapWidth(68.0);
        this._Z.setFontScale(0.75);
        this._Z.setTextColor(Color.WHITE);
    }

    @Override
    public boolean I$src$Z$19lcktz() {
        return true;
    }

    @Override
    public boolean boolean_I() {
        return this.I$src$Z$19lcktz();
    }

    static {
        if (PartyMemberTextStatusComponent.h() != null) {
            PartyMemberTextStatusComponent.K(new GuiComponent[1]);
        }
    }

    @Override
    public double x() {
        return this._t.A();
    }

    @Override
    public double C() {
        return this._t.L();
    }

    @Override
    public void c() {
        this._Z.setFontScale(0.8);
        GuiRenderPrimitives.d(this._t.G$src$D$1b2f02a(), this._t.n(), this._t.A(), this._t.L(), this.getDisabledOverlayColor());
        super.c();
        this._k = this._Z.C();
    }

    public static GuiComponent[] h() {
        return _0;
    }
}
