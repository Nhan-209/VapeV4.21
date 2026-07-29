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
    private final PanelComponent allItemsPanel;
    private final InventoryFilterRule filterRule;
    private boolean addSelectionAfterChoose = true;
    private final PanelComponent categoryPanel;
    private static final List<String> COMMON_ITEM_IDS;
    private final List<String> selectedItemIds;
    @Nullable
    private Consumer<ItemPickerSelection<String, ItemMappingEntry>> onExistingSelection;
    @Nullable
    private final InventoryItemMatcher filterMatcher;
    @NotNull
    private Consumer<ItemPickerSelection<String, ItemMappingEntry>> onSelect;
    private final boolean searchOnly;
    private final PanelComponent searchPanel;
    private final LabeledTextInputComponent searchInput = new LabeledTextInputComponent("Search items...", false, true);

    public static List<String> getSelectedItemIds(InventoryItemPickerPanel panel) {
        return panel.selectedItemIds;
    }

    @Nullable
    public Consumer<ItemPickerSelection<String, ItemMappingEntry>> getOnExistingSelection() {
        return this.onExistingSelection;
    }

    public void setOnSelect(@Nullable Consumer<ItemPickerSelection<String, ItemMappingEntry>> onSelect) {
        this.onSelect = onSelect;
    }

    private void dispatchSelection(ItemPickerSelection<String, ItemMappingEntry> itemPickerSelection) {
        if (this.selectedItemIds.contains(itemPickerSelection.getLeft() != null ? itemPickerSelection.getLeft() : itemPickerSelection.getRight().M())) {
            Consumer<ItemPickerSelection<String, ItemMappingEntry>> consumer = this.onExistingSelection;
            if (consumer != null) {
                consumer.accept(itemPickerSelection);
            }
        } else {
            this.onSelect.accept(itemPickerSelection);
        }
    }

    private List<OnlineRadarPreviewState<ItemStack, ItemMappingEntry>> getSearchResults() {
        ItemStack resolvedStack;
        LinkedHashMap<ItemMappingEntry, ItemStack> matchingItems = new LinkedHashMap<ItemMappingEntry, ItemStack>();
        String query = this.searchInput.getText().toLowerCase();
        if (query.trim().isEmpty()) {
            for (ItemStack commonStack : HotbarSlotRuleItemListFrame.Oq) {
                ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().resolve(commonStack);
                if (itemMappingEntry == null || this.selectedItemIds.contains(itemMappingEntry.M()) || (resolvedStack = itemMappingEntry.Q()) == null || resolvedStack.isNull()) continue;
                matchingItems.put(itemMappingEntry, resolvedStack);
            }
        }
        for (ItemStack candidateStack : ItemStackScoreUtil.S()) {
            ItemMappingEntry itemMappingEntry;
            if (this.filterMatcher != null && !this.filterMatcher.matches(candidateStack) || (itemMappingEntry = Vape.INSTANCE.getItemStackResolver().resolve(candidateStack)) == null || (resolvedStack = itemMappingEntry.Q()) == null || resolvedStack.isNull() || !itemMappingEntry.q().contains(query) && !itemMappingEntry.q().replace("_", " ").contains(query) && !resolvedStack.x().toLowerCase().contains(query) || this.selectedItemIds.contains(itemMappingEntry.M())) continue;
            matchingItems.put(itemMappingEntry, resolvedStack);
        }
        ArrayList<OnlineRadarPreviewState<ItemStack, ItemMappingEntry>> results = new ArrayList<>();
        for (Map.Entry<ItemMappingEntry, ItemStack> entry : matchingItems.entrySet()) {
            results.add(OnlineRadarPreviewState.l(entry.getValue(), entry.getKey()));
        }
        results.sort((first, second) -> InventoryItemPickerPanel.compareSearchResults(query, first, second));
        return results;
    }

    public boolean shouldAddSelectionAfterChoose() {
        return this.addSelectionAfterChoose;
    }

    private void selectMatcher(InventoryItemMatcher inventoryItemMatcher) {
        this.dispatchSelection(ItemPickerSelection.ofLeft(inventoryItemMatcher.getId()));
    }

    private void openMatcherGroup(InventoryItemMatcherGroup inventoryItemMatcherGroup) {
        this.showCategoryView(inventoryItemMatcherGroup);
    }

    public void showSearchView() {
        this.allItemsPanel.setVisible(false);
        this.searchPanel.setVisible(true);
        this.categoryPanel.setVisible(false);
        this.searchPanel.t$src$V$zbu1jn();
        this.searchPanel.h(this.createHeader(this.A(), "Search", false, !this.searchOnly), new Object[0]);
        this.searchPanel.h(new SpacerComponent(9.0, 0.0), "widthwrap");
        this.searchPanel.h(this.searchInput, new Object[0]);
        this.searchPanel.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        ScrollableFrameComponent scrollableFrameComponent = new ScrollableFrameComponent(this.searchPanel.A() - 5.0, 0.0);
        scrollableFrameComponent.t(36.0);
        scrollableFrameComponent.setShowDisabledOverlay(false);
        ArrayList<String> selectedIds = new ArrayList<String>(this.selectedItemIds);
        SimpleTextLabelComponent selectedLabel = null;
        if (!selectedIds.isEmpty()) {
            selectedLabel = new SimpleTextLabelComponent("SELECTED", 0.8, InventoryItemPickerPanel.J.h, true);
            selectedLabel.o(this.allItemsPanel.A() - 10.0);
            selectedLabel.Y(8.0);
            selectedLabel.setExtraHeight(0);
            this.searchPanel.h(new PaddedComponent(0.0, 2.0, 5.0, 0.0, selectedLabel), new Object[0]);
        }
        BiFunction<ItemStack, ItemMappingEntry, GuiComponent> previewFactory = this::createSelectedItemPreview;
        for (String selectedId : selectedIds) {
            ItemStack selectedStack;
            ItemMappingEntry selectedEntry = Vape.INSTANCE.getItemStackResolver().findByName(selectedId);
            if (selectedEntry == null || (selectedStack = selectedEntry.Q()) == null) continue;
            scrollableFrameComponent.h(previewFactory.apply(selectedStack, selectedEntry), scrollableFrameComponent.f().size() % 6 == 5 ? "wrap" : "widthwrap");
        }
        this.searchPanel.h(new PaddedComponent(5.0, 0.0, scrollableFrameComponent), new Object[0]);
        scrollableFrameComponent.H(true);
        SimpleTextLabelComponent simpleTextLabelComponent2 = new SimpleTextLabelComponent("SEARCH RESULTS...", 0.8, InventoryItemPickerPanel.J.h, true);
        simpleTextLabelComponent2.o(this.allItemsPanel.A() - 10.0);
        simpleTextLabelComponent2.Y(8.0);
        this.searchPanel.h(new PaddedComponent(5.0, 0.0, 5.0, 0.0, simpleTextLabelComponent2), new Object[0]);
        PanelComponent panelComponent = new PanelComponent(this.searchPanel.A(), 135.0 - scrollableFrameComponent.C() - (selectedLabel != null ? selectedLabel.L() + 2.0 : 0.0));
        panelComponent.t(panelComponent.L());
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent.setShowDisabledOverlay(false);
        this.searchPanel.h(panelComponent, new Object[0]);
        int resultCount = 0;
        int maxResults = 300;
        for (OnlineRadarPreviewState<ItemStack, ItemMappingEntry> onlineRadarPreviewState : this.getSearchResults()) {
            ItemStack itemStack = onlineRadarPreviewState.n();
            ItemMappingEntry itemMappingEntry = onlineRadarPreviewState.h();
            InventoryItemStackSelectionRowComponent inventoryItemStackSelectionRowComponent = new InventoryItemStackSelectionRowComponent(itemStack);
            inventoryItemStackSelectionRowComponent.o(panelComponent.A());
            inventoryItemStackSelectionRowComponent.Y(16.0);
            PaddedComponent paddedComponent = new PaddedComponent(1.0, 0.0, 0.0, 0.0, inventoryItemStackSelectionRowComponent);
            panelComponent.h(paddedComponent, new Object[0]);
            inventoryItemStackSelectionRowComponent.K(() -> this.scheduleItemSelection(itemMappingEntry));
            if (++resultCount < maxResults) continue;
            break;
        }
    }

    private GuiComponent createSelectedItemPreview(ItemStack itemStack, ItemMappingEntry itemMappingEntry) {
        InventoryItemPreviewComponent inventoryItemPreviewComponent = new InventoryItemPreviewComponent(itemStack, true);
        PaddedComponent paddedComponent = new PaddedComponent(1.0, 1.0, 2.0, 0.0, inventoryItemPreviewComponent);
        inventoryItemPreviewComponent.addMouseListener(new InventoryItemPickerSearchResultClickListener(this, itemMappingEntry));
        return paddedComponent;
    }

    public void removeSelectedItemId(String string) {
        this.selectedItemIds.remove(string);
    }

    public void setOnExistingSelection(@Nullable Consumer<ItemPickerSelection<String, ItemMappingEntry>> onExistingSelection) {
        this.onExistingSelection = onExistingSelection;
    }

    public void addSelectedItemId(String string) {
        this.selectedItemIds.add(string);
    }

    private void scheduleItemSelection(ItemMappingEntry itemMappingEntry) {
        ClientSettings.UI_EXECUTOR.execute(() -> this.selectMappingEntry(itemMappingEntry));
    }

    private static int compareSearchResults(String query, OnlineRadarPreviewState first, OnlineRadarPreviewState second) {
        String firstName = ((ItemStack)first.n()).x().toLowerCase();
        String secondName = ((ItemStack)second.n()).x().toLowerCase();
        if (firstName.equals(query) && !secondName.equals(query)) {
            return -1;
        }
        if (firstName.startsWith(query) && !secondName.startsWith(query)) {
            return -1;
        }
        return 0;
    }

    private void scheduleMatcherSelection(InventoryItemMatcher inventoryItemMatcher) {
        ClientSettings.UI_EXECUTOR.execute(() -> this.selectMatcher(inventoryItemMatcher));
    }

    static {
        COMMON_ITEM_IDS = Arrays.asList("minecraft:diamond_sword", "minecraft:diamond_pickaxe", "minecraft:diamond_axe", "minecraft:bow", "minecraft:cooked_beef", "minecraft:ender_pearl", "minecraft:snowball", "minecraft:egg", "minecraft:fishing_rod", "minecraft:enchanted_golden_apple", "minecraft:golden_apple", "minecraft:water_bucket");
    }

    public InventoryItemPickerPanel(InventoryFilterRule inventoryFilterRule, boolean searchOnly, @Nullable InventoryItemMatcher inventoryItemMatcher, List<String> selectedItemIds, Consumer<ItemPickerSelection<String, ItemMappingEntry>> onSelect) {
        super(108.0, 215.0);
        this.selectedItemIds = new ArrayList<String>();
        this.filterRule = inventoryFilterRule;
        this.searchOnly = searchOnly;
        this.filterMatcher = inventoryItemMatcher;
        this.selectedItemIds.addAll(selectedItemIds);
        this.onSelect = onSelect;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.setDisabledOverlayColor(InventoryItemPickerPanel.J.H);
        double panelHeight = this.L();
        this.allItemsPanel = new PanelComponent(this.A(), panelHeight);
        this.searchPanel = new PanelComponent(this.A(), panelHeight);
        this.categoryPanel = new PanelComponent(this.A(), panelHeight);
        this.allItemsPanel.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.searchPanel.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.categoryPanel.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.allItemsPanel.setShowDisabledOverlay(false);
        this.searchPanel.setShowDisabledOverlay(false);
        this.categoryPanel.setShowDisabledOverlay(false);
        this.allItemsPanel.h(this.createHeader(this.A(), "All items", true, false), new Object[0]);
        this.allItemsPanel.h(new SpacerComponent(this.A(), 0.0), new Object[0]);
        this.allItemsPanel.h(new SpacerComponent(9.0, 0.0), "widthwrap");
        this.searchInput.setShowDisabledOverlay(false);
        this.searchInput.setBackgroundVisible(false);
        this.searchInput.setHorizontalInset(0.0);
        this.searchInput.setLeftInset(0.0f);
        this.searchInput.setVerticalInset(0.0f);
        this.searchInput.getActionButton().setVisible(false);
        this.searchInput.setPlaceholderColor(InventoryItemPickerPanel.J.h);
        this.searchInput.Y(14.0);
        this.searchInput.o(this.allItemsPanel.A() - 16.0);
        this.searchInput.addKeyTypedListener(this::handleSearchInput);
        this.allItemsPanel.h(this.searchInput, new Object[0]);
        this.allItemsPanel.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("COMMON ITEMS", 0.7, InventoryItemPickerPanel.J.h, true);
        simpleTextLabelComponent.setExtraHeight(0);
        simpleTextLabelComponent.o(this.allItemsPanel.A() - 10.0);
        this.allItemsPanel.h(new SpacerComponent(5.0, 0.0), "widthwrap");
        this.allItemsPanel.h(simpleTextLabelComponent, new Object[0]);
        PanelComponent panelComponent = new PanelComponent(this.allItemsPanel.A() - 10.0, 30.0);
        panelComponent.setShowDisabledOverlay(false);
        PaddedComponent paddedComponent = new PaddedComponent(5.0, panelComponent);
        paddedComponent.setShowDisabledOverlay(false);
        this.allItemsPanel.h(paddedComponent, new Object[0]);
        int commonItemCount = 0;
        for (String commonItemId : COMMON_ITEM_IDS) {
            ItemMappingEntry itemEntry = Vape.INSTANCE.getItemStackResolver().findByName(commonItemId);
            ItemStack itemStack;
            if (itemEntry == null || (itemStack = itemEntry.Q()) == null || itemStack.isNull()) continue;
            InventoryItemPreviewComponent inventoryItemPreviewComponent = new InventoryItemPreviewComponent(itemStack, false);
            inventoryItemPreviewComponent.addMouseListener(new InventoryItemPickerCategoryItemClickListener(this, itemEntry));
            panelComponent.h(new PaddedComponent(0.0, 2.0, 2.0, 0.0, inventoryItemPreviewComponent), commonItemCount == 5 ? "wrap" : "widthwrap");
            ++commonItemCount;
        }
        SimpleTextLabelComponent simpleTextLabelComponent2 = new SimpleTextLabelComponent("GENERIC ITEMS", 0.7, InventoryItemPickerPanel.J.h, true);
        simpleTextLabelComponent2.setExtraHeight(4);
        simpleTextLabelComponent2.o(simpleTextLabelComponent2.getTextWidth() * 1.2);
        this.allItemsPanel.h(new SpacerComponent(5.0, 0.0), "widthwrap");
        this.allItemsPanel.h(simpleTextLabelComponent2, "widthwrap");
        IconGlyphComponent iconGlyphComponent = new IconGlyphComponent("newinfo", 5.0f, 5.0f);
        iconGlyphComponent.setSnapToPixels(true);
        iconGlyphComponent.w("Generic Items are groups of items that share a common theme.");
        this.allItemsPanel.h(new PaddedComponent(1.5, iconGlyphComponent), new Object[0]);
        PanelComponent matcherGroupsPanel = new PanelComponent(this.allItemsPanel.A(), 115.0);
        matcherGroupsPanel.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        matcherGroupsPanel.setShowDisabledOverlay(false);
        matcherGroupsPanel.t(matcherGroupsPanel.L());
        this.allItemsPanel.h(matcherGroupsPanel, new Object[0]);
        for (InventoryItemMatcherGroup inventoryItemMatcherGroup : InventoryItemMatcherGroup.VALUES) {
            if (inventoryItemMatcherGroup.getIconName() == null) continue;
            InventoryCleanerIconTextActionRow inventoryCleanerIconTextActionRow = new InventoryCleanerIconTextActionRow(inventoryItemMatcherGroup.getName(), inventoryItemMatcherGroup.getIconName(), () -> this.openMatcherGroup(inventoryItemMatcherGroup));
            inventoryCleanerIconTextActionRow.w(inventoryItemMatcherGroup.getDescription());
            inventoryCleanerIconTextActionRow.o(this.allItemsPanel.A());
            inventoryCleanerIconTextActionRow.Y(18.0);
            matcherGroupsPanel.h(new PaddedComponent(1.0, 0.0, 0.0, 0.0, inventoryCleanerIconTextActionRow), new Object[0]);
        }
        InventoryItemMatcher hiddenMatcher = HiddenInventoryItemMatchers.R;
        InventoryCleanerIconTextActionRow inventoryCleanerIconTextActionRow = new InventoryCleanerIconTextActionRow(hiddenMatcher.getName(), hiddenMatcher.getIconName(), () -> this.scheduleMatcherSelection(hiddenMatcher));
        inventoryCleanerIconTextActionRow.o(this.allItemsPanel.A());
        inventoryCleanerIconTextActionRow.Y(18.0);
        inventoryCleanerIconTextActionRow.w(hiddenMatcher.getDescription());
        matcherGroupsPanel.h(new PaddedComponent(1.0, 0.0, 0.0, 0.0, inventoryCleanerIconTextActionRow), new Object[0]);
        this.allItemsPanel.o(108.0);
        this.h(this.allItemsPanel, new Object[0]);
        this.h(this.searchPanel, new Object[0]);
        this.h(this.categoryPanel, new Object[0]);
        if (this.searchOnly) {
            this.showSearchView();
        } else {
            this.showAllItemsView();
        }
    }

    private void handleSearchInput(char character, int keyCode) {
        ClientSettings.UI_EXECUTOR.execute(this::updateViewFromSearch);
    }

    public void showCategoryView(InventoryItemMatcherGroup inventoryItemMatcherGroup) {
        this.allItemsPanel.setVisible(false);
        this.searchPanel.setVisible(false);
        this.categoryPanel.setVisible(true);
        this.categoryPanel.t$src$V$zbu1jn();
        PanelComponent panelComponent = this.createHeader(this.A(), inventoryItemMatcherGroup.getName(), false, true);
        this.categoryPanel.h(panelComponent, new Object[0]);
        PanelComponent panelComponent2 = new PanelComponent(this.searchPanel.A(), this.searchPanel.L() - panelComponent.L());
        panelComponent2.t(panelComponent2.L());
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent2.setShowDisabledOverlay(false);
        this.categoryPanel.h(panelComponent2, new Object[0]);
        for (InventoryItemMatcher inventoryItemMatcher : InventoryItemMatcherRegistry.getByGroup(inventoryItemMatcherGroup)) {
            InventoryItemMatcherRowComponent inventoryItemMatcherRowComponent = new InventoryItemMatcherRowComponent(inventoryItemMatcher, () -> this.scheduleMatcherSelection(inventoryItemMatcher));
            inventoryItemMatcherRowComponent.o(panelComponent2.A());
            inventoryItemMatcherRowComponent.Y(18.0);
        inventoryItemMatcherRowComponent.w(inventoryItemMatcher.getDescription());
            panelComponent2.h(new PaddedComponent(1.0, 0.0, 0.0, 0.0, inventoryItemMatcherRowComponent), new Object[0]);
        }
    }

    public void setAddSelectionAfterChoose(boolean addSelectionAfterChoose) {
        this.addSelectionAfterChoose = addSelectionAfterChoose;
    }


    @Nullable
    public Consumer<ItemPickerSelection<String, ItemMappingEntry>> getOnSelect() {
        return this.onSelect;
    }

    public static void select(InventoryItemPickerPanel panel, ItemPickerSelection itemPickerSelection) {
        panel.dispatchSelection(itemPickerSelection);
    }

    private void selectMappingEntry(ItemMappingEntry itemMappingEntry) {
        this.dispatchSelection(ItemPickerSelection.ofRight(itemMappingEntry));
        if (this.addSelectionAfterChoose) {
            this.selectedItemIds.add(itemMappingEntry.M());
        }
        this.showSearchView();
    }

    private PanelComponent createHeader(double width, String title, boolean showSlot, boolean showBackButton) {
        PanelComponent panelComponent = new PanelComponent(width, 28.0);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent.setShowDisabledOverlay(false);
        if (showSlot && this.filterRule instanceof SlotInventoryFilterRule) {
            SlotInventoryFilterRule slotRule = (SlotInventoryFilterRule)this.filterRule;
            PanelComponent panelComponent2 = new PanelComponent(10.0, 10.0);
            panelComponent2.setShowDisabledOverlay(true);
            panelComponent2.setDisabledOverlayColor(InventoryItemPickerPanel.J.y);
            panelComponent2.S(5);
            panelComponent.h(panelComponent2, new Object[0]);
            SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent(String.valueOf(slotRule.getSlot() + 1), 0.8, InventoryItemPickerPanel.J.A, true);
            simpleTextLabelComponent.setOffsetX(3.0f);
            panelComponent2.h(simpleTextLabelComponent, new Object[0]);
            panelComponent.h(panelComponent2, new Object[0]);
        } else {
            panelComponent.h(new SpacerComponent(0.0, 10.0), new Object[0]);
        }
        if (showBackButton) {
            GlyphIconComponent backButton = new GlyphIconComponent("back-hover@2x", 6.0, 6.0, 10.0, 10.0, InventoryItemPickerPanel.J.W, InventoryItemPickerPanel.J.f, null);
            backButton.setCenterVertically(true);
            backButton.setCenterHorizontally(true);
            backButton.addClickListener(this::showAllItemsView);
            panelComponent.h(new SpacerComponent(8.0, 0.0), "widthwrap");
            panelComponent.h(backButton, "widthwrap");
        }
        WrappingTextLabelComponent titleLabel = new WrappingTextLabelComponent(title, 1.0, InventoryItemPickerPanel.J.A);
        titleLabel.o(panelComponent.A() - (double)(showBackButton ? 36 : 0));
        titleLabel.setBold(true);
        titleLabel.setAcceptsMouseInput(false);
        panelComponent.h(titleLabel, new Object[0]);
        return panelComponent;
    }

    private void updateViewFromSearch() {
        if (this.searchInput.getText().trim().isEmpty() && !this.searchOnly) {
            this.showAllItemsView();
        } else {
            this.showSearchView();
        }
    }

    public void showAllItemsView() {
        this.allItemsPanel.setVisible(true);
        this.searchPanel.setVisible(false);
        this.categoryPanel.setVisible(false);
    }
}
