package gg.vape.module.blatant.blockin;

import gg.vape.Vape;
import gg.vape.module.utility.clutch.PlacementTarget;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.EnumFacing;
import java.util.Collection;
import java.util.HashSet;
import java.util.Vector;
import org.jetbrains.annotations.NotNull;

public class BlockPlacementPathSegmentState {
    private static final String DEBUG_PREFIX = "isFirst: ";
    public EnumFacing W;
    public Vector<PlacementTarget> M;
    private int expectedCount;
    public HashSet<Long> R;

    public boolean E(BlockData blockData) {
        return this.R.contains(this.posKeyFromData(blockData));
    }

    private long posKeyFromData(BlockData blockData) {
        return BlockPos.f(blockData.D(), blockData.B(), blockData.G());
    }

    public void Z(@NotNull Collection<PlacementTarget> collection) {
        this.M.addAll(collection);
        for (PlacementTarget placementTarget : collection) {
            this.R.add(this.posKeyFromData(placementTarget.s()));
        }
        this.expectedCount += collection.size();
    }

    public void A() {
        this.M.clear();
    }

    public BlockPlacementPathSegmentState(@NotNull EnumFacing enumFacing, @NotNull Vector<PlacementTarget> vector) {
        this.W = enumFacing;
        this.M = vector;
        this.R = new HashSet();
        for (PlacementTarget placementTarget : vector) {
            this.R.add(this.posKeyFromData(placementTarget.s()));
        }
        this.expectedCount = vector.size();
    }

    public boolean C() {
        Vape.debugLog(DEBUG_PREFIX + this.M.size() + " " + this.expectedCount);
        return this.M.size() == this.expectedCount;
    }


    private long posKeyFromCoords(int n, int n2, int n3) {
        return BlockPos.f(n, n2, n3);
    }

    public boolean X(int n, int n2, int n3) {
        return this.R.contains(this.posKeyFromCoords(n, n2, n3));
    }

    public int T() {
        return this.expectedCount;
    }
}

