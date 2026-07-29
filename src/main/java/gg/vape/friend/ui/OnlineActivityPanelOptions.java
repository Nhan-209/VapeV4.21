package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.event.impl.EventPreAttack;
import gg.vape.friend.OnlineFriendActivityState;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineActivitySettingsFrame;
import gg.vape.input.MouseClickRateTracker;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.none.ClientSettings;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.Minecraft;
import java.util.ArrayList;
import java.util.List;

public class OnlineActivityPanelOptions
implements EventListener {
    public static final OnlineActivityPanelOptions p = new OnlineActivityPanelOptions();
    private final BooleanValue H;
    private final BooleanValue D = BooleanValue.create(this, "Render Background", true);

    public BooleanValue i() {
        return this.H;
    }

    @EventHandler
    public void K(EventPreAttack eventPreAttack) {
        OnlineActivitySettingsFrame onlineActivitySettingsFrame = ClientSettings.getFrame(OnlineActivitySettingsFrame.class);
        if (onlineActivitySettingsFrame == null) {
            return;
        }
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        if (eventPreAttack.getTarget().isInstance(MappedClasses.zc)) {
            Vape.INSTANCE.getOnlineManager().r().E().P(eventPreAttack.getTarget().S());
        }
    }


    public OnlineActivityPanelOptions() {
        this.H = BooleanValue.create(this, "CPS Display", true);
    }

    public BooleanValue P() {
        return this.D;
    }

    public List<OnlineFriendActivityState> D() {
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            return new ArrayList<OnlineFriendActivityState>();
        }
        ArrayList<OnlineFriendActivityState> arrayList = new ArrayList<OnlineFriendActivityState>();
        for (OnlineFriendActivityState onlineFriendActivityState : Vape.INSTANCE.getOnlineManager().V().X()) {
            if (!onlineFriendActivityState.k() || !partyState.c().contains(onlineFriendActivityState.a())) continue;
            arrayList.add(onlineFriendActivityState);
        }
        return arrayList;
    }

    @EventHandler
    public void v(EventMouseButton eventMouseButton) {
        OnlineActivitySettingsFrame onlineActivitySettingsFrame = ClientSettings.getFrame(OnlineActivitySettingsFrame.class);
        if (onlineActivitySettingsFrame == null) {
            return;
        }
        if (eventMouseButton.getButtonState()) {
            Vape.INSTANCE.getOnlineManager().r().E().n(0);
            if (eventMouseButton.getButton() == 0 && Minecraft.currentScreen().isNull()) {
                MouseClickRateTracker.recordClick();
            }
        }
    }
}

