package gg.vape.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.Value;
import gg.vape.value.ValueDisplayNameMode;

public class ValueDisplayDescriptor {
    private final Value<?, ?> C;
    private final String l;
    private final ValueDisplayNameMode U;

    private ValueDisplayDescriptor(Value<?, ?> value, String string, ValueDisplayNameMode valueDisplayNameMode) {
        this.C = value;
        this.l = string;
        this.U = valueDisplayNameMode;
    }

    public static ValueDisplayDescriptor p(Value<?, ?> value) {
        return new ValueDisplayDescriptor(value, null, ValueDisplayNameMode.FULL);
    }

    private static String Z(String string) {
        if (string == null || string.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        boolean bl = true;
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (c == ' ' || c == '-' || c == '_') {
                bl = true;
                continue;
            }
            if (i > 0 && Character.isUpperCase(c) && Character.isLowerCase(string.charAt(i - 1))) {
                bl = true;
            }
            if (!bl || !Character.isLetterOrDigit(c)) continue;
            stringBuilder.append(Character.toUpperCase(c));
            bl = false;
        }
        return stringBuilder.toString();
    }

    public String S() {
        switch (this.U) {
            case CUSTOM: {
                return this.l;
            }
            case SIMPLE: {
                return ValueDisplayDescriptor.Z(this.C.getName());
            }
        }
        return this.C.getName();
    }

    public static ValueDisplayDescriptor X(Value<?, ?> value) {
        return new ValueDisplayDescriptor(value, null, ValueDisplayNameMode.SIMPLE);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static ValueDisplayDescriptor o(Value<?, ?> value, String string) {
        return new ValueDisplayDescriptor(value, string, ValueDisplayNameMode.CUSTOM);
    }

    public String M$src$Ljava_lang_String_$1ohdx77() {
        return this.C.getName();
    }

    public Value<?, ?> M() {
        return this.C;
    }

    public boolean X() {
        if (this.U == ValueDisplayNameMode.FULL) {
            return false;
        }
        return this.S().length() < this.M$src$Ljava_lang_String_$1ohdx77().length();
    }
}

