package gg.vape.ui.click.frame;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.ClickGuiQuickActionsComponent;
import gg.vape.ui.click.frame.impl.ClientSettingsSearchFrame;
import gg.vape.ui.click.frame.impl.quickactions.QuickActionsFrame;

class ClickGuiQuickActionsFrameToggleClickHandler
implements GuiClickListener {
    final ClickGuiQuickActionsComponent z;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void P() {
        QuickActionsFrame quickActionsFrame = ClientSettings.g(QuickActionsFrame.class);
        ClientSettingsSearchFrame clientSettingsSearchFrame = ClientSettings.g(ClientSettingsSearchFrame.class);
        if (quickActionsFrame == null || clientSettingsSearchFrame == null) {
            return;
        }
        quickActionsFrame.Z(!quickActionsFrame.V$src$Z$1xhop3l());
        if (quickActionsFrame.V$src$Z$1xhop3l()) {
            quickActionsFrame.U();
            quickActionsFrame.w(1);
        }
        quickActionsFrame.l$src$V$1mibm4x();
    }

    ClickGuiQuickActionsFrameToggleClickHandler(ClickGuiQuickActionsComponent clickGuiQuickActionsComponent) {
        this.z = clickGuiQuickActionsComponent;
    }
}

