package gg.vape.friend;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiComponentContract;
import java.awt.Color;

public enum FriendRelationColorState {
    FRIEND(ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1()),
    SYNCED(GuiComponentContract.J.T),
    ENEMY(GuiComponentContract.J.d);

    Color s;
    private static final /* synthetic */ FriendRelationColorState[] M;

    private FriendRelationColorState(Color color) {
        this.s = color;
    }

    static {
        String[] stringArray = new String[]{"ENEMY", "FRIEND", "SYNCED"};



        M = new FriendRelationColorState[]{FRIEND, SYNCED, ENEMY};
    }

    public Color G() {
        return this.s;
    }

    public void y(Color color) {
        this.s = color;
    }
}

