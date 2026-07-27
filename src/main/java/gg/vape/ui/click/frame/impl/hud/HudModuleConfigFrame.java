package gg.vape.ui.click.frame.impl.hud;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.HudModule;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.ColorDividerComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameCloseClickHandler;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameHeaderComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorFrame;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class HudModuleConfigFrame
extends Frame {
    private int LZ;
    private HudModule LU;
    private String Lo = "LegitSettingFrame";
    private DoubleAnimation LK = new DoubleAnimation(0.15, 0.0, 100.0);
    private DoubleAnimation Li = new DoubleAnimation(0.15, 0.0, 1.0);

    public HudModule F$src$Lgg_vape_module_render_hud_HudModule_$vjtm3x() {
        if (this.LU == null) {
            return null;
        }
        return (HudModule)Vape.INSTANCE.getModManager().getMod(this.LU.getClass());
    }

    @Override
    public void u() {
        if (this.LZ == 1) {
            this.LZ = 2;
            this.LK.c();
            this.Li.c();
        }
        if (this.LZ == 3) {
            this.LZ = 4;
            this.LK.J();
            this.Li.J();
            this.U();
        }
        if (this.LZ == 4 && this.Li.getInterpolatedValue().doubleValue() == this.Li.getStartValue()) {
            this.LZ = 0;
            this.LU = null;
            this.Z(false);
            return;
        }
    }

    public static int O(HudModuleConfigFrame hudModuleConfigFrame, int n) {
        hudModuleConfigFrame.LZ = n;
        return hudModuleConfigFrame.LZ;
    }

    public void T(HudModule hudModule) {
        this.LU = hudModule;
        this.Lo = hudModule.getName();
    }

    @Override
    public boolean d$src$Z$1lx9d06() {
        return false;
    }

    @Override
    public void v() {
    }

    @Override
    public void M() {
    }

    public void T(String string) {
        this.Lo = string;
    }


    @Override
    public String getName() {
        return this.Lo;
    }

    @Override
    public void Y() {
        HudModuleSelectorFrame hudModuleSelectorFrame = ClientSettings.g(HudModuleSelectorFrame.class);
        if (hudModuleSelectorFrame == null) {
            return;
        }
        if (!hudModuleSelectorFrame.V$src$Z$1xhop3l()) {
            this.Z(false);
            return;
        }
        if (this.LZ >= 2) {
            this.U();
        }
        if (this.Li.getInterpolatedValue().doubleValue() != this.Li.getEndValue()) {
            this.D(true);
            this.h(new RectData(hudModuleSelectorFrame.G$src$D$1b2f02a(), hudModuleSelectorFrame.n(), hudModuleSelectorFrame.A(), this.L()));
            this.K(hudModuleSelectorFrame.G$src$D$1b2f02a() + hudModuleSelectorFrame.A() - this.A() * this.Li.getInterpolatedValue());
            this.S(hudModuleSelectorFrame.n());
            this.l$src$V$1mibm4x();
        }
        if (this.G$src$D$1b2f02a() != hudModuleSelectorFrame.G$src$D$1b2f02a() || this.n() != hudModuleSelectorFrame.n()) {
            this.M(hudModuleSelectorFrame.G$src$D$1b2f02a() + hudModuleSelectorFrame.A() - this.A() * this.Li.getInterpolatedValue(), hudModuleSelectorFrame.n());
        }
        this.K(hudModuleSelectorFrame.G$src$D$1b2f02a() + hudModuleSelectorFrame.A() - this.A() * this.Li.getInterpolatedValue());
        GuiRenderPrimitives.e(hudModuleSelectorFrame.G$src$D$1b2f02a(), hudModuleSelectorFrame.n(), hudModuleSelectorFrame.A(), this.L(), new Color(0, 0, 0, this.LK.getInterpolatedValue().intValue()), false, 0.0f, 0.0f);
    }

    @Override
    public double L() {
        HudModuleSelectorFrame hudModuleSelectorFrame = ClientSettings.g(HudModuleSelectorFrame.class);
        if (hudModuleSelectorFrame == null) {
            return 0.0;
        }
        return hudModuleSelectorFrame.L();
    }

    public void R(int n) {
        this.LZ = n;
    }

    public HudModuleConfigFrame() {
        this.I2 = false;
        this.D(false);
        this.T(HudModuleConfigFrame.J.i);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        HudModuleConfigFrameHeaderComponent hudModuleConfigFrameHeaderComponent = new HudModuleConfigFrameHeaderComponent(this);
        hudModuleConfigFrameHeaderComponent.U$src$Lgg_vape_ui_click_component_IconButtonComponent_$1p5p8kd().r(new HudModuleConfigFrameCloseClickHandler(this));
        this.Y(hudModuleConfigFrameHeaderComponent);
        this.Z(false);
        this.L(false, false);
        this.h(new ColorDividerComponent(HudModuleConfigFrame.J.l), new Object[0]);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (!this.w$src$Z$e457mb()) {
            this.LZ = 3;
            this.U();
        }
    }

    public int L$src$I$jm6ud8() {
        return this.LZ;
    }
}

