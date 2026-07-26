package gg.vape.utils.render.shader;

import gg.vape.utils.render.shader.ShaderProgram;
import org.lwjgl.opengl.GL20;

public class CircleShaderProgram
extends ShaderProgram {
    private static final String z = "#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\ngl_FrontColor = gl_Color;\n}\n";
    private static final String L = "#version 430 compatibility\nprecision highp float;\nlayout(location = 0) uniform float u_Radius;\nlayout(location = 1) uniform float u_Feather;\nlayout(location = 2) uniform vec2 u_CenterPos;\nin vec2 f_Position;\nout vec4 fragColor;\nvoid main() {\nfloat v = length(f_Position - u_CenterPos);\nfloat a = 1.0 - smoothstep(u_Radius - u_Feather, u_Radius, v);\nfragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);\n}\n";

    public CircleShaderProgram(String string, String string2) {
        super(string, string2);
    }

    public void b(float f) {
        GL20.glUniform1f((int)0, (float)f);
    }

    public void e(float f, float f2) {
        GL20.glUniform2f((int)2, (float)f, (float)f2);
    }

    public CircleShaderProgram() {
        super("#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\ngl_FrontColor = gl_Color;\n}\n", "#version 430 compatibility\nprecision highp float;\nlayout(location = 0) uniform float u_Radius;\nlayout(location = 1) uniform float u_Feather;\nlayout(location = 2) uniform vec2 u_CenterPos;\nin vec2 f_Position;\nout vec4 fragColor;\nvoid main() {\nfloat v = length(f_Position - u_CenterPos);\nfloat a = 1.0 - smoothstep(u_Radius - u_Feather, u_Radius, v);\nfragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);\n}\n");
    }

    public void p(float f) {
        GL20.glUniform1f((int)1, (float)f);
    }
}

