package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.module.none.ClientSettings;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;

public class EventRender2D
extends Event {
    private static final EventListeners Y = new EventListeners();
    private static int q;
    private static int A;

    public static EventListeners getEventListeners() {
        return Y;
    }

    public static void create() {
        if (ForgeVersion.MC_1_17.d() && Minecraft.thePlayer().isNull()) {
            return;
        }
        A = Minecraft.J();
        q = Minecraft.h();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GuiRenderPrimitives.o(A, q);
        Vape.INSTANCE.getModManager().getMod(ClientSettings.class).renderHudOverlay();
        EventRender2D eventRender2D = new EventRender2D();
        eventRender2D.fire();
        GuiRenderPrimitives.L(A, q);
    }


    @Override
    public EventListeners getListeners() {
        return Y;
    }

    public int getDisplayWidth() {
        return A;
    }

    public int getDisplayHeight() {
        return q;
    }
}

