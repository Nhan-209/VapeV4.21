package gg.vape.module.blatant.invwalk;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.RenderBatchShaderProgram;

public class InvWalkKeyLayout {
    public static final float O = 1.0f;
    public static final float X = 6.0f;
    public static final float l = 12.0f;
    public static final float V = 8.0f;
    public static RenderBatchShaderProgram p;
    public static final float E = 14.0f;
    public static final float P = 4.0f;
    public static final float U = 5.0f;
    public static final float u = 10.0f;
    public static final float K = 19.0f;
    public static final float x = 18.0f;
    public static final float L = 17.0f;
    public static RenderBatchShaderProgram T;
    public static final float S = 9.0f;
    public static final float y = 0.0f;
    public static final float F = 13.0f;
    public static final float J = 3.0f;
    public static final float t = 11.0f;
    public static RenderBatchShaderProgram Q;
    public static final float s = 2.0f;
    public static final float r = 16.0f;
    public static final float Y = 20.0f;
    public static final float f = 7.0f;
    public static final float b = 15.0f;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void y() {
        if (Q == null) {
            Q = new RenderBatchShaderProgram("shader/universal_vert.vert", "shader/universal_frag.frag");
        }
        if (T == null) {
            T = new RenderBatchShaderProgram("shader/test/test_vert.vert", "shader/test/test_frag.frag");
        }
        if (p == null) {
            p = new RenderBatchShaderProgram("shader/block_esp_vert.vert", "shader/block_esp_frag.frag");
        }
    }

    public static void F() {
        Q = null;
        T = null;
        p = null;
    }
}

