package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.impl.ThemeComponentGroupFactory;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayTransitionMode;
import gg.vape.ui.theme.ThemeColors;

public final class ClickGuiFriendsThemeConfigFactory {
    private ClickGuiFriendsThemeConfigFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ClickGuiOverlaySpec O() {
        return ClickGuiOverlaySpec.q().e("Vape Online Settings").C("settings").N(ClickGuiFriendsThemeConfigFactory::C).r(ClickGuiOverlayTransitionMode.REPLACE).w();
    }

    private static void C(PanelComponent panelComponent) {
        GuiComponent[] guiComponentArray;
        for (GuiComponent guiComponent : guiComponentArray = ThemeComponentGroupFactory.k(ThemeColors.J)) {
            guiComponent.q(panelComponent.A() - 4.0);
            panelComponent.h(guiComponent, new Object[0]);
        }
    }
}

