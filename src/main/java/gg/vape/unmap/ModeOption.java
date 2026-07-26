package gg.vape.unmap;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeSelection;

public class ModeOption
extends ModeSelection {
    public ModeOption(String string) {
        this(string, 1.0);
    }

    public boolean o() {
        if (this.getMode() == null) {
            return false;
        }
        return ((ModeSelection)this.getMode().K()).equals(this);
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ModeOption(String string, double d) {
        super(string);
    }
}

