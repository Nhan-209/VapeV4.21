package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterLogicalOperator;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterConditionEditor;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterLogicalOperatorDividerComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import org.jetbrains.annotations.Nullable;

class InventoryFilterConditionGroupPanel
extends PanelComponent {
    @Nullable
    private final InventoryFilterConditionGroup previousGroup;
    private final InventoryFilterPreset preset;
    private final InventoryFilterRule rule;
    private final Runnable onChanged;
    private final InventoryFilterConditionGroup group;

    public InventoryFilterConditionGroupPanel(double d, InventoryFilterRule inventoryFilterRule, InventoryFilterPreset inventoryFilterPreset, InventoryFilterConditionGroup inventoryFilterConditionGroup, @Nullable InventoryFilterConditionGroup inventoryFilterConditionGroup2, Runnable runnable) {
        super(d, 0.0);
        this.rule = inventoryFilterRule;
        this.preset = inventoryFilterPreset;
        this.group = inventoryFilterConditionGroup;
        this.previousGroup = inventoryFilterConditionGroup2;
        this.onChanged = runnable;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.d(false);
        this.N(false);
        this.t(Double.MAX_VALUE);
        this.p();
    }

    private void onConditionRemoved() {
        if (this.group.c().isEmpty()) {
            this.preset.F(this.group);
            this.onChanged.run();
        } else {
            this.p();
        }
    }


    @Override
    public double C() {
        return this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y();
    }

    public void p() {
        this.t$src$V$zbu1jn();
        if (this.previousGroup != null) {
            this.h(new SpacerComponent(8.0, 0.0), "widthwrap");
            this.h(new InventoryFilterLogicalOperatorDividerComponent(InventoryFilterLogicalOperator.OR), new Object[0]);
        }
        for (int i = 0; i < this.group.c().size(); ++i) {
            InventoryFilterCondition<?> inventoryFilterCondition = this.group.c().get(i);
            InventoryFilterConditionEditor inventoryFilterConditionEditor = new InventoryFilterConditionEditor(this.A() - 5.0, this.rule, this.group, inventoryFilterCondition, this::onConditionRemoved);
            this.h(inventoryFilterConditionEditor, new Object[0]);
            if (i == this.group.c().size() - 1) continue;
            this.h(new SpacerComponent(8.0, 0.0), "widthwrap");
            this.h(new InventoryFilterLogicalOperatorDividerComponent(InventoryFilterLogicalOperator.AND), new Object[0]);
        }
    }
}

