package gg.vape.module.blatant;

import gg.vape.config.ClientSettings;
import gg.vape.input.KeyBindingHelper;
import gg.vape.wrapper.impl.KeyBinding;

public class InvWalkSettingsState {
    private static int keyState;

    public static int m() {
        int n = InvWalkSettingsState.r();
        if (n == 0) {
            return 116;
        }
        return 0;
    }

    public static void y(int n) {
        keyState = n;
    }

    public static boolean S(KeyBinding keyBinding) {
        return ClientSettings.B(keyBinding);
    }


    public static void C(KeyBinding keyBinding) {
        if (InvWalkSettingsState.S(keyBinding)) {
            InvWalkSettingsState.L(keyBinding, true);
        } else if (keyBinding.u()) {
            InvWalkSettingsState.L(keyBinding, false);
        }
    }

    public static void L(KeyBinding keyBinding, boolean bl) {
        KeyBindingHelper.d(keyBinding, bl);
    }

    public static int r() {
        return keyState;
    }

    static {
        if (InvWalkSettingsState.m() != 0) {
            InvWalkSettingsState.y(62);
        }
    }
}

