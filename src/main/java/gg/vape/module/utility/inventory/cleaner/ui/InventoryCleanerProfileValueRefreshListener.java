package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.ProfileChangeEvent;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerProfileValueComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.module.ModuleComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.ClientSettingsSearchFrame;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrame;

public class InventoryCleanerProfileValueRefreshListener
implements EventListener {
    @EventHandler
    public void H(ProfileChangeEvent profileChangeEvent) {
        for (Frame frame : ClientSettings.a.Y()) {
            if (!(frame instanceof ModuleCategoryFrame)) continue;
            ModuleCategoryFrame moduleCategoryFrame = (ModuleCategoryFrame)frame;
            for (GuiComponent guiComponent : moduleCategoryFrame.f()) {
                if (!(guiComponent instanceof ModuleComponent)) continue;
                ModuleComponent moduleComponent = (ModuleComponent)guiComponent;
                for (GuiComponent guiComponent2 : moduleComponent.K$src$Ljava_util_List_$1hwj5d6()) {
                    if (!(guiComponent2 instanceof InventoryCleanerProfileValueComponent)) continue;
                    InventoryCleanerProfileValueComponent inventoryCleanerProfileValueComponent = (InventoryCleanerProfileValueComponent)guiComponent2;
                    inventoryCleanerProfileValueComponent.b$src$V$18h0koc();
                }
            }
        }
        ClientSettings.g(ClientSettingsSearchFrame.class).N$src$V$1ncxuwi();
    }

}

