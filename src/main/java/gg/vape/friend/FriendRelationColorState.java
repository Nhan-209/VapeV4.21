package gg.vape.friend;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiComponentContract;
import java.awt.Color;

public enum FriendRelationColorState {
    FRIEND(ClientSettings.INSTANCE.getAccentColor()),
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

