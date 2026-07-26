package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineFriendActionPanel;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.BooleanValue;

class OnlineFriendNotificationsValue
extends BooleanValue {
    final OnlineFriendActionPanel y;

    @Override
    public Boolean L() {
        if (OnlineFriendActionPanel.O(this.y) == null) {
            return false;
        }
        return OnlineFriendActionPanel.O(this.y).y();
    }

    public void P(Boolean bl) {
        super.o(bl);
        OnlineFriendActionPanel.O(this.y).O(bl);
    }

    private static ObfuscatedRuntimeException e(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    OnlineFriendNotificationsValue(OnlineFriendActionPanel onlineFriendActionPanel, Object object, String string, boolean bl) {
        super(object, string, bl);
        this.y = onlineFriendActionPanel;
    }
}
