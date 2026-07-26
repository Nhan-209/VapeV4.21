package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.BlockIn;
import gg.vape.module.blatant.blockin.BlockPlacementPathSegment;
import gg.vape.module.utility.clutch.BlockPathSearchStrategy;
import gg.vape.module.utility.clutch.ClutchPlacementPathUtils;
import gg.vape.module.utility.clutch.PlacementTarget;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.World;
import java.util.ArrayList;
import java.util.Vector;

public class BlockInPlacementSearchStrategy
implements BlockPathSearchStrategy<PlacementTarget> {
    final int S;
    final World V;
    final EntityPlayerSP i;
    final BlockPlacementPathSegment L;
    final ArrayList u;
    final BlockIn h;
    final int W;
    final int M;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    @Override
    public boolean B(BlockData blockData) {
        Block px_12 = this.V.getBlockByPos(blockData.D(), blockData.B(), blockData.G());
        return BlockUtil.u(px_12);
    }

    @Override
    public int g(Vector<PlacementTarget> vector, int n) {
        return BlockIn.v(this.h, this.L, this.u, this.i, this.V, vector, n);
    }

    @Override
    public int w() {
        return this.M + this.W + this.S;
    }

    @Override
    public boolean V(BlockData blockData) {
        boolean bl = ClutchPlacementPathUtils.V(this.V, this.i, blockData) && !BlockIn.q(this.h).Y(blockData);
        return bl;
    }

    public BlockInPlacementSearchStrategy(BlockIn cB, int n, int n2, int n3, World world, EntityPlayerSP xH2, BlockPlacementPathSegment qo_22, ArrayList arrayList) {
        this.h = cB;
        this.M = n;
        this.W = n2;
        this.S = n3;
        this.V = world;
        this.i = xH2;
        this.L = qo_22;
        this.u = arrayList;
    }
}

