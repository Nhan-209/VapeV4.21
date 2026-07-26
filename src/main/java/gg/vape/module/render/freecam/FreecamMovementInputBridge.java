package gg.vape.module.render.freecam;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;

public class FreecamMovementInputBridge
extends Wrapper {
    public void N(boolean bl) {
        if (ForgeVersion.MC_1_21_4.d()) {
            this.r(this.x(), this.r(), this.e(), this.F(), this.f(), this.A(), bl);
            return;
        }
        FreecamMovementInputBridge.c.getMappingsMapperCompat().Cc.K(this.I, bl);
    }

    public boolean A() {
        return FreecamMovementInputBridge.c.getMappingsMapperCompat().Cc.r(this.I);
    }

    public boolean x() {
        return FreecamMovementInputBridge.c.getMappingsMapperCompat().Cc.H(this.I);
    }

    public boolean r() {
        return FreecamMovementInputBridge.c.getMappingsMapperCompat().Cc.W(this.I);
    }

    public boolean e() {
        return FreecamMovementInputBridge.c.getMappingsMapperCompat().Cc.K(this.I);
    }

    public FreecamMovementInputBridge(Object object) {
        super(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    private void r(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6, boolean bl7) {
        FreecamMovementInputBridge jh_22 = new FreecamMovementInputBridge(FreecamMovementInputBridge.c.getMappingsMapperCompat().Cc.z(bl, bl2, bl3, bl4, bl5, bl6, bl7));
        this.I = jh_22.getObject();
        Minecraft.a_xH_J().a_jw_2_I().r(jh_22);
    }

    public boolean u() {
        return FreecamMovementInputBridge.c.getMappingsMapperCompat().Cc.Y(this.I);
    }

    public void P(boolean bl) {
        if (ForgeVersion.MC_1_21_4.d()) {
            this.r(this.x(), this.r(), this.e(), this.F(), this.f(), bl, this.u());
            return;
        }
        FreecamMovementInputBridge.c.getMappingsMapperCompat().Cc.V(this.I, bl);
    }

    public boolean F() {
        return FreecamMovementInputBridge.c.getMappingsMapperCompat().Cc.A(this.I);
    }

    public void F(boolean bl) {
        if (ForgeVersion.MC_1_21_4.d()) {
            this.r(this.x(), this.r(), this.e(), this.F(), bl, this.A(), this.u());
            return;
        }
        FreecamMovementInputBridge.c.getMappingsMapperCompat().Cc.A(this.I, bl);
    }

    public boolean f() {
        return FreecamMovementInputBridge.c.getMappingsMapperCompat().Cc.p(this.I);
    }
}

