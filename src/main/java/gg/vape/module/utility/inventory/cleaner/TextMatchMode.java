package gg.vape.module.utility.inventory.cleaner;

import gg.vape.unmap.INamed;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public enum TextMatchMode
implements INamed {
    EQUALS("equals"),
    DOES_NOT_EQUAL("does not equal"),
    MATCH_REGEX("matches regex"),
    DOES_NOT_MATCH_REGEX("does not match regex"),
    CONTAINS("contains"),
    DOES_NOT_CONTAIN("does not contain"),
    IS_IN("is in", true),
    IS_NOT_IN("is not in", true);

    public static final @UnmodifiableView List<TextMatchMode> VALUES;
    private final String name;
    private final boolean multiValue;
    private static final TextMatchMode[] valuesCache;

    public boolean M(String string, List<String> list) {
        for (String string2 : list) {
            if (!this.y(string, string2)) continue;
            return true;
        }
        return false;
    }

    public boolean y(String string, String string2) {
        string = string.toLowerCase();
        string2 = string2.toLowerCase();
        switch (this) {
            case EQUALS: {
                return string.equals(string2);
            }
            case DOES_NOT_EQUAL: {
                return !string.equals(string2);
            }
            case MATCH_REGEX: {
                return string.matches(string2);
            }
            case DOES_NOT_MATCH_REGEX: {
                return !string.matches(string2);
            }
            case CONTAINS: 
            case IS_IN: {
                return string.contains(string2);
            }
            case DOES_NOT_CONTAIN: 
            case IS_NOT_IN: {
                return !string.contains(string2);
            }
        }
        return false;
    }

    @Nullable
    public static TextMatchMode findByName(String string) {
        for (TextMatchMode textMatchMode : VALUES) {
            if (!textMatchMode.getName().equalsIgnoreCase(string)) continue;
            return textMatchMode;
        }
        return null;
    }


    static {
        String[] stringArray = new String[]{"DOES_NOT_CONTAIN", "is not in", "contains", "CONTAINS", "matches regex", "IS_NOT_IN", "MATCH_REGEX", "does not match regex", "DOES_NOT_MATCH_REGEX", "IS_IN", "does not contain", "is in", "EQUALS", "does not equal", "equals", "DOES_NOT_EQUAL"};








        valuesCache = new TextMatchMode[]{EQUALS, DOES_NOT_EQUAL, MATCH_REGEX, DOES_NOT_MATCH_REGEX, CONTAINS, DOES_NOT_CONTAIN, IS_IN, IS_NOT_IN};
        VALUES = Arrays.asList(TextMatchMode.values());
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean d() {
        return this.multiValue;
    }

    private TextMatchMode(String string2, boolean bl) {
        this.name = string2;
        this.multiValue = bl;
    }

    private TextMatchMode(String string2) {
        this(string2, false);
    }

    public static TextMatchMode p(String string) {
        return TextMatchMode.fromNameOrDefault(string, EQUALS);
    }

    public static TextMatchMode fromNameOrDefault(String string, TextMatchMode textMatchMode) {
        TextMatchMode textMatchMode2 = TextMatchMode.findByName(string);
        return textMatchMode2 == null ? textMatchMode : textMatchMode2;
    }
}

