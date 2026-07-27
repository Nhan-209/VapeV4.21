package gg.vape.module.render.hud;

import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.ui.click.frame.impl.hud.ClockHudFrame;
import gg.vape.unmap.ModeOption;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;

public class ClockHudModule
extends HudModule {
    private final ModeOption digitalMode;
    public final ModeValue L;
    public final BooleanValue k;
    private final ModeOption analogMode;
    public final BooleanValue o = BooleanValue.create(this, "24 Hour Time", false);

    public ClockHudModule() {
        super("Clock", HudModuleGroup.f, "clock_mod", ClockHudFrame.class);
        this.k = BooleanValue.create(this, "Show date", true);
        this.analogMode = new ModeOption("Analog");
        this.digitalMode = new ModeOption("Digital");
        this.L = ModeValue.create((Object)this, "Clock Type", this.analogMode, this.analogMode, this.digitalMode);
        this.setSuffix("Draws a clock with the current real-world time");
        this.L.f(this.digitalMode, this.k);
        this.addValue(this.L, this.k, this.o);
    }
}

