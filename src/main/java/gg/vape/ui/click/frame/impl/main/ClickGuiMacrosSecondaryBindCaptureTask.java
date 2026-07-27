package gg.vape.ui.click.frame.impl.main;

import gg.vape.Vape;
import gg.vape.input.BindCaptureTask;
import gg.vape.ui.click.frame.impl.main.ClickGuiMacrosSettingsPanel;
import gg.vape.unmap.Bendable;

class ClickGuiMacrosSecondaryBindCaptureTask
extends BindCaptureTask {
    final ClickGuiMacrosSettingsPanel I;

    ClickGuiMacrosSecondaryBindCaptureTask(ClickGuiMacrosSettingsPanel clickGuiMacrosSettingsPanel, Bendable bendable) {
        super(bendable);
        this.I = clickGuiMacrosSettingsPanel;
    }


    public void V$src$V$ds8rn1() {
        ClickGuiMacrosSettingsPanel.K(this.I, null);
        if (ClickGuiMacrosSettingsPanel.M(this.I).y$src$Z$r0tfl8()) {
            ClickGuiMacrosSettingsPanel.g(this.I);
        } else {
            ClickGuiMacrosSettingsPanel.D(this.I);
        }
        Vape.INSTANCE.saveAndStop();
    }

    @Override
    public void void_V() {
        this.V$src$V$ds8rn1();
    }
}
