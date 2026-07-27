package gg.vape.ui.click.frame.impl.main;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.input.BindCaptureTask;
import gg.vape.module.Macro;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.input.SmallTextInputComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.RandomRangeSliderComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosNameInput;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosPreviewComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosPrimaryBindCaptureTask;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosSecondaryBindCaptureTask;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosSettingsControlPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosSettingsViewMode;
import gg.vape.ui.theme.ThemeColors;
import java.awt.Color;

public class ClickGuiMacrosSettingsPanel
extends PanelComponent {
    private PaddedComponent lW;
    private String lF = "";
    private final Runnable lE;
    private PaddedComponent l0;
    private static final Color lB;
    private final Runnable l7;
    private static final float lP = 3.0f;
    private static final float l2 = 6.0f;
    private static String ll;
    private final Macro l5;
    private final Macro l1;
    private static final Color lG;
    private ClickGuiMacrosSettingsViewMode lY;
    private static final Color lS;
    private PanelComponent le;
    private final boolean lQ;
    private RandomRangeSliderComponent lv;
    private SpacerComponent lp;
    private RandomRangeSliderComponent l8;
    private final ThemeColors lA = ThemeColors.J;
    private static final float lJ = 4.0f;
    private SmallTextInputComponent l9;
    private PanelComponent lt;
    private PaddedComponent ln;
    private BooleanToggleComponent lk;
    private SpacerComponent lg;
    private BindCaptureTask lX;

    private void a$src$V$wypqmt() {
        boolean bl;
        boolean bl2;
        boolean bl3 = this.lY == ClickGuiMacrosSettingsViewMode.NAME_INPUT;
        boolean bl4 = bl2 = this.lY == ClickGuiMacrosSettingsViewMode.KEYBIND_INPUT;
        if (bl2) {
            boolean bl5;
            boolean bl6 = bl5 = this.lY == ClickGuiMacrosSettingsViewMode.FULL_SETTINGS;
            if (bl5) {
                if (this.lQ && !this.lF.isEmpty()) {
                    this.l9.k(this.lF);
                }
                this.b$src$V$wz9j86();
                this.l9.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(bl3);
                this.lp.Z(bl5);
                this.ln.Z(bl5);
                this.l8.Z(bl5);
                this.lk.Z(bl5);
                this.lv.Z(this.l5.getDoubleClick().L() != false);
                this.lg.Z(bl5);
                this.lW.Z(bl5);
                this.p();
                this.H(true);
                return;
            }
            if (this.lQ) {
                // empty if block
            }
            this.b$src$V$wz9j86();
            this.l9.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(bl3);
            this.lp.Z(bl5);
            this.ln.Z(bl5);
            this.l8.Z(bl5);
            this.lk.Z(bl5);
            this.lv.Z(false);
            this.lg.Z(bl5);
            this.lW.Z(bl5);
            this.p();
            this.H(true);
            return;
        }
        boolean bl7 = bl = this.lY == ClickGuiMacrosSettingsViewMode.FULL_SETTINGS;
        if (bl) {
            if (this.lQ && !this.lF.isEmpty()) {
                this.l9.k(this.lF);
            }
            this.b$src$V$wz9j86();
            this.l9.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(bl3);
            this.lp.Z(bl);
            this.ln.Z(bl);
            this.l8.Z(bl);
            this.lk.Z(bl);
            this.lv.Z(this.l5.getDoubleClick().L() != false);
            this.lg.Z(bl);
            this.lW.Z(bl);
            this.H(true);
            return;
        }
        if (this.lQ) {
            // empty if block
        }
        this.b$src$V$wz9j86();
        this.l9.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(bl3);
        this.lp.Z(bl);
        this.ln.Z(bl);
        this.l8.Z(bl);
        this.lk.Z(bl);
        this.lv.Z(false);
        this.lg.Z(bl);
        this.lW.Z(bl);
        this.H(true);
    }

    private void E(String string) {
        this.lF = string;
        this.l9.L$src$V$w6nnjd();
        this.lY = ClickGuiMacrosSettingsViewMode.KEYBIND_INPUT;
        this.a$src$V$wypqmt();
    }

    static Color N$src$Ljava_awt_Color_$1qg5s8k() {
        return lB;
    }

    public static String L$src$Ljava_lang_String_$798gl2() {
        return ll;
    }

    static {
        ClickGuiMacrosSettingsPanel.H("sFUCB");
        long[] lArray = new long[]{2959981560782464037L, 2380650494719630879L};
        lG = new Color((int)lArray[0]);
        lS = new Color((int)lArray[1]);
        lB = new Color(255, 255, 255, 20);
    }

    public boolean A$src$Z$wh4bqp() {
        return this.lQ;
    }

    private void K$src$V$wmm9kv() {
        double d = this.A() - 12.0;
        this.Q(d);
        this.lp = new SpacerComponent(0.0, 8.0);
        this.h(this.lp, new Object[0]);
        ClickGuiMacrosPreviewComponent clickGuiMacrosPreviewComponent = new ClickGuiMacrosPreviewComponent(this);
        clickGuiMacrosPreviewComponent.o(d);
        clickGuiMacrosPreviewComponent.Y(1.0);
        this.ln = new PaddedComponent(0.0, 8.0, 6.0, 0.0, clickGuiMacrosPreviewComponent);
        this.h(this.ln, new Object[0]);
        this.l8 = new RandomRangeSliderComponent(this.l5.getDelay());
        this.l8.d(false);
        this.l8.q(this.A() - 1.0);
        this.l8.P(true);
        this.l8.T(this.lA.m);
        this.h(this.l8, "wrap");
        this.lk = new BooleanToggleComponent(this.l5.getDoubleClick());
        this.lk.d(false);
        this.lk.q(this.A() - 1.0);
        this.lk.P(true);
        this.lk.T(this.lA.m);
        this.h(this.lk, "wrap");
        this.lv = new RandomRangeSliderComponent(this.l5.getDoubleClickDelay());
        this.lv.d(false);
        this.lv.q(this.A() - 1.0);
        this.lv.P(true);
        this.lv.T(this.lA.m);
        this.h(this.lv, "wrap");
        this.lg = new SpacerComponent(0.0, 0.0);
        this.h(this.lg, new Object[0]);
        this.w$src$V$xat7or();
    }


    private void lambda$buildActionButtons$1() {
        String string = this.l9.i$src$Ljava_lang_String_$1n2xf3k().trim();
        if (string.isEmpty()) {
            return;
        }
        JsonObject jsonObject = this.l5.toJson();
        jsonObject.remove("name");
        if (this.lQ) {
            Macro macro = Macro.create(string).loadJson(jsonObject);
            Vape.INSTANCE.getMacrosManager().addMacro(macro);
        } else {
            Macro macro = Macro.create(string).loadJson(jsonObject);
            Vape.INSTANCE.getMacrosManager().removeMacro(this.l5);
            Vape.INSTANCE.getMacrosManager().addMacro(macro);
        }
        if (this.l7 != null) {
            this.l7.run();
        }
    }

    private void j$src$V$x3nvz2() {
        if (this.lg == null || this.le == null) {
            return;
        }
        this.lg.Y(0.0);
        this.H(true);
        double d = this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y();
        double d2 = this.L();
        double d3 = d2 - d;
        if (d3 < 0.0) {
            d3 = 0.0;
        }
        this.lg.Y(d3);
        this.H(true);
    }

    private void lambda$buildActionButtons$0() {
        if (!this.lQ) {
            this.l5.loadJson(this.l1.toJson());
        }
        if (this.lE != null) {
            this.lE.run();
        }
    }

    @Override
    public void u() {
        super.u();
        this.s$src$V$x8m1bb();
        if (this.lY == ClickGuiMacrosSettingsViewMode.FULL_SETTINGS) {
            this.j$src$V$x3nvz2();
        }
    }

    static Color u$src$Ljava_awt_Color_$burlbh() {
        return lG;
    }

    static Color H$src$Ljava_awt_Color_$vzgv0a() {
        return lS;
    }

    public Macro D$src$Lgg_vape_module_Macro_$1dp2tr6() {
        return this.l5;
    }

    public static void H(String string) {
        ll = string;
    }

    private void e$src$V$x0wx09() {
        if (this.lX != null && this.lX.V$src$Z$xc25df()) {
            return;
        }
        this.lX = new ClickGuiMacrosPrimaryBindCaptureTask(this, this.l5);
        this.lX.run();
    }

    static void g(ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel) {
        clickGuiMacrosSettingsPanel.k$src$V$x47okf();
    }

    public ClickGuiMacrosSettingsPanel(double d, double d2, Macro macro, boolean bl, Runnable runnable, Runnable runnable2) {
        super(d, d2);
        this.l5 = macro;
        this.l1 = Macro.create(macro.getName()).loadJson(macro.toJson());
        this.lQ = bl;
        this.lE = runnable;
        this.l7 = runnable2;
        this.lY = bl ? ClickGuiMacrosSettingsViewMode.NAME_INPUT : ClickGuiMacrosSettingsViewMode.FULL_SETTINGS;
        this.d(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.K$src$V$wmm9kv();
        this.a$src$V$wypqmt();
    }

    public ClickGuiMacrosSettingsViewMode o$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiMacros$in67k2() {
        return this.lY;
    }

    private void p() {
        if (this.lX != null && this.lX.V$src$Z$xc25df()) {
            return;
        }
        this.lX = new ClickGuiMacrosSecondaryBindCaptureTask(this, this.l5);
        this.lX.run();
    }

    private void s$src$V$x8m1bb() {
        boolean bl;
        boolean bl2 = this.lY == ClickGuiMacrosSettingsViewMode.FULL_SETTINGS;
        boolean bl3 = bl = bl2 && this.l5.getDoubleClick().L() != false;
        if (this.lv.V$src$Z$1xhop3l() != bl) {
            this.lv.Z(bl);
        }
    }

    static void H(ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel) {
        clickGuiMacrosSettingsPanel.e$src$V$x0wx09();
    }

    public String Y$src$Ljava_lang_String_$1uaqsjt() {
        return this.lF;
    }

    private void k$src$V$x47okf() {
        this.lY = ClickGuiMacrosSettingsViewMode.FULL_SETTINGS;
        this.a$src$V$wypqmt();
    }

    private void b$src$V$wz9j86() {
        float f = 30.0f;
        float f2 = 43.5f;
        boolean bl = this.lY == ClickGuiMacrosSettingsViewMode.KEYBIND_INPUT || this.lY == ClickGuiMacrosSettingsViewMode.FULL_SETTINGS;
        double d = bl ? 43.5 : 30.0;
        this.lt.Y(d);
    }

    static void D(ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel) {
        clickGuiMacrosSettingsPanel.a$src$V$wypqmt();
    }

    static BindCaptureTask K(ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel, BindCaptureTask bindCaptureTask) {
        clickGuiMacrosSettingsPanel.lX = bindCaptureTask;
        return clickGuiMacrosSettingsPanel.lX;
    }

    static ClickGuiMacrosSettingsViewMode F(ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel) {
        return clickGuiMacrosSettingsPanel.lY;
    }

    private void w$src$V$xat7or() {
        double d = this.A() - 12.0;
        this.le = new PanelComponent(d, 14.0);
        this.le.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.le.d(false);
        TextButton textButton = new TextButton("CANCEL", 0.625, this.lA.i, this.lA.i.brighter(), null, 2.0f, 1.0f, 35.5, 14.0);
        textButton.a(true);
        textButton.c(true);
        textButton.F(false);
        textButton.h(this.lA.A);
        textButton.r(this::lambda$buildActionButtons$0);
        TextButton textButton2 = new TextButton(this.lQ ? "ADD" : "UPDATE", 0.625, this.lA.B, this.lA.B.brighter(), null, 2.0f, 1.0f, 27.5, 14.0);
        textButton2.a(true);
        textButton2.F(false);
        textButton2.h(this.lA.A);
        textButton2.r(this::lambda$buildActionButtons$1);
        double d2 = textButton.A() + 4.0 + textButton2.A();
        this.le.h(new SpacerComponent(this.le.A() - d2, 0.0), new Object[0]);
        this.le.h(new PaddedComponent(0.0, 0.0, 0.0, 4.0, textButton), new Object[0]);
        this.le.h(textButton2, new Object[0]);
        this.lW = new PaddedComponent(0.0, 6.0, 6.0, 0.0, this.le);
        this.h(this.lW, new Object[0]);
    }

    private void Q(double d) {
        float f = 8.0f;
        float f2 = 4.0f;
        float f3 = 18.0f;
        float f4 = 30.0f;
        float f5 = 43.5f;
        float f6 = 0.5632f;
        float f7 = 8.0f;
        float f8 = 8.0f;
        float f9 = 3.0f;
        this.l9 = new ClickGuiMacrosNameInput(this, "Type item name");
        this.l9.e(false);
        this.l9.V(6.0f);
        this.l9.C(0.0);
        this.l9.H(0.0f);
        this.l9.W(true);
        this.l9.Y(18.0);
        this.l9.o(d);
        this.l9.n(32);
        this.l9.O(0.0f);
        this.l9.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(true);
        this.l9.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().W("newnext");
        if (!this.lQ) {
            this.l9.k(this.l5.getName());
        }
        this.lt = new ClickGuiMacrosSettingsControlPanel(this, d, 43.5);
        this.lt.d(false);
        this.lt.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.lt.h(new SpacerComponent(d, 12.0), new Object[0]);
        this.lt.h(this.l9, new Object[0]);
        this.l0 = new PaddedComponent(6.0, 0.0, 6.0, 0.0, this.lt);
        this.h(this.l0, new Object[0]);
    }

    static Macro M(ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel) {
        return clickGuiMacrosSettingsPanel.l5;
    }

    static void T(ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel, String string) {
        clickGuiMacrosSettingsPanel.E(string);
    }
}

