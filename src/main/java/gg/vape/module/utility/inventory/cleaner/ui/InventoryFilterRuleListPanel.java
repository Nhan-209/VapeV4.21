package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.ItemInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerPopupFrame;
import gg.vape.module.utility.inventory.cleaner.ui.ItemInventoryFilterRuleRow;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.FilledSpacerComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TwoButtonConfirmationPopupComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.AnchoredPopupFrame;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.ScrollableFrameComponent;
import java.awt.Color;
import java.util.ArrayList;

public class InventoryFilterRuleListPanel
extends ScrollableFrameComponent {
    private final SimpleTextLabelComponent lH;
    private final InventoryCleanerProfile lt;
    private final InventoryCleanerPopupFrame l_;
    private final SimpleTextLabelComponent l1 = new SimpleTextLabelComponent("", 0.7);
    private final ScrollableFrameComponent lO;
    private final PanelComponent lz;
    private final TextLabel lQ;
    private final GlyphIconComponent lb;

    public void C(ItemInventoryFilterRule itemInventoryFilterRule) {
        this.lO.h(this.n(itemInventoryFilterRule), new Object[0]);
        this.n$src$V$b9ayit();
    }

    public InventoryFilterRuleListPanel(InventoryCleanerPopupFrame inventoryCleanerPopupFrame, FrameComponent frameComponent, InventoryCleanerProfile inventoryCleanerProfile) {
        super(frameComponent.A() - 5.0, 20.0);
        this.lH = new SimpleTextLabelComponent("", 0.7);
        this.lb = new GlyphIconComponent("newadd", 8.0, 8.0, 32.0, 32.0, InventoryFilterRuleListPanel.J.B, InventoryFilterRuleListPanel.J.O, InventoryFilterRuleListPanel.J.l);
        this.lQ = new TextLabel("Clear all", 0.8);
        this.l_ = inventoryCleanerPopupFrame;
        this.lt = inventoryCleanerProfile;
        this.lO = new ScrollableFrameComponent(this.A() - 8.0, 20.0);
        this.t(90.0);
        this.C$src$V$nadrmg();
        this.d(false);
        this.T(Color.MAGENTA);
        this.l1.f(() -> String.valueOf(inventoryCleanerProfile.Q().size()));
        this.l1.l(true);
        this.l1.T$src$V$1orl066(InventoryFilterRuleListPanel.J.A);
        this.l1.o(this.l1.h());
        this.l1.Y(8.0);
        this.h(this.l1, "widthwrap");
        this.lH.f(() -> "  INVENTORY FILTER" + (inventoryCleanerProfile.Q().size() == 1 ? "" : "S"));
        this.lH.l(true);
        this.lH.o(58.0);
        this.lH.Y(8.0);
        this.h(this.lH, "widthwrap");
        this.lz = new PanelComponent(50.0, 4.0);
        this.lz.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.lz.d(false);
        this.lz.h(new SpacerComponent(3.0, 0.0), new Object[0]);
        this.lz.h(new FilledSpacerComponent(1.0, 8.0, InventoryFilterRuleListPanel.J.y), new Object[0]);
        this.lz.h(new SpacerComponent(3.0, 0.0), new Object[0]);
        this.lQ.a(false);
        this.lQ.s(true);
        this.lQ.o(this.lQ.W() * 0.75);
        this.lQ.Y(8.0);
        this.lQ.r(() -> this.lambda$new$4(inventoryCleanerProfile));
        this.lz.h(this.lQ, new Object[0]);
        this.h(this.lz, "wrap");
        this.h(new SpacerComponent(0.0, 0.0), "wrap");
        this.h(new SpacerComponent(0.0, 5.0), "widthwrap");
        this.lO.d(false);
        this.lO.T(Color.BLUE);
        this.lO.t(81.0);
        this.h(this.lO, new Object[0]);
        this.lb.E(InventoryFilterRuleListPanel.J.r, InventoryFilterRuleListPanel.J.R);
        this.lb.i((float)(this.lb.A() / 2.0));
        this.lb.Z(1.0f);
        this.lb.q(true);
        this.lb.R(true);
        this.lb.Q(false);
        this.lb.k$src$V$qmpccm();
        this.lb.r(() -> this.lambda$new$6(inventoryCleanerProfile));
        PaddedComponent paddedComponent = new PaddedComponent(2.0, 1.0, 3.0, 2.0, this.lb);
        paddedComponent.Q(false);
        this.lO.h(paddedComponent, new Object[0]);
        for (ItemInventoryFilterRule itemInventoryFilterRule : inventoryCleanerProfile.Q()) {
            this.C(itemInventoryFilterRule);
        }
        this.n$src$V$b9ayit();
    }

    private void lambda$new$6(InventoryCleanerProfile inventoryCleanerProfile) {
        ItemInventoryFilterRule itemInventoryFilterRule = new ItemInventoryFilterRule();
        this.l_.M(this.lb, inventoryCleanerProfile, itemInventoryFilterRule, () -> this.lambda$null$5(inventoryCleanerProfile, itemInventoryFilterRule));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void n$src$V$b9ayit() {
        this.lz.Z(!this.lt.Q().isEmpty());
    }

    private void lambda$null$2(InventoryCleanerProfile inventoryCleanerProfile, AnchoredPopupFrame anchoredPopupFrame) {
        inventoryCleanerProfile.b();
        this.lO.S();
        ClientSettings.K(anchoredPopupFrame);
        this.n$src$V$b9ayit();
    }

    private void lambda$null$5(InventoryCleanerProfile inventoryCleanerProfile, ItemInventoryFilterRule itemInventoryFilterRule) {
        inventoryCleanerProfile.f(itemInventoryFilterRule);
        this.C(itemInventoryFilterRule);
    }

    private void lambda$createInventoryFilterNode$8(ItemInventoryFilterRule itemInventoryFilterRule) {
        this.lt.U(itemInventoryFilterRule);
        this.J(itemInventoryFilterRule);
    }

    private GuiComponent n(ItemInventoryFilterRule itemInventoryFilterRule) {
        ItemInventoryFilterRuleRow itemInventoryFilterRuleRow = new ItemInventoryFilterRuleRow(this.lt, itemInventoryFilterRule);
        itemInventoryFilterRuleRow.z(() -> this.lambda$createInventoryFilterNode$7(itemInventoryFilterRule, itemInventoryFilterRuleRow));
        PaddedComponent paddedComponent = new PaddedComponent(0.0, 4.0, 1.0, 0.0, itemInventoryFilterRuleRow);
        itemInventoryFilterRuleRow.v(() -> this.lambda$createInventoryFilterNode$8(itemInventoryFilterRule));
        return paddedComponent;
    }

    private void lambda$createInventoryFilterNode$7(ItemInventoryFilterRule itemInventoryFilterRule, ItemInventoryFilterRuleRow itemInventoryFilterRuleRow) {
        if (itemInventoryFilterRule.q().j()) {
            this.l_.M(itemInventoryFilterRuleRow, this.lt, itemInventoryFilterRule, null);
        } else {
            this.l_.Z(itemInventoryFilterRuleRow, this.lt, itemInventoryFilterRule, false);
        }
    }

    private void lambda$new$4(InventoryCleanerProfile inventoryCleanerProfile) {
        TwoButtonConfirmationPopupComponent twoButtonConfirmationPopupComponent = new TwoButtonConfirmationPopupComponent("Confirm clear all?", "YES");
        AnchoredPopupFrame anchoredPopupFrame = ClientSettings.g(this.lQ, twoButtonConfirmationPopupComponent, AnchoredPopupFrame.class);
        anchoredPopupFrame.m(4.0);
        anchoredPopupFrame.r(false);
        anchoredPopupFrame.q(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), anchoredPopupFrame);
        twoButtonConfirmationPopupComponent.N().r(() -> this.lambda$null$2(inventoryCleanerProfile, anchoredPopupFrame));
        twoButtonConfirmationPopupComponent.O$src$Lgg_vape_ui_click_component_gui_TextButton_$1fvjbh().r(() -> InventoryFilterRuleListPanel.lambda$null$3(anchoredPopupFrame));
    }

    public void J(ItemInventoryFilterRule itemInventoryFilterRule) {
        ArrayList<GuiComponent> arrayList = new ArrayList<GuiComponent>();
        for (GuiComponent guiComponent : this.lO.f()) {
            PaddedComponent paddedComponent;
            ItemInventoryFilterRuleRow itemInventoryFilterRuleRow;
            if (!(guiComponent instanceof PaddedComponent) || (itemInventoryFilterRuleRow = (paddedComponent = (PaddedComponent)guiComponent).t(ItemInventoryFilterRuleRow.class)) == null || itemInventoryFilterRuleRow.R() != itemInventoryFilterRule) continue;
            arrayList.add(guiComponent);
        }
        for (GuiComponent guiComponent : arrayList) {
            this.lO.I(guiComponent);
        }
        this.n$src$V$b9ayit();
    }

    private static void lambda$null$3(AnchoredPopupFrame anchoredPopupFrame) {
        ClientSettings.K(anchoredPopupFrame);
    }
}
