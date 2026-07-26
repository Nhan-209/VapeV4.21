package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.Vape;
import gg.vape.friend.ui.OnlineRadarPreviewState;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemListFrame;
import gg.vape.module.utility.inventory.cleaner.HiddenInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcher;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherGroup;
import gg.vape.module.utility.inventory.cleaner.InventoryItemMatcherRegistry;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerIconTextActionRow;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemMatcherRowComponent;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemPickerCategoryItemClickListener;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemPickerSearchResultClickListener;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemPreviewComponent;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemStackSelectionRowComponent;
import gg.vape.module.utility.inventory.cleaner.ui.ItemPickerSelection;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.ScrollableFrameComponent;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InventoryItemPickerPanel
extends PanelComponent {
    private final PanelComponent VF;
    private final InventoryFilterRule Vj;
    private boolean VX = true;
    private final PanelComponent V2;
    private static final List<String> Vl;
    private final List<String> V9;
    @Nullable
    private Consumer<ItemPickerSelection<String, ItemMappingEntry>> V7;
    @Nullable
    private final InventoryItemMatcher Vt;
    @NotNull
    private Consumer<ItemPickerSelection<String, ItemMappingEntry>> V5;
    private static GuiComponent[] VL;
    private final boolean Vv;
    private final PanelComponent Vw;
    private final LabeledTextInputComponent VI = new LabeledTextInputComponent("Search items...", false, true);

    public static List a(InventoryItemPickerPanel inventoryItemPickerPanel) {
        return inventoryItemPickerPanel.V9;
    }

    @Nullable
    public Consumer<ItemPickerSelection<String, ItemMappingEntry>> a$src$Ljava_util_function_Consumer_$1pgzxj9() {
        return this.V7;
    }

    public void T(@Nullable Consumer<ItemPickerSelection<String, ItemMappingEntry>> consumer) {
        this.V5 = consumer;
    }

    private void c(ItemPickerSelection<String, ItemMappingEntry> itemPickerSelection) {
        if (this.V9.contains(itemPickerSelection.N() != null ? itemPickerSelection.N() : itemPickerSelection.X().M())) {
            Consumer<ItemPickerSelection<String, ItemMappingEntry>> consumer = this.V7;
            if (consumer != null) {
                consumer.accept(itemPickerSelection);
            }
        } else {
            this.V5.accept(itemPickerSelection);
        }
    }

    private List<OnlineRadarPreviewState<ItemStack, ItemMappingEntry>> T$src$Ljava_util_List_$1nsj6c3() {
        ItemStack itemStack;
        LinkedHashMap<ItemMappingEntry, ItemStack> linkedHashMap = new LinkedHashMap<ItemMappingEntry, ItemStack>();
        String string = this.VI.i$src$Ljava_lang_String_$1n2xf3k().toLowerCase();
        if (string.trim().isEmpty()) {
            for (ItemStack object2 : HotbarSlotRuleItemListFrame.Oq) {
                ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(object2);
                if (itemMappingEntry == null || this.V9.contains(itemMappingEntry.M()) || (itemStack = itemMappingEntry.Q()) == null || itemStack.isNull()) continue;
                linkedHashMap.put(itemMappingEntry, itemStack);
            }
        }
        for (ItemStack itemStack2 : ItemStackScoreUtil.S()) {
            ItemMappingEntry itemMappingEntry;
            if (this.Vt != null && !this.Vt.R(itemStack2) || (itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack2)) == null || (itemStack = itemMappingEntry.Q()) == null || itemStack.isNull() || !itemMappingEntry.q().contains(string) && !itemMappingEntry.q().replace("_", " ").contains(string) && !itemStack.x().toLowerCase().contains(string) || this.V9.contains(itemMappingEntry.M())) continue;
            linkedHashMap.put(itemMappingEntry, itemStack);
        }
        ArrayList<OnlineRadarPreviewState<ItemStack, ItemMappingEntry>> arrayList = new ArrayList<>();
        for (Map.Entry<ItemMappingEntry, ItemStack> entry : linkedHashMap.entrySet()) {
            arrayList.add(OnlineRadarPreviewState.l(entry.getValue(), entry.getKey()));
        }
        arrayList.sort((arg_0, arg_1) -> InventoryItemPickerPanel.lambda$getSearchedItems$8(string, arg_0, arg_1));
        return arrayList;
    }

    public boolean C$src$Z$ayi6fb() {
        return this.VX;
    }

    private void lambda$null$9(InventoryItemMatcher inventoryItemMatcher) {
        this.c(ItemPickerSelection.k(inventoryItemMatcher.k()));
    }

    public static void a(GuiComponent[] guiComponentArray) {
        VL = guiComponentArray;
    }

    private void lambda$new$2(InventoryItemMatcherGroup inventoryItemMatcherGroup) {
        this.e(inventoryItemMatcherGroup);
    }

    public void a$src$V$bf004p() {
        this.VF.Z(false);
        this.Vw.Z(true);
        this.V2.Z(false);
        this.Vw.t$src$V$zbu1jn();
        this.Vw.h(this.u(this.A(), "Search", false, !this.Vv), new Object[0]);
        this.Vw.h(new SpacerComponent(9.0, 0.0), "widthwrap");
        this.Vw.h(this.VI, new Object[0]);
        this.Vw.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        ScrollableFrameComponent scrollableFrameComponent = new ScrollableFrameComponent(this.Vw.A() - 5.0, 0.0);
        scrollableFrameComponent.t(36.0);
        scrollableFrameComponent.d(false);
        ArrayList<String> arrayList = new ArrayList<String>(this.V9);
        SimpleTextLabelComponent simpleTextLabelComponent = null;
        if (!arrayList.isEmpty()) {
            simpleTextLabelComponent = new SimpleTextLabelComponent("SELECTED", 0.8, InventoryItemPickerPanel.J.h, true);
            simpleTextLabelComponent.o(this.VF.A() - 10.0);
            simpleTextLabelComponent.Y(8.0);
            simpleTextLabelComponent.c(0);
            this.Vw.h(new PaddedComponent(0.0, 2.0, 5.0, 0.0, simpleTextLabelComponent), new Object[0]);
        }
        BiFunction<ItemStack, ItemMappingEntry, GuiComponent> biFunction = this::lambda$setSearchView$5;
        for (String object2 : arrayList) {
            ItemStack n2;
            ItemMappingEntry n = Vape.INSTANCE.getItemStackResolver().b(object2);
            if (n == null || (n2 = n.Q()) == null) continue;
            scrollableFrameComponent.h(biFunction.apply(n2, n), scrollableFrameComponent.f().size() % 6 == 5 ? "wrap" : "widthwrap");
        }
        this.Vw.h(new PaddedComponent(5.0, 0.0, scrollableFrameComponent), new Object[0]);
        scrollableFrameComponent.H(true);
        SimpleTextLabelComponent simpleTextLabelComponent2 = new SimpleTextLabelComponent("SEARCH RESULTS...", 0.8, InventoryItemPickerPanel.J.h, true);
        simpleTextLabelComponent2.o(this.VF.A() - 10.0);
        simpleTextLabelComponent2.Y(8.0);
        this.Vw.h(new PaddedComponent(5.0, 0.0, 5.0, 0.0, simpleTextLabelComponent2), new Object[0]);
        PanelComponent panelComponent = new PanelComponent(this.Vw.A(), 135.0 - scrollableFrameComponent.C() - (simpleTextLabelComponent != null ? simpleTextLabelComponent.L() + 2.0 : 0.0));
        panelComponent.t(panelComponent.L());
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent.d(false);
        this.Vw.h(panelComponent, new Object[0]);
        int n = 0;
        int n2 = 300;
        for (OnlineRadarPreviewState<ItemStack, ItemMappingEntry> onlineRadarPreviewState : this.T$src$Ljava_util_List_$1nsj6c3()) {
            ItemStack itemStack = onlineRadarPreviewState.n();
            ItemMappingEntry itemMappingEntry = onlineRadarPreviewState.h();
            InventoryItemStackSelectionRowComponent inventoryItemStackSelectionRowComponent = new InventoryItemStackSelectionRowComponent(itemStack);
            inventoryItemStackSelectionRowComponent.o(panelComponent.A());
            inventoryItemStackSelectionRowComponent.Y(16.0);
            PaddedComponent paddedComponent = new PaddedComponent(1.0, 0.0, 0.0, 0.0, inventoryItemStackSelectionRowComponent);
            panelComponent.h(paddedComponent, new Object[0]);
            inventoryItemStackSelectionRowComponent.K(() -> this.lambda$setSearchView$7(itemMappingEntry));
            if (++n < n2) continue;
            break;
        }
    }

    private GuiComponent lambda$setSearchView$5(ItemStack itemStack, ItemMappingEntry itemMappingEntry) {
        InventoryItemPreviewComponent inventoryItemPreviewComponent = new InventoryItemPreviewComponent(itemStack, true);
        PaddedComponent paddedComponent = new PaddedComponent(1.0, 1.0, 2.0, 0.0, inventoryItemPreviewComponent);
        inventoryItemPreviewComponent.j(new InventoryItemPickerSearchResultClickListener(this, itemMappingEntry));
        return paddedComponent;
    }

    public void M(String string) {
        this.V9.remove(string);
    }

    public void R(@Nullable Consumer<ItemPickerSelection<String, ItemMappingEntry>> consumer) {
        this.V7 = consumer;
    }

    public void j(String string) {
        this.V9.add(string);
    }

    private void lambda$setSearchView$7(ItemMappingEntry itemMappingEntry) {
        ClientSettings.f6.execute(() -> this.lambda$null$6(itemMappingEntry));
    }

    private static int lambda$getSearchedItems$8(String string, OnlineRadarPreviewState onlineRadarPreviewState, OnlineRadarPreviewState onlineRadarPreviewState2) {
        String string2 = ((ItemStack)onlineRadarPreviewState.n()).x().toLowerCase();
        String string3 = ((ItemStack)onlineRadarPreviewState2.n()).x().toLowerCase();
        if (string2.equals(string) && !string3.equals(string)) {
            return -1;
        }
        if (string2.startsWith(string) && !string3.startsWith(string)) {
            return -1;
        }
        return 0;
    }

    private void lambda$setCategoryView$10(InventoryItemMatcher inventoryItemMatcher) {
        ClientSettings.f6.execute(() -> this.lambda$null$9(inventoryItemMatcher));
    }

    static {
        InventoryItemPickerPanel.a(new GuiComponent[2]);
        Vl = Arrays.asList("minecraft:diamond_sword", "minecraft:diamond_pickaxe", "minecraft:diamond_axe", "minecraft:bow", "minecraft:cooked_beef", "minecraft:ender_pearl", "minecraft:snowball", "minecraft:egg", "minecraft:fishing_rod", "minecraft:enchanted_golden_apple", "minecraft:golden_apple", "minecraft:water_bucket");
    }

    private void lambda$new$4(InventoryItemMatcher inventoryItemMatcher) {
        ClientSettings.f6.execute(() -> this.lambda$null$3(inventoryItemMatcher));
    }

    public InventoryItemPickerPanel(InventoryFilterRule inventoryFilterRule, boolean bl, @Nullable InventoryItemMatcher inventoryItemMatcher, List<String> list, Consumer<ItemPickerSelection<String, ItemMappingEntry>> consumer) {
        super(108.0, 215.0);
        Object object;
        Object object2;
        this.V9 = new ArrayList<String>();
        this.Vj = inventoryFilterRule;
        this.Vv = bl;
        this.Vt = inventoryItemMatcher;
        this.V9.addAll(list);
        this.V5 = consumer;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.T(InventoryItemPickerPanel.J.H);
        double d = this.L();
        this.VF = new PanelComponent(this.A(), d);
        this.Vw = new PanelComponent(this.A(), d);
        this.V2 = new PanelComponent(this.A(), d);
        this.VF.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.Vw.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.V2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.VF.d(false);
        this.Vw.d(false);
        this.V2.d(false);
        this.VF.h(this.u(this.A(), "All items", true, false), new Object[0]);
        this.VF.h(new SpacerComponent(this.A(), 0.0), new Object[0]);
        this.VF.h(new SpacerComponent(9.0, 0.0), "widthwrap");
        this.VI.d(false);
        this.VI.e(false);
        this.VI.C(0.0);
        this.VI.H(0.0f);
        this.VI.O(0.0f);
        this.VI.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
        this.VI.A(InventoryItemPickerPanel.J.h);
        this.VI.Y(14.0);
        this.VI.o(this.VF.A() - 16.0);
        this.VI.o(this::lambda$new$1);
        this.VF.h(this.VI, new Object[0]);
        this.VF.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("COMMON ITEMS", 0.7, InventoryItemPickerPanel.J.h, true);
        simpleTextLabelComponent.c(0);
        simpleTextLabelComponent.o(this.VF.A() - 10.0);
        this.VF.h(new SpacerComponent(5.0, 0.0), "widthwrap");
        this.VF.h(simpleTextLabelComponent, new Object[0]);
        PanelComponent panelComponent = new PanelComponent(this.VF.A() - 10.0, 30.0);
        panelComponent.d(false);
        PaddedComponent paddedComponent = new PaddedComponent(5.0, panelComponent);
        paddedComponent.d(false);
        this.VF.h(paddedComponent, new Object[0]);
        int n = 0;
        for (String object42 : Vl) {
            object2 = Vape.INSTANCE.getItemStackResolver().b(object42);
            if (object2 == null || (object = ((ItemMappingEntry)object2).Q()) == null || ((ItemStack)object).isNull()) continue;
            InventoryItemPreviewComponent inventoryItemPreviewComponent = new InventoryItemPreviewComponent((ItemStack)object, false);
            inventoryItemPreviewComponent.j(new InventoryItemPickerCategoryItemClickListener(this, (ItemMappingEntry)object2));
            panelComponent.h(new PaddedComponent(0.0, 2.0, 2.0, 0.0, inventoryItemPreviewComponent), n == 5 ? "wrap" : "widthwrap");
            ++n;
        }
        SimpleTextLabelComponent simpleTextLabelComponent2 = new SimpleTextLabelComponent("GENERIC ITEMS", 0.7, InventoryItemPickerPanel.J.h, true);
        simpleTextLabelComponent2.c(4);
        simpleTextLabelComponent2.o(simpleTextLabelComponent2.h() * 1.2);
        this.VF.h(new SpacerComponent(5.0, 0.0), "widthwrap");
        this.VF.h(simpleTextLabelComponent2, "widthwrap");
        IconGlyphComponent iconGlyphComponent = new IconGlyphComponent("newinfo", 5.0f, 5.0f);
        iconGlyphComponent.r(true);
        iconGlyphComponent.w("Generic Items are groups of items that share a common theme.");
        this.VF.h(new PaddedComponent(1.5, iconGlyphComponent), new Object[0]);
        object2 = new PanelComponent(this.VF.A(), 115.0);
        ((FrameComponent)object2).l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        ((GuiComponent)object2).d(false);
        ((FrameComponent)object2).t(((GuiComponent)object2).L());
        this.VF.h((GuiComponent)object2, new Object[0]);
        for (InventoryItemMatcherGroup inventoryItemMatcherGroup : InventoryItemMatcherGroup.VALUES) {
            if (inventoryItemMatcherGroup.u() == null) continue;
            InventoryCleanerIconTextActionRow inventoryCleanerIconTextActionRow = new InventoryCleanerIconTextActionRow(inventoryItemMatcherGroup.getName(), inventoryItemMatcherGroup.u(), () -> this.lambda$new$2(inventoryItemMatcherGroup));
            inventoryCleanerIconTextActionRow.w(inventoryItemMatcherGroup.E());
            inventoryCleanerIconTextActionRow.o(this.VF.A());
            inventoryCleanerIconTextActionRow.Y(18.0);
            ((FrameComponent)object2).h(new PaddedComponent(1.0, 0.0, 0.0, 0.0, inventoryCleanerIconTextActionRow), new Object[0]);
        }
        InventoryItemMatcher hiddenMatcher = HiddenInventoryItemMatchers.R;
        InventoryCleanerIconTextActionRow inventoryCleanerIconTextActionRow = new InventoryCleanerIconTextActionRow(hiddenMatcher.getName(), hiddenMatcher.Z(), () -> this.lambda$new$4(hiddenMatcher));
        inventoryCleanerIconTextActionRow.o(this.VF.A());
        inventoryCleanerIconTextActionRow.Y(18.0);
        inventoryCleanerIconTextActionRow.w(hiddenMatcher.E());
        ((FrameComponent)object2).h(new PaddedComponent(1.0, 0.0, 0.0, 0.0, inventoryCleanerIconTextActionRow), new Object[0]);
        this.VF.o(108.0);
        this.h(this.VF, new Object[0]);
        this.h(this.Vw, new Object[0]);
        this.h(this.V2, new Object[0]);
        if (this.Vv) {
            this.a$src$V$bf004p();
        } else {
            this.L$src$V$b3gbo4();
        }
    }

    private void lambda$new$1(char c, int n) {
        ClientSettings.f6.execute(this::lambda$null$0);
    }

    public void e(InventoryItemMatcherGroup inventoryItemMatcherGroup) {
        this.VF.Z(false);
        this.Vw.Z(false);
        this.V2.Z(true);
        this.V2.t$src$V$zbu1jn();
        PanelComponent panelComponent = this.u(this.A(), inventoryItemMatcherGroup.getName(), false, true);
        this.V2.h(panelComponent, new Object[0]);
        PanelComponent panelComponent2 = new PanelComponent(this.Vw.A(), this.Vw.L() - panelComponent.L());
        panelComponent2.t(panelComponent2.L());
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent2.d(false);
        this.V2.h(panelComponent2, new Object[0]);
        for (InventoryItemMatcher inventoryItemMatcher : InventoryItemMatcherRegistry.N(inventoryItemMatcherGroup)) {
            InventoryItemMatcherRowComponent inventoryItemMatcherRowComponent = new InventoryItemMatcherRowComponent(inventoryItemMatcher, () -> this.lambda$setCategoryView$10(inventoryItemMatcher));
            inventoryItemMatcherRowComponent.o(panelComponent2.A());
            inventoryItemMatcherRowComponent.Y(18.0);
            inventoryItemMatcherRowComponent.w(inventoryItemMatcher.E());
            panelComponent2.h(new PaddedComponent(1.0, 0.0, 0.0, 0.0, inventoryItemMatcherRowComponent), new Object[0]);
        }
    }

    public void J(boolean bl) {
        this.VX = bl;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void lambda$null$3(InventoryItemMatcher inventoryItemMatcher) {
        this.c(ItemPickerSelection.k(inventoryItemMatcher.k()));
    }

    @Nullable
    public Consumer<ItemPickerSelection<String, ItemMappingEntry>> p() {
        return this.V5;
    }

    public static GuiComponent[] I$src$ALgg_vape_ui_click_component_GuiComponent_$fej6jd() {
        return VL;
    }

    public static void U(InventoryItemPickerPanel inventoryItemPickerPanel, ItemPickerSelection itemPickerSelection) {
        inventoryItemPickerPanel.c(itemPickerSelection);
    }

    private void lambda$null$6(ItemMappingEntry itemMappingEntry) {
        this.c(ItemPickerSelection.D(itemMappingEntry));
        if (this.VX) {
            this.V9.add(itemMappingEntry.M());
        }
        this.a$src$V$bf004p();
    }

    private PanelComponent u(double d, String string, boolean bl, boolean bl2) {
        Object object;
        PanelComponent panelComponent = new PanelComponent(d, 28.0);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent.d(false);
        if (bl && this.Vj instanceof SlotInventoryFilterRule) {
            object = (SlotInventoryFilterRule)this.Vj;
            PanelComponent panelComponent2 = new PanelComponent(10.0, 10.0);
            panelComponent2.d(true);
            panelComponent2.T(InventoryItemPickerPanel.J.y);
            panelComponent2.S(5);
            panelComponent.h(panelComponent2, new Object[0]);
            SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent(String.valueOf(((SlotInventoryFilterRule)object).m() + 1), 0.8, InventoryItemPickerPanel.J.A, true);
            simpleTextLabelComponent.g(3.0f);
            panelComponent2.h(simpleTextLabelComponent, new Object[0]);
            panelComponent.h(panelComponent2, new Object[0]);
        } else {
            panelComponent.h(new SpacerComponent(0.0, 10.0), new Object[0]);
        }
        if (bl2) {
            object = new GlyphIconComponent("back-hover@2x", 6.0, 6.0, 10.0, 10.0, InventoryItemPickerPanel.J.W, InventoryItemPickerPanel.J.f, null);
            ((GlyphIconComponent)object).R(true);
            ((GlyphIconComponent)object).q(true);
            ((InteractiveComponent)object).r(this::L$src$V$b3gbo4);
            panelComponent.h(new SpacerComponent(8.0, 0.0), "widthwrap");
            panelComponent.h((GuiComponent)object, "widthwrap");
        }
        object = new WrappingTextLabelComponent(string, 1.0, InventoryItemPickerPanel.J.A);
        ((GuiComponent)object).o(panelComponent.A() - (double)(bl2 ? 36 : 0));
        ((SimpleTextLabelComponent)object).l(true);
        ((GuiComponent)object).S(false);
        panelComponent.h((GuiComponent)object, new Object[0]);
        return panelComponent;
    }

    private void lambda$null$0() {
        if (this.VI.i$src$Ljava_lang_String_$1n2xf3k().trim().isEmpty() && !this.Vv) {
            this.L$src$V$b3gbo4();
        } else {
            this.a$src$V$bf004p();
        }
    }

    public void L$src$V$b3gbo4() {
        this.VF.Z(true);
        this.Vw.Z(false);
        this.V2.Z(false);
    }
}
