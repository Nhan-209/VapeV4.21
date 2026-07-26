package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.HiddenInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfileValue;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.ItemInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.MaterialFilterCondition;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerPopupOutsideClickFilter;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerProfileEditContext;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterRuleEditorFrame;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterRuleEditorPanel;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterRuleListPanel;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterRuleRowBase;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemPickerPanel;
import gg.vape.module.utility.inventory.cleaner.ui.ItemPickerSelection;
import gg.vape.module.utility.inventory.cleaner.ui.SlotInventoryFilterRuleRow;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.click.component.FilledSpacerComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.input.SmallTextInputComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.AnchoredPopupFrame;
import gg.vape.ui.click.frame.DimmedCenteredPopupFrame;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.click.frame.ScrollableFrameComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import java.awt.Color;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import org.jetbrains.annotations.Nullable;

public class InventoryCleanerPopupFrame
extends Frame {
    private InventoryCleanerProfileEditContext xY;
    private InventoryFilterRuleListPanel xM;
    @Nullable
    private FrameStackManager xh;
    private static int[] xZ;
    private final ScrollableFrameComponent xC = new ScrollableFrameComponent(358.0, 171.0);

    public void t(InventoryCleanerProfileValue inventoryCleanerProfileValue, InventoryCleanerProfile inventoryCleanerProfile, Runnable runnable) {
        Object object;
        this.xY = new InventoryCleanerProfileEditContext(inventoryCleanerProfileValue, inventoryCleanerProfile, runnable);
        this.xC.S();
        PanelComponent panelComponent = new PanelComponent(this.xC.A() - 10.0, 80.0);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent.d(false);
        panelComponent.t(false);
        this.xC.h(new SpacerComponent(5.0, 2.0), new Object[0]);
        this.xC.h(panelComponent, "wrap");
        GuiComponent guiComponent = new SmallTextInputComponent("Inventory name...");
        ((TextInputComponentBase)guiComponent).o(1.0f);
        ((TextInputComponentBase)guiComponent).i(true);
        ((TextInputComponentBase)guiComponent).A(InventoryCleanerPopupFrame.J.h);
        ((TextInputComponentBase)guiComponent).k(inventoryCleanerProfile.Y());
        SmallTextInputComponent nameInput = (SmallTextInputComponent)guiComponent;
        guiComponent.o((arg_0, arg_1) -> InventoryCleanerPopupFrame.lambda$setManagedInventory$0(inventoryCleanerProfile, nameInput, arg_0, arg_1));
        panelComponent.h(guiComponent, new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 3.0), new Object[0]);
        panelComponent.h(new SpacerComponent(5.0, 0.0), "widthwrap");
        GuiComponent guiComponent2 = new SimpleTextLabelComponent("HOTBAR", 0.7);
        ((SimpleTextLabelComponent)guiComponent2).l(true);
        panelComponent.h(guiComponent2, new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        PanelComponent panelComponent2 = new PanelComponent(this.xC.A() - 5.0, 34.0);
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent2.d(false);
        panelComponent2.T(Color.MAGENTA);
        panelComponent.h(new SpacerComponent(5.0, 0.0), "widthwrap");
        panelComponent.h(panelComponent2, new Object[0]);
        for (int i = 0; i < 9; ++i) {
            object = inventoryCleanerProfile.I(i);
            SlotInventoryFilterRuleRow slotInventoryFilterRuleRow = new SlotInventoryFilterRuleRow(inventoryCleanerProfile, (SlotInventoryFilterRule)object);
            SlotInventoryFilterRule slotRule = (SlotInventoryFilterRule)object;
            slotInventoryFilterRuleRow.p(() -> this.lambda$setManagedInventory$1(slotRule, slotInventoryFilterRuleRow, inventoryCleanerProfile));
            panelComponent2.h(new PaddedComponent(1.0, 1.0, 2.0, 0.0, slotInventoryFilterRuleRow), new Object[0]);
        }
        this.xC.h(new SpacerComponent(1.0, 0.0), new Object[0]);
        this.xC.h(new FilledSpacerComponent(this.A() - 4.0, 1.0, new Color(255, 255, 255, 13)), "wrap");
        guiComponent = new ScrollableFrameComponent(this.xC.A(), 55.0);
        guiComponent.d(true);
        ((FrameComponent)guiComponent).T(InventoryCleanerPopupFrame.J.r);
        this.xC.h(guiComponent, "wrap");
        guiComponent2 = new ScrollableFrameComponent(this.xC.A() - 10.0, 45.0);
        ((FrameComponent)guiComponent2).l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        guiComponent2.d(false);
        ((FrameComponent)guiComponent).h(new SpacerComponent(5.0, 2.0), new Object[0]);
        ((FrameComponent)guiComponent).h(guiComponent2, "wrap");
        ((FrameComponent)guiComponent2).h(new SpacerComponent(0.0, 5.0), new Object[0]);
        ((FrameComponent)guiComponent2).h(new SpacerComponent(5.0, 0.0), "widthwrap");
        this.xM = new InventoryFilterRuleListPanel(this, (FrameComponent)guiComponent2, inventoryCleanerProfile);
        ((FrameComponent)guiComponent2).h(this.xM, new Object[0]);
        panelComponent2 = new PanelComponent(this.xC.A() - 10.0, 16.0);
        panelComponent2.d(false);
        this.xC.h(new PaddedComponent(3.0, 3.0, 10.0, 5.0, panelComponent2), "wrap");
        DropdownSelectComponent dropdownSelectComponent = new DropdownSelectComponent(inventoryCleanerProfile.n);
        dropdownSelectComponent.v(true);
        dropdownSelectComponent.C(0.0);
        panelComponent2.h(dropdownSelectComponent, new Object[0]);
        object = new TextLabel("Delete Inventory", 0.8);
        ((TextLabel)object).c(true);
        ((TextLabel)object).l(InventoryCleanerPopupFrame.J.d);
        ((GuiComponent)object).Y(12.0);
        ((GuiComponent)object).o(65.0);
        ((InteractiveComponent)object).r(() -> this.lambda$setManagedInventory$4(inventoryCleanerProfileValue, inventoryCleanerProfile, runnable));
        panelComponent2.h(new PaddedComponent(6.0, 0.0, 170.0, 0.0, (GuiComponent)object), new Object[0]);
        this.H(true);
    }

    private static void lambda$createAnyItemItemPickerPopup$7(MaterialFilterCondition materialFilterCondition, InventoryFilterRule inventoryFilterRule, InventoryFilterPreset inventoryFilterPreset, ItemPickerSelection itemPickerSelection) {
        if (materialFilterCondition.U().isEmpty()) {
            inventoryFilterRule.p(inventoryFilterPreset);
        }
        materialFilterCondition.X(itemPickerSelection);
    }

    private static void lambda$setManagedInventory$0(InventoryCleanerProfile inventoryCleanerProfile, SmallTextInputComponent smallTextInputComponent, char c, int n) {
        inventoryCleanerProfile.b(smallTextInputComponent.i$src$Ljava_lang_String_$1n2xf3k().trim());
    }

    public void d(@Nullable FrameStackManager frameStackManager) {
        this.xh = frameStackManager;
    }

    private void lambda$createItemFilterItemSelectorPopup$9(InventoryFilterRule inventoryFilterRule, InventoryCleanerProfile inventoryCleanerProfile, Runnable runnable, GuiComponent guiComponent, ItemPickerSelection itemPickerSelection) {
        if (inventoryFilterRule instanceof ItemInventoryFilterRule && itemPickerSelection == null) {
            ItemInventoryFilterRule itemInventoryFilterRule = (ItemInventoryFilterRule)inventoryFilterRule;
            inventoryCleanerProfile.U(itemInventoryFilterRule);
            this.xM.J(itemInventoryFilterRule);
        } else {
            inventoryFilterRule.q().G(itemPickerSelection);
            inventoryFilterRule.i(inventoryFilterRule.L());
            if (runnable != null) {
                runnable.run();
            }
            if (itemPickerSelection != null) {
                if (!HiddenInventoryItemMatchers.R.k().equals(itemPickerSelection.N())) {
                    this.Z(guiComponent, inventoryCleanerProfile, inventoryFilterRule, true);
                }
            } else {
                inventoryFilterRule.y();
            }
        }
        if (guiComponent instanceof InventoryFilterRuleRowBase) {
            ((InventoryFilterRuleRowBase)guiComponent).p();
        }
    }

    private void lambda$null$10(boolean bl, InventoryFilterRule inventoryFilterRule, InventoryCleanerProfile inventoryCleanerProfile, AtomicReference atomicReference) {
        if (!bl) {
            inventoryFilterRule.y();
            if (inventoryFilterRule instanceof ItemInventoryFilterRule) {
                inventoryCleanerProfile.U((ItemInventoryFilterRule)inventoryFilterRule);
                this.O(this.xY);
            }
        }
        ClientSettings.K((PopupFrame)atomicReference.get());
    }

    private void O(InventoryCleanerProfileEditContext inventoryCleanerProfileEditContext) {
        this.t(inventoryCleanerProfileEditContext.I, inventoryCleanerProfileEditContext.s, inventoryCleanerProfileEditContext.T);
    }

    private static void lambda$createAnyItemItemPickerPopup$8(MaterialFilterCondition materialFilterCondition, ItemPickerSelection itemPickerSelection) {
        ItemFilterSelection itemFilterSelection = materialFilterCondition.t(itemPickerSelection.N() != null ? (String)itemPickerSelection.N() : ((ItemMappingEntry)itemPickerSelection.X()).M());
        if (itemFilterSelection == null) {
            return;
        }
        materialFilterCondition.A(itemFilterSelection);
    }

    protected void M(GuiComponent guiComponent, InventoryCleanerProfile inventoryCleanerProfile, InventoryFilterRule inventoryFilterRule, @Nullable Runnable runnable) {
        this.Z(guiComponent, inventoryFilterRule, arg_0 -> this.lambda$createItemFilterItemSelectorPopup$9(inventoryFilterRule, inventoryCleanerProfile, runnable, guiComponent, arg_0));
    }

    public static void K(InventoryFilterRule inventoryFilterRule, InventoryFilterPreset inventoryFilterPreset, InventoryFilterPreset inventoryFilterPreset2, boolean bl) {
        InventoryCleanerPopupFrame inventoryCleanerPopupFrame = ClientSettings.g(InventoryCleanerPopupFrame.class);
        InventoryFilterRuleEditorFrame inventoryFilterRuleEditorFrame = ClientSettings.g(InventoryFilterRuleEditorFrame.class);
        inventoryFilterRuleEditorFrame.z(inventoryFilterRule, inventoryFilterPreset, inventoryFilterPreset2, bl, inventoryFilterRuleEditorFrame.V$src$Z$1xhop3l());
        if (inventoryCleanerPopupFrame.u$src$Lgg_vape_ui_click_frame_FrameStackManager_$12v9ioe() instanceof ClickGuiFrameManager) {
            ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)inventoryCleanerPopupFrame.u$src$Lgg_vape_ui_click_frame_FrameStackManager_$12v9ioe();
            inventoryCleanerPopupFrame.Z(false);
            clickGuiFrameManager.K(inventoryFilterRuleEditorFrame);
            return;
        }
        inventoryCleanerPopupFrame.Z(false);
        inventoryFilterRuleEditorFrame.Z(true);
    }

    private static void lambda$null$3(PopupFrame popupFrame) {
        ClientSettings.K(popupFrame);
    }

    public InventoryCleanerPopupFrame() {
        this.g(true);
        this.d(false);
        this.r(false);
        this.k(true);
        this.X(true);
        this.C$src$V$nadrmg();
        this.xC.C$src$V$nadrmg();
        this.xC.d(true);
        this.xC.T(InventoryCleanerPopupFrame.J.i);
        PanelComponent panelComponent = new PanelComponent(12.0, 12.0);
        GlyphIconComponent glyphIconComponent = new GlyphIconComponent("newclose", 8.0, 8.0, 10.0, 10.0, InventoryCleanerPopupFrame.J.h, InventoryCleanerPopupFrame.J.A, null);
        panelComponent.h(new PaddedComponent(1.0, glyphIconComponent), new Object[0]);
        panelComponent.d(false);
        this.xC.h(new SpacerComponent(0.0, 0.0), new Object[0]);
        this.xC.h(panelComponent, "alignright");
        for (GuiComponent guiComponent : this.xC.f()) {
            guiComponent.Q(false);
        }
        PaddedComponent paddedComponent = new PaddedComponent(1.0, 3.0, 1.0, 1.0, this.xC);
        paddedComponent.d(true);
        paddedComponent.r(false);
        paddedComponent.T(InventoryCleanerPopupFrame.J.y);
        this.h(paddedComponent, new Object[0]);
        glyphIconComponent.E(new Color(0, 0, 0, 0), new Color(255, 255, 255, 25));
        glyphIconComponent.i(5.0f);
        glyphIconComponent.R(true);
        glyphIconComponent.q(true);
        glyphIconComponent.r(this::w$src$V$109w4c3);
    }

    public void Z(GuiComponent guiComponent, InventoryCleanerProfile inventoryCleanerProfile, InventoryFilterRule inventoryFilterRule, boolean bl) {
        AtomicReference<AnchoredPopupFrame> atomicReference = new AtomicReference<AnchoredPopupFrame>();
        InventoryFilterRuleEditorPanel inventoryFilterRuleEditorPanel = new InventoryFilterRuleEditorPanel(inventoryCleanerProfile, inventoryFilterRule, bl);
        inventoryFilterRuleEditorPanel.t$src$Lgg_vape_ui_click_component_gui_TextButton_$7k1zw2().s(() -> this.lambda$createSlotEditPopup$11(bl, inventoryFilterRule, inventoryCleanerProfile, atomicReference));
        if (inventoryFilterRuleEditorPanel.d$src$Lgg_vape_ui_click_component_GlyphIconComponent_$k693uv() != null) {
            inventoryFilterRuleEditorPanel.d$src$Lgg_vape_ui_click_component_GlyphIconComponent_$k693uv().r(() -> this.lambda$createSlotEditPopup$12(inventoryFilterRuleEditorPanel, inventoryFilterRule, atomicReference, guiComponent, inventoryCleanerProfile));
        }
        AnchoredPopupFrame anchoredPopupFrame = ClientSettings.g(guiComponent, inventoryFilterRuleEditorPanel, AnchoredPopupFrame.class);
        atomicReference.set(anchoredPopupFrame);
        anchoredPopupFrame.O(false);
        anchoredPopupFrame.r(false);
        anchoredPopupFrame.q(this, anchoredPopupFrame);
    }

    private void lambda$null$2(InventoryCleanerProfileValue inventoryCleanerProfileValue, InventoryCleanerProfile inventoryCleanerProfile, Runnable runnable, PopupFrame popupFrame) {
        inventoryCleanerProfileValue.o(null);
        inventoryCleanerProfileValue.i(inventoryCleanerProfile);
        runnable.run();
        ClientSettings.K(popupFrame);
        this.w$src$V$109w4c3();
    }

    static {
        InventoryCleanerPopupFrame.B(null);
    }

    private void lambda$setManagedInventory$1(SlotInventoryFilterRule slotInventoryFilterRule, SlotInventoryFilterRuleRow slotInventoryFilterRuleRow, InventoryCleanerProfile inventoryCleanerProfile) {
        if (slotInventoryFilterRule.q().j()) {
            this.M(slotInventoryFilterRuleRow, inventoryCleanerProfile, slotInventoryFilterRule, null);
        } else {
            this.Z(slotInventoryFilterRuleRow, inventoryCleanerProfile, slotInventoryFilterRule, false);
        }
    }

    private void lambda$createItemSelecterPopup$5(AtomicReference atomicReference, Consumer consumer, GuiComponent guiComponent, InventoryFilterRule inventoryFilterRule, ItemPickerSelection itemPickerSelection) {
        ClientSettings.K((PopupFrame)atomicReference.get());
        consumer.accept(itemPickerSelection);
        if (itemPickerSelection != null && HiddenInventoryItemMatchers.R.k().equals(itemPickerSelection.N())) {
            this.p(guiComponent, inventoryFilterRule);
        }
    }

    private void p(GuiComponent guiComponent, InventoryFilterRule inventoryFilterRule) {
        MaterialFilterCondition materialFilterCondition = new MaterialFilterCondition();
        InventoryFilterPreset inventoryFilterPreset = new InventoryFilterPreset(inventoryFilterRule instanceof ItemInventoryFilterRule);
        inventoryFilterPreset.x(InventoryFilterConditionGroup.w().O(materialFilterCondition).w());
        InventoryItemPickerPanel inventoryItemPickerPanel = new InventoryItemPickerPanel(inventoryFilterRule, true, null, Collections.emptyList(), arg_0 -> InventoryCleanerPopupFrame.lambda$createAnyItemItemPickerPopup$7(materialFilterCondition, inventoryFilterRule, inventoryFilterPreset, arg_0));
        inventoryItemPickerPanel.R(arg_0 -> InventoryCleanerPopupFrame.lambda$createAnyItemItemPickerPopup$8(materialFilterCondition, arg_0));
        AnchoredPopupFrame anchoredPopupFrame = ClientSettings.g(guiComponent, inventoryItemPickerPanel, AnchoredPopupFrame.class);
        anchoredPopupFrame.O(false);
        anchoredPopupFrame.r(false);
        anchoredPopupFrame.q(this, anchoredPopupFrame);
    }

    @Nullable
    public FrameStackManager u$src$Lgg_vape_ui_click_frame_FrameStackManager_$12v9ioe() {
        return this.xh;
    }

    @Override
    public String getName() {
        return "Managed Inventory Editor";
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void w$src$V$109w4c3() {
        FrameStackManager frameStackManager;
        InventoryCleanerProfile inventoryCleanerProfile = this.xY.s;
        if (inventoryCleanerProfile.Y().trim().isEmpty()) {
            inventoryCleanerProfile.q();
        }
        if ((frameStackManager = this.xh) != null) {
            if (frameStackManager instanceof ClickGuiFrameManager) {
                ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)frameStackManager;
                clickGuiFrameManager.G();
            } else {
                ClientSettings.fW.I(frameStackManager);
            }
            this.xh = null;
        } else {
            ClientSettings.fW.I(ClientSettings.a);
        }
    }

    private static void lambda$createItemSelecterPopup$6(Consumer consumer) {
        consumer.accept(null);
    }

    private void lambda$createSlotEditPopup$12(InventoryFilterRuleEditorPanel inventoryFilterRuleEditorPanel, InventoryFilterRule inventoryFilterRule, AtomicReference atomicReference, GuiComponent guiComponent, InventoryCleanerProfile inventoryCleanerProfile) {
        if (inventoryFilterRuleEditorPanel.D$src$Z$uu5hd4()) {
            inventoryFilterRule.q().G(null);
        }
        ClientSettings.K((PopupFrame)atomicReference.get());
        this.M(guiComponent, inventoryCleanerProfile, inventoryFilterRule, null);
        if (guiComponent instanceof InventoryFilterRuleRowBase) {
            ((InventoryFilterRuleRowBase)guiComponent).p();
        }
    }

    private void Z(GuiComponent guiComponent, InventoryFilterRule inventoryFilterRule, Consumer<@Nullable ItemPickerSelection<String, ItemMappingEntry>> consumer) {
        AtomicReference<AnchoredPopupFrame> atomicReference = new AtomicReference<AnchoredPopupFrame>();
        InventoryItemPickerPanel inventoryItemPickerPanel = new InventoryItemPickerPanel(inventoryFilterRule, false, null, Collections.emptyList(), arg_0 -> this.lambda$createItemSelecterPopup$5(atomicReference, consumer, guiComponent, inventoryFilterRule, arg_0));
        AnchoredPopupFrame anchoredPopupFrame = ClientSettings.g(guiComponent, inventoryItemPickerPanel, AnchoredPopupFrame.class);
        atomicReference.set(anchoredPopupFrame);
        anchoredPopupFrame.z(() -> InventoryCleanerPopupFrame.lambda$createItemSelecterPopup$6(consumer));
        anchoredPopupFrame.O(false);
        anchoredPopupFrame.r(false);
        anchoredPopupFrame.q(this, anchoredPopupFrame);
    }

    public static int[] L$src$AI$12p19wq() {
        return xZ;
    }

    private void lambda$setManagedInventory$4(InventoryCleanerProfileValue inventoryCleanerProfileValue, InventoryCleanerProfile inventoryCleanerProfile, Runnable runnable) {
        ConfirmationDialogComponent confirmationDialogComponent = new ConfirmationDialogComponent("Are you sure you want to delete this inventory?", "REMOVE", "newtrash");
        DimmedCenteredPopupFrame dimmedCenteredPopupFrame = ClientSettings.g(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), confirmationDialogComponent, DimmedCenteredPopupFrame.class);
        dimmedCenteredPopupFrame.r(false);
        dimmedCenteredPopupFrame.j(new InventoryCleanerPopupOutsideClickFilter(this, dimmedCenteredPopupFrame));
        confirmationDialogComponent.T$src$Lgg_vape_ui_click_component_gui_TextButton_$17m2d4e().r(() -> this.lambda$null$2(inventoryCleanerProfileValue, inventoryCleanerProfile, runnable, dimmedCenteredPopupFrame));
        confirmationDialogComponent.E().r(() -> InventoryCleanerPopupFrame.lambda$null$3(dimmedCenteredPopupFrame));
    }

    private void lambda$createSlotEditPopup$11(boolean bl, InventoryFilterRule inventoryFilterRule, InventoryCleanerProfile inventoryCleanerProfile, AtomicReference atomicReference) {
        ClientSettings.f6.execute(() -> this.lambda$null$10(bl, inventoryFilterRule, inventoryCleanerProfile, atomicReference));
    }

    public static void B(int[] nArray) {
        xZ = nArray;
    }

    public static void Z$src$V$zty34m() {
        InventoryCleanerPopupFrame inventoryCleanerPopupFrame = ClientSettings.g(InventoryCleanerPopupFrame.class);
        InventoryFilterRuleEditorFrame inventoryFilterRuleEditorFrame = ClientSettings.g(InventoryFilterRuleEditorFrame.class);
        inventoryFilterRuleEditorFrame.Z(false);
        if (inventoryCleanerPopupFrame.u$src$Lgg_vape_ui_click_frame_FrameStackManager_$12v9ioe() instanceof ClickGuiFrameManager) {
            ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)inventoryCleanerPopupFrame.u$src$Lgg_vape_ui_click_frame_FrameStackManager_$12v9ioe();
            clickGuiFrameManager.K(inventoryCleanerPopupFrame);
            return;
        }
        inventoryCleanerPopupFrame.Z(true);
    }
}
