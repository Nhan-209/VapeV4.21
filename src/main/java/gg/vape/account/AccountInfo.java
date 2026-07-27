package gg.vape.account;

import gg.vape.account.AccountEntitlements;
import gg.vape.account.AccountInfoResponse;
import java.util.Date;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AccountInfo {
    private final long J;
    private final Date x;
    private final boolean v;
    private final AccountEntitlements f;
    @Nullable
    private String I;


    public void y(@NotNull String string) {
        this.I = string;
    }

    public boolean r() {
        return this.v;
    }

    @Nullable
    public String h() {
        return this.I;
    }

    public long i() {
        return this.J;
    }

    public AccountEntitlements f() {
        return this.f;
    }

    public Date Z() {
        return this.x;
    }

    @Nullable
    @Contract(value="!null -> !null; null -> null")
    public static AccountInfo O(@Nullable AccountInfoResponse ig_02) {
        if (ig_02 == null) {
            return null;
        }
        return new AccountInfo(ig_02.z(), ig_02.S(), ig_02.M(), ig_02.W(), AccountEntitlements.t(ig_02));
    }

    AccountInfo(long l, @Nullable String string, Date date, boolean bl, AccountEntitlements accountEntitlements) {
        this.J = l;
        this.I = string;
        this.x = date;
        this.v = bl;
        this.f = accountEntitlements;
    }
}

