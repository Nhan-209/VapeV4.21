package gg.vape.tutorial;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.tutorial.MultiComponentHighlightTutorialAction;
import gg.vape.tutorial.TutorialPage;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.MouseClickButton;
import java.awt.Point;

class MultiComponentHighlightAdvanceClickListener
implements GuiMouseListener {
    final MultiComponentHighlightTutorialAction o;

    @Override
    public void I(Point point) {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    MultiComponentHighlightAdvanceClickListener(MultiComponentHighlightTutorialAction multiComponentHighlightTutorialAction) {
        this.o = multiComponentHighlightTutorialAction;
    }

    @Override
    public void g(Point point, MouseClickButton mouseClickButton) {
        if (mouseClickButton != MouseClickButton.LEFT_CLICK) {
            return;
        }
        TutorialPage tutorialPage = this.o.T();
        if (tutorialPage.v() != null && tutorialPage.v().equals(this.o)) {
            Vape.INSTANCE.getTutorialManager().o().r();
        }
    }
}

