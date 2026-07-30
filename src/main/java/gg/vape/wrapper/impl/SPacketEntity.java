package gg.vape.wrapper.impl;

public class SPacketEntity
extends Packet {
    public int B() {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation j4_02 = this.r();
            return (int)j4_02.a().getZ();
        }
        return SPacketEntity.vapeInstance.getMappingsMapperCompat().qR.m(this.I);
    }

    public SPacketEntity(Object object) {
        super(object);
    }

    public int H() {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation j4_02 = this.r();
            return (int)j4_02.a().getX();
        }
        return SPacketEntity.vapeInstance.getMappingsMapperCompat().qR.g(this.I);
    }

    public byte D() {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation j4_02 = this.r();
            return (byte)j4_02.b();
        }
        return SPacketEntity.vapeInstance.getMappingsMapperCompat().qR.C(this.I);
    }

    public int M() {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation j4_02 = this.r();
            return (int)j4_02.a().getY();
        }
        return SPacketEntity.vapeInstance.getMappingsMapperCompat().qR.U(this.I);
    }

    public byte U() {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation j4_02 = this.r();
            return (byte)j4_02.t();
        }
        return SPacketEntity.vapeInstance.getMappingsMapperCompat().qR.u(this.I);
    }

    public PositionMoveRotation r() {
        return new PositionMoveRotation(SPacketEntity.vapeInstance.getMappingsMapperCompat().qR.K(this.I));
    }

    public int k() {
        return SPacketEntity.vapeInstance.getMappingsMapperCompat().qR.R(this.I);
    }
}

