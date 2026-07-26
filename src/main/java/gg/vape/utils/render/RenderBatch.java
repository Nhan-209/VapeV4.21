package gg.vape.utils.render;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.GlCapabilityState;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.GlScissorRect;
import gg.vape.utils.render.PrimitiveTopology;
import gg.vape.utils.render.RenderBatchBuilder;
import gg.vape.utils.render.RenderMatrix4f;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RenderBatch {
    private RenderMatrix4f U;
    private GlCapabilityState V;
    private float E;
    private PrimitiveTopology C;
    private GlImageTexture Y;
    private List<Supplier<Void>> M = new ArrayList<Supplier<Void>>();
    private GlScissorRect p;
    private int t;
    private ArrayList<RenderBatchBuilder> g = new ArrayList();
    private Supplier<Void> A;

    public boolean J() {
        boolean bl = this.p != null;
        return bl;
    }

    public void l() {
        ++this.t;
    }

    public float j() {
        return this.E;
    }

    public List<Supplier<Void>> Y() {
        return this.M;
    }

    public void J(GlScissorRect cv_12) {
        this.p = cv_12;
    }

    public RenderBatch(RenderBatchBuilder hr_02) {
        this.Y = hr_02.C();
        this.U = hr_02.C;
        this.C = hr_02.q();
        this.E = hr_02.D();
        this.p = hr_02.c();
        this.V = hr_02.A();
        this.A = hr_02.d();
        this.t = 1;
        if (hr_02.h() != null) {
            this.M.add(hr_02.h());
        } else {
            hr_02.a = 0;
            hr_02.N(hr_02.q().name, 0);
        }
        this.g.add(hr_02);
    }

    public void e(RenderBatchBuilder hr_02) {
        this.g.add(hr_02);
        this.P(hr_02.h());
    }

    public PrimitiveTopology V() {
        return this.C;
    }

    public RenderMatrix4f m() {
        return this.U;
    }

    public int O() {
        return this.t;
    }

    public GlScissorRect z() {
        return this.p;
    }

    public GlImageTexture H() {
        return this.Y;
    }

    public void P(Supplier<Void> supplier) {
        if (supplier != null) {
            this.M.add(supplier);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public GlCapabilityState y() {
        return this.V;
    }

    public Supplier<Void> R() {
        return this.A;
    }

    public ArrayList<RenderBatchBuilder> U() {
        return this.g;
    }
}

