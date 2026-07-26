package gg.vape.utils.render.shader;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.shader.ShaderProgram;
import org.lwjgl.opengl.GL20;

public class RoundedCornerMaskShaderProgram
extends ShaderProgram {
    public static final int d = 1;
    public static final int n;
    public static final int V;
    private static final String s;
    private static final String I;
    public static final int v;
    public static final int M;

    public RoundedCornerMaskShaderProgram(String string, String string2) {
        super(string, string2);
    }

    public void o(float f) {
        GL20.glUniform1f((int)0, (float)f);
    }

    public RoundedCornerMaskShaderProgram() {
        super("#version 430 compatibility\n\nout vec2 f_Position;\n\nvoid main() {\n    f_Position = gl_Vertex.xy;\n    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n    gl_FrontColor = gl_Color;\n}", "#version 430 compatibility\nprecision highp float;\n\nlayout(location = 0) uniform float u_Radius;\nlayout(location = 1) uniform vec4 u_InnerRect;\nlayout(location = 2) uniform float u_Spread;\nlayout(location = 3) uniform vec4 u_Corners; // (topLeft, topRight, bottomRight, bottomLeft)\n\nin vec2 f_Position;\nout vec4 fragColor;\n\nvoid main() {\n    vec2 tl = u_InnerRect.xy - f_Position;\n    vec2 br = f_Position - u_InnerRect.zw;\n\n    vec2 dis = max(br, tl);\n\n    // Determine if the fragment is in one of the corners\n    bool inTopLeft = (f_Position.x <= u_InnerRect.x && f_Position.y <= u_InnerRect.y);\n    bool inBottomLeft = (f_Position.x <= u_InnerRect.x && f_Position.y >= u_InnerRect.w);\n    bool inTopRight = (f_Position.x >= u_InnerRect.z && f_Position.y <= u_InnerRect.y);\n    bool inBottomRight = (f_Position.x >= u_InnerRect.z && f_Position.y >= u_InnerRect.w);\n\n    // Check if the respective corner should be drawn based on u_Corners\n    bool drawTopLeft = inTopLeft && u_Corners.x > 0.5;\n    bool drawTopRight = inTopRight && u_Corners.y > 0.5;\n    bool drawBottomLeft = inBottomLeft && u_Corners.w > 0.5;\n    bool drawBottomRight = inBottomRight && u_Corners.z > 0.5;\n\n    bool inCorner = drawTopLeft || drawTopRight || drawBottomLeft || drawBottomRight;\n\n    float v = length(max(vec2(0.0), dis)) - u_Radius;\n    float a = inCorner ? 1.0 - smoothstep(0.0, u_Spread, v) : 0.0;\n\n    // Only draw the selected corners, nothing in the middle\n    fragColor = inCorner ? gl_Color * vec4(1.0, 1.0, 1.0, 1.0 - a) : vec4(0.0, 0.0, 0.0, 0.0);\n}");
    }

    public void Y(float f, float f2, float f3, float f4) {
        GL20.glUniform4f((int)1, (float)f, (float)f2, (float)f3, (float)f4);
    }

    static {
        I = "#version 430 compatibility\nprecision highp float;\n\nlayout(location = 0) uniform float u_Radius;\nlayout(location = 1) uniform vec4 u_InnerRect;\nlayout(location = 2) uniform float u_Spread;\nlayout(location = 3) uniform vec4 u_Corners; // (topLeft, topRight, bottomRight, bottomLeft)\n\nin vec2 f_Position;\nout vec4 fragColor;\n\nvoid main() {\n    vec2 tl = u_InnerRect.xy - f_Position;\n    vec2 br = f_Position - u_InnerRect.zw;\n\n    vec2 dis = max(br, tl);\n\n    // Determine if the fragment is in one of the corners\n    bool inTopLeft = (f_Position.x <= u_InnerRect.x && f_Position.y <= u_InnerRect.y);\n    bool inBottomLeft = (f_Position.x <= u_InnerRect.x && f_Position.y >= u_InnerRect.w);\n    bool inTopRight = (f_Position.x >= u_InnerRect.z && f_Position.y <= u_InnerRect.y);\n    bool inBottomRight = (f_Position.x >= u_InnerRect.z && f_Position.y >= u_InnerRect.w);\n\n    // Check if the respective corner should be drawn based on u_Corners\n    bool drawTopLeft = inTopLeft && u_Corners.x > 0.5;\n    bool drawTopRight = inTopRight && u_Corners.y > 0.5;\n    bool drawBottomLeft = inBottomLeft && u_Corners.w > 0.5;\n    bool drawBottomRight = inBottomRight && u_Corners.z > 0.5;\n\n    bool inCorner = drawTopLeft || drawTopRight || drawBottomLeft || drawBottomRight;\n\n    float v = length(max(vec2(0.0), dis)) - u_Radius;\n    float a = inCorner ? 1.0 - smoothstep(0.0, u_Spread, v) : 0.0;\n\n    // Only draw the selected corners, nothing in the middle\n    fragColor = inCorner ? gl_Color * vec4(1.0, 1.0, 1.0, 1.0 - a) : vec4(0.0, 0.0, 0.0, 0.0);\n}";
        s = "#version 430 compatibility\n\nout vec2 f_Position;\n\nvoid main() {\n    f_Position = gl_Vertex.xy;\n    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n    gl_FrontColor = gl_Color;\n}";
        long[] lArray = new long[]{486331774129930242L, -2271953926246891505L, -4654026927017820152L, 8199249025132658692L};
        V = (int)lArray[0];
        M = (int)lArray[3];
        v = (int)lArray[2];
        n = (int)lArray[1];
    }

    public void r(int n) {
        boolean bl = (n & 1) != 0;
        boolean bl2 = (n & 2) != 0;
        boolean bl3 = (n & 4) != 0;
        boolean bl4 = (n & 8) != 0;
        GL20.glUniform4f((int)3, (float)(bl ? 1.0f : 0.0f), (float)(bl2 ? 1.0f : 0.0f), (float)(bl3 ? 1.0f : 0.0f), (float)(bl4 ? 1.0f : 0.0f));
    }

    public void W(float f) {
        GL20.glUniform1f((int)2, (float)f);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

