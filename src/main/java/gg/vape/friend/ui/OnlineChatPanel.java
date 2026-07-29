package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineChatInputComponent;
import gg.vape.friend.ui.OnlineChatSender;
import gg.vape.friend.ui.PartyMemberListPanel;
import gg.vape.ui.click.component.PanelComponent;

public class OnlineChatPanel
extends PanelComponent {
    private PartyMemberListPanel QF = new PartyMemberListPanel(99.0, 80.0);
    private static final String db = "wrap";
    private OnlineChatInputComponent Qg;

    public OnlineChatInputComponent g$src$Lgg_vape_friend_ui_OnlineChatInputComponent_$1pydg2z() {
        return this.Qg;
    }

    @Override
    public void c() {
        super.c();
        double d = this.QF.L() + this.Qg.L();
        this.setExplicitHeight(d);
        this.Y(d);
        this.t(d + 1.0);
        this.setShowDisabledOverlay(false);
    }

    public PartyMemberListPanel z() {
        return this.QF;
    }

    public OnlineChatPanel(OnlineChatSender onlineChatSender) {
        super(99.0, 100.0);
        this.Qg = new OnlineChatInputComponent(this, onlineChatSender);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(db);
        this.addChildren(this.QF, this.Qg);
    }
}

