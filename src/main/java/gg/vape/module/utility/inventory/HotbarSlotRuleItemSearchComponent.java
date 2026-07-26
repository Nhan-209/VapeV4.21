package gg.vape.module.utility.inventory;

import gg.vape.module.utility.inventory.HotbarSlotRuleItemPickerFrame;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemPickerSearchCloseClickHandler;
import gg.vape.module.utility.inventory.HotbarSlotRuleSearchInputKeyTypedListener;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.component.gui.TextButton;

public class HotbarSlotRuleItemSearchComponent
extends GuiComponent {
    private TextButton O;
    private LabeledTextInputComponent a = new LabeledTextInputComponent("Search Item Name");

    @Override
    public void F() {
    }

    @Override
    public void I() {
    }

    @Override
    public double x() {
        return 220.0;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public static LabeledTextInputComponent g(HotbarSlotRuleItemSearchComponent hotbarSlotRuleItemSearchComponent) {
        return hotbarSlotRuleItemSearchComponent.a;
    }

    public HotbarSlotRuleItemSearchComponent(HotbarSlotRuleItemPickerFrame hotbarSlotRuleItemPickerFrame) {
        this.O = new TextButton("Save & Exit", HotbarSlotRuleItemSearchComponent.J.B);
        this.a.o(new HotbarSlotRuleSearchInputKeyTypedListener(this, hotbarSlotRuleItemPickerFrame));
        this.O.r(new HotbarSlotRuleItemPickerSearchCloseClickHandler(this));
        this.H(this.a, this.O);
    }

    @Override
    public void H() {
        double d = 27.5;
        this.a.K(this.G$src$D$1b2f02a());
        this.a.S(this.n() + 20.0 - 5.0 - 2.5);
        this.a.o(d * 6.0 + 5.0);
        this.O.o(d * 2.0);
        this.O.Y(15.0);
        this.O.K(this.G$src$D$1b2f02a() + this.a.A());
        this.O.S(this.n() + 20.0 - 5.0);
    }

    @Override
    public void u() {
    }

    @Override
    public double C() {
        return 40.0;
    }
}

