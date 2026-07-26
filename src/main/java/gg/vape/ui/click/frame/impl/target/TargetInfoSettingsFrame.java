package gg.vape.ui.click.frame.impl.target;

import gg.vape.event.EventListener;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.impl.hud.HudSettingsFrameBase;
import gg.vape.ui.click.frame.impl.target.TargetInfoPreviewComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoSettings;

public class TargetInfoSettingsFrame
extends HudSettingsFrameBase
implements EventListener {
    private final TargetInfoSettings oV = new TargetInfoSettings();
    private final BooleanToggleComponent o3;
    private final BooleanToggleComponent o8;
    private final TargetInfoPreviewComponent oY;
    private final BooleanToggleComponent ov;
    private final BooleanToggleComponent o6;
    private final BooleanToggleComponent ou;

    public TargetInfoSettingsFrame() {
        super("newtargetinfo", "Target Info");
        this.o8 = new BooleanToggleComponent(this.oV.l);
        this.ov = new BooleanToggleComponent(this.oV.Q);
        this.o6 = new BooleanToggleComponent(this.oV.P);
        this.o3 = new BooleanToggleComponent(this.oV.I);
        this.ou = new BooleanToggleComponent(this.oV.A);
        this.M(this.o8, this.ov, this.o6, this.o3, this.ou);
        this.oY = new TargetInfoPreviewComponent(this);
        this.h(this.oY, new Object[0]);
    }

    public TargetInfoSettings U$src$Lgg_vape_ui_click_frame_impl_target_TargetInfoSe$5b5o15() {
        return this.oV;
    }

    @Override
    public void Y() {
        this.o(100.0);
        this.H(true);
    }

    @Override
    protected void o$src$V$7f79jo() {
    }

    @Override
    public String getName() {
        return "Target Info";
    }
}

