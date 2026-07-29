package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.OutgoingFriendRequest;
import gg.vape.friend.ui.FriendRequestRow;
import gg.vape.friend.ui.OutgoingFriendRequestCancelClickHandler;
import gg.vape.ui.font.SmoothFontRenderer;

public class OutgoingFriendRequestRow
extends FriendRequestRow {
    private static final String jb = "Requested";
    private final OutgoingFriendRequest Of;

    @Override
    public void H() {
        String string = jb;
        SmoothFontRenderer smoothFontRenderer = this.getAlternateFontRenderer(0.75);
        this.CG.setMaxWidth(this.A() - 6.0 - this.N$src$Lgg_vape_ui_click_component_IconButtonComponent_$t1119y().A() - smoothFontRenderer.N(string) - 7.0 - 2.0);
        super.H();
        smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + this.A() - smoothFontRenderer.N(string) - this.N$src$Lgg_vape_ui_click_component_IconButtonComponent_$t1119y().A() - 7.0, this.n() + (17.0 - smoothFontRenderer.d(string)) / 2.0, OutgoingFriendRequestRow.J.h);
    }

    public static void X(OutgoingFriendRequestRow outgoingFriendRequestRow) {
        outgoingFriendRequestRow.a$src$V$16j32e0();
    }

    public OutgoingFriendRequestRow(OutgoingFriendRequest outgoingFriendRequest) {
        super(outgoingFriendRequest, null);
        this.Of = outgoingFriendRequest;
        this.N$src$Lgg_vape_ui_click_component_IconButtonComponent_$t1119y().addClickListener(new OutgoingFriendRequestCancelClickHandler(this));
    }

    private void a$src$V$16j32e0() {
        Vape.INSTANCE.getOnlineManager().D().Y(this.Of);
    }
}
