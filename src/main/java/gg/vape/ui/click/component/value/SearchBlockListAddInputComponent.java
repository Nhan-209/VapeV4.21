package gg.vape.ui.click.component.value;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.value.SearchBlockListDropdownLayer;
import gg.vape.ui.unmap.SearchBlock;
import gg.vape.unmap.ColorUtil;

public class SearchBlockListAddInputComponent
extends TextInputComponentBase {
    private Runnable vF;
    private boolean vA = false;

    @Override
    public void F() {
        if (this.vA) {
            this.xR.J();
            this.vA = false;
        }
        super.F();
    }

    public SearchBlockListAddInputComponent(String string, Runnable runnable) {
        super(string);
        this.d(false);
        this.vF = runnable;
    }

    public SearchBlockListAddInputComponent(String string) {
        super(string);
        this.d(false);
    }

    @Override
    public double p$src$D$187zcry() {
        if (this.vF != null) {
            return this.A();
        }
        return 110.0;
    }

    @Override
    public double C() {
        return 20.0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double x() {
        return 110.0;
    }

    @Override
    public void p() {
        if (!this.u$src$Z$wt77ym()) {
            this.k("");
            return;
        }
        SearchBlock searchBlock = new SearchBlock(this.i$src$Ljava_lang_String_$1n2xf3k(), ColorUtil.f().getRGB());
        Vape.INSTANCE.getSearch().H(searchBlock);
        if (this.vF != null) {
            this.vF.run();
        } else {
            ((SearchBlockListDropdownLayer)this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb()).e();
        }
        this.k("");
    }
}

