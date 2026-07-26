package gg.vape.friend.ui;

import gg.vape.friend.ui.UsernameEditorPanel;
import gg.vape.ui.click.component.GuiClickListener;

public class UsernameEditorEditButtonClickHandler
implements GuiClickListener {
    final UsernameEditorPanel b;

    @Override
    public void P() {
        UsernameEditorPanel.t(this.b);
    }

    public UsernameEditorEditButtonClickHandler(UsernameEditorPanel m5_02) {
        this.b = m5_02;
    }
}

