package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.HudModule;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.GuiComponentContract;
import gg.vape.ui.click.component.input.BindValueRowComponent;
import gg.vape.ui.click.component.value.ValueComponentFactory;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleListEntry;
import gg.vape.value.Value;

public class HudModuleListEntryToggleClickListener
implements GuiClickListener {
    final HudModule Z;
    private static final String b = "Keybind";
    final HudModuleListEntry c;

    @Override
    public void P() {
        HudModuleConfigFrame hudModuleConfigFrame = ClientSettings.g(HudModuleConfigFrame.class);
        if (hudModuleConfigFrame == null) {
            return;
        }
        hudModuleConfigFrame.T(this.Z);
        hudModuleConfigFrame.S();
        for (Value<?, ?> value : this.Z.F$src$Ljava_util_List_$1kytx9u()) {
            GuiComponent guiComponent = ValueComponentFactory.Y(value);
            if (guiComponent == null) continue;
            if (value.getParent() != null) {
                guiComponent.T(GuiComponentContract.J.r);
            } else {
                guiComponent.T(GuiComponentContract.J.i);
            }
            hudModuleConfigFrame.h(guiComponent, new Object[0]);
        }
        if (this.Z.W()) {
            hudModuleConfigFrame.h(new BindValueRowComponent(b, this.Z.a()), new Object[0]);
        }
        HudModuleListEntry.x(this.c).Z(true);
        hudModuleConfigFrame.Z(true);
        hudModuleConfigFrame.U();
        hudModuleConfigFrame.t(hudModuleConfigFrame.L());
        hudModuleConfigFrame.R(1);
        hudModuleConfigFrame.l$src$V$1mibm4x();
    }


    public HudModuleListEntryToggleClickListener(HudModuleListEntry hudModuleListEntry, HudModule hudModule) {
        this.c = hudModuleListEntry;
        this.Z = hudModule;
    }
}

