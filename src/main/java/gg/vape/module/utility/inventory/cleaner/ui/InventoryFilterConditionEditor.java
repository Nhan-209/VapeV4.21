package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.ComparisonOperator;
import gg.vape.module.utility.inventory.cleaner.DurabilityValueMode;
import gg.vape.module.utility.inventory.cleaner.EmptyInventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.EnchantmentFilterCondition;
import gg.vape.module.utility.inventory.cleaner.EnchantmentFilterMode;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ItemDurabilityFilterCondition;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.MaterialFilterCondition;
import gg.vape.module.utility.inventory.cleaner.MembershipMode;
import gg.vape.module.utility.inventory.cleaner.NumericFilterCondition;
import gg.vape.module.utility.inventory.cleaner.PotionEffectFilterCondition;
import gg.vape.module.utility.inventory.cleaner.PotionEffectFilterMode;
import gg.vape.module.utility.inventory.cleaner.TextFilterCondition;
import gg.vape.module.utility.inventory.cleaner.TextMatchMode;
import gg.vape.module.utility.inventory.cleaner.ui.MaterialFilterSelectionList;
import gg.vape.module.utility.inventory.cleaner.ui.TextSuggestionInputComponent;
import gg.vape.module.utility.inventory.cleaner.ui.TextSuggestionRow;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.component.OptionTextFormatter;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.ScrollableFrameComponent;
import gg.vape.utils.StringUtils;
import gg.vape.wrapper.impl.Enchantment;
import gg.vape.wrapper.impl.PotionEntry;
import gg.vape.wrapper.impl.PotionRegistry;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.Nullable;

class InventoryFilterConditionEditor
extends ScrollableFrameComponent {
    private final InventoryFilterRule G8;
    private InventoryFilterCondition<?> G3;
    private final InventoryFilterConditionGroup GB;
    private final Runnable GK;

    private void lambda$update$10(TextFilterCondition<?> textFilterCondition, DropdownSelectComponent dropdownSelectComponent) {
        textFilterCondition.W((TextMatchMode)dropdownSelectComponent.j$src$Ljava_lang_Object_$an7bt2());
        this.b$src$V$1rqayd3();
    }

    private static void lambda$update$6(PotionEffectFilterCondition potionEffectFilterCondition, DropdownSelectComponent dropdownSelectComponent) {
        potionEffectFilterCondition.m(((PotionEntry)dropdownSelectComponent.j$src$Ljava_lang_Object_$an7bt2()).T());
    }

    private void lambda$update$3(PotionEffectFilterCondition potionEffectFilterCondition, DropdownSelectComponent dropdownSelectComponent) {
        potionEffectFilterCondition.O((PotionEffectFilterMode)dropdownSelectComponent.j$src$Ljava_lang_Object_$an7bt2());
        this.b$src$V$1rqayd3();
    }

    private static NumberFormatException a(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    private static int lambda$update$4(PotionEntry potionEntry, PotionEntry potionEntry2) {
        return potionEntry.G().compareToIgnoreCase(potionEntry2.G());
    }

    private void lambda$update$12(TextFilterCondition<?> textFilterCondition, TextSuggestionInputComponent textSuggestionInputComponent, char c, int n) {
        if (textFilterCondition.M().d()) {
            String string;
            if (n == 9 && !(string = textSuggestionInputComponent.Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6().i$src$Ljava_lang_String_$1n2xf3k().trim()).isEmpty()) {
                textFilterCondition.n(string);
                textSuggestionInputComponent.Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6().k("");
                this.b$src$V$1rqayd3();
            }
        } else {
            textFilterCondition.B();
            textFilterCondition.n(textSuggestionInputComponent.Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6().i$src$Ljava_lang_String_$1n2xf3k());
        }
    }

    private void b$src$V$1rqayd3() {
        this.t$src$V$zbu1jn();
        this.h(new SpacerComponent(5.0, 0.0), new Object[0]);
        DropdownSelectComponent<InventoryFilterConditionType> dropdownSelectComponent = this.Z("Select...", this.G3 instanceof EmptyInventoryFilterCondition ? null : this.G3.K(), OptionTextFormatter.j(), Arrays.asList(InventoryFilterConditionType.values()));
        dropdownSelectComponent.D(() -> this.lambda$update$0(dropdownSelectComponent));
        this.h(dropdownSelectComponent, new Object[0]);
        if (this.G3 instanceof EmptyInventoryFilterCondition) {
            DropdownSelectComponent<String> emptyDropdown = this.Z("Select...", null, OptionTextFormatter.N(), Collections.emptyList());
            emptyDropdown.F(true);
            this.h(emptyDropdown, new Object[0]);
            LabeledTextInputComponent emptyInput = new LabeledTextInputComponent("", false, true);
            emptyInput.F(true);
            emptyInput.v$src$Lgg_vape_ui_click_component_IconButtonComponent_$9khxxe().Z(false);
            emptyInput.d(false);
            emptyInput.e(false);
            emptyInput.C(0.0);
            emptyInput.H(0.0f);
            emptyInput.O(0.0f);
            emptyInput.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
            emptyInput.A(InventoryFilterConditionEditor.J.h);
            emptyInput.o(this.A() - this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C() - 17.5 - 20.0);
            emptyInput.Y(15.0);
            emptyInput.b(true);
            this.h(emptyInput, new Object[0]);
        } else {
            boolean bl = true;
            if (this.G3 instanceof EnchantmentFilterCondition) {
                EnchantmentFilterCondition enchantmentCondition = (EnchantmentFilterCondition)this.G3;
                DropdownSelectComponent<EnchantmentFilterMode> enchantmentModeDropdown = this.Z("Select...", enchantmentCondition.v(), OptionTextFormatter.j(), EnchantmentFilterMode.VALUES);
                enchantmentModeDropdown.D(() -> this.lambda$update$1(enchantmentCondition, enchantmentModeDropdown));
                enchantmentModeDropdown.q(30.0);
                this.h(enchantmentModeDropdown, new Object[0]);
                List<String> enchantmentNames = new ArrayList<>();
                for (Enchantment enchantment : Enchantment.getEnchantments()) {
                    if (enchantment.isNull()) continue;
                    enchantmentNames.add(StringUtils.Q(enchantment.getTranslatedName(1)));
                }
                enchantmentNames.sort(String::compareToIgnoreCase);
                DropdownSelectComponent<String> enchantmentDropdown = this.Z("Select enchantment...", enchantmentCondition.A(), OptionTextFormatter.N(), enchantmentNames);
                enchantmentDropdown.q(enchantmentDropdown.l$src$D$1x5l26k() + 15.0);
                enchantmentDropdown.D(() -> InventoryFilterConditionEditor.lambda$update$2(enchantmentCondition, enchantmentDropdown));
                this.h(enchantmentDropdown, new Object[0]);
                if (enchantmentCondition.v() == EnchantmentFilterMode.HAS) {
                    bl = false;
                }
            }
            if (this.G3 instanceof PotionEffectFilterCondition) {
                PotionEffectFilterCondition potionCondition = (PotionEffectFilterCondition)this.G3;
                DropdownSelectComponent<PotionEffectFilterMode> potionModeDropdown = this.Z("Select...", potionCondition.K$src$Lgg_vape_module_utility_inventory_cleaner_Potion$q09io(), OptionTextFormatter.j(), PotionEffectFilterMode.VALUES);
                potionModeDropdown.D(() -> this.lambda$update$3(potionCondition, potionModeDropdown));
                potionModeDropdown.q(30.0);
                this.h(potionModeDropdown, new Object[0]);
                List<PotionEntry> potionEntries = new ArrayList<PotionEntry>(PotionRegistry.O());
                potionEntries.sort(InventoryFilterConditionEditor::lambda$update$4);
                DropdownSelectComponent<PotionEntry> potionDropdown = this.Z("Select potion...", potionCondition.z(), InventoryFilterConditionEditor::lambda$update$5, potionEntries);
                potionDropdown.q(potionDropdown.l$src$D$1x5l26k() + 15.0);
                potionDropdown.D(() -> InventoryFilterConditionEditor.lambda$update$6(potionCondition, potionDropdown));
                this.h(potionDropdown, new Object[0]);
                if (potionCondition.K$src$Lgg_vape_module_utility_inventory_cleaner_Potion$q09io() == PotionEffectFilterMode.HAS) {
                    bl = false;
                }
            }
            if (this.G3 instanceof ItemDurabilityFilterCondition) {
                ItemDurabilityFilterCondition durabilityCondition = (ItemDurabilityFilterCondition)this.G3;
                DropdownSelectComponent<DurabilityValueMode> durabilityDropdown = this.Z("Select item...", durabilityCondition.W(), OptionTextFormatter.j(), DurabilityValueMode.VALUES);
                durabilityDropdown.D(() -> this.lambda$update$7(durabilityCondition, durabilityDropdown));
                this.h(durabilityDropdown, new Object[0]);
            }
            if (this.G3 instanceof NumericFilterCondition && bl) {
                NumericFilterCondition numericCondition = (NumericFilterCondition)this.G3;
                DropdownSelectComponent<ComparisonOperator> comparisonDropdown = this.Z("Select item...", numericCondition.p(), OptionTextFormatter.j(), ComparisonOperator.VALUES);
                comparisonDropdown.q(82.0);
                comparisonDropdown.D(() -> this.lambda$update$8(numericCondition, comparisonDropdown));
                this.h(comparisonDropdown, new Object[0]);
                LabeledTextInputComponent numericInput = new LabeledTextInputComponent("", false, true);
                numericInput.v$src$Lgg_vape_ui_click_component_IconButtonComponent_$9khxxe().Z(false);
                numericInput.k(numericCondition.k());
                numericInput.d(false);
                numericInput.e(false);
                numericInput.C(0.0);
                numericInput.H(0.0f);
                numericInput.O(0.0f);
                numericInput.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
                numericInput.A(InventoryFilterConditionEditor.J.h);
                numericInput.o(this.A() - this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C() - 17.5 - 20.0);
                numericInput.Y(15.0);
                numericInput.b(true);
                numericInput.o((arg_0, arg_1) -> InventoryFilterConditionEditor.lambda$update$9(numericCondition, numericInput, arg_0, arg_1));
                this.h(new PaddedComponent(2.5, 0.0, 0.0, 0.0, numericInput), new Object[0]);
            } else if (this.G3 instanceof TextFilterCondition) {
                TextFilterCondition<?> textCondition = (TextFilterCondition<?>)this.G3;
                DropdownSelectComponent<TextMatchMode> textModeDropdown = this.Z("Select item...", textCondition.M(), OptionTextFormatter.j(), TextMatchMode.VALUES);
                textModeDropdown.q(textModeDropdown.l$src$D$1x5l26k() + 25.0);
                textModeDropdown.D(() -> this.lambda$update$10(textCondition, textModeDropdown));
                this.h(textModeDropdown, new Object[0]);
                TextSuggestionInputComponent suggestionInput = new TextSuggestionInputComponent("", arg_0 -> InventoryFilterConditionEditor.lambda$update$11(textCondition, arg_0), this.A() - this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C() - 17.5 - 20.0, 15.0, false, true);
                if (textCondition.M().d()) {
                    suggestionInput.Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6().k("");
                    for (String string : textCondition.M$src$Ljava_util_List_$bgq9xa()) {
                        suggestionInput.z(new TextSuggestionRow(string));
                    }
                } else {
                    suggestionInput.Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6().k(textCondition.M$src$Ljava_util_List_$bgq9xa().isEmpty() ? "" : textCondition.M$src$Ljava_util_List_$bgq9xa().get(0));
                }
                suggestionInput.d(false);
                suggestionInput.Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6().e(false);
                suggestionInput.C(0.0);
                suggestionInput.Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6().H(0.0f);
                suggestionInput.Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6().O(0.0f);
                suggestionInput.Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6().t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
                suggestionInput.Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6().A(InventoryFilterConditionEditor.J.h);
                suggestionInput.Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6().o((arg_0, arg_1) -> this.lambda$update$12(textCondition, suggestionInput, arg_0, arg_1));
                this.h(new PaddedComponent(2.5, 0.0, 0.0, 0.0, suggestionInput), new Object[0]);
            } else if (this.G3 instanceof MaterialFilterCondition) {
                MaterialFilterCondition materialCondition = (MaterialFilterCondition)this.G3;
                DropdownSelectComponent<MembershipMode> membershipDropdown = this.Z("Select item...", materialCondition.x(), OptionTextFormatter.j(), MembershipMode.VALUES);
                membershipDropdown.q(30.0);
                membershipDropdown.D(() -> this.lambda$update$13(materialCondition, membershipDropdown));
                this.h(membershipDropdown, new Object[0]);
                MaterialFilterSelectionList selectionList = new MaterialFilterSelectionList(this.G8, materialCondition, this.A() - this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C() - 36.0);
                for (ItemFilterSelection itemFilterSelection : materialCondition.U()) {
                    selectionList.C(itemFilterSelection);
                }
                selectionList.C(0.0);
                this.h(new PaddedComponent(2.5, 0.0, 0.0, 0.0, selectionList), new Object[0]);
                this.h(new SpacerComponent(this.A() - this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C() - 17.5 - 20.0, 0.0), new Object[0]);
            } else {
                this.h(new SpacerComponent(this.A() - this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().C() - 17.5 - 20.0, 0.0), new Object[0]);
            }
        }
        TextButton andButton = new TextButton("AND", 0.65, InventoryFilterConditionEditor.J.B, InventoryFilterConditionEditor.J.O, 58.0, 9.0);
        andButton.c(true);
        andButton.h(Color.WHITE);
        andButton.F(false);
        andButton.q(andButton.W() + 10.0);
        andButton.s(this::lambda$update$14);
        this.h(new PaddedComponent(5.0, 0.0, 5.0, 0.0, andButton), new Object[0]);
        PanelComponent closePanel = new PanelComponent(12.0, 12.0);
        GlyphIconComponent closeIcon = new GlyphIconComponent("newclose", 8.0, 8.0, 8.0, 8.0, InventoryFilterConditionEditor.J.A, InventoryFilterConditionEditor.J.A, null);
        closeIcon.q(true);
        closeIcon.R(true);
        closeIcon.i(4.0f);
        closeIcon.E(InventoryFilterConditionEditor.J.R, InventoryFilterConditionEditor.J.c);
        closeIcon.r(this::lambda$update$16);
        closePanel.h(new PaddedComponent(6.0, 0.0, 3.0, 3.0, closeIcon), new Object[0]);
        closePanel.d(false);
        this.h(closePanel, new Object[0]);
    }

    private static String lambda$update$5(PotionEntry potionEntry) {
        return potionEntry != null ? potionEntry.G() : "";
    }

    private void lambda$update$0(DropdownSelectComponent dropdownSelectComponent) {
        InventoryFilterCondition inventoryFilterCondition = ((InventoryFilterConditionType)dropdownSelectComponent.j$src$Ljava_lang_Object_$an7bt2()).O().get();
        this.GB.L(this.G3, inventoryFilterCondition);
        this.G3 = inventoryFilterCondition;
        this.b$src$V$1rqayd3();
    }

    private <T> DropdownSelectComponent<T> Z(String string, @Nullable T t, OptionTextFormatter<T> optionTextFormatter, List<T> list) {
        DropdownSelectComponent<T> dropdownSelectComponent = new DropdownSelectComponent<T>(string, optionTextFormatter, list);
        dropdownSelectComponent.H(string);
        dropdownSelectComponent.G(t);
        dropdownSelectComponent.q(false);
        dropdownSelectComponent.u(this.L());
        this.getClass();
        dropdownSelectComponent.q(60.0f + 5.0f * 2.0f);
        dropdownSelectComponent.d(false);
        dropdownSelectComponent.v(true);
        dropdownSelectComponent.C(0.0);
        return dropdownSelectComponent;
    }

    private static void lambda$update$11(TextFilterCondition<?> textFilterCondition, TextSuggestionRow textSuggestionRow) {
        textFilterCondition.l(textSuggestionRow.x$src$Ljava_lang_String_$1m64ofa());
    }

    private static void lambda$update$2(EnchantmentFilterCondition enchantmentFilterCondition, DropdownSelectComponent dropdownSelectComponent) {
        enchantmentFilterCondition.l((String)dropdownSelectComponent.j$src$Ljava_lang_Object_$an7bt2());
    }

    private static void lambda$update$9(NumericFilterCondition numericFilterCondition, LabeledTextInputComponent labeledTextInputComponent, char c, int n) {
        try {
            numericFilterCondition.Q(labeledTextInputComponent.i$src$Ljava_lang_String_$1n2xf3k());
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
    }

    private void lambda$update$7(ItemDurabilityFilterCondition itemDurabilityFilterCondition, DropdownSelectComponent dropdownSelectComponent) {
        itemDurabilityFilterCondition.m((DurabilityValueMode)dropdownSelectComponent.j$src$Ljava_lang_Object_$an7bt2());
        this.b$src$V$1rqayd3();
    }

    private void lambda$null$15() {
        this.GB.j(this.G3);
        this.GK.run();
    }

    private void lambda$update$8(NumericFilterCondition numericFilterCondition, DropdownSelectComponent dropdownSelectComponent) {
        numericFilterCondition.J((ComparisonOperator)dropdownSelectComponent.j$src$Ljava_lang_Object_$an7bt2());
        this.b$src$V$1rqayd3();
    }

    public void i(InventoryFilterCondition<?> inventoryFilterCondition) {
        this.G3 = inventoryFilterCondition;
        this.b$src$V$1rqayd3();
    }

    private void lambda$update$14() {
        EmptyInventoryFilterCondition emptyInventoryFilterCondition = new EmptyInventoryFilterCondition();
        this.GB.O(emptyInventoryFilterCondition);
        this.GK.run();
    }

    private void lambda$update$1(EnchantmentFilterCondition enchantmentFilterCondition, DropdownSelectComponent dropdownSelectComponent) {
        enchantmentFilterCondition.M((EnchantmentFilterMode)dropdownSelectComponent.j$src$Ljava_lang_Object_$an7bt2());
        this.b$src$V$1rqayd3();
    }

    private void lambda$update$16() {
        ClientSettings.f6.execute(this::lambda$null$15);
    }

    public InventoryFilterConditionEditor(double d, InventoryFilterRule inventoryFilterRule, InventoryFilterConditionGroup inventoryFilterConditionGroup, InventoryFilterCondition<?> inventoryFilterCondition, Runnable runnable) {
        super(d);
        this.v(d);
        this.h(15.0);
        this.G8 = inventoryFilterRule;
        this.GB = inventoryFilterConditionGroup;
        this.G3 = inventoryFilterCondition;
        this.GK = runnable;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.d(false);
        this.b$src$V$1rqayd3();
    }

    private void lambda$update$13(MaterialFilterCondition materialFilterCondition, DropdownSelectComponent dropdownSelectComponent) {
        materialFilterCondition.j((MembershipMode)dropdownSelectComponent.j$src$Ljava_lang_Object_$an7bt2());
        this.b$src$V$1rqayd3();
    }
}
