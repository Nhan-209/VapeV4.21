package gg.vape.utils.render.shader;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.shader.ShaderProgram;
import java.awt.Color;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class CompositeRoundedRectShaderProgram
extends ShaderProgram {
    private static final int C;
    private int s;
    private ByteBuffer v;
    private boolean R = false;
    private boolean g;
    private static final String y;
    private static final String a;

    @Override
    public void L() {
        if (this.v != null) {
            GL15.glUnmapBuffer((int)35345);
            this.v = null;
        }
        GL15.glBindBuffer((int)35345, (int)0);
        super.L();
    }

    @Override
    public boolean f() {
        super.f();
        this.D();
        return true;
    }

    public void D() {
        if (this.R) {
            return;
        }
        this.R = true;
        this.j(12.0f);
        this.l(new Color(0, 0, 0, 170));
        this.E(new Color(0, 0, 0, 150));
        this.D(new Color(45, 45, 45, 255));
        this.r(6.0f);
        this.L(1.0f);
        this.Y(0.8f);
        this.s(new Color(51, 51, 51, 255));
        this.r(false);
    }

    public void C(boolean bl, boolean bl2) {
        GL20.glUniform4f((int)8, (float)(bl ? 1.0f : 0.0f), (float)0.0f, (float)0.0f, (float)0.0f);
        GL20.glUniform4f((int)11, (float)(bl2 ? 4.0f : 0.0f), (float)0.0f, (float)0.0f, (float)0.0f);
    }

    public void E(Color color) {
        GL20.glUniform4f((int)2, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
    }

    static {
        y = "#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\n    f_Position = gl_Vertex.xy;\n    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n    gl_FrontColor = gl_Color;\n    gl_TexCoord[0] = gl_TextureMatrix[0] * gl_MultiTexCoord0;\n}\n";
        a = "#version 430 compatibility\nprecision highp float;\n\nlayout(location = 0) uniform vec4 u_InnerRect;         // Rectangle bounds\nlayout(location = 1) uniform vec4 u_Spread;            // Spread (sigma)\nlayout(location = 2) uniform vec4 u_ShadowColor;       // Shadow color (r, g, b, a)\nlayout(location = 3) uniform vec4 u_StrokeColor;       // Stroke color (r, g, b, a)\nlayout(location = 4) uniform vec4 u_RectColor;         // Black rectangle color (r, g, b, a)\nlayout(location = 5) uniform vec4 u_CornerRadius;      // Corner radius\nlayout(location = 6) uniform vec4 u_ShadowOffset;      // Shadow offset (x, y)\nlayout(location = 7) uniform vec4 u_StrokeWidth;       // Stroke width\nlayout(location = 8) uniform vec4 u_DrawCircle;        // DrawCircle (1/0)\nlayout(location = 9) uniform vec4 u_CircleThickness;   // Circle thickness\nlayout(location = 10) uniform vec4 u_CircleColor;      // Circle stroke color (r, g, b, a)\nlayout(location = 11) uniform vec4 u_CircleYOffset;    // Circle Y offset\nlayout(location = 12) uniform vec4 u_DisableShader;    // DisableShader (1/0)\n\nlayout(location = 13) uniform sampler2D u_Texture;     // Texture sampler\n\nin vec2 f_Position;\nout vec4 fragColor;\n\n// Helper function to draw the shadow\nvec4 drawRectShadow(vec2 pos, vec4 rect, vec4 color, float sigma, float cornerRadius) {\n    vec2 shadowOffsetPos = pos - rect.xy - rect.zw * 0.5;\n    float shadowDist = length(max(abs(shadowOffsetPos) - rect.zw * 0.5 + vec2(cornerRadius), 0.0));\n    float shadowMask = 1.0 - smoothstep(0.0, sigma, shadowDist);\n    return vec4(color.rgb, color.a * shadowMask);\n}\n\n// Calculate distance to the outer edge of the rounded rectangle\nfloat distToRoundedRect(vec2 p, vec4 rect, float r) {\n    vec2 d = abs(p - rect.xy - rect.zw * 0.5) - rect.zw * 0.5 + vec2(r);\n    return length(max(d, 0.0)) - r;\n}\n\n// Function to draw a circle stroke\nvec4 drawCircleStroke(vec2 pos, vec2 center, float radius, float thickness, vec4 strokeColor) {\n    float dist = length(pos - center);\n    float outerEdge = radius + thickness * 0.5;\n    float innerEdge = radius - thickness * 0.5;\n    float strokeAlpha = smoothstep(innerEdge - 0.5, innerEdge, dist) * (1.0 - smoothstep(outerEdge, outerEdge + 0.5, dist));\n    return vec4(strokeColor.rgb, strokeColor.a * strokeAlpha);\n}\n\nvoid main() {\n    if (u_DisableShader.x == 1.0) {\n        vec2 coords = gl_TexCoord[0].xy;\n        fragColor = texture2D(u_Texture, coords);  // Output the texture color\n        return;\n    }\n\n    float sigma = u_Spread.x;\n    float cornerRadius = u_CornerRadius.x;\n    vec2 shadowOffset = u_ShadowOffset.xy;\n    float strokeWidth = u_StrokeWidth.x;\n\n    vec4 rect = u_InnerRect;\n    vec4 shadowRect = vec4(vec2(rect.x + shadowOffset.x, rect.y + shadowOffset.y), vec2(rect.z, rect.w));\n    vec4 shadowColor = u_ShadowColor;\n    vec3 strokeColor = u_StrokeColor.rgb;\n    vec3 blackColor = u_RectColor.rgb;\n    float blackAlpha = u_RectColor.a;\n    float strokeAlpha = u_StrokeColor.a;\n\n    vec2 offsetPos = f_Position - shadowOffset;\n    vec4 result = drawRectShadow(offsetPos, shadowRect, shadowColor, sigma, cornerRadius);\n\n    float dist = distToRoundedRect(f_Position, rect, cornerRadius);\n    float aaWidth = 0.05 * sigma;\n\n    float strokeOuterEdge = strokeWidth;\n    float strokeInnerEdge = 0.0;\n\n    if (dist <= 0.0) {\n        result.rgb = blackColor;\n        result.a = blackAlpha;\n    } else if (dist > 0.0 && dist <= strokeOuterEdge) {\n        float innerStrokeAlpha = smoothstep(strokeInnerEdge, strokeInnerEdge + 0.5, dist);\n        result.rgb = mix(blackColor, strokeColor, innerStrokeAlpha);\n        result.a = mix(blackAlpha, 1.0, innerStrokeAlpha);\n    } else if (dist > strokeOuterEdge && dist <= (strokeOuterEdge + aaWidth)) {\n        float outerStrokeAlpha = smoothstep(strokeOuterEdge, strokeOuterEdge + aaWidth, dist);\n        result.rgb = mix(strokeColor, shadowColor.rgb, outerStrokeAlpha);\n        result.a = mix(1.0, result.a, outerStrokeAlpha);\n    } else {\n        float outerCornerSmoothing = smoothstep(0.0, aaWidth, dist);\n        result.rgb = mix(blackColor, shadowColor.rgb, outerCornerSmoothing);\n        result.a = mix(blackAlpha, result.a, outerCornerSmoothing);\n    }\n\n    if (u_DrawCircle.x == 1.0) {\n        // Draw a circle stroke in the middle of the black rectangle\n        vec2 circleCenter = rect.xy + rect.zw * 0.5 + vec2(0.0, u_CircleYOffset.x);\n        vec4 circleStroke = drawCircleStroke(f_Position, circleCenter, 5.0, u_CircleThickness.x, u_CircleColor);\n\n        // Blend the circle stroke with the black rectangle color\n        result.rgb = mix(result.rgb, circleStroke.rgb, circleStroke.a);\n        result.a = mix(result.a, blackAlpha, circleStroke.a);\n    }\n\n    fragColor = result;\n}\n";
        C = 256;
    }

    public void D(Color color) {
        GL20.glUniform4f((int)3, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
    }

    public void l(Color color) {
        GL20.glUniform4f((int)4, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
    }

    public void r(boolean bl) {
        GL20.glUniform4f((int)12, (float)(bl ? 1.0f : 0.0f), (float)0.0f, (float)0.0f, (float)0.0f);
    }

    public void j(float f) {
        GL20.glUniform4f((int)1, (float)f, (float)0.0f, (float)0.0f, (float)0.0f);
    }

    public CompositeRoundedRectShaderProgram() {
        super("#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\n    f_Position = gl_Vertex.xy;\n    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n    gl_FrontColor = gl_Color;\n    gl_TexCoord[0] = gl_TextureMatrix[0] * gl_MultiTexCoord0;\n}\n", "#version 430 compatibility\nprecision highp float;\n\nlayout(location = 0) uniform vec4 u_InnerRect;         // Rectangle bounds\nlayout(location = 1) uniform vec4 u_Spread;            // Spread (sigma)\nlayout(location = 2) uniform vec4 u_ShadowColor;       // Shadow color (r, g, b, a)\nlayout(location = 3) uniform vec4 u_StrokeColor;       // Stroke color (r, g, b, a)\nlayout(location = 4) uniform vec4 u_RectColor;         // Black rectangle color (r, g, b, a)\nlayout(location = 5) uniform vec4 u_CornerRadius;      // Corner radius\nlayout(location = 6) uniform vec4 u_ShadowOffset;      // Shadow offset (x, y)\nlayout(location = 7) uniform vec4 u_StrokeWidth;       // Stroke width\nlayout(location = 8) uniform vec4 u_DrawCircle;        // DrawCircle (1/0)\nlayout(location = 9) uniform vec4 u_CircleThickness;   // Circle thickness\nlayout(location = 10) uniform vec4 u_CircleColor;      // Circle stroke color (r, g, b, a)\nlayout(location = 11) uniform vec4 u_CircleYOffset;    // Circle Y offset\nlayout(location = 12) uniform vec4 u_DisableShader;    // DisableShader (1/0)\n\nlayout(location = 13) uniform sampler2D u_Texture;     // Texture sampler\n\nin vec2 f_Position;\nout vec4 fragColor;\n\n// Helper function to draw the shadow\nvec4 drawRectShadow(vec2 pos, vec4 rect, vec4 color, float sigma, float cornerRadius) {\n    vec2 shadowOffsetPos = pos - rect.xy - rect.zw * 0.5;\n    float shadowDist = length(max(abs(shadowOffsetPos) - rect.zw * 0.5 + vec2(cornerRadius), 0.0));\n    float shadowMask = 1.0 - smoothstep(0.0, sigma, shadowDist);\n    return vec4(color.rgb, color.a * shadowMask);\n}\n\n// Calculate distance to the outer edge of the rounded rectangle\nfloat distToRoundedRect(vec2 p, vec4 rect, float r) {\n    vec2 d = abs(p - rect.xy - rect.zw * 0.5) - rect.zw * 0.5 + vec2(r);\n    return length(max(d, 0.0)) - r;\n}\n\n// Function to draw a circle stroke\nvec4 drawCircleStroke(vec2 pos, vec2 center, float radius, float thickness, vec4 strokeColor) {\n    float dist = length(pos - center);\n    float outerEdge = radius + thickness * 0.5;\n    float innerEdge = radius - thickness * 0.5;\n    float strokeAlpha = smoothstep(innerEdge - 0.5, innerEdge, dist) * (1.0 - smoothstep(outerEdge, outerEdge + 0.5, dist));\n    return vec4(strokeColor.rgb, strokeColor.a * strokeAlpha);\n}\n\nvoid main() {\n    if (u_DisableShader.x == 1.0) {\n        vec2 coords = gl_TexCoord[0].xy;\n        fragColor = texture2D(u_Texture, coords);  // Output the texture color\n        return;\n    }\n\n    float sigma = u_Spread.x;\n    float cornerRadius = u_CornerRadius.x;\n    vec2 shadowOffset = u_ShadowOffset.xy;\n    float strokeWidth = u_StrokeWidth.x;\n\n    vec4 rect = u_InnerRect;\n    vec4 shadowRect = vec4(vec2(rect.x + shadowOffset.x, rect.y + shadowOffset.y), vec2(rect.z, rect.w));\n    vec4 shadowColor = u_ShadowColor;\n    vec3 strokeColor = u_StrokeColor.rgb;\n    vec3 blackColor = u_RectColor.rgb;\n    float blackAlpha = u_RectColor.a;\n    float strokeAlpha = u_StrokeColor.a;\n\n    vec2 offsetPos = f_Position - shadowOffset;\n    vec4 result = drawRectShadow(offsetPos, shadowRect, shadowColor, sigma, cornerRadius);\n\n    float dist = distToRoundedRect(f_Position, rect, cornerRadius);\n    float aaWidth = 0.05 * sigma;\n\n    float strokeOuterEdge = strokeWidth;\n    float strokeInnerEdge = 0.0;\n\n    if (dist <= 0.0) {\n        result.rgb = blackColor;\n        result.a = blackAlpha;\n    } else if (dist > 0.0 && dist <= strokeOuterEdge) {\n        float innerStrokeAlpha = smoothstep(strokeInnerEdge, strokeInnerEdge + 0.5, dist);\n        result.rgb = mix(blackColor, strokeColor, innerStrokeAlpha);\n        result.a = mix(blackAlpha, 1.0, innerStrokeAlpha);\n    } else if (dist > strokeOuterEdge && dist <= (strokeOuterEdge + aaWidth)) {\n        float outerStrokeAlpha = smoothstep(strokeOuterEdge, strokeOuterEdge + aaWidth, dist);\n        result.rgb = mix(strokeColor, shadowColor.rgb, outerStrokeAlpha);\n        result.a = mix(1.0, result.a, outerStrokeAlpha);\n    } else {\n        float outerCornerSmoothing = smoothstep(0.0, aaWidth, dist);\n        result.rgb = mix(blackColor, shadowColor.rgb, outerCornerSmoothing);\n        result.a = mix(blackAlpha, result.a, outerCornerSmoothing);\n    }\n\n    if (u_DrawCircle.x == 1.0) {\n        // Draw a circle stroke in the middle of the black rectangle\n        vec2 circleCenter = rect.xy + rect.zw * 0.5 + vec2(0.0, u_CircleYOffset.x);\n        vec4 circleStroke = drawCircleStroke(f_Position, circleCenter, 5.0, u_CircleThickness.x, u_CircleColor);\n\n        // Blend the circle stroke with the black rectangle color\n        result.rgb = mix(result.rgb, circleStroke.rgb, circleStroke.a);\n        result.a = mix(result.a, blackAlpha, circleStroke.a);\n    }\n\n    fragColor = result;\n}\n");
    }

    public void Y(float f) {
        GL20.glUniform4f((int)9, (float)f, (float)0.0f, (float)0.0f, (float)0.0f);
    }

    public void r(float f) {
        GL20.glUniform4f((int)5, (float)f, (float)0.0f, (float)0.0f, (float)0.0f);
    }

    public void L(float f) {
        GL20.glUniform4f((int)7, (float)f, (float)0.0f, (float)0.0f, (float)0.0f);
    }

    public void s(Color color) {
        GL20.glUniform4f((int)10, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
    }

    public void x(float f, float f2) {
        GL20.glUniform4f((int)6, (float)f, (float)f2, (float)0.0f, (float)0.0f);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void z(float f, float f2, float f3, float f4) {
        GL20.glUniform4f((int)0, (float)f, (float)f2, (float)f3, (float)f4);
    }
}

