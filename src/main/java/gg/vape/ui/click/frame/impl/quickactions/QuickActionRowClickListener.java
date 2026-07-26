package gg.vape.ui.click.frame.impl.quickactions;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.quickactions.QuickActionRowComponent;
import java.awt.Point;

class QuickActionRowClickListener
implements GuiMouseListener {
    final Class O;
    final QuickActionRowComponent K;

    QuickActionRowClickListener(QuickActionRowComponent quickActionRowComponent, Class clazz) {
        this.K = quickActionRowComponent;
        this.O = clazz;
    }

    @Override
    public void g(Point point, MouseClickButton uA) {
        Object t = ClientSettings.g(this.O);
        ClientSettings.T(this.O);
        ((Frame)ClientSettings.g(this.O)).c(((GuiComponent)t).V$src$Z$1xhop3l());
    }
}

