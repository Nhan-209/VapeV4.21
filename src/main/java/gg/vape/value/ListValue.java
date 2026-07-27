package gg.vape.value;

import com.google.common.collect.ImmutableList;
import gg.vape.value.ListValueMutableBackingList;
import gg.vape.value.ListValueSuggestionProvider;
import gg.vape.value.Value;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public abstract class ListValue<C, T extends ListValue<C, T>>
extends Value<List<C>, T> {
    @Nullable
    private ListValueSuggestionProvider V;
    private final String K;


    public ListValue(Object object, String string, String string2) {
        this(object, string, string2, new ArrayList<C>());
    }

    public T i(ListValueSuggestionProvider listValueSuggestionProvider) {
        this.V = listValueSuggestionProvider;
        return (T)this;
    }

    public abstract C j(String var1, int var2);

    public List<C> h() {
        return new ArrayList<C>(this.K());
    }

    @Override
    public String c() {
        List<C> list = this.K();
        if (list.isEmpty()) {
            return "None";
        }
        if (list.size() == 1) {
            return list.get(0).toString();
        }
        return list.get(0).toString() + " +" + (list.size() - 1);
    }

    @Override
    public List<C> l() {
        return this.h();
    }

    @Nullable
    public ListValueSuggestionProvider o() {
        return this.V;
    }

    public ListValue(Object object, String string, String string2, List<C> list) {
        super(object, string, ImmutableList.copyOf(list));
        this.F(new ListValueMutableBackingList<C>(this, list));
        this.K = string2;
    }

    @Override
    public String getName() {
        return this.K;
    }
}
