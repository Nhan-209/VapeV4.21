package gg.vape.tutorial.selector;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.TextGuiTutorialPage;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.ClickGuiQuickActionsComponent;
import java.util.ArrayList;
import java.util.Arrays;

public class TextGuiQuickActionsSettingsButtonTargetSelector
extends TutorialTargetSelector<ClickGuiQuickActionsComponent> {
    final TextGuiTutorialPage C;
    ClickGuiQuickActionsComponent V;

    public boolean D(ClickGuiQuickActionsComponent clickGuiQuickActionsComponent) {
        this.V = clickGuiQuickActionsComponent;
        return true;
    }

    @Override
    public boolean X(ClickGuiQuickActionsComponent clickGuiQuickActionsComponent) {
        return this.D(clickGuiQuickActionsComponent);
    }

    public TextGuiQuickActionsSettingsButtonTargetSelector(TextGuiTutorialPage textGuiTutorialPage, Class clazz) {
        super(clazz);
        this.C = textGuiTutorialPage;
        this.V = null;
    }

    @Override
    public ArrayList<GuiComponent> v(GuiComponent guiComponent) {
        if (this.o().isInstance(guiComponent)) {
            ArrayList<GuiComponent> arrayList = super.v(guiComponent);
            if (arrayList != null && this.V != null && this.V.equals(guiComponent)) {
                return new ArrayList<GuiComponent>(Arrays.asList(this.V.b$src$Lgg_vape_ui_click_component_IconButtonComponent_$1sg98rj()));
            }
            return arrayList;
        }
        return null;
    }

}
