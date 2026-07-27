package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventStepHeightOverride;
import gg.vape.event.impl.EventStepSnapshot;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.Speed;
import gg.vape.threads.ResetTimerThread;
import gg.vape.wrapper.impl.CPacketPlayerPosition;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;

public class Step
extends Mod {
    private static final String NAME = "Step";


    @EventHandler
    public void v(EventStepHeightOverride eventStepHeightOverride) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (eventStepHeightOverride.getRealHeight() > 0.5 && eventStepHeightOverride.getStepHeight() > 0.0 && !entityPlayerSP.movementInput().G() && entityPlayerSP.u$src$Z$g120nz()) {
            Vape.INSTANCE.getModManager().getMod(Speed.class).setStep(-4);
            if (eventStepHeightOverride.getRealHeight() >= 0.87) {
                double d = eventStepHeightOverride.getRealHeight();
                double d2 = d * 0.42;
                double d3 = d * 0.75;
                entityPlayerSP.sendQueue().addToSendQueue(CPacketPlayerPosition.newInstance(entityPlayerSP.z(), entityPlayerSP.N() + d2, entityPlayerSP.h(), entityPlayerSP.b$src$Z$fqlxe4()));
                entityPlayerSP.sendQueue().addToSendQueue(CPacketPlayerPosition.newInstance(entityPlayerSP.z(), entityPlayerSP.N() + d3, entityPlayerSP.h(), entityPlayerSP.b$src$Z$fqlxe4()));
            }
            Minecraft.getTimer().setTimerSpeed(0.45f);
            ResetTimerThread resetTimerThread = new ResetTimerThread(this);
            resetTimerThread.start();
        }
    }

    public Step() {
        super(NAME, new Color(42, 175, 224).getRGB(), Category.w);
    }

    @Override
    public boolean isBlatantMod() {
        return true;
    }

    @EventHandler
    public void j(EventStepSnapshot eventStepSnapshot) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!entityPlayerSP.movementInput().G() && entityPlayerSP.u$src$Z$g120nz()) {
            eventStepSnapshot.setStepHeight(1.0);
        }
    }
}

