package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiPlayerTabOverlayBridge;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.PlayerInfo;
import gg.vape.wrapper.impl.ScorePlayerTeam;
import gg.vape.wrapper.impl.ScorePlayerTeamTextComponent;
import gg.vape.wrapper.impl.TextComponent;
import gg.vape.wrapper.impl.TextComponentBaseBridge;

public class EventPlayerTabOverlayDisplayName
extends Event {
    private final Object f;
    private final Object M;
    private PlayerInfo C = null;
    private GuiPlayerTabOverlayBridge G = null;
    private static final EventListeners i = new EventListeners();
    private ITextComponent Q = null;
    private ITextComponent E = null;

    public ITextComponent getDisplayName() {
        if (this.Q != null) {
            return this.Q;
        }
        return this.getVanillaDisplayName();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static EventListeners getEventListeners() {
        return i;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public PlayerInfo getNetworkPlayerInfo() {
        if (this.C == null) {
            this.C = new PlayerInfo(this.M);
        }
        return this.C;
    }

    @Override
    public EventListeners getListeners() {
        return i;
    }

    public void setDisplayName(ITextComponent iTextComponent) {
        this.Q = iTextComponent;
        this.setCancelled(true);
    }

    private ITextComponent getVanillaDisplayName() {
        if (this.E != null) {
            return this.E;
        }
        PlayerInfo playerInfo = this.getNetworkPlayerInfo();
        ITextComponent iTextComponent = playerInfo.R();
        GuiPlayerTabOverlayBridge guiPlayerTabOverlayBridge = this.getGuiPlayerTabOverlay();
        if (iTextComponent.isNotNull()) {
            TextComponent textComponent = iTextComponent.h();
            this.E = ForgeVersion.MC_1_20_6.d() ? guiPlayerTabOverlayBridge.L(playerInfo, new TextComponentBaseBridge(textComponent.getObject())) : guiPlayerTabOverlayBridge.U(playerInfo, textComponent);
        } else {
            ScorePlayerTeam scorePlayerTeam = playerInfo.X();
            String string = playerInfo.v().getName();
            if (ForgeVersion.MC_1_20_6.d()) {
                TextComponentBaseBridge textComponentBaseBridge = ITextComponent.a(string);
                TextComponentBaseBridge textComponentBaseBridge2 = ScorePlayerTeam.j(scorePlayerTeam, textComponentBaseBridge);
                this.E = guiPlayerTabOverlayBridge.L(playerInfo, textComponentBaseBridge2);
            } else {
                ScorePlayerTeamTextComponent scorePlayerTeamTextComponent = ScorePlayerTeamTextComponent.B(string);
                TextComponent textComponent = ScorePlayerTeam.h(scorePlayerTeam, scorePlayerTeamTextComponent);
                this.E = guiPlayerTabOverlayBridge.U(playerInfo, textComponent);
            }
        }
        return this.E;
    }

    public Object getDisplayNameInstance() {
        return this.getDisplayName().getObject();
    }

    public EventPlayerTabOverlayDisplayName(Object object, Object object2) {
        this.f = object;
        this.M = object2;
    }

    public GuiPlayerTabOverlayBridge getGuiPlayerTabOverlay() {
        if (this.G == null) {
            this.G = new GuiPlayerTabOverlayBridge(this.f);
        }
        return this.G;
    }
}

