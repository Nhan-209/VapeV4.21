package gg.vape.tutorial.selector;

import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.tutorial.page.HudTutorialPage;
import gg.vape.ui.click.frame.impl.hud.HudModuleListEntry;

public class HudTutorialNamedHudModuleRowSelector
extends TutorialTargetSelector<HudModuleListEntry> {
    final HudTutorialPage H;
    private static final String b = "Freelook";

    public HudTutorialNamedHudModuleRowSelector(HudTutorialPage hudTutorialPage, Class clazz) {
        super(clazz);
        this.H = hudTutorialPage;
    }

    public boolean P(HudModuleListEntry hudModuleListEntry) {
        return hudModuleListEntry.l$src$Lgg_vape_module_render_hud_HudModule_$jeyvjw().getName().equals(b);
    }

    @Override
    public boolean X(HudModuleListEntry hudModuleListEntry) {
        return this.P(hudModuleListEntry);
    }
}
