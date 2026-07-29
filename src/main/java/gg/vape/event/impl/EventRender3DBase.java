package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.wrapper.impl.MatrixStack;

public abstract class EventRender3DBase
extends Event {
    private final float V;
    private final MatrixStack B;
    private static GuiComponent[] U;

    EventRender3DBase(MatrixStack bm_02, float f) {
        this.B = bm_02;
        this.V = f;
    }

    public static GuiComponent[] v() {
        return U;
    }

    public float getTicks() {
        return this.V;
    }

    public MatrixStack getMatrixStack() {
        return this.B;
    }

    @Override
    public boolean fire() {
        boolean bl = super.fire();
        if (GuiRenderPrimitives.d()) {
            RenderBatchManager.getInstance().flushWorldBatches(this.V);
        }
        return bl;
    }


    public static void t(GuiComponent[] upArray) {
        U = upArray;
    }

    static {
        if (EventRender3DBase.v() == null) {
            EventRender3DBase.t(new GuiComponent[5]);
        }
    }
}

