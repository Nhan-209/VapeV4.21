package gg.vape.module.combat.aimassist;

import gg.vape.Vape;
import gg.vape.module.combat.AimAssistRotationSubModule;

public class AimAssistRotationWorkerThread
extends Thread {
    final AimAssistRotationSubModule o;

    public AimAssistRotationWorkerThread(AimAssistRotationSubModule aimAssistRotationSubModule) {
        this.o = aimAssistRotationSubModule;
    }

    @Override
    public void run() {
        while (!Vape.INSTANCE.isEnabled()) {
            try {
                Thread.sleep(1L);
                if (!this.o.r$src$Z$14eylz9() || !this.o.J$src$Z$gcqtyf()) continue;
                AimAssistRotationSubModule.n(this.o);
            }
            catch (Exception exception) {}
        }
    }
}

