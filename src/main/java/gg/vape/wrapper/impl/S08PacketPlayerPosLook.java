package gg.vape.wrapper.impl;

public class S08PacketPlayerPosLook
extends Packet {
    public void setPitch(float f) {
        S08PacketPlayerPosLook.vapeInstance.getMappingsMapperCompat().q9.K(this.I, f);
    }

    public void setYaw(float f) {
        S08PacketPlayerPosLook.vapeInstance.getMappingsMapperCompat().q9.Q(this.I, f);
    }

    public S08PacketPlayerPosLook(Object object) {
        super(object);
    }

    public float getPitch() {
        return S08PacketPlayerPosLook.vapeInstance.getMappingsMapperCompat().q9.I(this.I);
    }

    public float getYaw() {
        return S08PacketPlayerPosLook.vapeInstance.getMappingsMapperCompat().q9.j(this.I);
    }
}

