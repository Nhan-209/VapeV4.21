package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.blockin.BlockInTargetRotationState;
import gg.vape.module.blatant.blockin.BlockPlacementPathSegmentState;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.Vec3d;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.wrapper.impl.Vec3;
import java.util.ArrayList;
import java.util.Vector;

public class BlockPlacementPathSegment {
    public final BlockCoordinate t;
    public final ArrayList<Vec3d> b;
    private static final String LEFT_SUFFIX = " (Left: ";
    public BlockCoordinate R;
    public String p = null;
    public final Vector<BlockInTargetRotationState> P = new Vector();
    public int a = 0;
    public Vec3 V;
    public BlockPlacementPathSegmentState g;

    public int w() {
        return this.g != null ? this.g.M.size() : 0;
    }

    public boolean u() {
        return this.p != null;
    }

    public int C() {
        return this.g != null ? this.g.T() : 0;
    }

    public BlockPlacementPathSegment(BlockCoordinate blockCoordinate, BlockCoordinate blockCoordinate2, ArrayList<Vec3d> arrayList) {
        this.t = blockCoordinate;
        this.R = blockCoordinate2;
        this.b = arrayList;
    }

    private static ObfuscatedRuntimeException passThroughException(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void I(String string) {
        if (this.p == null) {
            this.p = string + LEFT_SUFFIX + this.w() + ")";
            this.V = null;
            this.g = null;
        }
    }
}

