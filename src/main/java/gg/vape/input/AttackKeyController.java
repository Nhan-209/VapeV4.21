package gg.vape.input;

import gg.vape.event.impl.SyntheticAttackRequestEvent;
import gg.vape.input.KeyBindingHelper;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;

public class AttackKeyController {
    public static void Q() {
        KeyBinding keyBinding = Minecraft.a_w3_0_S().F();
        KeyBindingHelper.v(keyBinding, false, false);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static boolean u(Mod mod) {
        SyntheticAttackRequestEvent syntheticAttackRequestEvent = new SyntheticAttackRequestEvent(mod);
        if (syntheticAttackRequestEvent.fire()) {
            return false;
        }
        AttackKeyController.o();
        return true;
    }

    public static void o() {
        KeyBindingHelper.v(Minecraft.a_w3_0_S().F(), true, true);
    }
}

