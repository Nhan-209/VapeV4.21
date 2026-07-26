package gg.vape.friend.ui;

import gg.vape.friend.ui.AddFriendInputPanel;
import gg.vape.friend.ui.FriendEntriesPanel;
import gg.vape.ui.click.component.PanelComponent;

public class FriendManagementPanel
extends PanelComponent {
    private final AddFriendInputPanel Te = new AddFriendInputPanel();
    private static final String db = "wrap, spanWidth";
    private final FriendEntriesPanel Tf = new FriendEntriesPanel();

    public FriendEntriesPanel w$src$Lgg_vape_friend_ui_FriendEntriesPanel_$r03ijp() {
        return this.Tf;
    }

    public FriendManagementPanel() {
        super(104.0, 135.0);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(db);
        this.H(this.Te, this.Tf);
    }

    @Override
    public void c() {
        super.c();
    }
}

