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
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    private final InventoryFilterRule kn;
    private final MaterialFilterCondition kw;
    private static final String fb = "newadd";

    private void lambda$null$0(ItemPickerSelection itemPickerSelection) {
        if (itemPickerSelection != null) {
            ItemFilterSelection itemFilterSelection = new ItemFilterSelection();
            itemFilterSelection.G(itemPickerSelection);
            this.kw.b(itemFilterSelection);
            this.C(itemFilterSelection);
        }
    }

    private void lambda$null$1(ItemPickerSelection itemPickerSelection) {
        ItemFilterSelection itemFilterSelection = this.kw.t(itemPickerSelection.N() != null ? (String)itemPickerSelection.N() : ((ItemMappingEntry)itemPickerSelection.X()).M());
        if (itemFilterSelection == null) {
            return;
        }
        MaterialFilterSelectionRow materialFilterSelectionRow = this.X(itemFilterSelection);
        if (materialFilterSelectionRow == null) {
            return;
        }
        this.Y(materialFilterSelectionRow);
    }

    @Override
    public void H() {
        super.H();
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L() + 2.0, MaterialFilterSelectionList.J.y, 2.0f, 0.75f, 1.0f);
    }

    private void lambda$new$2(GlyphIconComponent glyphIconComponent) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (ItemFilterSelection object2 : this.kw.U()) {
            arrayList.add(object2.J());
        }
        InventoryItemPickerPanel inventoryItemPickerPanel = new InventoryItemPickerPanel(this.kn, true, this.kn.q().c(), arrayList, this::lambda$null$0);
        inventoryItemPickerPanel.R(this::lambda$null$1);
        AnchoredPopupFrame anchoredPopupFrame = ClientSettings.g(glyphIconComponent, inventoryItemPickerPanel, AnchoredPopupFrame.class);
        anchoredPopupFrame.O(false);
        anchoredPopupFrame.C$src$V$nadrmg();
        anchoredPopupFrame.q(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), anchoredPopupFrame);
        anchoredPopupFrame.Z(new MaterialFilterSelectionListClosePopupMouseListener(this, anchoredPopupFrame));
    }

    @Nullable
    public MaterialFilterSelectionRow X(ItemFilterSelection itemFilterSelection) {
        for (GuiComponent guiComponent : this.f()) {
            PaddedComponent paddedComponent;
            MaterialFilterSelectionRow materialFilterSelectionRow;
            if (!(guiComponent instanceof PaddedComponent) || (materialFilterSelectionRow = (paddedComponent = (PaddedComponent)guiComponent).t(MaterialFilterSelectionRow.class)) == null || !itemFilterSelection.equals(materialFilterSelectionRow.y$src$Lgg_vape_module_utility_inventory_cleaner_ItemFi$17dg1qx())) continue;
            return materialFilterSelectionRow;
        }
        return null;
    }

    public MaterialFilterSelectionList(InventoryFilterRule inventoryFilterRule, MaterialFilterCondition materialFilterCondition, double d) {
        super(d, 14.0);
        this.kn = inventoryFilterRule;
        this.kw = materialFilterCondition;
        this.d(false);
        GlyphIconComponent glyphIconComponent = new GlyphIconComponent(fb, 7.0, 7.0, 14.0, 14.0, MaterialFilterSelectionList.J.B, MaterialFilterSelectionList.J.O, null);
        glyphIconComponent.E(MaterialFilterSelectionList.J.z, MaterialFilterSelectionList.J.M);
        glyphIconComponent.q(true);
        glyphIconComponent.R(true);
        glyphIconComponent.r(() -> this.lambda$new$2(glyphIconComponent));
        this.h(new PaddedComponent(3.0, 0.0, 3.0, 0.0, glyphIconComponent), new Object[0]);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void C(ItemFilterSelection itemFilterSelection) {
        MaterialFilterSelectionRow materialFilterSelectionRow = new MaterialFilterSelectionRow(itemFilterSelection);
        materialFilterSelectionRow.j(new MaterialFilterSelectionRemoveClickHandler(this, materialFilterSelectionRow));
        this.H(new PaddedComponent(0.0, 0.0, 0.0, 0.0, materialFilterSelectionRow));
    }

    public void Y(MaterialFilterSelectionRow materialFilterSelectionRow) {
        ArrayList<PaddedComponent> arrayList = new ArrayList<PaddedComponent>();
        this.kw.A(materialFilterSelectionRow.y$src$Lgg_vape_module_utility_inventory_cleaner_ItemFi$17dg1qx());
        for (GuiComponent guiComponent : this.f()) {
            PaddedComponent paddedComponent;
            MaterialFilterSelectionRow materialFilterSelectionRow2;
            if (!(guiComponent instanceof PaddedComponent) || !materialFilterSelectionRow.equals(materialFilterSelectionRow2 = (paddedComponent = (PaddedComponent)guiComponent).t(MaterialFilterSelectionRow.class))) continue;
            arrayList.add(paddedComponent);
        }
        for (GuiComponent guiComponent : arrayList) {
            this.I(guiComponent);
        }
    }
}

