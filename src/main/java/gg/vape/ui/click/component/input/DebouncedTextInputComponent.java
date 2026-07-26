package gg.vape.ui.click.component.input;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.ClickCooldownState;
import gg.vape.ui.click.component.TextInputComponentBase;

public class DebouncedTextInputComponent
extends TextInputComponentBase {
    private static final String jb = "Unhandled cooldown fail ";
    ClickCooldownState Py = new ClickCooldownState();

    public ClickCooldownState I$src$Lgg_vape_ui_click_component_ClickCooldownState_$54kj38() {
        return this.Py;
    }

    public void U$src$V$1pxrzte() {
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public void p() {
        if (this.Py.t()) {
            this.j();
            return;
        }
        this.U$src$V$1pxrzte();
        this.Py.j(true);
    }

    public DebouncedTextInputComponent(String string, long l) {
        super(string);
        this.Py.I(l);
    }

    public void j() {
        Vape.debugLog(jb + this);
    }

    @Override
    public double x() {
        return 0.0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

