package gg.vape.ui.click.component;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.FilledSpacerComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class ActionButtonGroupComponent
extends GuiComponent {
    private final List<InteractiveComponent> O;
    private final PanelComponent K = new PanelComponent(this.A(), this.L());
    @Nullable
    private Color R;
    private final float b = 1.5f;
    private int Q = -1;
    private double i = 1.0;
    private final float I = 1.0f;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public double V$src$D$3dpaje() {
        return this.i;
    }

    public void T(double d) {
        this.i = d;
    }

    @Override
    public void H() {
        if (this.Z$src$Z$16e8vsp()) {
            GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.R != null ? this.R : ActionButtonGroupComponent.J.l, this.b, this.I, 1.0f);
        }
        this.K.K(this.G$src$D$1b2f02a());
        this.K.S(this.n());
        this.K.o(this.A());
        this.K.Y(this.L());
        List<InteractiveComponent> list = this.R$src$Ljava_util_List_$14uldez();
        if (this.Q != list.size()) {
            this.K.S();
            this.K.k(true);
            double d = (this.K.A() - this.i * 2.0) / (double)list.size();
            double d2 = (this.K.L() - this.i * 2.0) / 2.0;
            this.K.h(new SpacerComponent(0.0, this.K.L() / 2.0 - d2 / 2.0), "wrap");
            for (int i = 0; i < list.size(); ++i) {
                InteractiveComponent interactiveComponent = list.get(i);
                interactiveComponent.P(true);
                interactiveComponent.W(true);
                interactiveComponent.q(d);
                interactiveComponent.u(d2);
                this.K.h(interactiveComponent, new Object[0]);
                if (i == list.size() - 1) continue;
                this.K.H(new FilledSpacerComponent(1.0, d2, ActionButtonGroupComponent.J.l));
            }
            this.Q = list.size();
        }
    }

    public List<InteractiveComponent> R$src$Ljava_util_List_$14uldez() {
        ArrayList<InteractiveComponent> arrayList = new ArrayList<InteractiveComponent>();
        for (InteractiveComponent interactiveComponent : this.O) {
            if (!interactiveComponent.V$src$Z$1xhop3l()) continue;
            arrayList.add(interactiveComponent);
        }
        return arrayList;
    }

    public ActionButtonGroupComponent(InteractiveComponent ... interactiveComponentArray) {
        this(Arrays.asList(interactiveComponentArray));
    }

    public Color M() {
        return this.R;
    }

    @Override
    public double x() {
        return 0.0;
    }

    public ActionButtonGroupComponent(List<InteractiveComponent> list) {
        this.K.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.K.d(false);
        this.O = list;
        this.H(this.K);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public List<InteractiveComponent> a$src$Ljava_util_List_$agnlj0() {
        return this.O;
    }

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public void F() {
    }

    public void M(Color color) {
        this.R = color;
    }

    @Override
    public void u() {
    }

    public float X$src$F$3esvru() {
        return this.b;
    }

    @Override
    public void I() {
    }

    public float R() {
        return this.I;
    }
}

