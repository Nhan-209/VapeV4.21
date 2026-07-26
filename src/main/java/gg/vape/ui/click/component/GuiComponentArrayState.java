package gg.vape.ui.click.component;

import gg.vape.ui.click.component.GuiComponent;

public class GuiComponentArrayState {
    private static GuiComponent[] w;

    public static GuiComponent[] d() {
        return w;
    }

    public static void W(GuiComponent[] upArray) {
        w = upArray;
    }

    static {
        if (GuiComponentArrayState.d() != null) {
            GuiComponentArrayState.W(new GuiComponent[4]);
        }
    }
}

