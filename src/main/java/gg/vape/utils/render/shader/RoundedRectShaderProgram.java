package gg.vape.utils.render.shader;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.shader.ShaderProgram;
import org.lwjgl.opengl.GL20;

public class RoundedRectShaderProgram
extends ShaderProgram {
    public static final int u;
    private static final String i;
    public static final int V;
    public static final int X = 1;
    private static String H;
    public static final int y;
    public static final int J;
    private static final String F;

    public void G(int n) {
        boolean bl = (n & 1) != 0;
        boolean bl2 = (n & 2) != 0;
        boolean bl3 = (n & 4) != 0;
        boolean bl4 = (n & 8) != 0;
        GL20.glUniform4f((int)3, (float)(bl ? 1.0f : 0.0f), (float)(bl2 ? 1.0f : 0.0f), (float)(bl3 ? 1.0f : 0.0f), (float)(bl4 ? 1.0f : 0.0f));
    }

    public RoundedRectShaderProgram() {
        super("#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\ngl_FrontColor = gl_Color;\n}\n", "#version 430 compatibility\nprecision highp float;\n\nlayout(location = 0) uniform float u_Radius;\nlayout(location = 1) uniform vec4 u_InnerRect;\nlayout(location = 2) uniform float u_Spread;\nlayout(location = 3) uniform vec4 u_Corners; // (topLeft, topRight, bottomRight, bottomLeft)\n\nin vec2 f_Position;\nout vec4 fragColor;\n\nvoid main() {\n    vec2 tl = u_InnerRect.xy - f_Position;\n    vec2 br = f_Position - u_InnerRect.zw;\n\n    vec2 dis = max(br, tl);\n\n    // Determine if the fragment is in one of the corners\n    bool inTopLeft = (f_Position.x <= u_InnerRect.x && f_Position.y <= u_InnerRect.y);\n    bool inTopRight = (f_Position.x >= u_InnerRect.z && f_Position.y <= u_InnerRect.y);\n    bool inBottomLeft = (f_Position.x <= u_InnerRect.x && f_Position.y >= u_InnerRect.w);\n    bool inBottomRight = (f_Position.x >= u_InnerRect.z && f_Position.y >= u_InnerRect.w);\n\n    // Check if the respective corner should be drawn based on u_Corners\n    bool drawTopLeft = inTopLeft && u_Corners.x > 0.5;\n    bool drawTopRight = inTopRight && u_Corners.y > 0.5;\n    bool drawBottomLeft = inBottomLeft && u_Corners.w > 0.5;\n    bool drawBottomRight = inBottomRight && u_Corners.z > 0.5;\n\n    bool inCorner = drawTopLeft || drawTopRight || drawBottomLeft || drawBottomRight;\n\n    float v = length(max(vec2(0.0), dis)) - u_Radius;\n    float a = inCorner ? 1.0 - smoothstep(0.0, u_Spread, v) : 1.0;\n\n    fragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);\n}");
    }

    static {
        RoundedRectShaderProgram.n("TmJO");
        i = "#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\ngl_FrontColor = gl_Color;\n}\n";
        F = "#version 430 compatibility\nprecision highp float;\n\nlayout(location = 0) uniform float u_Radius;\nlayout(location = 1) uniform vec4 u_InnerRect;\nlayout(location = 2) uniform float u_Spread;\nlayout(location = 3) uniform vec4 u_Corners; // (topLeft, topRight, bottomRight, bottomLeft)\n\nin vec2 f_Position;\nout vec4 fragColor;\n\nvoid main() {\n    vec2 tl = u_InnerRect.xy - f_Position;\n    vec2 br = f_Position - u_InnerRect.zw;\n\n    vec2 dis = max(br, tl);\n\n    // Determine if the fragment is in one of the corners\n    bool inTopLeft = (f_Position.x <= u_InnerRect.x && f_Position.y <= u_InnerRect.y);\n    bool inTopRight = (f_Position.x >= u_InnerRect.z && f_Position.y <= u_InnerRect.y);\n    bool inBottomLeft = (f_Position.x <= u_InnerRect.x && f_Position.y >= u_InnerRect.w);\n    bool inBottomRight = (f_Position.x >= u_InnerRect.z && f_Position.y >= u_InnerRect.w);\n\n    // Check if the respective corner should be drawn based on u_Corners\n    bool drawTopLeft = inTopLeft && u_Corners.x > 0.5;\n    bool drawTopRight = inTopRight && u_Corners.y > 0.5;\n    bool drawBottomLeft = inBottomLeft && u_Corners.w > 0.5;\n    bool drawBottomRight = inBottomRight && u_Corners.z > 0.5;\n\n    bool inCorner = drawTopLeft || drawTopRight || drawBottomLeft || drawBottomRight;\n\n    float v = length(max(vec2(0.0), dis)) - u_Radius;\n    float a = inCorner ? 1.0 - smoothstep(0.0, u_Spread, v) : 1.0;\n\n    fragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);\n}";
        long[] lArray = new long[]{-851897314233548792L, -1817311979899977724L, -4868244889126567934L, -879629630301011953L};
        y = (int)lArray[1];
        J = (int)lArray[0];
        V = (int)lArray[3];
        u = (int)lArray[2];
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void H(float f, float f2, float f3, float f4) {
        GL20.glUniform4f((int)1, (float)f, (float)f2, (float)f3, (float)f4);
    }

    public static String L$src$Ljava_lang_String_$12bna39() {
        return H;
    }

    public void y(float f) {
        GL20.glUniform1f((int)2, (float)f);
    }

    public static void n(String string) {
        H = string;
    }

    public RoundedRectShaderProgram(String string, String string2) {
        super(string, string2);
    }

    public void h(float f) {
        GL20.glUniform1f((int)0, (float)f);
    }
}

