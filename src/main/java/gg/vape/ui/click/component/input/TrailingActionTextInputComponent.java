package gg.vape.ui.click.component.input;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import java.util.ArrayList;
import java.util.List;

public class TrailingActionTextInputComponent
extends TextInputComponentBase {
    private final List<GuiComponent> md;
    private String mh = null;
    private boolean mY = false;

    public void P(String string) {
        this.mh = string;
        this.mY = false;
    }

    @Override
    public double r() {
        double d = 0.0;
        for (GuiComponent guiComponent : this.md) {
            d += guiComponent.A() + 4.0;
        }
        return this.A() - d - 12.0 - (double)this.y() - (double)this.g();
    }

    private void O$src$V$1aztsxe() {
        if (this.mh != null && !this.mY) {
            this.k(this.mh);
            this.x2 = this.i$src$Ljava_lang_String_$1n2xf3k() != null ? this.i$src$Ljava_lang_String_$1n2xf3k().length() : 0;
            this.k(this.i$src$Ljava_lang_String_$1n2xf3k());
            this.mY = true;
            this.mh = null;
        }
    }

    public TrailingActionTextInputComponent(String string, List<GuiComponent> arrayList) {
        super(string);
        this.md = arrayList != null ? arrayList : new ArrayList();
        this.O(0.0f);
        this.W(true);
        this.Y(16.0);
        this.r(false);
        for (GuiComponent guiComponent : this.md) {
            this.H(guiComponent);
        }
        this.getClass();
        this.Y(18 + 5);
    }

    @Override
    public void c() {
        this.O$src$V$1aztsxe();
        super.c();
        double d = this.p$src$D$187zcry();
        double d2 = 0.0;
        for (GuiComponent guiComponent : this.md) {
            d2 += guiComponent.A() + 2.0;
        }
        double d3 = this.G$src$D$1b2f02a() + d - d2 - (double)this.g();
        double d4 = this.n() + this.L() / 2.0;
        double d5 = d3;
        for (GuiComponent guiComponent : this.md) {
            guiComponent.K(d5);
            guiComponent.S(d4 - guiComponent.L() / 2.0);
            d5 += guiComponent.A() + 2.0;
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void p() {
        if (!this.u$src$Z$wt77ym()) {
            return;
        }
    }
}

