package gg.vape.tutorial.selector;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.TextGuiTutorialPage;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.SettingsFrameHeaderComponent;
import java.util.ArrayList;
import java.util.Arrays;

public class SettingsFrameHeaderPrimaryButtonTargetSelector
extends TutorialTargetSelector<SettingsFrameHeaderComponent> {
    final TextGuiTutorialPage S;
    SettingsFrameHeaderComponent x;

    @Override
    public ArrayList<GuiComponent> v(GuiComponent guiComponent) {
        if (this.o().isInstance(guiComponent)) {
            ArrayList<GuiComponent> arrayList = super.v(guiComponent);
            if (arrayList != null && this.x != null && this.x.equals(guiComponent)) {
                return new ArrayList<GuiComponent>(Arrays.asList(this.x.x$src$Lgg_vape_ui_click_component_IconButtonComponent_$x1h5th()));
            }
            return arrayList;
        }
        return null;
    }

    public SettingsFrameHeaderPrimaryButtonTargetSelector(TextGuiTutorialPage textGuiTutorialPage, Class clazz) {
        super(clazz);
        this.S = textGuiTutorialPage;
        this.x = null;
    }

    public boolean i(SettingsFrameHeaderComponent settingsFrameHeaderComponent) {
        this.x = settingsFrameHeaderComponent;
        return true;
    }

    @Override
    public boolean X(SettingsFrameHeaderComponent settingsFrameHeaderComponent) {
        return this.i(settingsFrameHeaderComponent);
    }

}
