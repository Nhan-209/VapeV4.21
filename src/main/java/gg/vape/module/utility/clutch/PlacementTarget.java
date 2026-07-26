package gg.vape.module.utility.clutch;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.Vec3;

public class PlacementTarget {
    private BlockData Q;
    public int Y;
    public Vec3 v;
    public final BlockData k;
    public final EnumFacing G;
    private static String p;
    public final boolean M;

    public PlacementTarget(BlockData blockData, EnumFacing enumFacing) {
        this(blockData, enumFacing, true);
    }

    public static String F() {
        return p;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public EnumFacing c() {
        return this.G;
    }

    static {
        if (PlacementTarget.F() != null) {
            PlacementTarget.C("mxdNkb");
        }
    }

    public static void C(String string) {
        p = string;
    }

    public BlockData s() {
        if (this.Q == null) {
            this.Q = this.M && this.G != null ? this.k.R(this.G) : this.k;
        }
        return this.Q;
    }

    public PlacementTarget(BlockData blockData, EnumFacing enumFacing, boolean bl) {
        this.k = blockData;
        this.G = enumFacing;
        this.M = bl;
    }

    public BlockData q() {
        return this.k;
    }
}

