package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.ClassInventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.ClassInventoryItemMatcherBuilderConstructorMarker;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherBuilderBase;
import gg.vape.module.utility.inventory.cleaner.InventoryMatcherListMode;
import java.util.ArrayList;
import java.util.List;

public class ClassInventoryItemMatcherBuilder
extends InventoryItemMatcherBuilderBase<ClassInventoryItemMatcherBuilder> {
    private InventoryMatcherListMode X;
    private final List<Class<?>> U = new ArrayList();

    public ClassInventoryItemMatcherBuilder(InventoryItemMatcherBuilderBase qq_12, ClassInventoryItemMatcherBuilderConstructorMarker q0_02) {
        this(qq_12);
    }

    public InventoryMatcherListMode C() {
        return this.X;
    }

    public ClassInventoryItemMatcherBuilder p(InventoryMatcherListMode bL) {
        this.X = bL;
        return this;
    }

    public ClassInventoryItemMatcherBuilder Q(Class<?> clazz) {
        this.U.add(clazz);
        return this;
    }

    public ClassInventoryItemMatcher o() {
        return new ClassInventoryItemMatcher(this);
    }

    public List<Class<?>> V() {
        return this.U;
    }

    private ClassInventoryItemMatcherBuilder(InventoryItemMatcherBuilderBase<?> qq_12) {
        super(qq_12);
    }
}

