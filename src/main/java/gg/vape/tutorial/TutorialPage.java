package gg.vape.tutorial;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.tutorial.TutorialAction;
import gg.vape.tutorial.TutorialActionFinishClickHandler;
import gg.vape.tutorial.TutorialActionNextClickHandler;
import gg.vape.tutorial.TutorialFrame;
import gg.vape.ui.click.component.GuiComponent;
import java.util.ArrayList;
import java.util.List;

public class TutorialPage {
    private List<TutorialAction> A = new ArrayList<TutorialAction>();
    private TutorialAction y;
    private String i;
    private TutorialFrame H;
    private static final String c;
    private static GuiComponent[] q;

    public TutorialAction v() {
        return this.y;
    }

    public void i(TutorialAction tutorialAction) {
        this.A.add(tutorialAction);
        tutorialAction.I().G$src$Lgg_vape_ui_click_component_gui_TextButton_$82emrx().s(new TutorialActionNextClickHandler(this));
        tutorialAction.I().c$src$Lgg_vape_ui_click_component_gui_UnderlinedTextLa$npxkh1().s(new TutorialActionFinishClickHandler(this));
        tutorialAction.G(this);
    }

    public TutorialPage(String string) {
        this.i = string;
    }

    public void w() {
        this.y = null;
        this.r();
    }

    public String Y() {
        return this.i;
    }

    public static GuiComponent[] I() {
        return q;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static {
        TutorialPage.d(new GuiComponent[2]);
        c = "3 ";
    }

    public void c(TutorialAction tutorialAction) {
        if (this.y != null) {
            this.y.S();
        }
        this.y = tutorialAction;
        this.y.X$src$V$d06mc();
        if (this.H != null) {
            this.H.t$src$V$zbu1jn();
            this.H.h(tutorialAction.I(), new Object[0]);
            this.H.l$src$V$1mibm4x();
        }
    }

    public static void d(GuiComponent[] guiComponentArray) {
        q = guiComponentArray;
    }

    public void r() {
        Vape.debugLog("1");
        if (this.y != null) {
            if (!this.y.a()) {
                return;
            }
            boolean bl = false;
            boolean bl2 = false;
            Vape.debugLog("2");
            for (TutorialAction tutorialAction : this.A) {
                if (bl) {
                    this.c(tutorialAction);
                    bl2 = true;
                    break;
                }
                if (!tutorialAction.equals(this.y)) continue;
                bl = true;
            }
            Vape.debugLog(c + bl + " " + bl2);
            if (!bl2) {
                Vape.INSTANCE.getTutorialManager().o$src$V$e4pt9h();
            }
        } else {
            this.c(this.A.get(0));
        }
    }

    public void a(TutorialFrame tutorialFrame) {
        this.H = tutorialFrame;
    }
}

