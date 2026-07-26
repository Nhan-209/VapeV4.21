package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.utils.render.BufferedGuiRenderPrimitives;
import gg.vape.utils.render.FloatBufferObject;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GlTextureUnitState;
import gg.vape.utils.render.IntBufferObject;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.utils.render.PrimitiveTopology;
import gg.vape.utils.render.RenderBatch;
import gg.vape.utils.render.RenderBatchBuilder;
import gg.vape.utils.render.RenderBatchManager;
import gg.vape.utils.render.RenderBatchShaderProgram;
import gg.vape.utils.render.RenderMatrix4f;
import gg.vape.utils.render.VertexArrayObject;
import gg.vape.utils.render.VertexAttributeType;
import gg.vape.wrapper.impl.GlStateManager;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class RenderBatchBuffer {
    private RenderMatrix4f I;
    private final FloatBufferObject k;
    private IntBuffer o;
    private GlImageTexture f;
    private RenderBatchShaderProgram z;
    private float S;
    private FloatBuffer A;
    private int T = 0;
    private PrimitiveTopology c;
    private final VertexArrayObject u;
    private final IntBufferObject r;

    public void m(RenderBatch renderBatch) {
        this.f = renderBatch.H();
        for (RenderBatchBuilder renderBatchBuilder : renderBatch.U()) {
            this.A.put(renderBatchBuilder.y());
            this.o.put(renderBatchBuilder.R());
        }
        this.c = renderBatch.V();
        this.I = renderBatch.m();
        this.S = renderBatch.j();
    }

    public void A() {
        this.u.c();
        this.k.G();
        this.r.d();
    }

    public int n() {
        return this.o.capacity();
    }

    public void i() {
        int n = this.A.position();
        int n2 = this.o.position();
        if (n == 0 || n2 == 0) {
            throw new IllegalStateException("Number of vertices can't be 0");
        }
        gg.vape.wrapper.impl.GL20.w(this.z.T, false, this.I.J());
        if (this.c == PrimitiveTopology.LINES || this.c == PrimitiveTopology.LINES_LOOP) {
            OpenGlBackendHolder.d.l(2848);
        }
        if (this.c == null) {
            Vape.debugLog("Drawmode null: something fucked up");
            return;
        }
        int n3 = GL11.glGetInteger((int)32873);
        if (this.f != null) {
            GlTextureUnitState.H();
            this.f.F();
        }
        this.A.flip();
        this.o.flip();
        this.k.Q(this.A);
        this.r.S(this.o);
        GL11.glDrawElements((int)this.c.Q(), (int)n2, (int)5125, (long)0L);
        this.N();
        if (this.f != null) {
            GlStateManager.bindTexture(n3);
            GlTextureUnitState.V();
        }
        if (this.c == PrimitiveTopology.LINES || this.c == PrimitiveTopology.LINES_LOOP) {
            OpenGlBackendHolder.d.u$src$V$hntn98(2848);
        }
    }

    public RenderBatchBuffer(RenderBatchShaderProgram renderBatchShaderProgram, int n, VertexAttributeType ... vertexAttributeTypeArray) {
        VertexAttributeType[] vertexAttributeTypeArray2 = vertexAttributeTypeArray;
        int n2 = vertexAttributeTypeArray2.length;
        for (int i = 0; i < n2; ++i) {
            VertexAttributeType vertexAttributeType = vertexAttributeTypeArray2[i];
            this.T += vertexAttributeType.count;
        }
        this.z = renderBatchShaderProgram;
        this.c = null;
        this.A = BufferUtils.createFloatBuffer((int)(n * this.T * 4));
        this.o = BufferUtils.createIntBuffer((int)(n * 6));
        this.u = new VertexArrayObject();
        this.u.m();
        this.k = new FloatBufferObject();
        this.k.w();
        GL15.glBufferData((int)34962, (long)((long)this.A.capacity() * 4L), (int)35048);
        int n3 = 0;
        for (n2 = 0; n2 < vertexAttributeTypeArray.length; ++n2) {
            VertexAttributeType vertexAttributeType = vertexAttributeTypeArray[n2];
            GL20.glVertexAttribPointer((int)n2, (int)vertexAttributeType.count, (int)vertexAttributeType.type, (boolean)vertexAttributeType.normalized, (int)(this.T * 4), (long)((long)n3 * 4L));
            GL20.glEnableVertexAttribArray((int)n2);
            n3 += vertexAttributeType.count;
        }
        this.r = new IntBufferObject();
        this.r.c();
        GL15.glBufferData((int)34963, (long)((long)this.o.capacity() * 4L), (int)35048);
        this.D();
        this.u.X();
    }

    public int k() {
        return this.A.capacity();
    }

    public void z() {
        this.u.m();
        this.z.P();
        this.k.w();
        this.r.c();
        gg.vape.wrapper.impl.GL20.w(this.z.m, false, BufferedGuiRenderPrimitives.k.J());
        gg.vape.wrapper.impl.GL20.w(this.z.B, false, BufferedGuiRenderPrimitives.l.J());
    }

    public int Q() {
        return this.T;
    }

    private void D() {
        int n = GL11.glGetInteger((int)35725);
        try {
            if (this.z == null || this.z.S <= 0) {
                throw new IllegalStateException("Universal shader program was not created");
            }
            GL20.glUseProgram((int)this.z.S);
            int n2 = GL20.glGetUniformLocation((int)this.z.S, (CharSequence)"imgTexture");
            if (n2 < 0) {
                throw new IllegalStateException("Failed to resolve shader uniform 'imgTexture' (location=" + n2 + ")");
            }
            GL20.glUniform1i((int)n2, (int)0);
            int n3 = GL11.glGetError();
            if (n3 != 0) {
                throw new IllegalStateException("OpenGL error " + n3 + " after glUniform1i(imgTexture, 0)");
            }
        }
        catch (Throwable throwable) {
            throw RenderBatchManager.I("mesh uniform setup", throwable);
        }
        finally {
            GL20.glUseProgram((int)n);
        }
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    public void N() {
        this.o.clear();
        this.A.clear();
    }
}

