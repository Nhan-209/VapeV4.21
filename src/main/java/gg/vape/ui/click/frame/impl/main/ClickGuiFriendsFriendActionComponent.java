package gg.vape.ui.click.frame.impl.main;

import gg.vape.friend.ui.PlayerAvatarComponent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.IconShape;
import gg.vape.ui.click.component.ShapeIconComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.PlayerInfo;
import gg.vape.wrapper.impl.ResourceLocation;
import java.awt.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClickGuiFriendsFriendActionComponent
extends InteractiveComponent {
    private final String HL;
    private static final double b = 6.0;
    private GuiClickListener H7;
    private final PlayerAvatarComponent HA;
    private static final Color Hv;
    private final TruncatedTextComponent Hh;
    private static final Color Ha;
    private static final double Hk = 12.0;
    private static final double Hm = 6.0;
    private static final double Q = 22.0;
    private final TextButton He;
    private final ShapeIconComponent HO;
    private static final double HV = 10.0;
    private static final double H0 = 6.0;
    private final ColorAnimation HS;
    private static final double H5 = 50.0;
    private static final double K = 22.0;
    private static final Color Hr;
    private static final double H6 = 0.75;
    private static final float Hi = 3.0f;
    private static final double HU = 35.0;
    private final TextButton HN;
    private static final double Hf = 0.5;
    private GuiClickListener I;
    private static final double HH = 4.0;
    private static final Color HJ;
    private static final Color HT;
    private static final double HX = 14.0;
    private static final double HZ = 14.0;
    private static final Color v;
    private static final double Hc = 4.0;
    private boolean Hb;
    private static final float H_ = 2.5f;

    @Override
    public void H() {
        super.H();
        boolean bl = this.w$src$Z$e457mb();
        this.HS.u(bl);
        GuiRenderPrimitives.B(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.HS.getInterpolatedColor(), 3.0f);
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        double d3 = this.L();
        double d4 = this.A();
        this.HA.K(d + 4.0);
        this.HA.S(d2 + (d3 - 14.0) / 2.0);
        this.HA.o(14.0);
        this.HA.Y(14.0);
        double d5 = d + d4 - 6.0 - 35.0;
        double d6 = d2 + (d3 - 14.0) / 2.0;
        this.He.K(d5);
        this.He.S(d6);
        this.He.o(35.0);
        this.He.Y(14.0);
        double d7 = d + d4 - 6.0 - 50.0;
        double d8 = d2 + (d3 - 14.0) / 2.0;
        this.HN.K(d7);
        this.HN.S(d8);
        this.HN.o(50.0);
        this.HN.Y(14.0);
        double d9 = this.Hb ? d7 - 6.0 : d5 - 6.0;
        double d10 = d + 22.0;
        double d11 = Math.max(0.0, d9 - d10);
        if (this.HO != null) {
            double d12 = d9 - d10;
            double d13 = this.HO.O$src$D$h1g6kw();
            double d14 = Math.max(0.0, d12 - d13 - 6.0);
            SmoothFontRenderer smoothFontRenderer = this.O(0.75);
            double d15 = smoothFontRenderer.N(this.HL);
            double d16 = Math.min(d15, d14);
            this.HO.K(d10 + d16 + 6.0);
            this.HO.S(d2 + (d3 - 10.0) / 2.0);
            this.HO.o(d13);
            this.HO.Y(10.0);
            d11 = d14;
        }
        this.Hh.K(d10);
        this.Hh.S(d2);
        this.Hh.o(d11);
        this.Hh.Y(d3);
        this.Hh.D(d11);
        this.Hh.R(bl ? Hr : v);
    }

    public void J(@Nullable GuiClickListener guiClickListener) {
        this.H7 = guiClickListener;
        this.HN.s(this::lambda$setRemoveListener$2);
    }

    public PlayerAvatarComponent r$src$Lgg_vape_friend_ui_PlayerAvatarComponent_$1pdxk1w() {
        return this.HA;
    }

    private void lambda$addAddListener$1() {
        if (this.I != null) {
            this.I.P();
        }
        this.q(true);
    }

    public void a(@Nullable GuiClickListener guiClickListener) {
        this.I = guiClickListener;
        this.He.s(this::lambda$setAddListener$0);
    }

    private void lambda$setRemoveListener$2() {
        if (this.H7 != null) {
            this.H7.P();
        }
        this.q(false);
    }

    private void lambda$setAddListener$0() {
        if (this.I != null) {
            this.I.P();
        }
        this.q(true);
    }

    public ClickGuiFriendsFriendActionComponent(@NotNull String string, @Nullable String string2) {
        this(string, string2, null);
    }

    public void q(boolean bl) {
        this.Hb = bl;
        this.He.Z(!bl);
        this.HN.Z(bl);
    }

    private void lambda$addRemoveListener$3() {
        if (this.H7 != null) {
            this.H7.P();
        }
        this.q(false);
    }

    public ClickGuiFriendsFriendActionComponent(@NotNull String string, @Nullable String string2, @Nullable EntityPlayer entityPlayer) {
        this(string, string2, entityPlayer, null);
    }

    public void L(@NotNull GuiClickListener guiClickListener) {
        this.I = guiClickListener;
        this.He.r(this::lambda$addAddListener$1);
    }

    public ClickGuiFriendsFriendActionComponent(@NotNull String string) {
        this(string, null, null);
    }

    public boolean S$src$Z$1hdbp3t() {
        return this.Hb;
    }

    public ClickGuiFriendsFriendActionComponent(@NotNull String string, @Nullable String string2, @Nullable EntityPlayer entityPlayer, @Nullable PlayerInfo playerInfo) {
        ResourceLocation resourceLocation;
        this.getClass();
        this.HS = new ColorAnimation(0.15, HJ, HT);
        this.Hb = false;
        this.HL = string;
        this.Y(22.0);
        this.d(false);
        this.HA = entityPlayer != null && entityPlayer.isNotNull() ? PlayerAvatarComponent.D(entityPlayer, 14.0, 14.0) : (playerInfo != null ? ((resourceLocation = playerInfo.i()) != null && resourceLocation.isNotNull() ? PlayerAvatarComponent.q(resourceLocation, string, 14.0, 14.0) : new PlayerAvatarComponent(string, 14.0, 14.0)) : new PlayerAvatarComponent(string, 14.0, 14.0));
        this.Hh = new TruncatedTextComponent(string, "...", 0.0, 0.75, v, false);
        this.Hh.K(false);
        this.Hh.G(string);
        this.He = new TextButton("ADD", 0.625, ClickGuiFriendsFriendActionComponent.J.B, ClickGuiFriendsFriendActionComponent.J.B.brighter(), null, 2.0f, 1.0f, 35.0, 14.0);
        this.He.h(Color.WHITE);
        this.He.F(false);
        this.He.a(true);
        this.He.c(true);
        this.He.w("Add friend");
        this.HN = new TextButton("REMOVE", 0.625, ClickGuiFriendsFriendActionComponent.J.d, ClickGuiFriendsFriendActionComponent.J.d.brighter(), null, 2.0f, 1.0f, 50.0, 14.0);
        this.HN.h(Color.WHITE);
        this.HN.F(false);
        this.HN.a(true);
        this.HN.c(true);
        this.HN.w("Remove friend");
        this.HN.Z(false);
        if (string2 != null) {
            this.HO = new ShapeIconComponent(IconShape.ROUNDED_RECT, string2, 10.0, 12.0, 4.0, 2.5f, Ha, Hv, 0.5);
            this.H(this.HA, this.Hh, this.He, this.HN, this.HO);
        } else {
            this.HO = null;
            this.H(this.HA, this.Hh, this.He, this.HN);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public String o$src$Ljava_lang_String_$1aep259() {
        return this.HL;
    }

    public void F(@NotNull GuiClickListener guiClickListener) {
        this.H7 = guiClickListener;
        this.HN.r(this::lambda$addRemoveListener$3);
    }

    static {
        HJ = ClickGuiFriendsFriendActionComponent.J.m;
        HT = new Color(34, 33, 34);
        v = ClickGuiFriendsFriendActionComponent.J.A;
        Hr = ClickGuiFriendsFriendActionComponent.J.f;
        Ha = new Color(98, 197, 84, 20);
        Hv = new Color(98, 197, 84);
    }
}

