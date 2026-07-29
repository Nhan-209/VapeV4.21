package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineFriendAvatarStackComponent;
import gg.vape.friend.ui.OnlineFriendsFrame;
import gg.vape.friend.ui.PartyOverviewBackgroundPanel;
import gg.vape.friend.ui.PartyOverviewGroupOptionSyncMouseListener;
import gg.vape.friend.ui.PartyOverviewPanelActionClickHandler;
import gg.vape.friend.ui.PartyOverviewPanelPopupClickListener;
import gg.vape.friend.ui.PartyOverviewPanelPopupOutsideClickFilter;
import gg.vape.friend.ui.PartyOverviewPanelPopupOutsideClickListener;
import gg.vape.friend.ui.PartyPanel;
import gg.vape.module.none.ClientSettings;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.GroupDeleteResponsePacket;
import gg.vape.protocol.packet.GroupLeaveResponsePacket;
import gg.vape.protocol.packet.GroupOption;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.value.BooleanStateAdapter;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.AnchoredPopupFrame;
import gg.vape.ui.click.frame.DimmedCenteredPopupFrame;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.value.BooleanValue;
import gg.vape.value.Value;
import java.awt.Color;
import java.util.Map;

public class PartyDetailsPanel
extends PanelComponent {
    private static int xZ;
    boolean xU;
    private IconButtonComponent xh;
    private AnchoredPopupFrame xY;
    private final IconButtonComponent xA;
    private DimmedCenteredPopupFrame xE;
    TextButton x4;
    private FlowLayoutComponent xD;
    private final PartyPanel xT;
    private PartyState xN;

    public static AnchoredPopupFrame P(PartyDetailsPanel partyDetailsPanel) {
        return partyDetailsPanel.xY;
    }

    private static void lambda$leaveAction$4(GroupLeaveResponsePacket groupLeaveResponsePacket) {
    }

    public static int x$src$I$cyklaz() {
        int n = PartyDetailsPanel.v$src$I$cxh049();
        return 0;
    }

    public static void x(PartyDetailsPanel partyDetailsPanel) {
        partyDetailsPanel.W();
    }

    public static void B(PartyDetailsPanel partyDetailsPanel) {
        partyDetailsPanel.A$src$V$c4bwzb();
    }

    public static void d(int n) {
        xZ = n;
    }

    public static FlowLayoutComponent b(PartyDetailsPanel partyDetailsPanel) {
        return partyDetailsPanel.xD;
    }

    private void k$src$V$crf9wh() {
        if (this.xE != null) {
            ClientSettings.removePopup(this.xE);
            this.xE = null;
        }
    }

    private void b$src$V$cmh4k8() {
        if (this.xU) {
            return;
        }
        this.xU = true;
        if (this.xN != null) {
            if (this.xN.r().equals(Vape.INSTANCE.getOnlineManager().r())) {
                try {
                    ClientSettings.removePopup(this.xY);
                }
                catch (Exception exception) {
                    // empty catch block
                }
                ConfirmationDialogComponent confirmationDialogComponent = new ConfirmationDialogComponent("Are you sure you want to disband the party?", "DISBAND", "disband confirm@2x");
                DimmedCenteredPopupFrame dimmedCenteredPopupFrame = ClientSettings.createPopup(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), confirmationDialogComponent, DimmedCenteredPopupFrame.class);
                confirmationDialogComponent.getConfirmButton().addClickListener(() -> this.lambda$leaveAction$2(dimmedCenteredPopupFrame));
                confirmationDialogComponent.getCloseButton().addClickListener(() -> this.lambda$leaveAction$3(dimmedCenteredPopupFrame));
                dimmedCenteredPopupFrame.addMouseListener(new PartyOverviewPanelPopupOutsideClickFilter(this, dimmedCenteredPopupFrame));
            } else {
                ClientSettings.removePopup(this.xY);
                ZeusConnectionManager.T().u().u(PartyDetailsPanel::lambda$leaveAction$4, this::lambda$leaveAction$5);
            }
        }
    }

    static {
        PartyDetailsPanel.d(13);
    }

    private void W() {
        if (this.xY == null) {
            OnlineFriendsFrame onlineFriendsFrame = ClientSettings.getFrame(OnlineFriendsFrame.class);
            this.xY = (AnchoredPopupFrame)onlineFriendsFrame.A(this.xA, this.xD, AnchoredPopupFrame.class);
            this.xY.addMouseListener(new PartyOverviewPanelPopupOutsideClickListener(this));
        } else {
            this.s$src$V$cvtmnd();
        }
    }

    private void lambda$leaveAction$5() {
        this.xU = false;
    }

    public IconButtonComponent Y$src$Lgg_vape_ui_click_component_IconButtonComponent_$16i1alc() {
        return this.xh;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private void lambda$leaveAction$3(PopupFrame popupFrame) {
        ClientSettings.removePopup(popupFrame);
        this.xU = false;
    }

    public static void H(PartyDetailsPanel partyDetailsPanel) {
        partyDetailsPanel.s$src$V$cvtmnd();
    }

    private void lambda$leaveAction$2(PopupFrame popupFrame) {
        ClientSettings.removePopup(popupFrame);
        ZeusConnectionManager.T().u().l(PartyDetailsPanel::lambda$null$0, this::lambda$null$1);
    }

    @Override
    public void c() {
        this.setShowDisabledOverlay(false);
        super.c();
    }

    private static void lambda$null$0(GroupDeleteResponsePacket groupDeleteResponsePacket) {
    }

    private void s$src$V$cvtmnd() {
        if (this.xY != null) {
            ClientSettings.removePopup(this.xY);
            this.xY = null;
        }
    }

    @Override
    public void u() {
        this.xN = Vape.INSTANCE.getOnlineManager().y().j();
        if (this.xN == null) {
            this.k$src$V$crf9wh();
            this.s$src$V$cvtmnd();
        }
        boolean bl = this.xN.r().equals(Vape.INSTANCE.getOnlineManager().r());
        for (GuiComponent guiComponent : this.xD.f()) {
            if (!(guiComponent instanceof BooleanStateAdapter)) continue;
            ((BooleanStateAdapter)((Object)guiComponent)).setReadOnly(!bl);
        }
        super.u();
    }

    private void lambda$null$1() {
        this.xU = false;
    }

    public static int v$src$I$cxh049() {
        return xZ;
    }

    public PartyDetailsPanel(PartyState partyState) {
        super(92.0, 11.0);
        this.xh = new SquareIconButtonComponent("newclose", 1.0, new Color(0, 0, 0, 0), PartyDetailsPanel.J.l, 10.0, 10.0);
        this.xA = new IconButtonComponent("more", 1.0, PartyDetailsPanel.J.f, Color.white, 8.0, 8.0);
        this.xD = new FlowLayoutComponent(80.0);
        this.xU = false;
        this.xN = partyState;
        this.xT = new PartyPanel(partyState);
        PanelComponent panelComponent = new PanelComponent(45.0, 8.0);
        panelComponent.addChildren(new SpacerComponent(2.0, 1.0));
        OnlineFriendAvatarStackComponent onlineFriendAvatarStackComponent = new OnlineFriendAvatarStackComponent(partyState.c());
        panelComponent.addChildren(onlineFriendAvatarStackComponent);
        onlineFriendAvatarStackComponent.addMouseListener(new PartyOverviewPanelPopupClickListener(this));
        onlineFriendAvatarStackComponent.w("Party member list");
        this.xA.w("Party settings");
        this.xT.g$src$Lgg_vape_ui_click_component_IconButtonComponent_$1thfv1k().addClickListener(this::k$src$V$crf9wh);
        PanelComponent panelComponent2 = new PanelComponent(45.0, 8.0);
        panelComponent2.h(new SpacerComponent(panelComponent2.A() - this.xA.A() - this.xh.A() - 2.0, 1.0), new Object[0]);
        panelComponent2.h(this.xA, new Object[0]);
        panelComponent2.h(new SpacerComponent(2.0, 1.0), new Object[0]);
        panelComponent2.h(this.xh, new Object[0]);
        panelComponent2.setShowDisabledOverlay(false);
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        panelComponent.setShowDisabledOverlay(false);
        this.addChildren(panelComponent, panelComponent2);
        this.xA.addClickListener(new PartyOverviewPanelActionClickHandler(this));
        this.xD = new FlowLayoutComponent(99.0);
        this.xD.addChildren(new SpacerComponent(99.0, 3.0));
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("Party Settings");
        simpleTextLabelComponent.setTextColor(PartyDetailsPanel.J.Z);
        this.xD.addChildren(simpleTextLabelComponent);
        for (Map.Entry<GroupOption, Value<?, ?>> object2 : partyState.L().entrySet()) {
            GroupOption groupOption = object2.getKey();
            Value<?, ?> value = object2.getValue();
            if (!(value instanceof BooleanValue)) continue;
            BooleanValue booleanValue = (BooleanValue)value;
            BooleanToggleComponent booleanToggleComponent = new BooleanToggleComponent(booleanValue);
            booleanToggleComponent.addMouseListener(new PartyOverviewGroupOptionSyncMouseListener(this, booleanValue, groupOption, value));
            this.xD.addChildren(booleanToggleComponent);
        }
        this.xD.setDisabledOverlayColor(PartyDetailsPanel.J.y);
        this.x4 = new TextButton(partyState.r().equals(Vape.INSTANCE.getOnlineManager().r()) ? "DISBAND" : "LEAVE", 0.9, PartyDetailsPanel.J.d, PartyDetailsPanel.J.c, 80.0, 10.0);
        this.x4.addClickListener(this::b$src$V$cmh4k8);
        this.x4.setDeriveTextColorFromBackground(false);
        this.xD.o(99.0);
        PartyOverviewBackgroundPanel partyOverviewBackgroundPanel = new PartyOverviewBackgroundPanel(this, this.xD.A(), 14.0);
        partyOverviewBackgroundPanel.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        partyOverviewBackgroundPanel.h(new SpacerComponent(99.0, 2.0), new Object[0]);
        PanelComponent panelComponent3 = new PanelComponent(99.0, 10.0);
        panelComponent3.addChildren(new SpacerComponent(this.xD.A() / 2.0 - this.x4.A() / 2.0, 0.0), this.x4, new SpacerComponent(this.xD.A() / 2.0 - this.x4.A() / 2.0, 0.0));
        partyOverviewBackgroundPanel.addChildren(panelComponent3);
        partyOverviewBackgroundPanel.setDisabledOverlayColor(PartyDetailsPanel.J.i);
        this.xD.addChildren(partyOverviewBackgroundPanel);
        this.xD.h(new SpacerComponent(99.0, 6.0), new Object[0]);
    }

    private void A$src$V$c4bwzb() {
        if (this.xE == null) {
            OnlineFriendsFrame onlineFriendsFrame = ClientSettings.getFrame(OnlineFriendsFrame.class);
            this.xE = (DimmedCenteredPopupFrame)onlineFriendsFrame.A(onlineFriendsFrame.L$src$Lgg_vape_ui_click_component_PanelComponent_$1c87g2d(), this.xT, DimmedCenteredPopupFrame.class);
        }
    }
}
