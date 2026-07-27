package gg.vape.module.blatant.anchormacro;

import gg.vape.module.blatant.anchormacro.AnchorMacroStateSwitchMap;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.Vec3;

public class AnchorObstructionPlacementCandidate {
    private final BlockData block;
    private final float score;
    private final BlockData obstructionBlock;
    private final Vec3 hitVec;
    private final EnumFacing facing;

    public AnchorObstructionPlacementCandidate(BlockData blockData, BlockData blockData2, EnumFacing enumFacing, float f, Vec3 vec3, AnchorMacroStateSwitchMap marker) {
        this(blockData, blockData2, enumFacing, f, vec3);
    }

    private AnchorObstructionPlacementCandidate(BlockData blockData, BlockData blockData2, EnumFacing enumFacing, float f, Vec3 vec3) {
        this.block = blockData;
        this.obstructionBlock = blockData2;
        this.facing = enumFacing;
        this.score = f;
        this.hitVec = vec3;
    }

    public static EnumFacing O(AnchorObstructionPlacementCandidate candidate) {
        return candidate.facing;
    }

    public static BlockData b(AnchorObstructionPlacementCandidate candidate) {
        return candidate.block;
    }

    public static BlockData k(AnchorObstructionPlacementCandidate candidate) {
        return candidate.obstructionBlock;
    }
}

