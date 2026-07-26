package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleGroupTabClickHandler;
import gg.vape.ui.click.frame.impl.hud.HudModuleListPanel;
import gg.vape.ui.click.frame.impl.hud.HudModuleSearchBox;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorHeaderComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.ArrayList;

public class HudModuleSelectorFrame
extends Frame {
    private final PanelComponent Ww;
    public static HudModuleListPanel WN;
    private ArrayList<TextLabel> WX;
    private HudModuleGroup WP;
    private String W2 = "";
    private boolean W8 = false;
    private HudModuleSearchBox W1 = new HudModuleSearchBox(this);
    public static boolean W_;

    @Override
    public void v() {
    }

    public void d$src$V$b5ssve() {
        ClientSettings.q(this);
        ClientSettings.q(WN);
    }

    public HudModuleSearchBox P$src$Lgg_vape_ui_click_frame_impl_hud_HudModuleSearch$jdrd5q() {
        return this.W1;
    }

    @Override
    public boolean d$src$Z$1lx9d06() {
        return false;
    }

    static HudModuleGroup n(HudModuleSelectorFrame hudModuleSelectorFrame) {
        return hudModuleSelectorFrame.WP;
    }

    @Override
    public String getName() {
        return "LegitMenuFrame";
    }

    @Override
    public double C() {
        return 20.0;
    }

    @Override
    public double A() {
        return 350.0;
    }

    private void Z$src$V$b0auxs() {
        for (TextLabel textLabel : this.WX) {
            if (this.S$src$Lgg_vape_module_render_hud_HudModuleGroup_$8wvu6a().getName().equalsIgnoreCase(textLabel.L$src$Ljava_lang_String_$1ncdwqb())) {
                textLabel.l(Color.WHITE);
                this.e(textLabel);
                continue;
            }
            textLabel.l(null);
        }
    }

    public HudModuleGroup S$src$Lgg_vape_module_render_hud_HudModuleGroup_$8wvu6a() {
        return this.WP;
    }

    public HudModuleListPanel a$src$Lgg_vape_ui_click_frame_impl_hud_HudModuleListPa$qfwoz4() {
        return WN;
    }

    public String D$src$Ljava_lang_String_$18bm3e4() {
        return this.W2;
    }

    @Override
    public void Y() {
    }

    private void e(TextLabel textLabel) {
        double d = textLabel.n() + 7.0;
        for (double d2 = textLabel.G$src$D$1b2f02a(); d2 < textLabel.G$src$D$1b2f02a() + (textLabel.W() + 1.0); d2 += 2.0) {
            GuiRenderPrimitives.a(d2, d, 1.0, 1.0f, HudModuleSelectorFrame.J.A);
        }
    }

    @Override
    public void U() {
        this.d$src$V$b5ssve();
    }

    @Override
    public void D(GuiMouseEvent guiMouseEvent) {
        if (ClientSettings.g(HudModuleConfigFrame.class).V$src$Z$1xhop3l()) {
            ClientSettings.g(HudModuleConfigFrame.class).g(guiMouseEvent);
            return;
        }
        super.D(guiMouseEvent);
    }

    @Override
    public double L() {
        return (this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() + 20.0) * 3.5 + 52.0;
    }

    public void j(String string) {
        this.W2 = string;
        WN.N$src$V$wrn2a4();
    }

    static HudModuleGroup M(HudModuleSelectorFrame hudModuleSelectorFrame, HudModuleGroup hudModuleGroup) {
        hudModuleSelectorFrame.WP = hudModuleGroup;
        return hudModuleSelectorFrame.WP;
    }

    public HudModuleSelectorFrame() {
        this.WP = HudModuleGroup.J;
        this.WX = new ArrayList();
        this.Ww = new PanelComponent(this.A(), 18.0);
        WN = new HudModuleListPanel(this);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().t(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().I(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().U(false);
        this.T(HudModuleSelectorFrame.J.i);
        this.Y(new HudModuleSelectorHeaderComponent(this));
        this.Z(true);
        this.L(false, true);
        this.g(true);
        this.Y(false);
        this.H(new GuiComponent[0]);
        this.Ww.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        SpacerComponent spacerComponent = new SpacerComponent(12.5, 0.0);
        this.Ww.h(spacerComponent, new Object[0]);
        PanelComponent panelComponent = new PanelComponent((this.Ww.A() - spacerComponent.A()) / 2.0, this.Ww.L());
        panelComponent.h(new SpacerComponent(0.0, 8.0), "wrap");
        this.Ww.h(panelComponent, new Object[0]);
        panelComponent.d(false);
        for (HudModuleGroup hudModuleGroup : HudModuleGroup.C()) {
            TextLabel textLabel = new TextLabel(hudModuleGroup.getName(), 0.75);
            textLabel.r(new HudModuleGroupTabClickHandler(this, hudModuleGroup));
            textLabel.o(textLabel.W());
            textLabel.Y(7.0);
            textLabel.Z(true);
            this.WX.add(textLabel);
            panelComponent.h(textLabel, new Object[0]);
            panelComponent.h(new SpacerComponent(17.5, 0.0), new Object[0]);
        }
        this.Ww.d(false);
        this.h(this.Ww, new Object[0]);
        this.Ww.h(this.W1, "alignright");
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void c() {
        super.c();
        if (!this.W8) {
            WN.N$src$V$wrn2a4();
            this.W8 = true;
            this.U();
        }
        this.Ww.u(20.0);
        WN.o(this.A());
        WN.Y(WN.d$src$D$ibccpu());
        WN.M(this.G$src$D$1b2f02a(), this.n() + this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() + this.W1.L());
        WN.c();
        this.Z$src$V$b0auxs();
        this.l$src$V$1mibm4x();
    }

    @Override
    public double x() {
        return this.A();
    }

    @Override
    public void t(boolean bl, boolean bl2) {
        super.t(bl, bl2);
        WN.t(bl, bl2);
        if (bl) {
            this.d$src$V$b5ssve();
        }
    }

    @Override
    public void J() {
        if (ClientSettings.g(HudModuleConfigFrame.class).V$src$Z$1xhop3l()) {
            return;
        }
        super.J();
    }

    static {
        W_ = false;
    }
}

