package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.LocalOnlineFriend;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.PartyInvite;
import gg.vape.friend.PartyState;
import gg.vape.friend.ui.PartyInviteStatusIconComponent;
import gg.vape.friend.ui.PartyMemberEntryMode;
import gg.vape.module.none.ClientSettings;
import gg.vape.protocol.ZeusConnectionManager;
import gg.vape.protocol.packet.GroupDeleteResponsePacket;
import gg.vape.protocol.packet.GroupInviteStateResponsePacket;
import gg.vape.protocol.packet.GroupInviteStateStatus;
import gg.vape.protocol.packet.GroupLeaveResponsePacket;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.IconShape;
import gg.vape.ui.click.component.ShapeIconComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.frame.DimmedCenteredPopupFrame;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PartyMemberEntryComponent
extends InteractiveComponent {
    private static final double tP = 12.0;
    private final ColorAnimation t3;
    private static final double tE = 4.0;
    private static final Color tS;
    private static final Color t4;
    private static final double tl = 6.0;
    private final TruncatedTextComponent tr;
    private static final double tw = 0.625;
    private static final Color tH;
    private static final Color tt;
    private static final double b = 8.0;
    private static final Color tb;
    private static final float tC = 2.5f;
    private static final Color to;
    private static final String ta;
    @Nullable
    private final Supplier<@Nullable PartyState> tc;
    private static final Color tp;
    private static final Color t6;
    private static final double tY = 0.75;
    private static final String tj;
    private static final Color t7;
    private static final Color tZ;
    private static final double tX = 12.0;
    @Nullable
    private final PartyInviteStatusIconComponent Q;
    private final PartyMemberEntryMode tV;
    private static final Color tJ;
    private static final Color td;
    private static final float tn = 3.0f;
    @Nullable
    private final PartyInvite K;
    private static final double tv = 20.0;
    @Nullable
    private final ColorAnimation tT;
    private boolean v;
    private static final double t8 = 8.0;
    private static final double tF = 22.0;
    private static final Color t_;
    private static final double t5 = 10.0;
    private static final double tW = 0.5;
    private static final double I = 6.0;
    @Nullable
    private final ShapeIconComponent tk;
    private static final double ts = 8.0;
    private boolean tm;
    private final TruncatedTextComponent tu;
    private final TextButton t2;
    private static final Color t1;
    private static final double tO = 6.0;
    private static final Color tg;
    private static final double tR = 0.5;
    private static final Color tG;
    private static final double te = 10.0;
    private static final Color tU;
    private static final Color t9;

    private static String y(@Nullable OnlineFriend onlineFriend) {
        if (onlineFriend == null) {
            return "Party";
        }
        String string = onlineFriend.C();
        if (string == null || string.isEmpty()) {
            return "Party";
        }
        if (string.endsWith("'s Party") || string.endsWith("' Party")) {
            return string;
        }
        return string + "'s Party";
    }

    static Color c$src$Ljava_awt_Color_$ph3aai() {
        return tH;
    }

    private static String d(@NotNull OnlineFriend onlineFriend) {
        String string = onlineFriend.C();
        String string2 = onlineFriend.I();
        if (string2 != null && !string2.isEmpty() && !string2.equalsIgnoreCase(string)) {
            return string + " (" + string2 + ") invited you";
        }
        return string + " invited you";
    }

    private void V$src$V$1xpbqj3() {
        this.Y(22.0);
        this.setShowDisabledOverlay(false);
        this.tu.setShadowEnabled(false);
        this.tr.setShadowEnabled(false);
        this.tr.setAdditionalTooltipText("");
        this.t2.Y(10.0);
        this.t2.setDeriveTextColorFromBackground(false);
        this.t2.setNormalTextColor(Color.WHITE);
        this.t2.setCornerRadius(2.0f);
        this.t2.addClickListener(this::v);
        this.addChildren(this.tu, this.tr, this.t2);
        if (this.tk != null) {
            this.addChildren(this.tk);
        }
        if (this.Q != null) {
            this.addChildren(this.Q);
            this.Q.addClickListener(this::U$src$V$1xorxxq);
        }
    }

    public PartyMemberEntryComponent(@NotNull PartyInvite partyInvite) {
        this.tV = PartyMemberEntryMode.INVITE;
        this.tc = null;
        this.K = Objects.requireNonNull(partyInvite, "invite");
        this.getClass();
        this.t3 = new ColorAnimation(0.15, tZ, td);
        this.getClass();
        this.tT = new ColorAnimation(0.15, t9, to);
        this.tu = this.S(tJ);
        this.tr = this.g$src$Lgg_vape_ui_click_component_TruncatedTextCompone$1sn5zit();
        this.tk = null;
        this.t2 = this.T(PartyMemberEntryComponent.J.B, PartyMemberEntryComponent.J.O, "ACCEPT");
        this.Q = new PartyInviteStatusIconComponent(this, null);
        this.Q.w("Decline party invite");
        this.V$src$V$1xpbqj3();
    }

    private void lambda$handleCloseAction$1() {
        this.tm = false;
    }

    private void lambda$null$3() {
        this.v = false;
    }

    private void lambda$handleCloseAction$0(GroupInviteStateResponsePacket groupInviteStateResponsePacket) {
        if (groupInviteStateResponsePacket.M() == GroupInviteStateStatus.SUCCESSFULLY_DECLINED || groupInviteStateResponsePacket.M() == GroupInviteStateStatus.FAILED) {
            Vape.INSTANCE.getOnlineManager().y().y(this.K);
        }
    }

    private void lambda$sendInviteAcceptance$11() {
        this.v = false;
    }

    private void z() {
        PartyState partyState;
        PartyState partyState2 = partyState = this.tc != null ? this.tc.get() : null;
        if (partyState == null) {
            this.v = false;
            return;
        }
        LocalOnlineFriend localOnlineFriend = Vape.INSTANCE.getOnlineManager().r();
        boolean bl = partyState.r() != null && partyState.r().equals(localOnlineFriend);
        ZeusConnectionManager zeusConnectionManager = ZeusConnectionManager.T();
        if (zeusConnectionManager == null) {
            this.v = false;
            return;
        }
        if (bl) {
            this.U("Are you sure you want to disband the party?", () -> this.lambda$handleCurrentPartyAction$4(zeusConnectionManager));
        } else {
            zeusConnectionManager.u().u(PartyMemberEntryComponent::lambda$handleCurrentPartyAction$5, this::lambda$handleCurrentPartyAction$6);
        }
    }

    public PartyMemberEntryComponent(@NotNull Supplier<@Nullable PartyState> supplier) {
        this.tV = PartyMemberEntryMode.CURRENT_PARTY;
        this.tc = Objects.requireNonNull(supplier, "partySupplier");
        this.K = null;
        this.getClass();
        this.t3 = new ColorAnimation(0.15, tG, tS);
        this.getClass();
        this.tT = new ColorAnimation(0.15, tt, tp);
        this.tu = this.S(t4);
        this.tr = this.g$src$Lgg_vape_ui_click_component_TruncatedTextCompone$1sn5zit();
        this.tk = new ShapeIconComponent(IconShape.ROUNDED_RECT, "ACTIVE", 10.0, 12.0, 4.0, 2.5f, tU, t_, 0.5);
        this.t2 = this.T(PartyMemberEntryComponent.J.d, PartyMemberEntryComponent.J.c, "DISBAND");
        this.Q = null;
        this.V$src$V$1xpbqj3();
    }

    private static void lambda$null$2(GroupDeleteResponsePacket groupDeleteResponsePacket) {
    }

    private void L$src$V$1xjtslh() {
        this.setVisible(true);
        OnlineFriend onlineFriend = this.K.x();
        this.tu.setText(PartyMemberEntryComponent.y(onlineFriend));
        this.tr.setText(PartyMemberEntryComponent.d(onlineFriend));
        this.t2.w("Accept party invite");
    }

    private void o$src$V$1y32ld4() {
        double d;
        double d2;
        double d3;
        double d4 = this.G$src$D$1b2f02a();
        double d5 = this.n();
        double d6 = this.A();
        double d7 = this.L();
        double d8 = d4 + d6 - 8.0;
        if (this.Q != null) {
            d3 = d8 - 12.0;
            this.Q.o(12.0);
            this.Q.Y(12.0);
            this.Q.K(d3);
            this.Q.S(d5 + (d7 - 12.0) / 2.0);
            d8 = d3 - 8.0;
        }
        this.E(d5, d7);
        d8 = Math.min(d8, this.t2.G$src$D$1b2f02a() - 8.0);
        d3 = d4 + 20.0;
        double d9 = Math.max(0.0, d8 - d3);
        if (this.tk != null && this.tk.V$src$Z$1xhop3l()) {
            d2 = this.tk.getRequiredWidth();
            d9 = Math.max(0.0, d9 - d2 - 6.0);
            d = d4 + 20.0 + d9 + 6.0;
            this.tk.K(d);
            this.tk.S(d5 + (d7 - 10.0) / 2.0);
            this.tk.o(d2);
            this.tk.Y(10.0);
        }
        this.tu.K(d3);
        this.tu.S(d5);
        this.tu.o(d9);
        this.tu.Y(d7);
        this.tu.setMaxWidth(d9);
        d2 = d4 + 8.0 + 6.0 + 4.0;
        d = Math.max(0.0, d8 - d2);
        this.tr.K(d2);
        this.tr.S(d5);
        this.tr.o(d);
        this.tr.Y(d7);
        this.tr.setMaxWidth(d);
    }

    private void D$src$V$1xffful() {
        double d = this.G$src$D$1b2f02a() + 8.0;
        double d2 = this.n() + (this.L() - 6.0) / 2.0;
        Color color = this.tV == PartyMemberEntryMode.CURRENT_PARTY ? tg : t7;
        ImageRenderer.drawImage(color, (float)d, (float)d2, "party1@2x", 6.0f, 6.0f, false);
    }

    @Override
    public void u() {
        super.u();
        switch (this.tV) {
            case CURRENT_PARTY: {
                this.C$src$V$1xevn98();
                break;
            }
            case INVITE: {
                this.L$src$V$1xjtslh();
            }
        }
    }

    private void l$src$V$1y1f7l1() {
        if (this.K == null) {
            return;
        }
        ZeusConnectionManager zeusConnectionManager = ZeusConnectionManager.T();
        if (zeusConnectionManager == null) {
            this.v = false;
            return;
        }
        PartyState partyState = Vape.INSTANCE.getOnlineManager().y().j();
        if (partyState != null) {
            LocalOnlineFriend localOnlineFriend = Vape.INSTANCE.getOnlineManager().r();
            boolean bl = partyState.r() != null && partyState.r().equals(localOnlineFriend);
            this.U(bl ? "Are you sure you want to disband the party?" : "Are you sure you want to leave your current party?", () -> this.lambda$handleInviteAction$9(zeusConnectionManager));
            return;
        }
        this.q(zeusConnectionManager);
    }

    private void q(ZeusConnectionManager zeusConnectionManager) {
        zeusConnectionManager.u().c(this.K.x().S(), true, this::lambda$sendInviteAcceptance$10, this::lambda$sendInviteAcceptance$11);
    }

    private static void lambda$handleCurrentPartyAction$5(GroupLeaveResponsePacket groupLeaveResponsePacket) {
    }

    private static void lambda$showConfirmationPopup$7(PopupFrame popupFrame, Runnable runnable) {
        ClientSettings.removePopup(popupFrame);
        runnable.run();
    }

    private TruncatedTextComponent g$src$Lgg_vape_ui_click_component_TruncatedTextCompone$1sn5zit() {
        TruncatedTextComponent truncatedTextComponent = new TruncatedTextComponent("", "", 0.0, 0.625, t1, false);
        truncatedTextComponent.setCentered(true);
        return truncatedTextComponent;
    }

    private void v() {
        if (this.v) {
            return;
        }
        this.v = true;
        switch (this.tV) {
            case CURRENT_PARTY: {
                this.z();
                break;
            }
            case INVITE: {
                this.l$src$V$1y1f7l1();
            }
        }
    }

    private void lambda$handleCurrentPartyAction$6() {
        this.v = false;
    }

    private void lambda$handleInviteAction$9(ZeusConnectionManager zeusConnectionManager) {
        this.q(zeusConnectionManager);
    }

    private void E(double d, double d2) {
        double d3 = this.t2.getTextWidth();
        double d4 = Math.max(d3 + 8.0, this.t2.A());
        this.t2.o(d4);
        this.t2.Y(10.0);
        double d5 = this.G$src$D$1b2f02a() + this.A() - 8.0 - d4 - (this.Q != null ? 20.0 : 0.0);
        this.t2.K(d5);
        this.t2.S(d + (d2 - 10.0) / 2.0);
    }

    static Color q$src$Ljava_awt_Color_$1uk170() {
        return tb;
    }

    @Override
    public void H() {
        super.H();
        if (!this.V$src$Z$1xhop3l()) {
            return;
        }
        boolean bl = this.w$src$Z$e457mb();
        this.t3.u(bl);
        if (this.tT != null) {
            this.tT.u(bl);
        }
        GuiRenderPrimitives.B(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.t3.getInterpolatedColor(), 3.0f);
        if (this.tT != null) {
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.tT.getInterpolatedColor(), 3.0f, 1.0f, 1.0f);
        }
        this.o$src$V$1y32ld4();
        this.D$src$V$1xffful();
        this.tr.setTextColor(bl ? t6 : t1);
        this.tu.setTextColor(this.tV == PartyMemberEntryMode.CURRENT_PARTY ? t4 : tJ);
    }


    private void lambda$handleCurrentPartyAction$4(ZeusConnectionManager zeusConnectionManager) {
        zeusConnectionManager.u().l(PartyMemberEntryComponent::lambda$null$2, this::lambda$null$3);
    }

    private void lambda$showConfirmationPopup$8(PopupFrame popupFrame) {
        ClientSettings.removePopup(popupFrame);
        this.v = false;
    }

    private void lambda$sendInviteAcceptance$10(GroupInviteStateResponsePacket groupInviteStateResponsePacket) {
        if (groupInviteStateResponsePacket.M() == GroupInviteStateStatus.SUCCESSFULLY_ACCEPTED) {
            Vape.INSTANCE.getOnlineManager().y().y(this.K);
        }
    }

    private void C$src$V$1xevn98() {
        PartyState partyState;
        PartyState partyState2 = partyState = this.tc != null ? this.tc.get() : null;
        if (partyState != null) {
            boolean bl;
            this.setVisible(true);
            OnlineFriend onlineFriend = partyState.r();
            LocalOnlineFriend localOnlineFriend = Vape.INSTANCE.getOnlineManager().r();
            boolean bl2 = bl = onlineFriend != null && onlineFriend.equals(localOnlineFriend);
            if (bl) {
                this.tu.setText("My VAPE Party");
                this.t2.setLabelText("DISBAND");
                this.t2.w("Disband party");
            } else {
                this.tu.setText(PartyMemberEntryComponent.y(onlineFriend));
                this.t2.setLabelText("LEAVE");
                this.t2.w("Leave party");
            }
            this.tr.setText(PartyMemberEntryComponent.c(partyState, localOnlineFriend));
            if (this.tk != null) {
                this.tk.setText("ACTIVE");
                this.tk.setVisible(true);
            }
            return;
        }
        this.setVisible(false);
    }

    static {
        tj = "newclose";
        ta = "party1@2x";
        tG = new Color(98, 197, 84, 10);
        tS = new Color(98, 197, 84, 24);
        tt = new Color(98, 197, 84, 31);
        tp = new Color(98, 197, 84, 56);
        tZ = PartyMemberEntryComponent.J.m;
        td = PartyMemberEntryComponent.J.a;
        t9 = new Color(255, 255, 255, 10);
        to = new Color(255, 255, 255, 26);
        tJ = PartyMemberEntryComponent.J.A;
        t4 = Color.WHITE;
        t1 = PartyMemberEntryComponent.J.C;
        t6 = PartyMemberEntryComponent.J.Z;
        tU = new Color(98, 197, 84, 20);
        t_ = new Color(98, 197, 84);
        tg = new Color(98, 197, 84);
        t7 = new Color(173, 173, 173);
        tH = PartyMemberEntryComponent.J.W;
        tb = PartyMemberEntryComponent.J.f;
    }

    private TruncatedTextComponent S(Color color) {
        return new TruncatedTextComponent("", "...", 0.0, 0.75, color, false);
    }

    private void U(String string, Runnable runnable) {
        ConfirmationDialogComponent confirmationDialogComponent = new ConfirmationDialogComponent(string, "DISBAND", "disband confirm@2x");
        DimmedCenteredPopupFrame dimmedCenteredPopupFrame = ClientSettings.createPopup(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), confirmationDialogComponent, DimmedCenteredPopupFrame.class);
        confirmationDialogComponent.getConfirmButton().addClickListener(() -> PartyMemberEntryComponent.lambda$showConfirmationPopup$7(dimmedCenteredPopupFrame, runnable));
        confirmationDialogComponent.getCloseButton().addClickListener(() -> this.lambda$showConfirmationPopup$8(dimmedCenteredPopupFrame));
        dimmedCenteredPopupFrame.q(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), dimmedCenteredPopupFrame);
    }

    private static String c(@NotNull PartyState partyState, @Nullable OnlineFriend onlineFriend) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (OnlineFriend object2 : partyState.c()) {
            String n;
            if (onlineFriend != null && object2.equals(onlineFriend) || (n = object2.C()) == null || n.isEmpty()) continue;
            arrayList.add(n);
        }
        if (arrayList.isEmpty()) {
            return "Waiting for members to join";
        }
        if (arrayList.size() == 1) {
            return (String)arrayList.get(0) + " joined";
        }
        String string = (String)arrayList.get(0);
        String string2 = (String)arrayList.get(1);
        int n = arrayList.size() - 2;
        if (n <= 0) {
            return (String)string + ", " + string2 + " joined";
        }
        return (String)string + ", " + string2 + " + " + n + " others joined";
    }

    private void U$src$V$1xorxxq() {
        if (this.tm || this.K == null) {
            return;
        }
        ZeusConnectionManager zeusConnectionManager = ZeusConnectionManager.T();
        if (zeusConnectionManager == null) {
            return;
        }
        this.tm = true;
        zeusConnectionManager.u().c(this.K.x().S(), false, this::lambda$handleCloseAction$0, this::lambda$handleCloseAction$1);
    }

    private TextButton T(Color color, Color color2, String string) {
        TextButton textButton = new TextButton(string, 0.5, color, color2);
        textButton.setUppercase(true);
        return textButton;
    }
}
