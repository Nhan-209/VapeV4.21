package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.InputMappingsInput;

public class KeyBinding
extends Wrapper {
    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void setPressed(boolean bl) {
        if (bl) {
            this.I();
        } else {
            this.e();
        }
    }

    public static void H() {
        Vape.INSTANCE.getMappings().hJ.R();
    }

    public void Z() {
        boolean bl;
        boolean bl2 = ClientSettings.B(this);
        boolean bl3 = bl = this.isKeyDown() != bl2;
        if (bl) {
            this.setPressed(bl2);
        }
    }

    public boolean u() {
        return KeyBinding.c.getMappings().hJ.a(this.I);
    }

    public void e() {
        if (ForgeVersion.MC_1_16_5.d()) {
            KeyBinding.c.getMappings().hJ.S(this.I);
            return;
        }
        this.onTick(0);
        KeyBinding.setKeyBindState(this, false);
    }

    public boolean isKeyDown() {
        return KeyBinding.c.getMappings().hJ.S$src$Z$wvm5qt(this.I);
    }

    public static void setKeyBindState(KeyBinding keyBinding, boolean bl) {
        if (ForgeVersion.MC_1_16_5.d()) {
            KeyBinding.c.getMappings().hJ.d(keyBinding.u$src$Lgg_vape_wrapper_impl_InputMappingsInput_$1c10gpv().getObject(), bl);
            return;
        }
        KeyBinding.c.getMappings().hJ.g(keyBinding.getKeyCode(), bl);
    }

    public boolean isPressed() {
        return KeyBinding.c.getMappings().hJ.M(this.I);
    }

    public void onTick(int n) {
        KeyBinding.c.getMappings().hJ.q(this.I, n);
    }

    public KeyBinding(Object object) {
        super(object);
    }

    public int getKeyCode() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.u$src$Lgg_vape_wrapper_impl_InputMappingsInput_$1c10gpv().R();
        }
        return KeyBinding.c.getMappings().hJ.B(this.I);
    }

    private InputMappingsInput u$src$Lgg_vape_wrapper_impl_InputMappingsInput_$1c10gpv() {
        return new InputMappingsInput(KeyBinding.c.getMappings().hJ.l(this.I));
    }

    public static void onTick(KeyBinding keyBinding) {
        if (ForgeVersion.MC_1_16_5.d()) {
            KeyBinding.c.getMappings().hJ.x(keyBinding.u$src$Lgg_vape_wrapper_impl_InputMappingsInput_$1c10gpv().getObject());
            return;
        }
        KeyBinding.c.getMappings().hJ.s(keyBinding.getKeyCode());
    }

    public void setCancelled(boolean bl) {
        KeyBinding.c.getMappings().hJ.o(this.I, bl);
    }

    public void I() {
        KeyBinding.setKeyBindState(this, true);
        KeyBinding.onTick(this);
    }

    public int V() {
        return KeyBinding.c.getMappings().hJ.n(this.I);
    }
}

