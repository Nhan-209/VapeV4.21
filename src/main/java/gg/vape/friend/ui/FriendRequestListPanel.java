package gg.vape.friend.ui;

import gg.vape.friend.FriendRequest;
import gg.vape.friend.IncomingFriendRequest;
import gg.vape.friend.OutgoingFriendRequest;
import gg.vape.friend.ui.FriendRequestRow;
import gg.vape.friend.ui.FriendRequestRowsPanel;
import gg.vape.friend.ui.IncomingFriendRequestRow;
import gg.vape.friend.ui.OutgoingFriendRequestRow;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class FriendRequestListPanel
extends PanelComponent {
    private HashMap<FriendRequest, FriendRequestRow> ln;
    private final PanelComponent lT = new PanelComponent(100.0, 8.0);
    private FriendRequestRowsPanel lr;
    private final PanelComponent l2 = new PanelComponent(100.0, 90.0);

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void W() {
        ArrayList<FriendRequestRow> arrayList = new ArrayList<FriendRequestRow>(this.ln.values());
        arrayList.sort(Comparator.comparing(FriendRequestListPanel::lambda$refreshList$0));
        ArrayList<FriendRequestRow> arrayList2 = new ArrayList<FriendRequestRow>();
        for (FriendRequestRow friendRequestRow : arrayList) {
            if (!(friendRequestRow.y$src$Lgg_vape_friend_FriendRequest_$kilm25() instanceof IncomingFriendRequest)) continue;
            arrayList2.add(friendRequestRow);
        }
        for (FriendRequestRow friendRequestRow : arrayList) {
            if (!(friendRequestRow.y$src$Lgg_vape_friend_FriendRequest_$kilm25() instanceof OutgoingFriendRequest)) continue;
            arrayList2.add(friendRequestRow);
        }
        this.lr.S();
        this.lr.H(arrayList2.toArray(new GuiComponent[0]));
    }

    @Override
    public double C() {
        return this.lT.L() + (this.lr.d$src$D$ibccpu() + 2.0);
    }

    public void M(FriendRequest friendRequest) {
        FriendRequestRow friendRequestRow = this.ln.remove(friendRequest);
        if (friendRequestRow == null) {
            return;
        }
        this.W();
    }

    public HashMap<FriendRequest, FriendRequestRow> Z$src$Ljava_util_HashMap_$1f9jru6() {
        return this.ln;
    }

    public void z(FriendRequest friendRequest) {
        FriendRequestRow friendRequestRow = friendRequest instanceof IncomingFriendRequest ? new IncomingFriendRequestRow((IncomingFriendRequest)friendRequest) : new OutgoingFriendRequestRow((OutgoingFriendRequest)friendRequest);
        this.ln.put(friendRequest, friendRequestRow);
        this.W();
    }

    private static String lambda$refreshList$0(FriendRequestRow friendRequestRow) {
        return friendRequestRow.y$src$Lgg_vape_friend_FriendRequest_$kilm25().x().C();
    }

    public FriendRequestListPanel() {
        super(96.0, 16.0);
        this.ln = new HashMap();
        this.lr = new FriendRequestRowsPanel();
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.d(false);
        this.lT.d(false);
        this.lT.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.l2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.l2.H(new SpacerComponent(1.0, 2.0), this.lr);
        this.H(this.lT, this.l2);
    }

    @Override
    public double x() {
        return 100.0;
    }

    @Override
    public void c() {
        super.c();
        SmoothFontRenderer smoothFontRenderer = this.U$src$Lgg_vape_ui_font_SmoothFontRenderer_$16wbbnl(0.7);
        String string = this.lr.Z$src$I$1nljwqr() + " ";
        String string2 = "PENDING REQUESTS";
        double d = smoothFontRenderer.N(string) + 1.0;
        double d2 = (this.lT.L() - smoothFontRenderer.d(string2)) / 2.0;
        smoothFontRenderer.d(string, this.lT.G$src$D$1b2f02a() + 1.0, this.lT.n() + d2, FriendRequestListPanel.J.A);
        smoothFontRenderer.d(string2, this.lT.G$src$D$1b2f02a() + d + 1.0, this.lT.n() + d2, FriendRequestListPanel.J.h);
    }
}

