package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayTransitionMode;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionConnectingPageComponent;

public final class ClickGuiOverlaySpecFactoryC {
    public static ClickGuiOverlaySpec Z() {
        return ClickGuiOverlaySpec.q().e("Connecting").C("connecting").N(ClickGuiOverlaySpecFactoryC::D).r(ClickGuiOverlayTransitionMode.REPLACE).w();
    }

    private static void D(PanelComponent panelComponent) {
        OnlineConnectionConnectingPageComponent onlineConnectionConnectingPageComponent = new OnlineConnectionConnectingPageComponent();
        for (GuiComponent guiComponent : onlineConnectionConnectingPageComponent.f()) {
            panelComponent.h(guiComponent, new Object[0]);
        }
    }

    private ClickGuiOverlaySpecFactoryC() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

