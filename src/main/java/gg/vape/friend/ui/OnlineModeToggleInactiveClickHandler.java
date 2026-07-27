package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineModeToggleComponent;
import gg.vape.ui.click.component.GuiClickListener;

public class OnlineModeToggleInactiveClickHandler
implements GuiClickListener {
    final OnlineModeToggleComponent B;


    @Override
    public void P() {
        if (!this.B.r$src$Ljava_lang_Boolean_$180i77a().booleanValue()) {
            OnlineModeToggleComponent.w(this.B);
        }
    }

    public OnlineModeToggleInactiveClickHandler(OnlineModeToggleComponent onlineModeToggleComponent) {
        this.B = onlineModeToggleComponent;
    }
}

