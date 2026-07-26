package gg.vape.tutorial;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.tutorial.QueuedTutorialMessage;
import gg.vape.tutorial.TutorialAction;
import gg.vape.tutorial.TutorialActionComponent;
import gg.vape.tutorial.TutorialFrame;
import gg.vape.tutorial.TutorialTooltipPlacement;
import gg.vape.ui.click.GuiMouseListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public abstract class HighlightTutorialAction
extends TutorialAction {
    private double e;
    private TutorialTooltipPlacement h = TutorialTooltipPlacement.TOP;
    private boolean L;
    private int u = 1;
    private boolean X;
    private double z;
    private HashMap<GuiComponent, GuiMouseListener> N;
    private Queue<QueuedTutorialMessage> T = new ArrayDeque<QueuedTutorialMessage>();
    private final List<GuiComponent> B;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public TutorialTooltipPlacement j() {
        return this.h;
    }

    public void V(Frame frame, double d, double d2, double d3, double d4, TutorialTooltipPlacement tutorialTooltipPlacement) {
        double d5;
        double d6;
        double d7;
        double d8;
        double d9;
        if (tutorialTooltipPlacement == TutorialTooltipPlacement.TOP || tutorialTooltipPlacement == TutorialTooltipPlacement.BOTTOM) {
            d9 = 8.0;
            d8 = 18.0;
            d7 = 0.0;
            d6 = 0.0;
            d5 = 0.0;
            double d10 = d + d3 / 2.0;
            if (tutorialTooltipPlacement == TutorialTooltipPlacement.TOP) {
                d7 = d2 - frame.L() - 2.0;
                d6 = -d9;
                d5 = frame.L();
            } else {
                d7 = d2 + d4 + 2.0;
                d6 = d9;
                d5 = 0.0;
            }
            frame.K(d10 - frame.A() / 2.0);
            frame.S(d7 + d6);
            GuiRenderPrimitives.U(d10 - d8 / 2.0, (d7 += d5) + d6, d10, d7, d10 + d8 / 2.0, d7 + d6, new Color(26, 25, 26));
        }
        if (tutorialTooltipPlacement == TutorialTooltipPlacement.LEFT || tutorialTooltipPlacement == TutorialTooltipPlacement.RIGHT) {
            d9 = 10.0;
            d8 = 8.0;
            d7 = 0.0;
            d6 = d2 + d4 / 2.0;
            d5 = 0.0;
            if (tutorialTooltipPlacement == TutorialTooltipPlacement.LEFT) {
                d7 = -frame.A() - d8 - 3.0;
                d5 = d - d8 - 4.0;
                GuiRenderPrimitives.U(d5 + d8, d6, d5, d6 - d9 / 2.0, d5, d6 + d9 / 2.0, new Color(26, 25, 26));
            } else {
                d7 = d3 + d8 + 3.0;
                d5 = d + d7;
                GuiRenderPrimitives.U(d5 - d8, d6, d5, d6 - d9 / 2.0, d5, d6 + d9 / 2.0, new Color(26, 25, 26));
            }
            frame.K(d + d7);
            frame.S(d6 - frame.L() / 2.0);
        }
    }

    @Override
    public void w() {
        TutorialFrame tutorialFrame = ClientSettings.g(TutorialFrame.class);
        if (!this.X()) {
            return;
        }
        RectData rectData = this.t();
        if (this.e != 0.0) {
            rectData.A(this.e);
        }
        if (this.z != 0.0) {
            rectData.U(this.z);
        }
        GuiRenderPrimitives.t(rectData.o() - 2.0, rectData.W() - 2.0, rectData.e() + 3.0, rectData.R() + 4.0, ClientSettings.fW.O$src$Ljava_awt_Color_$19t4jn1().brighter().brighter());
        this.V(tutorialFrame, rectData.o(), rectData.W(), rectData.e(), rectData.R(), this.j());
    }

    public boolean V() {
        return this.L;
    }

    @Override
    public void S() {
        Vape.debugLog("completed stage");
        for (GuiComponent guiComponent : this.B) {
            Vape.debugLog("removed " + guiComponent + " " + this.N.get(guiComponent));
            guiComponent.E(this.N.get(guiComponent));
        }
        this.B.clear();
        this.N.clear();
    }

    @Override
    public boolean a() {
        if (this.T.isEmpty()) {
            return true;
        }
        QueuedTutorialMessage queuedTutorialMessage = this.T.poll();
        this.I().H(queuedTutorialMessage.b);
        this.I().e(queuedTutorialMessage.E);
        this.I().G$src$Lgg_vape_ui_click_component_gui_TextButton_$82emrx().d("Ok (" + (this.u - this.T.size()) + "/" + this.u + ")");
        return false;
    }

    public void H(GuiComponent guiComponent, GuiMouseListener guiMouseListener) {
        Vape.debugLog("adding listener " + guiComponent + " " + guiMouseListener);
        guiComponent.j(guiMouseListener);
        this.N.put(guiComponent, guiMouseListener);
    }

    public boolean M() {
        return this.X;
    }

    public HighlightTutorialAction y(double d) {
        this.z = d;
        return this;
    }

    @Override
    public boolean X() {
        return false;
    }

    public HighlightTutorialAction j(boolean bl) {
        this.X = bl;
        return this;
    }

    public HighlightTutorialAction E(TutorialTooltipPlacement tutorialTooltipPlacement) {
        this.h = tutorialTooltipPlacement;
        return this;
    }

    public abstract RectData t();

    public HighlightTutorialAction u(double d) {
        this.e = d;
        return this;
    }

    public HighlightTutorialAction h(String string, String string2) {
        this.T.add(new QueuedTutorialMessage(string, string2));
        this.I().G$src$Lgg_vape_ui_click_component_gui_TextButton_$82emrx().d("Ok (1/" + ++this.u + ")");
        return this;
    }

    public HighlightTutorialAction(TutorialActionComponent tutorialActionComponent, boolean bl) {
        super(tutorialActionComponent);
        this.N = new HashMap();
        this.B = new ArrayList<GuiComponent>();
        this.L = bl;
    }

    public List<GuiComponent> B() {
        return this.B;
    }
}

