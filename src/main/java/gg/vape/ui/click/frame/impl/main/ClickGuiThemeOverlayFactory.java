package gg.vape.ui.click.frame.impl.main;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.frame.impl.ClientSettingsFrameSectionLabelComponent;
import gg.vape.ui.click.frame.impl.ThemeComponentGroupFactory;
import gg.vape.ui.click.frame.impl.ThemeComponentGroupKey;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayTransitionMode;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import gg.vape.ui.theme.ThemeColors;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ClickGuiThemeOverlayFactory {
    private static void lambda$null$1(String string, ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        clickGuiSidecarPanelBase.B(string != null ? string : "newsettings");
    }

    private static void lambda$buildContent$3(ThemeComponentGroupKey themeComponentGroupKey, String string, String string2, ClickGuiMainFrame clickGuiMainFrame) {
        LinkedHashMap<ThemeComponentGroupKey, GuiComponent[]> linkedHashMap = ThemeComponentGroupFactory.R(ThemeColors.J);
        GuiComponent[] guiComponentArray = linkedHashMap.get(themeComponentGroupKey);
        ClickGuiOverlaySpec clickGuiOverlaySpec = ClickGuiOverlaySpec.q().e(string).D(arg_0 -> ClickGuiThemeOverlayFactory.lambda$null$1(string2, arg_0)).N(arg_0 -> ClickGuiThemeOverlayFactory.lambda$null$2(guiComponentArray, arg_0)).r(ClickGuiOverlayTransitionMode.PUSH).w();
        if (clickGuiMainFrame != null) {
            clickGuiMainFrame.Z(clickGuiOverlaySpec);
        }
    }

    public static ClickGuiOverlaySpec m(ClickGuiMainFrame clickGuiMainFrame) {
        return ClickGuiOverlaySpec.q().e("Friends Settings").C("newsettings").N(arg_0 -> ClickGuiThemeOverlayFactory.lambda$createConfig$0(clickGuiMainFrame, arg_0)).r(ClickGuiOverlayTransitionMode.REPLACE).w();
    }

    private static void lambda$null$2(GuiComponent[] guiComponentArray, PanelComponent panelComponent) {
        if (guiComponentArray != null) {
            double d = Math.max(0.0, panelComponent.A());
            for (GuiComponent guiComponent : guiComponentArray) {
                guiComponent.o(d);
                guiComponent.q(d);
                panelComponent.h(guiComponent, new Object[0]);
            }
        }
    }

    private static void lambda$createConfig$0(ClickGuiMainFrame clickGuiMainFrame, PanelComponent panelComponent) {
        ClickGuiThemeOverlayFactory.V(panelComponent, clickGuiMainFrame);
    }

    private ClickGuiThemeOverlayFactory() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private static void V(PanelComponent panelComponent, ClickGuiMainFrame clickGuiMainFrame) {
        LinkedHashMap<ThemeComponentGroupKey, GuiComponent[]> linkedHashMap = ThemeComponentGroupFactory.R(ThemeColors.J);
        for (Map.Entry<ThemeComponentGroupKey, GuiComponent[]> entry : linkedHashMap.entrySet()) {
            ThemeComponentGroupKey themeComponentGroupKey = entry.getKey();
            String object = themeComponentGroupKey.h();
            String string = themeComponentGroupKey.u();
            ClientSettingsFrameSectionLabelComponent clientSettingsFrameSectionLabelComponent = new ClientSettingsFrameSectionLabelComponent(object);
            double guiComponent = Math.max(0.0, panelComponent.A());
            clientSettingsFrameSectionLabelComponent.o(guiComponent);
            clientSettingsFrameSectionLabelComponent.q(guiComponent);
            clientSettingsFrameSectionLabelComponent.Y(18.0);
            clientSettingsFrameSectionLabelComponent.r(() -> ClickGuiThemeOverlayFactory.lambda$buildContent$3(themeComponentGroupKey, object, string, clickGuiMainFrame));
            panelComponent.h(clientSettingsFrameSectionLabelComponent, new Object[0]);
        }
        GuiComponent[] guiComponentArray = ThemeComponentGroupFactory.E(ThemeColors.J);
        double d = Math.max(0.0, panelComponent.A());
        for (GuiComponent guiComponent : guiComponentArray) {
            guiComponent.o(d);
            guiComponent.q(d);
            panelComponent.h(guiComponent, new Object[0]);
        }
    }

    private static UnsupportedOperationException a(UnsupportedOperationException unsupportedOperationException) {
        return unsupportedOperationException;
    }
}

