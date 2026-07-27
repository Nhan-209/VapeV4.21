package gg.vape.module.utility.autopearl;

import gg.vape.module.utility.AutoPearl;
import gg.vape.rotation.AdaptiveRotationController;

public class AutoPearlRotationController
extends AdaptiveRotationController {
    final Float pitch;
    final AutoPearl module;

    public AutoPearlRotationController(AutoPearl autoPearl, Float pitch) {
        this.module = autoPearl;
        this.pitch = pitch;
    }

    @Override
    public void g(float yaw, float pitch) {
        super.g(yaw, this.pitch.floatValue());
    }
}

