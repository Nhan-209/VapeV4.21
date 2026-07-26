package gg.vape.ui.click.frame.impl;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrameHeader;

public class ModuleCategoryFrameHeaderSearchLabelClickHandler
implements GuiClickListener {
    final ModuleCategoryFrameHeader r;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void P() {
        boolean bl;
        ClientSettings.Y = bl = !ClientSettings.Y;
        ClientSettings.M$src$V$1giazqf();
    }

    public ModuleCategoryFrameHeaderSearchLabelClickHandler(ModuleCategoryFrameHeader moduleCategoryFrameHeader) {
        this.r = moduleCategoryFrameHeader;
    }
}

