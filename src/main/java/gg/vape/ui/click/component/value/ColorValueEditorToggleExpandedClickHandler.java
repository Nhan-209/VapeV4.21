package gg.vape.ui.click.component.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.ColorValueEditorComponent;

public class ColorValueEditorToggleExpandedClickHandler
implements GuiClickListener {
    final ColorValueEditorComponent l;

    public ColorValueEditorToggleExpandedClickHandler(ColorValueEditorComponent colorValueEditorComponent) {
        this.l = colorValueEditorComponent;
    }

    @Override
    public void P() {
        ColorValueEditorComponent.h(this.l, !ColorValueEditorComponent.M(this.l));
        this.l.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().l$src$V$1mibm4x();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

