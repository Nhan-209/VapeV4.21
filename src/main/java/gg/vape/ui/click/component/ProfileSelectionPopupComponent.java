package gg.vape.ui.click.component;

import gg.vape.config.Profile;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.CenteredGlyphComponent;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.IconTextActionRowComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.PopupSelectorComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import java.awt.Color;
import java.util.function.Consumer;
import org.jetbrains.annotations.Nullable;

public class ProfileSelectionPopupComponent
extends PopupSelectorComponent {
    private final SimpleTextLabelComponent ne;
    private final TruncatedTextComponent b;
    private final CenteredGlyphComponent nu;
    private static final double n_ = 10.0;
    private final CenteredGlyphComponent I = new CenteredGlyphComponent("warning@2x", 5.0f, 5.0f, Color.WHITE);
    @Nullable
    private Profile n7;
    @Nullable
    private Consumer<Profile> nW;

    @Nullable
    public Profile t$src$Lgg_vape_config_Profile_$1unlee1() {
        return this.n7;
    }

    @Nullable
    public Consumer<Profile> o$src$Ljava_util_function_Consumer_$1tuef2j() {
        return this.nW;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        super.g(guiMouseEvent);
    }

    public ProfileSelectionPopupComponent(String string, @Nullable Profile profile, Profile ... profileArray) {
        super(new PanelComponent(108.0, 135.0));
        this.nu = new CenteredGlyphComponent("arrow down active@2x", 2.0f, 2.0f, Color.GRAY);
        this.n7 = profile;
        this.Q.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.Q.d(true);
        this.Q.I(true);
        this.Q.T(ProfileSelectionPopupComponent.J.B);
        this.Q.h(new SpacerComponent(0.0, 10.0), new Object[0]);
        WrappingTextLabelComponent wrappingTextLabelComponent = new WrappingTextLabelComponent(string, 1.0, Color.WHITE);
        wrappingTextLabelComponent.l(true);
        wrappingTextLabelComponent.Y(12.0);
        wrappingTextLabelComponent.o(this.Q.A());
        this.Q.h(wrappingTextLabelComponent, new Object[0]);
        this.Q.h(new SpacerComponent(0.0, 4.0), new Object[0]);
        PanelComponent panelComponent = new PanelComponent(this.Q.A(), 104.0);
        panelComponent.d(false);
        panelComponent.T(this.Q.d());
        panelComponent.I(true);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent.t(panelComponent.L());
        this.Q.h(panelComponent, "widthwrap");
        for (Profile profile2 : profileArray) {
            IconTextActionRowComponent iconTextActionRowComponent = new IconTextActionRowComponent(profile2.n$src$Ljava_lang_String_$xqhelw());
            iconTextActionRowComponent.o(panelComponent.A());
            iconTextActionRowComponent.Y(12.0);
            iconTextActionRowComponent.s(() -> this.lambda$new$1(profile2));
            panelComponent.h(iconTextActionRowComponent, "wrap");
            panelComponent.h(new SpacerComponent(0.0, 2.0), "wrap");
        }
        this.ne = new SimpleTextLabelComponent("DERIVED FROM", 0.65f, ProfileSelectionPopupComponent.J.h);
        this.b = new TruncatedTextComponent("", "...", 58.0, 0.65f, ProfileSelectionPopupComponent.J.A, false);
        this.b.o(this.b.v());
        this.I.w("The original profile that this profile was derived from no longer exists.");
        this.H(this.ne, this.b, this.I, this.nu);
        this.o(true);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void Z(@Nullable Consumer<Profile> consumer) {
        this.nW = consumer;
    }

    private void lambda$null$0(Profile profile) {
        if (this.K != null) {
            ClientSettings.K(this.K);
            this.K = null;
        }
        this.n7 = profile;
        Consumer<Profile> consumer = this.nW;
        if (consumer != null) {
            consumer.accept(profile);
        }
    }

    private void lambda$new$1(Profile profile) {
        ConfirmationDialogComponent.x(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), "Are you sure you want to change the derived profile?\n \nProfile settings will be swapped to other profile", "Confirm", "newtrash", () -> this.lambda$null$0(profile), 100.0, null, null);
    }

    @Override
    public void H() {
        this.ne.K(this.G$src$D$1b2f02a());
        this.ne.S(this.n());
        this.I.Z(this.n7 == null);
        this.I.K(this.ne.G$src$D$1b2f02a() + this.ne.h() + 5.0);
        this.I.S(this.n() - 1.0);
        this.b.K(this.ne.G$src$D$1b2f02a() + this.ne.h() + 8.0);
        this.b.S(this.n() + 2.0);
        this.b.O(this.n7 != null ? this.n7.n$src$Ljava_lang_String_$xqhelw() : "");
        this.nu.K(this.I.V$src$Z$1xhop3l() ? this.I.G$src$D$1b2f02a() + this.I.A() + 4.0 : this.b.G$src$D$1b2f02a() + this.b.u$src$D$ivbecn() + 2.0);
        this.nu.S(this.n() + 2.0);
    }
}

