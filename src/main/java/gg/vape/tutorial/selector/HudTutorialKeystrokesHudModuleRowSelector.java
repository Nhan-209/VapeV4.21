package gg.vape.tutorial.selector;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.HudTutorialPage;
import gg.vape.ui.click.frame.impl.hud.HudModuleListEntry;

public class HudTutorialKeystrokesHudModuleRowSelector
extends TutorialTargetSelector<HudModuleListEntry> {
    final HudTutorialPage q;
    private static final String b = "Keystrokes";

    public HudTutorialKeystrokesHudModuleRowSelector(HudTutorialPage hudTutorialPage, Class clazz) {
        super(clazz);
        this.q = hudTutorialPage;
    }

    public boolean W(HudModuleListEntry hudModuleListEntry) {
        return hudModuleListEntry.l$src$Lgg_vape_module_render_hud_HudModule_$jeyvjw().getName().equals(b);
    }

    @Override
    public boolean X(HudModuleListEntry hudModuleListEntry) {
        return this.W(hudModuleListEntry);
    }
}
