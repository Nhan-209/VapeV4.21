package gg.vape.module.utility.clutch;

import gg.vape.module.blatant.blockin.BlockPlacementPathSegmentState;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.EnumFacing;
import java.util.ArrayList;
import java.util.HashSet;

public class BlockPlacementNode {
    public ArrayList<BlockPlacementPathSegmentState> q;
    public final ArrayList<EnumFacing> I = new ArrayList();
    public final ArrayList<EnumFacing> S = new ArrayList();
    public EnumFacing r;
    public final HashSet<BlockData> Y = new HashSet();
    public final BlockData F;
    public final EnumFacing P;
    public int d = 0;
    public final BlockData h;

    public void W(EnumFacing enumFacing) {
        this.S.add(EnumFacing.t()[enumFacing.Y()]);
        if (this.F != null) {
            this.Y.add(this.F.R(enumFacing));
        }
    }

    public BlockPlacementNode(BlockData blockData, EnumFacing enumFacing, boolean bl) {
        this.q = new ArrayList();
        this.h = blockData;
        this.P = enumFacing;
        this.F = bl ? blockData.y(0, 1, 0) : null;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean D() {
        this.q.forEach(BlockPlacementPathSegmentState::A);
        this.q.clear();
        return true;
    }

    public boolean b(EnumFacing enumFacing) {
        return this.I.contains(EnumFacing.t()[enumFacing.Y()]);
    }

    public void w(EnumFacing enumFacing) {
        this.I.add(EnumFacing.t()[enumFacing.Y()]);
        this.Y.add(this.h.R(enumFacing));
    }

    public BlockPlacementNode(BlockData blockData) {
        this(blockData, null, false);
    }

    public boolean w() {
        boolean bl = this.F != null;
        return bl;
    }

    public BlockPlacementNode(BlockData blockData, EnumFacing enumFacing) {
        this(blockData, enumFacing, true);
    }

    public boolean M(EnumFacing enumFacing) {
        return this.S.contains(EnumFacing.t()[enumFacing.Y()]);
    }
}

