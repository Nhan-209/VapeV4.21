package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.GuiPlayerTabOverlayBridge;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.PlayerInfo;
import gg.vape.wrapper.impl.ScorePlayerTeam;

public class EventPlayerTabOverlayDisplayNameLegacy
extends Event {
    private final Object x;
    private String Y = null;
    private String v = null;
    private GuiPlayerTabOverlayBridge L = null;
    private final Object t;
    private PlayerInfo V = null;
    private static final EventListeners M = new EventListeners();

    @Override
    public EventListeners getListeners() {
        return M;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public PlayerInfo getNetworkPlayerInfo() {
        if (this.V == null) {
            this.V = new PlayerInfo(this.t);
        }
        return this.V;
    }

    public static EventListeners getEventListeners() {
        return M;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public String getDisplayName() {
        if (this.Y != null) {
            return this.Y;
        }
        return this.getVanillaDisplayName();
    }

    public void setDisplayName(String string) {
        this.Y = string;
        this.setCancelled(true);
    }

    public GuiPlayerTabOverlayBridge getGuiPlayerTabOverlay() {
        if (this.L == null) {
            this.L = new GuiPlayerTabOverlayBridge(this.x);
        }
        return this.L;
    }

    private String getVanillaDisplayName() {
        if (this.v != null) {
            return this.v;
        }
        PlayerInfo playerInfo = this.getNetworkPlayerInfo();
        ITextComponent iTextComponent = playerInfo.R();
        this.v = iTextComponent.isNotNull() ? iTextComponent.C() : ScorePlayerTeam.o(playerInfo.X(), playerInfo.v().getName());
        return this.v;
    }

    public EventPlayerTabOverlayDisplayNameLegacy(Object object, Object object2) {
        this.x = object;
        this.t = object2;
    }
}

