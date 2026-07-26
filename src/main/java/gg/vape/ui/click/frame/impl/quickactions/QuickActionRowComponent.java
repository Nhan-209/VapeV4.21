package gg.vape.ui.click.frame.impl.quickactions;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.impl.quickactions.QuickActionRowClickListener;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;

public class QuickActionRowComponent
extends BooleanToggleComponent {
    private String N1;
    private Class Nt;
    private int Nb;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double C() {
        return 20.0;
    }

    @Override
    protected void s$src$V$1uam4mz() {
        SmoothFontRenderer smoothFontRenderer = this.O(this.Q0);
        double d = smoothFontRenderer.d(this.QR);
        double d2 = this.n() + this.L() / 2.0 - d / 2.0;
        GuiRenderPrimitives.F(this.N1, this.G$src$D$1b2f02a() + 5.0 + 4.0, this.n() + this.L() / 2.0, (double)this.Nb, this.Nb, this.QE);
        smoothFontRenderer.d(this.QR, this.G$src$D$1b2f02a() + (double)this.Nb + 10.0, d2, this.QE);
    }

    public QuickActionRowComponent(String string, String string2, double d, int n) {
        super(string, d);
        this.N1 = string2;
        this.Nb = n;
        this.G(true);
    }

    public void y(Class clazz) {
        this.Nt = clazz;
        this.j(new QuickActionRowClickListener(this, clazz));
    }

    @Override
    protected void n$src$V$1tjvir5() {
        if (this.Nt != null && ((GuiComponent)ClientSettings.g(this.Nt)).V$src$Z$1xhop3l() != this.i$src$Z$1d37ezg() && !this.U$src$Z$1e0xb5o()) {
            this.k$src$V$5mynh8();
        }
        super.n$src$V$1tjvir5();
    }
}

