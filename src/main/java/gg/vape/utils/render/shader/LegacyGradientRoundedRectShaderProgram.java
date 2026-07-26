package gg.vape.utils.render.shader;

import gg.vape.utils.render.shader.ShaderProgram;
import org.lwjgl.opengl.GL20;

public class LegacyGradientRoundedRectShaderProgram
extends ShaderProgram {
    private static final String q;
    private static final String s;

    public void T(float f, float f2) {
        GL20.glUniform2f((int)0, (float)f, (float)f2);
    }

    public void I(float f, float f2, float f3, float f4) {
        GL20.glUniform4f((int)4, (float)f, (float)f2, (float)f3, (float)f4);
    }

    public void t(float f) {
        GL20.glUniform1f((int)2, (float)f);
    }

    public void n(float f, float f2, float f3, float f4) {
        GL20.glUniform4f((int)3, (float)f, (float)f2, (float)f3, (float)f4);
    }

    public void q(float f, float f2) {
        GL20.glUniform2f((int)1, (float)f, (float)f2);
    }

    public LegacyGradientRoundedRectShaderProgram() {
        super("#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n}\n", "#version 430 compatibility\nprecision highp float;\nlayout(location = 0) uniform vec2 u_A;\nlayout(location = 1) uniform vec2 u_B;\nlayout(location = 2) uniform float u_Radius;\nlayout(location = 3) uniform vec4 u_StartHSBA;\nlayout(location = 4) uniform vec4 u_EndHSBA;\nin vec2 f_Position;\nout vec4 fragColor;\nvec3 hsb2rgb(float h, float s, float b) {\n    vec3 rgb = clamp(abs(mod(h * 6.0 + vec3(0.0, 4.0, 2.0), 6.0) - 3.0) - 1.0, 0.0, 1.0);\n    return b * mix(vec3(1.0), rgb, s);\n}\nvoid main() {\nvec2 pa = f_Position - u_A;\nvec2 ba = u_B - u_A;\nfloat h = clamp(dot(pa, ba) / dot(ba, ba), 0.0, 1.0);\nfloat d = length(pa - ba * h) - u_Radius;\nfloat w = fwidth(d);\nfloat alpha = 1.0 - smoothstep(-0.5 * w, 0.5 * w, d);\nvec4 hsba = mix(u_StartHSBA, u_EndHSBA, h);\nvec3 rgb = hsb2rgb(hsba.x, hsba.y, hsba.z);\nfragColor = vec4(rgb, hsba.w * alpha);\n}\n");
    }

    static {
        s = "#version 430 compatibility\nprecision highp float;\nlayout(location = 0) uniform vec2 u_A;\nlayout(location = 1) uniform vec2 u_B;\nlayout(location = 2) uniform float u_Radius;\nlayout(location = 3) uniform vec4 u_StartHSBA;\nlayout(location = 4) uniform vec4 u_EndHSBA;\nin vec2 f_Position;\nout vec4 fragColor;\nvec3 hsb2rgb(float h, float s, float b) {\n    vec3 rgb = clamp(abs(mod(h * 6.0 + vec3(0.0, 4.0, 2.0), 6.0) - 3.0) - 1.0, 0.0, 1.0);\n    return b * mix(vec3(1.0), rgb, s);\n}\nvoid main() {\nvec2 pa = f_Position - u_A;\nvec2 ba = u_B - u_A;\nfloat h = clamp(dot(pa, ba) / dot(ba, ba), 0.0, 1.0);\nfloat d = length(pa - ba * h) - u_Radius;\nfloat w = fwidth(d);\nfloat alpha = 1.0 - smoothstep(-0.5 * w, 0.5 * w, d);\nvec4 hsba = mix(u_StartHSBA, u_EndHSBA, h);\nvec3 rgb = hsb2rgb(hsba.x, hsba.y, hsba.z);\nfragColor = vec4(rgb, hsba.w * alpha);\n}\n";
        q = "#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n}\n";
    }
}

