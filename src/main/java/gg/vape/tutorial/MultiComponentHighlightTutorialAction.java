package gg.vape.tutorial;

import func.skidline.RectData;
import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.tutorial.ClassTutorialTargetSelector;
import gg.vape.tutorial.HighlightTutorialAction;
import gg.vape.tutorial.MultiComponentHighlightAdvanceClickListener;
import gg.vape.tutorial.TutorialActionComponent;
import gg.vape.tutorial.TutorialTargetSelector;
import gg.vape.ui.click.component.GuiComponent;
import java.util.ArrayList;

public class MultiComponentHighlightTutorialAction
extends HighlightTutorialAction {
    private static final String g = "start ";
    private TutorialTargetSelector y;
    private final GuiComponent Q;

    @Override
    public boolean X() {
        return this.Q.V$src$Z$1xhop3l();
    }

    @Override
    public boolean boolean_X() {
        return this.X();
    }

    @Override
    public RectData t() {
        if (this.B().size() < 1) {
            return new RectData(0.0, 0.0, 0.0, 0.0);
        }
        GuiComponent guiComponent = null;
        GuiComponent guiComponent2 = null;
        for (int i = 0; i < this.B().size(); ++i) {
            GuiComponent guiComponent3 = this.B().get(i);
            if (!guiComponent3.V$src$Z$1xhop3l() && !this.M()) continue;
            guiComponent3.c();
            if (guiComponent == null) {
                guiComponent = guiComponent3;
            }
            guiComponent2 = guiComponent3;
        }
        if (guiComponent == null) {
            return new RectData(0.0, 0.0, 0.0, 0.0);
        }
        return new RectData(guiComponent.G$src$D$1b2f02a(), guiComponent.n(), guiComponent.A(), guiComponent2.n() + guiComponent2.L() - guiComponent.n());
    }

    @Override
    public void X$src$V$d06mc() {
        Vape.debugLog(g + this);
        for (GuiComponent object : this.Q.f()) {
            ArrayList<GuiComponent> arrayList = this.y.v(object);
            if (arrayList == null) continue;
            this.B().addAll(arrayList);
        }
        if (this.V()) {
            this.I().G$src$Lgg_vape_ui_click_component_gui_TextButton_$82emrx().Z(false);
            MultiComponentHighlightAdvanceClickListener multiComponentHighlightAdvanceClickListener = new MultiComponentHighlightAdvanceClickListener(this);
            for (GuiComponent guiComponent : this.B()) {
                this.H(guiComponent, multiComponentHighlightAdvanceClickListener);
            }
        }
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MultiComponentHighlightTutorialAction(GuiComponent guiComponent, Class clazz, String string, String string2, boolean bl) {
        this(guiComponent, new ClassTutorialTargetSelector(clazz, clazz), string, string2, bl);
    }

    public MultiComponentHighlightTutorialAction(GuiComponent guiComponent, TutorialTargetSelector tutorialTargetSelector, String string, String string2, boolean bl) {
        super(new TutorialActionComponent(string, string2), bl);
        this.y = tutorialTargetSelector;
        this.Q = guiComponent;
    }
}
