package gg.vape.friend.ui;

import gg.vape.friend.LocalOnlineFriend;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.ui.OnlineFriendAvatarComponent;
import gg.vape.friend.ui.PartyMemberNameTextComponent;
import gg.vape.friend.ui.PartyMemberStatusComponent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;

public class PartyMemberRow
extends PanelComponent {
    private FlowLayoutComponent Ie = new FlowLayoutComponent(100.0);
    private TruncatedTextComponent IW;
    private OnlineFriendAvatarComponent I6;
    private final PartyMemberStatusComponent Iz;
    private static int[] IQ;
    private PanelComponent IP = new PanelComponent(18.0, 16.0);
    private final OnlineFriend IX;
    private boolean IV;

    public PartyMemberRow(OnlineFriend onlineFriend, PartyMemberStatusComponent partyMemberStatusComponent) {
        this(onlineFriend, partyMemberStatusComponent, true);
    }

    static {
        PartyMemberRow.u(new int[5]);
    }

    @Override
    public void c() {
        super.c();
        this.IP.q(this.I6.V$src$Z$1xhop3l() ? 18.0 : 6.0);
    }

    @Override
    public double L() {
        return Math.max(this.Ie.L() + 3.0, this.IP.L());
    }

    public boolean D$src$Z$qb6a9o() {
        return this.IV;
    }

    @Override
    public double A() {
        return this.Ie.A() + this.IP.A();
    }

    public PartyMemberRow(OnlineFriend onlineFriend, PartyMemberStatusComponent partyMemberStatusComponent, boolean bl) {
        super(0.0, 0.0);
        this.d(false);
        this.Iz = partyMemberStatusComponent;
        this.IV = onlineFriend instanceof LocalOnlineFriend;
        this.IX = onlineFriend;
        this.IW = new PartyMemberNameTextComponent(this, onlineFriend.C(), "...", 74.0, 0.75, PartyMemberRow.J.h, false);
        this.Ie.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.Ie.d(false);
        this.q(partyMemberStatusComponent.A());
        this.u(partyMemberStatusComponent.H$src$D$1wlsgtk());
        this.I6 = new OnlineFriendAvatarComponent(onlineFriend, 8.0, 8.0);
        this.IP.d(false);
        if (this.IV) {
            partyMemberStatusComponent.T(PartyMemberRow.J.B);
            partyMemberStatusComponent.J(true);
            this.Ie.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("alignright, wrap");
            this.IP.H(new SpacerComponent(3.0, 1.0), this.I6);
            this.H(this.Ie, this.IP);
        } else {
            partyMemberStatusComponent.T(PartyMemberRow.J.g);
            this.IP.H(new SpacerComponent(6.0, 1.0), this.I6);
            this.H(this.IP, this.Ie);
        }
        if (bl) {
            this.Ie.H(this.D$src$Z$qb6a9o() ? new SpacerComponent(1.0, 8.0) : this.IW);
        }
        this.Ie.h(partyMemberStatusComponent, new Object[0]);
        if (!partyMemberStatusComponent.I$src$Z$19lcktz()) {
            this.IP.Z(false);
        }
    }

    public OnlineFriendAvatarComponent D$src$Lgg_vape_friend_ui_OnlineFriendAvatarComponent_$3ru3vc() {
        return this.I6;
    }

    public PanelComponent A$src$Lgg_vape_ui_click_component_PanelComponent_$1jb72u1() {
        return this.IP;
    }

    public static void u(int[] nArray) {
        IQ = nArray;
    }

    public static int[] X() {
        return IQ;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

