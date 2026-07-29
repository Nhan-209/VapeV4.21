package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.module.combat.AimAssistTargetingSubModule;

public class AimAssistWorkerThread
extends Thread {
    private final AimAssistTargetingSubModule targetingSubModule;

    @Override
    public void run() {
        while (!Vape.INSTANCE.isEnabled()) {
            try {
                Thread.sleep(1L);
                if (!this.targetingSubModule.r$src$Z$14eylz9() || !this.targetingSubModule.J$src$Z$gcqtyf()) continue;
                AimAssistTargetingSubModule.runWorkerTick(this.targetingSubModule);
            }
            catch (Exception ignored) {}
        }
    }

    public AimAssistWorkerThread(AimAssistTargetingSubModule targetingSubModule) {
        this.targetingSubModule = targetingSubModule;
    }
}

