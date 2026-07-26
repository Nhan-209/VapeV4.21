package gg.vape.unmap;

public class NumberFormat {
    private final int h;

    public NumberFormat(int n) {
        if (n <= 0) {
            n = -1;
        }
        this.h = n;
    }

    public NumberFormat(String string) {
        this(string.length() - (string.indexOf(".") + 1));
    }

    public double truncate(Double d) {
        return this.truncate((double)d);
    }

    public double truncate(double d) {
        return Double.valueOf(this.format(d));
    }

    public String format(Double d) {
        return this.format((double)d);
    }

    public String format(double d) {
        String string = String.valueOf(d).replaceAll(",", ".");
        if (string.contains("E")) {
            return string;
        }
        if (string.contains(".")) {
            int n = Math.min(string.indexOf(46) + this.h + 1, string.length());
            return string.substring(0, n);
        }
        return string;
    }

    public int getPrecision() {
        return this.h;
    }
}

