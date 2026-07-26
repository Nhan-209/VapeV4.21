package gg.vape.utils.render;

import gg.vape.module.blatant.invwalk.InvWalkKeyLayout;
import gg.vape.rotation.LocalPlayerRotationUtil;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.GlCapabilityState;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GlScissorRect;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.OpenGlDeviceInfo;
import gg.vape.utils.render.PrimitiveTopology;
import gg.vape.utils.render.RenderBatch;
import gg.vape.utils.render.RenderBatchBuffer;
import gg.vape.utils.render.RenderBatchBuilder;
import gg.vape.utils.render.RenderBatchShaderProgram;
import gg.vape.utils.render.RenderBatchState;
import gg.vape.utils.render.RenderBatchStateFlags;
import gg.vape.utils.render.RenderMatrixStack;
import gg.vape.utils.render.TextureAtlasRegistry;
import gg.vape.utils.render.VertexAttributeType;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.TextureManagerHandle;
import gg.vape.wrapper.impl.TextureObjectHandle;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;
import java.util.function.Supplier;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RenderBatchManager {
    private int J;
    private RenderBatchBuilder z;
    private int K = 999;
    private int p;
    private int s;
    private int f = 0;
    private static RenderBatchManager F;
    private static final long R = 1000L;
    public RenderBatchBuffer h;
    private int o;
    private int w;
    private boolean H;
    private int t;
    private final Deque<RenderBatchStateFlags> N = new ArrayDeque<RenderBatchStateFlags>();
    private long S = 0L;
    private RenderBatchBuilder B;
    public ArrayList<RenderBatch> u = new ArrayList();
    public RenderBatchShaderProgram q;
    public ArrayList<RenderBatch> i = new ArrayList();

    private boolean z(RenderBatchBuilder renderBatchBuilder, RenderBatchBuilder renderBatchBuilder2, boolean bl) {
        GlScissorRect glScissorRect;
        GlCapabilityState glCapabilityState;
        PrimitiveTopology primitiveTopology;
        GlImageTexture glImageTexture;
        if (renderBatchBuilder2 == null) {
            return true;
        }
        if (renderBatchBuilder.m() != renderBatchBuilder2.m()) {
            return true;
        }
        GlImageTexture glImageTexture2 = renderBatchBuilder.C();
        if (glImageTexture2 != (glImageTexture = renderBatchBuilder2.C())) {
            if (glImageTexture2 == null || glImageTexture == null) {
                return true;
            }
            if (glImageTexture2.F != glImageTexture.F) {
                return true;
            }
        }
        if (renderBatchBuilder.d() != null || renderBatchBuilder2.d() != null) {
            return true;
        }
        PrimitiveTopology primitiveTopology2 = renderBatchBuilder.q();
        if (!Objects.equals((Object)primitiveTopology2, (Object)(primitiveTopology = renderBatchBuilder2.q()))) {
            return true;
        }
        if (!renderBatchBuilder.C.Z(renderBatchBuilder2.C)) {
            return true;
        }
        GlCapabilityState glCapabilityState2 = renderBatchBuilder.A();
        if (!Objects.equals(glCapabilityState2, glCapabilityState = renderBatchBuilder2.A())) {
            return true;
        }
        GlScissorRect glScissorRect2 = renderBatchBuilder.c();
        if (!Objects.equals(glScissorRect2, glScissorRect = renderBatchBuilder2.c())) {
            return true;
        }
        return this.C(renderBatchBuilder, bl);
    }

    public int A(boolean bl) {
        try {
            if (bl) {
                return this.u.get(this.u.size() - 1).O();
            }
            return this.i.get(this.i.size() - 1).O();
        }
        catch (Exception exception) {
            return 0;
        }
    }

    public int E() {
        long l = System.currentTimeMillis();
        if (this.K == 999 || l - this.S >= 1000L && this.f == 0) {
            this.S = l;
            int n = this.K;
            this.f();
            if (n == 999 || n == this.K || this.K != -1) {
                // empty if block
            }
        }
        return this.K;
    }

    public static RenderBatchManager M() {
        if (!GuiRenderPrimitives.d()) {
            throw new IllegalStateException("Attempting to call RenderEngine on an older version of OpenGL");
        }
        if (F == null) {
            F = new RenderBatchManager();
        }
        return F;
    }

    public void a(int n) {
        this.f = this.E();
        this.K = n;
    }

    private boolean C(RenderBatchBuilder renderBatchBuilder, boolean bl) {
        if (renderBatchBuilder.y() == null || renderBatchBuilder.R() == null) {
            return false;
        }
        RenderBatch renderBatch = bl ? this.u.get(this.u.size() - 1) : this.i.get(this.i.size() - 1);
        return renderBatch.U().size() >= 4500;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void T(float f, boolean bl) {
        if (this.i.isEmpty()) {
            return;
        }
        if (bl) {
            LocalPlayerRotationUtil.t();
        }
        this.v();
        this.Y();
        try {
            for (RenderBatch renderBatch : this.i) {
                this.o();
                boolean bl2 = GL11.glIsEnabled((int)3089);
                if (renderBatch.J()) {
                    if (!bl2) {
                        OpenGlBackendHolder.d.l(3089);
                    }
                    GlScissorRect glScissorRect = renderBatch.z();
                    GL11.glScissor((int)glScissorRect.v, (int)glScissorRect.F, (int)glScissorRect.I, (int)glScissorRect.f);
                } else if (bl2) {
                    OpenGlBackendHolder.d.u$src$V$hntn98(3089);
                }
                if (!renderBatch.Y().isEmpty()) {
                    this.F();
                    this.E$src$V$ni8yo1();
                    OpenGlBackendHolder.d.u$src$V$hntn98(3089);
                    for (Supplier supplier : renderBatch.Y()) {
                        supplier.get();
                    }
                    this.v();
                    continue;
                }
                if (renderBatch.R() != null) {
                    renderBatch.R().get();
                }
                this.h.m(renderBatch);
                if (!this.H) continue;
                this.h.i();
            }
        }
        finally {
            this.i.clear();
            this.B = null;
            OpenGlBackendHolder.d.u$src$V$hntn98(3089);
            this.F();
            this.l();
            this.E$src$V$ni8yo1();
            BufferedGuiRenderPrimitives.X = new RenderMatrixStack();
            if (bl) {
                LocalPlayerRotationUtil.Q(f);
            }
        }
    }

    private void F() {
        if (this.H) {
            GL30.glBindVertexArray((int)0);
            this.H = false;
        }
    }

    public void G(float f) {
        this.T(f, true);
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    public static String i(String string, Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to initialize modern renderer\n");
        stringBuilder.append("Phase: ").append(string).append('\n');
        OpenGlDeviceInfo.Z(stringBuilder);
        try {
            stringBuilder.append("Current Program: ").append(GL11.glGetInteger((int)35725)).append('\n');
            stringBuilder.append("Current VAO: ").append(GL11.glGetInteger((int)34229)).append('\n');
            stringBuilder.append("Array Buffer: ").append(GL11.glGetInteger((int)34964)).append('\n');
            stringBuilder.append("Element Buffer: ").append(GL11.glGetInteger((int)34965)).append('\n');
            stringBuilder.append("Framebuffer: ").append(GL11.glGetInteger((int)36006)).append('\n');
            stringBuilder.append("GL Error: ").append(GL11.glGetError()).append('\n');
        }
        catch (Throwable throwable2) {
            stringBuilder.append("GL Diagnostics Error: ").append(throwable2.getClass().getSimpleName()).append(": ").append(throwable2.getMessage()).append('\n');
        }
        if (throwable != null) {
            stringBuilder.append("Exception: ").append(throwable.getClass().getName());
            if (throwable.getMessage() != null) {
                stringBuilder.append(": ").append(throwable.getMessage());
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public void G() {
        GL30.glBindVertexArray((int)0);
        this.l();
        GL30.glBindVertexArray((int)this.t);
        GL20.glUseProgram((int)this.w);
        GL11.glBindTexture((int)3553, (int)this.o);
        GL30.glBindFramebuffer((int)36160, (int)this.s);
        GL15.glBindBuffer((int)34962, (int)this.p);
        GL15.glBindBuffer((int)34963, (int)this.J);
        this.H = false;
    }

    public void c(RenderBatchBuilder renderBatchBuilder) {
        if (this.z(renderBatchBuilder, this.z, true)) {
            this.u.add(new RenderBatch(renderBatchBuilder));
        } else {
            RenderBatch renderBatch = this.u.get(this.u.size() - 1);
            renderBatch.l();
            renderBatch.e(renderBatchBuilder);
        }
        this.z = renderBatchBuilder;
    }

    private void l() {
        if (this.N.isEmpty()) {
            return;
        }
        RenderBatchStateFlags renderBatchStateFlags = this.N.pop();
        if (renderBatchStateFlags.A) {
            GlStateManager.enableBlend();
        } else {
            GlStateManager.disableBlend();
        }
        if (renderBatchStateFlags.X) {
            OpenGlBackendHolder.d.l(2884);
        } else {
            OpenGlBackendHolder.d.u$src$V$hntn98(2884);
        }
        if (renderBatchStateFlags.W) {
            GlStateManager.enableDepth();
        } else {
            GlStateManager.disableDepth();
        }
        GL11.glDepthMask((boolean)renderBatchStateFlags.M);
    }

    public RenderBatchBuffer s() {
        return this.h;
    }

    public void O(RenderBatchBuilder renderBatchBuilder) {
        if (renderBatchBuilder.C() == null) {
            renderBatchBuilder.o(TextureAtlasRegistry.w().m("vape_texture").d());
        }
        if (this.z(renderBatchBuilder, this.B, false)) {
            this.i.add(new RenderBatch(renderBatchBuilder));
        } else {
            RenderBatch renderBatch = this.i.get(this.i.size() - 1);
            renderBatch.l();
            renderBatch.e(renderBatchBuilder);
        }
        this.B = renderBatchBuilder;
    }

    private void v() {
        this.t = GL11.glGetInteger((int)34229);
        this.w = GL11.glGetInteger((int)35725);
        this.o = GL11.glGetInteger((int)32873);
        this.s = GL11.glGetInteger((int)36006);
        this.p = GL11.glGetInteger((int)34964);
        this.J = GL11.glGetInteger((int)34965);
    }

    public static IllegalStateException I(String string, Throwable throwable) {
        return new IllegalStateException(RenderBatchManager.i(string, throwable), throwable);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void C(float f) {
        if (this.u.isEmpty()) {
            return;
        }
        this.v();
        this.Y();
        try {
            LocalPlayerRotationUtil.Q(f);
            for (RenderBatch renderBatch : this.u) {
                this.o();
                renderBatch.y().K();
                if (!renderBatch.Y().isEmpty()) {
                    this.F();
                    this.E$src$V$ni8yo1();
                    GlStateManager.enableBlend();
                    for (Supplier<Void> supplier : renderBatch.Y()) {
                        supplier.get();
                    }
                    this.v();
                    continue;
                }
                this.h.m(renderBatch);
                if (!this.H) continue;
                this.h.i();
            }
        }
        finally {
            this.z = null;
            this.u.clear();
            this.F();
            this.l();
            this.E$src$V$ni8yo1();
            BufferedGuiRenderPrimitives.X = new RenderMatrixStack();
            LocalPlayerRotationUtil.t();
        }
    }

    public void j() {
        this.K = this.f;
        this.f = 0;
    }

    private void P() {
        int n = this.E();
        if (n != -1) {
            GL30.glBindFramebuffer((int)36160, (int)n);
        }
        this.h.z();
        this.H = true;
    }

    public static void K() {
        RenderBatchState.r();
        InvWalkKeyLayout.F();
        F = null;
    }

    public void f() {
        if (ForgeVersion.MC_1_21_10.d()) {
            try {
                TextureObjectHandle textureObjectHandle;
                int n;
                int n2;
                TextureObjectHandle textureObjectHandle2;
                TextureManagerHandle textureManagerHandle = Minecraft.M$src$Lgg_vape_wrapper_impl_TextureManagerHandle_$r0mor();
                if (textureManagerHandle != null && textureManagerHandle.isNotNull() && (textureObjectHandle2 = textureManagerHandle.e()) != null && textureObjectHandle2.isNotNull() && (n2 = textureObjectHandle2.G(n = (textureObjectHandle = textureManagerHandle.x()) != null && textureObjectHandle.isNotNull() ? textureObjectHandle.J() : 0)) > 0) {
                    this.K = n2;
                    return;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.K = -1;
    }

    public RenderBatchManager() {
        Object object;
        try {
            InvWalkKeyLayout.y();
            this.q = InvWalkKeyLayout.Q;
        }
        catch (Throwable throwable) {
            throw RenderBatchManager.I("shader load", throwable);
        }
        try {
            object = new VertexAttributeType[]{VertexAttributeType.Float, VertexAttributeType.Vec3, VertexAttributeType.Vec2, VertexAttributeType.Vec4, VertexAttributeType.Float, VertexAttributeType.Float, VertexAttributeType.Vec2, VertexAttributeType.Float, VertexAttributeType.Float, VertexAttributeType.Vec2, VertexAttributeType.Vec2, VertexAttributeType.Vec4, VertexAttributeType.Vec3, VertexAttributeType.Float, VertexAttributeType.Vec4};
            this.h = new RenderBatchBuffer(this.q, 5000, (VertexAttributeType[])object);
        }
        catch (Throwable throwable) {
            throw RenderBatchManager.I("mesh creation", throwable);
        }
        try {
            object = TextureAtlasRegistry.w();
            ((TextureAtlasRegistry)object).U(((TextureAtlasRegistry)object).U("vape_texture"));
        }
        catch (Throwable throwable) {
            throw RenderBatchManager.I("texture atlas creation", throwable);
        }
    }

    private void Y() {
        boolean bl = GL11.glIsEnabled((int)2884);
        boolean bl2 = GL11.glIsEnabled((int)3042);
        boolean bl3 = GL11.glIsEnabled((int)2929);
        boolean bl4 = GL11.glGetBoolean((int)2930);
        this.N.push(new RenderBatchStateFlags(bl, bl2, bl3, bl4));
        if (bl) {
            OpenGlBackendHolder.d.u$src$V$hntn98(2884);
        }
        if (!bl2) {
            OpenGlBackendHolder.d.l(3042);
        }
        if (bl3) {
            GlStateManager.disableDepth();
        }
        GlStateManager.Y(770, 771);
    }

    public void q() {
        this.v();
        int n = this.E();
        if (n != -1) {
            GL30.glBindFramebuffer((int)36160, (int)n);
        }
        this.Y();
        this.h.z();
        this.H = true;
    }

    private void E$src$V$ni8yo1() {
        GL30.glBindVertexArray((int)this.t);
        GL20.glUseProgram((int)this.w);
        GL11.glBindTexture((int)3553, (int)this.o);
        GL30.glBindFramebuffer((int)36160, (int)this.s);
        GL15.glBindBuffer((int)34962, (int)this.p);
        GL15.glBindBuffer((int)34963, (int)this.J);
    }

    private void o() {
        if (!this.H) {
            this.P();
        }
    }
}

