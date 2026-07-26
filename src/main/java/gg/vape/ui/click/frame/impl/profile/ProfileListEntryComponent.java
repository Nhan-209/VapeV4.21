package gg.vape.ui.click.frame.impl.profile;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.input.MouseInput;
import gg.vape.manager.client.ProfilesManager;
import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.MousePosition;
import gg.vape.ui.click.component.FadingTruncatedTextComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.input.BindableInputComponent;
import gg.vape.ui.click.frame.CenteredPopupFrame;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileGlyphIconPanel;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryBackgroundComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryContainer;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryMouseForwardingListener;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntryOpenSettingsClickHandler;
import gg.vape.ui.click.frame.impl.profile.ProfileListEntrySettingsPopupPanel;
import gg.vape.ui.click.frame.impl.profile.ProfileModuleSnapshotListComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileRenameInputComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilesSettingsFrame;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ProfileListEntryComponent
extends InteractiveComponent {
    private final Profile Qz;
    private final long Q1 = 2000L;
    private boolean Q2 = false;
    private final GlyphIconComponent Qo = new GlyphIconComponent("settingdots", 13.0, 13.0, 13.0, 13.0, null, null, null);
    private double Qt;
    private final BindableInputComponent Qb;
    private long QO = 0L;
    private double I;
    private final FadingTruncatedTextComponent Qf;
    private final SquareIconButtonComponent b = new SquareIconButtonComponent("newrearrange", 1.5);
    private final ProfilesSettingsFrame K;
    private boolean v;
    private int Q = -1;
    private RectData Q_;
    private String Qp = null;
    private static boolean Qe;

    private void h$src$V$kl44bl() {
        if (!MouseInput.I(MouseButton.LEFT_CLICK.ordinal())) {
            this.v = false;
            this.A(false);
            ClientSettings.fT = null;
            if (this.Q != -1) {
                Profile profile;
                ProfilesManager profilesManager = Vape.INSTANCE.getProfilesManager();
                List<Profile> list = profilesManager.b();
                Profile profile2 = profile = this.Q < list.size() ? list.get(this.Q) : null;
                if (profile != null) {
                    profile.c(true);
                }
                list.remove(this.Qz);
                list.add(this.Q, this.Qz);
                this.Qz.c(true);
            }
            Vape.INSTANCE.getProfilesManager().H();
            ProfilesSettingsFrame.Z$src$V$6cxyg1();
            this.Q = -1;
            return;
        }
        int n = -1;
        int n2 = -1;
        double d = this.L() * this.A();
        ArrayList<ProfileListEntryComponent> arrayList = new ArrayList<ProfileListEntryComponent>();
        for (GuiComponent guiComponent : this.K.m$src$Lgg_vape_ui_click_component_PanelComponent_$1rlcr9s().f()) {
            if (!(guiComponent instanceof ProfileListEntryComponent)) continue;
            arrayList.add((ProfileListEntryComponent)guiComponent);
        }
        arrayList.sort(Comparator.comparingInt(ProfileListEntryComponent::lambda$processMoving$4));
        for (ProfileListEntryComponent profileListEntryComponent : arrayList) {
            double d2;
            ++n2;
            if (profileListEntryComponent.equals(this) || !((d2 = profileListEntryComponent.Q().c(this.Q())) >= d / 2.0)) continue;
            n = n2;
            break;
        }
        if (n == -1) {
            return;
        }
        if (this.Q != n) {
            this.v(n);
        }
        this.Q = n;
    }

    public Profile N$src$Lgg_vape_config_Profile_$p2odie() {
        return this.Qz;
    }

    @Override
    public boolean V$src$Z$1xhop3l() {
        return super.V$src$Z$1xhop3l() && (this.K.u$src$Z$6rsek8() || this.Qz.U());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static {
        ProfileListEntryComponent.L(false);
    }

    public boolean m$src$Z$knv3du() {
        return Vape.INSTANCE.getProfilesManager().M().equals(this.Qz);
    }

    public static void L(boolean bl) {
        Qe = bl;
    }

    @Override
    public void u() {
        boolean bl = ProfileListEntryComponent.z();
        if (this.Qb.l$src$Z$1orbz7() && (this.Qp == null || !this.Qp.toLowerCase(Locale.ROOT).startsWith("press"))) {
            this.b("press a key to bind");
        }
        if (System.currentTimeMillis() > 1794906154878L) {
            Vape.INSTANCE.getModManager().getMods().forEach(ProfileListEntryComponent::lambda$onTick$3);
        }
        if (this.Q2 && System.currentTimeMillis() > this.QO + this.Q1) {
            this.Qp = null;
            this.Q2 = false;
        } else if (this.Qp != null && this.Qp.toLowerCase(Locale.ROOT).startsWith("press") && !this.Qb.l$src$Z$1orbz7()) {
            this.j$src$V$km7pib();
            if (!this.Qz.h().isEmpty()) {
                this.b("bound to");
            } else {
                this.b("bind removed");
            }
        }
    }

    private void lambda$new$0() {
        MousePosition mousePosition = RenderUtils.h();
        this.Qt = mousePosition.H;
        this.I = this.n();
        this.A(true);
        this.v = true;
        ClientSettings.fT = this;
    }

    public ProfileListEntryComponent(ProfilesSettingsFrame profilesSettingsFrame, Profile profile) {
        this.K = profilesSettingsFrame;
        this.Qz = profile;
        this.Qb = new BindableInputComponent(profile);
        this.Qo.r(new ProfileListEntryOpenSettingsClickHandler(this, profile));
        this.Qf = new FadingTruncatedTextComponent(profile.n$src$Ljava_lang_String_$xqhelw(), 64.0, 0.9, ProfileListEntryComponent.J.Z, ProfileListEntryComponent.J.m, false, false);
        this.Qf.j(new ProfileListEntryMouseForwardingListener(this));
        this.b.r(this::lambda$new$0);
        this.r(() -> this.lambda$new$1(profilesSettingsFrame, profile));
        this.Qo.Y(12.0);
        this.Qo.o(8.5);
        this.Qo.d(8.0);
        this.Qo.U(8.0);
        this.Qo.R(true);
        this.Qo.A(3.5);
        this.H(this.Qf, this.Qo, this.Qb, this.b);
        this.b.Z(false);
    }

    @Override
    public void F() {
    }

    private void N$src$V$k6tgw7() {
        MousePosition mousePosition = RenderUtils.h();
        double d = (double)mousePosition.H - this.Qt;
        this.S(this.n() + d);
        FrameComponent frameComponent = this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb();
        if (this.n() < frameComponent.n()) {
            if (frameComponent.k$src$Z$if6xeb()) {
                frameComponent.b(frameComponent.J$src$D$hx1pag() + 1.0);
            }
            this.S(frameComponent.n());
        } else if (frameComponent.k$src$Z$if6xeb() && this.n() > frameComponent.n() + frameComponent.d$src$D$ibccpu() - this.L()) {
            frameComponent.b(frameComponent.J$src$D$hx1pag() - 1.0);
            this.S(frameComponent.n() + frameComponent.d$src$D$ibccpu() - this.L());
        } else if (this.n() > frameComponent.n() + frameComponent.L() - this.L()) {
            this.S(frameComponent.n() + frameComponent.L() - this.L());
        } else {
            this.Qt = mousePosition.H;
        }
    }

    private static void lambda$onTick$3(Mod mod) {
        mod.a().c(Collections.singletonList(161));
    }

    private static int lambda$processMoving$4(ProfileListEntryComponent profileListEntryComponent) {
        return (int)profileListEntryComponent.n();
    }

    @Override
    public void H() {
        Color color;
        GlyphIconComponent glyphIconComponent;
        Color color2;
        GlyphIconComponent glyphIconComponent2;
        Color color3;
        Color color4;
        boolean bl;
        SmoothFontRenderer smoothFontRenderer;
        if (this.v) {
            this.N$src$V$k6tgw7();
            this.h$src$V$kl44bl();
        }
        smoothFontRenderer = this.O(0.9);
        double d9 = this.Qf.f$src$D$ldt7xy();
        double d8 = this.n() + 9.0 - d9 / 2.0;
        double d10 = this.G$src$D$1b2f02a();
        this.getClass();
        double d7 = d10 + (double)(5.0f * 2.0f);
        this.Qf.K(d7);
        this.Qf.S(d8);
        if (this.K.u$src$Z$6rsek8()) {
            this.Qf.D(66.0);
        } else {
            this.Qf.D(68.0 - (this.Qb.V$src$Z$1xhop3l() ? Math.min(this.Qb.A(), 25.0) : 0.0));
        }
        bl = Vape.INSTANCE.getProfilesManager().M().equals(this.Qz);
        color4 = bl ? J.z() : ProfileListEntryComponent.J.m;
        boolean bl2 = this.w$src$Z$e457mb() && this.K.e() == null || this.K.e() != null && this.K.e().equals(this);
        color3 = bl ? J.B() : ProfileListEntryComponent.J.h;
        this.Qf.R(!this.K.u$src$Z$6rsek8() && bl2 && !bl ? ProfileListEntryComponent.J.Z : color3);
                                this.Qf.C(color4);
                                if (this.K.u$src$Z$6rsek8()) {
                                    Color color7;
                                    double d11;
                                    double d12;
                                    double d13;
                                    double d14;
                                    this.getClass();
                                    this.getClass();
                                    double d15 = (double)(5.0f + 8.0f) * 1.5;
                                    double d16 = this.G$src$D$1b2f02a();
                                    this.getClass();
                                    this.Q_ = new RectData(d16 + 5.0, this.n() + 2.0, d15 - 4.0, this.L() - 2.0);
                                    this.b.Z(true);
                                    double d17 = this.G$src$D$1b2f02a() + this.b.A();
                                    this.getClass();
                                    double d18 = d17 + 5.0;
                                    this.getClass();
                                    this.b.K(d18 + 8.0);
                                    this.b.S(this.n());
                                    this.b.Y(this.L());
                                    double d19 = this.b.G$src$D$1b2f02a() + this.b.A();
                                    this.getClass();
                                    double d20 = d19 + 5.0;
                                    this.getClass();
                                    this.Qf.K(d20 + (double)(8.0f / 2.0f));
                                    this.Qo.Z(false);
                                    this.Qb.Z(false);
                                    double d21 = this.G$src$D$1b2f02a();
                                    this.getClass();
                                    double d22 = d21 + 5.0;
                                    double d23 = this.n() + 1.0;
                                    double d24 = this.A();
                                    this.getClass();
                                    double d25 = this.L() - 2.0;
                                    double d26 = d24 - (double)(5.0f * 2.0f);
                                    double d27 = d23;
                                    double d28 = d22;
                                    if (bl) {
                                        d14 = d28;
                                        d13 = d27;
                                        d12 = d26;
                                        d11 = d25;
                                        color7 = J.z();
                                    } else {
                                        d14 = d28;
                                        d13 = d27;
                                        d12 = d26;
                                        d11 = d25;
                                        color7 = ProfileListEntryComponent.J.m;
                                    }
                                    GuiRenderPrimitives.d(d14, d13, d12, d11, color7);
                                    double d29 = this.G$src$D$1b2f02a();
                                    this.getClass();
                                    double d30 = d29 + 5.0 - 0.2;
                                    double d31 = this.n() + 0.6;
                                    this.getClass();
                                    double d32 = 5.0 + this.b.A() * 2.0;
                                    this.getClass();
                                    GuiRenderPrimitives.C(d30, d31, d32 + 8.0, this.L() - 1.6, ProfileListEntryComponent.J.r);
                                    double d33 = 7.0;
                                    double d34 = 0.5;
                                    double d35 = d34 + 0.5;
                                    Color color8 = J.z();
                                    if (Vape.INSTANCE.getProfilesManager().M().equals(this.Qz)) {
                                        color8 = color8.darker().darker();
                                    }
                                    if (this.Qz.U()) {
                                        GuiRenderPrimitives.C(2.0 + this.G$src$D$1b2f02a() + d33, -1.0 + this.n() + d33, d15 - d33 * 2.0, this.L() - d33 * 1.8, color8);
                                        GuiRenderPrimitives.C(2.0 + this.G$src$D$1b2f02a() + d33 + d34, -1.0 + this.n() + d33 + d34, d15 - (d33 + d34) * 2.0, this.L() - (d33 + d34) * 1.8, ProfileListEntryComponent.J.r);
                                        GuiRenderPrimitives.C(2.0 + this.G$src$D$1b2f02a() + d33 + d35, -1.0 + this.n() + d33 + d35, d15 - (d33 + d35) * 2.0, this.L() - (d33 + d35) * 1.8, color8);
                                    } else {
                                        GuiRenderPrimitives.C(2.0 + this.G$src$D$1b2f02a() + d33, -1.0 + this.n() + d33, d15 - d33 * 2.0, this.L() - d33 * 1.8, ProfileListEntryComponent.J.l);
                                        GuiRenderPrimitives.C(2.0 + this.G$src$D$1b2f02a() + d33 + d34, -1.0 + this.n() + d33 + d34, d15 - (d33 + d34) * 2.0, this.L() - (d33 + d34) * 1.8, ProfileListEntryComponent.J.r);
                                    }
                                    return;
                                }
                                this.b.Z(false);
                                this.Qo.Z(true);
                                if (bl2 && !this.Qo.w$src$Z$e457mb()) {
                                    double d36 = this.G$src$D$1b2f02a();
                                    this.getClass();
                                    double d37 = d36 + 5.0 - 0.5;
                                    double d38 = this.n() + 0.5;
                                    double d39 = this.A();
                                    this.getClass();
                                    GuiRenderPrimitives.d(d37, d38, d39 - (double)(5.0f * 2.0f) + 1.0, this.L() - 1.0, ProfileListEntryComponent.J.l);
                                }
                                double d40 = this.G$src$D$1b2f02a();
                                this.getClass();
                                double d41 = d40 + 5.0;
                                double d42 = this.n() + 1.0;
                                double d43 = this.A();
                                this.getClass();
                                GuiRenderPrimitives.d(d41, d42, d43 - (double)(5.0f * 2.0f), this.L() - 2.0, color4);
        if (this.Qp != null) {
            smoothFontRenderer = this.O(0.75);
            smoothFontRenderer.d(this.Qp, d7, d8, bl2 && bl ? color3 : ProfileListEntryComponent.J.A);
            this.Qf.Z(false);
        } else {
            this.Qf.Z(true);
        }
        double d = this.G$src$D$1b2f02a() + this.A();
        this.getClass();
        double d44 = d - (double)(5.0f * 3.0f);
        GlyphIconComponent glyphIconComponent3 = this.Qo;
        if (bl) {
            glyphIconComponent2 = glyphIconComponent3;
            color2 = color3;
        } else {
            glyphIconComponent2 = glyphIconComponent3;
            color2 = ProfileListEntryComponent.J.W;
        }
        glyphIconComponent2.o(color2);
        GlyphIconComponent glyphIconComponent4 = this.Qo;
        if (bl) {
            glyphIconComponent = glyphIconComponent4;
            color = color3;
        } else {
            glyphIconComponent = glyphIconComponent4;
            color = ProfileListEntryComponent.J.f;
        }
        glyphIconComponent.P(color);
        this.Qo.K(d44);
        this.Qo.S(this.n() + 3.0);
        int n = ColorUtil.B(color4);
        if (n > 100) {
            this.Qo.E(ProfileListEntryComponent.J.t, new Color(0, 0, 0, 70));
        } else {
            this.Qo.E(ProfileListEntryComponent.J.t, new Color(255, 255, 255, 40));
        }
        if (this.N$src$Lgg_vape_config_Profile_$p2odie().y$src$Z$r0tfl8() || this.w$src$Z$e457mb() && !this.Qo.w$src$Z$e457mb() || this.Qb.u$src$Lgg_vape_input_BindCaptureTask_$1o4th8o().V$src$Z$xc25df()) {
            TruncatedTextComponent truncatedTextComponent = this.Qb.Q$src$Lgg_vape_ui_click_component_TruncatedTextCompone$6s53nl();
            double d45 = this.A();
            this.getClass();
            truncatedTextComponent.D(d45 - (double)(5.0f * 6.0f) - this.Qo.A() - (this.Qp != null ? smoothFontRenderer.N(this.Qp) : this.Qf.u$src$D$ivbecn()));
            this.getClass();
            this.Qb.K(d44 -= 5.0 + this.Qb.A());
            this.Qb.S(this.n() + 4.0);
            this.Qb.Z(true);
        } else {
            this.Qb.Z(false);
        }
    }

    @Override
    public double x() {
        return 110.0;
    }

    public void j$src$V$km7pib() {
        this.Q2 = true;
        this.QO = System.currentTimeMillis();
    }

    private void v(int n) {
        double d = this.K.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().n() + this.K.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().L() * 2.0;
        ArrayList<ProfileListEntryComponent> arrayList = new ArrayList<ProfileListEntryComponent>();
        for (GuiComponent guiComponent : this.K.m$src$Lgg_vape_ui_click_component_PanelComponent_$1rlcr9s().f()) {
            if (!(guiComponent instanceof ProfileListEntryComponent)) continue;
            arrayList.add((ProfileListEntryComponent)guiComponent);
        }
        arrayList.remove(this);
        arrayList.add(n, this);
        double d2 = 0.0;
        for (ProfileListEntryComponent profileListEntryComponent : arrayList) {
            if (profileListEntryComponent.n() < d) continue;
            if (profileListEntryComponent.N$src$Lgg_vape_config_Profile_$p2odie().equals(this.N$src$Lgg_vape_config_Profile_$p2odie())) {
                d2 += profileListEntryComponent.L();
                continue;
            }
            profileListEntryComponent.S(d + d2);
            d2 += profileListEntryComponent.L();
        }
    }

    public static boolean E() {
        boolean bl = ProfileListEntryComponent.z();
        return true;
    }

    private void lambda$new$1(ProfilesSettingsFrame profilesSettingsFrame, Profile profile) {
        if (!profilesSettingsFrame.u$src$Z$6rsek8()) {
            return;
        }
        if (this.Q_.Z(RenderUtils.h()) && !Vape.INSTANCE.getProfilesManager().M().equals(profile)) {
            profile.Y(!profile.U());
            profile.c(true);
            ClientSettings.M$src$V$1giazqf();
        }
    }

    @Override
    public double C() {
        return 18.0;
    }

    static Profile f(ProfileListEntryComponent profileListEntryComponent) {
        return profileListEntryComponent.Qz;
    }

    private void lambda$openSettings$2(TextInputComponentBase textInputComponentBase) {
        textInputComponentBase.Z(!textInputComponentBase.V$src$Z$1xhop3l());
        textInputComponentBase.k(this.Qz.n$src$Ljava_lang_String_$xqhelw());
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.K.u$src$Z$6rsek8()) {
            super.g(guiMouseEvent);
            return;
        }
        if (this.K.e() != null && this.K.e().equals(this) && !this.Q().J(guiMouseEvent.getX(), guiMouseEvent.getY())) {
            this.Qo.P$src$V$q7uwbv();
            return;
        }
        if (guiMouseEvent.getAction().equals((Object)MouseButton.LEFT_CLICK)) {
            Vape.INSTANCE.getProfilesManager().U(this.Qz);
        } else if (guiMouseEvent.getAction().equals((Object)MouseButton.RIGHT_CLICK)) {
            this.Qz.r$src$V$1goqkjq();
        }
    }

    public static boolean z() {
        return Qe;
    }

    public void e$src$V$kjgqji() {
        String string = this.Qz.n$src$Ljava_lang_String_$xqhelw();
        double d = this.K.A();
        this.getClass();
        TruncatedTextComponent truncatedTextComponent = new TruncatedTextComponent(string, "...", d - 5.0 - 20.0, 0.9, ProfileListEntryComponent.J.A, true);
        ProfileRenameInputComponent profileRenameInputComponent = new ProfileRenameInputComponent(this, this.Qz.n$src$Ljava_lang_String_$xqhelw(), truncatedTextComponent);
        profileRenameInputComponent.n(48);
        profileRenameInputComponent.Z(false);
        profileRenameInputComponent.e(false);
        profileRenameInputComponent.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().W("newnext");
        GlyphIconComponent glyphIconComponent = new GlyphIconComponent("newedit", 5.0, 5.0, 5.0, 5.0, null, null, null);
        glyphIconComponent.r(() -> this.lambda$openSettings$2(profileRenameInputComponent));
        glyphIconComponent.P(ProfileListEntryComponent.J.W);
        PanelComponent panelComponent = new PanelComponent(this.K.A(), this.K.Q$src$Lgg_vape_ui_click_component_FlowLayoutComponent_$s9lre().L());
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        ProfileGlyphIconPanel profileGlyphIconPanel = null;
        if (this.Qz.j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() != null) {
            profileGlyphIconPanel = new ProfileGlyphIconPanel(this.Qz, 5.0, 5.0);
        }
        ProfileGlyphIconPanel profileGlyphIconPanel2 = profileGlyphIconPanel;
        ProfileListEntrySettingsPopupPanel profileListEntrySettingsPopupPanel = new ProfileListEntrySettingsPopupPanel(this, this.K.A(), 26.0, truncatedTextComponent, glyphIconComponent, profileRenameInputComponent, profileGlyphIconPanel2);
        profileListEntrySettingsPopupPanel.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        ProfileListEntryBackgroundComponent profileListEntryBackgroundComponent = new ProfileListEntryBackgroundComponent();
        profileListEntryBackgroundComponent.o(panelComponent.A());
        profileListEntryBackgroundComponent.Y(18.0);
        profileListEntrySettingsPopupPanel.h(profileListEntryBackgroundComponent, new Object[0]);
        profileListEntrySettingsPopupPanel.h(truncatedTextComponent, new Object[0]);
        if (profileGlyphIconPanel2 != null) {
            profileListEntrySettingsPopupPanel.h(profileGlyphIconPanel2, new Object[0]);
        }
        profileListEntrySettingsPopupPanel.h(profileRenameInputComponent, new Object[0]);
        profileListEntrySettingsPopupPanel.h(glyphIconComponent, new Object[0]);
        ProfileListEntryContainer profileListEntryContainer = new ProfileListEntryContainer(this.K, this.Qz);
        profileListEntryContainer.W(true);
        profileListEntryContainer.Y(20.0);
        panelComponent.h(profileListEntrySettingsPopupPanel, new Object[0]);
        panelComponent.h(profileListEntryContainer, new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        panelComponent.h(new ProfileModuleSnapshotListComponent(this.Qz, 105.0, 86.0), new Object[0]);
        panelComponent.l$src$V$1mibm4x();
        if (this.Qz.z()) {
            // empty if block
        }
        boolean bl = false;
        CenteredPopupFrame centeredPopupFrame = ClientSettings.g(this.K.Q$src$Lgg_vape_ui_click_component_FlowLayoutComponent_$s9lre(), panelComponent, CenteredPopupFrame.class);
        this.K.w(centeredPopupFrame);
        this.K.i$src$Lgg_vape_ui_click_frame_FrameToolbarComponent_$gnpgc6().I("Profiles", false);
    }

    public void b(String string) {
        if (string == null) {
            this.j$src$V$km7pib();
            return;
        }
        this.Qp = string.toUpperCase();
    }
}
