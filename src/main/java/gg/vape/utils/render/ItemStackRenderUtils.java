package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderItem;
import gg.vape.wrapper.impl.RenderItemTextBridge;

public class ItemStackRenderUtils {
    private static String u;

    public static void O(RenderItemTextBridge renderItemTextBridge, ItemStack itemStack, int n, int n2) {
        Vape.INSTANCE.getMappingsMapperCompat().DI.M(renderItemTextBridge.getObject(), itemStack.getObject(), n, n2);
    }

    public static void g(ItemStack itemStack, int n, int n2) {
        if (ForgeVersion.MC_1_20_6.d()) {
            MatrixStack matrixStack = MatrixStack.A();
            matrixStack.H();
            matrixStack.i(BufferedGuiRenderPrimitives.X.c().u());
            RenderItemTextBridge renderItemTextBridge = RenderItemTextBridge.t(matrixStack);
            ItemStackRenderUtils.O(renderItemTextBridge, itemStack, n, n2);
        } else if (ForgeVersion.MC_1_7_10.L()) {
            RenderItem renderItem = RenderItem.d();
            renderItem.c(Minecraft.getFontRenderer(), Minecraft.Z(), itemStack, n, n2);
        } else {
            RenderItem renderItem = Minecraft.v();
            renderItem.c(Minecraft.getFontRenderer(), Minecraft.Z(), itemStack, n, n2);
        }
    }

    public static String E() {
        return u;
    }

    public static void V(String string) {
        u = string;
    }

    static {
        if (ItemStackRenderUtils.E() != null) {
            ItemStackRenderUtils.V("kN9BPb");
        }
    }
}

