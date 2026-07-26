package gg.vape.account;

import gg.vape.account.AccountCredentials;

public class LicenseStatus
implements AccountCredentials {
    private final String k;
    private static final String b;
    private final String d;
    private final boolean v;
    private static String X;

    public static String S() {
        return X;
    }

    public boolean x() {
        return this.v;
    }

    public static void F(String string) {
        X = string;
    }

    @Override
    public String y() {
        return this.d;
    }

    static {
        LicenseStatus.F(null);
        b = "password";
    }

    public LicenseStatus(String string) {
        this.d = string;
        this.k = b;
        this.v = true;
    }

    public LicenseStatus(String string, String string2) {
        this.d = string;
        this.k = string2;
        this.v = false;
    }

    @Override
    public String U() {
        return this.k;
    }
}

