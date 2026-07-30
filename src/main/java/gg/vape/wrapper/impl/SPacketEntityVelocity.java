package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MSPacketEntityVelocity;

public class SPacketEntityVelocity
extends Packet {
    private Vec3 E() {
        return new Vec3(MSPacketEntityVelocity.I(SPacketEntityVelocity.vapeInstance.getMappings().s, this.I));
    }

    public int getMotionZ() {
        if (this.I == null) {
            return 0;
        }
        if (ForgeVersion.MC_1_21_10.d()) {
            return (int)(this.E().getZ() * 8000.0);
        }
        return MSPacketEntityVelocity.X(SPacketEntityVelocity.vapeInstance.getMappings().s, this.I);
    }

    public void setMotionZ(double d) {
        if (this.I == null) {
            return;
        }
        if (ForgeVersion.MC_26_1.d()) {
            throw new UnsupportedOperationException("Use packet reconstruction for MC 26.1+");
        }
        if (ForgeVersion.MC_1_21_10.d()) {
            Vec3 vec3 = this.E();
            vec3.Z(d);
            MSPacketEntityVelocity.E(SPacketEntityVelocity.vapeInstance.getMappings().s, this.I, vec3.getObject());
            return;
        }
        MSPacketEntityVelocity.k(SPacketEntityVelocity.vapeInstance.getMappings().s, this.I, (int)d);
    }

    public int getMotionY() {
        if (this.I == null) {
            return 0;
        }
        if (ForgeVersion.MC_1_21_10.d()) {
            return (int)(this.E().getY() * 8000.0);
        }
        return MSPacketEntityVelocity.g(SPacketEntityVelocity.vapeInstance.getMappings().s, this.I);
    }

    public void setMotionX(double d) {
        if (this.I == null) {
            return;
        }
        if (ForgeVersion.MC_26_1.d()) {
            throw new UnsupportedOperationException("Use packet reconstruction for MC 26.1+");
        }
        if (ForgeVersion.MC_1_21_10.d()) {
            Vec3 vec3 = this.E();
            vec3.N(d);
            MSPacketEntityVelocity.E(SPacketEntityVelocity.vapeInstance.getMappings().s, this.I, vec3.getObject());
            return;
        }
        MSPacketEntityVelocity.q(SPacketEntityVelocity.vapeInstance.getMappings().s, this.I, (int)d);
    }

    public int getMotionX() {
        if (this.I == null) {
            return 0;
        }
        if (ForgeVersion.MC_1_21_10.d()) {
            return (int)(this.E().getX() * 8000.0);
        }
        return MSPacketEntityVelocity.a(SPacketEntityVelocity.vapeInstance.getMappings().s, this.I);
    }

    public void setMotionY(double d) {
        if (this.I == null) {
            return;
        }
        if (ForgeVersion.MC_26_1.d()) {
            throw new UnsupportedOperationException("Use packet reconstruction for MC 26.1+");
        }
        if (ForgeVersion.MC_1_21_10.d()) {
            Vec3 vec3 = this.E();
            vec3.m(d);
            MSPacketEntityVelocity.E(SPacketEntityVelocity.vapeInstance.getMappings().s, this.I, vec3.getObject());
            return;
        }
        MSPacketEntityVelocity.Q(SPacketEntityVelocity.vapeInstance.getMappings().s, this.I, (int)d);
    }

    public SPacketEntityVelocity(Object object) {
        super(object);
    }

    public int getEntityId() {
        if (this.I == null) {
            return 0;
        }
        return MSPacketEntityVelocity.V(SPacketEntityVelocity.vapeInstance.getMappings().s, this.I);
    }

    private static UnsupportedOperationException a(UnsupportedOperationException unsupportedOperationException) {
        return unsupportedOperationException;
    }
}

