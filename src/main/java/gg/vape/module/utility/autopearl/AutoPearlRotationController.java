package gg.vape.module.utility.autopearl;

import gg.vape.module.utility.AutoPearl;
import gg.vape.rotation.AdaptiveRotationController;

public class AutoPearlRotationController
extends AdaptiveRotationController {
    final Float _N;
    final AutoPearl _U;

    public AutoPearlRotationController(AutoPearl cu_02, Float f) {
        this._U = cu_02;
        this._N = f;
    }

    @Override
    public void g(float f, float f2) {
        super.g(f, this._N.floatValue());
    }
}

