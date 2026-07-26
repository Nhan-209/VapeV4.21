package gg.vape.ui.click.frame.impl;

import gg.vape.Vape;
import gg.vape.module.Macro;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.FrameMacros;
import gg.vape.ui.click.frame.impl.FrameMacrosAddMacroInputComponent;
import gg.vape.ui.click.frame.impl.FrameMacrosEditor;

class FrameMacrosAddMacroClickHandler
implements GuiClickListener {
    final FrameMacrosAddMacroInputComponent P;
    final FrameMacros D;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void P() {
        if (FrameMacrosAddMacroInputComponent.p$src$Z$xrofzd(this.P)) {
            this.P.p();
            if (FrameMacrosAddMacroInputComponent.j(this.P) != null) {
                Macro macro = Macro.create(FrameMacrosAddMacroInputComponent.j(this.P));
                if (Vape.INSTANCE.getMacrosManager().getMacro(FrameMacrosAddMacroInputComponent.j(this.P)) != null) {
                    this.P.k("");
                    ClientSettings.fT = null;
                    return;
                }
                ClientSettings.fT = null;
                FrameMacrosAddMacroInputComponent.w(this.P, new FrameMacrosEditor(this.D, macro));
                FrameMacrosAddMacroInputComponent.p(this.P).N$src$V$13y6z98();
                this.P.H(FrameMacrosAddMacroInputComponent.p(this.P));
            }
            this.P.k("");
        }
    }

    FrameMacrosAddMacroClickHandler(FrameMacrosAddMacroInputComponent frameMacrosAddMacroInputComponent, FrameMacros frameMacros) {
        this.P = frameMacrosAddMacroInputComponent;
        this.D = frameMacros;
    }
}

