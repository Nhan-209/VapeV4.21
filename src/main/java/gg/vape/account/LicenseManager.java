package gg.vape.account;

import gg.vape.account.LicenseInfo;
import gg.vape.account.LicenseInfoClient;
import gg.vape.account.LicenseStatus;
import gg.vape.account.LicenseStatusClient;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class LicenseManager {
    private String D;
    private LicenseInfo M;
    private static int[] t;

    public static int[] X() {
        return t;
    }

    public LicenseInfo gg_vape_account_LicenseInfo_D() {
        return this.M;
    }

    public static void U(int[] nArray) {
        t = nArray;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public LicenseStatus a_vt_2_D() {
        if (this.D == null) {
            return null;
        }
        return new LicenseStatusClient().J(this.D);
    }

    public LicenseInfo I(String string) {
        this.M = new LicenseInfoClient().v(string);
        if (this.M != null) {
            this.D = string;
        }
        return this.M;
    }

    static {
        if (LicenseManager.X() == null) {
            LicenseManager.U(new int[1]);
        }
    }
}

