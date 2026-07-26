package gg.vape.ui.click.frame.impl.main;

import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineStatus;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsOnlineIndicatorComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsPrimaryActionButton;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsSecondaryActionButton;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsStatusIconComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClickGuiFriendsFriendListComponent
extends InteractiveComponent {
    private static final double ZW = 6.0;
    private static final float ZU = 3.0f;
    private static final double ZK = 48.0;
    private static final Color Zd;
    private static final double Q = 0.75;
    private static final double Zw = 4.0;
    private static final double Zg = 7.0;
    private static final double ZY = 6.0;
    private static final Color b;
    private static final Color ZJ;
    private static final double Z3 = 6.0;
    private final OnlineFriend Zl;
    private final ClickGuiFriendsStatusIconComponent Zs;
    private boolean Z4;
    private final ClickGuiFriendsSecondaryActionButton ZC;
    private static final double Zm = 6.0;
    private static final double K = 6.0;
    private static final String ZG;
    private static final double Za = 0.625;
    private static final double ZX = 10.0;
    private final ColorAnimation Zr;
    private final ClickGuiFriendsPrimaryActionButton Zn;
    private static final double ZV = 20.0;
    private static final double Z1 = 8.0;
    private static final double Zu = 10.0;
    private static final double Zt = 0.5;
    private static final Color ZO;
    private static final Color ZF;
    private static final Color I;
    private static final String Z7;
    private final TruncatedTextComponent Zf;
    private static final double ZR = 14.0;
    private static final String ZB;
    private final ClickGuiFriendsSecondaryActionButton Zx;
    private final TruncatedTextComponent ZD;
    private static final double Z_ = 22.0;
    private static final double Z8 = 3.0;
    private static final double ZQ = 20.0;
    private static final double ZM = 10.0;
    private static final Color v;
    private static final Color Zk;
    private static final double ZZ = 7.0;
    private static final double ZP = 10.0;
    private static final double Z0 = 5.0;
    private static final double ZA = 1.0;
    private static final Color Ze;
    private static final float ZH = 2.0f;
    private final TruncatedTextComponent ZS;
    private boolean Zv;
    private static final Color ZT;
    private final ClickGuiFriendsOnlineIndicatorComponent Zj;

    private String t(boolean bl) {
        boolean bl2;
        boolean bl3 = bl2 = this.Zl.u() && this.Zl.I() != null && !this.Zl.I().isEmpty();
        if (bl2) {
            return this.Zl.I();
        }
        OnlineStatus onlineStatus = this.Zl.F();
        if (onlineStatus != null) {
            return onlineStatus.f();
        }
        return "";
    }

    private String A$src$Ljava_lang_String_$1yea3tj(boolean bl) {
        if (bl && this.Zl.C() != null) {
            return this.Zl.C();
        }
        String string = this.Zl.C();
        return string != null ? string : "";
    }

    public void U(boolean bl) {
        this.Z4 = bl;
    }

    private String x$src$Ljava_lang_String_$15m9aos() {
        String string = this.Zl.v();
        if (string == null) {
            return "";
        }
        return string.trim();
    }

    static Color G$src$Ljava_awt_Color_$1oq4c31() {
        return b;
    }

    static OnlineFriend H(ClickGuiFriendsFriendListComponent clickGuiFriendsFriendListComponent) {
        return clickGuiFriendsFriendListComponent.Zl;
    }

    @Override
    public void H() {
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        double d6;
        super.H();
        boolean bl = this.w$src$Z$e457mb();
        this.Zr.u(bl);
        double d7 = this.G$src$D$1b2f02a();
        double d8 = this.n();
        double d9 = this.A();
        double d10 = this.L();
        GuiRenderPrimitives.B(d7, d8, d9, d10, this.Zr.getInterpolatedColor(), 3.0f);
        double d11 = d7 + 6.0;
        double d12 = d8 + (d10 - 10.0) / 2.0;
        this.Zs.K(d11);
        this.Zs.S(d12);
        this.Zs.o(10.0);
        this.Zs.Y(10.0);
        this.Zs.H(5.0);
        double d13 = d7 + d9 - 6.0 - 10.0;
        double d14 = d8 + (d10 - 10.0) / 2.0;
        this.Zx.K(d13);
        this.Zx.S(d14);
        this.Zx.o(10.0);
        this.Zx.Y(10.0);
        this.Zx.Z(true);
        double d15 = d13 - 4.0;
        this.ZC.Z(false);
        if (!this.Zv && this.Z4) {
            d6 = d13 - 14.0 - 10.0;
            d5 = d8 + (d10 - 10.0) / 2.0;
            this.ZC.K(d6);
            this.ZC.S(d5);
            this.ZC.o(10.0);
            this.ZC.Y(10.0);
            this.ZC.Z(true);
            d15 = d6 - 4.0;
        }
        if (this.Zv) {
            ClickGuiFriendsPrimaryActionButton.T(this.Zn);
            d6 = this.Zn.A();
            d5 = d7 + d9 - 20.0 - d6;
            d4 = d8 + (d10 - 10.0) / 2.0;
            this.Zn.K(d5);
            this.Zn.S(d4);
            this.Zn.Y(10.0);
            d15 = d5 - 4.0;
        }
        String string = this.A$src$Ljava_lang_String_$1yea3tj(bl);
        String string2 = this.t(bl);
        String string3 = this.x$src$Ljava_lang_String_$15m9aos();
        SmoothFontRenderer smoothFontRenderer = this.O(0.625);
        d4 = string3.isEmpty() ? 0.0 : smoothFontRenderer.N(string3) + 6.0 + 3.0;
        double d16 = d15;
        if (d4 > 0.0) {
            d16 = Math.max(78.0, d15 - d4);
            d3 = d8 + (d10 - 7.0) / 2.0;
            this.Zj.Z(true);
            this.Zj.K(d16);
            this.Zj.S(d3);
            this.Zj.o(6.0);
            this.Zj.Y(7.0);
            d2 = d16 + 6.0 + 3.0;
            d = Math.max(0.0, d15 - d2);
            this.ZS.Z(true);
            this.ZS.K(d2);
            this.ZS.S(d3);
            this.ZS.o(d);
            this.ZS.Y(7.0);
            this.ZS.D(d);
            this.ZS.O(string3);
        } else {
            this.Zj.Z(false);
            this.ZS.Z(false);
            this.ZS.O("");
            d16 = d15;
        }
        d3 = d4 > 0.0 ? d16 - 10.0 : d15;
        d2 = d7 + 20.0;
        d = Math.max(0.0, d3 - d2);
        double d17 = 16.0;
        double d18 = d8 + (d10 - 16.0) / 2.0;
        this.Zf.K(d2);
        this.Zf.S(d18);
        this.Zf.o(d);
        this.Zf.Y(8.0);
        this.Zf.D(d);
        this.Zf.O(string);
        this.Zf.R(bl ? Zk : Ze);
        this.ZD.K(d2);
        this.ZD.S(d18 + 8.0 + 1.0);
        this.ZD.o(d);
        this.ZD.Y(7.0);
        this.ZD.D(d);
        this.ZD.O(string2);
        this.ZD.Z(!string2.isEmpty());
        ClickGuiFriendsSecondaryActionButton.y(this.ZC, this.Zl.r());
        this.Zs.n(bl);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static {
        ZG = "settingdots";
        Z7 = "avatar offline@2x";
        ZB = "chat@2x";
        v = ClickGuiFriendsFriendListComponent.J.m;
        ZF = new Color(34, 33, 34);
        Ze = ClickGuiFriendsFriendListComponent.J.A;
        Zk = ClickGuiFriendsFriendListComponent.J.f;
        ZT = ClickGuiFriendsFriendListComponent.J.C;
        ZJ = ClickGuiFriendsFriendListComponent.J.C;
        ZO = new Color(103, 101, 103);
        I = ClickGuiFriendsFriendListComponent.J.B;
        Zd = ClickGuiFriendsFriendListComponent.J.O;
        b = Color.WHITE;
    }

    static Color R() {
        return Zd;
    }

    public OnlineFriend K$src$Lgg_vape_friend_OnlineFriend_$1sampoe() {
        return this.Zl;
    }

    static Color g$src$Ljava_awt_Color_$1lgjsp9() {
        return ZO;
    }

    static Color W() {
        return I;
    }

    public void w(@Nullable GuiClickListener guiClickListener) {
        this.Zx.s(guiClickListener);
    }

    public ClickGuiFriendsFriendListComponent(@NotNull OnlineFriend onlineFriend) {
        this.getClass();
        this.Zr = new ColorAnimation(0.15, v, ZF);
        this.Zs = new ClickGuiFriendsStatusIconComponent(this, null);
        this.Zf = new TruncatedTextComponent("", "...", 0.0, 0.75, Ze, false, false);
        this.ZD = new TruncatedTextComponent("", "...", 0.0, 0.625, ZT, false, false);
        this.ZS = new TruncatedTextComponent("", "...", 0.0, 0.625, ZJ, false, false);
        this.Zj = new ClickGuiFriendsOnlineIndicatorComponent(this, null);
        this.ZC = new ClickGuiFriendsSecondaryActionButton(this, "chat@2x", null);
        this.Zx = new ClickGuiFriendsSecondaryActionButton(this, "settingdots", null);
        this.Zn = new ClickGuiFriendsPrimaryActionButton(this, "ACCEPT", null);
        this.Z4 = true;
        this.Zv = false;
        this.Zl = onlineFriend;
        this.d(false);
        this.Y(22.0);
        this.Zs.d(false);
        this.Zs.S(false);
        this.Zf.K(false);
        this.ZD.K(false);
        this.ZS.K(false);
        this.Zj.Z(false);
        this.Zj.S(false);
        this.ZC.Z(false);
        this.Zx.Z(true);
        this.Zn.Z(false);
        this.Zf.G("");
        this.ZD.G("");
        this.H(this.Zs, this.Zf, this.ZD, this.Zj, this.ZS, this.ZC, this.Zx, this.Zn);
    }

    public void Y(@Nullable GuiClickListener guiClickListener) {
        this.Zn.s(guiClickListener);
    }

    public void v(boolean bl) {
        this.Zv = bl;
        this.Zn.Z(bl);
        if (bl) {
            this.ZC.Z(false);
        }
    }

    public void b(@Nullable GuiClickListener guiClickListener) {
        this.ZC.s(guiClickListener);
    }
}

