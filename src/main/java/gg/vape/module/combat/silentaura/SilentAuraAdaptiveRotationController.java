package gg.vape.module.combat.silentaura;

import gg.vape.module.combat.SilentAuraTargetingModule;
import gg.vape.module.combat.silentaura.SilentAuraAdaptiveRotationEntry;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class SilentAuraAdaptiveRotationController
extends AdaptiveRotationController {
    final SilentAuraTargetingModule d2;

    private static ObfuscatedRuntimeException d(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SilentAuraAdaptiveRotationController(SilentAuraTargetingModule silentAuraTargetingModule) {
        this.d2 = silentAuraTargetingModule;
    }

    @Override
    public float O() {
        switch (SilentAuraAdaptiveRotationEntry.O[SilentAuraTargetingModule.p(this.d2).ordinal()]) {
            case 1: {
                return SilentAuraTargetingModule.f(this.d2);
            }
            case 2: {
                return SilentAuraTargetingModule.H(this.d2);
            }
        }
        return 48.0f;
    }
}

