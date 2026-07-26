package gg.vape.module.utility.clutch;

import gg.vape.module.utility.Clutch;
import gg.vape.module.utility.clutch.BlockPathSearchStrategy;
import gg.vape.module.utility.clutch.BlockPlacementNode;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.World;
import java.util.Vector;

public class ClutchSolidBlockPathSearchStrategy
implements BlockPathSearchStrategy<BlockPlacementNode> {
    final World L;
    final Clutch a;

    @Override
    public int t(Vector<BlockPlacementNode> vector) {
        return Clutch.T(this.a, this.L, vector);
    }

    @Override
    public boolean B(BlockData blockData) {
        Block block = this.L.getBlockByPos(blockData.D(), blockData.B(), blockData.G());
        return BlockUtil.f(block);
    }

    public ClutchSolidBlockPathSearchStrategy(Clutch clutch, World world) {
        this.a = clutch;
        this.L = world;
    }

    @Override
    public int w() {
        return 4;
    }
}

