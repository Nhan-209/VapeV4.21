package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.BlockIn;
import gg.vape.module.utility.clutch.BlockPathSearchStrategy;
import gg.vape.module.utility.clutch.PlacementTarget;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.EnumFacing;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class BlockInSearchPlanner {
    private static EnumFacing[] w;
    private final BlockPathSearchStrategy<PlacementTarget> d;
    private int b;
    private static EnumFacing[] q;
    private final Map<BlockData, Vector<PlacementTarget>> M;
    private static final long c;
    private long p;

    public BlockInSearchPlanner(BlockPathSearchStrategy<PlacementTarget> blockPathSearchStrategy) {
        this.d = blockPathSearchStrategy;
        this.p = 0L;
        this.b = 0;
        this.M = new HashMap<BlockData, Vector<PlacementTarget>>();
        if (w == null) {
            w = EnumFacing.t();
            q = EnumFacing.c$src$ALgg_vape_wrapper_impl_EnumFacing_$1i3g4ft();
        }
    }

    public Vector<PlacementTarget> P(BlockData blockData, BlockData blockData2) {
        this.M.clear();
        long l = System.nanoTime();
        this.b = 0;
        Vector<PlacementTarget> vector = this.F(blockData, blockData2, 0, new Vector<PlacementTarget>());
        long l2 = System.nanoTime();
        this.p = l2 - l;
        return vector;
    }

    public long f() {
        return this.p;
    }


    static {
        c = -1361127528260960257L;
    }

    public int P() {
        return this.b;
    }

    public Vector<PlacementTarget> F(BlockData blockData, BlockData blockData2, int n, Vector<PlacementTarget> vector) {
        ++this.b;
        if (this.M.containsKey(blockData)) {
            return this.M.get(blockData);
        }
        if (n > this.d.w()) {
            return null;
        }
        int n4 = blockData2.D() - blockData.D();
        int n5 = blockData2.B() - blockData.B();
        int n6 = blockData2.G() - blockData.G();
        int n7 = n4 > 0 ? 5 : (n4 < 0 ? 4 : -1);
        int n8 = n5 > 0 ? 1 : (n5 < 0 ? 0 : -1);
        int n9 = n6 > 0 ? 3 : (n6 < 0 ? 2 : -1);
        int[] nArray = new int[]{n7, n8, n9};
        ArrayList<PlacementTarget> arrayList = new ArrayList<PlacementTarget>();
        for (int n10 : nArray) {
            if (n10 == -1) continue;
            EnumFacing enumFacing = w[n10];
            BlockData blockData3 = blockData.R(enumFacing);
            if (!this.d.B(blockData3)) continue;
            PlacementTarget placementTarget = new PlacementTarget(blockData, enumFacing);
            placementTarget.Y = n;
            arrayList.add(placementTarget);
        }
        arrayList.sort(Comparator.comparingInt(arg_0 -> this.lambda$recurFindPlacePathTargets$0(vector, n, arg_0)));
        Vector<PlacementTarget> bestPath = null;
        int n11 = (int)c;
        for (PlacementTarget placementTarget : arrayList) {
            BlockData nextBlock = placementTarget.k.R(placementTarget.G);
            Vector<PlacementTarget> path = new Vector<PlacementTarget>(vector);
            path.add(placementTarget);
            if (nextBlock.equals(blockData2)) {
                bestPath = path;
                break;
            }
            Vector<PlacementTarget> recursivePath = this.F(nextBlock, blockData2, n + 1, path);
            if (recursivePath != null && !recursivePath.isEmpty()) {
                path = new Vector<PlacementTarget>(recursivePath);
            }
            if (path.isEmpty()) break;
            if (n == 0) {
                BlockIn.O.add(new Vector<PlacementTarget>(path));
            }
            int score = this.d.g(path, n);
            if (bestPath != null && score >= n11) continue;
            bestPath = path;
            n11 = score;
        }
        this.M.put(blockData, bestPath);
        return bestPath;
    }

    private int lambda$recurFindPlacePathTargets$0(Vector vector, int n, PlacementTarget placementTarget) {
        Vector<PlacementTarget> vector2 = new Vector<PlacementTarget>(vector);
        vector2.add(placementTarget);
        return this.d.g(vector2, n);
    }
}
