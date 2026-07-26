package gg.vape.ui.click.text;

public class TextLabelFitSpec {
    private double c;
    private boolean l;
    private double r;
    private double J;
    private String j = "";
    private double m;

    public void W(double d) {
        this.m = d;
    }

    public double u() {
        return this.r;
    }

    public String toString() {
        return "ScalingLabelData{text='" + this.j + '\'' + ", minScale=" + this.m + ", maxScale=" + this.r + ", scaleIncrement=" + this.J + ", maxWidth=" + this.c + ", bold=" + this.l + '}';
    }

    public TextLabelFitSpec(String string, double d, double d2, double d3, double d4, boolean bl) {
        this.j = string;
        this.m = d;
        this.r = d2;
        this.J = d3;
        this.c = d4;
        this.l = bl;
    }

    public double T() {
        return this.c;
    }

    public double h() {
        return this.J;
    }

    public String o() {
        return this.j;
    }

    public double g() {
        return this.m;
    }

    public void k(double d) {
        this.r = d;
    }

    public boolean x() {
        return this.l;
    }

    public void u(double d) {
        this.J = d;
    }

    public void Y(double d) {
        this.c = d;
    }

    public void Q(boolean bl) {
        this.l = bl;
    }

    public void N(String string) {
        this.j = string;
    }
}

