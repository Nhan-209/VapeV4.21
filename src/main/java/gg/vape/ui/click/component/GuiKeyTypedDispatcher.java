package gg.vape.ui.click.component;

import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiKeyTypedListener;

public class GuiKeyTypedDispatcher {
    public static void p(char c, int n) {
        ClientSettings clientSettings = Vape.INSTANCE.getModManager().getMod(ClientSettings.class);
        if (ClientSettings.fT != null) {
            for (GuiKeyTypedListener guiKeyTypedListener : ClientSettings.fT.b$src$Ljava_util_List_$1hubsov()) {
                guiKeyTypedListener.v(c, n);
            }
        }
    }
}

