package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.value.ListValueComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.value.ListValue;
import java.awt.Color;

public class CompactListValueComponent
extends ListValueComponent {
    private final boolean bo;
    private final ListValue bW;

    @Override
    public double x() {
        return 10.0;
    }

    public CompactListValueComponent(ListValue listValue) {
        super(listValue);
        this.w("Open " + listValue.getName());
        this.bW = listValue;
        this.bo = listValue.getName().contains("blacklist") || listValue.getName().contains("blocked");
    }

    @Override
    public void H() {
    }

    @Override
    public void c() {
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.P$src$Z$og01j6() ? CompactListValueComponent.J.m : CompactListValueComponent.J.i);
        GuiRenderPrimitives.P(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), this.P$src$Z$og01j6() ? CompactListValueComponent.J.y : CompactListValueComponent.J.l, 1.0f, 0.75f, 1.0f);
        super.c();
        Color color = this.d$src$Z$oqzxee() ? CompactListValueComponent.J.A : (this.P$src$Z$og01j6() ? CompactListValueComponent.J.A : CompactListValueComponent.J.Z);
        float f = (float)(this.n() + this.L() / 2.0) - 2.0f;
        float f2 = (float)this.G$src$D$1b2f02a() + 2.5f;
        if (this.bo) {
            ImageRenderer.E(color, f2, f, "newblockedlist", 5.0f, 5.0f, false);
            ImageRenderer.E(CompactListValueComponent.J.d, f2, f, "newblocked", 5.0f, 5.0f, false);
        } else {
            ImageRenderer.E(color, f2, f, "newallowedlist", 5.0f, 5.0f, false);
            ImageRenderer.E(CompactListValueComponent.J.B, f2, f, "newallowed", 5.0f, 5.0f, false);
        }
    }


    @Override
    public double C() {
        return 10.0;
    }

    @Override
    public void I() {
    }
}

