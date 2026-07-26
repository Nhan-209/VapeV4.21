package gg.vape.ui.click.frame.impl;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrameHeader;

public class ModuleCategoryFrameHeaderSearchIconClickHandler
implements GuiClickListener {
    final ModuleCategoryFrameHeader m;

    @Override
    public void P() {
        boolean bl;
        ClientSettings.Y = bl = !ClientSettings.Y;
        ClientSettings.M$src$V$1giazqf();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ModuleCategoryFrameHeaderSearchIconClickHandler(ModuleCategoryFrameHeader moduleCategoryFrameHeader) {
        this.m = moduleCategoryFrameHeader;
    }
}

