package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.friend.ui.PartyInviteCountBadge;
import gg.vape.ui.click.component.GuiClickListener;

class PartyInviteCountBadgeToggleInvitesClickHandler
implements GuiClickListener {
    final PartyInviteCountBadge I;

    @Override
    public void P() {
        OnlineFriendUiHelper.N(this.I).Y$src$Lgg_vape_friend_ui_PartyInvitesPanel_$1o49ve3().n$src$V$179hdlo();
    }

    PartyInviteCountBadgeToggleInvitesClickHandler(PartyInviteCountBadge partyInviteCountBadge) {
        this.I = partyInviteCountBadge;
    }
}

