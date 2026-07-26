package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.render.hud.ScoreboardHudModule;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.utils.Vec3d;

public class ScoreboardHudFrame
extends HudModuleConfigFrameBase {
    private ScoreboardHudModule HP = (ScoreboardHudModule)this.l$src$Lgg_vape_module_render_hud_HudModule_$v08nt0();
    private static final String tb = "Scoreboard";

    @Override
    public String getName() {
        return tb;
    }

    public ScoreboardHudFrame() {
        super(ScoreboardHudModule.class);
    }

    @Override
    public void o() {
        Vec3d vec3d = this.HP.K(this.G$src$D$1b2f02a(), this.n(), this.D$src$Z$1t1ofzo());
        this.o(vec3d.H + 2.0);
        this.Y(vec3d.B);
    }

    @Override
    public double L() {
        return super.L();
    }

    @Override
    public double A() {
        return super.A();
    }

    @Override
    public boolean m() {
        return false;
    }
}

