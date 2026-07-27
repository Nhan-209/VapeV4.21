package gg.vape.tutorial;

import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.tutorial.TutorialFinishedPanel;
import gg.vape.tutorial.TutorialFrame;
import gg.vape.tutorial.TutorialNextPromptPanel;
import gg.vape.tutorial.TutorialPage;
import gg.vape.tutorial.TutorialState;
import gg.vape.tutorial.TutorialWelcomePanel;
import gg.vape.tutorial.page.HudTutorialPage;
import gg.vape.tutorial.page.ModulesTutorialPage;
import gg.vape.tutorial.page.ProfilesTutorialPage;
import gg.vape.tutorial.page.TextGuiTutorialPage;
import java.util.ArrayList;
import java.util.List;

public class TutorialManager {
    private final TutorialFrame L;
    private List<TutorialPage> W = new ArrayList<TutorialPage>();
    private static final String b = "all complete";
    private TutorialState V = null;
    private TutorialPage l;

    public void V() {
        this.L.t$src$V$zbu1jn();
        this.L.H(new TutorialWelcomePanel());
        this.L.l$src$V$1mibm4x();
    }

    public void I(TutorialState tutorialState) {
        if (this.V != null && this.V.equals((Object)TutorialState.FINISHED) && !tutorialState.equals((Object)TutorialState.INDEX) && !tutorialState.equals((Object)TutorialState.WELCOME)) {
            return;
        }
        this.V = tutorialState;
        switch (tutorialState) {
            case WELCOME: {
                this.V();
                break;
            }
            case COMPLETED_ALL: {
                this.t();
                break;
            }
            case COMPLETED_TUTORIAL: {
                this.l();
            }
        }
    }

    public void P() {
        if (this.o() != null && this.V == TutorialState.IN_TUTORIAL) {
            this.o().v().w();
        }
    }

    public void B() {
        this.O(this.W.get(0));
    }

    public void X() {
        this.O(this.G());
    }

    public void l() {
        this.L.t$src$V$zbu1jn();
        this.L.H(new TutorialNextPromptPanel(this.l.Y(), this.G().Y()));
        this.L.l$src$V$1mibm4x();
    }

    public void O(TutorialPage tutorialPage) {
        this.I(TutorialState.IN_TUTORIAL);
        this.l = tutorialPage;
        this.L.t$src$V$zbu1jn();
        this.L.l$src$V$1mibm4x();
        tutorialPage.w();
    }

    public TutorialPage G() {
        int n = 0;
        for (int i = 0; i < this.W.size(); ++i) {
            TutorialPage tutorialPage = this.W.get(i);
            if (!tutorialPage.equals(this.l)) continue;
            n = i;
        }
        if (n + 1 >= this.W.size()) {
            return null;
        }
        return this.W.get(n + 1);
    }

    public void g() {
        boolean bl = this.x();
        if (bl != this.L.V$src$Z$1xhop3l()) {
            this.L.Z(bl);
        }
    }

    public TutorialPage o() {
        return this.l;
    }

    public void o$src$V$e4pt9h() {
        if (this.G() == null) {
            Vape.debugLog(b);
            this.I(TutorialState.COMPLETED_ALL);
        } else {
            this.I(TutorialState.COMPLETED_TUTORIAL);
        }
    }

    private boolean x() {
        if (this.V == TutorialState.IN_TUTORIAL && this.l != null) {
            return this.l.v() != null && this.l.v().X();
        }
        return this.V != TutorialState.FINISHED;
    }

    public void t() {
        this.L.t$src$V$zbu1jn();
        this.L.H(new TutorialFinishedPanel());
        this.L.l$src$V$1mibm4x();
    }


    private void v(TutorialPage tutorialPage) {
        this.W.add(tutorialPage);
        tutorialPage.a(this.L);
    }

    public TutorialManager() {
        this.L = ClientSettings.g(TutorialFrame.class);
        this.v(new ModulesTutorialPage());
        this.v(new ProfilesTutorialPage());
        this.v(new TextGuiTutorialPage());
        this.v(new HudTutorialPage());
    }
}

