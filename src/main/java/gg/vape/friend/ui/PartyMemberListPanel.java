package gg.vape.friend.ui;

import gg.vape.friend.ui.PartyMemberRow;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.ui.click.layout.BottomUpFlowLayout;
import gg.vape.ui.click.layout.ComponentLayout;

public class PartyMemberListPanel
extends PanelComponent {
    private static int Au;
    private WrappingTextLabelComponent AI;

    public void b(PartyMemberRow partyMemberRow) {
        if (partyMemberRow.D$src$Z$qb6a9o()) {
            this.h(partyMemberRow, "alignright");
        } else {
            this.h(partyMemberRow, new Object[0]);
        }
    }

    @Override
    public void c() {
        super.c();
        if (this.f().isEmpty()) {
            this.AI.setShowDisabledOverlay(false);
            this.AI.setFontScale(0.75);
            this.AI.K(this.G$src$D$1b2f02a() + 5.0);
            this.AI.S(this.n() + (this.L() - 40.0) / 2.0);
            this.AI.c();
        }
    }

    static {
        PartyMemberListPanel.P(10);
    }

    public static int i$src$I$ovnrge() {
        return Au;
    }

    public static int p() {
        int n = PartyMemberListPanel.i$src$I$ovnrge();
        return 0;
    }

    public static void P(int n) {
        Au = n;
    }

    public PartyMemberListPanel(double d, double d2) {
        super(d, d2);
        this.AI = new WrappingTextLabelComponent("No messages yet\nMessages do not save and will clear when your game closes", 0.75, PartyMemberListPanel.J.Z);
        this.AI.setExplicitWidth(d - 10.0);
        this.t(this.L());
        this.N(true);
        this.setShowDisabledOverlay(false);
        this.N(new BottomUpFlowLayout(this));
        ComponentLayout componentLayout = this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij();
        componentLayout.t(false);
        componentLayout.M(false);
        componentLayout.U(false);
        componentLayout.I(false);
        componentLayout.u(false);
        this.w(true);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
    }

}

