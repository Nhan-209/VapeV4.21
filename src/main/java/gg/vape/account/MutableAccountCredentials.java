package gg.vape.account;

import gg.vape.account.AccountCredentials;

public class MutableAccountCredentials
implements AccountCredentials {
    private String q;
    private String o;
    private String S;
    private String L;

    public String T() {
        return this.o;
    }

    public void T(String string) {
        this.S = string;
    }

    public MutableAccountCredentials(String string, String string2) {
        this.L = string;
        this.q = string2;
    }

    @Override
    public String U() {
        return this.q;
    }

    public String F() {
        return this.S;
    }

    @Override
    public String y() {
        return this.L;
    }

    public void X(String string) {
        this.o = string;
    }
}

