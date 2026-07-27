package gg.vape.config;

import gg.vape.config.BuiltinProfile;
import gg.vape.module.blatant.Backtrack;
import gg.vape.module.combat.AimAssist;
import gg.vape.module.combat.LeftClicker;
import gg.vape.module.combat.Sprint;
import gg.vape.module.combat.VelocityPacketReceiveMode;
import gg.vape.module.combat.WTap;
import gg.vape.wrapper.impl.ForgeVersion;

public class BuiltinProfileState
extends BuiltinProfile {
    private static final String t = "Classic PVP";

    @Override
    protected void O() {
        this.B(AimAssist.class);
        this.B(LeftClicker.class);
        this.B(VelocityPacketReceiveMode.class);
        this.B(WTap.class);
        this.B(Sprint.class);
        this.B(Backtrack.class);
    }

    @Override
    public boolean E() {
        return !ForgeVersion.MC_1_21_0.d();
    }

    public BuiltinProfileState() {
        super(t);
    }

}

