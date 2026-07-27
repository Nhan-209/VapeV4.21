package gg.vape.event.listener;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.friend.ui.OnlinePlayerPreviewComponent;
import gg.vape.module.none.ClientSettings;
import gg.vape.utils.render.RenderUtils;

public class ClientSettingsEventForwarder
implements EventListener {
    private ClientSettings n;


    @EventHandler
    public void H(EventPreRenderTick eventPreRenderTick) {
        ClientSettings clientSettings = this.B();
        if (clientSettings == null) {
            return;
        }
        OnlinePlayerPreviewComponent.b.S(eventPreRenderTick);
        if (!this.n.P) {
            RenderUtils.C();
        }
        clientSettings.X(eventPreRenderTick);
    }

    private ClientSettings B() {
        if (this.n == null) {
            this.n = Vape.INSTANCE.getModManager().getMod(ClientSettings.class);
        }
        return this.n;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        ClientSettings clientSettings = this.B();
        if (clientSettings == null) {
            return;
        }
        clientSettings.d(eventPreTick);
    }
}

