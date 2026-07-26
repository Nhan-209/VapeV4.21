package gg.vape.ui.click.component.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.DoubleAnimation;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.unmap.ColorUtil;
import java.awt.Color;

public abstract class SliderInputHandle
extends TextInputComponentBase {
    private DoubleAnimation kt = new DoubleAnimation(0.15, 0.0, 100.0);
    protected boolean kI;
    protected boolean kd;
    private double kN = 0.0;

    @Override
    public double r() {
        return 15.0;
    }

    @Override
    public double C() {
        return 6.0;
    }

    @Override
    public void u() {
        if (!this.w$src$Z$e457mb() && this.kd) {
            this.kd = false;
            this.kt.J();
        }
        if (!this.n$src$Z$1rnxqrn() && this.kI) {
            this.kI = false;
            this.p();
        }
        if (this.n$src$Z$1rnxqrn() && !this.kI) {
            this.kI = true;
            this.Y$src$V$npqhoj();
        }
    }

    public Color C$src$Ljava_awt_Color_$13eqlq4() {
        if (this.n$src$Z$1rnxqrn()) {
            return ColorUtil.W(SliderInputHandle.J.l, (int)this.kt.getEndValue());
        }
        return ColorUtil.W(SliderInputHandle.J.l, this.kt.getInterpolatedValue().intValue());
    }

    @Override
    public void F() {
        if (!this.kd) {
            this.kd = true;
            this.kt.J();
        }
    }

    @Override
    public double x() {
        return this.r();
    }

    public boolean W() {
        return this.kI;
    }

    public SliderInputHandle() {
        super("");
    }

    public abstract void Y$src$V$npqhoj();

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

