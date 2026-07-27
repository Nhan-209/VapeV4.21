package gg.vape.ui.click.component.gui;

import gg.vape.Vape;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WrappedTextComponent
extends SimpleTextLabelComponent {
    private double Df;
    private boolean o = true;
    private boolean Dd;
    private double Di = 0.0;
    private double b;
    private List<String> Dy;
    private double DM = 0.0;
    private boolean DY = false;

    @Override
    public void l(boolean bl) {
        this.Dd = bl;
    }

    public void c(double d) {
        this.b = d;
    }

    private void z() {
        SmoothFontRenderer smoothFontRenderer = this.Dd ? Vape.INSTANCE.getFontManager().W(this.G, false) : Vape.INSTANCE.getFontManager().E(this.G, false);
        double d = this.n();
        for (String string : this.Q$src$Ljava_util_List_$1gv03oz()) {
            if (this.DY) {
                smoothFontRenderer.v(string, this.G$src$D$1b2f02a() + this.Di, d, this.G$src$Ljava_awt_Color_$11jgid7());
            } else {
                smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + this.Di, d, this.G$src$Ljava_awt_Color_$11jgid7());
            }
            d += smoothFontRenderer.d(string);
            double d2 = smoothFontRenderer.N(string);
            if (!(d2 > this.Df)) continue;
            this.Df = d2;
        }
        this.DM = d - this.n();
    }

    public List<String> Q$src$Ljava_util_List_$1gv03oz() {
        if (this.Dy == null) {
            SmoothFontRenderer smoothFontRenderer = this.Dd ? Vape.INSTANCE.getFontManager().W(this.G, false) : Vape.INSTANCE.getFontManager().E(this.G, false);
            this.Dy = this.A(Arrays.asList(this.O.split("\n")), smoothFontRenderer);
        }
        return this.Dy;
    }

    private ArrayList<String> A(List<String> list, SmoothFontRenderer smoothFontRenderer) {
        ArrayList<String> arrayList = new ArrayList<String>();
        boolean bl = false;
        for (String string : list) {
            String[] stringArray = string.split(" ");
            String string2 = "";
            for (int i = 0; i < stringArray.length; ++i) {
                String string3 = stringArray[i];
                double d = smoothFontRenderer.N(string3);
                if (d > this.W()) {
                    bl = true;
                    double d2 = this.W() / d;
                    int n = (int)((double)string3.length() * d2);
                    String string4 = string3.substring(0, n);
                    String string5 = string3.substring(n, string3.length() - 1);
                    arrayList.add(string4);
                    arrayList.add(string5);
                    continue;
                }
                if (i < stringArray.length - 1) {
                    String string6 = stringArray[i + 1];
                    double d3 = smoothFontRenderer.N(string6);
                    if (d + smoothFontRenderer.N(string2) + d3 < this.W()) {
                        string2 = string2 + string3 + " ";
                        continue;
                    }
                    string2 = string2 + string3;
                    string2 = string2.trim();
                    arrayList.add(string2);
                    string2 = "";
                    continue;
                }
                string2 = string2 + string3;
                arrayList.add(string2);
            }
        }
        return bl ? this.A(arrayList, smoothFontRenderer) : arrayList;
    }

    public void u(boolean bl) {
        this.o = bl;
    }

    @Override
    public void G(String string) {
        super.G(string);
        this.Dy = null;
    }

    @Override
    public void c() {
        super.c();
        this.z();
    }

    public WrappedTextComponent(String string, double d, Color color, boolean bl, double d2) {
        super(string, d);
        this.T$src$V$1orl066(color);
        this.Dd = bl;
        this.Di = d2;
    }


    @Override
    public double A() {
        return this.Df;
    }

    @Override
    public double C() {
        return this.DM;
    }

    public WrappedTextComponent(String string, double d, Color color, boolean bl) {
        super(string, d);
        this.T$src$V$1orl066(color);
        this.Dd = bl;
    }

    @Override
    public void H() {
    }

    public void K(boolean bl) {
        this.DY = bl;
    }

    public double W() {
        return this.b;
    }

    public WrappedTextComponent(String string, double d) {
        super(string, d);
    }
}

