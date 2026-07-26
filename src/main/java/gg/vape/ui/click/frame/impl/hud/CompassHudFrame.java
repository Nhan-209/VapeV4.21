package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.render.hud.CompassHudModule;
import gg.vape.ui.click.frame.impl.hud.CompassStripComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;

public class CompassHudFrame
extends HudModuleConfigFrameBase {
    private static final String tb = "CompassFrame";

    @Override
    public void o() {
    }

    public CompassHudFrame() {
        super(CompassHudModule.class);
        this.h(new CompassStripComponent(this), new Object[0]);
    }

    @Override
    public double L() {
        return 30.0;
    }

    @Override
    public double A() {
        return 308.0;
    }

    @Override
    public String getName() {
        return tb;
    }
}

