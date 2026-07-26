package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfileValue;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerPopupFrame;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerProfileRow;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import java.awt.Point;

public class InventoryCleanerProfileRowClickHandler
implements GuiMouseListener {
    final InventoryCleanerProfile v;
    final InventoryCleanerProfileValue r;
    final Runnable J;
    final InventoryCleanerProfileRow O;

    private static void lambda$onClick$0(Runnable runnable) {
        ClientSettings.f6.execute(runnable);
    }

    public InventoryCleanerProfileRowClickHandler(InventoryCleanerProfileRow inventoryCleanerProfileRow, InventoryCleanerProfile inventoryCleanerProfile, InventoryCleanerProfileValue inventoryCleanerProfileValue, Runnable runnable) {
        this.O = inventoryCleanerProfileRow;
        this.v = inventoryCleanerProfile;
        this.r = inventoryCleanerProfileValue;
        this.J = runnable;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        if (this.v.equals(this.r.K())) {
            InventoryCleanerPopupFrame inventoryCleanerPopupFrame = ClientSettings.g(InventoryCleanerPopupFrame.class);
            inventoryCleanerPopupFrame.t(this.r, this.v, () -> InventoryCleanerProfileRowClickHandler.lambda$onClick$0(this.J));
            inventoryCleanerPopupFrame.Z$src$V$zty34m();
            if (ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v() instanceof ClickGuiFrameManager) {
                ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
                inventoryCleanerPopupFrame.d(clickGuiFrameManager);
                clickGuiFrameManager.K(inventoryCleanerPopupFrame);
            } else {
                inventoryCleanerPopupFrame.d((FrameStackManager)null);
                ClientSettings.fW.I(ClientSettings.p);
            }
        } else {
            this.r.o(this.v);
        }
    }
}

