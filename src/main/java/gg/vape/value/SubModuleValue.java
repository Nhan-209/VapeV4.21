package gg.vape.value;

import gg.vape.module.Mod;
import gg.vape.module.SubModule;
import gg.vape.unmap.ModeOption;

public class SubModuleValue<T extends SubModule>
extends ModeOption {
    private final T L;

    public SubModuleValue(String string, T t) {
        super(string);
        this.L = t;
    }

    public SubModuleValue(T t) {
        this(((Mod)t).getName(), t);
    }

    public T getInstance() {
        return this.L;
    }
}

