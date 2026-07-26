package gg.vape.friend.ui;

import gg.vape.friend.ui.CurrentPartyPanel;
import gg.vape.friend.ui.OnlineFriendEntriesPanel;
import gg.vape.friend.ui.PartyInviteCountBadge;
import gg.vape.friend.ui.PartyInvitesPanel;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.PanelComponent;

public class OnlineFriendsListPanel
extends PanelComponent {
    private static final String db;
    private final PartyInviteCountBadge BH;
    private final PartyInvitesPanel BA;
    private FlowLayoutComponent Br;
    private final CurrentPartyPanel B_;
    private final OnlineFriendEntriesPanel B0 = new OnlineFriendEntriesPanel();
    private static int[] B9;

    static {
        OnlineFriendsListPanel.L(new int[2]);
        db = "wrap";
    }

    public PartyInvitesPanel P() {
        return this.BA;
    }

    @Override
    public void c() {
        super.c();
        double d = this.L() - this.Br.L();
        this.B0.t(d);
    }

    public OnlineFriendEntriesPanel l$src$Lgg_vape_friend_ui_OnlineFriendEntriesPanel_$257n8u() {
        return this.B0;
    }

    public static void L(int[] nArray) {
        B9 = nArray;
    }

    public OnlineFriendsListPanel() {
        super(104.0, 130.0);
        this.Br = new FlowLayoutComponent(103.0);
        this.B_ = new CurrentPartyPanel();
        this.BH = new PartyInviteCountBadge();
        this.BA = new PartyInvitesPanel();
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(db);
        this.Br.h(this.B_, new Object[0]);
        this.Br.h(this.BH, new Object[0]);
        this.Br.h(this.BA, new Object[0]);
        this.B0.t(126.0);
        this.H(this.Br, this.B0);
    }

    public CurrentPartyPanel U$src$Lgg_vape_friend_ui_CurrentPartyPanel_$nthhyv() {
        return this.B_;
    }

    public static int[] e$src$AI$or91d3() {
        return B9;
    }
}

