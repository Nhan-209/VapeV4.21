package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.ExternalFriend;
import gg.vape.friend.FriendEntry;
import gg.vape.friend.OnlineStatus;
import gg.vape.friend.ui.FriendListEntryRow;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.FrameScrollbarPlacement;
import java.util.List;

public class FriendEntriesPanel
extends PanelComponent {
    private static final String db;
    private double Ri;
    private static boolean Rx;
    private boolean R4;

    @Override
    public double x() {
        return 0.0;
    }

    public FriendEntriesPanel() {
        super(99.0, 110.0);
        this.F(FrameScrollbarPlacement.INSIDE);
        this.setShowDisabledOverlay(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(db);
    }

    @Override
    public void c() {
        super.c();
        if (this.R4) {
            this.w$src$V$h6k7cb();
            this.R4 = false;
        }
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public void n(FriendListEntryRow friendListEntryRow) {
        this.removeChild(friendListEntryRow);
    }

    private void w$src$V$h6k7cb() {
        this.removeMarkedChildren();
        for (FriendEntry friendEntry : Vape.INSTANCE.getFriendManager().getFriends()) {
            Object object;
            if (friendEntry instanceof ExternalFriend && (((ExternalFriend)(object = (ExternalFriend)friendEntry)).d().F() == OnlineStatus.OFFLINE || OnlineConnectionManager.T.Q$src$Z$x2tw73())) continue;
            object = new FriendListEntryRow(friendEntry);
            this.h((GuiComponent)object, new Object[0]);
        }
    }


    @Override
    public void V() {
        double d = 0.0;
        List<GuiComponent> list = this.f();
        for (GuiComponent guiComponent : list) {
            if (!guiComponent.V$src$Z$1xhop3l()) continue;
            d += guiComponent.L();
        }
        this.Ri = d;
        this.t(112.0);
    }

    public static boolean J$src$Z$ghtgqi() {
        return Rx;
    }

    @Override
    public void Y() {
    }

    @Override
    public void v() {
    }

    public void F(FriendListEntryRow friendListEntryRow) {
        this.h(friendListEntryRow, new Object[0]);
    }

    @Override
    public double C() {
        return this.Ri;
    }

    public void k$src$V$gzyo7z() {
        this.R4 = true;
    }

    public static void L(boolean bl) {
        Rx = bl;
    }

    public static boolean R() {
        boolean bl = FriendEntriesPanel.J$src$Z$ghtgqi();
        return false;
    }

    static {
        FriendEntriesPanel.L(true);
        db = "wrap";
    }
}

