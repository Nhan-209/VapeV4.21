package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class AnimatedPanelComponent
extends PanelComponent {
    private boolean fP;
    private float fp = 1.0f;
    private float f0 = 2.0f;
    private ColorAnimation fx;
    private Color fw = null;

    private GuiComponent D(GuiComponent guiComponent) {
        ArrayList<GuiComponent> arrayList = this.X(new ArrayList<GuiComponent>(Arrays.asList(guiComponent)), 0);
        for (GuiComponent guiComponent2 : arrayList) {
            if (!guiComponent2.V$src$Z$1xhop3l() || !guiComponent2.w$src$Z$e457mb() || !(guiComponent2 instanceof InteractiveComponent) && !(guiComponent2 instanceof TextInputComponentBase)) continue;
            return guiComponent2;
        }
        return null;
    }

    public void T(float f) {
        this.f0 = f;
    }

    @Override
    public void J() {
        super.J();
    }

    public void e(float f) {
        this.fp = f;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void z(boolean bl) {
        if (!this.Z$src$Z$16e8vsp()) {
            return;
        }
        GuiRenderPrimitives.e(this.G$src$D$1b2f02a(), this.n(), this.A(), bl ? this.K : this.L(), this.fP ? AnimatedPanelComponent.J.K : this.fx.getInterpolatedColor(), this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc() != null, 2.0f, 1.0f);
    }

    public void L$src$V$1hgphv8() {
        this.fP = !this.fP;
    }

    public float j$src$F$1hx7baa() {
        return this.f0;
    }

    public AnimatedPanelComponent(double d, double d2) {
        super(d, d2);
        this.T(AnimatedPanelComponent.J.m);
        this.fx = new ColorAnimation(0.15, this.d(), new Color(36, 35, 36));
    }

    @Override
    public void F() {
        if (!this.w$src$Z$e457mb()) {
            // empty if block
        }
        super.F();
    }

    public Color w$src$Ljava_awt_Color_$j769pz() {
        return this.fw;
    }

    public void h(ColorAnimation colorAnimation) {
        this.fx = colorAnimation;
    }

    public ColorAnimation b$src$Lgg_vape_ui_click_animation_ColorAnimation_$1w5shon() {
        return this.fx;
    }

    public float b$src$F$1hssyje() {
        return this.fp;
    }

    @Override
    public void u() {
        super.u();
    }

    public AnimatedPanelComponent(double d, double d2, Color color, Color color2) {
        super(d, d2);
        this.T(color);
        this.fx = new ColorAnimation(0.15, this.d(), color2);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    public void n(Color color) {
        this.fw = color;
    }

    @Override
    public void D(GuiMouseEvent guiMouseEvent) {
        super.D(guiMouseEvent);
    }
}

