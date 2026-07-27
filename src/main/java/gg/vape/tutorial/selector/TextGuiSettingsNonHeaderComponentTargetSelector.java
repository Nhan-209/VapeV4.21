package gg.vape.tutorial.selector;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.TextGuiTutorialPage;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.SettingsFrameHeaderComponent;

public class TextGuiSettingsNonHeaderComponentTargetSelector
extends TutorialTargetSelector<GuiComponent> {
    final TextGuiTutorialPage M;

    public TextGuiSettingsNonHeaderComponentTargetSelector(TextGuiTutorialPage textGuiTutorialPage, Class clazz) {
        super(clazz);
        this.M = textGuiTutorialPage;
    }

    @Override
    public boolean X(GuiComponent guiComponent) {
        boolean bl = !(guiComponent instanceof SettingsFrameHeaderComponent);
        return bl;
    }

}

