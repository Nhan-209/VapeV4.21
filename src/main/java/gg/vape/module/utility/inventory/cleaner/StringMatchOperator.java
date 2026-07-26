package gg.vape.module.utility.inventory.cleaner;

import java.util.function.BiPredicate;

public enum StringMatchOperator {
    EQUALS(String::equals),
    STARTS(String::startsWith),
    ENDS(String::endsWith),
    ANY(StringMatchOperator::lambda$static$0);

    private static final StringMatchOperator[] o;
    private final BiPredicate<String, String> O;

    private static boolean lambda$static$0(String string, String string2) {
        return true;
    }

    private StringMatchOperator(BiPredicate<String, String> biPredicate) {
        this.O = biPredicate;
    }

    public BiPredicate<String, String> z() {
        return this.O;
    }

    static {
        String[] stringArray = new String[]{"EQUALS", "ENDS", "ANY", "STARTS"};




        o = new StringMatchOperator[]{EQUALS, STARTS, ENDS, ANY};
    }
}

