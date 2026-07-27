package gg.vape.utils.render;

import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GlFramebuffer;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GlScissorRect;
import gg.vape.utils.render.ItemIconRenderBackend;
import gg.vape.utils.render.ItemStackRenderUtils;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.Post117RenderPhaseCompat;
import gg.vape.utils.render.RenderBatchBuilder;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.RenderMatrix4f;
import gg.vape.utils.render.VertexCoordinateMode;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.BlockStateContainerBridge;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.MainWindow;
import gg.vape.wrapper.impl.Matrix4fHandle;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderBufferBridge;
import gg.vape.wrapper.impl.RenderItem;
import gg.vape.wrapper.impl.RenderItemTextBridge;
import gg.vape.wrapper.impl.StringTextComponent;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class Post117ItemIconFramebufferRenderer
implements ItemIconRenderBackend {
    GlFramebuffer T;

    @Override
    public void N(ItemStack itemStack, float f) {
        RenderBatchManager.M().G(0.0f);
        int n = 32;
        int n2 = 32;
        int n3 = GL11.glGetInteger((int)36006);
        int n4 = GL11.glGetInteger((int)32873);
        boolean bl = GL11.glIsEnabled((int)3089);
        if (bl) {
            OpenGlBackendHolder.d.u$src$V$hntn98(3089);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(64);
            byteBuffer.order(ByteOrder.nativeOrder());
            IntBuffer intBuffer = byteBuffer.asIntBuffer();
            gg.vape.wrapper.impl.GL11.X(2978, intBuffer);
            this.T = new GlFramebuffer(n, n2, true);
            this.T.f(true);
            GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
            GL11.glClear((int)16384);
            GL11.glClear((int)256);
            GlStateManager.enableDepth();
            GlStateManager.enableBlend();
            MatrixStack matrixStack = MatrixStack.A();
            matrixStack.H();
            float f2 = Minecraft.p().k(Minecraft.gameSettings().T(), false);
            float f3 = 1.0f / f2;
            matrixStack.S(f3, f3, f3);
            matrixStack.S((float)Minecraft.J() / 32.0f, (float)Minecraft.h() / 32.0f, 0.0f);
            OpenGlBackendHolder.d.m();
            if (ForgeVersion.MC_1_20_6.d()) {
                RenderItemTextBridge renderItemTextBridge;
                Object object;
                if (ForgeVersion.MC_1_21_6.d()) {
                    object = Matrix4fHandle.b(16);
                    renderItemTextBridge = RenderItemTextBridge.l((Matrix4fHandle)object);
                } else {
                    renderItemTextBridge = RenderItemTextBridge.t(matrixStack);
                }
                if (ForgeVersion.MC_1_21_6.d()) {
                    renderItemTextBridge.S().Q();
                }
                ItemStackRenderUtils.O(renderItemTextBridge, itemStack, 0, -100);
                if (ForgeVersion.MC_1_21_10.d()) {
                    float f4;
                    float f5;
                    int n5;
                    Object v;
                    Wrapper wrapper;
                    List<String> fogEntries = renderItemTextBridge.S().getFogMode().F();
                    if (fogEntries == null || fogEntries.isEmpty()) {
                        OpenGlBackendHolder.d.F();
                        return;
                    }
                    StringTextComponent stringTextComponent = new StringTextComponent(fogEntries.get(fogEntries.size() - 1));
                    RenderBufferBridge renderBufferBridge = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().V();
                    renderBufferBridge.j();
                    Post117RenderPhaseCompat.P();
                    if (ForgeVersion.MC_26_1.d()) {
                        wrapper = renderBufferBridge.P();
                        v = wrapper.isNull() ? null : ((BlockStateContainerBridge)wrapper).w(stringTextComponent.H());
                        n5 = wrapper.isNull() ? -1 : ((BlockStateContainerBridge)wrapper).I();
                    } else {
                        v = renderBufferBridge.p().get(stringTextComponent.H().m$src$Ljava_lang_Object_$hvczij());
                        int n6 = n5 = renderBufferBridge.F().isNull() ? -1 : renderBufferBridge.F().J();
                    }
                    if (v == null || n5 == -1) {
                        OpenGlBackendHolder.d.F();
                        return;
                    }
                    wrapper = new MainWindow(v);
                    float f6 = ((MainWindow)wrapper).q();
                    float f7 = ((MainWindow)wrapper).r();
                    if (ForgeVersion.MC_26_1.d()) {
                        f5 = ((MainWindow)wrapper).i();
                        f4 = ((MainWindow)wrapper).A();
                    } else {
                        int n7 = Minecraft.p().P();
                        int n8 = 16 * n7;
                        int n9 = renderBufferBridge.L(n8);
                        f5 = f6 + (float)n8 / (float)n9;
                        f4 = f7 + (float)(-n8) / (float)n9;
                    }
                    this.T.f(true);
                    GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
                    GlScissorRect glScissorRect = BufferedGuiRenderPrimitives.u;
                    BufferedGuiRenderPrimitives.u = null;
                    RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().o(new GlImageTexture(n5)).e(0.0f, 0.0f, n, n2, n, n2, f6, f7, f5, f4, Color.WHITE);
                    BufferedGuiRenderPrimitives.k = new RenderMatrix4f().b().e(0.0f, n, n2, 0.0f, -21000.0f, 21000.0f);
                    RenderBatchManager renderBatchManager = RenderBatchManager.M();
                    renderBatchManager.O(renderBatchBuilder);
                    renderBatchManager.a(this.T.w);
                    renderBatchManager.T(0.0f, false);
                    renderBatchManager.j();
                    BufferedGuiRenderPrimitives.u = glScissorRect;
                    OpenGlBackendHolder.d.F();
                }
            } else {
                RenderItem renderItem = Minecraft.v();
                renderItem.a(itemStack, 0, 0, matrixStack);
            }
            this.T.S();
            this.T.o();
            GL11.glViewport((int)intBuffer.get(0), (int)intBuffer.get(1), (int)intBuffer.get(2), (int)intBuffer.get(3));
            GL30.glBindFramebuffer((int)36160, (int)n3);
            GlStateManager.bindTexture(n4);
            OpenGlBackendHolder.d.l(3089);
            return;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(64);
        byteBuffer.order(ByteOrder.nativeOrder());
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        gg.vape.wrapper.impl.GL11.X(2978, intBuffer);
        this.T = new GlFramebuffer(n, n2, true);
        this.T.f(true);
        GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glClear((int)16384);
        GL11.glClear((int)256);
        GlStateManager.enableDepth();
        GlStateManager.enableBlend();
        MatrixStack matrixStack = MatrixStack.A();
        matrixStack.H();
        float f8 = Minecraft.p().k(Minecraft.gameSettings().T(), false);
        float f9 = 1.0f / f8;
        matrixStack.S(f9, f9, f9);
        matrixStack.S((float)Minecraft.J() / 32.0f, (float)Minecraft.h() / 32.0f, 0.0f);
        OpenGlBackendHolder.d.m();
        if (ForgeVersion.MC_1_20_6.d()) {
            RenderItemTextBridge renderItemTextBridge;
            Object object;
            if (ForgeVersion.MC_1_21_6.d()) {
                object = Matrix4fHandle.b(16);
                renderItemTextBridge = RenderItemTextBridge.l((Matrix4fHandle)object);
            } else {
                renderItemTextBridge = RenderItemTextBridge.t(matrixStack);
            }
            if (ForgeVersion.MC_1_21_6.d()) {
                renderItemTextBridge.S().Q();
            }
            ItemStackRenderUtils.O(renderItemTextBridge, itemStack, 0, -100);
            if (ForgeVersion.MC_1_21_10.d()) {
                float f10;
                float f11;
                int n10;
                Object v;
                Wrapper wrapper;
                List<String> fogEntries = renderItemTextBridge.S().getFogMode().F();
                if (fogEntries == null || fogEntries.isEmpty()) {
                    OpenGlBackendHolder.d.F();
                    return;
                }
                StringTextComponent stringTextComponent = new StringTextComponent(fogEntries.get(fogEntries.size() - 1));
                RenderBufferBridge renderBufferBridge = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().V();
                renderBufferBridge.j();
                Post117RenderPhaseCompat.P();
                if (ForgeVersion.MC_26_1.d()) {
                    wrapper = renderBufferBridge.P();
                    v = wrapper.isNull() ? null : ((BlockStateContainerBridge)wrapper).w(stringTextComponent.H());
                    n10 = wrapper.isNull() ? -1 : ((BlockStateContainerBridge)wrapper).I();
                } else {
                    v = renderBufferBridge.p().get(stringTextComponent.H().m$src$Ljava_lang_Object_$hvczij());
                    int n11 = n10 = renderBufferBridge.F().isNull() ? -1 : renderBufferBridge.F().J();
                }
                if (v == null || n10 == -1) {
                    OpenGlBackendHolder.d.F();
                    return;
                }
                wrapper = new MainWindow(v);
                float f12 = ((MainWindow)wrapper).q();
                float f13 = ((MainWindow)wrapper).r();
                if (ForgeVersion.MC_26_1.d()) {
                    f11 = ((MainWindow)wrapper).i();
                    f10 = ((MainWindow)wrapper).A();
                } else {
                    int n12 = Minecraft.p().P();
                    int n13 = 16 * n12;
                    int n14 = renderBufferBridge.L(n13);
                    f11 = f12 + (float)n13 / (float)n14;
                    f10 = f13 + (float)(-n13) / (float)n14;
                }
                this.T.f(true);
                GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
                GlScissorRect glScissorRect = BufferedGuiRenderPrimitives.u;
                BufferedGuiRenderPrimitives.u = null;
                RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder().o(new GlImageTexture(n10)).e(0.0f, 0.0f, n, n2, n, n2, f12, f13, f11, f10, Color.WHITE);
                BufferedGuiRenderPrimitives.k = new RenderMatrix4f().b().e(0.0f, n, n2, 0.0f, -21000.0f, 21000.0f);
                RenderBatchManager renderBatchManager = RenderBatchManager.M();
                renderBatchManager.O(renderBatchBuilder);
                renderBatchManager.a(this.T.w);
                renderBatchManager.T(0.0f, false);
                renderBatchManager.j();
                BufferedGuiRenderPrimitives.u = glScissorRect;
                OpenGlBackendHolder.d.F();
            }
        } else {
            RenderItem renderItem = Minecraft.v();
            renderItem.a(itemStack, 0, 0, matrixStack);
        }
        this.T.S();
        this.T.o();
        GL11.glViewport((int)intBuffer.get(0), (int)intBuffer.get(1), (int)intBuffer.get(2), (int)intBuffer.get(3));
        GL30.glBindFramebuffer((int)36160, (int)n3);
        GlStateManager.bindTexture(n4);
    }


    @Override
    public void s(float f, float f2, int n, int n2, float f3, boolean bl) {
        RenderBatchBuilder renderBatchBuilder = new RenderBatchBuilder(VertexCoordinateMode.DEFAULT, bl).o(new GlImageTexture(this.T.l)).e(f, f2, n, n2, 64.0f, 64.0f, 0.0f, 1.0f, 1.0f, 0.0f, new Color(1.0f, 1.0f, 1.0f, f3));
        if (bl) {
            RenderBatchManager.M().c(renderBatchBuilder);
        } else {
            RenderBatchManager.M().O(renderBatchBuilder);
        }
    }

    @Override
    public void H(float f, float f2, int n, int n2, float f3) {
        this.s(f, f2, n, n2, f3, false);
    }


    @Override
    public void e() {
        this.T.x();
        this.T = null;
    }
}
