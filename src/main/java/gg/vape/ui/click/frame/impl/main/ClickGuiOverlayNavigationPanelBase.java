package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import org.jetbrains.annotations.Nullable;

public class ClickGuiOverlayNavigationPanelBase
extends ClickGuiSidecarPanelBase {
    private static final double Jq = 12.0;
    private static final double Ji = 4.0;
    private static final double J5 = 20.0;
    private static final double Jh = 5.0;

    public ClickGuiOverlayNavigationPanelBase(@Nullable String string, @Nullable String string2, @Nullable Runnable runnable) {
        this.Y(20.0);
        if (string != null) {
            this.y(string);
        }
        if (string2 != null && !string2.isEmpty()) {
            this.B(string2);
        }
        this.v().P(null);
        this.y(runnable);
    }

}

