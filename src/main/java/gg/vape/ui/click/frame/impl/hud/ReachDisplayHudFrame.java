package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.module.render.hud.ReachDisplayHudModule;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.NumberFormat;

public class ReachDisplayHudFrame
extends HudModuleConfigFrameBase {
    private NumberFormat JR = new NumberFormat("0.00");
    private ReachDisplayHudModule JO = (ReachDisplayHudModule)this.l$src$Lgg_vape_module_render_hud_HudModule_$v08nt0();

    @Override
    public String getName() {
        return "ReachDisplayFrame";
    }

    @Override
    public void o() {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(1.0, true);
        String string = this.JR.format(this.JO.p());
        if (this.JO.p() == 0.0f) {
            string = "0.00";
        }
        string = string + " blocks";
        smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + 5.0, this.n() + 5.5, this.m$src$Ljava_awt_Color_$ppsp8z());
    }

    @Override
    public double A() {
        return 50.0;
    }

    @Override
    public double L() {
        return 20.0;
    }

    public ReachDisplayHudFrame() {
        super(ReachDisplayHudModule.class);
    }
}

