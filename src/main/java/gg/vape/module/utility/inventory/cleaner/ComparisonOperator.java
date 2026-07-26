package gg.vape.module.utility.inventory.cleaner;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.unmap.INamed;
import java.util.Arrays;
import java.util.List;
import gg.vape.module.utility.inventory.cleaner.DescribedOption;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public enum ComparisonOperator
implements INamed,
DescribedOption {
    EQUALS("equals", "=="),
    NOT_EQUAL("does not equal", "!="),
    GREATER_THAN("greater than", ">"),
    GREATHER_THAN_OR_EQUAL("greater or equal to", ">="),
    LESS_THAN("less than", "<"),
    LESS_THAN_OR_EQUAL("less or equal to", "<=");

    public static final @UnmodifiableView List<ComparisonOperator> VALUES;
    private static final ComparisonOperator[] C;
    private final String q;
    private static GuiComponent[] o;
    private final String T;

    @Override
    public String E() {
        return this.q;
    }

    public boolean q(short s, short s2) {
        switch (this) {
            case EQUALS: {
                return s == s2;
            }
            case NOT_EQUAL: {
                return s != s2;
            }
            case GREATER_THAN: {
                return s > s2;
            }
            case GREATHER_THAN_OR_EQUAL: {
                return s >= s2;
            }
            case LESS_THAN: {
                return s < s2;
            }
            case LESS_THAN_OR_EQUAL: {
                return s <= s2;
            }
        }
        return false;
    }

    public static ComparisonOperator Y(String string, ComparisonOperator comparisonOperator) {
        ComparisonOperator comparisonOperator2 = ComparisonOperator.K(string);
        return comparisonOperator2 == null ? comparisonOperator : comparisonOperator2;
    }

    public boolean p(int n, int n2) {
        switch (this) {
            case EQUALS: {
                return n == n2;
            }
            case NOT_EQUAL: {
                return n != n2;
            }
            case GREATER_THAN: {
                return n > n2;
            }
            case GREATHER_THAN_OR_EQUAL: {
                return n >= n2;
            }
            case LESS_THAN: {
                return n < n2;
            }
            case LESS_THAN_OR_EQUAL: {
                return n <= n2;
            }
        }
        return false;
    }

    public static GuiComponent[] v() {
        return o;
    }

    @Nullable
    public static ComparisonOperator K(String string) {
        for (ComparisonOperator comparisonOperator : VALUES) {
            if (!comparisonOperator.getName().equalsIgnoreCase(string)) continue;
            return comparisonOperator;
        }
        return null;
    }

    static {
        if (ComparisonOperator.v() == null) {
            ComparisonOperator.k(new GuiComponent[5]);
        }
        String[] stringArray = new String[]{"equals", "greater or equal to", "less or equal to", ">=", "NOT_EQUAL", "EQUALS", "==", "GREATER_THAN", "less than", "LESS_THAN_OR_EQUAL", "greater than", "<=", "GREATHER_THAN_OR_EQUAL", "!=", "LESS_THAN", "does not equal"};






        C = new ComparisonOperator[]{EQUALS, NOT_EQUAL, GREATER_THAN, GREATHER_THAN_OR_EQUAL, LESS_THAN, LESS_THAN_OR_EQUAL};
        VALUES = Arrays.asList(ComparisonOperator.values());
    }

    @Override
    public String getName() {
        return this.T;
    }

    public static void k(GuiComponent[] guiComponentArray) {
        o = guiComponentArray;
    }

    private ComparisonOperator(String string2, String string3) {
        this.T = string2;
        this.q = string3;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean A(double d, double d2) {
        switch (this) {
            case EQUALS: {
                return d == d2;
            }
            case NOT_EQUAL: {
                return d != d2;
            }
            case GREATER_THAN: {
                return d > d2;
            }
            case GREATHER_THAN_OR_EQUAL: {
                return d >= d2;
            }
            case LESS_THAN: {
                return d < d2;
            }
            case LESS_THAN_OR_EQUAL: {
                return d <= d2;
            }
        }
        return false;
    }

    public static ComparisonOperator a(String string) {
        return ComparisonOperator.Y(string, EQUALS);
    }

}

