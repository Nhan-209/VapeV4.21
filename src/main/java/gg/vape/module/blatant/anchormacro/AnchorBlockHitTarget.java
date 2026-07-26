package gg.vape.module.blatant.anchormacro;

import gg.vape.module.blatant.anchormacro.AnchorMacroStateSwitchMap;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.EnumFacing;

public class AnchorBlockHitTarget {
    private final BlockPos P;
    private final EnumFacing v;

    public static EnumFacing O(AnchorBlockHitTarget zk_22) {
        return zk_22.v;
    }

    private AnchorBlockHitTarget(BlockPos blockPos, EnumFacing enumFacing) {
        this.P = blockPos;
        this.v = enumFacing;
    }

    public static BlockPos q(AnchorBlockHitTarget zk_22) {
        return zk_22.P;
    }

    public AnchorBlockHitTarget(BlockPos blockPos, EnumFacing enumFacing, AnchorMacroStateSwitchMap l6_02) {
        this(blockPos, enumFacing);
    }
}

