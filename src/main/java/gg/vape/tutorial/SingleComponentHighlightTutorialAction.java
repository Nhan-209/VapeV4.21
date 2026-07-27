package gg.vape.tutorial;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.tutorial.HighlightTutorialAction;
import gg.vape.tutorial.SingleComponentHighlightAdvanceClickListener;
import gg.vape.tutorial.TutorialActionComponent;
import gg.vape.ui.click.component.GuiComponent;

public class SingleComponentHighlightTutorialAction
extends HighlightTutorialAction {
    private static final String g = "start ";


    public SingleComponentHighlightTutorialAction(GuiComponent guiComponent, String string, String string2, boolean bl) {
        super(new TutorialActionComponent(string, string2), bl);
        this.B().add(guiComponent);
    }

    public GuiComponent Q() {
        return this.B().size() > 0 ? this.B().get(0) : null;
    }

    @Override
    public RectData t() {
        GuiComponent guiComponent = this.Q();
        guiComponent.c();
        return new RectData(guiComponent.G$src$D$1b2f02a(), guiComponent.n(), guiComponent.A(), guiComponent.L());
    }

    @Override
    public void X$src$V$d06mc() {
        Vape.debugLog(g + this);
        if (this.V()) {
            SingleComponentHighlightAdvanceClickListener singleComponentHighlightAdvanceClickListener = new SingleComponentHighlightAdvanceClickListener(this);
            this.H(this.Q(), singleComponentHighlightAdvanceClickListener);
            this.I().G$src$Lgg_vape_ui_click_component_gui_TextButton_$82emrx().Z(false);
        }
    }

    @Override
    public boolean X() {
        return this.Q().V$src$Z$1xhop3l();
    }

    @Override
    public boolean boolean_X() {
        return this.X();
    }
}
