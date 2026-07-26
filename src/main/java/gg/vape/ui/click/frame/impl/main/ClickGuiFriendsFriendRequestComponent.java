package gg.vape.ui.click.frame.impl.main;

import gg.vape.friend.ExternalFriend;
import gg.vape.friend.Friend;
import gg.vape.friend.FriendEntry;
import gg.vape.friend.OnlineFriend;
import gg.vape.friend.OnlineStatus;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsRequestActionComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsRequestRemoveComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFriendsRequestTextComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClickGuiFriendsFriendRequestComponent
extends InteractiveComponent {
    private static final double EA = 5.0;
    private static final double EX = 10.0;
    private static final double EN = 9.0;
    private static final Color Em;
    private static final double Q = 4.0;
    private static final Color v;
    private static final String EC;
    private final FriendEntry b;
    private final TruncatedTextComponent EL;
    private static final double Eg = 8.0;
    private static final double EK = 6.0;
    private static final double Eu = 8.0;
    private static final String E_;
    private static final double El = 4.5;
    private static final String E9;
    private final ClickGuiFriendsRequestTextComponent E1;
    private static final Color En;
    private static final Color EH;
    private static final Color Ez;
    private static final String E4;
    private static final Color Ea;
    private final ColorAnimation EU;
    private static final Color EM;
    private static final double Ej = 22.0;
    private static final double Eo = 20.0;
    private static final double Ev = 0.75;
    private static final Color K;
    private static final String Er;
    private static final float E6 = 3.0f;
    private final ClickGuiFriendsRequestActionComponent Ep;
    private static final String Ew;
    private final ClickGuiFriendsRequestRemoveComponent E3;
    private static final double I = 6.0;

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (guiMouseEvent.getAction() == MouseButton.RIGHT_CLICK && !this.X$src$Lgg_vape_ui_click_component_ClickCooldownState_$1wl74z8().t()) {
            this.E3.P$src$V$q7uwbv();
            this.X$src$Lgg_vape_ui_click_component_ClickCooldownState_$1wl74z8().j(true);
            return;
        }
        if (guiMouseEvent.getAction() == MouseButton.LEFT_CLICK && !this.X$src$Lgg_vape_ui_click_component_ClickCooldownState_$1wl74z8().t()) {
            if (this.Ep.V$src$Z$1xhop3l() && this.Ep.i(guiMouseEvent.getX(), guiMouseEvent.getY())) {
                return;
            }
            if (this.E3.V$src$Z$1xhop3l() && this.E3.i(guiMouseEvent.getX(), guiMouseEvent.getY())) {
                return;
            }
            this.b.k(!this.b.c());
            this.X$src$Lgg_vape_ui_click_component_ClickCooldownState_$1wl74z8().j(true);
            return;
        }
        super.g(guiMouseEvent);
    }

    @Override
    public void H() {
        super.H();
        boolean bl = this.w$src$Z$e457mb();
        this.EU.u(bl);
        GuiRenderPrimitives.B(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.EU.getInterpolatedColor(), 3.0f);
        ClickGuiFriendsRequestTextComponent.q(this.E1);
        double d = this.G$src$D$1b2f02a();
        double d2 = this.n();
        double d3 = this.L();
        double d4 = this.A();
        this.E1.K(d + 8.0);
        this.E1.S(d2 + (d3 - 6.0) / 2.0);
        this.E1.o(6.0);
        this.E1.Y(6.0);
        double d5 = d + d4 - 8.0 - 10.0;
        double d6 = d2 + (d3 - 10.0) / 2.0;
        this.E3.K(d5);
        this.E3.S(d6);
        this.E3.o(10.0);
        this.E3.Y(10.0);
        this.E3.Z(true);
        double d7 = d5 - 4.0 - 9.0;
        double d8 = d2 + (d3 - 9.0) / 2.0;
        this.Ep.K(d7);
        this.Ep.S(d8);
        this.Ep.o(9.0);
        this.Ep.Y(9.0);
        this.Ep.Z(bl);
        double d9 = d + 20.0;
        double d10 = d7 - 6.0;
        double d11 = Math.max(0.0, d10 - d9);
        this.EL.K(d9);
        this.EL.S(d2);
        this.EL.o(d11);
        this.EL.Y(d3);
        this.EL.D(d11);
        this.EL.O(this.g(bl));
        this.EL.R(bl ? Em : Ea);
        String string = this.S$src$Ljava_lang_String_$1oeomw2(bl);
        this.EL.G(string.isEmpty() ? "" : string);
    }

    static Color T$src$Ljava_awt_Color_$vjmbs7() {
        return v;
    }

    private boolean z() {
        if (this.b instanceof Friend) {
            Friend friend = (Friend)this.b;
            return !ClickGuiFriendsFriendRequestComponent.r(friend.E(), "").equalsIgnoreCase(friend.s());
        }
        String string = ClickGuiFriendsFriendRequestComponent.r(this.b.o(), "");
        String string2 = ClickGuiFriendsFriendRequestComponent.r(this.b.s(), "");
        return !string.isEmpty() && !string.equalsIgnoreCase(string2);
    }

    public ClickGuiFriendsFriendRequestComponent(@NotNull FriendEntry friendEntry) {
        this.getClass();
        this.EU = new ColorAnimation(0.15, EM, EH);
        this.o(true);
        this.b = friendEntry;
        this.Y(22.0);
        this.d(false);
        this.E1 = new ClickGuiFriendsRequestTextComponent(this, null);
        this.EL = new TruncatedTextComponent(this.g(false), "...", 0.0, 0.75, Ea, false);
        this.EL.K(false);
        this.EL.G("");
        this.Ep = new ClickGuiFriendsRequestActionComponent(this, null);
        this.Ep.w("Remove friend");
        this.E3 = new ClickGuiFriendsRequestRemoveComponent(this, null);
        this.E3.w("Friend settings");
        this.H(this.E1, this.EL, this.E3, this.Ep);
    }

    private String S$src$Ljava_lang_String_$1oeomw2(boolean bl) {
        FriendEntry friendEntry;
        Object object;
        if (bl) {
            return "";
        }
        if (this.b instanceof ExternalFriend && (object = ((ExternalFriend)(friendEntry = (ExternalFriend)this.b)).d()) != null) {
            OnlineStatus onlineStatus;
            String string;
            StringBuilder stringBuilder = new StringBuilder();
            String string2 = ((OnlineFriend)object).C();
            if (string2 != null && !string2.isEmpty()) {
                stringBuilder.append(string2);
            }
            if ((string = ((OnlineFriend)object).I()) != null && !string.isEmpty() && !string.equals(string2)) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append('\n');
                }
                stringBuilder.append(string);
            }
            if ((onlineStatus = ((OnlineFriend)object).F()) != null) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append('\n');
                }
                stringBuilder.append(onlineStatus.f());
            }
            return stringBuilder.toString();
        }
        if (this.b instanceof Friend) {
            friendEntry = (Friend)this.b;
            object = ((Friend)friendEntry).E();
            String string = ((Friend)friendEntry).s();
            if (object != null && !((String)object).equals(string)) {
                return "*" + (String)object + "\n" + string;
            }
        }
        return "";
    }

    public FriendEntry P$src$Lgg_vape_friend_FriendEntry_$4u8x49() {
        return this.b;
    }

    static Color I$src$Ljava_awt_Color_$yvy0ia() {
        return En;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static Color g$src$Ljava_awt_Color_$1956ipg() {
        return Ez;
    }

    public void Z(@NotNull GuiClickListener guiClickListener) {
        this.E3.r(guiClickListener);
    }

    static Color s$src$Ljava_awt_Color_$z1gb6w() {
        return K;
    }

    static {
        E4 = "synced@2x";
        EC = "status online@2x";
        E_ = "status away@2x";
        Ew = "newsettings";
        E9 = "status offline@2x";
        Er = "newtrash";
        EM = ClickGuiFriendsFriendRequestComponent.J.m;
        EH = new Color(34, 33, 34);
        Ea = ClickGuiFriendsFriendRequestComponent.J.A;
        Em = ClickGuiFriendsFriendRequestComponent.J.f;
        v = ClickGuiFriendsFriendRequestComponent.J.d;
        K = ClickGuiFriendsFriendRequestComponent.J.c;
        Ez = ClickGuiFriendsFriendRequestComponent.J.W;
        En = ClickGuiFriendsFriendRequestComponent.J.f;
    }

    public boolean isBlatantMod() {
        return this.b.c();
    }

    private static String r(@Nullable String string, @Nullable String string2) {
        if (string != null && !string.isEmpty()) {
            return string;
        }
        return string2 != null ? string2 : "";
    }

    private String g(boolean bl) {
        Object object;
        Object object2;
        String string = this.b.s();
        boolean bl2 = this.z();
        if (bl2) {
            Object object3;
            Object object4;
            if (bl) {
                return string;
            }
            if (this.b instanceof ExternalFriend && (object4 = ((ExternalFriend)(object3 = (ExternalFriend)this.b)).d()) != null) {
                String string2 = ClickGuiFriendsFriendRequestComponent.r(((OnlineFriend)object4).C(), ((ExternalFriend)object3).s());
                return "*" + string2;
            }
            if (this.b instanceof Friend) {
                object3 = (Friend)this.b;
                object4 = ClickGuiFriendsFriendRequestComponent.r(((Friend)object3).E(), ((Friend)object3).s());
                return "*" + (String)object4;
            }
            object3 = ClickGuiFriendsFriendRequestComponent.r(this.b.o(), string);
            return "*" + (String)object3;
        }
        if (bl) {
            return string;
        }
        if (this.b instanceof ExternalFriend && (object2 = ((ExternalFriend)(object = (ExternalFriend)this.b)).d()) != null) {
            String string3 = ClickGuiFriendsFriendRequestComponent.r(((OnlineFriend)object2).C(), ((ExternalFriend)object).s());
            return string3;
        }
        if (this.b instanceof Friend) {
            object = (Friend)this.b;
            object2 = ClickGuiFriendsFriendRequestComponent.r(((Friend)object).E(), ((Friend)object).s());
            return (String)object2;
        }
        object = ClickGuiFriendsFriendRequestComponent.r(this.b.o(), string);
        return (String)object;
    }

    public void x(@Nullable GuiClickListener guiClickListener) {
        this.Ep.s(guiClickListener);
    }

    public void e(@Nullable GuiClickListener guiClickListener) {
        this.E3.s(guiClickListener);
    }

    public void H(@NotNull GuiClickListener guiClickListener) {
        this.Ep.r(guiClickListener);
    }

    static FriendEntry w(ClickGuiFriendsFriendRequestComponent clickGuiFriendsFriendRequestComponent) {
        return clickGuiFriendsFriendRequestComponent.b;
    }
}
