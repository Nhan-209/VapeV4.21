package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.MaterialFilterCondition;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryItemPickerPanel;
import gg.vape.module.utility.inventory.cleaner.ui.ItemPickerSelection;
import gg.vape.module.utility.inventory.cleaner.ui.MaterialFilterSelectionListClosePopupMouseListener;
import gg.vape.module.utility.inventory.cleaner.ui.MaterialFilterSelectionRemoveClickHandler;
import gg.vape.module.utility.inventory.cleaner.ui.MaterialFilterSelectionRow;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.AnchoredPopupFrame;
import gg.vape.ui.click.frame.ScrollableFrameComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;

public class MaterialFilterSelectionList
extends ScrollableFrameComponent {
    private final InventoryFilterRule filterRule;
    private final MaterialFilterCondition materialCondition;
    private static final String ADD_ICON = "newadd";

    private void addPickerSelection(ItemPickerSelection itemPickerSelection) {
        if (itemPickerSelection != null) {
            ItemFilterSelection itemFilterSelection = new ItemFilterSelection();
            itemFilterSelection.setSelection(itemPickerSelection);
            this.materialCondition.addSelection(itemFilterSelection);
            this.addSelectionRow(itemFilterSelection);
        }
    }

    private void removePickerSelection(ItemPickerSelection itemPickerSelection) {
        ItemFilterSelection itemFilterSelection = this.materialCondition.findSelectionById(itemPickerSelection.getLeft() != null ? (String)itemPickerSelection.getLeft() : ((ItemMappingEntry)itemPickerSelection.getRight()).M());
        if (itemFilterSelection == null) {
            return;
        }
        MaterialFilterSelectionRow materialFilterSelectionRow = this.findSelectionRow(itemFilterSelection);
        if (materialFilterSelectionRow == null) {
            return;
        }
        this.removeSelectionRow(materialFilterSelectionRow);
    }

    @Override
    public void H() {
        super.H();
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L() + 2.0, MaterialFilterSelectionList.J.y, 2.0f, 0.75f, 1.0f);
    }

    private void openItemPicker(GlyphIconComponent glyphIconComponent) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (ItemFilterSelection selection : this.materialCondition.getSelections()) {
            arrayList.add(selection.getItemName());
        }
        InventoryItemPickerPanel inventoryItemPickerPanel = new InventoryItemPickerPanel(this.filterRule, true, this.filterRule.getItemSelection().getMatcher(), arrayList, this::addPickerSelection);
        inventoryItemPickerPanel.setOnExistingSelection(this::removePickerSelection);
        AnchoredPopupFrame anchoredPopupFrame = ClientSettings.createPopup(glyphIconComponent, inventoryItemPickerPanel, AnchoredPopupFrame.class);
        anchoredPopupFrame.O(false);
        anchoredPopupFrame.C$src$V$nadrmg();
        anchoredPopupFrame.q(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), anchoredPopupFrame);
        anchoredPopupFrame.addGlobalMouseListener(new MaterialFilterSelectionListClosePopupMouseListener(this, anchoredPopupFrame));
    }

    @Nullable
    public MaterialFilterSelectionRow findSelectionRow(ItemFilterSelection itemFilterSelection) {
        for (GuiComponent guiComponent : this.f()) {
            PaddedComponent paddedComponent;
            MaterialFilterSelectionRow materialFilterSelectionRow;
            if (!(guiComponent instanceof PaddedComponent) || (materialFilterSelectionRow = (paddedComponent = (PaddedComponent)guiComponent).t(MaterialFilterSelectionRow.class)) == null || !itemFilterSelection.equals(materialFilterSelectionRow.getSelection())) continue;
            return materialFilterSelectionRow;
        }
        return null;
    }

    public MaterialFilterSelectionList(InventoryFilterRule inventoryFilterRule, MaterialFilterCondition materialFilterCondition, double d) {
        super(d, 14.0);
        this.filterRule = inventoryFilterRule;
        this.materialCondition = materialFilterCondition;
        this.setShowDisabledOverlay(false);
        GlyphIconComponent glyphIconComponent = new GlyphIconComponent(ADD_ICON, 7.0, 7.0, 14.0, 14.0, MaterialFilterSelectionList.J.B, MaterialFilterSelectionList.J.O, null);
        glyphIconComponent.setBackgroundAnimationColors(MaterialFilterSelectionList.J.z, MaterialFilterSelectionList.J.M);
        glyphIconComponent.setCenterHorizontally(true);
        glyphIconComponent.setCenterVertically(true);
        glyphIconComponent.addClickListener(() -> this.openItemPicker(glyphIconComponent));
        this.h(new PaddedComponent(3.0, 0.0, 3.0, 0.0, glyphIconComponent), new Object[0]);
    }


    public void addSelectionRow(ItemFilterSelection itemFilterSelection) {
        MaterialFilterSelectionRow materialFilterSelectionRow = new MaterialFilterSelectionRow(itemFilterSelection);
        materialFilterSelectionRow.addMouseListener(new MaterialFilterSelectionRemoveClickHandler(this, materialFilterSelectionRow));
        this.addChildren(new PaddedComponent(0.0, 0.0, 0.0, 0.0, materialFilterSelectionRow));
    }

    public void removeSelectionRow(MaterialFilterSelectionRow materialFilterSelectionRow) {
        ArrayList<PaddedComponent> arrayList = new ArrayList<PaddedComponent>();
        this.materialCondition.removeSelection(materialFilterSelectionRow.getSelection());
        for (GuiComponent guiComponent : this.f()) {
            PaddedComponent paddedComponent;
            MaterialFilterSelectionRow materialFilterSelectionRow2;
            if (!(guiComponent instanceof PaddedComponent) || !materialFilterSelectionRow.equals(materialFilterSelectionRow2 = (paddedComponent = (PaddedComponent)guiComponent).t(MaterialFilterSelectionRow.class))) continue;
            arrayList.add(paddedComponent);
        }
        for (GuiComponent guiComponent : arrayList) {
            this.removeChild(guiComponent);
        }
    }
}

