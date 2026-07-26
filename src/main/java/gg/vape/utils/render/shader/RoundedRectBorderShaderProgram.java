package gg.vape.utils.render.shader;

import gg.vape.utils.render.shader.ShaderProgram;
import org.lwjgl.opengl.GL20;

public class RoundedRectBorderShaderProgram
extends ShaderProgram {
    private static final String i;
    private static final String o;

    static {
        o = "#version 430 compatibility\nprecision highp float;\nlayout(location = 0) uniform vec3 u_Radius;\nlayout(location = 1) uniform vec4 u_InnerRect;\nin vec2 f_Position;\nout vec4 fragColor;\nvoid main() {\nvec2 tl = u_InnerRect.xy - f_Position;\nvec2 br = f_Position - u_InnerRect.zw;\nvec2 dis = max(br, tl);\nfloat v = length(max(vec2(0.0), dis)) - u_Radius.x;\nfloat a = 1.0 - smoothstep(-u_Radius.y, 0.0, abs(v) - u_Radius.z);\nfragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);\n}\n";
        i = "#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\ngl_FrontColor = gl_Color;\n}\n";
    }

    public void A(float f, float f2, float f3, float f4) {
        GL20.glUniform4f((int)1, (float)f, (float)f2, (float)f3, (float)f4);
    }

    public RoundedRectBorderShaderProgram() {
        super("#version 430 compatibility\nout vec2 f_Position;\nvoid main() {\nf_Position = gl_Vertex.xy;\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\ngl_FrontColor = gl_Color;\n}\n", "#version 430 compatibility\nprecision highp float;\nlayout(location = 0) uniform vec3 u_Radius;\nlayout(location = 1) uniform vec4 u_InnerRect;\nin vec2 f_Position;\nout vec4 fragColor;\nvoid main() {\nvec2 tl = u_InnerRect.xy - f_Position;\nvec2 br = f_Position - u_InnerRect.zw;\nvec2 dis = max(br, tl);\nfloat v = length(max(vec2(0.0), dis)) - u_Radius.x;\nfloat a = 1.0 - smoothstep(-u_Radius.y, 0.0, abs(v) - u_Radius.z);\nfragColor = gl_Color * vec4(1.0, 1.0, 1.0, a);\n}\n");
    }

    public void F(float f, float f2, float f3) {
        GL20.glUniform3f((int)0, (float)f, (float)f2, (float)f3);
    }
}

