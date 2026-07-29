package gg.vape.friend.ui;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.friend.ui.PartyInviteCountBadgeToggleInvitesClickHandler;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;

public class PartyInviteCountBadge
extends PanelComponent {
    int _n;
    PanelComponent _X;
    double _h;
    private TextLabel _e = new TextLabel("view all", 0.8, false, 20.0, 12.0);
    private TextButton _j;
    String _l = "Party Invites";
    PanelComponent _C;

    @Override
    public void c() {
        super.c();
    }

    public PartyInviteCountBadge() {
        super(100.0, 12.0);
        this._j = new TextButton("", 0.8, PartyInviteCountBadge.J.d, PartyInviteCountBadge.J.c, 8.5, 8.5);
        this._h = 0.0;
        this._X = new PanelComponent(80.0, 12.0);
        this._C = new PanelComponent(26.0, 12.0);
        this._n = 0;
        this.setShowDisabledOverlay(false);
        this._X.setShowDisabledOverlay(false);
        this._C.setShowDisabledOverlay(false);
        this._C.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this._e.addClickListener(new PartyInviteCountBadgeToggleInvitesClickHandler(this));
        this._C.addChildren(this._e);
        this.addChildren(this._X, this._C);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void u() {
        this._n = Vape.INSTANCE.getOnlineManager().y().n().size();
        this.setVisible(this._n > 1);
    }

    @Override
    public double x() {
        return 100.0;
    }

    public TextLabel t$src$Lgg_vape_ui_click_component_gui_TextLabel_$31po4r() {
        return this._e;
    }


    @Override
    public void H() {
        this._j.setVisible(false);
        SmoothFontRenderer smoothFontRenderer = this.getFontRenderer(0.7);
        smoothFontRenderer.d(this._l, this.G$src$D$1b2f02a(), this.n() + (this.L() - smoothFontRenderer.d(this._l)) / 2.0, PartyInviteCountBadge.J.Z);
        int n = 7;
        if (this._n > 99) {
            n = 10;
        }
        RectData rectData = new RectData(this.G$src$D$1b2f02a() + smoothFontRenderer.N(this._l) + 3.0, this.n() + 2.0, n, 7.0);
        GuiRenderPrimitives.e(rectData.o(), rectData.W(), rectData.e(), rectData.R(), PartyInviteCountBadge.J.d, false, 1.0f, 1.0f);
        String string = String.valueOf(this._n);
        smoothFontRenderer.d(string, rectData.o() + rectData.e() / 2.0 - smoothFontRenderer.N(string) / 2.0, this.n() + 2.5, PartyInviteCountBadge.J.A);
    }

    @Override
    public void F() {
    }

    @Override
    public void I() {
    }

    @Override
    public double C() {
        return this._h;
    }
}

