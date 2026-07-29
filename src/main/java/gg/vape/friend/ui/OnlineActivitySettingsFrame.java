package gg.vape.friend.ui;

import gg.vape.friend.ui.OnlineActivityPanelOptions;
import gg.vape.friend.ui.OnlineActivityPanelRefreshClickHandler;
import gg.vape.friend.ui.PartyActivityListPanel;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.input.BindValueRowComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.impl.hud.HudSettingsFrameBase;
import gg.vape.ui.click.layout.ComponentLayout;
import gg.vape.unmap.Bendable;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.KeyBoardUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.Minecraft;

public class OnlineActivitySettingsFrame
extends HudSettingsFrameBase {
    private final DropdownSelectComponent<ModeOption> ys = new DropdownSelectComponent(OnlineConnectionManager.T.S().r$src$Lgg_vape_value_ModeValue_$lqfla9());
    private boolean yQ = false;
    private final PartyActivityListPanel yD;
    private static GuiComponent[] yB;
    private final OnlineActivityPanelOptions yK;
    private boolean yd = false;

    public static GuiComponent[] B() {
        return yB;
    }

    @Override
    public void Y() {
        if (this.isManagedByClickGui()) {
            return;
        }
        GuiRenderPrimitives.e(this.G$src$D$1b2f02a(), this.n(), this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().A(), this.q() ? 18.0 : 109.0, this.getDisabledOverlayColor(), this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() != null, 2.0f, 1.0f);
    }

    @Override
    protected void renderHudModeBorder() {
    }

    @Override
    public String getName() {
        return "Party Overlay";
    }

    static {
        OnlineActivitySettingsFrame.e(new GuiComponent[3]);
    }


    private boolean X(Bendable bendable) {
        if (bendable.getBoundInputs().isEmpty()) {
            return false;
        }
        int n = 0;
        for (int n2 : bendable.getBoundInputs()) {
            if (!KeyBoardUtil.m(n2)) continue;
            ++n;
        }
        return n == bendable.getBoundInputs().size();
    }

    @Override
    public void u() {
        boolean bl;
        Bendable bendable = OnlineConnectionManager.T.S().k();
        boolean bl2 = Minecraft.currentScreen().isNotNull() && ClientSettings.INSTANCE.isInputEnabled();
        boolean bl3 = !bl2 && !(ClientSettings.activeComponent instanceof TextInputComponentBase) && this.X(bendable);
        boolean bl4 = bl = this.ys.getSelectedValue() == null || this.ys.getSelectedValue().equals(OnlineConnectionManager.T.S().r());
        if (bl) {
            this.g$src$Lgg_vape_friend_ui_PartyActivityListPanel_$1w1i13j().L(bl3);
        } else {
            if (bl3 && !this.yd) {
                this.yQ = !this.yQ;
            }
            this.g$src$Lgg_vape_friend_ui_PartyActivityListPanel_$1w1i13j().L(this.yQ);
        }
        this.yd = bl3;
    }

    public PartyActivityListPanel g$src$Lgg_vape_friend_ui_PartyActivityListPanel_$1w1i13j() {
        return this.yD;
    }

    public static PartyActivityListPanel b(OnlineActivitySettingsFrame onlineActivitySettingsFrame) {
        return onlineActivitySettingsFrame.yD;
    }

    public static void e(GuiComponent[] guiComponentArray) {
        yB = guiComponentArray;
    }

    public OnlineActivitySettingsFrame() {
        super("party@2x", "Party Overlay");
        ComponentLayout componentLayout = this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij();
        componentLayout.t(false);
        componentLayout.U(false);
        componentLayout.M(false);
        componentLayout.I(false);
        componentLayout.u(false);
        componentLayout.M("wrap");
        this.setShowDisabledOverlay(false);
        BindValueRowComponent bindValueRowComponent = new BindValueRowComponent("Show inventory bind", OnlineConnectionManager.T.S().k(), OnlineActivitySettingsFrame.J.Z);
        bindValueRowComponent.w("Keybind to show inventory of party members");
        bindValueRowComponent.getBindInput().setActiveAlpha(20);
        this.yK = OnlineActivityPanelOptions.p;
        BooleanToggleComponent booleanToggleComponent = new BooleanToggleComponent(this.yK.P());
        BooleanToggleComponent booleanToggleComponent2 = new BooleanToggleComponent(OnlineConnectionManager.T.S().j$src$Lgg_vape_value_BooleanValue_$1co7xi6());
        booleanToggleComponent2.addMouseListener(new OnlineActivityPanelRefreshClickHandler(this));
        BooleanToggleComponent booleanToggleComponent3 = new BooleanToggleComponent(this.yK.i());
        booleanToggleComponent2.o(110.0);
        booleanToggleComponent.o(110.0);
        booleanToggleComponent3.o(110.0);
        this.ys.o(110.0);
        bindValueRowComponent.o(110.0);
        this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().o(110.0);
        this.addSettings(bindValueRowComponent, this.ys, booleanToggleComponent2,
                booleanToggleComponent, booleanToggleComponent3);
        this.h(new SpacerComponent(1.0, 4.0), new Object[0]);
        this.yD = new PartyActivityListPanel();
        this.addChildren(this.yD);
    }
}

