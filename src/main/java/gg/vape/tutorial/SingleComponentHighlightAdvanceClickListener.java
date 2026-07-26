package gg.vape.tutorial;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.tutorial.SingleComponentHighlightTutorialAction;
import gg.vape.tutorial.TutorialPage;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import java.awt.Point;

public class SingleComponentHighlightAdvanceClickListener
implements GuiMouseListener {
    final SingleComponentHighlightTutorialAction H;

    @Override
    public void I(Point point) {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SingleComponentHighlightAdvanceClickListener(SingleComponentHighlightTutorialAction singleComponentHighlightTutorialAction) {
        this.H = singleComponentHighlightTutorialAction;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        if (mouseClickButton != MouseClickButton.LEFT_CLICK) {
            return;
        }
        TutorialPage tutorialPage = this.H.T();
        if (tutorialPage.v() != null && tutorialPage.v().equals(this.H)) {
            Vape.INSTANCE.getTutorialManager().o().r();
        }
    }
}

