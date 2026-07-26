package gg.vape.module.blatant.anchormacro;

import gg.vape.module.blatant.anchormacro.AnchorMacroStateSwitchMap;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.Vec3;

public class AnchorObstructionPlacementCandidate {
    private final BlockData R;
    private final float X;
    private final BlockData d;
    private final Vec3 K;
    private final EnumFacing H;

    public AnchorObstructionPlacementCandidate(BlockData blockData, BlockData blockData2, EnumFacing enumFacing, float f, Vec3 vec3, AnchorMacroStateSwitchMap l6_02) {
        this(blockData, blockData2, enumFacing, f, vec3);
    }

    private AnchorObstructionPlacementCandidate(BlockData blockData, BlockData blockData2, EnumFacing enumFacing, float f, Vec3 vec3) {
        this.R = blockData;
        this.d = blockData2;
        this.H = enumFacing;
        this.X = f;
        this.K = vec3;
    }

    public static EnumFacing O(AnchorObstructionPlacementCandidate iu_02) {
        return iu_02.H;
    }

    public static BlockData b(AnchorObstructionPlacementCandidate iu_02) {
        return iu_02.R;
    }

    public static BlockData k(AnchorObstructionPlacementCandidate iu_02) {
        return iu_02.d;
    }
}

