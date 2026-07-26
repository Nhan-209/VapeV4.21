package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MMovementInput;
import gg.vape.module.render.freecam.FreecamMovementInputBridge;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.PlayerInput;

public class MovementInput
extends Wrapper {
    public void B(float f) {
        if (ForgeVersion.MC_1_21_6.d()) {
            PlayerInput playerInput = new PlayerInput(MMovementInput.W(MovementInput.c.getMappings().h7, this.I));
            playerInput.A(f);
            return;
        }
        MMovementInput.p(MovementInput.c.getMappings().h7, this.I, f);
    }

    public MovementInput(Object object) {
        super(object);
    }

    public void r(FreecamMovementInputBridge freecamMovementInputBridge) {
        MMovementInput.x(MovementInput.c.getMappings().h7, this.I, freecamMovementInputBridge.getObject());
    }

    public void M(float f) {
        if (ForgeVersion.MC_1_21_6.d()) {
            PlayerInput playerInput = new PlayerInput(MMovementInput.W(MovementInput.c.getMappings().h7, this.I));
            playerInput.N(f);
            return;
        }
        MMovementInput.j(MovementInput.c.getMappings().h7, this.I, f);
    }

    public FreecamMovementInputBridge b() {
        return new FreecamMovementInputBridge(MMovementInput.x(MovementInput.c.getMappings().h7, this.I));
    }

    public float T() {
        if (ForgeVersion.MC_1_21_6.d()) {
            PlayerInput playerInput = new PlayerInput(MMovementInput.W(MovementInput.c.getMappings().h7, this.I));
            return playerInput.P();
        }
        return MMovementInput.b(MovementInput.c.getMappings().h7, this.I);
    }

    public void V(boolean bl) {
        if (ForgeVersion.MC_1_21_4.d()) {
            this.b().F(bl);
            return;
        }
        MMovementInput.H(MovementInput.c.getMappings().h7, this.I, bl);
    }

    public float D() {
        if (ForgeVersion.MC_1_21_6.d()) {
            PlayerInput playerInput = new PlayerInput(MMovementInput.W(MovementInput.c.getMappings().h7, this.I));
            return playerInput.Z();
        }
        return MMovementInput.f(MovementInput.c.getMappings().h7, this.I);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean G() {
        if (ForgeVersion.MC_1_21_4.d()) {
            return this.b().f();
        }
        return MMovementInput.j(MovementInput.c.getMappings().h7, this.I);
    }

    public void setCancelled(boolean bl) {
        if (ForgeVersion.MC_1_21_4.d()) {
            this.b().P(bl);
            return;
        }
        MMovementInput.V(MovementInput.c.getMappings().h7, this.I, bl);
    }

    public boolean D$src$Z$v5d6e8() {
        if (ForgeVersion.MC_1_21_4.d()) {
            return this.b().A();
        }
        return MMovementInput.R(MovementInput.c.getMappings().h7, this.I);
    }
}

