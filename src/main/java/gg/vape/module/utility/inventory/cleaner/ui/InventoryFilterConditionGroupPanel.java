package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.InventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterLogicalOperator;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterConditionEditor;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterLogicalOperatorDividerComponent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import org.jetbrains.annotations.Nullable;

class InventoryFilterConditionGroupPanel
extends PanelComponent {
    @Nullable
    private final InventoryFilterConditionGroup MJ;
    private final InventoryFilterPreset MR;
    private final InventoryFilterRule MH;
    private final Runnable ML;
    private final InventoryFilterConditionGroup Mb;

    public InventoryFilterConditionGroupPanel(double d, InventoryFilterRule inventoryFilterRule, InventoryFilterPreset inventoryFilterPreset, InventoryFilterConditionGroup inventoryFilterConditionGroup, @Nullable InventoryFilterConditionGroup inventoryFilterConditionGroup2, Runnable runnable) {
        super(d, 0.0);
        this.MH = inventoryFilterRule;
        this.MR = inventoryFilterPreset;
        this.Mb = inventoryFilterConditionGroup;
        this.MJ = inventoryFilterConditionGroup2;
        this.ML = runnable;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.d(false);
        this.N(false);
        this.t(Double.MAX_VALUE);
        this.p();
    }

    private void lambda$update$0() {
        if (this.Mb.c().isEmpty()) {
            this.MR.F(this.Mb);
            this.ML.run();
        } else {
            this.p();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double C() {
        return this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y();
    }

    public void p() {
        this.t$src$V$zbu1jn();
        if (this.MJ != null) {
            this.h(new SpacerComponent(8.0, 0.0), "widthwrap");
            this.h(new InventoryFilterLogicalOperatorDividerComponent(InventoryFilterLogicalOperator.OR), new Object[0]);
        }
        for (int i = 0; i < this.Mb.c().size(); ++i) {
            InventoryFilterCondition<?> inventoryFilterCondition = this.Mb.c().get(i);
            InventoryFilterConditionEditor inventoryFilterConditionEditor = new InventoryFilterConditionEditor(this.A() - 5.0, this.MH, this.Mb, inventoryFilterCondition, this::lambda$update$0);
            this.h(inventoryFilterConditionEditor, new Object[0]);
            if (i == this.Mb.c().size() - 1) continue;
            this.h(new SpacerComponent(8.0, 0.0), "widthwrap");
            this.h(new InventoryFilterLogicalOperatorDividerComponent(InventoryFilterLogicalOperator.AND), new Object[0]);
        }
    }
}

