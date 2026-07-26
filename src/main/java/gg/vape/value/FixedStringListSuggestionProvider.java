package gg.vape.value;

import gg.vape.value.AbstractListValueSuggestionProvider;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class FixedStringListSuggestionProvider
extends AbstractListValueSuggestionProvider {
    private List<String> B = new ArrayList<String>();

    public FixedStringListSuggestionProvider() {
        this.updateFilter("");
    }

    public void setValues(@NotNull List<String> list) {
        this.B = list;
        this.updateFilter("");
    }

    public FixedStringListSuggestionProvider(List<String> list) {
        this.B = list;
        this.updateFilter("");
    }

    @Override
    public List<String> getValues() {
        return this.B;
    }
}

