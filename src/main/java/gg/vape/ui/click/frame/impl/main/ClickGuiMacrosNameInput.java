package gg.vape.ui.click.frame.impl.main;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.input.SmallTextInputComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosSettingsPanel;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosSettingsViewMode;

class ClickGuiMacrosNameInput
extends SmallTextInputComponent {
    final ClickGuiMacrosSettingsPanel SC;

    @Override
    public float p$src$F$1qfoyd() {
        return 0.0f;
    }

    @Override
    public boolean n$src$Z$1rnxqrn() {
        return this.g$src$Z$tyzhmd() && super.n$src$Z$1rnxqrn();
    }

    @Override
    public void p() {
        if (this.g$src$Z$tyzhmd() && this.u$src$Z$wt77ym()) {
            ClickGuiMacrosSettingsPanel.T(this.SC, this.i$src$Ljava_lang_String_$1n2xf3k().trim());
        }
    }

    ClickGuiMacrosNameInput(ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel, String string) {
        super(string);
        this.SC = clickGuiMacrosSettingsPanel;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.g$src$Z$tyzhmd()) {
            super.g(guiMouseEvent);
        }
    }

    private boolean g$src$Z$tyzhmd() {
        return ClickGuiMacrosSettingsPanel.F(this.SC) == ClickGuiMacrosSettingsViewMode.NAME_INPUT;
    }

    @Override
    public void b$src$V$17wa4kz() {
        if (this.g$src$Z$tyzhmd()) {
            super.b$src$V$17wa4kz();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
