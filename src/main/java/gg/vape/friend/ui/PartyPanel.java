package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.PartyFriendRowComponent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import java.awt.Color;

public class PartyPanel
extends PanelComponent {
    private FlowLayoutComponent tq;
    private SimpleTextLabelComponent tD;
    private final PartyState tC;
    private SimpleTextLabelComponent t_;
    private IconButtonComponent tQ;
    private FlowLayoutComponent tR;
    private FlowLayoutComponent t9;

    public void w$src$V$1sfdd5j() {
        this.tR.t$src$V$zbu1jn();
        this.t9.t$src$V$zbu1jn();
        boolean bl = !this.tC.r().equals(Vape.INSTANCE.getOnlineManager().r());
        for (OnlineFriend onlineFriend : this.tC.c()) {
            if (this.tC.r() == onlineFriend) {
                this.tR.H(new PartyFriendRowComponent(onlineFriend, false, true));
                continue;
            }
            this.tR.H(new PartyFriendRowComponent(onlineFriend, false, bl));
        }
        this.tD.Z(!this.tC.S().isEmpty());
        for (OnlineFriend onlineFriend : this.tC.S()) {
            this.t9.H(new PartyFriendRowComponent(onlineFriend, true, bl));
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public IconButtonComponent g$src$Lgg_vape_ui_click_component_IconButtonComponent_$1thfv1k() {
        return this.tQ;
    }

    @Override
    public void H() {
        super.H();
    }

    @Override
    public void u() {
        super.u();
        this.t_.Z(false);
    }

    @Override
    public void c() {
        super.c();
        this.O(0.75).d("In party", this.G$src$D$1b2f02a() + 4.0, this.n() + 6.0, PartyPanel.J.Z);
    }

    public PartyPanel(PartyState partyState) {
        super(99.0, 150.0);
        this.tQ = new SquareIconButtonComponent("newclose", 1.0, new Color(255, 255, 255, 0), PartyPanel.J.l, 8.0, 8.0);
        this.tD = new SimpleTextLabelComponent("Invited", 0.75, PartyPanel.J.Z);
        this.t_ = new SimpleTextLabelComponent("Suggested", 0.75, PartyPanel.J.Z);
        this.tR = new FlowLayoutComponent(90.0);
        this.t9 = new FlowLayoutComponent(90.0);
        this.tq = new FlowLayoutComponent(90.0);
        this.tC = partyState;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        PanelComponent panelComponent = new PanelComponent(99.0, 15.0);
        panelComponent.H(new SpacerComponent(87.0, 8.0), this.tQ);
        this.H(new SpacerComponent(1.0, 4.0), panelComponent, new SpacerComponent(1.0, 3.0), this.tR, new SpacerComponent(1.0, 1.0), this.tD, new SpacerComponent(1.0, 3.0), this.t9, new SpacerComponent(1.0, 1.0), this.t_, new SpacerComponent(1.0, 3.0), this.tq);
        this.tR.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.t9.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.t(150.0);
        this.w$src$V$1sfdd5j();
        partyState.u(this);
    }
}

