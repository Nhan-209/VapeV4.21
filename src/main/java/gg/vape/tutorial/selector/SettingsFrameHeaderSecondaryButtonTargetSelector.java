package gg.vape.tutorial.selector;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.TextGuiTutorialPage;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.SettingsFrameHeaderComponent;
import java.util.ArrayList;
import java.util.Arrays;

public class SettingsFrameHeaderSecondaryButtonTargetSelector
extends TutorialTargetSelector<SettingsFrameHeaderComponent> {
    final TextGuiTutorialPage p;
    SettingsFrameHeaderComponent I;

    public boolean o(SettingsFrameHeaderComponent settingsFrameHeaderComponent) {
        this.I = settingsFrameHeaderComponent;
        return true;
    }

    @Override
    public boolean X(SettingsFrameHeaderComponent settingsFrameHeaderComponent) {
        return this.o(settingsFrameHeaderComponent);
    }

    @Override
    public ArrayList<GuiComponent> v(GuiComponent guiComponent) {
        if (this.o().isInstance(guiComponent)) {
            ArrayList<GuiComponent> arrayList = super.v(guiComponent);
            if (arrayList != null && this.I != null && this.I.equals(guiComponent)) {
                return new ArrayList<GuiComponent>(Arrays.asList(this.I.x$src$Lgg_vape_ui_click_component_IconButtonComponent_$x1h5th()));
            }
            return arrayList;
        }
        return null;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SettingsFrameHeaderSecondaryButtonTargetSelector(TextGuiTutorialPage textGuiTutorialPage, Class clazz) {
        super(clazz);
        this.p = textGuiTutorialPage;
        this.I = null;
    }
}
