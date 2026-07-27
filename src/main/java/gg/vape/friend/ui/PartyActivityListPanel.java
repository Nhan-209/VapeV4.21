package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineFriendActivityState;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineActivityPanelOptions;
import gg.vape.friend.ui.OnlineFriendActivityPanel;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.frame.SettingsFrameHeaderComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudSettingsFrameBase;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.wrapper.impl.Minecraft;
import java.util.ArrayList;
import java.util.List;

public class PartyActivityListPanel
extends PanelComponent {
    private int wn;
    private static final String[] wy;
    private final List<GuiComponent> wu;
    private boolean wC;
    private boolean wj = false;
    private static final int wo;
    final List<OnlineFriendActivityPanel> w9 = new ArrayList<OnlineFriendActivityPanel>();
    private final OnlineActivityPanelOptions wq;
    private int wb;

    @Override
    public void I() {
        this.c();
    }

    @Override
    public double A() {
        return 114.0;
    }

    public PartyActivityListPanel() {
        super(114.0, 0.0);
        this.wu = new ArrayList<GuiComponent>();
        this.d(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.wq = OnlineActivityPanelOptions.p;
    }

    @Override
    public double C() {
        if (this.S$src$Z$1pghvaa()) {
            return 108.0;
        }
        return this.w9.size() * 62;
    }

    @Override
    public void H() {
        boolean bl;
        boolean bl2 = bl = ClientSettings.fW != null && ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v() instanceof ClickGuiFrameManager;
        double d = ClientSettings.fW.v() ? this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().n() : (bl ? this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().n() : (((HudSettingsFrameBase)this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb()).q() ? this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().n() + 20.0 : this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().n() + 107.0));
        this.S(d);
        boolean bl3 = this.S$src$Z$1pghvaa();
        if (bl3) {
            if (!this.wj) {
                this.a$src$V$1po6zhw();
            }
            this.l$src$V$1mibm4x();
            return;
        }
        if (this.wj) {
            this.N$src$V$1pdqw81();
        }
        this.l$src$V$1mibm4x();
    }

    private boolean S$src$Z$1pghvaa() {
        return this.w9.isEmpty() && !ClientSettings.fW.P && HudModuleConfigFrameBase.h$src$Z$1tlh1co();
    }

    private void e(List<OnlineFriendActivityState> list) {
        this.b$src$V$1poqs39();
        if (OnlineConnectionManager.T.S().j$src$Lgg_vape_value_BooleanValue_$1co7xi6().L().booleanValue()) {
            this.w9.add(Vape.INSTANCE.getOnlineManager().r().X());
        }
        for (OnlineFriendActivityState object : list) {
            this.w9.add(new OnlineFriendActivityPanel(object));
        }
        for (OnlineFriendActivityPanel onlineFriendActivityPanel : this.w9) {
            this.h(new SpacerComponent(0.0, 2.0), new Object[0]);
            onlineFriendActivityPanel.U(this.wC);
            this.h(onlineFriendActivityPanel, new Object[0]);
        }
        this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().l$src$V$1mibm4x();
    }

    @Override
    public double x() {
        return 114.0;
    }


    public void L(boolean bl) {
        this.wC = bl;
        for (GuiComponent guiComponent : this.f()) {
            if (!(guiComponent instanceof OnlineFriendActivityPanel)) continue;
            ((OnlineFriendActivityPanel)guiComponent).U(bl);
        }
    }

    private void a$src$V$1po6zhw() {
        for (int i = 0; i < 2; ++i) {
            OnlineFriend onlineFriend = new OnlineFriend(wy[i]);
            onlineFriend.W("Steve");
            OnlineFriendActivityState onlineFriendActivityState = new OnlineFriendActivityState(onlineFriend);
            OnlineFriendActivityPanel onlineFriendActivityPanel = new OnlineFriendActivityPanel(onlineFriendActivityState);
            SpacerComponent spacerComponent = new SpacerComponent(0.0, 2.0);
            this.wu.add(spacerComponent);
            this.wu.add(onlineFriendActivityPanel);
            this.h(spacerComponent, new Object[0]);
            this.h(onlineFriendActivityPanel, new Object[0]);
        }
        this.wj = true;
        this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().l$src$V$1mibm4x();
    }

    public void Z$src$V$1pkcfcd() {
        this.wn = 0;
    }

    private void N$src$V$1pdqw81() {
        for (GuiComponent guiComponent : this.wu) {
            this.I(guiComponent);
        }
        this.wu.clear();
        this.wj = false;
        this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa().l$src$V$1mibm4x();
    }

    static {
        long l = -8029747256032755710L;
        wo = (int)l;
        wy = new String[]{"Player1", "Player2"};
    }

    private void b$src$V$1poqs39() {
        this.wn = 0;
        for (GuiComponent guiComponent : this.f()) {
            if (guiComponent instanceof SettingsFrameHeaderComponent) continue;
            this.I(guiComponent);
        }
        this.w9.clear();
        this.wu.clear();
        this.wj = false;
    }

    @Override
    public void u() {
        super.u();
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState == null) {
            if (!this.S$src$Z$1pghvaa()) {
                this.b$src$V$1poqs39();
            }
            return;
        }
        List<OnlineFriendActivityState> list = this.wq.D();
        int n = list.hashCode();
        if (this.wn != n) {
            this.e(list);
            this.wn = n;
        }
        for (OnlineFriendActivityPanel onlineFriendActivityPanel : this.w9) {
            onlineFriendActivityPanel.y$src$Lgg_vape_friend_OnlineFriendActivityState_$6vxj8m().D();
            if (this.wb % 20 != 19) continue;
            onlineFriendActivityPanel.s$src$V$ndfx0l();
        }
        ++this.wb;
        if (this.wb >= 20) {
            this.wb = 0;
        }
    }
}

