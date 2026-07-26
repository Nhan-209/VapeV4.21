package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MRenderHelper;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.OpenGlBackendHolder;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class RenderHelper
extends Wrapper {
    public RenderHelper(Object object) {
        super(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void s() {
        if (ForgeVersion.MC_1_17.d()) {
            return;
        }
        MRenderHelper.L(RenderHelper.c.getMappingsMapperCompat().x);
    }

    public static void l() {
        if (ForgeVersion.MC_1_17.d()) {
            MRenderHelper.M(RenderHelper.c.getMappingsMapperCompat().x);
            return;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            OpenGlBackendHolder.d.m();
            OpenGlBackendHolder.d.X(-30.0f, 0.0f, 1.0f, 0.0f);
            OpenGlBackendHolder.d.X(165.0f, 1.0f, 0.0f, 0.0f);
            RenderHelper.e();
            OpenGlBackendHolder.d.F();
            return;
        }
        MRenderHelper.M(RenderHelper.c.getMappingsMapperCompat().x);
    }

    public static void e() {
        if (ForgeVersion.MC_1_17.d()) {
            return;
        }
        MRenderHelper.Q(RenderHelper.c.getMappingsMapperCompat().x);
    }
}

