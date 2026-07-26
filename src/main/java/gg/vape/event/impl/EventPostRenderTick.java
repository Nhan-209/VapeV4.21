package gg.vape.event.impl;

import gg.vape.event.impl.EventRenderTickBase;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiScreenNativeCallbackBridge;
import gg.vape.util.RenderThreadTaskQueue;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderBatchManager;

public class EventPostRenderTick
extends EventRenderTickBase {
    private static final String b;

    public EventPostRenderTick() {
        super(-1.0f);
    }

    public EventPostRenderTick(float f) {
        super(f);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean fire() {
        GuiScreenNativeCallbackBridge.drawScreen(null, 0, 0, 0.0f);
        if (GuiRenderPrimitives.d()) {
            RenderThreadTaskQueue.t();
            RenderBatchManager.M().G(this.getTicks());
        }
        boolean bl = super.fire();
        GuiRenderPrimitives.l(b);
        return bl;
    }

    static {
        try {
            b = "Post render";
        }
        catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }
}

