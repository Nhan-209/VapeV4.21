package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.BlockIn;
import gg.vape.rotation.ThresholdFixedRotationController;
import gg.vape.wrapper.impl.EntityPlayerSP;

public class BlockInThresholdRotationController
extends ThresholdFixedRotationController {
    final BlockIn g;

    @Override
    public void u(boolean bl) {
        super.u(bl);
        if (bl) {
            if (BlockIn.E(this.g).X(this.g)) {
                // empty if block
            }
            BlockIn.B(this.g, -999.0);
        }
    }


    public BlockInThresholdRotationController(BlockIn blockIn, EntityPlayerSP entityPlayerSP, float f, float f2) {
        super(entityPlayerSP, f, f2);
        this.g = blockIn;
    }
}

