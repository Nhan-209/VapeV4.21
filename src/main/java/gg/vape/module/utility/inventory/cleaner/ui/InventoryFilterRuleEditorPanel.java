package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.Vape;
import gg.vape.module.utility.inventory.cleaner.EmptyInventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterAction;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPresetData;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategory;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategoryRegistry;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherPreset;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherPresetRegistry;
import gg.vape.module.utility.inventory.cleaner.ItemInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.SharedInventoryFilterPreset;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerPopupFrame;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryFilterPresetOptionComponent;
import gg.vape.module.utility.inventory.cleaner.ui.ItemFilterSelectionComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.PopupMenuButtonComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileCreateActionButtonComponent;
import gg.vape.wrapper.impl.ItemStack;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class InventoryFilterRuleEditorPanel
extends PanelComponent {
    private final PanelComponent kS;
    private final boolean kT;
    private final InventoryCleanerProfile kn;
    private final InventoryFilterRule kO;
    private TextButton kp;
    private static int ky;
    @Nullable
    private GlyphIconComponent kj;

    public InventoryCleanerProfile U$src$Lgg_vape_module_utility_inventory_cleaner_Invent$g2isi9() {
        return this.kn;
    }

    public boolean D$src$Z$uu5hd4() {
        return this.kT;
    }

    private void lambda$render$1() {
        this.kO.p(null);
        this.j$src$V$vf1nte();
    }

    private void lambda$render$0() {
        InventoryFilterPreset inventoryFilterPreset = new InventoryFilterPreset(!(this.kO instanceof SlotInventoryFilterRule));
        inventoryFilterPreset.x(InventoryFilterConditionGroup.w().O(new EmptyInventoryFilterCondition()).w());
        InventoryCleanerPopupFrame.K(this.kO, inventoryFilterPreset, inventoryFilterPreset, true);
    }

    private void lambda$render$2(InventoryFilterPreset inventoryFilterPreset) {
        InventoryCleanerPopupFrame.K(this.kO, inventoryFilterPreset, inventoryFilterPreset.s(), false);
    }

    public static void Y(int n) {
        ky = n;
    }

    private PanelComponent g(double d, String string) {
        Object object;
        PanelComponent panelComponent = new PanelComponent(d, 28.0);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent.d(false);
        panelComponent.h(new SpacerComponent(0.0, 10.0), new Object[0]);
        if (this.kT) {
            object = this.kj != null ? (!this.kj.l$src$Ljava_util_List_$7yhdmw().isEmpty() ? this.kj.l$src$Ljava_util_List_$7yhdmw().get(0) : null) : null;
            this.kj = new GlyphIconComponent("back-hover@2x", 6.0, 6.0, 10.0, 10.0, InventoryFilterRuleEditorPanel.J.W, InventoryFilterRuleEditorPanel.J.f, null);
            if (object != null) {
                this.kj.r((GuiClickListener)object);
            }
            this.kj.R(true);
            this.kj.q(true);
            panelComponent.h(new SpacerComponent(8.0, 0.0), "widthwrap");
            panelComponent.h(this.kj, "widthwrap");
        } else {
            panelComponent.h(new SpacerComponent(18.0, 0.0), "widthwrap");
        }
        object = new WrappingTextLabelComponent(string, 0.9, InventoryFilterRuleEditorPanel.J.Z);
        ((GuiComponent)object).o(panelComponent.A() - 36.0);
        ((SimpleTextLabelComponent)object).l(true);
        ((GuiComponent)object).S(false);
        panelComponent.h((GuiComponent)object, "widthwrap");
        return panelComponent;
    }

    private String T(InventoryFilterRule inventoryFilterRule) {
        ItemStack itemStack = inventoryFilterRule.q().E();
        InventoryItemMatcher inventoryItemMatcher = inventoryFilterRule.q().c();
        if (itemStack != null && !itemStack.isNull()) {
            return itemStack.x();
        }
        if (inventoryItemMatcher != null) {
            return inventoryItemMatcher.getName();
        }
        return "Unknown";
    }

    public static int c$src$I$vb73co() {
        return ky;
    }

    public InventoryFilterRule o$src$Lgg_vape_module_utility_inventory_cleaner_Invent$1md2c6w() {
        return this.kO;
    }

    private void lambda$render$4(InventoryFilterPreset inventoryFilterPreset) {
        InventoryCleanerPopupFrame.K(this.kO, inventoryFilterPreset, inventoryFilterPreset.s(), false);
    }

    public static int W() {
        int n = InventoryFilterRuleEditorPanel.c$src$I$vb73co();
        return 0;
    }

    private void lambda$render$8(ItemInventoryFilterRule itemInventoryFilterRule, InventoryFilterAction inventoryFilterAction) {
        itemInventoryFilterRule.S(inventoryFilterAction);
        this.j$src$V$vf1nte();
    }

    static {
        InventoryFilterRuleEditorPanel.Y(124);
    }

    @Nullable
    public GlyphIconComponent d$src$Lgg_vape_ui_click_component_GlyphIconComponent_$k693uv() {
        return this.kj;
    }

    public TextButton t$src$Lgg_vape_ui_click_component_gui_TextButton_$7k1zw2() {
        return this.kp;
    }

    private void j$src$V$vf1nte() {
        PopupMenuButtonComponent popupMenuButtonComponent;
        ArrayList<GuiComponent> arrayList;
        Object object;
        Object object2;
        this.kS.t$src$V$zbu1jn();
        String string = this.kO instanceof SlotInventoryFilterRule ? "Slot " + (((SlotInventoryFilterRule)this.kO).m() + 1) : "Filter";
        this.kS.h(this.g(this.A(), string), new Object[0]);
        this.kS.h(new SpacerComponent(this.A(), 0.0), new Object[0]);
        this.kS.h(new SpacerComponent(9.0, 0.0), "widthwrap");
        this.kS.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        PanelComponent panelComponent = new PanelComponent(32.0, 32.0);
        panelComponent.z(InventoryFilterRuleEditorPanel.J.y);
        panelComponent.R(0.5f);
        panelComponent.V(4.0f);
        GuiComponent guiComponent = new ItemFilterSelectionComponent(this.kO);
        guiComponent.o(32.0);
        guiComponent.Y(32.0);
        panelComponent.h(guiComponent, new Object[0]);
        this.kS.h(new SpacerComponent((this.A() - panelComponent.A()) / 2.0, 0.0), "widthwrap");
        this.kS.h(panelComponent, new Object[0]);
        this.kS.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        guiComponent = new WrappingTextLabelComponent(this.T(this.kO), 1.0, Color.WHITE);
        ((SimpleTextLabelComponent)guiComponent).l(true);
        guiComponent.o(this.kS.A());
        this.kS.h(guiComponent, new Object[0]);
        WrappingTextLabelComponent wrappingTextLabelComponent = new WrappingTextLabelComponent("ID: " + this.kO.q().J(), 0.75, InventoryFilterRuleEditorPanel.J.h);
        wrappingTextLabelComponent.l(true);
        wrappingTextLabelComponent.o(this.kS.A());
        wrappingTextLabelComponent.c(0);
        this.kS.h(wrappingTextLabelComponent, new Object[0]);
        this.kS.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("Item rule", 0.7, InventoryFilterRuleEditorPanel.J.A);
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.c(5);
        this.kS.h(new PaddedComponent(6.0, 0.0, simpleTextLabelComponent), new Object[0]);
        InventoryFilterPreset inventoryFilterPreset = this.kO.W();
        if (inventoryFilterPreset != null) {
            PopupMenuButtonComponent popupMenuButtonComponent2;
            ArrayList<GuiComponent> arrayList2;
            Object object3;
            Object object4;
            InteractiveComponent interactiveComponent;
            boolean bl = false;
            if (inventoryFilterPreset instanceof SharedInventoryFilterPreset) {
                bl = true;
            }
            ArrayList<InventoryFilterPreset> arrayList3 = new ArrayList<InventoryFilterPreset>(this.kO instanceof SlotInventoryFilterRule ? Vape.INSTANCE.getInventoryFilterPresetRegistry().g().M() : Vape.INSTANCE.getInventoryFilterPresetRegistry().r().M());
            arrayList3.remove(inventoryFilterPreset);
            arrayList3.add(0, inventoryFilterPreset);
            ArrayList<GuiComponent> arrayList4 = new ArrayList<GuiComponent>();
            if (bl) {
                interactiveComponent = new ProfileCreateActionButtonComponent("New Rule", false, true, 0.7, InventoryFilterRuleEditorPanel.J.A, "newadd", 0.75, InventoryFilterRuleEditorPanel.J.B, null);
                ((ProfileCreateActionButtonComponent)interactiveComponent).J(false);
                interactiveComponent.C(8.0);
                interactiveComponent.s(this::lambda$render$0);
                arrayList4.add(interactiveComponent);
                if (!arrayList3.isEmpty()) {
                    arrayList4.add(new SimpleTextLabelComponent("PRESETS", 0.7, InventoryFilterRuleEditorPanel.J.C, true));
                    InventoryItemMatcherPreset inventoryItemMatcherPreset = InventoryItemMatcherPresetRegistry.L;
                    InventoryFilterPresetOptionComponent matcherPresetOption = new InventoryFilterPresetOptionComponent(inventoryItemMatcherPreset, false);
                    matcherPresetOption.s(this::lambda$render$1);
                    arrayList4.add(matcherPresetOption);
                }
                for (InventoryFilterPreset preset : arrayList3) {
                    InventoryFilterPresetOptionComponent presetOption = new InventoryFilterPresetOptionComponent(preset, preset.equals(inventoryFilterPreset));
                    presetOption.m$src$Lgg_vape_ui_click_component_GlyphIconComponent_$1ecfqwu().s(() -> this.lambda$render$2(preset));
                    presetOption.s(() -> this.lambda$render$3(inventoryFilterPreset, preset));
                    arrayList4.add(presetOption);
                }
            } else {
                interactiveComponent = new ProfileCreateActionButtonComponent("Edit local rule", false, true, 0.7, InventoryFilterRuleEditorPanel.J.A, "newedit", 0.7, InventoryFilterRuleEditorPanel.J.B, null);
                ((ProfileCreateActionButtonComponent)interactiveComponent).J(false);
                interactiveComponent.s(() -> this.lambda$render$4(inventoryFilterPreset));
                object4 = new ProfileCreateActionButtonComponent("Delete local rule", false, true, 0.7, InventoryFilterRuleEditorPanel.J.A, "newtrash", 0.7, InventoryFilterRuleEditorPanel.J.B, null);
                ((ProfileCreateActionButtonComponent)object4).J(false);
                ((InteractiveComponent)object4).s(this::lambda$render$5);
                object3 = new ProfileCreateActionButtonComponent("Create preset", false, true, 0.7, InventoryFilterRuleEditorPanel.J.A, "newadd", 0.7, InventoryFilterRuleEditorPanel.J.B, null);
                ((ProfileCreateActionButtonComponent)object3).J(false);
                ((InteractiveComponent)object3).s(() -> this.lambda$render$6(inventoryFilterPreset));
                arrayList4.add(interactiveComponent);
                arrayList4.add((GuiComponent)object4);
                arrayList4.add((GuiComponent)object3);
            }
            PopupMenuButtonComponent presetPopup = new PopupMenuButtonComponent(inventoryFilterPreset.getName(), arrayList4, InventoryFilterRuleEditorPanel.J.Q, InventoryFilterRuleEditorPanel.J.Q, null, 0.0f, 0.0f);
            presetPopup.o(88.0);
            presetPopup.Y(14.0);
            presetPopup.c(false);
            presetPopup.z(false);
            presetPopup.e(false);
            presetPopup.r((Color)null);
            this.kS.h(new SpacerComponent((this.A() - presetPopup.A()) / 2.0, 0.0), "widthwrap");
            this.kS.h(presetPopup, "widthwrap");
            this.kS.h(new SpacerComponent(0.0, 18.0), new Object[0]);
            if (this.kO instanceof SlotInventoryFilterRule) {
                InventoryItemCategory selectedCategory = this.kO.o();
                List<InventoryItemCategory> categories = InventoryItemCategoryRegistry.Q(this.kO.q());
                ArrayList<GuiComponent> categoryButtons = new ArrayList<>();
                for (InventoryItemCategory category : categories) {
                    TextButton textButton = new TextButton(category.getName(), 0.75, InventoryFilterRuleEditorPanel.J.t, InventoryFilterRuleEditorPanel.J.M, 0.0, 0.0);
                    textButton.w(category.r());
                    textButton.F(false);
                    textButton.h(InventoryFilterRuleEditorPanel.J.A);
                    textButton.m(0.0f);
                    categoryButtons.add(textButton);
                    textButton.r(() -> this.lambda$render$7(category));
                }
                PopupMenuButtonComponent categoryPopup = new PopupMenuButtonComponent(selectedCategory.getName(), categoryButtons, InventoryFilterRuleEditorPanel.J.Q, InventoryFilterRuleEditorPanel.J.Q, null, 0.0f, 0.0f);
                categoryPopup.o(88.0);
                categoryPopup.Y(14.0);
                categoryPopup.c(false);
                categoryPopup.z(false);
                categoryPopup.e(false);
                categoryPopup.r((Color)null);
                SimpleTextLabelComponent object72 = new SimpleTextLabelComponent("Prioritization", 0.7, InventoryFilterRuleEditorPanel.J.A);
                object72.l(true);
                object72.c(5);
                this.kS.h(new PaddedComponent(6.0, 0.0, object72), new Object[0]);
                this.kS.h(new SpacerComponent((this.A() - categoryPopup.A()) / 2.0, 0.0), "widthwrap");
                this.kS.h(categoryPopup, new Object[0]);
            } else if (this.kO instanceof ItemInventoryFilterRule) {
                object4 = (ItemInventoryFilterRule)this.kO;
                object3 = ((ItemInventoryFilterRule)object4).K();
                ItemInventoryFilterRule itemRule = (ItemInventoryFilterRule)object4;
                arrayList2 = new ArrayList<GuiComponent>();
                for (InventoryFilterAction object9 : InventoryFilterAction.VALUES) {
                    TextButton textButton = new TextButton(object9.getName(), 0.75, InventoryFilterRuleEditorPanel.J.t, InventoryFilterRuleEditorPanel.J.M, 0.0, 0.0);
                    textButton.w(object9.E());
                    textButton.F(false);
                    textButton.h(InventoryFilterRuleEditorPanel.J.A);
                    textButton.m(0.0f);
                    arrayList2.add(textButton);
                    textButton.r(() -> this.lambda$render$8(itemRule, object9));
                }
                PopupMenuButtonComponent popupMenuButtonComponent5 = new PopupMenuButtonComponent(((InventoryFilterAction)object3).getName(), arrayList2, InventoryFilterRuleEditorPanel.J.Q, InventoryFilterRuleEditorPanel.J.Q, null, 0.0f, 0.0f);
                popupMenuButtonComponent5.o(88.0);
                popupMenuButtonComponent5.Y(14.0);
                popupMenuButtonComponent5.c(false);
                popupMenuButtonComponent5.z(false);
                popupMenuButtonComponent5.e(false);
                popupMenuButtonComponent5.r((Color)null);
                SimpleTextLabelComponent object112 = new SimpleTextLabelComponent("Action", 0.7, InventoryFilterRuleEditorPanel.J.A);
                object112.l(true);
                object112.c(5);
                this.kS.h(new PaddedComponent(6.0, 0.0, object112), new Object[0]);
                this.kS.h(new SpacerComponent((this.A() - popupMenuButtonComponent5.A()) / 2.0, 0.0), "widthwrap");
                this.kS.h(popupMenuButtonComponent5, new Object[0]);
            } else {
                this.kS.h(new SpacerComponent(0.0, 25.0), new Object[0]);
            }
            this.kS.h(new SpacerComponent(0.0, 8.0), new Object[0]);
            Object object5 = object4 = this.kT ? "CONFIRM" : "REMOVE";
            object3 = this.kp != null ? (!this.kp.l$src$Ljava_util_List_$7yhdmw().isEmpty() ? this.kp.l$src$Ljava_util_List_$7yhdmw().get(0) : null) : null;
            this.kp = new TextButton((String)object4, 0.7, this.kT ? InventoryFilterRuleEditorPanel.J.B : InventoryFilterRuleEditorPanel.J.d, this.kT ? InventoryFilterRuleEditorPanel.J.O : InventoryFilterRuleEditorPanel.J.c);
            if (object3 != null) {
                this.kp.r((GuiClickListener)object3);
            }
            this.kp.o(this.kT ? 56.0 : 68.0);
            this.kp.Y(16.0);
            this.kp.h(Color.WHITE);
            this.kp.F(false);
            this.kS.h(new SpacerComponent((this.A() - this.kp.A()) / 2.0, 0.0), "widthwrap");
            this.kS.h(this.kp, new Object[0]);
            return;
        }
        boolean bl = false;
        bl = true;
        ArrayList<InventoryFilterPreset> arrayList5 = new ArrayList<InventoryFilterPreset>(this.kO instanceof SlotInventoryFilterRule ? Vape.INSTANCE.getInventoryFilterPresetRegistry().g().M() : Vape.INSTANCE.getInventoryFilterPresetRegistry().r().M());
        ArrayList<GuiComponent> arrayList6 = new ArrayList<GuiComponent>();
        InteractiveComponent interactiveComponent = new ProfileCreateActionButtonComponent("New Rule", false, true, 0.7, InventoryFilterRuleEditorPanel.J.A, "newadd", 0.75, InventoryFilterRuleEditorPanel.J.B, null);
        ((ProfileCreateActionButtonComponent)interactiveComponent).J(false);
        interactiveComponent.C(8.0);
        interactiveComponent.s(this::lambda$render$0);
        arrayList6.add(interactiveComponent);
        if (!arrayList5.isEmpty()) {
            arrayList6.add(new SimpleTextLabelComponent("PRESETS", 0.7, InventoryFilterRuleEditorPanel.J.C, true));
            InventoryItemMatcherPreset inventoryItemMatcherPreset = InventoryItemMatcherPresetRegistry.L;
            InventoryFilterPresetOptionComponent matcherPresetOption = new InventoryFilterPresetOptionComponent(inventoryItemMatcherPreset, true);
            matcherPresetOption.s(this::lambda$render$1);
            arrayList6.add(matcherPresetOption);
        }
        for (InventoryFilterPreset preset : arrayList5) {
            InventoryFilterPresetOptionComponent presetOption = new InventoryFilterPresetOptionComponent(preset, preset.equals(inventoryFilterPreset));
            presetOption.m$src$Lgg_vape_ui_click_component_GlyphIconComponent_$1ecfqwu().s(() -> this.lambda$render$2(preset));
            presetOption.s(() -> this.lambda$render$3(inventoryFilterPreset, preset));
            arrayList6.add(presetOption);
        }
        PopupMenuButtonComponent presetPopup = new PopupMenuButtonComponent("No rule", arrayList6, InventoryFilterRuleEditorPanel.J.Q, InventoryFilterRuleEditorPanel.J.Q, null, 0.0f, 0.0f);
        presetPopup.o(88.0);
        presetPopup.Y(14.0);
        presetPopup.c(false);
        presetPopup.z(false);
        presetPopup.e(false);
        presetPopup.r((Color)null);
        this.kS.h(new SpacerComponent((this.A() - presetPopup.A()) / 2.0, 0.0), "widthwrap");
        this.kS.h(presetPopup, "widthwrap");
        this.kS.h(new SpacerComponent(0.0, 18.0), new Object[0]);
        if (this.kO instanceof SlotInventoryFilterRule) {
            InventoryItemCategory selectedCategory = this.kO.o();
            List<InventoryItemCategory> categories = InventoryItemCategoryRegistry.Q(this.kO.q());
            ArrayList<GuiComponent> categoryButtons = new ArrayList<>();
            for (InventoryItemCategory inventoryItemCategory : categories) {
                TextButton textButton = new TextButton(inventoryItemCategory.getName(), 0.75, InventoryFilterRuleEditorPanel.J.t, InventoryFilterRuleEditorPanel.J.M, 0.0, 0.0);
                textButton.w(inventoryItemCategory.r());
                textButton.F(false);
                textButton.h(InventoryFilterRuleEditorPanel.J.A);
                textButton.m(0.0f);
                categoryButtons.add(textButton);
                textButton.r(() -> this.lambda$render$7(inventoryItemCategory));
            }
            PopupMenuButtonComponent categoryPopup = new PopupMenuButtonComponent(selectedCategory.getName(), categoryButtons, InventoryFilterRuleEditorPanel.J.Q, InventoryFilterRuleEditorPanel.J.Q, null, 0.0f, 0.0f);
            categoryPopup.o(88.0);
            categoryPopup.Y(14.0);
            categoryPopup.c(false);
            categoryPopup.z(false);
            categoryPopup.e(false);
            categoryPopup.r((Color)null);
            SimpleTextLabelComponent simpleTextLabelComponent2 = new SimpleTextLabelComponent("Prioritization", 0.7, InventoryFilterRuleEditorPanel.J.A);
            simpleTextLabelComponent2.l(true);
            simpleTextLabelComponent2.c(5);
            this.kS.h(new PaddedComponent(6.0, 0.0, simpleTextLabelComponent2), new Object[0]);
            this.kS.h(new SpacerComponent((this.A() - categoryPopup.A()) / 2.0, 0.0), "widthwrap");
            this.kS.h(categoryPopup, new Object[0]);
        } else if (this.kO instanceof ItemInventoryFilterRule) {
            object2 = (ItemInventoryFilterRule)this.kO;
            object = ((ItemInventoryFilterRule)object2).K();
            ItemInventoryFilterRule itemRule = (ItemInventoryFilterRule)object2;
            arrayList = new ArrayList<GuiComponent>();
            for (InventoryFilterAction inventoryFilterAction : InventoryFilterAction.VALUES) {
                TextButton textButton = new TextButton(inventoryFilterAction.getName(), 0.75, InventoryFilterRuleEditorPanel.J.t, InventoryFilterRuleEditorPanel.J.M, 0.0, 0.0);
                textButton.w(inventoryFilterAction.E());
                textButton.F(false);
                textButton.h(InventoryFilterRuleEditorPanel.J.A);
                textButton.m(0.0f);
                arrayList.add(textButton);
                textButton.r(() -> this.lambda$render$8(itemRule, inventoryFilterAction));
            }
            PopupMenuButtonComponent popupMenuButtonComponent8 = new PopupMenuButtonComponent(((InventoryFilterAction)object).getName(), arrayList, InventoryFilterRuleEditorPanel.J.Q, InventoryFilterRuleEditorPanel.J.Q, null, 0.0f, 0.0f);
            popupMenuButtonComponent8.o(88.0);
            popupMenuButtonComponent8.Y(14.0);
            popupMenuButtonComponent8.c(false);
            popupMenuButtonComponent8.z(false);
            popupMenuButtonComponent8.e(false);
            popupMenuButtonComponent8.r((Color)null);
            SimpleTextLabelComponent simpleTextLabelComponent3 = new SimpleTextLabelComponent("Action", 0.7, InventoryFilterRuleEditorPanel.J.A);
            simpleTextLabelComponent3.l(true);
            simpleTextLabelComponent3.c(5);
            this.kS.h(new PaddedComponent(6.0, 0.0, simpleTextLabelComponent3), new Object[0]);
            this.kS.h(new SpacerComponent((this.A() - popupMenuButtonComponent8.A()) / 2.0, 0.0), "widthwrap");
            this.kS.h(popupMenuButtonComponent8, new Object[0]);
        } else {
            this.kS.h(new SpacerComponent(0.0, 25.0), new Object[0]);
        }
        this.kS.h(new SpacerComponent(0.0, 8.0), new Object[0]);
        Object object6 = object2 = this.kT ? "CONFIRM" : "REMOVE";
        object = this.kp != null ? (!this.kp.l$src$Ljava_util_List_$7yhdmw().isEmpty() ? this.kp.l$src$Ljava_util_List_$7yhdmw().get(0) : null) : null;
        this.kp = new TextButton((String)object2, 0.7, this.kT ? InventoryFilterRuleEditorPanel.J.B : InventoryFilterRuleEditorPanel.J.d, this.kT ? InventoryFilterRuleEditorPanel.J.O : InventoryFilterRuleEditorPanel.J.c);
        if (object != null) {
            this.kp.r((GuiClickListener)object);
        }
        this.kp.o(this.kT ? 56.0 : 68.0);
        this.kp.Y(16.0);
        this.kp.h(Color.WHITE);
        this.kp.F(false);
        this.kS.h(new SpacerComponent((this.A() - this.kp.A()) / 2.0, 0.0), "widthwrap");
        this.kS.h(this.kp, new Object[0]);
    }

    private void lambda$render$3(InventoryFilterPreset inventoryFilterPreset, InventoryFilterPreset inventoryFilterPreset2) {
        if (inventoryFilterPreset != null && inventoryFilterPreset.equals(inventoryFilterPreset2)) {
            InventoryCleanerPopupFrame.K(this.kO, inventoryFilterPreset2, inventoryFilterPreset2.s(), false);
            return;
        }
        this.kO.p(inventoryFilterPreset2);
        if (this.kO.W() != null) {
            this.j$src$V$vf1nte();
        }
    }

    private void lambda$render$6(InventoryFilterPreset inventoryFilterPreset) {
        SharedInventoryFilterPreset sharedInventoryFilterPreset = inventoryFilterPreset.o(this.kO);
        this.kO.p(sharedInventoryFilterPreset);
        this.j$src$V$vf1nte();
    }

    private void lambda$render$5() {
        this.kO.p(null);
        this.j$src$V$vf1nte();
    }

    public InventoryFilterRuleEditorPanel(InventoryCleanerProfile inventoryCleanerProfile, InventoryFilterRule inventoryFilterRule, boolean bl) {
        super(108.0, 182.0);
        this.kn = inventoryCleanerProfile;
        this.kO = inventoryFilterRule;
        this.kT = bl;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.T(InventoryFilterRuleEditorPanel.J.H);
        double d = this.L();
        this.kS = new PanelComponent(this.A(), d);
        this.kS.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.kS.d(false);
        this.j$src$V$vf1nte();
        this.h(this.kS, new Object[0]);
    }


    private void lambda$render$7(InventoryItemCategory inventoryItemCategory) {
        this.kO.i(inventoryItemCategory);
        this.j$src$V$vf1nte();
    }
}
