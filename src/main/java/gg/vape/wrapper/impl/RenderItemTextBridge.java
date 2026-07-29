package gg.vape.wrapper.impl;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.mappings.MAbstractGui;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.FontRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager$FogState;
import gg.vape.wrapper.impl.Matrix4fHandle;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.ResourceLocation;
import gg.vape.wrapper.impl.SoundEventRegistryName;
import gg.vape.wrapper.impl.TextureAtlasSprite;

public class RenderItemTextBridge
extends Wrapper {
    public static void x(MatrixStack matrixStack, int n, int n2, int n3, int n4, int n5, TextureAtlasSprite textureAtlasSprite) {
        if (ForgeVersion.MC_1_21_0.d()) {
            return;
        }
        if (ForgeVersion.MC_1_17.d()) {
            ResourceLocation resourceLocation = ForgeVersion.MC_1_20_6.d() ? new SoundEventRegistryName(textureAtlasSprite.e()).getRegistryName() : null;
            float[] fArray = textureAtlasSprite.j();
            RenderItemTextBridge.n(resourceLocation, matrixStack, n, n + n4, n2, n2 + n5, n3, fArray[0], fArray[1], fArray[2], fArray[3]);
            return;
        }
        MAbstractGui.x(RenderItemTextBridge.c.getMappings().qQ, null, matrixStack.getObject(), n, n2, n3, n4, n5, textureAtlasSprite.getObject());
    }


    public void P(FontRenderer fontRenderer, String string, int n, int n2, int n3, boolean bl) {
        RenderItemTextBridge.c.getMappings().qQ.Q(this.I, fontRenderer.getObject(), string, n, n2, n3, bl);
    }

    public RenderItemTextBridge(Object object) {
        super(object);
    }

    public static RenderItemTextBridge b(Object object, Matrix4fHandle matrix4fHandle, GlStateManager$FogState glStateManager$FogState) {
        if (ForgeVersion.MC_1_21_11.d()) {
            return new RenderItemTextBridge(MAbstractGui.y(RenderItemTextBridge.c.getMappings().qQ, object, matrix4fHandle.getObject(), glStateManager$FogState.getObject(), new Object[]{0, 0}));
        }
        return new RenderItemTextBridge(MAbstractGui.y(RenderItemTextBridge.c.getMappings().qQ, object, matrix4fHandle.getObject(), glStateManager$FogState.getObject(), new Object[0]));
    }

    public static RenderItemTextBridge t(MatrixStack matrixStack) {
        if (ForgeVersion.MC_1_21_11.d()) {
            return new RenderItemTextBridge(MAbstractGui.y(RenderItemTextBridge.c.getMappings().qQ, Minecraft.i(), Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().y().getObject(), 0, new Object[]{0}));
        }
        if (ForgeVersion.MC_1_21_6.d()) {
            if (matrixStack.getObject() == null) {
                return new RenderItemTextBridge(MAbstractGui.y(RenderItemTextBridge.c.getMappings().qQ, Minecraft.i(), Matrix4fHandle.b(16).getObject(), Minecraft.H$src$Lgg_vape_wrapper_impl_VoxelShape_$1dlcquv().getBoundingBox().getObject(), new Object[0]));
            }
            if (matrixStack.isInstance(MappedClasses.Dy)) {
                return RenderItemTextBridge.l(new Matrix4fHandle(matrixStack.getObject()));
            }
            Matrix4fHandle matrix4fHandle = Matrix4fHandle.b(16);
            matrix4fHandle.o();
            float[] fArray = matrixStack.F().u().m$src$Lgg_vape_utils_render_RenderMatrix4f_$1hodrum().elements;
            matrix4fHandle.K(fArray[0]);
            matrix4fHandle.m(fArray[1]);
            matrix4fHandle.z(fArray[4]);
            matrix4fHandle.b(fArray[5]);
            matrix4fHandle.p(fArray[12]);
            matrix4fHandle.T(fArray[13]);
            return RenderItemTextBridge.l(matrix4fHandle);
        }
        return new RenderItemTextBridge(MAbstractGui.y(RenderItemTextBridge.c.getMappings().qQ, Minecraft.i(), matrixStack.getObject(), Minecraft.H$src$Lgg_vape_wrapper_impl_VoxelShape_$1dlcquv().getBoundingBox().getObject(), new Object[0]));
    }

    public static RenderItemTextBridge l(Matrix4fHandle matrix4fHandle) {
        return RenderItemTextBridge.b(Minecraft.i(), matrix4fHandle, Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().y());
    }

    public void v() {
        RenderItemTextBridge.c.getMappings().qQ.j(this.I);
    }

    public Matrix4fHandle F() {
        return new Matrix4fHandle(RenderItemTextBridge.c.getMappings().qQ.B(this.I));
    }

    public static void n(ResourceLocation resourceLocation, MatrixStack matrixStack, int n, int n2, int n3, int n4, int n5, float f, float f2, float f3, float f4) {
        Object object = null;
        if (ForgeVersion.MC_1_21_0.d()) {
            return;
        }
        if (ForgeVersion.MC_1_20_6.d()) {
            object = RenderItemTextBridge.t(matrixStack).getObject();
            MAbstractGui.e(RenderItemTextBridge.c.getMappings().qQ, object, resourceLocation.getObject(), n, n2, n3, n4, n5, f, f2, f3, f4);
            return;
        }
        MAbstractGui.e(RenderItemTextBridge.c.getMappings().qQ, object, matrixStack.F().u().getObject(), n, n2, n3, n4, n5, f, f2, f3, f4);
    }

    public void k() {
        RenderItemTextBridge.c.getMappings().qQ.J(this.I);
    }

    public GlStateManager$FogState S() {
        return new GlStateManager$FogState(RenderItemTextBridge.c.getMappings().qQ.O(this.I));
    }

    public void a(int n, int n2, int n3, int n4) {
        RenderItemTextBridge.c.getMappings().qQ.J(this.I, n, n2, n3, n4);
    }
}

