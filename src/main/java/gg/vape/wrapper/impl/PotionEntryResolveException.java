package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.PotionEntry;

public class PotionEntryResolveException
extends Exception {
    private final PotionEntry S;

    public PotionEntry O() {
        return this.S;
    }

    public PotionEntryResolveException(PotionEntry potionEntry) {
        this.S = potionEntry;
    }
}

