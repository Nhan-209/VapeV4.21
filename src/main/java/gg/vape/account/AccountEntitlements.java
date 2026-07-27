package gg.vape.account;

import gg.vape.account.AccountInfoResponse;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class AccountEntitlements {
    private boolean T;
    private final boolean e;
    private final boolean B;

    public boolean q() {
        return this.e;
    }

    public boolean M() {
        return this.T;
    }

    public void X(boolean bl) {
        this.T = bl;
    }

    public boolean O() {
        return this.B;
    }


    AccountEntitlements(boolean bl, boolean bl2, boolean bl3) {
        this.e = bl;
        this.T = bl2;
        this.B = bl3;
    }

    static AccountEntitlements t(AccountInfoResponse ig_02) {
        return AccountEntitlements.w(ig_02);
    }

    @Nullable
    @Contract(value="!null -> !null; null -> null")
    private static AccountEntitlements w(@Nullable AccountInfoResponse ig_02) {
        if (ig_02 == null) {
            return null;
        }
        return new AccountEntitlements(ig_02.e(), ig_02.Y(), ig_02.f());
    }
}

