package gg.vape.ui.click.frame.impl;

import gg.vape.module.Mod;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrameHeader;

public class ModuleCategoryFrameHeaderNextModuleClickHandler
implements GuiClickListener {
    final Mod i;
    final ModuleCategoryFrameHeader U;

    public ModuleCategoryFrameHeaderNextModuleClickHandler(ModuleCategoryFrameHeader moduleCategoryFrameHeader, Mod mod) {
        this.U = moduleCategoryFrameHeader;
        this.i = mod;
    }

    @Override
    public void P() {
        ModuleCategoryFrameHeader.e(this.U).G(this.i);
        ModuleCategoryFrameHeader.e(this.U).W(this.i).K$src$V$lt0qn9();
        ModuleCategoryFrameHeader.e(this.U).l$src$V$1mibm4x();
    }
}

