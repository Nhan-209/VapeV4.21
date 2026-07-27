package gg.vape.module.utility.inventory.cleaner;

import gg.vape.unmap.INamed;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public enum MembershipMode
implements INamed {
    IS_IN("is in"),
    IS_NOT_IN("is not in");

    private final String label;
    private static final MembershipMode[] k;
    public static final @UnmodifiableView List<MembershipMode> VALUES;

    public boolean matchesAny(String string, List<String> list) {
        for (String string2 : list) {
            if (!this.matches(string, string2)) continue;
            return true;
        }
        return false;
    }

    public static MembershipMode N(String string) {
        return MembershipMode.fromNameOrDefault(string, IS_IN);
    }

    @Override
    public String getName() {
        return this.label;
    }

    private MembershipMode(String string2) {
        this.label = string2;
    }


    public boolean matches(String string, String string2) {
        string = string.toLowerCase();
        string2 = string2.toLowerCase();
        switch (this) {
            case IS_IN: {
                return string.contains(string2);
            }
            case IS_NOT_IN: {
                return !string.contains(string2);
            }
        }
        return false;
    }

    public static MembershipMode fromNameOrDefault(String string, MembershipMode membershipMode) {
        MembershipMode membershipMode2 = MembershipMode.fromName(string);
        return membershipMode2 == null ? membershipMode : membershipMode2;
    }

    static {
        String[] stringArray = new String[]{"is in", "IS_IN", "is not in", "IS_NOT_IN"};


        k = new MembershipMode[]{IS_IN, IS_NOT_IN};
        VALUES = Arrays.asList(MembershipMode.values());
    }

    @Nullable
    public static MembershipMode fromName(String string) {
        for (MembershipMode membershipMode : VALUES) {
            if (!membershipMode.getName().equalsIgnoreCase(string)) continue;
            return membershipMode;
        }
        return null;
    }
}

