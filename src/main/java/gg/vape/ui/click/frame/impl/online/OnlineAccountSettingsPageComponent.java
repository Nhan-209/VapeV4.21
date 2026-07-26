package gg.vape.ui.click.frame.impl.online;

import gg.vape.Vape;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.manager.client.OnlineSettings;
import gg.vape.notification.NotificationType;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.FilledSpacerComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.ui.click.component.gui.AnimatedCenteredTextLabelComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.input.DebouncedTextInputComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.impl.online.LinkedBooleanSettingsToggleComponent;
import gg.vape.ui.click.frame.impl.online.OnlineAccountSettingsTextInputComponent;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionSettingsFrame;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionSettingsPageComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileUserAvatarComponent;
import gg.vape.utils.ClipboardUtil;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.value.BooleanValue;
import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;

public class OnlineAccountSettingsPageComponent
extends OnlineConnectionSettingsPageComponent {
    public final AtomicBoolean fe;
    private static boolean fs;
    private final PanelComponent fH;
    private final PanelComponent fS;
    private final DebouncedTextInputComponent fi;
    private final PanelComponent fJ;
    private final PanelComponent fE;
    private final TextButton f2;
    private SimpleTextLabelComponent f6 = new SimpleTextLabelComponent("User");
    private final PublicProfileUserAvatarComponent fL;
    private final PanelComponent f3;

    private BooleanToggleComponent A(BooleanValue booleanValue) {
        BooleanToggleComponent booleanToggleComponent = new BooleanToggleComponent(booleanValue.o(), 0.8, booleanValue);
        booleanToggleComponent.q(this.fE.A());
        booleanToggleComponent.P(true);
        booleanToggleComponent.u(10.0);
        booleanToggleComponent.W(true);
        booleanToggleComponent.T(OnlineAccountSettingsPageComponent.J.t);
        return booleanToggleComponent;
    }

    @Override
    public void s() {
        this.f6.G(Vape.INSTANCE.getAccountInfo().h());
        this.fL.W(Vape.INSTANCE.getAccountInfo().i());
    }

    @Override
    public void c() {
        super.c();
        this.f6.G(Vape.INSTANCE.getAccountInfo().h());
        this.f2.d("  Logout");
        ImageRenderer.E(OnlineAccountSettingsPageComponent.J.W, (float)this.f2.G$src$D$1b2f02a() + 6.0f, (float)this.f2.n() + 2.0f, "signout", 5.0f, 5.0f, false);
    }

    static {
        OnlineAccountSettingsPageComponent.C(false);
    }

    public static void H(OnlineAccountSettingsPageComponent onlineAccountSettingsPageComponent) {
        onlineAccountSettingsPageComponent.N$src$V$102858o();
    }

    public static boolean p() {
        return fs;
    }

    private static void lambda$new$1() {
        OnlineConnectionSettingsFrame.x4.p();
    }

    public static boolean R$src$Z$104fbpk() {
        boolean bl = OnlineAccountSettingsPageComponent.p();
        return true;
    }

    private static void lambda$new$0() {
        ClipboardUtil.setText(Vape.INSTANCE.getAccountInfo().h());
        Vape.INSTANCE.getNotificationManager().t("Copied", "Copied " + Vape.INSTANCE.getAccountInfo().h(), NotificationType.INFO, 5000L);
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public OnlineAccountSettingsPageComponent() {
        this.f2 = new TextButton("Logout", Color.RED);
        this.fS = new PanelComponent(104.0, 65.0);
        this.f3 = new PanelComponent(96.0, 18.0);
        this.fE = new PanelComponent(96.0, 50.0);
        this.fe = new AtomicBoolean(false);
        this.fi = new OnlineAccountSettingsTextInputComponent(this, "Enter username", 10000L);
        this.fS.d(false);
        this.f3.d(true);
        this.fE.d(true);
        this.fS.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.fL = new PublicProfileUserAvatarComponent(null, 16.0, 16.0);
        this.fL.q(true);
        PanelComponent panelComponent = new PanelComponent(104.0, 24.0);
        this.fH = new PanelComponent(panelComponent.A(), 24.0);
        this.fH.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent.h(this.fH, new Object[0]);
        this.f6 = new WrappingTextLabelComponent(Vape.INSTANCE.getAccountInfo().h(), 1.0, Color.WHITE);
        this.f6.o(104.0);
        this.f6.Y(12.0);
        this.f6.l(true);
        this.fH.h(new PaddedComponent(0.0, 2.0, 0.0, 0.0, this.f6), "wrap");
        this.fJ = new PanelComponent(panelComponent.A(), 24.0);
        this.fJ.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.fJ.Z(false);
        panelComponent.h(this.fJ, new Object[0]);
        this.fi.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().W("newnext");
        this.fi.e(false);
        this.fi.n(16);
        this.fJ.h(new SpacerComponent((this.fJ.A() - this.fi.A()) / 2.0, 0.0), "widthwrap");
        this.fJ.h(this.fi, new Object[0]);
        this.fJ.h(new SpacerComponent((this.fJ.A() - this.fi.A()) / 2.0 + 4.0, 0.0), "widthwrap");
        this.fJ.h(new FilledSpacerComponent(this.fi.A() - 20.0, 1.0, OnlineAccountSettingsPageComponent.J.y), new Object[0]);
        TextLabel textLabel = new TextLabel("Cancel", 0.8, false, OnlineAccountSettingsPageComponent.J.l);
        textLabel.u(10.0);
        textLabel.q(22.0);
        textLabel.r(this::N$src$V$102858o);
        this.fJ.h(new SpacerComponent((this.fJ.A() - textLabel.A()) / 2.0, 3.0), new Object[0]);
        this.fJ.h(new SpacerComponent((this.fJ.A() - textLabel.A()) / 2.0, 0.0), "widthwrap");
        this.fJ.h(textLabel, new Object[0]);
        AnimatedCenteredTextLabelComponent animatedCenteredTextLabelComponent = new AnimatedCenteredTextLabelComponent("COPY", OnlineAccountSettingsPageComponent.J.l);
        animatedCenteredTextLabelComponent.o(19.0);
        animatedCenteredTextLabelComponent.Y(10.0);
        animatedCenteredTextLabelComponent.y(0.6);
        animatedCenteredTextLabelComponent.y(0.75f);
        animatedCenteredTextLabelComponent.c(true);
        animatedCenteredTextLabelComponent.r(OnlineAccountSettingsPageComponent::lambda$new$0);
        GlyphIconComponent glyphIconComponent = new GlyphIconComponent("newedit", 4.0, 4.0, 10.0, 10.0, OnlineAccountSettingsPageComponent.J.W, Color.WHITE, OnlineAccountSettingsPageComponent.J.l);
        glyphIconComponent.R(true);
        glyphIconComponent.q(true);
        glyphIconComponent.w(-0.5);
        glyphIconComponent.r(this::N$src$V$102858o);
        this.fH.h(new SpacerComponent(35.0, 0.0), new Object[0]);
        this.fH.h(animatedCenteredTextLabelComponent, new Object[0]);
        this.fH.h(new SpacerComponent(3.0, 0.0), new Object[0]);
        this.fH.h(glyphIconComponent, new Object[0]);
        this.fS.h(new PaddedComponent(this.A() / 2.0 - this.fL.A() / 2.0, 6.0, this.fL), new Object[0]);
        PanelComponent panelComponent2 = new PanelComponent(104.0, 26.0);
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("warp");
        this.fS.h(panelComponent, new Object[0]);
        this.f3.T(OnlineAccountSettingsPageComponent.J.m);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("Status", 0.8, OnlineAccountSettingsPageComponent.J.A);
        simpleTextLabelComponent.Y(this.f3.L());
        this.f3.h(simpleTextLabelComponent, new Object[0]);
        this.f2.r(OnlineConnectionManager.T::Q);
        this.f2.u(10.0);
        this.f2.q(45.0);
        this.f2.h(OnlineAccountSettingsPageComponent.J.Z);
        this.f2.F(false);
        this.f2.m(5.0f);
        this.f2.c(true);
        this.f2.y(0.8f);
        this.f2.G(OnlineAccountSettingsPageComponent.J.l, OnlineAccountSettingsPageComponent.J.y);
        this.f3.h(new PaddedComponent(4.0, 4.0, this.f2), "alignRight");
        this.fE.T(OnlineAccountSettingsPageComponent.J.m);
        this.fE.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.fE.h(new SpacerComponent(0.0, 4.0), new Object[0]);
        this.fE.h(this.A(OnlineConnectionManager.T.S().X$src$Lgg_vape_value_BooleanValue_$7rygmo()), new Object[0]);
        Object object = OnlineConnectionManager.T.S();
        BooleanValue[] booleanValueArray = new BooleanValue[]{((OnlineSettings)object).O(), ((OnlineSettings)object).z(), ((OnlineSettings)object).l()};
        LinkedBooleanSettingsToggleComponent linkedBooleanSettingsToggleComponent = new LinkedBooleanSettingsToggleComponent(this, "Privacy settings", 0.8, null, booleanValueArray);
        linkedBooleanSettingsToggleComponent.q(this.fE.A());
        linkedBooleanSettingsToggleComponent.P(true);
        linkedBooleanSettingsToggleComponent.T(OnlineAccountSettingsPageComponent.J.t);
        this.fE.h(linkedBooleanSettingsToggleComponent, new Object[0]);
        TextButton textButton = new TextButton("View all settings", 0.8, OnlineAccountSettingsPageComponent.J.t, OnlineAccountSettingsPageComponent.J.z, OnlineAccountSettingsPageComponent.J.l, 7.0f, 1.0f, this.fE.A() - 8.0, 14.0);
        textButton.F(false);
        textButton.h(OnlineAccountSettingsPageComponent.J.A);
        this.fE.h(new PaddedComponent(4.0, 2.0, textButton), new Object[0]);
        textButton.r(OnlineAccountSettingsPageComponent::lambda$new$1);
        object = new PanelComponent(96.0, 75.0);
        ((FrameComponent)object).l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        ((FrameComponent)object).h(this.f3, new Object[0]);
        ((FrameComponent)object).h(new SpacerComponent(0.0, 4.0), new Object[0]);
        ((FrameComponent)object).h(this.fE, new Object[0]);
        this.H(this.fS, new PaddedComponent(4.0, 0.0, (GuiComponent)object));
    }

    public static void C(boolean bl) {
        fs = bl;
    }

    private void N$src$V$102858o() {
        this.fH.Z(!this.fH.V$src$Z$1xhop3l());
        this.fJ.Z(!this.fJ.V$src$Z$1xhop3l());
    }
}

