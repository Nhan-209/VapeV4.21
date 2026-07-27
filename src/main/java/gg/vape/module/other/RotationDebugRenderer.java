package gg.vape.module.other;

import gg.vape.utils.Vec3d;
import gg.vape.wrapper.impl.Entity;

public class RotationDebugRenderer {
    private int entityId;
    private String action;
    private Vec3d hitVec;
    private String entityName;
    private final int packetId;

    public RotationDebugRenderer(int n, Entity entity, String string) {
        this.packetId = n;
        this.entityName = entity.getName();
        this.entityId = entity.S();
        this.action = string;
    }

    public static String S(RotationDebugRenderer rotationDebugRenderer) {
        return rotationDebugRenderer.buildDebugString();
    }

    private String buildDebugString() {
        String string = String.format("[C02 | %d], Entity: %s (ID: %d), Action: %s", this.packetId, this.entityName, this.entityId, this.action);
        if (this.hitVec != null) {
            string = string + ", HitVec: [" + this.hitVec.Y() + " " + this.hitVec.t() + " " + this.hitVec.o() + "]";
        }
        return string;
    }

    public static Vec3d G(RotationDebugRenderer rotationDebugRenderer, Vec3d vec3d) {
        rotationDebugRenderer.hitVec = vec3d;
        return rotationDebugRenderer.hitVec;
    }
}

