package gg.vape.friend.ui;

import gg.vape.friend.ui.UsernameEditorPanel;
import gg.vape.ui.click.component.GuiClickListener;

public class UsernameEditorEditModeToggleClickHandler
implements GuiClickListener {
    final UsernameEditorPanel T;

    public UsernameEditorEditModeToggleClickHandler(UsernameEditorPanel m5_02) {
        this.T = m5_02;
    }

    @Override
    public void P() {
        UsernameEditorPanel.t(this.T);
    }
}

