package gg.vape.friend.ui;

import gg.vape.friend.PartyState;
import gg.vape.friend.ui.OnlineChatPanel;
import gg.vape.friend.ui.PartyChatSender;
import gg.vape.friend.ui.PartyDetailsPanel;
import gg.vape.friend.ui.PartyMemberRow;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.utils.render.GuiRenderPrimitives;

public class PartyDetailsAndChatPanel
extends PanelComponent {
    private final PartyDetailsPanel V1;
    private final PartyState Vu;
    private static int VI;
    private final PanelComponent VH = new PanelComponent(100.0, 24.0);
    private static final String db;
    private OnlineChatPanel VK = new OnlineChatPanel(new PartyChatSender());

    public static int W() {
        int n = PartyDetailsAndChatPanel.H$src$I$1japgko();
        return 0;
    }

    @Override
    public double C() {
        return this.VH.L() + this.VK.L() - 5.0;
    }

    @Override
    public void c() {
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a() - 3.0, this.n() - 3.0, this.A() + 6.0, this.L() + 6.0, PartyDetailsAndChatPanel.J.i);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), PartyDetailsAndChatPanel.J.m);
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), PartyDetailsAndChatPanel.J.l, 2.0f, 1.0f, 1.0f);
        super.c();
    }

    public PartyDetailsAndChatPanel(PartyState partyState) {
        super(99.0, 24.0);
        this.Vu = partyState;
        this.VH.setShowDisabledOverlay(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(db);
        this.setShowDisabledOverlay(false);
        GuiComponent[] guiComponentArray = new GuiComponent[2];
        this.V1 = new PartyDetailsPanel(this.Vu);
        guiComponentArray[0] = new PaddedComponent(4.0, this.V1);
        guiComponentArray[1] = this.VK;
        this.addChildren(guiComponentArray);
        for (PartyMemberRow partyMemberRow : partyState.d()) {
            this.VK.z().b(partyMemberRow);
        }
    }

    public static int H$src$I$1japgko() {
        return VI;
    }

    static {
        PartyDetailsAndChatPanel.J(26);
        db = "wrap";
    }

    @Override
    public void z(boolean bl) {
    }

    public static void J(int n) {
        VI = n;
    }

    public IconButtonComponent e$src$Lgg_vape_ui_click_component_IconButtonComponent_$pbqe5z() {
        return this.V1.Y$src$Lgg_vape_ui_click_component_IconButtonComponent_$16i1alc();
    }

    public OnlineChatPanel e$src$Lgg_vape_friend_ui_OnlineChatPanel_$1fym7va() {
        return this.VK;
    }


    @Override
    public void u() {
    }
}

