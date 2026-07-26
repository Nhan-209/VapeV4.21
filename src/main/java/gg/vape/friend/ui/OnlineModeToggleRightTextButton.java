package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineModeToggleComponent;
import gg.vape.ui.click.component.gui.TextButton;
import java.awt.Color;

public class OnlineModeToggleRightTextButton
extends TextButton {
    final OnlineModeToggleComponent YR;

    public OnlineModeToggleRightTextButton(OnlineModeToggleComponent onlineModeToggleComponent, String string, double d, Color color, Color color2) {
        super(string, d, color, color2);
        this.YR = onlineModeToggleComponent;
    }

    @Override
    public void H() {
    }
}
