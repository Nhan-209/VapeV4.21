package gg.vape.ui.click.frame.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.frame.impl.FrameMacros;
import gg.vape.ui.click.frame.impl.FrameMacrosAddMacroClickHandler;
import gg.vape.ui.click.frame.impl.FrameMacrosAddMacroKeyTypedListener;
import gg.vape.ui.click.frame.impl.FrameMacrosEditor;

public class FrameMacrosAddMacroInputComponent
extends TextInputComponentBase {
    private FrameMacrosEditor Xa;
    private String XX;
    private boolean X1 = false;

    static boolean p$src$Z$xrofzd(FrameMacrosAddMacroInputComponent frameMacrosAddMacroInputComponent) {
        return frameMacrosAddMacroInputComponent.X1;
    }

    public FrameMacrosAddMacroInputComponent(FrameMacros frameMacros) {
        super("");
        this.b.W("newnext");
        this.o(new FrameMacrosAddMacroKeyTypedListener(this, frameMacros));
        this.b.r(new FrameMacrosAddMacroClickHandler(this, frameMacros));
    }

    @Override
    public void p() {
        if (!this.X1) {
            if (!this.u$src$Z$wt77ym()) {
                this.k("");
                return;
            }
            this.XX = this.i$src$Ljava_lang_String_$1n2xf3k();
        }
        this.X1 = !this.X1;
        this.a = this.X1 ? FrameMacrosAddMacroInputComponent.J.K : null;
    }

    static String j(FrameMacrosAddMacroInputComponent frameMacrosAddMacroInputComponent) {
        return frameMacrosAddMacroInputComponent.XX;
    }

    static FrameMacrosEditor p(FrameMacrosAddMacroInputComponent frameMacrosAddMacroInputComponent) {
        return frameMacrosAddMacroInputComponent.Xa;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double C() {
        return this.Xa != null ? this.Xa.L() : 20.0;
    }

    static FrameMacrosEditor w(FrameMacrosAddMacroInputComponent frameMacrosAddMacroInputComponent, FrameMacrosEditor frameMacrosEditor) {
        frameMacrosAddMacroInputComponent.Xa = frameMacrosEditor;
        return frameMacrosAddMacroInputComponent.Xa;
    }

    @Override
    public void H() {
        if (this.Xa != null) {
            if (!this.Xa.V$src$Z$1xhop3l()) {
                this.Xa = null;
                this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().l$src$V$1mibm4x();
            } else {
                this.b.Z(false);
                this.Xa.K(this.G$src$D$1b2f02a());
                this.Xa.S(this.n());
                return;
            }
        }
        this.b.Z(true);
        if (this.X1) {
            this.k("");
            this.v(FrameMacrosAddMacroInputComponent.J.m);
            this.b("Press a key to bind");
            this.b.W("newbind");
            this.j(false);
        } else {
            this.v(FrameMacrosAddMacroInputComponent.J.r);
            this.b("Type item name");
            this.b.W("newnext");
            this.j(true);
        }
        super.H();
    }

    @Override
    public double x() {
        return 110.0;
    }
}

