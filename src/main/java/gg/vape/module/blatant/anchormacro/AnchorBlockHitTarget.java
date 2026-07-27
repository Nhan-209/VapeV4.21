package gg.vape.module.blatant.anchormacro;

import gg.vape.module.blatant.anchormacro.AnchorMacroStateSwitchMap;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.EnumFacing;

public class AnchorBlockHitTarget {
    private final BlockPos hitPos;
    private final EnumFacing hitFace;

    public static EnumFacing O(AnchorBlockHitTarget target) {
        return target.hitFace;
    }

    private AnchorBlockHitTarget(BlockPos blockPos, EnumFacing enumFacing) {
        this.hitPos = blockPos;
        this.hitFace = enumFacing;
    }

    public static BlockPos q(AnchorBlockHitTarget target) {
        return target.hitPos;
    }

    public AnchorBlockHitTarget(BlockPos blockPos, EnumFacing enumFacing, AnchorMacroStateSwitchMap marker) {
        this(blockPos, enumFacing);
    }
}

