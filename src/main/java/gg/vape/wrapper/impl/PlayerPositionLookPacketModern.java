package gg.vape.wrapper.impl;

import java.util.Set;

public class PlayerPositionLookPacketModern
extends Packet {
    public PositionMoveRotation B$src$Lgg_vape_wrapper_impl_PositionMoveRotation_$14trjiv() {
        return new PositionMoveRotation(PlayerPositionLookPacketModern.vapeInstance.getMappings().Ry.j(this.I));
    }

    public Set G() {
        return (Set)PlayerPositionLookPacketModern.vapeInstance.getMappings().Ry.B(this.I);
    }

    public void z(float f) {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation positionMoveRotation = this.B$src$Lgg_vape_wrapper_impl_PositionMoveRotation_$14trjiv();
            positionMoveRotation.y(f);
            return;
        }
        PlayerPositionLookPacketModern.vapeInstance.getMappings().Ry.o(this.I, f);
    }

    public float M() {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation positionMoveRotation = this.B$src$Lgg_vape_wrapper_impl_PositionMoveRotation_$14trjiv();
            return positionMoveRotation.t();
        }
        return PlayerPositionLookPacketModern.vapeInstance.getMappings().Ry.d(this.I);
    }

    public Set W() {
        if (ForgeVersion.MC_1_21_4.d()) {
            return this.G();
        }
        return (Set)PlayerPositionLookPacketModern.vapeInstance.getMappings().Ry.w(this.I);
    }

    public double H() {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation positionMoveRotation = this.B$src$Lgg_vape_wrapper_impl_PositionMoveRotation_$14trjiv();
            return positionMoveRotation.a().getY();
        }
        return PlayerPositionLookPacketModern.vapeInstance.getMappings().Ry.O(this.I);
    }

    public double S() {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation positionMoveRotation = this.B$src$Lgg_vape_wrapper_impl_PositionMoveRotation_$14trjiv();
            return positionMoveRotation.a().getX();
        }
        return PlayerPositionLookPacketModern.vapeInstance.getMappings().Ry.e(this.I);
    }

    public PlayerPositionLookPacketModern(Object object) {
        super(object);
    }

    public float f() {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation positionMoveRotation = this.B$src$Lgg_vape_wrapper_impl_PositionMoveRotation_$14trjiv();
            return positionMoveRotation.b();
        }
        return PlayerPositionLookPacketModern.vapeInstance.getMappings().Ry.g(this.I);
    }


    public void r(float f) {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation positionMoveRotation = this.B$src$Lgg_vape_wrapper_impl_PositionMoveRotation_$14trjiv();
            positionMoveRotation.V(f);
            return;
        }
        PlayerPositionLookPacketModern.vapeInstance.getMappings().Ry.F(this.I, f);
    }

    public int B() {
        return PlayerPositionLookPacketModern.vapeInstance.getMappings().Ry.n(this.I);
    }

    public double e() {
        if (ForgeVersion.MC_1_21_4.d()) {
            PositionMoveRotation positionMoveRotation = this.B$src$Lgg_vape_wrapper_impl_PositionMoveRotation_$14trjiv();
            return positionMoveRotation.a().getZ();
        }
        return PlayerPositionLookPacketModern.vapeInstance.getMappings().Ry.Q(this.I);
    }
}

