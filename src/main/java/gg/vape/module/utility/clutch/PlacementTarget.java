package gg.vape.module.utility.clutch;

import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.Vec3;

public class PlacementTarget {
    private BlockData cachedBlockData;
    public int Y;
    public Vec3 v;
    public final BlockData k;
    public final EnumFacing G;
    private static String token;
    public final boolean M;

    public PlacementTarget(BlockData blockData, EnumFacing enumFacing) {
        this(blockData, enumFacing, true);
    }

    public static String F() {
        return token;
    }


    public EnumFacing getFacing() {
        return this.G;
    }

    static {
        if (PlacementTarget.F() != null) {
            PlacementTarget.C("mxdNkb");
        }
    }

    public static void C(String string) {
        token = string;
    }

    public BlockData s() {
        if (this.cachedBlockData == null) {
            this.cachedBlockData = this.M && this.G != null ? this.k.R(this.G) : this.k;
        }
        return this.cachedBlockData;
    }

    public PlacementTarget(BlockData blockData, EnumFacing enumFacing, boolean bl) {
        this.k = blockData;
        this.G = enumFacing;
        this.M = bl;
    }

    public BlockData getBlockData() {
        return this.k;
    }
}

