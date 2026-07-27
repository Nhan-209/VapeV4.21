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
    final World world;
    final Clutch clutch;
    final EntityPlayerSP player;
    final HashSet allowedBlocks;
    final HashSet excludedBlocks;
    final BlockPlacementNode node;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public int t(Vector<PlacementTarget> vector) {
        return Clutch.F(this.clutch, this.world, vector);
    }

    public ClutchBlockPlacementPathSearchStrategy(Clutch clutch, HashSet hashSet, BlockPlacementNode blockPlacementNode, World world, EntityPlayerSP entityPlayerSP, HashSet hashSet2) {
        this.clutch = clutch;
        this.excludedBlocks = hashSet;
        this.node = blockPlacementNode;
        this.world = world;
        this.player = entityPlayerSP;
        this.allowedBlocks = hashSet2;
    }

    @Override
    public boolean B(BlockData blockData) {
        if (this.allowedBlocks.contains(blockData)) {
            return true;
        }
        Block block = this.world.getBlockByPos(blockData.D(), blockData.B(), blockData.G());
        boolean isPlaceable = BlockUtil.k(block) && !ClutchPlacementPathUtils.e(block);
        boolean result = isPlaceable;
        return result;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean V(BlockData blockData) {
        if (this.excludedBlocks.contains(blockData)) return false;
        if (this.node.Y.contains(blockData)) return false;
        if (!ClutchPlacementPathUtils.V(this.world, this.player, blockData)) return false;
        return true;
    }

    @Override
    public int w() {
        return 2;
    }
}

