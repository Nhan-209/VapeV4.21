package gg.vape.config;

import gg.vape.config.BuiltinProfile;
import gg.vape.module.blatant.AnchorMacro;
import gg.vape.module.combat.AimAssist;
import gg.vape.module.combat.AutoClicker;
import gg.vape.module.combat.AutoMace;
import gg.vape.module.combat.CrystalAura;
import gg.vape.module.combat.Sprint;
import gg.vape.module.utility.AutoTotem;
import gg.vape.module.utility.WindChargeJump;
import gg.vape.wrapper.impl.ForgeVersion;

public class Minecraft121BuiltinProfile
extends BuiltinProfile {
    private static final String t = "Modern PVP";

    @Override
    protected void O() {
        this.B(AutoClicker.class);
        this.B(AimAssist.class);
        this.B(AutoMace.class);
        this.B(Sprint.class);
        this.B(CrystalAura.class);
        this.B(AutoTotem.class);
        this.B(AnchorMacro.class);
        this.B(WindChargeJump.class);
    }

    public Minecraft121BuiltinProfile() {
        super(t);
    }

    @Override
    public boolean E() {
        return ForgeVersion.MC_1_21_0.d();
    }
}

