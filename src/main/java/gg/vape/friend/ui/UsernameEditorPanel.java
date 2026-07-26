package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.LocalOnlineFriend;
import gg.vape.friend.ui.UsernameCopyClickHandler;
import gg.vape.friend.ui.UsernameEditorCurrentNameLabel;
import gg.vape.friend.ui.UsernameEditorEditButtonClickHandler;
import gg.vape.friend.ui.UsernameEditorEditModeToggleClickHandler;
import gg.vape.friend.ui.UsernameEditorTextInputComponent;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.UserDisplayNameResponsePacket;
import gg.vape.protocol.packet.UserDisplayNameStatus;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TextLabelComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.input.DebouncedTextInputComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class UsernameEditorPanel
extends PanelComponent {
    private PanelComponent kv;
    private IconButtonComponent kH = new IconButtonComponent("status online@2x", 1.4);
    private IconButtonComponent kQ;
    private FlowLayoutComponent kI = new FlowLayoutComponent(100.0);
    private IconButtonComponent kL;
    private static GuiComponent[] ke;
    private AtomicBoolean kW;
    private DebouncedTextInputComponent kw;
    private TextLabel k4;
    private PanelComponent kM;
    private PanelComponent ka = new PanelComponent(20.0, 16.0);
    private TextLabelComponent kq;

    public static void t(UsernameEditorPanel usernameEditorPanel) {
        usernameEditorPanel.s$src$V$fb5sbm();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    private void s$src$V$fb5sbm() {
        if (this.kM.V$src$Z$1xhop3l()) {
            this.kM.Z(false);
            this.kv.Z(true);
            LocalOnlineFriend localOnlineFriend = Vape.INSTANCE.getOnlineManager().r();
            String string = localOnlineFriend.C();
            this.kw.k(string);
        } else {
            this.kM.Z(true);
            this.kv.Z(false);
        }
    }

    public static void o(AtomicBoolean atomicBoolean, String string, Consumer<String> consumer, Consumer<String> consumer2) {
        if (atomicBoolean.get()) {
            return;
        }
        if (string.isEmpty() || string.equals(Vape.INSTANCE.getOnlineManager().r().C())) {
            return;
        }
        atomicBoolean.set(true);
        ZeusConnectionManager.T().u().U(string, arg_0 -> UsernameEditorPanel.lambda$inputOnEnter$0(consumer, consumer2, arg_0), () -> UsernameEditorPanel.lambda$inputOnEnter$1(atomicBoolean));
    }

    public static void t(GuiComponent[] guiComponentArray) {
        ke = guiComponentArray;
    }

    @Override
    public void H() {
        super.H();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void u() {
        super.u();
    }

    @Override
    public void Y() {
    }

    @Override
    public void v() {
    }

    @Override
    public void M() {
    }

    @Override
    public void c() {
        this.kH.Y(17.0);
        this.kH.q(8.0);
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a() - 3.0, this.n(), this.A() + 7.0, this.L(), new Color(255, 255, 255, 10));
        super.c();
        if (this.kv.V$src$Z$1xhop3l()) {
            GuiRenderPrimitives.a(this.kw.G$src$D$1b2f02a() + 4.0, this.kw.n() + 13.0, this.kw.A() - 20.0, 1.0f, UsernameEditorPanel.J.y);
        } else {
            this.kq.U(Vape.INSTANCE.getOnlineManager().r().C());
            this.kq.N(this.w$src$Z$e457mb() ? this.A() - 36.0 : this.A() - 26.0);
        }
        this.kL.o(this.w$src$Z$e457mb() ? UsernameEditorPanel.J.W : UsernameEditorPanel.J.t);
    }

    private static void lambda$inputOnEnter$0(Consumer consumer, Consumer consumer2, UserDisplayNameResponsePacket userDisplayNameResponsePacket) {
        if (userDisplayNameResponsePacket.S() == UserDisplayNameStatus.SUCCESSFUL) {
            Vape.INSTANCE.getAccountInfo().y(userDisplayNameResponsePacket.A());
            consumer.accept(userDisplayNameResponsePacket.A());
        } else if (userDisplayNameResponsePacket.S() == UserDisplayNameStatus.COOLDOWN) {
            String string = userDisplayNameResponsePacket.f() / 1000L + "s";
            consumer2.accept("On cooldown for " + string);
        } else if (userDisplayNameResponsePacket.S() == UserDisplayNameStatus.USERNAME_VALIDATION_FAILED) {
            consumer2.accept("Invalid characters were used");
        } else if (userDisplayNameResponsePacket.S() == UserDisplayNameStatus.BANNED) {
            consumer2.accept("You're banned from changing your username");
        } else if (userDisplayNameResponsePacket.S() == UserDisplayNameStatus.USERNAME_TAKEN) {
            consumer2.accept("Username already taken");
        } else {
            consumer2.accept("Name change error");
        }
    }

    public static AtomicBoolean I(UsernameEditorPanel usernameEditorPanel) {
        return usernameEditorPanel.kW;
    }

    static {
        UsernameEditorPanel.t(new GuiComponent[5]);
    }

    @Override
    public void V() {
    }

    public UsernameEditorPanel() {
        super(104.0, 16.0);
        this.kL = new IconButtonComponent("newedit", 0.6, UsernameEditorPanel.J.W, UsernameEditorPanel.J.f, 10.0, 10.0);
        this.kQ = new IconButtonComponent("newcopy", 0.6, UsernameEditorPanel.J.h);
        this.kM = new PanelComponent(104.0, 16.0);
        this.kv = new PanelComponent(104.0, 16.0);
        this.kW = new AtomicBoolean();
        this.kw = new UsernameEditorTextInputComponent(this, "Enter username", 10000L);
        this.k4 = new TextLabel("Cancel", 0.8, false, UsernameEditorPanel.J.l);
        this.kH.Y(16.0);
        this.kH.o(Color.WHITE);
        this.kH.P(Color.WHITE);
        this.kq = new UsernameEditorCurrentNameLabel(this, "", 0.6, 0.8, 0.1, this.w$src$Z$e457mb() ? this.A() - 36.0 : this.A() - 26.0, true, false, UsernameEditorPanel.J.h, this);
        this.d(false);
        this.t(16.0);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.kw.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().W("newnext");
        this.kI.d(false);
        this.kQ.q(10.0);
        this.kQ.u(10.0);
        this.kL.q(10.0);
        this.kL.u(10.0);
        this.kQ.l(UsernameEditorPanel.J.y);
        this.ka.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.ka.d(false);
        this.ka.q(25.0);
        this.kI.H(new SpacerComponent(2.0, 1.0), this.kH, this.kq);
        this.ka.H(this.kL);
        this.ka.H(new SpacerComponent(2.0, 1.0));
        this.ka.H(this.kQ);
        this.kM.h(this.kI, new Object[0]);
        this.kM.h(this.ka, "alignright");
        this.kM.d(false);
        this.kv.h(this.kw, new Object[0]);
        this.kv.h(this.k4, new Object[0]);
        this.kv.Z(false);
        this.kv.d(false);
        this.kw.e(false);
        this.kw.n(16);
        this.kL.l(UsernameEditorPanel.J.t);
        this.k4.u(10.0);
        this.k4.q(22.0);
        this.kv.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        this.kL.w("Edit display name");
        this.kQ.w("Copy display name");
        this.h(this.kM, new Object[0]);
        this.h(this.kv, new Object[0]);
        this.kQ.r(new UsernameCopyClickHandler(this));
        this.kL.r(new UsernameEditorEditButtonClickHandler(this));
        this.k4.r(new UsernameEditorEditModeToggleClickHandler(this));
    }

    public static GuiComponent[] C$src$ALgg_vape_ui_click_component_GuiComponent_$15ojrbs() {
        return ke;
    }

    private static void lambda$inputOnEnter$1(AtomicBoolean atomicBoolean) {
        atomicBoolean.set(false);
    }
}

