package gg.vape.value;

import gg.vape.value.ListValueSuggestionProvider;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public abstract class AbstractListValueSuggestionProvider
implements ListValueSuggestionProvider {
    private static String R;
    @Nullable
    private Comparator<String> n = AbstractListValueSuggestionProvider::lambda$new$0;
    private List<String> E;
    private boolean C = false;

    @Override
    public @UnmodifiableView List<String> getSuggestions() {
        return this.E;
    }

    private static int lambda$new$0(String string, String string2) {
        return string.compareToIgnoreCase(string2);
    }

    @Override
    public boolean isIncludeAllWhenEmpty() {
        return this.C;
    }

    @Override
    @Nullable
    public Comparator<String> getComparator() {
        return this.n;
    }

    public static String S() {
        return R;
    }

    @Override
    public void setIncludeAllWhenEmpty(boolean bl) {
        this.C = bl;
    }


    @Override
    public void setComparator(@Nullable Comparator<String> comparator) {
        this.n = comparator;
    }

    private List<String> a(String string) {
        Comparator<String> comparator;
        ArrayList<String> arrayList = new ArrayList<String>();
        if (!this.isIncludeAllWhenEmpty() && string.isEmpty()) {
            return arrayList;
        }
        List<String> list = this.getValues();
        String string2 = string.toLowerCase();
        if (list != null) {
            for (String string3 : list) {
                if ((!this.isIncludeAllWhenEmpty() || !string2.isEmpty()) && !string3.toLowerCase().startsWith(string2)) continue;
                arrayList.add(string3);
            }
        }
        if ((comparator = this.n) != null) {
            arrayList.sort(comparator);
        }
        return arrayList;
    }

    public static void k(String string) {
        R = string;
    }

    @Override
    public void updateFilter(String string) {
        this.E = this.a(string);
    }

    public AbstractListValueSuggestionProvider() {
        this.updateFilter("");
    }

    static {
        if (AbstractListValueSuggestionProvider.S() == null) {
            AbstractListValueSuggestionProvider.k("Cvgij");
        }
    }
}

