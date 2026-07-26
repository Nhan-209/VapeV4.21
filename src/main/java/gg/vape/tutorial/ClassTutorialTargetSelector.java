package gg.vape.tutorial;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.ui.click.component.GuiComponent;

class ClassTutorialTargetSelector
extends TutorialTargetSelector<GuiComponent> {
    final Class V;

    @Override
    public boolean X(GuiComponent guiComponent) {
        return this.V.isInstance(guiComponent);
    }

    ClassTutorialTargetSelector(Class clazz, Class clazz2) {
        super(clazz);
        this.V = clazz2;
    }
}

