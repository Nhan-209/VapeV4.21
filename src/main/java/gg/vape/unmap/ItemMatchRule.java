package gg.vape.unmap;

import gg.vape.unmap.ItemMatchRuleConstructorMarker;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

class ItemMatchRule<T> {
    @Nullable
    private Class[] y;
    private Predicate<T> T;
    private final String[] z;

    private ItemMatchRule(String ... stringArray) {
        this.z = stringArray;
    }

    public Predicate<T> getPredicate() {
        return this.T;
    }

    public String[] getAliases() {
        return this.z;
    }

    ItemMatchRule(String[] stringArray, ItemMatchRuleConstructorMarker itemMatchRuleConstructorMarker) {
        this(stringArray);
    }

    @Nullable
    public Class[] getAcceptedClasses() {
        return this.y;
    }

    public void setPredicate(Predicate<T> predicate) {
        this.T = predicate;
    }

    public void setAcceptedClasses(Class[] classArray) {
        this.y = classArray;
    }
}

