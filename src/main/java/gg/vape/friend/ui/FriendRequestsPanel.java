package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.FriendRequest;
import gg.vape.friend.FriendRequestService;
import gg.vape.friend.IncomingFriendRequest;
import gg.vape.friend.ui.FriendRequestListPanel;
import gg.vape.friend.ui.FriendRequestUsernameInputComponent;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.friend.ui.UsernameEditorPanel;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.notification.NotificationMessage;
import gg.vape.ui.notification.NotificationType;

public class FriendRequestsPanel
extends PanelComponent {
    private final FriendRequestListPanel L4;
    private final TextInputComponentBase LG;
    private final UsernameEditorPanel L6 = new UsernameEditorPanel();

    static void I(FriendRequestsPanel friendRequestsPanel, String string) {
        friendRequestsPanel.G(string);
    }

    public FriendRequestListPanel k$src$Lgg_vape_friend_ui_FriendRequestListPanel_$1poeaqd() {
        return this.L4;
    }

    public FriendRequestsPanel() {
        super(105.0, 150.0);
        this.LG = new FriendRequestUsernameInputComponent(this, "Add Vape friend...");
        this.T(FriendRequestsPanel.J.i);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.LG.Z(true);
        this.h(this.L6, new Object[0]);
        this.h(new SpacerComponent(1.0, 2.0), new Object[0]);
        this.h(this.LG, new Object[0]);
        this.L4 = new FriendRequestListPanel();
        this.h(new PaddedComponent(3.0, this.L4), new Object[0]);
    }

    private void G(String string) {
        String string2 = string;
        if (string2.isEmpty()) {
            return;
        }
        for (FriendRequest friendRequest : Vape.INSTANCE.getOnlineManager().D().I()) {
            if (!friendRequest.x().C().equals(string2)) continue;
            OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.SUCCESS, "Added " + string2 + " as a friend"));
            Vape.INSTANCE.getOnlineManager().D().N((IncomingFriendRequest)friendRequest);
            return;
        }
        FriendRequestService.Z(string2);
    }

    @Override
    public void c() {
        super.c();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

