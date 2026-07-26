package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.ui.FriendEntriesPanel;
import gg.vape.friend.ui.FriendManagementPanel;
import gg.vape.friend.ui.FriendRequestsPanel;
import gg.vape.friend.ui.OnlineConnectionStatusPanel;
import gg.vape.friend.ui.OnlineFriendEntriesPanel;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.friend.ui.OnlineFriendsEmptyStatePanel;
import gg.vape.friend.ui.OnlineFriendsFrameConditionalPopupCloseClickHandler;
import gg.vape.friend.ui.OnlineFriendsFrameModeToggleComponent;
import gg.vape.friend.ui.OnlineFriendsFramePopupCloseClickHandler;
import gg.vape.friend.ui.OnlineFriendsFramePopupOutsideClickListener;
import gg.vape.friend.ui.OnlineFriendsListPanel;
import gg.vape.friend.ui.OnlineModeToggleComponent;
import gg.vape.friend.ui.OnlineRegistrationPanel;
import gg.vape.friend.ui.PartyInvitesPanel;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.manager.client.OnlineConnectionState;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.component.ColorDividerComponent;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.input.BindValueRowComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.ColorValueEditorComponent;
import gg.vape.ui.click.frame.CenteredPopupFrame;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.click.frame.SettingsSectionComponent;
import gg.vape.ui.click.frame.SettingsSubpageFrame;
import gg.vape.ui.click.frame.impl.ThemeComponentGroupFactory;
import gg.vape.ui.click.frame.impl.ThemeComponentGroupKey;
import gg.vape.ui.notification.NotificationToastOverlay;
import gg.vape.unmap.ModeOption;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OnlineFriendsFrame
extends SettingsSubpageFrame {
    private final PanelComponent On = new PanelComponent(104.0, 150.0);
    private BooleanToggleComponent Oa;
    private PopupFrame Of;
    private OnlineRegistrationPanel OU;
    private BooleanToggleComponent Op;
    private BooleanToggleComponent OV;
    private FriendRequestsPanel Oz;
    private PopupFrame Od;
    private final OnlineFriendsEmptyStatePanel Or;
    private BooleanToggleComponent OC;
    private static String[] O4;
    private BooleanToggleComponent OX;
    private BooleanToggleComponent Os;
    private IconButtonComponent Oh;
    private BooleanToggleComponent Om;
    private ColorValueEditorComponent Oe;
    private NotificationToastOverlay Oy;
    private BooleanToggleComponent O3;
    private final OnlineConnectionStatusPanel Og;
    private GuiMouseListener OJ;
    private SimpleTextLabelComponent OG;
    private BooleanToggleComponent O7;
    private final OnlineFriendsListPanel O9;
    private PopupFrame OL;
    private PopupFrame Ok;
    private BooleanToggleComponent OY;
    private final PanelComponent OQ;
    private ColorDividerComponent O6;
    private DropdownSelectComponent<ModeOption> Oj;
    private BooleanToggleComponent Ol;
    private final FriendManagementPanel Ot;
    private PanelComponent OI;
    private BooleanToggleComponent OT;
    private final PanelComponent Ou = new PanelComponent(104.0, 150.0);
    private OnlineModeToggleComponent OP;
    private BooleanToggleComponent O1;

    public OnlineFriendsFrame() {
        super("newfriends", "Friends");
        this.OQ = new PanelComponent(104.0, 130.0);
        this.OP = new OnlineFriendsFrameModeToggleComponent(this, "VAPE FRIENDS", "MINECRAFT FRIENDS", true);
        this.Ot = new FriendManagementPanel();
        this.Oz = new FriendRequestsPanel();
        this.Oh = new IconButtonComponent("add friends@2x", 1.0, new Color(180, 180, 180), Color.WHITE, 13.0, 13.0);
        this.O6 = new ColorDividerComponent(OnlineFriendsFrame.J.l);
        this.O9 = new OnlineFriendsListPanel();
        this.Or = new OnlineFriendsEmptyStatePanel();
        this.Og = new OnlineConnectionStatusPanel();
        this.OJ = new OnlineFriendsFramePopupOutsideClickListener(this);
        this.Z(false);
        this.o(103.0);
        this.N(false);
        this.D(true);
        this.C6();
        this.s$src$V$vr89i8();
        this.d$src$V$vizclt();
        this.w(this.Or);
        this.Oh.w("Add Vape friends");
        this.OP.u("", "");
        this.Oy = new NotificationToastOverlay(this);
        this.j(this.OJ);
        this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().x(0.8f);
    }

    @Override
    public void u() {
        super.u();
        if (Vape.INSTANCE.getOnlineManager() == null) {
            return;
        }
        int n = Vape.INSTANCE.getOnlineManager().D().I().size();
        if (n > 0) {
            this.Oh.H("add friends notification@2x");
        } else {
            this.Oh.H("add friends@2x");
        }
        this.Oh.Z(false);
        if (this.OP.r$src$Ljava_lang_Boolean_$180i77a().booleanValue()) {
            if (OnlineConnectionManager.T.n().equals((Object)OnlineConnectionState.ONLINE)) {
                if (Vape.INSTANCE.getOnlineFriendManager().g().size() > 0 || Vape.INSTANCE.getOnlineManager().y().j() != null) {
                    this.w(this.O9);
                } else {
                    this.w(this.Or);
                }
                this.Oh.Z(true);
            } else {
                this.w(this.Og);
            }
            if (this.Od != null) {
                this.OI.Z(false);
            } else if (this.OI != null) {
                this.OI.Z(true);
            }
        } else {
            if (this.OI != null) {
                this.OI.Z(false);
            }
            if (!this.q$src$Lgg_vape_ui_click_component_IconButtonComponent_$1bvowkh().V$src$Z$1xhop3l()) {
                this.q$src$Lgg_vape_ui_click_component_IconButtonComponent_$1bvowkh().Z(true);
            }
            if (OnlineConnectionManager.T.n().equals((Object)OnlineConnectionState.ONLINE)) {
                this.Oh.Z(true);
            }
        }
    }

    public void N$src$V$v6vvjv() {
        try {
            this.k$src$V$vmtwrc();
            OnlineFriendUiHelper.U();
            if (!this.OP.r$src$Ljava_lang_Boolean_$180i77a().booleanValue()) {
                this.OP.u(false);
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    public OnlineFriendEntriesPanel d$src$Lgg_vape_friend_ui_OnlineFriendEntriesPanel_$86qf3j() {
        return this.O9.l$src$Lgg_vape_friend_ui_OnlineFriendEntriesPanel_$257n8u();
    }

    public void p(boolean bl) {
        this.k$src$V$vmtwrc();
        if (!bl) {
            if (this.Of == null) {
                this.Of = this.A(this.OQ, this.Ot, CenteredPopupFrame.class);
            }
        } else if (this.Of != null) {
            ClientSettings.K(this.Of);
            this.Of = null;
        }
    }

    public void e() {
        if (this.Od != null) {
            this.Q$src$V$v8j9by();
        }
    }

    private void C6() {
        this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().Q(this.Oh);
        this.Oh.r(new OnlineFriendsFramePopupCloseClickHandler(this));
        this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().r$src$Lgg_vape_ui_click_component_IconButtonComponent_$86hdsq().r(new OnlineFriendsFrameConditionalPopupCloseClickHandler(this));
    }

    public static NotificationToastOverlay e(OnlineFriendsFrame onlineFriendsFrame) {
        return onlineFriendsFrame.Oy;
    }

    public static void c$src$V$11veyie(OnlineFriendsFrame onlineFriendsFrame) {
        onlineFriendsFrame.CM();
    }

    private void CM() {
        if (this.Ok == null) {
            this.Ok = this.A(this.On, this.Oz, CenteredPopupFrame.class);
            this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().I("Friend requests", false);
        } else {
            ClientSettings.K(this.Ok);
            this.Ok = null;
            this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().h();
        }
    }

    private void Ci() {
        if (this.OL == null) {
            this.OL = this.A(this, this.Oy, PopupFrame.class);
        }
        this.c(this.OL);
        this.OL.K(this.G$src$D$1b2f02a());
        this.OL.S(this.n());
    }

    public PartyInvitesPanel Y$src$Lgg_vape_friend_ui_PartyInvitesPanel_$1o49ve3() {
        return this.O9.P();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void G(String[] stringArray) {
        O4 = stringArray;
    }

    public void Z$src$V$vdheo7() {
        if (this.Od != null) {
            this.Q$src$V$v8j9by();
        }
        if (this.OU == null) {
            this.OU = new OnlineRegistrationPanel();
        }
        this.Od = this.A(this.On, this.OU, CenteredPopupFrame.class);
        this.q$src$Lgg_vape_ui_click_component_IconButtonComponent_$1bvowkh().Z(false);
        this.Oh.Z(false);
    }

    public PanelComponent L$src$Lgg_vape_ui_click_component_PanelComponent_$1c87g2d() {
        return this.Ou;
    }

    public OnlineModeToggleComponent p$src$Lgg_vape_friend_ui_OnlineModeToggleComponent_$u0bbsl() {
        return this.OP;
    }

    static {
        OnlineFriendsFrame.G(new String[4]);
    }

    public PopupFrame A(GuiComponent guiComponent, GuiComponent guiComponent2, Class<? extends PopupFrame> clazz) {
        PopupFrame popupFrame = ClientSettings.g(guiComponent, guiComponent2, clazz);
        popupFrame.j(this.OJ);
        return popupFrame;
    }

    private void d$src$V$vizclt() {
        this.Ou.t(150.0);
        this.Ou.N(false);
        this.Ou.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.Ou.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        this.Ou.h(this.OP, new Object[0]);
        this.Ou.h(new SpacerComponent(1.0, 4.0), new Object[0]);
        this.Ou.h(this.OQ, new Object[0]);
        this.On.t(150.0);
        this.On.N(false);
        this.On.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("spanWidth, offsetX 6");
        this.On.h(this.Ou, new Object[0]);
        this.h(this.On, new Object[0]);
    }

    public FriendRequestsPanel o$src$Lgg_vape_friend_ui_FriendRequestsPanel_$8g38ub() {
        return this.Oz;
    }

    public void Q$src$V$v8j9by() {
        if (this.Od == null) {
            return;
        }
        this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().h();
        ClientSettings.K(this.Od);
        this.Od = null;
        this.q$src$Lgg_vape_ui_click_component_IconButtonComponent_$1bvowkh().Z(true);
        this.Oh.Z(true);
    }

    public FriendEntriesPanel R$src$Lgg_vape_friend_ui_FriendEntriesPanel_$19ux45q() {
        return this.Ot.w$src$Lgg_vape_friend_ui_FriendEntriesPanel_$r03ijp();
    }

    public static String[] w$src$ALjava_lang_String_$1ugxiph() {
        return O4;
    }

    public OnlineFriendsListPanel x$src$Lgg_vape_friend_ui_OnlineFriendsListPanel_$lt2vne() {
        return this.O9;
    }

    private void s$src$V$vr89i8() {
        LinkedHashMap<ThemeComponentGroupKey, GuiComponent[]> linkedHashMap = ThemeComponentGroupFactory.R(J);
        for (Map.Entry<ThemeComponentGroupKey, GuiComponent[]> entry : linkedHashMap.entrySet()) {
            String string = entry.getKey().h();
            GuiComponent[] guiComponentArray = entry.getValue();
            this.n(this.s(string, guiComponentArray));
        }
        GuiComponent[] guiComponentArray = ThemeComponentGroupFactory.E(J);
        if (guiComponentArray != null && guiComponentArray.length > 0) {
            this.n(guiComponentArray);
        }
    }

    @Override
    public void w() {
        super.w();
        this.p(this.OP.r$src$Ljava_lang_Boolean_$180i77a());
    }

    public OnlineFriendsEmptyStatePanel k$src$Lgg_vape_friend_ui_OnlineFriendsEmptyStatePanel_$xjl6dd() {
        return this.Or;
    }

    public NotificationToastOverlay V$src$Lgg_vape_ui_notification_NotificationToastOverla$1025be3() {
        return this.Oy;
    }

    public void o$src$V$vp134s() {
        ClientSettings.g(OnlineFriendsFrame.class).p$src$Lgg_vape_friend_ui_OnlineModeToggleComponent_$u0bbsl().u(false);
        this.q$src$Lgg_vape_ui_click_component_IconButtonComponent_$1bvowkh().Z(true);
    }

    private void w(PanelComponent panelComponent) {
        if (this.OI != panelComponent) {
            this.OQ.t$src$V$zbu1jn();
            this.OQ.h(panelComponent, new Object[0]);
            this.OI = panelComponent;
            this.Q$src$V$v8j9by();
            this.k$src$V$vmtwrc();
        }
    }

    @Override
    public void p() {
        if (this.H$src$Lgg_vape_ui_click_frame_CenteredPopupFrame_$1qmombx() == null) {
            boolean bl = OnlineConnectionManager.T.n().equals((Object)OnlineConnectionState.ONLINE);
            List<GuiComponent> list = this.h();
            for (GuiComponent guiComponent : list) {
                if (guiComponent instanceof SettingsSectionComponent) {
                    SettingsSectionComponent settingsSectionComponent = (SettingsSectionComponent)guiComponent;
                    String string = settingsSectionComponent.A$src$Ljava_lang_String_$9tmd4u();
                    if (!"Notification Settings".equals(string) && !"Party Settings".equals(string)) continue;
                    settingsSectionComponent.Z(bl);
                    continue;
                }
                if (!(guiComponent instanceof BindValueRowComponent)) continue;
                guiComponent.Z(bl);
            }
        }
        super.p();
    }

    public static PopupFrame c(OnlineFriendsFrame onlineFriendsFrame) {
        return onlineFriendsFrame.Ok;
    }

    @Override
    public void c() {
        this.Ci();
        this.l$src$V$1mibm4x();
        super.c();
    }

    public void k$src$V$vmtwrc() {
        ClientSettings.p(this);
        this.Od = null;
        this.Of = null;
        this.Ok = null;
        this.OL = null;
        this.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().h();
    }
}

