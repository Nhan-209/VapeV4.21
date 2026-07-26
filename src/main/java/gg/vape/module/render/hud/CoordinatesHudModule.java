package gg.vape.module.render.hud;

import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.ui.click.frame.impl.hud.CoordinatesHudFrame;
import gg.vape.unmap.ModeOption;
import gg.vape.value.ModeValue;

public class CoordinatesHudModule
extends HudModule {
    public final ModeOption U = new ModeOption("Vertical");
    public final ModeOption b = new ModeOption("Horizontal");
    public final ModeValue p = ModeValue.create((Object)this, "Display Type", this.b, this.b, this.U);

    public CoordinatesHudModule() {
        super("Coords", HudModuleGroup.f, "coords", CoordinatesHudFrame.class);
        this.setSuffix("Shows your current XYZ coordinates");
        this.addValue(this.p);
    }
}

