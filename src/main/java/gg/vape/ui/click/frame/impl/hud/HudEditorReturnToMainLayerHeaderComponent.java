package gg.vape.ui.click.frame.impl.hud;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.HalfScaleIconButtonComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.ui.click.frame.FrameHeaderMainLayerClickHandler;
import gg.vape.ui.click.frame.impl.hud.HudEditorReturnToMainLayerFrame;

public class HudEditorReturnToMainLayerHeaderComponent
extends FrameHeaderComponent {
    private static final String o;
    private static GuiComponent[] K;
    private HudEditorReturnToMainLayerFrame G;
    private IconButtonComponent Q = new HalfScaleIconButtonComponent(o);

    @Override
    public boolean V$src$Z$1xhop3l() {
        return true;
    }

    @Override
    public void I() {
    }

    public static GuiComponent[] j$src$ALgg_vape_ui_click_component_GuiComponent_$88thfo() {
        return K;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void H() {
        this.G.Z$src$V$1vz8z77();
        this.Q.K(this.G$src$D$1b2f02a() + 1.0);
        this.Q.S(this.n() + 1.5);
        this.Q.o(this.A());
        this.Q.Y(this.L());
        if (this.w$src$Z$e457mb()) {
            this.Q.G(J.z().brighter());
        } else {
            this.Q.G(J.z());
        }
        this.Q.Z(true);
    }

    @Override
    public double A() {
        return 22.0;
    }

    @Override
    public void F() {
    }

    public IconButtonComponent X$src$Lgg_vape_ui_click_component_IconButtonComponent_$1etljff() {
        return this.Q;
    }

    @Override
    public double L() {
        return 16.0;
    }

    @Override
    public void u() {
    }

    public static void k(GuiComponent[] guiComponentArray) {
        K = guiComponentArray;
    }

    static {
        HudEditorReturnToMainLayerHeaderComponent.k(new GuiComponent[3]);
        o = "cheat_switch";
    }

    public HudEditorReturnToMainLayerHeaderComponent(HudEditorReturnToMainLayerFrame hudEditorReturnToMainLayerFrame) {
        super(hudEditorReturnToMainLayerFrame);
        this.G = hudEditorReturnToMainLayerFrame;
        this.Q.r(new FrameHeaderMainLayerClickHandler(this));
        this.H(this.Q);
        this.Z(true);
    }
}

