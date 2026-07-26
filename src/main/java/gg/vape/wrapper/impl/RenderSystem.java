package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRenderSystem;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Items;
import gg.vape.wrapper.impl.Matrix4f;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.ResourceLocation;

public class RenderSystem
extends Wrapper {
    public static void o(float f) {
        MRenderSystem.Z(RenderSystem.c.getMappings().f, f);
    }

    public static void U(float f, float f2, float f3, float f4) {
        if (ForgeVersion.MC_1_21_6.v()) {
            return;
        }
        MRenderSystem.S(RenderSystem.c.getMappings().f, f, f2, f3, f4);
    }

    public static void p$src$V$18am5c() {
        MRenderSystem.f(RenderSystem.c.getMappings().f);
    }

    public static MatrixStack p() {
        return new MatrixStack(RenderSystem.c.getMappings().f.P());
    }

    public static void v() {
        if (ForgeVersion.MC_1_21_4.d()) {
            return;
        }
        MRenderSystem.U(RenderSystem.c.getMappings().f);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void L(Matrix4f matrix4f, Items items) {
    }

    public static void b(float f) {
        if (ForgeVersion.MC_1_21_11.d()) {
            return;
        }
        MRenderSystem.F(RenderSystem.c.getMappings().f, f);
    }

    public static void s(int n, int n2) {
        if (ForgeVersion.MC_1_21_6.v()) {
            return;
        }
        MRenderSystem.o(RenderSystem.c.getMappings().f, n, n2);
    }

    public static void k(int n, int n2) {
        if (ForgeVersion.MC_1_21_6.v()) {
            return;
        }
        MRenderSystem.n(RenderSystem.c.getMappings().f, n, n2);
    }

    public static void R() {
        MRenderSystem.c(RenderSystem.c.getMappings().f);
    }

    public static void u(int n, ResourceLocation resourceLocation) {
        MRenderSystem.o(RenderSystem.c.getMappings().f, n, resourceLocation.getObject());
    }

    public static void f() {
        MRenderSystem.h(RenderSystem.c.getMappings().f);
    }

    public static void n() {
        if (ForgeVersion.MC_1_21_6.v()) {
            return;
        }
        MRenderSystem.D(RenderSystem.c.getMappings().f);
    }

    public RenderSystem(Object object) {
        super(object);
    }

    public static void x() {
        if (ForgeVersion.MC_1_21_6.v()) {
            return;
        }
        MRenderSystem.j(RenderSystem.c.getMappings().f);
    }

    public static int[] I() {
        return MRenderSystem.x(RenderSystem.c.getMappings().f);
    }
}

