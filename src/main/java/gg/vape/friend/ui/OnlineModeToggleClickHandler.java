package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineModeToggleComponent;
import gg.vape.ui.click.component.GuiClickListener;

public class OnlineModeToggleClickHandler
implements GuiClickListener {
    final OnlineModeToggleComponent y;

    @Override
    public void onPrimaryClick() {
        if (this.y.r$src$Ljava_lang_Boolean_$180i77a().booleanValue()) {
            OnlineModeToggleComponent.w(this.y);
        }
    }

    public OnlineModeToggleClickHandler(OnlineModeToggleComponent onlineModeToggleComponent) {
        this.y = onlineModeToggleComponent;
    }

}

