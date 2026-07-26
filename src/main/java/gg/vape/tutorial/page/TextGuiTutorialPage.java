package gg.vape.tutorial.page;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.none.TextGuiSettingsFrame;
import gg.vape.tutorial.MultiComponentHighlightTutorialAction;
import gg.vape.tutorial.SingleComponentHighlightTutorialAction;
import gg.vape.tutorial.TutorialPage;
import gg.vape.tutorial.TutorialTooltipPlacement;
import gg.vape.tutorial.selector.SettingsFrameHeaderPrimaryButtonTargetSelector;
import gg.vape.tutorial.selector.SettingsFrameHeaderSecondaryButtonTargetSelector;
import gg.vape.tutorial.selector.TextGuiQuickActionsSettingsButtonTargetSelector;
import gg.vape.tutorial.selector.TextGuiSettingsNonHeaderComponentTargetSelector;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.ClickGuiQuickActionsComponent;
import gg.vape.ui.click.frame.SettingsFrameHeaderComponent;
import gg.vape.ui.click.frame.impl.quickactions.QuickActionsFrame;

public class TextGuiTutorialPage
extends TutorialPage {
    public TextGuiTutorialPage() {
        super("Overlays");
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.J, new TextGuiQuickActionsSettingsButtonTargetSelector(this, ClickGuiQuickActionsComponent.class), "Overlays Menu", "Open the overlays menu by clicking this button", true).E(TutorialTooltipPlacement.RIGHT));
        this.i(new SingleComponentHighlightTutorialAction(ClientSettings.g(QuickActionsFrame.class), "Overlays menu", "Here you can enable overlays in Vape. Overlays are frames that will render on-top of your screen while in game.", false).E(TutorialTooltipPlacement.RIGHT));
        this.i(new SingleComponentHighlightTutorialAction(ClientSettings.g(QuickActionsFrame.class).m$src$Lgg_vape_ui_click_frame_impl_quickactions_QuickA$1kmfigl(), "Text GUI", "Enable the Text GUI by clicking this button", true).E(TutorialTooltipPlacement.RIGHT));
        this.i(new SingleComponentHighlightTutorialAction(ClientSettings.g(TextGuiSettingsFrame.class), "Text GUI", "This is the Text GUI overlay. It will show you a list of the modules that you have enabled", false).E(TutorialTooltipPlacement.RIGHT));
        this.i(new SingleComponentHighlightTutorialAction(ClientSettings.g(TextGuiSettingsFrame.class).K$src$Lgg_vape_ui_click_frame_impl_TextGuiOverlayCompo$1shgn4i(), "Text GUI", "The enabled modules will appear below here", false).E(TutorialTooltipPlacement.RIGHT).y(50.0));
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.g(TextGuiSettingsFrame.class), new SettingsFrameHeaderPrimaryButtonTargetSelector(this, SettingsFrameHeaderComponent.class), "Open the Text GUI settings", "", true).E(TutorialTooltipPlacement.RIGHT));
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.g(TextGuiSettingsFrame.class), new TextGuiSettingsNonHeaderComponentTargetSelector(this, GuiComponent.class), "Text GUI settings", "Here you can adjust settings for the Text GUI", false).E(TutorialTooltipPlacement.RIGHT));
        this.i(new MultiComponentHighlightTutorialAction((GuiComponent)ClientSettings.g(TextGuiSettingsFrame.class), new SettingsFrameHeaderSecondaryButtonTargetSelector(this, SettingsFrameHeaderComponent.class), "Close the Text GUI settings", "", true).E(TutorialTooltipPlacement.RIGHT));
    }
}

