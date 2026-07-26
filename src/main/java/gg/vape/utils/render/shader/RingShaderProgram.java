package gg.vape.utils.render.shader;

import gg.vape.utils.render.shader.ShaderProgram;
import org.lwjgl.opengl.GL20;

public class RingShaderProgram
extends ShaderProgram {
    private static final String z = "#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\ngl_FrontColor = gl_Color;\n}\n";
    private static final String R = "#version 430 compatibility\nprecision highp float;\nlayout(location = 0) uniform float u_Inner;\nlayout(location = 1) uniform float u_Radius;\nlayout(location = 2) uniform float u_Feather;\nlayout(location = 3) uniform vec2 u_CenterPos;\nin vec2 f_Position;\nout vec4 fragColor;\nvoid main() {\nfloat v = length(f_Position - u_CenterPos);\nfloat a = smoothstep(u_Inner, u_Inner + u_Feather, v) * (1.0 - smoothstep(u_Radius - u_Feather, u_Radius, v));\nfragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);\n}\n";

    public void I(float f, float f2) {
        GL20.glUniform1f((int)0, (float)(f - f2));
        GL20.glUniform1f((int)1, (float)f);
    }

    public void A(float f) {
        GL20.glUniform1f((int)2, (float)f);
    }

    public RingShaderProgram() {
        super("#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\ngl_FrontColor = gl_Color;\n}\n", "#version 430 compatibility\nprecision highp float;\nlayout(location = 0) uniform float u_Inner;\nlayout(location = 1) uniform float u_Radius;\nlayout(location = 2) uniform float u_Feather;\nlayout(location = 3) uniform vec2 u_CenterPos;\nin vec2 f_Position;\nout vec4 fragColor;\nvoid main() {\nfloat v = length(f_Position - u_CenterPos);\nfloat a = smoothstep(u_Inner, u_Inner + u_Feather, v) * (1.0 - smoothstep(u_Radius - u_Feather, u_Radius, v));\nfragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);\n}\n");
    }

    public void x(float f, float f2) {
        GL20.glUniform2f((int)3, (float)f, (float)f2);
    }
}

