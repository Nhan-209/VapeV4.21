package gg.vape.ui.click.frame.impl.target;

import gg.vape.ui.click.frame.impl.target.TargetInfoEntityPreviewComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoPreviewComponent;
import gg.vape.wrapper.impl.EntityLivingBase;

public class TargetInfoLiveEntityPreviewComponent
extends TargetInfoEntityPreviewComponent {
    final TargetInfoPreviewComponent i;

    public TargetInfoLiveEntityPreviewComponent(TargetInfoPreviewComponent targetInfoPreviewComponent, double d, double d2) {
        super(d, d2);
        this.i = targetInfoPreviewComponent;
    }

    @Override
    public EntityLivingBase s() {
        return this.i.h$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1x3oue();
    }
}
