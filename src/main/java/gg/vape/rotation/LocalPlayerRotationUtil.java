package gg.vape.rotation;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.RenderMatrix4f;
import gg.vape.wrapper.impl.EntityRenderer;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.Matrix4f;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;

public class LocalPlayerRotationUtil {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void Q(float f) {
        Matrix4f matrix4f;
        if (Minecraft.D().getActiveRenderInfo().isNull()) {
            return;
        }
        EntityRenderer entityRenderer = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf();
        GameSettings gameSettings = Minecraft.gameSettings();
        Matrix4f matrix4f2 = entityRenderer.l(entityRenderer.l(), f, true);
        if (matrix4f2.isNull()) {
            return;
        }
        MatrixStack matrixStack = MatrixStack.A();
        entityRenderer.B(matrixStack, f);
        if (gameSettings.k()) {
            entityRenderer.Z(matrixStack, f);
        }
        if ((matrix4f = matrixStack.F().u()).isNull()) {
            BufferedGuiRenderPrimitives.k = matrix4f2.m$src$Lgg_vape_utils_render_RenderMatrix4f_$1hodrum();
            BufferedGuiRenderPrimitives.l = new RenderMatrix4f().b();
            return;
        }
        matrix4f2.a(matrix4f);
        BufferedGuiRenderPrimitives.k = matrix4f2.m$src$Lgg_vape_utils_render_RenderMatrix4f_$1hodrum();
        BufferedGuiRenderPrimitives.l = new RenderMatrix4f().b();
    }

    public static void t() {
        float f = 0.0f;
        float f2 = (float)Minecraft.p().I() / 2.0f;
        float f3 = (float)Minecraft.p().R() / 2.0f;
        float f4 = 0.0f;
        float f5 = -21000.0f;
        float f6 = 21000.0f;
        BufferedGuiRenderPrimitives.k = new RenderMatrix4f().b().e(f, f2, f3, f4, f6, f5);
        BufferedGuiRenderPrimitives.l = new RenderMatrix4f().b();
    }
}

