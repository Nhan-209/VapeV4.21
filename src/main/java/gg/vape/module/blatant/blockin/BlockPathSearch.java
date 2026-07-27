package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.blockin.BlockPathSearchNode;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.wrapper.impl.BlockPos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class BlockPathSearch {
    int y = -1;
    private final HashMap<Long, BlockPathSearchNode> C = new HashMap();
    int q = -1;
    private final BlockPathSearchNode h;
    private final BlockPathSearchNode I;

    public BlockPathSearchNode K() {
        return this.h;
    }

    public int Z() {
        this.H();
        return this.y;
    }

    private double W(BlockPathSearchNode blockPathSearchNode, BlockPathSearchNode blockPathSearchNode2) {
        double d = blockPathSearchNode.S - blockPathSearchNode2.S;
        double d2 = blockPathSearchNode.E - blockPathSearchNode2.E;
        double d3 = blockPathSearchNode.w - blockPathSearchNode2.w;
        return d * d + d2 * d2 + d3 * d3;
    }

    public List<BlockPathSearchNode> q() {
        ArrayList<BlockPathSearchNode> arrayList = new ArrayList<BlockPathSearchNode>();
        BlockPathSearchNode blockPathSearchNode = this.I;
        if (this.I.C == null) {
            this.i();
        }
        arrayList.add(this.I);
        while (!blockPathSearchNode.equals(this.h)) {
            blockPathSearchNode = blockPathSearchNode.C;
            arrayList.add(blockPathSearchNode);
        }
        return arrayList;
    }

    public BlockPathSearchNode e() {
        return this.I;
    }

    private List<BlockPathSearchNode> j(BlockPathSearchNode blockPathSearchNode) {
        ArrayList<BlockPathSearchNode> arrayList = new ArrayList<BlockPathSearchNode>();
        double d = blockPathSearchNode.S;
        double d2 = blockPathSearchNode.E;
        double d3 = blockPathSearchNode.w;
        if (blockPathSearchNode.equals(this.h)) {
            d = (double)MathUtil.floor(d) + 0.5;
            d2 = MathUtil.floor(d2);
            d3 = (double)MathUtil.floor(d3) + 0.5;
        }
        for (int i = -1; i <= 1; ++i) {
            if (i == 0) continue;
            arrayList.add(this.a(d + (double)i, d2, d3));
            arrayList.add(this.a(d, d2 + (double)i, d3));
            arrayList.add(this.a(d, d2, d3 + (double)i));
        }
        return arrayList;
    }

    public BlockPathSearch(BlockPos blockPos, BlockPos blockPos2) {
        this(blockPos.P(), blockPos.o(), blockPos.d(), blockPos2.P(), blockPos2.o(), blockPos2.d());
    }

    public BlockPathSearch(BlockCoordinate blockCoordinate, BlockCoordinate blockCoordinate2) {
        this(blockCoordinate.B(), blockCoordinate.E(), blockCoordinate.A(), blockCoordinate2.B(), blockCoordinate2.E(), blockCoordinate2.A());
    }

    public void i() {
        ArrayList<BlockPathSearchNode> arrayList = new ArrayList<BlockPathSearchNode>();
        HashSet<BlockPathSearchNode> hashSet = new HashSet<BlockPathSearchNode>();
        arrayList.add(this.h);
        float f = RotationUtil.k(this.h.S, this.h.w, this.I.S + 0.5, this.I.w + 0.5);
        while (arrayList.size() > 0) {
            BlockPathSearchNode blockPathSearchNode = (BlockPathSearchNode)arrayList.get(0);
            for (int i = 1; i < arrayList.size(); ++i) {
                if (!(((BlockPathSearchNode)arrayList.get(i)).p() < blockPathSearchNode.p()) && (((BlockPathSearchNode)arrayList.get(i)).p() != blockPathSearchNode.p() || !(((BlockPathSearchNode)arrayList.get((int)i)).T < blockPathSearchNode.T))) continue;
                blockPathSearchNode = (BlockPathSearchNode)arrayList.get(i);
            }
            arrayList.remove(blockPathSearchNode);
            hashSet.add(blockPathSearchNode);
            if (blockPathSearchNode == this.I) {
                return;
            }
            double d = 0.0;
            double d2 = 0.0;
            for (BlockPathSearchNode blockPathSearchNode2 : this.j(blockPathSearchNode)) {
                if (hashSet.contains(blockPathSearchNode2)) continue;
                double d3 = 0.0;
                d3 += (double)(blockPathSearchNode2.E - blockPathSearchNode.E == 0.0 ? 0 : 20);
                d3 += RotationUtil.C(this.h.S, this.h.w, f, blockPathSearchNode2.S, blockPathSearchNode2.w);
                double d4 = blockPathSearchNode.j + this.W(blockPathSearchNode, blockPathSearchNode2) + (d3 += RotationUtil.V(this.h.S, this.h.w, blockPathSearchNode2.S, blockPathSearchNode2.w) * 3.0);
                if (!(d4 < blockPathSearchNode2.j) && arrayList.contains(blockPathSearchNode2)) continue;
                blockPathSearchNode2.j = d4;
                blockPathSearchNode2.T = this.W(blockPathSearchNode2, this.h);
                blockPathSearchNode2.C = blockPathSearchNode;
                if (arrayList.contains(blockPathSearchNode2)) continue;
                arrayList.add(blockPathSearchNode2);
            }
        }
    }

    private BlockPathSearchNode a(double d, double d2, double d3) {
        BlockPathSearchNode blockPathSearchNode = this.C.get(BlockPos.p(d, d2, d3));
        if (blockPathSearchNode == null) {
            blockPathSearchNode = new BlockPathSearchNode(d, d2, d3);
            this.C.put(BlockPos.p(d, d2, d3), blockPathSearchNode);
        }
        return blockPathSearchNode;
    }

    public int A() {
        this.H();
        return this.q;
    }

    public BlockPathSearch(double d, double d2, double d3, double d4, double d5, double d6) {
        this.h = this.a(d4, d5, d6);
        this.I = this.a(d, d2, d3);
    }


    private void H() {
        List<BlockPathSearchNode> list;
        if (this.y == -1 && (list = this.q()).size() > 0) {
            int n = (int)list.get((int)0).E;
            for (BlockPathSearchNode blockPathSearchNode : list) {
                if (blockPathSearchNode.E == (double)n) {
                    ++this.y;
                } else {
                    ++this.q;
                }
                n = (int)blockPathSearchNode.E;
            }
        }
    }
}

