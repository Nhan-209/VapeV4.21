package gg.vape.module.other;

import gg.vape.utils.Vec3d;
import gg.vape.wrapper.impl.Entity;

public class RotationDebugRenderer {
    private int j;
    private String N;
    private Vec3d w;
    private String k;
    private final int h;

    public RotationDebugRenderer(int n, Entity entity, String string) {
        this.h = n;
        this.k = entity.getName();
        this.j = entity.S();
        this.N = string;
    }

    public static String S(RotationDebugRenderer rotationDebugRenderer) {
        return rotationDebugRenderer.Y();
    }

    private String Y() {
        String string = String.format("[C02 | %d], Entity: %s (ID: %d), Action: %s", this.h, this.k, this.j, this.N);
        if (this.w != null) {
            string = string + ", HitVec: [" + this.w.Y() + " " + this.w.t() + " " + this.w.o() + "]";
        }
        return string;
    }

    public static Vec3d G(RotationDebugRenderer rotationDebugRenderer, Vec3d vec3d) {
        rotationDebugRenderer.w = vec3d;
        return rotationDebugRenderer.w;
    }
}

