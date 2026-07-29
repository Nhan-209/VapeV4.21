package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.event.impl.EventPostTick;
import gg.vape.input.KeyboardInput;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.util.ThreadBoundExecutor;
import gg.vape.utils.render.shader.ShaderProgram;
import gg.vape.wrapper.impl.Minecraft;

public class EventTickBase
extends Event {
    private static final EventListeners U;
    private static GuiComponent[] b;
    public static final ThreadBoundExecutor p;
    public static final ThreadBoundExecutor S;

    public static void N(GuiComponent[] guiComponentArray) {
        b = guiComponentArray;
    }

    @Override
    public EventListeners getListeners() {
        return U;
    }

    public static EventListeners getEventListeners() {
        return U;
    }

    static {
        S = new ThreadBoundExecutor();
        p = new ThreadBoundExecutor();
        EventTickBase.N(null);
        U = new EventListeners();
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @Override
    public boolean fire() {
        try {
            if (Vape.INSTANCE.isTickActionPending()) {
                gg.vape.module.none.ClientSettings.INSTANCE.openGui();
                Vape.INSTANCE.setPendingTickAction(false);
            }
            if (Minecraft.theWorld().isNotNull()) {
                ClientSettings.d = true;
            }
            if ((KeyboardInput.isKeyDown(163) || KeyboardInput.isKeyDown(162) || KeyboardInput.isKeyDown(161)) && KeyboardInput.isKeyDown(36) && this instanceof EventPostTick && Minecraft.currentScreen().isNull()) {
                Vape.INSTANCE.getModManager().getMod(gg.vape.module.none.ClientSettings.class).F();
            }
            ShaderProgram.setCurrentProgramId(-1);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
        return super.fire();
    }

    public static GuiComponent[] f() {
        return b;
    }
}
