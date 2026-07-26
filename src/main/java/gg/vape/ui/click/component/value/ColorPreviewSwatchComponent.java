package gg.vape.ui.click.component.value;

import func.skidline.RectData;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.ColorValue;

public class ColorPreviewSwatchComponent
extends InteractiveComponent {
    private final ColorValue K;
    private TimerUtil v;
    private boolean Q;
    int b = 0;
    private RectData I = new RectData(0.0, 0.0, 0.0, 0.0);

    @Override
    public void D(GuiMouseEvent guiMouseEvent) {
        if (this.I.J(guiMouseEvent.getX(), guiMouseEvent.getY())) {
            this.K.Y(!this.K.g());
            if (this.K.g()) {
                this.K.G().A(255.0);
                this.K.y().A(255.0);
            }
        }
    }

    @Override
    public void u() {
        if (this.Q && !this.I.Z(RenderUtils.h())) {
            this.Q = false;
        }
    }

    @Override
    public void H() {
        this.I = new RectData(this.G$src$D$1b2f02a(), this.n(), 6.0, 6.0);
        ImageRenderer.E(this.K.g() ? ColorPreviewSwatchComponent.J.W : (this.Q ? ColorPreviewSwatchComponent.J.f : ColorPreviewSwatchComponent.J.W), (float)this.I.o(), (float)this.I.W(), "rainbow_4", (float)this.I.e(), (float)this.I.R(), false);
        ImageRenderer.E(this.b >= 3 ? ColorPreviewSwatchComponent.J.d : (this.Q ? ColorPreviewSwatchComponent.J.f : ColorPreviewSwatchComponent.J.W), (float)this.I.o(), (float)this.I.W(), "rainbow_3", (float)this.I.e(), (float)this.I.R(), false);
        ImageRenderer.E(this.b >= 2 ? ColorPreviewSwatchComponent.J.I : (this.Q ? ColorPreviewSwatchComponent.J.f : ColorPreviewSwatchComponent.J.W), (float)this.I.o(), (float)this.I.W(), "rainbow_2", (float)this.I.e(), (float)this.I.R(), false);
        ImageRenderer.E(this.b >= 1 ? ColorPreviewSwatchComponent.J.B : (this.Q ? ColorPreviewSwatchComponent.J.f : ColorPreviewSwatchComponent.J.W), (float)this.I.o(), (float)this.I.W(), "rainbow_1", (float)this.I.e(), (float)this.I.R(), false);
        if (this.v.hasTimeElapsed(100L)) {
            if (this.K.g()) {
                if (this.b < 3) {
                    ++this.b;
                }
            } else if (this.b > 0) {
                --this.b;
            }
            this.v.reset();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double x() {
        return 6.0;
    }

    @Override
    public double C() {
        return 6.0;
    }

    public ColorPreviewSwatchComponent(ColorValue colorValue) {
        this.v = new TimerUtil();
        this.K = colorValue;
    }
}

