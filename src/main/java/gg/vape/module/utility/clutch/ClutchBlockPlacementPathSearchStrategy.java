package gg.vape.module.utility.clutch;

import gg.vape.module.utility.Clutch;
import gg.vape.module.utility.clutch.BlockPathSearchStrategy;
import gg.vape.module.utility.clutch.BlockPlacementNode;
import gg.vape.module.utility.clutch.ClutchPlacementPathUtils;
import gg.vape.module.utility.clutch.PlacementTarget;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.World;
import java.util.HashSet;
import java.util.Vector;

public class ClutchBlockPlacementPathSearchStrategy
implements BlockPathSearchStrategy<PlacementTarget> {
    final World p;
    final Clutch M;
    final EntityPlayerSP d;
    final HashSet q;
    final HashSet K;
    final BlockPlacementNode o;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public int t(Vector<PlacementTarget> vector) {
        return Clutch.F(this.M, this.p, vector);
    }

    public ClutchBlockPlacementPathSearchStrategy(Clutch clutch, HashSet hashSet, BlockPlacementNode blockPlacementNode, World world, EntityPlayerSP entityPlayerSP, HashSet hashSet2) {
        this.M = clutch;
        this.K = hashSet;
        this.o = blockPlacementNode;
        this.p = world;
        this.d = entityPlayerSP;
        this.q = hashSet2;
    }

    @Override
    public boolean B(BlockData blockData) {
        if (this.q.contains(blockData)) {
            return true;
        }
        Block block = this.p.getBlockByPos(blockData.D(), blockData.B(), blockData.G());
        boolean bl = BlockUtil.k(block) && !ClutchPlacementPathUtils.e(block);
        boolean bl2 = bl;
        return bl2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean V(BlockData blockData) {
        if (this.K.contains(blockData)) return false;
        if (this.o.Y.contains(blockData)) return false;
        if (!ClutchPlacementPathUtils.V(this.p, this.d, blockData)) return false;
        return true;
    }

    @Override
    public int w() {
        return 2;
    }
}

