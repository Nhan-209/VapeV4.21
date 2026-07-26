package gg.vape.event.impl;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.render.hud.HudModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.GuiScreen;

public class EventGuiOpen
extends Event {
    private GuiScreen a;
    private static final EventListeners N = new EventListeners();

    @Override
    public boolean fire() {
        if (ClientSettings.d && Vape.INSTANCE.getClientSettings().T.L().booleanValue()) {
            ClientSettings.d = false;
            boolean bl = false;
            if (this.a.isInstance(MappedClasses.u5) || this.a.isInstance(MappedClasses.D6) || this.a.isInstance(MappedClasses.F_)) {
                for (Mod mod : Vape.INSTANCE.getModManager().collectMods()) {
                    if (mod instanceof HudModule || mod.getCategory() == Category.b || !mod.r$src$Z$14eylz9()) continue;
                    mod.Y(false);
                    bl = true;
                }
            }
            if (bl) {
                Vape.INSTANCE.getNotificationManager().k("Sanity Check", "All modules have been disabled!", 5000L);
            }
        }
        return super.fire();
    }

    public static EventListeners getEventListeners() {
        return N;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public GuiScreen getGuiScreen() {
        return this.a;
    }

    public Object getGuiScreenObject() {
        return this.a.getObject();
    }

    public EventGuiOpen(Object object) {
        this.a = new GuiScreen(object);
    }

    public void setGuiScreen(GuiScreen guiScreen) {
        this.a = guiScreen;
    }

    @Override
    public EventListeners getListeners() {
        return N;
    }
}

