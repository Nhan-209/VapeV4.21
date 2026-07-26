package gg.vape.ui.click.frame;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.CollapsibleFrame;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.impl.ModuleSearchFrame;

public abstract class FrameHeaderComponent
extends GuiComponent {
    private Frame b;
    private static boolean a;

    public Frame w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0() {
        return this.b;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static boolean U$src$Z$vux5o0() {
        boolean bl = FrameHeaderComponent.l$src$Z$w7kfbb();
        return false;
    }

    @Override
    public boolean j$src$Z$dapde9() {
        return false;
    }

    @Override
    public void H(GuiComponent ... guiComponentArray) {
        super.H(guiComponentArray);
        for (GuiComponent guiComponent : guiComponentArray) {
            guiComponent.w(this);
        }
    }

    @Override
    public double C() {
        return 18.0;
    }

    @Override
    public double x() {
        return 110.0;
    }

    public void K(double d, double d2) {
        for (GuiComponent guiComponent : this.f()) {
            guiComponent.K(guiComponent.G$src$D$1b2f02a() + d);
            guiComponent.S(guiComponent.n() + d2);
            if (!(guiComponent instanceof FrameComponent)) continue;
            ((FrameComponent)guiComponent).R(d, d2);
        }
    }

    @Override
    public void D(GuiMouseEvent guiMouseEvent) {
        if (guiMouseEvent.getAction().equals((Object)MouseButton.RIGHT_CLICK) && this.b instanceof CollapsibleFrame && !(this.b instanceof ModuleSearchFrame)) {
            ((CollapsibleFrame)((Object)this.b)).w();
            return;
        }
        super.D(guiMouseEvent);
    }

    static {
        if (!FrameHeaderComponent.l$src$Z$w7kfbb()) {
            FrameHeaderComponent.f(true);
        }
    }

    public FrameHeaderComponent(Frame frame) {
        this.b = frame;
    }

    public static boolean l$src$Z$w7kfbb() {
        return a;
    }

    public static void f(boolean bl) {
        a = bl;
    }

    public boolean D(int n, int n2) {
        for (GuiComponent guiComponent : this.f()) {
            if (!guiComponent.Q().J(n, n2)) continue;
            return false;
        }
        return this.Q().J(n, n2);
    }
}

