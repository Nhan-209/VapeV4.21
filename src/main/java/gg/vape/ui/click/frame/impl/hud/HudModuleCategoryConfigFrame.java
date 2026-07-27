package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.render.hud.HudModuleCategoryEntry;
import gg.vape.ui.click.component.ZeroSizeNoOpGuiComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import java.util.ArrayList;

public class HudModuleCategoryConfigFrame
extends HudModuleConfigFrameBase {
    private static final String tb = "CooldownFrame";
    private ArrayList<ZeroSizeNoOpGuiComponent> sL = new ArrayList();

    @Override
    public double L() {
        int n = this.sL.size();
        if (n == 0) {
            return 55.0;
        }
        return 25 + n * 30;
    }

    @Override
    public void o() {
    }

    public HudModuleCategoryConfigFrame() {
        super(HudModuleCategoryEntry.class);
    }


    @Override
    public String getName() {
        return tb;
    }

    @Override
    public double A() {
        return 55.0;
    }
}

