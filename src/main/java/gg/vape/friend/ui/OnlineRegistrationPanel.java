package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.ui.OnlineFriendsFrame;
import gg.vape.manager.client.OnlineAccountState;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.gui.WrappedTextComponent;
import gg.vape.ui.click.frame.SettingsSectionComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class OnlineRegistrationPanel
extends GuiComponent {
    private long i;
    private final TextLabel G;
    private boolean I;
    private final TextButton O;
    private final WrappedTextComponent v;

    @Override
    public double x() {
        return 105.0;
    }

    private void C$src$V$1m5h0jt() {
        if (OnlineConnectionManager.T.j() == OnlineAccountState.CONNECTING) {
            return;
        }
        if (this.I) {
            return;
        }
        if (this.i != -1L) {
            if (this.i - System.currentTimeMillis() > 0L) {
                return;
            }
            this.i = -1L;
        }
        this.I = true;
        try {
            OnlineConnectionManager.T.E();
            if (OnlineConnectionManager.T.j() != OnlineAccountState.REGISTRATION_OFFLINE) {
                ClientSettings.getFrame(OnlineFriendsFrame.class).Q$src$V$v8j9by();
                this.i = -1L;
            }
        }
        catch (Exception exception) {
            this.i = System.currentTimeMillis() + 10000L;
            Vape.logThrowable(exception);
        }
        this.I = false;
    }

    public OnlineRegistrationPanel() {
        this.O = new TextButton("Reattempt", 0.8, OnlineRegistrationPanel.J.B, OnlineRegistrationPanel.J.O);
        this.G = new TextLabel("Maybe later", 0.8, false);
        this.v = new WrappedTextComponent("", 0.8, OnlineRegistrationPanel.J.Z, false);
        this.I = false;
        this.i = -1L;
        this.addChildren(this.v, this.O, this.G);
        this.G.o(32.0);
        this.G.Y(10.0);
        this.G.addClickListener(this::n$src$V$1mt462c);
        this.v.setFontScale(0.9);
        this.v.setWrapWidth(90.0);
        this.O.setDeriveTextColorFromBackground(false);
        this.O.setNormalTextColor(OnlineRegistrationPanel.J.A);
        this.O.addClickListener(this::C$src$V$1m5h0jt);
    }

    @Override
    public void H() {
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), OnlineRegistrationPanel.J.i);
    }

    private void n$src$V$1mt462c() {
        OnlineFriendsFrame onlineFriendsFrame = ClientSettings.getFrame(OnlineFriendsFrame.class);
        onlineFriendsFrame.Q$src$V$v8j9by();
        onlineFriendsFrame.p$src$Lgg_vape_friend_ui_OnlineModeToggleComponent_$u0bbsl().u(false);
        for (GuiComponent guiComponent : ClientSettings.getFrame(OnlineFriendsFrame.class).h()) {
            if (!(guiComponent instanceof SettingsSectionComponent) || !((SettingsSectionComponent)guiComponent).A$src$Ljava_lang_String_$9tmd4u().equals("Online Settings")) continue;
            guiComponent.setVisible(false);
        }
    }

    @Override
    public void F() {
    }

    @Override
    public void I() {
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @Override
    public double C() {
        return 155.0;
    }

    @Override
    public void u() {
    }

    @Override
    public void c() {
        this.O.setNormalTextColor(Color.white);
        double d = 8.0;
        this.v.K(this.G$src$D$1b2f02a() + d);
        this.v.S(this.n() + 30.0);
        this.O.K(this.G$src$D$1b2f02a() + d * 1.0);
        this.O.S(this.n() + 45.0);
        this.O.setExplicitWidth(this.A() - d * 2.0);
        this.O.o(this.A() - d * 2.0);
        this.O.Y(14.0);
        if (OnlineConnectionManager.T.j() == OnlineAccountState.CONNECTING) {
            this.O.setVisible(false);
            this.v.setText("Checking Account");
        } else {
            this.O.setVisible(true);
            this.v.setText("Authentication Error");
            if (this.i != -1L) {
                int n = (int)((this.i - System.currentTimeMillis()) / 1000L);
                if (n >= 0) {
                    this.O.setLabelText("Reattempt in " + n + " second" + (n == 1 ? "" : "s") + "...");
                } else {
                    this.i = -1L;
                    this.O.setLabelText("Reattempt");
                }
            }
        }
        this.G.K(this.G$src$D$1b2f02a() + this.A() - this.G.A() - d);
        this.G.S(this.n() + this.L() + 3.0 - this.G.L() - 12.0);
        super.c();
        GuiRenderPrimitives.L(this.G$src$D$1b2f02a() + this.A() - this.G.A() - d, this.n() + this.L() - 10.0, this.G.A(), OnlineRegistrationPanel.J.Z);
    }
}
