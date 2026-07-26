package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.BlockIn;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.PlayerMouseRotationApplier;
import gg.vape.rotation.RotationAngles;
import gg.vape.wrapper.impl.EntityPlayer;

public class EntityFixedRotationController
extends FixedRotationController {
    final EntityPlayer D;
    final BlockIn z;

    public EntityFixedRotationController(BlockIn blockIn, RotationAngles rotationAngles, EntityPlayer entityPlayer) {
        super(rotationAngles);
        this.z = blockIn;
        this.D = entityPlayer;
    }

    @Override
    public void w(float f, float f2) {
        PlayerMouseRotationApplier.L(this.D, f, f2);
    }

    @Override
    public float d() {
        return this.D.V();
    }

    @Override
    public float k() {
        return this.D.J();
    }
}
