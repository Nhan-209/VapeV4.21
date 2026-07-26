package gg.vape.ui.click.component.input;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.input.BindableInputComponent;

class BindableInputComponentCaptureToggleClickHandler
implements GuiClickListener {
    final BindableInputComponent g;

    BindableInputComponentCaptureToggleClickHandler(BindableInputComponent bindableInputComponent) {
        this.g = bindableInputComponent;
    }

    @Override
    public void P() {
        if (BindableInputComponent.J(this.g).V$src$Z$xc25df()) {
            BindableInputComponent.P(this.g).M(null);
            this.g.w(BindableInputComponent.h(this.g));
            return;
        }
        if (BindableInputComponent.C(this.g) && BindableInputComponent.J$src$Z$1bckkjz(this.g)) {
            BindableInputComponent.Z(this.g).n$src$V$quroyt();
            Vape.INSTANCE.saveAndStop();
            return;
        }
        BindableInputComponent.J(this.g).run();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

