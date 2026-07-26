package gg.vape.api;

public class ApiHttpStatusException
extends Exception {
    private final String I;
    private final String w;
    private final int J;

    public String J() {
        return this.I;
    }

    public int m() {
        return this.J;
    }

    public String S() {
        return this.w;
    }

    public ApiHttpStatusException(String string, String string2, int n) {
        super("Failed making Online request, URL: " + string + ", Action: " + string2 + ", Error Code: " + n);
        this.I = string;
        this.w = string2;
        this.J = n;
    }
}

