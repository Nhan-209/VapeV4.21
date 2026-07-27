package gg.vape.status;

import gg.vape.Vape;
import gg.vape.module.none.TextGuiSettings;
import gg.vape.runtime.NativeBridge;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.TimerUtil;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.ServerData;

public class NativePresenceUpdater {
    private final TimerUtil D = new TimerUtil();
    private static GuiComponent[] R;
    private String Q;
    private String o;

    public void X(boolean bl) {
        String string;
        if (!this.D.hasTimeElapsed(1000L)) {
            return;
        }
        this.D.reset();
        if (!bl) {
            NativeBridge.updc(null, null);
            return;
        }
        ServerData serverData = Minecraft.H();
        String string2 = "Not in a server";
        if (serverData.isNotNull()) {
            string2 = "Playing legit on " + serverData.f();
        }
        if ((string = Vape.INSTANCE.getModManager().getMod(TextGuiSettings.class).z()).length() >= 128) {
            string = string.substring(0, 128);
        }
        if (!string2.equals(this.Q) || !this.o.equals(string)) {
            NativeBridge.updc(string2, string);
        }
        this.Q = string2;
        this.o = string;
    }

    static {
        NativePresenceUpdater.U(new GuiComponent[5]);
    }


    public static void U(GuiComponent[] guiComponentArray) {
        R = guiComponentArray;
    }

    public static GuiComponent[] F() {
        return R;
    }
}

