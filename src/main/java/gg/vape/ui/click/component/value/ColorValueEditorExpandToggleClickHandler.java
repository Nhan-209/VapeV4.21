package gg.vape.ui.click.component.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.ClientSettingsColorValueEditorComponent;

class ColorValueEditorExpandToggleClickHandler
implements GuiClickListener {
    final ClientSettingsColorValueEditorComponent O;

    @Override
    public void P() {
        ClientSettingsColorValueEditorComponent.p(this.O, !ClientSettingsColorValueEditorComponent.Q(this.O));
        this.O.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().l$src$V$1mibm4x();
    }

    ColorValueEditorExpandToggleClickHandler(ClientSettingsColorValueEditorComponent clientSettingsColorValueEditorComponent) {
        this.O = clientSettingsColorValueEditorComponent;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

