package gg.vape.module.utility.autopearl;

import gg.vape.module.utility.AutoPearl;
import gg.vape.rotation.PointRotationController;
import gg.vape.wrapper.impl.Vec3;

public class AutoPearlPointRotationController
extends PointRotationController {
    final Float E;
    final AutoPearl u;

    public AutoPearlPointRotationController(AutoPearl autoPearl, Vec3 vec3, Float f) {
        super(vec3);
        this.u = autoPearl;
        this.E = f;
    }

    @Override
    public void g(float f, float f2) {
        super.g(f, this.E.floatValue());
    }
}
