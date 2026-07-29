package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.IncomingFriendRequest;
import gg.vape.friend.ui.FriendRequestRow;
import gg.vape.friend.ui.IncomingFriendRequestAcceptClickHandler;
import gg.vape.friend.ui.IncomingFriendRequestDeclineClickHandler;

public class IncomingFriendRequestRow
extends FriendRequestRow {
    private final IncomingFriendRequest Pm;

    public static void k(IncomingFriendRequestRow incomingFriendRequestRow) {
        incomingFriendRequestRow.U$src$V$gqrbie();
    }

    public IncomingFriendRequestRow(IncomingFriendRequest incomingFriendRequest) {
        super(incomingFriendRequest, null);
        this.Pm = incomingFriendRequest;
        this.N$src$Lgg_vape_ui_click_component_gui_TextButton_$8z76vj().addClickListener(new IncomingFriendRequestAcceptClickHandler(this));
        this.R$src$Lgg_vape_ui_click_component_PanelComponent_$61we3d().h(this.N$src$Lgg_vape_ui_click_component_gui_TextButton_$8z76vj(), new Object[0]);
        this.N$src$Lgg_vape_ui_click_component_IconButtonComponent_$t1119y().addClickListener(new IncomingFriendRequestDeclineClickHandler(this));
    }

    private void b$src$V$gxwn83() {
        Vape.INSTANCE.getOnlineManager().D().X(this.Pm);
    }

    private void U$src$V$gqrbie() {
        Vape.INSTANCE.getOnlineManager().D().N(this.Pm);
    }

    public static void v(IncomingFriendRequestRow incomingFriendRequestRow) {
        incomingFriendRequestRow.b$src$V$gxwn83();
    }
}
