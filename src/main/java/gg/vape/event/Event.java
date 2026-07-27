package gg.vape.event;

import gg.vape.event.EventBus;
import gg.vape.event.ICancelableEvent;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EntityRenderer;
import gg.vape.wrapper.impl.FontRenderer;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.WorldClient;

public abstract class Event
implements ICancelableEvent {
    private WorldClient o;
    private boolean u;
    private RenderManager D;
    private GuiScreen X;
    private EntityRenderer R;
    private FontRenderer k;
    private GameSettings J;
    private static String I;
    private EntityPlayerSP z;

    static {
        if (Event.Y() == null) {
            Event.b("Wfjubc");
        }
    }

    @Override
    public void setCancelled(boolean bl) {
        this.u = bl;
    }

    public static String Y() {
        return I;
    }

    @Override
    public boolean isCanceled() {
        return this.u;
    }

    public final EntityPlayerSP getThePlayer() {
        if (this.z == null) {
            this.z = Minecraft.thePlayer();
        }
        return this.z;
    }

    public final RenderManager getRenderManager() {
        if (this.D == null) {
            this.D = Minecraft.D();
        }
        return this.D;
    }

    public final GameSettings getGameSettings() {
        if (this.J == null) {
            this.J = Minecraft.gameSettings();
        }
        return this.J;
    }

    public final EntityRenderer getEntityRenderer() {
        if (this.R == null) {
            this.R = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf();
        }
        return this.R;
    }

    public final FontRenderer getFontRenderer() {
        if (this.k == null) {
            this.k = Minecraft.getFontRenderer();
        }
        return this.k;
    }

    @Override
    public boolean fire() {
        EventBus.getInstance().post(this);
        return this.isCanceled();
    }


    public final WorldClient getWorld() {
        if (this.o == null) {
            this.o = Minecraft.theWorld();
        }
        return this.o;
    }

    public final GuiScreen getCurrentScreen() {
        if (this.X == null) {
            this.X = Minecraft.currentScreen();
        }
        return this.X;
    }

    public static void b(String string) {
        I = string;
    }
}

