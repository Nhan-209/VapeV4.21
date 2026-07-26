package gg.vape.ui.click.text;

import java.util.Objects;

public class TruncatedTextSpec {
    private boolean a;
    private double G;
    private double X;
    private String p;
    private String e;

    public double y() {
        return this.X;
    }

    public TruncatedTextSpec(String string, String string2, double d, double d2, boolean bl) {
        this.p = string;
        this.e = string2;
        this.X = d;
        this.G = d2;
        this.a = bl;
    }

    public void v(String string) {
        this.p = string;
    }

    public void R(double d) {
        this.G = d;
    }

    public void O(double d) {
        this.X = d;
    }

    public String L() {
        return this.e;
    }

    public String toString() {
        return "CutoffLabelData{text='" + this.p + '\'' + ", endText='" + this.e + '\'' + ", maxWidth=" + this.X + ", scale=" + this.G + ", bold=" + this.a + '}';
    }

    public void B(boolean bl) {
        this.a = bl;
    }

    public void w(String string) {
        this.e = string;
    }

    public int hashCode() {
        return Objects.hash(this.p, this.e, this.X, this.G, this.a);
    }

    public double N() {
        return this.G;
    }

    public boolean q() {
        return this.a;
    }

    public String g() {
        return this.p;
    }
}

