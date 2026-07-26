package gg.vape.ui.click.frame;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.frame.CloseableFrameHeaderComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.ToggleableFrameHeaderToggleClickHandler;
import gg.vape.value.Value;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToggleableFrameHeaderComponent
extends CloseableFrameHeaderComponent {
    private List<GuiComponent> om;
    private static final String jb = "newsettings";
    private boolean o;
    private IconButtonComponent i = new IconButtonComponent(jb);

    public void q(GuiComponent ... guiComponentArray) {
        this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().H(guiComponentArray);
        this.om.addAll(Arrays.asList(guiComponentArray));
        for (GuiComponent guiComponent : this.om) {
            guiComponent.T(ToggleableFrameHeaderComponent.J.r);
            guiComponent.Q(false);
            guiComponent.Z(false);
            guiComponent.T(ToggleableFrameHeaderComponent.J.r);
            Value value = guiComponent.r$src$Lgg_vape_value_Value_$fdf20y();
            if (value == null || value.getParent() == null) continue;
            Color color = value.q$src$Ljava_awt_Color_$1ibcet6() == null ? ToggleableFrameHeaderComponent.J.r.darker() : value.q$src$Ljava_awt_Color_$1ibcet6();
            guiComponent.T(color);
        }
    }

    public boolean I$src$Z$f74e2a() {
        return this.o;
    }

    public IconButtonComponent L$src$Lgg_vape_ui_click_component_IconButtonComponent_$1i7gwfq() {
        return this.i;
    }

    public void R() {
        for (GuiComponent guiComponent : this.om) {
            guiComponent.Z(this.o);
        }
        this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().l$src$V$1mibm4x();
    }

    public ToggleableFrameHeaderComponent(Frame frame, String string, String string2) {
        this(frame, string, string2, 1.0);
    }

    @Override
    public void H() {
        super.H();
        this.i.G(this.o ? ToggleableFrameHeaderComponent.J.f : null);
        double d = this.G$src$D$1b2f02a() + this.A() - 12.5;
        this.getClass();
        this.i.K(d - (double)(8.0f * 2.0f));
        this.i.S(this.n());
        this.i.Y(this.L());
    }

    private static ObfuscatedRuntimeException d(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ToggleableFrameHeaderComponent(Frame frame, String string, String string2, double d) {
        super(frame, string, string2, d);
        this.om = new ArrayList<GuiComponent>();
        this.i.r(new ToggleableFrameHeaderToggleClickHandler(this));
        this.H(this.i);
    }

    static boolean i(ToggleableFrameHeaderComponent toggleableFrameHeaderComponent) {
        return toggleableFrameHeaderComponent.o;
    }

    static boolean a(ToggleableFrameHeaderComponent toggleableFrameHeaderComponent, boolean bl) {
        toggleableFrameHeaderComponent.o = bl;
        return toggleableFrameHeaderComponent.o;
    }

    public void L(boolean bl) {
        this.o = bl;
        this.R();
    }
}

