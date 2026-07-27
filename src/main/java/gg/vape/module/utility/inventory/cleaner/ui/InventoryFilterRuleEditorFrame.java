package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.EmptyInventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterLogicalOperator;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRulePresetChange;
import gg.vape.module.utility.inventory.cleaner.ItemInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.SharedInventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerPopupFrame;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterConditionGroupPanel;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterRuleEditorPanel;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.FilledSpacerComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.PopupMenuButtonComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.input.SmallTextInputComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.click.frame.ScrollableFrameComponent;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class InventoryFilterRuleEditorFrame
extends Frame {
    private int lastScreenWidth = -1;
    private InventoryFilterRulePresetChange pendingChange;
    private ScrollableFrameComponent conditionsScroll;
    private final ScrollableFrameComponent contentScroll = new ScrollableFrameComponent(400.0, 230.0);
    private final PaddedComponent rootComponent;

    private void closePopup() {
        InventoryCleanerPopupFrame.Z$src$V$zty34m();
        InventoryCleanerPopupFrame inventoryCleanerPopupFrame = ClientSettings.g(InventoryCleanerPopupFrame.class);
        PopupFrame popupFrame = null;
        InventoryFilterRuleEditorPanel inventoryFilterRuleEditorPanel = null;
        Iterator<PopupFrame> popupFrames = inventoryCleanerPopupFrame.s$src$Ljava_util_ArrayList_$1a2240q().iterator();
        while (popupFrames.hasNext()) {
            popupFrame = popupFrames.next();
            inventoryFilterRuleEditorPanel = popupFrame.k(InventoryFilterRuleEditorPanel.class);
            if (inventoryFilterRuleEditorPanel == null) continue;
            break;
        }
        if (inventoryFilterRuleEditorPanel != null) {
            InventoryFilterRule inventoryFilterRule = inventoryFilterRuleEditorPanel.o$src$Lgg_vape_module_utility_inventory_cleaner_Invent$1md2c6w();
            InventoryFilterPreset inventoryFilterPreset = inventoryFilterRule.W();
            if (inventoryFilterPreset != null && inventoryFilterPreset.getName().trim().isEmpty()) {
                inventoryFilterPreset.I(inventoryFilterRule instanceof ItemInventoryFilterRule);
            }
            ClientSettings.K(popupFrame);
            inventoryCleanerPopupFrame.Z(popupFrame.V$src$Lgg_vape_ui_click_component_GuiComponent_$jpbobc(), inventoryFilterRuleEditorPanel.U$src$Lgg_vape_module_utility_inventory_cleaner_Invent$g2isi9(), (InventoryFilterRule)inventoryFilterRule, false);
        }
    }

    @Override
    public double L() {
        return this.rootComponent.L();
    }

    private void confirmCreatePreset(Runnable runnable) {
        ConfirmationDialogComponent.x(this, "Are you sure you want to convert this preset to a rule? This will allow you to apply this to other slots.", "Create Preset", "info", runnable, 95.0, "Cancel", null);
    }

    @Override
    public void c() {
        int n = Minecraft.h();
        if (n != this.lastScreenWidth) {
            this.lastScreenWidth = n;
            this.z(this.pendingChange.R(), this.pendingChange.L(), this.pendingChange.W(), this.pendingChange.q(), true);
        }
        super.c();
    }

    private void confirmUpdate(boolean bl, Runnable runnable, boolean bl2, Runnable runnable2) {
        if (bl) {
            ConfirmationDialogComponent.x(this, "Are you sure you want to update this preset? This will apply to all other slots that are utilizing this preset.", "Update Preset", "info", runnable, 95.0, "Cancel", null);
        } else if (bl2) {
            runnable2.run();
        } else {
            ConfirmationDialogComponent.x(this, "Are you sure you want to update this rule? This will only apply to this item slot.", "Update Rule", "info", runnable2, 95.0, "Cancel", null);
        }
    }

    public void z(InventoryFilterRule inventoryFilterRule, InventoryFilterPreset inventoryFilterPreset, InventoryFilterPreset inventoryFilterPreset2, boolean bl, boolean bl2) {
        InteractiveComponent interactiveComponent;
        this.pendingChange = new InventoryFilterRulePresetChange(inventoryFilterRule, inventoryFilterPreset, inventoryFilterPreset2, bl);
        Double d = bl2 && this.conditionsScroll != null ? Double.valueOf(this.conditionsScroll.J$src$D$hx1pag()) : null;
        this.contentScroll.C$src$V$nadrmg();
        this.contentScroll.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(null);
        this.contentScroll.d(true);
        this.contentScroll.t$src$V$zbu1jn();
        PanelComponent panelComponent = new PanelComponent(this.contentScroll.A(), 10.0);
        panelComponent.d(false);
        this.contentScroll.h(panelComponent, "wrap");
        FrameComponent frameComponent = new PanelComponent(10.0, 10.0);
        frameComponent.d(false);
        panelComponent.h(frameComponent, "alignright");
        GuiComponent guiComponent = new GlyphIconComponent("newclose", 8.0, 8.0, 8.0, 8.0, InventoryFilterRuleEditorFrame.J.h, InventoryFilterRuleEditorFrame.J.A, null);
        frameComponent.h(new PaddedComponent(1.0, guiComponent), new Object[0]);
        ((GlyphIconComponent)guiComponent).E(new Color(0, 0, 0, 0), new Color(255, 255, 255, 25));
        ((GlyphIconComponent)guiComponent).i(5.0f);
        ((GlyphIconComponent)guiComponent).R(true);
        ((GlyphIconComponent)guiComponent).q(true);
        ((InteractiveComponent)guiComponent).r(this::closePopup);
        frameComponent = new ScrollableFrameComponent(this.contentScroll.A() - 10.0, this.contentScroll.L() - 25.0);
        guiComponent = new PaddedComponent(5.0, frameComponent);
        guiComponent.d(false);
        ((PaddedComponent)guiComponent).C$src$V$nadrmg();
        this.contentScroll.h(guiComponent, new Object[0]);
        frameComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        frameComponent.d(false);
        frameComponent.C$src$V$nadrmg();
        PanelComponent panelComponent2 = new PanelComponent(frameComponent.A(), 20.0);
        frameComponent.h(panelComponent2, new Object[0]);
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent2.d(false);
        GuiComponent guiComponent2 = new SmallTextInputComponent("New filter name...");
        guiComponent2.C(0.0);
        ((TextInputComponentBase)guiComponent2).H(0.0f);
        ((TextInputComponentBase)guiComponent2).o(1.0f);
        ((TextInputComponentBase)guiComponent2).i(true);
        ((TextInputComponentBase)guiComponent2).A(InventoryFilterRuleEditorFrame.J.h);
        ((TextInputComponentBase)guiComponent2).k(inventoryFilterPreset2.getName());
        SmallTextInputComponent nameInput = (SmallTextInputComponent)guiComponent2;
        guiComponent2.o((arg_0, arg_1) -> InventoryFilterRuleEditorFrame.updatePresetName(inventoryFilterPreset2, nameInput, arg_0, arg_1));
        panelComponent2.h(guiComponent2, new Object[0]);
        frameComponent.h(new FilledSpacerComponent(frameComponent.A(), 1.0, InventoryFilterRuleEditorFrame.J.y), new Object[0]);
        guiComponent2 = new ScrollableFrameComponent(frameComponent.A(), 167.0);
        frameComponent.h(guiComponent2, new Object[0]);
        guiComponent2.d(false);
        ((FrameComponent)guiComponent2).C$src$V$nadrmg();
        ((FrameComponent)guiComponent2).l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        PanelComponent panelComponent3 = new PanelComponent(guiComponent2.A(), 15.0);
        ((FrameComponent)guiComponent2).h(panelComponent3, new Object[0]);
        panelComponent3.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent3.d(false);
        panelComponent3.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        GuiComponent guiComponent3 = new SimpleTextLabelComponent("If item matches...", 0.75, InventoryFilterRuleEditorFrame.J.A);
        ((SimpleTextLabelComponent)guiComponent3).c(0);
        ((SimpleTextLabelComponent)guiComponent3).l(true);
        panelComponent3.h(guiComponent3, new Object[0]);
        guiComponent3 = new ScrollableFrameComponent(guiComponent2.A(), guiComponent2.L() - panelComponent3.L());
        ((FrameComponent)guiComponent3).C$src$V$nadrmg();
        ((FrameComponent)guiComponent2).h(guiComponent3, new Object[0]);
        guiComponent3.d(false);
        this.conditionsScroll = new ScrollableFrameComponent(guiComponent3.A(), guiComponent3.L());
        ((FrameComponent)guiComponent3).h(this.conditionsScroll, new Object[0]);
        this.conditionsScroll.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.conditionsScroll.t((double)Minecraft.h() / 2.0 - 85.0);
        this.conditionsScroll.d(false);
        Object object2 = null;
        for (int i = 0; i < inventoryFilterPreset2.z().size(); ++i) {
            InventoryFilterConditionGroup conditionGroup = inventoryFilterPreset2.z().get(i);
            InventoryFilterConditionGroupPanel inventoryFilterConditionGroupPanel = new InventoryFilterConditionGroupPanel(this.conditionsScroll.A(), inventoryFilterRule, inventoryFilterPreset2, conditionGroup, (InventoryFilterConditionGroup)object2, () -> this.refreshRule(inventoryFilterRule, inventoryFilterPreset, inventoryFilterPreset2, bl));
            object2 = conditionGroup;
            this.conditionsScroll.h(inventoryFilterConditionGroupPanel, new Object[0]);
        }
        this.conditionsScroll.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        PanelComponent panelComponent4 = new PanelComponent(this.conditionsScroll.A(), 11.0);
        this.conditionsScroll.h(panelComponent4, new Object[0]);
        panelComponent4.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent4.d(false);
        panelComponent4.C$src$V$nadrmg();
        panelComponent4.h(new SpacerComponent(5.0, 0.0), new Object[0]);
        for (InventoryFilterLogicalOperator object3 : InventoryFilterLogicalOperator.values()) {
            interactiveComponent = new TextButton(object3.getName().toUpperCase(), 0.65, InventoryFilterRuleEditorFrame.J.B, InventoryFilterRuleEditorFrame.J.O, 58.0, 9.0);
            ((TextButton)interactiveComponent).F(false);
            ((TextButton)interactiveComponent).h(Color.WHITE);
            ((TextButton)interactiveComponent).m(1.0f);
            ((TextLabel)interactiveComponent).c(true);
            interactiveComponent.q(((TextLabel)interactiveComponent).W() + 10.0);
            interactiveComponent.s(() -> this.addConditionGroup(object3, inventoryFilterPreset2, inventoryFilterRule, inventoryFilterPreset, bl));
            PaddedComponent paddedComponent = new PaddedComponent(1.0, 0.0, 0.0, 3.0, interactiveComponent);
            paddedComponent.C$src$V$nadrmg();
            panelComponent4.h(paddedComponent, new Object[0]);
        }
        frameComponent.h(new FilledSpacerComponent(frameComponent.A(), 1.0, InventoryFilterRuleEditorFrame.J.y), new Object[0]);
        panelComponent3 = new PanelComponent(frameComponent.A(), 28.0);
        frameComponent.h(panelComponent3, new Object[0]);
        panelComponent3.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent3.d(false);
        panelComponent3.C$src$V$nadrmg();
        panelComponent3.h(new SpacerComponent(0.0, 6.0), "wrap");
        if (!bl && inventoryFilterPreset instanceof SharedInventoryFilterPreset) {
            guiComponent3 = new TextLabel("Delete Preset", 0.75, true);
            ((TextLabel)guiComponent3).l(InventoryFilterRuleEditorFrame.J.d);
            ((TextLabel)guiComponent3).c(true);
            guiComponent3.o(((TextLabel)guiComponent3).W());
            guiComponent3.Y(16.0);
            ((InteractiveComponent)guiComponent3).s(() -> this.promptDeletePreset(inventoryFilterPreset, inventoryFilterRule));
            panelComponent3.h(guiComponent3, new Object[0]);
        }
        guiComponent3 = new PanelComponent(86.0, panelComponent3.L() - 6.0);
        guiComponent3.d(false);
        ((FrameComponent)guiComponent3).C$src$V$nadrmg();
        panelComponent3.h(guiComponent3, "alignright");
        object2 = new TextLabel("Cancel", 0.75, true);
        ((TextLabel)object2).c(true);
        ((GuiComponent)object2).o(((TextLabel)object2).W());
        ((GuiComponent)object2).Y(16.0);
        ((InteractiveComponent)object2).s(this::closePopup);
        ((FrameComponent)guiComponent3).h(new PaddedComponent(0.0, 0.0, 0.0, 5.0, (GuiComponent)object2), new Object[0]);
        boolean bl3 = inventoryFilterPreset2 instanceof SharedInventoryFilterPreset;
        boolean bl4 = !bl3;
        Runnable runnable = () -> this.detachPreset(inventoryFilterPreset2, inventoryFilterRule);
        Runnable runnable2 = () -> this.applySharedPreset(bl4, inventoryFilterPreset2, inventoryFilterRule, inventoryFilterPreset);
        ArrayList<GuiComponent> arrayList = new ArrayList<GuiComponent>();
        if (bl3) {
            arrayList.add(new TextLabel("CREATE RULE", 0.75, false).B$src$Lgg_vape_ui_click_component_gui_TextLabel_$1bc29rb(true).l(Color.WHITE).c(true).r(() -> this.confirmCreateRule(runnable)));
        } else {
            arrayList.add(new TextLabel("CREATE PRESET", 0.75, false).B$src$Lgg_vape_ui_click_component_gui_TextLabel_$1bc29rb(true).l(Color.WHITE).c(true).r(() -> this.confirmCreatePreset(runnable2)));
        }
        interactiveComponent = new PopupMenuButtonComponent(bl3 ? "UPDATE PRESET" : (bl ? "CREATE RULE" : "UPDATE RULE"), arrayList, InventoryFilterRuleEditorFrame.J.B, InventoryFilterRuleEditorFrame.J.O, null, 1.0f, 1.0f);
        ((PopupMenuButtonComponent)interactiveComponent).l(true);
        interactiveComponent.o(58.0);
        interactiveComponent.Y(16.0);
        interactiveComponent.Y(panelComponent3.L());
        interactiveComponent.s(() -> this.confirmUpdate(bl3, runnable2, bl, runnable));
        ((FrameComponent)guiComponent3).h(new PaddedComponent(1.0, 0.0, interactiveComponent), new Object[0]);
        if (d != null) {
            this.conditionsScroll.W(d);
        }
        this.H(true);
    }

    @Override
    public double A() {
        return this.rootComponent.A();
    }

    private void confirmCreateRule(Runnable runnable) {
        ConfirmationDialogComponent.x(this, "Are you sure you want to convert to a rule? This will apply the presets ruless to this slot and allow for individual modification.", "Create Rule", "info", runnable, 100.0, "Cancel", null);
    }

    private void detachPreset(InventoryFilterPreset inventoryFilterPreset, InventoryFilterRule inventoryFilterRule) {
        InventoryFilterPreset inventoryFilterPreset2 = inventoryFilterPreset instanceof SharedInventoryFilterPreset ? new InventoryFilterPreset((SharedInventoryFilterPreset)inventoryFilterPreset) : inventoryFilterPreset;
        inventoryFilterRule.p(inventoryFilterPreset2);
        this.closePopup();
    }

    private static void updatePresetName(InventoryFilterPreset inventoryFilterPreset, SmallTextInputComponent smallTextInputComponent, char c, int n) {
        inventoryFilterPreset.J(smallTextInputComponent.i$src$Ljava_lang_String_$1n2xf3k().trim());
    }

    private void deletePresetConfirmed(InventoryFilterPreset inventoryFilterPreset, InventoryFilterRule inventoryFilterRule) {
        Vape.INSTANCE.getInventoryFilterPresetRegistry().Z((SharedInventoryFilterPreset)inventoryFilterPreset);
        if (inventoryFilterRule instanceof SlotInventoryFilterRule) {
            Vape.INSTANCE.getInventoryFilterPresetRegistry().g().r((SharedInventoryFilterPreset)inventoryFilterPreset);
        } else {
            Vape.INSTANCE.getInventoryFilterPresetRegistry().r().r((SharedInventoryFilterPreset)inventoryFilterPreset);
        }
        this.closePopup();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException exception) {
        return exception;
    }

    private void refreshRule(InventoryFilterRule inventoryFilterRule, InventoryFilterPreset inventoryFilterPreset, InventoryFilterPreset inventoryFilterPreset2, boolean bl) {
        this.z(inventoryFilterRule, inventoryFilterPreset, inventoryFilterPreset2, bl, true);
    }

    public InventoryFilterRuleEditorFrame() {
        this.Z(false);
        this.g(true);
        this.d(false);
        this.r(false);
        this.k(true);
        this.C$src$V$nadrmg();
        this.rootComponent = new PaddedComponent(1.0, 3.0, 1.0, 1.0, this.contentScroll);
        this.rootComponent.C$src$V$nadrmg();
        this.rootComponent.d(true);
        this.rootComponent.r(false);
        this.rootComponent.T(InventoryFilterRuleEditorFrame.J.y);
        this.h(this.rootComponent, new Object[0]);
    }

    private void applySharedPreset(boolean bl, InventoryFilterPreset inventoryFilterPreset, InventoryFilterRule inventoryFilterRule, InventoryFilterPreset inventoryFilterPreset2) {
        SharedInventoryFilterPreset sharedInventoryFilterPreset;
        SharedInventoryFilterPreset sharedInventoryFilterPreset2 = bl ? new SharedInventoryFilterPreset(inventoryFilterPreset) : (SharedInventoryFilterPreset)inventoryFilterPreset;
        boolean bl2 = inventoryFilterRule instanceof SlotInventoryFilterRule;
        inventoryFilterRule.p(sharedInventoryFilterPreset2);
        SharedInventoryFilterPreset sharedInventoryFilterPreset3 = sharedInventoryFilterPreset = inventoryFilterPreset2 instanceof SharedInventoryFilterPreset ? (SharedInventoryFilterPreset)inventoryFilterPreset2 : null;
        if (bl2) {
            Vape.INSTANCE.getInventoryFilterPresetRegistry().g().u(sharedInventoryFilterPreset, sharedInventoryFilterPreset2);
        } else {
            Vape.INSTANCE.getInventoryFilterPresetRegistry().r().u(sharedInventoryFilterPreset, sharedInventoryFilterPreset2);
        }
        this.closePopup();
    }

    private void promptDeletePreset(InventoryFilterPreset inventoryFilterPreset, InventoryFilterRule inventoryFilterRule) {
        ConfirmationDialogComponent.x(this, "Are you sure you want to delete this preset? This will remove it from all existing slots.", "Delete Preset", "delete", () -> this.deletePresetConfirmed(inventoryFilterPreset, inventoryFilterRule), 100.0, "Cancel", null);
    }

    @Override
    public String getName() {
        return "Rule Editor";
    }

    private void addConditionGroup(InventoryFilterLogicalOperator inventoryFilterLogicalOperator, InventoryFilterPreset inventoryFilterPreset, InventoryFilterRule inventoryFilterRule, InventoryFilterPreset inventoryFilterPreset2, boolean bl) {
        EmptyInventoryFilterCondition emptyInventoryFilterCondition = new EmptyInventoryFilterCondition();
        if (inventoryFilterLogicalOperator == InventoryFilterLogicalOperator.OR || inventoryFilterPreset.z().isEmpty()) {
            inventoryFilterPreset.x(InventoryFilterConditionGroup.w().O(emptyInventoryFilterCondition).w());
        } else if (inventoryFilterLogicalOperator == InventoryFilterLogicalOperator.AND) {
            InventoryFilterConditionGroup inventoryFilterConditionGroup = inventoryFilterPreset.z().get(inventoryFilterPreset.z().size() - 1);
            inventoryFilterConditionGroup.O(emptyInventoryFilterCondition);
        }
        this.z(inventoryFilterRule, inventoryFilterPreset2, inventoryFilterPreset, bl, true);
    }
}
