package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MPacketVelocityBridge;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.Vec3;
import java.util.Optional;

public class PacketVelocityBridge
extends Packet {
    private void U(float f) {
        Optional<Object> optional = this.P();
        if (optional.isPresent()) {
            Vec3 vec3 = new Vec3(optional.get());
            vec3.N(f);
        }
    }

    public void setMotionY(float f) {
        if (ForgeVersion.MC_1_21_0.d()) {
            this.q(f);
            return;
        }
        MPacketVelocityBridge.u(PacketVelocityBridge.c.getMappingsMapperCompat().qO, this.I, f);
    }

    public float getMotionZ() {
        if (ForgeVersion.MC_1_21_0.d()) {
            return this.g();
        }
        return MPacketVelocityBridge.Y(PacketVelocityBridge.c.getMappingsMapperCompat().qO, this.I);
    }

    private float g() {
        Optional<Object> optional = this.P();
        if (optional.isPresent()) {
            Vec3 vec3 = new Vec3(optional.get());
            return (float)vec3.getZ();
        }
        return 0.0f;
    }

    public void setMotionX(float f) {
        if (ForgeVersion.MC_1_21_0.d()) {
            this.U(f);
            return;
        }
        MPacketVelocityBridge.P(PacketVelocityBridge.c.getMappingsMapperCompat().qO, this.I, f);
    }

    public float getMotionY() {
        if (ForgeVersion.MC_1_21_0.d()) {
            return this.K();
        }
        return MPacketVelocityBridge.A(PacketVelocityBridge.c.getMappingsMapperCompat().qO, this.I);
    }

    public PacketVelocityBridge(Object object) {
        super(object);
    }

    private void P(float f) {
        Optional<Object> optional = this.P();
        if (optional.isPresent()) {
            Vec3 vec3 = new Vec3(optional.get());
            vec3.Z(f);
        }
    }

    public void setMotionZ(float f) {
        if (ForgeVersion.MC_1_21_0.d()) {
            this.P(f);
            return;
        }
        MPacketVelocityBridge.i(PacketVelocityBridge.c.getMappingsMapperCompat().qO, this.I, f);
    }

    private float T() {
        Optional<Object> optional = this.P();
        if (optional.isPresent()) {
            Vec3 vec3 = new Vec3(optional.get());
            return (float)vec3.getX();
        }
        return 0.0f;
    }

    private void q(float f) {
        Optional<Object> optional = this.P();
        if (optional.isPresent()) {
            Vec3 vec3 = new Vec3(optional.get());
            vec3.m(f);
        }
    }

    private float K() {
        Optional<Object> optional = this.P();
        if (optional.isPresent()) {
            Vec3 vec3 = new Vec3(optional.get());
            return (float)vec3.getY();
        }
        return 0.0f;
    }

    public float getMotionX() {
        if (ForgeVersion.MC_1_21_0.d()) {
            return this.T();
        }
        return MPacketVelocityBridge.z(PacketVelocityBridge.c.getMappingsMapperCompat().qO, this.I);
    }

    public Optional<Object> P() {
        return PacketVelocityBridge.c.getMappingsMapperCompat().qO.M(this.I);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }
}

