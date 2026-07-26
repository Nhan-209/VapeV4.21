package gg.vape.ui.click.frame.impl.target;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameBase;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class TargetInfoStatStripComponent
extends GuiComponent {
    private Color i;
    private static int[] o;
    protected HudModuleFrameBase O;

    public TargetInfoStatStripComponent(int n, int n2) {
        this.i = TargetInfoStatStripComponent.J.r;
        this.o(n);
        this.Y(n2);
        this.T(this.i);
    }

    @Override
    public void H() {
        Color color = this.O != null ? this.O.l(this.d()) : this.d();
        GuiRenderPrimitives.B(this.G$src$D$1b2f02a() + 1.0, this.n(), this.A() - 2.0, this.L(), color, 1.0f);
    }

    public static int[] t$src$AI$1luooe3() {
        return o;
    }

    static {
        if (TargetInfoStatStripComponent.t$src$AI$1luooe3() != null) {
            TargetInfoStatStripComponent.j(new int[5]);
        }
    }

    public void E(HudModuleFrameBase hudModuleFrameBase) {
        this.O = hudModuleFrameBase;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void j(int[] nArray) {
        o = nArray;
    }
}

