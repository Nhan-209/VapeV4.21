package gg.vape.ui.click.component.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;

public abstract class AbstractListValueComponent
extends InteractiveComponent {
    private boolean Q;
    private boolean b;
    private ColorAnimation v;

    public void a(boolean bl) {
        if (bl) {
            for (GuiComponent guiComponent : this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().f()) {
                if (!(guiComponent instanceof AbstractListValueComponent)) continue;
                ((AbstractListValueComponent)guiComponent).a(false);
            }
        }
        this.Q = bl;
    }

    @Override
    public void u() {
        if (this.b && !this.w$src$Z$e457mb()) {
            this.v.J();
            this.b = false;
        }
    }

    @Override
    public double C() {
        return 25.0;
    }

    @Override
    public double x() {
        return 110.0;
    }

    public AbstractListValueComponent() {
        this.v = new ColorAnimation(0.15, AbstractListValueComponent.J.l, AbstractListValueComponent.J.W);
    }

    @Override
    public void I() {
    }

    public ColorAnimation K$src$Lgg_vape_ui_click_animation_ColorAnimation_$la4la() {
        return this.v;
    }

    public boolean P$src$Z$og01j6() {
        return this.Q;
    }

    public boolean d$src$Z$oqzxee() {
        return this.b;
    }

    private static ObfuscatedRuntimeException c(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void F() {
        if (!this.b) {
            this.v.J();
        }
        this.b = true;
    }
}

