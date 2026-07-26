package gg.vape.ui.click.frame.impl.main;

import gg.vape.Vape;
import gg.vape.module.Macro;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.component.MacroCardComponent;
import gg.vape.ui.click.component.MultilineTextBlockComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.FrameScrollbarPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiContentPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosLabelInput;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosSettingsPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesPage;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlayPlacement;
import gg.vape.ui.click.frame.impl.main.ClickGuiOverlaySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import gg.vape.ui.theme.ThemeColors;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class ClickGuiMacrosController {
    private String M = "";
    private LabeledTextInputComponent U;
    private final ClickGuiModulesPage s;
    private ClickGuiContentPanel m;

    private void lambda$null$3() {
        this.s.wN.K$src$V$sfnnd();
        this.S();
    }

    private static void lambda$filterMacroButtons$2() {
        try {
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://docs.vape.gg/features/misc/Macros"});
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    public static String b(ClickGuiMacrosController clickGuiMacrosController, String string) {
        clickGuiMacrosController.M = string;
        return clickGuiMacrosController.M;
    }

    private void lambda$null$10() {
        this.s.wN.K$src$V$sfnnd();
        this.S();
    }

    private void lambda$filterMacroButtons$1(MacroCardComponent macroCardComponent) {
        this.L(macroCardComponent);
    }

    private void lambda$openCreateMacroSidecar$13(ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        clickGuiSidecarPanelBase.c(false);
        clickGuiSidecarPanelBase.k().Z(false);
        clickGuiSidecarPanelBase.N(this::lambda$null$12);
    }

    void O(Macro macro) {
        this.s.wN.Z(ClickGuiOverlaySpec.q().e(macro.getName()).n(ClickGuiOverlayPlacement.DOCKED_SHIFT).x(false).N(arg_0 -> this.lambda$openMacroSettings$5(macro, arg_0)).D(arg_0 -> this.lambda$openMacroSettings$8(macro, arg_0)).w());
    }

    private void lambda$null$12() {
        this.s.wN.K$src$V$sfnnd();
        this.S();
    }

    private void lambda$null$7(Macro macro) {
        Vape.INSTANCE.getMacrosManager().removeMacro(macro);
        this.s.wN.K$src$V$sfnnd();
        this.S();
    }

    public ClickGuiMacrosController(ClickGuiModulesPage clickGuiModulesPage) {
        this.s = clickGuiModulesPage;
    }

    private void lambda$openMacroSettings$5(Macro macro, PanelComponent panelComponent) {
        ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel = new ClickGuiMacrosSettingsPanel(panelComponent.A(), panelComponent.L(), macro, false, this::lambda$null$3, this::lambda$null$4);
        panelComponent.h(clickGuiMacrosSettingsPanel, new Object[0]);
    }

    private void lambda$openMacroSettings$8(Macro macro, ClickGuiSidecarPanelBase clickGuiSidecarPanelBase) {
        clickGuiSidecarPanelBase.c(false);
        clickGuiSidecarPanelBase.k().Z(false);
        clickGuiSidecarPanelBase.N(this::lambda$null$6);
        GlyphIconComponent glyphIconComponent = new GlyphIconComponent("newtrash", 5.0, 5.0, 8.0, 8.0, ClickGuiModulesPage.J.h, ClickGuiModulesPage.J.d, null);
        glyphIconComponent.r(() -> this.lambda$null$7(macro));
        clickGuiSidecarPanelBase.e(glyphIconComponent);
    }

    private void lambda$openCreateMacroSidecar$11(Macro macro, PanelComponent panelComponent) {
        ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel = new ClickGuiMacrosSettingsPanel(panelComponent.A(), panelComponent.L(), macro, true, this::lambda$null$9, this::lambda$null$10);
        panelComponent.h(clickGuiMacrosSettingsPanel, new Object[0]);
    }

    private void lambda$null$9() {
        this.s.wN.K$src$V$sfnnd();
        this.S();
    }

    private void lambda$null$4() {
        this.s.wN.K$src$V$sfnnd();
        this.S();
    }

    void K() {
        Macro macro = Macro.create("New Macro");
        this.s.wN.Z(ClickGuiOverlaySpec.q().e("Add new Macro").n(ClickGuiOverlayPlacement.DOCKED_SHIFT).N(arg_0 -> this.lambda$openCreateMacroSidecar$11(macro, arg_0)).D(this::lambda$openCreateMacroSidecar$13).w());
    }

    public void D(MacroCardComponent macroCardComponent) {
        this.L(macroCardComponent);
    }

    private void lambda$filterMacroButtons$0(MacroCardComponent macroCardComponent) {
        this.L(macroCardComponent);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void L(@Nullable MacroCardComponent macroCardComponent) {
        if (this.m == null) {
            return;
        }
        if (macroCardComponent != null) {
            if (macroCardComponent.Q$src$Z$jxpu9m()) {
                this.s.wN.K$src$V$sfnnd();
                macroCardComponent.I(false);
                macroCardComponent = null;
            } else {
                this.O(macroCardComponent.j$src$Lgg_vape_module_Macro_$1ed9en7());
            }
        }
        for (GuiComponent guiComponent : this.m.f()) {
            MacroCardComponent macroCardComponent2;
            if (!(guiComponent instanceof PaddedComponent) || (macroCardComponent2 = ((PaddedComponent)guiComponent).t(MacroCardComponent.class)) == null) continue;
            boolean bl = macroCardComponent2 == macroCardComponent;
            macroCardComponent2.I(bl);
            macroCardComponent2.l(!bl && macroCardComponent != null);
        }
    }

    public void i() {
        double d = this.s.Q$src$D$yixn83();
        this.U = new ClickGuiMacrosLabelInput(this, "Search macros...");
        this.U.a(false);
        TextButton textButton = new TextButton("NEW MACRO", 0.625, ClickGuiModulesPage.J.z(), ClickGuiModulesPage.J.z().brighter(), null, 2.0f, 1.0f, 51.0, 16.0);
        textButton.T("newadd");
        textButton.i(6.0f);
        textButton.c(true);
        textButton.a(true);
        textButton.F(false);
        textButton.h(Color.WHITE);
        textButton.m(true);
        textButton.r(this::K);
        this.U.o(Math.max(0.0, d - textButton.A() - 4.0));
        this.U.H(0.0f);
        this.U.C(0.0);
        this.U.V(0.0f);
        this.U.O(0.0f);
        this.U.W(true);
        this.U.Y(16.0);
        this.U.D(0.75f);
        this.U.I(4.0f);
        this.U.s(ColorAnimation.Y(ThemeColors.J.s));
        this.U.W(null);
        this.s.f$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$o6l04().u(true);
        this.s.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(this.U, "widthwrap");
        this.s.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new PaddedComponent(0.0, 0.0, 4.0, 0.0, textButton), new Object[0]);
        this.s.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(new SpacerComponent(0.0, 5.0), new Object[0]);
        double d2 = this.s.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().L() - this.s.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y() - 1.0;
        if (d2 < 0.0) {
            d2 = 0.0;
        }
        this.m = new ClickGuiContentPanel(d, d2);
        this.m.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.m.d(false);
        this.m.t(this.m.L());
        this.m.F(FrameScrollbarPlacement.OUTSIDE);
        this.m.E(true);
        this.s.I$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiConten$89xfjr().h(this.m, new Object[0]);
        if (this.M != null && !this.M.isEmpty()) {
            this.U.k(this.M);
        } else {
            this.S();
        }
    }

    public void S() {
        boolean bl;
        if (this.m == null) {
            return;
        }
        this.m.S();
        String string = this.U != null ? this.U.i$src$Ljava_lang_String_$1n2xf3k() : "";
        String string2 = string == null ? "" : string.trim().toLowerCase();
        boolean bl2 = bl = !string2.isEmpty();
        if (bl) {
            for (Macro macro : Vape.INSTANCE.getMacrosManager().getMacros()) {
                if (!macro.getName().toLowerCase().contains(string2)) continue;
                MacroCardComponent macroCardComponent = new MacroCardComponent(macro);
                macroCardComponent.o(this.m.A());
                macroCardComponent.k(() -> this.lambda$filterMacroButtons$0(macroCardComponent));
                macroCardComponent.Z(() -> this.lambda$filterMacroButtons$1(macroCardComponent));
                this.m.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, macroCardComponent), new Object[0]);
            }
            if (this.m.f().isEmpty()) {
                // empty if block
            }
            this.m.H(true);
            return;
        }
        for (Macro macro : Vape.INSTANCE.getMacrosManager().getMacros()) {
            MacroCardComponent macroCardComponent = new MacroCardComponent(macro);
            macroCardComponent.o(this.m.A());
            macroCardComponent.k(() -> this.lambda$filterMacroButtons$0(macroCardComponent));
            macroCardComponent.Z(() -> this.lambda$filterMacroButtons$1(macroCardComponent));
            this.m.h(new PaddedComponent(0.0, 3.0, 0.0, 0.0, macroCardComponent), new Object[0]);
        }
        if (this.m.f().isEmpty()) {
            MultilineTextBlockComponent multilineTextBlockComponent = new MultilineTextBlockComponent("INFO", "Click NEW MACRO to add a macro.\n\nFor more info on macros, read the docs");
            multilineTextBlockComponent.k(this.m.A());
            multilineTextBlockComponent.N("read the docs", ClickGuiMacrosController::lambda$filterMacroButtons$2);
            this.m.h(new PaddedComponent(3.0, 3.0, 0.0, 0.0, multilineTextBlockComponent), new Object[0]);
        }
        this.m.H(true);
    }

    private void lambda$null$6() {
        this.s.wN.K$src$V$sfnnd();
        this.S();
    }
}

