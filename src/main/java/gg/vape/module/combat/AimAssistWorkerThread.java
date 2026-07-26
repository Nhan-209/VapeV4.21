package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.module.combat.AimAssistTargetingSubModule;

public class AimAssistWorkerThread
extends Thread {
    final AimAssistTargetingSubModule A;

    @Override
    public void run() {
        while (!Vape.INSTANCE.isEnabled()) {
            try {
                Thread.sleep(1L);
                if (!this.A.r$src$Z$14eylz9() || !this.A.J$src$Z$gcqtyf()) continue;
                AimAssistTargetingSubModule.c(this.A);
            }
            catch (Exception exception) {}
        }
    }

    public AimAssistWorkerThread(AimAssistTargetingSubModule aimAssistTargetingSubModule) {
        this.A = aimAssistTargetingSubModule;
    }
}

