package gg.vape.ui.click.frame.impl.main;

import gg.vape.Vape;
import gg.vape.input.BindCaptureTask;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosSettingsPanel;
import gg.vape.unmap.Bendable;

class ClickGuiMacrosPrimaryBindCaptureTask
extends BindCaptureTask {
    final ClickGuiMacrosSettingsPanel h;

    public void V$src$V$1ybrqsf() {
        ClickGuiMacrosSettingsPanel.K(this.h, null);
        Vape.INSTANCE.saveAndStop();
    }

    @Override
    public void void_V() {
        this.V$src$V$1ybrqsf();
    }

    ClickGuiMacrosPrimaryBindCaptureTask(ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel, Bendable bendable) {
        super(bendable);
        this.h = clickGuiMacrosSettingsPanel;
    }
}
