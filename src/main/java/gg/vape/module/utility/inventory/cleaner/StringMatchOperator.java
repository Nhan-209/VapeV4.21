package gg.vape.module.utility.inventory.cleaner;

import java.util.function.BiPredicate;

public enum StringMatchOperator {
    EQUALS(String::equals),
    STARTS(String::startsWith),
    ENDS(String::endsWith),
    ANY(StringMatchOperator::matchAny);

    private static final StringMatchOperator[] VALUES;
    private final BiPredicate<String, String> predicate;

    private static boolean matchAny(String string, String string2) {
        return true;
    }

    private StringMatchOperator(BiPredicate<String, String> biPredicate) {
        this.predicate = biPredicate;
    }

    public BiPredicate<String, String> z() {
        return this.predicate;
    }

    static {
        String[] stringArray = new String[]{"EQUALS", "ENDS", "ANY", "STARTS"};




        VALUES = new StringMatchOperator[]{EQUALS, STARTS, ENDS, ANY};
    }
}

