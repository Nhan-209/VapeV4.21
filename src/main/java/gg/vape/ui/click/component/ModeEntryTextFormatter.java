package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.OptionTextFormatter;
import gg.vape.unmap.ModeSelection;

public class ModeEntryTextFormatter<T extends ModeSelection>
implements OptionTextFormatter<T> {
    public static final OptionTextFormatter<ModeSelection> B = new ModeEntryTextFormatter<ModeSelection>();

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public String l(T t) {
        return t != null ? ((ModeSelection)t).toString() : "";
    }

    @Override
    public String I(T t) {
        return this.l(t);
    }
}
