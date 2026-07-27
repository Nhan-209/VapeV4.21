package gg.vape.module.combat.aimassist;

import gg.vape.Vape;
import gg.vape.module.combat.AimAssistRotationSubModule;

public class AimAssistRotationWorkerThread
extends Thread {
    final AimAssistRotationSubModule rotationModule;

    public AimAssistRotationWorkerThread(AimAssistRotationSubModule aimAssistRotationSubModule) {
        this.rotationModule = aimAssistRotationSubModule;
    }

    @Override
    public void run() {
        while (!Vape.INSTANCE.isEnabled()) {
            try {
                Thread.sleep(1L);
                if (!this.rotationModule.r$src$Z$14eylz9() || !this.rotationModule.J$src$Z$gcqtyf()) continue;
                AimAssistRotationSubModule.n(this.rotationModule);
            }
            catch (Exception exception) {}
        }
    }
}

