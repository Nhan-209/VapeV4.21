package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.friend.ui.UsernameEditorPanel;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.notification.NotificationMessage;
import gg.vape.ui.notification.NotificationType;
import gg.vape.utils.ClipboardUtil;

public class UsernameCopyClickHandler
implements GuiClickListener {
    final UsernameEditorPanel x;

    @Override
    public void P() {
        ClipboardUtil.setText(Vape.INSTANCE.getOnlineManager().r().C());
        OnlineFriendUiHelper.P(new NotificationMessage(NotificationType.SUCCESS, "Copied " + Vape.INSTANCE.getOnlineManager().r().C() + " to clipboard"));
    }

    public UsernameCopyClickHandler(UsernameEditorPanel usernameEditorPanel) {
        this.x = usernameEditorPanel;
    }
}

