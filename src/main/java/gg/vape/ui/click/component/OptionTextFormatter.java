package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.INamed;
import org.jetbrains.annotations.Nullable;

public interface OptionTextFormatter<T> {
    public String I(T var1);

    public static OptionTextFormatter<String> N() {
        return OptionTextFormatter.c("");
    }

    public static OptionTextFormatter<String> c(@Nullable String string) {
        return arg_0 -> OptionTextFormatter.lambda$createStringFormatter$1(string, arg_0);
    }

    public static <N extends INamed> OptionTextFormatter<N> W(@Nullable String string) {
        return arg_0 -> OptionTextFormatter.lambda$createNamedFormatter$0(string, arg_0);
    }

    private static String lambda$createStringFormatter$1(String string, String string2) {
        return string2 != null ? string2 : string;
    }

    public static <N extends INamed> OptionTextFormatter<N> j() {
        return OptionTextFormatter.W("");
    }

    private static String lambda$createNamedFormatter$0(String string, INamed iNamed) {
        return iNamed != null ? iNamed.getName() : string;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

