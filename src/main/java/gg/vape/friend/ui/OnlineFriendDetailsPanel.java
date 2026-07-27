package gg.vape.friend.ui;

import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineStatus;
import gg.vape.friend.ui.DirectFriendChatSender;
import gg.vape.friend.ui.OnlineChatPanel;
import gg.vape.friend.ui.OnlineFriendActionPanel;
import gg.vape.friend.ui.OnlineFriendCard;
import gg.vape.ui.click.component.PanelComponent;

public class OnlineFriendDetailsPanel
extends PanelComponent {
    private final OnlineFriend jx;
    private boolean jB = false;
    private OnlineChatPanel j9;
    private final OnlineFriendCard jO;
    private final OnlineFriendActionPanel jv;

    public OnlineFriendDetailsPanel(OnlineFriendCard onlineFriendCard, OnlineFriend onlineFriend) {
        super(99.0, onlineFriendCard.L());
        this.jx = onlineFriend;
        this.jv = new OnlineFriendActionPanel(onlineFriend);
        this.jO = onlineFriendCard;
        this.j9 = new OnlineChatPanel(new DirectFriendChatSender(onlineFriend));
        this.d(false);
        this.H(this.jv);
    }

    public OnlineChatPanel o$src$Lgg_vape_friend_ui_OnlineChatPanel_$15yewwy() {
        return this.j9;
    }

    public OnlineFriendActionPanel j$src$Lgg_vape_friend_ui_OnlineFriendActionPanel_$1bao1mp() {
        return this.jv;
    }

    @Override
    public double C() {
        return this.jB ? this.j9.L() : this.jv.L();
    }

    @Override
    public void c() {
        super.c();
        if (this.jx.F() == OnlineStatus.ONLINE) {
            this.j9.g$src$Lgg_vape_friend_ui_OnlineChatInputComponent_$1pydg2z().j(true);
        } else {
            this.j9.g$src$Lgg_vape_friend_ui_OnlineChatInputComponent_$1pydg2z().j(false);
        }
    }


    public void N$src$V$16a20lf() {
        this.jB = true;
        this.I(this.jv);
        this.H(this.j9);
        this.H(true);
    }

    public void K$src$V$168emtc() {
        this.jB = false;
        this.I(this.j9);
        this.H(this.jv);
        this.H(true);
    }
}

