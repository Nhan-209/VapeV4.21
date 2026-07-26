package gg.vape.friend.ui;

import gg.vape.friend.FriendRequest;
import gg.vape.friend.ui.FriendRequestNameTextComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class FriendRequestRow
extends PanelComponent {
    private static String[] CF;
    public TruncatedTextComponent CG;
    private final PanelComponent Cc;
    private final FriendRequest CA;
    private final TextButton Cw;
    private final PanelComponent Cp;
    private final IconButtonComponent CE;
    private final PanelComponent Ce;

    private FriendRequestRow(FriendRequest friendRequest) {
        super(100.0, 17.5);
        this.CE = new SquareIconButtonComponent("newclose", 1.0, new Color(0, 0, 0, 0), FriendRequestRow.J.l, 6.0, 6.0);
        this.Cw = new TextButton("ADD", 0.6, FriendRequestRow.J.B, FriendRequestRow.J.O, 14.0, 8.0);
        this.Cp = new PanelComponent(74.0, 16.0);
        this.Cc = new PanelComponent(16.0, 16.0);
        this.Ce = new PanelComponent(8.0, 16.0);
        this.Cw.F(false);
        this.Cw.h(Color.WHITE);
        this.CA = friendRequest;
        this.d(false);
        this.Cp.d(false);
        this.Cc.d(false);
        this.Ce.d(false);
        this.Cc.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.Ce.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.Ce.h(this.CE, new Object[0]);
        this.CG = new FriendRequestNameTextComponent(this, friendRequest.x().C(), "...", 66.0, 0.8, FriendRequestRow.J.Z, false);
        this.Cp.H(new SpacerComponent(6.0, 1.0), this.CG);
        this.H(this.Cp, this.Cc, this.Ce);
    }

    public FriendRequest y$src$Lgg_vape_friend_FriendRequest_$kilm25() {
        return this.CA;
    }

    public IconButtonComponent N$src$Lgg_vape_ui_click_component_IconButtonComponent_$t1119y() {
        return this.CE;
    }

    public PanelComponent R$src$Lgg_vape_ui_click_component_PanelComponent_$61we3d() {
        return this.Cc;
    }

    @Override
    public void H() {
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L() - 1.5, FriendRequestRow.J.m);
        this.CG.S(this.n());
    }

    public static void f(String[] stringArray) {
        CF = stringArray;
    }

    public static String[] m$src$ALjava_lang_String_$1ctc4rt() {
        return CF;
    }

    static {
        FriendRequestRow.f(new String[1]);
    }

    @Override
    public double C() {
        return 17.5;
    }

    public FriendRequestRow(FriendRequest friendRequest, FriendRequestNameTextComponent friendRequestNameTextComponent) {
        this(friendRequest);
    }

    public TextButton N$src$Lgg_vape_ui_click_component_gui_TextButton_$8z76vj() {
        return this.Cw;
    }
}

