package gg.vape.utils.render.shader;

import gg.vape.utils.render.shader.ShaderProgram;
import org.lwjgl.opengl.GL20;

public class ArcShaderProgram
extends ShaderProgram {
    private static final String H = "#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\ngl_FrontColor = gl_Color;\n}\n";
    private static final String I = "#version 430 compatibility\nprecision highp float;\nlayout(location = 0) uniform float u_Inner;\nlayout(location = 1) uniform float u_Radius;\nlayout(location = 2) uniform float u_Feather;\nlayout(location = 3) uniform vec2 u_CenterPos;\nlayout(location = 4) uniform float u_MiddleAngle;\nlayout(location = 5) uniform float u_SweepAngle;\nin vec2 f_Position;\nout vec4 fragColor;\nvoid main() {\nvec2 v = f_Position - u_CenterPos;\nfloat strokeRadius = u_Radius - u_Inner;\nfloat d1 = abs(length(v) - u_Radius) - strokeRadius;\nfloat a1 = smoothstep(-u_Feather, 0.0, d1);\nfloat c = cos(u_SweepAngle * 0.00872664626);\nfloat f = u_MiddleAngle * 0.01745329252;\nvec2 up = vec2(cos(f), sin(f));\nfloat d2 = dot(up, normalize(v)) - c;\nfloat w = u_Feather * fwidth(d2);\nfloat a2 = smoothstep(w * -1.0, w * 1.0, d2);\nfloat a = (1.0 - a1) * a2;\nfragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);\n}\n";

    public ArcShaderProgram() {
        super("#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\ngl_FrontColor = gl_Color;\n}\n", "#version 430 compatibility\nprecision highp float;\nlayout(location = 0) uniform float u_Inner;\nlayout(location = 1) uniform float u_Radius;\nlayout(location = 2) uniform float u_Feather;\nlayout(location = 3) uniform vec2 u_CenterPos;\nlayout(location = 4) uniform float u_MiddleAngle;\nlayout(location = 5) uniform float u_SweepAngle;\nin vec2 f_Position;\nout vec4 fragColor;\nvoid main() {\nvec2 v = f_Position - u_CenterPos;\nfloat strokeRadius = u_Radius - u_Inner;\nfloat d1 = abs(length(v) - u_Radius) - strokeRadius;\nfloat a1 = smoothstep(-u_Feather, 0.0, d1);\nfloat c = cos(u_SweepAngle * 0.00872664626);\nfloat f = u_MiddleAngle * 0.01745329252;\nvec2 up = vec2(cos(f), sin(f));\nfloat d2 = dot(up, normalize(v)) - c;\nfloat w = u_Feather * fwidth(d2);\nfloat a2 = smoothstep(w * -1.0, w * 1.0, d2);\nfloat a = (1.0 - a1) * a2;\nfragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);\n}\n");
    }

    public void H(float f, float f2) {
        if (f2 != -360.0f) {
            f2 %= 360.0f;
        }
        float f3 = f % 360.0f + f2 * 0.5f;
        GL20.glUniform1f((int)4, (float)f3);
        GL20.glUniform1f((int)5, (float)f2);
    }

    public void B(float f, float f2) {
        GL20.glUniform2f((int)3, (float)f, (float)f2);
    }

    public void g(float f) {
        GL20.glUniform1f((int)2, (float)f);
    }

    public void X(float f, float f2) {
        GL20.glUniform1f((int)0, (float)((f -= 1.0f) - f2));
        GL20.glUniform1f((int)1, (float)f);
    }
}

