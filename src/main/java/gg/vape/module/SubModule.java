package gg.vape.module;

import gg.vape.event.IEvent;
import gg.vape.module.Mod;
import gg.vape.value.SubModuleValue;
import java.util.function.Predicate;

public class SubModule<T extends Mod>
extends Mod {
    private static boolean a;
    private final SubModuleValue Y;
    private final Mod k;
    private boolean j;

    public boolean U() {
        return this.j;
    }

    @Override
    protected Predicate<IEvent> w() {
        return this::lambda$getEventPredicate$0;
    }

    static {
        if (!SubModule.y$src$Z$h2l5ue()) {
            SubModule.W(true);
        }
    }

    public SubModule(Mod mod, String string) {
        this(mod, string, true);
    }

    private boolean lambda$getEventPredicate$0(IEvent iEvent) {
        return this.r$src$Z$14eylz9() && this.J$src$Z$gcqtyf();
    }

    public static boolean y$src$Z$h2l5ue() {
        return a;
    }


    public T getParent() {
        return (T)this.k;
    }

    public SubModule(Mod mod, String string, boolean bl) {
        super(string);
        this.k = mod;
        this.j = bl;
        this.Y = new SubModuleValue<SubModule>(this);
    }

    public static boolean g$src$Z$gsov5w() {
        boolean bl = SubModule.y$src$Z$h2l5ue();
        return false;
    }

    public SubModuleValue r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx() {
        return this.Y;
    }

    public boolean J$src$Z$gcqtyf() {
        return this.Y.o();
    }

    public static void W(boolean bl) {
        a = bl;
    }

    public boolean G() {
        return super.r$src$Z$14eylz9();
    }
}

