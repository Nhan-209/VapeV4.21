package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.render.hud.HudModule;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.ScaledResolution;

public class HudModuleToggleComponent
extends GuiComponent {
    private float o;
    private String G;
    private boolean a;
    private HudModule O;
    private boolean b;
    private Frame i = null;

    @Override
    public double x() {
        return 26.0;
    }

    @Override
    public void F() {
        this.a = true;
    }

    @Override
    public void u() {
        if (this.a && !this.w$src$Z$e457mb()) {
            this.a = false;
        }
        this.d$src$V$1o6ea79();
    }

    @Override
    public void I() {
    }

    @Override
    public void H() {
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 5.0, this.n(), this.A() - 3.0, this.L() - 3.0, this.b ? J.z() : (this.a ? HudModuleToggleComponent.J.l : HudModuleToggleComponent.J.m));
        GuiRenderPrimitives.F(this.G, this.G$src$D$1b2f02a() + this.A() / 2.0 + 3.0, this.n() + this.L() / 2.0 - 2.0, (double)(8.0f * this.o), 8.0f * this.o, this.b || this.a ? HudModuleToggleComponent.J.f : HudModuleToggleComponent.J.W);
    }

    @Override
    public double C() {
        return 26.0;
    }

    private void Y$src$V$1o0cjoa() {
        this.b = !this.b;
        this.O.Y(this.b);
        if (this.i != null) {
            this.w$src$V$1ogudh4();
        }
    }

    private void d$src$V$1o6ea79() {
        if (this.O.r$src$Z$14eylz9() != this.b) {
            this.Y$src$V$1o0cjoa();
        }
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (guiMouseEvent.getAction().equals((Object)MouseButton.LEFT_CLICK)) {
            this.Y$src$V$1o0cjoa();
        }
    }


    public HudModuleToggleComponent(HudModule hudModule) {
        this(hudModule, 1.0f);
    }

    public HudModuleToggleComponent y(Frame frame) {
        this.i = frame;
        return this;
    }

    public HudModuleToggleComponent(HudModule hudModule, float f) {
        this.O = hudModule;
        this.G = hudModule.s$src$Ljava_lang_String_$pdppcm();
        this.b = hudModule.r$src$Z$14eylz9();
        this.o = f;
    }

    public void w$src$V$1ogudh4() {
        if (this.i == null) {
            return;
        }
        this.i.Z(this.b);
        this.i.c(true);
        this.i.U();
        ScaledResolution scaledResolution = Minecraft.G();
        if (this.i.n() > (double)scaledResolution.G() || this.i.n() < 0.0) {
            this.i.S((double)(scaledResolution.G() / 2));
        }
        if (this.i.G$src$D$1b2f02a() > (double)scaledResolution.T() || this.i.G$src$D$1b2f02a() < 0.0) {
            this.i.K(scaledResolution.T() / 2);
        }
        if (this.i instanceof HudModuleConfigFrameBase) {
            HudModuleConfigFrameBase hudModuleConfigFrameBase = (HudModuleConfigFrameBase)this.i;
            hudModuleConfigFrameBase.w$src$V$1ttpy5n();
            hudModuleConfigFrameBase.Z$src$Lgg_vape_ui_click_frame_impl_hud_AnchoredHudModu$1jkbe02().Z(false);
        }
    }
}

