package gg.vape.utils.render.shader;

import gg.vape.utils.render.shader.ShaderProgram;
import org.lwjgl.opengl.GL20;

public class VignetteShaderProgram
extends ShaderProgram {
    private static final String n;
    private static final String E;

    public void A(float f) {
        GL20.glUniform1f((int)1, (float)f);
    }

    public void f(float f) {
        GL20.glUniform1f((int)2, (float)f);
    }

    public VignetteShaderProgram(float f, float f2, float f3, float f4, float f5) {
        super("#version 430 compatibility\n\nvoid main(void)\n{\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n}", "#version 430 compatibility\nlayout(location = 0) uniform float outerRadius;\nlayout(location = 1) uniform float innerRadius;\nlayout(location = 2) uniform float intensity;\nlayout(location = 3) uniform vec2 u_resolution;\nvoid main(void) {\n    vec4 color = vec4(1.0, 1.0, 1.0, 1.0);\n    vec2 relativePosition = gl_FragCoord.xy / u_resolution - .5;\n    float len = length(relativePosition);\n    float vignette = smoothstep(outerRadius, innerRadius, len);\n    color.rbg = mix(color.rgb, color.rgb * vignette, intensity);\n    gl_FragColor = color;}");
        GL20.glUniform1f((int)0, (float)f);
        GL20.glUniform1f((int)1, (float)f2);
        GL20.glUniform1f((int)2, (float)f3);
        GL20.glUniform2f((int)3, (float)f4, (float)f5);
    }

    public void N(float f, float f2) {
        GL20.glUniform2f((int)3, (float)f, (float)f2);
    }

    public VignetteShaderProgram() {
        super("#version 430 compatibility\n\nvoid main(void)\n{\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n}", "#version 430 compatibility\nlayout(location = 0) uniform float outerRadius;\nlayout(location = 1) uniform float innerRadius;\nlayout(location = 2) uniform float intensity;\nlayout(location = 3) uniform vec2 u_resolution;\nvoid main(void) {\n    vec4 color = vec4(1.0, 1.0, 1.0, 1.0);\n    vec2 relativePosition = gl_FragCoord.xy / u_resolution - .5;\n    float len = length(relativePosition);\n    float vignette = smoothstep(outerRadius, innerRadius, len);\n    color.rbg = mix(color.rgb, color.rgb * vignette, intensity);\n    gl_FragColor = color;}");
    }

    public void h(float f) {
        GL20.glUniform1f((int)0, (float)f);
    }

    static {
        E = "#version 430 compatibility\n\nvoid main(void)\n{\ngl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n}";
        n = "#version 430 compatibility\nlayout(location = 0) uniform float outerRadius;\nlayout(location = 1) uniform float innerRadius;\nlayout(location = 2) uniform float intensity;\nlayout(location = 3) uniform vec2 u_resolution;\nvoid main(void) {\n    vec4 color = vec4(1.0, 1.0, 1.0, 1.0);\n    vec2 relativePosition = gl_FragCoord.xy / u_resolution - .5;\n    float len = length(relativePosition);\n    float vignette = smoothstep(outerRadius, innerRadius, len);\n    color.rbg = mix(color.rgb, color.rgb * vignette, intensity);\n    gl_FragColor = color;}";
    }
}

