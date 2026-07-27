package gg.vape.module.combat.silentaura;

import gg.vape.module.combat.SilentAuraTargetingModule;
import gg.vape.module.combat.silentaura.SilentAuraAdaptiveRotationEntry;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class SilentAuraAdaptiveRotationController
extends AdaptiveRotationController {
    final SilentAuraTargetingModule targetingModule;

    private static ObfuscatedRuntimeException passThrough(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public SilentAuraAdaptiveRotationController(SilentAuraTargetingModule silentAuraTargetingModule) {
        this.targetingModule = silentAuraTargetingModule;
    }

    @Override
    public float O() {
        switch (SilentAuraAdaptiveRotationEntry.O[SilentAuraTargetingModule.p(this.targetingModule).ordinal()]) {
            case 1: {
                return SilentAuraTargetingModule.f(this.targetingModule);
            }
            case 2: {
                return SilentAuraTargetingModule.H(this.targetingModule);
            }
        }
        return 48.0f;
    }
}

