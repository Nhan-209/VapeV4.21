package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderBatchBuilder;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.RenderMatrix4f;
import gg.vape.utils.render.RenderVector4f;
import gg.vape.utils.render.VertexCoordinateMode;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.function.Supplier;

public class GuiInventory
extends Wrapper {
    private static Void lambda$drawEntityOnScreen$0(RenderMatrix4f renderMatrix4f, int n, float f, float f2, EntityLivingBase entityLivingBase) {
        Vape.INSTANCE.getMappings().Q_.H((int)renderMatrix4f.elements[0], (int)renderMatrix4f.elements[5], (int)((double)n * Vape.INSTANCE.getClientSettings().getGuiScaleFactor()), f, f2, entityLivingBase.getObject());
        return null;
    }

    public static void n(int n, int n2, int n3, float f, float f2, EntityLivingBase entityLivingBase) {
        if (ForgeVersion.MC_1_20_6.d()) {
            Vape.notifyNativeStackTrace();
        }
        if (GuiRenderPrimitives.d()) {
            RenderMatrix4f renderMatrix4f = new RenderMatrix4f(new RenderVector4f(n, n2, 0.0f, 1.0f)).multiply(BufferedGuiRenderPrimitives.matrixStack.peek());
            Supplier<Void> supplier = () -> GuiInventory.lambda$drawEntityOnScreen$0(renderMatrix4f, n3, f, f2, entityLivingBase);
            RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(VertexCoordinateMode.MINECRAFT, false).setStandaloneRenderCallback(supplier);
            RenderBatchManager.getInstance().queueGuiBatch(renderBatchBuilder);
            return;
        }
        Vape.INSTANCE.getMappings().Q_.H(n, n2, n3, f, f2, entityLivingBase.getObject());
    }

    public GuiInventory(Object object) {
        super(object);
    }

}

