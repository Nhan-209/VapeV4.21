package gg.vape.ui.click.frame.impl.target;

import gg.vape.ui.click.frame.impl.target.TargetInfoHealthBarComponent;
import gg.vape.ui.click.frame.impl.target.TargetInfoPreviewComponent;

public class TargetInfoPreviewHealthBarComponent
extends TargetInfoHealthBarComponent {
    final TargetInfoPreviewComponent K;

    public TargetInfoPreviewHealthBarComponent(TargetInfoPreviewComponent targetInfoPreviewComponent, int n, int n2) {
        super(n, n2);
        this.K = targetInfoPreviewComponent;
    }

    @Override
    public double M() {
        return TargetInfoPreviewComponent.X(this.K) ? 0.6 : super.M();
    }

}
